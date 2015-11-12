package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.boutique.dao.DaoInventarios;
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
@SuppressWarnings({ "unused", "serial" })
public class PnlModificacionProductos
    extends JPanel {
  ModificacionProductosEngine engine = new ModificacionProductosEngine();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlTop = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtEtiqueta = new JTextField();
  JButton cmdBuscar = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JScrollPane scrollDatos = new JScrollPane();
  JTable tblDatos = new JTable();
  ModelModificacionProducto modelDatosProducto1 = new ModelModificacionProducto();
  JPanel jPanel1 = new JPanel();
  JButton cmdImprimirEtiqueta = new JButton();
  JButton cmdCambiarPrecio = new JButton();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel pnlPrecio = new JPanel();
  JLabel jLabel3 = new JLabel();
  JTextField txtPrecio = new JTextField();
  GridLayout gridLayout2 = new GridLayout();
  double precioActual = 0;
  public PnlModificacionProductos() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jLabel1.setText("Etiqueta del producto:");
    txtEtiqueta.setText("");
    txtEtiqueta.addActionListener(new
                                  PnlModificacionProductos_txtEtiqueta_actionAdapter(this));
    cmdBuscar.setMnemonic('B');
    cmdBuscar.setText("Buscar");
    cmdBuscar.addActionListener(new
                                PnlModificacionProductos_cmdBuscar_actionAdapter(this));
    pnlTop.setLayout(borderLayout2);
    tblDatos.setModel(modelDatosProducto1);
    cmdImprimirEtiqueta.setText("IMPRIMIR ETIQUETA");
    cmdImprimirEtiqueta.addActionListener(new
                                          PnlModificacionProductos_cmdImprimirEtiqueta_actionAdapter(this));
    cmdCambiarPrecio.setText("CAMBIAR PRECIO");
    cmdCambiarPrecio.addActionListener(new
                                       PnlModificacionProductos_cmdCambiarPrecio_actionAdapter(this));
    jPanel2.setLayout(borderLayout3);
    jLabel2.setText("MODIFICACION DE PRECIO");
    jPanel4.setLayout(gridLayout1);
    jLabel3.setText("NUEVO PRECIO");
    txtPrecio.setText("");
    txtPrecio.addKeyListener(new PnlModificacionProductos_txtPrecio_keyAdapter(this));
    txtPrecio.addFocusListener(new
                               PnlModificacionProductos_txtPrecio_focusAdapter(this));
    pnlPrecio.setLayout(gridLayout2);
    pnlTop.add(txtEtiqueta, java.awt.BorderLayout.CENTER);
    pnlTop.add(jLabel1, java.awt.BorderLayout.WEST);
    pnlTop.add(cmdBuscar, java.awt.BorderLayout.EAST);
    jPanel3.add(jLabel2);
    jPanel4.add(pnlPrecio);
    jPanel4.add(pnlTop);
    pnlPrecio.add(jLabel3);
    pnlPrecio.add(txtPrecio);

    this.add(scrollDatos, java.awt.BorderLayout.CENTER);
    scrollDatos.getViewport().add(tblDatos);
    this.add(jPanel1, java.awt.BorderLayout.SOUTH);
    jPanel1.add(cmdCambiarPrecio);
    jPanel1.add(cmdImprimirEtiqueta);
    this.add(jPanel2, java.awt.BorderLayout.NORTH);
    jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);
    jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);
  }

  public void txtEtiqueta_actionPerformed(ActionEvent e) {
    String etiqueta = txtEtiqueta.getText().replace('\'', '-');
    mostrarResultados(etiqueta);

  }

  /**
   * mostrarResultados
   *
   * @param etiqueta String
   */
  private void mostrarResultados(String etiqueta) {
    //Buscamos en el inventarioLocal y mostramos los resultados de la busqueda...
    this.setCursor(AppInstance.waitCursor);
    this.modelDatosProducto1.data = engine.cambiarPrecioaProducto(etiqueta,
        precioActual);
    this.txtEtiqueta.setText("");
    this.txtEtiqueta.requestFocus();
    this.modelDatosProducto1.fireTableDataChanged();
    this.setCursor(AppInstance.defCursor);
  }

  public void cmdBuscar_actionPerformed(ActionEvent e) {
    String etiqueta = txtEtiqueta.getText().replace('\'', '-');
    mostrarResultados(etiqueta);
  }

  public void txtPrecio_focusLost(FocusEvent e) {
    //REVISAMOS SI EL PRECIO ESTA CORRECTO
    try {
      precioActual = Double.parseDouble(txtPrecio.getText());
      txtPrecio.setText(AppInstance.number.format(precioActual));
      this.habilitarBotones();
    }
    catch (NumberFormatException ex) {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this, "INDIQUE UN PRECIO VALIDO",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio, JOptionPane.ERROR_MESSAGE);
      precioActual = 0;
      txtPrecio.setText("0.0");
    }
  }

  private void habilitarBotones() {
    this.cmdBuscar.setEnabled(true);
    this.txtEtiqueta.setEnabled(true);
    this.cmdCambiarPrecio.setEnabled(true);
  }

  private void deshabilitarBotones() {
    this.cmdBuscar.setEnabled(false);
    this.txtEtiqueta.setEnabled(false);
    this.cmdCambiarPrecio.setEnabled(false);
    this.cmdImprimirEtiqueta.setEnabled(false);

  }

  public void cmdCambiarPrecio_actionPerformed(ActionEvent e) {
    //CAMBIAMOS DE PRECIO LOS PRODUCTOS CON LA LISTA QUE TIENE EL ENGINE
    this.setCursor(AppInstance.waitCursor);
    if (engine.guardarCambios()) {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this,
                                    "EL PRECIO DE LOS PRODUCTOS HA SIDO ACTUALIZADO",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio,
                                    JOptionPane.INFORMATION_MESSAGE);
      this.deshabilitarBotones();
      this.txtPrecio.setEnabled(false);
      this.cmdImprimirEtiqueta.setEnabled(true);

    }
    else {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this,
                                    "NO SE PUDO ACTUALIZAR EL PRECIO DE LOS PRODUCTOS",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio,
                                    JOptionPane.ERROR_MESSAGE);
      this.cmdImprimirEtiqueta.setEnabled(false);

    }
  }

  public void cmdImprimirEtiqueta_actionPerformed(ActionEvent e) {
    int[] rows = this.tblDatos.getSelectedRows();
    if (rows.length > 0) {
      DlgImprimirEtiquetas dlg = new DlgImprimirEtiquetas(null, "", true);
      dlg.setSize(300, 200);
      dlg.setLocationRelativeTo(this);
      dlg.setVisible(true);
      if (dlg.posicion > 0) {
        this.engine.imprimirEtiquetas(dlg.posicion, rows[0],
                                      rows[rows.length - 1], 1);
      }
    }
  }

  public void txtPrecio_keyReleased(KeyEvent e) {
    //REVISAMOS SI EL PRECIO ESTA CORRECTO
    try {
      if (txtPrecio.getText().length() > 0) {
        precioActual = Double.parseDouble(txtPrecio.getText());
        this.habilitarBotones();
      }
    }
    catch (NumberFormatException ex) {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this, "INDIQUE UN PRECIO VALIDO",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio, JOptionPane.ERROR_MESSAGE);
      precioActual = 0;
      txtPrecio.setText("0.0");
    }
  }
}

class PnlModificacionProductos_txtPrecio_keyAdapter
    extends KeyAdapter {
  private PnlModificacionProductos adaptee;
  PnlModificacionProductos_txtPrecio_keyAdapter(PnlModificacionProductos
                                                adaptee) {
    this.adaptee = adaptee;
  }

  public void keyReleased(KeyEvent e) {
    adaptee.txtPrecio_keyReleased(e);
  }
}

class PnlModificacionProductos_cmdCambiarPrecio_actionAdapter
    implements ActionListener {
  private PnlModificacionProductos adaptee;
  PnlModificacionProductos_cmdCambiarPrecio_actionAdapter(
      PnlModificacionProductos adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCambiarPrecio_actionPerformed(e);
  }
}

class PnlModificacionProductos_txtPrecio_focusAdapter
    extends FocusAdapter {
  private PnlModificacionProductos adaptee;
  PnlModificacionProductos_txtPrecio_focusAdapter(PnlModificacionProductos
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void focusLost(FocusEvent e) {
    adaptee.txtPrecio_focusLost(e);
  }

  public void focusGained(FocusEvent e) {
  }

}

class PnlModificacionProductos_cmdBuscar_actionAdapter
    implements ActionListener {
  private PnlModificacionProductos adaptee;
  PnlModificacionProductos_cmdBuscar_actionAdapter(PnlModificacionProductos
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdBuscar_actionPerformed(e);
  }
}

class PnlModificacionProductos_txtEtiqueta_actionAdapter
    implements ActionListener {
  private PnlModificacionProductos adaptee;
  PnlModificacionProductos_txtEtiqueta_actionAdapter(PnlModificacionProductos
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtEtiqueta_actionPerformed(e);
  }
}

class PnlModificacionProductos_cmdImprimirEtiqueta_actionAdapter
    implements ActionListener {
  private PnlModificacionProductos adaptee;
  PnlModificacionProductos_cmdImprimirEtiqueta_actionAdapter(
      PnlModificacionProductos adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdImprimirEtiqueta_actionPerformed(e);
  }
}
