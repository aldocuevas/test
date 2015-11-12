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
public class ProductoInventario {
  int cantidad;
  String descripcion;
  String etiqueta;
  String detalle;
  double precioUnitario;
  double total;
  Date fecha;
  int id;
  public ProductoInventario() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public int getCantidad() {
    return cantidad;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getEtiqueta() {
    return etiqueta;
  }

  public Date getFecha() {
    return fecha;
  }

  public double getPrecioUnitario() {
    return precioUnitario;
  }

  public double getTotal() {
    return total;
  }

  public int getId() {
    return id;
  }

  public String getDetalle() {
    return detalle;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setPrecioUnitario(double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public void setEtiqueta(String etiqueta) {
    this.etiqueta = etiqueta;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setDetalle(String detalle) {
    this.detalle = detalle;
  }

  private void jbInit() throws Exception {
  }
}
