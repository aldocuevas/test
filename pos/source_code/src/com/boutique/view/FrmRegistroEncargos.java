package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.boutique.domain.Boutique;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.boutique.engine.impl.RotacionInventarioEngine;

public class FrmRegistroEncargos extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlTop = new JPanel();
	JLabel jLabel1 = new JLabel();
	JComboBox cmbBoutiques = new JComboBox();
	JButton cmdAceptar = new JButton();
	RotacionInventarioEngine engine = new RotacionInventarioEngine();
	JPanel pnlCenter = new JPanel();
	JTextField txtCodigoProducto = new JTextField();
	BorderLayout borderLayout2 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable tblProductosDivididos = new JTable();
	JPanel pnlSouth = new JPanel();
	JButton cmdCerrar = new JButton();
	ModelRegistroEncargo modelProductoaDividir1 = new ModelRegistroEncargo();
	JPopupMenu pop = new JPopupMenu();
	JMenuItem jMenuItem1 = new JMenuItem();

	public FrmRegistroEncargos() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("REGISTRO DE ENCARGOS");
		jLabel1.setText("Selecciona la sucursal a donde irá la mercancia seleccionada:");
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addActionListener(new FrmRegistroEncargos_cmdAceptar_actionAdapter(
						this));
		txtCodigoProducto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtCodigoProducto.setText("");
		txtCodigoProducto
				.addActionListener(new FrmRegistroEncargos_txtCodigoProducto_actionAdapter(
						this));
		pnlCenter.setLayout(borderLayout2);
		pnlCenter.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		cmdCerrar.setEnabled(false);
		cmdCerrar.setText("ENVIAR MERCANCIA AHORA");
		cmdCerrar
				.addActionListener(new FrmRegistroEncargos_cmdCerrar_actionAdapter(
						this));

		tblProductosDivididos.setModel(modelProductoaDividir1);
		tblProductosDivididos
				.addMouseListener(new FrmRegistroEncargos_tblProductosDivididos_mouseAdapter(
						this));
		jMenuItem1.setText("Regresar producto ");
		jMenuItem1
				.addActionListener(new FrmRegistroEncargos_jMenuItem1_actionAdapter(
						this));
		pnlTop.add(jLabel1);
		pnlTop.add(cmbBoutiques);
		pnlTop.add(cmdAceptar);
		this.getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
		pnlCenter.add(txtCodigoProducto, java.awt.BorderLayout.NORTH);
		pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(tblProductosDivididos);
		this.getContentPane().add(pnlSouth, java.awt.BorderLayout.SOUTH);
		pnlSouth.add(cmdCerrar);
		this.getContentPane().add(pnlTop, java.awt.BorderLayout.NORTH);
		pop.add(jMenuItem1);
		for (Boutique b : engine.getBoutiques()) {
			cmbBoutiques.addItem(b.getNombre());
		}
		this.txtCodigoProducto.setEnabled(false);
		this.modelProductoaDividir1.data = this.engine.productosView;
	}

	public void txtCodigoProducto_actionPerformed(ActionEvent e) {

		engine.agregarProductoaRotar(txtCodigoProducto.getText());
		txtCodigoProducto.setText("");
		this.modelProductoaDividir1.fireTableDataChanged();
		if (engine.productosView.size() > 0) {
			this.cmdCerrar.setEnabled(true);
		}
	}

	public void cmdCancelar_mouseClicked(MouseEvent e) {
		engine = null;
		this.setVisible(false);

	}

	public void cmdRealizarDivision_mouseClicked(MouseEvent e) {
		// Realizamos la division

	}

	public void tblProductosDivididos_mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.pop.show(tblProductosDivididos, e.getX(), e.getY());
		}
	}

	public void jMenuItem1_actionPerformed(ActionEvent e) {
		// Regresamos el producto al inventario inicial

		if (engine
				.regresarProducto(this.tblProductosDivididos.getSelectedRow())) {
			this.modelProductoaDividir1.setProductos(engine.productosView);
		} else {
			JOptionPane.showMessageDialog(this,
					"El producto no pudo ser regresado", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cmdCerrar_actionPerformed(ActionEvent e) {
		if (engine.cerrarRotacion()) {
			JOptionPane.showMessageDialog(this,
					"Proceso realizado exitosamente", "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
	}

	public void cmdAceptar_actionPerformed(ActionEvent e) {
		// Seleccionaron la boutique a la que quieren enviar la mercancia
		if (cmbBoutiques.getSelectedIndex() >= 0) {
			engine.setBoutiqueSeleccionada(cmbBoutiques.getSelectedIndex());
			engine.inciarRotacion();

			this.modelProductoaDividir1.setProductos(engine.productosView);
			if (engine.productosView.size() > 0) {
				this.cmdCerrar.setEnabled(true);
			}

			cmbBoutiques.setEnabled(false);
			cmdAceptar.setEnabled(false);
			// Ponemos habilitada la casilla para pasar los articulos y
			// dividirlos a la boutique seleccionada
			txtCodigoProducto.setEnabled(true);
			txtCodigoProducto.requestFocus();
		}
	}
}

class FrmRegistroEncargos_cmdAceptar_actionAdapter implements ActionListener {
	private FrmRegistroEncargos adaptee;

	FrmRegistroEncargos_cmdAceptar_actionAdapter(FrmRegistroEncargos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAceptar_actionPerformed(e);
	}
}

class FrmRegistroEncargos_cmdCerrar_actionAdapter implements ActionListener {
	private FrmRegistroEncargos adaptee;

	FrmRegistroEncargos_cmdCerrar_actionAdapter(FrmRegistroEncargos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCerrar_actionPerformed(e);
	}
}

class FrmRegistroEncargos_tblProductosDivididos_mouseAdapter extends
		MouseAdapter {
	private FrmRegistroEncargos adaptee;

	FrmRegistroEncargos_tblProductosDivididos_mouseAdapter(
			FrmRegistroEncargos adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.tblProductosDivididos_mouseClicked(e);
	}
}

class FrmRegistroEncargos_jMenuItem1_actionAdapter implements ActionListener {
	private FrmRegistroEncargos adaptee;

	FrmRegistroEncargos_jMenuItem1_actionAdapter(FrmRegistroEncargos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuItem1_actionPerformed(e);
	}
}

class FrmRegistroEncargos_txtCodigoProducto_actionAdapter implements
		ActionListener {
	private FrmRegistroEncargos adaptee;

	FrmRegistroEncargos_txtCodigoProducto_actionAdapter(
			FrmRegistroEncargos adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtCodigoProducto_actionPerformed(e);
	}
}
