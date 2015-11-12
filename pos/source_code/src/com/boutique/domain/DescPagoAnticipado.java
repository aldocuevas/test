package com.boutique.domain;

import java.util.*;

public class DescPagoAnticipado {
  boolean activado = false;
  Date fechaInicial = null;
  Date fechaFinal = null;
  double porcentajeDescuento;
  private int tipoVentaCredito;
  public DescPagoAnticipado() {
  }

  public void setFechaFinal(Date fechaFinal) {
    this.fechaFinal = fechaFinal;
  }

  public void setFechaInicial(Date fechaInicial) {
    this.fechaInicial = fechaInicial;
  }

  public void setActivado(boolean activado) {
    this.activado = activado;
  }

  public void setPorcentajeDescuento(double porcentajeDescuento) {
    this.porcentajeDescuento = porcentajeDescuento;
  }

  public double getPorcentajeDescuento() {
    return porcentajeDescuento;
  }

  public boolean isActivado() {
    return activado;
  }

  public Date getFechaFinal() {
    return fechaFinal;
  }

  public Date getFechaInicial() {
    return fechaInicial;
  }

public void setTipoVentaCredito(int tipoVentaCredito) {
	this.tipoVentaCredito = tipoVentaCredito;
}

public int getTipoVentaCredito() {
	return tipoVentaCredito;
}
}
