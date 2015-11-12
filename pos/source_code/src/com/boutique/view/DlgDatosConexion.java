package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.boutique.dao.DaoSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.boutique.engine.impl.AppInstance;

/**
 * <p>Title: boutique management</p>
 *
 * <p>Description: Sistema de administracion de boitiques</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: SESTO</p>
 *
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class DlgDatosConexion
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel panel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JTextField txtIP = new JTextField();
  JTextField txtPuerto = new JTextField();
  JTextField txtUsuario = new JTextField();
  JPasswordField txtPassword = new JPasswordField();
  JTextField txtBaseDeDatos = new JTextField();
  JButton cmdProbar = new JButton();
  JButton jButton2 = new JButton();
  JButton cmdCancelar = new JButton();
  JLabel jLabel6 = new JLabel();

  public DlgDatosConexion(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgDatosConexion() {
    this(new Frame(), "DlgoDatosConexion", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(null);
    this.getContentPane().setLayout(null);
    jLabel4.setToolTipText("");
    txtIP.setText("");
    txtIP.setBounds(new Rectangle(148, 30, 142, 20));
    txtPuerto.setText("");
    txtPuerto.setBounds(new Rectangle(148, 55, 142, 20));
    txtUsuario.setText("");
    txtUsuario.setBounds(new Rectangle(148, 81, 142, 20));
    txtPassword.setText("");
    txtPassword.setBounds(new Rectangle(148, 103, 142, 20));
    txtBaseDeDatos.setText("");
    txtBaseDeDatos.setBounds(new Rectangle(148, 129, 142, 20));
    cmdProbar.setBounds(new Rectangle(31, 166, 95, 24));
    cmdProbar.setText("PROBAR");
    cmdProbar.addActionListener(new DlgoDatosConexion_cmdProbar_actionAdapter(this));
    jButton2.setBounds(new Rectangle(130, 166, 95, 24));
    jButton2.setText("GUARDAR");
    jButton2.addActionListener(new DlgoDatosConexion_jButton2_actionAdapter(this));
    cmdCancelar.setBounds(new Rectangle(228, 166, 95, 24));
    cmdCancelar.setText("CANCELAR");
    cmdCancelar.addActionListener(new
                                  DlgoDatosConexion_cmdCancelar_actionAdapter(this));
    jLabel2.setText("Puerto:");
    jLabel2.setBounds(new Rectangle(54, 55, 81, 21));
    jLabel3.setText("Direccion IP:");
    jLabel3.setBounds(new Rectangle(54, 30, 81, 21));
    jLabel4.setText("Contraseña:");
    jLabel4.setBounds(new Rectangle(54, 103, 81, 21));
    jLabel5.setText("Base de datos:");
    jLabel5.setBounds(new Rectangle(54, 129, 81, 21));
    jLabel6.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel6.setText("Configuracion de conectividad");
    jLabel6.setBounds(new Rectangle(63, 0, 249, 25));
    this.addWindowListener(new DlgoDatosConexion_this_windowAdapter(this));
    panel1.add(cmdProbar);
    panel1.add(jButton2);
    panel1.add(cmdCancelar);
    panel1.add(txtBaseDeDatos);
    panel1.add(jLabel3);
    panel1.add(txtIP);
    panel1.add(txtPuerto);
    panel1.add(jLabel2);
    panel1.add(jLabel1, null);
    panel1.add(txtUsuario);
    panel1.add(txtPassword);
    panel1.add(jLabel4);
    panel1.add(jLabel5);
    panel1.add(jLabel6);
    this.getContentPane().add(panel1, null);

    jLabel1.setBounds(new Rectangle(54, 81, 81, 21));
    jLabel1.setText("Usuario:");
    panel1.setBounds(new Rectangle( -13, 7, 400, 300));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public void this_windowOpened(WindowEvent e) {
    this.txtIP.setText(DaoSource.ip);
    this.txtPuerto.setText(DaoSource.port);
    this.txtUsuario.setText(DaoSource.username);
    this.txtPassword.setText(DaoSource.password);
    this.txtBaseDeDatos.setText(DaoSource.database);
  }

  @SuppressWarnings("deprecation")
public void cmdProbar_actionPerformed(ActionEvent e) {

    try {
      this.setCursor(AppInstance.waitCursor);
      if (DaoSource.testConnection(txtIP.getText(), txtPuerto.getText(),
                                   txtBaseDeDatos.getText(), txtUsuario.getText(),
                                   txtPassword.getText())) {
        this.setCursor(AppInstance.defCursor);
        JOptionPane.showMessageDialog(null, "CONEXION EXITOSA",
                                      com.boutique.engine.impl.AppInstance.
                                      nombreNegocio, JOptionPane.PLAIN_MESSAGE);
      }

      else {
                this.setCursor(AppInstance.defCursor);
        JOptionPane.showMessageDialog(null, "NO SE PUDO REALIZAR LA CONEXION",
                                      com.boutique.engine.impl.AppInstance.
                                      nombreNegocio, JOptionPane.ERROR_MESSAGE);
      }
    }

    catch (SQLException ex) {
              this.setCursor(AppInstance.defCursor);
      javax.swing.JOptionPane.showMessageDialog(null,
                                                "ERROR EN LA CONEXION AL SERVIDOR: " +
                                                ( (ex.getMessage().toString().
          length() >
          200) ?
                                                 ex.getMessage().substring(0,
          200) :
                                                 ex.getMessage()),
                                                com.boutique.engine.impl.AppInstance.
                                                nombreNegocio,
                                                javax.swing.JOptionPane.
                                                ERROR_MESSAGE);

    }

  }

  @SuppressWarnings("deprecation")
public void jButton2_actionPerformed(ActionEvent e) {
            this.setCursor(AppInstance.waitCursor);
    DaoSource.ip = txtIP.getText();
    DaoSource.port = txtPuerto.getText();
    DaoSource.username = txtUsuario.getText();
    DaoSource.password = txtPassword.getText();
    DaoSource.database = txtBaseDeDatos.getText();
    if (DaoSource.guardarDatosConexion()) {
              this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(null, "CONFIGURACION GUARDADA EXITOSAMENTE",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio,
                                    JOptionPane.INFORMATION_MESSAGE);
      this.setVisible(false);
    }
    else {
              this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(null, "NO SE PUDO GUARDAR LA CONFIGURACION",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio, JOptionPane.ERROR_MESSAGE);
    }
  }

  public void cmdCancelar_actionPerformed(ActionEvent e) {
this.setVisible(false);
  }
}

class DlgoDatosConexion_cmdCancelar_actionAdapter
    implements ActionListener {
  private DlgDatosConexion adaptee;
  DlgoDatosConexion_cmdCancelar_actionAdapter(DlgDatosConexion adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class DlgoDatosConexion_jButton2_actionAdapter
    implements ActionListener {
  private DlgDatosConexion adaptee;
  DlgoDatosConexion_jButton2_actionAdapter(DlgDatosConexion adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.jButton2_actionPerformed(e);
  }
}

class DlgoDatosConexion_this_windowAdapter
    extends WindowAdapter {
  private DlgDatosConexion adaptee;
  DlgoDatosConexion_this_windowAdapter(DlgDatosConexion adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class DlgoDatosConexion_cmdProbar_actionAdapter
    implements ActionListener {
  private DlgDatosConexion adaptee;
  DlgoDatosConexion_cmdProbar_actionAdapter(DlgDatosConexion adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdProbar_actionPerformed(e);
  }
}
