package com.boutique.reportes.facturacion.dto;

public class TotalesPorFormaDePagoDTO extends StringFriendlyDTO {
	private double efectivo=0.0;
	private double tarjeta=0.0;
	private double cheque=0.0;
	private double vale=0.0;

	public double getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(double efectivo) {
		this.efectivo = efectivo;
	}

	public double getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(double tarjeta) {
		this.tarjeta = tarjeta;
	}

	public double getCheque() {
		return cheque;
	}

	public void setCheque(double cheque) {
		this.cheque = cheque;
	}

	public double getVale() {
		return vale;
	}

	public void setVale(double vale) {
		this.vale = vale;
	}

	public void setDepoOTransf(double depoOTransf) {
		this.depoOTransf = depoOTransf;
	}

	public double getDepoOTransf() {
		return depoOTransf;
	}

	private double depoOTransf;

}
