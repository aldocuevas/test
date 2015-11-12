package com.boutique.view;

import java.beans.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class FrmVentaApartado
    extends JFrame implements PropertyChangeListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  PnlVentaApartado pnlVentaApartado = new PnlVentaApartado();

  public FrmVentaApartado() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    this.addWindowListener(new FrmVentaApartadoEvento_this_windowAdapter(this));
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setTitle("VENTA DE APARTADO");
    this.getContentPane().add(pnlVentaApartado,
                              java.awt.BorderLayout.CENTER);

    this.pack();
    this.pnlVentaApartado.addPropertyChangeListener(this);

  }

  void this_windowOpened(WindowEvent e) {
    this.setExtendedState(Frame.MAXIMIZED_BOTH);
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

class FrmVentaApartadoEvento_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  FrmVentaApartado adaptee;

  FrmVentaApartadoEvento_this_windowAdapter(FrmVentaApartado adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }



}
