package com.boutique.view;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boutique.engine.impl.*;
import com.boutique.enums.TipoReporteFacturacion;
import com.boutique.reportes.facturacion.dto.FacturaClienteIndividualDTO;
import com.boutique.reportes.facturacion.dto.FacturaDatosGeneralesDTO;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.*;

import com.boutique.dao.DaoSource;
import com.boutique.dao.DaoClientesCentral;
import com.boutique.domain.InterfaceGrafica;
import com.boutique.domain.VentaTypeEnum;

public class FrmCreditEngine extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReporteFacturacionEngine rfEngine;
	public FrmCreditEngine() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void main(String ar[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				FrmCreditEngine frm = new FrmCreditEngine();
				frm.pack();
				frm.setSize(400, 700);
				frm.setVisible(true);
			}
		});

	}

	private void jbInit() throws Exception {
		DaoSource.iniciar();
		if (DaoSource.ip == null || DaoSource.ip.equals("")) {
			// SIGNIFICA QUE NO HAY CONECTIVIDAD CORRECTA, ABRIMOS EL CUADRO DE
			// DIALOGO DE CONEXION
			javax.swing.JOptionPane
					.showMessageDialog(
							null,
							"NO EXISTE CONFIGURACION DE CONECTIVIDAD, SE PROCEDERA A CONFIGURAR LA CONEXION",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.WARNING_MESSAGE);
			DlgDatosConexion dlg = new DlgDatosConexion();
			dlg.setSize(350, 250);
			dlg.setModal(true);
			dlg.setVisible(true);

		}

		AppInstance.iniciarApp();
		AppInstance.interfaceGrafica = InterfaceGrafica.ADMIN;

		rfEngine = new ReporteFacturacionEngine();

		// this.setTitle(AppInstance.nombreNegocio +
		// " - Módulo de administración");
		getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new FrmCreditEngine_this_windowAdapter(this));
		cmdVPlacetas.setBounds(new Rectangle(33, 77, 103, 23));
		cmdVPlacetas.setText("VENTAS");
		cmdVPlacetas
				.addActionListener(new FrmCreditEngine_cmdVPlacetas_actionAdapter(
						this));
		cmdPPlacetas.setBounds(new Rectangle(157, 77, 103, 23));
		cmdPPlacetas.setText("PAGOS");
		cmdPPlacetas
				.addActionListener(new FrmCreditEngine_cmdPPlacetas_actionAdapter(
						this));
		cmdCancelaciones.setBounds(new Rectangle(32, 110, 228, 34));
		cmdCancelaciones.setText("DEVOLUCIONES");
		cmdCancelaciones
				.addActionListener(new FrmCreditEngine_cmdCancelaciones_actionAdapter(
						this));
		cmdBuscarProducto.setBounds(new Rectangle(33, 152, 226, 34));
		cmdBuscarProducto.setText("BUSCAR PRODUCTO");
		cmdBuscarProducto
				.addActionListener(new FrmCreditEngine_cmdBuscarProducto_actionAdapter(
						this));
		jLabel1.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
		jLabel1.setText("Selecciona la sucursal:");
		jLabel1.setBounds(new Rectangle(31, 15, 199, 22));
		cmdClientes.setBounds(new Rectangle(34, 189, 108, 27));
		cmdClientes.setText("CLIENTES");
		cmdClientes
				.addActionListener(new FrmCreditEngine_cmdClientes_actionAdapter(
						this));
		cmdColonias.setBounds(new Rectangle(151, 189, 108, 28));
		cmdColonias.setText("COLONIAS");
		cmdColonias
				.addActionListener(new FrmCreditEngine_cmdColonias_actionAdapter(
						this));
		cmdEstadosPorRuta.setBounds(new Rectangle(36, 261, 225, 35));
		cmdEstadosPorRuta.setText("HISTORIAL DE CLIENTES");
		cmdEstadosPorRuta
				.addActionListener(new FrmCreditEngine_cmdEstadosPorRuta_actionAdapter(
						this));
		cmdResumenes.setText("RESUMENES");
		cmdResumenes
				.addActionListener(new FrmCreditEngine_cmdResumenes_actionAdapter(
						this));
		cmdResumenes.setBounds(new Rectangle(36, 304, 225, 35));
		cmdCambiarPrecios.setBounds(new Rectangle(36, 342, 225, 35));
		cmdCambiarPrecios.setToolTipText("");
		cmdCambiarPrecios.setText("CAMBIAR PRECIOS");
		cmdCambiarPrecios
				.addActionListener(new FrmCreditEngine_cmdCambiarPrecios_actionAdapter(
						this));
		cmdGenerarVale.setBounds(new Rectangle(36, 379, 225, 35));
		cmdGenerarVale.setText("GENERAR VALE");
		cmdGenerarVale
				.addActionListener(new FrmCreditEngine_cmdGenerarVale_actionAdapter(
						this));
		cmdUsuarios.setBounds(new Rectangle(36, 416, 225, 35));
		cmdUsuarios.setText("USUARIOS");
		cmdUsuarios
				.addActionListener(new FrmCreditEngine_cmdUsuarios_actionAdapter(
						this));
		cmdMovimientosPorProducto.setBounds(new Rectangle(36, 455, 225, 35));
		cmdMovimientosPorProducto.setText("MOVIMIENTOS POR PRODUCTO");
		cmdMovimientosPorProducto
				.addActionListener(new FrmCreditEngine_cmdMovimientosPorProducto_actionAdapter(
						this));
		cmdAjustes.setBounds(new Rectangle(36, 531, 225, 35));
		cmdAjustes.setText("REPORTE DE AJUSTES MANUALES");
		cmdAjustes
				.addActionListener(new FrmCreditEngine_cmdAjustes_actionAdapter(
						this));
		cmdReporteFacturacion.setBounds(new Rectangle(36, 569, 225, 35));
		cmdReporteFacturacion.setText("FACTURACION");
		cmdReporteFacturacion
				.addActionListener(new FrmCreditEngine_cmdReporteFacturacion_actionAdapter(
						this));
		cmdReporteador.setBounds(new Rectangle(35, 608, 225, 35));
		cmdReporteador.setText("REPORTEADOR");
		cmdReporteador.setVisible(false);
		cmdReporteador
				.addActionListener(new FrmCreditEngine_cmdReporteador_actionAdapter(
						this));

		aboutJPanel.setBounds(new Rectangle(100, 636, 225, 35));
		aboutJPanel.setBackground(Color.white);

		cmdClasificaciones.setBounds(new Rectangle(36, 222, 225, 35));
		cmdClasificaciones.setText("CLASIFICACIONES");
		cmdClasificaciones
				.addActionListener(new FrmCreditEngine_cmdClasificaciones_actionAdapter(
						this));
		cmbSucursales.setBounds(new Rectangle(33, 40, 228, 21));
		jButton1.setBounds(new Rectangle(32, 66, 103, 23));
		jButton1.setText("jButton1");
		cmdDescuentoEmpleadas.setBounds(new Rectangle(37, 493, 225, 35));
		cmdDescuentoEmpleadas.setText("APLICAR DESCUENTO A EMPLEADAS");
		cmdDescuentoEmpleadas
				.addActionListener(new FrmCreditEngine_cmdDescuentoEmpleadas_actionAdapter(
						this));
		this.getContentPane().add(cmdPPlacetas);
		this.getContentPane().add(jLabel1);
		this.getContentPane().add(cmdCancelaciones);
		this.getContentPane().add(cmdVPlacetas);
		this.getContentPane().add(cmbSucursales);
		this.getContentPane().add(cmdBuscarProducto);
		this.getContentPane().add(cmdClientes);
		this.getContentPane().add(cmdColonias);
		this.getContentPane().add(cmdClasificaciones);
		this.getContentPane().add(cmdReporteador);
		this.getContentPane().add(cmdReporteFacturacion);
		this.getContentPane().add(cmdAjustes);
		this.getContentPane().add(cmdUsuarios);
		this.getContentPane().add(cmdGenerarVale);
		this.getContentPane().add(cmdCambiarPrecios);
		this.getContentPane().add(cmdResumenes);
		this.getContentPane().add(cmdEstadosPorRuta);
		this.getContentPane().add(cmdMovimientosPorProducto);
		this.getContentPane().add(cmdDescuentoEmpleadas);

		for (Object nombre : AppInstance.nombreBoutiqueToId.keySet()) {
			this.cmbSucursales.addItem(nombre.toString());
		}
		this.getContentPane().add(aboutJPanel);
	}

	JButton cmdVPlacetas = new JButton();
	JButton cmdPPlacetas = new JButton();
	JLabel jLabel1 = new JLabel();
	JButton cmdCancelaciones = new JButton();
	JButton cmdBuscarProducto = new JButton();
	JButton cmdClientes = new JButton();
	JButton cmdColonias = new JButton();
	JButton cmdEstadosPorRuta = new JButton();
	JButton cmdResumenes = new JButton();
	JButton cmdCambiarPrecios = new JButton();
	JButton cmdGenerarVale = new JButton();
	JButton cmdUsuarios = new JButton();
	JButton cmdMovimientosPorProducto = new JButton();
	JButton cmdAjustes = new JButton();
	JButton cmdReporteFacturacion = new JButton();
	JButton cmdReporteador = new JButton();
	AboutJPanel aboutJPanel = new AboutJPanel();
	JButton cmdClasificaciones = new JButton();
	JComboBox cmbSucursales = new JComboBox();
	JButton jButton1 = new JButton();
	JButton cmdDescuentoEmpleadas = new JButton();

	public void cmdVPlacetas_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		FrmVentaCreditoFechaDefinida frm = new FrmVentaCreditoFechaDefinida();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);

		AppInstance.boutique().setId(
				(Integer) AppInstance.nombreBoutiqueToId.get(cmbSucursales
						.getSelectedItem().toString()));
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdVSevilla_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		FrmVentaCreditoFechaDefinida frm = new FrmVentaCreditoFechaDefinida();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		AppInstance.boutique().setId(2);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdVZentralia_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		FrmVentaCreditoFechaDefinida frm = new FrmVentaCreditoFechaDefinida();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		AppInstance.boutique().setId(3);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void this_windowOpened(WindowEvent e) {

		this.setSize(300, 700);
		this.setLocationRelativeTo(this.getRootPane());
		DlgIniciarSesion dlg = new DlgIniciarSesion(this,
				com.boutique.engine.impl.AppInstance.nombreNegocio, true);
		dlg.setLocationRelativeTo(this);
		dlg.setSize(225, 195);
		dlg.setVisible(true);
		if (dlg.validado) {
			if (dlg.usuario.getActivo() == 0 || dlg.usuario.getAdmin() == 0) {
				JOptionPane.showMessageDialog(this,
						"Usuario inactivo o sin permisos como administrador",
						com.boutique.engine.impl.AppInstance.nombreNegocio,
						JOptionPane.WARNING_MESSAGE);
				this.setVisible(false);
				System.exit(0);

			}
			// REVISAMOS SI TIENE PERMISOS DE VER EL REPORTEADOR O NO
			if (dlg.usuario.getReporteador() == 1) {
				this.cmdReporteador.setVisible(true);
			}
		} else { // NO ESTA VALIDADO
			JOptionPane
					.showMessageDialog(
							this,
							"Debe introducir un usuario y contraseña válidos, la aplicación se cerrará",
							com.boutique.engine.impl.AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			System.exit(0);
		}

		// VALIDAMOS SI TIENE PERMISOS PARA ADMINISTRAR USUARIOS SINO LE
		// DESHABILITAMOS EL BOTON
		if (AppInstance.usuario().getAdminUsuarios() == 0) {
			this.cmdUsuarios.setEnabled(false);
		}

		// VALIDAMOS SI YA SE HIZO UN RESPALDO DE LA BASE DE DATOS EL DIA DE HOY
		/*
		 * if(!DaoSource.respaldoRealizado()){ int i =
		 * JOptionPane.showConfirmDialog
		 * (this,"NO SE HA RESPALDADO LA BASE DE DATOS , DESESA HACERLO AHORA?"
		 * ,AppInstance.nombreNegocio,JOptionPane.YES_NO_OPTION); if
		 * (i==JOptionPane.YES_OPTION){ DlgRespaldoBD dlgBD = new
		 * DlgRespaldoBD(); dlgBD.setModal(true); dlgBD.setSize(400,350);
		 * dlgBD.setLocationRelativeTo(this); dlgBD.setVisible(true); //
		 * DaoSource.registrarRespaldo(); } }
		 */

	}

	public void cmdPPlacetas_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		AppInstance.boutique().setId(
				(Integer) AppInstance.nombreBoutiqueToId.get(cmbSucursales
						.getSelectedItem().toString()));
		FrmEstadoCuenta frm = new FrmEstadoCuenta(false);
		frm.pack();
		frm.setSize(730, 700);
		frm.setLocationRelativeTo(this);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdCancelaciones_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmDevoluciones frm = new FrmDevoluciones();
		AppInstance.boutique().setId(
				(Integer) AppInstance.nombreBoutiqueToId.get(cmbSucursales
						.getSelectedItem().toString()));
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

	public void cmdClientes_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmDirectorioClientes frm = new FrmDirectorioClientes();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdColonias_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmCatalogoColonias frm = new FrmCatalogoColonias();
		frm.setSize(480, 350);
		frm.setLocationRelativeTo(this);

		// frm.setExtendedState(frm.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void jButton1_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		FrmDevoluciones frm = new FrmDevoluciones();
		AppInstance.boutique().setId(2);
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdEstadosPorRuta_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmHistorialCliente frm = new FrmHistorialCliente();
		frm.pack();
		frm.setSize(730, 700);
		frm.setLocationRelativeTo(this);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdResumenes_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		DlgResumenes dlg = new DlgResumenes(this, AppInstance.nombreNegocio,
				true);
		dlg.tipoResumen = 1;
		dlg.setSize(420, 130);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdCambiarPrecios_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmBusquedaModificacionProducto frm = new FrmBusquedaModificacionProducto();
		frm.pack();
		frm.setSize(700, 600);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);
	}

	public void cmdGenerarVale_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmGenerarVale frm = new FrmGenerarVale();
		frm.pack();
		frm.setSize(280, 335);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdUsuarios_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		FrmCatalogoUsuarios frm = new FrmCatalogoUsuarios();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	@SuppressWarnings("rawtypes")
	public void cmdMovimientosPorProducto_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);
		try {

			String etiqueta = JOptionPane.showInputDialog(this,
					"Introduzca la etiqueta:", "");
			if (etiqueta != null && !etiqueta.equals("")) {
				InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
						.getResourceAsStream("MovimientosPorCodigo.jasper");
				// File fdesign = new
				// File(com.boutique.impresiones.ImpresionDevolucion.class.
				// getResource("resumenProductos.jasper").getPath());

				// String fileName =
				// "src\\com\\boutique\\impresiones\\resumenProductos.jasper";
				// Ruta de archivo pdf de destino

				// Ruta de archivo xls de destino
				// String destFileNameXls="C:\\SIAU\\reportes\\reporte1.xls";

				// Pasamos parametros al reporte Jasper.
				Map<String, Comparable> parameters = new HashMap<String, Comparable>();
				parameters.put("etiqueta", etiqueta);
				// Preparacion del reporte (en esta etapa se inserta el valor
				JasperPrint listo = JasperFillManager.fillReport(st,
						parameters,
						com.boutique.dao.DaoSource.getConnectionLocal());
				JasperViewer vw = new JasperViewer(listo, false);
				// net.sf.jasperreports.view.JRViewer jrv = new
				// net.sf.jasperreports.view.JRViewer(listo);
				// jrv.setVisible(true);
				vw.setTitle("Lista de productos");
				this.setCursor(AppInstance.defCursor);
				vw.setVisible(true);
				// Creación del PDF
				// JasperExportManager.exportReportToPdfFile(listo,
				// destFileNamePdf);
				// JasperViewer.viewReport(listo);

				// Creación del XLS
				/*
				 * JRXlsExporter exporter = new JRXlsExporter();
				 * exporter.setParameter(JRExporterParameter.JASPER_PRINT,listo)
				 * ; exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				 * "prueba.xls") ; exporter.setParameter(JRXlsExporterParameter.
				 * IS_ONE_PAGE_PER_SHEET, Boolean.TRUE) ;
				 * //exporter.exportReport(); exporter.exportReport();
				 */
				// System.exit(0);
			} else {
				JOptionPane.showMessageDialog(this,
						"Indique una etiqueta valida",
						AppInstance.nombreNegocio, JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}

	}

	public void cmdAjustes_actionPerformed(ActionEvent e) {

		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("ajustesAdministrativos.jasper");
			// File fdesign = new
			// File(com.boutique.impresiones.ImpresionDevolucion.class.
			// getResource("resumenProductos.jasper").getPath());

			// String fileName =
			// "src\\com\\boutique\\impresiones\\resumenProductos.jasper";
			// Ruta de archivo pdf de destino

			// Ruta de archivo xls de destino
			// String destFileNameXls="C:\\SIAU\\reportes\\reporte1.xls";

			// Pasamos parametros al reporte Jasper.
			Calendar cal = Calendar.getInstance();

			cal.setTime(dlg.jdFechaInicial.getDate());
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("idBoutique", com.boutique.engine.impl.AppInstance
					.boutique().getId());
			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("fechaInicial",
					new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("fechaFinal",
					new java.sql.Timestamp(cal.getTimeInMillis()));

			JasperPrint listo = JasperFillManager.fillReport(st, parameters,
					com.boutique.dao.DaoSource.getConnectionLocal());
			JasperViewer vw = new JasperViewer(listo, false);
			// net.sf.jasperreports.view.JRViewer jrv = new
			// net.sf.jasperreports.view.JRViewer(listo);
			// jrv.setVisible(true);
			vw.setTitle("Lista de productos");
			this.setCursor(AppInstance.defCursor);
			vw.setVisible(true);
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

	public void cmdReporteFacturacion_actionPerformed(ActionEvent e) {
		DlgEligeFecha dlg = new DlgEligeFecha(null);
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		if (dlg.getFechaInicial().getDate() == null
				|| dlg.getCmbTipoReporte2().getSelectedIndex() == -1) {
			JOptionPane
					.showMessageDialog(
							null,
							"Es necesario indicar una fecha y un tipo de reporte, intente nuevamente.",
							AppInstance.nombreNegocio,
							JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.setCursor(AppInstance.waitCursor);

		// Decidir que tipo de reporte ejecutar
		String tipoReporte = (String) dlg.getCmbTipoReporte2()
				.getSelectedItem();

		Date fechaReporte = dlg.getFechaInicial().getDate();

		try {
			ejecutarReporteFacturacion(tipoReporte, fechaReporte);
		} catch (JRException e1) {
			this.setCursor(AppInstance.defCursor);

			JOptionPane.showMessageDialog(null,
					"No se pudo generar el reporte: " + e1.getMessage(),
					AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} finally {
			this.setCursor(AppInstance.defCursor);

		}

	}

	private void ejecutarReporteFacturacion(String tipoReporte,
			Date fechaReporte) throws JRException {

		String nombreArchivoReporte = null;
		String tituloReporte = null;
		List<FacturaDatosGeneralesDTO> facturaGeneral = null;
		JRBeanCollectionDataSource dataSource = null;

		if (TipoReporteFacturacion.PUBLICO_GENERAL.getFriendlyText().equals(
				tipoReporte)) {
			nombreArchivoReporte = "factura_detalles2.jasper";
			tituloReporte = "Factura al publico en general - "
					+ AppInstance.formatoCorto.format(fechaReporte);
			// Obtener ventas para factura publico en general.
			facturaGeneral = rfEngine
					.getReporteFacturaGeneralPorFecha(fechaReporte);
			System.out.println(facturaGeneral);
			dataSource = new JRBeanCollectionDataSource(facturaGeneral);

		} else {
			nombreArchivoReporte = "facturas_por_cliente2.jasper";
			List<FacturaClienteIndividualDTO> facturas = null;
			if (TipoReporteFacturacion.INDIVIDUAL_CONTADO.getFriendlyText()
					.equals(tipoReporte)) {
				tituloReporte = "Facturas Ventas Contado - "
						+ AppInstance.formatoCorto.format(fechaReporte);

				facturas = rfEngine.getReporteFacturasPorCliente(fechaReporte,
						VentaTypeEnum.CONTADO);
			} else if (TipoReporteFacturacion.INDIVIDUAL_CREDITO
					.getFriendlyText().equals(tipoReporte)) {
				tituloReporte = "Facturas Ventas Credito - "
						+ AppInstance.formatoCorto.format(fechaReporte);

				facturas = rfEngine.getReporteFacturasPorCliente(fechaReporte,
						VentaTypeEnum.CREDITO);

			} else if (TipoReporteFacturacion.INDVIDUAL_APARTADO
					.getFriendlyText().equals(tipoReporte)) {
				tituloReporte = "Facturas Apartados - "
						+ AppInstance.formatoCorto.format(fechaReporte);

				facturas = rfEngine.getReporteFacturasPorCliente(fechaReporte,
						VentaTypeEnum.APARTADO);

			}
			dataSource = new JRBeanCollectionDataSource(facturas);
		}

		visualizarReporte(nombreArchivoReporte, tituloReporte, dataSource);
	}

	private void visualizarReporte(String nombreArchivoReporte,
			String tituloReporte, JRBeanCollectionDataSource dataSource)
			throws JRException {

		InputStream stream = ClassLoader
				.getSystemResourceAsStream(nombreArchivoReporte);
		Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();
		JasperPrint listo = JasperFillManager.fillReport(stream, parameters,
				dataSource);
		JasperViewer vw = new JasperViewer(listo, false);
		vw.setTitle(tituloReporte);
		this.setCursor(AppInstance.defCursor);
		vw.setVisible(true);

	}

	public void cmdReporteador_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		FrmReporteadorGerencial frm = new FrmReporteadorGerencial();
		frm.pack();
		frm.setSize(450, 400);
		frm.setLocationRelativeTo(this);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdClasificaciones_actionPerformed(ActionEvent e) {
		this.setCursor(AppInstance.waitCursor);

		DlgCatalogoClasificacionClientes frm = new DlgCatalogoClasificacionClientes();
		frm.pack();
		frm.setExtendedState(Frame.MAXIMIZED_BOTH);
		frm.setVisible(true);
		this.setCursor(AppInstance.defCursor);

	}

	public void cmdDescuentoEmpleadas_actionPerformed(ActionEvent e) {
		if (DaoClientesCentral.aplicarDescuentoEmpleado(20)) {
			JOptionPane.showMessageDialog(this,
					"DESCUENTO APLICADO EXITOSAMENTE",
					AppInstance.nombreNegocio, JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this,
					"NO PUDO APLICARSE EL DESCUENTO",
					AppInstance.nombreNegocio, JOptionPane.ERROR_MESSAGE);
		}
	}
}

class FrmCreditEngine_cmdDescuentoEmpleadas_actionAdapter implements
		ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdDescuentoEmpleadas_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdDescuentoEmpleadas_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdClasificaciones_actionAdapter implements
		ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdClasificaciones_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdClasificaciones_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdReporteador_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdReporteador_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdReporteador_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdAjustes_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdAjustes_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdAjustes_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdMovimientosPorProducto_actionAdapter implements
		ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdMovimientosPorProducto_actionAdapter(
			FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdMovimientosPorProducto_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdUsuarios_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdUsuarios_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdUsuarios_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdGenerarVale_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdGenerarVale_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdGenerarVale_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdCambiarPrecios_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdCambiarPrecios_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCambiarPrecios_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdResumenes_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdResumenes_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdResumenes_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdEstadosPorRuta_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdEstadosPorRuta_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.cmdEstadosPorRuta_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdColonias_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdColonias_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdColonias_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdClientes_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdClientes_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdClientes_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdBuscarProducto_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdBuscarProducto_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdBuscarProducto_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdCancelaciones_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdCancelaciones_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdCancelaciones_actionPerformed(e);
	}
}

class FrmCreditEngine_this_windowAdapter extends WindowAdapter {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_this_windowAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void windowOpened(WindowEvent e) {
		adaptee.this_windowOpened(e);
	}
}

class FrmCreditEngine_cmdReporteFacturacion_actionAdapter implements
		ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdReporteFacturacion_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdReporteFacturacion_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdPPlacetas_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdPPlacetas_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdPPlacetas_actionPerformed(e);
	}
}

class FrmCreditEngine_cmdVPlacetas_actionAdapter implements ActionListener {
	private FrmCreditEngine adaptee;

	FrmCreditEngine_cmdVPlacetas_actionAdapter(FrmCreditEngine adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVPlacetas_actionPerformed(e);
	}
}
