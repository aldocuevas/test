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
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
 
public class FrmDiarioDeEntradas
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

FrmDatosVenta frmDatosVenta = null;

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
  ModelDiarioVentasDeContado modelDiarioVentasDeContado1 = new
      ModelDiarioVentasDeContado();
  DiarioDeEntradasEngine engine = new DiarioDeEntradasEngine();
  JLabel jLabel2 = new JLabel();
  JLabel lblTotal = new JLabel();
  JLabel lblFecha = new JLabel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  public FrmDiarioDeEntradas() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    setTitle("DIARIO CONTADO");
    frmDatosVenta = new FrmDatosVenta(engine);
    frmDatosVenta.setLocationRelativeTo(this);
    getContentPane().setLayout(borderLayout1);
    jLabel1.setFont(new java.awt.Font("Arial", Font.PLAIN, 18));
    jLabel1.setToolTipText("");
    jLabel1.setText("DIARIO DE ENTRADAS");
    pnlCentro.setLayout(borderLayout2);
    cmdCerrar.setText("CERRAR");
    cmdCerrar.addActionListener(new FrmDiarioDeEntradas_cmdCerrar_actionAdapter(this));
    cmdImprimir.setText("IMPRIMIR");
    tblDiario.setBackground(new Color(255, 240, 255));
    tblDiario.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
    tblDiario.setModel(modelDiarioVentasDeContado1);
    tblDiario.addMouseListener(new FrmDiarioDeEntradas_tblDiario_mouseAdapter(this));
    this.addWindowListener(new FrmDiarioDeEntradas_this_windowAdapter(this));
    jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    jLabel2.setText("Total:");
    lblTotal.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    lblTotal.setText("");
    this.getContentPane().setBackground(Color.white);
    this.addKeyListener(new FrmDiarioDeEntradas_this_keyAdapter(this));
    pnlCentro.setBackground(Color.white);
    pnlNorte.setBackground(Color.white);
    pnlNorte.setLayout(borderLayout4);
    scrollDiario.getViewport().setBackground(Color.white);
    scrollDiario.setPreferredSize(new Dimension(800, 600));
    lblFecha.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
    lblFecha.setText("");
    jPanel1.setLayout(borderLayout3);
    pnlSur.setMaximumSize(new Dimension(4000, 200));
    jPanel1.setBackground(Color.white);
    jPanel2.setBackground(Color.white);
    this.getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    pnlCentro.add(pnlNorte, java.awt.BorderLayout.CENTER);
    pnlCentro.add(scrollDiario, java.awt.BorderLayout.NORTH);
    scrollDiario.getViewport().add(tblDiario);
    this.getContentPane().add(pnlSur, java.awt.BorderLayout.SOUTH);
    pnlSur.add(cmdImprimir);
    pnlSur.add(cmdCerrar);
    this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
    jPanel1.add(lblFecha, java.awt.BorderLayout.EAST);
    jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);
    pnlNorte.add(jPanel2, java.awt.BorderLayout.EAST);
    jPanel2.add(jLabel2);
    jPanel2.add(lblTotal);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }

  public void this_windowOpened(WindowEvent e) {
    //Sacamos los datos del diario de entradas
    this.setCursor(AppInstance.waitCursor);
    this.modelDiarioVentasDeContado1.data = engine.getVentasDeContado();
    modelDiarioVentasDeContado1.fireTableDataChanged();
    this.lblFecha.setText(AppInstance.formatoCorto.format(new java.util.Date()));
    this.lblTotal.setText(AppInstance.number.format(engine.totalVentasDeContado));
    this.setCursor(AppInstance.defCursor);
  }

  public void cmdCerrar_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  public void tblDiario_keyPressed(KeyEvent e) {
    System.out.println(e.getKeyCode() + " " + e.getKeyChar());
  }

  public void this_keyPressed(KeyEvent e) {

  }

  public void tblDiario_mouseClicked(MouseEvent e) {
    this.setCursor(AppInstance.waitCursor);
    Object[] row = (Object[])this.modelDiarioVentasDeContado1.data.get(
        tblDiario.getSelectedRow());
    frmDatosVenta.idVenta = Integer.parseInt(row[0].toString());
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

class FrmDiarioDeEntradas_tblDiario_mouseAdapter
    extends MouseAdapter {
  private FrmDiarioDeEntradas adaptee;
  FrmDiarioDeEntradas_tblDiario_mouseAdapter(FrmDiarioDeEntradas adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblDiario_mouseClicked(e);
  }
}

class FrmDiarioDeEntradas_cmdCerrar_actionAdapter
    implements ActionListener {
  private FrmDiarioDeEntradas adaptee;
  FrmDiarioDeEntradas_cmdCerrar_actionAdapter(FrmDiarioDeEntradas adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCerrar_actionPerformed(e);
  }
}

class FrmDiarioDeEntradas_this_windowAdapter
    extends WindowAdapter {
  private FrmDiarioDeEntradas adaptee;
  FrmDiarioDeEntradas_this_windowAdapter(FrmDiarioDeEntradas adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }

  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }

}

class FrmDiarioDeEntradas_this_keyAdapter
    extends KeyAdapter {
  private FrmDiarioDeEntradas adaptee;
  FrmDiarioDeEntradas_this_keyAdapter(FrmDiarioDeEntradas adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}
