package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.awt.event.*;

public class DlgIndiqueClave
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel Anticipo = new JLabel();
  JPasswordField txtClave = new JPasswordField();
  JPanel jPanel1 = new JPanel();
  JButton cmdAceptar = new JButton();
  JButton cmdCancelar = new JButton();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JTextField txtMonto = new JTextField();
  JLabel jLabel3 = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  java.text.NumberFormat number = java.text.NumberFormat.getNumberInstance();
  public double monto;
  public boolean doIt=false;
  public DlgIndiqueClave(Frame owner, String title, boolean modal) {
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

  public DlgIndiqueClave() {
    this(new Frame(), "DlgIndiqueClave", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    Anticipo.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
    Anticipo.setHorizontalAlignment(SwingConstants.CENTER);
    Anticipo.setText("Anticipo");
    cmdAceptar.setMnemonic('A');
    cmdAceptar.setText("Aceptar");
    cmdAceptar.addActionListener(new DlgIndiqueClave_cmdAceptar_actionAdapter(this));
    cmdCancelar.setMnemonic('C');
    cmdCancelar.setText("Cancelar");
    cmdCancelar.addActionListener(new DlgIndiqueClave_cmdCancelar_actionAdapter(this));
    jLabel2.setText("Clave de operacion:");
    txtMonto.setText("");
    txtMonto.addActionListener(new DlgIndiqueClave_txtMonto_actionAdapter(this));
    jLabel3.setText("Monto del anticipo:");
    jPanel2.setLayout(gridLayout1);
    gridLayout1.setColumns(1);
    gridLayout1.setRows(4);
    getContentPane().add(panel1);
    panel1.add(Anticipo, java.awt.BorderLayout.NORTH);
    jPanel1.add(cmdAceptar);
    jPanel1.add(cmdCancelar);
    panel1.add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(jLabel2);
    jPanel2.add(txtClave);
    jPanel2.add(jLabel3);
    jPanel2.add(txtMonto);
    panel1.add(jPanel1, java.awt.BorderLayout.SOUTH);
    this.setModal(true);
    this.pack();
    this.txtClave.requestFocus();
  }

  public void cmdAceptar_actionPerformed(ActionEvent e) {
    if (String.valueOf(txtClave.getPassword()).equals("4597")) {
      //Aceptamos le monto
      try {
        monto = number.parse(this.txtMonto.getText()).doubleValue();
        doIt=true;
        this.setVisible(false);
      }
      catch (ParseException ex) {
        JOptionPane.showMessageDialog(null,"La cantidad no es valida","Error",JOptionPane.WARNING_MESSAGE);
        doIt=false;
      }
    }
  }

  public void cmdCancelar_actionPerformed(ActionEvent e) {
doIt=false;
    this.setVisible(false);
  }

  void txtMonto_actionPerformed(ActionEvent e) {
    if (String.valueOf(txtClave.getPassword()).equals("4597")) {
         //Aceptamos le monto
         try {
           monto = number.parse(this.txtMonto.getText()).doubleValue();
           doIt=true;
           this.setVisible(false);
         }
         catch (ParseException ex) {
           JOptionPane.showMessageDialog(null,"La cantidad no es valida","Error",JOptionPane.WARNING_MESSAGE);
           doIt=false;
         }
       }

  }
}

class DlgIndiqueClave_cmdCancelar_actionAdapter
    implements ActionListener {
  private DlgIndiqueClave adaptee;
  DlgIndiqueClave_cmdCancelar_actionAdapter(DlgIndiqueClave adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class DlgIndiqueClave_cmdAceptar_actionAdapter
    implements ActionListener {
  private DlgIndiqueClave adaptee;
  DlgIndiqueClave_cmdAceptar_actionAdapter(DlgIndiqueClave adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}

class DlgIndiqueClave_txtMonto_actionAdapter implements java.awt.event.ActionListener {
  DlgIndiqueClave adaptee;

  DlgIndiqueClave_txtMonto_actionAdapter(DlgIndiqueClave adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.txtMonto_actionPerformed(e);
  }
}
