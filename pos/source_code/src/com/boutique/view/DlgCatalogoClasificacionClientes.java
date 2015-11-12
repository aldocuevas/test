package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.boutique.domain.ClasificacionCliente;
import com.boutique.dao.DaoClientesSucursal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import com.boutique.engine.impl.AppInstance;

public class DlgCatalogoClasificacionClientes
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

List<ClasificacionCliente> lstClasificacion = null;

  BorderLayout borderLayout1 = new BorderLayout();
  JSplitPane jSplitPane1 = new JSplitPane();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtBuscar = new JTextField();
  JButton cmdBuscar = new JButton();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JList lstClasificaciones = new JList();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField txtClasificacion = new JTextField();
  JLabel jLabel4 = new JLabel();
  JComboBox cmbTipo = new JComboBox();
  JButton cmdAgregar = new JButton();
  JButton cmdModificar = new JButton();
  JButton cmdEliminar = new JButton();
  JButton cmdAceptar = new JButton();
  JButton cmdCancelar = new JButton();
  String operacion = "";
  ClasificacionCliente cc = null;
  DefaultListModel model = new DefaultListModel();
  public DlgCatalogoClasificacionClientes() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    jPanel3.setLayout(null);
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
    jLabel2.setText("CATALOGO DE CLASIFICACION DE CLIENTES");
    jLabel2.setBounds(new Rectangle(7, 2, 321, 15));
    jLabel3.setText("CLASIFICACION:");
    jLabel3.setBounds(new Rectangle(15, 36, 86, 15));
    txtClasificacion.setText("");
    txtClasificacion.setBounds(new Rectangle(99, 34, 138, 20));
    jLabel4.setText("TIPO:");
    jLabel4.setBounds(new Rectangle(19, 64, 78, 17));
    cmbTipo.setBounds(new Rectangle(98, 64, 138, 19));
    cmdAgregar.setBounds(new Rectangle(2, 105, 96, 20));
    cmdAgregar.setText("AGREGAR");
    cmdAgregar.addActionListener(new
                                 DlgCatalogoClasificacionClientes_cmdAgregar_actionAdapter(this));
    cmdModificar.setBounds(new Rectangle(102, 105, 98, 20));
    cmdModificar.setText("MODIFICAR");
    cmdModificar.addActionListener(new
                                   DlgCatalogoClasificacionClientes_cmdModificar_actionAdapter(this));
    cmdEliminar.setBounds(new Rectangle(204, 105, 100, 20));
    cmdEliminar.setText("ELIMINAR");
    cmdEliminar.addActionListener(new
        DlgCatalogoClasificacionClientes_cmdEliminar_actionAdapter(this));
    cmdAceptar.setBounds(new Rectangle(56, 139, 89, 25));
    cmdAceptar.setText("ACEPTAR");
    cmdAceptar.addActionListener(new
                                 DlgCatalogoClasificacionClientes_cmdAceptar_actionAdapter(this));
    cmdCancelar.setBounds(new Rectangle(156, 139, 91, 25));
    cmdCancelar.setText("CANCELAR");
    cmdCancelar.addActionListener(new
        DlgCatalogoClasificacionClientes_cmdCancelar_actionAdapter(this));
    this.addWindowListener(new
                           DlgCatalogoClasificacionClientes_this_windowAdapter(this));
    cmdBuscar.addActionListener(new
                                DlgCatalogoClasificacionClientes_cmdBuscar_actionAdapter(this));
    lstClasificaciones.setModel(model);
    lstClasificaciones.addMouseListener(new
                                        DlgCatalogoClasificacionClientes_lstClasificaciones_mouseAdapter(this));
    this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
    jPanel1.setLayout(borderLayout2);
    jLabel1.setText("CLASIFICACION:");
    txtBuscar.setText("");
    cmdBuscar.setText("BUSCAR");
    jPanel2.setLayout(borderLayout3);
    jPanel2.add(txtBuscar, java.awt.BorderLayout.CENTER);
    jPanel2.add(jLabel1, java.awt.BorderLayout.WEST);
    jPanel2.add(cmdBuscar, java.awt.BorderLayout.EAST);
    jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);
    jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jSplitPane1.add(jPanel3, JSplitPane.RIGHT);
    jPanel3.add(jLabel2);
    jPanel3.add(jLabel3);
    jPanel3.add(txtClasificacion);
    jPanel3.add(jLabel4);
    jPanel3.add(cmbTipo);
    jPanel3.add(cmdAgregar);
    jPanel3.add(cmdEliminar);
    jPanel3.add(cmdModificar);
    jPanel3.add(cmdCancelar);
    jPanel3.add(cmdAceptar);
    jScrollPane1.getViewport().add(lstClasificaciones);
    jSplitPane1.add(jPanel1, JSplitPane.LEFT);
    cmbTipo.addItem("CREDITO");
    cmbTipo.addItem("CONTADO");
  }

  void deshabilitarBotonesCompletos() {
    cmdAgregar.setEnabled(false);
    cmdModificar.setEnabled(false);
    cmdEliminar.setEnabled(false);
    cmdAceptar.setEnabled(false);
    cmdCancelar.setEnabled(false);
  }

  void deshabilitarCajas() {
    txtClasificacion.setEnabled(false);
    cmbTipo.setEnabled(false);
  }

  void habilitarCajas() {
    txtClasificacion.setEnabled(true);
    cmbTipo.setEnabled(true);
  }

  void deshabilitarBotones() {
    cmdAgregar.setEnabled(false);
    cmdModificar.setEnabled(false);
    cmdEliminar.setEnabled(false);
    this.lstClasificaciones.setEnabled(false);
    this.txtBuscar.setEnabled(false);
    this.cmdBuscar.setEnabled(false);
    cmdAceptar.setEnabled(true);
    cmdCancelar.setEnabled(true);
  }

  void habilitarBotones() {
    cmdAgregar.setEnabled(true);
    cmdModificar.setEnabled(true);
    cmdEliminar.setEnabled(true);
    this.lstClasificaciones.setEnabled(true);
    this.txtBuscar.setEnabled(true);
    this.cmdBuscar.setEnabled(true);
    cmdAceptar.setEnabled(false);
    cmdCancelar.setEnabled(false);
  }

  void limpiarCajas() {
    txtClasificacion.setText("");
    cmbTipo.setSelectedIndex( -1);
  }

  public void cmdAgregar_actionPerformed(ActionEvent e) {
    operacion = "AGREGAR";
    limpiarCajas();
    habilitarCajas();
    deshabilitarBotones();
  }

  public void cmdModificar_actionPerformed(ActionEvent e) {
    operacion = "MODIFICAR";
    habilitarCajas();
    deshabilitarBotones();
  }

  public void cmdAceptar_actionPerformed(ActionEvent e) {
    cc.setClasificacion(txtClasificacion.getText());
    cc.setTipo(cmbTipo.getSelectedItem().toString());
    this.deshabilitarBotonesCompletos();
    if (operacion.equals("AGREGAR")) {
      if (DaoClientesSucursal.addClasificacion(cc)) {
        JOptionPane.showMessageDialog(this,
                                      "CLASIFICACION AGREGADA EXITOSAMENTE",
                                      AppInstance.nombreNegocio,
                                      JOptionPane.INFORMATION_MESSAGE);
        this.limpiarCajas();
        this.deshabilitarCajas();
        this.habilitarBotones();
      }
      else {
        JOptionPane.showMessageDialog(this, "ERROR AL AGREGAR LA CLASIFICACION",
                                      AppInstance.nombreNegocio,
                                      JOptionPane.ERROR_MESSAGE);
        this.deshabilitarBotones();
      }
    }
    else if (operacion.equals("MODIFICAR")) {
      if (DaoClientesSucursal.updateClasificacion(cc)) {
        JOptionPane.showMessageDialog(this,
                                      "CLASIFICACION MODIFICADA EXITOSAMENTE",
                                      AppInstance.nombreNegocio,
                                      JOptionPane.INFORMATION_MESSAGE);
        this.limpiarCajas();
        this.deshabilitarCajas();
        this.habilitarBotones();

      }
      else {
        JOptionPane.showMessageDialog(this,
                                      "ERROR AL MODIFICAR LA CLASIFICACION",
                                      AppInstance.nombreNegocio,
                                      JOptionPane.ERROR_MESSAGE);
        this.deshabilitarBotones();
      }

    }
    operacion = "";
  }

  public void this_windowOpened(WindowEvent e) {
    this.deshabilitarBotonesCompletos();
    this.deshabilitarCajas();
  }

  public void cmdBuscar_actionPerformed(ActionEvent e) {
    model.clear();
//BUSCAMOS LA LISTA DE CLASIFICACIONES
    lstClasificacion = DaoClientesSucursal.findClasificaciones(txtBuscar.
        getText());
    for (ClasificacionCliente cc : lstClasificacion) {
      model.addElement(cc.getClasificacion());
    }
  }

  public void lstClasificaciones_mouseClicked(MouseEvent e) {
    if (lstClasificaciones.isEnabled()){
      cc = lstClasificacion.get(lstClasificaciones.getSelectedIndex());
      this.txtClasificacion.setText(cc.getClasificacion());
      this.cmbTipo.setSelectedItem(cc.getTipo());
      this.habilitarBotones();
    }
  }

  public void cmdEliminar_actionPerformed(ActionEvent e) {
    this.deshabilitarBotonesCompletos();
int i = JOptionPane.showConfirmDialog(this,"ESTA SEGURO QUE DESEA ELIMINAR LA CLASIFICACION?",AppInstance.nombreNegocio,JOptionPane.YES_NO_OPTION);
    if(i==JOptionPane.YES_OPTION){
      if(DaoClientesSucursal.deleteClasificacion(cc.getId())){
        JOptionPane.showMessageDialog(this,
                                      "CLASIFICACION ELIMINADA EXITOSAMENTE",
                                      AppInstance.nombreNegocio,
                                      JOptionPane.INFORMATION_MESSAGE);

      }else{
        JOptionPane.showMessageDialog(this,
                                    "NO SE PUDO ELIMINAR LA CLASIFICACION",
                                    AppInstance.nombreNegocio,
                                    JOptionPane.INFORMATION_MESSAGE);
      }

    }
          this.habilitarBotones();
  }

  public void cmdCancelar_actionPerformed(ActionEvent e) {
this.operacion="";
    this.habilitarBotones();
    this.deshabilitarCajas();
  }
}

class DlgCatalogoClasificacionClientes_cmdEliminar_actionAdapter
    implements ActionListener {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_cmdEliminar_actionAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdEliminar_actionPerformed(e);
  }
}

class DlgCatalogoClasificacionClientes_cmdCancelar_actionAdapter
    implements ActionListener {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_cmdCancelar_actionAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class DlgCatalogoClasificacionClientes_lstClasificaciones_mouseAdapter
    extends MouseAdapter {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_lstClasificaciones_mouseAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {

    adaptee.lstClasificaciones_mouseClicked(e);
  }
}

class DlgCatalogoClasificacionClientes_cmdBuscar_actionAdapter
    implements ActionListener {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_cmdBuscar_actionAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscar_actionPerformed(e);
  }
}

class DlgCatalogoClasificacionClientes_this_windowAdapter
    extends WindowAdapter {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_this_windowAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DlgCatalogoClasificacionClientes_cmdAceptar_actionAdapter
    implements ActionListener {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_cmdAceptar_actionAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}

class DlgCatalogoClasificacionClientes_cmdModificar_actionAdapter
    implements ActionListener {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_cmdModificar_actionAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdModificar_actionPerformed(e);
  }
}

class DlgCatalogoClasificacionClientes_cmdAgregar_actionAdapter
    implements ActionListener {
  private DlgCatalogoClasificacionClientes adaptee;
  DlgCatalogoClasificacionClientes_cmdAgregar_actionAdapter(
      DlgCatalogoClasificacionClientes adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdAgregar_actionPerformed(e);
  }
}
