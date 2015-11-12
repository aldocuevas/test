package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.boutique.domain.InterfaceGrafica;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.SistemaCreditoEngine;

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

public class PnlDatosVenta extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SistemaCreditoEngine engine;

	BorderLayout borderLayout1 = new BorderLayout();
	JLabel lblTitulo = new JLabel();
	JPanel PnlDatosVenta = new JPanel();
	JPanel pnlDatosVentaNorte = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	BorderLayout borderLayout3 = new BorderLayout();
	JPanel pnlCliente = new JPanel();
	BorderLayout borderLayout4 = new BorderLayout();
	JPanel pnlDatosCliente = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel6 = new JLabel();
	JTextField txtBoutique = new JTextField();
	JTextField txtVendedor = new JTextField();
	JLabel jLabel9 = new JLabel();
	JTextField txtNoVenta = new JTextField();
	JLabel jLabel10 = new JLabel();
	JTextField txtFecha = new JTextField();
	JLabel jLabel11 = new JLabel();
	JPanel PnlArticulosDEscuentosPAgos = new JPanel();
	BorderLayout borderLayout5 = new BorderLayout();
	JPanel PnlArticulos = new JPanel();
	BorderLayout borderLayout6 = new BorderLayout();
	JLabel jLabel5 = new JLabel();
	JScrollPane scrollArticulos = new JScrollPane();
	JTable tblArticulos = new JTable();
	JPanel pnlTotalesSubtotales = new JPanel();
	BorderLayout borderLayout7 = new BorderLayout();
	JPanel pnlDerechaTotalesSubtotales = new JPanel();
	GridLayout gridLayout2 = new GridLayout();
	JLabel jLabel7 = new JLabel();
	JTextField txtTotal = new JFormattedTextField();
	JLabel jLabel13 = new JLabel();
	JTextField txtAnticipo = new JFormattedTextField();
	JLabel jLabel14 = new JLabel();
	JTextField txtSubtotal = new JFormattedTextField();
	JSplitPane splitPagos = new JSplitPane();
	JPanel pnlPagosaRealizar = new JPanel();
	JPanel pnlPagosRealizados = new JPanel();
	BorderLayout borderLayout9 = new BorderLayout();
	JLabel jLabel15 = new JLabel();
	JLabel jLabel16 = new JLabel();
	BorderLayout borderLayout10 = new BorderLayout();
	JScrollPane scrollPagosRealizar = new JScrollPane();
	JScrollPane scrollPagosRealizados = new JScrollPane();
	static ModelPagosCreditoEstado modelPagosCredito1 = new ModelPagosCreditoEstado();
	JTable tblPagosRealizar = new JTable();
	static ModelVentaArticulos modelVentaArticulos1 = new ModelVentaArticulos();
	private PnlEstadoCuenta pnlEstadoCuenta;

	public PnlEstadoCuenta getPnlEstadoCuenta() {
		return pnlEstadoCuenta;
	}

	public void setPnlEstadoCuenta(PnlEstadoCuenta pnlEstadoCuenta) {
		this.pnlEstadoCuenta = pnlEstadoCuenta;
	}

	static ModelPagosRealizados modelPagosRealizados1 = new ModelPagosRealizados();
	JTable tblPagosRealizados = new JTable();

	public PnlDatosVenta() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public PnlDatosVenta(PnlEstadoCuenta pnlEstadoCuenta) {
		this();
		this.pnlEstadoCuenta = pnlEstadoCuenta;
	}

	void jbInit() throws Exception {
		lblTitulo.setBackground(new Color(145, 145, 255));
		lblTitulo.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
		lblTitulo.setForeground(Color.black);
		lblTitulo.setOpaque(true);
		lblTitulo.setToolTipText("");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEADING);
		lblTitulo.setText("DATOS DE LA VENTA:");
		this.setLayout(borderLayout1);
		PnlDatosVenta.setLayout(borderLayout2);
		pnlDatosVentaNorte.setLayout(borderLayout3);
		pnlCliente.setLayout(borderLayout4);
		pnlDatosCliente.setLayout(gridLayout1);
		gridLayout1.setColumns(6);
		gridLayout1.setHgap(3);
		gridLayout1.setRows(2);
		gridLayout1.setVgap(3);
		jLabel6.setText("Boutique:");
		jLabel9.setText("Vendedor:");
		jLabel10.setText("No. venta:");
		jLabel11.setText("Fecha:");
		txtBoutique.setText("");
		txtVendedor.setText("");
		txtNoVenta.setText("");
		txtFecha.setText("");
		PnlArticulosDEscuentosPAgos.setDebugGraphicsOptions(0);
		PnlArticulosDEscuentosPAgos.setLayout(borderLayout5);
		PnlArticulos.setLayout(borderLayout6);
		jLabel5.setText("Articulos comprados");
		pnlTotalesSubtotales.setLayout(borderLayout7);
		pnlDerechaTotalesSubtotales.setLayout(gridLayout2);
		gridLayout2.setColumns(2);
		gridLayout2.setRows(3);
		gridLayout2.setVgap(2);
		jLabel7.setFont(new java.awt.Font("Dialog", 0, 14));
		jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel7.setHorizontalTextPosition(SwingConstants.LEADING);
		jLabel7.setText("Subtotal:");
		jLabel13.setFont(new java.awt.Font("Dialog", 0, 14));
		jLabel13.setRequestFocusEnabled(true);
		jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel13.setText("Total:");
		jLabel14.setFont(new java.awt.Font("Dialog", 0, 14));
		jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel14.setText("Anticipo:");
		scrollArticulos.getViewport().setBackground(Color.white);
		scrollArticulos.setPreferredSize(new Dimension(454, 98));
		txtSubtotal.setText("");
		txtAnticipo.setText("");
		splitPagos.setPreferredSize(new Dimension(27, 100));
		splitPagos.setLastDividerLocation(-220);
		pnlPagosaRealizar.setLayout(borderLayout9);
		jLabel15.setBackground(new Color(145, 145, 255));
		jLabel15.setOpaque(true);
		jLabel15.setText("Pagos a relizar");
		jLabel16.setBackground(new Color(145, 145, 255));
		jLabel16.setOpaque(true);
		jLabel16.setText("Pagos realizados:");
		pnlPagosRealizados.setLayout(borderLayout10);
		tblPagosRealizar.setModel(modelPagosCredito1);
		tblArticulos.setModel(modelVentaArticulos1);
		tblArticulos.addKeyListener(new PnlDatosVenta_tblArticulos_keyAdapter(
				this));
		tblArticulos
				.addMouseListener(new PnlDatosVenta_tblArticulos_mouseAdapter(
						this));

		tblPagosRealizados.setModel(modelPagosRealizados1);
		tblPagosRealizados
				.addKeyListener(new PnlDatosVenta_tblPagosRealizados_keyAdapter(
						this));
		pnlDerechaTotalesSubtotales.setBackground(Color.white);
		pnlDerechaTotalesSubtotales.setMinimumSize(new Dimension(84, 100));
		pnlDerechaTotalesSubtotales.setPreferredSize(new Dimension(150, 80));
		this.setBackground(Color.white);
		pnlDatosVentaNorte.setBackground(Color.white);
		pnlTotalesSubtotales.setBackground(Color.white);
		scrollPagosRealizar.getViewport().setBackground(Color.white);
		scrollPagosRealizados.getViewport().setBackground(Color.white);
		pnlDatosCliente.setBackground(Color.white);
		this.add(lblTitulo, BorderLayout.NORTH);
		this.add(PnlDatosVenta, BorderLayout.CENTER);
		PnlDatosVenta.add(pnlDatosVentaNorte, BorderLayout.NORTH);
		pnlDatosVentaNorte.add(pnlCliente, BorderLayout.CENTER);
		pnlCliente.add(pnlDatosCliente, BorderLayout.CENTER);
		pnlDatosCliente.add(jLabel6, null);
		pnlDatosCliente.add(txtBoutique, null);
		pnlDatosCliente.add(jLabel11, null);
		pnlDatosCliente.add(txtFecha, null);
		pnlDatosCliente.add(jLabel10, null);
		pnlDatosCliente.add(txtNoVenta, null);
		pnlDatosCliente.add(jLabel9, null);
		pnlDatosCliente.add(txtVendedor, null);
		PnlDatosVenta.add(PnlArticulosDEscuentosPAgos, BorderLayout.CENTER);
		PnlArticulosDEscuentosPAgos.add(PnlArticulos, BorderLayout.NORTH);
		PnlArticulos.add(jLabel5, BorderLayout.NORTH);
		PnlArticulos.add(scrollArticulos, BorderLayout.CENTER);
		scrollArticulos.getViewport().add(tblArticulos, null);
		PnlArticulos.add(pnlTotalesSubtotales, BorderLayout.SOUTH);
		pnlTotalesSubtotales
				.add(pnlDerechaTotalesSubtotales, BorderLayout.EAST);
		pnlDerechaTotalesSubtotales.add(jLabel7, null);
		pnlDerechaTotalesSubtotales.add(txtSubtotal, null);
		pnlDerechaTotalesSubtotales.add(jLabel14, null);
		pnlDerechaTotalesSubtotales.add(txtAnticipo, null);
		pnlDerechaTotalesSubtotales.add(jLabel13, null);
		pnlDerechaTotalesSubtotales.add(txtTotal, null);
		splitPagos.add(pnlPagosaRealizar, JSplitPane.LEFT);
		splitPagos.add(pnlPagosRealizados, JSplitPane.RIGHT);
		pnlPagosaRealizar.add(jLabel15, BorderLayout.NORTH);
		pnlPagosRealizados.add(jLabel16, BorderLayout.NORTH);
		pnlPagosRealizados.add(scrollPagosRealizados, BorderLayout.CENTER);
		scrollPagosRealizados.getViewport().add(tblPagosRealizados, null);
		pnlPagosaRealizar.add(scrollPagosRealizar, BorderLayout.CENTER);
		scrollPagosRealizar.getViewport().add(tblPagosRealizar, null);
		PnlArticulosDEscuentosPAgos.add(splitPagos,
				java.awt.BorderLayout.CENTER);
		tblArticulos.getColumnModel().getColumn(0).setMaxWidth(120);
		tblArticulos.getColumnModel().getColumn(1).setMinWidth(200);
		tblArticulos.getColumnModel().getColumn(2).setMaxWidth(300);
		tblArticulos.getColumnModel().getColumn(3).setMaxWidth(250);
		tblArticulos.getColumnModel().getColumn(4).setMaxWidth(250);
		tblArticulos.getColumnModel().getColumn(5).setMaxWidth(250);
		splitPagos.setDividerLocation(265);

	}

	/**
	 * limpiarDatos
	 */
	public void limpiarDatos() {
		txtBoutique.setText("");
		txtFecha.setText("");
		txtNoVenta.setText("");
		txtVendedor.setText("");
		txtSubtotal.setText("");
		txtAnticipo.setText("");
		txtTotal.setText("");
		modelPagosCredito1.clean();
		modelPagosRealizados1.data.clear();
		modelVentaArticulos1.data.clear();
		modelPagosCredito1.fireTableDataChanged();
		modelPagosRealizados1.fireTableDataChanged();
		modelVentaArticulos1.fireTableDataChanged();
	}

	public void tblPagosRealizados_keyPressed(KeyEvent e) {
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
							.get(tblPagosRealizados.getSelectedRow());
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

	public void tblArticulos_keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 112) {
			if (AppInstance.interfaceGrafica == InterfaceGrafica.ADMIN) {
				int idProductoVendido = engine.idProductos.get(tblArticulos
						.getSelectedRow());

				DlgDescuentoCredito dlg = new DlgDescuentoCredito(
						idProductoVendido);
				dlg.setModal(true);
				dlg.setLocationRelativeTo(this);
				dlg.setVisible(true);
				if (dlg.isExito()) {
					pnlEstadoCuenta.setEdoCuenta();
				}
			}

		}
	}

	public void tblArticulos_mouseClicked(MouseEvent e) {
		System.out.println(e.getButton());
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (AppInstance.interfaceGrafica == InterfaceGrafica.ADMIN) {
				int idProductoVendido = engine.idProductos.get(tblArticulos
						.getSelectedRow());

				DlgDescuentoCredito dlg = new DlgDescuentoCredito(
						idProductoVendido);
				dlg.setModal(true);
				dlg.setLocationRelativeTo(this);
				dlg.setVisible(true);
				if (dlg.isExito()) {
					pnlEstadoCuenta.setEdoCuenta();
				}
			}

		}
	}
}

class PnlDatosVenta_tblArticulos_keyAdapter extends KeyAdapter {
	private PnlDatosVenta adaptee;

	PnlDatosVenta_tblArticulos_keyAdapter(PnlDatosVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {

		adaptee.tblArticulos_keyPressed(e);
	}
}

class PnlDatosVenta_tblArticulos_mouseAdapter extends MouseAdapter {
	private PnlDatosVenta adaptee;

	PnlDatosVenta_tblArticulos_mouseAdapter(PnlDatosVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {

		adaptee.tblArticulos_mouseClicked(e);
	}
}

class PnlDatosVenta_tblPagosRealizados_keyAdapter extends KeyAdapter {
	private PnlDatosVenta adaptee;

	PnlDatosVenta_tblPagosRealizados_keyAdapter(PnlDatosVenta adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.tblPagosRealizados_keyPressed(e);
	}
}
