package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.engine.impl.*;
/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class DlgAgregarInventarioLocal
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 9006775971956256282L;

JPanel panel1 = new JPanel();

  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel2 = new JLabel();
  JComboBox cmbDescripcion = new JComboBox();
  JLabel jLabel3 = new JLabel();
  JTextField txtCosto = new JTextField();
  JPanel jPanel2 = new JPanel();
  JButton cmdAgregar = new JButton();

  java.util.List<TipoProducto> listaTipoProductos = DaoTipoProductos.findAllLocal();
  TipoProducto tipoProducto = new TipoProducto();
//  DirectorioInventarioLocal dirLocal = new DirectorioInventarioLocal();
  public InventarioLocalEngine engine = new InventarioLocalEngine();
  public boolean operacion = false;
  JButton cmdCancelar = new JButton();
  public DlgAgregarInventarioLocal(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public DlgAgregarInventarioLocal() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    for (TipoProducto tp : listaTipoProductos) {
     this.cmbDescripcion.addItem(tp.getNombre());
   }


    panel1.setLayout(borderLayout1);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Agregar producto");
    jPanel1.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(4);
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel2.setText("Selecciona el producto");
    jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel3.setText("Indique el costo:");
    cmdAgregar.setText("Agregar");
    cmdAgregar.addActionListener(new
        DlgAgregarInventarioLocal_cmdAgregar_actionAdapter(this));
    txtCosto.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    txtCosto.setText("");
    txtCosto.addActionListener(new
                               DlgAgregarInventarioLocal_txtCosto_actionAdapter(this));
    cmdCancelar.setMnemonic('C');
    cmdCancelar.setText("Cancelar");
    cmdCancelar.addActionListener(new
                                  DlgAgregarInventarioLocal_cmdCancelar_actionAdapter(this));
    cmbDescripcion.addItemListener(new
                                   DlgAgregarInventarioLocal_cmbDescripcion_itemAdapter(this));
    cmbDescripcion.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));

    getContentPane().add(panel1);
    panel1.add(jLabel1, BorderLayout.NORTH);
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jLabel2, null);
    jPanel1.add(cmbDescripcion, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(txtCosto, null);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(cmdAgregar, null);
    jPanel2.add(cmdCancelar, null);
    this.setModal(true);
    this.pack();
  }


  void cmdCancelar_actionPerformed(ActionEvent e) {
    this.operacion = false;
    this.setVisible(false);
  }

  void txtCosto_actionPerformed(ActionEvent e) {
    try {
      tipoProducto = this.listaTipoProductos.get(this.cmbDescripcion.
                                                 getSelectedIndex());
      double monto = Double.parseDouble(txtCosto.getText());
//Tenemos el numero, generamos el producto
      engine.agregarInventarioLocal(monto, tipoProducto,AppInstance.boutique().getId());
      //Si hay etiqueta tenemos producto
     if(engine.etiqueta()==null){
        JOptionPane.showMessageDialog(this.getRootPane(),
                                      "Error al registrar el producto",
                                      "Atencion", JOptionPane.WARNING_MESSAGE);
      }
      this.setVisible(false);
    }
    catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this.getRootPane(),
                                    "Indique una cantidad válida", "Atencion",
                                    JOptionPane.WARNING_MESSAGE);
    }

  }

  void cmbDescripcion_itemStateChanged(ItemEvent e) {

  }

  public void cmdAgregar_actionPerformed(ActionEvent e) {
    try {
      tipoProducto = this.listaTipoProductos.get(this.cmbDescripcion.
                                                 getSelectedIndex());
      double monto = Double.parseDouble(txtCosto.getText());
//Tenemos el numero, generamos el producto
      engine.agregarInventarioLocal(monto, tipoProducto,AppInstance.boutique().getId());
      //Si hay etiqueta tenemos producto
     if(engine.etiqueta()==null){
        JOptionPane.showMessageDialog(this.getRootPane(),
                                      "Error al registrar el producto",
                                      "Atencion", JOptionPane.WARNING_MESSAGE);
      }
      this.setVisible(false);
    }
    catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this.getRootPane(),
                                    "Indique una cantidad válida", "Atencion",
                                    JOptionPane.WARNING_MESSAGE);
    }


  }

}

class DlgAgregarInventarioLocal_cmdAgregar_actionAdapter
    implements ActionListener {
  private DlgAgregarInventarioLocal adaptee;
  DlgAgregarInventarioLocal_cmdAgregar_actionAdapter(DlgAgregarInventarioLocal
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAgregar_actionPerformed(e);
  }
}

class DlgAgregarInventarioLocal_cmdCancelar_actionAdapter
    implements java.awt.event.ActionListener {
  DlgAgregarInventarioLocal adaptee;

  DlgAgregarInventarioLocal_cmdCancelar_actionAdapter(
      DlgAgregarInventarioLocal adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class DlgAgregarInventarioLocal_txtCosto_actionAdapter
    implements java.awt.event.ActionListener {
  DlgAgregarInventarioLocal adaptee;

  DlgAgregarInventarioLocal_txtCosto_actionAdapter(DlgAgregarInventarioLocal
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtCosto_actionPerformed(e);
  }
}

class DlgAgregarInventarioLocal_cmbDescripcion_itemAdapter
    implements java.awt.event.ItemListener {
  DlgAgregarInventarioLocal adaptee;

  DlgAgregarInventarioLocal_cmbDescripcion_itemAdapter(
      DlgAgregarInventarioLocal adaptee) {
    this.adaptee = adaptee;
  }

  public void itemStateChanged(ItemEvent e) {
    adaptee.cmbDescripcion_itemStateChanged(e);
  }
}
