package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.boutique.domain.DescPagoAnticipado;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.text.*;

public class DlgDescuentoPagoAnticipado extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1332816989110679233L;
	int montoDescuento;
	boolean abonar = false;
	SistemaCreditoEngine engine;
	DescPagoAnticipado promocion;

	public DlgDescuentoPagoAnticipado(Frame owner, String title, boolean modal,
			SistemaCreditoEngine engine, DescPagoAnticipado promocion) {
		super(owner, title, modal);

		try {
			this.engine = engine;
			this.promocion = promocion;
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		jLabel1.setFont(new java.awt.Font("Verdana", Font.BOLD, 12));
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setHorizontalTextPosition(SwingConstants.LEADING);
		jLabel1.setText("Saldo inicial:");
		jLabel1.setBounds(new Rectangle(14, 29, 145, 15));
		this.getContentPane().setLayout(null);
		txtSaldoInicial.setFont(new java.awt.Font("Verdana", Font.PLAIN, 18));
		txtSaldoInicial.setEditable(false);
		txtSaldoConDescuento.setFont(new java.awt.Font("Verdana", Font.PLAIN,
				18));
		txtSaldoConDescuento.setEditable(false);
		txtSaldoAbonado.setEditable(false);

		cmdAplicarDescuento
				.setFont(new java.awt.Font("Verdana", Font.PLAIN, 14));
		jLabel2.setFont(new java.awt.Font("Verdana", Font.BOLD, 11));
		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setText(promocion.getPorcentajeDescuento() + "% de descuento:");
		jLabel2.setBounds(new Rectangle(14, 72, 145, 15));
		jLabel3.setFont(new java.awt.Font("Verdana", Font.BOLD, 12));
		jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);

		cmdAplicarDescuento.setBounds(new Rectangle(69, 198, 218, 43));
		cmdAplicarDescuento.setText("Aplicar descuento y pagar");
		cmdAplicarDescuento
				.addActionListener(new DlgDescuentoPagoAnticipado_cmdAplicarDescuento_actionAdapter(
						this));

		double descuento = engine.venta.subTotal
				* (this.promocion.getPorcentajeDescuento() / 100.0);
		double saldoConDescuento = engine.venta.subTotal - descuento;
		txtSaldoAbonado.setBounds(new Rectangle(170, 107, 128, 37));

		txtSaldoConDescuento.setText(AppInstance.number
				.format(saldoConDescuento));
		txtSaldoAbonado.setText(AppInstance.number
				.format(engine.venta.saldoAbonado));
		txtSaldoConDescuento.setBounds(new Rectangle(170, 62, 128, 37));
		txtSaldoInicial.setText(AppInstance.number
				.format(engine.venta.subTotal));
		txtSaldoAbonado.setFont(new java.awt.Font("Verdana", Font.PLAIN, 18));
		txtSaldoInicial.setBounds(new Rectangle(170, 20, 128, 34));
		jLabel3.setText("Saldo a pagar:");
		jLabel3.setBounds(new Rectangle(13, 160, 146, 21));
		this.getContentPane().setBackground(Color.white);
		this.addWindowListener(new DlgDescuentoPagoAnticipado_this_windowAdapter(
				this));
		txtSaldoaPagar.setFont(new java.awt.Font("Verdana", Font.PLAIN, 18));
		txtSaldoaPagar.setEditable(false);
		txtSaldoaPagar.setText(AppInstance.number.format(saldoConDescuento
				- engine.venta.saldoAbonado));
		txtSaldoaPagar.setBounds(new Rectangle(170, 152, 128, 37));
		jLabel4.setFont(new java.awt.Font("Verdana", Font.BOLD, 12));
		jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel4.setText("Saldo abonado:");
		jLabel4.setBounds(new Rectangle(13, 115, 146, 21));
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(txtSaldoInicial);
		this.getContentPane().add(txtSaldoConDescuento);
		this.getContentPane().add(txtSaldoAbonado);
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(cmdAplicarDescuento);
		this.getContentPane().add(txtSaldoaPagar);
		this.getContentPane().add(jLabel4);
		this.getContentPane().add(jLabel3);
	}

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JTextField txtSaldoInicial = new JTextField();
	JTextField txtSaldoConDescuento = new JTextField();
	JTextField txtSaldoAbonado = new JTextField();
	JButton cmdAplicarDescuento = new JButton();
	JTextField txtSaldoaPagar = new JTextField();
	JLabel jLabel4 = new JLabel();

	@SuppressWarnings("unchecked")
	public void cmdAplicarDescuento_actionPerformed(ActionEvent e) {

		cmdAplicarDescuento.setEnabled(false);

		double abono = 0.0;
		try {
			abono = AppInstance.number.parse(txtSaldoaPagar.getText())
					.doubleValue();
		} catch (ParseException ex) {
			return;
		}
		// MOSTRAMOS EL DIALOGO PARA INDICAR EL TIPO DE PAGO
		double saldoConDescuento = engine.venta.saldoTotal
				- (engine.venta.saldoTotal * promocion.getPorcentajeDescuento() / 100);
		FrmIndicarTipoPago frm = new FrmIndicarTipoPago(2, abono, false, true);
		frm.setSize(400, 400);
		frm.setLocationRelativeTo(this.getRootPane());
		frm.setVisible(true);
		if (frm.engine.getMontoPendiente() <= 0.05) {
			// AQUI HACEMOS LA TRANSACCION COMPLETA CON EL DESCUENTO

			if (frm.engine.getPagos() != null) {
				if (engine.finalizarNotaConDescuento(promocion,
						frm.engine.getPagos())) {
					DlgPagoRegistrado dlgPagoRegistrado = new DlgPagoRegistrado(
							engine.venta.getStatus(), frm.engine.getPagos(),
							frm.engine.montoAcumulado);

					dlgPagoRegistrado.setModal(true);
					dlgPagoRegistrado.setSize(500, 250);
					dlgPagoRegistrado.setLocationRelativeTo(this);

					engine.imprimirPagosAbonosaNotaPagoAnticipado(
							dlgPagoRegistrado.pagosView,
							frm.engine.montoAcumulado, saldoConDescuento);
					dlgPagoRegistrado.setVisible(true);
					abonar = true;
				} else {
					JOptionPane.showMessageDialog(this,
							"No se pudieron registrar los pagos",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane
						.showMessageDialog(
								this,
								"No hay pagos indicados, no se registrará ningún pago.",
								com.boutique.engine.impl.AppInstance.nombreNegocio,
								JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"No hay pagos indicados o la cantidad no cubren el monto de la nota, no se registrará ningún pago.",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
		}
		this.setVisible(false);
	}

	public void this_windowOpened(WindowEvent e) {

	}
}

class DlgDescuentoPagoAnticipado_this_windowAdapter extends WindowAdapter {
	private DlgDescuentoPagoAnticipado adaptee;

	DlgDescuentoPagoAnticipado_this_windowAdapter(
			DlgDescuentoPagoAnticipado adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class DlgDescuentoPagoAnticipado_cmdAplicarDescuento_actionAdapter implements
		ActionListener {
	private DlgDescuentoPagoAnticipado adaptee;

	DlgDescuentoPagoAnticipado_cmdAplicarDescuento_actionAdapter(
			DlgDescuentoPagoAnticipado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAplicarDescuento_actionPerformed(e);
	}
}
