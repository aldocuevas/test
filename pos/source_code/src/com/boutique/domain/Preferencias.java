package com.boutique.domain;

public class Preferencias {
private int diasDevolucionProductos;
private boolean pagarCreditoConTC;

public void setDiasDevolucionProductos(int diasDevolucionProductos) {
	this.diasDevolucionProductos = diasDevolucionProductos;
}

public int getDiasDevolucionProductos() {
	return diasDevolucionProductos;
}

public void setPagarCreditoConTC(boolean pagarCreditoConTC) {
	this.pagarCreditoConTC = pagarCreditoConTC;
}

public boolean getPagarCreditoConTC() {
	return pagarCreditoConTC;
}
}
