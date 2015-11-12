package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.boutique.domain.Usuario;
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
public class FrmCatalogoUsuarios extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UsuariosEngine engine = new UsuariosEngine();
	DefaultListModel modelo = new DefaultListModel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlDatos = new JPanel();
	JPanel pnlIzquierdo = new JPanel();
	JScrollPane scrollUsuarios = new JScrollPane();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel pnlBuscar = new JPanel();
	JLabel lblBuscar = new JLabel();
	JTextField txtNombre = new JTextField();
	JButton cmdBuscar = new JButton();
	JList lstUsuarios = new JList();
	BorderLayout borderLayout3 = new BorderLayout();
	JPanel pnlBotones = new JPanel();
	JButton cmdCancelar = new JButton();
	JButton cmdAceptar = new JButton();
	JButton cmdEliminar = new JButton();
	JButton cmdModificar = new JButton();
	JButton cmdAgregar = new JButton();
	BorderLayout borderLayout4 = new BorderLayout();
	JPanel pnlCentro = new JPanel();
	BorderLayout borderLayout5 = new BorderLayout();
	JPanel pnlDatosCajas = new JPanel();
	JCheckBox chkActivo = new JCheckBox();
	JCheckBox chkAuditor = new JCheckBox();
	JCheckBox chkAdmin = new JCheckBox();
	JCheckBox chkVendedor = new JCheckBox();
	JCheckBox chkInventarios = new JCheckBox();
	JCheckBox chkEliminarPagos = new JCheckBox();
	JCheckBox chkAdminUsuarios = new JCheckBox();
	JCheckBox chkModificarCantidadInventarios = new JCheckBox();
	JCheckBox chkDevolucionesIlimitadas = new JCheckBox();
	JCheckBox chkExportarClientes = new JCheckBox();
	JCheckBox jCheckBox1 = new JCheckBox();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JTextField txtUsuario = new JTextField();
	JPasswordField txtPass1 = new JPasswordField();
	JPasswordField txtPass2 = new JPasswordField();
	JTextField txtNombreCompleto = new JTextField();
	private String accion;
	JPanel pnlResultados = new JPanel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JCheckBox chkReporteador = new JCheckBox();

	public FrmCatalogoUsuarios() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		pnlDatos.setLayout(borderLayout3);
		cmdCancelar.setText("CANCELAR");
		cmdCancelar
				.addActionListener(new FrmCatalogoUsuarios_cmdCancelar_actionAdapter(
						this));
		cmdAceptar.setText("ACEPTAR");
		cmdAceptar
				.addActionListener(new FrmCatalogoUsuarios_cmdAceptar_actionAdapter(
						this));
		cmdEliminar.setText("ELIMINAR");
		cmdEliminar
				.addActionListener(new FrmCatalogoUsuarios_cmdEliminar_actionAdapter(
						this));
		cmdModificar.setText("MODIFICAR");
		cmdModificar
				.addActionListener(new FrmCatalogoUsuarios_cmdModificar_actionAdapter(
						this));
		cmdAgregar.setText("AGREGAR");
		cmdAgregar
				.addActionListener(new FrmCatalogoUsuarios_cmdAgregar_actionAdapter(
						this));
		pnlBuscar.setLayout(borderLayout4);
		pnlCentro.setLayout(borderLayout5);
		pnlDatosCajas.setLayout(null);
		chkActivo.setText("Usuario activo");
		chkActivo.setBounds(new Rectangle(46, 204, 115, 23));
		chkAuditor.setText("Auditor de inventarios");
		chkAuditor.setBounds(new Rectangle(181, 228, 144, 23));
		chkAdmin.setText("Permisos gerenciales");
		chkAdmin.setBounds(new Rectangle(46, 253, 130, 23));
		chkVendedor.setText("Vendedor");
		chkVendedor.setBounds(new Rectangle(181, 203, 83, 23));
		chkInventarios.setText("Registrar inventario");
		chkInventarios.setBounds(new Rectangle(46, 229, 127, 23));
		chkEliminarPagos.setText("Modificar pagos");
		chkEliminarPagos.setBounds(new Rectangle(181, 252, 212, 23));
		chkAdminUsuarios.setText("Administrar usuarios");
		chkAdminUsuarios.setBounds(new Rectangle(46, 278, 132, 23));
		
		chkModificarCantidadInventarios.setText("Modificar Inventarios");
		chkModificarCantidadInventarios.setBounds(new Rectangle(46, 304, 132, 23));
		
		chkDevolucionesIlimitadas.setText("Devoluciones ilimitadas");
		chkDevolucionesIlimitadas.setBounds(new Rectangle(181, 304, 172, 23));
		
		chkExportarClientes.setText("Exportar clientes");
		chkExportarClientes.setBounds(new Rectangle(46, 330, 132, 23));
		
		
		
		jCheckBox1.setText("jCheckBox1");
		jLabel1.setText("Usuario:");
		jLabel1.setBounds(new Rectangle(56, 70, 55, 22));
		jLabel2.setText("Contraseña:");
		jLabel2.setBounds(new Rectangle(56, 96, 67, 22));
		jLabel3.setText("Confirmar contraseña:");
		jLabel3.setBounds(new Rectangle(56, 122, 113, 22));
		jLabel4.setText("Nombre:");
		jLabel4.setBounds(new Rectangle(56, 151, 55, 22));
		txtUsuario.setText("");
		txtUsuario.setBounds(new Rectangle(113, 70, 222, 22));
		txtPass1.setText("");
		txtPass1.setBounds(new Rectangle(128, 97, 207, 22));
		txtPass2.setText("");
		txtPass2.setBounds(new Rectangle(177, 124, 158, 22));
		txtNombreCompleto.setText("");
		txtNombreCompleto.setBounds(new Rectangle(114, 151, 221, 22));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("CATALOGO DE USUARIOS");
		this.addWindowListener(new FrmCatalogoUsuarios_this_windowAdapter(this));
		lstUsuarios
				.addMouseListener(new FrmCatalogoUsuarios_lstUsuarios_mouseAdapter(
						this));
		txtNombre
				.addActionListener(new FrmCatalogoUsuarios_txtNombre_actionAdapter(
						this));
		cmdBuscar
				.addActionListener(new FrmCatalogoUsuarios_cmdBuscar_actionAdapter(
						this));
		jLabel5.setText("RESULTADOS DE LA BUSQUEDA:");
		jLabel6.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		jLabel6.setText("DATOS DEL USUARIO");
		jLabel6.setBounds(new Rectangle(144, 34, 212, 25));
		chkReporteador.setText("Ejecutar reportes");
		chkReporteador.setBounds(new Rectangle(181, 277, 197, 23));
		this.getContentPane().add(pnlDatos, java.awt.BorderLayout.CENTER);
		pnlIzquierdo.setLayout(borderLayout2);
		lblBuscar.setText("Indica el nombre a buscar:");
		txtNombre.setText("");
		cmdBuscar.setText("BUSCAR");
		pnlDatos.setPreferredSize(new Dimension(400, 400));
		pnlIzquierdo.add(scrollUsuarios, java.awt.BorderLayout.CENTER);
		scrollUsuarios.getViewport().add(lstUsuarios);
		pnlDatos.add(pnlBotones, java.awt.BorderLayout.SOUTH);
		pnlBotones.add(cmdAgregar);
		pnlBotones.add(cmdModificar);
		pnlBotones.add(cmdEliminar);
		pnlBotones.add(cmdAceptar);
		pnlBotones.add(cmdCancelar);
		pnlBuscar.add(txtNombre, java.awt.BorderLayout.CENTER);
		pnlBuscar.add(cmdBuscar, java.awt.BorderLayout.EAST);
		this.getContentPane().add(pnlIzquierdo, java.awt.BorderLayout.WEST);
		pnlDatos.add(pnlCentro, java.awt.BorderLayout.CENTER);
		pnlCentro.add(pnlDatosCajas, java.awt.BorderLayout.CENTER);
		pnlDatosCajas.add(jLabel1);
		pnlDatosCajas.add(txtUsuario);
		pnlDatosCajas.add(jLabel2);
		pnlDatosCajas.add(txtPass1);
		pnlDatosCajas.add(jLabel3);
		pnlDatosCajas.add(txtPass2);
		pnlDatosCajas.add(jLabel4);
		pnlDatosCajas.add(txtNombreCompleto);
		pnlDatosCajas.add(chkActivo);
		pnlDatosCajas.add(chkVendedor);
		pnlDatosCajas.add(chkInventarios);
		pnlDatosCajas.add(chkAuditor);
		pnlDatosCajas.add(chkAdmin);
		pnlDatosCajas.add(chkEliminarPagos);
		pnlDatosCajas.add(chkAdminUsuarios);
		pnlDatosCajas.add(jLabel6);
		pnlDatosCajas.add(chkReporteador);
		pnlDatosCajas.add(chkModificarCantidadInventarios);
		pnlDatosCajas.add(chkExportarClientes);
		pnlDatosCajas.add(chkDevolucionesIlimitadas);
		
		
		pnlIzquierdo.add(pnlBuscar, java.awt.BorderLayout.NORTH);
		pnlResultados.add(jLabel5);
		pnlBuscar.add(lblBuscar, java.awt.BorderLayout.NORTH);
		pnlBuscar.add(pnlResultados, java.awt.BorderLayout.SOUTH);
		this.lstUsuarios.setModel(modelo);
	}

	public void this_windowOpened(WindowEvent e) {
		// deshabilitar las cajas de texto y los botones de aceptar y cancela
		this.deshabilitarCajas();
		// activar la lista de busqueda y los botones de agregar
		this.cmdAceptar.setEnabled(false);
		this.cmdCancelar.setEnabled(false);
		this.cmdAgregar.setEnabled(true);
		this.cmdModificar.setEnabled(false);
		this.cmdEliminar.setEnabled(false);
		this.txtNombre.requestFocus();
	}

	void habilitarBotones() {
		this.cmdAceptar.setEnabled(false);
		this.cmdCancelar.setEnabled(false);
		this.cmdBuscar.setEnabled(true);
		this.cmdAgregar.setEnabled(true);
		this.cmdModificar.setEnabled(true);
		this.cmdEliminar.setEnabled(true);
		this.txtNombre.setEditable(true);
	}

	void deshabilitarBotones() {
		this.cmdAceptar.setEnabled(true);
		this.cmdCancelar.setEnabled(true);
		this.cmdAgregar.setEnabled(false);
		this.cmdModificar.setEnabled(false);
		this.cmdEliminar.setEnabled(false);
		this.cmdBuscar.setEnabled(false);
		this.txtNombre.setEditable(false);
	}

	void deshabilitarCajas() {
		this.txtUsuario.setEditable(false);
		this.txtNombreCompleto.setEditable(false);
		this.txtPass1.setEditable(false);
		this.txtPass2.setEditable(false);
		this.chkActivo.setEnabled(false);
		this.chkReporteador.setEnabled(false);
		this.chkAdmin.setEnabled(false);
		this.chkAdminUsuarios.setEnabled(false);
		this.chkAuditor.setEnabled(false);
		this.chkEliminarPagos.setEnabled(false);
		this.chkInventarios.setEnabled(false);
		this.chkVendedor.setEnabled(false);
		chkModificarCantidadInventarios.setEnabled(false);
		chkExportarClientes.setEnabled(false);
		chkDevolucionesIlimitadas.setEnabled(false);
	}

	void habilitarCajas() {
		this.txtUsuario.setEditable(true);
		this.txtNombreCompleto.setEditable(true);
		this.txtPass1.setEditable(true);
		this.txtPass2.setEditable(true);
		this.chkActivo.setEnabled(true);
		this.chkAdmin.setEnabled(true);
		this.chkAdminUsuarios.setEnabled(true);
		this.chkAuditor.setEnabled(true);
		this.chkEliminarPagos.setEnabled(true);
		this.chkInventarios.setEnabled(true);
		this.chkVendedor.setEnabled(true);
		this.chkReporteador.setEnabled(true);
		chkModificarCantidadInventarios.setEnabled(true);
		chkExportarClientes.setEnabled(true);
		chkDevolucionesIlimitadas.setEnabled(true);
		
	}

	void buscarUsuarios() {
		// BUSCAMOS LA LISTA DE USUARIOS QUE EMPATAN
		modelo.clear();
		engine.buscarUsuarios(txtNombre.getText());
		for (Usuario u : engine.usuariosEncontrados) {
			modelo.addElement(u.getNombre());
		}
		// LLENAMOS LA LISTA CON LOS DATOS
		// SEGUIMOS SIN HABILITAR NI DESHABILITAR NADA
	}

	public void txtNombre_actionPerformed(ActionEvent e) {
		// BUSCAMOS TODOS LOS USUARIOS Y LOS PONEMOS EN LA LISTA
		buscarUsuarios();
	}

	public void lstUsuarios_mouseClicked(MouseEvent e) {
		// SI HAY ELEMENTO SELECCIONADO LO CARGAMOS EN EL FORMULARIO
		if (lstUsuarios.getSelectedIndex() >= 0) {
			engine.seleccionarUsuario(lstUsuarios.getSelectedIndex());
			mostrarDatosUsuario();
			// PONEMOS LOS DATOS
			habilitarBotones();
		}
		// HABILITAMOS EL BOTON DE MODIFICAR Y ELIMINAR
	}

	void mostrarDatosUsuario() {
		this.txtUsuario.setText(engine.u.getUsuario());
		this.txtPass1.setText(engine.u.getPass());
		this.txtPass2.setText(engine.u.getPass());
		this.txtNombreCompleto.setText(engine.u.getNombre());
		this.chkActivo
				.setSelected(((engine.u.getActivo() == 1) ? true : false));
		this.chkAdmin.setSelected(((engine.u.getAdmin() == 1) ? true : false));
		this.chkAdminUsuarios
				.setSelected(((engine.u.getAdminUsuarios() == 1) ? true : false));
		this.chkAuditor.setSelected(((engine.u.getAuditor() == 1) ? true
				: false));
		this.chkEliminarPagos
				.setSelected(((engine.u.getEliminarPagos() == 1) ? true : false));
		this.chkInventarios
				.setSelected(((engine.u.getInventarios() == 1) ? true : false));
		this.chkVendedor.setSelected(((engine.u.getVendedor() == 1) ? true
				: false));
		this.chkReporteador
				.setSelected(((engine.u.getReporteador() == 1) ? true : false));
		
		this.chkModificarCantidadInventarios
		.setSelected(((engine.u.getModificarCantidadInventarios() == 1) ? true : false));

		this.chkExportarClientes
		.setSelected(((engine.u.getExportar() == 1) ? true : false));

		this.chkDevolucionesIlimitadas
		.setSelected(((engine.u.getDevolucionesIlimitadas() == 1) ? true : false));

	}

	void limpiarDatos() {
		this.txtUsuario.setText("");
		this.txtPass1.setText("");
		this.txtPass2.setText("");
		this.txtNombreCompleto.setText("");
		this.chkActivo.setSelected(false);
		this.chkAdmin.setSelected(false);
		this.chkAdminUsuarios.setSelected(false);
		this.chkAuditor.setSelected(false);
		this.chkEliminarPagos.setSelected(false);
		this.chkInventarios.setSelected(false);
		this.chkVendedor.setSelected(false);
		this.chkReporteador.setSelected(false);
		chkModificarCantidadInventarios.setSelected(false);
		chkExportarClientes.setSelected(false);
		chkDevolucionesIlimitadas.setSelected(false);

	}

	public void cmdModificar_actionPerformed(ActionEvent e) {
		accion = "modificar";
		this.habilitarCajas();
		this.deshabilitarBotones();
	}

	public void cmdAgregar_actionPerformed(ActionEvent e) {
		accion = "agregar";
		this.limpiarDatos();
		this.habilitarCajas();
		this.deshabilitarBotones();
	}

	public void cmdEliminar_actionPerformed(ActionEvent e) {
		int i = JOptionPane.showConfirmDialog(this,
				"ESTA SEGURO DE QUE DESEA ELIMINAR EL USUARIO?",
				AppInstance.nombreNegocio, JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) { // BORRAMOS EL USUARIO
			if (engine.eliminarUsuario()) {
				JOptionPane.showMessageDialog(this,
						"USUARIO ELIMINADO CORRECTAMENTE",
						AppInstance.nombreNegocio,
						JOptionPane.INFORMATION_MESSAGE);
				this.limpiarDatos();
				this.deshabilitarCajas();
				this.habilitarBotones();
				this.cmdEliminar.setEnabled(false);
				this.cmdModificar.setEnabled(false);
				engine.u = null; // PONEMOS EN NULL EL USUARIO
			} else {
				JOptionPane.showMessageDialog(this,
						"ERROR AL ELIMINAR EL USUARIO",
						AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		if (accion.equals("agregar")) {
			if (verificarPassword()) { // VERIFICAMOS EL PASSWORD QUE EN LAS DOS
										// CAJAS SEA IGUAL
				// ASIGNAMOS LAS CAJAS DE TEXTO AL USUARIO DEL ENGINE
				engine.u = new Usuario();
				mapearUsuario();
				// AGREGAMOS EL USUARIO
				if (engine.agregarUsuario()) {
					// MOSTRAMOS MENSAJE DE OK , LIMPIAMOS Y DESHABILITAMOS
					// CAJAS, DESHABILITAMOS TODOS LOS BOTONES MENOS EL DE
					// AGREGAR, HABILITAMOS LA LISTA Y LA BUSQUEDA
					JOptionPane.showMessageDialog(this,
							"USUARIO AGREGADO CORRECTAMENTE",
							AppInstance.nombreNegocio,
							JOptionPane.INFORMATION_MESSAGE);
					this.limpiarDatos();
					this.deshabilitarCajas();
					this.habilitarBotones();
					this.cmdModificar.setEnabled(false);
					this.cmdEliminar.setEnabled(false);
					modelo.clear();
				} else { // SI NO SE PUDO AGREGAR MOSTRAMOS MENSAJE DE ERROR, Y
							// NO DESHABILITAMOS NI HABILITAMOS NADA
					JOptionPane.showMessageDialog(this,
							"ERROR AL AGREGAR EL USUARIO",
							AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);

				}

			}
		} else if (accion.equals("modificar")) {
			if (verificarPassword()) { // VERIFICAMOS EL PASSWORD QUE EN LAS DOS
										// CAJAS SEA IGUAL
				// ASIGNAMOS LAS CAJAS DE TEXTO AL USUARIO DEL ENGINE
				mapearUsuario();
				// AGREGAMOS EL USUARIO
				if (engine.modificarUsuario()) {
					// MOSTRAMOS MENSAJE DE OK , LIMPIAMOS Y DESHABILITAMOS
					// CAJAS, DESHABILITAMOS TODOS LOS BOTONES MENOS EL DE
					// AGREGAR, HABILITAMOS LA LISTA Y LA BUSQUEDA
					JOptionPane.showMessageDialog(this,
							"USUARIO MODIFICADO CORRECTAMENTE",
							AppInstance.nombreNegocio,
							JOptionPane.INFORMATION_MESSAGE);
					this.limpiarDatos();
					this.deshabilitarCajas();
					this.habilitarBotones();
					this.cmdModificar.setEnabled(false);
					this.cmdEliminar.setEnabled(false);
					modelo.clear();
				} else { // SI NO SE PUDO AGREGAR MOSTRAMOS MENSAJE DE ERROR, Y
							// NO DESHABILITAMOS NI HABILITAMOS NADA
					JOptionPane.showMessageDialog(this,
							"ERROR AL MODIFICAR EL USUARIO",
							AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);

				}

			}
		}
	}

	@SuppressWarnings("deprecation")
	boolean verificarPassword() {
		if (!this.txtPass1.getText().equals(txtPass2.getText())) {
			JOptionPane.showMessageDialog(this, "LOS PASSWORDS NO COINCIDEN",
					AppInstance.nombreNegocio, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	void mapearUsuario() {
		engine.u.setUsuario(this.txtUsuario.getText());
		engine.u.setPass(this.txtPass1.getText());
		engine.u.setNombre(this.txtNombreCompleto.getText());
		engine.u.setActivo((chkActivo.isSelected()) ? 1 : 0);
		engine.u.setAdmin((chkAdmin.isSelected()) ? 1 : 0);
		engine.u.setAdminUsuarios((chkAdminUsuarios.isSelected()) ? 1 : 0);
		engine.u.setAuditor((chkAuditor.isSelected()) ? 1 : 0);
		engine.u.setEliminarPagos((chkEliminarPagos.isSelected()) ? 1 : 0);
		engine.u.setInventarios((chkInventarios.isSelected()) ? 1 : 0);
		engine.u.setVendedor((chkVendedor.isSelected()) ? 1 : 0);
		engine.u.setReporteador((chkReporteador.isSelected()) ? 1 : 0);
		engine.u.setModificarCantidadInventarios((chkModificarCantidadInventarios.isSelected()) ? 1 : 0);
		engine.u.setExportar((chkExportarClientes.isSelected()) ? 1 : 0);
		engine.u.setDevolucionesIlimitadas((chkDevolucionesIlimitadas.isSelected()) ? 1 : 0);
	}

	public void cmdCancelar_actionPerformed(ActionEvent e) {
		this.habilitarBotones();
		this.deshabilitarCajas();
		if (accion.equals("agregar")) {
			this.limpiarDatos();
			this.cmdModificar.setEnabled(false);
			this.cmdEliminar.setEnabled(false);
		}
		if (accion.equals("modificar")) {
			mostrarDatosUsuario();
		}
	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {
		buscarUsuarios();
	}
}

class FrmCatalogoUsuarios_cmdCancelar_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_cmdCancelar_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelar_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_cmdAceptar_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_cmdAceptar_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_cmdEliminar_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_cmdEliminar_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdEliminar_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_cmdAgregar_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_cmdAgregar_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregar_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_cmdModificar_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_cmdModificar_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdModificar_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_lstUsuarios_mouseAdapter extends MouseAdapter {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_lstUsuarios_mouseAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {

		adaptee.lstUsuarios_mouseClicked(e);
	}
}

class FrmCatalogoUsuarios_txtNombre_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_txtNombre_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.txtNombre_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_cmdBuscar_actionAdapter implements ActionListener {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_cmdBuscar_actionAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}

class FrmCatalogoUsuarios_this_windowAdapter extends WindowAdapter {
	private FrmCatalogoUsuarios adaptee;

	FrmCatalogoUsuarios_this_windowAdapter(FrmCatalogoUsuarios adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}
