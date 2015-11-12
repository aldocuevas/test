package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.SistemaCreditoEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class FrmEstadoCuenta extends JDialog {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	PnlDatosVenta pnlDatosVenta1 = new PnlDatosVenta();
	public PnlEstadoCuenta pnlEstadoCuenta1 = new PnlEstadoCuenta(
			pnlDatosVenta1);
	SistemaCreditoEngine engine = new SistemaCreditoEngine();
	FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);
	JPanel jPanel1 = new JPanel();
	JButton cmdSalir = new JButton();
	JButton cmdBuscarOtroCliente = new JButton();

	public FrmEstadoCuenta() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public FrmEstadoCuenta(boolean pagosEnCorte) {
		try {
			engine.agregarPagosAlCorte = pagosEnCorte;
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		setTitle("ESTADO DE CUENTA");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(borderLayout1);
		pnlEstadoCuenta1.engine = engine;
		pnlDatosVenta1.engine=engine;
		pnlDatosVenta1.setPnlEstadoCuenta(pnlEstadoCuenta1);
		pnlEstadoCuenta1.setPreferredSize(new Dimension(400, 250));
		cmdBuscarOtroCliente
				.addActionListener(new FrmEstadoCuenta_cmdBuscarOtroCliente_actionAdapter(
						this));
		cmdSalir.addActionListener(new FrmEstadoCuenta_cmdSalir_actionAdapter(
				this));
		this.addWindowListener(new FrmEstadoCuenta_this_windowAdapter(this));
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
		jPanel1.add(cmdBuscarOtroCliente);
		jPanel1.add(cmdSalir);
		cmdBuscarOtroCliente.setText("OTRO CLIENTE");
		cmdSalir.setText("SALIR");

		frmBuscarCliente.setModal(true);
		frmBuscarCliente.setSize(900, 600);
		frmBuscarCliente.setLocationRelativeTo(this);

		this.getContentPane().add(pnlDatosVenta1, java.awt.BorderLayout.CENTER);
		this.getContentPane()
				.add(pnlEstadoCuenta1, java.awt.BorderLayout.NORTH);
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
	}

	public void cmdBuscarOtroCliente_actionPerformed(ActionEvent e) {
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		frmBuscarCliente.setVisible(true);
		engine.cliente = frmBuscarCliente.cliente;
		if (engine.cliente != null) {
			pnlEstadoCuenta1.setEdoCuenta();
		} else {
			this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
			JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.WARNING_MESSAGE);
		}
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
	}

	public void cmdSalir_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void this_windowOpened(WindowEvent e) {
		int i;
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		while (frmBuscarCliente.cliente == null) {
			frmBuscarCliente.setVisible(true);

			if (frmBuscarCliente.cliente == null) {
				this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
				i = JOptionPane
						.showConfirmDialog(
								this,
								"No has elegido un cliente, ¿Deseas salir de la opción?",
								com.boutique.engine.impl.AppInstance.nombreNegocio,
								JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					this.setVisible(false);
					return;
				}
			}
		}

		engine.cliente = frmBuscarCliente.cliente;

		if (engine.cliente != null) {
			pnlEstadoCuenta1.setEdoCuenta();
		}
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
	}
}

class FrmEstadoCuenta_this_windowAdapter extends WindowAdapter {
	private FrmEstadoCuenta adaptee;

	FrmEstadoCuenta_this_windowAdapter(FrmEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmEstadoCuenta_cmdSalir_actionAdapter implements ActionListener {
	private FrmEstadoCuenta adaptee;

	FrmEstadoCuenta_cmdSalir_actionAdapter(FrmEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalir_actionPerformed(e);
	}
}

class FrmEstadoCuenta_cmdBuscarOtroCliente_actionAdapter implements
		ActionListener {
	private FrmEstadoCuenta adaptee;

	FrmEstadoCuenta_cmdBuscarOtroCliente_actionAdapter(FrmEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscarOtroCliente_actionPerformed(e);
	}
}
