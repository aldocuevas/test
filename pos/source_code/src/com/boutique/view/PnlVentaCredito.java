package com.boutique.view;

import java.beans.*;
import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

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

public class PnlVentaCredito extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FrmIndicarTipoPago frmIndicarTipoPago;
	VentaCreditoEngine engine = new VentaCreditoEngine();
	RegistroFacturaIndividualEngine regFacIndEngine = new RegistroFacturaIndividualEngine();
	private DlgFacturaRequerida dlgFacReq = new DlgFacturaRequerida(null,
			regFacIndEngine);

	BorderLayout borderLayout1 = new BorderLayout();
	ImageIcon puntoVenta = new ImageIcon();
	JPanel encabezado = new JPanel();
	JPanel bottom = new JPanel();
	JPanel pnlCentro = new JPanel();
	JPanel pnlTipoVentaDatosCliente = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel pnlDatosClienteyBusquedaArticulo = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	BorderLayout borderLayout4 = new BorderLayout();
	JLabel datosCliente = new JLabel();
	JLabel jLabel3 = new JLabel();
	JPanel pnlDatos = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel4 = new JLabel();
	JTextField txtTelefono = new JTextField();
	JLabel jLabel5 = new JLabel();
	JTextField txtCiudad = new JTextField();
	JLabel asd = new JLabel();
	JTextField txtColonia = new JTextField();
	JLabel jLabel7 = new JLabel();
	JTextField txtMontoDisponible = new JTextField();
	JLabel jLabel8 = new JLabel();
	JTextField txtCalle = new JTextField();
	JLabel jLabel9 = new JLabel();
	JTextField txtNombre = new JTextField();
	JPanel pnlBusquedaVentasPagos = new JPanel();
	JScrollPane scrollVentaArticulos = new JScrollPane();
	BorderLayout borderLayout5 = new BorderLayout();
	JLabel jLabel2 = new JLabel();
	JTable tblProductos = new JTable();
	public ModelVentaArticulos modelVentaArticulos1 = new ModelVentaArticulos();
	JPanel pnlBusquedaArticulos = new JPanel();
	JTextField txtEtiqueta = new JTextField();
	JPanel pnlBusquedaClientes = new JPanel();

	JPanel pnlTotales = new JPanel();
	JSplitPane split = new JSplitPane();
	BorderLayout borderLayout6 = new BorderLayout();
	JPanel pnlDerechaTotales = new JPanel();
	GridLayout gridLayout3 = new GridLayout();
	JButton cmdRegistrarSinRecibir = new JButton();
	BorderLayout borderLayout7 = new BorderLayout();
	JPanel pnlLeftBuscarCliente = new JPanel();
	JLabel lblFecha = new JLabel();
	GridLayout gridLayout4 = new GridLayout();
	JPanel pnlDatoDelPago = new JPanel();
	JPanel pnlTipoDePago = new JPanel();
	JPanel pnlIzquierdaTipoPago = new JPanel();
	JLabel lblLeyenda = new JLabel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	JLabel jLabel11 = new JLabel();
	JTextField txtTotal = new JTextField();
	JPanel jPanel2 = new JPanel();

	BorderLayout borderLayout8 = new BorderLayout();
	SpinnerDateModel dateModel = new SpinnerDateModel();

	BorderLayout borderLayout9 = new BorderLayout();
	// JButton cmdAgregarProducto = new JButton();
	JPanel pnlPagosCredito = new JPanel();
	BorderLayout borderLayout10 = new BorderLayout();
	JLabel jLabel10 = new JLabel();
	JRadioButton rSemanal = new JRadioButton();
	JRadioButton rQuincenal = new JRadioButton();
	JRadioButton jRadioButton3 = new JRadioButton();
	JLabel jLabel13 = new JLabel();
	JPanel jPanel3 = new JPanel();
	JPanel pnlNorthCantidadPagos = new JPanel();
	BorderLayout borderLayout11 = new BorderLayout();
	JPanel pnlRadios = new JPanel();
	JRadioButton rMensual = new JRadioButton();
	JLabel jLabel12 = new JLabel();
	JComboBox cmbCantidadPagos = new JComboBox();
	ButtonGroup buttonGroup1 = new ButtonGroup();
	JTable tblPagos = new JTable();
	JScrollPane scrollPagos = new JScrollPane();
	DefaultComboBoxModel comboSemanal = new DefaultComboBoxModel();
	DefaultComboBoxModel comboQuincenal = new DefaultComboBoxModel();
	DefaultComboBoxModel comboMensual = new DefaultComboBoxModel();

	ModelPagosCredito modelPagosCredito1 = new ModelPagosCredito();
	private NumberFormat number = AppInstance.number;
	JPanel jPanel1 = new JPanel();
	private DlgAnticipoCredito dlg;
	BorderLayout borderLayout12 = new BorderLayout();
	JLabel jLabel14 = new JLabel();
	JPanel jPanel4 = new JPanel();
	JLabel jLabel15 = new JLabel();
	JLabel lblVendedor = new JLabel();
	JButton cmdPonerAnticipo = new JButton();

	public PnlVentaCredito() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		comboSemanal.addElement("1");
		comboSemanal.addElement("2");
		comboSemanal.addElement("3");
		comboSemanal.addElement("4");
		comboSemanal.addElement("5");
		comboSemanal.addElement("6");
		comboQuincenal.addElement("1");
		comboQuincenal.addElement("2");
		comboQuincenal.addElement("3");
		comboQuincenal.addElement("4");
		comboQuincenal.addElement("5");
		comboQuincenal.addElement("6");

		comboMensual.addElement("1");
		comboMensual.addElement("2");
		// puntoVenta = new ImageIcon(gui.FrmAppBoutique.class.getResource(
		// "img/puntoVenta.jpg"));
		this.setBackground(Color.white);
		this.setForeground(Color.black);
		this.setMinimumSize(new Dimension(0, 0));
		this.setLayout(borderLayout1);
		pnlCentro.setBackground(Color.white);
		pnlCentro.setLayout(borderLayout2);
		pnlTipoVentaDatosCliente.setLayout(borderLayout3);
		pnlDatosClienteyBusquedaArticulo.setBackground(Color.white);
		pnlDatosClienteyBusquedaArticulo.setLayout(borderLayout4);
		datosCliente.setFont(new java.awt.Font("Dialog", 1, 12));
		datosCliente.setToolTipText("");
		datosCliente.setText("Datos del cliente");
		jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		jLabel3.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/ventaCredito.jpg")));
		jLabel3.setText("");
		encabezado.setOpaque(false);
		encabezado.setLayout(borderLayout12);
		pnlDatos.setLayout(gridLayout1);
		gridLayout1.setColumns(6);
		gridLayout1.setHgap(1);
		gridLayout1.setRows(2);
		gridLayout1.setVgap(1);
		jLabel4.setOpaque(false);
		jLabel4.setText("Nombre:");
		txtTelefono.setEnabled(false);
		txtTelefono.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
		txtTelefono.setBorder(BorderFactory.createEtchedBorder());
		txtTelefono.setDisabledTextColor(Color.black);
		txtTelefono.setText("");
		jLabel5.setText("Teléfono:");
		txtCiudad.setEnabled(false);
		txtCiudad.setBorder(BorderFactory.createEtchedBorder());
		txtCiudad.setDebugGraphicsOptions(0);
		txtCiudad.setDisabledTextColor(Color.black);
		txtCiudad.setText("");
		txtCiudad.setColumns(0);
		asd.setText("Ciudad:");
		txtColonia.setEnabled(false);
		txtColonia.setBorder(BorderFactory.createEtchedBorder());
		txtColonia.setDisabledTextColor(Color.black);
		txtColonia.setText("");
		jLabel7.setText("Colonia:");
		txtMontoDisponible.setEnabled(false);
		txtMontoDisponible.setBorder(BorderFactory.createEtchedBorder());
		txtMontoDisponible.setDisabledTextColor(Color.black);
		txtMontoDisponible.setText("");
		jLabel8.setText("Monto disponible:");
		txtCalle.setEnabled(false);
		txtCalle.setBorder(BorderFactory.createEtchedBorder());
		txtCalle.setDisabledTextColor(Color.black);
		txtCalle.setText("");
		jLabel9.setText("Calle:");
		txtNombre.setEnabled(false);
		txtNombre.setBorder(BorderFactory.createEtchedBorder());
		txtNombre.setDisabledTextColor(Color.black);
		txtNombre.setText("");
		txtNombre.setBackground(Color.white);
		txtCalle.setBackground(Color.white);
		txtTelefono.setBackground(Color.white);
		txtColonia.setBackground(Color.white);
		txtCiudad.setBackground(Color.white);
		txtMontoDisponible.setBackground(Color.white);
		pnlDatos.setOpaque(false);
		pnlBusquedaVentasPagos.setLayout(borderLayout5);
		jLabel2.setText("Pase el artículo");
		scrollVentaArticulos.getViewport().setBackground(Color.white);
		scrollVentaArticulos.setAutoscrolls(true);
		scrollVentaArticulos.setMaximumSize(new Dimension(200, 250));
		scrollVentaArticulos.setMinimumSize(new Dimension(24, 250));
		scrollVentaArticulos.setPreferredSize(new Dimension(200, 100));
		tblProductos.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		tblProductos.setBorder(BorderFactory.createEtchedBorder());
		tblProductos.setDoubleBuffered(false);
		tblProductos.setCellSelectionEnabled(false);
		tblProductos.setColumnSelectionAllowed(false);
		tblProductos.setIntercellSpacing(new Dimension(0, 2));
		tblProductos.setRowMargin(1);
		tblProductos.setRowSelectionAllowed(true);
		tblProductos.setShowHorizontalLines(false);
		tblProductos.setShowVerticalLines(false);
		tblProductos
				.addKeyListener(new PnlPuntoVentaCredito_tblProductos_keyAdapter(
						this));
		pnlBusquedaArticulos.setEnabled(true);
		pnlBusquedaArticulos.setDebugGraphicsOptions(0);
		pnlBusquedaArticulos.setDoubleBuffered(true);
		pnlBusquedaArticulos.setLayout(borderLayout9);
		txtEtiqueta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtEtiqueta.setText("");
		txtEtiqueta
				.addActionListener(new PnlPuntoVenta_txtEtiqueta_actionAdapter(
						this));
		pnlBusquedaClientes.setAlignmentY((float) 0.5);
		pnlBusquedaClientes.setOpaque(false);
		pnlBusquedaClientes.setLayout(borderLayout7);
		pnlTotales.setLayout(borderLayout6);
		pnlDerechaTotales.setLayout(gridLayout3);
		gridLayout3.setColumns(2);
		gridLayout3.setRows(3);
		pnlDerechaTotales.setMinimumSize(new Dimension(250, 200));
		pnlDerechaTotales.setOpaque(true);
		pnlDerechaTotales.setPreferredSize(new Dimension(250, 200));
		pnlTotales.setBackground(Color.white);
		pnlTotales.setMinimumSize(new Dimension(260, 200));
		pnlTotales.setPreferredSize(new Dimension(444, 200));
		cmdRegistrarSinRecibir.setEnabled(false);
		cmdRegistrarSinRecibir.setFont(new java.awt.Font("Dialog", Font.PLAIN,
				16));
		cmdRegistrarSinRecibir.setMnemonic('S');
		cmdRegistrarSinRecibir.setText("Sin anticipo");
		cmdRegistrarSinRecibir
				.addActionListener(new PnlPuntoVentaCredito_cmdRegistrarSinRecibir_actionAdapter(
						this));
		pnlLeftBuscarCliente.setOpaque(false);
		lblFecha.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		lblFecha.setText("Fecha:");
		gridLayout4.setRows(2);
		gridLayout4.setColumns(1);
		pnlDatoDelPago.setLayout(gridLayout4);
		pnlTipoDePago.setMinimumSize(new Dimension(106, 15));
		pnlTipoDePago.setDoubleBuffered(true);
		pnlTipoDePago.setMaximumSize(new Dimension(32767, 100));
		pnlIzquierdaTipoPago.setLayout(gridBagLayout1);
		pnlIzquierdaTipoPago.setBorder(null);
		pnlIzquierdaTipoPago.setDebugGraphicsOptions(0);
		jLabel11.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel11.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel11.setText("Total:");

		txtTotal.setBackground(Color.white);
		txtTotal.setEnabled(false);
		txtTotal.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		txtTotal.setBorder(BorderFactory.createEtchedBorder());
		txtTotal.setDisabledTextColor(Color.black);
		txtTotal.setText(number.format(0));
		txtTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		jPanel2.setLayout(borderLayout8);
		// cmdAgregarProducto.setMnemonic('A');
		// cmdAgregarProducto.setText("Agregar producto");
		// cmdAgregarProducto.addActionListener(new
		// PnlPuntoVentaCredito_cmdAgregarProducto_actionAdapter(this));
		pnlTipoVentaDatosCliente.setPreferredSize(new Dimension(996, 600));
		pnlBusquedaVentasPagos.setBorder(null);
		pnlBusquedaVentasPagos.setMinimumSize(new Dimension(206, 200));
		pnlBusquedaVentasPagos.setPreferredSize(new Dimension(452, 100));
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setPreferredSize(new Dimension(454, 500));
		pnlPagosCredito.setBorder(null);
		pnlPagosCredito.setMinimumSize(new Dimension(10, 160));
		pnlPagosCredito.setPreferredSize(new Dimension(10, 160));
		pnlPagosCredito.setLayout(borderLayout10);
		jLabel10.setFont(new java.awt.Font("Dialog", Font.BOLD, 13));
		jLabel10.setText("Indique la cantidad de pagos");
		rSemanal.setText("Semanal");
		rSemanal.addActionListener(new PnlPuntoVentaCredito_rSemanal_actionAdapter(
				this));
		rQuincenal.setSelected(true);
		rQuincenal.setText("Quincenal");
		rQuincenal
				.addActionListener(new PnlPuntoVentaCredito_rQuincenal_actionAdapter(
						this));
		jRadioButton3.setSelected(true);
		jRadioButton3.setText("Quincenal");
		jLabel13.setText("Cantidad de pagos:");
		pnlNorthCantidadPagos.setLayout(borderLayout11);
		rMensual.setText("Mensual");
		rMensual.addActionListener(new PnlPuntoVentaCredito_rMensual_actionAdapter(
				this));
		jLabel12.setText("Cantidad de pagos:");
		tblPagos.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
		tblPagos.setModel(modelPagosCredito1);
		tblPagos.setRowHeight(20);
		tblPagos.setShowHorizontalLines(false);
		tblProductos.setModel(modelVentaArticulos1);
		tblProductos.setRowHeight(20);
		tblPagos.setShowVerticalLines(false);
		cmbCantidadPagos.setModel(comboQuincenal);
		cmbCantidadPagos
				.addItemListener(new PnlPuntoVentaCredito_cmbCantidadPagos_itemAdapter(
						this));
		jLabel14.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/logo.jpg")));
		jPanel4.setBackground(Color.white);
		jLabel15.setText("Vendedor:");
		jLabel15.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblVendedor.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		cmdPonerAnticipo.setEnabled(false);
		cmdPonerAnticipo.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		cmdPonerAnticipo.setMnemonic('C');
		cmdPonerAnticipo.setText("Con anticipo");
		cmdPonerAnticipo
				.addActionListener(new PnlPuntoVentaCredito_cmdPonerAnticipo_actionAdapter(
						this));
		cmdRegistrarVenta.setEnabled(false);
		cmdRegistrarVenta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdRegistrarVenta.setMnemonic('P');
		cmdRegistrarVenta.setText("REGISTRAR VENTA");
		cmdRegistrarVenta
				.addActionListener(new PnlPuntoVentaCredito_cmdRegistrarVenta_actionAdapter(
						this));
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel1.setHorizontalAlignment(SwingConstants.TRAILING);
		jLabel1.setText("Anticipo:");
		txtAnticipo.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		txtAnticipo.setText("0.00");
		txtAnticipo.setHorizontalAlignment(SwingConstants.TRAILING);
		jLabel6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel6.setHorizontalAlignment(SwingConstants.TRAILING);
		jLabel6.setText("Subtotal:");
		txtSubtotal.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
		txtSubtotal.setText("0.00");
		txtSubtotal.setHorizontalAlignment(SwingConstants.TRAILING);
		cmdImprimirNota.setEnabled(false);
		cmdImprimirNota.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdImprimirNota.setText("IMPRIMIR");
		cmdImprimirNota
				.addActionListener(new PnlVentaCredito_cmdImprimirNota_actionAdapter(
						this));
		cmdSalir.setEnabled(true);
		cmdSalir.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdSalir.setText("SALIR");
		cmdSalir.setMnemonic('R');
		cmdSalir.addActionListener(new PnlVentaCredito_cmdCerrar_actionAdapter(
				this));
		lblTotalProductos.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblTotalProductos.setText("0");
		jLabel17.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel17.setText("Total de productos:");
		this.add(encabezado, BorderLayout.NORTH);
		encabezado.add(jLabel3, BorderLayout.CENTER);
		encabezado.add(jLabel14, BorderLayout.EAST);
		this.add(bottom, BorderLayout.SOUTH);
		bottom.add(cmdRegistrarVenta);
		bottom.add(cmdImprimirNota);
		bottom.add(cmdSalir);
		this.add(pnlCentro, BorderLayout.CENTER);
		pnlDatosClienteyBusquedaArticulo.add(pnlDatos, BorderLayout.CENTER);
		pnlDatos.add(jLabel4, null);
		pnlDatos.add(txtNombre, null);
		pnlDatos.add(jLabel9, null);
		pnlDatos.add(txtCalle, null);
		pnlDatos.add(jLabel8, null);
		pnlDatos.add(txtMontoDisponible, null);
		pnlDatos.add(jLabel7, null);
		pnlDatos.add(txtColonia, null);
		pnlDatos.add(asd, null);
		pnlDatos.add(txtCiudad, null);
		pnlDatos.add(jLabel5, null);
		pnlDatos.add(txtTelefono, null);
		pnlDatosClienteyBusquedaArticulo.add(pnlBusquedaClientes,
				BorderLayout.NORTH);
		pnlBusquedaClientes.add(pnlLeftBuscarCliente, BorderLayout.WEST);
		pnlLeftBuscarCliente.add(datosCliente, null);
		pnlDatosClienteyBusquedaArticulo.add(pnlBusquedaArticulos,
				BorderLayout.SOUTH);
		pnlTotales.add(pnlDerechaTotales, BorderLayout.EAST);
		pnlDerechaTotales.add(jLabel6);
		pnlDerechaTotales.add(txtSubtotal);
		pnlDerechaTotales.add(jLabel1);
		pnlDerechaTotales.add(txtAnticipo);
		pnlDerechaTotales.add(jLabel11, null);
		pnlIzquierdaTipoPago.add(pnlTipoDePago, new GridBagConstraints(0, 0, 1,
				1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		pnlIzquierdaTipoPago.add(pnlDatoDelPago, new GridBagConstraints(0, 1,
				1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 106, 0));
		pnlTotales.add(jPanel2, BorderLayout.CENTER);
		pnlBusquedaClientes.add(lblFecha, BorderLayout.EAST);
		pnlDerechaTotales.add(txtTotal, null);
		pnlBusquedaArticulos.add(txtEtiqueta, java.awt.BorderLayout.CENTER);
		pnlBusquedaArticulos.add(jLabel2, java.awt.BorderLayout.WEST);
		// pnlBusquedaArticulos.add(cmdAgregarProducto,
		// java.awt.BorderLayout.EAST);
		pnlBusquedaVentasPagos.add(pnlTotales, java.awt.BorderLayout.CENTER);
		split.add(pnlPagosCredito, JSplitPane.BOTTOM);
		split.add(scrollVentaArticulos, JSplitPane.TOP);
		scrollVentaArticulos.getViewport().add(tblProductos);
		pnlTipoVentaDatosCliente.add(split, java.awt.BorderLayout.CENTER);
		pnlCentro.add(pnlTipoVentaDatosCliente, java.awt.BorderLayout.CENTER);
		pnlTipoVentaDatosCliente.add(pnlDatosClienteyBusquedaArticulo,
				java.awt.BorderLayout.NORTH);
		pnlTipoVentaDatosCliente.add(pnlBusquedaVentasPagos,
				java.awt.BorderLayout.SOUTH);
		pnlRadios.add(jLabel12);
		pnlRadios.add(cmbCantidadPagos);
		pnlRadios.add(rSemanal);
		pnlRadios.add(rQuincenal);
		pnlRadios.add(rMensual);
		pnlNorthCantidadPagos.add(pnlConSinAnticipo,
				java.awt.BorderLayout.NORTH);
		pnlConSinAnticipo.add(cmdPonerAnticipo);
		pnlConSinAnticipo.add(cmdRegistrarSinRecibir);
		this.lblFecha.setText("Fecha: "
				+ AppInstance.formatoLargo.format(new java.util.Date()));
		buttonGroup1.add(rSemanal);
		buttonGroup1.add(rQuincenal);
		buttonGroup1.add(rMensual);
		pnlPagosCredito.add(pnlNorthCantidadPagos, java.awt.BorderLayout.NORTH);
		pnlPagosCredito.add(scrollPagos, java.awt.BorderLayout.CENTER);
		scrollPagos.getViewport().add(tblPagos);
		pnlTotales.add(pnlIzquierdaTipoPago, java.awt.BorderLayout.SOUTH);
		pnlBusquedaClientes.add(jPanel4, java.awt.BorderLayout.CENTER);
		jPanel4.add(jLabel15);
		jPanel4.add(lblVendedor);
		pnlNorthCantidadPagos.add(pnlRadios, java.awt.BorderLayout.SOUTH);
		pnlNorthCantidadPagos.add(jLabel10, java.awt.BorderLayout.CENTER);
		tblProductos.getColumnModel().getColumn(0).setMaxWidth(120);
		tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
		tblProductos.getColumnModel().getColumn(2).setMaxWidth(300);
		tblProductos.getColumnModel().getColumn(3).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(4).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(5).setMaxWidth(250);
		jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);
		jPanel1.add(jLabel17);
		jPanel1.add(lblTotalProductos);
	}

	void txtEtiqueta_actionPerformed(ActionEvent e) {
		// Buscamos el articulo por etiqueta a ver si hay disponible
		// Buscamos el articulo
		if (engine.agregarProducto(txtEtiqueta.getText().replace("-", "/"))) {
			this.modelVentaArticulos1.fireTableDataChanged();
			this.modelPagosCredito1.fireTableDataChanged();
			this.totalProductos++;
			this.lblTotalProductos.setText(String.valueOf(totalProductos));

			// Revisamos el total de la nota>0 habilitamos los botones
			txtTotal.setText(number.format(engine.getTotal()));
			txtSubtotal.setText(number.format(engine.getSubTotal()));
			if (engine.getTotal() > 0) {
				// habilitarBotonesVenta();
				cmdPonerAnticipo.setEnabled(true);
				this.cmdRegistrarSinRecibir.setEnabled(true);
				cmdRegistrarVenta.setEnabled(true);
			} else {
				cmdRegistrarVenta.setEnabled(false);
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

	}

	/**
	 * setDatosCliente
	 */
	public void setDatosCliente() {
		this.modelVentaArticulos1.data = engine.productosView();
		this.modelPagosCredito1.data = engine.intervaloPagos();
		this.txtNombre.setText(engine.cliente().getNombre() + " "
				+ engine.cliente().getApellidoPaterno() + " "
				+ engine.cliente().getApellidoMaterno());
		this.txtCalle.setText(engine.cliente().getCalle()
				+ " "
				+ engine.cliente().getNumero()
				+ " "
				+ (!engine.cliente().getNumeroInterior().equals("") ? "INT. "
						+ engine.cliente().getNumeroInterior() : ""));
		try {
			this.txtTelefono.setText(engine.cliente().getTelefono()
					+ ((!engine.cliente().getCelular().equals("")) ? " Cel. "
							+ engine.cliente().getCelular() : ""));
		} catch (Exception ex) {
		}
		this.txtMontoDisponible.setText(AppInstance.number
				.format(engine.montoDisponible));
		this.txtColonia.setText(engine.cliente().getColonia());
		// this.txtCiudad.setText(engine.cliente().getCiudad());
		this.modelPagosCredito1.fireTableDataChanged();
	}

	void ventaNueva() {
		this.setPagos();
	}

	void habilitarVenta() {
		this.txtEtiqueta.setEnabled(true);
		cmdRegistrarVenta.setEnabled(true);
		// this.cmdAgregarProducto.setEnabled(true);
		this.cmdPonerAnticipo.setEnabled(true);
		this.cmdRegistrarSinRecibir.setEnabled(true);
		this.cmdImprimirNota.setEnabled(false);
	}

	public void rSemanal_actionPerformed(ActionEvent e) {
		if (rSemanal.isSelected()) {
			if (rSemanal.isSelected()) {
				this.cmbCantidadPagos.setModel(this.comboSemanal);
				this.cmbCantidadPagos.setSelectedIndex(this.cmbCantidadPagos
						.getItemCount() - 1);

				this.setPagos();

			}

		}
	}

	public void rQuincenal_actionPerformed(ActionEvent e) {
		if (rQuincenal.isSelected()) {
			this.cmbCantidadPagos.setModel(this.comboQuincenal);
			this.cmbCantidadPagos.setSelectedIndex(2);
			this.setPagos();

		}
	}

	/**
	 * setPagosQuincenales
	 */
	private void setPagos() {
		// Ponemos los pagos en la tabla
		int noPagos = Integer.parseInt(this.cmbCantidadPagos.getSelectedItem()
				.toString());

		if (rSemanal.isSelected()) {

			engine.calcularFechasDePago(7, noPagos);
			this.modelPagosCredito1.fireTableDataChanged();
		} else if (rQuincenal.isSelected()) {
			engine.calcularFechasDePago(15, noPagos);
			this.modelPagosCredito1.fireTableDataChanged();
		}

		else if (rMensual.isSelected()) {
			engine.calcularFechasDePago(30, noPagos);
			this.modelPagosCredito1.fireTableDataChanged();
		}

	}

	public void rMensual_actionPerformed(ActionEvent e) {
		if (this.rMensual.isSelected()) {
			this.cmbCantidadPagos.setModel(this.comboMensual);
			this.setPagos();

		}
	}

	public void cmdAgregarProducto_actionPerformed(ActionEvent e) {
		/*
		 * if (cmdAgregarProducto.isEnabled()) {
		 * cmdAgregarProducto.setEnabled(false); dlgILocal = new
		 * DlgAgregarInventarioLocal();
		 * dlgILocal.setLocationRelativeTo(this.getRootPane());
		 * dlgILocal.setVisible(true); if (dlgILocal.operacion == true) { String
		 * etiqueta = this.dlgILocal.iLocal.getId(); articulo =
		 * dirLocal.findByEtiquetaOnStock(etiqueta); if (articulo != null) {
		 * //Tenemos el articulo lo ponemos en la tabla //Lo quitamos del stock
		 * if (dirLocal.descontarProducto(articulo.getEtiqueta())) {
		 * this.subTotal += Double.parseDouble(articulo.getPrecioPublico());
		 * this.txtSubtotal.setText(number.format(this.subTotal));
		 * calcularTotales(); this.setPagos(); permitirVenta(); }
		 * 
		 * //Agregamos el articulo a la venta }
		 * 
		 * } this.txtEtiqueta.requestFocus();
		 * cmdAgregarProducto.setEnabled(true); }
		 */
	}

	public void tblProductos_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27 && tblProductos.getRowCount() > 0) {
			int i = JOptionPane.showConfirmDialog(this.getRootPane(),
					"¿Esta seguro que desea cancelar el producto?",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				// Quitamos el articulo de la venta
				// this.quitarArticuloDeVenta();
				engine.removerProducto(tblProductos.getSelectedRow());
				this.modelVentaArticulos1.fireTableDataChanged();
				this.modelPagosCredito1.fireTableDataChanged();
				this.totalProductos--;
				this.lblTotalProductos.setText(String.valueOf(totalProductos));

				this.txtTotal.setText(number.format(engine.getTotal()));
				this.txtSubtotal.setText(number.format(engine.getSubTotal()));
				if (engine.productosView().size() == 0) {
					cmdPonerAnticipo.setEnabled(false);
					this.cmdRegistrarSinRecibir.setEnabled(false);
					this.cmdRegistrarVenta.setEnabled(false);
					this.txtEtiqueta.requestFocus();
				}
			}

		}
	}

	public void cmbCantidadPagos_itemStateChanged(ItemEvent e) {
		this.setPagos();
	}

	public void cmdPonerAnticipo_actionPerformed(ActionEvent e) {
		// Solicitamos anticipo del apartado
		dlg = new DlgAnticipoCredito(Math.ceil(engine.getSubTotal() * .25));
		dlg.setSize(380, 340);
		dlg.setLocationRelativeTo(this.getRootPane());
		dlg.setVisible(true);
		if (dlg.monto > 0) {
			// calculamos nuevamente los pagos con el total de la nota menos el
			// anticipo

			// Abrimos el tipo de pago y mandamos el monto a pagar
			frmIndicarTipoPago = new FrmIndicarTipoPago(1, dlg.monto, false,
					true);
			frmIndicarTipoPago.setSize(400, 400);
			frmIndicarTipoPago.setLocationRelativeTo(this.getRootPane());
			frmIndicarTipoPago.setVisible(true);
			if (frmIndicarTipoPago.engine.getMontoPendiente() <= 0.05) {
				engine.indicarAnticipo(dlg.monto);
				this.modelPagosCredito1.fireTableDataChanged();

				// Tenemos los pagos, le ponemos los datos correspondientes a la
				// nota que es credito
				this.engine.agregarPagos(frmIndicarTipoPago.engine.getPagos()); // frmIndicarTipoPago.engine;
				this.txtAnticipo.setText(number.format(dlg.monto * -1));
				this.txtTotal.setText(number.format(engine.getTotal()));
				this.cmdRegistrarVenta.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "No indico los pagos",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	transient Vector propertyChangeListeners;
	JPanel pnlConSinAnticipo = new JPanel();
	JButton cmdRegistrarVenta = new JButton();
	JLabel jLabel1 = new JLabel();
	JTextField txtAnticipo = new JTextField();
	JLabel jLabel6 = new JLabel();
	JTextField txtSubtotal = new JTextField();
	JButton cmdImprimirNota = new JButton();
	JButton cmdSalir = new JButton();
	JLabel lblTotalProductos = new JLabel();
	JLabel jLabel17 = new JLabel();
	private int totalProductos;

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	public void cmdRegistrarSinRecibir_actionPerformed(ActionEvent e) {
		if (cmdRegistrarSinRecibir.isEnabled()) {
			engine.indicarAnticipo(0);
			engine.limpiarPagos();
			this.modelPagosCredito1.fireTableDataChanged();
			this.txtAnticipo.setText("0.00");
			this.txtTotal.setText(number.format(engine.getTotal()));
			this.cmdRegistrarVenta.setEnabled(true);
		}
	}

	void deshabilitarBotonesVenta() {
		this.txtEtiqueta.setEnabled(false);
		cmdRegistrarVenta.setEnabled(false);
		// this.cmdAgregarProducto.setEnabled(false);
		this.cmdPonerAnticipo.setEnabled(false);
		this.cmdRegistrarSinRecibir.setEnabled(false);
	}

	@SuppressWarnings("deprecation")
	public void cmdRegistrarVenta_actionPerformed(ActionEvent e) {
		if (cmdRegistrarVenta.isEnabled()) {

			boolean permitida = true;

			if (engine.cliente().getLimiteCredito() > 0.0
					&& engine.getTotal() > engine.montoDisponible) {
				DlgAprobarLimiteSuperado dlg1 = new DlgAprobarLimiteSuperado(
						engine.getTotal() - engine.montoDisponible);
				dlg1.setModal(true);
				dlg1.setSize(385, 193);

				dlg1.setLocationRelativeTo(this.getRootPane());
				dlg1.setVisible(true);
				if (!dlg1.accion) {
					return;
				}
				// SIGNIFICA QUE DESEA CONTINUAR
				if (!dlg1.txtClave.getText().equals("jalisco")) {
					permitida = false;
				}
			}
			if (permitida) {
				this.deshabilitarBotonesVenta();
				//PREGUNTAMOS SI SE DESEA FACTURA
				this.dlgFacReq.setLocationRelativeTo(this.getRootPane());
				this.dlgFacReq.setVisible(true);
				if (this.engine.registrarVenta()) { // Damos de alta la venta

					engine.imprimirNota(this.modelPagosCredito1.data); // Imprimimos
																		// la
																		// nota
																		// de
																		// venta
																		// de
																		// credito

					cmdImprimirNota.setEnabled(true);
					JOptionPane.showMessageDialog(null, "Venta realizada",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.INFORMATION_MESSAGE);
					// this.firePropertyChange("cerrar", false, true);
				} else {
					JOptionPane.showMessageDialog(null, "Venta no realizada",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
					this.habilitarVenta();
				}
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"CLAVE DE AUTORIZACION INCORRECTA, NO SE PUEDE REGISTRAR LA VENTA, INTENTE ELIMINANDO ALGUNOS PRODUCTOS",
								com.boutique.engine.impl.AppInstance.nombreNegocio,
								JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	public void cmdImprimirNota_actionPerformed(ActionEvent e) {
		engine.imprimirNota(modelPagosCredito1.data);
	}

	public void cmdCerrar_actionPerformed(ActionEvent e) {
		this.firePropertyChange("cerrar", false, true);

	}

}

class PnlVentaCredito_cmdCerrar_actionAdapter implements ActionListener {
	private PnlVentaCredito adaptee;

	PnlVentaCredito_cmdCerrar_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCerrar_actionPerformed(e);
	}
}

class PnlVentaCredito_cmdImprimirNota_actionAdapter implements ActionListener {
	private PnlVentaCredito adaptee;

	PnlVentaCredito_cmdImprimirNota_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdImprimirNota_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_cmdRegistrarVenta_actionAdapter implements
		ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_cmdRegistrarVenta_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdRegistrarVenta_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_cmdRegistrarSinRecibir_actionAdapter implements
		ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_cmdRegistrarSinRecibir_actionAdapter(
			PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdRegistrarSinRecibir_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_cmdPonerAnticipo_actionAdapter implements
		ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_cmdPonerAnticipo_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdPonerAnticipo_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_tblProductos_keyAdapter extends KeyAdapter {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_tblProductos_keyAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.tblProductos_keyPressed(e);
	}
}

class PnlPuntoVentaCredito_cmbCantidadPagos_itemAdapter implements ItemListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_cmbCantidadPagos_itemAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void itemStateChanged(ItemEvent e) {

		adaptee.cmbCantidadPagos_itemStateChanged(e);
	}
}

class PnlPuntoVentaCredito_cmdAgregarProducto_actionAdapter implements
		ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_cmdAgregarProducto_actionAdapter(
			PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarProducto_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_rMensual_actionAdapter implements ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_rMensual_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.rMensual_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_rQuincenal_actionAdapter implements ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_rQuincenal_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.rQuincenal_actionPerformed(e);
	}
}

class PnlPuntoVentaCredito_rSemanal_actionAdapter implements ActionListener {
	private PnlVentaCredito adaptee;

	PnlPuntoVentaCredito_rSemanal_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.rSemanal_actionPerformed(e);
	}
}

class PnlPuntoVenta_txtEtiqueta_actionAdapter implements
		java.awt.event.ActionListener {
	PnlVentaCredito adaptee;

	PnlPuntoVenta_txtEtiqueta_actionAdapter(PnlVentaCredito adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtEtiqueta_actionPerformed(e);
	}
}
