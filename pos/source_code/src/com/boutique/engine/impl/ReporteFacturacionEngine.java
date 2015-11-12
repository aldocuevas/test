package com.boutique.engine.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math.util.MathUtils;

import com.boutique.dao.DaoClientesCentral;
import com.boutique.dao.DaoColonias;
import com.boutique.dao.DaoInventarios;
import com.boutique.dao.DaoVentas;
import com.boutique.domain.Cliente;
import com.boutique.domain.Colonia;
import com.boutique.domain.Pago;
import com.boutique.domain.PagoCheque;
import com.boutique.domain.PagoTarjetaCredito;
import com.boutique.domain.PagoTransferenciaBancaria;
import com.boutique.domain.PagoVale;
import com.boutique.domain.Producto;
import com.boutique.domain.ProductoVendido;
import com.boutique.domain.Venta;
import com.boutique.domain.VentaTypeEnum;
import com.boutique.helper.IvaHelper;
import com.boutique.reportes.facturacion.dto.DatosClienteDTO;
import com.boutique.reportes.facturacion.dto.DomicilioDTO;
import com.boutique.reportes.facturacion.dto.FacturaClienteIndividualDTO;
import com.boutique.reportes.facturacion.dto.FacturaDatosGeneralesDTO;
import com.boutique.reportes.facturacion.dto.ProductoFacturadoDTO;
import com.boutique.reportes.facturacion.dto.TotalesFacturaDTO;
import com.boutique.reportes.facturacion.dto.TotalesPorFormaDePagoDTO;

public class ReporteFacturacionEngine {

	public List<FacturaDatosGeneralesDTO> getReporteFacturaGeneralPorFecha(
			Date fecha) {
		List<Venta> ventas = DaoVentas.findVentasFacturaGeneralByFecha(fecha);
		List<FacturaDatosGeneralesDTO> list = new ArrayList<FacturaDatosGeneralesDTO>();
		FacturaDatosGeneralesDTO dto = new FacturaDatosGeneralesDTO();
		dto.setFecha(fecha);
		if (CollectionUtils.isNotEmpty(ventas)) {
			for (Venta venta : ventas) {
				addProductosFacturados(dto, venta);
			}
			list.add(dto);

		}
		return list;
	}

	public List<FacturaClienteIndividualDTO> getReporteFacturasPorCliente(
			Date fecha, VentaTypeEnum tipoVenta) {
		List<Venta> ventas = null;
		if (VentaTypeEnum.CONTADO.equals(tipoVenta)) {
			ventas = DaoVentas.findVentasFacturaPorClienteContado(fecha);
		} else if (VentaTypeEnum.CREDITO.equals(tipoVenta)) {
			ventas = DaoVentas.findVentasFacturaPorClienteByFechaCredito(fecha);
		} else if (VentaTypeEnum.APARTADO.equals(tipoVenta)) {
			ventas = DaoVentas
					.findVentasFacturaPorClienteByFechaApartado(fecha);
		}

		List<FacturaClienteIndividualDTO> facturasIndividuales = new ArrayList<FacturaClienteIndividualDTO>();
		FacturaClienteIndividualDTO dto;
		if (CollectionUtils.isNotEmpty(ventas)) {
			for (Venta venta : ventas) {
				dto = new FacturaClienteIndividualDTO();

				Cliente cliente = DaoClientesCentral.findById(venta
						.getIdCliente());
				dto.setCliente(copiarDatosCliente(cliente));
				FacturaDatosGeneralesDTO fGenerales = new FacturaDatosGeneralesDTO();
				fGenerales.setFecha(fecha);
				addProductosFacturados(fGenerales, venta);
				List<FacturaDatosGeneralesDTO> lFGenerales = new ArrayList<FacturaDatosGeneralesDTO>();
				lFGenerales.add(fGenerales);
				dto.setDatosFactura(lFGenerales);
				facturasIndividuales.add(dto);
				if (venta.isRequiereFacturaIndividual()) {
					dto.setEnviarPorCorreo("SI");
				} else {
					dto.setEnviarPorCorreo("NO");
				}
			}
		}
		return facturasIndividuales;

	}

	private DatosClienteDTO copiarDatosCliente(Cliente cliente) {
		DatosClienteDTO dto = new DatosClienteDTO();
		dto.setNombreCompleto(cliente.getNombre() + " "
				+ cliente.getApellidoPaterno() + " "
				+ cliente.getApellidoMaterno());
		dto.setCorreoElectronico(cliente.getEmail());
		dto.setRfc(cliente.getRfc());
		dto.setTelefono(cliente.getTelefono());
		dto.setDomicilio(copiarDomicilio(cliente));
		return dto;
	}

	private DomicilioDTO copiarDomicilio(Cliente cliente) {
		DomicilioDTO dto = new DomicilioDTO();
		dto.setCalle(cliente.getCalle());
		dto.setNumeroInterior(cliente.getNumeroInterior());
		dto.setNumeroExterior(cliente.getNumero());
		dto.setColonia(cliente.getColonia());
		Colonia c = DaoColonias.findByNombreUnico(cliente.getColonia());
		if (null != c) {

			dto.setCiudad(c.getCiudad());
			dto.setCodigoPostal(c.getCp());
			dto.setEstado(c.getEstado());
		}
		return dto;
	}

	private void addProductosFacturados(FacturaDatosGeneralesDTO dto,
			Venta venta) {

		for (ProductoVendido pv : venta.getProductosVendidos()) {
			Producto p = DaoInventarios.findById(pv.getIdInventario());
			ProductoFacturadoDTO pf = new ProductoFacturadoDTO();
			pf.setCantidad("1");
			pf.setCodigo(p.getEtiqueta());
			pf.setProducto(AppInstance.idToTipoProducto.get(String.valueOf(p
					.getIdTipoProducto())));
			pf.setEstilo(p.getEstilo());
			pf.setPrecioUnitario(IvaHelper.getMontoSinIva(pv.getPrecioFinal()));
			pf.setPrecioTotal(pf.getPrecioUnitario());
			dto.getProductosFacturados().add(pf);
			addPrecioFinalToTotales(dto,  pv);
		}
		if (CollectionUtils.isNotEmpty(venta.getPagos())) {
			for (Pago pago : venta.getPagos()) {
				addTotalesPorFormaDePago(dto, pago);
			}
		} else {
			dto.setTotalesPorFormaDePago(new TotalesPorFormaDePagoDTO());
		}

	}

	private void addTotalesPorFormaDePago(FacturaDatosGeneralesDTO dto,
			Pago pago) {
		TotalesPorFormaDePagoDTO tfp = dto.getTotalesPorFormaDePago();
		if (null == tfp) {
			tfp = new TotalesPorFormaDePagoDTO();
		}
		if (pago instanceof PagoTarjetaCredito) {
			tfp.setTarjeta(tfp.getTarjeta() + pago.getMonto());
		} else if (pago instanceof PagoVale) {
			tfp.setVale(tfp.getVale() + pago.getMonto());
		} else if (pago instanceof PagoCheque) {
			tfp.setCheque(tfp.getCheque() + pago.getMonto());
		} else if (pago instanceof PagoTransferenciaBancaria) {
			tfp.setDepoOTransf(tfp.getDepoOTransf() + pago.getMonto());
		} else if (pago instanceof Pago) {
			tfp.setEfectivo(tfp.getEfectivo() + pago.getMonto());
		}
		dto.setTotalesPorFormaDePago(tfp);
	}

	private void addPrecioFinalToTotales(FacturaDatosGeneralesDTO dto,
			  ProductoVendido pv) {
		TotalesFacturaDTO totales = dto.getTotales();
		if (totales == null) {
			totales = new TotalesFacturaDTO();
		}
		totales.setSubTotal(totales.getSubTotal()
				+ (pv.getPrecioFinal() - pv.getImpuestoPrecioFinal()));
		totales.setIva(totales.getIva() + pv.getImpuestoPrecioFinal());
		totales.setTotal(totales.getTotal() + pv.getPrecioFinal());
		dto.setTotales(totales);
	}
}
