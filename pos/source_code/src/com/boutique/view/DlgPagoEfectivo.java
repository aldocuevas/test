package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgPagoEfectivo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double monto;
	NumberFormat number;

	public DlgPagoEfectivo(Frame owner, String title, boolean modal) {

		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgPagoEfectivo(double monto) {
		this(new Frame(), "DlgPagoEfectivo", false);
		txtMonto.setText(number.format(monto));
	}

	public double getMonto() {
		return monto;
	}

	private void jbInit() throws Exception {
		number = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
		this.getContentPane().setLayout(null);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel1.setText("Monto a pagar:");
		jLabel1.setBounds(new Rectangle(74, 9, 145, 39));
		txtMonto.addActionListener(new DlgPagoEfectivo_txtMonto_actionAdapter(
				this));
		this.setTitle("PAGO EN EFECTIVO");
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(txtMonto);
		txtMonto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 50));
		txtMonto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMonto.setBounds(new Rectangle(34, 67, 228, 75));
		txtMonto.requestFocus();
	}

	JLabel jLabel1 = new JLabel();
	JTextField txtMonto = new JTextField();

	public void txtMonto_actionPerformed(ActionEvent e) {
		try {

			monto = number.parse(txtMonto.getText()).doubleValue();
			this.setVisible(false);
		} catch (java.text.ParseException ex) {
			JOptionPane.showMessageDialog(null,
					"La cantidad indicada no es correcta",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

class DlgPagoEfectivo_txtMonto_actionAdapter implements ActionListener {
	private DlgPagoEfectivo adaptee;

	DlgPagoEfectivo_txtMonto_actionAdapter(DlgPagoEfectivo adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtMonto_actionPerformed(e);
	}
}
