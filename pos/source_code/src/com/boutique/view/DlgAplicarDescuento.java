package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class DlgAplicarDescuento extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DefaultComboBoxModel model = new DefaultComboBoxModel();

	public DlgAplicarDescuento(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgAplicarDescuento() {
		this(new Frame(), "DlgAplicarDescuento", false);
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jLabel1.setText("Indica el porcentaje de descuento");
		jLabel1.setBounds(new Rectangle(13, 9, 209, 25));
		cmbPorcentaje.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		cmbPorcentaje
				.addKeyListener(new DlgAplicarDescuento_cmbPorcentaje_keyAdapter(
						this));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		jLabel2.setText("%");
		jLabel2.setBounds(new Rectangle(174, 44, 50, 35));
		cmdAceptar.setBounds(new Rectangle(29, 89, 167, 39));
		cmdAceptar.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		cmdAceptar.setText("Aplicar descuento");
		cmdAceptar
				.addActionListener(new DlgAplicarDescuento_cmdAceptar_actionAdapter(
						this));
		this.getContentPane().setBackground(Color.white);
		this.getContentPane().add(cmbPorcentaje);
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(cmdAceptar);
		cmbPorcentaje.setBounds(new Rectangle(56, 45, 113, 34));
		cmbPorcentaje.setModel(model);
		for (int i = 0; i < 61; i++) {
			model.addElement(i);
		}

	}

	JLabel jLabel1 = new JLabel();
	JComboBox cmbPorcentaje = new JComboBox();
	JLabel jLabel2 = new JLabel();
	JButton cmdAceptar = new JButton();
	public int descuento = 0;

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		descuento = Integer
				.parseInt(cmbPorcentaje.getSelectedItem().toString());
		this.setVisible(false);
	}

	public void cmbPorcentaje_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			descuento = Integer.parseInt(cmbPorcentaje.getSelectedItem()
					.toString());
			this.setVisible(false);

		}
	}
}

class DlgAplicarDescuento_cmbPorcentaje_keyAdapter extends KeyAdapter {
	private DlgAplicarDescuento adaptee;

	DlgAplicarDescuento_cmbPorcentaje_keyAdapter(DlgAplicarDescuento adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.cmbPorcentaje_keyPressed(e);
	}
}

class DlgAplicarDescuento_cmdAceptar_actionAdapter implements ActionListener {
	private DlgAplicarDescuento adaptee;

	DlgAplicarDescuento_cmdAceptar_actionAdapter(DlgAplicarDescuento adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}
