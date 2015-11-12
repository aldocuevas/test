package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import javax.swing.table.*;

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

public class FrmBuscarClientePorRuta extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	TableColumnModel m;

	public FrmBuscarClientePorRuta() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		setModal(true);
		this.setTitle("BUSCAR CLIENTE");
		pnlTop.setLayout(borderLayout2);
		cmdAceptar.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
		cmdAceptar.setText("CONTINUAR");
		cmdAceptar
				.addActionListener(new FrmBuscarClientePorRuta_cmdAceptar_actionAdapter(
						this));
		txtNombre
				.addActionListener(new FrmBuscarClientePorRuta_txtNombre_actionAdapter(
						this));
		lblNombreCliente.setFont(new java.awt.Font("Dialog", Font.PLAIN, 15));
		scrollBottom.getViewport().setBackground(Color.white);
		tblClientes.setBackground(new Color(255, 240, 255));
		tblClientes.setFont(new java.awt.Font("Arial", Font.PLAIN, 11));
		tblClientes.setGridColor(Color.lightGray);
		tblClientes.setShowVerticalLines(false);
		cmdBuscar.setFont(new java.awt.Font("Arial", Font.PLAIN, 11));
		pnlTop.add(cmdBuscar, java.awt.BorderLayout.EAST);
		pnlTop.add(lblNombreCliente, java.awt.BorderLayout.WEST);
		pnlTop.add(txtNombre, java.awt.BorderLayout.CENTER);

		lblNombreCliente.setText("Indica el nombre del cliente:");
		tblClientes.setModel(modelDatosClientes1);
		cmdBuscar
				.addActionListener(new FrmBuscarClientePorRuta_jButton1_actionAdapter(
						this));
		pnlTop.setPreferredSize(new Dimension(199, 20));
		jSplitPane1.add(pnlTop, JSplitPane.TOP);
		this.getContentPane().setLayout(borderLayout1);
		cmdBuscar.setText("BUSCAR");
		jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPane1.add(pnlTop, JSplitPane.TOP);
		jSplitPane1.add(scrollBottom, JSplitPane.BOTTOM);
		scrollBottom.getViewport().add(tblClientes);
		this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
		jPanel1.add(cmdAceptar);
		tblClientes.getColumnModel().getColumn(0).setMaxWidth(40);
		tblClientes.getColumnModel().getColumn(1).setMinWidth(200);
		tblClientes.getColumnModel().getColumn(2).setMinWidth(200);
		this.pack();

	}

	Object[] objCliente;
	JPanel pnlTop = new JPanel();
	JButton cmdBuscar = new JButton();
	JTextField txtNombre = new JTextField();
	JSplitPane jSplitPane1 = new JSplitPane();
	JScrollPane scrollBottom = new JScrollPane();
	BorderLayout borderLayout2 = new BorderLayout();
	JLabel lblNombreCliente = new JLabel();
	JTable tblClientes = new JTable();
	ModelDatosClientes modelDatosClientes1 = new ModelDatosClientes();

	JPanel jPanel1 = new JPanel();
	JButton cmdAceptar = new JButton();
	java.util.List<Object[]> listaClientes;
	public Cliente cliente = null;

	public void jButton1_actionPerformed(ActionEvent e) {
		listaClientes = DaoClientesSucursal.findAllByRutaAndVentasActivas();
		this.modelDatosClientes1.setClientes(listaClientes);
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		if (cmdAceptar.isEnabled() && this.tblClientes.getSelectedRow() >= 0) {
			objCliente = (Object[]) this.listaClientes.get(tblClientes
					.getSelectedRow());
			cliente = DaoClientesSucursal.findById(Integer
					.parseInt(objCliente[0].toString()));
			this.setVisible(false);
		}

	}

	public void txtNombre_actionPerformed(ActionEvent e) {
		listaClientes = DaoClientesSucursal.findAllByRutaAndVentasActivas();
		this.modelDatosClientes1.setClientes(listaClientes);

	}
}

class FrmBuscarClientePorRuta_txtNombre_actionAdapter implements ActionListener {
	private FrmBuscarClientePorRuta adaptee;

	FrmBuscarClientePorRuta_txtNombre_actionAdapter(
			FrmBuscarClientePorRuta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtNombre_actionPerformed(e);
	}
}

class FrmBuscarClientePorRuta_cmdAceptar_actionAdapter implements
		ActionListener {
	private FrmBuscarClientePorRuta adaptee;

	FrmBuscarClientePorRuta_cmdAceptar_actionAdapter(
			FrmBuscarClientePorRuta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class FrmBuscarClientePorRuta_jButton1_actionAdapter implements ActionListener {
	private FrmBuscarClientePorRuta adaptee;

	FrmBuscarClientePorRuta_jButton1_actionAdapter(
			FrmBuscarClientePorRuta adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}
