package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

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
public class DlgIntervaloFechas extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel1 = new JLabel();
	public JDateChooser jdFechaFinal = new JDateChooser();
	JLabel jLabel2 = new JLabel();
	JDateChooser jdFechaInicial = new JDateChooser();
	JPanel jPanel2 = new JPanel();
	JLabel jLabel3 = new JLabel();
	JPanel jPanel3 = new JPanel();
	JPanel jPanel4 = new JPanel();
	JPanel jPanel5 = new JPanel();
	JPanel jPanel6 = new JPanel();
	JButton cmdConsultar = new JButton();

	public DlgIntervaloFechas(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgIntervaloFechas() {
		this(new Frame(), "DlgoIntervaloFechas", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		jPanel1.setLayout(gridLayout1);
		gridLayout1.setColumns(1);
		gridLayout1.setRows(5);
		jLabel1.setText("Fecha inicial:");
		jLabel2.setText("Fecha final:");
		jLabel3.setText("ELIGE LAS FECHAS DE BUSQUEDA");
		cmdConsultar.setText("CONSULTAR");
		cmdConsultar
				.addActionListener(new DlgoIntervaloFechas_cmdConsultar_actionAdapter(
						this));
		this.addWindowListener(new DlgoIntervaloFechas_this_windowAdapter(this));
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		getContentPane().add(panel1);
		jPanel1.add(jLabel1);
		jPanel1.add(jdFechaInicial);
		jPanel1.add(jLabel2);
		jPanel1.add(jdFechaFinal);
		panel1.add(jPanel1, java.awt.BorderLayout.CENTER);
		jPanel2.add(jLabel3);
		panel1.add(jPanel3, java.awt.BorderLayout.WEST);
		panel1.add(jPanel4, java.awt.BorderLayout.EAST);
		panel1.add(jPanel2, java.awt.BorderLayout.NORTH);
		panel1.add(jPanel6, java.awt.BorderLayout.SOUTH);
		jPanel6.add(cmdConsultar);
	}

	public void cmdConsultar_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void this_windowOpened(WindowEvent e) {
		java.util.Date d = new java.util.Date();
		this.jdFechaFinal.setDate(d);
		this.jdFechaInicial.setDate(d);
	}
}

class DlgoIntervaloFechas_this_windowAdapter extends WindowAdapter {
	private DlgIntervaloFechas adaptee;

	DlgoIntervaloFechas_this_windowAdapter(DlgIntervaloFechas adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class DlgoIntervaloFechas_cmdConsultar_actionAdapter implements ActionListener {
	private DlgIntervaloFechas adaptee;

	DlgoIntervaloFechas_cmdConsultar_actionAdapter(DlgIntervaloFechas adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdConsultar_actionPerformed(e);
	}
}
