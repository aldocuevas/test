package com.boutique.view;

import javax.swing.*;
import java.awt.*;

import com.boutique.domain.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.boutique.dao.*;

public class FrmCatalogoColonias
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
pnlCatalogosOperaciones pnlCatalogo = new
      pnlCatalogosOperaciones();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JPanel pnlCentral = new JPanel();
  JLabel jLabel2 = new JLabel();
  JTextField txtNombre = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField txtCiudad = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField txtCodigoPostal = new JTextField();
  JLabel jLabel5 = new JLabel();
  JTextField txtMunicipio = new JTextField();
  //  PnlOperaciones pnlOperaciones1 = new PnlOperaciones();
  Colonia coloniaSeleccionada;
  JTextField jTextField1 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JTextField txtEstado = new JTextField();
     DlgBuscarColonia dlg = new DlgBuscarColonia(this, "Buscar colonias", true);
  JLabel jLabel7 = new JLabel();
  JTextField txtRuta = new JTextField();

  public FrmCatalogoColonias() {
    try {
      jbInit();

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
dlg.setSize(400,300);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Colonias");
    this.getContentPane().setLayout(borderLayout1);
    jLabel2.setText("Nombre:");
    jLabel2.setBounds(new Rectangle(39, 15, 66, 23));
    jLabel3.setText("Código Postal:");
    jLabel3.setBounds(new Rectangle(39, 43, 77, 23));
    txtCiudad.setToolTipText("");
    txtCiudad.setText("");
    txtCiudad.setBounds(new Rectangle(112, 68, 256, 24));
    jLabel4.setText("Estado:");
    jLabel4.setBounds(new Rectangle(39, 125, 87, 23));
    jLabel5.setToolTipText("");
    jLabel5.setText("Ciudad:");
    jLabel5.setBounds(new Rectangle(39, 70, 51, 23));
    txtMunicipio.setVerifyInputWhenFocusTarget(true);
    txtMunicipio.setText("");
    txtMunicipio.setBounds(new Rectangle(112, 96, 256, 24));
    pnlCentral.setDebugGraphicsOptions(0);
    pnlCentral.setMaximumSize(new Dimension(32767, 32767));
    pnlCentral.setLayout(null);
    txtNombre.setText("");
    txtNombre.setBounds(new Rectangle(112, 13, 256, 24));
    txtCodigoPostal.setText("");
    txtCodigoPostal.setBounds(new Rectangle(112, 41, 256, 24));
    jTextField1.setText("jTextField1");
    jTextField1.setBounds(new Rectangle(178, 151, 256, 46));
    jLabel6.setText("Municipio:");
    jLabel6.setBounds(new Rectangle(39, 97, 88, 22));
    txtEstado.setText("");
    txtEstado.setBounds(new Rectangle(113, 124, 256, 23));
    jLabel7.setText("Ruta:");
    jLabel7.setBounds(new Rectangle(39, 154, 64, 19));
    txtRuta.setEditable(true);
    txtRuta.setText("");
    txtRuta.setBounds(new Rectangle(113, 151, 255, 25));
    this.getContentPane().add(pnlCentral, java.awt.BorderLayout.CENTER);
    pnlCentral.add(txtNombre, null);
    pnlCentral.add(txtCodigoPostal, null);
    pnlCentral.add(txtCiudad, null);
    pnlCentral.add(txtEstado);
    pnlCentral.add(txtMunicipio, null);
    pnlCentral.add(jLabel5, null);
    pnlCentral.add(jLabel2, null);
    pnlCentral.add(jLabel3, null);
    pnlCentral.add(jLabel6);
    pnlCentral.add(jLabel4, null);
    pnlCentral.add(jLabel7);
    pnlCentral.add(txtRuta);
    this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
    this.getContentPane().add(pnlCatalogo, java.awt.BorderLayout.SOUTH);
    desHabilitarCajas(); limpiarCajas();
  }

  /*
    public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals("accion")) {
        if (evt.getNewValue().equals("add")) {
          this.limpiarCajas();
          this.habilitarCajas();
          this.txtNombre.requestFocus();
        }
        else if (evt.getNewValue().equals("buscar")) {
   DlgBuscarColonia dlg = new DlgBuscarColonia(this, "Buscar colonias", true);
          dlg.setVisible(true);
          if (dlg.colonia != null) {
            coloniaSeleccionada = dlg.colonia;
            this.txtCiudad.setText(coloniaSeleccionada.getCiudad());
            this.txtCodigoPostal.setText(coloniaSeleccionada.getCp());
            this.txtEstado.setText(coloniaSeleccionada.getEstado());
            this.txtNombre.setText(coloniaSeleccionada.getNombre());
          }
        }
        else if (evt.getNewValue().equals("edit")) {
          this.habilitarCajas();
          this.txtNombre.requestFocus();
        }
        else if (evt.getNewValue().equals("delete")) {
          DaoColonias.remove(coloniaSeleccionada.getId());
          this.limpiarCajas();
//        DaoColonias.remove()
//       DaoClientes.remove(cliente.getId());

        }
        else if (evt.getNewValue().equals("ok")) {
          //Revisamos que esten los datos minimos necesarios          this.tosCliente();
          /*     if (! ("".equals(nombre.getText()) || "".equals(txtDomicilio.getText()) ||
        "".equals(txtTelefono.getText()) || "".equals(cmbColonia.getText()) ||
        "".equals(txtCiudad.getText()) || "".equals(txtEstado.getText()))) {
    */
   /*       if (evt.getOldValue().equals("add")) {
            Colonia c = this.setDatosColonia();
            if (!DaoColonias.add(c)) {
              JOptionPane.showMessageDialog(null,
    "No se pudo agregar la colonia, revise los datos introducidos");
              this.pnlOperaciones1.setAccionExitosa(false);
            }
            else {
              this.limpiarCajas();
              this.desHabilitarCajas();
              this.pnlOperaciones1.setAccionExitosa(true);
            }
          }
        }
        else if (evt.getOldValue().equals("edit")) {
          this.setDatosColonia();
          if (!DaoClientesCentral.actualizarCliente(null)) {
            JOptionPane.showMessageDialog(null,
    "No se pudo actualizar los datos del cliente");
            this.pnlOperaciones1.setAccionExitosa(false);
          }
          else {
            this.desHabilitarCajas();
            this.pnlOperaciones1.setAccionExitosa(true);
          }

        }
        else if (evt.getNewValue().equals("cancel")) {
          this.desHabilitarCajas();
          this.limpiarCajas();
        }

      }

    }*/

   /**
    * habilitarCajas
    */
   private void habilitarCajas() {
     txtNombre.setEnabled(true);
     txtCiudad.setEnabled(true);
     txtMunicipio.setEnabled(true);
     txtCodigoPostal.setEnabled(true);
     txtEstado.setEnabled(true);
     txtRuta.setEnabled(true);
   }

  /**
   * setDatosColonia
   */
  private Colonia setDatosColonia() {
    Colonia c = null;
   /* if (!"".equals(txtNombre.getText()) && !"".equals(txtCodigoPostal.getText()) &&
        !"".equals(txtCiudad.getText()) && !"".equals(txtEstado.getText()) &&
        !"".equals(txtMunicipio.getText())) {*/
      c = new Colonia();
      //System.out.println(txtNombre.getText().toUpperCase());
      if(pnlCatalogo.operacion.equals("modificar")){
         c.setId(coloniaSeleccionada.getId());
      }
      c.setNombre(txtNombre.getText().toUpperCase());
      c.setCp(txtCodigoPostal.getText().toUpperCase());
      c.setCiudad(txtCiudad.getText().toUpperCase());
      c.setEstado(txtEstado.getText().toUpperCase());
      c.setMunicipio(txtMunicipio.getText().toUpperCase());
      try {
        c.setRuta(Integer.parseInt(txtRuta.getText()));
      }
      catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this.getRootPane(),com.boutique.engine.impl.AppInstance.nombreNegocio,"El valor de la ruta debe ser numerico",JOptionPane.ERROR_MESSAGE);
        c.setRuta(0);
      }
   // }
    return c;
  }

  private void desHabilitarCajas() {
    txtNombre.setEnabled(false);
    txtCiudad.setEnabled(false);
    txtMunicipio.setEnabled(false);
    txtCodigoPostal.setEnabled(false);
    txtEstado.setEnabled(false);
    txtRuta.setEnabled(false);

  }

  /**
   * limpiarCajas
   */
  private void limpiarCajas() {
    txtNombre.setText("");
    txtCiudad.setText("");
    txtMunicipio.setText("");
    txtCodigoPostal.setText("");
    txtEstado.setText("");
    txtRuta.setText("");
  }

  private class pnlCatalogosOperaciones
      extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton cmdAceptar = new JButton();
    JButton cmdEliminar = new JButton();
    JButton cmdCancelar = new JButton();
    JButton cmdModificar = new JButton();
    JButton cmdAgregar = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton cmdBuscar = new JButton();
    public String operacion;
    public pnlCatalogosOperaciones() {
      try {
        jbInit();
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }

    private void jbInit() throws Exception {
      this.setLayout(flowLayout1);
      cmdAceptar.setText("Aceptar");
      cmdAceptar.addActionListener(new
                                   pnlCatalogosOperaciones_cmdAceptar_actionAdapter(this));
      cmdEliminar.setText("Eliminar");
      cmdEliminar.addActionListener(new
                                    pnlCatalogosOperaciones_cmdEliminar_actionAdapter(this));
      cmdCancelar.setText("Cancelar");
      cmdCancelar.addActionListener(new
                                    pnlCatalogosOperaciones_cmdCancelar_actionAdapter(this));
      cmdModificar.setText("Modificar");
      cmdModificar.addActionListener(new
                                     pnlCatalogosOperaciones_cmdModificar_actionAdapter(this));
      cmdAgregar.setText("Agregar");
      cmdAgregar.addActionListener(new
                                   pnlCatalogosOperaciones_cmdAgregar_actionAdapter(this));
      cmdBuscar.setText("Buscar");
      cmdBuscar.addActionListener(new
                                  pnlCatalogosOperaciones_cmdBuscar_actionAdapter(this));
       this.add(cmdBuscar, null);
       this.add(cmdAgregar, null);
      this.add(cmdModificar, null);
      this.add(cmdEliminar, null);
      this.add(cmdAceptar, null);
      this.add(cmdCancelar, null);



      habilitarBotones();

    }

    void desHabilitarBotones() {
      cmdAgregar.setEnabled(false);
      cmdModificar.setEnabled(false);
      cmdEliminar.setEnabled(false);
      cmdAceptar.setEnabled(true);
      cmdCancelar.setEnabled(true);
      cmdBuscar.setEnabled(false);
    }

    void habilitarBotones() {
      cmdAgregar.setEnabled(true);
      cmdModificar.setEnabled(true);
      cmdEliminar.setEnabled(true);
      cmdBuscar.setEnabled(true);
      cmdAceptar.setEnabled(false);
      cmdCancelar.setEnabled(false);
    }

    public void cmdAgregar_actionPerformed(ActionEvent e) {
      operacion="agregar";
      limpiarCajas();
      habilitarCajas();
      txtNombre.requestFocus();
      this.desHabilitarBotones();
    }

    public void cmdModificar_actionPerformed(ActionEvent e) {
      operacion="modificar";
      habilitarCajas();
      txtNombre.requestFocus();
      this.desHabilitarBotones();
    }

    public void cmdEliminar_actionPerformed(ActionEvent e) {
      int i = JOptionPane.showConfirmDialog(null,"¿Estás seguro que deseas borrar la colonia?",com.boutique.engine.impl.AppInstance.nombreNegocio,JOptionPane.YES_NO_OPTION);
      if(i==JOptionPane.YES_OPTION){
       if( DaoColonias.remove(coloniaSeleccionada.getId())){
         limpiarCajas();
         this.habilitarBotones();
       }else{
         JOptionPane.showMessageDialog(null,com.boutique.engine.impl.AppInstance.nombreNegocio,
                                        "No se pudo eliminar la colonia",JOptionPane.ERROR_MESSAGE);


       }
      }
    }

    public void cmdAceptar_actionPerformed(ActionEvent e) {
      if (operacion.equals("agregar")) {
        Colonia c = setDatosColonia();
        if (!DaoColonias.add(c)) {
          JOptionPane.showMessageDialog(null,
                                        "No se pudo agregar la colonia, revise los datos introducidos");
          // this.pnlOperaciones1.setAccionExitosa(false);
        }
        else {
          JOptionPane.showMessageDialog(null,
                                     "COLONIA AGREGADA");


          limpiarCajas();
          desHabilitarCajas();
          this.habilitarBotones();
          //pnlOperaciones1.setAccionExitosa(true);
        }
      }

      else if (operacion.equals("modificar")) {
       // setDatosColonia();
        if (!DaoColonias.update(setDatosColonia()) ) {
          JOptionPane.showMessageDialog(null,
                                        "No se pudo actualizar los datos de la colonia");

        }
        else {
          desHabilitarCajas();
          this.habilitarBotones();
          JOptionPane.showMessageDialog(null,
                                       "DATOS ACTUALIZADOS");
         limpiarCajas();

          //
        }

      }

    }

    public void cmdCancelar_actionPerformed(ActionEvent e) {
      desHabilitarCajas();
      limpiarCajas();
      this.habilitarBotones();

    }

    public void cmdBuscar_actionPerformed(ActionEvent e) {
   dlg.setLocationRelativeTo(this.getRootPane());
      dlg.setVisible(true);
      if (dlg.colonia != null) {
        coloniaSeleccionada = dlg.colonia;
        txtCiudad.setText(coloniaSeleccionada.getCiudad());
        txtCodigoPostal.setText(coloniaSeleccionada.getCp());
        txtEstado.setText(coloniaSeleccionada.getEstado());
        txtNombre.setText(coloniaSeleccionada.getNombre());
        txtMunicipio.setText(coloniaSeleccionada.getMunicipio());
        txtRuta.setText(String.valueOf(coloniaSeleccionada.getRuta()));
      }
    }
  }

  class pnlCatalogosOperaciones_cmdBuscar_actionAdapter
      implements ActionListener {
    private pnlCatalogosOperaciones adaptee;
    pnlCatalogosOperaciones_cmdBuscar_actionAdapter(pnlCatalogosOperaciones
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.cmdBuscar_actionPerformed(e);
    }
  }

  class pnlCatalogosOperaciones_cmdCancelar_actionAdapter
      implements ActionListener {
    private pnlCatalogosOperaciones adaptee;
    pnlCatalogosOperaciones_cmdCancelar_actionAdapter(pnlCatalogosOperaciones
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.cmdCancelar_actionPerformed(e);
    }
  }

  class pnlCatalogosOperaciones_cmdAceptar_actionAdapter
      implements ActionListener {
    private pnlCatalogosOperaciones adaptee;
    pnlCatalogosOperaciones_cmdAceptar_actionAdapter(pnlCatalogosOperaciones
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.cmdAceptar_actionPerformed(e);
    }
  }

  class pnlCatalogosOperaciones_cmdEliminar_actionAdapter
      implements ActionListener {
    private pnlCatalogosOperaciones adaptee;
    pnlCatalogosOperaciones_cmdEliminar_actionAdapter(pnlCatalogosOperaciones
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.cmdEliminar_actionPerformed(e);
    }
  }

  class pnlCatalogosOperaciones_cmdModificar_actionAdapter
      implements ActionListener {
    private pnlCatalogosOperaciones adaptee;
    pnlCatalogosOperaciones_cmdModificar_actionAdapter(pnlCatalogosOperaciones
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.cmdModificar_actionPerformed(e);
    }
  }

  class pnlCatalogosOperaciones_cmdAgregar_actionAdapter
      implements ActionListener {
    private pnlCatalogosOperaciones adaptee;
    pnlCatalogosOperaciones_cmdAgregar_actionAdapter(pnlCatalogosOperaciones
        adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.cmdAgregar_actionPerformed(e);
    }
  }

}
