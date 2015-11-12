package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boutique.engine.impl.*;
import com.boutique.domain.Vale;
import com.boutique.dao.DaoVentas;

/**
 * <p>
 * Title: boutique management
 * </p>
 * 
 * <p>
 * Description: Sistema de administracion de boitiques
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: SESTO
 * </p>
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class FrmGenerarVale extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel2 = new JLabel();
	JTextField txtMonto = new JTextField();
	JButton cmdGenerarVale = new JButton();
	JLabel jLabel3 = new JLabel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTextArea txtMotivo = new JTextArea();
	JButton cmdImprimir = new JButton();
	Vale vale = null;
	JButton cmdSalir = new JButton();

	public FrmGenerarVale() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel1.setText("GENERAR VALE");
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jLabel3.setText("Motivo de la generacion del vale:");
		jLabel3.setBounds(new Rectangle(42, 90, 179, 17));
		jScrollPane1.setBounds(new Rectangle(35, 114, 200, 70));
		txtMotivo.setText("");
		txtMotivo.setWrapStyleWord(true);
		txtMonto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		cmdGenerarVale
				.addActionListener(new FrmGenerarVale_cmdGenerarVale_actionAdapter(
						this));
		cmdImprimir.setBounds(new Rectangle(139, 195, 120, 31));
		cmdImprimir.setEnabled(false);
		cmdImprimir.setToolTipText("");
		cmdImprimir.setText("IMPRIMIR VALE");
		cmdImprimir
				.addActionListener(new FrmGenerarVale_cmdImprimir_actionAdapter(
						this));
		cmdSalir.setBounds(new Rectangle(170, 238, 89, 25));
		cmdSalir.setText("Salir");
		cmdSalir.addActionListener(new FrmGenerarVale_cmdSalir_actionAdapter(
				this));
		this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
		jLabel2.setText("Indica el monto:");
		jLabel2.setBounds(new Rectangle(40, 23, 177, 23));
		jPanel1.setBackground(Color.white);
		jPanel1.setLayout(null);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
		txtMonto.setText("0.0");
		txtMonto.setBounds(new Rectangle(38, 50, 194, 34));
		cmdGenerarVale.setBounds(new Rectangle(12, 195, 120, 31));
		cmdGenerarVale.setText("GENERAR VALE");
		jPanel1.add(jLabel2);
		jPanel1.add(jLabel3);
		jPanel1.add(jScrollPane1);
		jPanel1.add(txtMonto);
		jPanel1.add(cmdImprimir);
		jPanel1.add(cmdGenerarVale);
		jPanel1.add(cmdSalir);
		jScrollPane1.getViewport().add(txtMotivo);
	}

	public void cmdGenerarVale_actionPerformed(ActionEvent e) {
		// Mostramos el vale de compra
		try {
			double monto = Double.parseDouble(this.txtMonto.getText());
			if (monto <= 0) {
				JOptionPane.showMessageDialog(this,
						"EL MONTO DEBE SER MAYOR A 0.0",
						AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (this.txtMotivo.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "DEBE INDICARSE EL MOTIVO",
						AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
				return;
			}
			vale = DaoVentas.generarValeManual(monto, this.txtMotivo.getText(),
					AppInstance.usuario().getId(), AppInstance.boutique()
							.getId());
			if (vale != null) {
				DlgMostrarVale dlg = new DlgMostrarVale();
				dlg.lblMonto.setText("$"
						+ AppInstance.number.format(vale.getMonto()));
				dlg.lblNoVale.setText(String.valueOf(vale.getId()));
				dlg.setSize(193, 235);
				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setModal(true);
				// engine.imprimirVale();
				dlg.setVisible(true);
				cmdImprimir.setEnabled(true);
				cmdGenerarVale.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(this,
						"NO SE PUDO GENERAR EL VALE",
						AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);

			}
		} catch (HeadlessException ex) {
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "INDIQUE UNA CANTIDAD VALIDA",
					AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);

		}
	}

	public void cmdImprimir_actionPerformed(ActionEvent e) {
		com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.PrintTask(
				4, vale); // Venta de contado
		Thread t = new Thread(obj);
		t.start();

	}

	public void cmdSalir_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}

class FrmGenerarVale_cmdSalir_actionAdapter implements ActionListener {
	private FrmGenerarVale adaptee;

	FrmGenerarVale_cmdSalir_actionAdapter(FrmGenerarVale adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalir_actionPerformed(e);
	}
}

class FrmGenerarVale_cmdImprimir_actionAdapter implements ActionListener {
	private FrmGenerarVale adaptee;

	FrmGenerarVale_cmdImprimir_actionAdapter(FrmGenerarVale adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdImprimir_actionPerformed(e);
	}
}

class FrmGenerarVale_cmdGenerarVale_actionAdapter implements ActionListener {
	private FrmGenerarVale adaptee;

	FrmGenerarVale_cmdGenerarVale_actionAdapter(FrmGenerarVale adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdGenerarVale_actionPerformed(e);
	}
}
