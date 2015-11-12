package com.boutique.domain;

public class PagoVale
    extends Pago {
  private int idVale;
  private int numero;
  public int getIdVale() {
    return idVale;
  }

  public int getNumero() {
    return numero;
  }

  public void setIdVale(int idVale) {
    this.idVale = idVale;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }
}
