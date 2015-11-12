package com.boutique.view;

import java.beans.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

public class PnlOperaciones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton add = new JButton();
	JButton ok = new JButton();
	JButton delete = new JButton();
	JButton edit = new JButton();
	JButton cancel = new JButton();
	JButton cmdBuscar = new JButton();
	ImageIcon iCancel;
	ImageIcon fileNew;
	ImageIcon editTrash;
	ImageIcon fileOpen;
	ImageIcon iOk;
	@SuppressWarnings("rawtypes")
	private transient Vector propertyChangeListeners;
	private boolean accionExitosa;
	private String accion;
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout2 = new GridLayout();
	BorderLayout borderLayout1 = new BorderLayout();
	String SIDE = BorderLayout.EAST;

	public PnlOperaciones() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public PnlOperaciones(String side) {
		try {
			this.SIDE = side;
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		iOk = new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/ok.png"));
		fileOpen = new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/fileopen.png"));

		editTrash = new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/edittrash.png"));
		fileNew = new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/filenew.png"));
		iCancel = new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/cancel.png"));
		ok.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));

		// ok.setIcon(iOk);
		ok.setText("Guardar");
		ok.addActionListener(new PnlOperaciones_ok_actionAdapter(this));
		this.setLayout(borderLayout1);
		delete.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		// delete.setIcon(editTrash);
		delete.setText("Eliminar");
		delete.addActionListener(new PnlOperaciones_delete_actionAdapter(this));
		edit.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		edit.setToolTipText("Modificar");
		// edit.setIcon(fileNew);
		edit.setText("Modificar");
		edit.addActionListener(new PnlOperaciones_edit_actionAdapter(this));
		cancel.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		cancel.setToolTipText("Cancelar");
		// cancel.setIcon(iCancel);
		cancel.setText("Cancelar");
		cancel.addActionListener(new PnlOperaciones_cancel_actionAdapter(this));
		add.setFont(new java.awt.Font("Dialog", Font.PLAIN, 10));
		// add.setIcon(fileNew);
		add.setMnemonic('0');
		add.setText("");
		add.setText("Agregar");
		add.addActionListener(new PnlOperaciones_add_actionAdapter(this));
		this.setMinimumSize(new Dimension(250, 42));
		this.setPreferredSize(new Dimension(250, 42));
		jPanel1.setLayout(gridLayout2);
		jPanel1.setPreferredSize(new Dimension(500, 31));
		// cmdBuscar.setIcon(fileOpen);
		cmdBuscar.setText("Buscar");
		cmdBuscar.addActionListener(new PnlOperaciones_jButton1_actionAdapter(
				this));
		this.add(jPanel1, SIDE);
		jPanel1.add(cmdBuscar);
		jPanel1.add(add, null);
		jPanel1.add(edit, null);
		jPanel1.add(delete, null);
		jPanel1.add(ok, null);
		jPanel1.add(cancel, null);
		this.habilitarBotones();
		add.setToolTipText("Agregar");
		delete.setVerifyInputWhenFocusTarget(true);
		delete.setToolTipText("Eliminar");
		ok.setToolTipText("Aceptar");
	}

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

	void add_actionPerformed(ActionEvent e) {

		if (add.isEnabled()) {
			String oldAccion;
			oldAccion = this.accion;
			this.accion = "add";
			this.desHabilitarBotones();
			this.firePropertyChange("accion", oldAccion, this.accion);
		}
	}

	public void setEdit(boolean v) {
		this.edit.setEnabled(v);
		this.delete.setEnabled(v);
	}

	void edit_actionPerformed(ActionEvent e) {
		if (edit.isEnabled()) {
			String oldAccion;
			oldAccion = this.accion;
			this.accion = "edit";
			this.desHabilitarBotones();
			this.firePropertyChange("accion", oldAccion, this.accion);
		}

	}

	void delete_actionPerformed(ActionEvent e) {
		String oldAccion;
		int i = JOptionPane.showConfirmDialog(null,
				"Estas seguro que quieres borrar el registro");
		if (i == JOptionPane.YES_OPTION) {
			oldAccion = this.accion;
			this.accion = "delete";
			this.firePropertyChange("accion", oldAccion, this.accion);
			this.accion = "";
		}

	}

	void ok_actionPerformed(ActionEvent e) {
		if (ok.isEnabled()) {
			String oldAccion;
			oldAccion = this.accion;
			this.accion = "ok";
			this.firePropertyChange("accion", oldAccion, this.accion);
		}
	}

	void cancel_actionPerformed(ActionEvent e) {
		if (cancel.isEnabled()) {

			String oldAccion;
			oldAccion = this.accion;
			this.accion = "cancel";
			this.firePropertyChange("accion", oldAccion, this.accion);
			this.habilitarBotones();
		}

	}

	void desHabilitarBotones() {
		add.setEnabled(false);
		edit.setEnabled(false);
		delete.setEnabled(false);
		ok.setEnabled(true);
		cancel.setEnabled(true);
		cmdBuscar.setEnabled(false);
	}

	void habilitarBotones() {
		add.setEnabled(true);
		edit.setEnabled(true);
		delete.setEnabled(true);
		cmdBuscar.setEnabled(true);
		ok.setEnabled(false);
		cancel.setEnabled(false);
	}

	public void propertyChange(PropertyChangeEvent e) {
		System.out.println(e.getPropertyName());
		if (e.getPropertyName().equals("add")) {
			if (e.getNewValue().equals("true")) {
				habilitarBotones();
			} else if (e.getNewValue().equals("false")) {
				desHabilitarBotones();
			}
		}
	}

	public boolean getAccionExitosa() {
		return accionExitosa;
	}

	public void setAccionExitosa(boolean accionExitosa) {
		this.accionExitosa = accionExitosa;
		if (this.accionExitosa == true) {
			this.habilitarBotones();
		} else {
			this.desHabilitarBotones();
		}
	}

	public void jButton1_actionPerformed(ActionEvent e) {

	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {
		if (cmdBuscar.isEnabled()) {
			String oldAccion;
			oldAccion = this.accion;
			this.accion = "buscar";
			this.firePropertyChange("accion", oldAccion, this.accion);
		}
	}

	/**
	 * setAccion
	 * 
	 * @param string
	 *            String
	 */
	public void setAccion(String string) {
		this.accion = string;
	}

}

class PnlOperaciones_add_actionAdapter implements java.awt.event.ActionListener {
	PnlOperaciones adaptee;

	PnlOperaciones_add_actionAdapter(PnlOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.add_actionPerformed(e);
	}
}

class PnlOperaciones_jButton1_actionAdapter implements ActionListener {
	private PnlOperaciones adaptee;

	PnlOperaciones_jButton1_actionAdapter(PnlOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.cmdBuscar_actionPerformed(e);
	}
}

class PnlOperaciones_cancel_actionAdapter implements
		java.awt.event.ActionListener {
	PnlOperaciones adaptee;

	PnlOperaciones_cancel_actionAdapter(PnlOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cancel_actionPerformed(e);
	}
}

class PnlOperaciones_delete_actionAdapter implements
		java.awt.event.ActionListener {
	PnlOperaciones adaptee;

	PnlOperaciones_delete_actionAdapter(PnlOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.delete_actionPerformed(e);
	}
}

class PnlOperaciones_ok_actionAdapter implements java.awt.event.ActionListener {
	PnlOperaciones adaptee;

	PnlOperaciones_ok_actionAdapter(PnlOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ok_actionPerformed(e);
	}
}

class PnlOperaciones_edit_actionAdapter implements
		java.awt.event.ActionListener {
	PnlOperaciones adaptee;

	PnlOperaciones_edit_actionAdapter(PnlOperaciones adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.edit_actionPerformed(e);
	}
}
