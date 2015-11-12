package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.SistemaCreditoEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class FrmEstadoCuentaPorRuta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	PnlDatosVenta pnlDatosVenta1 = new PnlDatosVenta();
	public PnlEstadoCuenta pnlEstadoCuenta1 = new PnlEstadoCuenta(
			pnlDatosVenta1);
	SistemaCreditoEngine engine = new SistemaCreditoEngine();
	FrmBuscarClientePorRuta frmBuscarCliente = new FrmBuscarClientePorRuta();
	JPanel jPanel1 = new JPanel();
	JButton cmdSalir = new JButton();
	JButton cmdBuscarOtroCliente = new JButton();

	public FrmEstadoCuentaPorRuta() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setTitle("ESTADO DE CUENTA");
		getContentPane().setLayout(borderLayout1);
		pnlEstadoCuenta1.engine = engine;
		pnlEstadoCuenta1.setPreferredSize(new Dimension(400, 250));
		cmdBuscarOtroCliente
				.addActionListener(new FrmEstadoCuentaPorRuta_cmdBuscarOtroCliente_actionAdapter(
						this));
		cmdSalir.addActionListener(new FrmEstadoCuentaPorRuta_cmdSalir_actionAdapter(
				this));
		this.addWindowListener(new FrmEstadoCuentaPorRuta_this_windowAdapter(
				this));
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
	}

	public void cmdBuscarOtroCliente_actionPerformed(ActionEvent e) {

		frmBuscarCliente.setVisible(true);
		engine.cliente = frmBuscarCliente.cliente;
		if (engine.cliente != null) {
			pnlEstadoCuenta1.setEdoCuenta();
		} else {
			JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void cmdSalir_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void this_windowOpened(WindowEvent e) {
		int i;
		while (frmBuscarCliente.cliente == null) {
			frmBuscarCliente.setVisible(true);
			if (frmBuscarCliente.cliente == null) {
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
	}
}

class FrmEstadoCuentaPorRuta_this_windowAdapter extends WindowAdapter {
	private FrmEstadoCuentaPorRuta adaptee;

	FrmEstadoCuentaPorRuta_this_windowAdapter(FrmEstadoCuentaPorRuta adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmEstadoCuentaPorRuta_cmdSalir_actionAdapter implements ActionListener {
	private FrmEstadoCuentaPorRuta adaptee;

	FrmEstadoCuentaPorRuta_cmdSalir_actionAdapter(FrmEstadoCuentaPorRuta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalir_actionPerformed(e);
	}
}

class FrmEstadoCuentaPorRuta_cmdBuscarOtroCliente_actionAdapter implements
		ActionListener {
	private FrmEstadoCuentaPorRuta adaptee;

	FrmEstadoCuentaPorRuta_cmdBuscarOtroCliente_actionAdapter(
			FrmEstadoCuentaPorRuta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscarOtroCliente_actionPerformed(e);
	}
}
