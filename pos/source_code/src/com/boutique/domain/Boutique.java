package com.boutique.domain;

public class Boutique {
  private int id;
  private String nombre;
  private String calle;
  private String numero;
  private String colonia;
  private String ciudad;
  private String cp;
  private String descripcion;
  private String ip;
  private int matriz;
  private String local;
  private String nombrePropietario;
  private String rfc;
  private String telefono;
  private String curp;
  private String lugarExpedicion;
  private String lugarExpedicionPagare;
  private boolean anticipoApartadoLibre = false;
  private int wFoto;
  private int hFoto;
  private boolean mostrarFoto=false;
  private double fondoFijoEnCaja;
  public Boutique() {
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setMatriz(int matriz) {
    this.matriz = matriz;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public void setNombrePropietario(String nombrePropietario) {
    this.nombrePropietario = nombrePropietario;
  }

  public void setCalle(String calle) {
    this.calle = calle;
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

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public void setCurp(String curp) {
    this.curp = curp;
  }

  public void setLugarExpedicion(String lugarExpedicion) {
    this.lugarExpedicion = lugarExpedicion;
  }

  public void setLugarExpedicionPagare(String lugarExpedicionPagare) {
    this.lugarExpedicionPagare = lugarExpedicionPagare;
  }

  public void setAnticipoApartadoLibre(boolean anticipoApartadoLibre) {
    this.anticipoApartadoLibre = anticipoApartadoLibre;
  }

  public void setHFoto(int hFoto) {
    this.hFoto = hFoto;
  }

  public void setWFoto(int wFoto) {
    this.wFoto = wFoto;
  }

  public void setMostrarFoto(boolean mostrarFoto) {
    this.mostrarFoto = mostrarFoto;
  }

  public void setRFC(String RFC) {
    this.rfc = RFC;
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getIp() {
    return ip;
  }

  public int getMatriz() {
    return matriz;
  }

  public String getLocal() {
    return local;
  }

  public String getNombrePropietario() {
    return nombrePropietario;
  }

  public String getCalle() {
    return calle;
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

  public String getNumero() {
    return numero;
  }

  public String getRfc() {
    return rfc;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getCurp() {
    return curp;
  }

  public String getLugarExpedicion() {
    return lugarExpedicion;
  }

  public String getLugarExpedicionPagare() {
    return lugarExpedicionPagare;
  }

  public boolean isAnticipoApartadoLibre() {
    return anticipoApartadoLibre;
  }

  public int getHFoto() {
    return hFoto;
  }

  public int getWFoto() {
    return wFoto;
  }

  public boolean isMostrarFoto() {
    return mostrarFoto;
  }

  public String getRFC() {
    return rfc;
  }

public void setFondoFijoEnCaja(double fondoFijoEnCaja) {
	this.fondoFijoEnCaja = fondoFijoEnCaja;
}

public double getFondoFijoEnCaja() {
	return fondoFijoEnCaja;
}
}
