package com.boutique.domain;

public class PagoSucursalesCheque {
  private String banco;
  private int noCheque;
  public String getBanco() {
    return banco;
  }

  public int getNoCheque() {
    return noCheque;
  }

  public void setBanco(String banco) {
    this.banco = banco;
  }

  public void setNoCheque(int noCheque) {
    this.noCheque = noCheque;
  }
}
