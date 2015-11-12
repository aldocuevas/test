package com.boutique.view;

import java.beans.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.*;
import com.boutique.dao.DaoResumenes;
import com.boutique.domain.ClasificacionCliente;

public class FrmVentaCredito extends JFrame implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	PnlVentaCredito pnlPuntoVentaCredito1 = new PnlVentaCredito();
	FrmEstadoCuenta frmEdoCuenta = new FrmEstadoCuenta();
	FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);

	public FrmVentaCredito() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		pnlPuntoVentaCredito1.addPropertyChangeListener(this);
		getContentPane().setLayout(borderLayout1);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("VENTA DE CREDITO");
		this.addWindowListener(new FrmVentaCredito_this_windowAdapter(this));
		this.getContentPane().add(pnlPuntoVentaCredito1,
				java.awt.BorderLayout.CENTER);

		/*
		 * this.cliente = frmBuscarCliente.cliente; this.setDatosCliente();
		 * this.txtEtiqueta.requestFocus();
		 */

	}

	public void this_windowOpened(WindowEvent e) {
		// REVISAMOS EL ESTADO DE CUENTA..
		frmEdoCuenta.setModal(true);
		frmEdoCuenta.pack();
		frmEdoCuenta.setSize(730, 700);
		frmEdoCuenta.setLocationRelativeTo(this.getRootPane());
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
		frmEdoCuenta.setVisible(true);
		// frmBuscarCliente.setSize(800, 600);

		// frmBuscarCliente.setVisible(true);
		if (frmEdoCuenta.frmBuscarCliente.cliente == null) {
			this.setVisible(false);

		} else {
			ClasificacionCliente cc = frmEdoCuenta.frmBuscarCliente.cliente
					.getClasificacionCliente();
			if (!cc.getTipo().equals("CREDITO")) {
				JOptionPane.showMessageDialog(
						this,
						"El cliente tiene tiene la clasificación de tipo "
								+ cc.getClasificacion()
								+ ", no puede continuar con la venta.",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				return;
			} else if (cc.getTipo().equals("CREDITO")
					&& !frmEdoCuenta.frmBuscarCliente.cliente.getTieneCredito()
							.equals("AUTORIZADO")) {
				// EL CREDITO NO ESTA AUTORIZADO.
				JOptionPane.showMessageDialog(
						this,
						"El cliente tiene el credito "
								+ frmEdoCuenta.frmBuscarCliente.cliente
										.getTieneCredito()
								+ ", no puede continuar con la venta.",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				return;

			}
			if (!frmEdoCuenta.frmBuscarCliente.cliente.getTieneCredito()
					.equals("AUTORIZADO")) {
				// EL CREDITO NO ESTA AUTORIZADO.
				JOptionPane.showMessageDialog(
						this,
						"El cliente tiene el credito "
								+ frmEdoCuenta.frmBuscarCliente.cliente
										.getTieneCredito()
								+ ", no puede continuar con la venta.",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				return;
			}

			if (!frmEdoCuenta.frmBuscarCliente.msgFaltantes.equals("")) {
				String tmp = frmEdoCuenta.frmBuscarCliente.msgFaltantes;

				frmEdoCuenta.frmBuscarCliente.msgFaltantes = "El cliente no tiene actualizados los siguientes datos:\n\r"
						+ frmEdoCuenta.frmBuscarCliente.msgFaltantes;

				JOptionPane.showMessageDialog(this,
						frmEdoCuenta.frmBuscarCliente.msgFaltantes,
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				if (!tmp.equals("Limite de crédito,")) {
					DlgActualizarDatosClientes dlg = new DlgActualizarDatosClientes(
							frmEdoCuenta.frmBuscarCliente.cliente,
							"ACTUALIZAR", false);
					dlg.setModal(true);
					dlg.setSize(500, 700);
					this.setLocationRelativeTo(this.getRootPane());
					dlg.setVisible(true);
					frmEdoCuenta.frmBuscarCliente.cliente = dlg.cliente;
					dlg.dispose();
					dlg = null;
				}
			}
			if (frmEdoCuenta.pnlEstadoCuenta1.saldoVencido > 0) {
				int i = JOptionPane.showConfirmDialog(this,
						"EL CLIENTE TIENE UN SALDO VENCIDO DE $"
								+ frmEdoCuenta.pnlEstadoCuenta1.saldoVencido
								+ ",¿DESEA CONTINUAR CON LA VENTA?",
						"SALDO VENCIDO", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.NO_OPTION) {
					this.setVisible(false);
					return;
				} else {
					// GRABAMOS PARA VER QUIEN LE DIO CREDITO A ESTA PERSONA A
					// PESAR DE QUE TIENE SALDO VENCIDO.
					DaoResumenes
							.registrarAutorizacionVentaCreditoConSaldoVencido(
									AppInstance.usuario().getId(),
									frmEdoCuenta.frmBuscarCliente.cliente
											.getId(),
									frmEdoCuenta.pnlEstadoCuenta1.saldoVencido,
									AppInstance.boutique().getId(), AppInstance
											.terminal().getId());
				}
			}

			this.pnlPuntoVentaCredito1.engine.iniciarVenta(AppInstance
					.usuario().getId(), frmEdoCuenta.frmBuscarCliente.cliente,
					frmEdoCuenta.pnlEstadoCuenta1.saldoTotal);
			
			this.pnlPuntoVentaCredito1.regFacIndEngine
					.setVenta(this.pnlPuntoVentaCredito1.engine.venta);
			this.pnlPuntoVentaCredito1.regFacIndEngine.setCliente(frmEdoCuenta.frmBuscarCliente.cliente);

			this.pnlPuntoVentaCredito1.lblVendedor.setText(AppInstance
					.usuario().getNombre());
			this.pnlPuntoVentaCredito1.setDatosCliente();
			this.pnlPuntoVentaCredito1.txtEtiqueta.requestFocus();
			pnlPuntoVentaCredito1.cmbCantidadPagos.setSelectedIndex(2);
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

class FrmVentaCredito_this_windowAdapter extends WindowAdapter {
	private FrmVentaCredito adaptee;

	FrmVentaCredito_this_windowAdapter(FrmVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}
