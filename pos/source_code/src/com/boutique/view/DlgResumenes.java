package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sf.jasperreports.engine.JasperPrint;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

import java.util.Calendar;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperFillManager;

import com.boutique.engine.impl.*;

public class DlgResumenes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	JLabel jLabel1 = new JLabel();
	JComboBox cmbDarios = new JComboBox();
	JButton cmdVisualizar = new JButton();
	DefaultComboBoxModel modelCombo = new DefaultComboBoxModel();
	public int tipoResumen = 0; // Fecha de hoy 0 , fecha modificable 1

	public DlgResumenes(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public DlgResumenes() {
		this(new Frame(), "DlgResumenes", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(null);
		jLabel1.setText("Selecciona el diario a revisar:");
		jLabel1.setBounds(new Rectangle(3, 15, 400, 15));
		cmbDarios.setModel(modelCombo);
		cmbDarios.setBounds(new Rectangle(3, 33, 397, 24));
		cmdVisualizar.setBounds(new Rectangle(106, 62, 199, 27));
		cmdVisualizar.setText("VISUALIZAR");
		cmdVisualizar
				.addActionListener(new DlgResumenes_cmdVisualizar_actionAdapter(
						this));
		getContentPane().add(panel1);
		panel1.add(cmbDarios, null);
		panel1.add(jLabel1, null);
		panel1.add(cmdVisualizar);
		modelCombo.addElement("Diario de entradas - Contado");
		modelCombo.addElement("Diario de entradas - Credito/Apartado");
		modelCombo.addElement("Resumen - Cancelaciones");
		modelCombo.addElement("Resumen - Ventas a credito");
	}

	public void cmdVisualizar_actionPerformed(ActionEvent e) {
		String item = modelCombo.getSelectedItem().toString();
		this.setCursor(AppInstance.waitCursor);
		if (item.equals("Diario de entradas - Contado")) {
			if (tipoResumen == 0) {
				FrmDiarioDeEntradas frm2 = new FrmDiarioDeEntradas();
				frm2.setExtendedState(Frame.MAXIMIZED_BOTH);
				frm2.setVisible(true);
			} else {
				FrmDiarioDeEntradasPorDia frm2 = new FrmDiarioDeEntradasPorDia();
				frm2.setExtendedState(Frame.MAXIMIZED_BOTH);
				frm2.setVisible(true);
			}
		} else if (item.equals("Diario de entradas - Credito/Apartado")) { // NO
																			// SE
																			// UTILIZA
			if (tipoResumen == 0) {
				FrmDiarioCreditos frm = new FrmDiarioCreditos();
				frm.setExtendedState(Frame.MAXIMIZED_BOTH);
				frm.setVisible(true);
			} else {
				FrmDiarioCreditosPorDia frm = new FrmDiarioCreditosPorDia();
				frm.setExtendedState(Frame.MAXIMIZED_BOTH);
				frm.setVisible(true);
			}
		} else if (item.equals("Resumen - Cancelaciones")) {
			if (tipoResumen == 0) {
				FrmDiarioDevoluciones frm3 = new FrmDiarioDevoluciones();
				// frm3.pack();
				// frm3.setAlwaysOnTop(true);
				frm3.setExtendedState(Frame.MAXIMIZED_BOTH);
				frm3.setVisible(true);
			} else {
				FrmDiarioDevolucionesPorDia frm3 = new FrmDiarioDevolucionesPorDia();
				// frm3.pack();
				// frm3.setAlwaysOnTop(true);
				frm3.setExtendedState(Frame.MAXIMIZED_BOTH);
				frm3.setVisible(true);
			}
		} else if (item.equals("Resumen - Ventas a credito")) {
			this.setCursor(AppInstance.waitCursor);
			try {
				InputStream st = com.boutique.impresiones.ImpresionDevolucion.class
						.getResourceAsStream("VentasCreditoPorDia.jasper");

				// Pasamos parametros al reporte Jasper.
				Calendar cal = Calendar.getInstance();
				cal.setTime(new java.util.Date());
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				@SuppressWarnings("rawtypes")
				Map<String, Comparable> parameters = new HashMap<String, Comparable>();
				new java.sql.Timestamp(cal.getTimeInMillis());
				parameters.put("idBoutique", com.boutique.engine.impl.AppInstance
						.boutique().getId());
				// Preparacion del reporte (en esta etapa se inserta el valor
				parameters.put("fechaInicial",
						new java.sql.Timestamp(cal.getTimeInMillis()));
				cal.set(Calendar.HOUR, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				parameters.put("fechaFinal",
						new java.sql.Timestamp(cal.getTimeInMillis()));

				JasperPrint listo = JasperFillManager.fillReport(st,
						parameters,
						com.boutique.dao.DaoSource.getConnectionLocal());
				JasperViewer vw = new JasperViewer(listo, false);
				// net.sf.jasperreports.view.JRViewer jrv = new
				// net.sf.jasperreports.view.JRViewer(listo);
				// jrv.setVisible(true);
				vw.setTitle("Ventas a credito");
				this.setCursor(AppInstance.defCursor);
				vw.setVisible(true);
				vw.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			} catch (Exception ed) {
				this.setCursor(AppInstance.defCursor);
				ed.printStackTrace();
				System.out.println(ed.getMessage());
			}

		}
		this.setCursor(AppInstance.defCursor);
		this.dispose();
	}

}

class DlgResumenes_cmdVisualizar_actionAdapter implements ActionListener {
	private DlgResumenes adaptee;

	DlgResumenes_cmdVisualizar_actionAdapter(DlgResumenes adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.cmdVisualizar_actionPerformed(e);
	}
}
