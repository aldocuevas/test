package com.boutique.domain;

public class PagoTarjetaCredito extends Pago {
	private String banco;
	private int meses;

	public String getBanco() {
		return banco;
	}

	public int getMeses() {
		return meses;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}
}
