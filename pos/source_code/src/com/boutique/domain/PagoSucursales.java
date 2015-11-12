package com.boutique.domain;

import java.sql.*;

public class PagoSucursales {
  private int id;
  private double monto;
  private int idVenta;
  private int idVendedor;
  private int entregado;
  private int idSucursal;
  private Date fecha;
  private int enCorte;
  private Date fechaEntrega;
  public int getId() {
    return id;
  }

  public double getMonto() {
    return monto;
  }

  public int getIdVenta() {
    return idVenta;
  }

  public int getIdVendedor() {
    return idVendedor;
  }

  public int getEntregado() {
    return entregado;
  }

  public int getIdSucursal() {
    return idSucursal;
  }

  public Date getFecha() {
    return fecha;
  }

  public int getEnCorte() {
    return enCorte;
  }

  public Date getFechaEntrega() {
    return fechaEntrega;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public void setIdVenta(int idVenta) {
    this.idVenta = idVenta;
  }

  public void setIdVendedor(int idVendedor) {
    this.idVendedor = idVendedor;
  }

  public void setEntregado(int entregado) {
    this.entregado = entregado;
  }

  public void setIdSucursal(int idSucursal) {
    this.idSucursal = idSucursal;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public void setEnCorte(int enCorte) {
    this.enCorte = enCorte;
  }

  public void setFechaEntrega(Date fechaEntrega) {
    this.fechaEntrega = fechaEntrega;
  }
}
