package com.boutique.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Frame;
import javax.swing.JDialog;

import com.boutique.engine.impl.AppInstance;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.text.ParseException;

import javax.swing.JTextField;
 
public class DlgPagoTransferenciaBancaria extends JDialog {
	private String referencia;
	private double monto;

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lblReferencia = null;
	private JLabel lblMonto = null;
	private JTextField txtReferencia = null;
	private JTextField txtMonto = null;
	private JLabel jLabel = null;

	/**
	 * @param owner
	 */
	public DlgPagoTransferenciaBancaria(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * @param owner
	 */
	public DlgPagoTransferenciaBancaria(Frame owner, double monto) {
		super(owner);
		this.monto = monto;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 242);
		this.setTitle(AppInstance.nombreNegocio);
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				monto = 0;
			}
		});
		txtMonto.setText(AppInstance.number.format(monto));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(20, 11, 243, 25));
			jLabel.setFont(new Font("Arial", Font.BOLD, 14));
			jLabel.setText("Depósito o transferencia Bancaria");
			lblMonto = new JLabel();
			lblMonto.setBounds(new Rectangle(16, 124, 38, 16));
			lblMonto.setText("Monto:");
			lblReferencia = new JLabel();
			lblReferencia.setBounds(new Rectangle(16, 51, 76, 16));
			lblReferencia.setText("Referencia:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblReferencia, null);
			jContentPane.add(lblMonto, null);
			jContentPane.add(getTxtReferencia(), null);
			jContentPane.add(getTxtMonto(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

	private void registrarMontoDeTransferencia() {
		if (txtReferencia.getText().length() < 4) {
			JOptionPane.showMessageDialog(null,
					"Indíque un número de referencia válido",
					AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			monto = AppInstance.number.parse(txtMonto.getText()).doubleValue();
			this.referencia = txtReferencia.getText();
			this.setVisible(false);
		} catch (ParseException e) {
			monto = 0;
			txtMonto.setText("0.00");
			JOptionPane.showMessageDialog(null,
					"La cantidad indicada no es correcta",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * This method initializes txtReferencia
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtReferencia() {
		if (txtReferencia == null) {
			txtReferencia = new JTextField();
			txtReferencia.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
			txtReferencia.setBounds(new Rectangle(17, 75, 255, 34));
		}
		return txtReferencia;
	}

	/**
	 * This method initializes txtMonto
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtMonto() {
		if (txtMonto == null) {
			txtMonto = new JTextField();
			txtMonto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 25));
			txtMonto.setBounds(new Rectangle(17, 149, 255, 36));
			txtMonto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					registrarMontoDeTransferencia();
				}

			});
		}
		return txtMonto;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferencia() {
		return referencia;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

} // @jve:decl-index=0:visual-constraint="346,39"
