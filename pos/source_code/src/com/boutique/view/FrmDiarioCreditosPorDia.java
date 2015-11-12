package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.boutique.engine.impl.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import com.boutique.domain.Boutique;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class FrmDiarioCreditosPorDia
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlNorth = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlTitulo = new JPanel();
  JLabel jLabel1 = new JLabel();
  JPanel pnlCenter = new JPanel();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel2 = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel pnlBoutiqueLocal = new JPanel();
  JPanel pnlBoutique1 = new JPanel();
  JPanel pnlBoutique2 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel pnlTituloBoutiqueLocal = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel pnlTituloBoutique1 = new JPanel();
  DefaultComboBoxModel modelBoutiques = new DefaultComboBoxModel();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel pnlTituloBoutique2 = new JPanel();
  JLabel lblBoutiqueLocal = new JLabel();
  JLabel lblBoutique1 = new JLabel();
  JLabel lblBoutique2 = new JLabel();
  JPanel pnlTotalBoutiqueLocal = new JPanel();
  JPanel pnlTotalBoutique1 = new JPanel();
  JPanel pnlTotalBoutique2 = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JLabel lblTotalBoutiqueLocal = new JLabel();
  JLabel jLabel7 = new JLabel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JLabel lblTotalBoutique1 = new JLabel();
  JLabel jLabel8 = new JLabel();
  BorderLayout borderLayout8 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  JLabel lblTotalBoutique2 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JPanel pnlSouth = new JPanel();
  BorderLayout borderLayout9 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  JLabel lblGranTotal = new JLabel();
  JLabel jLabel10 = new JLabel();
  JPanel jPanel6 = new JPanel();
  JButton cmdCerrar = new JButton();
  JButton cmdImprimir = new JButton();
  JScrollPane scrollBoutiqueLocal = new JScrollPane();
  JScrollPane scrollBoutique1 = new JScrollPane();
  JScrollPane scrollBoutique2 = new JScrollPane();
  JTable tblBoutiqueLocal = new JTable();
  JTable tblBoutique1 = new JTable();
  JTable tblBoutique2 = new JTable();
  ModelDiarioEntradasCredito modelDiarioEntradasCredito1 = new
      ModelDiarioEntradasCredito();
  ModelDiarioEntradasCredito modelDiarioEntradasCredito2 = new
      ModelDiarioEntradasCredito();
  ModelDiarioEntradasCredito modelDiarioEntradasCredito3 = new
      ModelDiarioEntradasCredito();
  GridLayout gridLayout2 = new GridLayout();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JDateChooser jcFecha = new JDateChooser();
  JButton cmdBuscar = new JButton();
  JComboBox cmbBoutique = new JComboBox();
  DiarioDeEntradasEngine engine = new DiarioDeEntradasEngine();
  FrmDatosVenta frmDatosVenta = null;
  public FrmDiarioCreditosPorDia() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    frmDatosVenta = new FrmDatosVenta(engine);
    frmDatosVenta.setLocationRelativeTo(this);
    setTitle("DIARIO CREDITOS");
    getContentPane().setLayout(borderLayout1);
    pnlNorth.setLayout(borderLayout2);
    jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
    jLabel1.setText("ENTRADAS (CREDITOS)");
    pnlNorth.setBackground(Color.white);
    pnlTitulo.setBackground(Color.white);
    pnlCenter.setLayout(gridLayout1);
    pnlCenter.setBackground(Color.white);
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel2.setText("Fecha de hoy:");
    gridLayout1.setColumns(1);
    gridLayout1.setRows(3);
    pnlBoutiqueLocal.setLayout(borderLayout3);
    pnlBoutique1.setLayout(borderLayout4);
    pnlBoutique2.setLayout(borderLayout5);
    lblBoutiqueLocal.setText("Boutique local:");
    lblBoutique1.setText("Boutique:");
    lblBoutique2.setText("B2");
    pnlTotalBoutiqueLocal.setLayout(borderLayout6);
    lblTotalBoutiqueLocal.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    lblTotalBoutiqueLocal.setText("0.0");
    jLabel7.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel7.setText("Total:");
    pnlTotalBoutique1.setLayout(borderLayout7);
    lblTotalBoutique1.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    lblTotalBoutique1.setText("0.0");
    jLabel8.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel8.setText("Total:");
    pnlTotalBoutique2.setLayout(borderLayout8);
    lblTotalBoutique2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    lblTotalBoutique2.setText("0.0");
    jLabel9.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel9.setText("Total:");
    pnlSouth.setLayout(borderLayout9);
    lblGranTotal.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
    lblGranTotal.setText("0.0");
    jLabel10.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
    jLabel10.setText("Gran Total:");
    cmdCerrar.setText("CERRAR");
    cmdCerrar.addActionListener(new
                                FrmDiarioCreditosPorDia_cmdCerrar_actionAdapter(this));
    cmdImprimir.setText("IMPRIMIR");
    tblBoutiqueLocal.setModel(modelDiarioEntradasCredito1);
    tblBoutiqueLocal.addMouseListener(new
                                      FrmDiarioCreditosPorDia_tblBoutiqueLocal_mouseAdapter(this));
    tblBoutique1.setModel(modelDiarioEntradasCredito2);
    tblBoutique1.addMouseListener(new
                                  FrmDiarioCreditosPorDia_tblBoutique1_mouseAdapter(this));
    tblBoutique2.setModel(modelDiarioEntradasCredito3);
    tblBoutique2.addMouseListener(new
                                  FrmDiarioCreditosPorDia_tblBoutique2_mouseAdapter(this));
    this.getContentPane().setBackground(Color.white);
    this.addWindowListener(new FrmDiarioCreditosPorDia_this_windowAdapter(this));
    pnlBoutiqueLocal.setBackground(Color.white);
    pnlTotalBoutiqueLocal.setBackground(Color.white);
    pnlTituloBoutiqueLocal.setBackground(Color.white);
    scrollBoutiqueLocal.getViewport().setBackground(Color.white);
    jPanel2.setBackground(Color.white);
    jPanel1.setBackground(Color.white);
    pnlTituloBoutique1.setBackground(Color.white);
    scrollBoutique1.getViewport().setBackground(Color.white);
    pnlTotalBoutique1.setBackground(Color.white);
    jPanel3.setBackground(Color.white);
    pnlTituloBoutique2.setBackground(Color.white);
    scrollBoutique2.getViewport().setBackground(Color.white);
    pnlTotalBoutique2.setBackground(Color.white);
    jPanel4.setBackground(Color.white);
    jPanel6.setBackground(Color.white);
    jPanel5.setBackground(Color.white);
    jLabel3.setText("Entradas para:");
    jLabel4.setText("Entradas para:");
    cmdBuscar.setText("BUSCAR");
    cmdBuscar.addActionListener(new
                                FrmDiarioCreditosPorDia_cmdBuscar_actionAdapter(this));
    cmdBuscar.addActionListener(new
                                FrmDiarioCreditosPorDia_cmdBuscar_actionAdapter(this));
    cmbBoutique.setModel(modelBoutiques);
    pnlNorth.add(pnlTitulo, java.awt.BorderLayout.WEST);
    pnlTitulo.add(jLabel1);
    pnlCenter.add(pnlBoutiqueLocal);
    pnlTituloBoutiqueLocal.add(lblBoutiqueLocal);
    pnlCenter.add(pnlBoutique1);
    pnlTituloBoutique1.add(jLabel3);
    pnlTituloBoutique1.add(lblBoutique1);
    pnlCenter.add(pnlBoutique2);
    pnlTituloBoutique2.add(jLabel4);
    pnlTituloBoutique2.add(lblBoutique2);
    pnlNorth.add(jPanel1, java.awt.BorderLayout.SOUTH);
    jPanel1.add(jLabel2);
    jPanel1.add(jcFecha);
    jPanel1.add(cmbBoutique);
    jPanel1.add(cmdBuscar);
    pnlBoutiqueLocal.add(pnlTotalBoutiqueLocal, java.awt.BorderLayout.SOUTH);
    pnlBoutique1.add(pnlTotalBoutique1, java.awt.BorderLayout.SOUTH);
    pnlBoutique2.add(pnlTotalBoutique2, java.awt.BorderLayout.SOUTH);
    pnlTotalBoutiqueLocal.add(jPanel2, java.awt.BorderLayout.EAST);
    jPanel2.add(jLabel7);
    jPanel2.add(lblTotalBoutiqueLocal);
    pnlTotalBoutique1.add(jPanel3, java.awt.BorderLayout.EAST);
    jPanel3.add(jLabel8);
    jPanel3.add(lblTotalBoutique1);
    pnlTotalBoutique2.add(jPanel4, java.awt.BorderLayout.EAST);
    jPanel4.add(jLabel9);
    jPanel4.add(lblTotalBoutique2);
    this.getContentPane().add(pnlSouth, java.awt.BorderLayout.SOUTH);
    this.getContentPane().add(pnlNorth, java.awt.BorderLayout.NORTH);
    pnlSouth.add(jPanel5, java.awt.BorderLayout.EAST);
    jPanel5.add(jLabel10);
    jPanel5.add(lblGranTotal);
    pnlSouth.add(jPanel6, java.awt.BorderLayout.CENTER);
    jPanel6.add(cmdImprimir);
    jPanel6.add(cmdCerrar);
    pnlBoutiqueLocal.add(pnlTituloBoutiqueLocal, java.awt.BorderLayout.NORTH);
    pnlBoutiqueLocal.add(scrollBoutiqueLocal, java.awt.BorderLayout.CENTER);
    scrollBoutiqueLocal.getViewport().add(tblBoutiqueLocal);
    pnlBoutique1.add(pnlTituloBoutique1, java.awt.BorderLayout.NORTH);
    pnlBoutique1.add(scrollBoutique1, java.awt.BorderLayout.CENTER);
    scrollBoutique1.getViewport().add(tblBoutique1);
    pnlBoutique2.add(pnlTituloBoutique2, java.awt.BorderLayout.NORTH);
    pnlBoutique2.add(scrollBoutique2, java.awt.BorderLayout.CENTER);
    scrollBoutique2.getViewport().add(tblBoutique2);
    this.getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
  }

  public void this_windowOpened(WindowEvent e) {
    java.util.List<Boutique> boutiques = engine.boutiques;
    for (Boutique b : boutiques) {
      modelBoutiques.addElement(b.getNombre());
    }

    this.jcFecha.setDate(new java.util.Date());
  }

  public void cmdCerrar_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  public void cmdBuscar_actionPerformed(ActionEvent e) {
    this.setCursor(AppInstance.waitCursor);
    Boutique b = (Boutique) engine.boutiques.get(cmbBoutique.getSelectedIndex());
    engine.getDiarioACreditoCompleto(b.getId(), jcFecha.getDate());
    this.modelDiarioEntradasCredito1.data = engine.boutiqueLocal;
    this.modelDiarioEntradasCredito2.data = engine.boutique1;
    this.modelDiarioEntradasCredito3.data = engine.boutique2;
    this.modelDiarioEntradasCredito1.fireTableDataChanged();
    this.modelDiarioEntradasCredito2.fireTableDataChanged();
    this.modelDiarioEntradasCredito3.fireTableDataChanged();
    this.lblBoutiqueLocal.setText(engine.nombreBoutiqueLocal);
    this.lblBoutique1.setText(engine.nombreBoutique1);
    this.lblBoutique2.setText(engine.nombreBoutique2);
    this.lblGranTotal.setText(AppInstance.number.format(engine.granTotal()));
    this.lblTotalBoutique1.setText(AppInstance.number.format(engine.
        totalBoutique1));
    this.lblTotalBoutique2.setText(AppInstance.number.format(engine.
        totalBoutique2));
    this.lblTotalBoutiqueLocal.setText(AppInstance.number.format(engine.
        totalBoutiqueLocal));
    this.setCursor(AppInstance.defCursor);
  }

  public void tblBoutiqueLocal_mouseClicked(MouseEvent e) {
    this.setCursor(AppInstance.waitCursor);
    Object[] row = (Object[])this.modelDiarioEntradasCredito1.data.get(
        tblBoutiqueLocal.getSelectedRow());
    frmDatosVenta.idVenta = Integer.parseInt(row[0].toString());
    frmDatosVenta.ponerDatosVenta();
    frmDatosVenta.setVisible(true);
    this.setCursor(AppInstance.defCursor);
  }

  public void tblBoutique1_mouseClicked(MouseEvent e) {
    this.setCursor(AppInstance.waitCursor);
    Object[] row = (Object[])this.modelDiarioEntradasCredito2.data.get(
        tblBoutique1.getSelectedRow());
    frmDatosVenta.idVenta = Integer.parseInt(row[0].toString());
    frmDatosVenta.ponerDatosVenta();
    frmDatosVenta.setVisible(true);
    this.setCursor(AppInstance.defCursor);
  }

  public void tblBoutique2_mouseClicked(MouseEvent e) {
    this.setCursor(AppInstance.waitCursor);
    Object[] row = (Object[])this.modelDiarioEntradasCredito3.data.get(
        tblBoutique2.getSelectedRow());
    frmDatosVenta.idVenta = Integer.parseInt(row[0].toString());
    frmDatosVenta.ponerDatosVenta();
    frmDatosVenta.setVisible(true);
    this.setCursor(AppInstance.defCursor);
  }

  public void this_windowClosing(WindowEvent e) {
    this.frmDatosVenta.setVisible(false);
          this.frmDatosVenta.dispose();
        this.frmDatosVenta=null;
  }
}

class FrmDiarioCreditosPorDia_tblBoutique2_mouseAdapter
    extends MouseAdapter {
  private FrmDiarioCreditosPorDia adaptee;
  FrmDiarioCreditosPorDia_tblBoutique2_mouseAdapter(FrmDiarioCreditosPorDia
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblBoutique2_mouseClicked(e);
  }
}

class FrmDiarioCreditosPorDia_tblBoutique1_mouseAdapter
    extends MouseAdapter {
  private FrmDiarioCreditosPorDia adaptee;
  FrmDiarioCreditosPorDia_tblBoutique1_mouseAdapter(FrmDiarioCreditosPorDia
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblBoutique1_mouseClicked(e);
  }
}

class FrmDiarioCreditosPorDia_tblBoutiqueLocal_mouseAdapter
    extends MouseAdapter {
  private FrmDiarioCreditosPorDia adaptee;
  FrmDiarioCreditosPorDia_tblBoutiqueLocal_mouseAdapter(FrmDiarioCreditosPorDia
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblBoutiqueLocal_mouseClicked(e);
  }
}

class FrmDiarioCreditosPorDia_cmdBuscar_actionAdapter
    implements ActionListener {
  private FrmDiarioCreditosPorDia adaptee;
  FrmDiarioCreditosPorDia_cmdBuscar_actionAdapter(FrmDiarioCreditosPorDia
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdBuscar_actionPerformed(e);
  }
}

class FrmDiarioCreditosPorDia_cmdCerrar_actionAdapter
    implements ActionListener {
  private FrmDiarioCreditosPorDia adaptee;
  FrmDiarioCreditosPorDia_cmdCerrar_actionAdapter(FrmDiarioCreditosPorDia
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCerrar_actionPerformed(e);
  }
}

class FrmDiarioCreditosPorDia_this_windowAdapter
    extends WindowAdapter {
  private FrmDiarioCreditosPorDia adaptee;
  FrmDiarioCreditosPorDia_this_windowAdapter(FrmDiarioCreditosPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
  public void windowClosing(WindowEvent e){
    adaptee.this_windowClosing(e);
  }
}
