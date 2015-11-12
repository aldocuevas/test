package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.boutique.domain.*;
import com.boutique.dao.DaoVales;
import com.boutique.engine.impl.AppInstance;

import java.text.*;

public class DlgValeCompra
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Vale vale;
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel2 = new JLabel();
  JTextField txtMontoaUtilizar = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField txtMonto = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField txtNoVale = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JPanel jPanel2 = new JPanel();
  JButton cmdUsarVale = new JButton();
  double monto;
  public DlgValeCompra(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
      jbInit();
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgValeCompra(double monto) {
    this(new Frame(), "VALE DE COMPRA", false);
    this.monto = monto;

  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    jPanel1.setLayout(gridLayout1);
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel2.setText("Indica el número de vale:");
    txtMontoaUtilizar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 24));
    txtMontoaUtilizar.setText("");
    txtMontoaUtilizar.addActionListener(new
                                        DlgValeCompra_txtMontoaUtilizar_actionAdapter(this));
    jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel3.setText("Monto:");
    txtMonto.setEnabled(false);
    txtMonto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 24));
    txtMonto.setDisabledTextColor(Color.black);
    txtMonto.setText("");
    jLabel4.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
    jLabel4.setText("Monto a utilizar:");
    txtNoVale.setFont(new java.awt.Font("Dialog", Font.PLAIN, 24));
    txtNoVale.setText("");
    txtNoVale.addActionListener(new DlgValeCompra_txtNoVale_actionAdapter(this));
    gridLayout1.setColumns(1);
    gridLayout1.setRows(6);
    jLabel1.setText("          ");
    jLabel5.setText("           ");
    cmdUsarVale.setText("USAR VALE");
    cmdUsarVale.addActionListener(new DlgValeCompra_cmdUsarVale_actionAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jPanel1, java.awt.BorderLayout.CENTER);
    jPanel1.add(jLabel2);
    jPanel1.add(txtNoVale);
    jPanel1.add(jLabel3);
    jPanel1.add(txtMonto);
    jPanel1.add(jLabel4);
    jPanel1.add(txtMontoaUtilizar);
    panel1.add(jLabel1, java.awt.BorderLayout.WEST);
    panel1.add(jLabel5, java.awt.BorderLayout.EAST);
    this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
    jPanel2.add(cmdUsarVale);

  }

  public void txtNoVale_actionPerformed(ActionEvent e) {
    try {
      int noVale = Integer.parseInt(txtNoVale.getText());
      vale = DaoVales.findVale(noVale);
      if (vale != null) {
        if (vale.getUtilizado() == 0) {
          //TENEMOS EL VALE.. PONEMOS LOS DATOS EN LAS CAJAS DE TEXTO
          this.txtMonto.setText(AppInstance.number.format(vale.getMonto()));
          if (vale.getMonto() <= monto) {

            this.txtMontoaUtilizar.setText(AppInstance.number.format(vale.
                getMonto()));
          }
          else {
            this.txtMontoaUtilizar.setText(AppInstance.number.format(monto));
          }
          this.txtMontoaUtilizar.requestFocus();
        }
        else {
          JOptionPane.showMessageDialog(this, "El vale ya fue utilizado",
                                        com.boutique.engine.impl.AppInstance.nombreNegocio,
                                        JOptionPane.ERROR_MESSAGE);
        }
      }
      else {
        JOptionPane.showMessageDialog(this, "El vale no ha sido encontrado",
                                      com.boutique.engine.impl.AppInstance.nombreNegocio,
                                      JOptionPane.WARNING_MESSAGE);
      }
    }
    catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Indique un número valido",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
    }
  }

  void usarVale() {

    double montoaUtilizar = 0.0;
    try {
      montoaUtilizar = AppInstance.number.parse(txtMontoaUtilizar.getText()).
          doubleValue();
      vale.setMontoUtilizado(montoaUtilizar);
    }
    catch (ParseException ex) {
      JOptionPane.showMessageDialog(this, "Cantidad incorrecta!!",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
      return;
    }
    //REVISAMOS SI EL MONTO DEL VALE ES MAYOR A LO QUE DEBEMOS DE USAR
    int i = JOptionPane.NO_OPTION;
    if (montoaUtilizar > vale.getMonto()) {
      JOptionPane.showMessageDialog(this,
                                    "El monto indicado es mayor al monto del vale!!",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio,
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (montoaUtilizar > monto) {
      JOptionPane.showMessageDialog(this,
                                    "El monto indicado es mayor al monto a cubrir!!!!",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio,
                                    JOptionPane.ERROR_MESSAGE);
      return;

    }
    //  if (montoaUtilizar<monto) {
    //    i = JOptionPane.showConfirmDialog(this, "El monto indicado es menor al monto a cubrir.\r ¿Deseas modificar el monto del vale y generar uno nuevo con el resto?",
    //                                     com.boutique.engine.AppInstance.nombreNegocio,
    ///                                      JOptionPane.YES_NO_OPTION);
    //REVISAMOS SI EL MONTO DEL VALE NO SUPERA AL MONTO QUE HAY QUE CUBRIR

    // }
    if (montoaUtilizar < vale.getMonto()) {
      i = JOptionPane.showConfirmDialog(this, "El monto indicado es menor al monto del vale, ¿Desea generar otro vale con el resto?",
                                       com.boutique.engine.impl.AppInstance.nombreNegocio,
                                        JOptionPane.YES_NO_CANCEL_OPTION);
    }
    if (i == JOptionPane.YES_OPTION) {
      JOptionPane.showMessageDialog(this,
                                    "A continuacion se mostrara el nuevo vale generado",
                                    com.boutique.engine.impl.AppInstance.nombreNegocio,
                                    JOptionPane.INFORMATION_MESSAGE); ;
      Vale nuevoVale = DaoVales.modificaryCrearNuevoVale(vale.getId(),
          montoaUtilizar,AppInstance.terminal().getId());
      if (nuevoVale != null) { //MODIIFCAMOS EL VALE, CAMBIAMOS SU MONTO
        DlgMostrarVale dlg = new DlgMostrarVale(); //COPIAMOS SU IDPAGO Y GENERAMOS EL NUEVO VALE CON LA DIFERENCIA

        dlg.lblMonto.setText("$" +
                             AppInstance.number.format(nuevoVale.getMonto()));
        dlg.lblNoVale.setText(String.valueOf(nuevoVale.getId()));
        dlg.setSize(193, 235);
        dlg.setLocationRelativeTo(this.getRootPane());
        dlg.setModal(true);

        com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.
            PrintTask(4, nuevoVale); //Venta de contado
        Thread t = new Thread(obj);
        t.start();

        dlg.setVisible(true);

        //MOSTRAMOS LA INFORMACION DEL NUEVO VALE
      }

    }
    else {
      this.txtMontoaUtilizar.setText(txtMonto.getText());
    }
    this.setVisible(false);
  }

  public void cmdUsarVale_actionPerformed(ActionEvent e) {
    usarVale();

  }

  public void txtMontoaUtilizar_actionPerformed(ActionEvent e) {
    usarVale();

  }
}

class DlgValeCompra_txtMontoaUtilizar_actionAdapter
    implements ActionListener {
  private DlgValeCompra adaptee;
  DlgValeCompra_txtMontoaUtilizar_actionAdapter(DlgValeCompra adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtMontoaUtilizar_actionPerformed(e);
  }
}

class DlgValeCompra_cmdUsarVale_actionAdapter
    implements ActionListener {
  private DlgValeCompra adaptee;
  DlgValeCompra_cmdUsarVale_actionAdapter(DlgValeCompra adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdUsarVale_actionPerformed(e);
  }
}

class DlgValeCompra_txtNoVale_actionAdapter
    implements ActionListener {
  private DlgValeCompra adaptee;
  DlgValeCompra_txtNoVale_actionAdapter(DlgValeCompra adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtNoVale_actionPerformed(e);
  }
}
