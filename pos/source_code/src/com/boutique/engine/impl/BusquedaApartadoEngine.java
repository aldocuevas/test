package com.boutique.engine.impl;

import java.sql.*;
import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import java.io.InputStream;

public class BusquedaApartadoEngine {
  public BusquedaApartadoEngine() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public double abonado = 0;
  public double total = 0;
  public double montoPendiente = 0;
  private double montoUsado = 0;
  public double montoDisponible = 0;
  VentaApartado ventaSeleccionada;
  List<VentaApartado> ventasEncontradas;

  List<Object[]> ventasView = new ArrayList<Object[]>();
  List<Object[]> pagosView = new ArrayList<Object[]>();

  List<Object[]> productosView = new ArrayList<Object[]>();
  public void buscarVentas(String nombre) {
    ventasEncontradas = DaoApartados.findByNombre(nombre,
                                                  AppInstance.boutique().getId());
    ventasView.clear();
    Object[] row;
    for (VentaApartado v : ventasEncontradas) {
      row = new Object[4];
      row[0] = v.getId();
      row[1] = v.getCliente();
      row[2] = v.getDomicilio();
      row[3] = v.getTelefono();
      ventasView.add(row);

    }
  }

  public VentaApartado ventaSeleccionada() {
    return this.ventaSeleccionada;
  }

  public List<Object[]> ventasView() {
    return this.ventasView;
  }

  public List<VentaApartado> ventasEncontradas() {
    return this.ventasEncontradas;
  }

  public List<Object[]> pagosView() {
    return this.pagosView;
  }

  public ProductoVendido getProducto(int index) {
    return
        ventaSeleccionada.getProductosVendidos().get(index);
  }

  public void imprimirPagosAbonosaNota(List<Pago> pagosaVentas,
                                       double montoAcumulado,
                                       double saldoAnterior) {
    com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.
        PrintTask(7, pagosaVentas, ventaSeleccionada, montoAcumulado,
                  saldoAnterior); //Venta de contado
    Thread t = new Thread(obj);
    t.start();

  }

  public void seleccionarVenta(int index) {
    pagosView.clear();
    productosView.clear();
    //Obtenemos los productos vendidos en la venta/ si es -1 estoy refrescando los datos de la venta previamente
    //seleccionada
    if (index != -1) {
      ventaSeleccionada = ventasEncontradas.get(index);
    }
    else {
      ventaSeleccionada = (VentaApartado) DaoVentas.findByIdNoFinalizada(
          ventaSeleccionada.
          getId());
    }
    total = 0;
    abonado = 0;
    ventaSeleccionada.setProductosVendidos(DaoVentas.getProductosVendidos(
        ventaSeleccionada.getId()));
    Object[] row = null;
    for (ProductoVendido pv : ventaSeleccionada.getProductosVendidos()) {
      row = new Object[7];
      row[0] = "1";
      row[1] = pv.getDescripcion();
      row[2] = pv.getEtiqueta();
      row[3] = AppInstance.number.format(pv.getPrecioInicial());
      row[4] = pv.getDescuento() + "%";
      row[5] = AppInstance.number.format(pv.getPrecioFinal());
      switch (pv.getEntregado()) {
        case 0:
          row[6] = "No";
          break;
        case 1:
          row[6] = "Si";
          montoUsado += pv.getPrecioFinal();
          break;
      }
      productosView.add(row);

      total += pv.getPrecioFinal();
    }
    //Obtenemos los pagos realizados en la venta
    ventaSeleccionada.setPagos(DaoVentas.getPagos(ventaSeleccionada.getId()));

    for (Pago p : ventaSeleccionada.getPagos()) {
      row = new Object[4];
      row[0] = p.getId();
      row[1] = AppInstance.formatoLargo.format(p.getFecha());
      row[2] = AppInstance.number.format(p.getMonto());
      if (p instanceof PagoTarjetaCredito) {
        PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
        row[3] = "T Credito/debito " + pt.getBanco() +
            ( (pt.getMeses() > 0) ? pt.getMeses() + " meses sin intereses" :
             "");
      }
      else if (p instanceof PagoVale) {
        PagoVale pv = (PagoVale) p;
        row[3] = "Vale no. " + pv.getIdVale();

      }
      else if (p instanceof PagoCheque) {
        PagoCheque pch = (PagoCheque) p;
        row[3] = "Cheque no. " + pch.getNoCheque() + " de " + pch.getBanco();

      }
      else if (p instanceof PagoDevolucion) {
        PagoDevolucion pv = (PagoDevolucion) p;
        row[3] = "Descuento por entrega de vale no. " + pv.getVale().getId();
        pv = null;
      }
      else if(p instanceof PagoTransferenciaBancaria){
    	  PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria)p;
    	  row[3]= "Depósito/transf. bancaria, ref: "+ pb.getNoReferencia();
      }
      else if (p instanceof Pago) {
        row[3] = "Efectivo";
      }
      abonado += p.getMonto();
      pagosView.add(row);
    }
    montoPendiente = total - abonado;
    montoDisponible = abonado - montoUsado;
    //Retornamos la venta.

  }

  /**
   *
   * @param pagos List
   * @param montoaAbonar double
   * @return int 1, finalizar nota entregar articulos y entregar articulos
   */
  public java.util.List<Pago> agregarPagos(List<Pago> pagos,
                                           double montoaAbonar) {
    try {

      pagos = DaoVentas.registrarPagos(ventaSeleccionada.getId(), pagos, null);
      //Registramos los pagos, ahora revisamos si es necesario finalizar la nota checando el monto pendiente
      if ( (montoPendiente - montoaAbonar) <= 0.05) {
        //Se han registrado los pagos suficientes para finalizar la nota
        DaoVentas.finalizarVenta(this.ventaSeleccionada);
      }
      return pagos;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return null;
    }
  }

  public List<Object[]> productosView() {
    return this.productosView;
  }

  private void jbInit() throws Exception {
  }

  /**
   * entregarProducto
   *
   * @param pv ProductoVendido
   */
  public boolean entregarProducto(ProductoVendido pv) {
    return DaoVentas.entregarProducto(pv);
  }

  /**
   * buscarFotografiaCliente
   *
   * @param i int
   */
  public InputStream buscarFotografiaCliente(int i) {
    return DaoClientesCentral.findFoto(i);
  }
}
