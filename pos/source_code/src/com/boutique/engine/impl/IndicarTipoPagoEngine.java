package com.boutique.engine.impl;

import com.boutique.domain.*;
import java.util.*;
import java.text.*;

public class IndicarTipoPagoEngine {
  List<Pago> pagos = new ArrayList<Pago> ();
  List<Object[]> pagosView = new ArrayList<Object[]>();
  double monto;
  double montoPendiente;
  public double montoAcumulado;
  public boolean pagoEnEfectivo=false;
  public double montoPagoEnEfectivo=0;
  public IndicarTipoPagoEngine() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    // number = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);
    // number.setMaximumFractionDigits(2);
    // number.setMinimumFractionDigits(2);

  }

  public List<Object[]> pagosView() {
    return pagosView;
  }

  public double getMontoPendiente() {
    return montoPendiente;
  }

  public void setMontoaPagar(double monto) {
    this.monto = monto;
    this.montoPendiente = monto;
  }

  public void descontarMonto(double monto) {

    this.montoPendiente -= monto;
    try {
      this.montoPendiente = AppInstance.number.parse(AppInstance.number.format(
          montoPendiente)).doubleValue();
    }
    catch (ParseException ex) {
    }
  }

  public void addPago(Pago pago) {
    pagos.add(pago);
    montoAcumulado += pago.getMonto();
    Object[] row = new Object[3];
    if (pago instanceof PagoTarjetaCredito) { //Pago con tarjeta de credito
      PagoTarjetaCredito pt = (PagoTarjetaCredito) pago;
      row[0] = "T. CREDITO/DEBITO";
      row[1] = pt.getBanco() + " " +
          ( (pt.getMeses() > 0) ? pt.getMeses() + " meses" : "");
      row[2] = AppInstance.number.format(pago.getMonto());

    }

    else if (pago instanceof PagoCheque) { //Pago con cheque
      PagoCheque pch = (PagoCheque) pago;
      row[0] = "CHEQUE";
      row[1] = pch.getBanco() + ", Cheque: " + pch.getNoCheque();
      row[2] = AppInstance.number.format(pago.getMonto());
    }
    else if (pago instanceof PagoVale) { // Pago con vale
      PagoVale pv = (PagoVale) pago;
      row[0] = "VALE DE COMPRA";
      row[1] = "No. " + pv.getIdVale();
      row[2] = AppInstance.number.format(pago.getMonto());
    }else if (pago instanceof PagoTransferenciaBancaria){
    	PagoTransferenciaBancaria pb= (PagoTransferenciaBancaria)pago;
    	row[0] = "DEPOSITO O TRANSFERENCIA BANCARIA";
        row[1] = "Ref: " + pb.getNoReferencia();
        row[2] = AppInstance.number.format(pago.getMonto());
    }
    else if (pago instanceof Pago) { //Pago efectivo

      row[0] = "EFECTIVO";
      row[1] = "";
      row[2] = AppInstance.number.format(pago.getMonto());
    }

    pagosView.add(row);

  }

  public void removePago(int index) {
    montoAcumulado -= pagos.get(index).getMonto();
    pagos.remove(index);
    pagosView.remove(index);
  }

  public List<Pago> getPagos() {
    return pagos;
  }

  private void jbInit() throws Exception {
  }
}
