package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.borland.jbcl.layout.*;

public class DlgNoImpresionEtiquetas extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JTextField txtNoEtiqueta = new JTextField();
	VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
	JButton cmdAceptar = new JButton();
	int noEtiquetaInicial;

	public DlgNoImpresionEtiquetas(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgNoImpresionEtiquetas() {
		this(new Frame(), "DlgNoImpresionEtiquetas", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Impresión de etiquetas");
		jLabel2.setText("Indique el número de etiqueta en el que empezara la impresión:");
		txtNoEtiqueta.setText("");
		jPanel1.setLayout(verticalFlowLayout1);
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addActionListener(new DlgNoImpresionEtiquetas_cmdAceptar_actionAdapter(
						this));
		this.setModal(true);
		getContentPane().add(panel1);
		panel1.add(jPanel1, java.awt.BorderLayout.CENTER);
		jPanel1.add(jLabel2, null);
		jPanel1.add(txtNoEtiqueta, null);
		panel1.add(jLabel1, java.awt.BorderLayout.NORTH);
		panel1.add(cmdAceptar, java.awt.BorderLayout.SOUTH);
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		try {
			this.setNoEtiquetaInicial(Integer.parseInt(this.txtNoEtiqueta
					.getText()));
			if (this.noEtiquetaInicial > 0 && this.noEtiquetaInicial <= 28) { // Numero
																				// correcto,
																				// imprimimos
																				// la
																				// etiqueta
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this.getRootPane(),
						"Indique un número válido entre 1 y 28", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this.getRootPane(),
					"Indique un número válido entre 1 y 28", "Error",
					JOptionPane.WARNING_MESSAGE);

		}

	}

	public int getNoEtiquetaInicial() {
		return noEtiquetaInicial;
	}

	public void setNoEtiquetaInicial(int noEtiquetaInicial) {
		this.noEtiquetaInicial = noEtiquetaInicial;
	}
}

class DlgNoImpresionEtiquetas_cmdAceptar_actionAdapter implements
		ActionListener {
	private DlgNoImpresionEtiquetas adaptee;

	DlgNoImpresionEtiquetas_cmdAceptar_actionAdapter(
			DlgNoImpresionEtiquetas adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}
