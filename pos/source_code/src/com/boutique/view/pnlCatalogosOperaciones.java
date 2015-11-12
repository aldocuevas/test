package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnlCatalogosOperaciones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton cmdAceptar = new JButton();
	JButton cmdEliminar = new JButton();
	JButton cmdCancelar = new JButton();
	JButton cmdModificar = new JButton();
	JButton cmdAgregar = new JButton();
	FlowLayout flowLayout1 = new FlowLayout();
	JButton cmdBuscar = new JButton();
	private String operacion;

	public pnlCatalogosOperaciones() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setLayout(flowLayout1);
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addActionListener(new pnlCatalogosOperaciones_cmdAceptar_actionAdapter(
						this));
		cmdEliminar.setText("Eliminar");
		cmdEliminar
				.addActionListener(new pnlCatalogosOperaciones_cmdEliminar_actionAdapter(
						this));
		cmdCancelar.setText("Cancelar");
		cmdCancelar
				.addActionListener(new pnlCatalogosOperaciones_cmdCancelar_actionAdapter(
						this));
		cmdModificar.setText("Modificar");
		cmdModificar
				.addActionListener(new pnlCatalogosOperaciones_cmdModificar_actionAdapter(
						this));
		cmdAgregar.setText("Agregar");
		cmdAgregar
				.addActionListener(new pnlCatalogosOperaciones_cmdAgregar_actionAdapter(
						this));
		cmdBuscar.setText("Buscar");
		cmdBuscar
				.addActionListener(new pnlCatalogosOperaciones_cmdBuscar_actionAdapter(
						this));
		this.add(cmdAgregar, null);
		this.add(cmdModificar, null);
		this.add(cmdEliminar, null);
		this.add(cmdAceptar, null);
		this.add(cmdCancelar, null);
		this.add(cmdBuscar, null);

	}

	void desHabilitarBotones() {
		cmdAgregar.setEnabled(false);
		cmdModificar.setEnabled(false);
		cmdEliminar.setEnabled(false);
		cmdAceptar.setEnabled(true);
		cmdCancelar.setEnabled(true);
		cmdBuscar.setEnabled(false);
	}

	void habilitarBotones() {
		cmdAgregar.setEnabled(true);
		cmdModificar.setEnabled(true);
		cmdEliminar.setEnabled(true);
		cmdBuscar.setEnabled(true);
		cmdAceptar.setEnabled(false);
		cmdCancelar.setEnabled(false);
	}

	public void cmdAgregar_actionPerformed(ActionEvent e) {
		setOperacion("agregar");
	}

	public void cmdModificar_actionPerformed(ActionEvent e) {
		setOperacion("modificar");
	}

	public void cmdEliminar_actionPerformed(ActionEvent e) {

	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {

	}

	public void cmdCancelar_actionPerformed(ActionEvent e) {

	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {

	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getOperacion() {
		return operacion;
	}
}

class pnlCatalogosOperaciones_cmdBuscar_actionAdapter implements ActionListener {
	private pnlCatalogosOperaciones adaptee;

	pnlCatalogosOperaciones_cmdBuscar_actionAdapter(
			pnlCatalogosOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}

class pnlCatalogosOperaciones_cmdCancelar_actionAdapter implements
		ActionListener {
	private pnlCatalogosOperaciones adaptee;

	pnlCatalogosOperaciones_cmdCancelar_actionAdapter(
			pnlCatalogosOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelar_actionPerformed(e);
	}
}

class pnlCatalogosOperaciones_cmdAceptar_actionAdapter implements
		ActionListener {
	private pnlCatalogosOperaciones adaptee;

	pnlCatalogosOperaciones_cmdAceptar_actionAdapter(
			pnlCatalogosOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class pnlCatalogosOperaciones_cmdEliminar_actionAdapter implements
		ActionListener {
	private pnlCatalogosOperaciones adaptee;

	pnlCatalogosOperaciones_cmdEliminar_actionAdapter(
			pnlCatalogosOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdEliminar_actionPerformed(e);
	}
}

class pnlCatalogosOperaciones_cmdModificar_actionAdapter implements
		ActionListener {
	private pnlCatalogosOperaciones adaptee;

	pnlCatalogosOperaciones_cmdModificar_actionAdapter(
			pnlCatalogosOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdModificar_actionPerformed(e);
	}
}

class pnlCatalogosOperaciones_cmdAgregar_actionAdapter implements
		ActionListener {
	private pnlCatalogosOperaciones adaptee;

	pnlCatalogosOperaciones_cmdAgregar_actionAdapter(
			pnlCatalogosOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregar_actionPerformed(e);
	}
}
