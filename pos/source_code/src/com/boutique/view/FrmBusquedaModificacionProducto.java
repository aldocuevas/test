package com.boutique.view;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

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
public class FrmBusquedaModificacionProducto
    extends JFrame {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// BorderLayout borderLayout1 = new BorderLayout();
  PnlModificacionProductos pnlProductos1 = new PnlModificacionProductos();
  public FrmBusquedaModificacionProducto() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setTitle("Busqueda de productos");
    this.addWindowListener(new
                           FrmBusquedaModificacionProducto_this_windowAdapter(this));
    this.getContentPane().add(pnlProductos1, java.awt.BorderLayout.CENTER);
    this.pack();
  }

  public void this_windowOpened(WindowEvent e) {
this.pnlProductos1.txtPrecio.requestFocus();
  }
}

class FrmBusquedaModificacionProducto_this_windowAdapter
    extends WindowAdapter {
  private FrmBusquedaModificacionProducto adaptee;
  FrmBusquedaModificacionProducto_this_windowAdapter(
      FrmBusquedaModificacionProducto adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
