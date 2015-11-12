package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.BusquedaApartadoEngine;

public class DlgBuscarApartados extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BusquedaApartadoEngine engine = new BusquedaApartadoEngine();
	JPanel panel1 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlNorth = new JPanel();
	JPanel pnlSouth = new JPanel();
	JLabel jLabel1 = new JLabel();
	JTextField txtNombre = new JTextField();
	JButton cmdBuscar = new JButton();
	BorderLayout borderLayout2 = new BorderLayout();
	JScrollPane scrollNotas = new JScrollPane();
	JTable tblApartados = new JTable();
	ModelDatosApartado modelDatosApartado1 = new ModelDatosApartado();
	JButton cmdContinuar = new JButton();

	public DlgBuscarApartados(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgBuscarApartados() {
		this(new Frame(), "DlgBuscarApartados", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		jLabel1.setToolTipText("");
		jLabel1.setText("Indique el nombre del cliente:");
		txtNombre.setText("");
		txtNombre
				.addActionListener(new DlgBuscarApartados_txtNombre_actionAdapter(
						this));
		cmdBuscar.setText("BUSCAR");
		cmdBuscar
				.addActionListener(new DlgBuscarApartados_cmdBuscar_actionAdapter(
						this));
		pnlNorth.setLayout(borderLayout2);
		cmdContinuar.setText("CONTINUAR");
		cmdContinuar
				.addActionListener(new DlgBuscarApartados_cmdContinuar_actionAdapter(
						this));
		this.setModal(true);
		this.setTitle("Buscar apartados");
		scrollNotas.getViewport().setBackground(Color.white);
		tblApartados.setBackground(new Color(255, 240, 255));
		tblApartados.setFont(new java.awt.Font("Arial", Font.PLAIN, 11));
		tblApartados.setGridColor(Color.lightGray);
		tblApartados.setShowVerticalLines(false);
		getContentPane().add(panel1);
		pnlNorth.add(txtNombre, java.awt.BorderLayout.CENTER);
		pnlNorth.add(cmdBuscar, java.awt.BorderLayout.EAST);
		pnlNorth.add(jLabel1, java.awt.BorderLayout.WEST);
		panel1.add(pnlSouth, java.awt.BorderLayout.SOUTH);
		pnlSouth.add(cmdContinuar);
		panel1.add(pnlNorth, java.awt.BorderLayout.NORTH);
		panel1.add(scrollNotas, java.awt.BorderLayout.CENTER);
		scrollNotas.getViewport().add(tblApartados);

		tblApartados.setModel(modelDatosApartado1);
	}

	public void txtNombre_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		engine.buscarVentas(txtNombre.getText());
		this.modelDatosApartado1.data = engine.ventasView();
		modelDatosApartado1.fireTableDataChanged();
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdContinuar_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		// Seleccionamos la venta
		if (tblApartados.getSelectedRow() >= 0) {
			engine.seleccionarVenta(tblApartados.getSelectedRow());
			// BUSCAMOS LA FOTOGRAFIA
			this.setVisible(false);
		} else {
			this.setCursor(AppInstance.defCursor);
			JOptionPane.showMessageDialog(this,
					"Es necesario que elija una venta de apartado",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.WARNING_MESSAGE);
		}
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdBuscar_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		engine.buscarVentas(txtNombre.getText());
		this.modelDatosApartado1.data = engine.ventasView();
		modelDatosApartado1.fireTableDataChanged();
		this.setCursor(AppInstance.defCursor);
	}
}

class DlgBuscarApartados_cmdContinuar_actionAdapter implements ActionListener {
	private DlgBuscarApartados adaptee;

	DlgBuscarApartados_cmdContinuar_actionAdapter(DlgBuscarApartados adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdContinuar_actionPerformed(e);
	}
}

class DlgBuscarApartados_txtNombre_actionAdapter implements ActionListener {
	private DlgBuscarApartados adaptee;

	DlgBuscarApartados_txtNombre_actionAdapter(DlgBuscarApartados adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtNombre_actionPerformed(e);
	}
}

class DlgBuscarApartados_cmdBuscar_actionAdapter implements ActionListener {
	private DlgBuscarApartados adaptee;

	DlgBuscarApartados_cmdBuscar_actionAdapter(DlgBuscarApartados adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscar_actionPerformed(e);
	}
}
