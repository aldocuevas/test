package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.boutique.engine.impl.DividirInventarioEngine;
import com.boutique.domain.Boutique;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmDividirInventario extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel pnlTop = new JPanel();
	JLabel jLabel1 = new JLabel();
	JComboBox cmbBoutiques = new JComboBox();
	JButton cmdAceptar = new JButton();
	DividirInventarioEngine engine = new DividirInventarioEngine();
	JPanel pnlCenter = new JPanel();
	JTextField txtCodigoProducto = new JTextField();
	BorderLayout borderLayout2 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable tblProductosDivididos = new JTable();
	JPanel pnlSouth = new JPanel();
	JButton cmdCerrar = new JButton();
	ModelProductoaDividir modelProductoaDividir1 = new ModelProductoaDividir();
	JPopupMenu pop = new JPopupMenu();
	JMenuItem jMenuItem1 = new JMenuItem();
	JLabel jLabel2 = new JLabel();
	JLabel lblCantidad = new JLabel();

	public FrmDividirInventario() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("División de inventario");
		jLabel1.setText("Selecciona la sucursal a donde irá la mercancia seleccionada:");
		cmdAceptar.setText("Aceptar");
		cmdAceptar
				.addMouseListener(new FrmDividirInventario_cmdAceptar_mouseAdapter(
						this));
		txtCodigoProducto.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
		txtCodigoProducto.setText("");
		txtCodigoProducto
				.addActionListener(new FrmDividirInventario_txtCodigoProducto_actionAdapter(
						this));
		pnlCenter.setLayout(borderLayout2);
		pnlCenter.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
		cmdCerrar.setText("Cerrar");
		cmdCerrar
				.addMouseListener(new FrmDividirInventario_cmdCancelar_mouseAdapter(
						this));
		tblProductosDivididos.setModel(modelProductoaDividir1);
		tblProductosDivididos
				.addMouseListener(new FrmDividirInventario_tblProductosDivididos_mouseAdapter(
						this));
		jMenuItem1.setText("Regresar producto ");
		jMenuItem1
				.addActionListener(new FrmDividirInventario_jMenuItem1_actionAdapter(
						this));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		jLabel2.setText("Cantidad de productos:");
		lblCantidad.setFont(new java.awt.Font("Dialog", Font.BOLD, 18));
		lblCantidad.setText("jLabel3");
		pnlTop.add(jLabel1);
		pnlTop.add(cmbBoutiques);
		pnlTop.add(cmdAceptar);
		pnlTop.add(jLabel2);
		pnlTop.add(lblCantidad);
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

	public void cmdAceptar_mouseClicked(MouseEvent e) {
		// Seleccionaron la boutique a la que quieren enviar la mercancia
		if (cmbBoutiques.getSelectedIndex() >= 0) {
			this.setCursor(com.boutique.engine.impl.AppInstance.waitCursor);
			engine.setBoutiqueSeleccionada(cmbBoutiques.getSelectedIndex());
			engine.setProductosADividir();
			this.modelProductoaDividir1.setProductos(engine.productosView);
			this.lblCantidad.setText(String
					.valueOf(this.modelProductoaDividir1.data.size()));
			cmbBoutiques.setEnabled(false);
			cmdAceptar.setEnabled(false);
			// Ponemos habilitada la casilla para pasar los articulos y
			// dividirlos a la boutique seleccionada
			txtCodigoProducto.setEnabled(true);
			txtCodigoProducto.requestFocus();
			this.setCursor(com.boutique.engine.impl.AppInstance.defCursor);
		}
	}

	public void txtCodigoProducto_actionPerformed(ActionEvent e) {
		engine.agregarProductoaDividir(txtCodigoProducto.getText());
		txtCodigoProducto.setText("");
		this.modelProductoaDividir1.fireTableDataChanged();
		this.lblCantidad.setText(String
				.valueOf(this.modelProductoaDividir1.data.size()));
	}

	public void cmdCancelar_mouseClicked(MouseEvent e) {
		engine = null;
		this.setVisible(false);

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
			this.lblCantidad.setText(String
					.valueOf(this.modelProductoaDividir1.data.size()));
		} else {
			JOptionPane.showMessageDialog(this,
					"El producto no pudo ser regresado", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}

class FrmDividirInventario_tblProductosDivididos_mouseAdapter extends
		MouseAdapter {
	private FrmDividirInventario adaptee;

	FrmDividirInventario_tblProductosDivididos_mouseAdapter(
			FrmDividirInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.tblProductosDivididos_mouseClicked(e);
	}
}

class FrmDividirInventario_jMenuItem1_actionAdapter implements ActionListener {
	private FrmDividirInventario adaptee;

	FrmDividirInventario_jMenuItem1_actionAdapter(FrmDividirInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuItem1_actionPerformed(e);
	}
}

class FrmDividirInventario_cmdCancelar_mouseAdapter extends MouseAdapter {
	private FrmDividirInventario adaptee;

	FrmDividirInventario_cmdCancelar_mouseAdapter(FrmDividirInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.cmdCancelar_mouseClicked(e);
	}
}

class FrmDividirInventario_txtCodigoProducto_actionAdapter implements
		ActionListener {
	private FrmDividirInventario adaptee;

	FrmDividirInventario_txtCodigoProducto_actionAdapter(
			FrmDividirInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtCodigoProducto_actionPerformed(e);
	}
}

class FrmDividirInventario_cmdAceptar_mouseAdapter extends MouseAdapter {
	private FrmDividirInventario adaptee;

	FrmDividirInventario_cmdAceptar_mouseAdapter(FrmDividirInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.cmdAceptar_mouseClicked(e);
	}
}
