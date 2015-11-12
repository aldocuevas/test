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
import com.toedter.calendar.JDateChooser;
import com.boutique.domain.Boutique;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class FrmDiarioDeEntradasPorDia
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
  ModelDiarioVentasDeContado modelDiarioVentasDeContado1 = new
      ModelDiarioVentasDeContado();
  DefaultComboBoxModel modelBoutiques = new DefaultComboBoxModel();
  DiarioDeEntradasEngine engine = new DiarioDeEntradasEngine();
  JLabel jLabel2 = new JLabel();
  JLabel lblTotal = new JLabel();
  JLabel lblFecha = new JLabel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JDateChooser jDateChooser1 = new JDateChooser();
  JButton cmdBuscar = new JButton();
  JComboBox cmbBoutique = new JComboBox();
  public FrmDiarioDeEntradasPorDia() {
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
    cmdCerrar.addActionListener(new
        FrmDiarioDeEntradasPorDia_cmdCerrar_actionAdapter(this));
    cmdImprimir.setText("IMPRIMIR");
    tblDiario.setBackground(new Color(255, 240, 255));
    tblDiario.setFont(new java.awt.Font("Arial", Font.PLAIN, 12));
    tblDiario.setModel(modelDiarioVentasDeContado1);
    tblDiario.addMouseListener(new
                               FrmDiarioDeEntradasPorDia_tblDiario_mouseAdapter(this));
    tblDiario.addKeyListener(new FrmDiarioDeEntradasPorDia_tblDiario_keyAdapter(this));
    this.addWindowListener(new FrmDiarioDeEntradasPorDia_this_windowAdapter(this));
    jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    jLabel2.setText("Total:");
    lblTotal.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    lblTotal.setText("");
    this.getContentPane().setBackground(Color.white);
    this.addKeyListener(new FrmDiarioDeEntradasPorDia_this_keyAdapter(this));
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
                                FrmDiarioDeEntradasPorDia_cmdBuscar_actionAdapter(this));
    cmbBoutique.setModel(modelBoutiques);
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
    jPanel1.add(jDateChooser1);
    jPanel1.add(cmbBoutique);
    jPanel1.add(cmdBuscar);
    jPanel1.add(lblFecha, null);
  }

  public void this_windowOpened(WindowEvent e) {
    //Sacamos los datos del diario de entradas
    java.util.List<Boutique> boutiques = engine.boutiques;
    for (Boutique b : boutiques) {
      modelBoutiques.addElement(b.getNombre());
    }

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
    Boutique b = (Boutique) engine.boutiques.get(cmbBoutique.getSelectedIndex());
    this.modelDiarioVentasDeContado1.data = engine.getVentasDeContado(b.getId(),
        jDateChooser1.getDate());
    modelDiarioVentasDeContado1.fireTableDataChanged();
    this.lblFecha.setText(AppInstance.formatoCorto.format(new java.util.Date()));
    this.lblTotal.setText(AppInstance.number.format(engine.totalVentasDeContado));
    this.setCursor(AppInstance.defCursor);
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

}

class FrmDiarioDeEntradasPorDia_cmdBuscar_actionAdapter
    implements ActionListener {
  private FrmDiarioDeEntradasPorDia adaptee;
  FrmDiarioDeEntradasPorDia_cmdBuscar_actionAdapter(FrmDiarioDeEntradasPorDia
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscar_actionPerformed(e);
  }
}

class FrmDiarioDeEntradasPorDia_tblDiario_keyAdapter
    extends KeyAdapter {
  private FrmDiarioDeEntradasPorDia adaptee;
  FrmDiarioDeEntradasPorDia_tblDiario_keyAdapter(FrmDiarioDeEntradasPorDia
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.tblDiario_keyPressed(e);
  }
}

class FrmDiarioDeEntradasPorDia_tblDiario_mouseAdapter
    extends MouseAdapter {
  private FrmDiarioDeEntradasPorDia adaptee;
  FrmDiarioDeEntradasPorDia_tblDiario_mouseAdapter(FrmDiarioDeEntradasPorDia
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblDiario_mouseClicked(e);
  }
}

class FrmDiarioDeEntradasPorDia_cmdCerrar_actionAdapter
    implements ActionListener {
  private FrmDiarioDeEntradasPorDia adaptee;
  FrmDiarioDeEntradasPorDia_cmdCerrar_actionAdapter(FrmDiarioDeEntradasPorDia
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCerrar_actionPerformed(e);
  }
}

class FrmDiarioDeEntradasPorDia_this_windowAdapter
    extends WindowAdapter {
  private FrmDiarioDeEntradasPorDia adaptee;
  FrmDiarioDeEntradasPorDia_this_windowAdapter(FrmDiarioDeEntradasPorDia
                                               adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class FrmDiarioDeEntradasPorDia_this_keyAdapter
    extends KeyAdapter {
  private FrmDiarioDeEntradasPorDia adaptee;
  FrmDiarioDeEntradasPorDia_this_keyAdapter(FrmDiarioDeEntradasPorDia adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
}
