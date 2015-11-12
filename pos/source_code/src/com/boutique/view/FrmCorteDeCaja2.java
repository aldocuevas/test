package com.boutique.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.boutique.dao.DaoSource;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.DiarioDeEntradasEngine;
import com.boutique.view.salidas.PnlSalidasDineroCorteDeCaja;

public class FrmCorteDeCaja2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean corteImpreso = false;
	boolean corteRealizado = false;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlNorth = new JPanel();
	JPanel pnlCenter = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JScrollPane scrollCorte = new JScrollPane();
	JTable tblCorte = new JTable();
	ModelCorteDeCaja modelCorteDeCaja1 = new ModelCorteDeCaja();
	DiarioDeEntradasEngine engine = new DiarioDeEntradasEngine();
	JPanel pnlSalidasObservaciones = new JPanel();
	GridLayout gridLayout1 = new GridLayout();
	BorderLayout borderLayout3 = new BorderLayout();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JPanel pnlSouth = new JPanel();
	JButton cmdSalir = new JButton();
	JButton cmdImprimir = new JButton();
	JButton cmdRealizarCorte = new JButton();
	JScrollPane jScrollPane1 = new JScrollPane();
	BorderLayout borderLayout4 = new BorderLayout();
	JLabel jLabel5 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JLabel jLabel6 = new JLabel();
	JLabel lblVendedor = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel lblFecha = new JLabel();
	GridBagLayout gridBagLayout2 = new GridBagLayout();
	JLabel jLabel7 = new JLabel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	double salidas = 0.0;
	PnlSalidasDineroCorteDeCaja pnlSalidasDineroCorteDeCaja;

	public FrmCorteDeCaja2() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setTitle("CORTE DE CAJA");
		pnlSalidasDineroCorteDeCaja = new PnlSalidasDineroCorteDeCaja();

		getContentPane().setLayout(borderLayout1);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tblCorte.setModel(modelCorteDeCaja1);
		this.addWindowListener(new FrmCorteDeCaja2_this_windowAdapter(this));
		pnlSalidasObservaciones.setLayout(gridLayout1);
		gridLayout1.setColumns(1);
		gridLayout1.setRows(2);

		jLabel3.setText("                     ");
		jLabel4.setText("                       ");
		scrollCorte.setSize(new Dimension(400, 250));
		scrollCorte.setPreferredSize(new Dimension(400, 400));
		pnlCenter.setLayout(borderLayout2);
		cmdSalir.setText("SALIR");
		cmdSalir.addActionListener(new FrmCorteDeCaja2_cmdSalir_actionAdapter(
				this));
		cmdImprimir.setEnabled(false);
		cmdImprimir.setText("IMPRIMIR");
		cmdImprimir
				.addActionListener(new FrmCorteDeCaja2_cmdImprimir_actionAdapter(
						this));
		cmdRealizarCorte.setText("REALIZAR CORTE");
		cmdRealizarCorte
				.addActionListener(new FrmCorteDeCaja2_cmdRealizarCorte_actionAdapter(
						this));
		pnlNorth.setLayout(borderLayout4);
		jLabel5.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		jLabel5.setPreferredSize(new Dimension(131, 40));
		jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel5.setText("CORTE DE CAJA");
		jPanel1.setLayout(gridBagLayout2);
		jLabel6.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		jLabel6.setText("Fecha:");
		lblVendedor.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		lblVendedor.setText("Aldo Antonio Cuevas Alvarez");
		jLabel8.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		jLabel8.setText("Vendedor:");
		lblFecha.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		lblFecha.setText("23/jul/2009 23:54:00");
		jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel7.setHorizontalTextPosition(SwingConstants.LEFT);
		jLabel7.setText("Dinero recogido:");

		this.getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
		scrollCorte.getViewport().add(tblCorte);
		pnlCenter.add(pnlSalidasDineroCorteDeCaja, java.awt.BorderLayout.SOUTH);
		pnlCenter.add(scrollCorte, java.awt.BorderLayout.CENTER);
		this.getContentPane().add(pnlSouth, java.awt.BorderLayout.SOUTH);
		pnlSouth.add(cmdRealizarCorte);
		pnlSouth.add(cmdImprimir);
		pnlSouth.add(cmdSalir);

		this.getContentPane().add(pnlNorth, java.awt.BorderLayout.NORTH);
		jPanel1.add(lblFecha, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 1), 85, 0));
		jPanel1.add(jLabel6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 53, 0));
		jPanel1.add(jLabel8, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 28, 0));
		jPanel1.add(lblVendedor, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 1), 34, 0));
		pnlNorth.add(jLabel5, java.awt.BorderLayout.CENTER);
		pnlNorth.add(jPanel1, java.awt.BorderLayout.SOUTH);

	}

	public void this_windowOpened(WindowEvent e) {
		this.lblFecha.setText(AppInstance.formatoLargo.format(DaoSource
				.getFechaActual()));
		this.lblVendedor.setText(AppInstance.usuario().getNombre());
		this.modelCorteDeCaja1.data = engine.getCorteDeCaja(AppInstance
				.boutique().getId(), AppInstance.terminal().getId());
		this.modelCorteDeCaja1.fireTableDataChanged();
	}

	public void cmdSalir_actionPerformed(ActionEvent e) {
		// corteRealizado = false;corteImpreso=false;

		if (corteRealizado && !corteImpreso) {
			int i = JOptionPane
					.showConfirmDialog(
							this,
							"EL CORTE NO HA SIDO IMPRESO, SI SALE AHORA NO PODRA IMPRIMIRLO, ¿REALMENTE DESEA SALIR?",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.NO_OPTION) {
				this.setVisible(true);

			} else {
				this.setVisible(false);
			}
		} else {
			this.setVisible(false);
		}

	}

	public void cmdRealizarCorte_actionPerformed(ActionEvent e) {
		try {

			int i = JOptionPane.showConfirmDialog(this,
					"¿Estás seguro que deseas realizar el corte de caja?",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				// REGISTRAMOS EL CORTE
				if (engine.registrarCorte(AppInstance.boutique().getId(),
						AppInstance.terminal().getId(), AppInstance.usuario()
								.getId())) {
					engine.imprimirCorteDeCaja(
							salidas,
							this.pnlSalidasDineroCorteDeCaja.getModelSalidas().data);
					corteImpreso = true;
					JOptionPane
							.showMessageDialog(
									this,
									"CORTE REALIZADO EXITOSAMENTE, POR FAVOR IMPRIMA EL CORTE",
									com.boutique.engine.impl.AppInstance.nombreNegocio,
									JOptionPane.INFORMATION_MESSAGE);
					this.cmdImprimir.setEnabled(true);
					corteRealizado = true;
					this.cmdRealizarCorte.setEnabled(false);
				}
			}
		}

		catch (NumberFormatException ex) {
			JOptionPane
					.showMessageDialog(
							this,
							"El valor de las salidas y/o dinero recogido no es una cantidad valida",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cmdImprimir_actionPerformed(ActionEvent e) {
		if (cmdImprimir.isEnabled()) {
			engine.imprimirCorteDeCaja(salidas,
					this.pnlSalidasDineroCorteDeCaja.getModelSalidas().data);
			corteImpreso = true;

		}
	}

	public void this_windowClosing(WindowEvent e) {
	}

}

class FrmCorteDeCaja2_cmdImprimir_actionAdapter implements ActionListener {
	private final FrmCorteDeCaja2 adaptee;

	FrmCorteDeCaja2_cmdImprimir_actionAdapter(FrmCorteDeCaja2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdImprimir_actionPerformed(e);
	}
}

class FrmCorteDeCaja2_cmdRealizarCorte_actionAdapter implements ActionListener {
	private final FrmCorteDeCaja2 adaptee;

	FrmCorteDeCaja2_cmdRealizarCorte_actionAdapter(FrmCorteDeCaja2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdRealizarCorte_actionPerformed(e);
	}
}

class FrmCorteDeCaja2_cmdSalir_actionAdapter implements ActionListener {
	private final FrmCorteDeCaja2 adaptee;

	FrmCorteDeCaja2_cmdSalir_actionAdapter(FrmCorteDeCaja2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalir_actionPerformed(e);
	}
}

class FrmCorteDeCaja2_this_windowAdapter extends WindowAdapter {
	private final FrmCorteDeCaja2 adaptee;

	FrmCorteDeCaja2_this_windowAdapter(FrmCorteDeCaja2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		adaptee.this_windowClosing(e);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}
