package com.boutique.view;

import java.beans.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class FrmVentaContado
    extends JFrame implements PropertyChangeListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  PnlVentaContado pnlVentaContadoEvento1 = new PnlVentaContado();

  public FrmVentaContado() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setTitle("VENTA DE CONTADO");
    this.addWindowListener(new FrmVentaContado_this_windowAdapter(this));
    this.getContentPane().add(pnlVentaContadoEvento1, BorderLayout.CENTER);
    this.pack();
    this.pnlVentaContadoEvento1.addPropertyChangeListener(this);
    // Center the window


//    this.setState(super.MAXIMIZED_BOTH);

  }

  public void this_windowClosing(WindowEvent e) {
 /*   if (this.pnlVentaContadoEvento1.modelVentaArticulos1.data.size() > 0) {
      int i = JOptionPane.showConfirmDialog(this, "¿Desea cancelar la venta?",
                                            "Cancelación de venta",
                                            JOptionPane.YES_NO_OPTION);
      if (i == JOptionPane.YES_OPTION) {
      //  this.pnlVentaContadoEvento1.cancelarVenta();
        this.setVisible(false);
      }
    }
    else {
      this.setVisible(false);
    }*/
  }

  public void this_windowOpened(WindowEvent e) {

  }

  /**
   * This method gets called when a bound property is changed.
   *
   * @param evt A PropertyChangeEvent object describing the event source and
   *   the property that has changed.
   * @todo Implement this java.beans.PropertyChangeListener method
   */
  public void propertyChange(PropertyChangeEvent evt) {
    if(evt.getPropertyName().equals("cerrar")){
      this.setVisible(false);
    }
  }

}

class FrmVentaContado_this_windowAdapter
    extends WindowAdapter {
  private FrmVentaContado adaptee;
  FrmVentaContado_this_windowAdapter(FrmVentaContado adaptee) {
    this.adaptee = adaptee;
  }

  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
