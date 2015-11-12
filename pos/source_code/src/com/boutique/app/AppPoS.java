package com.boutique.app;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.boutique.dao.DaoSource;
import com.boutique.domain.Boutique;
import com.boutique.domain.InterfaceGrafica;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.KeepAliveThread;
import com.boutique.view.DlgDatosConexion;
import com.boutique.view.DlgIndicarBoutique;

public class AppPoS {

	boolean packFrame = false;
	public static Boutique boutique;
	KeepAliveThread thread;

	/**
	 * Construct and show the application.
	 */
	public AppPoS() {
		AppInstance.interfaceGrafica = InterfaceGrafica.POS;
		// DaoInventario.inventario2();
		// INICIAMOS LOS DATOS DE LA BASE DE DATOS
		// Read properties file.
		/*
		 * net.sf.jasperreports.engine.util.JRProperties.setProperty(
		 * "net.sf.jasperreports.query.executer.factory.sql"
		 * ,"net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory");
		 * net.sf.jasperreports.engine.util.JRProperties.setProperty(
		 * "net.sf.jasperreports.query.executer.factory.SQL"
		 * ,"net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory");
		 * JRPropertiesMap map = new JRPropertiesMap();
		 * net.sf.jasperreports.engine.util.JRProperties.getProperties(map,"*");
		 */
		/*
		 * try {
		 * this.getClass().getClassLoader().getSystemClassLoader().loadClass(
		 * "net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory"); }
		 * catch (Exception ex2) { ex2.printStackTrace(); }
		 */

		// System.setProperty("net.sf.jasperreports.properties","default.jasperreports.properties");
		// System.out.println("Class path: " +
		// prop.getProperty("java.class.path"));
		// System.out.println("Propertu nst: " +
		// System.getProperty("net.sf.jasperreports.properties"));

		try {
			DaoSource.iniciar();
			if (DaoSource.ip == null || DaoSource.ip.equals("")) {
				// SIGNIFICA QUE NO HAY CONECTIVIDAD CORRECTA, ABRIMOS EL CUADRO
				// DE DIALOGO DE CONEXION
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
			if (DaoSource.idBoutique == 0) {
				DlgIndicarBoutique dlg = new DlgIndicarBoutique();
				dlg.setSize(400, 210);
				dlg.setModal(true);
				dlg.setVisible(true);
				if (DaoSource.idBoutique == 0) {
					// NO TENEMOS BOUTIQUE INDICADA
					JOptionPane
							.showMessageDialog(
									null,
									"Es necesario indicar una boutique, el sistema se cerrará",
									AppInstance.nombreNegocio,
									JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}

			}
		} catch (Exception ex1) {
			if (DaoSource.idBoutique == 0 || DaoSource.idTerminal == 0) {
				// NO TENEMOS BOUTIQUE INDICADA
				JOptionPane
						.showMessageDialog(
								null,
								"Es necesario indicar el nombre de sucursal y numero de terminal. Borre el archivo ApplicationResources.propertes y reinicie la aplicación",
								AppInstance.nombreNegocio,
								JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		AppInstance.iniciarApp();
		FrmPosBoutique2 frame = new FrmPosBoutique2();

		// FrmPosBoutiqueVis frame = new FrmPosBoutiqueVis();
		// boutique = com.boutique.dao.DaoBoutique.getBoutiqueLocal();
		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their
		// layout
		frame.setSize(new Dimension(530, 500));
		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}

		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
		thread = new KeepAliveThread();
		thread.start();
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Application entry point.
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());

				} catch (Exception exception) {
					exception.printStackTrace();
				}

				new AppPoS();
			}
		});
	}

	private void jbInit() throws Exception {
	}
}
