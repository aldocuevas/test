package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.boutique.domain.Venta;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.DiarioDeEntradasEngine;
import com.boutique.domain.VentaCredito;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmDatosVenta
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  PnlDatosVenta pnlDatosVenta1 = new PnlDatosVenta();
  public int idVenta;
  DiarioDeEntradasEngine engine = null;
  JPanel jPanel1 = new JPanel();
  JButton cmdCerrar = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  public FrmDatosVenta(DiarioDeEntradasEngine engine) {
    try {
      this.engine = engine;
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void ponerDatosVenta() {
    this.setCursor(AppInstance.waitCursor);
    Venta venta = engine.getVentaContado(idVenta);
    PnlDatosVenta.modelVentaArticulos1.data = engine.productosView;
    PnlDatosVenta.modelVentaArticulos1.fireTableDataChanged();
    PnlDatosVenta.modelPagosRealizados1.data = engine.pagosView;
    PnlDatosVenta.modelPagosRealizados1.fireTableDataChanged();
    PnlDatosVenta.modelPagosCredito1.data = engine.intervaloPagos;
    PnlDatosVenta.modelPagosCredito1.fireTableDataChanged();
    pnlDatosVenta1.txtVendedor.setText(AppInstance.idToNombreUsuario.get(venta.
        getIdVendedor()).toString());
    pnlDatosVenta1.txtTotal.setText(AppInstance.number.format(venta.getTotal()));
    pnlDatosVenta1.txtFecha.setText(AppInstance.formatoLargo.format(venta.
        getFecha()));
    pnlDatosVenta1.txtNoVenta.setText(String.valueOf(venta.getId()));
    pnlDatosVenta1.txtBoutique.setText(AppInstance.idToNombreBoutique.get(venta.
        getIdBoutique()).toString());

    if (venta instanceof VentaCredito) {
      VentaCredito vc = (VentaCredito) venta;
      pnlDatosVenta1.txtSubtotal.setText(AppInstance.number.format(vc.subTotal));
      pnlDatosVenta1.txtAnticipo.setText(AppInstance.number.format(vc.
          getAnticipo()));
    }
    else {
      pnlDatosVenta1.txtSubtotal.setText(AppInstance.number.format(venta.
          getTotal()));
      pnlDatosVenta1.txtAnticipo.setText(AppInstance.number.format(0));
    }
//Habilitamos el boton de abonar a nota
    this.setCursor(AppInstance.defCursor);

  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    cmdCerrar.setText("X");
    cmdCerrar.addActionListener(new FrmDatosVenta_cmdCerrar_actionAdapter(this));
    jPanel1.setLayout(borderLayout2);
    this.getContentPane().add(pnlDatosVenta1, java.awt.BorderLayout.CENTER);
    jPanel1.add(cmdCerrar, java.awt.BorderLayout.EAST);
    this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

    this.setUndecorated(true);
    this.setSize(640,480);
  }

  public void cmdCerrar_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }
}

class FrmDatosVenta_cmdCerrar_actionAdapter
    implements ActionListener {
  private FrmDatosVenta adaptee;
  FrmDatosVenta_cmdCerrar_actionAdapter(FrmDatosVenta adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCerrar_actionPerformed(e);
  }
}
