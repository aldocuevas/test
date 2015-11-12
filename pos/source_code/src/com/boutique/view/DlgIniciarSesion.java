package com.boutique.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.boutique.domain.Usuario;
import com.boutique.engine.impl.AppInstance;

public class DlgIniciarSesion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Usuario usuario = null;
	JPanel panel1 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JPasswordField txtContrasena = new JPasswordField();
	JLabel jLabel3 = new JLabel();
	JTextField txtUsuario = new JTextField();
	JButton cmdIniciarSesion = new JButton();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	public boolean validado = false;

	public DlgIniciarSesion(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgIniciarSesion() {
		this(new Frame(), "DlgIniciarSesion", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(gridBagLayout1);
		panel1.setBackground(Color.white);
		this.getContentPane().setLayout(borderLayout2);
		jLabel1.setMaximumSize(new Dimension(76, 35));
		jLabel1.setMinimumSize(new Dimension(76, 25));
		jLabel1.setPreferredSize(new Dimension(76, 25));
		txtUsuario.setToolTipText("");
		cmdIniciarSesion
				.addActionListener(new DlgIniciarSesion_cmdIniciarSesion_actionAdapter(
						this));
		txtContrasena
				.addActionListener(new DlgIniciarSesion_txtContrasena_actionAdapter(
						this));
		this.getContentPane().add(panel1, java.awt.BorderLayout.CENTER);
		txtContrasena.setText("");
		jLabel3.setText("Contraseña:");
		txtUsuario.setText("");
		cmdIniciarSesion.setText("Iniciar sesión");
		jLabel2.setText("Usuario:");
		this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
		panel1.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						28, 0, 0), 16, 5));
		panel1.add(jLabel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						29, 0, 0), 61, 5));
		panel1.add(txtUsuario, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						28, 0, 0), 155, 2));
		panel1.add(txtContrasena, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						27, 0, 0), 157, 2));
		panel1.add(cmdIniciarSesion, new GridBagConstraints(0, 4, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(11, 5, 4, 13), 23, 2));
		jLabel1.setBackground(Color.white);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
		jLabel1.setOpaque(true);
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Iniciar sesión");
	}

	@SuppressWarnings("deprecation")
	private void iniciarSesion() {

		usuario = AppInstance.iniciarSesion(txtUsuario.getText(),
				txtContrasena.getText());
		if (usuario != null) {
			validado = true;
			this.setVisible(false);
		}

		else {
			JOptionPane.showMessageDialog(this,
					"Usuario o contraseña incorrecta",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.WARNING_MESSAGE);
		}

	}

	public void cmdIniciarSesion_actionPerformed(ActionEvent e) {
		iniciarSesion();
	}

	public void txtContrasena_actionPerformed(ActionEvent e) {
		iniciarSesion();
	}
}

class DlgIniciarSesion_txtContrasena_actionAdapter implements ActionListener {
	private final DlgIniciarSesion adaptee;

	DlgIniciarSesion_txtContrasena_actionAdapter(DlgIniciarSesion adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.txtContrasena_actionPerformed(e);
	}
}

class DlgIniciarSesion_cmdIniciarSesion_actionAdapter implements ActionListener {
	private final DlgIniciarSesion adaptee;

	DlgIniciarSesion_cmdIniciarSesion_actionAdapter(DlgIniciarSesion adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdIniciarSesion_actionPerformed(e);
	}
}
