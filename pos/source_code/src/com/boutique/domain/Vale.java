package com.boutique.domain;

public class Vale {
	private int id;
	private double monto;
	private double numero;
	private int utilizado;
	private int idPago;
	private double montoUtilizado;
	private int idTerminalCreacion;

	public int getId() {
		return id;
	}

	public double getMonto() {
		return monto;
	}

	public double getNumero() {
		return numero;
	}

	public int getUtilizado() {
		return utilizado;
	}

	public int getIdPago() {
		return idPago;
	}

	public double getMontoUtilizado() {
		return montoUtilizado;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public void setUtilizado(int utilizado) {
		this.utilizado = utilizado;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public void setMontoUtilizado(double montoUtilizado) {
		this.montoUtilizado = montoUtilizado;
	}

	public void setIdTerminalCreacion(int idTerminalCreacion) {
		this.idTerminalCreacion = idTerminalCreacion;
	}

	public int getIdTerminalCreacion() {
		return idTerminalCreacion;
	}

	 
}
