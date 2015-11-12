package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DlgImprimirEtiquetas
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public DlgImprimirEtiquetas(Frame owner, String title, boolean modal) {
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

  public DlgImprimirEtiquetas() {
    this(new Frame(), "DlgImprimirEtiquetas", false);
  }

  private void jbInit() throws Exception {
    this.getContentPane().setLayout(null);
    jLabel1.setText("Indique la posicion a partir de la cual imprimira");
    jLabel1.setBounds(new Rectangle(43, 20, 227, 15));
    cmdAceptar.addMouseListener(new
                                DlgImprimirEtiquetas_cmdAceptar_mouseAdapter(this));
    cmdCancelar.addMouseListener(new
                                 DlgImprimirEtiquetas_cmdCancelar_mouseAdapter(this));
    this.getContentPane().add(jLabel1);
    cmdCancelar.setBounds(new Rectangle(157, 65, 76, 23));
    cmdCancelar.setText("Cancelar");
    cmdAceptar.setBounds(new Rectangle(72, 65, 76, 23));
    cmdAceptar.setText("Aceptar");
    this.getContentPane().add(txtPosicion);
    this.getContentPane().add(cmdCancelar);
    this.getContentPane().add(cmdAceptar);
    txtPosicion.setText("1");
    txtPosicion.setBounds(new Rectangle(43, 38, 217, 22));
  }

  JLabel jLabel1 = new JLabel();
  JTextField txtPosicion = new JTextField();
  JButton cmdAceptar = new JButton();
  JButton cmdCancelar = new JButton();
  int posicion;
  public void cmdAceptar_mouseClicked(MouseEvent e) {
    try {
      posicion = Integer.parseInt(txtPosicion.getText());
      this.setVisible(false);
    }
    catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Posicion incorrecta");
    }

  }

  public void cmdCancelar_mouseClicked(MouseEvent e) {
    this.posicion = 0;
    this.setVisible(false);
  }
}

class DlgImprimirEtiquetas_cmdCancelar_mouseAdapter
    extends MouseAdapter {
  private DlgImprimirEtiquetas adaptee;
  DlgImprimirEtiquetas_cmdCancelar_mouseAdapter(DlgImprimirEtiquetas adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.cmdCancelar_mouseClicked(e);
  }
}

class DlgImprimirEtiquetas_cmdAceptar_mouseAdapter
    extends MouseAdapter {
  private DlgImprimirEtiquetas adaptee;
  DlgImprimirEtiquetas_cmdAceptar_mouseAdapter(DlgImprimirEtiquetas adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.cmdAceptar_mouseClicked(e);
  }
}
