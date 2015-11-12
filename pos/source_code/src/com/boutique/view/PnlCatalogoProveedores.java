package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.beans.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
 

public class PnlCatalogoProveedores
    extends JPanel
    implements PropertyChangeListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
BorderLayout borderLayout1 = new BorderLayout();
  JSplitPane jSplitPane1 = new JSplitPane();
  PnlBusquedaProveedores pnlBusquedaProveedores1 = new PnlBusquedaProveedores();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField cp = new JTextField();
  JTextField colonia = new JTextField();
  JRadioButton creditoNo = new JRadioButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JTextField rfc = new JTextField();
  JPanel jPanel2 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel2 = new JLabel();
  JTextField nombre = new JTextField();
  JLabel jLabel6 = new JLabel();
  JRadioButton creditoSi = new JRadioButton();
  JTextField estado = new JTextField();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JTextField ciudad = new JTextField();
  JTextField telefono = new JTextField();
  JTextField domicilio = new JTextField();
  JLabel jLabel5 = new JLabel();
  BorderLayout borderLayout2 = new BorderLayout();
  PnlOperaciones pnlOperaciones1 = new PnlOperaciones();
  Proveedor proveedor = new Proveedor();
  DaoProveedores dirProveedores = new DaoProveedores();
  public PnlCatalogoProveedores() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.pnlBusquedaProveedores1.addPropertyChangeListener(this);
    this.pnlOperaciones1.addPropertyChangeListener(this);
    this.setLayout(borderLayout1);
    jLabel8.setText("RFC:");
    jLabel4.setText("Ciudad:");
    cp.setText("");
    colonia.setText("");
    creditoNo.setText("No");
    jLabel1.setText("Nombre:");
    jLabel1.setToolTipText("");
    jLabel3.setText("Colonia:");
    jLabel7.setText("Teléfono:");
    rfc.setText("");
    jPanel2.setLayout(gridLayout1);
    gridLayout1.setColumns(2);
    gridLayout1.setRows(10);
    jLabel2.setText("Domicilio:");
    nombre.setText("jTextField1");
    nombre.setText("");
    jLabel6.setToolTipText("");
    jLabel6.setText("Código postal:");
    creditoSi.setText("Si");
    estado.setText("jTextField5");
    estado.setText("");
    jLabel9.setText("Me da crédito:");
    jLabel10.setText("");
    ciudad.setText("jTextField4");
    ciudad.setText("");
    telefono.setText("jTextField7");
    telefono.setText("");
    domicilio.setText("jTextField2");
    domicilio.setText("");
    jLabel5.setRequestFocusEnabled(true);
    jLabel5.setText("Estado:");
    jPanel1.setLayout(borderLayout2);
    colonia.setText("");
    cp.setText("");
    this.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(pnlBusquedaProveedores1, JSplitPane.LEFT);
    jSplitPane1.add(jPanel1, JSplitPane.RIGHT);
    jPanel1.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jLabel1, null);
    jPanel2.add(nombre, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(domicilio, null);
    jPanel2.add(jLabel3, null);
    jPanel2.add(colonia, null);
    jPanel2.add(jLabel4, null);
    jPanel2.add(ciudad, null);
    jPanel2.add(jLabel5, null);
    jPanel2.add(estado, null);
    jPanel2.add(jLabel6, null);
    jPanel2.add(cp, null);
    jPanel2.add(jLabel7, null);
    jPanel2.add(telefono, null);
    jPanel2.add(jLabel8, null);
    jPanel2.add(rfc, null);
    jPanel2.add(jLabel9, null);
    jPanel2.add(jLabel10, null);
    jPanel2.add(creditoSi, null);
    jPanel2.add(creditoNo, null);
    jPanel1.add(pnlOperaciones1, BorderLayout.SOUTH);
    buttonGroup1.add(creditoSi);
    buttonGroup1.add(creditoNo);
    this.desHabilitarCajas();
    nombre.setDisabledTextColor(Color.black);
    domicilio.setDisabledTextColor(Color.black);
    colonia.setDisabledTextColor(Color.black);
    ciudad.setDisabledTextColor(Color.black);
    estado.setDisabledTextColor(Color.black);
    cp.setDisabledTextColor(Color.black);
    telefono.setDisabledTextColor(Color.black);
    rfc.setDisabledTextColor(Color.black);
  }

  public void propertyChange(PropertyChangeEvent e) {
    if (e.getPropertyName().equals("proveedor")) {
      proveedor = (Proveedor) e.getNewValue();
      getDatosProveedor();
    }
    else if (e.getPropertyName().equals("accion")) {
      if (e.getNewValue().equals("add")) {
        this.limpiarCajas();
        this.habilitarCajas();
        this.nombre.requestFocus();
      }

      else if (e.getNewValue().equals("edit")) {
        this.habilitarCajas();
        this.nombre.requestFocus();
      }
      else if (e.getNewValue().equals("delete")) {
        if(proveedor!=null){
          DaoProveedores.remove(proveedor.getId());
        }

      }
      else if (e.getNewValue().equals("ok")) {
        if (e.getOldValue().equals("add")) {
          this.setDatosProveedor();
          if (!DaoProveedores.add(proveedor)) {
            JOptionPane.showMessageDialog(null,
                                          "No se pudo agregar el cliente");
            this.pnlOperaciones1.setAccionExitosa(false);
          }
          else {
            this.limpiarCajas();
            this.desHabilitarCajas();
            this.pnlOperaciones1.setAccionExitosa(true);
          }

        }
        else if (e.getOldValue().equals("edit")) {
          this.setDatosProveedor();
          if (!DaoProveedores.update(proveedor)) {
            JOptionPane.showMessageDialog(null,
                                          "No se pudo actualizar el cliente");
            this.pnlOperaciones1.setAccionExitosa(false);
          }
          else {
            this.desHabilitarCajas();
           this.pnlOperaciones1.setAccionExitosa(true);
          }

        }
      }
      else if (e.getNewValue().equals("cancel")) {
        this.desHabilitarCajas();
        this.limpiarCajas();
      }

    }

  }

  /**
   * desHabilitarCajas
   */
  public void desHabilitarCajas() {
    nombre.setEnabled(false);
    domicilio.setEnabled(false);
    colonia.setEnabled(false);
    ciudad.setEnabled(false);
    estado.setEnabled(false);
    cp.setEnabled(false);
    telefono.setEnabled(false);
    creditoSi.setEnabled(false);
    creditoNo.setEnabled(false);
    rfc.setEnabled(false);
  }

  /**
   * setDatosCheque
   */
  public void setDatosProveedor() {
    proveedor.setNombre(nombre.getText().toUpperCase());
    proveedor.setDomicilio(domicilio.getText().toUpperCase());
    proveedor.setColonia(colonia.getText().toUpperCase());
    proveedor.setEstado(estado.getText().toUpperCase());
    proveedor.setCiudad(ciudad.getText().toUpperCase());
    proveedor.setCp(cp.getText().toUpperCase());
    proveedor.setTelefono(telefono.getText().toUpperCase());
    proveedor.setRfc(rfc.getText().toUpperCase());
    if (creditoSi.isSelected()) {
      proveedor.setCredito(1);
    }
    else if (creditoNo.isSelected()) {
      proveedor.setCredito(0);
    }
  }

  /**
   * habilitarCajas
   */
  public void habilitarCajas() {
    nombre.setEnabled(true);
    domicilio.setEnabled(true);
    colonia.setEnabled(true);
    ciudad.setEnabled(true);
    estado.setEnabled(true);
    cp.setEnabled(true);
    telefono.setEnabled(true);
    creditoSi.setEnabled(true);
    creditoNo.setEnabled(true);
    rfc.setEnabled(true);
  }

  /**
   * limpiarCajas
   */
  public void limpiarCajas() {
    nombre.setText("");
    domicilio.setText("");
    colonia.setText("");
    ciudad.setText("");
    estado.setText("");
    cp.setText("");
    telefono.setText("");
    creditoSi.setSelected(false);
    creditoNo.setSelected(false);
    rfc.setText("");
  }

  /**
   * getDatosProveedor
   */
  public void getDatosProveedor() {
    nombre.setText(proveedor.getNombre());
    domicilio.setText(proveedor.getDomicilio());
    colonia.setText(proveedor.getColonia());
    ciudad.setText(proveedor.getCiudad());
    estado.setText(proveedor.getEstado());
    cp.setText(proveedor.getCp());
    telefono.setText(proveedor.getTelefono());
    if (proveedor.getCredito()==1) {
      creditoSi.setSelected(true);
    }
    else if (proveedor.getCredito()==0) {
      creditoNo.setSelected(true);
    }
    rfc.setText(proveedor.getRfc());
  }
}
