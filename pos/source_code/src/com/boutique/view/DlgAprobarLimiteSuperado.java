package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.boutique.engine.impl.AppInstance;

/**
 * <p>Title: boutique management</p>
 *
 * <p>Description: Sistema de administracion de boitiques</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: SESTO</p>
 *
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class DlgAprobarLimiteSuperado
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 8254025857954951189L;
public boolean accion = false;
  public double excedente=0.0;
  public DlgAprobarLimiteSuperado(Frame owner, String title, boolean modal) {
    super(owner, title, modal);

  }

  public DlgAprobarLimiteSuperado(double excedente) {
    this(new Frame(), "DlgAprobarLimiteSuperado", false);
    this.excedente=excedente;
    try {
     setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     jbInit();
     pack();
   }
   catch (Exception exception) {
     exception.printStackTrace();
   }

  }

  private void jbInit() throws Exception {
    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(Color.white);
    this.setUndecorated(true);
    jLabel1.setForeground(Color.red);
    jLabel1.setText(
        "EL MONTO DE LA VENTA ES MAYOR AL MONTO DISPONIBLE DEL CLIENTE");
    jLabel1.setBounds(new Rectangle(9, 11, 375, 23));
    cmdAceptar.setBounds(new Rectangle(98, 133, 99, 27));
    cmdAceptar.setText("ACEPTAR");
    cmdAceptar.addActionListener(new
                                 DlgAprobarLimiteSuperado_cmdAceptar_actionAdapter(this));
    cmdCancelar.setBounds(new Rectangle(199, 133, 98, 27));
    cmdCancelar.setText("CANCELAR");
    cmdCancelar.addActionListener(new
                                  DlgAprobarLimiteSuperado_cmdCancelar_actionAdapter(this));
    this.addWindowListener(new DlgAprobarLimiteSuperado_this_windowAdapter(this));
    jTextPane1.setEditable(false);
    txtClave.addActionListener(new
                               DlgAprobarLimiteSuperado_txtClave_actionAdapter(this));
    this.getContentPane().add(jLabel1);
    jLabel2.setText("Clave:");
    jLabel2.setBounds(new Rectangle(85, 103, 37, 22));
    txtClave.setText("");
    txtClave.setBounds(new Rectangle(125, 101, 137, 26));
    this.getContentPane().add(jTextPane1);
    this.getContentPane().add(cmdCancelar);
    this.getContentPane().add(cmdAceptar);
    this.getContentPane().add(txtClave);
    this.getContentPane().add(jLabel2);
    jTextPane1.setText(
      "La venta tiene un excedente de $" +
        AppInstance.number.format(excedente) + " al limite credito del cliente. Para continuar con la venta indique la clave de autorizacion y de " +
        "clic en continuar, de lo contrario de clic en cancelar");
    jTextPane1.setBounds(new Rectangle(8, 36, 361, 52));


  }

  JLabel jLabel1 = new JLabel();
  JTextPane jTextPane1 = new JTextPane();
  JPasswordField txtClave = new JPasswordField();
  JLabel jLabel2 = new JLabel();
  JButton cmdAceptar = new JButton();
  JButton cmdCancelar = new JButton();
  public void cmdAceptar_actionPerformed(ActionEvent e) {
    accion = true;
    this.setVisible(false);
  }

  public void cmdCancelar_actionPerformed(ActionEvent e) {
    this.accion = false;
    this.setVisible(false);
  }

  public void this_windowOpened(WindowEvent e) {
    txtClave.requestFocus();
  }

  public void txtClave_actionPerformed(ActionEvent e) {
    accion = true;
    this.setVisible(false);
  }
}

class DlgAprobarLimiteSuperado_this_windowAdapter
    extends WindowAdapter {
  private DlgAprobarLimiteSuperado adaptee;
  DlgAprobarLimiteSuperado_this_windowAdapter(DlgAprobarLimiteSuperado adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DlgAprobarLimiteSuperado_txtClave_actionAdapter
    implements ActionListener {
  private DlgAprobarLimiteSuperado adaptee;
  DlgAprobarLimiteSuperado_txtClave_actionAdapter(DlgAprobarLimiteSuperado
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtClave_actionPerformed(e);
  }
}

class DlgAprobarLimiteSuperado_cmdCancelar_actionAdapter
    implements ActionListener {
  private DlgAprobarLimiteSuperado adaptee;
  DlgAprobarLimiteSuperado_cmdCancelar_actionAdapter(DlgAprobarLimiteSuperado
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class DlgAprobarLimiteSuperado_cmdAceptar_actionAdapter
    implements ActionListener {
  private DlgAprobarLimiteSuperado adaptee;
  DlgAprobarLimiteSuperado_cmdAceptar_actionAdapter(DlgAprobarLimiteSuperado
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}
