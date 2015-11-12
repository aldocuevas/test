package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgAnticipoCredito extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6601267527580808969L;
	java.text.NumberFormat number = java.text.NumberFormat
			.getInstance(java.util.Locale.ENGLISH);
	double pagoMinimo;
	double monto;

	public DlgAnticipoCredito(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			number.setMaximumFractionDigits(2);
			number.setMinimumFractionDigits(2);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgAnticipoCredito(double pagoMinimo) {

		this(new Frame(), "DlgAnticipoApartado", false);
		this.pagoMinimo = pagoMinimo;
		txtMinimo.setText(number.format(pagoMinimo));
		txtMonto.setText(number.format(pagoMinimo));
		txtMonto.requestFocus();
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		jLabel1.setToolTipText("");
		jLabel1.setText("Cantidad sugerida:");
		jLabel1.setBounds(new Rectangle(55, 4, 279, 40));
		cmdContinuar.setBounds(new Rectangle(55, 236, 139, 41));
		cmdContinuar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdContinuar.setText("CONTINUAR");
		cmdContinuar
				.addActionListener(new DlgAnticipoCredito_cmdContinuar_actionAdapter(
						this));
		cmdCancelar.setBounds(new Rectangle(201, 236, 139, 41));
		cmdCancelar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdCancelar.setText("CANCELAR");
		cmdCancelar
				.addActionListener(new DlgAnticipoCredito_cmdCancelar_actionAdapter(
						this));
		txtMonto.addActionListener(new DlgAnticipoCredito_txtMonto_actionAdapter(
				this));

		txtMonto.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		txtMonto.setHorizontalAlignment(SwingConstants.TRAILING);
		txtMonto.setBounds(new Rectangle(56, 159, 281, 68));
		txtMinimo.setEnabled(true);
		txtMinimo.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		txtMinimo.setDisabledTextColor(Color.black);
		txtMinimo.setEditable(false);
		txtMinimo.setHorizontalAlignment(SwingConstants.TRAILING);
		txtMinimo.setBounds(new Rectangle(55, 45, 281, 68));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		this.getContentPane().setBackground(Color.white);
		this.setModal(true);
		this.setTitle("ANTICIPO");
		this.getContentPane().add(cmdContinuar);
		this.getContentPane().add(cmdCancelar);
		this.getContentPane().add(txtMinimo);
		this.getContentPane().add(txtMonto);
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(jLabel2);
		jLabel2.setText("Monto del anticipo:");
		jLabel2.setBounds(new Rectangle(56, 118, 162, 42));
	}

	JLabel jLabel1 = new JLabel();
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
			this.setVisible(false);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null,
					"La cantidad indicada no es correcta",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			txtMonto.requestFocus();

		}
	}

	public void cmdContinuar_actionPerformed(ActionEvent e) {
		indicarMonto();
	}

	public void cmdCancelar_actionPerformed(ActionEvent e) {
		monto = 0;
		this.setVisible(false);
	}
}

class DlgAnticipoCredito_cmdCancelar_actionAdapter implements ActionListener {
	private DlgAnticipoCredito adaptee;

	DlgAnticipoCredito_cmdCancelar_actionAdapter(DlgAnticipoCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelar_actionPerformed(e);
	}
}

class DlgAnticipoCredito_cmdContinuar_actionAdapter implements ActionListener {
	private DlgAnticipoCredito adaptee;

	DlgAnticipoCredito_cmdContinuar_actionAdapter(DlgAnticipoCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdContinuar_actionPerformed(e);
	}
}

class DlgAnticipoCredito_txtMonto_actionAdapter implements ActionListener {
	private DlgAnticipoCredito adaptee;

	DlgAnticipoCredito_txtMonto_actionAdapter(DlgAnticipoCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtMonto_actionPerformed(e);
	}
}
