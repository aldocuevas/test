package com.boutique.domain;

import java.sql.*;

public  class Pago {
  public Pago() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private int id;
  private Timestamp fecha;
  private int idVenta;
  private double impuestoMonto;
  private double monto;
  private int enCorte;
  private int idVendedor;
  private int idSucursal;
  private int tipo;
  private int esAnticipo;
  private int idTerminal;
  public int getId() {
    return id;
  }

  public Timestamp getFecha() {
    return fecha;
  }

  public int getIdVenta() {
    return idVenta;
  }

  public double getMonto() {
    return monto;
  }

  public int getEnCorte() {
    return enCorte;
  }

  public int getIdVendedor() {
    return idVendedor;
  }

  public int getIdSucursal() {
    return idSucursal;
  }

  public int getTipo() {
    return tipo;
  }

  public int getEsAnticipo() {

    return esAnticipo;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFecha(Timestamp fecha) {
    this.fecha = fecha;
  }

  public void setIdVenta(int idVenta) {
    this.idVenta = idVenta;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public void setEnCorte(int enCorte) {
    this.enCorte = enCorte;
  }

  public void setIdVendedor(int idVendedor) {
    this.idVendedor = idVendedor;
  }

  public void setIdSucursal(int idSucursal) {
    this.idSucursal = idSucursal;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public void setEsAnticipo(int esAnticipo) {

    this.esAnticipo = esAnticipo;
  }

  private void jbInit() throws Exception {
  }

public void setIdTerminal(int idTerminal) {
	this.idTerminal = idTerminal;
}

public int getIdTerminal() {
	return idTerminal;
}

public void setImpuestoMonto(double impuestoMonto) {
	this.impuestoMonto = impuestoMonto;
}

public double getImpuestoMonto() {
	return impuestoMonto;
}

}
