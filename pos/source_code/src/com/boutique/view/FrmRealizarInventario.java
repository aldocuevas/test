package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.VerticalFlowLayout;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.RevisionInventarioEngine;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.boutique.domain.Boutique;
import com.boutique.dao.*;
import java.awt.event.ActionEvent;
import com.boutique.impresiones.ImpresionRevisionInventario;
import java.awt.event.ActionListener;

/**
 * <p>
 * Title: boutique management
 * </p>
 * 
 * <p>
 * Description: Sistema de administracion de boitiques
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: SESTO
 * </p>
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class FrmRealizarInventario extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout mainLyt = new BorderLayout();
	JPanel northLyt = new JPanel();
	JPanel centerLyt = new JPanel();
	JPanel southLyt = new JPanel();
	JPanel southLyt2 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	DefaultComboBoxModel modelBoutiques = new DefaultComboBoxModel();
	DefaultComboBoxModel modelAuditores = new DefaultComboBoxModel();
	DefaultComboBoxModel modelEmpleados = new DefaultComboBoxModel();
	JPanel botonesLyt = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();
	JButton cmdSalir = new JButton();
	JPanel northLyt1 = new JPanel();
	BorderLayout borderLayout3 = new BorderLayout();
	GridLayout gridLayout1 = new GridLayout();
	JPanel northLeftLyt = new JPanel();
	JPanel northRightLyt = new JPanel();
	JPanel northNorthLyt = new JPanel();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout2 = new GridLayout();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JTextField txtFechaHora = new JTextField();
	JPanel jPanel2 = new JPanel();
	GridLayout gridLayout3 = new GridLayout();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
	JPanel jPanel3 = new JPanel();
	JButton cmdIniciarInventario = new JButton();
	JTabbedPane tbdPane = new JTabbedPane();
	BorderLayout borderLayout4 = new BorderLayout();
	JPanel pnlProductosEnInventario = new JPanel();
	JPanel pnlProductosEncontrados = new JPanel();
	JPanel pnlProductosNoEncontrados = new JPanel();
	JPanel pnlBusqueda = new JPanel();
	BorderLayout borderLayout5 = new BorderLayout();
	JLabel jLabel6 = new JLabel();
	JTextField txtProducto = new JTextField();
	JPanel totalesLyt = new JPanel();
	JTextField txtProductosEncontrados2 = new JTextField();
	JLabel lblProductosEncontrados = new JLabel();
	JTextField txtProductosNoEncontrados = new JTextField();
	JLabel lblProductosFaltantes = new JLabel();
	JTextField txtMontoInicial = new JTextField();
	JLabel lblTotalProductosInicial = new JLabel();
	JTextField txtTotalProductosInicial = new JTextField();
	GridLayout gridLayout4 = new GridLayout();
	BorderLayout borderLayout6 = new BorderLayout();
	JPanel pnlBotones = new JPanel();
	BorderLayout borderLayout7 = new BorderLayout();

	BorderLayout borderLayout8 = new BorderLayout();
	JPanel pnlBotonesNoEncontrados = new JPanel();
	JButton cmdAgregarInventario = new JButton();
	JButton cmdIgnorar = new JButton();
	JPanel jPanel4 = new JPanel();
	BorderLayout borderLayout9 = new BorderLayout();
	JPanel jPanel5 = new JPanel();
	JPanel jPanel6 = new JPanel();
	BorderLayout borderLayout10 = new BorderLayout();
	JPanel jPanel7 = new JPanel();
	GridLayout gridLayout5 = new GridLayout();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable tblProductosEncontrados = new JTable();
	JScrollPane jScrollPane2 = new JScrollPane();
	JTable tblProductosEnInventario = new JTable();
	JTable tblProductosNoEncontrados = new JTable();
	JScrollPane jScrollPane3 = new JScrollPane();
	JComboBox cmbBoutiques = new JComboBox();
	JPanel jPanel8 = new JPanel();
	VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
	JButton cmdTerminarInventario = new JButton();
	JButton cmdImprimirInventario = new JButton();
	ModelDatosProductoEnInventario modelDatosProductoEnInventario1 = new ModelDatosProductoEnInventario();
	ModelDatosProductoEnInventario modelDatosProductoIniciales = new ModelDatosProductoEnInventario();

	JComboBox cmbAuditores = new JComboBox();
	JComboBox cmbEmpleados = new JComboBox();
	RevisionInventarioEngine engine = new RevisionInventarioEngine();
	ModelDatosProductoTemporal modelDatosProductoNoEncontrado = new ModelDatosProductoTemporal();
	ModelDatosProductoTemporal modelDatosProductoEncontrado = new ModelDatosProductoTemporal();
	JPanel pnlProductosIniciales = new JPanel();
	BorderLayout borderLayout11 = new BorderLayout();
	JScrollPane jScrollPane4 = new JScrollPane();
	JTable tblProductosIniciales = new JTable();
	JLabel lblProductosNoEncontrados = new JLabel();
	JTextField txtMontoFaltante = new JTextField();
	JLabel lblMontoInicial = new JLabel();
	JLabel lblMontoFaltante = new JLabel();
	JTextField txtProductosFaltantes = new JTextField();
	JPanel jPanel9 = new JPanel();
	JPanel jPanel10 = new JPanel();
	BorderLayout borderLayout12 = new BorderLayout();
	JPanel pnlBotonRegistrarMerma = new JPanel();
	JButton cmdRegistrarMermas2 = new JButton();

	public FrmRealizarInventario() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		getContentPane().setLayout(mainLyt);
		this.setTitle("REVISION DE INVENTARIO");
		this.addWindowListener(new FrmRealizarInventario_this_windowAdapter(
				this));
		southLyt.setLayout(borderLayout1);
		southLyt2.setLayout(borderLayout2);
		cmdSalir.setText("SALIR");
		cmdSalir.addActionListener(new FrmRealizarInventario_cmdSalir_actionAdapter(
				this));
		northLyt.setLayout(borderLayout3);
		northLyt1.setLayout(gridLayout1);
		gridLayout1.setColumns(2);
		jLabel1.setToolTipText("");
		jLabel1.setText("REVISION DE INVENTARIO");
		jPanel1.setLayout(gridLayout2);
		gridLayout2.setColumns(2);
		gridLayout2.setRows(2);
		jLabel2.setText("Fecha y hora:");
		jLabel3.setText("Boutique:");
		txtFechaHora.setText("");
		jPanel2.setLayout(gridLayout3);
		jLabel4.setText("Auditor:");
		jLabel5.setText("Empleado:");
		gridLayout3.setColumns(2);
		gridLayout3.setRows(2);
		northLeftLyt.setLayout(verticalFlowLayout1);
		cmdIniciarInventario.setText("INICIAR INVENTARIO");
		cmdIniciarInventario
				.addActionListener(new FrmRealizarInventario_cmdIniciarInventario_actionAdapter(
						this));

		centerLyt.setLayout(borderLayout4);
		pnlBusqueda.setLayout(borderLayout5);
		jLabel6.setText("Indica el producto");
		txtProducto.setText("");
		txtProducto
				.addActionListener(new FrmRealizarInventario_txtProducto_actionAdapter(
						this));
		txtProductosEncontrados2.setFont(new java.awt.Font("Dialog", Font.BOLD,
				20));
		txtProductosEncontrados2.setEditable(false);
		txtProductosEncontrados2.setText("");
		lblProductosEncontrados.setText("Productos encontrados:");
		txtProductosNoEncontrados.setFont(new java.awt.Font("Dialog",
				Font.BOLD, 20));
		txtProductosNoEncontrados.setEditable(false);
		txtProductosNoEncontrados.setText("");
		lblProductosFaltantes.setText("Productos faltantes:");
		txtMontoInicial.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtMontoInicial.setEditable(false);
		txtMontoInicial.setText("");
		lblTotalProductosInicial.setText("Productos iniciales:");
		txtTotalProductosInicial.setFont(new java.awt.Font("Dialog", Font.BOLD,
				20));
		txtTotalProductosInicial.setEditable(false);
		txtTotalProductosInicial.setText("");
		gridLayout4.setColumns(6);
		gridLayout4.setRows(2);
		totalesLyt.setLayout(gridLayout4);

		pnlProductosEnInventario.setLayout(borderLayout6);
		pnlProductosEncontrados.setLayout(borderLayout7);
		pnlProductosNoEncontrados.setLayout(borderLayout8);
		cmdAgregarInventario.setText("AGREGAR AL INVENTARIO");
		cmdAgregarInventario
				.addActionListener(new FrmRealizarInventario_cmdAgregarInventario_actionAdapter(
						this));
		cmdIgnorar.setText("IGNORAR");
		cmdIgnorar
				.addActionListener(new FrmRealizarInventario_cmdIgnorar_actionAdapter(
						this));
		jPanel4.setLayout(borderLayout9);
		jPanel6.setLayout(borderLayout10);
		jPanel7.setLayout(gridLayout5);
		northRightLyt.setLayout(verticalFlowLayout2);
		cmdTerminarInventario.setText("TERMINAR INVENTARIO");
		cmdTerminarInventario
				.addActionListener(new FrmRealizarInventario_cmdTerminarInventario_actionAdapter(
						this));
		cmdImprimirInventario.setText("IMPRIMIR INVENTARIO");
		cmdImprimirInventario
				.addActionListener(new FrmRealizarInventario_cmdImprimirInventario_actionAdapter(
						this));
		tblProductosEnInventario.setModel(modelDatosProductoEnInventario1);
		cmbAuditores.setModel(modelAuditores);
		cmbEmpleados.setModel(modelEmpleados);
		cmbBoutiques.setModel(modelBoutiques);
		tblProductosEncontrados.setModel(modelDatosProductoEncontrado);
		tblProductosNoEncontrados.setModel(modelDatosProductoNoEncontrado);
		pnlProductosIniciales.setLayout(borderLayout11);
		tblProductosIniciales.setModel(modelDatosProductoIniciales);
		lblProductosNoEncontrados.setText("Prod. no encontrados:");
		txtMontoFaltante.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		txtMontoFaltante.setForeground(Color.red);
		txtMontoFaltante.setEditable(false);
		txtMontoFaltante.setText("");
		lblMontoInicial.setText("Monto inicial:");
		lblMontoFaltante.setText("Monto faltante:");
		txtProductosFaltantes
				.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		txtProductosFaltantes.setDisabledTextColor(Color.black);
		txtProductosFaltantes.setEditable(false);
		txtProductosFaltantes.setText("");
		southLyt2.setMinimumSize(new Dimension(117, 80));
		totalesLyt.setMaximumSize(new Dimension(300, 300));
		jPanel10.setLayout(borderLayout12);
		cmdRegistrarMermas2.setText("REGISTRAR MERMAS");

		cmdRegistrarMermas2
				.addActionListener(new FrmRealizarInventario_cmdRegistrarMermas2_actionAdapter(
						this));
		this.getContentPane().add(centerLyt, java.awt.BorderLayout.CENTER);
		this.getContentPane().add(southLyt, java.awt.BorderLayout.SOUTH);
		this.getContentPane().add(northLyt, java.awt.BorderLayout.NORTH);
		southLyt.add(southLyt2, java.awt.BorderLayout.CENTER);
		southLyt2.add(botonesLyt, java.awt.BorderLayout.EAST);
		botonesLyt.add(cmdSalir);
		northLyt.add(northLyt1, java.awt.BorderLayout.CENTER);
		northLyt1.add(northLeftLyt);
		northLeftLyt.add(jPanel1);
		jPanel1.add(jLabel2);
		jPanel1.add(txtFechaHora);
		jPanel1.add(jLabel3);
		jPanel1.add(cmbBoutiques);
		northLeftLyt.add(jPanel3);
		jPanel3.add(cmdIniciarInventario);
		northLyt1.add(northRightLyt);
		northRightLyt.add(jPanel2);
		northRightLyt.add(jPanel8);
		jPanel8.add(cmdTerminarInventario);
		jPanel8.add(cmdImprimirInventario);
		jPanel2.add(jLabel4);
		jPanel2.add(cmbAuditores);
		jPanel2.add(jLabel5);
		jPanel2.add(cmbEmpleados);
		northLyt.add(northNorthLyt, java.awt.BorderLayout.NORTH);
		northNorthLyt.add(jLabel1);
		centerLyt.add(tbdPane, java.awt.BorderLayout.CENTER);
		centerLyt.add(pnlBusqueda, java.awt.BorderLayout.NORTH);
		pnlBusqueda.add(jLabel6, java.awt.BorderLayout.WEST);
		pnlBusqueda.add(txtProducto, java.awt.BorderLayout.CENTER);

		totalesLyt.add(lblTotalProductosInicial);
		totalesLyt.add(txtTotalProductosInicial);
		totalesLyt.add(lblProductosFaltantes);
		totalesLyt.add(txtProductosFaltantes);
		totalesLyt.add(lblProductosEncontrados);
		totalesLyt.add(txtProductosEncontrados2);
		totalesLyt.add(lblMontoInicial);
		totalesLyt.add(txtMontoInicial);
		totalesLyt.add(lblMontoFaltante);
		totalesLyt.add(txtMontoFaltante);
		totalesLyt.add(lblProductosNoEncontrados);
		totalesLyt.add(txtProductosNoEncontrados);
		pnlBotonesNoEncontrados.add(cmdIgnorar);
		pnlBotonesNoEncontrados.add(cmdAgregarInventario);
		jPanel4.add(jPanel5, java.awt.BorderLayout.EAST);

		jPanel4.add(pnlBotonesNoEncontrados, java.awt.BorderLayout.WEST);
		pnlProductosNoEncontrados
				.add(jScrollPane3, java.awt.BorderLayout.NORTH);
		jScrollPane3.getViewport().add(tblProductosNoEncontrados);
		jPanel6.add(pnlBotones, java.awt.BorderLayout.WEST);
		jPanel6.add(jPanel7, java.awt.BorderLayout.EAST);
		pnlProductosEncontrados.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(tblProductosEncontrados);
		pnlProductosEncontrados.add(jPanel6, java.awt.BorderLayout.SOUTH);

		jScrollPane2.getViewport().add(tblProductosEnInventario);
		pnlProductosNoEncontrados.add(jPanel4, java.awt.BorderLayout.SOUTH);

		tbdPane.add(pnlProductosEnInventario, "PRODUCTOS A BUSCAR");
		tbdPane.add(pnlProductosEncontrados, "PRODUCTOS ENCONTRADOS");
		tbdPane.add(pnlProductosNoEncontrados, "PRODUCTOS NO ENCONTRADOS");
		tbdPane.add(pnlProductosIniciales, "PRODUCTOS INICIALES");
		pnlProductosIniciales.add(jScrollPane4, java.awt.BorderLayout.CENTER);
		jScrollPane4.getViewport().add(tblProductosIniciales);
		southLyt2.add(totalesLyt, java.awt.BorderLayout.WEST);
		southLyt2.add(jPanel9, java.awt.BorderLayout.CENTER);
		pnlProductosEnInventario
				.add(jScrollPane2, java.awt.BorderLayout.CENTER);
		pnlProductosEnInventario.add(jPanel10, java.awt.BorderLayout.SOUTH);
		jPanel10.add(pnlBotonRegistrarMerma, java.awt.BorderLayout.WEST);
		pnlBotonRegistrarMerma.add(cmdRegistrarMermas2);
		totalesLyt.setPreferredSize(new Dimension(800, 100));
	}

	public void this_windowOpened(WindowEvent e) {
		java.util.List<Integer> auditores = engine.getAuditores();
		for (Integer id : auditores) {
			modelAuditores.addElement(AppInstance.idToNombreUsuario.get(id));
		}
		java.util.List<Integer> empleados = engine.getNoAuditores();
		for (Integer id : empleados) {
			modelEmpleados.addElement(AppInstance.idToNombreUsuario.get(id));
		}
		java.util.List<Boutique> boutiques = engine.getBoutiques();
		for (Boutique b : boutiques) {
			modelBoutiques.addElement(b.getNombre());
		}
		txtFechaHora.setText(AppInstance.formatoLargo.format(DaoSource
				.getFechaActual()));
		cmdTerminarInventario.setEnabled(false);
		cmdImprimirInventario.setEnabled(false);
		tbdPane.setEnabled(false);
		txtProducto.setEnabled(false);
		cmdRegistrarMermas2.setEnabled(false);
		cmdIgnorar.setEnabled(false);
		this.cmdAgregarInventario.setEnabled(false);

	}

	public void cmdIniciarInventario_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		txtFechaHora.setEnabled(false);
		cmbAuditores.setEnabled(false);
		cmbEmpleados.setEnabled(false);
		this.cmbBoutiques.setEnabled(false);
		cmdTerminarInventario.setEnabled(true);
		tbdPane.setEnabled(true);
		cmdIniciarInventario.setEnabled(false);
		txtProducto.setEnabled(true);
		txtProducto.requestFocus();
		// INICIAMOS EL INVENTARIO
		engine.iniciarInventario(cmbAuditores.getSelectedIndex(),
				cmbEmpleados.getSelectedIndex(),
				cmbBoutiques.getSelectedIndex());
		// BUSCAMOS LOS PRODUCTOS EN INVENTARIO
		engine.findProductosEnInventario();
		this.modelDatosProductoEnInventario1.data = engine.inventario
				.getProductosInventario();
		this.modelDatosProductoEnInventario1.fireTableDataChanged();
		this.modelDatosProductoIniciales.data = engine.inventario
				.getProductosInventario();
		this.modelDatosProductoIniciales.fireTableDataChanged();

		this.txtTotalProductosInicial.setText(String
				.valueOf(engine.inventario.cantidadProductosInicial));
		this.txtProductosFaltantes.setText(String
				.valueOf(engine.inventario.cantidadProductosFaltantes));
		this.txtMontoInicial.setText(AppInstance.number
				.format(engine.inventario.montoInicial));
		this.txtMontoFaltante.setText(AppInstance.number
				.format(engine.inventario.montoFaltante));
		// ACTUALIZAMOS LA LISTA
		// PONEMOS LOS TOTALES
		this.setCursor(AppInstance.defCursor);
	}

	public void txtProducto_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		engine.cotejarProducto(txtProducto.getText());
		this.txtMontoFaltante.setText(AppInstance.number
				.format(engine.inventario.montoFaltante));
		this.txtProductosNoEncontrados.setText(String.valueOf(engine.inventario
				.getProductosNoEncontrados().size()));
		this.txtProductosEncontrados2.setText(String.valueOf(engine.inventario
				.getProductosEncontrados().size()));

		this.txtProductosFaltantes.setText(String
				.valueOf(engine.inventario.cantidadProductosFaltantes));
		txtProducto.setText("");

		this.modelDatosProductoEnInventario1.data = engine.inventario
				.getProductosInventario();
		this.modelDatosProductoEncontrado.data = engine.inventario
				.getProductosEncontrados();
		this.modelDatosProductoNoEncontrado.data = engine.inventario
				.getProductosNoEncontrados();
		this.modelDatosProductoEncontrado.fireTableDataChanged();
		this.modelDatosProductoEnInventario1.fireTableDataChanged();
		this.modelDatosProductoNoEncontrado.fireTableDataChanged();
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdTerminarInventario_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		// PARA TERMINAR EL INVENTARIO HAY QUE HACER LO SIGUIENTE
		if (engine.finalizarInventario()) {
			// HABILITAR LAS OPCIONES PARA REGISTRAR COMO MERMA LOS PRODUCTOS
			// FALTANTES
			this.cmdIgnorar.setEnabled(true);
			this.cmdImprimirInventario.setEnabled(true);
			this.cmdRegistrarMermas2.setEnabled(true);
			this.cmdTerminarInventario.setEnabled(false);
			this.cmdAgregarInventario.setEnabled(true);
			// HABILITAR LA OPCION DE IMPRIMIR INVENTARIO.
			// HABILITAR EL BOTON DE IGNORAR Y DE AGREGAR AL INVENTARIO A LOS
			// PRODUCTOS NO ENCONTRADOS.
		} else {
			JOptionPane.showMessageDialog(this,
					"No se pudo registrar el término del inventario",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdRegistrarMermas2_actionPerformed(ActionEvent e) {
		// MOSTRAMOS MENSAJE DE MERMAS REGISTRADAS
		int i = JOptionPane
				.showConfirmDialog(
						this,
						"¿Está seguro de que deseas registrar  los productos como mermas?",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			i = JOptionPane
					.showConfirmDialog(
							this,
							"Si das clic en si, los productos ya no aparecerán en el inventario. ¿Estás seguro?",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				this.setCursor(AppInstance.waitCursor);
				if (engine.registrarMermas()) {
					cmdRegistrarMermas2.setEnabled(false);
					this.setCursor(AppInstance.defCursor);
				} else {
					this.setCursor(AppInstance.defCursor);
					JOptionPane
							.showMessageDialog(
									this,
									"NO SE PUDIERON REGISTRAR LOS PRODUCTOS COMO MERMAS",
									com.boutique.engine.impl.AppInstance.nombreNegocio,
									JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	public void cmdIgnorar_actionPerformed(ActionEvent e) {
		// AL IGNORAR ESTOS PRODUCTOS NO HACEMOS NADA... SOLO DESHABILITAMOS EL
		// BOTON TANTO DE IGNORAR COMO DE AGREGAR AL INVENTARIO
		int i = JOptionPane.showConfirmDialog(this,
				"¿Está seguro de ignorar los productos no encontrados?",
				com.boutique.engine.impl.AppInstance.nombreNegocio,
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			cmdIgnorar.setEnabled(false);
			this.cmdAgregarInventario.setEnabled(false);
		}
	}

	public void cmdAgregarInventario_actionPerformed(ActionEvent e) {
		if (engine.agregarAlInventario()) {
			JOptionPane.showMessageDialog(this,
					"PRODUCTOS AGREGADOS EXITOSAMENTE",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.INFORMATION_MESSAGE);
			cmdAgregarInventario.setEnabled(false);
			cmdIgnorar.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(this,
					"NO SE PUDIERON REGRISTRAR LOS PRODUCTOS EN EL INVENTARIO",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void this_windowClosed(WindowEvent e) {
	}

	public void this_windowClosing(WindowEvent e) {
		if (!engine.terminado) {
			JOptionPane.showMessageDialog(this,
					"EL INVENTARIO SE DARA POR TERMINADO",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			engine.finalizarInventario();

		}

	}

	public void cmdImprimirInventario_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		@SuppressWarnings("unused")
		ImpresionRevisionInventario inv = new ImpresionRevisionInventario(
				engine);
		ImpresionRevisionInventario.imprimir();
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdSalir_actionPerformed(ActionEvent e) {
		if (!engine.terminado) {
			JOptionPane.showMessageDialog(this,
					"EL INVENTARIO SE DARA POR TERMINADO",
					com.boutique.engine.impl.AppInstance.nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			this.setCursor(AppInstance.waitCursor);
			engine.finalizarInventario();
			this.setCursor(AppInstance.defCursor);

		}
		this.setVisible(false);

	}
}

class FrmRealizarInventario_cmdSalir_actionAdapter implements ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdSalir_actionAdapter(FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalir_actionPerformed(e);
	}
}

class FrmRealizarInventario_cmdAgregarInventario_actionAdapter implements
		ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdAgregarInventario_actionAdapter(
			FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAgregarInventario_actionPerformed(e);
	}
}

class FrmRealizarInventario_cmdIgnorar_actionAdapter implements ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdIgnorar_actionAdapter(FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdIgnorar_actionPerformed(e);
	}
}

class FrmRealizarInventario_cmdRegistrarMermas2_actionAdapter implements
		ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdRegistrarMermas2_actionAdapter(
			FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdRegistrarMermas2_actionPerformed(e);
	}
}

class FrmRealizarInventario_cmdTerminarInventario_actionAdapter implements
		ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdTerminarInventario_actionAdapter(
			FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdTerminarInventario_actionPerformed(e);
	}
}

class FrmRealizarInventario_txtProducto_actionAdapter implements ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_txtProducto_actionAdapter(
			FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.txtProducto_actionPerformed(e);
	}
}

class FrmRealizarInventario_cmdIniciarInventario_actionAdapter implements
		ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdIniciarInventario_actionAdapter(
			FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdIniciarInventario_actionPerformed(e);
	}
}

class FrmRealizarInventario_this_windowAdapter extends WindowAdapter {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_this_windowAdapter(FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void windowClosing(WindowEvent e) {
		adaptee.this_windowClosing(e);
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmRealizarInventario_cmdImprimirInventario_actionAdapter implements
		ActionListener {
	private FrmRealizarInventario adaptee;

	FrmRealizarInventario_cmdImprimirInventario_actionAdapter(
			FrmRealizarInventario adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.cmdImprimirInventario_actionPerformed(e);
	}
}
