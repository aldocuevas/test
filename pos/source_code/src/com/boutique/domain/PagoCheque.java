package com.boutique.domain;

public class PagoCheque
    extends Pago {
  private String banco;
  private String noCheque;
  public String getBanco() {
    return banco;
  }

  public String getNoCheque() {
    return noCheque;
  }

  public void setBanco(String banco) {
    this.banco = banco;
  }

  public void setNoCheque(String noCheque) {
    this.noCheque = noCheque;
  }
}
