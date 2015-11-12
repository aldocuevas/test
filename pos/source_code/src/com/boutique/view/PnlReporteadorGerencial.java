package com.boutique.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.boutique.engine.impl.AppInstance;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class PnlReporteadorGerencial extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JButton cmdPagosPorDia = new JButton();
	JPanel jPanel2 = new JPanel();
	JButton cmdIngresosPorMes = new JButton();
	JButton cmdVentasPorDia = new JButton();
	JButton cmdVentasPorMes = new JButton();
	JButton cmdProductosPorDia = new JButton();
	JButton cmdProductosPorMes = new JButton();
	JButton cmdProductosPorMesPorTipo = new JButton();

	public PnlReporteadorGerencial() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setLayout(borderLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 13));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel1.setText("REPORTEADOR GERENCIAL");
		jPanel1.setLayout(null);
		cmdPagosPorDia.setBounds(new Rectangle(10, 20, 169, 32));
		cmdPagosPorDia.setText("INGRESOS POR DIA");
		cmdPagosPorDia.addActionListener(new PnlReporteadorGerencial_cmdPagosPorDia_actionAdapter(this));
		cmdIngresosPorMes.setBounds(new Rectangle(185, 19, 169, 32));
		cmdIngresosPorMes.setText("INGRESOS MES A MES");
		cmdIngresosPorMes.addActionListener(new PnlReporteadorGerencial_cmdIngresosPorMes_actionAdapter(this));
		cmdVentasPorDia.setBounds(new Rectangle(10, 58, 169, 32));
		cmdVentasPorDia.setText("VENTAS POR DIA");
		cmdVentasPorDia.addActionListener(new PnlReporteadorGerencial_cmdVentasPorDia_actionAdapter(this));
		cmdVentasPorMes.setBounds(new Rectangle(186, 58, 169, 32));
		cmdVentasPorMes.setText("VENTAS MES A MES");
		cmdVentasPorMes.addActionListener(new PnlReporteadorGerencial_cmdVentasPorMes_actionAdapter(this));
		cmdProductosPorDia.setBounds(new Rectangle(11, 95, 169, 32));
		cmdProductosPorDia.setText("PRODUCTOS POR DIA");
		cmdProductosPorDia.addActionListener(new PnlReporteadorGerencial_jButton1_actionAdapter(this));
		cmdProductosPorMes.setBounds(new Rectangle(186, 95, 169, 32));
		cmdProductosPorMes.setText("PRODUCTOS MES A MES");
		cmdProductosPorMes.addActionListener(new PnlReporteadorGerencial_cmdProductosPorMes_actionAdapter(this));
		cmdProductosPorMesPorTipo.setBounds(new Rectangle(11, 135, 169, 32));
		cmdProductosPorMesPorTipo.setText("PRODUCTOS POR TIPO MES A MES");
		cmdProductosPorMesPorTipo
				.addActionListener(new PnlReporteadorGerencial_cmdProductosPorMesPorTipo_actionAdapter(this));

		jPanel1.add(cmdPagosPorDia);
		jPanel1.add(cmdIngresosPorMes);
		jPanel1.add(cmdVentasPorDia);
		jPanel1.add(cmdProductosPorDia);
		jPanel1.add(cmdVentasPorMes);
		jPanel1.add(cmdProductosPorMes);
		jPanel1.add(cmdProductosPorMesPorTipo);

		this.add(jPanel2, java.awt.BorderLayout.NORTH);
		jPanel2.add(jLabel1);
		this.add(jPanel1, null);
	}

	public void cmdPagosPorDia_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("DiarioDePagosGlobales2015.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}

	}

	public void cmdIngresosPorMes_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("MensualDePagos.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}

	}

	public void cmdVentasPorDia_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("DiarioDeVentas.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}
	}

	public void cmdVentasPorMes_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("MensualDeVentas.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}
	}

	public void jButton1_actionPerformed(ActionEvent e) {

	}

	public void cmdProductosPorDia_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("DiarioDeProductosVendidos.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			JasperPrint listo = JasperFillManager.fillReport(st, parameters,
					com.boutique.dao.DaoSource.getConnectionLocal());
			JasperViewer vw = new JasperViewer(listo, false);
			// net.sf.jasperreports.view.JRViewer jrv = new
			// net.sf.jasperreports.view.JRViewer(listo);
			// jrv.setVisible(true);
			vw.setTitle("REPORTE");
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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}
	}

	public void cmdProductosPorMes_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("MensualDeProductosVendidos.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			JasperPrint listo = JasperFillManager.fillReport(st, parameters,
					com.boutique.dao.DaoSource.getConnectionLocal());
			JasperViewer vw = new JasperViewer(listo, false);
			// net.sf.jasperreports.view.JRViewer jrv = new
			// net.sf.jasperreports.view.JRViewer(listo);
			// jrv.setVisible(true);
			vw.setTitle("REPORTE");
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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}

	}

	public void cmdProductosPorMesPorTipo_actionPerformed(ActionEvent e) {
		DlgIntervaloFechas dlg = new DlgIntervaloFechas();
		dlg.setSize(200, 200);
		dlg.setModal(true);
		dlg.setLocationRelativeTo(this);
		dlg.setVisible(true);
		this.setCursor(AppInstance.waitCursor);
		try {
			InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
					.getResourceAsStream("MensualDeProductosVendidosPorTipo.jasper");
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
			Map<String, Timestamp> parameters = new HashMap<String, Timestamp>();

			// Preparacion del reporte (en esta etapa se inserta el valor
			parameters.put("startDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			cal.setTime(dlg.jdFechaFinal.getDate());
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			parameters.put("endDate", new java.sql.Timestamp(cal.getTimeInMillis()));

			JasperPrint listo = JasperFillManager.fillReport(st, parameters,
					com.boutique.dao.DaoSource.getConnectionLocal());
			JasperViewer vw = new JasperViewer(listo, false);
			// net.sf.jasperreports.view.JRViewer jrv = new
			// net.sf.jasperreports.view.JRViewer(listo);
			// jrv.setVisible(true);
			vw.setTitle("REPORTE");
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
			 * ; exporter.setParameter(JRXlsExporterParameter.
			 * IS_ONE_PAGE_PER_SHEET , Boolean.TRUE) ;
			 * //exporter.exportReport(); exporter.exportReport();
			 */
			// System.exit(0);

		} catch (Exception ed) {
			this.setCursor(AppInstance.defCursor);
			ed.printStackTrace();
			System.out.println(ed.getMessage());
		}

	}

}

class PnlReporteadorGerencial_cmdProductosPorMesPorTipo_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_cmdProductosPorMesPorTipo_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdProductosPorMesPorTipo_actionPerformed(e);
	}
}

class PnlReporteadorGerencial_cmdProductosPorMes_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_cmdProductosPorMes_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdProductosPorMes_actionPerformed(e);
	}
}

class PnlReporteadorGerencial_jButton1_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_jButton1_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		adaptee.cmdProductosPorDia_actionPerformed(e);
	}
}

class PnlReporteadorGerencial_cmdVentasPorMes_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_cmdVentasPorMes_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVentasPorMes_actionPerformed(e);
	}
}

class PnlReporteadorGerencial_cmdVentasPorDia_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_cmdVentasPorDia_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVentasPorDia_actionPerformed(e);
	}
}

class PnlReporteadorGerencial_cmdIngresosPorMes_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_cmdIngresosPorMes_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdIngresosPorMes_actionPerformed(e);
	}
}

class PnlReporteadorGerencial_cmdPagosPorDia_actionAdapter implements ActionListener {
	private final PnlReporteadorGerencial adaptee;

	PnlReporteadorGerencial_cmdPagosPorDia_actionAdapter(PnlReporteadorGerencial adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.cmdPagosPorDia_actionPerformed(e);
	}
}
