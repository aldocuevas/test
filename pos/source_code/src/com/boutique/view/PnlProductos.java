package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.dao.DaoBoutique;
import com.boutique.dao.DaoInventarios;
import com.boutique.domain.Terminal;

import java.util.*;

import com.boutique.engine.impl.AppInstance;

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
public class PnlProductos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlTop = new JPanel();
	JLabel jLabel1 = new JLabel();
	JTextField txtEtiqueta = new JTextField();
	JButton cmdBuscar = new JButton();
	BorderLayout borderLayout2 = new BorderLayout();
	JScrollPane scrollDatos = new JScrollPane();
	JTable tblDatos = new JTable();
	ModelBusquedaProducto modelDatosProducto1 = new ModelBusquedaProducto();
	JPopupMenu mnuModificarProducto = new JPopupMenu();
	JMenuItem mnuCambiarCantidad = new JMenuItem();
	java.util.List<Object> datos = new ArrayList<Object>();

	public PnlProductos() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setLayout(borderLayout1);
		jLabel1.setText("Etiqueta del producto:");
		txtEtiqueta.setText("");
		txtEtiqueta
				.addActionListener(new PnlProductos_txtEtiqueta_actionAdapter(
						this));
		cmdBuscar.setMnemonic('B');
		cmdBuscar.setText("Buscar");
		cmdBuscar.addActionListener(new PnlProductos_cmdBuscar_actionAdapter(
				this));
		pnlTop.setLayout(borderLayout2);
		tblDatos.setModel(modelDatosProducto1);
		tblDatos.addMouseListener(new PnlProductos_tblDatos_mouseAdapter(this));
		mnuCambiarCantidad
				.addActionListener(new PnlProductos_mnuCambiarCantidad_actionAdapter(
						this));
		mnuCambiarCantidad.setText("CAMBIAR CANTIDAD");
		this.add(pnlTop, java.awt.BorderLayout.NORTH);
		pnlTop.add(txtEtiqueta, java.awt.BorderLayout.CENTER);
		pnlTop.add(jLabel1, java.awt.BorderLayout.WEST);
		pnlTop.add(cmdBuscar, java.awt.BorderLayout.EAST);
		this.add(scrollDatos, java.awt.BorderLayout.CENTER);
		scrollDatos.getViewport().add(tblDatos);
		mnuModificarProducto.add(mnuCambiarCantidad);
	}

	public void txtEtiqueta_actionPerformed(ActionEvent e) {
		String etiqueta = txtEtiqueta.getText().replace('\'', '/');
		etiqueta = etiqueta.replace("-", "/");
		mostrarResultados(etiqueta);

	}

	/**
	 * mostrarResultados
	 * 
	 * @param etiqueta
	 *            String
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void mostrarResultados(String etiqueta) {
		java.util.List[] listas = DaoInventarios.findByEtiquetaAll(etiqueta);
		this.modelDatosProducto1.data = listas[0];
		datos = listas[1];
		this.txtEtiqueta.setText("");
		this.txtEtiqueta.requestFocus();
		this.modelDatosProducto1.fireTableDataChanged();
	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {
		String etiqueta = txtEtiqueta.getText().replace('\'', '/');
		etiqueta = txtEtiqueta.getText().replace('-', '/');
		mostrarResultados(etiqueta);
	}

	public void mnuCambiarCantidad_actionPerformed(ActionEvent e) {
		// AQUI AGREGAMOS EL CODIGO PARA MODIFICAR LA CANTIDAD DE LOS PRODUCTOS
		Object[] row = (Object[]) (datos.get(this.tblDatos.getSelectedRow()));
		String respuesta = JOptionPane.showInputDialog(this.getRootPane(),
				"Indica la nueva cantidad en el inventario", row[3].toString());
		try {
			int cantidad = Integer.parseInt(respuesta);
			// "Codigo","Descripcion", "Cantidad",
			// "Boutique","Precio",""idbotuqieu};
			int idTerminal=0;
			if(AppInstance.terminal()==null){
				Terminal terminal = DaoBoutique.findLegacyTerminal();
				idTerminal=terminal.getId();
			}else{
				idTerminal = AppInstance.terminal().getId();
			}
			if (DaoInventarios
					.cambiarCantidad(row[0].toString(), Integer.parseInt(row[3]
							.toString()), cantidad, Integer.parseInt(row[6]
							.toString()), AppInstance.usuario().getId(),idTerminal)) {

				JOptionPane.showMessageDialog(this.getRootPane(),
						"CANTIDAD MODIFICADA EXITOSAMENTE",
						AppInstance.nombreNegocio,
						JOptionPane.INFORMATION_MESSAGE);
				mostrarResultados(row[0].toString());
			} else {
				JOptionPane.showMessageDialog(this.getRootPane(),
						"ERROR AL HACER LA ACTUALIZACION DE CANTIDAD",
						AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this.getRootPane(),
					"INDIQUE UNA CANTIDAD VALIDA", AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void tblDatos_mouseClicked(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON2
				|| e.getButton() == MouseEvent.BUTTON3)
				&& com.boutique.engine.impl.AppInstance.usuario().getModificarCantidadInventarios() == 1) {
			this.mnuModificarProducto.show(tblDatos, e.getX(), e.getY());

		}
	}

}

class PnlProductos_mnuCambiarCantidad_actionAdapter implements ActionListener {
	private PnlProductos adaptee;

	PnlProductos_mnuCambiarCantidad_actionAdapter(PnlProductos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.mnuCambiarCantidad_actionPerformed(e);
	}
}

class PnlProductos_tblDatos_mouseAdapter extends MouseAdapter {
	private PnlProductos adaptee;

	PnlProductos_tblDatos_mouseAdapter(PnlProductos adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.tblDatos_mouseClicked(e);
	}
}

class PnlProductos_cmdBuscar_actionAdapter implements ActionListener {
	private PnlProductos adaptee;

	PnlProductos_cmdBuscar_actionAdapter(PnlProductos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}

class PnlProductos_txtEtiqueta_actionAdapter implements ActionListener {
	private PnlProductos adaptee;

	PnlProductos_txtEtiqueta_actionAdapter(PnlProductos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtEtiqueta_actionPerformed(e);
	}
}
