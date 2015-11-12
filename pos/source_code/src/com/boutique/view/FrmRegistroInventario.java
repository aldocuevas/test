package com.boutique.view;

import java.beans.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;

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
public class FrmRegistroInventario extends JFrame implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RegistroInventarioEngine engine = new RegistroInventarioEngine();
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel pnlDatosCompra = new JPanel();
	JLabel jLabel2 = new JLabel();
	JLabel lblFecha = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel lblProveedor = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel lblReferencia = new JLabel();
	JPanel pnlTabla = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	JPanel pnlBotonesAgregar = new JPanel();
	JButton cmdRegistrarProductoExistente = new JButton();
	JButton cmdAgregarProducto = new JButton();
	FrmAgregarProductoNuevo frmAgregarNuevoProducto = new FrmAgregarProductoNuevo();
	FrmRegistrarProductoExistente frmRegistrarProductoExistente = new FrmRegistrarProductoExistente();
	Compra compra;
	JPanel pnlTablaProductosRegistrados = new JPanel();
	BorderLayout borderLayout4 = new BorderLayout();
	JScrollPane scroll = new JScrollPane();
	JTable tblProductosRegistrados = new JTable();
	ModelProductosRegistrados modelProductosRegistrados1 = new ModelProductosRegistrados();
	JPanel jPanel2 = new JPanel();
	JButton cmdImprimirEtiquetasTukStick = new JButton();
	ImageIcon imageImprimir = new ImageIcon();
	ImageIcon imageIcon2 = new ImageIcon();
	JButton cmdCerrarCompra = new JButton();
	JButton cmdMargenesImpresion = new JButton();
	JPopupMenu popOperaciones = new JPopupMenu();
	JPopupMenu popImprimirUnaEtiqueta = new JPopupMenu();
	JMenuItem mnuCambiarCantidad = new JMenuItem();
	JMenuItem mnuCambiarPrecio = new JMenuItem();
	JMenuItem mnuEliminar = new JMenuItem();
	JButton cmdImprimirJanel = new JButton();
	JMenuItem mnuImprimirUnaEtiqueta = new JMenuItem();

	public FrmRegistroInventario() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		mnuCambiarCantidad.setText("Cambiar cantidad");
		mnuCambiarCantidad
				.addActionListener(new FrmRegistroInventario_mnuEditar_actionAdapter(
						this));
		mnuCambiarPrecio.setToolTipText("");
		mnuCambiarPrecio.setText("Cambiar precio");
		mnuCambiarPrecio
				.addActionListener(new FrmRegistroInventario_mnuCambiarPrecio_actionAdapter(
						this));
		mnuCambiarPrecio.setEnabled(false);
		this.getContentPane().setBackground(SystemColor.controlLtHighlight);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Inventario inicial");
		this.addWindowListener(new FrmRegistroInventario_this_windowAdapter(
				this));
		jLabel1.setToolTipText("");
		jLabel1.setText("REGISTRO DE INVENTARIOS");
		pnlDatosCompra.setMinimumSize(new Dimension(35, 30));
		pnlDatosCompra.setPreferredSize(new Dimension(35, 40));
		pnlTabla.setLayout(borderLayout3);
		cmdRegistrarProductoExistente.setToolTipText("");
		cmdRegistrarProductoExistente.setText("Agregar producto existente");
		cmdRegistrarProductoExistente.setEnabled(false);

		cmdRegistrarProductoExistente
				.addMouseListener(new FrmRegistroInventario_cmdRegistrarProductoExistente_mouseAdapter(
						this));
		cmdAgregarProducto.setHorizontalTextPosition(SwingConstants.CENTER);
		cmdAgregarProducto.setText("Agregar producto nuevo");
		cmdAgregarProducto
				.addActionListener(new FrmRegistroInventario_cmdAgregarProducto_actionAdapter(
						this));
		pnlTablaProductosRegistrados.setLayout(borderLayout4);
		tblProductosRegistrados.setModel(modelProductosRegistrados1);
		tblProductosRegistrados
				.addMouseListener(new FrmRegistroInventario_tblProductosRegistrados_mouseAdapter(
						this));
		jPanel1.setLayout(borderLayout2);
		cmdImprimirEtiquetasTukStick
				.addMouseListener(new FrmRegistroInventario_cmdImprimirEtiquetas_mouseAdapter(
						this));
		cmdCerrarCompra.setText("Cerrar la compra");
		cmdCerrarCompra
				.addMouseListener(new FrmRegistroInventario_cmdSeleccionarImpresora_mouseAdapter(
						this));
		cmdMargenesImpresion.setText("Indicar margenes de impresion");
		cmdMargenesImpresion
				.addActionListener(new FrmRegistroInventario_cmdMargenesImpresion_actionAdapter(
						this));
		mnuEliminar.setText("Eliminar producto");
		mnuEliminar
				.addActionListener(new FrmRegistroInventario_mnuEliminar_actionAdapter(
						this));
		cmdImprimirJanel.setText("Imprimir Janel");
		cmdImprimirJanel
				.addActionListener(new FrmRegistroInventario_cmdImprimirJanel_actionAdapter(
						this));
		mnuImprimirUnaEtiqueta.setText("Imprimir una etiqueta");
		mnuImprimirUnaEtiqueta
				.addActionListener(new FrmRegistroInventario_jMenuItem1_actionAdapter(
						this));

		this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
		jLabel2.setText("Referencia:");

		// lblFecha.setText(compra.getFecha().toString());
		jLabel4.setText("Fecha:");
		// lblProveedor.setText(engine.getProveedor().getNombre());
		jLabel6.setToolTipText("");
		jLabel6.setText("Proveedor:");
		// lblReferencia.setText(compra.getNoReferencia());
		pnlDatosCompra.add(jLabel6);
		pnlDatosCompra.add(lblProveedor);
		pnlDatosCompra.add(jLabel4);
		pnlDatosCompra.add(lblFecha);
		pnlDatosCompra.add(jLabel2);
		pnlDatosCompra.add(lblReferencia);
		jPanel1.add(pnlDatosCompra, java.awt.BorderLayout.NORTH);
		jPanel1.add(pnlTabla, java.awt.BorderLayout.CENTER);
		pnlBotonesAgregar.add(cmdAgregarProducto);
		pnlBotonesAgregar.add(cmdRegistrarProductoExistente);
		pnlTablaProductosRegistrados.add(scroll, java.awt.BorderLayout.CENTER);
		scroll.getViewport().add(tblProductosRegistrados);
		pnlTabla.add(pnlTablaProductosRegistrados, java.awt.BorderLayout.CENTER);
		pnlTabla.add(pnlBotonesAgregar, java.awt.BorderLayout.NORTH);
		this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel2.add(cmdImprimirJanel);
		jPanel2.add(cmdImprimirEtiquetasTukStick);
		jPanel2.add(cmdCerrarCompra);
		jPanel2.add(cmdMargenesImpresion);
		cmdImprimirEtiquetasTukStick.setText("Imprimir etiquetas Tuk-Stik");
		this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
		popOperaciones.add(mnuCambiarCantidad);
		popOperaciones.add(mnuCambiarPrecio);
		popOperaciones.add(mnuEliminar);
		this.popImprimirUnaEtiqueta.add(mnuImprimirUnaEtiqueta);

		frmRegistrarProductoExistente.pack();
		frmAgregarNuevoProducto.engine = this.engine;
		frmRegistrarProductoExistente.engine = this.engine;
		frmAgregarNuevoProducto.pack();
		frmAgregarNuevoProducto.setSize(600, 820);
		this.frmAgregarNuevoProducto.addPropertyChangeListener(this);
		this.frmRegistrarProductoExistente.addPropertyChangeListener(this);
	}

	public void cmdAgregarProducto_actionPerformed(ActionEvent e) {
		// frmAgregarNuevoProducto.setLocationByPlatform(true);
		frmAgregarNuevoProducto.setLocationRelativeTo(this);
		frmAgregarNuevoProducto.setVisible(true);
	}

	public void cmdRegistrarProductoExistente_mouseClicked(MouseEvent e) {
		if (cmdRegistrarProductoExistente.isEnabled()) {
			frmRegistrarProductoExistente.setLocationRelativeTo(this);
			this.frmRegistrarProductoExistente.setVisible(true);
		}
	}

	public void cmdImprimirEtiquetas_mouseClicked(MouseEvent e) {
		int[] rows = this.tblProductosRegistrados.getSelectedRows();
		if (rows.length > 0) {
			DlgImprimirEtiquetas dlg = new DlgImprimirEtiquetas(this, "", true);

			dlg.setSize(300, 200);
			dlg.setLocationRelativeTo(this);
			dlg.setVisible(true);
			if (dlg.posicion > 0) {
				this.engine.imprimirEtiquetas(dlg.posicion, rows[0],
						rows[rows.length - 1], 2);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"Si deseas imprimir, primero debes de elegir los productos de la lista",
							AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cmdSeleccionarImpresora_mouseClicked(MouseEvent e) {
		/*
		 * impresiones.HojaEtiquetas h = new
		 * impresiones.HojaEtiquetas(this.engine. getProductosRegistrados(), 1);
		 */
		if (JOptionPane
				.showConfirmDialog(
						this,
						"ESTA SEGURO QUE DESEA CERRAR LA COMPRA? SI LO HACE YA NO PODRA AGREGAR PRODUCTOS EN ESTA COMPRA",
						"Está seguro?", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
			// Cerramos la compra
			if (this.engine.cerrarCompraSeleccionada() == true) {
				JOptionPane.showMessageDialog(this,
						"Compra cerrada exitosamente", "Cierre de compra",
						JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this,
						"No se pudo cerrar la compra", "Cierre de compra",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void this_windowOpened(WindowEvent e) {
		DlgRegistroInventario dlg = new DlgRegistroInventario();
		dlg.engine = this.engine;
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		while (engine.getCompraSeleccionada() == null) {
			if (JOptionPane
					.showConfirmDialog(
							this,
							"Es necesario que selecciones una compra, ¿Deseas salir de esta función?",
							"Atencion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.setVisible(false);
				return;
			}
			dlg.setVisible(true);
		}
		this.modelProductosRegistrados1.setProductos(engine
				.getProductosRegistradosView());
		compra = engine.getCompraSeleccionada();
		lblFecha.setText(compra.getFecha().toString());
		lblProveedor.setText(engine.getProveedor().getNombre());
		lblReferencia.setText(compra.getNoReferencia());
	}

	/**
	 * This method gets called when a bound property is changed.
	 * 
	 * @param evt
	 *            A PropertyChangeEvent object describing the event source and
	 *            the property that has changed.
	 * @todo Implement this java.beans.PropertyChangeListener method
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("actualizarTabla")) {
			this.modelProductosRegistrados1.setProductos(engine
					.getProductosRegistradosView());
		}
	}

	public void cmdMargenesImpresion_actionPerformed(ActionEvent e) {
		DlgMargenesImpresion dlg = new DlgMargenesImpresion();
		dlg.setSize(280, 180);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
	}

	public void tblProductosRegistrados_mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (tblProductosRegistrados.getSelectedRows().length == 1) {
				this.popOperaciones.show(tblProductosRegistrados, e.getX(),
						e.getY());
			} else if (tblProductosRegistrados.getSelectedRows().length > 1) {
				this.popImprimirUnaEtiqueta.show(tblProductosRegistrados,
						e.getX(), e.getY());
			}
		}
	}

	public void mnuEditar_actionPerformed(ActionEvent e) {
		// Tenemos que editar el producto seleccionado
		// Vamos a cambiar la cantidad de productos
		engine.seleccionarProductoaEditar(this.tblProductosRegistrados
				.getSelectedRow());
		DlgCambiarCantidadProducto dlg = new DlgCambiarCantidadProducto();
		dlg.setSize(190, 130);
		dlg.engine = engine;
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.modelProductosRegistrados1.setProductos(engine
				.getProductosRegistradosView());
	}

	public void mnuCambiarPrecio_actionPerformed(ActionEvent e) {
		engine.seleccionarProductoaEditar(this.tblProductosRegistrados
				.getSelectedRow());
		DlgCambiarPrecio dlg = new DlgCambiarPrecio();
		dlg.setSize(250, 270);
		dlg.engine = engine;
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.modelProductosRegistrados1.setProductos(engine
				.getProductosRegistradosView());
	}

	public void mnuEliminar_actionPerformed(ActionEvent e) {
		int i = JOptionPane.showConfirmDialog(this,
				"¿Estás seguro que deseas eliminar el producto?", "Confirmar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (i == JOptionPane.YES_OPTION) {
			// Eliminamos el productoSeleccionado
			if (engine.eliminarProducto(this.tblProductosRegistrados
					.getSelectedRow())) {
				this.modelProductosRegistrados1.setProductos(engine
						.getProductosRegistradosView());
			} else {
				JOptionPane.showMessageDialog(this,
						"No se pudo eliminar el producto", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void cmdImprimirJanel_actionPerformed(ActionEvent e) {
		int[] rows = this.tblProductosRegistrados.getSelectedRows();
		if (rows.length > 0) {
			DlgImprimirEtiquetas dlg = new DlgImprimirEtiquetas(this, "", true);

			dlg.setSize(300, 200);
			dlg.setLocationRelativeTo(this);
			dlg.setVisible(true);
			if (dlg.posicion > 0) {
				this.engine.imprimirEtiquetas(dlg.posicion, rows[0],
						rows[rows.length - 1], 1);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"Si deseas imprimir, primero debes de elegir los productos de la lista",
							AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
		}
	}

	public void jMenuItem1_actionPerformed(ActionEvent e) {
		// IMPRIMIMOS UNA ETIQUETA
		int[] rows = this.tblProductosRegistrados.getSelectedRows();
		DlgImprimirEtiquetas dlg = new DlgImprimirEtiquetas(this, "", true);

		dlg.setSize(300, 200);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		if (dlg.posicion > 0) {
			this.engine.imprimirUnaEtiqueta(dlg.posicion, rows[0],
					rows[rows.length - 1]);
		}

	}
}

class FrmRegistroInventario_jMenuItem1_actionAdapter implements ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_jMenuItem1_actionAdapter(FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuItem1_actionPerformed(e);
	}
}

class FrmRegistroInventario_cmdImprimirJanel_actionAdapter implements
		ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_cmdImprimirJanel_actionAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdImprimirJanel_actionPerformed(e);
	}
}

class FrmRegistroInventario_mnuEditar_actionAdapter implements ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_mnuEditar_actionAdapter(FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuEditar_actionPerformed(e);
	}
}

class FrmRegistroInventario_mnuCambiarPrecio_actionAdapter implements
		ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_mnuCambiarPrecio_actionAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuCambiarPrecio_actionPerformed(e);
	}
}

class FrmRegistroInventario_mnuEliminar_actionAdapter implements ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_mnuEliminar_actionAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuEliminar_actionPerformed(e);
	}
}

class FrmRegistroInventario_tblProductosRegistrados_mouseAdapter extends
		MouseAdapter {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_tblProductosRegistrados_mouseAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.tblProductosRegistrados_mouseClicked(e);
	}
}

class FrmRegistroInventario_cmdMargenesImpresion_actionAdapter implements
		ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_cmdMargenesImpresion_actionAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdMargenesImpresion_actionPerformed(e);
	}
}

class FrmRegistroInventario_this_windowAdapter extends WindowAdapter {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_this_windowAdapter(FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmRegistroInventario_cmdSeleccionarImpresora_mouseAdapter extends
		MouseAdapter {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_cmdSeleccionarImpresora_mouseAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.cmdSeleccionarImpresora_mouseClicked(e);
	}
}

class FrmRegistroInventario_cmdImprimirEtiquetas_mouseAdapter extends
		MouseAdapter {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_cmdImprimirEtiquetas_mouseAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.cmdImprimirEtiquetas_mouseClicked(e);
	}
}

class FrmRegistroInventario_cmdRegistrarProductoExistente_mouseAdapter extends
		MouseAdapter {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_cmdRegistrarProductoExistente_mouseAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.cmdRegistrarProductoExistente_mouseClicked(e);
	}
}

class FrmRegistroInventario_cmdAgregarProducto_actionAdapter implements
		ActionListener {
	private FrmRegistroInventario adaptee;

	FrmRegistroInventario_cmdAgregarProducto_actionAdapter(
			FrmRegistroInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarProducto_actionPerformed(e);
	}
}
