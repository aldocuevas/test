package com.boutique.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import com.boutique.dao.DaoVentas;
import com.boutique.engine.impl.AppInstance;

public class DlgDescuentoCredito extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lblTitulo = null;
	private JLabel lblPorcentaje = null;
	private JComboBox cmbDescuento = null;
	private JLabel lblDescripcion = null;
	private JTextArea txtJustificacion = null;
	private JButton cmdAplicarDescuento = null;
	private int idProducto;
    private boolean exito;
	/**
	 * This is the default constructor
	 */
	public DlgDescuentoCredito() {
		super();
		initialize();
	}

	public DlgDescuentoCredito(int idProducto) {
		super();
		this.idProducto = idProducto;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 267);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Descuento a credito");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				DefaultComboBoxModel model = new DefaultComboBoxModel();
				cmbDescuento.setModel(model);
				for (int i = 1; i < 81; i++) {
					model.addElement(i);
				}

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
			lblDescripcion = new JLabel();
			lblDescripcion.setBounds(new Rectangle(12, 87, 122, 22));
			lblDescripcion.setText("Justificacion:");
			lblPorcentaje = new JLabel();
			lblPorcentaje.setBounds(new Rectangle(15, 34, 155, 16));
			lblPorcentaje.setText("Selecciona el porcentaje");
			lblTitulo = new JLabel();
			lblTitulo.setBounds(new Rectangle(24, 8, 241, 16));
			lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitulo.setText("Descuento a producto seleccionado");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblTitulo, null);
			jContentPane.add(lblPorcentaje, null);
			jContentPane.add(getCmbDescuento(), null);
			jContentPane.add(lblDescripcion, null);
			jContentPane.add(getTxtJustificacion(), null);
			jContentPane.add(getCmdAplicarDescuento(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cmbDescuento
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getCmbDescuento() {
		if (cmbDescuento == null) {
			cmbDescuento = new JComboBox();
			cmbDescuento.setBounds(new Rectangle(16, 54, 252, 24));
		}
		return cmbDescuento;
	}

	/**
	 * This method initializes txtJustificacion
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtJustificacion() {
		if (txtJustificacion == null) {
			txtJustificacion = new JTextArea();
			txtJustificacion.setBounds(new Rectangle(12, 112, 261, 43));
			txtJustificacion.setLineWrap(true);
		}
		return txtJustificacion;
	}

	/**
	 * This method initializes cmdAplicarDescuento
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCmdAplicarDescuento() {
		if (cmdAplicarDescuento == null) {
			cmdAplicarDescuento = new JButton();
			cmdAplicarDescuento.setBounds(new Rectangle(43, 166, 197, 57));
			cmdAplicarDescuento.setText("Aplicar descuento");
			cmdAplicarDescuento
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							setExito(false);
							try {
								int descuento = Integer.parseInt(cmbDescuento
										.getSelectedItem().toString());
								if (txtJustificacion.getText().equals("")) {
									JOptionPane.showMessageDialog(null,
											"Una justificacion es requerida",
											AppInstance.nombreNegocio,
											JOptionPane.WARNING_MESSAGE);
									return;

								}
								boolean result = DaoVentas
										.aplicarDescuentoCredito(idProducto,
												AppInstance.usuario().getId(),
												descuento,
												txtJustificacion.getText());
								if (!result) {
									JOptionPane
											.showMessageDialog(
													null,
													"El descuento no ha podido ser aplicado, contacte al administador del sistema para corregir este problema",
													com.boutique.engine.impl.AppInstance.nombreNegocio,
													JOptionPane.ERROR_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null,
											"Descuento aplicado con exito",
											AppInstance.nombreNegocio,
											JOptionPane.INFORMATION_MESSAGE);
									setExito(true);
									setVisible(false);
								}
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane
										.showMessageDialog(
												null,
												"El descuento no ha podido ser aplicado, contacte al administador del sistema para corregir este problema",
												com.boutique.engine.impl.AppInstance.nombreNegocio,
												JOptionPane.ERROR_MESSAGE);
							}
						}
					});
		 
		}
		return cmdAplicarDescuento;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public boolean isExito() {
		return exito;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
