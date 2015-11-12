package com.boutique.domain;
import java.sql.Timestamp;
public class ProductoDevuelto {
  private int id;
  private int idVenta;
  private String etiqueta;
  private int modelo;
  private double precioInicial;
  private int descuento;
  private double impuestoPrecioFinal;
  private double precioFinal;
  private int idTipoProducto;
  private int idInventario;
  private int idVendedor;
  private Timestamp fechaDevolucion;
  public ProductoDevuelto() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setIdVenta(int idVenta) {
    this.idVenta = idVenta;
  }

  public void setEtiqueta(String etiqueta) {
    this.etiqueta = etiqueta;
  }

  public void setModelo(int modelo) {
    this.modelo = modelo;
  }

  public void setPrecioInicial(double precioInicial) {
    this.precioInicial = precioInicial;
  }

  public void setDescuento(int descuento) {
    this.descuento = descuento;
  }

  public void setPrecioFinal(double precioFinal) {
    this.precioFinal = precioFinal;
  }



  public void setIdTipoProducto(int idTipoProducto) {
    this.idTipoProducto = idTipoProducto;
  }

  public void setIdInventario(int idInventario) {
    this.idInventario = idInventario;
  }

  public void setFechaDevolucion(Timestamp fecha) {
    this.fechaDevolucion = fecha;
  }

  public void setIdVendedor(int idVendedor) {
    this.idVendedor = idVendedor;
  }

  public int getId() {
    return id;
  }

  public int getIdVenta() {
    return idVenta;
  }

  public String getEtiqueta() {
    return etiqueta;
  }

  public int getModelo() {
    return modelo;
  }

  public double getPrecioInicial() {
    return precioInicial;
  }

  public int getDescuento() {
    return descuento;
  }

  public double getPrecioFinal() {
    return precioFinal;
  }

  public int getIdTipoProducto() {
    return idTipoProducto;
  }

  public int getIdInventario() {
    return idInventario;
  }

  public Timestamp getFechaDevolucion() {
    return fechaDevolucion;
  }

  public int getIdVendedor() {
    return idVendedor;
  }

  private void jbInit() throws Exception {
  }

public void setImpuestoPrecioFinal(double impuestoPrecioFinal) {
	this.impuestoPrecioFinal = impuestoPrecioFinal;
}

public double getImpuestoPrecioFinal() {
	return impuestoPrecioFinal;
}
}
