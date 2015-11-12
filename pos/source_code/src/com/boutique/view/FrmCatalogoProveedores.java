package com.boutique.view;

import java.awt.*;
import javax.swing.*;

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
public class FrmCatalogoProveedores
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  PnlCatalogoProveedores pnlCatalogoProveedores1 = new PnlCatalogoProveedores();

  public FrmCatalogoProveedores() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    this.setTitle("Catálogo de proveedores");
    this.getContentPane().add(pnlCatalogoProveedores1,
                              java.awt.BorderLayout.CENTER);

  }
}
