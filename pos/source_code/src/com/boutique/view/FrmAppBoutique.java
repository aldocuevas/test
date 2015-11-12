package com.boutique.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005<Z/p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class FrmAppBoutique
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JSplitPane jSplitPane1 = new JSplitPane();
  JPanel jPanel1 = new JPanel();
  JToggleButton tglClientes = new JToggleButton();
  JToggleButton tglCredito = new JToggleButton();
  JToggleButton tglVentas = new JToggleButton();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JTabbedPane tabMain = new JTabbedPane();
 // PnlCatalogos pnlCatalogos = new PnlCatalogos();

  PnlVentas pnlVentas = new PnlVentas();
  com.boutique.view.PnlDirectorioClientes pnlDirectorioClientes = new
      com.boutique.view.PnlDirectorioClientes();
  //Construct the frame
 // PnlDirectorioCheques pnlDirectorioCheques = new PnlDirectorioCheques();
  PnlCatalogoProveedores pnlDirectorioProveedores = new
      PnlCatalogoProveedores();
//  PnlDirectorioCuentas pnlDirectorioCuentas1 = new PnlDirectorioCuentas();
  PnlCatalogoTipoDeProductos pnlDirectorioArticulos1 = new PnlCatalogoTipoDeProductos();

  JPopupMenu popupMenu = new JPopupMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem4 = new JMenuItem();
  GridLayout gridLayout1 = new GridLayout();

  public FrmAppBoutique() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    image1 = new ImageIcon(com.boutique.view.FrmAppBoutique.class.getResource("openFile.png"));
    image2 = new ImageIcon(com.boutique.view.FrmAppBoutique.class.getResource("closeFile.png"));
    image3 = new ImageIcon(com.boutique.view.FrmAppBoutique.class.getResource("help.png"));
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(617, 649));
    this.setTitle(com.boutique.engine.impl.AppInstance.nombreNegocio);
    this.addWindowListener(new FrmAppBoutique_this_windowAdapter(this));
    statusBar.setText(" ");
    jMenuFile.setText("Inicio");
    jMenuFileExit.setText("Salir");
    jMenuFileExit.addActionListener(new
                                    FrmAppBoutique_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Ayuda");
    jMenuHelpAbout.setText("Acerca de..");
    jMenuHelpAbout.addActionListener(new
                                     FrmAppBoutique_jMenuHelpAbout_ActionAdapter(this));
    jButton1.setIcon(image1);
    jButton1.setToolTipText("Open File");
    jButton2.setIcon(image2);
    jButton2.setToolTipText("Close File");
    jButton3.setIcon(image3);
    jButton3.setToolTipText("Help");
    jSplitPane1.setDividerSize(5);
    tglClientes.setToolTipText("");
    tglClientes.setActionCommand("Clientes");
    tglClientes.setText("Catalogos");
    tglClientes.addMouseListener(new FrmAppBoutique_tglClientes_mouseAdapter(this));
    tglCredito.setText("Creditos");
    tglCredito.addMouseListener(new FrmAppBoutique_tglCredito_mouseAdapter(this));
    tglVentas.setText("Ventas");
    tglVentas.addMouseListener(new FrmAppBoutique_tglVentas_mouseAdapter(this));
    jPanel1.setBackground(Color.white);
    jPanel1.setLayout(gridLayout1);
    tabMain.setTabPlacement(JTabbedPane.TOP);
    tabMain.setTabPlacement(JTabbedPane.TOP);
    tabMain.setFont(new java.awt.Font("Dialog", 0, 10));
    jMenuItem1.setText("Duplicar Registro");
    jMenuItem2.setText("Agregar registro");
    jMenuItem3.setText("Eliminar registro");
    jMenuItem4.setText("Impimir etiqueta");
    gridLayout1.setRows(3);
    jToolBar.add(jButton1);
    jToolBar.add(jButton2);
    jToolBar.add(jButton3);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jSplitPane1, BorderLayout.CENTER);
    // jSplitPane1.add(pnlDirectorioProveedores, JSplitPane.BOTTOM);
    jPanel1.add(tglVentas, null);
    jPanel1.add(tglCredito, null);
    jPanel1.add(tglClientes, null);

    buttonGroup1.add(tglVentas);
    buttonGroup1.add(tglCredito);
    buttonGroup1.add(tglClientes);
    popupMenu.add(jMenuItem1);
    popupMenu.add(jMenuItem2);
    popupMenu.add(jMenuItem3);
    popupMenu.add(jMenuItem4);
    jSplitPane1.add(jPanel1, JSplitPane.TOP);
    tabMain.setSelectedIndex( -1);
  }

  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

 

  void tglVentas_mouseClicked(MouseEvent e) {
//    this.jSplitPane1.setRightComponent(pnlVentas);

  }

  void tglCredito_mouseClicked(MouseEvent e) {
    if (tglCredito.isSelected()) {
    }
  }

  void tglClientes_mouseClicked(MouseEvent e) {
    if (tglClientes.isSelected()) {
    //  this.jSplitPane1.setRightComponent(pnlCatalogos);
    }
  }

  public void this_windowOpened(WindowEvent e) {
    this.setExtendedState(Frame.MAXIMIZED_BOTH);
  }

}

class FrmAppBoutique_this_windowAdapter
    extends WindowAdapter {
  private FrmAppBoutique adaptee;
  FrmAppBoutique_this_windowAdapter(FrmAppBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class FrmAppBoutique_jMenuFileExit_ActionAdapter
    implements ActionListener {
  FrmAppBoutique adaptee;

  FrmAppBoutique_jMenuFileExit_ActionAdapter(FrmAppBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class FrmAppBoutique_jMenuHelpAbout_ActionAdapter
    implements ActionListener {
  FrmAppBoutique adaptee;

  FrmAppBoutique_jMenuHelpAbout_ActionAdapter(FrmAppBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class FrmAppBoutique_tglVentas_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FrmAppBoutique adaptee;

  FrmAppBoutique_tglVentas_mouseAdapter(FrmAppBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tglVentas_mouseClicked(e);
  }
}

class FrmAppBoutique_tglCredito_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FrmAppBoutique adaptee;

  FrmAppBoutique_tglCredito_mouseAdapter(FrmAppBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tglCredito_mouseClicked(e);
  }
}

class FrmAppBoutique_tglClientes_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FrmAppBoutique adaptee;

  FrmAppBoutique_tglClientes_mouseAdapter(FrmAppBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tglClientes_mouseClicked(e);
  }
}
