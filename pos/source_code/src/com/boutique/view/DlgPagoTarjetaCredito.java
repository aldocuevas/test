package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class DlgPagoTarjetaCredito
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
DefaultComboBoxModel modelBancos = new DefaultComboBoxModel(AppInstance.
      getBancos().toArray());
  DefaultComboBoxModel modelPromociones = new DefaultComboBoxModel(AppInstance.
      getPromocionesBancarias().keySet().toArray());
  double monto;
  int promocion;
  String banco;
  NumberFormat number;

  public DlgPagoTarjetaCredito(Frame owner, String title, boolean modal) {
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

  public DlgPagoTarjetaCredito(double monto) {

    this(new Frame(), "PAGO CON TARJETA", false);
    this.txtMonto.setText(number.format(monto));
  }

  private void jbInit() throws Exception {
    number = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);
    number.setMaximumFractionDigits(2);
    number.setMinimumFractionDigits(2);
    this.getContentPane().setLayout(null);
    jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
    jLabel1.setText("Monto a pagar:");
    jLabel1.setBounds(new Rectangle(39, 204, 196, 39));
    cmbBancos.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    cmbBancos.setModel(modelBancos);
    cmbBancos.setBounds(new Rectangle(40, 51, 227, 51));
    cmbBancos.addActionListener(new
                                DlgPagoTarjetaCredito_cmbBancos_actionAdapter(this));
    cmbPromocionesBancarias.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    cmbPromocionesBancarias.setModel(modelPromociones);
    cmbPromocionesBancarias.setBounds(new Rectangle(43, 142, 220, 58));
    cmbPromocionesBancarias.addActionListener(new
                                              DlgPagoTarjetaCredito_cmbPromocionesBancarias_actionAdapter(this));
    jLabel3.setToolTipText("");
    txtMonto.addActionListener(new DlgPagoTarjetaCredito_txtMonto_actionAdapter(this));
    this.getContentPane().add(txtMonto);
    jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
    jLabel3.setText("Promoción:");
    jLabel3.setBounds(new Rectangle(42, 106, 201, 33));
    jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
    jLabel2.setText("Banco:");
    jLabel2.setBounds(new Rectangle(39, 8, 215, 34));
    this.getContentPane().add(cmbPromocionesBancarias);
    this.getContentPane().add(cmbBancos);
    this.getContentPane().add(jLabel2);
    this.getContentPane().add(jLabel3);
    this.getContentPane().add(jLabel1);
    txtMonto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 50));

    txtMonto.setHorizontalAlignment(SwingConstants.RIGHT);
    txtMonto.setBounds(new Rectangle(41, 243, 219, 75));
    this.banco = this.cmbBancos.getSelectedItem().toString();
    this.promocion = Integer.parseInt(AppInstance.getPromocionesBancarias().get(this.
        cmbPromocionesBancarias.getSelectedItem().toString()).toString());

  }

  public int getPromocion() {
    return promocion;
  }

  public String getBanco() {
    return banco;
  }

  public double getMonto() {
    return monto;
  }

  JLabel jLabel1 = new JLabel();
  JTextField txtMonto = new JTextField();
  JComboBox cmbBancos = new JComboBox();
  JComboBox cmbPromocionesBancarias = new JComboBox();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  public void cmbBancos_actionPerformed(ActionEvent e) {
    this.banco = cmbBancos.getSelectedItem().toString();
  }

  public void cmbPromocionesBancarias_actionPerformed(ActionEvent e) {
    this.promocion = Integer.parseInt(AppInstance.getPromocionesBancarias().get(this.
        cmbPromocionesBancarias.getSelectedItem().toString()).toString());
  }

  public void txtMonto_actionPerformed(ActionEvent e) {
    try {
      monto = number.parse(txtMonto.getText()).doubleValue();
      this.setVisible(false);
    }
    catch (java.text.ParseException ex) {
      JOptionPane.showMessageDialog(null, "La cantidad indicada no es correcta",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
    }
  }
}

class DlgPagoTarjetaCredito_txtMonto_actionAdapter
    implements ActionListener {
  private DlgPagoTarjetaCredito adaptee;
  DlgPagoTarjetaCredito_txtMonto_actionAdapter(DlgPagoTarjetaCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtMonto_actionPerformed(e);
  }
}

class DlgPagoTarjetaCredito_cmbPromocionesBancarias_actionAdapter
    implements ActionListener {
  private DlgPagoTarjetaCredito adaptee;
  DlgPagoTarjetaCredito_cmbPromocionesBancarias_actionAdapter(
      DlgPagoTarjetaCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmbPromocionesBancarias_actionPerformed(e);
  }
}

class DlgPagoTarjetaCredito_cmbBancos_actionAdapter
    implements ActionListener {
  private DlgPagoTarjetaCredito adaptee;
  DlgPagoTarjetaCredito_cmbBancos_actionAdapter(DlgPagoTarjetaCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmbBancos_actionPerformed(e);
  }
}
