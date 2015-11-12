package com.boutique.domain;

import java.sql.*;

public class ProductoRotacion extends Producto{
  private int idVendedor;
  private Timestamp fechaRegistro;
  private int idLote;
  public Timestamp getFechaRegistro() {
    return fechaRegistro;
  }

  public int getIdLote() {
    return idLote;
  }

  public int getIdVendedor() {

    return idVendedor;
  }

  public void setFechaRegistro(Timestamp fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public void setIdLote(int idLote) {
    this.idLote = idLote;
  }

  public void setIdVendedor(int idVendedor) {

    this.idVendedor = idVendedor;
  }

}
