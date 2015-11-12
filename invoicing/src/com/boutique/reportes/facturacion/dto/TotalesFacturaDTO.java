package com.boutique.reportes.facturacion.dto;

import java.math.BigDecimal;

/**
 * Representa los montos totales de una factura.
 * 
 * @author aldo
 * 
 */
public class TotalesFacturaDTO extends StringFriendlyDTO {
	private double subTotal=0;
	private double iva=0;
	private double total=0;

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
