package com.boutique.view.salidas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.boutique.dao.DaoSalidas;
import com.boutique.domain.SalidaCaja;
import com.boutique.engine.impl.AppInstance;
import com.boutique.populator.ModelSalidasPopulator;
import com.boutique.view.tablemodels.ModelSalidas;

public class FrmSalidasDineroTerminal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable tblSalidas = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextField txtMonto = null;
	private JLabel jLabel1 = null;
	private JTextArea txtConceptoSalida = null;
	private JButton cmdAgregar = null;
	private ModelSalidas modelSalidas = null;
	private JLabel lblTile = null;
	private List<SalidaCaja> salidasCaja = null; // @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public FrmSalidasDineroTerminal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(349, 461);
		this.setContentPane(getJContentPane());
		this.setTitle("REGISTRO DE SALIDAS");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowOpened(java.awt.event.WindowEvent e) {
				salidasCaja = DaoSalidas
						.findSalidasCajaByidTerminalEnCorte(AppInstance
								.terminal().getId());
				ModelSalidasPopulator.populateModelSalidas(salidasCaja,
						modelSalidas);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTblSalidas());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tblSalidas
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getTblSalidas() {
		if (tblSalidas == null) {
			tblSalidas = new JTable();
			modelSalidas = new ModelSalidas();
			tblSalidas.setModel(modelSalidas);
			tblSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent e) {

					removeSalida(e.getKeyCode());
				}
			});
		}
		return tblSalidas;
	}

	protected void removeSalida(int keyCode) {
		if (keyCode == 27 && tblSalidas.getSelectedRow() != -1) {
			int i = JOptionPane.showConfirmDialog(this,
					"¿Deseas eliminar cancelar la salida registrada?",
					AppInstance.nombreNegocio, JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == i) {
				SalidaCaja s = salidasCaja.get(tblSalidas.getSelectedRow());
				if (DaoSalidas.eliminarSalida(s.getId())) {
					modelSalidas.data.remove(tblSalidas.getSelectedRow());
					modelSalidas.fireTableDataChanged();
				} else {
					JOptionPane
							.showMessageDialog(
									this,
									"El sistema no ha podido eliminar la salida de dinero, intente nuevamente",
									AppInstance.nombreNegocio,
									JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			lblTile = new JLabel();
			lblTile.setBounds(new Rectangle(56, 198, 228, 25));
			lblTile.setFont(new Font("Dialog", Font.BOLD, 14));
			lblTile.setText("SALIDAS DE CAJA REGISTRADAS");
			jLabel1 = new JLabel();
			jLabel1.setText("Monto:");
			jLabel1.setBounds(new Rectangle(31, 90, 38, 16));
			jLabel = new JLabel();
			jLabel.setText("Concepto:");
			jLabel.setBounds(new Rectangle(10, 10, 65, 27));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(84, 230));
			jPanel.add(jLabel, null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getCmdConceptoSalida(), null);
			jPanel.add(getCmdAgregar(), null);
			jPanel.add(lblTile, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (txtMonto == null) {
			txtMonto = new JTextField();
			txtMonto.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtMonto.setBounds(new Rectangle(83, 85, 234, 35));
		}
		return txtMonto;
	}

	/**
	 * This method initializes cmdConceptoSalida
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getCmdConceptoSalida() {
		if (txtConceptoSalida == null) {
			txtConceptoSalida = new JTextArea();
			txtConceptoSalida.setBounds(new Rectangle(83, 10, 234, 67));
			txtConceptoSalida.setLineWrap(false);
		}
		return txtConceptoSalida;
	}

	/**
	 * This method initializes cmdAgregar
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCmdAgregar() {
		if (cmdAgregar == null) {
			cmdAgregar = new JButton();
			cmdAgregar.setBounds(new Rectangle(83, 130, 233, 35));
			cmdAgregar.setText("REGISTRAR SALIDA");
			cmdAgregar.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					agregarSalida();
				}
			});
		}
		return cmdAgregar;
	}

	protected void agregarSalida() {
		if (StringUtils.isEmpty(txtConceptoSalida.getText())) {
			JOptionPane.showMessageDialog(this,
					"Por favor, indica el concepto de la salida",
					AppInstance.nombreNegocio,

					JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			double monto = AppInstance.number.parse(txtMonto.getText())
					.doubleValue();
			SalidaCaja s = new SalidaCaja();
			s.setConcepto(txtConceptoSalida.getText());
			s.setMonto(monto);
			if (DaoSalidas.registrarSalida(s, AppInstance.terminal()
					.getIdBoutique(), AppInstance.terminal().getId(),
					AppInstance.usuario().getId())) {

				txtConceptoSalida.setText(StringUtils.EMPTY);
				txtMonto.setText(StringUtils.EMPTY);
				txtConceptoSalida.requestFocus();
				salidasCaja = DaoSalidas
						.findSalidasCajaByidTerminalEnCorte(AppInstance
								.terminal().getId());
				ModelSalidasPopulator.populateModelSalidas(salidasCaja,
						modelSalidas);
			} else {
				JOptionPane
						.showMessageDialog(
								this,
								"El sistema no ha podido registrar la salida de dinero, intente nuevamente",
								AppInstance.nombreNegocio,
								JOptionPane.ERROR_MESSAGE);
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this,
					"Por favor, indica un monto valido",
					AppInstance.nombreNegocio,

					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

	}

} // @jve:decl-index=0:visual-constraint="10,10"
