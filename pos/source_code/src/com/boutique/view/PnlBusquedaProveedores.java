package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.beans.*;
import java.util.*;

import com.boutique.engine.impl.*;
import com.boutique.domain.*;

public class PnlBusquedaProveedores
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel1 = new JPanel();
  JButton cmdBuscar = new JButton();
  JTextField txtNombre = new JTextField();
  JSplitPane jSplitPane1 = new JSplitPane();
  JLabel jLabel1 = new JLabel();
  JList lstProveedores = new JList();
  BorderLayout borderLayout2 = new BorderLayout();
  // DaoProveedores dirProveedores = new DaoProveedores();
  ProveedoresEngine engine = new ProveedoresEngine();
  DefaultListModel lModel = new DefaultListModel();
  @SuppressWarnings("rawtypes")
private transient Vector propertyChangeListeners;
  public PnlBusquedaProveedores() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    jPanel1.setLayout(gridLayout1);
    this.setLayout(borderLayout1);
    cmdBuscar.setText("Buscar");
    cmdBuscar.addActionListener(new
                                PnlBusquedaProveedores_cmdBuscar_actionAdapter(this));
    txtNombre.setText("");
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jLabel1.setToolTipText("");
    jLabel1.setText("Nombre:");
    jSplitPane1.setDividerLocation(30);
    lstProveedores.addKeyListener(new
                                  PnlBusquedaProveedores_lstProveedores_keyAdapter(this));
    lstProveedores.addMouseListener(new
                                    PnlBusquedaProveedores_lstProveedores_mouseAdapter(this));
    this.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(lstProveedores, JSplitPane.BOTTOM);
    jSplitPane1.add(jPanel1, JSplitPane.TOP);
    jPanel1.add(jLabel1, null);
    jPanel1.add(txtNombre, null);
    jPanel1.add(cmdBuscar, null);
    lstProveedores.setModel(lModel);
  }

  void cmdBuscar_actionPerformed(ActionEvent e) {
    if (cmdBuscar.isEnabled()) {
      lModel.clear();
      //Buscamos los proveedores
      engine.BuscarProveedores(this.txtNombre.getText());
      for (Proveedor p : engine.proveedores() ) {
        lModel.addElement(p.getNombre());
      }
    }
  }

  @SuppressWarnings("rawtypes")
public synchronized void removePropertyChangeListener(PropertyChangeListener
      l) {
    super.removePropertyChangeListener(l);
    if (propertyChangeListeners != null && propertyChangeListeners.contains(l)) {
      Vector v = (Vector) propertyChangeListeners.clone();
      v.removeElement(l);
      propertyChangeListeners = v;
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
    super.addPropertyChangeListener(l);
    Vector v = propertyChangeListeners == null ? new Vector(2) :
        (Vector) propertyChangeListeners.clone();
    if (!v.contains(l)) {
      v.addElement(l);
      propertyChangeListeners = v;
    }
  }

  @SuppressWarnings("rawtypes")
protected void firePropertyChange(PropertyChangeEvent e) {
    if (propertyChangeListeners != null) {
      Vector listeners = propertyChangeListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ( (PropertyChangeListener) listeners.elementAt(i)).propertyChange(e);
      }
    }
  }

  void lstProveedores_keyReleased(KeyEvent e) {
    Proveedor p = engine.proveedores().get(lstProveedores.getSelectedIndex());
    this.firePropertyChange("proveedor", "none", p);
  }

  void lstProveedores_mouseClicked(MouseEvent e) {
    if (lstProveedores.getSelectedIndex() >= 0) {
     Proveedor p = engine.proveedores().get(lstProveedores.getSelectedIndex());
      this.firePropertyChange("proveedor", "null",p);
    }
  }
}

class PnlBusquedaProveedores_cmdBuscar_actionAdapter
    implements java.awt.event.ActionListener {
  PnlBusquedaProveedores adaptee;

  PnlBusquedaProveedores_cmdBuscar_actionAdapter(PnlBusquedaProveedores
                                                 adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdBuscar_actionPerformed(e);
  }
}

class PnlBusquedaProveedores_lstProveedores_keyAdapter
    extends java.awt.event.KeyAdapter {
  PnlBusquedaProveedores adaptee;

  PnlBusquedaProveedores_lstProveedores_keyAdapter(PnlBusquedaProveedores
      adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.lstProveedores_keyReleased(e);
  }
}

class PnlBusquedaProveedores_lstProveedores_mouseAdapter
    extends java.awt.event.MouseAdapter {
  PnlBusquedaProveedores adaptee;

  PnlBusquedaProveedores_lstProveedores_mouseAdapter(PnlBusquedaProveedores
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.lstProveedores_mouseClicked(e);
  }
}
