package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.boutique.domain.Cliente;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.boutique.dao.DaoClientesCentral;
import com.boutique.engine.impl.AppInstance;
import com.boutique.domain.Colonia;
import com.boutique.dao.DaoColonias;
import com.boutique.util.Formateador;
import com.toedter.calendar.JDateChooser;
import com.boutique.domain.ClasificacionCliente;
import com.boutique.dao.DaoClientesSucursal;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import com.boutique.util.Conversor;

public class DlgActualizarDatosClientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean correoElectronicoRequerido = false;
	JPanel panel1 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlNorte = new JPanel();
	JLabel lblTitulo = new JLabel();
	JPanel pnlCentro = new JPanel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel9 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JLabel jLabel11 = new JLabel();
	JLabel lblRfc = new JLabel();
	JTextField txtNombre = new JTextField();
	JTextField txtApellidoPaterno = new JTextField();
	JTextField txtApellidoMaterno = new JTextField();
	JTextField txtCalle = new JTextField();
	JTextField txtNumero = new JTextField();
	JTextField txtInterior = new JTextField();
	JComboBox cmbColonia = new JComboBox();
	JTextField txtTelefono = new JTextField();
	JTextField txtCelular = new JTextField();
	JTextField txtLastUpdate = new JTextField();
	JTextField txtRfc = new JTextField();
	JButton cmdActualizarDatos = new JButton();
	public Cliente cliente = null;
	JLabel jLabel12 = new JLabel();
	JLabel jLabel13 = new JLabel();
	JComboBox cmbCalzado = new JComboBox();
	JComboBox cmbVestido = new JComboBox();
	JLabel jLabel14 = new JLabel();
	JTextField txtCorreoElectronico = new JTextField();
	JCheckBox chkNoCorreo = new JCheckBox();
	JDateChooser jdcCumpleanos = new JDateChooser();
	JLabel jLabel15 = new JLabel();
	JLabel lblLastUpdate = new JLabel();

	JButton cmdContinuar = new JButton();
	String tipoOperacion = "";
	PnlFotografia pnlFotografia1 = new PnlFotografia();
	JButton cmdExaminar = new JButton();
	JButton cmdCancelar = new JButton();
	private File file;

	public DlgActualizarDatosClientes(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {

			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgActualizarDatosClientes(Cliente cliente, String tipoOperacion,
			boolean correoElectronicoRequerido) {
		this(new Frame(), AppInstance.nombreNegocio, false);
		this.tipoOperacion = tipoOperacion;
		this.cliente = cliente;
		this.correoElectronicoRequerido = correoElectronicoRequerido;

		if (tipoOperacion.equals("AGREGAR")) {
			this.lblTitulo.setText("CAPTURE LOS DATOS DEL CLIENTE");
			this.cmdActualizarDatos.setText("AGREGAR CLIENTE");
			this.cmdContinuar.setText("CONTINUAR SIN AGREGAR");
		}
	}

	private void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		lblTitulo.setText("CAPTURE LOS NUEVOS DATOS DEL CLIENTE");
		pnlCentro.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.addWindowListener(new DlgActualizarDatosClientes_this_windowAdapter(
				this));
		panel1.setBackground(Color.white);
		pnlNorte.setBackground(Color.white);
		jLabel2.setText("APELLIDO PATERNO");
		jLabel2.setBounds(new Rectangle(32, 61, 118, 19));
		jLabel3.setText("APELLIDO MATERNO");
		jLabel3.setBounds(new Rectangle(211, 61, 117, 19));
		jLabel5.setText("CALLE");
		jLabel5.setBounds(new Rectangle(33, 114, 287, 19));
		jLabel6.setText("NUMERO");
		jLabel6.setBounds(new Rectangle(32, 169, 53, 19));
		jLabel7.setText("NUM. INTERIOR");
		jLabel7.setBounds(new Rectangle(253, 169, 86, 19));
		jLabel8.setText("NOMBRE(S)");
		jLabel8.setBounds(new Rectangle(32, 11, 287, 19));
		jLabel9.setText("COLONIA:");
		jLabel9.setBounds(new Rectangle(32, 223, 87, 19));
		jLabel10.setText("TELEFONO PARTICULAR");
		jLabel10.setBounds(new Rectangle(32, 274, 135, 19));
		jLabel11.setText("TELEFONO CELULAR");
		jLabel11.setBounds(new Rectangle(255, 274, 129, 19));
		txtNombre.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtNombre.setText("");
		txtNombre.setBounds(new Rectangle(32, 30, 328, 28));
		txtApellidoPaterno.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtApellidoPaterno.setText("");
		txtApellidoPaterno.setBounds(new Rectangle(32, 81, 152, 28));
		txtApellidoMaterno.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtApellidoMaterno.setText("");
		txtApellidoMaterno.setBounds(new Rectangle(208, 81, 152, 28));
		txtCalle.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtCalle.setText("");
		txtCalle.setBounds(new Rectangle(32, 134, 328, 28));
		txtNumero.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtNumero.setText("");
		txtNumero.setBounds(new Rectangle(32, 190, 200, 28));
		txtInterior.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtInterior.setText("");
		txtInterior.setBounds(new Rectangle(255, 190, 200, 28));
		cmbColonia.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		cmbColonia.setActionCommand("cmbColonias");
		cmbColonia.setBounds(new Rectangle(32, 245, 422, 28));
		txtTelefono.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtTelefono.setText("");
		txtTelefono.setBounds(new Rectangle(32, 294, 200, 28));
		txtCelular.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtCelular.setText("");
		txtCelular.setBounds(new Rectangle(255, 294, 200, 28));
		txtLastUpdate.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		txtLastUpdate.setText("");
		txtLastUpdate.setEnabled(false);
		txtLastUpdate.setDisabledTextColor(Color.RED);
		cmdActualizarDatos.setText("ACTUALIZAR DATOS");
		cmdActualizarDatos
				.addActionListener(new DlgActualizarDatosClientes_cmdActualizarDatos_actionAdapter(
						this));
		jLabel12.setText("TALLA DE CALZADO");
		jLabel12.setBounds(new Rectangle(33, 327, 144, 19));
		jLabel13.setText("TALLA DE VESTIDO");
		jLabel13.setBounds(new Rectangle(255, 327, 143, 18));
		cmbCalzado.setBounds(new Rectangle(32, 347, 200, 27));
		cmbVestido.setBounds(new Rectangle(254, 347, 200, 27));
		jLabel14.setText("CORREO ELECTRONICO");
		jLabel14.setBounds(new Rectangle(33, 376, 153, 18));
		txtCorreoElectronico
				.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtCorreoElectronico.setText("");
		txtCorreoElectronico.setBounds(new Rectangle(32, 395, 422, 28));
		chkNoCorreo.setText("NO TIENE CORREO");
		chkNoCorreo.setBounds(new Rectangle(32, 427, 127, 23));
		chkNoCorreo
				.addActionListener(new DlgActualizarDatosClientes_chkNoCorreo_actionAdapter(
						this));

		jdcCumpleanos.setBounds(new Rectangle(255, 445, 200, 28));

		lblLastUpdate.setBounds(new Rectangle(32, 535, 200, 28));
		txtLastUpdate.setBounds(new Rectangle(32, 560, 415, 28));

		lblLastUpdate.setText("ULTIMA ACTUALIZACION");
		jLabel15.setText("CUMPLEAÑOS");
		jLabel15.setBounds(new Rectangle(255, 423, 151, 22));

		lblRfc.setText("RFC");
		lblRfc.setBounds(new Rectangle(32, 460, 200, 18));
		txtRfc.setBounds(new Rectangle(32, 480, 200, 28));
		cmdContinuar.setBounds(new Rectangle(254, 592, 188, 29));
		cmdActualizarDatos.setBounds(new Rectangle(61, 592, 185, 29));

		cmdContinuar.setText("CONTINUAR SIN ACTUALIZAR");
		cmdContinuar
				.addActionListener(new DlgActualizarDatosClientes_jButton1_actionAdapter(
						this));
		pnlFotografia1.setBounds(new Rectangle(367, 21, 106, 120));
		cmdExaminar.setBounds(new Rectangle(382, 143, 32, 32));
		cmdExaminar.setPreferredSize(new Dimension(32, 32));
		cmdExaminar.setText("");
		cmdExaminar
				.addActionListener(new DlgActualizarDatosClientes_cmdExaminar_actionAdapter(
						this));

		cmdCancelar.setBounds(new Rectangle(420, 143, 32, 32));
		cmdCancelar.setText("");
		cmdCancelar
				.addActionListener(new DlgActualizarDatosClientes_cmdCancelar_actionAdapter(
						this));

		getContentPane().add(panel1);
		pnlNorte.add(lblTitulo);
		panel1.add(pnlCentro, java.awt.BorderLayout.CENTER);
		pnlCentro.add(jLabel8);
		pnlCentro.add(txtNombre);
		pnlCentro.add(txtApellidoPaterno);
		pnlCentro.add(txtApellidoMaterno);
		pnlCentro.add(jLabel2);
		pnlCentro.add(jLabel3);
		pnlCentro.add(txtTelefono);
		pnlCentro.add(jLabel10);
		pnlCentro.add(cmbColonia);
		pnlCentro.add(jLabel9);
		pnlCentro.add(txtNumero);
		pnlCentro.add(txtCalle);
		pnlCentro.add(jLabel5);
		pnlCentro.add(jLabel6);
		pnlCentro.add(jLabel12);
		pnlCentro.add(cmbCalzado);
		pnlCentro.add(jLabel14);
		pnlCentro.add(txtCorreoElectronico);
		pnlCentro.add(chkNoCorreo);
		pnlCentro.add(pnlFotografia1);
		pnlCentro.add(cmdContinuar);
		pnlCentro.add(txtLastUpdate);
		pnlCentro.add(cmdActualizarDatos);
		pnlCentro.add(txtInterior);
		pnlCentro.add(jLabel7);
		pnlCentro.add(txtCelular);
		pnlCentro.add(jLabel11);
		pnlCentro.add(jLabel13);
		pnlCentro.add(cmbVestido);
		pnlCentro.add(jLabel15);
		pnlCentro.add(lblLastUpdate);
		pnlCentro.add(jdcCumpleanos);
		pnlCentro.add(cmdExaminar);
		pnlCentro.add(cmdCancelar);
		pnlCentro.add(lblRfc);
		pnlCentro.add(txtRfc);
		panel1.add(pnlNorte, java.awt.BorderLayout.NORTH);
		cmdCancelar.setIcon(new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/cancel.png")));
		cmdExaminar.setIcon(new ImageIcon(
				com.boutique.view.FrmAppBoutique.class
						.getResource("img/foto.png")));

	}

	boolean validar() {
		String str = "";
		if ("".equals(this.txtNombre.getText())) {
			str += "Nombre, ";
		}
		if ("".equals(this.txtApellidoPaterno.getText())) {
			str += "Apellido paterno, ";
		}
		if ("".equals(this.txtCalle.getText())) {
			str += "Calle, ";
		}
		if ("".equals(this.txtNumero.getText())) {
			str += "Numero, ";
		}
		if ("".equals(this.cmbColonia.getSelectedItem())) {
			str += "Colonia, ";
		}
		if ("".equals(this.cmbCalzado.getSelectedItem())) {
			str += "Talla de calzado, ";
		}
		if ("".equals(this.cmbVestido.getSelectedItem())) {
			str += "Talla de conjunto, ";
		}

		if ("".equals(this.txtTelefono.getText())) {
			str += "Telefono particular, ";
		}
		if ("".equals(this.txtCelular.getText())) {
			str += "Celular, ";
		}
		if (!(this.jdcCumpleanos.getDate() != null && this.jdcCumpleanos
				.getDate().compareTo(new java.util.Date()) != 0)) {
			str += "Cumpleaños, ";
		}
		if (!str.equals("")) {
			str = str.substring(0, str.lastIndexOf(","));
			this.setCursor(AppInstance.defCursor);
			JOptionPane.showMessageDialog(this,
					"Los siguientes datos deben ser indicados:\n\r" + str,
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	public void cmdActualizarDatos_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		if (validar()) {
			// LOS DATOS YA SON VALIDOS, AHORA ACTUALIZAMOS LA INFORMACION
			cliente.setNombre(txtNombre.getText().toUpperCase());
			cliente.setApellidoPaterno(txtApellidoPaterno.getText()
					.toUpperCase());
			cliente.setApellidoMaterno(txtApellidoMaterno.getText()
					.toUpperCase());
			cliente.setCalle(txtCalle.getText().toUpperCase());
			cliente.setNumero(txtNumero.getText().toUpperCase());
			cliente.setNumeroInterior(txtInterior.getText().toUpperCase());
			cliente.setTelefono(txtTelefono.getText().toUpperCase());
			cliente.setCelular(txtCelular.getText().toUpperCase());
			cliente.setColonia(cmbColonia.getSelectedItem().toString()
					.toUpperCase());
			cliente.setTallaCalzado(this.cmbCalzado.getSelectedItem()
					.toString().toUpperCase());
			cliente.setTallaConjunto(this.cmbVestido.getSelectedItem()
					.toString().toUpperCase());
			cliente.setFechaNacimiento(new java.sql.Date(this.jdcCumpleanos
					.getDate().getTime()));
			// TODO assign RFC
			if (null != txtRfc.getText()) {
				cliente.setRfc(txtRfc.getText().toUpperCase());
			}
			if (chkNoCorreo.isSelected()) {
				cliente.setEmail("");
				cliente.setNoEmail("1");
			} else {
				if (Formateador.validateEmail(txtCorreoElectronico.getText())) {
					cliente.setEmail(txtCorreoElectronico.getText()
							.toLowerCase());
					cliente.setNoEmail("0");
				} else { // NO SE HA INDICADO NINGUN CORREO, NOS VAMOS
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(this.getRootPane(),
							"DEBE INDICAR UN CORREO ELECTRONICO VALIDO",
							AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			if (tipoOperacion.equals("ACTUALIZAR")) {
				cliente.setFotografia(getFotoAjustada());
				if (DaoClientesCentral.actualizarCliente(cliente,
						((file != null) ? file.length() : 0))) { // TENGO
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(this,
							"DATOS ACTUALIZADOS CORRECTAMENTE",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.WARNING_MESSAGE);

				} else {
					this.setCursor(AppInstance.defCursor);
					JOptionPane
							.showMessageDialog(
									this,
									"LOS DATOS NO PUDIERON SER ACTUALIZADOS, ¿QUIERES CONTINUAR CON LA VENTA?",
									AppInstance.nombreNegocio,
									JOptionPane.YES_NO_OPTION);
				}
			} else if (tipoOperacion.equals("AGREGAR")) {
				InputStream is = getFotoAjustada();
				if (is != null) {
					cliente.setFotografia(is);
				}

				cliente.setTieneCredito("CONTADO");
				cliente.setEstadoCivil("SOLTERO");
				cliente.setCasaPropia("0");
				cliente.setTrabaja("0");
				ClasificacionCliente cc = DaoClientesSucursal
						.findClasificacionContado();
				cliente.setClasificacionCliente(cc);

				if (DaoClientesCentral.agregarCliente(cliente,
						((file != null) ? file.length() : 0))) { // TENGO
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(this,
							"CLIENTE AGREGADO CORRECTAMENTE",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.WARNING_MESSAGE);

				} else {
					this.setCursor(AppInstance.defCursor);
					JOptionPane.showMessageDialog(this,
							"NO SE PUDO REGISTRAR EL NUEVO CLIENTE",
							AppInstance.nombreNegocio, JOptionPane.OK_OPTION);
				}
			}
			// Actualizando datos del cliente
			cliente = DaoClientesCentral.findById(cliente.getId());
			this.setVisible(false);

		}

	}

	public void this_windowOpened(WindowEvent e) {
		this.setCursor(AppInstance.waitCursor);
		this.txtNombre.setText(cliente.getNombre());
		this.txtApellidoMaterno.setText(cliente.getApellidoMaterno());
		this.txtApellidoPaterno.setText(cliente.getApellidoPaterno());
		this.txtCalle.setText(cliente.getCalle());
		this.txtNumero.setText(cliente.getNumero());
		this.txtInterior.setText(cliente.getNumeroInterior());
		this.txtTelefono.setText(cliente.getTelefono());
		this.txtCelular.setText(cliente.getCelular());
		this.txtRfc.setText(cliente.getRfc());
		if (cliente.getFotografia() != null) {
			this.pnlFotografia1.setImagen(cliente.getFotografia());
		}
		java.util.List<Colonia> colonias = DaoColonias.findAll();
		for (Colonia c : colonias) {
			cmbColonia.addItem(c.getNombre());
		}
		cmbColonia.setSelectedItem(cliente.getColonia());
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
		this.cmbCalzado.setSelectedItem(cliente.getTallaCalzado());
		this.cmbVestido.setSelectedItem(cliente.getTallaConjunto());
		if (cliente.getNoEmail().equals("1")) {
			this.chkNoCorreo.setSelected(true);
		} else {
			this.chkNoCorreo.setSelected(false);
			this.txtCorreoElectronico.setText(cliente.getEmail());
		}
		if (correoElectronicoRequerido) {
			chkNoCorreo.setSelected(false);
			chkNoCorreo.setEnabled(false);
		}
		if (cliente.getFechaNacimiento() != null) {
			this.jdcCumpleanos.setDate(cliente.getFechaNacimiento());
		}
		txtLastUpdate
				.setText((cliente.getLastUpdate() != null) ? AppInstance.formatoLargo
						.format(cliente.getLastUpdate())
						+ ", hace: "
						+ Conversor.tiempoTranscurrido(cliente.getLastUpdate())
						: "Nunca actualizado");

		this.setCursor(AppInstance.defCursor);
	}

	public void chkNoCorreo_actionPerformed(ActionEvent e) {
		if (chkNoCorreo.isSelected()) {
			this.txtCorreoElectronico.setEnabled(false);
		} else {
			this.txtCorreoElectronico.setEnabled(true);
		}
	}

	public void jButton1_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void cmdExaminar_actionPerformed(ActionEvent e) {
		// BUSCAMOS LA FOTO PARA EL SOCIO
		this.setCursor(AppInstance.waitCursor);
		JFileChooser fl = new JFileChooser();
		fl.showOpenDialog(this);
		if (fl.getSelectedFile() != null && !"".equals(fl.getSelectedFile())) {
			pnlFotografia1.setImagen(fl.getSelectedFile().toString());
		} else {
			pnlFotografia1.setImagen((InputStream) null);
		}
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdCancelar_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		pnlFotografia1.setImagen((InputStream) null);
		file = null;
		this.setCursor(AppInstance.defCursor);
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

	public void setCorreoElectronicoRequerido(boolean correoElectronicoRequerido) {
		this.correoElectronicoRequerido = correoElectronicoRequerido;
	}

	public boolean isCorreoElectronicoRequerido() {
		return correoElectronicoRequerido;
	}

}

class DlgActualizarDatosClientes_cmdCancelar_actionAdapter implements
		ActionListener {
	private DlgActualizarDatosClientes adaptee;

	DlgActualizarDatosClientes_cmdCancelar_actionAdapter(
			DlgActualizarDatosClientes adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.cmdCancelar_actionPerformed(e);
	}
}

class DlgActualizarDatosClientes_cmdExaminar_actionAdapter implements
		ActionListener {
	private DlgActualizarDatosClientes adaptee;

	DlgActualizarDatosClientes_cmdExaminar_actionAdapter(
			DlgActualizarDatosClientes adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdExaminar_actionPerformed(e);
	}
}

class DlgActualizarDatosClientes_jButton1_actionAdapter implements
		ActionListener {
	private DlgActualizarDatosClientes adaptee;

	DlgActualizarDatosClientes_jButton1_actionAdapter(
			DlgActualizarDatosClientes adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class DlgActualizarDatosClientes_chkNoCorreo_actionAdapter implements
		ActionListener {
	private DlgActualizarDatosClientes adaptee;

	DlgActualizarDatosClientes_chkNoCorreo_actionAdapter(
			DlgActualizarDatosClientes adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.chkNoCorreo_actionPerformed(e);
	}
}

class DlgActualizarDatosClientes_this_windowAdapter extends WindowAdapter {
	private DlgActualizarDatosClientes adaptee;

	DlgActualizarDatosClientes_this_windowAdapter(
			DlgActualizarDatosClientes adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class DlgActualizarDatosClientes_cmdActualizarDatos_actionAdapter implements
		ActionListener {
	private DlgActualizarDatosClientes adaptee;

	DlgActualizarDatosClientes_cmdActualizarDatos_actionAdapter(
			DlgActualizarDatosClientes adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdActualizarDatos_actionPerformed(e);
	}
}
