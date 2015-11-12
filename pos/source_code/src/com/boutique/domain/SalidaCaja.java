package com.boutique.domain;

public class SalidaCaja {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getMonto() {
		return monto;
	}

	private String concepto;
	private double monto;
}
