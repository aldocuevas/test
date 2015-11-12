package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.boutique.domain.Pago;
import com.boutique.engine.impl.AppInstance;

import java.util.*;
import com.boutique.domain.PagoTarjetaCredito;
import com.boutique.domain.PagoTransferenciaBancaria;
import com.boutique.domain.PagoVale;
import com.boutique.domain.PagoCheque;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class DlgPagoRegistrado extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	public java.util.List pagosView = new ArrayList<Object[]>();
	Object[] row;

	@SuppressWarnings("unchecked")
	public DlgPagoRegistrado(int finalizada, java.util.List<Pago> pagos,
			double monto) {
		this(new Frame(), "DlgPagoRegistrado", false);

		for (Pago p : pagos) {
			row = new Object[4];
			row[0] = p.getId();
			row[1] = p.getFecha();

			row[2] = AppInstance.number.format(p.getMonto());
			if (p instanceof PagoTarjetaCredito) {
				PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
				row[3] = "T Credito/debito "
						+ pt.getBanco()
						+ ((pt.getMeses() > 0) ? pt.getMeses()
								+ " meses sin intereses" : "");
			} else if (p instanceof PagoVale) {
				PagoVale pv = (PagoVale) p;
				row[3] = "Vale no. " + pv.getIdVale();

			} else if (p instanceof PagoCheque) {
				PagoCheque pch = (PagoCheque) p;
				row[3] = "Cheque no. " + pch.getNoCheque() + " de "
						+ pch.getBanco();

			} else if (p instanceof PagoTransferenciaBancaria) {
				PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
				row[3] = "Depósito/transf. bancaria, ref: "
						+ pb.getNoReferencia();
			} else if (p instanceof Pago) {
				row[3] = "Efectivo";
			}

			pagosView.add(row);
		}
		if (finalizada == 0) {
			lblFinalizada.setVisible(false);
		} else {
			lblFinalizada.setVisible(true);
		}
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		lblTotal.setText(AppInstance.number.format(monto));
	}

	public DlgPagoRegistrado(Frame owner, String title, boolean modal) {
		super(owner, title, modal);

	}

	public DlgPagoRegistrado() {

	}

	private void jbInit() throws Exception {
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setTitle("Pago registrado");
		this.addWindowListener(new DlgPagoRegistrado_this_windowAdapter(this));
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		jLabel1.setText("Pago registrado");
		jLabel1.setBounds(new Rectangle(12, 4, 159, 34));
		tblPagos.setModel(modelPagosRealizados1);
		modelPagosRealizados1.data = pagosView;
		cmdAceptar.setBounds(new Rectangle(276, 174, 89, 27));
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addActionListener(new DlgPagoRegistrado_cmdAceptar_actionAdapter(
						this));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		jLabel2.setText("Total abonado:");
		jLabel2.setBounds(new Rectangle(194, 4, 150, 36));
		lblTotal.setFont(new java.awt.Font("Dialog", Font.BOLD, 14));
		lblTotal.setText("jLabel3");
		lblTotal.setBounds(new Rectangle(350, 4, 100, 36));
		lblFinalizada.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		lblFinalizada.setForeground(Color.red);
		lblFinalizada.setText("VENTA FINALIZADA");
		lblFinalizada.setBounds(new Rectangle(20, 172, 231, 35));
		this.getContentPane().add(cmdAceptar);
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(jLabel2);
		this.getContentPane().add(scrollPagos);
		this.getContentPane().add(lblTotal);
		this.getContentPane().add(lblFinalizada);
		scrollPagos.getViewport().add(tblPagos);
		scrollPagos.setBounds(new Rectangle(11, 33, 450, 134));
		this.cmdAceptar.requestFocus();
	}

	JLabel jLabel1 = new JLabel();
	JScrollPane scrollPagos = new JScrollPane();
	JTable tblPagos = new JTable();
	ModelPagosRealizados modelPagosRealizados1 = new ModelPagosRealizados();
	JButton cmdAceptar = new JButton();
	JLabel jLabel2 = new JLabel();
	JLabel lblTotal = new JLabel();
	JLabel lblFinalizada = new JLabel();

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void this_windowOpened(WindowEvent e) {
		this.cmdAceptar.requestFocus();
	}
}

class DlgPagoRegistrado_this_windowAdapter extends WindowAdapter {
	private DlgPagoRegistrado adaptee;

	DlgPagoRegistrado_this_windowAdapter(DlgPagoRegistrado adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class DlgPagoRegistrado_cmdAceptar_actionAdapter implements ActionListener {
	private DlgPagoRegistrado adaptee;

	DlgPagoRegistrado_cmdAceptar_actionAdapter(DlgPagoRegistrado adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}
