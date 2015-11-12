package com.boutique.view;

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
public class FrmBusquedaProducto
    extends JFrame {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// BorderLayout borderLayout1 = new BorderLayout();
  PnlProductos pnlProductos1 = new PnlProductos();
  public FrmBusquedaProducto() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setTitle("Busqueda de productos");
    this.getContentPane().add(pnlProductos1, java.awt.BorderLayout.CENTER);
    this.pack();
  }
}
