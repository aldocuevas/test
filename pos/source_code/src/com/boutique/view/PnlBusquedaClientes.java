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

public class PnlBusquedaClientes extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JSplitPane jSplitPane1 = new JSplitPane();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtNombre = new JTextField();
  JButton cmdBuscar = new JButton();
  GridLayout gridLayout1 = new GridLayout();
  JList jList1 = new JList();

  public PnlBusquedaClientes() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jLabel1.setToolTipText("");
    jLabel1.setText("Cliente:");
    cmdBuscar.setText("Buscar");
    txtNombre.setText("");
    jPanel1.setLayout(gridLayout1);
    jPanel1.add(jLabel1, null);
    jPanel1.add(txtNombre, null);
    jPanel1.add(cmdBuscar, null);
    jSplitPane1.add(jList1, JSplitPane.BOTTOM);
    this.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jPanel1, JSplitPane.TOP);
    jSplitPane1.setDividerLocation(30);
  }
}
