package com.boutique.view;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class PnlTipoVenta extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JRadioButton rCredito = new JRadioButton();
  JRadioButton rApartado = new JRadioButton();
  JLabel jLabel1 = new JLabel();
  JPanel pnlTipoVenta = new JPanel();
  JRadioButton rContado = new JRadioButton();
  JButton cmdOk = new JButton();

  public PnlTipoVenta() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    pnlTipoVenta.setBackground(Color.white);
    jLabel1.setText("Tipo de venta:");
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setText("");
    rApartado.setText("Apartado");
    rApartado.setBackground(Color.white);
    rCredito.setText("Credito");
    rCredito.setBackground(Color.white);
    this.setLayout(borderLayout1);
    rContado.setBackground(Color.white);
    rContado.setText("Contado");
    cmdOk.setText("Aceptar");
    pnlTipoVenta.add(jLabel1, null);
    pnlTipoVenta.add(rCredito, null);
    pnlTipoVenta.add(rContado, null);
    pnlTipoVenta.add(rApartado, null);
    this.add(cmdOk,  BorderLayout.SOUTH);
    this.add(pnlTipoVenta,  BorderLayout.CENTER);
  }
}
