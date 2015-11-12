package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

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
public class DlgRegistroInventario
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JSplitPane jSplitPane1 = new JSplitPane();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JButton jButton1 = new JButton();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel jLabel3 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable tblUltimasCompras = new JTable();
  JPanel jPanel3 = new JPanel();
  JButton cmdContinuar = new JButton();
  ModelUltimasCompras modelUltimasCompras1 = new ModelUltimasCompras();
  RegistroInventarioEngine engine = new RegistroInventarioEngine();
  FlowLayout flowLayout1 = new FlowLayout();
  public DlgRegistroInventario(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgRegistroInventario() {
    this(new Frame(), "DlgRegistroInventario", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    this.getContentPane().setBackground(Color.white);
    this.setTitle("Seleccionar una compra");
    panel1.setBackground(Color.white);
    jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
    jLabel1.setText("Selección de compra");
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jLabel2.setText(
        "Por favor, selecciona una compra de la lista o bien agrega una nueva compra");
    jButton1.setText("Agregar nueva compra");
    jButton1.addActionListener(new DlgRegistroInventario_jButton1_actionAdapter(this));
    jPanel2.setLayout(borderLayout2);
    jLabel3.setText("Ultimas compras realizadas");
    cmdContinuar.setText("Seleccionar compra");
    cmdContinuar.addActionListener(new
                                   DlgRegistroInventario_cmdContinuar_actionAdapter(this));
    tblUltimasCompras.setModel(modelUltimasCompras1);
    jPanel1.setLayout(flowLayout1);
    getContentPane().add(panel1);
    panel1.add(jLabel1, java.awt.BorderLayout.NORTH);
    panel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);
    jPanel1.add(jLabel2);
    jPanel1.add(jButton1);
    jSplitPane1.add(jPanel2, JSplitPane.BOTTOM);
    jSplitPane1.add(jPanel1, JSplitPane.TOP);
    jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tblUltimasCompras);
    jPanel2.add(jLabel3, java.awt.BorderLayout.NORTH);
    jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);
    jPanel3.add(cmdContinuar);
    this.modelUltimasCompras1.setData(engine.findUltimas10Compras());
  }

  public void jButton1_actionPerformed(ActionEvent e) {
    FrmCatalogoCompras frm = new FrmCatalogoCompras();
    frm.setModal(true);
    frm.pack();

    frm.setLocationRelativeTo(this);


    frm.setVisible(true);
    //Actualizamos la lista de las ultimas compras
    this.modelUltimasCompras1.setData(engine.findUltimas10Compras());
  }

  public void cmdContinuar_actionPerformed(ActionEvent e) {
    if (this.tblUltimasCompras.getSelectedRow() >= 0) {
      //Seleccionamos la compra a la que agregaremos inventario

      engine.seleccionarCompra(Integer.parseInt(this.tblUltimasCompras.getValueAt(this.tblUltimasCompras.getSelectedRow(),0 ).toString()));
      this.setVisible(false);
    }else{
      JOptionPane.showMessageDialog(this,"Es necesario que selecciones una compra","Atencion",JOptionPane.WARNING_MESSAGE);
    }
  }
}

class DlgRegistroInventario_cmdContinuar_actionAdapter
    implements ActionListener {
  private DlgRegistroInventario adaptee;
  DlgRegistroInventario_cmdContinuar_actionAdapter(DlgRegistroInventario
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdContinuar_actionPerformed(e);
  }
}

class DlgRegistroInventario_jButton1_actionAdapter
    implements ActionListener {
  private DlgRegistroInventario adaptee;
  DlgRegistroInventario_jButton1_actionAdapter(DlgRegistroInventario adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}
