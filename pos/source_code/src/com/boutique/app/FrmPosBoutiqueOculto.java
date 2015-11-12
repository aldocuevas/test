package com.boutique.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
public class FrmPosBoutiqueOculto
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6555317592903853934L;
BorderLayout borderLayout1 = new BorderLayout();
  JLabel lblTitulo = new JLabel();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JLabel lblVendedor = new JLabel();
  JLabel lblSucursal = new JLabel();
  GridLayout gridLayout1 = new GridLayout(3, 3, 3, 3);
  JButton cmdVentasCredito = new JButton();
  JButton cmdAbonos = new JButton();
  JButton cmdDevoluciones = new JButton();
  JButton cmdCorteDeCaja = new JButton();
  JButton cmdDiarioDeEntradas = new JButton();
  JButton cmdRegistrarEncargo = new JButton();
  JButton cmdBuscarProducto = new JButton();
  public FrmPosBoutiqueOculto() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(borderLayout1);
    lblTitulo.setBackground(Color.white);
    lblTitulo.setOpaque(true);
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    // lblTitulo.setText(com.boutique.engine.AppInstance.nombreNegocio);
    lblTitulo.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class.
                                    getResource("img/logo.jpg")));
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setLayout(gridLayout1);
    lblVendedor.setToolTipText("");
    cmdVentasCredito.setBackground(Color.pink);
    cmdVentasCredito.setText("Venta a credito");
    cmdVentasCredito.addKeyListener(new
                                    FrmPosBoutiqueOculto_cmdVentasCredito_keyAdapter(this));
    cmdVentasCredito.addActionListener(new
                                       FrmPosBoutiqueOculto_cmdVentasCredito_actionAdapter(this));
    cmdAbonos.setBackground(Color.pink);
    cmdAbonos.setText("Abonos");
    cmdAbonos.addKeyListener(new FrmPosBoutiqueOculto_cmdAbonos_keyAdapter(this));
    cmdAbonos.addActionListener(new
                                FrmPosBoutiqueOculto_cmdAbonos_actionAdapter(this));
    cmdDevoluciones.setBackground(Color.pink);
    cmdDevoluciones.setText("Devoluciones");
    cmdDevoluciones.addKeyListener(new
                                   FrmPosBoutiqueOculto_cmdDevoluciones_keyAdapter(this));
    cmdDevoluciones.addActionListener(new
                                      FrmPosBoutiqueOculto_cmdDevoluciones_actionAdapter(this));
    cmdCorteDeCaja.setBackground(Color.pink);
    cmdCorteDeCaja.setText("Corte de caja");
    cmdCorteDeCaja.addKeyListener(new
                                  FrmPosBoutiqueOculto_cmdCorteDeCaja_keyAdapter(this));
    cmdCorteDeCaja.addActionListener(new
                                     FrmPosBoutiqueOculto_cmdCorteDeCaja_actionAdapter(this));
    cmdDiarioDeEntradas.setBackground(Color.pink);
    cmdDiarioDeEntradas.setEnabled(true);
    cmdDiarioDeEntradas.setText("Diario de entradas");
    cmdDiarioDeEntradas.addKeyListener(new
                                       FrmPosBoutiqueOculto_cmdDiarioDeEntradas_keyAdapter(this));
    cmdDiarioDeEntradas.addActionListener(new
                                          FrmPosBoutiqueOculto_cmdDiarioDeEntradas_actionAdapter(this));
    cmdRegistrarEncargo.setBackground(Color.pink);
    cmdRegistrarEncargo.setText("Registrar encargo");
    cmdRegistrarEncargo.addKeyListener(new
                                       FrmPosBoutiqueOculto_cmdRegistrarEncargo_keyAdapter(this));
    cmdRegistrarEncargo.addActionListener(new
                                          FrmPosBoutiqueOculto_cmdRegistrarEncargo_actionAdapter(this));
    gridLayout1.setColumns(3);
    gridLayout1.setHgap(2);
    gridLayout1.setRows(4);
    gridLayout1.setVgap(2);
    this.addWindowListener(new FrmPosBoutiqueOculto_this_windowAdapter(this));
    //  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("Punto de venta");
    cmdBuscarProducto.setBackground(Color.pink);
    cmdBuscarProducto.setText("Buscar producto");
    cmdBuscarProducto.addKeyListener(new
                                     FrmPosBoutiqueOculto_cmdBuscarProducto_keyAdapter(this));
    cmdBuscarProducto.addActionListener(new
                                        FrmPosBoutiqueOculto_cmdBuscarProducto_actionAdapter(this));
    this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    jPanel1.add(cmdVentasCredito);
    jPanel1.add(cmdAbonos);
    jPanel1.add(cmdDevoluciones);
    jPanel1.add(cmdCorteDeCaja);
    jPanel1.add(cmdDiarioDeEntradas);
    jPanel1.add(cmdRegistrarEncargo);
    jPanel1.add(cmdBuscarProducto);
    this.getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);
    this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
    lblVendedor.setText("");
    lblSucursal.setText("");
    jPanel2.add(lblVendedor);
    jPanel2.add(lblSucursal);
  }

  public void cmdVentasContado_actionPerformed(ActionEvent e) {
    FrmVentaContado frm = new FrmVentaContado();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void this_windowOpened(WindowEvent e) {

  }

  public void cmdVentasCredito_actionPerformed(ActionEvent e) {
    FrmVentaCredito frm = new FrmVentaCredito();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
    this.setVisible(false);
  }

  public void cmdVentasApartado_actionPerformed(ActionEvent e) {
    FrmVentaApartado frm = new FrmVentaApartado();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
    this.setVisible(false);
  }

  public void cmdAbonos_actionPerformed(ActionEvent e) {
    FrmEstadoCuenta frm = new FrmEstadoCuenta();
    frm.pack();
    frm.setSize(730, 700);
    frm.setLocationRelativeTo(this);
    frm.setVisible(true);
    this.setVisible(false);
  }

  public void cmdPagarApartados_actionPerformed(ActionEvent e) {
    FrmControlApartados frm = new FrmControlApartados();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
    this.setVisible(false);
  }

  public void cmdDevoluciones_actionPerformed(ActionEvent e) {
    FrmDevoluciones frm = new FrmDevoluciones();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
    this.setVisible(false);
  }

  public void cmdCorteDeCaja_actionPerformed(ActionEvent e) {
    FrmCorteDeCaja2 frm = new FrmCorteDeCaja2();
    //frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setSize(407, 650);
    frm.setLocationRelativeTo(this);
    frm.setVisible(true);
    this.setVisible(false);
  }

  public void cmdDiarioDeEntradas_actionPerformed(ActionEvent e) {

    FrmDiarioDevoluciones frm3 = new FrmDiarioDevoluciones();
    frm3.pack();
    frm3.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm3.setVisible(true);

    FrmDiarioDeEntradas frm2 = new FrmDiarioDeEntradas();
    frm2.pack();
    frm2.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm2.setVisible(true);

    FrmDiarioCreditos frm = new FrmDiarioCreditos();
    frm.pack();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
    this.setVisible(false);

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

  public void cmdVentasCredito_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdAbonos_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdDevoluciones_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdCorteDeCaja_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdDiarioDeEntradas_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdRegistrarEncargo_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void cmdBuscarProducto_keyPressed(KeyEvent e) {
    openHidden(e);
  }

  public void openHidden(KeyEvent e) {
    if (e.getKeyCode() == 122) {
      this.setVisible(false);
    }
  }
}

class FrmPosBoutiqueOculto_cmdBuscarProducto_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdBuscarProducto_actionAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscarProducto_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdBuscarProducto_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdBuscarProducto_keyAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdBuscarProducto_keyPressed(e);
  }
}

class FrmPosBoutiqueOculto_cmdRegistrarEncargo_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdRegistrarEncargo_actionAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdRegistrarEncargo_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdRegistrarEncargo_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdRegistrarEncargo_keyAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdRegistrarEncargo_keyPressed(e);
  }
}

class FrmPosBoutiqueOculto_cmdDevoluciones_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdDevoluciones_actionAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdDevoluciones_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdDevoluciones_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdDevoluciones_keyAdapter(FrmPosBoutiqueOculto
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdDevoluciones_keyPressed(e);
  }
}

class FrmPosBoutiqueOculto_cmdCorteDeCaja_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdCorteDeCaja_actionAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCorteDeCaja_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdCorteDeCaja_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdCorteDeCaja_keyAdapter(FrmPosBoutiqueOculto adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdCorteDeCaja_keyPressed(e);
  }
}

class FrmPosBoutiqueOculto_cmdDiarioDeEntradas_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdDiarioDeEntradas_actionAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdDiarioDeEntradas_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdDiarioDeEntradas_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdDiarioDeEntradas_keyAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdDiarioDeEntradas_keyPressed(e);
  }
}

class FrmPosBoutiqueOculto_cmdAbonos_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdAbonos_actionAdapter(FrmPosBoutiqueOculto adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAbonos_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdAbonos_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdAbonos_keyAdapter(FrmPosBoutiqueOculto adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdAbonos_keyPressed(e);
  }
}

class FrmPosBoutiqueOculto_this_windowAdapter
    extends WindowAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_this_windowAdapter(FrmPosBoutiqueOculto adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class FrmPosBoutiqueOculto_cmdVentasCredito_actionAdapter
    implements ActionListener {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdVentasCredito_actionAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdVentasCredito_actionPerformed(e);
  }
}

class FrmPosBoutiqueOculto_cmdVentasCredito_keyAdapter
    extends KeyAdapter {
  private FrmPosBoutiqueOculto adaptee;
  FrmPosBoutiqueOculto_cmdVentasCredito_keyAdapter(FrmPosBoutiqueOculto
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.cmdVentasCredito_keyPressed(e);
  }
}
