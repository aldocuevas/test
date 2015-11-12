package com.boutique.view;

import java.beans.*;
import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

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

@SuppressWarnings("serial")
public class PnlVentaContado extends JPanel {
	int totalProductos = 0;
	DlgAplicarDescuento dlgDescuento = new DlgAplicarDescuento(null,
			com.boutique.engine.impl.AppInstance.nombreNegocio, true);

	VentaContadoEngine engine = new VentaContadoEngine();
	RegistroFacturaIndividualEngine regFacIndEngine = new RegistroFacturaIndividualEngine();
	private DlgFacturaRequerida dlgFacReq = new DlgFacturaRequerida(null,
			regFacIndEngine);

	// Ponemos los panels para los tipos de tarjetas y las cajas de texto
	NumberFormat number = NumberFormat
			.getNumberInstance(java.util.Locale.ENGLISH);
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel lblTitulo = new JLabel();
	JPanel pnlCentro = new JPanel();
	JPanel pnlDatosVenta = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	BorderLayout borderLayout3 = new BorderLayout();
	JPanel PnlFecha = new JPanel();
	JLabel fecha = new JLabel();
	JLabel lblFecha = new JLabel();
	JLabel lblLE = new JLabel();
	JLabel lblLugarExpedicion = new JLabel();
	JPanel pnlDatosArticulos = new JPanel();
	JPanel pnlLeerEtiquetas = new JPanel();
	BorderLayout borderLayout4 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JTextField txtEtiqueta = new JTextField();
	BorderLayout borderLayout5 = new BorderLayout();
	JScrollPane scrollProductos = new JScrollPane();
	JTable tblProductos = new JTable();
	public ModelVentaArticulos modelVentaArticulos1 = new ModelVentaArticulos();
	java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
			"dd/MMM/yyyy hh:mm");
	// InventarioLocalEngine articulo = new InventarioLocalEngine();
	JPanel PnlTotales = new JPanel();
	BorderLayout borderLayout6 = new BorderLayout();
	JPanel PnlDerechaTotales = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel lblTotal = new JLabel();
	JTextField txtTotal = new JTextField();

	JLabel lblLeyenda = new JLabel(); // Mostramos la cajita de recibo/regreso..
	JPanel pnlBottomSouth = new JPanel();
	JPanel PnlCentroFormaPago = new JPanel();
	// JButton cmdAgregarProducto = new JButton();
	JPopupMenu pop = new JPopupMenu();
	JMenuItem jMenuItem1 = new JMenuItem();
	CardLayout cardLayout1 = new CardLayout();
	GridLayout gridLayout3 = new GridLayout();
	JPanel pnlDescuento = new JPanel();
	JButton cmdAplicarDescuentoNota = new JButton();
	BorderLayout borderLayout7 = new BorderLayout();
	JPanel jPanel2 = new JPanel();
	JLabel lblPorcentajeDescuento = new JLabel("");
	JPanel jPanel1 = new JPanel();
	BorderLayout borderLayout8 = new BorderLayout();
	JLabel jLabel4 = new JLabel();
	TitledBorder titledBorder1;
	TitledBorder titledBorder2;
	TitledBorder titledBorder3;
	TitledBorder titledBorder4;
	TitledBorder titledBorder5;
	JLabel jLabel5 = new JLabel();
	JLabel lblVendedor = new JLabel();
	JButton cmdAplicarDescuentoAlProductoSeleccionado = new JButton();
	JButton cmdPagar = new JButton();
	FrmIndicarTipoPago frmIndicarTipoPago = null;

	public PnlVentaContado() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * public void calcularDescuento() { //Sacamos el porcentaje de descuento if
	 * (null.isVisible()) { this.porcentajeDescuento = Double.parseDouble(null.
	 * getSelectedItem().toString()); } else { this.porcentajeDescuento = 0.0; }
	 * this.descuento = (this.porcentajeDescuento / 100) * this.subTotal;
	 * this.total = this.subTotal - this.descuento;
	 * this.txtDescuento.setText(number.format(this.descuento));
	 * this.txtTotal.setText(number.format(this.total)); }
	 */

	void jbInit() throws Exception {
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
		dlgDescuento.setSize(240, 180);
		titledBorder1 = new TitledBorder("");
		titledBorder2 = new TitledBorder("");
		titledBorder3 = new TitledBorder("");
		titledBorder4 = new TitledBorder("");
		titledBorder5 = new TitledBorder("");
		// cmdAgregarProducto.setBackground(new Color(249, 243, 255));

		fecha.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));

		lblTitulo.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		lblTitulo.setMaximumSize(new Dimension(34, 15));
		lblTitulo.setOpaque(false);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/ventaContado.jpg")));

		lblTitulo.setText("");
		this.setBackground(Color.white);
		this.setLayout(borderLayout1);
		pnlCentro.setOpaque(false);
		pnlCentro.setLayout(borderLayout2);
		pnlDatosVenta.setLayout(borderLayout3);
		lblFecha.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblFecha.setText("Fecha: ");
		PnlFecha.setOpaque(false);
		pnlDatosVenta.setOpaque(false);
		lblLE.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblLE.setText(AppInstance.boutique().getLugarExpedicion() + "       ");
		lblLugarExpedicion.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblLugarExpedicion.setText("Lugar de expedición:");
		pnlDatosArticulos.setLayout(borderLayout4);
		jLabel1.setBackground(Color.white);
		jLabel1.setOpaque(true);
		jLabel1.setText("Pase el artículo:");
		pnlLeerEtiquetas.setLayout(borderLayout5);
		tblProductos.setBackground(new Color(255, 240, 248));
		tblProductos.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
		tblProductos.setForeground(Color.black);
		tblProductos.setBorder(titledBorder5);
		tblProductos.setOpaque(true);
		tblProductos.setGridColor(Color.white);
		tblProductos.setModel(modelVentaArticulos1);
		tblProductos.setRowHeight(20);
		tblProductos.setShowHorizontalLines(true);
		tblProductos.setShowVerticalLines(false);
		tblProductos
				.addKeyListener(new PnlVentaContadoEvento_tblProductos_keyAdapter(
						this));
		txtEtiqueta
				.addActionListener(new PnlVentaContadoEvento_txtEtiqueta_actionAdapter(
						this));
		scrollProductos.getViewport().setBackground(Color.white);
		scrollProductos.setOpaque(true);
		PnlTotales.setLayout(borderLayout6);
		PnlDerechaTotales.setLayout(gridLayout1);
		gridLayout1.setColumns(2);
		gridLayout1.setRows(2);
		gridLayout1.setVgap(2);
		lblTotal.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		lblTotal.setVerifyInputWhenFocusTarget(true);
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setText("Total a pagar:");
		txtTotal.setEnabled(false);
		txtTotal.setFont(new java.awt.Font("Dialog", 1, 30));
		txtTotal.setForeground(Color.black);
		txtTotal.setBorder(BorderFactory.createEtchedBorder());
		txtTotal.setDisabledTextColor(Color.black);
		txtTotal.setText("");
		txtTotal.setBackground(Color.white);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		PnlDerechaTotales.setMinimumSize(new Dimension(170, 79));
		PnlDerechaTotales.setOpaque(false);
		PnlDerechaTotales.setPreferredSize(new Dimension(250, 67));
		PnlCentroFormaPago.setLayout(gridLayout3);
		txtEtiqueta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtEtiqueta.setBorder(BorderFactory.createEtchedBorder());
		txtEtiqueta.setCaretColor(Color.black);
		// cmdAgregarProducto.setDoubleBuffered(true);
		// cmdAgregarProducto.setActionCommand("Agregar producto");
		// cmdAgregarProducto.setMnemonic('A');
		// cmdAgregarProducto.setText("Agregar producto");
		// cmdAgregarProducto.addActionListener(new
		// PnlVentaContadoEvento_cmdAgregarProducto_actionAdapter(this));
		jMenuItem1.setText("Cancelar producto");
		gridLayout3.setColumns(2);
		gridLayout3.setRows(1);
		PnlCentroFormaPago.setBackground(Color.white);
		PnlCentroFormaPago.setPreferredSize(new Dimension(190, 98));
		cmdAplicarDescuentoNota.setEnabled(false);
		cmdAplicarDescuentoNota.setFont(new java.awt.Font("Dialog", Font.PLAIN,
				12));
		cmdAplicarDescuentoNota.setText("Aplicar descuento a toda la nota");
		cmdAplicarDescuentoNota
				.addActionListener(new PnlVentaContado_cmdAplicarDescuentoNota_actionAdapter(
						this));

		pnlDescuento.setLayout(borderLayout7);
		jPanel1.setLayout(borderLayout8);
		jLabel4.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/logo.jpg")));
		jLabel4.setText("");
		jPanel1.setBackground(Color.white);
		PnlTotales.setBackground(Color.white);
		pnlDescuento.setBackground(Color.white);
		pnlDescuento.setOpaque(true);
		pnlDescuento.setPreferredSize(new Dimension(141, 35));
		jPanel2.setOpaque(false);
		pnlBottomSouth.setOpaque(false);
		jLabel5.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		jLabel5.setToolTipText("");
		jLabel5.setText("Vendedor:");
		cmdAplicarDescuentoAlProductoSeleccionado.setEnabled(false);
		cmdAplicarDescuentoAlProductoSeleccionado.setFont(new java.awt.Font(
				"Dialog", Font.PLAIN, 12));
		cmdAplicarDescuentoAlProductoSeleccionado
				.setText("Aplicar descuento a producto seleccionado");
		cmdAplicarDescuentoAlProductoSeleccionado
				.addActionListener(new PnlVentaContado_cmdAplicarDescuentoAlProductoSeleccionado_actionAdapter(
						this));
		cmdPagar.setEnabled(false);
		cmdPagar.setMnemonic('P');
		cmdImprimirNota.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
		cmdImprimirNota.setMnemonic('I');

		cmdPagar.setText("PAGAR");
		cmdPagar.addActionListener(new PnlVentaContado_cmdIndicarPago_actionAdapter(
				this));
		lblVendedor.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		cmdImprimirNota.setEnabled(false);
		cmdImprimirNota.setText("IMPRIMIR");
		cmdImprimirNota
				.addActionListener(new PnlVentaContado_cmdImprimirNota_actionAdapter(
						this));
		cmdSalir.setMnemonic('R');
		cmdSalir.setText("SALIR");
		cmdSalir.addActionListener(new PnlVentaContado_cmdSalir_actionAdapter(
				this));
		lblLblCliente.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblLblCliente.setText("Cliente:");
		lblNombreCliente.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblNombreCliente.setText("Público en general");
		jButton1.setText("jButton1");
		cmdBuscarCliente.setText("CLIENTE");
		cmdBuscarCliente
				.addActionListener(new PnlVentaContado_cmdBuscarCliente_actionAdapter(
						this));
		lblTotalProductos.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblTotalProductos.setText("0");
		jLabel6.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel6.setText("Total de productos:");
		jPanel3.setBackground(Color.white);
		pnlFoto.setBackground(Color.white);
		pnlFoto.setMinimumSize(new Dimension(140, 140));
		pnlFoto.setPreferredSize(new Dimension(140, 140));
		jLabel2.setText("Fotografia");
		pnlFotografia1.setMinimumSize(new Dimension(107, 114));
		pnlFotografia1.setPreferredSize(new Dimension(107, 114));
		pnlFotografia1.setBackground(Color.white);
		PnlFecha.add(lblLblCliente);
		PnlFecha.add(lblNombreCliente);

		PnlFecha.add(lblLugarExpedicion, null);
		PnlFecha.add(lblLE, null);
		this.add(pnlCentro, BorderLayout.CENTER);
		pnlCentro.add(pnlDatosVenta, BorderLayout.NORTH);
		PnlFecha.add(lblFecha, null);
		PnlFecha.add(fecha, null);
		PnlFecha.add(jLabel5);
		PnlFecha.add(lblVendedor);
		pnlCentro.add(pnlDatosArticulos, BorderLayout.CENTER);
		pnlLeerEtiquetas.add(jLabel1, BorderLayout.WEST);
		pnlLeerEtiquetas.add(txtEtiqueta, BorderLayout.CENTER); // pnlLeerEtiquetas.add(cmdAgregarProducto,
																// java.awt.BorderLayout.EAST);
		pnlDatosArticulos.add(scrollProductos, BorderLayout.CENTER);
		pnlDatosArticulos.add(PnlTotales, BorderLayout.SOUTH);
		PnlTotales.add(PnlDerechaTotales, BorderLayout.EAST);
		scrollProductos.getViewport().add(tblProductos, null);
		jPanel2.add(cmdAplicarDescuentoNota, null);
		jPanel2.add(cmdAplicarDescuentoAlProductoSeleccionado);
		PnlTotales.add(PnlCentroFormaPago, BorderLayout.CENTER);
		PnlCentroFormaPago.add(pnlDescuento, null);
		this.add(pnlBottomSouth, BorderLayout.SOUTH);
		pnlBottomSouth.add(cmdPagar);
		pnlBottomSouth.add(cmdImprimirNota);
		pnlBottomSouth.add(cmdSalir);
		this.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(lblTitulo, BorderLayout.CENTER);
		jPanel1.add(jLabel4, BorderLayout.EAST);
		PnlDerechaTotales.add(lblTotal, null);
		PnlDerechaTotales.add(txtTotal, null);
		pop.add(jMenuItem1);
		pnlDatosVenta.add(PnlFecha, java.awt.BorderLayout.CENTER);
		pnlLeerEtiquetas.add(cmdBuscarCliente, java.awt.BorderLayout.EAST);
		pnlDescuento.add(jPanel2, java.awt.BorderLayout.CENTER);
		jPanel3.add(jLabel6);
		jPanel3.add(lblTotalProductos);
		pnlDescuento.add(jPanel3, java.awt.BorderLayout.NORTH);
		pnlDatosArticulos.add(pnlLeerEtiquetas, java.awt.BorderLayout.NORTH);
		pnlDatosArticulos.add(pnlFoto, java.awt.BorderLayout.WEST);
		pnlFoto.add(jLabel2);
		pnlFoto.add(pnlFotografia1);
		cmdAplicarDescuentoNota.setMnemonic('D');
		// cmdAgregarProducto.setBorder(null);

		lblVendedor.setText(AppInstance.usuario().getNombre());
		fecha.setText(formato.format(new java.util.Date()));

		txtEtiqueta.requestFocus();
		this.txtTotal.setText(number.format(0.0));
		engine.iniciarVenta(AppInstance.usuario().getId());
		regFacIndEngine.setVenta(engine.getVenta());
		this.modelVentaArticulos1.data = engine.productosView();
		tblProductos.getColumnModel().getColumn(0).setMaxWidth(120);
		tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
		tblProductos.getColumnModel().getColumn(2).setMaxWidth(300);
		tblProductos.getColumnModel().getColumn(3).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(4).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(5).setMaxWidth(250);
	}

	void txtEtiqueta_actionPerformed(ActionEvent e) {
		// Buscamos el articulo

		if (engine.agregarProducto(txtEtiqueta.getText().replace("-", "/"),
				AppInstance.boutique().getId())) {
			this.totalProductos++;
			this.lblTotalProductos.setText(String.valueOf(totalProductos));
			this.modelVentaArticulos1.fireTableDataChanged();
			// Revisamos el total de la nota>0 habilitamos los botones
			txtTotal.setText(AppInstance.number.format(engine.getTotal()));
			if (engine.getTotal() > 0) {
				habilitarBotonesVenta();
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

	void deshabilitarBotonesVenta() {
		this.cmdAplicarDescuentoNota.setEnabled(false);
		this.cmdPagar.setEnabled(false);
		this.cmdAplicarDescuentoAlProductoSeleccionado.setEnabled(false);
		// this.txtEtiqueta.setEditable(false);
		// this.cmdAgregarProducto.setEnabled(false);

	}

	void habilitarBotonesVenta() {
		this.cmdAplicarDescuentoNota.setEnabled(true);
		this.cmdPagar.setEnabled(true);
		this.cmdAplicarDescuentoAlProductoSeleccionado.setEnabled(true);
		this.txtEtiqueta.setEditable(true);
		// this.cmdAgregarProducto.setEnabled(true);
	}

	void cmdAgregarProducto_actionPerformed(ActionEvent e) {
		DlgAgregarInventarioLocal dlgILocal = new DlgAgregarInventarioLocal();
		dlgILocal.setLocationRelativeTo(this.getRootPane());
		dlgILocal.setVisible(true);

		if (dlgILocal.engine.etiqueta() != null) {
			engine.agregarProducto(dlgILocal.engine.etiqueta(), AppInstance
					.boutique().getId());
			this.modelVentaArticulos1.fireTableDataChanged();
			// Revisamos el total de la nota>0 habilitamos los botones
			txtTotal.setText(number.format(engine.getTotal()));
			if (engine.getTotal() > 0) {
				habilitarBotonesVenta();
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

	public void tblProductos_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 27) {
			int i = JOptionPane.showConfirmDialog(this.getRootPane(),
					"¿Esta seguro que desea cancelar el producto?",
					"Cancelar articulo", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				// Quitamos el articulo de la venta
				// this.quitarArticuloDeVenta();
				engine.removerProducto(tblProductos.getSelectedRow());
				this.modelVentaArticulos1.fireTableDataChanged();
				this.totalProductos--;
				this.lblTotalProductos.setText(String.valueOf(totalProductos));

				this.txtTotal.setText(number.format(engine.getTotal()));
				if (engine.productosView().size() == 0) {
					deshabilitarBotonesVenta();
					this.txtEtiqueta.requestFocus();
				}
			}
		}
		System.out.println(e.getKeyCode());
	}

	/**
	 * cancelarDescuento
	 */

	public void cmdAplicarDescuentoAlProductoSeleccionado_actionPerformed(
			ActionEvent e) {
		if (this.tblProductos.getSelectedRow() >= 0) {
			dlgDescuento.descuento = 0;
			dlgDescuento.setLocationRelativeTo(this.getRootPane());
			dlgDescuento.setVisible(true);
			engine.aplicarDescuentoaProductoSeleccionado(
					tblProductos.getSelectedRow(), dlgDescuento.descuento);
			this.modelVentaArticulos1.fireTableDataChanged();
			this.txtTotal.setText(number.format(engine.getTotal()));
		} else {
			JOptionPane.showMessageDialog(this.getRootPane(),
					"No ha seleccionado ningún producto",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.WARNING_MESSAGE);
			;
		}
	}

	public void cmdAplicarDescuentoNota_actionPerformed(ActionEvent e) {
		if (this.tblProductos.getRowCount() > 0) {
			dlgDescuento.descuento = 0;
			dlgDescuento.setLocationRelativeTo(this.getRootPane());
			dlgDescuento.setVisible(true);
			engine.aplicarDescuentoaNota(dlgDescuento.descuento);
			this.modelVentaArticulos1.fireTableDataChanged();
			this.txtTotal.setText(number.format(engine.getTotal()));
		} else {
			JOptionPane.showMessageDialog(this.getRootPane(),
					"No hay productos en la nota de venta",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.WARNING_MESSAGE);
			;
		}
	}

	public void cmdIndicarPago_actionPerformed(ActionEvent e) {
		if (cmdPagar.isEnabled()) {
			this.deshabilitarBotonesVenta();
			// Indicamos el tipo de pago en este caso es pago de contado, se
			// muestran todos los tipos de pago posibles.
			frmIndicarTipoPago = new FrmIndicarTipoPago(1, engine.getTotal(),
					false, true);
			frmIndicarTipoPago.setSize(400, 400);
			frmIndicarTipoPago.setLocationRelativeTo(this.getRootPane());

			frmIndicarTipoPago.setVisible(true);
			// Tenemos los pagos, le ponemos los datos correspondientes a la
			// nota que es de contado
			if (frmIndicarTipoPago.engine.getMontoPendiente() < 0.05) {
				this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
				this.engine.agregarPagos(frmIndicarTipoPago.engine.getPagos()); // frmIndicarTipoPago.engine;
				this.dlgFacReq.setLocationRelativeTo(this.getRootPane());
				this.dlgFacReq.setVisible(true);
				this.engine.setCliente(this.regFacIndEngine.getCliente());
				if (this.engine.registrarVenta()) {
					engine.imprimirNota();
					this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
					JOptionPane.showMessageDialog(null, "Venta realizada",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.INFORMATION_MESSAGE);
					this.cmdImprimirNota.setEnabled(true);
					// this.firePropertyChange("cerrar", false, true);

				} else {
					this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
					JOptionPane.showMessageDialog(null, "Venta no realizada",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
					this.habilitarBotonesVenta();
				}
			} else {
				this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
				JOptionPane.showMessageDialog(null,
						"Los pagos indicados no cubren el total de la venta",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				this.habilitarBotonesVenta();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	transient Vector propertyChangeListeners;
	BorderLayout borderLayout9 = new BorderLayout();
	JButton cmdImprimirNota = new JButton();
	JButton cmdSalir = new JButton();
	JLabel lblNombreCliente = new JLabel();
	JLabel lblLblCliente = new JLabel();
	JButton jButton1 = new JButton();
	JButton cmdBuscarCliente = new JButton();
	JPanel jPanel3 = new JPanel();
	JLabel lblTotalProductos = new JLabel();
	JLabel jLabel6 = new JLabel();
	JPanel pnlFoto = new JPanel();
	JLabel jLabel2 = new JLabel();
	PnlFotografia pnlFotografia1 = new PnlFotografia();

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
	protected void firePropertyChange(PropertyChangeEvent e) {
		if (propertyChangeListeners != null) {
			Vector listeners = propertyChangeListeners;
			int count = listeners.size();
			for (int i = 0; i < count; i++) {
				((PropertyChangeListener) listeners.elementAt(i))
						.propertyChange(e);
			}
		}
	}

	public void cmdImprimirNota_actionPerformed(ActionEvent e) {
		engine.imprimirNota();
	}

	public void cmdSalir_actionPerformed(ActionEvent e) {
		this.firePropertyChange("cerrar", null, null);

	}

	public void cmdBuscarCliente_actionPerformed(ActionEvent e) {
		FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(false);
		frmBuscarCliente.setModal(true);
		frmBuscarCliente.setSize(900, 600);
		frmBuscarCliente.setLocationRelativeTo(this);
		frmBuscarCliente.setVisible(true);
		if (frmBuscarCliente.cliente != null) {
			engine.setCliente(frmBuscarCliente.cliente);
			this.lblNombreCliente.setText(engine.getCliente().getNombre() + " "
					+ engine.getCliente().getApellidoPaterno() + " "
					+ engine.getCliente().getApellidoMaterno());
			this.pnlFotografia1.setImagen(engine.getCliente().getFotografia());
			this.regFacIndEngine.setCliente(engine.getCliente());
		}
		this.txtEtiqueta.requestFocus();
	}

}

class PnlVentaContado_cmdBuscarCliente_actionAdapter implements ActionListener {
	private PnlVentaContado adaptee;

	PnlVentaContado_cmdBuscarCliente_actionAdapter(PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscarCliente_actionPerformed(e);
	}
}

class PnlVentaContado_cmdSalir_actionAdapter implements ActionListener {
	private PnlVentaContado adaptee;

	PnlVentaContado_cmdSalir_actionAdapter(PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalir_actionPerformed(e);
	}
}

class PnlVentaContado_cmdImprimirNota_actionAdapter implements ActionListener {
	private PnlVentaContado adaptee;

	PnlVentaContado_cmdImprimirNota_actionAdapter(PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdImprimirNota_actionPerformed(e);
	}
}

class PnlVentaContado_cmdIndicarPago_actionAdapter implements ActionListener {
	private PnlVentaContado adaptee;

	PnlVentaContado_cmdIndicarPago_actionAdapter(PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdIndicarPago_actionPerformed(e);
	}
}

class PnlVentaContado_cmdAplicarDescuentoAlProductoSeleccionado_actionAdapter
		implements ActionListener {
	private PnlVentaContado adaptee;

	PnlVentaContado_cmdAplicarDescuentoAlProductoSeleccionado_actionAdapter(
			PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAplicarDescuentoAlProductoSeleccionado_actionPerformed(e);
	}
}

class PnlVentaContado_cmdAplicarDescuentoNota_actionAdapter implements
		ActionListener {
	private PnlVentaContado adaptee;

	PnlVentaContado_cmdAplicarDescuentoNota_actionAdapter(
			PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAplicarDescuentoNota_actionPerformed(e);
	}
}

class PnlVentaContadoEvento_tblProductos_keyAdapter extends KeyAdapter {
	private PnlVentaContado adaptee;

	PnlVentaContadoEvento_tblProductos_keyAdapter(PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.tblProductos_keyPressed(e);
	}
}

class PnlVentaContadoEvento_txtEtiqueta_actionAdapter implements
		java.awt.event.ActionListener {
	PnlVentaContado adaptee;

	PnlVentaContadoEvento_txtEtiqueta_actionAdapter(PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtEtiqueta_actionPerformed(e);
	}
}

class PnlVentaContadoEvento_cmdAgregarProducto_actionAdapter implements
		java.awt.event.ActionListener {
	PnlVentaContado adaptee;

	PnlVentaContadoEvento_cmdAgregarProducto_actionAdapter(
			PnlVentaContado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarProducto_actionPerformed(e);
	}
}
