package com.boutique.view;

import com.boutique.dao.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.beans.*;
import java.awt.event.*;
import com.boutique.domain.*;

public class PnlCatalogoTipoDeProductos extends JPanel implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	PnlOperaciones pnlOperaciones1 = new PnlOperaciones();
	TipoProducto producto = new TipoProducto();
	Map<String, String[]> mapTipoTallas;
	java.util.List<TipoProducto> listaTipoProductos;
	DefaultListModel lModel = new DefaultListModel();
	DefaultListModel lModelTipoProductos = new DefaultListModel();
	JSplitPane jSplitPane1 = new JSplitPane();
	GridLayout gridLayout1 = new GridLayout();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel2 = new JLabel();
	JTextField txtNombre = new JTextField();
	JLabel jLabel1 = new JLabel();
	JList tipoTalla = new JList();
	JList lstTipoProductos = new JList();
	JPanel jPanel2 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JLabel jLabel3 = new JLabel();

	public PnlCatalogoTipoDeProductos() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		this.setLayout(borderLayout1);
		gridLayout1.setColumns(2);
		gridLayout1.setRows(2);
		jPanel1.setMaximumSize(new Dimension(32767, 32767));
		jPanel1.setLayout(gridLayout1);
		jLabel2.setText("Nombre del tipo de producto:");
		txtNombre.setText("");
		jLabel1.setText("Tipo de talla");
		tipoTalla.setModel(lModel);
		jSplitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		lstTipoProductos.setModel(lModelTipoProductos);
		lstTipoProductos
				.addMouseListener(new PnlDirectorioArticulos_lstArticulos_mouseAdapter(
						this));
		jPanel2.setLayout(borderLayout2);
		jLabel3.setText("Lista de tipo de productos");
		this.add(pnlOperaciones1, BorderLayout.SOUTH);
		this.add(jSplitPane1, BorderLayout.CENTER);
		jPanel1.add(jLabel2, null);
		jPanel1.add(txtNombre, null);
		jPanel1.add(jLabel1, null);
		jPanel1.add(tipoTalla, null);
		jSplitPane1.add(jPanel2, JSplitPane.TOP);
		jSplitPane1.add(jPanel1, JSplitPane.BOTTOM);
		jPanel2.add(lstTipoProductos, java.awt.BorderLayout.CENTER);
		jPanel2.add(jLabel3, java.awt.BorderLayout.NORTH);
		pnlOperaciones1.addPropertyChangeListener(this);
		mapTipoTallas = TipoTallas.getTipoTallas();
		// Tenemos el mapa de tallas, la ponemos
		for (String s : mapTipoTallas.keySet()) {
			lModel.addElement(s);
		}
		this.pnlOperaciones1.habilitarBotones();
		this.desHabilitarCajas();

		enlistarProductos();
	}

 	private void enlistarProductos() {
		// Ponemos los datos en la lista
		listaTipoProductos = (ArrayList<TipoProducto>) DaoTipoProductos.findAll();
		lModelTipoProductos.clear();
		for (TipoProducto p : listaTipoProductos) {
			lModelTipoProductos.addElement(p.getNombre());
		}

	}

	public void propertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName().equals("articulo")) {
			producto = (TipoProducto) e.getNewValue();
			mostrarDatosProductoSeleccionado();
		} else if (e.getPropertyName().equals("accion")) {
			if (e.getNewValue().equals("add")) {
				this.limpiarCajas();
				this.habilitarCajas();
				this.txtNombre.requestFocus();
			} else if (e.getNewValue().equals("edit")) {
				this.habilitarCajas();
				this.txtNombre.requestFocus();
			} else if (e.getNewValue().equals("delete")) {
				DaoTipoProductos.remove(producto);
				enlistarProductos();

			} else if (e.getNewValue().equals("ok")) {
				if (e.getOldValue().equals("add")) {
					this.setDatosArticulo();
					if (!DaoTipoProductos.add(producto)) {
						JOptionPane.showMessageDialog(null,
								"No se pudo agregar el tipo de producto");
						this.pnlOperaciones1.setAccionExitosa(false);
					} else {
						this.limpiarCajas();
						this.desHabilitarCajas();
						enlistarProductos();
						this.pnlOperaciones1.setAccionExitosa(true);
					}

				} else if (e.getOldValue().equals("edit")) {
					this.setDatosArticulo();
					if (!DaoTipoProductos.update(producto)) {
						JOptionPane
								.showMessageDialog(null,
										"No se pudo actualizar el registro del tipo de producto");
						this.pnlOperaciones1.setAccionExitosa(false);
					} else {
						this.desHabilitarCajas();
						this.pnlOperaciones1.setAccionExitosa(true);
						enlistarProductos();
					}

				}
			} else if (e.getNewValue().equals("cancel")) {
				this.desHabilitarCajas();
				this.limpiarCajas();
			}

		}

	}

	/**
	 * desHabilitarCajas
	 */
	private void desHabilitarCajas() {
		this.txtNombre.setEnabled(false);
		this.tipoTalla.setEnabled(false);
	}

	/**
	 * setDatosCheque
	 */
	private void setDatosArticulo() {
		// Sacamos le producto seleccionado
		if (tipoTalla.getSelectedIndex() >= 0) {
			producto.setTipoTalla(String.valueOf(tipoTalla.getSelectedValue()));
		} else {
			JOptionPane
					.showMessageDialog(this.getRootPane(),
							"Selecciona el tipo de talla a utilizar para este producto");
			return;
		}
		producto.setNombre(txtNombre.getText().toUpperCase());

	}

	/**
	 * habilitarCajas
	 */
	private void habilitarCajas() {
		this.txtNombre.setEnabled(true);
		this.tipoTalla.setEnabled(true);

	}

	/**
	 * limpiarCajas
	 */
	private void limpiarCajas() {
		this.txtNombre.setText("");
		this.tipoTalla.setSelectedIndex(-1);

	}

	/**
	 * getDatosArticulo
	 */
	private void mostrarDatosProductoSeleccionado() {
		// Obtenemos los datos del producto seleccionado

		producto = this.listaTipoProductos.get(this.lstTipoProductos
				.getSelectedIndex());
		txtNombre.setText(producto.getNombre());
		tipoTalla.setSelectedValue(producto.getTipoTalla(), true);
	}

	void lstArticulos_mouseClicked(MouseEvent e) {
		// articulo =
		// (Articulo)this.arrArticulos.get(lstArticulos.getSelectedIndex());
		this.mostrarDatosProductoSeleccionado();
	}
}

class PnlDirectorioArticulos_lstArticulos_mouseAdapter extends
		java.awt.event.MouseAdapter {
	PnlCatalogoTipoDeProductos adaptee;

	PnlDirectorioArticulos_lstArticulos_mouseAdapter(
			PnlCatalogoTipoDeProductos adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.lstArticulos_mouseClicked(e);
	}
}
