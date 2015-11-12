package com.boutique.domain;

import java.util.*;

/**
 * <p>Title: boutique management</p>
 *
 * <p>Description: Sistema de administracion de boitiques</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: SESTO</p>
 *
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class Inventario {
  Date fecha;
  Boutique boutique;
  Usuario auditor;
  Usuario empeladoPresente;
  List<Object[]> productosInventario = new ArrayList<Object[]>();
  List<Object[]> productosEncontrados = new ArrayList<Object[]>();
  List<Object[]> productosNoEncontrados = new ArrayList<Object[]>();
  public int cantidadProductosInicial = 0;
  public int cantidadProductosFaltantes = 0;
  public int cantidadProductosNoEncontrados=0;
  public double montoNoEncontrado=0;
  public double montoInicial = 0;
  public double montoFaltante = 0;
  public int id=0;
  public Inventario() {
  }

  public Usuario getAuditor() {
    return auditor;
  }

  public Boutique getBoutique() {
    return boutique;
  }

  public Usuario getEmpeladoPresente() {
    return empeladoPresente;
  }

  public Date getFecha() {
    return fecha;
  }

  public List<Object[]> getProductosEncontrados() {
    return productosEncontrados;
  }

  public List<Object[]> getProductosInventario() {
    return productosInventario;
  }

  public List<Object[]> getProductosNoEncontrados() {
    return productosNoEncontrados;
  }

  public void setAuditor(Usuario auditor) {
    this.auditor = auditor;
  }

  public void setBoutique(Boutique boutique) {
    this.boutique = boutique;
  }

  public void setEmpeladoPresente(Usuario empeladoPresente) {
    this.empeladoPresente = empeladoPresente;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public void setProductosEncontrados(List<Object[]> productosFaltantes) {
    this.productosEncontrados = productosFaltantes;
  }

  public void setProductosInventario(List<Object[]> productosInventario) {
    this.productosInventario = productosInventario;
  }

  public void setProductosNoEncontrados(List<Object[]> productosNoEncontrados) {
    this.productosNoEncontrados = productosNoEncontrados;
  }
}
