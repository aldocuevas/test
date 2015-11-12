package com.boutique.domain;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.boutique.engine.impl.*;

public class VentaCredito
    extends Venta {

  private int noPagos;
  private int noDias;
  private Timestamp fechaFinalizacion;
  public double saldoVencido;
  public double saldoTotal;
  public double subTotal;
  public double saldoActual;
  public double saldoAbonado;
  private Timestamp fechaVencimiento;
  private double anticipo;
  public VentaCredito() {
	 super();
	 setVentaTypeEnum(VentaTypeEnum.CREDITO);
  }

  



  public void setNoPagos(int noPagos) {
    this.noPagos = noPagos;
  }

  public void setNoDias(int noDias) {
    this.noDias = noDias;
  }

  public void setFechaFinalizacion(Timestamp fechaFinalizacion) {
    this.fechaFinalizacion = fechaFinalizacion;
  }

  public void setFechaVencimiento(Timestamp fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public void setAnticipo(double anticipo) {
    this.anticipo = anticipo;
  }



  public int getNoPagos() {
    return noPagos;
  }

  public int getNoDias() {
    return noDias;
  }

  public Timestamp getFechaFinalizacion() {
    return fechaFinalizacion;
  }

  public Timestamp getFechaVencimiento() {
    return fechaVencimiento;
  }

  public double getAnticipo() {
    return anticipo;
  }

  public static double getSaldoVencido(VentaCredito vc) {
    //DECLARAMOS UNA VARIABLE CON EL SALDO ABONADO CON EL FIN DE SACAR
    double tmpSaldoAbonado = vc.saldoAbonado;
    //Vemos la fecha de hoy
    java.util.Calendar calHoy = java.util.Calendar.getInstance();
    calHoy.setTime(com.boutique.dao.DaoSource.getFechaActual());
    java.util.Calendar calFechaNota = java.util.Calendar.getInstance();
    calFechaNota.setTime(vc.getFecha());
    calFechaNota.set(Calendar.HOUR,0);
    calFechaNota.set(Calendar.MINUTE,0);
    calFechaNota.set(Calendar.SECOND,0);
//    double cantidadPorPago= Double.parseDouble(AppInstance.real.format( vc.saldoTotal/vc.getNoPagos()));
    double cantidadPorPago= vc.saldoTotal/vc.getNoPagos();
    double cantidadPorPagoDelimitada =Double.parseDouble(AppInstance.real.format( vc.saldoTotal/vc.getNoPagos()));
    double saldoVencido = 0.0;
    for (int i = 0; i < vc.noPagos; i++) {
      calFechaNota.add(Calendar.DAY_OF_MONTH, vc.noDias);
      java.util.Date fechaPago = calFechaNota.getTime(); //Tenemos la primer fecha de pago
      if (fechaPago.compareTo(calHoy.getTime()) < 0) { //Este pago ya esta vencido
        saldoVencido += cantidadPorPago;
      }
      if(tmpSaldoAbonado<cantidadPorPagoDelimitada && vc.getFechaVencimiento()==null){
        vc.setFechaVencimiento(new java.sql.Timestamp(fechaPago.getTime()));
      }
      tmpSaldoAbonado-=cantidadPorPagoDelimitada;
    }
    if(vc.getFechaVencimiento()==null){
          calFechaNota.setTime(vc.getFecha());
          calFechaNota.add(Calendar.DAY_OF_MONTH, vc.noDias);
            java.util.Date fechaPago = calFechaNota.getTime(); //Tenemos la primer fecha de pago
          vc.setFechaVencimiento(new java.sql.Timestamp(fechaPago.getTime()));
    }
    //Tenemos el saldo vencido.. ahora hay que restarle los abonos
  //  saldoVencido += (vc.subTotal - vc.saldoAbonado);
    saldoVencido -= vc.saldoAbonado-vc.getAnticipo();

    try {
      vc.saldoVencido = AppInstance.number.parse(AppInstance.number.format(
          saldoVencido)).doubleValue();
    }
    catch (ParseException ex) {
    }
    if(vc.saldoVencido<0){
      vc.saldoVencido=0;
    }
    vc.saldoVencido=Double.parseDouble(AppInstance.number.format(vc.saldoVencido).replaceAll(",",""));
    return vc.saldoVencido;

  }

}
