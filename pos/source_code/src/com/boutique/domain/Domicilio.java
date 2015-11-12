package com.boutique.domain;

public class Domicilio {
  private String id;
  private String callePrincipal;
  private String numero;
  private String calleColindante1;
  private String calleColindante2;
  private Colonia colonia;
  public Domicilio() {
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getCallePrincipal() {
    return callePrincipal;
  }
  public void setCallePrincipal(String callePrincipal) {
    this.callePrincipal = callePrincipal;
  }
  public String getNumero() {
    return numero;
  }
  public void setNumero(String numero) {
    this.numero = numero;
  }
  public String getCalleColindante1() {
    return calleColindante1;
  }
  public void setCalleColindante1(String calleColindante1) {
    this.calleColindante1 = calleColindante1;
  }
  public String getCalleColindante2() {
    return calleColindante2;
  }
  public void setCalleColindante2(String calleColindante2) {
    this.calleColindante2 = calleColindante2;
  }
  public Colonia getColonia() {
    return colonia;
  }
  public void setColonia(Colonia colonia) {
    this.colonia = colonia;
  }
}
