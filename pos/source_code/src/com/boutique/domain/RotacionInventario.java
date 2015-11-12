package com.boutique.domain;

import java.sql.*;
import java.util.*;
public class RotacionInventario {
  private int id;
  private Timestamp fechaInicio;
  private Timestamp fechaCierre;
  private int boutiqueInicial;
  private int boutiqueFinal;
  private String status;
  private List<ProductoRotacion> productos;
  public int getId() {
    return id;
  }

  public Timestamp getFechaInicio() {
    return fechaInicio;
  }

  public Timestamp getFechaCierre() {
    return fechaCierre;
  }

  public int getBoutiqueInicial() {
    return boutiqueInicial;
  }

  public int getBoutiqueFinal() {
    return boutiqueFinal;
  }

  public String getStatus() {
    return status;
  }

  public List<ProductoRotacion> getProductos() {
    return productos;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFechaInicio(Timestamp fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public void setFechaCierre(Timestamp fechaCierre) {
    this.fechaCierre = fechaCierre;
  }

  public void setBoutiqueInicial(int boutiqueInicial) {
    this.boutiqueInicial = boutiqueInicial;
  }

  public void setBoutiqueFinal(int boutiqueFinal) {
    this.boutiqueFinal = boutiqueFinal;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setProductos(List<ProductoRotacion> productos) {
    this.productos = productos;
  }
}
