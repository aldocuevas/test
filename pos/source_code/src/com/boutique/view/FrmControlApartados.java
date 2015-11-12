package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.boutique.domain.*;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.BusquedaApartadoEngine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
 
public class FrmControlApartados
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
DlgPagoRegistrado dlgPagoRegistrado;
  FrmIndicarTipoPago frmIndicarTipoPago;
  BusquedaApartadoEngine engine = new BusquedaApartadoEngine();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel pnlNorte = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel pnlEncabezado = new JPanel();
  JPanel pnlDatosCompra = new JPanel();
  JPanel pnlDatosCliente = new JPanel();
  JPanel pnlSur = new JPanel();
  JPanel pnlCentro = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JSplitPane splitPagosProductos = new JSplitPane();
  JPanel pnlProductos = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel pnlPagos = new JPanel();
  JLabel jLabel1 = new JLabel();
  JScrollPane scrollProductos = new JScrollPane();
  JTable tblProductos = new JTable();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel jLabel2 = new JLabel();
  JScrollPane scollPagos = new JScrollPane();
  JTable tblPagos = new JTable();
  JPanel pnlProductosSur = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  JPanel pnlTotaleProductos = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel3 = new JLabel();
  JTextField txtTotalVenta = new JTextField();
  JPanel pnlPagosSur = new JPanel();
  JPanel pnlPagosTotales = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  GridLayout gridLayout2 = new GridLayout();
  JLabel jLabel4 = new JLabel();
  JTextField txtMontoaPagar = new JTextField();
  JLabel jLabel5 = new JLabel();
  JTextField txtMontoPagado = new JTextField();
  JButton cmdAgregarPago = new JButton();
  JLabel jLabel6 = new JLabel();
  JLabel lblFechaCompra = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel lblNoVenta = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JTextField txtTelefono = new JTextField();
  JLabel jLabel12 = new JLabel();
  JTextField txtDomicilio = new JTextField();
  JLabel jLabel13 = new JLabel();
  JTextField txtNombreCliente = new JTextField();
  JLabel lblVendedor = new JLabel();
  DlgBuscarApartados dlg = new DlgBuscarApartados();
  JLabel jLabel7 = new JLabel();
  BorderLayout borderLayout8 = new BorderLayout();
  JLabel lblTitulo = new JLabel();
  ModelVentaArticulosApartado modelVentaArticulos1 = new
      ModelVentaArticulosApartado();
  ModelPagosRealizados modelPagosRealizados1 = new ModelPagosRealizados();
  JButton cmdEntregarProducto = new JButton();
  JPanel pnlDatosVenta = new JPanel();
  BorderLayout borderLayout9 = new BorderLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel pnlFoto = new JPanel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout10 = new BorderLayout();
  PnlFotografia pnlFotografia1 = new PnlFotografia();
  JLabel jLabel9 = new JLabel();
  public FrmControlApartados() {
    try {
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    jLabel7.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class.
                                  getResource("img/logo.jpg")));
    setTitle("BUSQUEDA DE APARTADOS");
    getContentPane().setLayout(borderLayout1);
    this.getContentPane().setBackground(Color.white);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.addWindowListener(new FrmControlApartados_this_windowAdapter(this));
    pnlCentro.setBackground(Color.white);
    pnlCentro.setLayout(borderLayout3);
    pnlProductos.setLayout(borderLayout4);
    splitPagosProductos.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPagosProductos.setBackground(Color.white);
    splitPagosProductos.setPreferredSize(new Dimension(200, 300));
    jLabel1.setBackground(Color.white);
    jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
    jLabel1.setOpaque(true);
    jLabel1.setText("Productos registrados:");
    pnlPagos.setLayout(borderLayout5);
    jLabel2.setBackground(Color.white);
    jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 12));
    jLabel2.setOpaque(true);
    jLabel2.setText("Pagos realizados:");
    pnlProductosSur.setLayout(borderLayout6);
    pnlTotaleProductos.setLayout(gridLayout1);
    jLabel3.setBackground(Color.white);
    jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    jLabel3.setOpaque(true);
    jLabel3.setText("Total de la nota:");
    txtTotalVenta.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    txtTotalVenta.setText("0.00");
    txtTotalVenta.setHorizontalAlignment(SwingConstants.TRAILING);
    pnlPagosSur.setLayout(borderLayout7);
    pnlPagosTotales.setLayout(gridLayout2);
    jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    jLabel4.setText("Total abonado:");
    txtMontoaPagar.setEnabled(false);
    txtMontoaPagar.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    txtMontoaPagar.setForeground(Color.red);
    txtMontoaPagar.setOpaque(false);
    txtMontoaPagar.setCaretColor(Color.black);
    txtMontoaPagar.setDisabledTextColor(Color.black);
    txtMontoaPagar.setText("0.00");
    txtMontoaPagar.setHorizontalAlignment(SwingConstants.TRAILING);
    jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    jLabel5.setHorizontalAlignment(SwingConstants.TRAILING);
    jLabel5.setText("Total a pagar:");
    txtMontoPagado.setEnabled(false);
    txtMontoPagado.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
    txtMontoPagado.setOpaque(false);
    txtMontoPagado.setDisabledTextColor(Color.black);
    txtMontoPagado.setText("0.00");
    txtMontoPagado.setHorizontalAlignment(SwingConstants.TRAILING);
    gridLayout2.setColumns(2);
    gridLayout2.setRows(2);
    pnlPagosSur.setBackground(Color.white);
    pnlPagosTotales.setBackground(Color.white);
    cmdAgregarPago.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
    cmdAgregarPago.setMnemonic('P');
    cmdAgregarPago.setText("Agregar pago");
    cmdAgregarPago.addActionListener(new
                                     FrmControlApartados_cmdAgregarPago_actionAdapter(this));
    jLabel6.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
    jLabel6.setText("Vendedor:");
    lblFechaCompra.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
    lblFechaCompra.setText("");
    jLabel8.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
    jLabel8.setText("Fecha de compra:");
    lblNoVenta.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
    lblNoVenta.setText("");
    jLabel10.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
    jLabel10.setText("No. de venta:");
    pnlDatosCliente.setLayout(gridBagLayout1);
    jLabel11.setToolTipText("");
    jLabel11.setText("Nombre del cliente:");
    txtTelefono.setEnabled(false);
    txtTelefono.setOpaque(false);
    txtTelefono.setDisabledTextColor(Color.black);
    txtTelefono.setText("");
    jLabel12.setText("Teléfono:");
    txtDomicilio.setEnabled(false);
    txtDomicilio.setOpaque(false);
    txtDomicilio.setDisabledTextColor(Color.black);
    txtDomicilio.setText("");
    jLabel13.setText("Domicilio:");
    txtNombreCliente.setEnabled(false);
    txtNombreCliente.setOpaque(false);
    txtNombreCliente.setDisabledTextColor(Color.black);
    txtNombreCliente.setText("");
    lblVendedor.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
    lblVendedor.setText("");
    pnlDatosCliente.setBackground(Color.white);
    pnlSur.setBackground(Color.white);
    pnlNorte.setLayout(borderLayout2);
    scollPagos.getViewport().setBackground(Color.white);
    pnlProductosSur.setBackground(Color.white);
    jLabel7.setText("");
    pnlEncabezado.setLayout(borderLayout8);
    pnlEncabezado.setBackground(Color.white);
    lblTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.magenta);
    lblTitulo.setText("BUSQUEDA DE APARTADOS");
    tblProductos.setModel(modelVentaArticulos1);
    tblProductos.addMouseListener(new
                                  FrmControlApartados_tblProductos_mouseAdapter(this));
    tblPagos.setModel(modelPagosRealizados1);
    cmdEntregarProducto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
    cmdEntregarProducto.setText("Entregar producto");
    cmdEntregarProducto.addActionListener(new
                                          FrmControlApartados_jButton1_actionAdapter(this));
    pnlDatosVenta.setLayout(borderLayout9);
    pnlDatosVenta.setBackground(Color.white);
    pnlDatosCompra.setBackground(Color.white);
    scrollProductos.getViewport().setBackground(Color.white);
    jPanel1.setLayout(borderLayout10);
    pnlFoto.setBackground(Color.white);
    pnlFoto.setMinimumSize(new Dimension(140, 140));
    pnlFoto.setPreferredSize(new Dimension(140, 140));
    pnlFotografia1.setBackground(Color.white);
    pnlFotografia1.setMinimumSize(new Dimension(106, 110));
    pnlFotografia1.setPreferredSize(new Dimension(106, 110));
    jLabel9.setText("Fotografia");
    pnlNorte.add(pnlDatosCliente, java.awt.BorderLayout.SOUTH);
    pnlDatosCompra.add(jLabel10);
    pnlDatosCompra.add(lblNoVenta);
    pnlDatosCompra.add(jLabel8);
    pnlDatosCompra.add(lblFechaCompra);
    pnlDatosCompra.add(jLabel6);
    pnlDatosCompra.add(lblVendedor);
    this.getContentPane().add(pnlSur, java.awt.BorderLayout.SOUTH);
    pnlSur.add(cmdAgregarPago);
    pnlSur.add(cmdEntregarProducto);
    this.getContentPane().add(pnlNorte, java.awt.BorderLayout.NORTH);
    this.getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    pnlCentro.add(splitPagosProductos, java.awt.BorderLayout.CENTER);
    splitPagosProductos.add(pnlProductos, JSplitPane.TOP);
    splitPagosProductos.add(pnlPagos, JSplitPane.BOTTOM);
    pnlPagos.add(scollPagos, java.awt.BorderLayout.CENTER);
    scollPagos.getViewport().add(tblPagos);
    pnlTotaleProductos.add(jLabel3);
    pnlTotaleProductos.add(txtTotalVenta);
    scrollProductos.getViewport().add(tblProductos);
    pnlPagos.add(jLabel2, java.awt.BorderLayout.NORTH);
    pnlPagos.add(pnlPagosSur, java.awt.BorderLayout.SOUTH);
    pnlPagosSur.add(pnlPagosTotales, java.awt.BorderLayout.EAST);
    pnlPagosTotales.add(jLabel4);
    pnlPagosTotales.add(txtMontoPagado);
    pnlPagosTotales.add(jLabel5);
    pnlPagosTotales.add(txtMontoaPagar);
    pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.WEST);
    pnlEncabezado.add(jLabel7, java.awt.BorderLayout.EAST);
    pnlNorte.add(pnlDatosVenta, java.awt.BorderLayout.CENTER);
    pnlNorte.add(pnlEncabezado, java.awt.BorderLayout.NORTH);
    pnlDatosVenta.add(pnlDatosCompra, java.awt.BorderLayout.WEST);
    pnlDatosCliente.add(jLabel11, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 10, 5));
    pnlDatosCliente.add(jLabel13, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 8, 5));
    pnlDatosCliente.add(jLabel12, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 5, 5));
    pnlDatosCliente.add(txtDomicilio,
                        new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(0, 0, 0, 0), 310, 0));
    pnlDatosCliente.add(txtNombreCliente,
                        new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(0, 0, 0, 2), 273, 0));
    pnlDatosCliente.add(txtTelefono,
                        new GridBagConstraints(5, 0, 4, 1, 1.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(0, 0, 0, 0), 146, 0));
    pnlProductos.add(pnlFoto, java.awt.BorderLayout.WEST);
    pnlFoto.add(jLabel9);
    pnlFoto.add(pnlFotografia1);
    pnlProductosSur.add(pnlTotaleProductos, java.awt.BorderLayout.EAST);
    jPanel1.add(pnlProductosSur, java.awt.BorderLayout.SOUTH);
    jPanel1.add(scrollProductos, java.awt.BorderLayout.CENTER);
    jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
    pnlProductos.add(jPanel1, java.awt.BorderLayout.CENTER);
    tblProductos.getColumnModel().getColumn(0).setMaxWidth(120);
    tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
    tblProductos.getColumnModel().getColumn(2).setMaxWidth(300);
    tblProductos.getColumnModel().getColumn(3).setMaxWidth(250);
    tblProductos.getColumnModel().getColumn(4).setMaxWidth(250);
    tblProductos.getColumnModel().getColumn(5).setMaxWidth(250);
    tblProductos.getColumnModel().getColumn(6).setMaxWidth(250);

  }

  public void this_windowOpened(WindowEvent e) {
    dlg.setSize(600, 400);
    dlg.setLocationRelativeTo(this);
    dlg.engine = this.engine;
    dlg.setVisible(true);

    //Revisamos que haya venta seleccionada
    this.setCursor(AppInstance.waitCursor);
    if (engine.ventaSeleccionada() != null) {
//Tenemos los datos de la venta.. ponemos los datos en el formulario
      this.setDatosVenta();
    }
    else {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this,
                                    "Debe seleccionar una venta de apartado",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio,
                                    JOptionPane.WARNING_MESSAGE);
      this.setVisible(false);
    }
    this.setCursor(AppInstance.defCursor);
  }

  private void setDatosVenta() {
    VentaApartado v = engine.ventaSeleccionada();
    this.txtNombreCliente.setText(v.getCliente());
    this.txtDomicilio.setText(v.getDomicilio());
    this.txtTelefono.setText(v.getTelefono());
    //Ponemos los productos vendidos
    this.lblVendedor.setText(AppInstance.idToNombreUsuario.get(v.
        getIdVendedor()).toString());
    this.modelVentaArticulos1.data = engine.productosView();
    this.modelPagosRealizados1.data = engine.pagosView();
    this.modelVentaArticulos1.fireTableDataChanged();
    this.modelPagosRealizados1.fireTableDataChanged();
    this.txtMontoPagado.setText(AppInstance.number.format(engine.abonado));
    this.txtTotalVenta.setText(AppInstance.number.format(engine.total));
    this.txtMontoaPagar.setText(AppInstance.number.format(engine.montoPendiente));
    this.lblNoVenta.setText(String.valueOf(v.getId()));
    this.lblFechaCompra.setText(v.getFecha().toString());

    this.cmdEntregarProducto.setEnabled(false);

    if (v.getStatus() == 1) {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this, "Venta finalizada exitosamente",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio, JOptionPane.PLAIN_MESSAGE);
//Venta finalizada mostramos el JOptionPane
    }
    else {
      this.pnlFotografia1.setImagen(engine.buscarFotografiaCliente(engine.
          ventaSeleccionada().getIdCliente()));
    }

  }

  public void tblProductos_mouseClicked(MouseEvent e) {
    if (tblProductos.getSelectedRow() >= 0) {
      ProductoVendido pv = engine.getProducto(tblProductos.getSelectedRow());
      //y el estado es no entregado
      if (pv.getEntregado() == 0) {
        //Sacamos el costo del producto seleccionado y comparamos si es menor o igual a lo que nos resta de lo abonado
        if (pv.getPrecioFinal() <= engine.montoDisponible) {
          cmdEntregarProducto.setEnabled(true); //Si es asi habilitamos el boton de entregar producto.
        }
        else {
          cmdEntregarProducto.setEnabled(false); //Si no pues no
        }
      }
      else {
        cmdEntregarProducto.setEnabled(false); //Si el producto ya fue entregado pues no aplica
      }
    }
  }

  public void jButton1_actionPerformed(ActionEvent e) {
//Marcamos el producto como entregado
    this.setCursor(AppInstance.waitCursor);
    ProductoVendido pv = this.engine.getProducto(this.tblProductos.
                                                 getSelectedRow());
    if (engine.entregarProducto(pv)) {
      //Mostramos el pago y damos la opcion de imprimir
      engine.seleccionarVenta( -1);
    }
    else {
      this.setCursor(AppInstance.defCursor);
      JOptionPane.showMessageDialog(this,
                                    "No se pudo registrar el articulo como entregado",
                                    com.boutique.engine.impl.AppInstance.
                                    nombreNegocio,
                                    JOptionPane.ERROR_MESSAGE);
    }
    this.setDatosVenta();
    //Actualizamos los datos de la venta (productos vendidos)
    //Actualizamos lo de el monto disponible para sacar productos
    //Preguntamos al vendedor si desea imprimir la entrega del producto para que firme
    this.setCursor(AppInstance.defCursor);
  }

  @SuppressWarnings("unchecked")
public void cmdAgregarPago_actionPerformed(ActionEvent e) {
    if (cmdAgregarPago.isEnabled()) {
      double saldoAnterior = engine.montoPendiente;
      frmIndicarTipoPago = new FrmIndicarTipoPago(1,
                                                  engine.montoPendiente, true, true);
      frmIndicarTipoPago.setSize(400, 400);
      frmIndicarTipoPago.setLocationRelativeTo(this.getRootPane());
      frmIndicarTipoPago.setVisible(true);

      this.setCursor(AppInstance.waitCursor);
      if (frmIndicarTipoPago.engine.getPagos().size() > 0 &&
          frmIndicarTipoPago.abonar) {
        engine.agregarPagos(frmIndicarTipoPago.engine.getPagos(),
                            frmIndicarTipoPago.engine.montoAcumulado);

        engine.seleccionarVenta( -1); //ACTUALIZAMOS LOS DATOS DE LA VENTA SELECCIONADA EN ESTE MOMENTO
        dlgPagoRegistrado = new DlgPagoRegistrado(engine.ventaSeleccionada().
                                                  getStatus(),
                                                  frmIndicarTipoPago.engine.
                                                  getPagos(),
                                                  frmIndicarTipoPago.engine.
                                                  montoAcumulado);
        dlgPagoRegistrado.setModal(true);
        dlgPagoRegistrado.setSize(500, 250);
        dlgPagoRegistrado.setLocationRelativeTo(this);
        engine.imprimirPagosAbonosaNota(dlgPagoRegistrado.pagosView,
                                        frmIndicarTipoPago.engine.
                                        montoAcumulado, saldoAnterior);

        dlgPagoRegistrado.setVisible(true);

        //Mostramos el mensaje con los datos de la venta y damos opcion de imprimir el pago
        //Imprimimos el pago
        //Mostramos el mensaje de pagos registrados
        //Refrescamos la venta

        //Revisamos si el total pagado cubre la nota
        //Total pagado, entregamos articulos, ponemos
        this.setDatosVenta();
        this.setVisible(false);
        this.setCursor(AppInstance.defCursor);
      }
      else {
        this.setCursor(AppInstance.defCursor);
        JOptionPane.showMessageDialog(this,
                                      "No se pudieron registrar los pagos",
                                      com.boutique.engine.impl.AppInstance.
                                      nombreNegocio,
                                      JOptionPane.ERROR_MESSAGE);

      }

    }

  }
}

class FrmControlApartados_cmdAgregarPago_actionAdapter
    implements ActionListener {
  private FrmControlApartados adaptee;
  FrmControlApartados_cmdAgregarPago_actionAdapter(FrmControlApartados adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAgregarPago_actionPerformed(e);
  }
}

class FrmControlApartados_jButton1_actionAdapter
    implements ActionListener {
  private FrmControlApartados adaptee;
  FrmControlApartados_jButton1_actionAdapter(FrmControlApartados adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class FrmControlApartados_tblProductos_mouseAdapter
    extends MouseAdapter {
  private FrmControlApartados adaptee;
  FrmControlApartados_tblProductos_mouseAdapter(FrmControlApartados adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.tblProductos_mouseClicked(e);
  }
}

class FrmControlApartados_this_windowAdapter
    extends WindowAdapter {
  private FrmControlApartados adaptee;
  FrmControlApartados_this_windowAdapter(FrmControlApartados adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
