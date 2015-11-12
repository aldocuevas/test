package com.boutique.reportes.facturacion.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 
/**
 * DTO que refleja la informacion de una factura de venta al publico en general.
 * 
 * @author aldo
 * 
 */
public class FacturaDatosGeneralesDTO extends StringFriendlyDTO{
	private Date fecha;
	private List<ProductoFacturadoDTO> productosFacturados;
	private TotalesFacturaDTO totales;
	private TotalesPorFormaDePagoDTO totalesPorFormaDePago;

	public List<ProductoFacturadoDTO> getProductosFacturados() {
		if (productosFacturados == null) {
			productosFacturados = new ArrayList<ProductoFacturadoDTO>();
		}
		return productosFacturados;
	}

	public void setProductosFacturados(
			List<ProductoFacturadoDTO> productosFacturados) {
		this.productosFacturados = productosFacturados;
	}

	public TotalesFacturaDTO getTotales() {
		return totales;
	}

	public void setTotales(TotalesFacturaDTO totales) {
		this.totales = totales;
	}

	public TotalesPorFormaDePagoDTO getTotalesPorFormaDePago() {
		return totalesPorFormaDePago;
	}

	public void setTotalesPorFormaDePago(
			TotalesPorFormaDePagoDTO totalesPorFormaDePago) {
		this.totalesPorFormaDePago = totalesPorFormaDePago;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}
	 

}
