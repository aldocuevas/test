package com.boutique.engine.impl;

import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.helper.IvaHelper;

public class VentaCreditoEngine {
  public VentaCredito venta;
  Cliente cliente;
  List<ProductoVendido> productos;
  List<Object[]> productosView;
  List<Pago> pagos;
  List<?> pagosView;
  List<Object[]> intervaloPagos;

  public java.sql.Timestamp fecha = new java.sql.Timestamp(DaoSource.getFechaActual().
      getTime());
  double total = 0.0;
  double subtotal = 0.0;
  double anticipo = 0.0;
  public double montoDisponible = 0.0;
  Map<?, ?> idToTipoProducto;

  java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
      "dd/MMM/yyyy");

  public VentaCreditoEngine() {

  }

  public Cliente cliente() {
    return this.cliente;
  }

  public List<Object[]> productosView() {
    return productosView;
  }

  public void calcularFechasDePago(int diasPorPeriodo, int numeroPagos,
                                   java.util.Date nuevaFecha) {
    this.fecha = new java.sql.Timestamp(nuevaFecha.getTime());
    venta.setFecha(this.fecha);
    calcularFechasDePago(diasPorPeriodo, numeroPagos);

  }

  public void calcularFechasDePago(int diasPorPeriodo, int numeroPagos) {
    venta.setNoDias(diasPorPeriodo);
    venta.setNoPagos(numeroPagos);
    Object[] row = new Object[3];
    //double pagoIndividual = Math.ceil(this.total / numeroPagos);
    double pagoIndividual = (this.total) / numeroPagos;
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.setTime(venta.getFecha());
    this.intervaloPagos.clear();
    for (int i = 0; i < numeroPagos; i++) {
      row = new Object[3];
      cal.add(Calendar.DAY_OF_MONTH, diasPorPeriodo);
      row[0] = String.valueOf(i + 1);
      row[1] = formato.format(cal.getTime());
      row[2] = AppInstance.number.format(pagoIndividual);
      this.intervaloPagos.add(row);
    }
  }

  public List<Object[]> intervaloPagos() {
    return intervaloPagos;
  }

  public void iniciarVenta(int idVendedor, Cliente cliente, double saldoTotal) {
    venta = new VentaCredito();
    venta.setEnCorte(0);
    venta.setIdVendedor(idVendedor);
    venta.setIdBoutique(AppInstance.boutique().getId());
    venta.setIdTerminal(AppInstance.terminal().getId());
    venta.setFecha(fecha);
    venta.setIdCliente(cliente.getId());
    venta.setStatus(0); //Pagado
    productos = new ArrayList<ProductoVendido> ();
    pagos = new ArrayList<Pago> ();
    productosView = new ArrayList<Object[]>();
    intervaloPagos = new ArrayList<Object[]>();
    pagosView = new ArrayList<Object>();
    idToTipoProducto = DaoTipoProductos.getMapLocal();
    //  this.calcularFechasDePago(15, 3);
    this.cliente = cliente;
    montoDisponible = cliente.getLimiteCredito() - saldoTotal;
    if (montoDisponible < 0.0) {
      montoDisponible = 0.0;
    }
  }

  public boolean agregarProducto(String etiqueta) {
    //Obtenemos del inventario inicial
    int idProducto = -1;
    int cantidadUsados = 0;

    for (ProductoVendido pv : productos) {
      if (pv.getEtiqueta().equals(etiqueta)) {
        if (pv.getIdInventario() != idProducto) {
          cantidadUsados = 0;
        }
        idProducto = pv.getIdInventario();
        cantidadUsados++;
      }
    }
    Producto producto = DaoInventarios.findByEtiquetaAndIdBoutique(etiqueta,
        AppInstance.boutique().getId(), idProducto, cantidadUsados);
    if (producto != null) {
      //Generamos el producto vendido
      ProductoVendido pv = new ProductoVendido();
      pv.setId(producto.getId());
      pv.setIdTipoProducto(producto.getIdTipoProducto());
      pv.setDescuento(0);
      pv.setEntregado(1);
      pv.setEtiqueta(etiqueta);
      pv.setModelo(producto.getModelo());
      pv.setPrecioInicial(producto.getPrecioPublico());
      pv.setImpuestoPrecioFinal(IvaHelper.getIvaDesglosadFromMonto(producto.getPrecioPublico()));
      pv.setPrecioFinal(producto.getPrecioPublico());
      pv.setIdInventario(producto.getId());

      productos.add(pv);
      subtotal += pv.getPrecioFinal();
      total = subtotal - anticipo;
      //Agregamos el producto a productosView
      Object[] row = new Object[6];
      row[0] = "1";
      String tipoPr=idToTipoProducto.get(String.valueOf(pv.getIdTipoProducto())).toString();
      tipoPr=((tipoPr.length()>13)?tipoPr.substring(0,12):tipoPr) ;
      String detalle = tipoPr+ " " + Producto.detalleProducto(producto);
      pv.setDescripcion(detalle);
      row[1] = detalle;
      row[2] = producto.getEtiqueta();
      row[3] = AppInstance.number.format(pv.getPrecioInicial());
      row[4] = "0%";
      row[5] = AppInstance.number.format(pv.getPrecioFinal());
      productosView.add(row);

      this.calcularFechasDePago(venta.getNoDias(), venta.getNoPagos());
      return true;
    }
    else {
      return false;
    }

    //Generamos el producto vendido
    //Buscamos el producto en inventario


  }

  public void removerProducto(int index) {
    ProductoVendido pv = (ProductoVendido) productos.get(index);
    this.subtotal -= pv.getPrecioFinal();
    this.total = this.subtotal - anticipo;
    productos.remove(index);
    productosView.remove(index);
    this.calcularFechasDePago(venta.getNoDias(), venta.getNoPagos());
  }

  public void indicarAnticipo(double anticipo) {
    this.anticipo = anticipo;
    this.total = this.subtotal - this.anticipo;
    this.calcularFechasDePago(venta.getNoDias(), venta.getNoPagos());
  }

  public void agregarPagos(List<Pago> pagos) {
    for (Pago pago : pagos) {
      pago.setEsAnticipo(1);
      //pago.setEnCorte(0);
      //pago.setFecha(fecha);
      //pago.setIdVendedor(AppInstance.usuario().getId());
      //pago.setIdSucursal(AppInstance.boutique().getId());
    }
    this.pagos = pagos;
  }

  public void limpiarPagos() {
    this.pagos.clear();
  }

  public boolean registrarVentaFechaAnterior() {
    venta.setProductosVendidos(productos);
    venta.setPagos(pagos);
    return DaoVentas.registrarVenta(venta, this.fecha);
  }

  public boolean registrarVenta() {
    venta.setProductosVendidos(productos);
    venta.setPagos(pagos);
    return DaoVentas.registrarVenta(venta);
  }

  /**
   * getTotal
   *
   * @return boolean
   */
  public double getTotal() {
    return total;
  }

  /**
   * imprimirNota
   */
  public void imprimirNota(List<?> listaPagos) {
    com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.
        PrintTask(this.venta, listaPagos, cliente); //Venta de contado
    Thread t = new Thread(obj);
    t.start();
  }

  /**
   * getSubTotal
   *
   * @return Object
   */
  public double getSubTotal() {
    return subtotal;
  }

}
