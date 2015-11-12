package com.boutique.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.boutique.domain.*;
import com.boutique.engine.impl.*;

/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class FrmAbonoANota
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
SistemaCreditoEngine engine;
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField txtSaldoTotal = new JTextField();
  JButton cmdAbonar = new JButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel1 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel6 = new JLabel();
  JButton cmdCancelar = new JButton();
  JTextField txtSaldoVencido = new JTextField();
  JTextField txtAbono = new JTextField();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  double abono = 0.0;
  DlgPagoRegistrado dlgPagoRegistrado;
  public boolean abonar = false;
  VentaCredito venta;
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JCheckBox chkPagarNotaCompleta = new JCheckBox();
  DlgAplicarDescuento dlgDescuento = new DlgAplicarDescuento(null,
      com.boutique.engine.impl.AppInstance.nombreNegocio, true);

  FrmIndicarTipoPago frm;
  public FrmAbonoANota() {
    try {

      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    jLabel1.setBackground(new Color(210, 218, 255));
    jLabel1.setText("   ");
    jLabel2.setText("   ");
    jLabel4.setText("Abonar:");
    jLabel4.setHorizontalAlignment(SwingConstants.TRAILING);
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 14));
    txtSaldoTotal.setHorizontalAlignment(SwingConstants.LEADING);
    txtSaldoTotal.setText("543.20");
    txtSaldoTotal.setEditable(false);
    txtSaldoTotal.setDoubleBuffered(false);
    txtSaldoTotal.setForeground(Color.black);
    txtSaldoTotal.setFont(new java.awt.Font("Verdana", 0, 18));
    txtSaldoTotal.setEnabled(true);
    cmdAbonar.setText("ABONAR");
    cmdAbonar.addActionListener(new FrmAbonoANota_cmdAbonar_actionAdapter(this));
    jLabel5.setText("Abono a nota");
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setBackground(new Color(255, 95, 201));
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel5.setForeground(Color.white);
    jLabel5.setAlignmentX( (float) 0.0);
    jLabel5.setBorder(BorderFactory.createEtchedBorder());
    jLabel5.setOpaque(true);
    jLabel5.setPreferredSize(new Dimension(102, 21));
    jLabel3.setText("Saldo vencido:");
    jLabel3.setHorizontalTextPosition(SwingConstants.TRAILING);
    jLabel3.setHorizontalAlignment(SwingConstants.TRAILING);
    jLabel3.setFont(new java.awt.Font("Dialog", 0, 14));
    jPanel1.setLayout(gridLayout1);
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(0);
    gridLayout1.setRows(3);
    gridLayout1.setVgap(5);
    jLabel6.setFont(new java.awt.Font("Arial", 0, 14));
    jLabel6.setHorizontalAlignment(SwingConstants.TRAILING);
    jLabel6.setText("Saldo total:");
    cmdCancelar.setActionCommand("jButton1");
    cmdCancelar.setText("CANCELAR");
    cmdCancelar.addActionListener(new FrmAbonoANota_cmdCancelar_actionAdapter(this));
    txtSaldoVencido.setFont(new java.awt.Font("Verdana", 0, 18));
    txtSaldoVencido.setForeground(Color.black);
    txtSaldoVencido.setEditable(false);
    txtSaldoVencido.setText("320.00");
    txtAbono.setFont(new java.awt.Font("Verdana", 0, 18));
    txtAbono.setText("0.0");
    txtAbono.addActionListener(new FrmAbonoANota_txtAbono_actionAdapter(this));
    this.getContentPane().setBackground(Color.white);
    this.setModal(true);
    this.addWindowListener(new FrmAbonoANota_this_windowAdapter(this));
    jPanel1.setBackground(Color.white);
    jPanel1.setAlignmentY( (float) 0.5);
    jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(false);
    jPanel3.setLayout(borderLayout3);
    chkPagarNotaCompleta.setBackground(Color.white);
    chkPagarNotaCompleta.setOpaque(true);
    chkPagarNotaCompleta.setHorizontalAlignment(SwingConstants.CENTER);
    chkPagarNotaCompleta.setText("Pagar nota completa");
    chkPagarNotaCompleta.addMouseListener(new
                                          FrmAbonoANota_chkPagarNotaCompleta_mouseAdapter(this));
    this.getContentPane().add(jLabel1, BorderLayout.WEST);
    this.getContentPane().add(jLabel2, BorderLayout.EAST);
    jPanel1.add(jLabel6, null);
    jPanel1.add(txtSaldoTotal, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(txtSaldoVencido, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(txtAbono, null);
    jPanel3.add(chkPagarNotaCompleta, BorderLayout.NORTH);
    this.getContentPane().add(jLabel5, BorderLayout.NORTH);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(cmdAbonar, null);
    jPanel2.add(cmdCancelar, null);
    this.getContentPane().add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jPanel1, BorderLayout.CENTER);
    this.setUndecorated(true);

    this.pack();
    this.setSize(200, 220);
  }

  public Venta getVenta() {
    return venta;
  }

  public void setVenta(VentaCredito venta) {
    this.venta = venta;

    this.txtSaldoTotal.setText(AppInstance.number.format(venta.saldoActual));
    this.txtSaldoVencido.setText(AppInstance.number.format(venta.saldoVencido));
    this.txtAbono.setText(AppInstance.number.format(venta.saldoVencido));
  }

  void cmdCancelar_actionPerformed(ActionEvent e) {
    this.abonar = false;
    this.setVisible(false);
    this.chkPagarNotaCompleta.setSelected(false);
  }

  @SuppressWarnings("unchecked")
void abonar() {
    if (Double.parseDouble(this.txtAbono.getText().replaceAll(",", "")) >
        Double.parseDouble(this.txtSaldoTotal.getText().replaceAll(",", ""))) {
      JOptionPane.showMessageDialog(this.getRootPane(),
                                    "El abono no puede ser mayor al saldo total de la venta",
                                    "Abono",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }

    cmdAbonar.setEnabled(false);
    //Agregamos el abono
    this.abono = Double.parseDouble(this.txtAbono.getText().replaceAll(",", ""));

    frm = new FrmIndicarTipoPago(2, this.abono, false,engine.agregarPagosAlCorte);
    double saldo = venta.saldoActual;
    frm.setSize(400, 400);
    frm.setLocationRelativeTo(this.getRootPane());
    frm.setVisible(true);
    if (frm.engine.getMontoPendiente() <= 0.05) {
      engine.registrarPagos(frm.engine.
                            getPagos());
      if (frm.engine.getPagos() != null) {

        if ( (venta.saldoActual - abono) <= 0.05) { //SIGNIFICA QUE SE FINIQUITA EL SALDO DE LA DEUDA.. TERMINAMOS LA VENTA
          engine.finalizarVenta();
          //MOSTRAMOS LA LISTA DE PAGOS REALIZADOS CON LO DE LA VENTA FINALIZADA
        }
        dlgPagoRegistrado = new DlgPagoRegistrado(engine.venta.
                                                  getStatus(),
                                                  frm.engine.
                                                  getPagos(),
                                                  frm.engine.
                                                  montoAcumulado);

        dlgPagoRegistrado.setModal(true);
        dlgPagoRegistrado.setSize(500, 250);
        dlgPagoRegistrado.setLocationRelativeTo(this);

        engine.imprimirPagosAbonosaNota(dlgPagoRegistrado.pagosView,
                                        frm.engine.montoAcumulado, saldo);
        dlgPagoRegistrado.setVisible(true);
        abonar = true;
      }
      else {
        JOptionPane.showMessageDialog(this,
                                      "No se pudieron registrar los pagos",
                                      com.boutique.engine.impl.AppInstance.nombreNegocio,
                                      JOptionPane.ERROR_MESSAGE);
      }
      //Registramos los pagos de la nota
      this.cmdAbonar.setEnabled(true);
      this.setVisible(false);
      this.chkPagarNotaCompleta.setSelected(false);

    }
  }

  void cmdAbonar_actionPerformed(ActionEvent e) {
    if (cmdAbonar.isEnabled()) {
      abonar();
    }

  }

  void chkPagarNotaCompleta_mouseClicked(MouseEvent e) {
    if (chkPagarNotaCompleta.isSelected()) {
      this.txtAbono.setText(AppInstance.number.format(venta.saldoActual));
      this.txtAbono.setEditable(false);
/*      Calendar calFechaNota = Calendar.getInstance();
      Calendar calFechaHoy = Calendar.getInstance();
      calFechaHoy.setTime(DaoSource.getFechaActual());
      calFechaNota.setTime(engine.venta.getFecha());
      int diaActual = calFechaHoy.get(Calendar.DAY_OF_YEAR);
      int diaNota = calFechaNota.get(Calendar.DAY_OF_YEAR);*/
/*      if (diaActual - diaNota < 15) {
        //LA NOTA TIENE MENOS DE 15 DIAS
        int i = JOptionPane.showConfirmDialog(this,
                                              "Desea aplicar descuento por pago anticipado",
                                              com.boutique.engine.AppInstance.nombreNegocio,
                                              JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
          //CALCULAMOS CUAL SERIA EL NUEVO TOTAL Y LO MOSTRAMOS EN LA PANTALLA
          dlgDescuento.model.removeAllElements();
          for (i = 0; i < 21; i++) {
            dlgDescuento.model.addElement(i);
          }
          dlgDescuento.pack();
          dlgDescuento.setSize(230,180);
          dlgDescuento.setLocationRelativeTo(this);
          dlgDescuento.setVisible(true);

          DlgDescuentoPagoAnticipado nDlg = new DlgDescuentoPagoAnticipado(null,"Descuento pago anticipado",true,dlgDescuento.descuento,engine);
          nDlg.pack();
          nDlg.setSize(343,248);
          nDlg.setLocationRelativeTo(this);
          nDlg.setVisible(true);
          //TENEMOS EL PORCENTAJE DE DESCUENTO.. CALCULAMOS EL NUEVO SALDO.
          //MOSTRAMOS EL FORMULARIO CON EL PRECIO ACTUAL, EL DESCUENTO Y EL NUEVO SALDO
          this.abono = Double.parseDouble(txtAbono.getText().replaceAll(",", ""));

        }

      }
*/
    }
    else {
      this.txtAbono.setEditable(true);
    }

  }

  void this_windowOpened(WindowEvent e) {
  }

  public void txtAbono_actionPerformed(ActionEvent e) {
    abonar();
  }

}

class FrmAbonoANota_txtAbono_actionAdapter
    implements ActionListener {
  private FrmAbonoANota adaptee;
  FrmAbonoANota_txtAbono_actionAdapter(FrmAbonoANota adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.txtAbono_actionPerformed(e);
  }
}

class FrmAbonoANota_cmdCancelar_actionAdapter
    implements java.awt.event.ActionListener {
  FrmAbonoANota adaptee;

  FrmAbonoANota_cmdCancelar_actionAdapter(FrmAbonoANota adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class FrmAbonoANota_cmdAbonar_actionAdapter
    implements java.awt.event.ActionListener {
  FrmAbonoANota adaptee;

  FrmAbonoANota_cmdAbonar_actionAdapter(FrmAbonoANota adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAbonar_actionPerformed(e);
  }
}

class FrmAbonoANota_chkPagarNotaCompleta_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FrmAbonoANota adaptee;

  FrmAbonoANota_chkPagarNotaCompleta_mouseAdapter(FrmAbonoANota adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.chkPagarNotaCompleta_mouseClicked(e);
  }
}

class FrmAbonoANota_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  FrmAbonoANota adaptee;

  FrmAbonoANota_this_windowAdapter(FrmAbonoANota adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
