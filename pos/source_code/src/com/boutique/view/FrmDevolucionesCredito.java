package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.boutique.engine.impl.DevolucionDeProductosEngine;

public class FrmDevolucionesCredito
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);
  DevolucionDeProductosEngine engine = new DevolucionDeProductosEngine();
  public FrmDevolucionesCredito() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    this.addWindowListener(new FrmDevolucionesCredito_this_windowAdapter(this));
  }

  public void this_windowOpened(WindowEvent e) {
    frmBuscarCliente.setSize(900, 600);
    frmBuscarCliente.setLocationRelativeTo(this.getRootPane());

    frmBuscarCliente.setVisible(true);
    if (frmBuscarCliente.cliente == null) {
      this.setVisible(false);
    }
    else {
    //  engine.setCliente(frmBuscarCliente.cliente);
      //Tenemos las ventas encontradas... las ponemos si no pues mostramos mensaje de que no hay
      if (engine.buscarNotasPorCliente(frmBuscarCliente.cliente.getId()) == null) {
        JOptionPane.showMessageDialog(this.getRootPane(),"No hay ventas registradas del cliente seleccionado",com.boutique.engine.impl.AppInstance.nombreNegocio,JOptionPane.ERROR_MESSAGE);
      }
      else {
          //Tenemos las ventas.. las mostramos
      }

    }
  }
}

class FrmDevolucionesCredito_this_windowAdapter
    extends WindowAdapter {
  private FrmDevolucionesCredito adaptee;
  FrmDevolucionesCredito_this_windowAdapter(FrmDevolucionesCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
