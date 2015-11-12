package com.boutique.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.border.TitledBorder;

/**
 * <p>
 * Title: boutique management
 * </p>
 * <p>
 * Description: Sistema de administracion de boitiques
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: SESTO
 * </p>
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class FrmReciboRegreso extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jLabel1 = new JLabel();
	JTextField txtTogalAPagar = new JTextField();
	JLabel jLabel2 = new JLabel();
	JTextField txtRecibo = new JTextField();
	JLabel jLabel3 = new JLabel();
	JTextField txtCambio = new JTextField();
	JButton cmdCobrar = new JButton();
	private double totalPago;

	NumberFormat number = NumberFormat.getNumberInstance();
	TitledBorder titledBorder1 = new TitledBorder("");

	public FrmReciboRegreso() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText("CAMBIO:");
		jLabel1.setBounds(new Rectangle(19, 132, 96, 56));
		this.getContentPane().setLayout(null);
		txtTogalAPagar.setEnabled(false);
		txtTogalAPagar.setFont(new java.awt.Font("Arial", Font.PLAIN, 40));
		txtTogalAPagar.setBorder(BorderFactory.createEtchedBorder());
		txtTogalAPagar.setOpaque(true);
		txtTogalAPagar.setDisabledTextColor(Color.black);
		txtTogalAPagar.setText("0.00");
		txtTogalAPagar.setHorizontalAlignment(SwingConstants.LEADING);
		txtTogalAPagar.setBounds(new Rectangle(126, 14, 187, 56));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setText("TOTAL:");
		jLabel2.setBounds(new Rectangle(5, 14, 110, 56));
		txtRecibo.setFont(new java.awt.Font("Arial", Font.PLAIN, 40));
		txtRecibo.setBorder(BorderFactory.createEtchedBorder());
		txtRecibo.setText("");
		txtRecibo.setBounds(new Rectangle(126, 73, 187, 56));
		txtRecibo
				.addActionListener(new FrmReciboRegreso_txtRecibo_actionAdapter(
						this));
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel3.setText("RECIBIDO:");
		jLabel3.setBounds(new Rectangle(1, 73, 114, 56));
		txtCambio.setEnabled(false);
		txtCambio.setFont(new java.awt.Font("Arial", Font.PLAIN, 40));
		txtCambio.setForeground(Color.black);

		txtCambio.setBorder(BorderFactory.createEtchedBorder());
		txtCambio.setDisabledTextColor(Color.red);
		txtCambio.setBounds(new Rectangle(126, 132, 187, 56));
		cmdCobrar.setBounds(new Rectangle(75, 191, 167, 51));
		cmdCobrar.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		cmdCobrar.setText("ACEPTAR");
		cmdCobrar
				.addActionListener(new FrmReciboRegreso_cmdCobrar_actionAdapter(
						this));
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Total a pagar:");
		this.getContentPane().add(txtRecibo);
		this.getContentPane().add(txtTogalAPagar);
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(jLabel3);
		this.getContentPane().add(txtCambio);
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(cmdCobrar);
	}

	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
		this.txtTogalAPagar.setText(number.format(totalPago));
		this.txtRecibo.requestFocus();
	}

	void cmdCobrar_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	void txtRecibo_actionPerformed(ActionEvent e) {
		this.txtCambio.setText(number.format(Double.parseDouble(this.txtRecibo
				.getText()) - this.totalPago));
		this.cmdCobrar.requestFocus();
	}

}

class FrmReciboRegreso_cmdCobrar_actionAdapter implements
		java.awt.event.ActionListener {
	FrmReciboRegreso adaptee;

	FrmReciboRegreso_cmdCobrar_actionAdapter(FrmReciboRegreso adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCobrar_actionPerformed(e);
	}
}

class FrmReciboRegreso_txtRecibo_actionAdapter implements
		java.awt.event.ActionListener {
	FrmReciboRegreso adaptee;

	FrmReciboRegreso_txtRecibo_actionAdapter(FrmReciboRegreso adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtRecibo_actionPerformed(e);
	}
}
