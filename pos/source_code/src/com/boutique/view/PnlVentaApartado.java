package com.boutique.view;

import java.beans.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import com.boutique.engine.impl.*;

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

public class PnlVentaApartado extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6677956370863231790L;
	DlgAnticipoApartado dlg;
	BorderLayout borderLayout1 = new BorderLayout();
	ImageIcon puntoVenta = new ImageIcon();
	JPanel encabezado = new JPanel();
	JPanel bottom = new JPanel();
	JPanel pnlCentro = new JPanel();
	JPanel pnlTipoVentaDatosCliente = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	BorderLayout borderLayout11 = new BorderLayout();
	JPanel pnlDatosClienteyBusquedaArticulo = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	BorderLayout borderLayout4 = new BorderLayout();
	JLabel jLabel3 = new JLabel();
	JPanel pnlDatos = new JPanel();
	JLabel jLabel4 = new JLabel();
	JTextField txtTelefono = new JTextField();
	JLabel jLabel5 = new JLabel();
	JTextField txtDomicilio = new JTextField();
	JLabel jLabel9 = new JLabel();
	JTextField txtCliente = new JTextField();
	JPanel pnlBusquedaVentasPagos = new JPanel();
	JScrollPane scrollVentaArticulos = new JScrollPane();
	BorderLayout borderLayout5 = new BorderLayout();
	JLabel jLabel2 = new JLabel();
	JTable tblProductos = new JTable();
	public ModelVentaArticulos modelVentaArticulos1 = new ModelVentaArticulos();
	JPanel pnlBusquedaArticulos = new JPanel();
	JTextField txtEtiqueta = new JTextField();

	JPanel pnlTotales = new JPanel();
	BorderLayout borderLayout6 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout3 = new GridLayout();
	JButton cmdPagarAnticipo = new JButton();
	BorderLayout borderLayout7 = new BorderLayout();
	JPanel pnlLeftBuscarCliente = new JPanel();
	JLabel lblFecha = new JLabel();
	GridLayout gridLayout4 = new GridLayout();
	JLabel jLabel11 = new JLabel();
	JTextField txtTotal = new JTextField();
	JPanel pnlCentroFormaPago = new JPanel();
	JPanel jPanel5 = new JPanel();
	GridLayout gridLayout6 = new GridLayout();
	java.text.NumberFormat number = java.text.NumberFormat
			.getInstance(java.util.Locale.ENGLISH);
	// JButton cmdAgregarProducto = new JButton();
	BorderLayout borderLayout9 = new BorderLayout();
	JLabel jLabel6 = new JLabel();
	TitledBorder titledBorder5;
	BorderLayout borderLayout8 = new BorderLayout();
	BorderLayout borderLayout10 = new BorderLayout();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel lblVendedor = new JLabel();
	JLabel lblLugar = new JLabel();
	FrmIndicarTipoPago frmIndicarTipoPago;
	VentaApartadoEngine engine = new VentaApartadoEngine();
	RegistroFacturaIndividualEngine regFacIndEngine = new RegistroFacturaIndividualEngine();
	private DlgFacturaRequerida dlgFacReq = new DlgFacturaRequerida(null,
			regFacIndEngine);

	public PnlVentaApartado() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
		// this.jspFecha.setEditor(new EditorDate);
		txtTotal.setText(number.format(0));
		titledBorder5 = new TitledBorder("");
		puntoVenta = new ImageIcon(
				com.boutique.view.FrmAppBoutique.class.getResource(

				"img/puntoVenta.jpg"));
		this.setBackground(Color.white);
		this.setForeground(Color.black);
		this.addMouseListener(new PnlVentaApartadoEvento_this_mouseAdapter(this));
		this.setLayout(borderLayout1);
		pnlCentro.setBackground(Color.white);
		pnlCentro.setLayout(borderLayout2);
		pnlTipoVentaDatosCliente.setLayout(borderLayout3);
		pnlDatosClienteyBusquedaArticulo.setBackground(Color.white);
		pnlDatosClienteyBusquedaArticulo
				.setPreferredSize(new Dimension(243, 90));
		pnlDatosClienteyBusquedaArticulo.setLayout(borderLayout4);
		jLabel3.setFont(new java.awt.Font("Dialog", 1, 18));
		jLabel3.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/ventaApartado.jpg")));
		jLabel3.setText("");
		encabezado.setOpaque(false);
		encabezado.setLayout(borderLayout10);
		pnlDatos.setLayout(gridBagLayout2);
		jLabel4.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel4.setOpaque(false);
		jLabel4.setText("Nombre:");
		lblVendedor.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblLugar.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblFecha.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		jLabel8.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		txtTelefono.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtTelefono.setBorder(BorderFactory.createEtchedBorder());
		txtTelefono.setText("");
		txtTelefono.setEnabled(false);
		jLabel5.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel5.setText("Teléfono:");
		txtDomicilio.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtDomicilio.setBorder(BorderFactory.createEtchedBorder());
		txtDomicilio.setText("");
		txtDomicilio.setEnabled(false);
		jLabel9.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel9.setText("Domicilio:");
		txtCliente.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtCliente.setBorder(BorderFactory.createEtchedBorder());
		txtCliente.setText("");
		txtCliente.setEnabled(false);
		pnlDatos.setOpaque(false);
		pnlBusquedaVentasPagos.setLayout(borderLayout5);
		jLabel2.setBackground(Color.white);
		jLabel2.setMaximumSize(new Dimension(150, 15));
		jLabel2.setMinimumSize(new Dimension(150, 15));
		jLabel2.setOpaque(true);
		jLabel2.setPreferredSize(new Dimension(140, 15));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
		jLabel2.setText("Pase el artículo:");
		scrollVentaArticulos.getViewport().setBackground(Color.white);
		scrollVentaArticulos.setAutoscrolls(true);
		scrollVentaArticulos.setMaximumSize(new Dimension(200, 200));
		scrollVentaArticulos.setMinimumSize(new Dimension(24, 28));
		scrollVentaArticulos.setPreferredSize(new Dimension(200, 100));
		tblProductos.setBackground(new Color(255, 240, 248));
		tblProductos.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		tblProductos.setForeground(Color.black);

		tblProductos.setOpaque(true);
		tblProductos.setGridColor(Color.white);
		tblProductos.setModel(modelVentaArticulos1);
		tblProductos.setRowHeight(20);
		tblProductos.setShowHorizontalLines(true);
		tblProductos.setShowVerticalLines(false);

		tblProductos.setDoubleBuffered(false);
		tblProductos.setCellSelectionEnabled(false);
		tblProductos.setColumnSelectionAllowed(false);
		tblProductos.setIntercellSpacing(new Dimension(0, 2));
		tblProductos.setRowHeight(20);
		tblProductos.setRowMargin(1);
		tblProductos.setRowSelectionAllowed(true);

		tblProductos.setModel(this.modelVentaArticulos1);
		tblProductos
				.addKeyListener(new PnlVentaApartadoEvento_tblProductos_keyAdapter(
						this));
		pnlBusquedaArticulos.setEnabled(true);
		pnlBusquedaArticulos.setDebugGraphicsOptions(0);
		pnlBusquedaArticulos.setDoubleBuffered(true);
		pnlBusquedaArticulos.setMinimumSize(new Dimension(79, 30));
		pnlBusquedaArticulos.setPreferredSize(new Dimension(79, 30));
		pnlBusquedaArticulos.setLayout(borderLayout9);
		txtEtiqueta.setText("");
		txtEtiqueta
				.addActionListener(new PnlVentaApartadoEvento_txtEtiqueta_actionAdapter(
						this));
		pnlTotales.setLayout(borderLayout6);
		jPanel1.setLayout(gridLayout3);
		gridLayout3.setColumns(2);
		gridLayout3.setRows(2);
		jPanel1.setMinimumSize(new Dimension(250, 100));
		jPanel1.setOpaque(false);
		jPanel1.setPreferredSize(new Dimension(250, 100));
		pnlTotales.setBackground(Color.white);
		pnlTotales.setPreferredSize(new Dimension(483, 133));
		cmdPagarAnticipo.setActionCommand("REGISTRAR VENTA");
		cmdPagarAnticipo.setMnemonic('P');
		cmdPagarAnticipo.setText("REGISTRAR VENTA");
		cmdPagarAnticipo
				.addActionListener(new PnlVentaApartadoEvento_cmdRegistrar_actionAdapter(
						this));
		pnlLeftBuscarCliente.setOpaque(false);
		lblFecha.setText("Fecha:");
		gridLayout4.setRows(2);
		gridLayout4.setColumns(1);
		jLabel11.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel11.setText("Total a pagar:");
		txtTotal.setBackground(Color.white);
		txtTotal.setEnabled(false);
		txtTotal.setFont(new java.awt.Font("Dialog", 1, 30));
		txtTotal.setBorder(BorderFactory.createEtchedBorder());
		txtTotal.setDisabledTextColor(Color.black);

		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlCentroFormaPago.setLayout(borderLayout11);

		// cmdAgregarProducto.setFont(new java.awt.Font("Dialog", Font.PLAIN,
		// 14));
		// cmdAgregarProducto.setToolTipText("");
		// cmdAgregarProducto.setMnemonic('A');
		// cmdAgregarProducto.setText("Agregar producto");
		// cmdAgregarProducto.addActionListener(new
		// PnlVentaApartadoEvento_cmdAgregarProducto_actionAdapter(this));
		jLabel6.setFont(new java.awt.Font("Dialog", 0, 16));
		jLabel6.setText("Porcentaje %");
		jLabel7.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/logo.jpg")));
		jLabel7.setText("");
		pnlCentroFormaPago.setOpaque(false);
		bottom.setOpaque(false);
		jLabel8.setText("Vendedor:");
		lblVendedor.setText(AppInstance.usuario().getNombre());
		lblLugar.setText("Lugar de expedicion:");
		cmdImprimirNota.setEnabled(false);
		cmdImprimirNota.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdImprimirNota.setText("IMPRIMIR");
		cmdImprimirNota
				.addActionListener(new PnlVentaApartado_cmdImprimirNota_actionAdapter(
						this));
		cmdCerrar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdCerrar.setText("SALIR");
		cmdCerrar.setMnemonic('R');
		cmdCerrar
				.addActionListener(new PnlVentaApartado_cmdCerrar_actionAdapter(
						this));
		lblTotalProductos.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblTotalProductos.setText("0");
		jLabel10.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel10.setText("Total de productos:");
		jPanel2.setBackground(Color.white);
		cmdCliente.setText("CLIENTE");
		cmdCliente
				.addActionListener(new PnlVentaApartado_cmdCliente_actionAdapter(
						this));
		jPanel3.setBackground(Color.white);
		jPanel3.setMinimumSize(new Dimension(140, 140));
		jPanel3.setPreferredSize(new Dimension(140, 140));
		jPanel3.setInputVerifier(null);
		jPanel3.setLayout(flowLayout1);
		pnlCentroVentaArticulos.setLayout(borderLayout13);
		pnlFotografia1.setBackground(Color.white);
		pnlFotografia1.setPreferredSize(new Dimension(107, 114));
		jLabel1.setText("Fotografia");
		pnlGeneralesVenta.setLayout(borderLayout12);
		pnlGridBagLugarExp.setLayout(gridBagLayout1);
		pnlGridBagLugarExp.setBackground(Color.white);
		pnlGridBagLugarExp.setMinimumSize(new Dimension(1124, 30));
		pnlGridBagLugarExp.setPreferredSize(new Dimension(1124, 30));
		this.add(encabezado, BorderLayout.NORTH);
		encabezado.add(jLabel3, BorderLayout.CENTER);
		encabezado.add(jLabel7, BorderLayout.EAST);
		this.add(bottom, BorderLayout.SOUTH);
		bottom.add(cmdPagarAnticipo, null);
		bottom.add(cmdImprimirNota);
		bottom.add(cmdCerrar);
		this.add(pnlCentro, BorderLayout.CENTER);
		pnlDatosClienteyBusquedaArticulo.add(pnlDatos, BorderLayout.CENTER);
		pnlDatosClienteyBusquedaArticulo.add(pnlBusquedaArticulos,
				BorderLayout.SOUTH);
		pnlBusquedaArticulos.add(jLabel2, BorderLayout.WEST);
		pnlBusquedaArticulos.add(txtEtiqueta, BorderLayout.CENTER);
		pnlDatosClienteyBusquedaArticulo.add(pnlGeneralesVenta,
				java.awt.BorderLayout.NORTH); // pnlBusquedaArticulos.add(cmdAgregarProducto,
												// BorderLayout.EAST);
		jPanel1.add(jLabel11, null);
		jPanel1.add(txtTotal, null);
		pnlTotales.add(jPanel1, BorderLayout.EAST);
		pnlTotales.add(pnlCentroFormaPago, BorderLayout.CENTER);
		jPanel2.add(jLabel10);
		jPanel2.add(lblTotalProductos);
		scrollVentaArticulos.getViewport().add(tblProductos, null);
		pnlBusquedaVentasPagos.add(pnlTotales, BorderLayout.SOUTH);
		pnlLeftBuscarCliente.add(lblLugar);
		pnlLeftBuscarCliente.add(lblFecha);
		pnlLeftBuscarCliente.add(jLabel8);
		pnlLeftBuscarCliente.add(lblVendedor);

		pnlCentro.add(pnlTipoVentaDatosCliente, java.awt.BorderLayout.CENTER);
		pnlTipoVentaDatosCliente.add(pnlDatosClienteyBusquedaArticulo,
				java.awt.BorderLayout.NORTH);
		pnlTipoVentaDatosCliente.add(pnlBusquedaVentasPagos,
				java.awt.BorderLayout.CENTER);
		pnlCentroFormaPago.add(jPanel2, java.awt.BorderLayout.CENTER);
		pnlBusquedaVentasPagos.add(pnlCentroVentaArticulos,
				java.awt.BorderLayout.CENTER);
		pnlCentroVentaArticulos.add(scrollVentaArticulos,
				java.awt.BorderLayout.CENTER);
		jPanel3.add(jLabel1);
		jPanel3.add(pnlFotografia1, null);
		pnlCentroVentaArticulos.add(jPanel3, java.awt.BorderLayout.WEST);
		pnlGeneralesVenta.add(pnlGridBagLugarExp, java.awt.BorderLayout.NORTH);
		pnlGridBagLugarExp.add(lblFecha, new GridBagConstraints(1, 0, 1, 1,
				0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 300, 0));
		pnlGridBagLugarExp.add(lblVendedor, new GridBagConstraints(4, 0, 1, 1,
				0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(0, 1, 0, 0), 300, 15));
		pnlGridBagLugarExp.add(jLabel8, new GridBagConstraints(2, 0, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 20, 0));
		pnlGridBagLugarExp.add(lblLugar, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(0, 25, 0, 67), 318, 15));
		pnlDatos.add(txtCliente, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 187, 7));
		pnlDatos.add(txtDomicilio, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 187, 7));
		pnlDatos.add(txtTelefono, new GridBagConstraints(5, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 187, 7));
		pnlDatos.add(jLabel4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 5, 10));
		pnlDatos.add(jLabel9, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 4, 10));
		pnlDatos.add(jLabel5, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 5, 10));
		pnlDatos.add(cmdCliente, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 4), 74, 7));
		this.cmdPagarAnticipo.setEnabled(false);
		this.lblFecha.setText("Fecha: "
				+ AppInstance.formatoLargo.format(new java.util.Date()));

		txtEtiqueta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		cmdPagarAnticipo.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		txtEtiqueta.setBorder(BorderFactory.createEtchedBorder());
		engine.iniciarVenta(AppInstance.usuario().getId());
		this.modelVentaArticulos1.data = engine.productosView();
		regFacIndEngine.setVenta(engine.getVenta());
		
		tblProductos.getColumnModel().getColumn(0).setMaxWidth(120);
		tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
		tblProductos.getColumnModel().getColumn(2).setMaxWidth(300);
		tblProductos.getColumnModel().getColumn(3).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(4).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(5).setMaxWidth(250);
	}

	void txtEtiqueta_actionPerformed(ActionEvent e) {
		if (engine.agregarProducto(txtEtiqueta.getText())) {
			this.modelVentaArticulos1.fireTableDataChanged();
			this.totalProductos++;
			this.lblTotalProductos.setText(String.valueOf(this.totalProductos));
			// Revisamos el total de la nota>0 habilitamos los botones
			txtTotal.setText(number.format(engine.getTotal()));
			if (engine.getTotal() > 0) {
				this.cmdPagarAnticipo.setEnabled(true);
			}

		} else {
			JOptionPane.showMessageDialog(this.getRootPane(),
					"El producto no fue encontrado en el inventario",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);

		}
		txtEtiqueta.setText("");
		txtEtiqueta.requestFocus();

	}

	public void propertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName().equals("idCliente")) {
		}
	}

	/**
	 * setDatosCliente
	 */

	void txtSubtotal_keyReleased(KeyEvent e) {
	}

	void cmdRegistrar_actionPerformed(ActionEvent e) {
		if (cmdPagarAnticipo.isEnabled()) {
			if ("".equals(txtCliente.getText())
					|| "".equals(txtDomicilio.getText())
					|| "".equals(txtTelefono.getText())) {
				JOptionPane.showMessageDialog(null,
						"Seleccione un cliente", "Atencion",
						JOptionPane.WARNING_MESSAGE);
				txtCliente.requestFocus();
				return;
			} else {
				// Ponemos los datos del cliente
				engine.setDatosCliente(txtCliente.getText().toUpperCase(),
						txtDomicilio.getText().toUpperCase(), txtTelefono
								.getText().toUpperCase());

				//PREGUNTAR SI SE REQUIERE FACTURA
				//PREGUNTAMOS SI SE DESEA FACTURA
				this.dlgFacReq.setLocationRelativeTo(this.getRootPane());
				this.dlgFacReq.setVisible(true);
		
				// Solicitamos anticipo del apartado
				dlg = new DlgAnticipoApartado(
						Math.ceil(engine.getTotal() * .25));
				dlg.setSize(400, 360);
				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setVisible(true);
				if (dlg.abonar) {
					if (!AppInstance.boutique().isAnticipoApartadoLibre()) {
						if (dlg.monto > 0) {
							// Abrimos el tipo de pago y mandamos el monto a
							// pagar
							frmIndicarTipoPago = new FrmIndicarTipoPago(1,
									dlg.monto, false, true);
							frmIndicarTipoPago.setSize(400, 400);
							frmIndicarTipoPago.setLocationRelativeTo(this
									.getRootPane());
							frmIndicarTipoPago.setVisible(true);
							if (frmIndicarTipoPago.engine.getMontoPendiente() <= 0.05) {
				
								// Tenemos los pagos, le ponemos los datos
								// correspondientes a la nota que es de apartado
								this.engine
										.agregarPagos(frmIndicarTipoPago.engine
												.getPagos()); // frmIndicarTipoPago.engine;
								if (this.engine.registrarVenta()) { // Damos de
																	// alta la
																	// venta
									engine.imprimirNota();
									cmdPagarAnticipo.setEnabled(false);
									JOptionPane
											.showMessageDialog(
													null,
													"Venta realizada",
													com.boutique.engine.impl.AppInstance.nombreNegocio,
													JOptionPane.INFORMATION_MESSAGE);
									cmdImprimirNota.setEnabled(true);
									// this.firePropertyChange("cerrar", false,
									// true);
								} else {
									JOptionPane
											.showMessageDialog(
													null,
													"Venta no realizada",
													com.boutique.engine.impl.AppInstance.nombreNegocio,
													JOptionPane.ERROR_MESSAGE);
								}
							}

						} else {
							JOptionPane
									.showMessageDialog(
											null,
											"Los pagos indicados no cubren el total a pagar",
											com.boutique.engine.impl.AppInstance.nombreNegocio,
											JOptionPane.ERROR_MESSAGE);
						}
					} else { // EL PAGO NO ES LIBRE DEBE SER EL MINIMO DE 25%

						if (dlg.monto > 0) {
							// Abrimos el tipo de pago y mandamos el monto a
							// pagar
							frmIndicarTipoPago = new FrmIndicarTipoPago(1,
									dlg.monto, false, true);
							frmIndicarTipoPago.setSize(400, 400);
							frmIndicarTipoPago.setLocationRelativeTo(this
									.getRootPane());
							frmIndicarTipoPago.setVisible(true);
							if (frmIndicarTipoPago.engine.getMontoPendiente() <= 0.05) {
								// Tenemos los pagos, le ponemos los datos
								// correspondientes a la nota que es de apartado
								this.engine
										.agregarPagos(frmIndicarTipoPago.engine
												.getPagos()); // frmIndicarTipoPago.engine;
								if (this.engine.registrarVenta()) { // Damos de
																	// alta la
																	// venta
									engine.imprimirNota();
									cmdPagarAnticipo.setEnabled(false);
									JOptionPane
											.showMessageDialog(
													null,
													"Venta realizada",
													com.boutique.engine.impl.AppInstance.nombreNegocio,
													JOptionPane.INFORMATION_MESSAGE);
									cmdImprimirNota.setEnabled(true);
									// this.firePropertyChange("cerrar", false,
									// true);
								} else {
									JOptionPane
											.showMessageDialog(
													null,
													"Venta no realizada",
													com.boutique.engine.impl.AppInstance.nombreNegocio,
													JOptionPane.ERROR_MESSAGE);

								}

							}
						} else { // NO HAY MONTO, SOLO REGISTRAMOS LA VENTA.
							if (this.engine.registrarVenta()) { // Damos de alta
																// la venta
								engine.imprimirNota();
								cmdPagarAnticipo.setEnabled(false);
								JOptionPane
										.showMessageDialog(
												null,
												"Venta realizada",
												com.boutique.engine.impl.AppInstance.nombreNegocio,
												JOptionPane.INFORMATION_MESSAGE);
								cmdImprimirNota.setEnabled(true);
								// this.firePropertyChange("cerrar", false,
								// true);
							} else {
								JOptionPane
										.showMessageDialog(
												null,
												"Venta no realizada",
												com.boutique.engine.impl.AppInstance.nombreNegocio,
												JOptionPane.ERROR_MESSAGE);

							}

						}
					}
				}
			}
		}
	}

	void this_mouseClicked(MouseEvent e) {

	}

	void cmdAgregarProducto_actionPerformed(ActionEvent e) {

		DlgAgregarInventarioLocal dlgILocal = new DlgAgregarInventarioLocal();
		dlgILocal.setLocationRelativeTo(this.getRootPane());
		dlgILocal.setVisible(true);

		if (dlgILocal.engine.etiqueta() != null) {
			engine.agregarProducto(dlgILocal.engine.etiqueta());
			this.modelVentaArticulos1.fireTableDataChanged();
			// Revisamos el total de la nota>0 habilitamos los botones
			txtTotal.setText(number.format(engine.getTotal()));
			if (engine.getTotal() > 0) {
				this.cmdPagarAnticipo.setEnabled(true);
			}

		} else {
			JOptionPane.showMessageDialog(this.getRootPane(),
					"El producto no fue encontrado en el inventario",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);

		}

		txtEtiqueta.setText("");
		txtEtiqueta.requestFocus();

	}

	void tblProductos_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27 && tblProductos.getSelectedRow() >= 0) {
			int i = JOptionPane.showConfirmDialog(this.getRootPane(),
					"¿Esta seguro que desea cancelar el producto?",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				// Quitamos el articulo de la venta
				// this.quitarArticuloDeVenta();
				engine.removerProducto(tblProductos.getSelectedRow());
				this.modelVentaArticulos1.fireTableDataChanged();
				this.totalProductos--;
				this.lblTotalProductos.setText(String
						.valueOf(this.totalProductos));

				this.txtTotal.setText(number.format(engine.getTotal()));
				if (engine.productosView().size() == 0) {
					this.cmdPagarAnticipo.setEnabled(false);
					this.txtEtiqueta.requestFocus();
				}
			}

		}
	}

	@SuppressWarnings("rawtypes")
	transient Vector propertyChangeListeners;
	JButton cmdImprimirNota = new JButton();
	JButton cmdCerrar = new JButton();
	JPanel jPanel2 = new JPanel();
	JLabel lblTotalProductos = new JLabel();
	JLabel jLabel10 = new JLabel();
	private int totalProductos;
	JButton cmdCliente = new JButton();
	JPanel jPanel3 = new JPanel();
	PnlFotografia pnlFotografia1 = new PnlFotografia();
	JPanel pnlCentroVentaArticulos = new JPanel();
	BorderLayout borderLayout13 = new BorderLayout();
	FlowLayout flowLayout1 = new FlowLayout();
	JLabel jLabel1 = new JLabel();
	JPanel pnlGeneralesVenta = new JPanel();
	BorderLayout borderLayout12 = new BorderLayout();
	JPanel pnlGridBagLugarExp = new JPanel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	GridBagLayout gridBagLayout2 = new GridBagLayout();

	@SuppressWarnings("rawtypes")
	public synchronized void removePropertyChangeListener(
			PropertyChangeListener l) {
		super.removePropertyChangeListener(l);
		if (propertyChangeListeners != null
				&& propertyChangeListeners.contains(l)) {
			Vector v = (Vector) propertyChangeListeners.clone();
			v.removeElement(l);
			propertyChangeListeners = v;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		super.addPropertyChangeListener(l);
		Vector v = propertyChangeListeners == null ? new Vector(2)
				: (Vector) propertyChangeListeners.clone();
		if (!v.contains(l)) {
			v.addElement(l);
			propertyChangeListeners = v;
		}
	}

	@SuppressWarnings("rawtypes")
	protected void firePropertyChange(PropertyChangeEvent evt) {
		if (propertyChangeListeners != null) {
			Vector listeners = propertyChangeListeners;
			int count = listeners.size();
			for (int i = 0; i < count; i++) {
				((PropertyChangeListener) listeners.elementAt(i))
						.propertyChange(evt);
			}
		}
	}

	public void cmdImprimirNota_actionPerformed(ActionEvent e) {
		engine.imprimirNota();
	}

	public void cmdCerrar_actionPerformed(ActionEvent e) {
		this.firePropertyChange("cerrar", false, true);
	}

	public void cmdCliente_actionPerformed(ActionEvent e) {
		FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);
		frmBuscarCliente.setModal(true);
		frmBuscarCliente.setSize(900, 600);
		frmBuscarCliente.setLocationRelativeTo(this);
		frmBuscarCliente.setVisible(true);
		if (frmBuscarCliente.cliente != null) {
			engine.cliente = frmBuscarCliente.cliente;
			this.regFacIndEngine.setCliente(engine.cliente);
			this.txtCliente.setText(engine.cliente.getNombre() + " "
					+ engine.cliente.getApellidoPaterno() + " "
					+ engine.cliente.getApellidoMaterno());

			this.txtDomicilio
					.setText(engine.cliente.getCalle()
							+ " "
							+ engine.cliente.getNumero()
							+ (!(engine.cliente.getNumeroInterior().equals("")) ? " int. "
									+ engine.cliente.getNumeroInterior()
									: "") + ", " + engine.cliente.getColonia());
			this.txtTelefono.setText(engine.cliente.getTelefono()
					+ (!(engine.cliente.getCelular().equals("")) ? " Cel."
							+ engine.cliente.getCelular() : ""));
			if (engine.cliente.getFotografia() == null) {
				pnlFotografia1.setImagen((InputStream) null);
			} else {
				pnlFotografia1.setImagen(engine.cliente.getFotografia());
			}

		}
		this.txtEtiqueta.requestFocus();

	}

}

class PnlVentaApartado_cmdCliente_actionAdapter implements ActionListener {
	private PnlVentaApartado adaptee;

	PnlVentaApartado_cmdCliente_actionAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCliente_actionPerformed(e);
	}
}

class PnlVentaApartado_cmdCerrar_actionAdapter implements ActionListener {
	private PnlVentaApartado adaptee;

	PnlVentaApartado_cmdCerrar_actionAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCerrar_actionPerformed(e);
	}
}

class PnlVentaApartado_cmdImprimirNota_actionAdapter implements ActionListener {
	private PnlVentaApartado adaptee;

	PnlVentaApartado_cmdImprimirNota_actionAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdImprimirNota_actionPerformed(e);
	}
}

class PnlVentaApartadoEvento_txtEtiqueta_actionAdapter implements
		java.awt.event.ActionListener {
	PnlVentaApartado adaptee;

	PnlVentaApartadoEvento_txtEtiqueta_actionAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtEtiqueta_actionPerformed(e);
	}
}

class PnlVentaApartadoEvento_cmdRegistrar_actionAdapter implements
		java.awt.event.ActionListener {
	PnlVentaApartado adaptee;

	PnlVentaApartadoEvento_cmdRegistrar_actionAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdRegistrar_actionPerformed(e);
	}
}

class PnlVentaApartadoEvento_this_mouseAdapter extends
		java.awt.event.MouseAdapter {
	PnlVentaApartado adaptee;

	PnlVentaApartadoEvento_this_mouseAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.this_mouseClicked(e);
	}
}

class PnlVentaApartadoEvento_cmdAgregarProducto_actionAdapter implements
		java.awt.event.ActionListener {
	PnlVentaApartado adaptee;

	PnlVentaApartadoEvento_cmdAgregarProducto_actionAdapter(
			PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarProducto_actionPerformed(e);
	}
}

class PnlVentaApartadoEvento_tblProductos_keyAdapter extends
		java.awt.event.KeyAdapter {
	PnlVentaApartado adaptee;

	PnlVentaApartadoEvento_tblProductos_keyAdapter(PnlVentaApartado adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.tblProductos_keyPressed(e);
	}
}
