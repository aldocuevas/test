package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.ActualizarInventarioEngine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmDistribuirInventario
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JButton cmdAceptar = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable tblActualizarInventario = new JTable();
  ModelProductoaDividir modelProductoaDividir1 = new ModelProductoaDividir();
  JPanel jPanel3 = new JPanel();
  JButton cmdActualizarInventarioRemoto = new JButton();
  JButton cmdCerrar = new JButton();
  ActualizarInventarioEngine engine = new ActualizarInventarioEngine();
  public FrmDistribuirInventario() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    cmdAceptar.setText("Ver productos a distribuir");
    cmdAceptar.addActionListener(new
                                 FrmActualizarInventarioRemoto_cmdAceptar_actionAdapter(this));
    cmdAceptar.addMouseListener(new
                                FrmActualizarInventarioRemoto_cmdAceptar_mouseAdapter(this));
    tblActualizarInventario.setModel(modelProductoaDividir1);
    cmdActualizarInventarioRemoto.setText("Realizar distribución de inventario");
    cmdActualizarInventarioRemoto.addActionListener(new
        FrmActualizarInventarioRemoto_cmdActualizarInventarioRemoto_actionAdapter(this));
    cmdCerrar.setText("Cerrar");
    cmdCerrar.addActionListener(new
                                FrmActualizarInventarioRemoto_cmdCerrar_actionAdapter(this));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
    jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(cmdAceptar);
    this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tblActualizarInventario);
    this.getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);
    jPanel3.add(cmdActualizarInventarioRemoto);
    jPanel3.add(cmdCerrar);

    this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
    jLabel1.setText("Actualizacion de inventarios remotos");
  
  }

  public void cmdAceptar_mouseClicked(MouseEvent e) {
    this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
    engine.verProductosADistribuir();
    this.modelProductoaDividir1.setProductos(engine.getProductosView());
    this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
  }

  public void cmdActualizarInventarioRemoto_actionPerformed(ActionEvent e) {
//Abrimos un JFileChooser
    // JFileChooser jf = new JFileChooser();
    // jf.showSaveDialog(this);
    // String filename = jf.getSelectedFile().getPath();
    // if (filename == null) {
    //   JOptionPane.showMessageDialog(this,
    //                                 "Indique el nombre del archivo, la operacion no pudo ser concretada",
    //                                 "Error en la operacion",
    //                                 JOptionPane.ERROR_MESSAGE);
    //}
    //else {

    try {
      engine.distribuirInventario();
      cmdActualizarInventarioRemoto.setEnabled(false);

    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(this,
                                    "Ha ocurrido una excepcion: " +
                                    ex.getMessage(), "Error en la operacion",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }
    engine.verProductosADistribuir();
    this.modelProductoaDividir1.setProductos(engine.getProductosView());
    JOptionPane.showMessageDialog(this,
                                  "Inventarios actualizados",
                                  "Distribucion de inventarios",
                                  JOptionPane.INFORMATION_MESSAGE);

    // }
  }

  public void cmdAceptar_actionPerformed(ActionEvent e) {

  }

  public void cmdCerrar_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }
}

class FrmActualizarInventarioRemoto_cmdAceptar_actionAdapter
    implements ActionListener {
  private FrmDistribuirInventario adaptee;
  FrmActualizarInventarioRemoto_cmdAceptar_actionAdapter(
      FrmDistribuirInventario adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}

class FrmActualizarInventarioRemoto_cmdActualizarInventarioRemoto_actionAdapter
    implements ActionListener {
  private FrmDistribuirInventario adaptee;
  FrmActualizarInventarioRemoto_cmdActualizarInventarioRemoto_actionAdapter(
      FrmDistribuirInventario adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdActualizarInventarioRemoto_actionPerformed(e);
  }
}

class FrmActualizarInventarioRemoto_cmdCerrar_actionAdapter
    implements ActionListener {
  private FrmDistribuirInventario adaptee;
  FrmActualizarInventarioRemoto_cmdCerrar_actionAdapter(
      FrmDistribuirInventario adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdCerrar_actionPerformed(e);
  }
}

class FrmActualizarInventarioRemoto_cmdAceptar_mouseAdapter
    extends MouseAdapter {
  private FrmDistribuirInventario adaptee;
  FrmActualizarInventarioRemoto_cmdAceptar_mouseAdapter(
      FrmDistribuirInventario adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.cmdAceptar_mouseClicked(e);
  }
}
