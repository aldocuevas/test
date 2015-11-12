package com.boutique.reportes.facturacion.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.boutique.reportes.facturacion.dto.DatosClienteDTO;
import com.boutique.reportes.facturacion.dto.DomicilioDTO;
import com.boutique.reportes.facturacion.dto.FacturaClienteIndividualDTO;
import com.boutique.reportes.facturacion.dto.FacturaDatosGeneralesDTO;
import com.boutique.reportes.facturacion.dto.ProductoFacturadoDTO;
import com.boutique.reportes.facturacion.dto.TotalesFacturaDTO;
import com.boutique.reportes.facturacion.dto.TotalesPorFormaDePagoDTO;

public class FacturactionMockFactory {

	public static List<FacturaDatosGeneralesDTO> getFacturaGeneral() {
		List<FacturaDatosGeneralesDTO> list = new ArrayList<FacturaDatosGeneralesDTO>();
		FacturaDatosGeneralesDTO factura = new FacturaDatosGeneralesDTO();
		factura.setFecha(new java.util.Date());
		factura.setProductosFacturados(createProductosFacturados());
		factura.setTotales(createTotales());
		factura.setTotalesPorFormaDePago(createTotalesPorFormaDePago());
		list.add(factura);
		return list;
	}

	public static List<FacturaClienteIndividualDTO> getFacturasPorCliente() {
		List<FacturaClienteIndividualDTO> facturasPorCliente = new ArrayList<FacturaClienteIndividualDTO>();
		facturasPorCliente.add(createFacturaClienteIndividual("1"));
		facturasPorCliente.add(createFacturaClienteIndividual("2"));
		facturasPorCliente.add(createFacturaClienteIndividual("3"));
		facturasPorCliente.add(createFacturaClienteIndividual("4"));
		facturasPorCliente.add(createFacturaClienteIndividual("5"));
		facturasPorCliente.add(createFacturaClienteIndividual("6"));
		facturasPorCliente.add(createFacturaClienteIndividual("7"));
		return facturasPorCliente;
	}

	private static FacturaClienteIndividualDTO createFacturaClienteIndividual(
			String suffix) {
		FacturaClienteIndividualDTO dto = new FacturaClienteIndividualDTO();
		dto.setEnviarPorCorreo("SI");
		dto.setCliente(createDatosCliente(suffix));
		dto.setDatosFactura(getFacturaGeneral());
		return dto;
	}

	private static DatosClienteDTO createDatosCliente(String suffix) {
		DatosClienteDTO dto = new DatosClienteDTO();
		dto.setNombreCompleto("nombreCompleto " + suffix);
		dto.setTelefono("8198886657" + suffix);
		dto.setRfc("CUAA820098L2" + suffix);
		dto.setDomicilio(createDomicilio(suffix));
		dto.setCorreoElectronico("correo@patata.com");
		return dto;
	}

	private static DomicilioDTO createDomicilio(String suffix) {
		DomicilioDTO dto = new DomicilioDTO();
		dto.setCalle("calle");
		dto.setNumeroInterior("123");
		dto.setNumeroExterior("321");
		dto.setEstado("estado");
		dto.setCiudad("ciudad");
		dto.setCodigoPostal("28050");
		dto.setColonia("colonia");
		return dto;
	}

	private static TotalesPorFormaDePagoDTO createTotalesPorFormaDePago() {
		TotalesPorFormaDePagoDTO dto = new TotalesPorFormaDePagoDTO();
		dto.setEfectivo(200.58);
		dto.setCheque(300.18);
		dto.setTarjeta(250.20);
		dto.setVale(283.00);
		dto.setDepoOTransf(965);
		return dto;
	}

	private static TotalesFacturaDTO createTotales() {
		TotalesFacturaDTO totales = new TotalesFacturaDTO();
		totales.setSubTotal(1000);
		totales.setIva(160);
		totales.setTotal(1160);
		return totales;
	}

	public static List<ProductoFacturadoDTO> createProductosFacturados() {
		List<ProductoFacturadoDTO> productos = new ArrayList<ProductoFacturadoDTO>();
		productos.add(crearProducto("1"));
		productos.add(crearProducto("2"));
		productos.add(crearProducto("3"));
		productos.add(crearProducto("4"));
		productos.add(crearProducto("5"));
		productos.add(crearProducto("6"));
		productos.add(crearProducto("7"));
		productos.add(crearProducto("8"));
		productos.add(crearProducto("9"));
		productos.add(crearProducto("10"));
		return productos;
	}

	private static ProductoFacturadoDTO crearProducto(String suffix) {
		ProductoFacturadoDTO producto = new ProductoFacturadoDTO();
		producto.setCantidad("1" + suffix);
		producto.setCodigo("133249CH" + suffix);
		producto.setEstilo("estilo" + suffix);
		producto.setPrecioUnitario(35.00);
		producto.setProducto("falda" + suffix);
		producto.setPrecioTotal(70);

		return producto;
	}

}
