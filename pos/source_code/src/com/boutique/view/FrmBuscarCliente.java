package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import javax.swing.table.*;

import com.boutique.engine.impl.AppInstance;

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

public class FrmBuscarCliente extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	TableColumnModel m;
	private boolean correoElectronicoNecesario;

	public FrmBuscarCliente(boolean correoElectronicoNecesario) {
		try {
			this.correoElectronicoNecesario=correoElectronicoNecesario;
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
				.addActionListener(new FrmBuscarCliente_cmdAceptar_actionAdapter(
						this));
		txtNombre
				.addActionListener(new FrmBuscarCliente_txtNombre_actionAdapter(
						this));
		lblNombreCliente.setFont(new java.awt.Font("Dialog", Font.PLAIN, 15));
		scrollBottom.getViewport().setBackground(Color.white);
		tblClientes.setBackground(new Color(255, 240, 255));
		tblClientes.setFont(new java.awt.Font("Arial", Font.PLAIN, 11));
		tblClientes.setGridColor(Color.lightGray);
		tblClientes.setShowVerticalLines(false);
		cmdBuscar.setFont(new java.awt.Font("Arial", Font.PLAIN, 11));
		cmdAgregarCliente.setText("AGREGAR CLIENTE");
		cmdAgregarCliente
				.addActionListener(new FrmBuscarCliente_cmdAgregarCliente_actionAdapter(
						this));
		cmdModificar.setText("MODIFICAR CLIENTE");
		cmdModificar
				.addActionListener(new FrmBuscarCliente_cmdModificar_actionAdapter(
						this));
		pnlTop.add(cmdBuscar, java.awt.BorderLayout.EAST);
		pnlTop.add(lblNombreCliente, java.awt.BorderLayout.WEST);
		pnlTop.add(txtNombre, java.awt.BorderLayout.CENTER);

		lblNombreCliente.setText("Indica el nombre del cliente:");
		tblClientes.setModel(modelDatosClientes1);
		cmdBuscar
				.addActionListener(new FrmBuscarCliente_jButton1_actionAdapter(
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
		jPanel1.add(cmdAgregarCliente);
		jPanel1.add(cmdModificar);
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
	public String msgFaltantes = "";
	JButton cmdAgregarCliente = new JButton();
	JButton cmdModificar = new JButton();

	public void jButton1_actionPerformed(ActionEvent e) {
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		listaClientes = DaoClientesSucursal.findByNombreCompleto(this.txtNombre
				.getText());
		this.modelDatosClientes1.setClientes(listaClientes);
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		if (cmdAceptar.isEnabled() && this.tblClientes.getSelectedRow() >= 0) {
			this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
			objCliente = (Object[]) this.listaClientes.get(tblClientes
					.getSelectedRow());
			cliente = DaoClientesSucursal.findById(Integer
					.parseInt(objCliente[0].toString()));
			if (cliente.getApellidoPaterno() == null
					|| cliente.getApellidoPaterno().equals("")) {
				msgFaltantes += "Apellido paterno,";
			}
			if (cliente.getApellidoMaterno() == null
					|| cliente.getApellidoMaterno().equals("")) {
				msgFaltantes += "Apellido materno,";
			}
			if (cliente.getCalle() == null || cliente.getCalle().equals("")) {
				msgFaltantes += "Domicilio (Calle),";
			}
			if (cliente.getNumero() == null || cliente.getNumero().equals("")) {
				msgFaltantes += "Domicilio (numero),";
			}
			if (cliente.getColonia() == null || cliente.getColonia().equals("")
					|| cliente.getColonia().equals("NO ESPECIFICADA")) {
				msgFaltantes += "colonia,";
			}
			if (cliente.getTelefono() == null
					|| cliente.getTelefono().equals("")) {
				msgFaltantes += "Telefono particular,";
			}
			if (cliente.getCelular() == null || cliente.getCelular().equals("")) {
				msgFaltantes += "Telefono celular,";
			}
			if (cliente.getFechaNacimiento() == null) {
				msgFaltantes += "Fecha de nacimiento,";
			}
			if (cliente.getTallaCalzado() == null
					|| cliente.getTallaCalzado().equals("")) {
				msgFaltantes += "Talla de calzado,";
			}
			if (cliente.getTallaConjunto() == null
					|| cliente.getTallaConjunto().equals("")) {
				msgFaltantes += "Talla de conjunto,";
			}
			if (cliente.getLimiteCredito() == 0.0) {
				msgFaltantes += "Limite de crédito,";
			}
			if (cliente.getEmail() == null
					|| (cliente.getNoEmail().equals("0") && cliente.getEmail()
							.equals(""))) {
				msgFaltantes += "Correo electrónico";
			}

			// apellidos, calle, numero , colonia, tel part , tel cel, fech nac,
			// talla calz, talla conj, limite credito,
			this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
			this.setVisible(false);
		}

	}

	public void txtNombre_actionPerformed(ActionEvent e) {
		this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
		listaClientes = DaoClientesSucursal.findByNombreCompleto(this.txtNombre
				.getText());
		this.modelDatosClientes1.setClientes(listaClientes);
		this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);

	}

	public void cmdAgregarCliente_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		DlgActualizarDatosClientes dlg = new DlgActualizarDatosClientes(
				new Cliente(), "AGREGAR",isCorreoElectronicoNecesario());
		dlg.setModal(true);
		dlg.setSize(500, 700);
		this.setLocationRelativeTo(this.getRootPane());
		this.setCursor(AppInstance.defCursor);
		dlg.setVisible(true);
		dlg.dispose();
		dlg = null;

	}

	public void cmdModificar_actionPerformed(ActionEvent e) {
		if (this.tblClientes.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this,
					"POR FAVOR, ELIGE EL CLIENTE A MODIFICAR",
					AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.setCursor(AppInstance.waitCursor);
		objCliente = (Object[]) this.listaClientes.get(tblClientes
				.getSelectedRow());
		cliente = DaoClientesSucursal.findById(Integer.parseInt(objCliente[0]
				.toString()));

		DlgActualizarDatosClientes dlg = new DlgActualizarDatosClientes(
				cliente, "ACTUALIZAR",isCorreoElectronicoNecesario());
		dlg.setModal(true);
		dlg.setSize(500, 700);
		this.setLocationRelativeTo(this.getRootPane());
		this.setCursor(AppInstance.defCursor);
		dlg.setVisible(true);
		dlg.dispose();
		dlg = null;
	}

	public void setCorreoElectronicoNecesario(boolean correoElectronicoNecesario) {
		this.correoElectronicoNecesario = correoElectronicoNecesario;
	}

	public boolean isCorreoElectronicoNecesario() {
		return correoElectronicoNecesario;
	}
}

class FrmBuscarCliente_cmdModificar_actionAdapter implements ActionListener {
	private FrmBuscarCliente adaptee;

	FrmBuscarCliente_cmdModificar_actionAdapter(FrmBuscarCliente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdModificar_actionPerformed(e);
	}
}

class FrmBuscarCliente_cmdAgregarCliente_actionAdapter implements
		ActionListener {
	private FrmBuscarCliente adaptee;

	FrmBuscarCliente_cmdAgregarCliente_actionAdapter(FrmBuscarCliente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarCliente_actionPerformed(e);
	}
}

class FrmBuscarCliente_txtNombre_actionAdapter implements ActionListener {
	private FrmBuscarCliente adaptee;

	FrmBuscarCliente_txtNombre_actionAdapter(FrmBuscarCliente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtNombre_actionPerformed(e);
	}
}

class FrmBuscarCliente_cmdAceptar_actionAdapter implements ActionListener {
	private FrmBuscarCliente adaptee;

	FrmBuscarCliente_cmdAceptar_actionAdapter(FrmBuscarCliente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class FrmBuscarCliente_jButton1_actionAdapter implements ActionListener {
	private FrmBuscarCliente adaptee;

	FrmBuscarCliente_jButton1_actionAdapter(FrmBuscarCliente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}
