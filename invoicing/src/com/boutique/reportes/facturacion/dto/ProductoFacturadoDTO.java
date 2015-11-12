package com.boutique.reportes.facturacion.dto;

 
/**
 * Corresponde a un producto en una factura.
 * 
 * @author aldo
 * 
 */
public class ProductoFacturadoDTO extends StringFriendlyDTO {
	private String cantidad;
	private String codigo;
	private String producto;
	private String estilo;
	private double precioUnitario;
	private double precioTotal;

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	 
}
