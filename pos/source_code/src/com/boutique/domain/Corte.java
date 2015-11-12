package com.boutique.domain;

public class Corte {
  private int id;
  private int fecha;
  private double enCaja;
  private double recogido;
  private double idUsuario;
  public int getId() {
    return id;
  }

  public int getFecha() {
    return fecha;
  }

  public double getEnCaja() {
    return enCaja;
  }

  public double getRecogido() {
    return recogido;
  }

  public double getIdUsuario() {
    return idUsuario;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFecha(int fecha) {
    this.fecha = fecha;
  }

  public void setEnCaja(double enCaja) {
    this.enCaja = enCaja;
  }

  public void setRecogido(double recogido) {
    this.recogido = recogido;
  }

  public void setIdUsuario(double idUsuario) {
    this.idUsuario = idUsuario;
  }
}
