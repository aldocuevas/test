package com.boutique.view;

import java.beans.*;
import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.borland.jbcl.layout.*;
import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.engine.impl.*;

public class FrmRegistrarProductoExistente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NumberFormat numF = NumberFormat.getNumberInstance(Locale.ENGLISH);
	RegistroInventarioEngine engine;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	JButton cmdRegistrarProducto = new JButton();
	JButton cmdCancelar = new JButton();
	JPanel jPanel2 = new JPanel();
	VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
	JLabel jLabel1 = new JLabel();
	JTextField txtCodigoProducto = new JTextField();
	JPanel pnlDatosAnteriores = new JPanel();
	VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
	JLabel jLabel2 = new JLabel();
	JTextField txtTipoProducto = new JTextField();
	JPanel jPanel4 = new JPanel();
	JLabel jLabel3 = new JLabel();
	JTextField txtPrecioPublicoRegistrado = new JTextField();
	JLabel jLabel4 = new JLabel();
	JTextField txtPrecioCostoRegistrado = new JTextField();
	GridLayout gridLayout1 = new GridLayout();
	JLabel jLabel5 = new JLabel();
	JTextField txtCostoNuevo = new JTextField();
	JPanel jPanel5 = new JPanel();
	JLabel jLabel6 = new JLabel();
	JTextField txtPrecio100 = new JTextField();
	JLabel jLabel7 = new JLabel();
	JTextField txtPrecio80 = new JTextField();
	JLabel jLabel8 = new JLabel();
	JTextField txtPrecio120 = new JTextField();
	JLabel jLabel9 = new JLabel();
	JTextField txtPrecioAl140 = new JTextField();
	GridLayout gridLayout2 = new GridLayout();
	JLabel jLabel10 = new JLabel();
	JTextField txtPrecioAlPublico = new JTextField();
	JSplitPane split = new JSplitPane();
	JScrollPane scroll = new JScrollPane();
	JTable tblProductosPorTalla = new JTable();
	ModelProductosPorTalla modelProductosPorTalla1 = new ModelProductosPorTalla();
	Producto p;

	public FrmRegistrarProductoExistente() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		tblProductosPorTalla.setModel(modelProductosPorTalla1);
		txtCodigoProducto
				.addActionListener(new FrmRegistrarProductoExistente_txtCodigoProducto_actionAdapter(
						this));
		txtCostoNuevo
				.addActionListener(new FrmRegistrarProductoExistente_txtCostoNuevo_actionAdapter(
						this));
		cmdCancelar
				.addActionListener(new FrmRegistrarProductoExistente_cmdCancelar_actionAdapter(
						this));
		cmdRegistrarProducto
				.addMouseListener(new FrmRegistrarProductoExistente_cmdRegistrarProducto_mouseAdapter(
						this));
		cmdCancelar.setActionCommand("Cerrar");
		cmdRegistrarProducto.setMnemonic('R');
		txtTipoProducto.setEnabled(false);
		txtPrecioCostoRegistrado.setEnabled(false);
		txtPrecioPublicoRegistrado.setEnabled(false);
		txtPrecio80.setEnabled(false);
		txtPrecio100.setEnabled(false);
		txtPrecio120.setEnabled(false);
		txtPrecioAl140.setEnabled(false);
		scroll.getViewport().add(tblProductosPorTalla);
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().setLayout(borderLayout1);
		this.setTitle("Registrar producto existente");
		cmdRegistrarProducto.setText("Registrar producto");
		cmdCancelar.setText("Cancelar");
		jPanel2.setLayout(verticalFlowLayout1);
		jLabel1.setText("Introduce el código del producto:");
		txtCodigoProducto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtCodigoProducto.setText("");
		pnlDatosAnteriores.setLayout(verticalFlowLayout2);
		jLabel2.setText("Tipo de producto:");
		txtTipoProducto.setText("");
		jLabel3.setText("Precio al costo registrado:");
		txtPrecioPublicoRegistrado.setText("");
		jLabel4.setText("Precio al público registrado");
		txtPrecioCostoRegistrado.setText("");
		jPanel4.setLayout(gridLayout1);
		gridLayout1.setColumns(2);
		gridLayout1.setRows(2);
		jLabel5.setText("Precio al costo de la nueva adquisición:");
		txtCostoNuevo.setText("");
		jLabel6.setText("Precio al 100%");
		txtPrecio100.setText("");
		jLabel7.setText("Precio al 120%");
		txtPrecio80.setText("");
		jLabel8.setText("Precio al 140%");
		txtPrecio120.setText("");
		jLabel9.setText("Precio al 80%");
		txtPrecioAl140.setText("");
		jPanel5.setLayout(gridLayout2);
		gridLayout2.setColumns(4);
		gridLayout2.setRows(2);
		jLabel10.setText("Precio al público de la nueva adquisición");
		txtPrecioAlPublico.setText("");
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);
		jPanel1.add(cmdRegistrarProducto);
		jPanel1.add(cmdCancelar);
		this.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
		jPanel2.add(jLabel1);
		jPanel2.add(txtCodigoProducto);
		this.getContentPane().add(split, java.awt.BorderLayout.CENTER);
		pnlDatosAnteriores.add(jLabel2);
		pnlDatosAnteriores.add(txtTipoProducto);
		pnlDatosAnteriores.add(jPanel4);
		pnlDatosAnteriores.add(jLabel5);
		pnlDatosAnteriores.add(txtCostoNuevo);
		pnlDatosAnteriores.add(jPanel5);
		pnlDatosAnteriores.add(jLabel10);
		pnlDatosAnteriores.add(txtPrecioAlPublico);
		jPanel5.add(jLabel9);
		jPanel5.add(jLabel6);
		jPanel5.add(jLabel7);
		jPanel5.add(jLabel8);
		jPanel5.add(txtPrecio80);
		jPanel5.add(txtPrecio100);
		jPanel5.add(txtPrecio120);
		jPanel5.add(txtPrecioAl140);
		jPanel4.add(jLabel3);
		jPanel4.add(jLabel4);
		jPanel4.add(txtPrecioCostoRegistrado);
		jPanel4.add(txtPrecioPublicoRegistrado);
		split.add(pnlDatosAnteriores, JSplitPane.TOP);
		split.add(scroll, JSplitPane.BOTTOM);
		numF.setMaximumFractionDigits(2);
		numF.setMinimumFractionDigits(2);
	}

	public void jTextField1_actionPerformed(ActionEvent e) {

	}

	public void txtCodigoProducto_actionPerformed(ActionEvent e) {
		// Buscamos el codigo en la base de datos.
		p = DaoInventarios.findByEtiqueta(txtCodigoProducto.getText());
		if (p != null) {

			this.txtTipoProducto.setText(String.valueOf(this.engine
					.getIdToTipoProducto().get(
							String.valueOf(p.getIdTipoProducto())))
					+ " " + Producto.detalleProducto(p));
			this.txtPrecioCostoRegistrado.setText(numF.format(p
					.getPrecioCosto()));
			this.txtPrecioPublicoRegistrado.setText(numF.format(p
					.getPrecioPublico()));
			this.txtCostoNuevo.requestFocus();
			// Ponemos la tablita con lo de las tallas para que el usuario
			// indique cuantos nuevos de cada talla habra
			String tipoTalla = DaoTipoProductos.findTipoTallaFromId(p
					.getIdTipoProducto());
			this.modelProductosPorTalla1.setTipoTallas(tipoTalla,null);
		}
		txtCodigoProducto.setText("");
	}

	public void txtCostoNuevo_actionPerformed(ActionEvent e) {
		// Tenemos el costo nuevo
		// Revisamos que sea numero
		try {
			numF.parse(txtCostoNuevo.getText());
			double costo = Double.parseDouble(txtCostoNuevo.getText());
			txtPrecio80.setText(numF.format(costo * 1.80));
			txtPrecio100.setText(numF.format(costo * 2));
			txtPrecio120.setText(numF.format(costo + costo * 1.2));
			txtPrecioAl140.setText(numF.format(costo + costo * 1.4));
			this.txtPrecioAlPublico.setText(numF.format(costo * 2));
			txtPrecioAlPublico.requestFocus();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,
					"Introduzca un formato de numero valido", "Atencion",
					JOptionPane.ERROR_MESSAGE);
			System.out.println(ex.toString());
			txtCostoNuevo.setText("");
			txtCostoNuevo.requestFocus();
		}

	}

	public void cmdCancelar_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void cmdRegistrarProducto_mouseClicked(MouseEvent e) {
		// Revisamos que el precio de costo y precio de publico existan asi como
		// que la cantidad por cada
		try {
			// Revisamos que haya precio de costo
			double precioCosto = numF.parse(txtCostoNuevo.getText())
					.doubleValue();
			// Revisamos que haya precio al publico
			double precioPublico = numF.parse(txtPrecioAlPublico.getText())
					.doubleValue();
			if (this.modelProductosPorTalla1.siHayProductos()) {

				// Revisamos si el nuevo precio al publico es diferente.
				if (Double.parseDouble(this.txtPrecioAlPublico.getText()) != p
						.getPrecioPublico()) {
					if (JOptionPane
							.showConfirmDialog(
									this,
									"Se registrará como nuevo producto debido a que el precio al público ha cambiado",
									"Nuevo producto",
									JOptionPane.INFORMATION_MESSAGE,
									JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
						TipoProducto tp = DaoTipoProductos.findById(p
								.getIdTipoProducto());
						/*if (this.engine.registrarProducto(tp, precioCosto,
								precioPublico, modelProductosPorTalla1.data,
								"", 0, 0, 0)) {
							// Se agregaron los registros, refrescamos la
							// ventana grande y preguntamos si deseamos agregar
							// otro producto
							limpiarCasillas();
							String oldAccion;
							oldAccion = this.accion;
							this.firePropertyChange("actualizarTabla",
									oldAccion, this.accion);

						} else {
							JOptionPane
									.showMessageDialog(
											this,
											"El producto no pudo ser registrado en el inventario inicial",
											"Error", JOptionPane.ERROR_MESSAGE);
						}*/
					}
				} else {
					// Agregamos como producto existente.
					if (this.engine.registrarProductoExistente(p, precioCosto,
							precioPublico, modelProductosPorTalla1.data)) {
						// Se agregaron los registros, refrescamos la ventana
						// grande y preguntamos si deseamos agregar otro
						// producto
						limpiarCasillas();
						String oldAccion;
						oldAccion = this.accion;
						this.accion = String.valueOf(this.engine
								.getProductosRegistrados().size());

						this.firePropertyChange("actualizarTabla", oldAccion,
								this.accion);
					} else {
						JOptionPane
								.showMessageDialog(
										this,
										"El producto no pudo ser registrado en el inventario inicial",
										"Error", JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		}
		// Tenemos todos los productos del modelo registrados

		catch (ParseException ex) {
			JOptionPane.showMessageDialog(this, "Precios incorrectos",
					"Atencion", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * limpiarCasillas
	 */
	private void limpiarCasillas() {
		this.txtCostoNuevo.setText("");
		this.txtPrecioAl140.setText("");
		this.txtPrecio80.setText("");
		this.txtPrecio100.setText("");
		this.txtPrecio120.setText("");
		this.txtPrecioAlPublico.setText("");
		this.txtPrecioCostoRegistrado.setText("");
		this.txtPrecioPublicoRegistrado.setText("");
		this.txtTipoProducto.setText("");
	}

	@SuppressWarnings("rawtypes")
	transient Vector propertyChangeListeners;
	private String accion;

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

}

class FrmRegistrarProductoExistente_cmdRegistrarProducto_mouseAdapter extends
		MouseAdapter {
	private FrmRegistrarProductoExistente adaptee;

	FrmRegistrarProductoExistente_cmdRegistrarProducto_mouseAdapter(
			FrmRegistrarProductoExistente adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.cmdRegistrarProducto_mouseClicked(e);
	}
}

class FrmRegistrarProductoExistente_cmdCancelar_actionAdapter implements
		ActionListener {
	private FrmRegistrarProductoExistente adaptee;

	FrmRegistrarProductoExistente_cmdCancelar_actionAdapter(
			FrmRegistrarProductoExistente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelar_actionPerformed(e);
	}
}

class FrmRegistrarProductoExistente_txtCostoNuevo_actionAdapter implements
		ActionListener {
	private FrmRegistrarProductoExistente adaptee;

	FrmRegistrarProductoExistente_txtCostoNuevo_actionAdapter(
			FrmRegistrarProductoExistente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtCostoNuevo_actionPerformed(e);
	}
}

class FrmRegistrarProductoExistente_txtCodigoProducto_actionAdapter implements
		ActionListener {
	private FrmRegistrarProductoExistente adaptee;

	FrmRegistrarProductoExistente_txtCodigoProducto_actionAdapter(
			FrmRegistrarProductoExistente adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.txtCodigoProducto_actionPerformed(e);
	}
}
