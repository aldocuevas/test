package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.boutique.engine.impl.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import com.boutique.dao.DaoResumenes;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class FrmDiarioDevolucionesPorDia
    extends JFrame {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
FrmDatosVenta frmDatosVenta=null;
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JPanel pnlCentro = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlNorte = new JPanel();
  JScrollPane scrollDiario = new JScrollPane();
  JTable tblDiario = new JTable();
  JPanel pnlSur = new JPanel();
  JButton cmdCerrar = new JButton();
  JButton cmdImprimir = new JButton();
  ModelDiarioDevoluciones modelDiarioDevoluciones = new
      ModelDiarioDevoluciones();

  JLabel jLabel2 = new JLabel();
  JLabel lblTotal = new JLabel();
  JLabel lblFecha = new JLabel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JDateChooser jDateChooser1 = new JDateChooser();
  JButton cmdBuscar = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  public FrmDiarioDevolucionesPorDia() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    setTitle("CANCELACIONES DEL DIA");
    frmDatosVenta = new FrmDatosVenta(new DiarioDeEntradasEngine());
    frmDatosVenta.setLocationRelativeTo(this);
    getContentPane().setLayout(borderLayout1);
    jLabel1.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
    jLabel1.setToolTipText("");
    jLabel1.setText("CANCELACIONES DEL DIA");
    pnlCentro.setLayout(borderLayout2);
    cmdCerrar.setText("CERRAR");
    cmdCerrar.addActionListener(new FrmDiarioDevolucionesPorDia_cmdCerrar_actionAdapter(this));
    cmdImprimir.setText("IMPRIMIR");
    tblDiario.setBackground(new Color(255, 240, 255));
    tblDiario.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
    tblDiario.setModel(modelDiarioDevoluciones);
    tblDiario.addMouseListener(new
        FrmDiarioDevolucionesPorDia_tblDiario_mouseAdapter(this));
    tblDiario.addKeyListener(new FrmDiarioDevolucionesPorDia_tblDiario_keyAdapter(this));
    this.addWindowListener(new FrmDiarioDevolucionesPorDia_this_windowAdapter(this));
    jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    jLabel2.setText("");
    lblTotal.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    lblTotal.setText("");
    this.getContentPane().setBackground(Color.white);
    this.addKeyListener(new FrmDiarioDevolucionesPorDia_this_keyAdapter(this));
    pnlCentro.setBackground(Color.white);
    pnlNorte.setBackground(Color.white);
    pnlNorte.setLayout(borderLayout4);
    scrollDiario.getViewport().setBackground(Color.white);
    scrollDiario.setPreferredSize(new Dimension(800, 600));
    lblFecha.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
    lblFecha.setText("");
    jPanel1.setLayout(flowLayout1);
    pnlSur.setMaximumSize(new Dimension(4000, 200));
    jPanel1.setBackground(Color.white);
    jPanel2.setBackground(Color.white);
    cmdBuscar.setText("BUSCAR");
    cmdBuscar.addActionListener(new
        FrmDiarioDevolucionesPorDia_cmdBuscar_actionAdapter(this));
    this.getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    pnlCentro.add(pnlNorte, java.awt.BorderLayout.CENTER);
    pnlCentro.add(scrollDiario, java.awt.BorderLayout.NORTH);
    scrollDiario.getViewport().add(tblDiario);
    this.getContentPane().add(pnlSur, java.awt.BorderLayout.SOUTH);
    pnlSur.add(cmdImprimir);
    pnlSur.add(cmdCerrar);
    this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
    pnlNorte.add(jPanel2, java.awt.BorderLayout.EAST);
    jPanel2.add(jLabel2);
    jPanel2.add(lblTotal);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jDateChooser1, null);
    jPanel1.add(cmdBuscar, null);
    jPanel1.add(lblFecha, null);
  }

  public void this_windowOpened(WindowEvent e) {
    //Sacamos los datos del diario de entradas
    this.jDateChooser1.setDate(new java.util.Date());
  }

  public void cmdCerrar_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  public void tblDiario_keyPressed(KeyEvent e) {
    System.out.println(e.getKeyCode() + " " + e.getKeyChar());
  }

  public void this_keyPressed(KeyEvent e) {

  }

  public void cmdBuscar_actionPerformed(ActionEvent e) {
    this.setCursor(AppInstance.waitCursor);
    this.modelDiarioDevoluciones.data = DaoResumenes.getDevolucionesDelDia(AppInstance.boutique().getId(),this.jDateChooser1.getDate());
    modelDiarioDevoluciones.fireTableDataChanged();
    this.lblFecha.setText(AppInstance.formatoCorto.format(this.jDateChooser1.getDate()));
   // this.lblTotal.setText(AppInstance.number.format(engine.totalVentasDeContado));
   this.setCursor(AppInstance.defCursor);
  }

  public void tblDiario_mouseClicked(MouseEvent e) {
    this.setCursor(AppInstance.waitCursor);

       Object[] row = (Object[])this.modelDiarioDevoluciones.data.get(
           tblDiario.getSelectedRow());
       frmDatosVenta.idVenta = Integer.parseInt(row[3].toString());
       frmDatosVenta.ponerDatosVenta();
       frmDatosVenta.setVisible(true);
    this.setCursor(AppInstance.defCursor);
  }

  public void this_windowClosing(WindowEvent e) {
    this.frmDatosVenta.setVisible(false);
    this.frmDatosVenta.dispose();
    this.frmDatosVenta = null;
  }
}

class FrmDiarioDevolucionesPorDia_cmdBuscar_actionAdapter
    implements ActionListener {
  private FrmDiarioDevolucionesPorDia adaptee;
  FrmDiarioDevolucionesPorDia_cmdBuscar_actionAdapter(
      FrmDiarioDevolucionesPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscar_actionPerformed(e);
  }
}

class FrmDiarioDevolucionesPorDia_tblDiario_keyAdapter
    extends KeyAdapter {
  private FrmDiarioDevolucionesPorDia adaptee;
  FrmDiarioDevolucionesPorDia_tblDiario_keyAdapter(FrmDiarioDevolucionesPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.tblDiario_keyPressed(e);
  }
}

class FrmDiarioDevolucionesPorDia_tblDiario_mouseAdapter
    extends MouseAdapter {
  private FrmDiarioDevolucionesPorDia adaptee;
  FrmDiarioDevolucionesPorDia_tblDiario_mouseAdapter(
      FrmDiarioDevolucionesPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblDiario_mouseClicked(e);
  }
}

class FrmDiarioDevolucionesPorDia_cmdCerrar_actionAdapter
    implements ActionListener {
  private FrmDiarioDevolucionesPorDia adaptee;
  FrmDiarioDevolucionesPorDia_cmdCerrar_actionAdapter(FrmDiarioDevolucionesPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCerrar_actionPerformed(e);
  }
}

class FrmDiarioDevolucionesPorDia_this_windowAdapter
    extends WindowAdapter {
  private FrmDiarioDevolucionesPorDia adaptee;
  FrmDiarioDevolucionesPorDia_this_windowAdapter(FrmDiarioDevolucionesPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
  public void windowClosing(WindowEvent e) {
   adaptee.this_windowClosing(e);
 }

}

class FrmDiarioDevolucionesPorDia_this_keyAdapter
    extends KeyAdapter {
  private FrmDiarioDevolucionesPorDia adaptee;
  FrmDiarioDevolucionesPorDia_this_keyAdapter(FrmDiarioDevolucionesPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}
