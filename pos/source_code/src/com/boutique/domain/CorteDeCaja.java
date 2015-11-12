package com.boutique.domain;

public class CorteDeCaja {
	private double fondoFijoEnCaja;
	private double totalEfectivo;
	private double totalTarjeta;
	private double totalCheque;
	private double totalVales;
	private double totalTransferencia;
	private int valesEmitidos;
	private double montoValesEmitidos;
	private double creditos;
	private double totalEnCaja;
	private int id;
	private double salidas;
	private double dineroRecogido;
	public String observaciones;

	public double getFondoFijoEnCaja() {

		return fondoFijoEnCaja;
	}

	public double getTotalEfectivo() {
		return totalEfectivo;
	}

	public double getTotalTarjeta() {

		return totalTarjeta;
	}

	public double getTotalCheque() {
		return totalCheque;
	}

	public double getTotalVales() {
		return totalVales;
	}

	public double getTotalTransferencia() {
		return totalTransferencia;
	}

	public int getValesEmitidos() {
		return valesEmitidos;
	}

	public double getMontoValesEmitidos() {
		return montoValesEmitidos;
	}

	public double getCreditos() {
		return creditos;
	}

	public double getTotalEnCaja() {
		return totalEnCaja;
	}

	public int getId() {
		return id;
	}

	public double getSalidas() {
		return salidas;
	}

	public double getDineroRecogido() {
		return dineroRecogido;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setFondoFijoEnCaja(double fondoFijoEnCaja) {
		this.fondoFijoEnCaja = fondoFijoEnCaja;
	}

	public void setTotalEfectivo(double totalEfectivo) {
		this.totalEfectivo = totalEfectivo;
	}

	public void setTotalTarjeta(double totalTarjeta) {

		this.totalTarjeta = totalTarjeta;
	}

	public void setTotalCheque(double totalCheque) {
		this.totalCheque = totalCheque;
	}

	public void setTotalVales(double totalVales) {
		this.totalVales = totalVales;
	}

	public void setTotalTransferencia(double totalTransferencia) {
		this.totalTransferencia = totalTransferencia;
	}

	public void setValesEmitidos(int valesEmitidos) {
		this.valesEmitidos = valesEmitidos;
	}

	public void setMontoValesEmitidos(double montoValesEmitidos) {
		this.montoValesEmitidos = montoValesEmitidos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	public void setTotalEnCaja(double totalEnCaja) {
		this.totalEnCaja = totalEnCaja;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSalidas(double salidas) {
		this.salidas = salidas;
	}

	public void setDineroRecogido(double dineroRecogido) {
		this.dineroRecogido = dineroRecogido;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
