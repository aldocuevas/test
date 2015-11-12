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

public class PnlVentas extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  PnlVentaCredito pnlPuntoVentaCredito1 = new PnlVentaCredito();
  //PnlPuntoVentaContado pnlPuntoVentaContado1 = new PnlPuntoVentaContado();
 // PnlDevolucionesEvento pnlDevoluciones1 = new PnlDevolucionesEvento();
  //PnlPuntoVentaApartado pnlPuntoVentaApartado1 = new PnlPuntoVentaApartado();
  PnlVentaContado pnlVentaContadoEvento1 = new PnlVentaContado();

  public PnlVentas() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    this.add(jTabbedPane1, BorderLayout.CENTER);
    jTabbedPane1.add(pnlPuntoVentaCredito1,  "pnlPuntoVentaCredito1");
   // jTabbedPane1.add(pnlPuntoVentaContado1,  "pnlPuntoVentaContado1");
  //  jTabbedPane1.add(pnlDevoluciones1,  "pnlDevoluciones1");
        //jTabbedPane1.add(pnlPuntoVentaApartado1,  "Apartados");
    jTabbedPane1.add(pnlVentaContadoEvento1, "pnlVentaContadoEvento1");
  }
}
