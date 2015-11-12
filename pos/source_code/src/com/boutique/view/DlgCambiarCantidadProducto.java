package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgCambiarCantidadProducto
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2860379175228496014L;
com.boutique.engine.impl.RegistroInventarioEngine engine;
  public DlgCambiarCantidadProducto(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgCambiarCantidadProducto() {
    this(new Frame(), "DlgCambiarCantidadProducto", false);
  }

  private void jbInit() throws Exception {
    this.getContentPane().setLayout(null);
    jLabel1.setToolTipText("");
    jLabel1.setText("Nueva cantidad:");
    jLabel1.setBounds(new Rectangle(36, 16, 86, 24));
    this.getContentPane().add(jLabel1);
    this.getContentPane().add(txtCantidad);
    txtCantidad.setText("");
    txtCantidad.setBounds(new Rectangle(34, 42, 115, 20));
    txtCantidad.addActionListener(new
                                  DlgCambiarCantidadProducto_txtCantidad_actionAdapter(this));
  }

  JLabel jLabel1 = new JLabel();
  JTextField txtCantidad = new JTextField();
  public void txtCantidad_actionPerformed(ActionEvent e) {
    try {
      int cantidad = Integer.parseInt(txtCantidad.getText());
     if(!engine.setNuevaCantidadEnProductoSeleccionado(cantidad)){
       JOptionPane.showMessageDialog(this,"No se pudo actualizar la cantidad de producto","Error",JOptionPane.ERROR_MESSAGE);
     }else{
       this.setVisible(false);
     }
    }
    catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(null,"Cantidad incorrecta","Cantidad incorrecta",JOptionPane.ERROR_MESSAGE);
    }
  }
}

class DlgCambiarCantidadProducto_txtCantidad_actionAdapter
    implements ActionListener {
  private DlgCambiarCantidadProducto adaptee;
  DlgCambiarCantidadProducto_txtCantidad_actionAdapter(
      DlgCambiarCantidadProducto adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.txtCantidad_actionPerformed(e);
  }
}
