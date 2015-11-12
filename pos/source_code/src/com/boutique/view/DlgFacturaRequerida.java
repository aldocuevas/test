package com.boutique.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.RegistroFacturaIndividualEngine;

public class DlgFacturaRequerida extends JDialog {

	private RegistroFacturaIndividualEngine registroFacturaEngine; // @jve:decl-index=0:
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton cmdSi = null;
	private JButton cmdNo = null;
	private JLabel lblPregunta = null;
	private FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente(true);

	/**
	 * @param owner
	 */
	public DlgFacturaRequerida(Frame owner,
			RegistroFacturaIndividualEngine engine) {
		super(owner);
		this.registroFacturaEngine = engine;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(292, 150);
		this.setModal(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblPregunta = new JLabel();
			lblPregunta.setBounds(new Rectangle(64, 17, 165, 16));
			lblPregunta.setText("¿El cliente requiere factura?");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getCmdNo(), null);
			jContentPane.add(getCmdSi(), null);
			jContentPane.add(lblPregunta, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cmdSi
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCmdSi() {
		if (cmdSi == null) {
			cmdSi = new JButton();
			cmdSi.setBounds(new Rectangle(154, 51, 72, 29));
			cmdSi.setText("SI");
			cmdSi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					performValidacionDelCliente();
				}

			});

		}
		return cmdSi;
	}

	private void performValidacionDelCliente() {
		if (!getRegistroFacturaEngine().clienteAsignadoAVenta()) {
			int choice = JOptionPane
					.showOptionDialog(
							null,
							"Es necesario asociar un cliente a la venta, asegurese que el cliente tenga un correo electronico registrado",
							AppInstance.nombreNegocio,
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null,
							new Object[] { "ASOCIAR UN CLIENTE A LA VENTA",
									"CANCELAR SOLICITUD DE FACTURA" },
							"ASOCIAR UN CLIENTE A LA VENTA");
			if (choice == 0) {
				// ASOCIAR CLIENTE
				frmBuscarCliente.setModal(true);
				frmBuscarCliente.setSize(900, 600);
				frmBuscarCliente.setLocationRelativeTo(this);
				getFrmBuscarCliente().setVisible(true);
				this.getRegistroFacturaEngine().setCliente(
						getFrmBuscarCliente().cliente);

				performValidacionDelCliente();
				return;
			} else {
				// SALIR SIN ASOCIAR CLIENTE
				this.getRegistroFacturaEngine().setCliente(null);
				this.setVisible(false);
				return;
			}

		}
		if (!getRegistroFacturaEngine().clienteTieneCorreoRegistrado()) {
			int choice = JOptionPane
					.showOptionDialog(
							null,
							"El cliente asociado a la venta no cuenta con registro de correo electronico, por favor,actualice los datos del cliente e indique el correo electronico (e-mail)",
							AppInstance.nombreNegocio,
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null,
							new Object[] {
									"ACTUALIZAR CORREO ELECTRONICO DEL CLIENTE",
									"CANCELAR SOLICITUD DE FACTURA" },
							"ACTUALIZAR CORREO ELECTRONICO DEL CLIENTE");
			if (choice == 0) {
				DlgActualizarDatosClientes dlg = new DlgActualizarDatosClientes(
						this.getRegistroFacturaEngine().getCliente(), "ACTUALIZAR",true);
				dlg.setModal(true);
				dlg.setSize(500, 700);
				this.setLocationRelativeTo(this.getRootPane());
				this.setCursor(AppInstance.defCursor);
				dlg.setVisible(true);
				this.getRegistroFacturaEngine().setCliente(
						dlg.cliente);
				dlg.dispose();
				dlg = null;
				performValidacionDelCliente();
				return;
			} else {
				this.getRegistroFacturaEngine().setCliente(null);
				this.setVisible(false);
				return;
			}

		}
		if (!getRegistroFacturaEngine().clienteTieneRFCRegistrado()) {
			int i = JOptionPane
					.showConfirmDialog(
							null,
							"El cliente asociado a la venta no cuenta con RFC registrado, ¿Desea registrar el RFC?",
							AppInstance.nombreNegocio,
							JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == i) {
				// MOSTAR DIALOGO DE ACTUALIZACION DE CLIENTE
				DlgActualizarDatosClientes dlg = new DlgActualizarDatosClientes(
						this.getRegistroFacturaEngine().getCliente(), "ACTUALIZAR",true);
				dlg.setModal(true);
				dlg.setSize(500, 700);
				this.setLocationRelativeTo(this.getRootPane());
				this.setCursor(AppInstance.defCursor);
				dlg.setVisible(true);
				this.getRegistroFacturaEngine().setCliente(
						dlg.cliente);
				dlg.dispose();
				dlg = null;
				
				performValidacionDelCliente();
				return;
			}
		}

		int choice = JOptionPane.showOptionDialog(null,
				"La factura sera creada a nombre de "
						+ this.getRegistroFacturaEngine().getCliente()
								.getNombre()
						+ " "
						+ this.getRegistroFacturaEngine().getCliente()
								.getApellidoPaterno()
						+ " "
						+ this.getRegistroFacturaEngine().getCliente()
								.getApellidoMaterno()
						+ " y enviada al correo electronico: "
						+ this.getRegistroFacturaEngine().getCliente()
								.getEmail(), AppInstance.nombreNegocio,
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, new Object[] { "CONFIRMAR",
						"CANCELAR SOLICITUD DE FACTURA" }, "CANCELAR");
		if (choice == 0) {
			getRegistroFacturaEngine().marcarVentaParaFacturaIndividual();
		} else {
			this.getRegistroFacturaEngine().setCliente(null);
			this.setVisible(false);
			return;
		}

		this.setVisible(false);
	}

	/**
	 * This method initializes cmdNo
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCmdNo() {
		if (cmdNo == null) {
			cmdNo = new JButton();
			cmdNo.setText("NO");
			cmdNo.setLocation(new Point(57, 51));
			cmdNo.setPreferredSize(new Dimension(45, 26));
			cmdNo.setMnemonic(KeyEvent.VK_UNDEFINED);
			cmdNo.setSize(new Dimension(72, 29));
			cmdNo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
				}
			});
		}
		return cmdNo;
	}

	private final void close() {
		this.setVisible(false);
	}

	public void setFrmBuscarCliente(FrmBuscarCliente frmBuscarCliente) {
		this.frmBuscarCliente = frmBuscarCliente;
	}

	public FrmBuscarCliente getFrmBuscarCliente() {
		return frmBuscarCliente;
	}

	public void setRegistroFacturaEngine(
			RegistroFacturaIndividualEngine registroFacturaEngine) {
		this.registroFacturaEngine = registroFacturaEngine;
	}

	public RegistroFacturaIndividualEngine getRegistroFacturaEngine() {
		return registroFacturaEngine;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
