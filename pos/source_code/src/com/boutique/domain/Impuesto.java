package com.boutique.domain;

public class Impuesto {
	private int id;
	private String concepto;
	private double porcentaje;
	private String cd;
	private boolean activo;
	private double porcentajeMultiplicativo = -1;
	private double porcentajeDivisivo = -1;

	public double getPorcentajeMultiplicativo() {
		if (porcentajeMultiplicativo == -1) {
			porcentajeMultiplicativo = porcentaje / 100;
		}
		return porcentajeMultiplicativo;
	}

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

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public double getPorcentajeDivisivo() {
		if (porcentajeDivisivo == -1) {
			porcentajeDivisivo = 1 + (porcentaje / 100);
		}
		return porcentajeDivisivo;
	}
}
