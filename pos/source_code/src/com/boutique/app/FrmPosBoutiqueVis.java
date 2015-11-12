package com.boutique.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.AppInstance;
import com.boutique.view.*;

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
public class FrmPosBoutiqueVis
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = -5621642871137517439L;
BorderLayout borderLayout1 = new BorderLayout();
  JLabel lblTitulo = new JLabel();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel lblVendedor = new JLabel();
  JLabel lblSucursal = new JLabel();
  JLabel jLabel4 = new JLabel();
  GridLayout gridLayout1 = new GridLayout(3, 3, 3, 3);
  JButton cmdVentasContado = new JButton();
  JButton cmdPagarApartados = new JButton();
  JButton cmdVentasApartado = new JButton();
  JButton cmdDevoluciones = new JButton();
  JButton cmdDiarioDeEntradas = new JButton();
  public FrmPosBoutiqueVis() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @SuppressWarnings("deprecation")
private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    lblTitulo.setBackground(Color.white);
    lblTitulo.setOpaque(true);
    lblTitulo.setRequestFocusEnabled(false);
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    // lblTitulo.setText(com.boutique.engine.AppInstance.nombreNegocio);
    lblTitulo.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class.
                                    getResource("img/logo.jpg")));
    lblTitulo.addKeyListener(new FrmPosBoutiqueVis_lblTitulo_keyAdapter(this));
    jPanel1.setBackground(Color.white);
    jPanel1.setLayout(gridLayout1);
    lblVendedor.setToolTipText("");
    cmdVentasContado.setText("Venta de contado");
    cmdVentasContado.addKeyListener(new
                                    FrmPosBoutiqueVis_cmdVentasContado_keyAdapter(this));
    cmdVentasContado.addActionListener(new
                                       FrmPosBoutiqueVis_cmdVentasContado_actionAdapter(this));
    cmdVentasApartado.setText("Venta de apartado");
    cmdVentasApartado.addKeyListener(new
                                     FrmPosBoutiqueVis_cmdVentasApartado_keyAdapter(this));
    cmdVentasApartado.addActionListener(new
                                        FrmPosBoutiqueVis_cmdVentasApartado_actionAdapter(this));
    cmdPagarApartados.setText("Pagar apartado");
    cmdPagarApartados.addKeyListener(new
                                     FrmPosBoutiqueVis_cmdPagarApartados_keyAdapter(this));
    cmdPagarApartados.addActionListener(new
                                        FrmPosBoutiqueVis_cmdPagarApartados_actionAdapter(this));
    cmdDevoluciones.setText("Devoluciones");
    cmdDevoluciones.addKeyListener(new
                                   FrmPosBoutiqueVis_cmdDevoluciones_keyAdapter(this));
    cmdDevoluciones.addActionListener(new
                                      FrmPosBoutiqueVis_cmdDevoluciones_actionAdapter(this));
    cmdDiarioDeEntradas.setEnabled(true);
    cmdDiarioDeEntradas.setNextFocusableComponent(lblTitulo);
    cmdDiarioDeEntradas.setText("Diario de entradas");
    cmdDiarioDeEntradas.addKeyListener(new
                                       FrmPosBoutiqueVis_cmdDiarioDeEntradas_keyAdapter(this));
    cmdDiarioDeEntradas.addActionListener(new
                                          FrmPosBoutiqueVis_cmdDiarioDeEntradas_actionAdapter(this));
    gridLayout1.setColumns(3);
    gridLayout1.setHgap(2);
    gridLayout1.setRows(4);
    gridLayout1.setVgap(2);
    this.addWindowListener(new FrmPosBoutiqueVis_this_windowAdapter(this));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("Punto de venta");
    this.addKeyListener(new FrmPosBoutiqueVis_this_keyAdapter(this));
    this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    jPanel1.add(cmdVentasContado);
    jPanel1.add(cmdVentasApartado);
    jPanel1.add(cmdPagarApartados);
    jPanel1.add(cmdDevoluciones);
    jPanel1.add(cmdDiarioDeEntradas);
    jLabel1.setText("Vendedor:");
    this.getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);
    this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
    lblVendedor.setText("");
    lblSucursal.setText("");
    jLabel4.setText("Boutique:");
    jPanel2.add(jLabel1);
    jPanel2.add(lblVendedor);
    jPanel2.add(jLabel4);
    jPanel2.add(lblSucursal);
  }

  public void cmdVentasContado_actionPerformed(ActionEvent e) {
    FrmVentaContado frm = new FrmVentaContado();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void this_windowOpened(WindowEvent e) {
    DlgIniciarSesion dlg = new DlgIniciarSesion(this, com.boutique.engine.impl.AppInstance.nombreNegocio, true);
    dlg.setLocationRelativeTo(this);
    dlg.setSize(225, 195);
    dlg.setVisible(true);
    if (!dlg.validado) {
      JOptionPane.showMessageDialog(this,
                                    "Debe introducir un usuario y contraseña válidos, la aplicación se cerrará",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
      this.setVisible(false);
      System.exit(0);
    }
    this.lblVendedor.setText(AppInstance.usuario().getNombre());
    this.lblSucursal.setText(AppInstance.boutique().getNombre());
  }

  public void cmdVentasCredito_actionPerformed(ActionEvent e) {
    FrmVentaCredito frm = new FrmVentaCredito();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdVentasApartado_actionPerformed(ActionEvent e) {
    FrmVentaApartado frm = new FrmVentaApartado();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdAbonos_actionPerformed(ActionEvent e) {
    FrmEstadoCuenta frm = new FrmEstadoCuenta();
    frm.pack();
    frm.setSize(730, 700);
    frm.setLocationRelativeTo(this);
    frm.setVisible(true);
  }

  public void cmdPagarApartados_actionPerformed(ActionEvent e) {
    FrmControlApartados frm = new FrmControlApartados();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdDevoluciones_actionPerformed(ActionEvent e) {
    FrmDevoluciones frm = new FrmDevoluciones();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdCorteDeCaja_actionPerformed(ActionEvent e) {
    FrmCorteDeCaja2 frm = new FrmCorteDeCaja2();
    //frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setSize(407, 650);
    frm.setLocationRelativeTo(this);
    frm.setVisible(true);
  }

  public void cmdDiarioDeEntradas_actionPerformed(ActionEvent e) {

    FrmDiarioDeEntradas frm2 = new FrmDiarioDeEntradas();
    frm2.pack();
    frm2.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm2.setVisible(true);

  }

  public void cmdRegistrarEncargo_actionPerformed(ActionEvent e) {
    FrmRegistroEncargos frm = new FrmRegistroEncargos();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdBuscarProducto_actionPerformed(ActionEvent e) {
    FrmBusquedaProducto frm = new FrmBusquedaProducto();
    frm.pack();
    frm.setSize(650, 300);
    frm.setLocationRelativeTo(this);
    //frm.setExtendedState(frm.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdDevoluciones_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void lblTitulo_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void this_keyReleased(KeyEvent e) {
    openHidden(e);
  }

  public void this_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdVentasContado_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdVentasApartado_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdPagarApartados_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdDiarioDeEntradas_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void openHidden(KeyEvent e) {
    System.out.println(e.getKeyChar());
    System.out.println(e.getKeyCode());
    if (e.getKeyCode() == 122) {
      //ABRIMOS EL OCULO
      FrmPosBoutiqueOculto frame = new FrmPosBoutiqueOculto();
      frame.pack();
      frame.setSize(new Dimension(400, 420));

      // Center the window
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = frame.getSize();
      frame.setLocation( (screenSize.width - frameSize.width) / 2,
                        (screenSize.height - frameSize.height) / 2);
      frame.setVisible(true);

    }
  }
}

class FrmPosBoutiqueVis_lblTitulo_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_lblTitulo_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.lblTitulo_keyPressed(e);
  }
}

class FrmPosBoutiqueVis_cmdDevoluciones_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdDevoluciones_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdDevoluciones_keyPressed(e);
  }
}

class FrmPosBoutiqueVis_cmdDevoluciones_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdDevoluciones_actionAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdDevoluciones_actionPerformed(e);
  }
}

class FrmPosBoutiqueVis_cmdDiarioDeEntradas_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdDiarioDeEntradas_actionAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdDiarioDeEntradas_actionPerformed(e);
  }
}

class FrmPosBoutiqueVis_cmdDiarioDeEntradas_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdDiarioDeEntradas_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdDiarioDeEntradas_keyPressed(e);
  }
}

class FrmPosBoutiqueVis_cmdPagarApartados_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdPagarApartados_actionAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdPagarApartados_actionPerformed(e);
  }
}

class FrmPosBoutiqueVis_cmdPagarApartados_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdPagarApartados_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdPagarApartados_keyPressed(e);
  }
}

class FrmPosBoutiqueVis_this_windowAdapter
    extends WindowAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_this_windowAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class FrmPosBoutiqueVis_this_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_this_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.this_keyReleased(e);
  }

  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}

class FrmPosBoutiqueVis_cmdVentasApartado_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdVentasApartado_actionAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdVentasApartado_actionPerformed(e);
  }
}

class FrmPosBoutiqueVis_cmdVentasApartado_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdVentasApartado_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdVentasApartado_keyPressed(e);
  }
}

class FrmPosBoutiqueVis_cmdVentasContado_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdVentasContado_actionAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdVentasContado_actionPerformed(e);
  }
}

class FrmPosBoutiqueVis_cmdVentasContado_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueVis adaptee;
  FrmPosBoutiqueVis_cmdVentasContado_keyAdapter(FrmPosBoutiqueVis adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdVentasContado_keyPressed(e);
  }
}
