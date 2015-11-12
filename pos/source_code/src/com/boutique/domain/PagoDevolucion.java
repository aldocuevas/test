package com.boutique.domain;

public class PagoDevolucion
    extends Pago {
  private Vale vale;
  public PagoDevolucion() {
  }

  public Vale getVale() {
    return vale;
  }

  public void setVale(Vale vale) {
    this.vale = vale;
  }
}
