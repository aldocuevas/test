package com.boutique.domain;

public class Colonia {
  private String nombre;
  private String cp;
  private String ciudad;
  private String estado;
  private int ruta;
  private int id;
  private String municipio;
  public Colonia() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCp() {
    return cp;
  }

  public void setCp(String cp) {
    this.cp = cp;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public String getEstado() {
    return estado;
  }

  public String getMunicipio() {
    return municipio;
  }

  public int getRuta() {
    return ruta;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public void setRuta(int ruta) {
    this.ruta = ruta;
  }
}
