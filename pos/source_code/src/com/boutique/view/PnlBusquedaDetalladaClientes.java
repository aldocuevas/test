package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.boutique.dao.DaoClientesCentral;


/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class PnlBusquedaDetalladaClientes
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  BorderLayout borderLayout2 = new BorderLayout();
  JTextField txtNombreCliente = new JTextField();
  JButton cmdBuscarCliente = new JButton();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable tblDatosCliente = new JTable();
  ModelDatosClientes modelDatosClientes1 = new ModelDatosClientes();

  JPanel jPanel3 = new JPanel();
  public PnlBusquedaDetalladaClientes() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jLabel1.setText("Nombre del cliente:");
    jPanel1.setLayout(borderLayout2);
    cmdBuscarCliente.setText("Buscar");
    cmdBuscarCliente.addActionListener(new
        PnlBusquedaDetalladaClientes_cmdBuscarCliente_actionAdapter(this));
    txtNombreCliente.setText("");
    txtNombreCliente.addActionListener(new PnlBusquedaDetalladaClientes_txtNombreCliente_actionAdapter(this));
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setLayout(borderLayout3);
    tblDatosCliente.setModel(modelDatosClientes1);
    this.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jLabel1, BorderLayout.WEST);
    jPanel1.add(txtNombreCliente, BorderLayout.CENTER);
    jPanel1.add(cmdBuscarCliente, BorderLayout.EAST);
    this.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jScrollPane1, BorderLayout.CENTER);
    this.add(jPanel3,  BorderLayout.SOUTH);
    jScrollPane1.getViewport().add(tblDatosCliente, null);
  }

  void cmdBuscarCliente_actionPerformed(ActionEvent e) {
    this.modelDatosClientes1.setClientes(DaoClientesCentral.findByNombreMaternoPaterno(this.
        txtNombreCliente.getText()));
  }

  void txtNombreCliente_actionPerformed(ActionEvent e) {
    this.modelDatosClientes1.setClientes(DaoClientesCentral.findByNombreMaternoPaterno(this.
        txtNombreCliente.getText()));
  }



  void tblDatosCliente_keyReleased(KeyEvent e) {
System.out.println(e.getKeyCode());
  }
}

class PnlBusquedaDetalladaClientes_cmdBuscarCliente_actionAdapter
    implements java.awt.event.ActionListener {
  PnlBusquedaDetalladaClientes adaptee;

  PnlBusquedaDetalladaClientes_cmdBuscarCliente_actionAdapter(
      PnlBusquedaDetalladaClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscarCliente_actionPerformed(e);
  }
}

class PnlBusquedaDetalladaClientes_txtNombreCliente_actionAdapter implements java.awt.event.ActionListener {
  PnlBusquedaDetalladaClientes adaptee;

  PnlBusquedaDetalladaClientes_txtNombreCliente_actionAdapter(PnlBusquedaDetalladaClientes adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtNombreCliente_actionPerformed(e);
  }
}
