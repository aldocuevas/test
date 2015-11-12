package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.dao.DaoSource;
import com.boutique.domain.*;
import com.boutique.engine.impl.*;
import com.boutique.enums.DescuentoAnticipadoPorVentaCredito;

import java.util.Date;
import java.io.InputStream;

/**
 * <p>
 * Title: boutique management
 * </p>
 * <p>
 * Description: Sistema de administracion de boitiques
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: SESTO
 * </p>
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class PnlEstadoCuenta extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FrmAbonoANota frmAbonoNota = new FrmAbonoANota();
	FrmAbonoaMasVencido frmMasVencido = new FrmAbonoaMasVencido();

	SistemaCreditoEngine engine;
	double saldoVencido;
	double saldoTotal;
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel jPanel2 = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel2 = new JLabel();
	JTextField txtSaldoTotal = new JTextField();
	JLabel jLabel3 = new JLabel();
	JTextField txtLimiteCredito = new JTextField();
	JLabel jLabel4 = new JLabel();
	JTextField txtSaldoVencido = new JTextField();
	JScrollPane scrollEdoCuenta = new JScrollPane();
	JTable tblEdoCuenta = new JTable();
	ModelEdoCuenta modelEdoCuenta1 = new ModelEdoCuenta();
	JPanel jPanel3 = new JPanel();
	JLabel jLabel5 = new JLabel();
	JTextField txtTelefono = new JTextField();
	JLabel jLabel6 = new JLabel();
	JTextField txtCelular = new JTextField();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	JButton cmdAbonaraNota = new JButton();
	JButton cmdAbonaraMasVencido = new JButton();
	PnlDatosVenta pnlVenta = null;
	JLabel jLabel7 = new JLabel();
	JTextField txtCliente = new JTextField();
	JLabel jLabel8 = new JLabel();
	JTextField txtDomicilio = new JTextField();
	JButton cmdPagarNotaPromocion = new JButton();
	PnlFotografia pnlFotografia1 = new PnlFotografia();

	public PnlEstadoCuenta(PnlDatosVenta pnlVenta) {
		try {
			this.pnlVenta = pnlVenta;
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void setEdoCuenta() {

		saldoVencido = 0;
		saldoTotal = 0;
		this.modelEdoCuenta1.clean();
		txtCliente.setText("");
		txtDomicilio.setText("");
		txtTelefono.setText("");
		txtCelular.setText("");
		Object[] row = null;

		if (engine.getResumenEstadoCuenta() != null) {
			// Si hay ventas.. ponemos los datos en el datamodel
			for (VentaCredito vc : engine.ventasEncontradas) {
				row = new Object[7];
				row[0] = vc.getId();
				row[1] = AppInstance.idToNombreBoutique.get(vc.getIdBoutique());
				row[2] = AppInstance.number.format(vc.subTotal);
				row[3] = AppInstance.number.format(vc.saldoAbonado);
				row[4] = AppInstance.number.format(vc.saldoActual);
				row[5] = AppInstance.number.format(vc.saldoVencido);
				row[6] = AppInstance.formatoCorto.format(vc
						.getFechaVencimiento());
				saldoVencido += vc.saldoVencido;
				saldoTotal += vc.saldoActual;
				this.modelEdoCuenta1.data.add(row);
			}
		}
		this.txtLimiteCredito.setText(AppInstance.number.format(engine.cliente
				.getLimiteCredito()));
		this.modelEdoCuenta1.fireTableDataChanged();
		this.txtSaldoVencido.setText(AppInstance.number.format(saldoVencido));
		this.txtSaldoTotal.setText(AppInstance.number.format(saldoTotal));
		this.txtCliente.setText(engine.cliente.getNombre() + " "
				+ engine.cliente.getApellidoPaterno() + " "
				+ engine.cliente.getApellidoMaterno());
		int ruta = com.boutique.dao.DaoColonias.findRutaByNombre(engine.cliente
				.getColonia());

		this.txtDomicilio
				.setText(engine.cliente.getCalle()
						+ " "
						+ engine.cliente.getNumero()
						+ ((engine.cliente.getNumeroInterior() == null || engine.cliente
								.getNumeroInterior().equals("")) ? ""
								: " Int. " + engine.cliente.getNumeroInterior())
						+ " Col." + engine.cliente.getColonia() + " R" + ruta);

		this.txtTelefono.setText(engine.cliente.getTelefono());
		this.txtCelular.setText(engine.cliente.getCelular());
		if (engine.cliente.getFotografia() != null) {
			pnlFotografia1.setImagen(engine.cliente.getFotografia());
		} else {
			pnlFotografia1.setImagen((InputStream) null);
		}
		pnlVenta.limpiarDatos();
		if (engine.ventasEncontradas.size() > 0) {
			this.cmdAbonaraMasVencido.setEnabled(true);
		} else {
			this.cmdAbonaraMasVencido.setEnabled(false);
		}
		this.cmdAbonaraNota.setEnabled(false);
		this.cmdPagarNotaPromocion.setEnabled(false);
	}

	void jbInit() throws Exception {
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Estado de cuenta");
		this.setLayout(null);
		jPanel1.setLayout(borderLayout2);
		jPanel2.setLayout(gridLayout1);
		jLabel2.setText("Limite credito:");
		jLabel3.setText("Saldo vencido:");
		txtLimiteCredito.setToolTipText("");
		txtLimiteCredito.setSelectionStart(11);
		txtLimiteCredito.setText("");
		jLabel4.setToolTipText("");
		jLabel4.setText("Saldo total:");
		gridLayout1.setColumns(3);
		gridLayout1.setRows(2);
		txtSaldoTotal.setText("");
		txtSaldoVencido.setForeground(Color.red);
		txtSaldoVencido.setCaretColor(Color.red);
		txtSaldoVencido.setText("");
		tblEdoCuenta.setModel(modelEdoCuenta1);
		tblEdoCuenta
				.addMouseListener(new PnlEstadoCuenta_tblEdoCuenta_mouseAdapter(
						this));
		scrollEdoCuenta.setBounds(new Rectangle(1, 130, 705, 95));
		jPanel1.setBounds(new Rectangle(10, 10, 581, 113));
		jPanel3.setLayout(gridBagLayout1);
		jLabel5.setToolTipText("");
		jLabel5.setText("Telefono:");
		txtTelefono.setText("");
		jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel6.setText("Celular:");
		txtCelular.setText("");
		cmdAbonaraNota.setText("ABONAR A NOTA");
		cmdAbonaraNota
				.addActionListener(new PnlEstadoCuenta_cmdAbonaraNota_actionAdapter(
						this));
		cmdAbonaraMasVencido.setBounds(new Rectangle(543, 226, 164, 21));
		cmdAbonaraMasVencido.setEnabled(false);
		cmdAbonaraMasVencido.setText("ABONAR A MAS VENCIDO");
		cmdAbonaraMasVencido
				.addActionListener(new PnlEstadoCuenta_cmdAbonaraMasVencido_actionAdapter(
						this));
		cmdAbonaraNota.setBounds(new Rectangle(374, 226, 164, 21));
		cmdAbonaraNota.setEnabled(false);
		jLabel7.setToolTipText("");
		jLabel7.setText("Cliente:");
		txtCliente.setText("");
		jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel8.setHorizontalTextPosition(SwingConstants.RIGHT);
		jLabel8.setText("Domicilio:");
		txtDomicilio.setText("");
		cmdPagarNotaPromocion.setBounds(new Rectangle(193, 226, 177, 21));
		cmdPagarNotaPromocion.setEnabled(false);
		cmdPagarNotaPromocion.setText("PAGAR NOTA CON DESC.");
		cmdPagarNotaPromocion
				.addActionListener(new PnlEstadoCuenta_cmdPagarNotaPromocion_actionAdapter(
						this));
		pnlFotografia1.setBounds(new Rectangle(600, 4, 106, 120));
		jPanel2.add(jLabel2, null);
		jPanel2.add(jLabel3, null);
		jPanel2.add(jLabel4, null);
		jPanel2.add(txtLimiteCredito, null);
		jPanel2.add(txtSaldoVencido, null);
		jPanel2.add(txtSaldoTotal, null);
		this.add(scrollEdoCuenta, null);
		scrollEdoCuenta.getViewport().add(tblEdoCuenta, null);
		this.add(jPanel1, null);
		jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);
		jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
		this.add(pnlFotografia1);
		this.add(cmdAbonaraMasVencido);
		this.add(cmdAbonaraNota);
		this.add(cmdPagarNotaPromocion);
		jPanel3.add(jLabel5, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 10, 3));
		jPanel3.add(jLabel7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		jPanel3.add(jLabel6, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 23, 3));
		jPanel3.add(jLabel8, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 18, 0));
		jPanel3.add(txtCliente, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 2), 168, 0));
		jPanel3.add(txtDomicilio, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 163, 0));
		jPanel3.add(txtCelular, new GridBagConstraints(3, 2, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 163, 1));
		jPanel3.add(txtTelefono, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 1), 165, 1));
	}

	public void tblEdoCuenta_mouseClicked(MouseEvent e) {
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		if (tblEdoCuenta.getSelectedRow() >= 0) {
			// Mostramos los datos de la venta completos

			engine.seleccionarVenta(tblEdoCuenta.getSelectedRow());
			VentaCredito venta = engine.venta;

			PnlDatosVenta.modelVentaArticulos1.data = engine.productosView;
			PnlDatosVenta.modelVentaArticulos1.fireTableDataChanged();
			PnlDatosVenta.modelPagosRealizados1.data = engine.pagosView;
			PnlDatosVenta.modelPagosRealizados1.fireTableDataChanged();
			PnlDatosVenta.modelPagosCredito1.data = engine.intervaloPagos;
			PnlDatosVenta.modelPagosCredito1.fireTableDataChanged();

			pnlVenta.txtSubtotal.setText(AppInstance.number
					.format(venta.subTotal));
			pnlVenta.txtVendedor.setText(AppInstance.idToNombreUsuario.get(
					venta.getIdVendedor()).toString()
					+ " ");
			pnlVenta.txtAnticipo.setText(AppInstance.number.format(venta
					.getAnticipo()));
			pnlVenta.txtTotal.setText(AppInstance.number
					.format(venta.saldoTotal));
			pnlVenta.txtFecha.setText(AppInstance.formatoLargo
					.format(engine.venta.getFecha()));
			pnlVenta.txtNoVenta.setText(String.valueOf(engine.venta.getId()));
			pnlVenta.txtBoutique.setText(AppInstance.idToNombreBoutique.get(
					engine.venta.getIdBoutique()).toString());

			// Habilitamos el boton de abonar a nota
			habilitarBotonesPago(venta);
		}
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
	}

	private final void habilitarBotonesPago(Venta venta) {
		this.cmdAbonaraNota.setEnabled(true);
		Date fechaReferencia = null;
		if (AppInstance.promocionDesc != null) {

			if (AppInstance.promocionDesc.getTipoVentaCredito() == DescuentoAnticipadoPorVentaCredito.GENERAL
					.getBDValue()) {
				fechaReferencia = DaoSource.getFechaActual();
			} else if (AppInstance.promocionDesc.getTipoVentaCredito() == DescuentoAnticipadoPorVentaCredito.VENTAS_EN_PERIODO
					.getBDValue()) {
				fechaReferencia = new java.util.Date(venta.getFecha().getTime());

			}
			if (fechaReferencia == null) {
				this.cmdPagarNotaPromocion.setEnabled(false);
			} else {
				if ((AppInstance.promocionDesc.getFechaFinal().compareTo(
						fechaReferencia) >= 0 && AppInstance.promocionDesc
						.getFechaInicial().compareTo(fechaReferencia) <= 0)) {
					this.cmdPagarNotaPromocion.setEnabled(true);
				} else {
					this.cmdPagarNotaPromocion.setEnabled(false);
				}
			}

		}

	}

	public void cmdAbonaraNota_actionPerformed(ActionEvent e) {
		if (cmdAbonaraNota.isEnabled()) {
			frmAbonoNota.engine = this.engine;
			frmAbonoNota.setSize(400, 200);
			frmAbonoNota.setLocationRelativeTo(this);
			frmAbonoNota.setVenta(engine.venta);
			frmAbonoNota.txtAbono.requestFocus();
			frmAbonoNota.setVisible(true);
			if (frmAbonoNota.abonar) { // Si se realizo el abono.. actualizamos
										// el estado de cuenta
				setEdoCuenta();
			}
		}
	}

	public void cmdAbonaraMasVencido_actionPerformed(ActionEvent e) {
		if (cmdAbonaraMasVencido.isEnabled()) {
			frmMasVencido.engine = engine;
			frmMasVencido.setSize(400, 200);
			frmMasVencido.setSaldos(saldoTotal, saldoVencido);
			frmMasVencido.setLocationRelativeTo(this);
			frmMasVencido.txtAbono.requestFocus();
			frmMasVencido.cmdAbonar.setEnabled(true);
			frmMasVencido.setVisible(true);
			if (frmMasVencido.abonar) { // Si se realizo el abono.. actualizamos
										// el estado de cuenta
				setEdoCuenta(); // Acualizamos el estado de cuenta
			}
		}
	}

	public void cmdPagarNotaPromocion_actionPerformed(ActionEvent e) {
		// MOSTRAMOS LOS DATOS DE LA NOTA SELECCIONADA
		DlgDescuentoPagoAnticipado nDlg = new DlgDescuentoPagoAnticipado(null,
				"Descuento pago anticipado", true, engine,
				com.boutique.engine.impl.AppInstance.promocionDesc);
		nDlg.pack();
		nDlg.setSize(353, 288);
		nDlg.setLocationRelativeTo(this);
		nDlg.setVisible(true);
		setEdoCuenta();

	}
}

class PnlEstadoCuenta_cmdPagarNotaPromocion_actionAdapter implements
		ActionListener {
	private PnlEstadoCuenta adaptee;

	PnlEstadoCuenta_cmdPagarNotaPromocion_actionAdapter(PnlEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdPagarNotaPromocion_actionPerformed(e);
	}
}

class PnlEstadoCuenta_cmdAbonaraMasVencido_actionAdapter implements
		ActionListener {
	private PnlEstadoCuenta adaptee;

	PnlEstadoCuenta_cmdAbonaraMasVencido_actionAdapter(PnlEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAbonaraMasVencido_actionPerformed(e);
	}
}

class PnlEstadoCuenta_cmdAbonaraNota_actionAdapter implements ActionListener {
	private PnlEstadoCuenta adaptee;

	PnlEstadoCuenta_cmdAbonaraNota_actionAdapter(PnlEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAbonaraNota_actionPerformed(e);
	}
}

class PnlEstadoCuenta_tblEdoCuenta_mouseAdapter extends MouseAdapter {
	private PnlEstadoCuenta adaptee;

	PnlEstadoCuenta_tblEdoCuenta_mouseAdapter(PnlEstadoCuenta adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.tblEdoCuenta_mouseClicked(e);
	}
}
