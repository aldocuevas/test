package com.boutique.view;

import java.beans.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

public class FrmVentaCreditoFechaDefinida extends JFrame implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	PnlVentaCreditoFechaDefinida pnlPuntoVentaCredito1 = new PnlVentaCreditoFechaDefinida();
	FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);

	public FrmVentaCreditoFechaDefinida() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		pnlPuntoVentaCredito1.addPropertyChangeListener(this);
		getContentPane().setLayout(borderLayout1);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("CREDITO");
		this.addWindowListener(new FrmVentaCreditoFechaDefinida_this_windowAdapter(
				this));
		this.getContentPane().add(pnlPuntoVentaCredito1,
				java.awt.BorderLayout.CENTER);

		/*
		 * this.cliente = frmBuscarCliente.cliente; this.setDatosCliente();
		 * this.txtEtiqueta.requestFocus();
		 */

	}

	public void this_windowOpened(WindowEvent e) {
		frmBuscarCliente.setSize(900, 600);
		frmBuscarCliente.setLocationRelativeTo(this.getRootPane());

		frmBuscarCliente.setVisible(true);
		if (frmBuscarCliente.cliente == null) {
			this.setVisible(false);
		} else {
			this.pnlPuntoVentaCredito1.engine.iniciarVenta(AppInstance
					.usuario().getId(), frmBuscarCliente.cliente, 0);
			this.pnlPuntoVentaCredito1.lblVendedor.setText(AppInstance
					.usuario().getNombre());
			this.pnlPuntoVentaCredito1.setDatosCliente();
			this.pnlPuntoVentaCredito1.txtEtiqueta.requestFocus();
			pnlPuntoVentaCredito1.cmbCantidadPagos.setSelectedIndex(2);
			this.pnlPuntoVentaCredito1.regFacIndEngine.setCliente(pnlPuntoVentaCredito1.engine.cliente());
			this.pnlPuntoVentaCredito1.regFacIndEngine.setVenta(pnlPuntoVentaCredito1.engine.venta);

		}

	}

	/**
	 * This method gets called when a bound property is changed.
	 * 
	 * @param evt
	 *            A PropertyChangeEvent object describing the event source and
	 *            the property that has changed.
	 * @todo Implement this java.beans.PropertyChangeListener method
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("cerrar")) {
			this.setVisible(false);
		}

	}

}

class FrmVentaCreditoFechaDefinida_this_windowAdapter extends WindowAdapter {
	private FrmVentaCreditoFechaDefinida adaptee;

	FrmVentaCreditoFechaDefinida_this_windowAdapter(
			FrmVentaCreditoFechaDefinida adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}
