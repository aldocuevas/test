package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.AppInstance;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class DlgPagoRegistradoAMasVencido extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	public java.util.List pagosView = new ArrayList();
	Object[] row;

	@SuppressWarnings("rawtypes")
	public DlgPagoRegistradoAMasVencido(java.util.List pagos, double monto) {
		this(new Frame(), "DlgPagoRegistrado", false);

		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.modelPagosRealizados1.data = pagos;
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		lblTotal.setText(AppInstance.number.format(monto));
	}

	public DlgPagoRegistradoAMasVencido(Frame owner, String title, boolean modal) {
		super(owner, title, modal);

	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setTitle("Pago registrado");
		this.addWindowListener(new DlgPagoRegistradoAMasVencido_this_windowAdapter(
				this));
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		jLabel1.setText("Pagos desglosados");
		jLabel1.setBounds(new Rectangle(12, 4, 159, 34));
		tblPagos.setModel(modelPagosRealizados1);

		cmdAceptar.setBounds(new Rectangle(318, 173, 89, 27));
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addActionListener(new DlgPagoRegistradoAMasVencido_cmdAceptar_actionAdapter(
						this));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		jLabel2.setText("Total abonado:");
		jLabel2.setBounds(new Rectangle(194, 4, 107, 36));
		lblTotal.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		lblTotal.setText("jLabel3");
		lblTotal.setBounds(new Rectangle(317, 4, 90, 36));
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(scrollPagos);
		this.getContentPane().add(lblTotal);
		this.getContentPane().add(cmdAceptar);
		scrollPagos.getViewport().add(tblPagos);
		scrollPagos.setBounds(new Rectangle(11, 33, 400, 134));
		this.cmdAceptar.requestFocus();
	}

	JLabel jLabel1 = new JLabel();
	JScrollPane scrollPagos = new JScrollPane();
	JTable tblPagos = new JTable();
	ModelPagosRealizadosaMasVencido modelPagosRealizados1 = new ModelPagosRealizadosaMasVencido();
	JButton cmdAceptar = new JButton();
	JLabel jLabel2 = new JLabel();
	JLabel lblTotal = new JLabel();

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void this_windowOpened(WindowEvent e) {
		this.cmdAceptar.requestFocus();
	}
}

class DlgPagoRegistradoAMasVencido_this_windowAdapter extends WindowAdapter {
	private DlgPagoRegistradoAMasVencido adaptee;

	DlgPagoRegistradoAMasVencido_this_windowAdapter(
			DlgPagoRegistradoAMasVencido adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class DlgPagoRegistradoAMasVencido_cmdAceptar_actionAdapter implements
		ActionListener {
	private DlgPagoRegistradoAMasVencido adaptee;

	DlgPagoRegistradoAMasVencido_cmdAceptar_actionAdapter(
			DlgPagoRegistradoAMasVencido adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}
