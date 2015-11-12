package com.boutique.domain;

public class ClienteResumen {
  private String nombre;
  private String calle;
  private String numero;
  private String colonia;
  private String ciudad;
  private String telefono;
  private String tieneCredito;
  private double limiteCredito;
  private int id;
  private ClasificacionCliente clasificacionCliente;
  public String getNumero() {
    return numero;
  }

  public String getNombre() {
    return nombre;
  }

  public double getLimiteCredito() {
    return limiteCredito;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getTieneCredito() {
    return tieneCredito;
  }

  public String getCiudad() {
    return ciudad;
  }

  public String getColonia() {
    return colonia;
  }

  public String getCalle() {
    return calle;
  }

  public int getId() {
    return id;
  }

  public ClasificacionCliente getClasificacionCliente() {
    return clasificacionCliente;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setLimiteCredito(double limiteCredito) {
    this.limiteCredito = limiteCredito;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public void setTieneCredito(String tieneCredito) {
    this.tieneCredito = tieneCredito;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public void setColonia(String colonia) {
    this.colonia = colonia;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setClasificacionCliente(ClasificacionCliente clasificacionCliente) {
    this.clasificacionCliente = clasificacionCliente;
  }

}
