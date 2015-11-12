package com.boutique.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import com.boutique.engine.impl.AppInstance;
import com.boutique.view.AboutJPanel;
import com.boutique.view.DlgDatosConexion;
import com.boutique.view.DlgIniciarSesion;
import com.boutique.view.DlgResumenes;
import com.boutique.view.FrmBusquedaProducto;
import com.boutique.view.FrmControlApartados;
import com.boutique.view.FrmCorteDeCaja2;
import com.boutique.view.FrmDevoluciones;
import com.boutique.view.FrmEstadoCuenta;
import com.boutique.view.FrmRegistroEncargos;
import com.boutique.view.FrmVentaApartado;
import com.boutique.view.FrmVentaContado;
import com.boutique.view.FrmVentaCredito;
import com.boutique.view.salidas.FrmSalidasDineroTerminal;

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
 * sdfsdf
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class FrmPosBoutique2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel lblTitulo = new JLabel();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel(); // @jve:decl-index=0:visual-constraint="10,3196"
	JLabel jLabel1 = new JLabel();
	JLabel lblVendedor = new JLabel();
	JLabel lblSucursal = new JLabel();
	JLabel lblTerminal = new JLabel();

	JLabel jLabel4 = new JLabel();
	JLabel lblTerminalTitle = new JLabel();

	GridLayout gridLayout1 = new GridLayout(3, 3, 3, 3);
	JButton cmdVentasContado = new JButton();
	JButton cmdPagarApartados = new JButton();
	JButton cmdVentasApartado = new JButton();
	JButton cmdVentasCredito = new JButton();
	JButton cmdAbonos = new JButton();
	JButton cmdDevoluciones = new JButton();
	JButton cmdCorteDeCaja = new JButton();
	JButton cmdDiarioDeEntradas = new JButton();
	JButton cmdRegistrarEncargo = new JButton();
	JButton cmdBuscarProducto = new JButton();
	JButton cmdResumenProductos = new JButton();
	JButton cmdConfiguracion = new JButton();
	JButton cmdSalidas = new JButton();

	public FrmPosBoutique2() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		jPanel2.setSize(new Dimension(419, 26));
		lblTitulo.setBackground(Color.white);
		lblTitulo.setMinimumSize(new Dimension(200, 80));
		lblTitulo.setOpaque(true);
		lblTitulo.setPreferredSize(new Dimension(200, 80));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		// lblTitulo.setText(com.boutique.engine.AppInstance.nombreNegocio);
		lblTitulo.setIcon(new ImageIcon(com.boutique.view.FrmAppBoutique.class
				.getResource("img/logo.jpg")));
		jPanel1.setBackground(Color.white);
		jPanel1.setLayout(gridLayout1);
		lblVendedor.setToolTipText("");
		cmdVentasContado.setText("Venta de contado");
		cmdVentasContado
				.addActionListener(new FrmPosBoutique2_cmdVentasContado_actionAdapter(
						this));
		cmdVentasApartado.setText("Venta de apartado");
		cmdVentasApartado
				.addActionListener(new FrmPosBoutique2_cmdVentasApartado_actionAdapter(
						this));
		cmdVentasCredito.setText("Venta a credito");
		cmdVentasCredito
				.addActionListener(new FrmPosBoutique2_cmdVentasCredito_actionAdapter(
						this));
		cmdAbonos.setText("Abonos");
		cmdAbonos
				.addActionListener(new FrmPosBoutique2_cmdAbonos_actionAdapter(
						this));
		cmdPagarApartados.setText("Pagar apartado");
		cmdPagarApartados
				.addActionListener(new FrmPosBoutique2_cmdPagarApartados_actionAdapter(
						this));
		cmdDevoluciones.setText("Devoluciones");
		cmdDevoluciones
				.addActionListener(new FrmPosBoutique2_cmdDevoluciones_actionAdapter(
						this));
		cmdCorteDeCaja.setText("Corte de caja");
		cmdCorteDeCaja
				.addActionListener(new FrmPosBoutique2_cmdCorteDeCaja_actionAdapter(
						this));
		cmdDiarioDeEntradas.setEnabled(true);
		cmdDiarioDeEntradas.setText("Diario de entradas");
		cmdDiarioDeEntradas
				.addActionListener(new FrmPosBoutique2_cmdDiarioDeEntradas_actionAdapter(
						this));
		cmdRegistrarEncargo.setText("Registrar encargo");
		cmdRegistrarEncargo
				.addActionListener(new FrmPosBoutique2_cmdRegistrarEncargo_actionAdapter(
						this));
		gridLayout1.setColumns(3);
		gridLayout1.setHgap(2);
		gridLayout1.setRows(5);
		gridLayout1.setVgap(2);
		this.addWindowListener(new FrmPosBoutique2_this_windowAdapter(this));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Punto de venta");
		cmdBuscarProducto.setText("Buscar producto");
		cmdBuscarProducto
				.addActionListener(new FrmPosBoutique2_cmdBuscarProducto_actionAdapter(
						this));
		cmdResumenProductos.setText("Lista de productos");
		cmdResumenProductos
				.addActionListener(new FrmPosBoutique2_cmdResumenProductos_actionAdapter(
						this));
		cmdConfiguracion.setText("Configuracion");
		cmdConfiguracion
				.addActionListener(new FrmPosBoutique2_cmdConfiguracion_actionAdapter(
						this));
		cmdSalidas.setText("Salidas");
		cmdSalidas
				.addActionListener(new FrmPosBoutique2_cmdSalidas_actionAdapter(
						this));
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
		jPanel1.add(cmdVentasContado);
		jPanel1.add(cmdVentasCredito);
		jPanel1.add(cmdVentasApartado);
		jPanel1.add(cmdAbonos);
		jPanel1.add(cmdPagarApartados);
		jPanel1.add(cmdDevoluciones);
		jPanel1.add(cmdCorteDeCaja);
		jPanel1.add(cmdDiarioDeEntradas);
		jPanel1.add(cmdRegistrarEncargo);
		jPanel1.add(cmdBuscarProducto);
		jPanel1.add(cmdResumenProductos);
		jPanel1.add(cmdConfiguracion);
		jPanel1.add(cmdSalidas);
		jLabel1.setText("Vendedor:");
		this.getContentPane().add(lblTitulo, java.awt.BorderLayout.NORTH);
		this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
		lblVendedor.setText("");
		lblSucursal.setText("");
		jLabel4.setText("Boutique:");
		lblTerminalTitle.setText("Terminal:");
		lblTerminal.setText("");
		jPanel2.add(jLabel1);
		jPanel2.add(lblVendedor);
		jPanel2.add(jLabel4);
		jPanel2.add(lblSucursal);
		jPanel2.add(lblTerminalTitle);
		jPanel2.add(lblTerminal);
		jPanel2.add(new AboutJPanel());
	}

	public void cmdVentasContado_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmVentaContado frm = new FrmVentaContado();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void this_windowOpened(WindowEvent e) {
		DlgIniciarSesion dlg = new DlgIniciarSesion(this,
				com.boutique.engine.impl.AppInstance.nombreNegocio, true);
		dlg.setLocationRelativeTo(this);
		dlg.setSize(225, 195);
		dlg.setVisible(true);
		if (dlg.validado) {
			if (dlg.usuario.getVendedor() == 0 || dlg.usuario.getActivo() == 0) {
				JOptionPane.showMessageDialog(this,
						"Usuario inactivo o sin permisos como vendedor",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.WARNING_MESSAGE);
				this.setVisible(false);
				System.exit(0);

			}
		} else { // NO ESTA VALIDADO
			JOptionPane
					.showMessageDialog(
							this,
							"Debe introducir un usuario y contraseña válidos, la aplicación se cerrará",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		this.lblVendedor.setText(AppInstance.usuario().getNombre());
		this.lblSucursal.setText(AppInstance.boutique().getNombre());
		this.lblTerminal.setText(AppInstance.terminal().getNoTerminal());
	}

	public void cmdVentasCredito_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmVentaCredito frm = new FrmVentaCredito();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdVentasApartado_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmVentaApartado frm = new FrmVentaApartado();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdAbonos_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmEstadoCuenta frm = new FrmEstadoCuenta();
		frm.pack();
		frm.setSize(730, 700);
		frm.setLocationRelativeTo(this);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdPagarApartados_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmControlApartados frm = new FrmControlApartados();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdDevoluciones_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmDevoluciones frm = new FrmDevoluciones();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdCorteDeCaja_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmCorteDeCaja2 frm = new FrmCorteDeCaja2();
		// frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setSize(407, 650);
		frm.setLocationRelativeTo(this);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdDiarioDeEntradas_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		DlgResumenes dlg = new DlgResumenes(this, AppInstance.nombreNegocio,
				true);
		dlg.setSize(420, 130);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdRegistrarEncargo_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmRegistroEncargos frm = new FrmRegistroEncargos();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdBuscarProducto_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmBusquedaProducto frm = new FrmBusquedaProducto();
		frm.pack();
		frm.setSize(650, 300);
		frm.setLocationRelativeTo(this);
		// frm.setExtendedState(frm.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdResumenProductos_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("resumenProductos.jasper");
			// File fdesign = new
			// File(com.boutique.impresiones.ImpresionDevolucion.class.
			// getResource("resumenProductos.jasper").getPath());

			// String fileName =
			// "src\\com\\boutique\\impresiones\\resumenProductos.jasper";
			// Ruta de archivo pdf de destino

			// Ruta de archivo xls de destino
			// String destFileNameXls="C:\\SIAU\\reportes\\reporte1.xls";

			// Pasamos parametros al reporte Jasper.
			Map<String, Integer> parameters = new HashMap<String, Integer>();
			parameters.put("idBoutique", com.boutique.engine.impl.AppInstance
					.boutique().getId());
			// Preparacion del reporte (en esta etapa se inserta el valor
			JasperPrint listo = JasperFillManager.fillReport(st, parameters,
					com.boutique.dao.DaoSource.getConnection());
			JasperViewer vw = new JasperViewer(listo, false);
			// net.sf.jasperreports.view.JRViewer jrv = new
			// net.sf.jasperreports.view.JRViewer(listo);
			// jrv.setVisible(true);
			vw.setTitle("Lista de productos");
			this.setCursor(AppInstance.defCursor);
			vw.setVisible(true);
			vw.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setCursor(AppInstance.defCursor);
			// Creación del PDF
			// JasperExportManager.exportReportToPdfFile(listo,
			// destFileNamePdf);
			// JasperViewer.viewReport(listo);

			// Creación del XLS
			/*
			 * JRXlsExporter exporter = new JRXlsExporter();
			 * exporter.setParameter(JRExporterParameter.JASPER_PRINT,listo) ;
			 * exporter
			 * .setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"prueba.xls")
			 * ;
			 * exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET
			 * , Boolean.TRUE) ; //exporter.exportReport();
			 * exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}

	}

	public void cmdConfiguracion_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		DlgDatosConexion dlg = new DlgDatosConexion();
		dlg.pack();
		dlg.setSize(350, 250);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdSalidas_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmSalidasDineroTerminal dlg = new FrmSalidasDineroTerminal();
		dlg.pack();
		dlg.setSize(349, 400);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}
}

class FrmPosBoutique2_cmdConfiguracion_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdConfiguracion_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdConfiguracion_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdSalidas_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdSalidas_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdSalidas_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdResumenProductos_actionAdapter implements
		ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdResumenProductos_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdResumenProductos_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdBuscarProducto_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdBuscarProducto_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscarProducto_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdRegistrarEncargo_actionAdapter implements
		ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdRegistrarEncargo_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		adaptee.cmdRegistrarEncargo_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdDevoluciones_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdDevoluciones_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdDevoluciones_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdCorteDeCaja_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdCorteDeCaja_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCorteDeCaja_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdDiarioDeEntradas_actionAdapter implements
		ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdDiarioDeEntradas_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdDiarioDeEntradas_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdAbonos_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdAbonos_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAbonos_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdPagarApartados_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdPagarApartados_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdPagarApartados_actionPerformed(e);
	}
}

class FrmPosBoutique2_this_windowAdapter extends WindowAdapter {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_this_windowAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmPosBoutique2_cmdVentasCredito_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdVentasCredito_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVentasCredito_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdVentasApartado_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdVentasApartado_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVentasApartado_actionPerformed(e);
	}
}

class FrmPosBoutique2_cmdVentasContado_actionAdapter implements ActionListener {
	private final FrmPosBoutique2 adaptee;

	FrmPosBoutique2_cmdVentasContado_actionAdapter(FrmPosBoutique2 adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVentasContado_actionPerformed(e);
	}
}
