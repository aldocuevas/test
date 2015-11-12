package com.boutique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import com.boutique.dao.DaoClientesCentral;
import com.boutique.domain.ClasificacionCliente;
import com.boutique.domain.Cliente;
import com.boutique.domain.Colonia;
import com.boutique.engine.impl.AppInstance;
import com.boutique.util.Conversor;
import com.toedter.calendar.JDateChooser;

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

public class PnlDirectorioClientes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	com.boutique.engine.impl.CatalogoClientesEngine engine = new com.boutique.engine.impl.CatalogoClientesEngine();
	java.util.List<String[]> listaClientesEncontrados;
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JSplitPane jSplitPane1 = new JSplitPane();
	JPanel jPanel1 = new JPanel();
	File file = null;
	// ModelDatosSocio modelDatosSocio1 = new ModelDatosSocio();
	JPanel jPanel2 = new JPanel();
	JScrollPane jScrollPane1 = new JScrollPane();
	BorderLayout borderLayout2 = new BorderLayout();

	com.toedter.calendar.JDateChooser JCFechaNacimiento = new JDateChooser();
	JComboBox cmbEstadoCivil = new JComboBox();
	JLabel lblLimiteCredito = new JLabel();
	JTextField txtTelefonoTrabajo = new JTextField();
	JTextField txtCalleAledanaA = new JTextField();
	JLabel jLabel23 = new JLabel();
	JLabel jLabel9 = new JLabel();
	JLabel jClasificacion = new JLabel();
	JTextField txtCalle = new JTextField();
	JTextField txtPersonal2 = new JTextField();
	JLabel jLabel16 = new JLabel();
	JLabel jLabel18 = new JLabel();
	JLabel jLabel17 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JComboBox cmbCalzado = new JComboBox();
	JComboBox cmbVestido = new JComboBox();

	JTextField txtComercial2 = new JTextField();
	JTextField txtCalleAledanaB = new JTextField();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel lblNumero = new JLabel();
	JLabel jLabel21 = new JLabel();
	JLabel jLabel26 = new JLabel();
	JLabel lblTallaConjunto = new JLabel();
	JCheckBox chkCasaPropia = new JCheckBox();
	JCheckBox chkNoCorreo = new JCheckBox();
	JTextField txtComercial1 = new JTextField();
	JLabel jLabel20 = new JLabel();
	JTextField txtOcupacionConyugue = new JTextField();
	JLabel lblTieneCredito = new JLabel();
	JLabel lblCalle = new JLabel();
	JTextField txtTelefono = new JTextField();
	JTextField txtDomicilioTrabajoConyugue = new JTextField();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel24 = new JLabel();
	JPanel jPanel3 = new JPanel();
	JTextField txtDomicilioTrabajo = new JTextField();
	JTextField txtNumero = new JTextField();
	JLabel jLabel19 = new JLabel();
	JLabel lblTallaCalzado = new JLabel();
	JTextField txtColonia = new JTextField();
	JLabel jLabel7 = new JLabel();
	JButton cmdCancelarFoto = new JButton();
	JLabel jLabel25 = new JLabel();
	JCheckBox chkTrabaja = new JCheckBox();
	JComboBox cmbTieneCredito = new JComboBox();
	JTextField txtLimiteCredito = new JTextField();
	JTextField txtNombre = new JTextField();
	JLabel jLabel22 = new JLabel();
	JLabel jLabel27 = new JLabel();
	JLabel jLabel15 = new JLabel();

	JLabel jLabel10 = new JLabel();
	JComboBox cmbColonia = new JComboBox();
	JComboBox cmbClasificacion = new JComboBox();
	JTextField txtPersonal1 = new JTextField();

	Cliente cliente = new Cliente();
	String accion = new String("");
	pnlCatalogosOperaciones pnlOperaciones1 = new pnlCatalogosOperaciones();

	JLabel jLabel28 = new JLabel();
	JLabel jLabelCorreo = new JLabel();
	JTextField txtComercial3 = new JTextField();
	JPanel jPanel4 = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	JPanel jPanel5 = new JPanel();
	JLabel jLabel29 = new JLabel();
	JTextField txtNombre1 = new JTextField();
	JButton cmdBuscar = new JButton();
	BorderLayout borderLayout4 = new BorderLayout();
	JScrollPane jScrollPane2 = new JScrollPane();
	JList lstClientesEncontrados = new JList();
	DefaultListModel modelClientesEncontrados = new DefaultListModel();
	JLabel jLabel30 = new JLabel();
	JTextField txtApellidoMaterno = new JTextField();
	JLabel jLabel31 = new JLabel();
	JTextField txtApellidoPaterno = new JTextField();
	JTextField txtCodigoCliente = new JTextField();
	JTextField txtCorreoElectronico = new JTextField();
	JTextField txtCiudad = new JTextField();
	JTextField txtEstado = new JTextField();
	JTextField txtCodigoPostal = new JTextField();
	JLabel lblEstado = new JLabel();
	JLabel lblCodigoPostal = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel32 = new JLabel();
	JLabel jLabel33 = new JLabel();
	JLabel jLabel34 = new JLabel();
	JLabel jLabel35 = new JLabel();
	JTextField txtEmpresaTrabajoConyugue = new JTextField();
	JTextField txtNumeroInterior = new JTextField();
	JTextField txtCelular = new JTextField();
	JLabel jLabel36 = new JLabel();
	JLabel jLabel37 = new JLabel();
	JTextField txtFamiliar1 = new JTextField();
	JTextField txtFamiliar2 = new JTextField();
	JTextField txtNombreEmpresa = new JTextField();
	JTextField txtOcupacion = new JTextField();
	JTextField txtNombreConyugue = new JTextField();
	JButton cmdSeleccionarFoto = new JButton();
	PnlFotografia pnlFotografia1 = new PnlFotografia();

	JTextField jTextField2 = new JTextField();
	JTextField jTextField3 = new JTextField();
	JLabel jLabel11 = new JLabel();
	JTextField txtFechaActualizacion = new JTextField();

	public PnlDirectorioClientes() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void cmbClasificacion_actionPerformed(ActionEvent e) {
		if (cmbClasificacion.isEnabled()
				&& cmbClasificacion.getSelectedIndex() >= 0) {
			ClasificacionCliente cc = engine.getClasificaciones().get(
					cmbClasificacion.getSelectedIndex());
			if (cc.getTipo().equals("CREDITO")) {
				this.cmbTieneCredito.setEnabled(true);
				this.txtLimiteCredito.setEnabled(true);
			} else {
				this.cmbTieneCredito.setEnabled(false);
				this.txtLimiteCredito.setEnabled(false);
			}
			// JOptionPane.showMessageDialog(this, "asdf");
		}
	}

	void jbInit() throws Exception {

		this.setLayout(borderLayout1);
		jPanel2.setLayout(borderLayout2);
		jLabel1.setBackground(Color.white);
		jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		jLabel1.setOpaque(true);
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Directorio de clientes");
		cmdCancelarFoto.setBounds(new Rectangle(717, 115, 49, 25));
		cmdCancelarFoto.setIcon(new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/cancel.png")));
		cmdCancelarFoto
				.addActionListener(new PnlDirectorioClientes_cmdCancelarFoto_actionAdapter(
						this));
		cmdSeleccionarFoto.setIcon(new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/foto.png")));
		lblLimiteCredito.setText("Limite de crédito:");
		lblLimiteCredito.setBounds(new Rectangle(467, 140, 87, 21));
		txtTelefonoTrabajo.setDisabledTextColor(Color.black);
		txtTelefonoTrabajo.setText("");
		txtTelefonoTrabajo.setBounds(new Rectangle(120, 210, 258, 21));
		txtCalleAledanaA.setDisabledTextColor(Color.black);
		txtCalleAledanaA.setText("");
		txtCalleAledanaA.setBounds(new Rectangle(91, 46, 233, 21));
		jLabel23.setText("Referencia personal 1:");
		jLabel23.setBounds(new Rectangle(396, 256, 113, 21));
		jLabel9.setText("Telefono:");
		jLabel9.setBounds(new Rectangle(229, 92, 50, 21));
		jClasificacion.setText("Clasificacion: ");
		jClasificacion.setBounds(new Rectangle(27, 139, 67, 21));
		this.JCFechaNacimiento.setBounds(new Rectangle(91, 119, 103, 21));
		cmbClasificacion.setBounds(new Rectangle(101, 140, 123, 21));
		txtCalle.setDisabledTextColor(Color.black);
		txtCalle.setText("");
		txtCalle.setBounds(new Rectangle(91, 23, 233, 21));
		txtCalle.addActionListener(new PnlDirectorioClientes_txtCalle_actionAdapter(
				this));
		txtPersonal2.setDisabledTextColor(Color.black);
		txtPersonal2.setText("");
		txtPersonal2.setBounds(new Rectangle(120, 280, 258, 21));
		jLabel16.setText("Casa propia:");
		jLabel16.setBounds(new Rectangle(32, 164, 67, 21));
		jLabel18.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel18.setHorizontalTextPosition(SwingConstants.RIGHT);
		jLabel18.setText("Domicilio del trabajo:");
		jLabel18.setBounds(new Rectangle(406, 187, 100, 21));
		jLabel17.setText("Trabaja:");
		jLabel17.setBounds(new Rectangle(140, 164, 49, 21));
		jLabel6.setToolTipText("");
		jLabel6.setText("Calle aledana B:");
		jLabel6.setBounds(new Rectangle(330, 48, 86, 16));
		txtCalleAledanaB.setDisabledTextColor(Color.black);
		txtCalleAledanaB.setText("");
		txtCalleAledanaB.setBounds(new Rectangle(409, 46, 233, 21));
		jLabel5.setText("Calle aledana A:");
		jLabel5.setBounds(new Rectangle(6, 48, 80, 16));
		jLabel5.setRequestFocusEnabled(true);
		jLabel5.setToolTipText("");
		jLabel8.setText("Ciudad:");
		jLabel8.setBounds(new Rectangle(441, 69, 38, 21));
		jLabel8.setRequestFocusEnabled(true);
		lblNumero.setText("Numero:");
		lblNumero.setBounds(new Rectangle(330, 25, 45, 16));
		jLabel21.setText("Ocupacion del conyugue:");
		jLabel21.setBounds(new Rectangle(383, 233, 126, 21));
		jLabel26.setText("Referencia comercial 2:");
		jLabel26.setBounds(new Rectangle(4, 303, 119, 21));
		lblTallaConjunto.setText("Talla conjunto:");
		lblTallaConjunto.setBounds(new Rectangle(351, 116, 72, 21));
		lblTallaCalzado.setBounds(new Rectangle(229, 116, 72, 21));
		// chkCasaPropia.setDisabledTextColor(Color.black);
		chkCasaPropia.setText("Si");
		chkNoCorreo.setText("No tiene correo");
		chkCasaPropia.setBounds(new Rectangle(97, 164, 36, 21));
		chkNoCorreo.setBounds(new Rectangle(508, 376, 149, 21));
		txtComercial1.setDisabledTextColor(Color.black);
		txtComercial1.setText("");
		txtComercial1.setBounds(new Rectangle(508, 279, 254, 21));
		jLabel20.setText("Nombre del conyugue:");
		jLabel20.setBounds(new Rectangle(398, 210, 111, 21));
		txtOcupacionConyugue.setDisabledTextColor(Color.black);
		txtOcupacionConyugue.setText("sdfgsdfgsdfgsdfg");
		txtOcupacionConyugue.setBounds(new Rectangle(508, 233, 254, 21));
		lblTieneCredito.setText("Status crediticio:");
		lblTieneCredito.setBounds(new Rectangle(229, 140, 82, 21));
		lblCalle.setText("Domicilio calle:");
		lblCalle.setBounds(new Rectangle(16, 25, 70, 16));
		txtTelefono.setDisabledTextColor(Color.black);
		txtTelefono.setText("");
		txtTelefono.setBounds(new Rectangle(280, 92, 155, 21));
		txtDomicilioTrabajoConyugue.setDisabledTextColor(Color.black);
		txtDomicilioTrabajoConyugue.setText("");
		txtDomicilioTrabajoConyugue.setBounds(new Rectangle(167, 256, 211, 21));
		txtCodigoPostal.setText("");
		txtCodigoPostal.setBounds(new Rectangle(321, 69, 114, 21));
		txtEstado.setText("");
		txtEstado.setBounds(new Rectangle(91, 92, 133, 21));
		lblEstado.setText("Estado:");
		lblEstado.setBounds(new Rectangle(48, 92, 38, 21));
		lblCodigoPostal.setText("Codigo postal:");
		lblCodigoPostal.setBounds(new Rectangle(250, 69, 70, 21));
		jLabel2.setText("Nombre(s):");
		jLabel2.setBounds(new Rectangle(32, 2, 54, 16));
		jLabel2.setBackground(Color.lightGray);
		jLabel24.setText("Referencia personal 2:");
		jLabel24.setBounds(new Rectangle(7, 280, 113, 21));
		jPanel3.setLayout(null);
		jPanel3.setVerifyInputWhenFocusTarget(true);
		jPanel3.setToolTipText("");
		jPanel3.setRequestFocusEnabled(true);
		jPanel3.setPreferredSize(new Dimension(350, 500));
		jPanel3.setOpaque(true);
		jPanel3.setMinimumSize(new Dimension(350, 500));
		jPanel3.setDoubleBuffered(true);
		jPanel3.setDebugGraphicsOptions(0);
		jPanel3.setAlignmentY((float) 0.5);
		jPanel3.setAlignmentX((float) 0.5);
		jPanel3.setForeground(Color.black);
		jPanel3.setFont(new java.awt.Font("Dialog", 0, 12));
		jPanel3.setBackground(Color.white);
		jPanel3.setEnabled(true);
		txtDomicilioTrabajo.setDisabledTextColor(Color.black);
		txtDomicilioTrabajo.setText("");
		txtDomicilioTrabajo.setBounds(new Rectangle(508, 187, 254, 21));
		txtNumero.setDisabledTextColor(Color.black);
		txtNumero.setText("");
		txtNumero.setBounds(new Rectangle(374, 23, 55, 21));
		jLabel19.setText("Telefono del trabajo:");
		jLabel19.setBounds(new Rectangle(3, 210, 102, 21));
		lblTallaCalzado.setText("Talla calzado:");
		jLabel7.setText("Colonia:");
		jLabel7.setBounds(new Rectangle(46, 68, 40, 21));

		// cmbVestido.setDisabledTextColor(Color.black);
		cmbVestido.setSelectedIndex(-1);
		cmbVestido.setBounds(new Rectangle(419, 116, 55, 21));
		jLabel25.setText("Referencia comercial 1:");
		jLabel25.setBounds(new Rectangle(392, 279, 117, 21));
		// chkTrabaja.setDisabledTextColor(Color.black);
		chkTrabaja.setText("Si");
		chkTrabaja.setBounds(new Rectangle(188, 164, 36, 21));
		chkTrabaja
				.addActionListener(new PnlDirectorioClientes_chkTrabaja_actionAdapter(
						this));

		chkNoCorreo
				.addActionListener(new PnlDirectorioClientes_chkNoCorreo_actionAdapter(
						this));

		cmbTieneCredito.addItem("AUTORIZADO");
		cmbTieneCredito.addItem("NO AUTORIZADO");
		cmbTieneCredito.addItem("CANCELADO");

		cmbTieneCredito
				.addItemListener(new PnlDirectorioClientes_chkTieneCredito_itemAdapter(
						this));
		txtLimiteCredito.setDisabledTextColor(Color.black);
		txtLimiteCredito.setText("");
		txtLimiteCredito.setBounds(new Rectangle(556, 140, 85, 21));
		txtNombre.setDisabledTextColor(Color.black);
		txtNombre.setText("");
		txtNombre.setBounds(new Rectangle(91, 0, 167, 21));

		jLabel22.setRequestFocusEnabled(true);
		jLabel22.setText("Domicilio del trabajo del conyugue:");
		jLabel22.setBounds(new Rectangle(2, 256, 168, 21));
		jLabel27.setText("Referencia comercial 3:");
		jLabel27.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel27.setBounds(new Rectangle(392, 303, 117, 21));
		jLabel15.setText("Estado civil:");
		jLabel15.setBounds(new Rectangle(477, 116, 58, 21));
		txtNombreConyugue.setDisabledTextColor(Color.black);
		txtNombreConyugue.setText("");
		txtNombreConyugue.setBounds(new Rectangle(508, 210, 254, 21));

		jLabel10.setText("Fecha de nacimiento:");
		jLabel10.setBounds(new Rectangle(0, 116, 103, 21));
		txtPersonal1.setDisabledTextColor(Color.black);
		txtPersonal1.setText("");
		txtPersonal1.setBounds(new Rectangle(508, 256, 254, 21));
		jScrollPane1
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jLabel28.setText("Codigo de cliente:");
		jLabel28.setBounds(new Rectangle(32, 350, 92, 21));
		jLabelCorreo.setText("Email:");
		jLabelCorreo.setBounds(new Rectangle(479, 350, 30, 21));
		txtCorreoElectronico.setText("");
		txtCorreoElectronico.setBounds(new Rectangle(508, 350, 254, 21));
		jPanel4.setLayout(borderLayout3);
		jLabel29.setText("Nombre:");
		cmdBuscar.setText("Buscar");
		cmdBuscar
				.addActionListener(new PnlDirectorioClientes_cmdBuscar_actionAdapter(
						this));
		jPanel4.setPreferredSize(new Dimension(260, 35));
		jSplitPane1.setDebugGraphicsOptions(0);
		jSplitPane1.setLastDividerLocation(-1);
		jPanel5.setLayout(borderLayout4);
		txtNombre1.setText("");
		txtNombre1
				.addActionListener(new PnlDirectorioClientes_txtNombre1_actionAdapter(
						this));
		lstClientesEncontrados.setModel(modelClientesEncontrados);
		lstClientesEncontrados
				.addMouseListener(new PnlDirectorioClientes_lstClientesEncontrados_mouseAdapter(
						this));
		jLabel30.setText("Apellido paterno:");
		jLabel30.setBounds(new Rectangle(261, 2, 86, 16));
		jLabel31.setText("Apellido materno:");
		jLabel31.setBounds(new Rectangle(454, 2, 86, 16));
		txtApellidoMaterno.setDisabledTextColor(Color.black);
		txtApellidoMaterno.setText("");
		txtApellidoMaterno.setBounds(new Rectangle(540, 0, 112, 21));
		txtComercial2.setToolTipText("");
		txtComercial2.setDisabledTextColor(Color.black);
		txtComercial2.setText("");
		txtComercial2.setBounds(new Rectangle(120, 303, 258, 21));
		txtCodigoCliente.setText("");
		txtCodigoCliente.setBounds(new Rectangle(120, 350, 258, 21));
		txtCodigoCliente.setEnabled(false);
		txtCodigoCliente.setDisabledTextColor(Color.black);
		txtCorreoElectronico.setText("");
		txtCorreoElectronico.setEnabled(false);
		txtCorreoElectronico.setDisabledTextColor(Color.black);

		txtEstado.setEnabled(false);
		txtEstado.setDisabledTextColor(Color.black);
		txtCodigoPostal.setEnabled(false);
		txtCodigoPostal.setDisabledTextColor(Color.black);
		cmbColonia
				.addItemListener(new PnlDirectorioClientes_cmbColonia_itemAdapter(
						this));
		txtCiudad.setEnabled(false);
		cmbEstadoCivil
				.addItemListener(new PnlDirectorioClientes_cmbEstadoCivil_itemAdapter(
						this));
		cmbClasificacion
				.addActionListener(new PnlDirectorioClientes_cmbClasificacion_actionAdapter(
						this));

		txtApellidoPaterno.setDisabledTextColor(Color.black);
		txtApellidoPaterno.setBounds(new Rectangle(344, 0, 107, 21));
		txtCiudad.setDisabledTextColor(Color.black);
		txtCiudad.setBounds(new Rectangle(480, 69, 162, 21));
		// txtTallaCalzado.setDisabledTextColor(Color.black);
		cmbCalzado.setBounds(new Rectangle(298, 116, 50, 21));
		txtComercial3.setDisabledTextColor(Color.black);
		txtComercial3.setBounds(new Rectangle(508, 303, 254, 21));
		cmbEstadoCivil.setBounds(new Rectangle(533, 116, 108, 21));
		cmbTieneCredito.setBounds(new Rectangle(320, 141, 125, 21));
		cmbColonia.setBounds(new Rectangle(91, 69, 151, 21));
		jLabel3.setText("Interior:");
		jLabel3.setBounds(new Rectangle(437, 25, 43, 17));
		jLabel4.setText("jLabel4");
		jLabel4.setBounds(new Rectangle(0, 0, 34, 15));
		jLabel32.setText("Celular:");
		jLabel32.setBounds(new Rectangle(441, 91, 40, 21));
		jLabel33.setText("Nombre de la empresa:");
		jLabel33.setBounds(new Rectangle(4, 187, 111, 21));
		jLabel34.setText("Ocupación:");
		jLabel34.setBounds(new Rectangle(229, 164, 59, 21));
		jLabel35.setText("Empresa trab. conyugue:");
		jLabel35.setBounds(new Rectangle(4, 233, 128, 21));
		txtEmpresaTrabajoConyugue.setText("");
		txtEmpresaTrabajoConyugue.setBounds(new Rectangle(134, 233, 244, 21));
		txtNumeroInterior.setDisabledTextColor(Color.black);
		txtNumeroInterior.setText("");
		txtNumeroInterior.setBounds(new Rectangle(481, 23, 55, 21));
		txtCelular.setDisabledTextColor(Color.black);
		txtCelular.setText("");
		txtCelular.setBounds(new Rectangle(480, 92, 161, 21));
		jLabel36.setText("Referencia familiar 1:");
		jLabel36.setBounds(new Rectangle(16, 326, 102, 21));
		jLabel37.setText("Referencia familiar 2:");
		jLabel37.setBounds(new Rectangle(403, 326, 106, 21));
		txtFamiliar1.setDisabledTextColor(Color.black);
		txtFamiliar1.setText("");
		txtFamiliar1.setBounds(new Rectangle(120, 326, 258, 21));
		txtFamiliar2.setToolTipText("");
		txtFamiliar2.setDisabledTextColor(Color.black);
		txtFamiliar2.setText("");
		txtFamiliar2.setBounds(new Rectangle(508, 326, 254, 21));
		txtNombreEmpresa.setDisabledTextColor(Color.black);
		txtNombreEmpresa.setText("");
		txtNombreEmpresa.setBounds(new Rectangle(120, 187, 258, 21));
		txtNombreEmpresa
				.addActionListener(new PnlDirectorioClientes_txtNombreEmpresa_actionAdapter(
						this));
		txtOcupacion.setDisabledTextColor(Color.black);
		txtOcupacion.setText("");
		txtOcupacion.setBounds(new Rectangle(287, 164, 354, 21));
		cmbEstadoCivil.addItem("Soltero");
		cmbEstadoCivil.addItem("Casado");
		cmbEstadoCivil.addItem("Divorciado");
		cmbEstadoCivil.addItem("Viudo");
		cmbEstadoCivil.addItem("Union libre");

		this.cmbCalzado.addItem("22");
		this.cmbCalzado.addItem("22/");
		this.cmbCalzado.addItem("23");
		this.cmbCalzado.addItem("23/");
		this.cmbCalzado.addItem("24");
		this.cmbCalzado.addItem("24/");
		this.cmbCalzado.addItem("25");
		this.cmbCalzado.addItem("25/");
		this.cmbCalzado.addItem("26");
		this.cmbCalzado.addItem("26/");
		this.cmbCalzado.addItem("27");
		this.cmbCalzado.addItem("27/");
		this.cmbVestido.addItem("XS");
		this.cmbVestido.addItem("CH");
		this.cmbVestido.addItem("M");
		this.cmbVestido.addItem("G");
		this.cmbVestido.addItem("X");
		this.cmbVestido.addItem("XX");
		this.cmbVestido.addItem("XXX");
		cmdSeleccionarFoto.setBounds(new Rectangle(661, 115, 49, 25));
		cmdSeleccionarFoto.setText("");
		cmdSeleccionarFoto
				.addActionListener(new PnlDirectorioClientes_cmdSeleccionarFoto_actionAdapter(
						this));
		pnlFotografia1.setBounds(new Rectangle(661, 0, 105, 112));
		JCFechaNacimiento.setBounds(new Rectangle(101, 116, 123, 21));
		jTextField2.setText("jTextField2");
		jTextField3.setText("jTextField3");
		jLabel11.setText("Ultima actualización:");
		jLabel11.setBounds(new Rectangle(20, 376, 104, 17));
		txtFechaActualizacion.setEnabled(false);
		txtFechaActualizacion.setDisabledTextColor(Color.red);
		txtFechaActualizacion.setText("");
		txtFechaActualizacion.setBounds(new Rectangle(120, 374, 258, 21));
		this.add(jLabel1, BorderLayout.NORTH);
		this.add(jSplitPane1, BorderLayout.CENTER);
		jSplitPane1.add(jPanel2, JSplitPane.RIGHT);
		jPanel2.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jPanel3, null);
		jPanel2.add(pnlOperaciones1, BorderLayout.SOUTH);
		jSplitPane1.add(jPanel4, JSplitPane.LEFT);
		jPanel4.add(jPanel5, BorderLayout.NORTH);
		jPanel5.add(jLabel29, BorderLayout.WEST);
		jPanel5.add(txtNombre1, BorderLayout.CENTER);
		jPanel5.add(cmdBuscar, BorderLayout.EAST);
		jPanel3.add(txtNombre, null);
		jPanel3.add(jLabel5, null);
		jPanel3.add(pnlFotografia1);
		jPanel3.add(jLabel2, null);
		jPanel3.add(jLabel30, null);
		jPanel3.add(txtApellidoPaterno, null);
		jPanel3.add(txtApellidoMaterno, null);
		jPanel3.add(jLabel31, null);
		jPanel3.add(txtCalle, null);
		jPanel3.add(lblCalle, null);
		jPanel3.add(txtNumero, null);
		jPanel3.add(lblNumero, null);
		jPanel3.add(txtNumeroInterior);
		jPanel3.add(jLabel3);
		this.add(jPanel1, BorderLayout.SOUTH);
		jPanel4.add(jScrollPane2, BorderLayout.CENTER);
		jScrollPane2.getViewport().add(lstClientesEncontrados);
		jPanel3.add(txtCalleAledanaA, null);
		jPanel3.add(jLabel6, null);
		// VAMOS A SACAR LA LISTA DE CLASIFICACIONES
		jPanel3.add(txtCalleAledanaB, null);
		for (Colonia c : engine.colonias()) {
			cmbColonia.addItem(c.getNombre());
		}
		java.util.List<ClasificacionCliente> cc = engine.getClasificaciones();
		for (ClasificacionCliente c : cc) {
			cmbClasificacion.addItem(c.getClasificacion());
		}
		this.limpiarCajas();
		this.desHabilitarCajas();
		pnlOperaciones1.habilitarBotones();
		jPanel3.add(jLabel7, null);

		jPanel3.add(cmbColonia, null);

		jPanel3.add(cmdSeleccionarFoto);

		jPanel3.add(txtCodigoPostal, null);
		jPanel3.add(lblCodigoPostal, null);
		jPanel3.add(txtCiudad);
		jPanel3.add(jLabel8, null);
		jPanel3.add(txtEstado, null);
		jPanel3.add(lblEstado, null);
		jPanel3.add(jLabel9, null);
		jPanel3.add(txtTelefono, null);
		jPanel3.add(txtCelular);
		jPanel3.add(jLabel32);
		jPanel3.add(jLabel10, null);
		jPanel3.add(JCFechaNacimiento, null);
		jPanel3.add(cmbCalzado, null);
		jPanel3.add(lblTallaConjunto, null);
		jPanel3.add(cmbVestido, null);
		jPanel3.add(jLabel15, null);
		jPanel3.add(cmbEstadoCivil, null);
		jPanel3.add(jClasificacion, null);
		jPanel3.add(cmbClasificacion, null);
		jPanel3.add(txtLimiteCredito, null);
		jPanel3.add(lblLimiteCredito, null);
		jPanel3.add(cmbTieneCredito, null);
		jPanel3.add(lblTieneCredito, null);
		jPanel3.add(lblTallaCalzado, null);
		jPanel3.add(jLabel16, null);
		jPanel3.add(jLabel34);
		jPanel3.add(chkCasaPropia, null);
		jPanel3.add(jLabel17, null);
		jPanel3.add(chkTrabaja, null);
		jPanel3.add(txtOcupacion);
		jPanel3.add(txtDomicilioTrabajo, null);
		jPanel3.add(jLabel33);
		jPanel3.add(txtNombreConyugue, null);
		jPanel3.add(jLabel19, null);
		jPanel3.add(txtTelefonoTrabajo, null);
		jPanel3.add(txtOcupacionConyugue);
		jPanel3.add(txtEmpresaTrabajoConyugue);
		jPanel3.add(txtPersonal1, null);
		jPanel3.add(jLabel22, null);
		jPanel3.add(txtDomicilioTrabajoConyugue, null);
		jPanel3.add(txtComercial1, null);
		jPanel3.add(txtPersonal2, null);
		jPanel3.add(txtComercial3, null);
		jPanel3.add(txtComercial2, null);
		jPanel3.add(jLabel26, null);
		jPanel3.add(txtFamiliar2);
		jPanel3.add(txtFamiliar1);
		jPanel3.add(txtCorreoElectronico);
		jPanel3.add(jLabel28, null);
		jPanel3.add(txtCodigoCliente);
		jPanel3.add(jLabelCorreo, null);
		jPanel3.add(jLabel20, null);
		jPanel3.add(jLabel21, null);
		jPanel3.add(jLabel23, null);
		jPanel3.add(jLabel25, null);
		jPanel3.add(jLabel27, null);
		jPanel3.add(jLabel37);
		jPanel3.add(jLabel18, null);
		jPanel3.add(txtNombreEmpresa);
		jPanel3.add(jLabel35);
		jPanel3.add(jLabel24, null);
		jPanel3.add(jLabel36);
		jPanel3.add(chkNoCorreo, null);
		jPanel3.add(cmdCancelarFoto);
		jPanel3.add(jLabel11);
		jPanel3.add(txtFechaActualizacion);
		jPanel3.add(jLabel28, null);
	}

	public void mostrarDatosCliente(Cliente cliente) {
		this.txtCodigoCliente.setText(String.valueOf(cliente.getId()));
		this.txtCorreoElectronico.setText(cliente.getEmail());

		this.txtNombre.setText(cliente.getNombre());
		ClasificacionCliente cc = cliente.getClasificacionCliente();
		this.cmbClasificacion.setSelectedItem(cc.getClasificacion());

		this.txtApellidoPaterno.setText(cliente.getApellidoPaterno());
		this.txtApellidoMaterno.setText(cliente.getApellidoMaterno());
		this.txtCalle.setText(cliente.getCalle());
		this.txtNumero.setText(cliente.getNumero());
		this.txtNumeroInterior.setText(cliente.getNumeroInterior());

		this.txtCalleAledanaA.setText(cliente.getCalleAledanaA());
		this.txtCalleAledanaB.setText(cliente.getCalleAledanaB());
		this.cmbColonia.setSelectedItem(cliente.getColonia());
		this.txtCelular.setText(cliente.getCelular());
		this.txtTelefono.setText(cliente.getTelefono());
		this.JCFechaNacimiento.setDate(cliente.getFechaNacimiento());
		if (cliente.getTallaCalzado().equals("")) {
			this.cmbCalzado.setSelectedIndex(-1);
		} else {
			this.cmbCalzado.setSelectedItem(cliente.getTallaCalzado());
		}
		if (cliente.getTallaConjunto().equals("")) {
			this.cmbVestido.setSelectedIndex(-1);
		} else {
			this.cmbVestido.setSelectedItem(cliente.getTallaConjunto());
		}
		cmbTieneCredito.setSelectedItem(cliente.getTieneCredito());
		txtLimiteCredito.setText(String.valueOf(cliente.getLimiteCredito()));

		txtLimiteCredito.setEnabled(false);
		try {
			chkCasaPropia
					.setSelected((cliente.getCasaPropia().equals("1")) ? true
							: false);
			chkTrabaja.setSelected((cliente.getTrabaja().equals("1")) ? true
					: false);
			chkNoCorreo.setSelected((cliente.getNoEmail().equals("1")) ? true
					: false);
		} catch (Exception ex) {
		}
		JCFechaNacimiento.setDate(cliente.getFechaNacimiento());
		if (JCFechaNacimiento.getDate() == null) {
			JCFechaNacimiento.setDate(new java.util.Date());
		}
		txtOcupacion.setText(cliente.getOcupacion());
		txtNombreEmpresa.setText(cliente.getNombreEmpresa());
		txtDomicilioTrabajo.setText(cliente.getDomicilioTrabajo());
		txtTelefonoTrabajo.setText(cliente.getTelefonoTrabajo());
		cmbVestido.setSelectedItem(cliente.getTallaConjunto());
		txtNombreConyugue.setText(cliente.getNombreConyugue());
		txtOcupacionConyugue.setText(cliente.getOcupacionConyugue());

		txtDomicilioTrabajoConyugue.setText(cliente
				.getDomicilioTrabajoConyugue());
		this.txtEmpresaTrabajoConyugue.setText(cliente
				.getNombreEmpresaConyugue());
		txtPersonal1.setText(cliente.getPersonal1());
		txtPersonal2.setText(cliente.getPersonal2());
		txtComercial1.setText(cliente.getComercial1());
		txtComercial2.setText(cliente.getComercial2());
		txtComercial3.setText(cliente.getComercial3());
		this.cmbEstadoCivil.setSelectedItem(cliente.getEstadoCivil());
		txtFamiliar1.setText(cliente.getFamiliar1());
		txtFamiliar2.setText(cliente.getFamiliar2());
		txtFechaActualizacion
				.setText((cliente.getLastUpdate() != null) ? AppInstance.formatoLargo
						.format(cliente.getLastUpdate())
						+ ", hace: "
						+ Conversor.tiempoTranscurrido(cliente.getLastUpdate())
						: "Nunca actualizado");

		if (cliente.getFotografia() != null) {
			pnlFotografia1.setImagen(cliente.getFotografia());
		} else {
			pnlFotografia1.setImagen((InputStream) null);
		}
	}

	public void setDatosCliente() {
		try {
			cliente.setId(Integer.parseInt(txtCodigoCliente.getText()));
		} catch (NumberFormatException ex) {
		}
		cliente.setEmail(txtCorreoElectronico.getText());
		// TODO SET RFC
		cliente.setClasificacionCliente(engine.getClasificaciones().get(
				cmbClasificacion.getSelectedIndex()));
		cliente.setNombre(txtNombre.getText().toUpperCase());
		cliente.setApellidoPaterno(txtApellidoPaterno.getText().toUpperCase());
		cliente.setApellidoMaterno(txtApellidoMaterno.getText().toUpperCase());
		cliente.setCalle(txtCalle.getText().toUpperCase());
		cliente.setNumero(txtNumero.getText().toUpperCase());
		cliente.setNumeroInterior(txtNumeroInterior.getText().toUpperCase());
		cliente.setCalleAledanaA(txtCalleAledanaA.getText().toUpperCase());
		cliente.setCalleAledanaB(txtCalleAledanaB.getText().toUpperCase());

		try {
			cliente.setColonia(this.cmbColonia.getSelectedItem().toString());
			cliente.setEstadoCivil(this.cmbEstadoCivil.getSelectedItem()
					.toString());
		} catch (Exception ex1) {
		}
		cliente.setTelefono(txtTelefono.getText().toUpperCase());
		cliente.setCelular(txtCelular.getText().toUpperCase());
		cliente.setFechaNacimiento(new java.sql.Date(JCFechaNacimiento
				.getDate().getTime()));

		cliente.setTallaCalzado((cmbCalzado.getSelectedItem() != null ? cmbCalzado
				.getSelectedItem().toString() : ""));
		cliente.setTallaConjunto(((cmbVestido.getSelectedItem() != null) ? cmbVestido
				.getSelectedItem().toString() : ""));
		cliente.setTieneCredito((cmbTieneCredito.getSelectedItem().toString()));
		cliente.setLimiteCredito(Double.parseDouble(txtLimiteCredito.getText()));
		cliente.setCasaPropia((chkCasaPropia.isSelected()) ? "1" : "0");
		chkCasaPropia.setSelected((cliente.getCasaPropia().equals("1")) ? true
				: false);
		cliente.setTrabaja((chkTrabaja.isSelected()) ? "1" : "0");
		chkTrabaja.setSelected((cliente.getTrabaja().equals("1")) ? true
				: false);
		cliente.setNoEmail((chkTrabaja.isSelected()) ? "1" : "0");
		chkNoCorreo.setSelected((cliente.getNoEmail().equals("1")) ? true
				: false);
		cliente.setOcupacion(txtOcupacion.getText().toUpperCase());
		cliente.setDomicilioTrabajo(txtDomicilioTrabajo.getText().toUpperCase());
		cliente.setTelefonoTrabajo(txtTelefonoTrabajo.getText().toUpperCase());
		cliente.setNombreEmpresa(txtNombreEmpresa.getText().toUpperCase());
		cliente.setNombreConyugue(txtNombreConyugue.getText().toUpperCase());
		cliente.setOcupacionConyugue(txtOcupacionConyugue.getText()
				.toUpperCase());
		cliente.setNombreEmpresaConyugue(txtEmpresaTrabajoConyugue.getText()
				.toUpperCase());
		cliente.setDomicilioTrabajoConyugue(txtDomicilioTrabajoConyugue
				.getText().toUpperCase());
		cliente.setPersonal1(txtPersonal1.getText().toUpperCase());
		cliente.setPersonal2(txtPersonal2.getText().toUpperCase());
		cliente.setComercial1(txtComercial1.getText().toUpperCase());
		cliente.setComercial2(txtComercial2.getText().toUpperCase());
		cliente.setComercial3(txtComercial3.getText().toUpperCase());
		cliente.setFamiliar1(txtFamiliar1.getText().toUpperCase());
		cliente.setFamiliar2(txtFamiliar2.getText().toUpperCase());
		String o = pnlOperaciones1.operacion;

		if (o.equals("agregar")) { // AGREGAR LO QUE HAYA EN EL PANEL
			cliente.setFotografia(getFotoAjustada());
		} else if (o.equals("modificar")) {
			InputStream is = getFotoAjustada();
			if (is != null) {
				cliente.setFotografia(is);
			}
		}

	}

	/**
	 * getFotoAjustada
	 * 
	 * @return InputStream
	 */
	private InputStream getFotoAjustada() {
		InputStream is = null;
		if (pnlFotografia1.imagen != null) {
			BufferedImage bf = new BufferedImage(
					pnlFotografia1.imagen.getWidth(null),
					pnlFotografia1.imagen.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			Graphics g = bf.createGraphics(); // Paint the image onto the
												// buffered image
			g.drawImage(pnlFotografia1.imagen, 0, 0, null);
			g.dispose();
			try {
				file = new File("tmp.jpg");
				ImageIO.write(bf, "JPG", file);
				is = new FileInputStream(file);

				file.delete();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return is;
	}

	void habilitarCajas() {
		this.lstClientesEncontrados.setEnabled(false);
		txtNombre1.setEnabled(false);
		this.cmdBuscar.setEnabled(false);
		// txtCiudad.setEnabled(true);
		// txtClaveCliente.setEnabled(true);
		txtNombre.setEnabled(true);
		txtCorreoElectronico.setEnabled(true);
		chkNoCorreo.setEnabled(true);
		txtCalle.setEnabled(true);
		cmbColonia.setEnabled(true);
		txtNumero.setEnabled(true);
		cmbClasificacion.setEnabled(true);
		// cmbColonias.setEnabled(true);
		txtCalleAledanaA.setEnabled(true);
		JCFechaNacimiento.setEnabled(true);
		txtCalleAledanaB.setEnabled(true);
		txtTelefono.setEnabled(true);
		cmbCalzado.setEnabled(true);
		cmbVestido.setEnabled(true);
		cmbTieneCredito.setEnabled(true);
		txtLimiteCredito.setEnabled(true);
		chkCasaPropia.setEnabled(true);
		chkTrabaja.setEnabled(true);
		txtNombreEmpresa.setEnabled(false);
		txtDomicilioTrabajo.setEnabled(false);
		txtTelefonoTrabajo.setEnabled(false);
		txtPersonal1.setEnabled(true);
		txtPersonal2.setEnabled(true);
		txtComercial1.setEnabled(true);
		txtComercial2.setEnabled(true);
		cmbEstadoCivil.setEnabled(true);
		txtComercial3.setEnabled(true);
		txtApellidoPaterno.setEnabled(true);
		txtApellidoMaterno.setEnabled(true);
		txtNombreConyugue.setEnabled(false);
		txtOcupacionConyugue.setEnabled(false);
		txtDomicilioTrabajoConyugue.setEnabled(false);
		txtNumeroInterior.setEnabled(true);
		txtCelular.setEnabled(true);
		txtOcupacion.setEnabled(true);
		txtEmpresaTrabajoConyugue.setEnabled(true);
		txtFamiliar1.setEnabled(true);
		txtFamiliar2.setEnabled(true);
		this.txtNombreConyugue.setEnabled(true);
		this.txtDomicilioTrabajoConyugue.setEnabled(true);
		this.cmdSeleccionarFoto.setEnabled(true);
		cmdCancelarFoto.setEnabled(true);
	}

	void desHabilitarCajas() {

		this.lstClientesEncontrados.setEnabled(true);
		txtNombre1.setEnabled(true);
		this.cmdBuscar.setEnabled(true);
		txtCorreoElectronico.setEnabled(false);
		this.txtNombreConyugue.setEnabled(false);
		this.txtDomicilioTrabajoConyugue.setEnabled(false);
		txtNumeroInterior.setEnabled(false);
		txtCelular.setEnabled(false);
		chkNoCorreo.setEnabled(false);
		txtOcupacion.setEnabled(false);
		txtNombreEmpresa.setEnabled(false);
		txtEmpresaTrabajoConyugue.setEnabled(false);
		txtFamiliar1.setEnabled(false);
		txtFamiliar2.setEnabled(false);
		cmbClasificacion.setEnabled(false);
		txtNombre.setEnabled(false);
		txtCalle.setEnabled(false);
		cmbColonia.setEnabled(false);
		txtNumero.setEnabled(false);

		txtCalleAledanaA.setEnabled(false);
		JCFechaNacimiento.setEnabled(false);
		txtCalleAledanaB.setEnabled(false);
		txtCiudad.setEnabled(false);
		txtTelefono.setEnabled(false);
		cmbCalzado.setEnabled(false);
		cmbVestido.setEnabled(false);
		cmbTieneCredito.setEnabled(false);
		txtLimiteCredito.setEnabled(false);
		chkCasaPropia.setEnabled(false);
		chkTrabaja.setEnabled(false);
		txtDomicilioTrabajo.setEnabled(false);
		txtTelefonoTrabajo.setEnabled(false);
		txtNombreConyugue.setEnabled(false);
		txtOcupacionConyugue.setEnabled(false);
		txtDomicilioTrabajoConyugue.setEnabled(false);
		txtPersonal1.setEnabled(false);
		txtPersonal2.setEnabled(false);
		txtComercial1.setEnabled(false);
		txtComercial2.setEnabled(false);
		cmbEstadoCivil.setEnabled(false);
		txtComercial3.setEnabled(false);
		txtApellidoPaterno.setEnabled(false);
		txtApellidoMaterno.setEnabled(false);
		cmdSeleccionarFoto.setEnabled(false);
		this.cmdCancelarFoto.setEnabled(false);
	}

	void limpiarCajas() {
		txtNombreConyugue.setText("");
		txtDomicilioTrabajoConyugue.setText("");
		txtNumeroInterior.setText("");
		txtCelular.setText("");
		txtOcupacion.setText("");
		txtNombreEmpresa.setText("");
		txtEmpresaTrabajoConyugue.setText("");
		txtFamiliar1.setText("");
		txtFamiliar2.setText("");
		txtCorreoElectronico.setText("");
		txtNombre.setText("");
		txtCalle.setText("");
		txtCodigoPostal.setText("");
		txtEstado.setText("");
		txtNumero.setText("");
		txtCiudad.setText("");
		txtCalleAledanaA.setText("");
		JCFechaNacimiento.setDate(new java.util.Date());
		txtCalleAledanaB.setText("");
		txtTelefono.setText("");
		txtCodigoCliente.setText("");
		txtCorreoElectronico.setText("");
		cmbVestido.setSelectedIndex(-1);
		cmbCalzado.setSelectedIndex(-1);
		cmbTieneCredito.setSelectedItem(-1);
		txtLimiteCredito.setText("0.00");
		chkCasaPropia.setSelected(false);
		chkTrabaja.setSelected(false);
		txtDomicilioTrabajo.setText("");
		txtTelefonoTrabajo.setText("");
		txtNombreConyugue.setText("");
		txtOcupacionConyugue.setText("");
		txtDomicilioTrabajoConyugue.setText("");
		txtPersonal1.setText("");
		txtPersonal2.setText("");
		txtComercial1.setText("");
		txtComercial2.setText("");
		cmbEstadoCivil.setSelectedIndex(-1);
		cmbColonia.setSelectedIndex(-1);
		txtComercial3.setText("");
		txtApellidoPaterno.setText("");
		txtApellidoMaterno.setText("");
		this.pnlFotografia1.limpiarImagen();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("idCliente")) {
			this.cliente = (Cliente) evt.getNewValue();
			this.desHabilitarCajas();
			this.mostrarDatosCliente(null);
		} else if (evt.getPropertyName().equals("nueva")) {
			JOptionPane.showMessageDialog(null, evt.getNewValue());
		} else if (evt.getPropertyName().equals("accion")) {
			if (evt.getNewValue().equals("add")) {
				this.limpiarCajas();
				this.habilitarCajas();
				this.txtNombre.requestFocus();
			} else if (evt.getNewValue().equals("edit")) {
				if (txtNombre.getText().equals("")) {
					String[] row = this.listaClientesEncontrados
							.get(lstClientesEncontrados.getSelectedIndex());
					Cliente c = engine.setClienteSeleccionado(Integer
							.parseInt(row[0]));
					this.mostrarDatosCliente(c);
				}
				this.habilitarCajas();
				this.txtNombre.requestFocus();
			} else if (evt.getNewValue().equals("delete")) {
				int i = JOptionPane.showConfirmDialog(this.getRootPane(),
						"Esta seguro que desea eliminar el cliente?",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					if (DaoClientesCentral.eliminarCliente(Integer
							.parseInt(txtCodigoCliente.getText()))) {
						JOptionPane
								.showMessageDialog(
										this.getRootPane(),
										"El cliente ha sido eliminado",
										com.boutique.engine.impl.AppInstance.nombreNegocio,
										JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else if (evt.getNewValue().equals("ok")) {
				// Revisamos que esten los datos minimos necesarios
				// this.setDatosCliente();
				/*
				 * if (! ( "".equals(txtApellidoPaterno.getText())
				 * ||"".equals(txtNombre1.getText()) ||
				 * "".equals(txtCalle.getText()) ||
				 * "".equals(txtTelefono.getText()) ||
				 * "".equals(cmbColonia.getSelectedItem()) ||
				 * "".equals(txtColonia.getText()) || "".equals(
				 * "".equals(.getText()))) {
				 */
				if (evt.getOldValue().equals("add")) {
					this.setDatosCliente();
					if (!DaoClientesCentral.agregarCliente(cliente)) {
						JOptionPane.showMessageDialog(null,
								"No se pudo agregar el nuevo cliente");
						// this.pnlOperaciones1.setAccion("add");
						// this.pnlOperaciones1.setAccionExitosa(false);
					} else {
						this.limpiarCajas();
						this.desHabilitarCajas();
						// this.pnlOperaciones1.setAccionExitosa(true);
					}
				} else if (evt.getOldValue().equals("edit")) {

					this.setDatosCliente();
					if (!DaoClientesCentral.actualizarCliente(cliente,
							file.length())) {
						JOptionPane.showMessageDialog(null,
								"No se pudo actualizar los datos del");
						// this.pnlOperaciones1.setAccion("add");

					} else {
						this.desHabilitarCajas();
						// this.pnlOperaciones1.setAccionExitosa(true);
					}

				}
				/*
				 * } else { JOptionPane.showMessageDialog(null,
				 * "Indique la información minima necesaria"); }
				 */
			} else if (evt.getNewValue().equals("cancel")) {
				this.desHabilitarCajas();
				this.limpiarCajas();
			}

		}

	}

	void cmdBuscar_actionPerformed(ActionEvent e) {
		// Buscamos los clientes en el engine y los mostramos
		this.setCursor(AppInstance.waitCursor);
		this.listaClientesEncontrados = DaoClientesCentral
				.findByNombreCompleto(this.txtNombre1.getText());
		modelClientesEncontrados.clear();
		for (String[] s : listaClientesEncontrados) {
			modelClientesEncontrados.addElement(s[1]);
		}
		this.setCursor(AppInstance.defCursor);

	}

	public void cmbClasificacion_itemStateChanged(ItemEvent e) {

	}

	public void cmbColonia_itemStateChanged(ItemEvent e) {
		if (cmbColonia.getSelectedIndex() >= 0) {
			engine.seleccionarColonia(cmbColonia.getSelectedIndex());
			this.txtCiudad.setText(engine.coloniaSeleccionada().getCiudad());
			txtCodigoPostal.setText(engine.coloniaSeleccionada().getCp());
			txtEstado.setText(engine.coloniaSeleccionada().getEstado());
		}
	}

	public void chkTieneCredito_itemStateChanged(ItemEvent e) {
		if (cmbTieneCredito.getSelectedItem().toString().equals("AUTORIZADO")) {
			this.txtLimiteCredito.setEnabled(true);
		} else {
			this.txtLimiteCredito.setEnabled(false);
			this.txtLimiteCredito.setText("0.00");
		}
	}

	void cajasEstadoCivil() {
		if (cmbEstadoCivil.getSelectedIndex() >= 0) {
			String edoCivil = cmbEstadoCivil.getSelectedItem().toString();
			if (edoCivil.equals("Casado") || edoCivil.equals("Union libre")) {
				txtNombreConyugue.setEnabled(true);
				txtOcupacionConyugue.setEnabled(true);
				txtDomicilioTrabajoConyugue.setEnabled(true);
				this.txtEmpresaTrabajoConyugue.setEnabled(true);

			} else {
				txtNombreConyugue.setEnabled(false);
				txtOcupacionConyugue.setEnabled(false);
				txtDomicilioTrabajoConyugue.setEnabled(false);
				this.txtEmpresaTrabajoConyugue.setEnabled(false);
				this.txtEmpresaTrabajoConyugue.setText("");
				txtNombreConyugue.setText("");
				txtOcupacionConyugue.setText("");
				txtDomicilioTrabajoConyugue.setText("");
			}
		}
	}

	public void cmbEstadoCivil_itemStateChanged(ItemEvent e) {
		if (cmbEstadoCivil.isEnabled()) {
			cajasEstadoCivil();
		}
	}

	public void lstClientesEncontrados_mouseClicked(MouseEvent e) {
		// Sacamos el String[] con el cliente seleccionado
		this.setCursor(AppInstance.waitCursor);
		if (lstClientesEncontrados.isEnabled()
				&& listaClientesEncontrados.size() > 0) {
			String[] row = this.listaClientesEncontrados
					.get(lstClientesEncontrados.getSelectedIndex());
			Cliente c = engine.setClienteSeleccionado(Integer.parseInt(row[0]));
			this.mostrarDatosCliente(c);
		}
		this.setCursor(AppInstance.defCursor);
	}

	void cajasTrabaja() {
		if (!chkTrabaja.isSelected()) {
			this.txtNombreEmpresa.setEnabled(false);
			this.txtDomicilioTrabajo.setEnabled(false);
			this.txtTelefonoTrabajo.setEnabled(false);
		} else {
			this.txtNombreEmpresa.setEnabled(true);
			this.txtDomicilioTrabajo.setEnabled(true);
			this.txtTelefonoTrabajo.setEnabled(true);
		}

	}

	public void chkTrabaja_actionPerformed(ActionEvent e) {
		cajasTrabaja();
	}

	public void chkNoCorreo_actionPerformed(ActionEvent e) {
		if (chkNoCorreo.isSelected()) {
			this.txtCorreoElectronico.setText("");
			this.txtCorreoElectronico.setEditable(false);
		} else {
			this.txtCorreoElectronico.setText("");
			this.txtCorreoElectronico.setEditable(true);
		}

	}

	public void txtNombre1_actionPerformed(ActionEvent e) {
		// Buscamos los clientes en el engine y los mostramos
		this.listaClientesEncontrados = DaoClientesCentral
				.findByNombreCompleto(this.txtNombre1.getText());
		modelClientesEncontrados.clear();
		for (String[] s : listaClientesEncontrados) {
			modelClientesEncontrados.addElement(s[1]);
		}
	}

	public void cmdSeleccionarFoto_actionPerformed(ActionEvent e) {
		// BUSCAMOS LA FOTO PARA EL SOCIO
		JFileChooser fl = new JFileChooser();
		fl.showOpenDialog(this);
		// System.out.println(fl.getSelectedFile().toString());
		if (fl.getSelectedFile() != null && !"".equals(fl.getSelectedFile())) {
			pnlFotografia1.setImagen(fl.getSelectedFile().toString());
		} else {
			pnlFotografia1.setImagen((InputStream) null);
		}

	}

	public void txtCalle_actionPerformed(ActionEvent e) {

	}

	public void txtNombreEmpresa_actionPerformed(ActionEvent e) {

	}

	public void cmdCancelarFoto_actionPerformed(ActionEvent e) {
		pnlFotografia1.setImagen((InputStream) null);
		file = null;
		this.setCursor(AppInstance.defCursor);

	}

	private class pnlCatalogosOperaciones extends JPanel {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		JButton cmdAceptar = new JButton();
		JButton cmdEliminar = new JButton();
		JButton cmdCancelar = new JButton();
		JButton cmdModificar = new JButton();
		JButton cmdAgregar = new JButton();
		JButton cmdExportar = new JButton();
		JButton cmdCumpleanos = new JButton();
		FlowLayout flowLayout1 = new FlowLayout();
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
			// cmdBuscar.setText("Buscar");
			// cmdBuscar.addActionListener(new
			// pnlCatalogosOperaciones_cmdBuscar_actionAdapter(this));
			cmdExportar.setText("Exportar");
			cmdExportar
					.addActionListener(new pnlCatalogosOperaciones_cmdExportar_actionAdapter(
							this));

			cmdCumpleanos.setText("Cumpleaños");
			cmdCumpleanos
					.addActionListener(new pnlCatalogosOperaciones_cmdCumpleanos_actionAdapter(
							this));

			if (AppInstance.usuario().getExportar() > 0) {
				cmdExportar.setVisible(true);
				cmdCumpleanos.setVisible(true);
			} else {
				cmdExportar.setVisible(false);
				cmdCumpleanos.setVisible(false);

			}

			this.add(cmdAgregar, null);
			this.add(cmdModificar, null);
			this.add(cmdEliminar, null);
			this.add(cmdAceptar, null);
			this.add(cmdCancelar, null);
			// this.add(cmdBuscar, null);
			this.add(cmdExportar, null);
			this.add(cmdCumpleanos, null);

		}

		void desHabilitarBotones() {
			cmdAgregar.setEnabled(false);
			cmdModificar.setEnabled(false);
			cmdEliminar.setEnabled(false);
			cmdAceptar.setEnabled(true);
			cmdCancelar.setEnabled(true);
			// cmdBuscar.setEnabled(false);
			cmdExportar.setEnabled(false);
			cmdCumpleanos.setEnabled(false);
		}

		void habilitarBotones() {
			cmdAgregar.setEnabled(true);
			cmdModificar.setEnabled(true);
			cmdEliminar.setEnabled(true);
			// cmdBuscar.setEnabled(true);
			cmdAceptar.setEnabled(false);
			cmdCancelar.setEnabled(false);
			if (AppInstance.usuario().getExportar() > 0) {
				cmdExportar.setEnabled(true);
				cmdCumpleanos.setEnabled(true);
			}
		}

		public void cmdAgregar_actionPerformed(ActionEvent e) {
			this.setCursor(AppInstance.waitCursor);
			operacion = "agregar";
			limpiarCajas();
			habilitarCajas();
			txtNombre.requestFocus();
			this.desHabilitarBotones();
			this.setCursor(AppInstance.defCursor);

		}

		public void cmdModificar_actionPerformed(ActionEvent e) {
			this.setCursor(AppInstance.waitCursor);
			operacion = "modificar";
			if (txtNombre.getText().equals("")) {
				String[] row = listaClientesEncontrados
						.get(lstClientesEncontrados.getSelectedIndex());
				Cliente c = engine.setClienteSeleccionado(Integer
						.parseInt(row[0]));
				mostrarDatosCliente(c);
			}
			habilitarCajas();
			cajasEstadoCivil();
			cajasTrabaja();
			desHabilitarBotones();
			txtNombre.requestFocus();
			this.setCursor(AppInstance.defCursor);
		}

		public void cmdEliminar_actionPerformed(ActionEvent e) {
			int i = JOptionPane.showConfirmDialog(this.getRootPane(),
					"Esta seguro que desea eliminar el cliente?",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				this.setCursor(AppInstance.waitCursor);
				if (DaoClientesCentral.eliminarCliente(Integer
						.parseInt(txtCodigoCliente.getText()))) {
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(this.getRootPane(),
							"El cliente ha sido eliminado",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.INFORMATION_MESSAGE);
				}
				this.setCursor(AppInstance.defCursor);
			}

		}

		public void cmdAceptar_actionPerformed(ActionEvent e) {
			this.setCursor(AppInstance.waitCursor);
			if (operacion.equals("agregar")) {
				setDatosCliente();
				if (!DaoClientesCentral.agregarCliente(cliente,
						((file == null) ? 0 : file.length()))) {
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(null,
							"No se pudo agregar el nuevo cliente",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
					// this.pnlOperaciones1.setAccion("add");
					// this.pnlOperaciones1.setAccionExitosa(false);
				} else {
					limpiarCajas();
					desHabilitarCajas();
					habilitarBotones();
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(null,
							"Cliente agregado exitosamente");

					// this.pnlOperaciones1.setAccionExitosa(true);
				}
			} else if (operacion.equals("modificar")) {

				setDatosCliente();
				if (!DaoClientesCentral.actualizarCliente(cliente,
						((file != null) ? file.length() : 0))) {
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(null,
							"No se pudo actualizar los datos del cliente",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
					// this.pnlOperaciones1.setAccion("add");

				} else {
					desHabilitarCajas();
					habilitarBotones();
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(null,
							"Cliente modificado exitosamente");

					// this.pnlOperaciones1.setAccionExitosa(true);
				}

			}
		}

		public void cmdCancelar_actionPerformed(ActionEvent e) {
			this.habilitarBotones();
			operacion = "";

			desHabilitarCajas();
		}

		public void cmdCumpleanos_actionPerformed(ActionEvent e) {
			this.setCursor(AppInstance.waitCursor);
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("clientes_cumpleanos.jasper");

			DlgIntervaloFechas dlg = new DlgIntervaloFechas();
			dlg.jdFechaFinal.setEnabled(false);
			dlg.setSize(200, 200);
			dlg.setModal(true);
			dlg.setLocationRelativeTo(this.getRootPane());
			dlg.setVisible(true);
			this.getRootPane().setCursor(AppInstance.waitCursor);
			try {

				// Pasamos parametros al reporte Jasper.
				Calendar cal = Calendar.getInstance();

				cal.setTime(dlg.jdFechaInicial.getDate());
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Map<String, Object> parameters = new HashMap<String, Object>();

				// Preparacion del reporte (en esta etapa se inserta el valor
				parameters.put("fechaInicial",
						new java.sql.Timestamp(cal.getTimeInMillis()));

				/*
				 * cal.setTime(dlg.jdFechaFinal.getDate());
				 * cal.set(Calendar.HOUR, 23); cal.set(Calendar.MINUTE, 59);
				 * cal.set(Calendar.SECOND, 59); parameters.put("fechaFinal",
				 * new java.sql.Timestamp(cal.getTimeInMillis()));
				 */
				// Preparacion del reporte (en esta etapa se inserta el valor
				JasperPrint listo = JasperFillManager.fillReport(st,
						parameters,
						com.boutique.dao.DaoSource.getConnectionLocal());
				JasperViewer vw = new JasperViewer(listo, false);
				// net.sf.jasperreports.view.JRViewer jrv = new
				// net.sf.jasperreports.view.JRViewer(listo);
				// jrv.setVisible(true);
				vw.setTitle("CUMPLEAÑOS DE CLIENTES");
				this.getRootPane().setCursor(AppInstance.defCursor);
				vw.setVisible(true);
				// Creación del PDF
				// JasperExportManager.exportReportToPdfFile(listo,
				// destFileNamePdf);
				// JasperViewer.viewReport(listo);

				// Creación del XLS
				/*
				 * JRXlsExporter exporter = new JRXlsExporter();
				 * exporter.setParameter(JRExporterParameter.JASPER_PRINT,listo)
				 * ; exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				 * "prueba.xls") ; exporter.setParameter(JRXlsExporterParameter.
				 * IS_ONE_PAGE_PER_SHEET, Boolean.TRUE) ;
				 * //exporter.exportReport(); exporter.exportReport();
				 * 
				 * //System.exit(0);
				 */

			} catch (Exception ed) {
				ed.printStackTrace();
				System.out.println(ed.getMessage());
			}
			this.setCursor(AppInstance.defCursor);
		}

		public void cmdExportar_actionPerformed(ActionEvent e) {
			this.setCursor(AppInstance.waitCursor);
			try {

				// System.out.println(com.boutique.impresiones.ImpresionDevolucion.class.getResource("clientes.jasper").getPat
				InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
						.getResourceAsStream("clientes2014.jasper");

				// // File fdesign = new File(
				// com.boutique.impresiones.ImpresionDevolucion.class.getResource("clientes.jasper").getPath());

				// Ruta de archivo pdf de destino

				// Ruta de archivo xls de destino
				// String destFileNameXls="C:\\SIAU\\reportes\\reporte1.xls";

				// Pasamos parametros al reporte Jasper.
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters
						.put("idBoutique", com.boutique.engine.impl.AppInstance
								.boutique().getId());
				// Preparacion del reporte (en esta etapa se inserta el valor
				JasperPrint listo = JasperFillManager.fillReport(st,
						parameters,
						com.boutique.dao.DaoSource.getConnectionLocal());
				JasperViewer vw = new JasperViewer(listo, false);
				// net.sf.jasperreports.view.JRViewer jrv = new
				// net.sf.jasperreports.view.JRViewer(listo);
				// jrv.setVisible(true);
				vw.setTitle("LISTA DE CLIENTES");
				this.setCursor(AppInstance.defCursor);
				vw.setVisible(true);
				// Creación del PDF
				// JasperExportManager.exportReportToPdfFile(listo,
				// destFileNamePdf);
				// JasperViewer.viewReport(listo);

				// Creación del XLS
				/*
				 * JRXlsExporter exporter = new JRXlsExporter();
				 * exporter.setParameter(JRExporterParameter.JASPER_PRINT,listo)
				 * ; exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				 * "prueba.xls") ; exporter.setParameter(JRXlsExporterParameter.
				 * IS_ONE_PAGE_PER_SHEET, Boolean.TRUE) ;
				 * //exporter.exportReport(); exporter.exportReport();
				 * 
				 * //System.exit(0);
				 */

			} catch (Exception ed) {
				ed.printStackTrace();
				System.out.println(ed.getMessage());
				this.setCursor(AppInstance.defCursor);

			}

		}

		public void cmdBuscar_actionPerformed(ActionEvent e) {
			txtNombre1.requestFocus();
		}
	}

	class pnlCatalogosOperaciones_cmdBuscar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdBuscar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdBuscar_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdCancelar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdCancelar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdCancelar_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdExportar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdExportar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdExportar_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdCumpleanos_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdCumpleanos_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdCumpleanos_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdAceptar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdAceptar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdAceptar_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdEliminar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdEliminar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdEliminar_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdModificar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdModificar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdModificar_actionPerformed(e);
		}
	}

	class pnlCatalogosOperaciones_cmdAgregar_actionAdapter implements
			ActionListener {
		private final pnlCatalogosOperaciones adaptee;

		pnlCatalogosOperaciones_cmdAgregar_actionAdapter(
				pnlCatalogosOperaciones adaptee) {
			this.adaptee = adaptee;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			adaptee.cmdAgregar_actionPerformed(e);
		}
	}

}

class PnlDirectorioClientes_cmdCancelarFoto_actionAdapter implements
		ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmdCancelarFoto_actionAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelarFoto_actionPerformed(e);
	}
}

class PnlDirectorioClientes_txtNombreEmpresa_actionAdapter implements
		ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_txtNombreEmpresa_actionAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		adaptee.txtNombreEmpresa_actionPerformed(e);
	}
}

class PnlDirectorioClientes_txtCalle_actionAdapter implements ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_txtCalle_actionAdapter(PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.txtCalle_actionPerformed(e);
	}
}

class PnlDirectorioClientes_cmdSeleccionarFoto_actionAdapter implements
		ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmdSeleccionarFoto_actionAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSeleccionarFoto_actionPerformed(e);
	}
}

class PnlDirectorioClientes_chkTrabaja_actionAdapter implements ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_chkTrabaja_actionAdapter(PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.chkTrabaja_actionPerformed(e);
	}
}

class PnlDirectorioClientes_chkNoCorreo_actionAdapter implements ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_chkNoCorreo_actionAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.chkNoCorreo_actionPerformed(e);
	}
}

class PnlDirectorioClientes_lstClientesEncontrados_mouseAdapter extends
		MouseAdapter {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_lstClientesEncontrados_mouseAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		adaptee.lstClientesEncontrados_mouseClicked(e);
	}
}

class PnlDirectorioClientes_cmbEstadoCivil_itemAdapter implements ItemListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmbEstadoCivil_itemAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		adaptee.cmbEstadoCivil_itemStateChanged(e);
	}
}

class PnlDirectorioClientes_cmbClasificacion_itemAdapter implements
		ItemListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmbClasificacion_itemAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		adaptee.cmbClasificacion_itemStateChanged(e);
	}
}

class PnlDirectorioClientes_chkTieneCredito_itemAdapter implements ItemListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_chkTieneCredito_itemAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		adaptee.chkTieneCredito_itemStateChanged(e);
	}
}

class PnlDirectorioClientes_cmbColonia_itemAdapter implements ItemListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmbColonia_itemAdapter(PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		adaptee.cmbColonia_itemStateChanged(e);
	}
}

class PnlDirectorioClientes_cmdBuscar_actionAdapter implements
		java.awt.event.ActionListener {
	PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmdBuscar_actionAdapter(PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}

class PnlDirectorioClientes_cmbClasificacion_actionAdapter implements
		ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_cmbClasificacion_actionAdapter(
			PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmbClasificacion_actionPerformed(e);
	}
}

class PnlDirectorioClientes_txtNombre1_actionAdapter implements ActionListener {
	private final PnlDirectorioClientes adaptee;

	PnlDirectorioClientes_txtNombre1_actionAdapter(PnlDirectorioClientes adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.txtNombre1_actionPerformed(e);
	}
}
