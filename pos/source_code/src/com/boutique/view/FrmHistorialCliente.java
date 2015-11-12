package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.HistorialClienteEngine;

public class FrmHistorialCliente
    extends JDialog {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  PnlDatosVenta pnlDatosVenta1 = new PnlDatosVenta();
  public PnlHistorialCliente pnlHistorialCliente1 = new PnlHistorialCliente(pnlDatosVenta1);
  HistorialClienteEngine engine = new HistorialClienteEngine();
  FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);
  JPanel jPanel1 = new JPanel();
  JButton cmdSalir = new JButton();
  JButton cmdBuscarOtroCliente = new JButton();
  public FrmHistorialCliente() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
    setTitle("HISTORIAL DEL CLIENTE");
    getContentPane().setLayout(borderLayout1);
    pnlHistorialCliente1.engine = engine;
    pnlHistorialCliente1.setPreferredSize(new Dimension(400, 250));
    cmdBuscarOtroCliente.addActionListener(new
                                           FrmHistorialCliente_cmdBuscarOtroCliente_actionAdapter(this));
    cmdSalir.addActionListener(new FrmHistorialCliente_cmdSalir_actionAdapter(this));
    this.addWindowListener(new FrmHistorialCliente_this_windowAdapter(this));
    this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
    jPanel1.add(cmdBuscarOtroCliente);
    jPanel1.add(cmdSalir);
    cmdBuscarOtroCliente.setText("OTRO CLIENTE");
    cmdSalir.setText("SALIR");

    frmBuscarCliente.setModal(true);
    frmBuscarCliente.setSize(900, 600);
    frmBuscarCliente.setLocationRelativeTo(this);

    this.getContentPane().add(pnlDatosVenta1, java.awt.BorderLayout.CENTER);
    this.getContentPane().add(pnlHistorialCliente1, java.awt.BorderLayout.NORTH);
        this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
  }

  public void cmdBuscarOtroCliente_actionPerformed(ActionEvent e) {
    this.setCursor(AppInstance.waitCursor);

    frmBuscarCliente.setVisible(true);
    engine.cliente = frmBuscarCliente.cliente;
    if (engine.cliente != null) {
      pnlHistorialCliente1.setHistorialCliente();
    }
    else {
      JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio,
                                    JOptionPane.WARNING_MESSAGE);
    }
    this.setCursor(AppInstance.defCursor);

  }

  public void cmdSalir_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  public void this_windowOpened(WindowEvent e) {
    int i;
    while (frmBuscarCliente.cliente == null) {
      frmBuscarCliente.setVisible(true);

      if (frmBuscarCliente.cliente == null) {
        i = JOptionPane.showConfirmDialog(this,
                                          "No has elegido un cliente, ¿Deseas salir de la opción?",
                                          com.boutique.engine.impl.AppInstance.nombreNegocio,
                                          JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
          this.setVisible(false);
          return;
        }
      }
    }
    this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
    engine.cliente = frmBuscarCliente.cliente;

    if (engine.cliente != null) {
      pnlHistorialCliente1.setHistorialCliente();
    }
        this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
  }
}

class FrmHistorialCliente_this_windowAdapter
    extends WindowAdapter {
  private FrmHistorialCliente adaptee;
  FrmHistorialCliente_this_windowAdapter(FrmHistorialCliente adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class FrmHistorialCliente_cmdSalir_actionAdapter
    implements ActionListener {
  private FrmHistorialCliente adaptee;
  FrmHistorialCliente_cmdSalir_actionAdapter(FrmHistorialCliente adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdSalir_actionPerformed(e);
  }
}

class FrmHistorialCliente_cmdBuscarOtroCliente_actionAdapter
    implements ActionListener {
  private FrmHistorialCliente adaptee;
  FrmHistorialCliente_cmdBuscarOtroCliente_actionAdapter(FrmHistorialCliente adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscarOtroCliente_actionPerformed(e);
  }
}
