package com.boutique.domain;

public class PagoTransferenciaBancaria extends Pago {
	private String noReferencia;

	public void setNoReferencia(String noReferencia) {
		this.noReferencia = noReferencia;
	}

	public String getNoReferencia() {
		return noReferencia;
	}
}
