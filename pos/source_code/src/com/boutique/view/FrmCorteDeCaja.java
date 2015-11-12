package com.boutique.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import com.boutique.engine.impl.AppInstance;
import com.boutique.dao.DaoSource;

public class FrmCorteDeCaja
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public FrmCorteDeCaja() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    getContentPane().setLayout(null);
    jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
    jLabel1.setText("CORTE DE CAJA");
    jLabel1.setBounds(new Rectangle(65, 9, 158, 32));
    lblVendedor.setText("jLabel3");
    lblVendedor.setBounds(new Rectangle(64, 66, 234, 28));
    jLabel3.setText("Total Ventas de contado:");
    jLabel3.setBounds(new Rectangle(7, 94, 127, 21));
    jLabel5.setToolTipText("");
    jLabel5.setText("Efectivo:");
    jLabel5.setBounds(new Rectangle(7, 119, 133, 21));
    jLabel6.setText("Tarjeta:");
    jLabel6.setBounds(new Rectangle(7, 143, 127, 21));
    lbltPF.setText("Vales:");
    lbltPF.setBounds(new Rectangle(7, 195, 127, 21));
    jLabel8.setText("Efectivo");
    jLabel8.setBounds(new Rectangle(7, 248, 128, 21));
    jLabel9.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
    jLabel9.setText("Tarjeta:");
    jLabel9.setBounds(new Rectangle(8, 278, 130, 21));
    jLabel10.setText("OBSERVACIONES Y DESCRIPCION DE SALIDAS:");
    jLabel10.setBounds(new Rectangle(23, 607, 253, 19));
    txtDineroRecibido.setEditable(false);
    txtDineroRecibido.setBounds(new Rectangle(140, 96, 134, 21));
    txtVentasContado.setEditable(false);
    txtVentasContado.setText("");
    txtVentasContado.setBounds(new Rectangle(140, 119, 134, 22));
    txtVentasTarjeta.setEditable(false);
    txtVentasTarjeta.setText("");
    txtCreditos.setEditable(false);
    txtCreditos.setText("");
    txtCreditos.setBounds(new Rectangle(140, 193, 134, 24));
    txtSalidas.setEditable(true);
    txtSalidas.setText("");
    txtSalidas.setBounds(new Rectangle(140, 245, 134, 24));
    txtTotal.setEditable(false);
    txtTotal.setText("");
    txtTotal.setBounds(new Rectangle(140, 271, 134, 24));
    txtObservaciones.setBorder(BorderFactory.createEtchedBorder());
    txtObservaciones.setText("");
    txtObservaciones.setBounds(new Rectangle(23, 630, 260, 55));
    cmdRealizarCorte.setBounds(new Rectangle(24, 700, 122, 27));
    cmdRealizarCorte.setToolTipText("");
    cmdRealizarCorte.setText("REALIZAR CORTE");
    cmdCancelar.setBounds(new Rectangle(156, 700, 120, 27));
    cmdCancelar.setToolTipText("");
    cmdCancelar.setText("CANCELAR");
    jLabel4.setText("Vendedor:");
    jLabel4.setBounds(new Rectangle(7, 67, 73, 24));
    lblFechayHora.setText("23/Nov/2007 34:34:44");
    lblFechayHora.setBounds(new Rectangle(137, 43, 146, 24));
    this.addWindowListener(new FrmCorteDeCaja_this_windowAdapter(this));
    jLabel7.setToolTipText("");
    jLabel7.setText("Total Ant/Abonos/PF");
    jLabel7.setBounds(new Rectangle(7, 225, 133, 15));
    txtValesEmitidos.setText("");
    txtValesEmitidos.setBounds(new Rectangle(140, 297, 134, 24));
    jLabel12.setText("Cheques:");
    jLabel12.setBounds(new Rectangle(7, 308, 115, 15));
    txtMontoValesEmitidos.setText("");
    txtMontoValesEmitidos.setBounds(new Rectangle(140, 323, 134, 24));
    jLabel13.setText("Vales:");
    jLabel13.setBounds(new Rectangle(7, 334, 118, 18));
    txtVentasPorVales.setEditable(false);
    txtVentasPorVales.setText("");
    txtVentasPorVales.setBounds(new Rectangle(140, 220, 134, 23));
    jLabel14.setText("Dinero recibido:");
    jLabel14.setBounds(new Rectangle(7, 374, 113, 15));
    txtVentasCredito.setText("");
    txtVentasCredito.setBounds(new Rectangle(140, 349, 134, 24));
    jLabel15.setText("Cheques:");
    jLabel15.setBounds(new Rectangle(7, 173, 75, 15));
    txtTotalCheques.setEditable(false);
    txtTotalCheques.setText("");
    txtTotalCheques.setBounds(new Rectangle(140, 167, 134, 24));
    jLabel11.setText("Total efectivo:");
    jLabel11.setBounds(new Rectangle(8, 401, 108, 15));
    jLabel16.setText("Total tarjeta:");
    jLabel16.setBounds(new Rectangle(8, 425, 106, 15));
    jLabel17.setText("Total cheques:");
    jLabel17.setBounds(new Rectangle(8, 450, 102, 15));
    jLabel18.setText("Total vales:");
    jLabel18.setBounds(new Rectangle(8, 474, 120, 15));
    jLabel19.setText("TOTAL EN CAJA:");
    jLabel19.setBounds(new Rectangle(8, 518, 118, 15));
    jLabel20.setText("Vales emitidos:");
    jLabel20.setBounds(new Rectangle(8, 542, 100, 15));
    jLabel21.setText("Total en vales emitidos:");
    jLabel21.setBounds(new Rectangle(8, 567, 125, 15));
    jLabel22.setText("Ventas a credito (sin anticipos)");
    jLabel22.setBounds(new Rectangle(8, 591, 163, 15));
    jLabel23.setText("Salidas:");
    jLabel23.setBounds(new Rectangle(9, 492, 40, 15));
    jTextField1.setText("jTextField1");
    jTextField1.setBounds(new Rectangle(140, 375, 134, 24));
    jTextField2.setText("jTextField2");
    jTextField2.setBounds(new Rectangle(140, 401, 134, 24));
    jTextField3.setText("jTextField3");
    jTextField3.setBounds(new Rectangle(140, 427, 134, 24));
    jTextField4.setText("jTextField4");
    jTextField4.setBounds(new Rectangle(140, 453, 134, 24));
    jTextField5.setText("jTextField5");
    jTextField5.setBounds(new Rectangle(140, 480, 134, 24));
    jTextField6.setText("jTextField6");
    jTextField6.setBounds(new Rectangle(140, 506, 134, 24));
    jTextField7.setText("jTextField7");
    jTextField7.setBounds(new Rectangle(140, 532, 134, 24));
    jTextField8.setText("jTextField8");
    jTextField8.setBounds(new Rectangle(140, 558, 134, 24));
    jTextField9.setText("jTextField9");
    jTextField9.setBounds(new Rectangle(140, 584, 134, 24));
    jPanel1.setBounds(new Rectangle(0, 0, 10, 10));
    this.getContentPane().add(jLabel2);
    this.getContentPane().add(jLabel4);
    this.getContentPane().add(jLabel3);
    this.getContentPane().add(jLabel5);
    this.getContentPane().add(jLabel6);
    this.getContentPane().add(lblFechayHora);
    this.getContentPane().add(txtDineroRecibido);
    this.getContentPane().add(jLabel1);
    this.getContentPane().add(lblVendedor);
    this.getContentPane().add(txtVentasTarjeta);
    this.getContentPane().add(txtVentasContado);
    this.getContentPane().add(txtCreditos);
    this.getContentPane().add(lbltPF);
    this.getContentPane().add(jLabel7);
    this.getContentPane().add(txtVentasPorVales);
    this.getContentPane().add(jLabel8);
    this.getContentPane().add(txtSalidas);
    this.getContentPane().add(jLabel9);
    this.getContentPane().add(txtTotal);
    this.getContentPane().add(jLabel12);
    this.getContentPane().add(txtValesEmitidos);
    this.getContentPane().add(txtMontoValesEmitidos);
    this.getContentPane().add(jLabel13);
    this.getContentPane().add(txtTotalCheques);
    this.getContentPane().add(jLabel15);
    this.getContentPane().add(cmdCancelar);
    this.getContentPane().add(cmdRealizarCorte);
    this.getContentPane().add(txtObservaciones);
    this.getContentPane().add(jLabel10);
    this.getContentPane().add(jLabel11);
    this.getContentPane().add(jLabel16);
    this.getContentPane().add(jLabel17);
    this.getContentPane().add(jLabel18);
    this.getContentPane().add(jLabel14);
    this.getContentPane().add(jLabel21);
    this.getContentPane().add(jLabel19);
    this.getContentPane().add(jLabel20);
    this.getContentPane().add(jLabel22);
    this.getContentPane().add(jLabel23);
    this.getContentPane().add(txtVentasCredito);
    this.getContentPane().add(jTextField2);
    this.getContentPane().add(jTextField3);
    this.getContentPane().add(jTextField4);
    this.getContentPane().add(jTextField5);
    this.getContentPane().add(jTextField6);
    this.getContentPane().add(jTextField7);
    this.getContentPane().add(jTextField8);
    this.getContentPane().add(jTextField9);
    this.getContentPane().add(jTextField1);
    this.getContentPane().add(jPanel1);
    jLabel2.setText("Fecha y hora:");
    jLabel2.setBounds(new Rectangle(7, 43, 75, 24));
  }

  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel lblFechayHora = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel lblVendedor = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel lbltPF = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JTextField txtDineroRecibido = new JTextField();
  JTextField txtVentasContado = new JTextField();
  JTextField txtVentasTarjeta = new JTextField();
  JTextField txtCreditos = new JTextField();
  JTextField txtSalidas = new JTextField();
  JTextField txtTotal = new JTextField();
  JTextArea txtObservaciones = new JTextArea();
  JButton cmdRealizarCorte = new JButton();
  JButton cmdCancelar = new JButton();
  JLabel jLabel7 = new JLabel();
  JTextField txtValesEmitidos = new JTextField();
  JLabel jLabel12 = new JLabel();
  JTextField txtMontoValesEmitidos = new JTextField();
  JLabel jLabel13 = new JLabel();
  JTextField txtVentasPorVales = new JTextField();
  JLabel jLabel14 = new JLabel();
  JTextField txtVentasCredito = new JTextField();
  JLabel jLabel15 = new JLabel();
  JTextField txtTotalCheques = new JTextField();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JLabel jLabel19 = new JLabel();
  JLabel jLabel20 = new JLabel();
  JLabel jLabel21 = new JLabel();
  JLabel jLabel22 = new JLabel();
  JLabel jLabel23 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JPanel jPanel1 = new JPanel();
  public void this_windowOpened(WindowEvent e) {
    this.lblFechayHora.setText(AppInstance.formatoLargo.format(DaoSource.
        getFechaActual()));
    this.lblVendedor.setText(AppInstance.usuario().getNombre());
  }
}

class FrmCorteDeCaja_this_windowAdapter
    extends WindowAdapter {
  private FrmCorteDeCaja adaptee;
  FrmCorteDeCaja_this_windowAdapter(FrmCorteDeCaja adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
