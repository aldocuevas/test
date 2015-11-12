package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;


public class FrmDirectorioClientes
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  PnlDirectorioClientes pnlDirectorioClientes1 = new PnlDirectorioClientes();

  public FrmDirectorioClientes() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    this.getContentPane().add(pnlDirectorioClientes1,
                              java.awt.BorderLayout.CENTER);
  }

  public void this_windowOpened(WindowEvent e) {
    this.setExtendedState(MAXIMIZED_BOTH);
  }
}
