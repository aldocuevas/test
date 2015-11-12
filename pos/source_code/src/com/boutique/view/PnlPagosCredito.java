package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class PnlPagosCredito
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel2 = new JPanel();
  JRadioButton rSemanal = new JRadioButton();
  JRadioButton rQuincenal = new JRadioButton();
  JRadioButton rMensual = new JRadioButton();
  JLabel jLabel3 = new JLabel();
  JComboBox cmbCantidadPagos = new JComboBox();
  DefaultComboBoxModel comboSemanal = new DefaultComboBoxModel();
  DefaultComboBoxModel comboQuincenal = new DefaultComboBoxModel();
  DefaultComboBoxModel comboMensual = new DefaultComboBoxModel();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JPanel pnlPagos = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JScrollPane scrollPagos = new JScrollPane();
  JTable tblPagos = new JTable();
  ModelPagosCredito modelPagosCredito1 = new ModelPagosCredito();
  public double total;
  private int diasPorPago;
  private int noPagos;
  public PnlPagosCredito() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {

    this.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    jLabel2.setText("Tipo de pago");
    rSemanal.setText("Semanal");
    rSemanal.addActionListener(new PnlPagosCredito_rSemanal_actionAdapter(this));
    rQuincenal.setSelected(true);
    rQuincenal.setText("Quincenal");
    rQuincenal.addActionListener(new PnlPagosCredito_rQuincenal_actionAdapter(this));
    rMensual.setText("Mensual");
    rMensual.addActionListener(new PnlPagosCredito_rMensual_actionAdapter(this));
    jLabel3.setText("Cantidad de pagos:");
    pnlPagos.setLayout(borderLayout3);
    tblPagos.setModel(modelPagosCredito1);
    tblPagos.setShowHorizontalLines(false);
    tblPagos.setShowVerticalLines(false);
    cmbCantidadPagos.addActionListener(new
                                       PnlPagosCredito_cmbCantidadPagos_actionAdapter(this));
    this.add(jPanel1, BorderLayout.SOUTH);
    jPanel2.add(rSemanal, null);
    jPanel2.add(rQuincenal, null);
    jPanel2.add(rMensual, null);
    jPanel2.add(jLabel3, null);
    jPanel2.add(cmbCantidadPagos, null);
    this.add(scrollPagos, BorderLayout.CENTER);
    scrollPagos.getViewport().add(tblPagos, null);
    this.add(pnlPagos, BorderLayout.NORTH);
    pnlPagos.add(jLabel2, BorderLayout.NORTH);
    pnlPagos.add(jPanel2, BorderLayout.CENTER);
    comboSemanal.addElement("1");
    comboSemanal.addElement("2");
    comboSemanal.addElement("3");
    comboSemanal.addElement("4");
    comboSemanal.addElement("5");
    comboSemanal.addElement("6");
    comboQuincenal.addElement("1");
    comboQuincenal.addElement("2");
    comboQuincenal.addElement("3");
    comboQuincenal.addElement("4");
    comboQuincenal.addElement("5");
    comboQuincenal.addElement("6");
    comboMensual.addElement("1");
    comboMensual.addElement("2");
    buttonGroup1.add(rSemanal);
    buttonGroup1.add(rQuincenal);
    buttonGroup1.add(rMensual);
    this.cmbCantidadPagos.setModel(this.comboQuincenal);

  }

  void rSemanal_actionPerformed(ActionEvent e) {
    if (rSemanal.isSelected()) {
      this.cmbCantidadPagos.setModel(this.comboSemanal);
      this.setPagosQuincenales();
      this.diasPorPago = 7;
    }
  }

  void rQuincenal_actionPerformed(ActionEvent e) {
    if (rQuincenal.isSelected()) {
      this.cmbCantidadPagos.setModel(this.comboQuincenal);
      this.setPagosQuincenales();
      this.diasPorPago = 15;
    }
  }

  void rMensual_actionPerformed(ActionEvent e) {
    if (this.rMensual.isSelected()) {
      this.cmbCantidadPagos.setModel(this.comboMensual);
      this.setPagosQuincenales();
      this.diasPorPago = 31;
    }
  }

  void setPagosQuincenales() {
    //Ponemos los pagos en la tabla
    this.cmbCantidadPagos.setSelectedIndex(this.cmbCantidadPagos.getItemCount() -
                                           1);
    this.noPagos = Integer.parseInt(this.cmbCantidadPagos.getSelectedItem().
                                    toString());
    if (rSemanal.isSelected()) {

      this.diasPorPago = 7;
      /*  this.modelPagosCredito1.addPagos(new java.util.Date(), 7,
                                         Integer.
                                         parseInt(this.cmbCantidadPagos.
                                                  getSelectedItem().
                                                  toString()), total);*/
    }
    else if (rQuincenal.isSelected()) {
      /*      this.diasPorPago=15;
            this.modelPagosCredito1.addPagos(new java.util.Date(), 15,
                                             Integer.
                                             parseInt(this.cmbCantidadPagos.
                                                      getSelectedItem().
                                                      toString()), total);*/
    }
    else if (rMensual.isSelected()) {
      /*  this.diasPorPago=30;
        this.modelPagosCredito1.addPagos(new java.util.Date(), 30,
                                         Integer.
                                         parseInt(this.cmbCantidadPagos.
                                                  getSelectedItem().
                                                  toString()), total);*/
    }
  }

  void cmbCantidadPagos_actionPerformed(ActionEvent e) {
    this.noPagos = this.cmbCantidadPagos.getSelectedIndex() + 1;
    this.setPagosQuincenales();
  }

  public int getNoPagos() {
    return noPagos;
  }

  public void setNoPagos(int noPagos) {
    this.noPagos = noPagos;
  }

  public int getDiasPorPago() {
    return diasPorPago;
  }

  public void setDiasPorPago(int diasPorPago) {
    this.diasPorPago = diasPorPago;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
    this.setPagosQuincenales();
  }

}

class PnlPagosCredito_rSemanal_actionAdapter
    implements java.awt.event.ActionListener {
  PnlPagosCredito adaptee;

  PnlPagosCredito_rSemanal_actionAdapter(PnlPagosCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.rSemanal_actionPerformed(e);
  }
}

class PnlPagosCredito_rQuincenal_actionAdapter
    implements java.awt.event.ActionListener {
  PnlPagosCredito adaptee;

  PnlPagosCredito_rQuincenal_actionAdapter(PnlPagosCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.rQuincenal_actionPerformed(e);
  }
}

class PnlPagosCredito_rMensual_actionAdapter
    implements java.awt.event.ActionListener {
  PnlPagosCredito adaptee;

  PnlPagosCredito_rMensual_actionAdapter(PnlPagosCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.rMensual_actionPerformed(e);
  }
}

class PnlPagosCredito_cmbCantidadPagos_actionAdapter
    implements java.awt.event.ActionListener {
  PnlPagosCredito adaptee;

  PnlPagosCredito_cmbCantidadPagos_actionAdapter(PnlPagosCredito adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmbCantidadPagos_actionPerformed(e);
  }
}
