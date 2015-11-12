package com.boutique.enums;

public enum DescuentoAnticipadoPorVentaCredito {
  GENERAL(1), VENTAS_EN_PERIODO(2);
  
  private int value;
  private DescuentoAnticipadoPorVentaCredito(int value){
	  this.value=value;
  }
  public int getBDValue(){
	  return this.value;
  }
}
