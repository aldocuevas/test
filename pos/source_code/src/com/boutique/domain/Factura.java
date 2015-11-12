package com.boutique.domain;

import java.sql.Timestamp;

 
public class Factura {
	private Timestamp fecha;
	private String noFactura;
	private int id;
	private int idProveedor;
	private int tipoPago;

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNoFactura() {
		return noFactura;
	}

	public void setNoFactura(String noFactura) {
		this.noFactura = noFactura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	private String banco;
}
