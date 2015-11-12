package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class DlgIndicarIdVenta extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int idVenta;

	public DlgIndicarIdVenta(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgIndicarIdVenta() {
		this(new Frame(), "DlgIndicarIdVenta", false);
	}

	private void jbInit() throws Exception {
		this.getContentPane().setBackground(Color.white);
		this.setModal(true);
		this.addKeyListener(new DlgIndicarIdVenta_this_keyAdapter(this));
		this.getContentPane().setLayout(null);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Indique el número de venta:");
		jLabel1.setBounds(new Rectangle(28, 27, 200, 28));
		txtNoVenta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 15));
		txtNoVenta.addKeyListener(new DlgIndicarIdVenta_txtNoVenta_keyAdapter(
				this));
		cmdContinuar.setBounds(new Rectangle(71, 108, 110, 29));
		cmdContinuar.setToolTipText("");
		cmdContinuar.setText("CONTINUAR");
		cmdContinuar
				.addActionListener(new DlgIndicarIdVenta_cmdContinuar_actionAdapter(
						this));
		this.getContentPane().add(txtNoVenta);
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(cmdContinuar);
		txtNoVenta.setText("");
		txtNoVenta.setBounds(new Rectangle(26, 63, 205, 36));
		txtNoVenta
				.addActionListener(new DlgIndicarIdVenta_txtNoVenta_actionAdapter(
						this));
	}

	JLabel jLabel1 = new JLabel();
	JTextField txtNoVenta = new JTextField();
	JButton cmdContinuar = new JButton();

	void indicarVenta() {
		try {
			idVenta = Integer.parseInt(txtNoVenta.getText());
			this.setVisible(false);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Indique un número válido",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void txtNoVenta_actionPerformed(ActionEvent e) {
		indicarVenta();
	}

	public void this_keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
	}

	public void txtNoVenta_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27) {
			this.setVisible(false);
		}
	}

	public void cmdContinuar_actionPerformed(ActionEvent e) {
		indicarVenta();
	}
}

class DlgIndicarIdVenta_cmdContinuar_actionAdapter implements ActionListener {
	private DlgIndicarIdVenta adaptee;

	DlgIndicarIdVenta_cmdContinuar_actionAdapter(DlgIndicarIdVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdContinuar_actionPerformed(e);
	}
}

class DlgIndicarIdVenta_this_keyAdapter extends KeyAdapter {
	private DlgIndicarIdVenta adaptee;

	DlgIndicarIdVenta_this_keyAdapter(DlgIndicarIdVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.this_keyPressed(e);
	}
}

class DlgIndicarIdVenta_txtNoVenta_actionAdapter implements ActionListener {
	private DlgIndicarIdVenta adaptee;

	DlgIndicarIdVenta_txtNoVenta_actionAdapter(DlgIndicarIdVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtNoVenta_actionPerformed(e);
	}
}

class DlgIndicarIdVenta_txtNoVenta_keyAdapter extends KeyAdapter {
	private DlgIndicarIdVenta adaptee;

	DlgIndicarIdVenta_txtNoVenta_keyAdapter(DlgIndicarIdVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.txtNoVenta_keyPressed(e);
	}
}
