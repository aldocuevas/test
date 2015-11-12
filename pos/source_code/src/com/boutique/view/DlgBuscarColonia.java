package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.boutique.domain.Colonia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class DlgBuscarColonia
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2497284092242194983L;
JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JTextField txtNombreColonia = new JTextField();
  JButton cmdBuscar = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  JList lstColonias = new JList();
  DefaultListModel modelColonias = new DefaultListModel();
  public Colonia colonia;
  java.util.List<Colonia> lista;
  JPanel jPanel2 = new JPanel();
  JButton cmdAceptar = new JButton();
  public DlgBuscarColonia(Frame owner, String title, boolean modal) {
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

  public DlgBuscarColonia() {
    this(new Frame(), "DlgBuscarColonia", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    jLabel1.setText("Indique el nombre de la colonia:");
    txtNombreColonia.setText("");
    txtNombreColonia.addActionListener(new
        DlgBuscarColonia_txtNombreColonia_actionAdapter(this));
    cmdBuscar.setText("Buscar");
    cmdBuscar.addActionListener(new DlgBuscarColonia_cmdBuscar_actionAdapter(this));
    lstColonias.addMouseListener(new DlgBuscarColonia_lstColonias_mouseAdapter(this));
    cmdAceptar.setText("Aceptar");
    cmdAceptar.addActionListener(new DlgBuscarColonia_cmdAceptar_actionAdapter(this));
    getContentPane().add(panel1);
    jPanel1.add(txtNombreColonia, java.awt.BorderLayout.CENTER);
    jPanel1.add(cmdBuscar, java.awt.BorderLayout.EAST);
    jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);
    panel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jScrollPane1.getViewport().add(lstColonias);
    panel1.add(jPanel1, java.awt.BorderLayout.NORTH);
    panel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
    jPanel2.add(cmdAceptar);
    this.lstColonias.setModel(this.modelColonias);
  }

  public void cmdBuscar_actionPerformed(ActionEvent e) {
    lista =
        com.boutique.dao.DaoColonias.findByNombre(this.txtNombreColonia.getText());
    modelColonias.clear();
    for (Colonia c : lista) {
      modelColonias.addElement(c.getNombre());
    }
  }

  public void lstColonias_mouseClicked(MouseEvent e) {
    this.colonia = lista.get(lstColonias.getSelectedIndex());
  }

  public void cmdAceptar_actionPerformed(ActionEvent e) {
this.setVisible(false);
  }

  public void txtNombreColonia_actionPerformed(ActionEvent e) {
    lista =
        com.boutique.dao.DaoColonias.findByNombre(this.txtNombreColonia.getText());
    modelColonias.clear();
    for (Colonia c : lista) {
      modelColonias.addElement(c.getNombre());
    }
  }
}

class DlgBuscarColonia_txtNombreColonia_actionAdapter
    implements ActionListener {
  private DlgBuscarColonia adaptee;
  DlgBuscarColonia_txtNombreColonia_actionAdapter(DlgBuscarColonia adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtNombreColonia_actionPerformed(e);
  }
}

class DlgBuscarColonia_cmdAceptar_actionAdapter
    implements ActionListener {
  private DlgBuscarColonia adaptee;
  DlgBuscarColonia_cmdAceptar_actionAdapter(DlgBuscarColonia adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}

class DlgBuscarColonia_lstColonias_mouseAdapter
    extends MouseAdapter {
  private DlgBuscarColonia adaptee;
  DlgBuscarColonia_lstColonias_mouseAdapter(DlgBuscarColonia adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.lstColonias_mouseClicked(e);
  }
}

class DlgBuscarColonia_cmdBuscar_actionAdapter
    implements ActionListener {
  private DlgBuscarColonia adaptee;
  DlgBuscarColonia_cmdBuscar_actionAdapter(DlgBuscarColonia adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscar_actionPerformed(e);
  }
}
