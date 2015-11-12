package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.domain.*;
import com.boutique.dao.*;
import com.boutique.engine.impl.AppInstance;

import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: boutique management
 * </p>
 * 
 * <p>
 * Description: Sistema de administracion de boitiques
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: SESTO
 * </p>
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class FrmCatalogoCompras extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.util.List<Proveedor> lProveedores;
	BorderLayout borderLayout1 = new BorderLayout();
	JSplitPane jSplitPane1 = new JSplitPane();
	JPanel jPanel1 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel jPanel2 = new JPanel();
	JLabel jLabel1 = new JLabel();
	JTextField txtNombreProveedor = new JTextField();
	JButton cmdBuscar = new JButton();
	JScrollPane jScrollPane1 = new JScrollPane();
	JList lstComprasEncontradas = new JList();

	JPanel jPanel3 = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	java.util.List<Compra> lst = new java.util.ArrayList<Compra>();
	DefaultListModel model = new DefaultListModel();
	JPanel jPanel4 = new JPanel();
	JButton cmdEliminar = new JButton();
	JButton cmdModificar = new JButton();
	JButton cmdAgregar = new JButton();
	JButton cmdAceptar = new JButton();
	JButton cmdCancelar = new JButton();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel2 = new JLabel();
	JDateChooser jdFechaCompra = new JDateChooser();
	JLabel jLabel3 = new JLabel();
	JComboBox cmbProveedores = new JComboBox();
	Compra compra;
	String accion;
	JLabel lblNoReferencia = new JLabel();
	JTextField txtNoReferencia = new JTextField();
	JLabel lblNoFactura = new JLabel();
	JTextField txtNoFactura = new JTextField();
	JLabel lblTipoPago = new JLabel();
	JComboBox cmbTipoPago = new JComboBox();
	JLabel lblBanco = new JLabel();
	JComboBox cmbBanco = new JComboBox();
	Map<String, Integer> mapTipoPago = new HashMap<String, Integer>();
	DefaultComboBoxModel modelTipoPagos = new DefaultComboBoxModel();

	public FrmCatalogoCompras() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		lstComprasEncontradas.setModel(model);
		lstComprasEncontradas
				.addMouseListener(new FrmCatalogoCompras_lstComprasEncontradas_mouseAdapter(
						this));
		getContentPane().setLayout(borderLayout1);
		jPanel2.setLayout(borderLayout3);
		cmdBuscar
				.addActionListener(new FrmCatalogoCompras_cmdBuscar_actionAdapter(
						this));
		cmdEliminar.setText("Eliminar");
		cmdEliminar
				.addActionListener(new FrmCatalogoCompras_cmdEliminar_actionAdapter(
						this));
		cmdModificar.setText("Modificar");
		cmdModificar
				.addActionListener(new FrmCatalogoCompras_cmdModificar_actionAdapter(
						this));
		cmdAgregar.setText("Agregar");
		cmdAgregar
				.addActionListener(new FrmCatalogoCompras_cmdAgregar_actionAdapter(
						this));
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addActionListener(new FrmCatalogoCompras_cmdAceptar_actionAdapter(
						this));
		cmdCancelar.setText("Cancelar");
		cmdCancelar
				.addActionListener(new FrmCatalogoCompras_cmdCancelar_actionAdapter(
						this));
		jPanel3.setLayout(gridLayout1);
		gridLayout1.setColumns(2);
		gridLayout1.setRows(7);
		jLabel2.setText("Fecha de la compra:");
		jLabel3.setText("Nombre del proveedor:");
		lblNoReferencia.setText("No. de referencia");
		txtNoReferencia.setText("");
		this.setTitle("Catalogo de compras");
		this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
		jPanel1.setLayout(borderLayout2);
		jLabel1.setText("Indica el nombre del proveedor");
		txtNombreProveedor.setText("");
		cmdBuscar.setText("Buscar");
		jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);
		jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jSplitPane1.add(jPanel3, JSplitPane.RIGHT);
		jPanel3.add(jLabel2);
		jPanel3.add(jdFechaCompra);
		jPanel3.add(jLabel3);
		jPanel3.add(cmbProveedores);
		jPanel3.add(lblNoReferencia);
		jPanel3.add(txtNoReferencia);
		jPanel3.add(lblNoFactura);
		jPanel3.add(txtNoFactura);
		jPanel3.add(lblTipoPago);
		jPanel3.add(cmbTipoPago);
		jPanel3.add(lblBanco);
		jPanel3.add(cmbBanco);

		jSplitPane1.add(jPanel1, JSplitPane.LEFT);
		jPanel2.add(jLabel1, java.awt.BorderLayout.WEST);
		jPanel2.add(txtNombreProveedor, java.awt.BorderLayout.CENTER);
		jPanel2.add(cmdBuscar, java.awt.BorderLayout.EAST);
		jScrollPane1.getViewport().add(lstComprasEncontradas);
		this.getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);
		jPanel4.add(cmdAgregar);
		jPanel4.add(cmdModificar);
		jPanel4.add(cmdEliminar);
		jPanel4.add(cmdAceptar);
		jPanel4.add(cmdCancelar);
		lblBanco.setText("Banco");
		lblNoFactura.setText("No. de factura");
		lblTipoPago.setText("Tipo de pago");
		ponerProveedores();
		habilitarBotones();
		deshabilitarCajas();
		ponerBancos();
		ponerTipoPagos();
	}

	private void ponerTipoPagos() {
		mapTipoPago.put("Efectivo", 1);
		mapTipoPago.put("Tarjeta de credito/debito", 2);
		mapTipoPago.put("Cheque", 4);
		modelTipoPagos.addElement("Efectivo");
		modelTipoPagos.addElement("Tarjeta de credito/debito");
		modelTipoPagos.addElement("Cheque");
		cmbTipoPago.setModel(modelTipoPagos);

	}

	private void ponerBancos() {
		cmbBanco.setModel(AppInstance.modelBancos);
	}

	public void ponerProveedores() {
		lProveedores = DaoProveedores.findAll();
		for (Proveedor p : lProveedores) {
			cmbProveedores.addItem(p.getNombre());
		}

	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {
		mostrarComprasPorProveedor();
	}

	public void habilitarBotones() {
		cmdAceptar.setEnabled(false);
		cmdCancelar.setEnabled(false);
		cmdAgregar.setEnabled(true);
		cmdModificar.setEnabled(true);
		cmdEliminar.setEnabled(true);
	}

	public void deshabilitarBotones() {
		cmdAceptar.setEnabled(true);
		cmdCancelar.setEnabled(true);
		cmdAgregar.setEnabled(false);
		cmdModificar.setEnabled(false);
		cmdEliminar.setEnabled(false);
	}

	public void limpiarCajas() {
		cmbProveedores.setSelectedIndex(-1);
		jdFechaCompra.setDate(new java.util.Date());
		txtNoReferencia.setText("");
	}

	public void habilitarCajas() {
		cmbProveedores.setEnabled(true);
		jdFechaCompra.setEnabled(true);
		txtNoReferencia.setEnabled(true);
		txtNoFactura.setEnabled(true);
		cmbTipoPago.setEnabled(true);
		cmbBanco.setEnabled(true);
	}

	public void deshabilitarCajas() {
		cmbProveedores.setEnabled(false);
		jdFechaCompra.setEnabled(false);
		txtNoReferencia.setEnabled(false);
		txtNoFactura.setEnabled(false);
		cmbTipoPago.setEnabled(false);
		cmbBanco.setEnabled(false);
	}

	/**
	 * mostrarComprasPorProveedor
	 */
	private void mostrarComprasPorProveedor() {
		model.clear();
		lst = DaoCompras.findByNombre(txtNombreProveedor.getText());
		for (Compra c : lst) {
			model.addElement(c.getFecha().toString());
		}

	}

	public void cmdAgregar_actionPerformed(ActionEvent e) {
		deshabilitarBotones();
		limpiarCajas();
		habilitarCajas();
		accion = "agregar";
	}

	public void cmdModificar_actionPerformed(ActionEvent e) {
		deshabilitarBotones();
		habilitarCajas();
		accion = "modificar";
	}

	public void cmdEliminar_actionPerformed(ActionEvent e) {
		int i = JOptionPane.showConfirmDialog(this,
				"Estas seguro de eliminar la compra?", "Eliminar",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			// Eliminamos el registro
			try {
				DaoCompras.remove(compra.getId());
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		if (accion.equals("agregar")) {
			compra = new Compra();
			compra.setFecha(new java.sql.Timestamp(jdFechaCompra.getDate()
					.getTime()));
			Proveedor p = lProveedores.get(cmbProveedores.getSelectedIndex());
			compra.setIdProveedor(p.getId());
			compra.setNoReferencia(txtNoReferencia.getText());
			compra.getFactura().setFecha(compra.getFecha());
			compra.getFactura().setIdProveedor(p.getId());
			compra.getFactura().setTipoPago(
					this.mapTipoPago.get(this.cmbTipoPago.getSelectedItem()));
			compra.getFactura().setBanco(
					this.cmbBanco.getSelectedItem().toString());
			compra.getFactura().setNoFactura(this.txtNoFactura.getText());
			try {
				DaoCompras.add(compra);
				DaoCompras.add(compra.getFactura());
				habilitarBotones();
				deshabilitarCajas();

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		} else if (accion.equals("modificar")) {
			compra.setFecha(new java.sql.Timestamp(jdFechaCompra.getDate()
					.getTime()));
			Proveedor p = lProveedores.get(cmbProveedores.getSelectedIndex());
			compra.setIdProveedor(p.getId());
			compra.setNoReferencia(txtNoReferencia.getText());

			try {
				DaoCompras.update(compra);
				habilitarBotones();
				deshabilitarCajas();

			} catch (SQLException ex1) {
				JOptionPane.showMessageDialog(this, ex1.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	public void cmdCancelar_actionPerformed(ActionEvent e) {
		this.habilitarBotones();
		this.limpiarCajas();
		this.deshabilitarCajas();
	}

	public void lstComprasEncontradas_mouseClicked(MouseEvent e) {
		if (lstComprasEncontradas.getSelectedIndex() >= 0) {
			compra = lst.get(lstComprasEncontradas.getSelectedIndex());
			this.jdFechaCompra.setDate(compra.getFecha());
			this.txtNoReferencia.setText(compra.getNoReferencia());
			Proveedor p = DaoProveedores.findById(compra.getIdProveedor());
			this.cmbProveedores.setSelectedItem(p.getNombre());
		}
	}

}

class FrmCatalogoCompras_lstComprasEncontradas_mouseAdapter extends
		MouseAdapter {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_lstComprasEncontradas_mouseAdapter(
			FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.lstComprasEncontradas_mouseClicked(e);
	}
}

class FrmCatalogoCompras_cmdCancelar_actionAdapter implements ActionListener {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_cmdCancelar_actionAdapter(FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelar_actionPerformed(e);
	}
}

class FrmCatalogoCompras_cmdAceptar_actionAdapter implements ActionListener {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_cmdAceptar_actionAdapter(FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class FrmCatalogoCompras_cmdEliminar_actionAdapter implements ActionListener {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_cmdEliminar_actionAdapter(FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdEliminar_actionPerformed(e);
	}
}

class FrmCatalogoCompras_cmdModificar_actionAdapter implements ActionListener {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_cmdModificar_actionAdapter(FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdModificar_actionPerformed(e);
	}
}

class FrmCatalogoCompras_cmdAgregar_actionAdapter implements ActionListener {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_cmdAgregar_actionAdapter(FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregar_actionPerformed(e);
	}
}

class FrmCatalogoCompras_cmdBuscar_actionAdapter implements ActionListener {
	private FrmCatalogoCompras adaptee;

	FrmCatalogoCompras_cmdBuscar_actionAdapter(FrmCatalogoCompras adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}
