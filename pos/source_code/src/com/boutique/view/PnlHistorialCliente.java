package com.boutique.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;

import java.util.Calendar;

/**
 * <p>Title: boutique management</p>
 * <p>Description: Sistema de administracion de boitiques</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SESTO</p>
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */

public class PnlHistorialCliente
    extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/* FrmAbonoANota frmAbonoNota = new FrmAbonoANota();
   FrmAbonoaMasVencido frmMasVencido = new FrmAbonoaMasVencido();
   */
//  FrmAbonoANota frmAbonoNota = null;
//  FrmAbonoaMasVencido frmMasVencido = null;
  Calendar cal = Calendar.getInstance();
  HistorialClienteEngine engine;
  double saldoVencido;
  int totalNotas;
  double totalCompras;
  double saldoTotal;
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel2 = new JLabel();
  JTextField txtSaldoTotal = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField txtLimiteCredito = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField txtSaldoVencido = new JTextField();
  JScrollPane scrollEdoCuenta = new JScrollPane();
  JTable tblEdoCuenta = new JTable();
  ModelHistorialCliente modelHistorialCliente1 = new ModelHistorialCliente();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel5 = new JLabel();
  JTextField txtTelefono = new JTextField();
  JLabel jLabel6 = new JLabel();
  JTextField txtCelular = new JTextField();

  PnlDatosVenta pnlVenta = null;
  JLabel jLabel7 = new JLabel();
  JTextField txtCliente = new JTextField();
  JLabel jLabel8 = new JLabel();
  JTextField txtDomicilio = new JTextField();
  JLabel jLabel9 = new JLabel();
  JTextField txtTipo = new JTextField();
  JLabel jLabel10 = new JLabel();
  JTextField txtComprasRealizadas = new JTextField();
  JLabel jLabel11 = new JLabel();
  JTextField txtCompras = new JTextField();
  JLabel jLabel12 = new JLabel();
  JTextField txtCodCliente = new JTextField();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PnlFotografia pnlFotografia1 = new PnlFotografia();
  public PnlHistorialCliente(PnlDatosVenta pnlVenta) {
    try {
      this.pnlVenta = pnlVenta;
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
public void setHistorialCliente() {
    saldoVencido = 0;
    saldoTotal = 0;
    this.modelHistorialCliente1.clean();
    txtCliente.setText("");
    txtDomicilio.setText("");
    txtTelefono.setText("");
    txtCelular.setText("");
    txtCodCliente.setText("");
    txtTipo.setText("");
    Object[] row = null;

    if (engine.getHistorialCliente() != null) {
      //Si hay ventas.. ponemos los datos en el datamodel
      totalNotas = 0;
      totalCompras = 0.0;
      for (Venta venta : engine.ventasEncontradas) {
        totalNotas++;
        row = new Object[9];
        if (venta instanceof VentaCredito) {
          VentaCredito vc = (VentaCredito) venta;
          row[0] = vc.getId();
          row[1] = AppInstance.idToNombreBoutique.get(vc.getIdBoutique());
          row[2] = AppInstance.number.format(vc.subTotal);
          row[3] = AppInstance.number.format(vc.saldoAbonado);
          row[4] = AppInstance.number.format(vc.saldoActual);
          row[5] = AppInstance.number.format(vc.saldoVencido);
          row[6] = AppInstance.formatoCorto.format(vc.getFechaVencimiento());
          switch (vc.getStatus()) {
            case 0:
              row[7] = "ABIERTA";
              break;
            case 1:
              row[7] = "FINALIZADA";
              break;
            case 2:
              row[7] = "CANCELADA";
              break;
          }

          row[8] = "CREDITO";
          this.modelHistorialCliente1.data.add(row);
          saldoVencido += vc.saldoVencido;
          saldoTotal += vc.saldoActual;
          totalCompras += vc.saldoAbonado + vc.saldoActual;
        }
        else if (venta instanceof VentaApartado) {

          VentaApartado vc = (VentaApartado) venta;
          row[0] = vc.getId();
          row[1] = AppInstance.idToNombreBoutique.get(vc.getIdBoutique());

          row[2] = AppInstance.number.format(vc.getTotal());
          row[3] = AppInstance.number.format(vc.getAbonado());
          row[4] = AppInstance.number.format(vc.getMontoPendiente());
          row[5] = AppInstance.number.format(0);
          cal.setTime(vc.getFecha());
          cal.add(Calendar.DAY_OF_MONTH, 30);
          row[6] = AppInstance.formatoCorto.format(cal.getTime());
          switch (vc.getStatus()) {
            case 0:
              row[7] = "ABIERTA";
              break;
            case 1:
              row[7] = "FINALIZADA";
              break;
            case 2:
              row[7] = "CANCELADA";
              break;
          }

          row[8] = "APARTADO";
          this.modelHistorialCliente1.data.add(row);
          //   saldoVencido += vc.saldoVencido;
          saldoTotal += vc.getMontoPendiente();
          totalCompras += vc.getTotal();

        }
        else if (venta instanceof Venta) {
          Venta vc = (Venta) venta;
          row[0] = vc.getId();
          row[1] = AppInstance.idToNombreBoutique.get(vc.getIdBoutique());
          row[2] = AppInstance.number.format(vc.getAbonado());
          row[3] = AppInstance.number.format(vc.getAbonado());
          row[4] = AppInstance.number.format(0);
          row[5] = AppInstance.number.format(0);
          row[6] = "N/A";
          switch (vc.getStatus()) {
            case 0:
              row[7] = "ABIERTA";
              break;
            case 1:
              row[7] = "FINALIZADA";
              break;
            case 2:
              row[7] = "CANCELADA";
              break;
          }

          row[8] = "CONTADO";
          this.modelHistorialCliente1.data.add(row);
          totalCompras += vc.getAbonado();
        }

      }
    }
    this.pnlFotografia1.setImagen(engine.cliente.getFotografia());
    this.txtLimiteCredito.setText(AppInstance.number.format(engine.cliente.
        getLimiteCredito()));
    this.modelHistorialCliente1.fireTableDataChanged();
    this.txtSaldoVencido.setText(AppInstance.number.format(saldoVencido));
    this.txtSaldoTotal.setText(AppInstance.number.format(saldoTotal));
    this.txtCliente.setText(engine.cliente.getNombre() + " " +
                            engine.cliente.getApellidoPaterno() + " " +
                            engine.cliente.getApellidoMaterno());
    int ruta = com.boutique.dao.DaoColonias.findRutaByNombre(engine.cliente.
        getColonia());

    this.txtDomicilio.setText(engine.cliente.getCalle() + " " +
                              engine.cliente.getNumero() +
                              ( (engine.cliente.getNumeroInterior() == null ||
                                 engine.cliente.getNumeroInterior().equals("")) ?
                               "" :
                               " Int. " + engine.cliente.getNumeroInterior()) +
                              " Col." + engine.cliente.getColonia() + " R" +
                              ruta);

    this.txtTelefono.setText(engine.cliente.getTelefono());
    this.txtCelular.setText(engine.cliente.getCelular());
    this.txtCodCliente.setText(String.valueOf(engine.cliente.getId()));
    this.txtTipo.setText(engine.cliente.getTieneCredito());
    this.txtComprasRealizadas.setText(String.valueOf(totalNotas));
    this.txtCompras.setText(AppInstance.number.format(totalCompras));

    pnlVenta.limpiarDatos();

  }

  void jbInit() throws Exception {
    jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Historial del cliente");
    this.setLayout(null);
    jPanel1.setLayout(borderLayout2);
    jPanel2.setLayout(gridLayout1);
    jLabel2.setText("Limite credito:");
    jLabel3.setText("Saldo vencido:");
    txtLimiteCredito.setToolTipText("");
    txtLimiteCredito.setSelectionStart(11);
    txtLimiteCredito.setText("");
    jLabel4.setToolTipText("");
    jLabel4.setText("Saldo total:");
    gridLayout1.setColumns(3);
    gridLayout1.setRows(2);
    txtSaldoTotal.setText("");
    txtSaldoVencido.setForeground(Color.red);
    txtSaldoVencido.setCaretColor(Color.red);
    txtSaldoVencido.setText("");
    tblEdoCuenta.setModel(modelHistorialCliente1);
    tblEdoCuenta.addMouseListener(new
                                  PnlHistorialCliente_tblEdoCuenta_mouseAdapter(this));
    scrollEdoCuenta.setBounds(new Rectangle(1, 145, 712, 100));
    jPanel1.setMinimumSize(new Dimension(462, 200));
    jPanel1.setPreferredSize(new Dimension(498, 200));
    jPanel1.setBounds(new Rectangle(10, 10, 581, 120));
    jPanel3.setLayout(gridBagLayout1);
    jLabel5.setToolTipText("");
    jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel5.setText("Telefono:");
    txtTelefono.setText("");
    jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel6.setText("Celular:");
    txtCelular.setText("");
    jLabel7.setToolTipText("");
    jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel7.setText("Cliente:");
    txtCliente.setText("");
    jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel8.setHorizontalTextPosition(SwingConstants.RIGHT);
    jLabel8.setText("Domicilio:");
    txtDomicilio.setText("");
    jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel9.setHorizontalTextPosition(SwingConstants.RIGHT);
    jLabel9.setText("Cod. cliente:");
    txtTipo.setText("");
    jPanel3.setMaximumSize(new Dimension(32767, 70));
    jPanel3.setMinimumSize(new Dimension(462, 70));
    jPanel3.setPreferredSize(new Dimension(498, 70));
    jLabel10.setText("Total de compras:");
    txtComprasRealizadas.setText("");
    jLabel11.setText("Compras realizadas:");
    txtCompras.setText("");
    jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel12.setText("Tipo:");
    txtCodCliente.setText("");
    pnlFotografia1.setMinimumSize(new Dimension(107, 114));
    pnlFotografia1.setPreferredSize(new Dimension(107, 114));
    pnlFotografia1.setBounds(new Rectangle(600, 4, 106, 120));
    jPanel2.add(jLabel2, null);
    jPanel2.add(jLabel3, null);
    jPanel2.add(jLabel4, null);
    jPanel2.add(jLabel11);
    jPanel2.add(jLabel10);
    jPanel2.add(txtLimiteCredito, null);
    jPanel2.add(txtSaldoVencido, null);
    jPanel2.add(txtSaldoTotal, null);
    jPanel2.add(txtComprasRealizadas);
    jPanel2.add(txtCompras);
    this.add(scrollEdoCuenta, null);
    scrollEdoCuenta.getViewport().add(tblEdoCuenta, null);
    this.add(jPanel1, null);
    this.add(pnlFotografia1);
    jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);
    jPanel1.add(jPanel2, java.awt.BorderLayout.SOUTH);
    jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
    jPanel3.add(txtCodCliente, new GridBagConstraints(5, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 5), 90, 6));
    jPanel3.add(txtDomicilio, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 90, 6));
    jPanel3.add(txtCelular, new GridBagConstraints(5, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 5), 90, 6));
    jPanel3.add(jLabel7, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 13, 12));
    jPanel3.add(jLabel8, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 3, 12));
    jPanel3.add(jLabel5, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 10, 12));
    jPanel3.add(jLabel12, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.WEST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(0, 0, 0, 0), 33, 12));
    jPanel3.add(txtCliente, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 237, 6));
    jPanel3.add(txtTipo, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(0, 0, 0, 2), 90, 6));
    jPanel3.add(jLabel6, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 7, 12));
    jPanel3.add(jLabel9, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.EAST,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 3), 4, 12));
    jPanel3.add(txtTelefono, new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 1), 90, 6));
  }

  public void tblEdoCuenta_mouseClicked(MouseEvent e) {
    this.setCursor(AppInstance.waitCursor);
    if (tblEdoCuenta.getSelectedRow() >= 0) {
      //Mostramos los datos de la venta completos

      Venta venta = engine.seleccionarVenta(tblEdoCuenta.getSelectedRow());

      PnlDatosVenta.modelVentaArticulos1.data = engine.productosView;
      PnlDatosVenta.modelVentaArticulos1.fireTableDataChanged();
      PnlDatosVenta.modelPagosRealizados1.data = engine.pagosView;
      PnlDatosVenta.modelPagosRealizados1.fireTableDataChanged();
      PnlDatosVenta.modelPagosCredito1.data = engine.intervaloPagos;
      PnlDatosVenta.modelPagosCredito1.fireTableDataChanged();
      pnlVenta.txtVendedor.setText(AppInstance.idToNombreUsuario.get(venta.
          getIdVendedor()).toString());
      pnlVenta.txtTotal.setText(AppInstance.number.format(venta.getTotal()));
      pnlVenta.txtFecha.setText(AppInstance.formatoLargo.format(venta.
          getFecha()));
      pnlVenta.txtNoVenta.setText(String.valueOf(venta.getId()));
      pnlVenta.txtBoutique.setText(AppInstance.idToNombreBoutique.get(venta.
          getIdBoutique()).toString());

      if (venta instanceof VentaCredito) {
        VentaCredito vc = (VentaCredito) venta;
        pnlVenta.txtSubtotal.setText(AppInstance.number.format(vc.subTotal));
        pnlVenta.txtAnticipo.setText(AppInstance.number.format(vc.getAnticipo()));
      }
      else {
        pnlVenta.txtSubtotal.setText(AppInstance.number.format(venta.getTotal()));
        pnlVenta.txtAnticipo.setText(AppInstance.number.format(0));
      }
      //Habilitamos el boton de abonar a nota
    }
    this.setCursor(AppInstance.defCursor);
  }
}

class PnlHistorialCliente_tblEdoCuenta_mouseAdapter
    extends MouseAdapter {
  private PnlHistorialCliente adaptee;
  PnlHistorialCliente_tblEdoCuenta_mouseAdapter(PnlHistorialCliente adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblEdoCuenta_mouseClicked(e);
  }
}
