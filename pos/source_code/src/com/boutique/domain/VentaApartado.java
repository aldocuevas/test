package com.boutique.domain;

import java.sql.*;

public class VentaApartado extends Venta {
	private String cliente;
	private String domicilio;
	private String telefono;
	private Timestamp fechaFinalizacion;
	private double montoPendiente = 0;

	public VentaApartado() {
		super();
		setVentaTypeEnum(VentaTypeEnum.APARTADO);
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFechaFinalizacion(Timestamp fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public void setMontoPendiente(double montoPendiente) {
		this.montoPendiente = montoPendiente;
	}

	public String getCliente() {
		return cliente;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public Timestamp getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public double getMontoPendiente() {
		return montoPendiente;
	}

}
