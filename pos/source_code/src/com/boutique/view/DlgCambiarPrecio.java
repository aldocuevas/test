package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boutique.engine.impl.RegistroInventarioEngine;

public class DlgCambiarPrecio extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -903668228513843653L;
	RegistroInventarioEngine engine;

	public DlgCambiarPrecio(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgCambiarPrecio() {
		this(new Frame(), "DlgCambiarPrecio", false);
	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		jLabel1.setText("Nuevo precio al costo:");
		jLabel1.setBounds(new Rectangle(36, 20, 130, 19));
		txtPrecioPublico
				.addActionListener(new DlgCambiarPrecio_txtPrecioPublico_actionAdapter(
						this));
		this.getContentPane().add(jLabel1);
		txtPrecioPublico.setText("");
		txtPrecioPublico.setBounds(new Rectangle(38, 92, 158, 22));
		txtPrecioCosto.setText("");
		txtPrecioCosto.setBounds(new Rectangle(37, 44, 160, 22));
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(txtPrecioCosto);
		this.getContentPane().add(txtPrecioPublico);
		jLabel2.setText("Nuevo precio al publico:");
		jLabel2.setBounds(new Rectangle(36, 67, 121, 22));
	}

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JTextField txtPrecioCosto = new JTextField();
	JTextField txtPrecioPublico = new JTextField();

	public void txtPrecioPublico_actionPerformed(ActionEvent e) {
		try {
			double precioCosto = Double.parseDouble(txtPrecioCosto.getText());
			double precioPublico = Double.parseDouble(txtPrecioPublico
					.getText());
			if (!engine.setNuevosPrecios(precioCosto, precioPublico)) {
				JOptionPane.showMessageDialog(this,
						"No se pudo actualizar el precio del producto",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				this.setVisible(false);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Precios incorrectos",
					"Precios incorrectos", JOptionPane.ERROR_MESSAGE);

		}
	}
}

class DlgCambiarPrecio_txtPrecioPublico_actionAdapter implements ActionListener {
	private DlgCambiarPrecio adaptee;

	DlgCambiarPrecio_txtPrecioPublico_actionAdapter(DlgCambiarPrecio adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtPrecioPublico_actionPerformed(e);
	}
}
