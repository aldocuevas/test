package com.boutique.domain;

public class ClasificacionCliente {
  int id;
  String clasificacion;
  String tipo;
  public ClasificacionCliente() {

  }

  public String getClasificacion() {
    return clasificacion;
  }

  public int getId() {
    return id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setClasificacion(String clasificacion) {
    this.clasificacion = clasificacion;
  }
}
