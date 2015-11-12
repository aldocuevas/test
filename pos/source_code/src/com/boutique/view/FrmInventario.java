package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
public class FrmInventario extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	BorderLayout borderLayout1 = new BorderLayout();
	JMenuBar jMenuBar1 = new JMenuBar();
	JLabel statusBar = new JLabel();
	JPanel jPanel1 = new JPanel();
	JButton cmdAgregarInventario = new JButton();
	JButton cmdDividirInventario = new JButton();
	JButton cmdProveedores = new JButton();
	JButton cmdCatalogoTipoDeProductos = new JButton();
	JButton cmdGenerarArchivoInventario = new JButton();
	GridLayout gridLayout1 = new GridLayout();
	JButton cmdRealizarInventario = new JButton();
	JButton cmdBuscarProducto = new JButton();
	JButton cmdBuscar = new JButton();

	public FrmInventario() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 * 
	 * @throws java.lang.Exception
	 */
	private void jbInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(borderLayout1);
		setSize(new Dimension(458, 300));
		setTitle("Registro de inventarios");
		this.addWindowListener(new FrmInventario_this_windowAdapter(this));
		statusBar.setText(" ");
		cmdAgregarInventario.setText("1. Registro de inventario");
		cmdAgregarInventario
				.addActionListener(new FrmInventario_cmdAgregarInventario_actionAdapter(
						this));
		cmdDividirInventario.setText("2. Dividir inventario");
		cmdDividirInventario
				.addActionListener(new FrmInventario_cmdDividirInventario_actionAdapter(
						this));
		cmdProveedores.setText("Proveedores");
		cmdProveedores
				.addActionListener(new FrmInventario_cmdProveedores_actionAdapter(
						this));
		cmdCatalogoTipoDeProductos
				.setActionCommand("Catalogo de tipo de producto");
		cmdCatalogoTipoDeProductos.setText("Tipo de productos");
		cmdCatalogoTipoDeProductos
				.addActionListener(new FrmInventario_cmdCatalogoTipoDeProductos_actionAdapter(
						this));
		cmdGenerarArchivoInventario.setEnabled(true);
		cmdGenerarArchivoInventario.setText("3. Distribuir inventario");
		cmdGenerarArchivoInventario
				.addActionListener(new FrmInventario_cmdActualizarInventarioRemoto_actionAdapter(
						this));
		jPanel1.setLayout(gridLayout1);
		gridLayout1.setColumns(3);
		gridLayout1.setRows(4);
		setJMenuBar(jMenuBar1);
		jMenuBar1.add(new AboutJPanel());
		cmdRealizarInventario.setText("Realizar inventario");
		cmdRealizarInventario
				.addActionListener(new FrmInventario_cmdRealizarInventario_actionAdapter(
						this));
		cmdBuscarProducto.setText("Modificar precios");
		cmdBuscarProducto
				.addActionListener(new FrmInventario_cmdBuscarProducto_actionAdapter(
						this));
		cmdBuscar.setText("Buscar Producto");
		cmdBuscar.addActionListener(new FrmInventario_cmdBuscar_actionAdapter(
				this));
		contentPane.add(statusBar, java.awt.BorderLayout.NORTH);
		contentPane.add(jPanel1, java.awt.BorderLayout.CENTER);
		jPanel1.add(cmdAgregarInventario, null);
		jPanel1.add(cmdDividirInventario, null);
		jPanel1.add(cmdGenerarArchivoInventario, null);
		jPanel1.add(cmdCatalogoTipoDeProductos, null);
		jPanel1.add(cmdProveedores, null);
		jPanel1.add(cmdRealizarInventario);
		jPanel1.add(cmdBuscarProducto);
		jPanel1.add(cmdBuscar);
	}

	/**
	 * File | Exit action performed.
	 * 
	 * @param actionEvent
	 *            ActionEvent
	 */
	void jMenuFileExit_actionPerformed(ActionEvent actionEvent) {
		System.exit(0);
	}

	public void cmdProveedores_actionPerformed(ActionEvent e) {
		FrmCatalogoProveedores frm = new FrmCatalogoProveedores();
		frm.pack();
		frm.setExtendedState(MAXIMIZED_BOTH);
		frm.setVisible(true);
	}

	public void cmdCatalogoTipoDeProductos_actionPerformed(ActionEvent e) {
		FrmCatalogoTipoProductos frm = new FrmCatalogoTipoProductos();
		frm.pack();
		frm.setExtendedState(MAXIMIZED_BOTH);
		frm.setVisible(true);
	}

	public void cmdAgregarInventario_actionPerformed(ActionEvent e) {
		FrmRegistroInventario frm = new FrmRegistroInventario();
		frm.setLocationRelativeTo(this);
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
	}

	public void cmdDividirInventario_actionPerformed(ActionEvent e) {
		FrmDividirInventario frm = new FrmDividirInventario();
		frm.pack();
		frm.setExtendedState(MAXIMIZED_BOTH);
		frm.setVisible(true);
	}

	public void cmdActualizarInventarioRemoto_actionPerformed(ActionEvent e) {
		FrmDistribuirInventario frm = new FrmDistribuirInventario();
		frm.pack();
		frm.setVisible(true);
	}

	public void cmdCatalogoColonias_mouseClicked(MouseEvent e) {
		FrmCatalogoColonias frm = new FrmCatalogoColonias();
		frm.setLocationRelativeTo(this);
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);

	}

	public void cmdRealizarInventario_actionPerformed(ActionEvent e) {
		FrmRealizarInventario frm = new FrmRealizarInventario();
		frm.setLocationRelativeTo(this);
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
	}

	public void cmdBuscarProducto_actionPerformed(ActionEvent e) {
		FrmBusquedaModificacionProducto frm = new FrmBusquedaModificacionProducto();
		frm.pack();
		frm.setSize(700, 600);
		frm.setVisible(true);
	}

	public void this_windowOpened(WindowEvent e) {
		// AQUI EJECUTAMOS EL INICIO DE SESION
		DlgIniciarSesion dlg = new DlgIniciarSesion(this,
				com.boutique.engine.impl.AppInstance.nombreNegocio, true);
		dlg.setLocationRelativeTo(this);
		dlg.setSize(225, 195);
		dlg.setVisible(true);
		if (!dlg.validado) {

			JOptionPane
					.showMessageDialog(
							this,
							"Debe introducir un usuario y contraseña válidos, la aplicación se cerrará",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			System.exit(0);
		} else {
			if (dlg.usuario.getInventarios() == 0) {
				JOptionPane
						.showMessageDialog(
								this,
								"Su cuenta no tiene privilegios para controlar inventarios",
								com.boutique.engine.impl.AppInstance.nombreNegocio,
								JOptionPane.ERROR_MESSAGE);
				this.setVisible(false);
				System.exit(0);

			}
		}

	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {
		FrmBusquedaProducto frm = new FrmBusquedaProducto();
		frm.pack();
		frm.setSize(650, 300);
		frm.setLocationRelativeTo(this);
		// frm.setExtendedState(frm.MAXIMIZED_BOTH);
		frm.setVisible(true);
	}
}

class FrmInventario_cmdBuscar_actionAdapter implements ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdBuscar_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}

class FrmInventario_cmdBuscarProducto_actionAdapter implements ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdBuscarProducto_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscarProducto_actionPerformed(e);
	}
}

class FrmInventario_cmdRealizarInventario_actionAdapter implements
		ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdRealizarInventario_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdRealizarInventario_actionPerformed(e);
	}
}

class FrmInventario_cmdDividirInventario_actionAdapter implements
		ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdDividirInventario_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdDividirInventario_actionPerformed(e);
	}
}

class FrmInventario_cmdActualizarInventarioRemoto_actionAdapter implements
		ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdActualizarInventarioRemoto_actionAdapter(
			FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdActualizarInventarioRemoto_actionPerformed(e);
	}
}

class FrmInventario_this_windowAdapter extends WindowAdapter {
	private FrmInventario adaptee;

	FrmInventario_this_windowAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmInventario_cmdAgregarInventario_actionAdapter implements
		ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdAgregarInventario_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarInventario_actionPerformed(e);
	}
}

class FrmInventario_cmdProveedores_actionAdapter implements ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdProveedores_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdProveedores_actionPerformed(e);
	}
}

class FrmInventario_cmdCatalogoTipoDeProductos_actionAdapter implements
		ActionListener {
	private FrmInventario adaptee;

	FrmInventario_cmdCatalogoTipoDeProductos_actionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCatalogoTipoDeProductos_actionPerformed(e);
	}
}

class FrmInventario_jMenuFileExit_ActionAdapter implements ActionListener {
	FrmInventario adaptee;

	FrmInventario_jMenuFileExit_ActionAdapter(FrmInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent) {
		adaptee.jMenuFileExit_actionPerformed(actionEvent);
	}
}
