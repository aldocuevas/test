package com.boutique.domain;



public class Proveedor {
  private int id;
  private String nombre;
  private String domicilio;
  private String colonia;
  private String ciudad;
  private String estado;
  private String cp;
  private String telefono;
  private String rfc;
  private int credito;

  public Proveedor() {
  }
  public String getCiudad() {
    return ciudad;
  }
  public String getColonia() {
    return colonia;
  }
  public String getCp() {
    return cp;
  }
  public int getCredito() {
    return credito;
  }
  public String getDomicilio() {
    return domicilio;
  }
  public String getEstado() {
    return estado;
  }
  public int getId() {
    return id;
  }
  public String getNombre() {
    return nombre;
  }
  public String getRfc() {
    return rfc;
  }
  public String getTelefono() {
    return telefono;
  }
  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }
  public void setColonia(String colonia) {
    this.colonia = colonia;
  }
  public void setCp(String cp) {
    this.cp = cp;
  }
  public void setCredito(int credito) {
    this.credito = credito;
  }
  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }
  public void setEstado(String estado) {
    this.estado = estado;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public void setRfc(String rfc) {
    this.rfc = rfc;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
