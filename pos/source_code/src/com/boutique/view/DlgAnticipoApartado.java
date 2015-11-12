package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boutique.engine.impl.*;

public class DlgAnticipoApartado
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
java.text.NumberFormat number = java.text.NumberFormat.getInstance(java.util.
      Locale.ENGLISH);
  double pagoMinimo;
  double monto;
  boolean abonar=false;
  public DlgAnticipoApartado(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      number.setMaximumFractionDigits(2);
      number.setMinimumFractionDigits(2);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgAnticipoApartado(double pagoMinimo) {

    this(new Frame(), "DlgAnticipoApartado", false);
    this.pagoMinimo = pagoMinimo;
    txtMinimo.setText(number.format(pagoMinimo));
    txtMonto.setText(number.format(pagoMinimo));

  }

  private void jbInit() throws Exception {

    this.getContentPane().setLayout(null);
    lblTextoAbonar.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    if (com.boutique.engine.impl.AppInstance.boutique().isAnticipoApartadoLibre()) {
      lblTextoAbonar.setText("Cantidad sugerida a abonar:");
    }
    else {
      lblTextoAbonar.setText("Cantidad mínima a abonar:");
    }
    lblTextoAbonar.setBounds(new Rectangle(55, 4, 279, 40));
    cmdContinuar.setBounds(new Rectangle(55, 236, 139, 41));
    cmdContinuar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
    cmdContinuar.setText("CONTINUAR");
    cmdContinuar.addActionListener(new
                                   DlgAnticipoApartado_cmdContinuar_actionAdapter(this));
    cmdCancelar.setBounds(new Rectangle(201, 236, 139, 41));
    cmdCancelar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
    cmdCancelar.setText("CANCELAR");
    cmdCancelar.addActionListener(new
                                  DlgAnticipoApartado_cmdCancelar_actionAdapter(this));
    txtMonto.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
    txtMonto.setHorizontalAlignment(SwingConstants.TRAILING);
    txtMonto.setBounds(new Rectangle(56, 159, 281, 68));
    txtMonto.addActionListener(new DlgAnticipoApartado_txtMonto_actionAdapter(this));
    txtMinimo.setEnabled(false);
    txtMinimo.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
    txtMinimo.setDisabledTextColor(Color.black);
    txtMinimo.setHorizontalAlignment(SwingConstants.TRAILING);
    txtMinimo.setBounds(new Rectangle(55, 45, 281, 68));
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    this.getContentPane().setBackground(Color.white);
    this.setModal(true);
    this.setTitle("INDICAR ANTICIPO");
    this.getContentPane().add(cmdContinuar);
    this.getContentPane().add(cmdCancelar);
    this.getContentPane().add(txtMinimo);
    this.getContentPane().add(txtMonto);
    this.getContentPane().add(lblTextoAbonar);
    this.getContentPane().add(jLabel2);
    jLabel2.setText("Cantidad a abonar:");
    jLabel2.setBounds(new Rectangle(56, 118, 162, 42));
  }

  JLabel lblTextoAbonar = new JLabel();
  JLabel jLabel2 = new JLabel();
  JButton cmdCancelar = new JButton();
  JButton cmdContinuar = new JButton();
  JTextField txtMonto = new JTextField();
  JTextField txtMinimo = new JTextField();
  public void txtMonto_actionPerformed(ActionEvent e) {
    indicarMonto();
  }

  private void indicarMonto() {
    try {
      monto = Double.parseDouble(txtMonto.getText());
      if (!AppInstance.boutique().isAnticipoApartadoLibre()) {
        if (monto >= pagoMinimo) {
          abonar=true;
          this.setVisible(false);
        }
        else {
          JOptionPane.showMessageDialog(null,
                                        "La cantidad indicada es menor al monto minimo",
                                        com.boutique.engine.impl.AppInstance.
                                        nombreNegocio,
                                        JOptionPane.ERROR_MESSAGE);
          txtMonto.requestFocus();
        }
      }else{
        abonar=true;
        this.setVisible(false);
      }
    }
    catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(null, "La cantidad indicada no es correcta",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio, JOptionPane.ERROR_MESSAGE);
    }
  }

  public void cmdContinuar_actionPerformed(ActionEvent e) {
    indicarMonto();
  }

  public void cmdCancelar_actionPerformed(ActionEvent e) {
    this.abonar=false;
    this.setVisible(false);
  }
}

class DlgAnticipoApartado_cmdCancelar_actionAdapter
    implements ActionListener {
  private DlgAnticipoApartado adaptee;
  DlgAnticipoApartado_cmdCancelar_actionAdapter(DlgAnticipoApartado adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class DlgAnticipoApartado_cmdContinuar_actionAdapter
    implements ActionListener {
  private DlgAnticipoApartado adaptee;
  DlgAnticipoApartado_cmdContinuar_actionAdapter(DlgAnticipoApartado adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdContinuar_actionPerformed(e);
  }
}

class DlgAnticipoApartado_txtMonto_actionAdapter
    implements ActionListener {
  private DlgAnticipoApartado adaptee;
  DlgAnticipoApartado_txtMonto_actionAdapter(DlgAnticipoApartado adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtMonto_actionPerformed(e);
  }
}
