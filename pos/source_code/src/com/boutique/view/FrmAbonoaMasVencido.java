package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class FrmAbonoaMasVencido
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
FrmIndicarTipoPago frm;
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
  public boolean abonar = false;
  DlgPagoRegistradoAMasVencido dlg;
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JCheckBox chkPagarSaldoTotal = new JCheckBox();
  double saldoTotal;
  double saldoVencido;

  public FrmAbonoaMasVencido() {
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
    cmdAbonar.addActionListener(new FrmAbonoaMasVencido_cmdAbonar_actionAdapter(this));
    jLabel5.setText("Abono a lo mas vencido");
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
    cmdCancelar.addActionListener(new
                                  FrmAbonoaMasVencido_cmdCancelar_actionAdapter(this));
    txtSaldoVencido.setFont(new java.awt.Font("Verdana", 0, 18));
    txtSaldoVencido.setForeground(Color.black);
    txtSaldoVencido.setEditable(false);
    txtSaldoVencido.setText("320.00");
    txtAbono.setFont(new java.awt.Font("Verdana", 0, 18));
    txtAbono.setText("0.0");
    txtAbono.addActionListener(new FrmAbonoaMasVencido_txtAbono_actionAdapter(this));
    this.getContentPane().setBackground(Color.white);
    this.setModal(true);
    this.addWindowListener(new FrmAbonoaMasVencido_this_windowAdapter(this));
    jPanel1.setBackground(Color.white);
    jPanel1.setAlignmentY( (float) 0.5);
    jPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setOpaque(false);
    jPanel3.setLayout(borderLayout3);
    chkPagarSaldoTotal.setBackground(Color.white);
    chkPagarSaldoTotal.setOpaque(true);
    chkPagarSaldoTotal.setHorizontalAlignment(SwingConstants.CENTER);
    chkPagarSaldoTotal.setText("Pagar saldo total");
    chkPagarSaldoTotal.addMouseListener(new
                                        FrmAbonoaMasVencido_chkPagarNotaCompleta_mouseAdapter(this));
    this.getContentPane().add(jLabel1, BorderLayout.WEST);
    this.getContentPane().add(jLabel2, BorderLayout.EAST);
    jPanel1.add(jLabel6, null);
    jPanel1.add(txtSaldoTotal, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(txtSaldoVencido, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(txtAbono, null);
    jPanel3.add(chkPagarSaldoTotal, BorderLayout.NORTH);
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

  public void setSaldos(double saldoActual, double saldoVencido) {
    this.saldoTotal = saldoActual;
    this.saldoVencido=saldoVencido;
    this.txtSaldoTotal.setText(AppInstance.number.format(saldoActual));
    this.txtSaldoVencido.setText(AppInstance.number.format(saldoVencido));
    this.txtAbono.setText(AppInstance.number.format(saldoVencido));
  }

  void cmdCancelar_actionPerformed(ActionEvent e) {
    this.abonar = false;
    this.setVisible(false);
  }

  @SuppressWarnings("rawtypes")
public void abonar() {
    if (Double.parseDouble(this.txtAbono.getText().replaceAll(",", "")) >
        Double.parseDouble(this.txtSaldoTotal.getText().replaceAll(",", ""))) {
      JOptionPane.showMessageDialog(this.getRootPane(),
                                    "El abono no puede ser mayor al saldo total del cliente",
                                    "Abono",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }

    cmdAbonar.setEnabled(false);
    //Agregamos el abono
    this.abono = Double.parseDouble(this.txtAbono.getText().replaceAll(",", ""));
    frm = new FrmIndicarTipoPago(2, this.abono, false,engine.agregarPagosAlCorte);
    frm.setSize(400, 400);
    frm.setLocationRelativeTo(this.getRootPane());
    frm.setVisible(true);
    if (frm.engine.getMontoPendiente() <= 0.05) {
      //REGISTRAMOS LOS ABONOS EN LAS NOTAS MAS VENCIDAS
      this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
      java.util.List pagosaVentas = engine.abonarMasVencido(frm.engine.
          getPagos(), frm.engine.montoAcumulado); //REGRESA LAS NOTAS CON SUS ABONOS RESPECTIVOS PARA VER COMO QUEDARON LOS PAGOS

      if (pagosaVentas != null) { //INDICA QUE SI SE GENERARON LOS PAGOS
        //REVISAMOS CADA VENTA y MOSTRAMOS LA LISTA DE PAGOS REALIZADOS CON LO DE LA VENTA FINALIZADA
        dlg = new DlgPagoRegistradoAMasVencido(
            pagosaVentas,
            frm.engine.
            montoAcumulado);
        //GENERAMOS EL TICKET DE LOS PAGOS REALIZADOS.
        //PASAMOS EL SALDO ANTERIOR
        //PASAMOS EL NUEVO SALDO QUE SERIA EL ANTERIOR MENOS EL MONTO ACUMULADO
        //OBTENEMOS TAMBIEN LA VISTA DE PAGOS DE DlgPagoRegistradoAMasVencido

        engine.imprimirPagosMasVencidos(this.saldoTotal,this.saldoVencido,frm.engine.montoAcumulado,pagosaVentas);
        this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
        //
        //  engine.imprimirPagos(pagosaVentas, frm.engine.montoAcumulado);
        dlg.setModal(true);
        dlg.setSize(440, 250);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);

      }

    }
    else {
      JOptionPane.showMessageDialog(this,
                                    "No se pudieron registrar los pagos",
                                   com.boutique.engine.impl.AppInstance.nombreNegocio,
                                    JOptionPane.ERROR_MESSAGE);
    }
    //Registramos los pagos de la nota
    abonar = true;
    this.chkPagarSaldoTotal.setSelected(false);
    this.cmdAbonar.setEnabled(true);
    this.setVisible(false);
  }

  void cmdAbonar_actionPerformed(ActionEvent e) {
    if (cmdAbonar.isEnabled()) {
      abonar();

    }
  }

  void chkPagarNotaCompleta_mouseClicked(MouseEvent e) {
    if (chkPagarSaldoTotal.isSelected()) {
      this.txtAbono.setText(AppInstance.number.format(saldoTotal));
      /* this.txtAbono.setEnabled(false);
       //Revisamos si tiene alguna promocion o descuento por pago anticipado la nota.....
       java.util.Date dateL = this.venta.getFechaLimite();
       Date dateH = null;
       try {
         dateL = formato.parse(formato.format(dateL));
         dateH = formato.parse(formato.format(new java.util.Date()));
       }
       catch (ParseException ex) {
       }
       if (dateL.compareTo(dateH) >= 0) {
         JOptionPane.showMessageDialog(this.getRootPane(), new String("Se ha detectado una promocion por pago anticipado, el nuevo saldo total se ajustara en este momento"),
                                       new
                                       String("Descuento por pago anticipado"),
                                       JOptionPane.INFORMATION_MESSAGE);
         double saldoConDescuento = this.venta.getSubTotal() -
             ( (this.venta.getSubTotal() *
                this.venta.getPorcentajeDescuento()) / 100);
         saldoConDescuento -= this.venta.getTotalPagado();
       this.txtSaldoTotal.setText(String.valueOf(Math.ceil(saldoConDescuento)));
       }
       this.txtAbono.setText(this.txtSaldoTotal.getText());
           }
           else {
       this.txtAbono.setEnabled(true);
       */
    }

  }

  void this_windowOpened(WindowEvent e) {
  }

  public void txtAbono_actionPerformed(ActionEvent e) {
    abonar();
  }

}

class FrmAbonoaMasVencido_txtAbono_actionAdapter
    implements ActionListener {
  private FrmAbonoaMasVencido adaptee;
  FrmAbonoaMasVencido_txtAbono_actionAdapter(FrmAbonoaMasVencido adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtAbono_actionPerformed(e);
  }
}

class FrmAbonoaMasVencido_cmdCancelar_actionAdapter
    implements java.awt.event.ActionListener {
  FrmAbonoaMasVencido adaptee;

  FrmAbonoaMasVencido_cmdCancelar_actionAdapter(FrmAbonoaMasVencido adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdCancelar_actionPerformed(e);
  }
}

class FrmAbonoaMasVencido_cmdAbonar_actionAdapter
    implements java.awt.event.ActionListener {
  FrmAbonoaMasVencido adaptee;

  FrmAbonoaMasVencido_cmdAbonar_actionAdapter(FrmAbonoaMasVencido adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAbonar_actionPerformed(e);
  }
}

class FrmAbonoaMasVencido_chkPagarNotaCompleta_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FrmAbonoaMasVencido adaptee;

  FrmAbonoaMasVencido_chkPagarNotaCompleta_mouseAdapter(FrmAbonoaMasVencido
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.chkPagarNotaCompleta_mouseClicked(e);
  }
}

class FrmAbonoaMasVencido_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  FrmAbonoaMasVencido adaptee;

  FrmAbonoaMasVencido_this_windowAdapter(FrmAbonoaMasVencido adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
