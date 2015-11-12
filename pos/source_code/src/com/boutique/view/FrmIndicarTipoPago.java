package com.boutique.view;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;
import com.boutique.helper.IvaHelper;

public class FrmIndicarTipoPago extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean abonar = false;
	java.util.List<Pago> listaPagos = new ArrayList<Pago>();
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	JScrollPane scrollPagos = new JScrollPane();
	JList lstTipoPagos = new JList();
	DefaultListModel model = new DefaultListModel();
	Map<String, String> mapTipoPago = new HashMap<String, String>();
	JPanel pnlDatos = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JLabel jLabel2 = new JLabel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable tblPagosIndicados = new JTable();
	ModelTipoPago modelTipoPago1 = new ModelTipoPago();
	IndicarTipoPagoEngine engine = new IndicarTipoPagoEngine();
	JPanel jPanel2 = new JPanel();
	JButton cmdAceptar = new JButton();
	java.sql.Timestamp fecha = new java.sql.Timestamp(
			new java.util.Date().getTime());
	boolean ponerPagosEnCorte = true;

	public FrmIndicarTipoPago() {
		try {
			jbInit();
		} catch (Exception ex) {
		}
	}

	public FrmIndicarTipoPago(int tipoVenta, double monto,
			boolean mostrarBoton, boolean ponerPagosEnCorte) {
		try {
			monto = Double.parseDouble(AppInstance.number.format(monto)
					.replaceAll(",", ""));
			this.ponerPagosEnCorte = ponerPagosEnCorte;
			if (!mostrarBoton) {
				this.cmdAceptar.setVisible(false);
			}
			engine.setMontoaPagar(monto);
			jbInit();

			switch (tipoVenta) {
			case 1: // Venta decontado
				mapTipoPago.put("Efectivo", "1");
				mapTipoPago.put("Tarjeta de credito/debito", "2");
				mapTipoPago.put("Vale de compra", "3");
				mapTipoPago.put("Cheque", "4");
				mapTipoPago.put("Deposito o transferencia bancaria", "6");
				model.addElement("Efectivo");
				model.addElement("Tarjeta de credito/debito");
				model.addElement("Vale de compra");
				model.addElement("Cheque");
				model.addElement("Deposito o transferencia bancaria");

				break;

			case 2: // Venta de credito
				mapTipoPago.put("Efectivo", "1");
				mapTipoPago.put("Tarjeta de credito/debito", "2");
				mapTipoPago.put("Vale de compra", "3");
				mapTipoPago.put("Cheque", "4");
				mapTipoPago.put("Deposito o transferencia bancaria", "6");
				model.addElement("Efectivo");
				if(AppInstance.preferencias.getPagarCreditoConTC()){
					model.addElement("Tarjeta de credito/debito");
				}
				model.addElement("Vale de compra");
				model.addElement("Cheque");
				model.addElement("Deposito o transferencia bancaria");

				break;
			case 3: // Venta de apartado
				mapTipoPago.put("Efectivo", "1");
				mapTipoPago.put("Vale de compra", "3");
				mapTipoPago.put("Cheque", "4");
				mapTipoPago.put("Deposito o transferencia bancaria", "6");
				model.addElement("Efectivo");
				model.addElement("Vale de compra");
				model.addElement("Deposito o transferencia bancaria");

				break;
			case 4: // Venta a credito de otra sucursal
				mapTipoPago.put("Efectivo", "1");
				mapTipoPago.put("Cheque", "4");
				mapTipoPago.put("Deposito o transferencia bancaria", "6");
				model.addElement("Efectivo");
				model.addElement("Cheque");
				model.addElement("Deposito o transferencia bancaria");

				break;

			}

			lstTipoPagos.setModel(model);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setModal(true);
		this.addWindowListener(new FrmIndicarTipoPago_this_windowAdapter(this));
		getContentPane().setLayout(borderLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		jLabel1.setText("Selecciona el tipo de pago:");
		pnlDatos.setLayout(borderLayout2);
		jLabel2.setText("Pagos indicados:");
		tblPagosIndicados.setFont(new java.awt.Font("Dialog", Font.BOLD, 15));
		tblPagosIndicados.setModel(modelTipoPago1);
		lstTipoPagos
				.addKeyListener(new FrmIndicarTipoPago_lstTipoPagos_keyAdapter(
						this));
		lstTipoPagos.setFont(new java.awt.Font("Dialog", Font.BOLD, 16));
		scrollPagos.getViewport().setBackground(Color.white);
		cmdAceptar.setToolTipText("");
		cmdAceptar.setText("Registrar pago");
		cmdAceptar
				.addActionListener(new FrmIndicarTipoPago_cmdAceptar_actionAdapter(
						this));
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
		gridLayout1.setRows(2);
		gridLayout1.setColumns(1);

		jPanel1.setBackground(Color.white);
		jPanel1.setLayout(gridLayout1);
		this.getContentPane().setBackground(Color.white);
		jPanel1.add(scrollPagos);
		jPanel1.add(pnlDatos);
		pnlDatos.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(tblPagosIndicados);
		pnlDatos.add(jLabel2, java.awt.BorderLayout.NORTH);
		scrollPagos.getViewport().add(lstTipoPagos);
		this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
		jPanel2.add(cmdAceptar);

		this.getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);
		this.modelTipoPago1.data = engine.pagosView();
	}

	public void lstTipoPagos_keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			// Selecciono enter.. vemos que opcion selecciono
			if (lstTipoPagos.getSelectedValue().toString().equals("Efectivo")) {
				DlgPagoEfectivo dlg = new DlgPagoEfectivo(
						engine.getMontoPendiente());
				dlg.setSize(300, 200);
				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.getMonto() > 0
						&& engine.getMontoPendiente() >= dlg.getMonto()) {

					Pago pago = new Pago();
					pago.setTipo(1);
					pago.setFecha(fecha);
					pago.setIdSucursal(AppInstance.boutique().getId());
					pago.setIdVendedor(AppInstance.usuario().getId());
					pago.setIdTerminal(AppInstance.terminal().getId());
					pago.setMonto(dlg.getMonto());
					pago.setImpuestoMonto(IvaHelper.getIvaDesglosadFromMonto(pago.getMonto()));
					engine.montoPagoEnEfectivo += pago.getMonto();
					engine.pagoEnEfectivo = true;
					if (!ponerPagosEnCorte) {
						pago.setEnCorte(-1);
					}
					engine.descontarMonto(dlg.getMonto());
					engine.addPago(pago);
				} else {
					showCantidadIncorrecta();
				}
			} else if (lstTipoPagos.getSelectedValue().toString()
					.equals("Tarjeta de credito/debito")) {
				DlgPagoTarjetaCredito dlg = new DlgPagoTarjetaCredito(
						engine.getMontoPendiente());
				dlg.setSize(300, 400);
				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.getMonto() > 0
						&& engine.getMontoPendiente() >= dlg.getMonto()) {
					PagoTarjetaCredito pago = new PagoTarjetaCredito();
					pago.setTipo(2);
					pago.setIdSucursal(AppInstance.boutique().getId());
					pago.setIdVendedor(AppInstance.usuario().getId());
					pago.setIdTerminal(AppInstance.terminal().getId());
					pago.setMonto(dlg.getMonto());
					pago.setImpuestoMonto(IvaHelper.getIvaDesglosadFromMonto(pago.getMonto()));
					pago.setBanco(dlg.getBanco());
					pago.setMeses(dlg.getPromocion());
					pago.setFecha(fecha);
					if (!ponerPagosEnCorte) {
						pago.setEnCorte(-1);
					}
					engine.descontarMonto(dlg.getMonto());
					engine.addPago(pago);
				} else {
					showCantidadIncorrecta();
				}

			} else if (lstTipoPagos.getSelectedValue().toString()
					.equals("Vale de compra")) {
				DlgValeCompra dlg = new DlgValeCompra(
						engine.getMontoPendiente());
				dlg.setSize(300, 400);
				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.vale != null) {
					if (dlg.vale.getMontoUtilizado() > 0
							&& engine.getMontoPendiente() >= dlg.vale
									.getMontoUtilizado()) {
						// Creamos un pago con vale;
						PagoVale pago = new PagoVale();
						pago.setTipo(3);
						pago.setIdSucursal(AppInstance.boutique().getId());
						pago.setIdVendedor(AppInstance.usuario().getId());
						pago.setIdTerminal(AppInstance.terminal().getId());
						pago.setMonto(dlg.vale.getMontoUtilizado());
						pago.setImpuestoMonto(IvaHelper.getIvaDesglosadFromMonto(pago.getMonto()));
						pago.setEnCorte(0);
						pago.setIdVale(dlg.vale.getId());
						pago.setNumero(0);
						pago.setFecha(fecha);
						if (!ponerPagosEnCorte) {
							pago.setEnCorte(-1);
						}

						engine.descontarMonto(dlg.vale.getMontoUtilizado());
						engine.addPago(pago);
					} else {
						showCantidadIncorrecta();
					}
				} else {
					showCantidadIncorrecta();
				}
			} else if (lstTipoPagos.getSelectedValue().toString()
					.equals("Cheque")) {
				DlgPagoCheque dlg = new DlgPagoCheque(
						engine.getMontoPendiente());
				dlg.setSize(300, 400);
				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.getMonto() > 0
						&& engine.getMontoPendiente() >= dlg.getMonto()) {
					PagoCheque pago = new PagoCheque();
					pago.setTipo(4);
					pago.setIdSucursal(AppInstance.boutique().getId());
					pago.setIdVendedor(AppInstance.usuario().getId());
					pago.setIdTerminal(AppInstance.terminal().getId());
					pago.setMonto(dlg.getMonto());
					pago.setImpuestoMonto(IvaHelper.getIvaDesglosadFromMonto(pago.getMonto()));
					pago.setBanco(dlg.getBanco());
					pago.setFecha(fecha);
					pago.setNoCheque(dlg.getNoCheque());
					if (!ponerPagosEnCorte) {
						pago.setEnCorte(-1);
					}
					engine.descontarMonto(dlg.getMonto());
					engine.addPago(pago);
				} else {
					showCantidadIncorrecta();
				}

			} else if (lstTipoPagos.getSelectedValue().toString().equals("Deposito o transferencia bancaria")) {
				DlgPagoTransferenciaBancaria dlg = new DlgPagoTransferenciaBancaria(null,
						engine.getMontoPendiente());
 				dlg.setLocationRelativeTo(this.getRootPane());
				dlg.setModal(true);
				dlg.setVisible(true);
				if (dlg.getMonto() > 0 && engine.getMontoPendiente() >= dlg.getMonto()){
					PagoTransferenciaBancaria pago = new PagoTransferenciaBancaria();
					pago.setTipo(6);
					pago.setIdSucursal(AppInstance.boutique().getId());
					pago.setIdVendedor(AppInstance.usuario().getId());
					pago.setIdTerminal(AppInstance.terminal().getId());
					pago.setMonto(dlg.getMonto());
					pago.setImpuestoMonto(IvaHelper.getIvaDesglosadFromMonto(pago.getMonto()));
					pago.setNoReferencia(dlg.getReferencia());
					pago.setFecha(fecha);
					if (!ponerPagosEnCorte) {
						pago.setEnCorte(-1);
					}
					engine.descontarMonto(dlg.getMonto());
					engine.addPago(pago);
				}else{
					showCantidadIncorrecta();
				}

			}
			this.modelTipoPago1.fireTableDataChanged();
			if (engine.getMontoPendiente() <= 0.05) {
				this.abonar = true;
				if (engine.pagoEnEfectivo) {
					FrmReciboRegreso frm = new FrmReciboRegreso();
					frm.setTotalPago(engine.montoPagoEnEfectivo);
					frm.setSize(320, 270);
					frm.setModal(true);
					frm.setLocationRelativeTo(this);
					frm.setVisible(true);
				}
				this.setVisible(false);
			}

		}
	}

	private void showCantidadIncorrecta() {
		JOptionPane.showMessageDialog(this, "Cantidad incorrecta",
				com.boutique.engine.impl.AppInstance.nombreNegocio,
				JOptionPane.ERROR_MESSAGE);
	}

	public void this_windowOpened(WindowEvent e) {
		this.lstTipoPagos.setSelectedIndex(0);
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		this.abonar = true;
		this.setVisible(false);
	}
}

class FrmIndicarTipoPago_cmdAceptar_actionAdapter implements ActionListener {
	private FrmIndicarTipoPago adaptee;

	FrmIndicarTipoPago_cmdAceptar_actionAdapter(FrmIndicarTipoPago adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class FrmIndicarTipoPago_this_windowAdapter extends WindowAdapter {
	private FrmIndicarTipoPago adaptee;

	FrmIndicarTipoPago_this_windowAdapter(FrmIndicarTipoPago adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmIndicarTipoPago_lstTipoPagos_keyAdapter extends KeyAdapter {
	private FrmIndicarTipoPago adaptee;

	FrmIndicarTipoPago_lstTipoPagos_keyAdapter(FrmIndicarTipoPago adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.lstTipoPagos_keyPressed(e);
	}
}
