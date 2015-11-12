package com.boutique.domain;

public class Usuario {
  private String usuario;
  private String pass;
  private String nombre;
  private int vendedor;
  private int cortedecaja;
  private int id;
  private int entregarDinero;
  private int inventarios;
  private int admin;
  private int eliminarPagos;
  private int adminUsuarios;
  private int auditor;
  private int activo;
  private int reporteador;
  private int exportar;
  private int devolucionesIlimitadas;
  private int modificarCantidadInventarios;
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setVendedor(int vendedor) {
    this.vendedor = vendedor;
  }

  public void setCortedecaja(int cortedecaja) {
    this.cortedecaja = cortedecaja;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setEntregarDinero(int entregarDinero) {
    this.entregarDinero = entregarDinero;
  }

  public void setInventarios(int inventarios) {
    this.inventarios = inventarios;
  }

  public void setAdmin(int admin) {
    this.admin = admin;
  }

  public void setEliminarPagos(int eliminarPagos) {

    this.eliminarPagos = eliminarPagos;
  }

  public void setAdminUsuarios(int adminUsuarios) {
    this.adminUsuarios = adminUsuarios;
  }

  public void setAuditor(int auditor) {
    this.auditor = auditor;
  }

  public void setActivo(int activo) {
    this.activo = activo;
  }

  public void setReporteador(int reporteador) {
    this.reporteador = reporteador;
  }

  public String getUsuario() {
    return usuario;
  }

  public String getPass() {
    return pass;
  }

  public String getNombre() {
    return nombre;
  }

  public int getVendedor() {
    return vendedor;
  }

  public int getCortedecaja() {
    return cortedecaja;
  }

  public int getId() {
    return id;
  }

  public int getEntregarDinero() {
    return entregarDinero;
  }

  public int getInventarios() {
    return inventarios;
  }

  public int getAdmin() {
    return admin;
  }

  public int getEliminarPagos() {

    return eliminarPagos;
  }

  public int getAdminUsuarios() {
    return adminUsuarios;
  }

  public int getAuditor() {
    return auditor;
  }

  public int getActivo() {
    return activo;
  }

  public int getReporteador() {
    return reporteador;
  }

public void setExportar(int exportar) {
	this.exportar = exportar;
}

public int getExportar() {
	return exportar;
}



public void setDevolucionesIlimitadas(int devolucionesIlimitadas) {
	this.devolucionesIlimitadas = devolucionesIlimitadas;
}

public int getDevolucionesIlimitadas() {
	return devolucionesIlimitadas;
}

public void setModificarCantidadInventarios(int modificarCantidadInventarios) {
	this.modificarCantidadInventarios = modificarCantidadInventarios;
}

public int getModificarCantidadInventarios() {
	return modificarCantidadInventarios;
}
}
