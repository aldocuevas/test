package com.boutique.view;

import java.io.*;
import javax.imageio.*;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.*;
 

public class PnlFotografia
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JLabel lblFoto = new JLabel();
  ImageIcon icono = new ImageIcon();
  Image imagen = null;
  InputStream is = null;
  File file = null;
  public PnlFotografia() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    lblFoto.setBorder(BorderFactory.createEtchedBorder());
    lblFoto.setText("");
    this.add(lblFoto, java.awt.BorderLayout.CENTER);
  }

  void setImagen(String strImagen) {
    try {
      file = new File(strImagen);
      is = new FileInputStream(file);
      icono = new ImageIcon(strImagen);
      imagen = icono.getImage().getScaledInstance(AppInstance.boutique().getWFoto(), AppInstance.boutique().getHFoto(), Image.SCALE_SMOOTH);
      icono = new ImageIcon(imagen,"imagen escalada");
      lblFoto.setIcon(icono);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      is = null;
      icono = null;
      imagen = null;
      lblFoto.setIcon(null);
    }
  }

  void setImagen(InputStream iss) {
    try {
      is = iss;
      this.imagen = ImageIO.read(is);
      icono = new ImageIcon(this.imagen);
      lblFoto.setIcon(icono);
    }
    catch (Exception ex) {
      ex.toString();
      lblFoto.setIcon(AppInstance.nodisponible);
      icono=null;
      imagen=null;
      is=null;
    }

  }

  InputStream getImagen() {
    return is;
  }

  /**
   * limpiarImagen
   */
  public void limpiarImagen() {
    lblFoto.setIcon(null);
icono=null;
imagen=null;
is=null;

  }
}
