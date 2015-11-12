package com.boutique.view;

import java.text.*;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgPagoCheque extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	double monto;
	String noCheque = "";
	String banco;
	NumberFormat number;
	JComboBox cmbBanco = new JComboBox();
	JLabel jLabel1 = new JLabel();
	JTextField txtMonto = new JTextField();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JTextField txtNoCheque = new JTextField();

	public DlgPagoCheque() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public DlgPagoCheque(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgPagoCheque(double monto) {

		this(new Frame(), "PAGO CON CHEQUE", false);
		txtMonto.setText(number.format(monto));
	}

	private void jbInit() throws Exception {
		number = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
		this.getContentPane().setLayout(null);
		cmbBanco.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		cmbBanco.setModel(AppInstance.modelBancos);
		cmbBanco.setBounds(new Rectangle(40, 51, 227, 51));
		cmbBanco.addActionListener(new DlgPagoCheque_cmbBanco_actionAdapter(
				this));
		txtNoCheque.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
		txtNoCheque.setText("");
		txtNoCheque.setBounds(new Rectangle(41, 144, 220, 67));
		txtNoCheque.addKeyListener(new DlgPagoCheque_txtNoCheque_keyAdapter(
				this));
		txtMonto.addActionListener(new DlgPagoCheque_txtMonto_actionAdapter(
				this));
		this.getContentPane().add(cmbBanco);
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
		jLabel3.setToolTipText("");
		jLabel3.setText("No de cheque:");
		jLabel3.setBounds(new Rectangle(42, 106, 201, 33));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
		jLabel2.setText("Monto a pagar:");
		jLabel2.setBounds(new Rectangle(39, 220, 196, 39));
		txtMonto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 50));

		txtMonto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMonto.setBounds(new Rectangle(40, 260, 222, 75));
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(jLabel3);
		this.getContentPane().add(txtNoCheque);
		this.getContentPane().add(txtMonto);
		this.getContentPane().add(jLabel2);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
		jLabel1.setText("Banco:");
		jLabel1.setBounds(new Rectangle(39, 8, 215, 34));
		this.banco = this.cmbBanco.getSelectedItem().toString();

	}

	public double getMonto() {
		return monto;
	}

	public String getNoCheque() {
		return noCheque;
	}

	public String getBanco() {
		return banco;
	}

	public void cmbBanco_actionPerformed(ActionEvent e) {
		this.banco = cmbBanco.getSelectedItem().toString();
	}

	public void txtMonto_actionPerformed(ActionEvent e) {
		try {
			monto = number.parse(txtMonto.getText()).doubleValue();
			if (!(this.noCheque.equals(""))) {
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null,
						"Indique el numero de cheque",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				this.monto = 0;

			}
		} catch (java.text.ParseException ex) {
			JOptionPane.showMessageDialog(null,
					"La cantidad indicada no es correcta",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void txtNoCheque_keyReleased(KeyEvent e) {
		this.noCheque = this.txtNoCheque.getText();

	}

}

class DlgPagoCheque_txtNoCheque_keyAdapter extends KeyAdapter {
	private DlgPagoCheque adaptee;

	DlgPagoCheque_txtNoCheque_keyAdapter(DlgPagoCheque adaptee) {
		this.adaptee = adaptee;
	}

	public void keyReleased(KeyEvent e) {
		adaptee.txtNoCheque_keyReleased(e);
	}
}

class DlgPagoCheque_cmbBanco_actionAdapter implements ActionListener {
	private DlgPagoCheque adaptee;

	DlgPagoCheque_cmbBanco_actionAdapter(DlgPagoCheque adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmbBanco_actionPerformed(e);
	}
}

class DlgPagoCheque_txtMonto_actionAdapter implements ActionListener {
	private DlgPagoCheque adaptee;

	DlgPagoCheque_txtMonto_actionAdapter(DlgPagoCheque adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtMonto_actionPerformed(e);
	}
}
