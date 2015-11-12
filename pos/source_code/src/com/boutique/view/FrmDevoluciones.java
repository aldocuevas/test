package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import org.jfree.date.DateUtilities;

import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

import com.boutique.engine.impl.*;
import com.boutique.domain.*;

import static com.boutique.engine.impl.AppInstance.*;

import com.boutique.dao.DaoClientesCentral;
import com.boutique.dao.DaoSource;

public class FrmDevoluciones extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	DlgIndicarIdVenta dlg = new DlgIndicarIdVenta();
	DevolucionDeProductosEngine engine = new DevolucionDeProductosEngine();

	JPanel pnlNorte = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel pnlEncabezado = new JPanel();
	JPanel pnlDatosCompra = new JPanel();
	JPanel pnlDatosCliente = new JPanel();
	JPanel pnlSur = new JPanel();
	JPanel pnlCentro = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	JSplitPane splitPagosProductos = new JSplitPane();
	JPanel pnlProductos = new JPanel();
	BorderLayout borderLayout4 = new BorderLayout();
	JPanel pnlPagos = new JPanel();
	JLabel jLabel1 = new JLabel();
	JScrollPane scrollProductos = new JScrollPane();
	JTable tblProductos = new JTable();
	BorderLayout borderLayout5 = new BorderLayout();
	JLabel jLabel2 = new JLabel();
	JScrollPane scollPagos = new JScrollPane();
	JTable tblPagos = new JTable();
	JPanel pnlProductosSur = new JPanel();
	BorderLayout borderLayout6 = new BorderLayout();
	JPanel pnlTotaleProductos = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel3 = new JLabel();
	JTextField txtTotalVenta = new JTextField();
	JPanel pnlPagosSur = new JPanel();
	JPanel pnlPagosTotales = new JPanel();
	BorderLayout borderLayout7 = new BorderLayout();
	GridLayout gridLayout2 = new GridLayout();
	JLabel jLabel4 = new JLabel();
	JTextField txtMontoaPagar = new JTextField();
	JLabel jLabel5 = new JLabel();
	JTextField txtMontoPagado = new JTextField();
	JButton cmdDevolverProductoSeleccionado = new JButton();
	JLabel jLabel6 = new JLabel();
	JLabel lblFechaCompra = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel lblNoVenta = new JLabel();
	JLabel jLabel10 = new JLabel();
	GridLayout gridLayout3 = new GridLayout();
	JLabel jLabel11 = new JLabel();
	JTextField txtTelefono = new JTextField();
	JLabel jLabel12 = new JLabel();
	JTextField txtDomicilio = new JTextField();
	JLabel jLabel13 = new JLabel();
	JTextField txtNombreCliente = new JTextField();
	JLabel lblVendedor = new JLabel();
	JButton cmdCancelarApartado = new JButton();
	JButton cmdTerminar = new JButton();
	JLabel lblImagen = new JLabel();
	BorderLayout borderLayout8 = new BorderLayout();
	JLabel lblDevoluciones = new JLabel();
	ModelVentaArticulosApartado modelVentaArticulos1 = new ModelVentaArticulosApartado();
	ModelPagosRealizados modelPagosRealizados1 = new ModelPagosRealizados();
	javax.swing.JLabel jLabel7 = new JLabel();
	javax.swing.JLabel lblSucursal = new JLabel();
	PnlFotografia pnlFotografia1 = new PnlFotografia();
	javax.swing.JPanel jPanel1 = new JPanel();
	javax.swing.JLabel jLabel9 = new JLabel();
	javax.swing.JPanel jPanel2 = new JPanel();
	java.awt.BorderLayout borderLayout9 = new BorderLayout();

	public FrmDevoluciones() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setTitle("DEVOLUCIONES");
		getContentPane().setLayout(borderLayout1);
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pnlCentro.setBackground(Color.white);
		pnlCentro.setLayout(borderLayout3);
		pnlProductos.setLayout(borderLayout4);
		lblImagen.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/logo.jpg")));
		splitPagosProductos.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPagosProductos.setPreferredSize(new Dimension(200, 300));
		jLabel1.setBackground(new java.awt.Color(255, 152, 228));
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
		lblDevoluciones.setBackground(Color.white);
		lblDevoluciones.setOpaque(true);
		lblDevoluciones.setIcon(new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/devoluciones.jpg")));
		jLabel1.setText("Productos registrados:");
		pnlPagos.setLayout(borderLayout5);
		jLabel2.setBackground(Color.white);
		jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
		jLabel2.setOpaque(true);
		jLabel2.setText("Pagos realizados:");
		pnlProductosSur.setLayout(borderLayout6);
		pnlTotaleProductos.setLayout(gridLayout1);
		jLabel3.setBackground(Color.white);
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel3.setOpaque(true);
		jLabel3.setText("Total de la nota:");
		txtTotalVenta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtTotalVenta.setText("0.00");
		txtTotalVenta.setHorizontalAlignment(SwingConstants.TRAILING);
		pnlPagosSur.setLayout(borderLayout7);
		pnlPagosTotales.setLayout(gridLayout2);
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("Total abonado:");
		txtMontoaPagar.setEnabled(false);
		txtMontoaPagar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtMontoaPagar.setForeground(Color.red);
		txtMontoaPagar.setOpaque(false);
		txtMontoaPagar.setCaretColor(Color.black);
		txtMontoaPagar.setDisabledTextColor(Color.black);
		txtMontoaPagar.setText("0.00");
		txtMontoaPagar.setHorizontalAlignment(SwingConstants.TRAILING);
		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel5.setHorizontalAlignment(SwingConstants.TRAILING);
		jLabel5.setText("Total a pagar:");
		txtMontoPagado.setEnabled(false);
		txtMontoPagado.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtMontoPagado.setOpaque(false);
		txtMontoPagado.setDisabledTextColor(Color.black);
		txtMontoPagado.setText("0.00");
		txtMontoPagado.setHorizontalAlignment(SwingConstants.TRAILING);
		gridLayout2.setColumns(2);
		gridLayout2.setRows(2);
		pnlPagosSur.setBackground(Color.white);
		pnlPagosTotales.setBackground(Color.white);
		cmdDevolverProductoSeleccionado.setEnabled(false);
		cmdDevolverProductoSeleccionado.setFont(new java.awt.Font("Dialog",
				Font.PLAIN, 16));
		cmdDevolverProductoSeleccionado.setMnemonic('P');
		cmdDevolverProductoSeleccionado.setText("Devolver producto");
		cmdDevolverProductoSeleccionado
				.addActionListener(new FrmDevolucionContadoApartado_cmdDevolverProductoSeleccionado_actionAdapter(
						this));
		jLabel6.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel6.setText("Tipo de venta:");
		lblFechaCompra.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblFechaCompra.setText("");
		jLabel8.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel8.setText("Fecha de compra:");
		lblNoVenta.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblNoVenta.setText("");
		jLabel10.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel10.setText("No. de venta:");
		pnlDatosCliente.setLayout(gridLayout3);
		jLabel11.setToolTipText("");
		jLabel11.setText("Nombre del cliente:");
		txtTelefono.setEnabled(false);
		txtTelefono.setOpaque(false);
		txtTelefono.setText("");
		jLabel12.setText("Telefono:");
		txtDomicilio.setEnabled(false);
		txtDomicilio.setOpaque(false);
		txtDomicilio.setText("");
		jLabel13.setText("Domicilio:");
		txtNombreCliente.setEnabled(false);
		txtNombreCliente.setOpaque(false);
		txtNombreCliente.setText("");
		lblVendedor.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblVendedor.setText("");
		pnlDatosCliente.setBackground(Color.white);
		pnlSur.setBackground(Color.white);
		cmdCancelarApartado
				.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		cmdCancelarApartado.setText("Cancelar toda la venta");
		cmdCancelarApartado
				.addActionListener(new FrmDevolucionContadoApartado_cmdCancelarApartado_actionAdapter(
						this));

		pnlNorte.setLayout(borderLayout2);
		cmdTerminar.setEnabled(false);
		cmdTerminar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		cmdTerminar.setActionCommand("");
		cmdTerminar.setText("Confirmar devolución");
		cmdTerminar
				.addActionListener(new FrmDevolucionContadoApartado_cmdTerminar_actionAdapter(
						this));
		lblImagen.setText("");
		pnlEncabezado.setLayout(borderLayout8);
		lblDevoluciones.setText("");
		pnlEncabezado.setBackground(Color.white);
		tblProductos.setModel(modelVentaArticulos1);
		tblProductos
				.addMouseListener(new FrmDevolucionContadoApartado_tblProductos_mouseAdapter(
						this));
		tblPagos.setModel(modelPagosRealizados1);
		tblPagos.addKeyListener(new FrmDevolucionContadoApartado_tblPagos_keyAdapter(
				this));
		pnlDatosCompra.setBackground(Color.white);
		scrollProductos.getViewport().setBackground(Color.white);
		pnlProductos.setBackground(Color.white);
		pnlProductosSur.setBackground(Color.white);
		scollPagos.getViewport().setBackground(Color.white);
		jLabel7.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		jLabel7.setText("Sucursal:");
		lblSucursal.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		lblSucursal.setText("");
		pnlFotografia1.setBackground(Color.white);
		pnlFotografia1.setMinimumSize(new Dimension(106, 110));
		pnlFotografia1.setPreferredSize(new Dimension(106, 110));
		jPanel1.setBackground(Color.white);
		jPanel1.setMinimumSize(new Dimension(140, 140));
		jPanel1.setPreferredSize(new Dimension(140, 140));
		jLabel9.setText("Fotografia");
		jPanel2.setLayout(borderLayout9);
		pnlNorte.add(pnlEncabezado, java.awt.BorderLayout.NORTH);
		pnlNorte.add(pnlDatosCliente, java.awt.BorderLayout.SOUTH);
		pnlNorte.add(pnlDatosCompra, java.awt.BorderLayout.CENTER);
		pnlDatosCompra.add(jLabel10);
		pnlDatosCompra.add(lblNoVenta);
		pnlDatosCompra.add(jLabel8);
		pnlDatosCompra.add(lblFechaCompra);
		pnlDatosCompra.add(jLabel6);
		pnlDatosCompra.add(lblVendedor);
		pnlDatosCompra.add(jLabel7);
		pnlDatosCompra.add(lblSucursal);
		pnlDatosCliente.add(jLabel11);
		pnlDatosCliente.add(txtNombreCliente);
		pnlDatosCliente.add(jLabel13);
		pnlDatosCliente.add(txtDomicilio);
		pnlDatosCliente.add(jLabel12);
		pnlDatosCliente.add(txtTelefono);
		this.getContentPane().add(pnlSur, java.awt.BorderLayout.SOUTH);
		pnlSur.add(cmdDevolverProductoSeleccionado);
		pnlSur.add(cmdCancelarApartado);
		pnlSur.add(cmdTerminar);
		this.getContentPane().add(pnlNorte, java.awt.BorderLayout.NORTH);
		this.getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);

		pnlCentro.add(splitPagosProductos, java.awt.BorderLayout.CENTER);
		pnlPagos.add(scollPagos, java.awt.BorderLayout.CENTER);
		scollPagos.getViewport().add(tblPagos);
		pnlProductos.add(scrollProductos, java.awt.BorderLayout.CENTER);
		pnlProductos.add(jLabel1, java.awt.BorderLayout.NORTH);
		pnlProductos.add(pnlProductosSur, java.awt.BorderLayout.SOUTH);
		pnlProductosSur.add(pnlTotaleProductos, java.awt.BorderLayout.EAST);
		scrollProductos.getViewport().add(tblProductos);

		pnlTotaleProductos.add(jLabel3);
		pnlTotaleProductos.add(txtTotalVenta);
		pnlPagos.add(jLabel2, java.awt.BorderLayout.NORTH);
		pnlPagos.add(pnlPagosSur, java.awt.BorderLayout.SOUTH);
		pnlPagosSur.add(pnlPagosTotales, java.awt.BorderLayout.EAST);
		pnlPagosTotales.add(jLabel4);
		pnlPagosTotales.add(txtMontoPagado);
		pnlPagosTotales.add(jLabel5);
		pnlPagosTotales.add(txtMontoaPagar);
		splitPagosProductos.add(jPanel2, JSplitPane.TOP);
		pnlEncabezado.add(lblImagen, java.awt.BorderLayout.EAST);
		pnlEncabezado.add(lblDevoluciones, java.awt.BorderLayout.WEST);
		jPanel1.add(jLabel9);
		jPanel1.add(pnlFotografia1);
		splitPagosProductos.add(pnlPagos, JSplitPane.BOTTOM);
		jPanel2.add(pnlProductos, java.awt.BorderLayout.CENTER);
		jPanel2.add(jPanel1, java.awt.BorderLayout.WEST);
		tblProductos.getColumnModel().getColumn(0).setMaxWidth(120);
		tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
		tblProductos.getColumnModel().getColumn(2).setMaxWidth(300);
		tblProductos.getColumnModel().getColumn(3).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(4).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(5).setMaxWidth(250);
		tblProductos.getColumnModel().getColumn(6).setMaxWidth(250);

		this.addWindowListener(new FrmDevolucionContadoApartado_this_windowAdapter(
				this));
		splitPagosProductos.setDividerLocation(300);
	}

	public void this_windowOpened(WindowEvent e) {
		dlg.setSize(260, 200);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		if (dlg.idVenta == 0) {
			this.setVisible(false);
		} else {
			this.setCursor(AppInstance.waitCursor);
			if (engine.buscarPorNoVenta(dlg.idVenta, true)) {
				Venta venta = engine.getVenta();
				// Check Sale's date and compare it with current date to see how
				// old the sale is.
				if (engine.ventaSePuedeDevolver(venta)) {
					this.lblSucursal.setText(AppInstance.idToNombreBoutique
							.get(venta.getIdBoutique()).toString());
					if (venta instanceof VentaApartado) {

						VentaApartado va = (VentaApartado) venta;
						this.lblNoVenta.setText(String.valueOf(va.getId()));
						this.lblFechaCompra.setText(AppInstance.formatoCorto
								.format(va.getFecha()));
						this.lblVendedor.setText("APARTADO");
						this.txtNombreCliente.setText(va.getCliente());
						this.txtDomicilio.setText(va.getDomicilio());
						this.txtTelefono.setText(va.getTelefono());

					} else if (venta instanceof VentaCredito) {
						VentaCredito vc = (VentaCredito) venta;
						// BUSCAMOS EL CLIENTE
						Cliente cliente = com.boutique.dao.DaoClientesSucursal
								.findById(vc.getIdCliente());
						this.lblNoVenta.setText(String.valueOf(vc.getId()));
						this.lblFechaCompra.setText(AppInstance.formatoCorto
								.format(vc.getFecha()));
						this.lblVendedor.setText("CREDITO");
						this.txtNombreCliente.setText(cliente.getNombre() + " "
								+ cliente.getApellidoPaterno() + " "
								+ cliente.getApellidoMaterno());

						this.txtDomicilio
								.setText(cliente.getCalle()
										+ " No."
										+ cliente.getNumero()
										+ ((cliente.getNumeroInterior() == null || cliente
												.getNumeroInterior().equals("")) ? ""
												: " Int. "
														+ cliente
																.getNumeroInterior())
										+ " Col." + cliente.getColonia());

						this.txtTelefono.setText(cliente.getTelefono());

					} else if (venta instanceof Venta) {
						Cliente cliente = com.boutique.dao.DaoClientesSucursal
								.findById(venta.getIdCliente());
						if (cliente != null) {
							this.txtNombreCliente.setText(cliente.getNombre()
									+ " " + cliente.getApellidoPaterno() + " "
									+ cliente.getApellidoMaterno());

							this.txtDomicilio
									.setText(cliente.getCalle()
											+ " No."
											+ cliente.getNumero()
											+ ((cliente.getNumeroInterior() == null || cliente
													.getNumeroInterior()
													.equals("")) ? ""
													: " Int. "
															+ cliente
																	.getNumeroInterior())
											+ " Col." + cliente.getColonia());

							this.txtTelefono.setText(cliente.getTelefono());
						} else {
							this.txtNombreCliente.setText("PUBLICO EN GENERAL");
						}
						this.lblNoVenta.setText(String.valueOf(venta.getId()));
						this.lblFechaCompra.setText(AppInstance.formatoCorto
								.format(venta.getFecha()));
						this.lblVendedor.setText("CONTADO");
						cliente = null;

					}

					this.pnlFotografia1.setImagen(DaoClientesCentral
							.findFoto(venta.getIdCliente()));
					this.modelVentaArticulos1.data = engine.productosView();
					this.modelVentaArticulos1.fireTableDataChanged();
					// Ponemos los productos vendidos
					this.txtTotalVenta.setText(AppInstance.number.format(engine
							.getTotal()));
					// Ponemos los pagos realizados
					this.modelPagosRealizados1.data = engine.pagosView();
					this.modelPagosRealizados1.fireTableDataChanged();
					this.txtMontoPagado.setText(number.format(engine
							.getAbonado()));
					this.txtMontoaPagar.setText(number.format(engine
							.getTotalaPagar()));
					this.setCursor(AppInstance.defCursor);
				} else {
				     this.setCursor(AppInstance.defCursor);
				        JOptionPane.showMessageDialog(this, "El plazo máximo para realizar una devolución es de " + engine.getDiasPermitidosParaDevolucion() + " días, Devolución no permitida",
				                                      com.boutique.engine.impl.AppInstance.
				                                      nombreNegocio,
				                                      JOptionPane.ERROR_MESSAGE);
				        this.setVisible(false);
				   
				}
			} else {
				this.setCursor(AppInstance.defCursor);
				JOptionPane.showMessageDialog(this,
						"La venta no fue encontrada",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
			}
		}
	}

	public void cmdDevolverProductoSeleccionado_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		Object[] producto = (Object[]) engine.productosView().get(
				tblProductos.getSelectedRow());
		if (producto != null && producto[6].toString().indexOf("DEVUELTO") >= 0) {
			this.setCursor(AppInstance.defCursor);
			JOptionPane.showMessageDialog(this, "EL PRODUCTO YA ESTA DEVUELTO",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (this.tblProductos.getSelectedRow() >= 0
				&& cmdDevolverProductoSeleccionado.isEnabled()) {
			engine.devolverProducto(this.tblProductos.getSelectedRow());
			this.modelVentaArticulos1.fireTableDataChanged();
			this.cmdTerminar.setEnabled(true);
			this.txtTotalVenta.setText(number.format(engine.getTotal()));
			this.txtMontoaPagar.setText(number.format(engine.getTotalaPagar()));
		}
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdTerminar_actionPerformed(java.awt.event.ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		if (engine.productosADevolver.size() == 0) {
			this.setCursor(AppInstance.defCursor);
			JOptionPane.showMessageDialog(this,
					"NO EXISTEN PRODUCTOS A DEVOLVER",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (cmdTerminar.isEnabled()) {
			// ANTES DE TERMINAR DEBE REVISARSE SI LA VENTA Y LOS PAGOS ESTAN O
			// NO EN CORTE
			// SI NO ESTAN EN CORTE.. SE PUEDE CANCELAR LA NOTA SIN GENERAR EL
			// VALE. DE ESTA FORMA NUNCA SE RECOGIO EL DINERO DE LOS PAGOS DADO
			// QUE QUEDAN CANCELADOS
			// Y LA VENTA QUEDA COMO DEVOLUCION
			// SI YA ESTA EN CORTE.. SE SIGUE EL PROCEDIMIENTO NORMAL
			if (engine.terminar()) {
				if (engine.vale() != null) {
					Vale vale = engine.vale();
					// Mostramos el vale de compra
					DlgMostrarVale dlg = new DlgMostrarVale();
					dlg.lblMonto.setText("$" + number.format(vale.getMonto()));
					dlg.lblNoVale.setText(String.valueOf(vale.getId()));
					dlg.setSize(193, 235);
					dlg.setLocationRelativeTo(this.getRootPane());
					dlg.setModal(true);
					engine.imprimirVale();
					dlg.setVisible(true);
					// IMPRIMIMOS EL VALE DE COMPRA

				}
				// IMPRIMIMOS LA DEVOLUCION.

				engine.buscarPorNoVenta(dlg.idVenta, false);

				try {
					engine.imprimirDevolucion();
				} catch (Exception ex) {
					this.setCursor(AppInstance.defCursor);
					JOptionPane
							.showMessageDialog(
									this,
									"NO SE PUDO REALIZAR LA IMPRESION, VERIFIQUE EL ESTADO DE LA IMPRESORA",
									com.boutique.engine.impl.AppInstance.nombreNegocio,
									JOptionPane.ERROR_MESSAGE);

				}
				this.setCursor(AppInstance.defCursor);
				JOptionPane.showMessageDialog(this,
						"Devolucion realizada exitosamente",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				this.setCursor(AppInstance.defCursor);
				JOptionPane.showMessageDialog(this,
						"La devolucion no se pudo realizar",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.ERROR_MESSAGE);

			}

			this.setVisible(false);
			this.setCursor(AppInstance.defCursor);
		}
	}

	public void cmdCancelarApartado_actionPerformed(java.awt.event.ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		Object[] producto = (Object[]) engine.productosView().get(0);
		while (this.tblProductos.getRowCount() > 0
				&& !(producto[6].toString().indexOf("DEVUELTO") >= 0)) {
			engine.devolverProducto(0);
			if (engine.productosView().size() > 0) {
				producto = (Object[]) engine.productosView().get(0);
			}
		}
		this.modelVentaArticulos1.fireTableDataChanged();
		this.txtTotalVenta.setText(number.format(engine.getTotal()));
		this.cmdTerminar.setEnabled(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void tblProductos_mouseClicked(java.awt.event.MouseEvent e) {
		if (tblProductos.getSelectedRow() >= 0) {
			this.cmdDevolverProductoSeleccionado.setEnabled(true);
		}
	}

	public void tblPagos_keyPressed(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == 27) { // REVISAR SI SE PRESIONO EL ESCAPE
			if (AppInstance.usuario().getEliminarPagos() == 1) { // SI FUE ASI,
																	// REVISAR
																	// SI EL
																	// USUARIO
																	// TIENE
																	// PERMISOS
																	// PARA
																	// ELIMINAR
				int i = JOptionPane.showConfirmDialog(this,
						"¿ESTAS SEGURO(A) QUE DESEAS ELIMINAR EL PAGO?",
						AppInstance.nombreNegocio, JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					// SI TIENE PERMISOS PARA ELIMINAR EL ABONO, SE REGISTRA LA
					// ELIMINACION DEL ABONO

					Object[] row = (Object[]) modelPagosRealizados1.data
							.get(tblPagos.getSelectedRow());
					String razon = JOptionPane.showInputDialog(this,
							"Indica la razon por la cual se cancela el pago",
							AppInstance.nombreNegocio,
							JOptionPane.QUESTION_MESSAGE);
					if (razon != null) {
						int movimiento = com.boutique.dao.DaoVentas
								.cancelarAbono(
										Integer.parseInt(row[0].toString()),
										razon);
						if (movimiento > 0) {
							JOptionPane.showMessageDialog(this,
									"EL PAGO HA SIDO ELIMINADO, NO. DE MOVIMIENTO"
											+ movimiento,
									AppInstance.nombreNegocio,
									JOptionPane.INFORMATION_MESSAGE);

							engine.buscarPorNoVenta(dlg.idVenta, false);
							Venta venta = engine.getVenta();
							this.lblSucursal
									.setText(AppInstance.idToNombreBoutique
											.get(venta.getIdBoutique())
											.toString());
							if (venta instanceof VentaApartado) {
								VentaApartado va = (VentaApartado) venta;
								this.lblNoVenta.setText(String.valueOf(va
										.getId()));
								this.lblFechaCompra
										.setText(AppInstance.formatoCorto
												.format(va.getFecha()));
								this.lblVendedor.setText("APARTADO");
								this.txtNombreCliente.setText(va.getCliente());
								this.txtDomicilio.setText(va.getDomicilio());
								this.txtTelefono.setText(va.getTelefono());

							} else if (venta instanceof VentaCredito) {
								VentaCredito vc = (VentaCredito) venta;
								// BUSCAMOS EL CLIENTE
								Cliente cliente = com.boutique.dao.DaoClientesSucursal
										.findById(vc.getIdCliente());
								this.lblNoVenta.setText(String.valueOf(vc
										.getId()));
								this.lblFechaCompra
										.setText(AppInstance.formatoCorto
												.format(vc.getFecha()));
								this.lblVendedor.setText("CREDITO");
								this.txtNombreCliente.setText(cliente
										.getNombre()
										+ " "
										+ cliente.getApellidoPaterno()
										+ " "
										+ cliente.getApellidoMaterno());

								this.txtDomicilio
										.setText(cliente.getCalle()
												+ " No."
												+ cliente.getNumero()
												+ ((cliente.getNumeroInterior() == null || cliente
														.getNumeroInterior()
														.equals("")) ? ""
														: " Int. "
																+ cliente
																		.getNumeroInterior())
												+ " Col."
												+ cliente.getColonia());

								this.txtTelefono.setText(cliente.getTelefono());

							} else if (venta instanceof Venta) {
								this.lblNoVenta.setText(String.valueOf(venta
										.getId()));
								this.lblFechaCompra
										.setText(AppInstance.formatoCorto
												.format(venta.getFecha()));
								this.lblVendedor.setText("CONTADO");
								this.txtNombreCliente
										.setText("PUBLICO EN GENERAL");

							}
							this.modelVentaArticulos1.data = engine
									.productosView();
							this.modelVentaArticulos1.fireTableDataChanged();
							// Ponemos los productos vendidos
							this.txtTotalVenta.setText(AppInstance.number
									.format(engine.getTotal()));
							// Ponemos los pagos realizados
							this.modelPagosRealizados1.data = engine
									.pagosView();
							this.modelPagosRealizados1.fireTableDataChanged();
							this.txtMontoPagado.setText(number.format(engine
									.getAbonado()));
							this.txtMontoaPagar.setText(number.format(engine
									.getTotalaPagar()));

						}
						// Y SE ELIMINA EL ABONO DE LA BASE DE DATOS
					}
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"UD. NO ESTA AUTORIZADO A ELIMINAR ABONOS",
						AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}

class FrmDevolucionContadoApartado_tblPagos_keyAdapter extends
		java.awt.event.KeyAdapter {
	private FrmDevoluciones adaptee;

	FrmDevolucionContadoApartado_tblPagos_keyAdapter(
			FrmDevoluciones adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		adaptee.tblPagos_keyPressed(e);
	}
}

class FrmDevolucionContadoApartado_tblProductos_mouseAdapter extends
		java.awt.event.MouseAdapter {
	private FrmDevoluciones adaptee;

	FrmDevolucionContadoApartado_tblProductos_mouseAdapter(
			FrmDevoluciones adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
		adaptee.tblProductos_mouseClicked(e);
	}
}

class FrmDevolucionContadoApartado_cmdCancelarApartado_actionAdapter implements
		java.awt.event.ActionListener {
	private FrmDevoluciones adaptee;

	FrmDevolucionContadoApartado_cmdCancelarApartado_actionAdapter(
			FrmDevoluciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		adaptee.cmdCancelarApartado_actionPerformed(e);
	}
}

class FrmDevolucionContadoApartado_cmdTerminar_actionAdapter implements
		java.awt.event.ActionListener {
	private FrmDevoluciones adaptee;

	FrmDevolucionContadoApartado_cmdTerminar_actionAdapter(
			FrmDevoluciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		adaptee.cmdTerminar_actionPerformed(e);
	}
}

class FrmDevolucionContadoApartado_cmdDevolverProductoSeleccionado_actionAdapter
		implements ActionListener {
	private FrmDevoluciones adaptee;

	FrmDevolucionContadoApartado_cmdDevolverProductoSeleccionado_actionAdapter(
			FrmDevoluciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.cmdDevolverProductoSeleccionado_actionPerformed(e);
	}
}

class FrmDevolucionContadoApartado_this_windowAdapter extends WindowAdapter {
	private FrmDevoluciones adaptee;

	FrmDevolucionContadoApartado_this_windowAdapter(
			FrmDevoluciones adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}
