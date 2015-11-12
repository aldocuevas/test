package com.boutique.app;

import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;
import com.boutique.view.*;
import com.boutique.engine.impl.AppInstance;

import javax.swing.JOptionPane;
import com.boutique.dao.DaoSource;
import com.boutique.domain.InterfaceGrafica;

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
public class AppBoutiqueInventario {
	boolean packFrame = false;

	/**
	 * Construct and show the application.
	 */
	public AppBoutiqueInventario() {
		try {
			AppInstance.interfaceGrafica = InterfaceGrafica.INVENTARIOS;
			DaoSource.iniciar();
		} catch (Exception ex1) {
		}
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
		FrmInventario frame = new FrmInventario();
		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their
		// layout
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
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				new AppBoutiqueInventario();
			}
		});
	}

	private void jbInit() throws Exception {
	}
}
