package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgMostrarVale extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DlgMostrarVale(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgMostrarVale() {
		this(new Frame(), "DlgMostrarVale", false);
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		jLabel1.setText("Número de vale:");
		jLabel1.setBounds(new Rectangle(30, 18, 132, 29));
		cmdAceptar.setBounds(new Rectangle(46, 131, 100, 39));
		cmdAceptar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdAceptar.setText("ACEPTAR");
		cmdAceptar
				.addActionListener(new DlgMostrarVale_cmdAceptar_actionAdapter(
						this));

		this.getContentPane().add(jLabel1);
		this.getContentPane().setBackground(Color.white);
		lblMonto.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblMonto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMonto.setText("$345.00");
		lblMonto.setBounds(new Rectangle(36, 92, 122, 33));
		jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		jLabel3.setText("Monto:");
		jLabel3.setBounds(new Rectangle(30, 66, 164, 35));
		this.getContentPane().add(lblNoVale);
		this.getContentPane().add(jLabel3);
		this.getContentPane().add(cmdAceptar);
		this.getContentPane().add(lblMonto);
		lblNoVale.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblNoVale.setToolTipText("");
		lblNoVale.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNoVale.setText("33");
		lblNoVale.setBounds(new Rectangle(30, 39, 128, 34));
	}

	JLabel jLabel1 = new JLabel();
	JLabel lblNoVale = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel lblMonto = new JLabel();
	JButton cmdAceptar = new JButton();

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}

class DlgMostrarVale_cmdAceptar_actionAdapter implements ActionListener {
	private DlgMostrarVale adaptee;

	DlgMostrarVale_cmdAceptar_actionAdapter(DlgMostrarVale adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}
