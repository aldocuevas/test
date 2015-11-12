package com.boutique.view;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class DlgAcercaDe extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel Build = null;
	private JLabel Fecha = null;
	private JLabel lblVersion = null;
	private JLabel lblBuild = null;
	private JLabel lblFecha = null;
	private JButton cmdVerLogCambios = null;
	private JTextArea txtLogCambios = null;
	private JScrollPane jScrollPane = null;

	/**
	 * @param owner
	 */
	public DlgAcercaDe(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(671, 393);
		this.setContentPane(getJContentPane());
		getVersionData();
		txtLogCambios.setAutoscrolls(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {

			}
		});
	}

	private void getChangeLog() {
		InputStream stream=null;

		byte[] changeLogFile;
		try {
 				
			stream =  ClassLoader.getSystemResourceAsStream("changelog.txt");
			changeLogFile = new byte[stream.available()];
			stream.read(changeLogFile);
			this.txtLogCambios.setText(new String(changeLogFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void getVersionData() {
		Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("about.txt"));
			this.lblVersion.setText(properties.getProperty("version")
					.toString());
			this.lblBuild.setText(properties.getProperty("build"));
			this.lblFecha.setText(properties.getProperty("date"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblFecha = new JLabel();
			lblFecha.setBounds(new Rectangle(133, 64, 146, 16));
			lblFecha.setText("");
			lblBuild = new JLabel();
			lblBuild.setBounds(new Rectangle(132, 44, 148, 16));
			lblBuild.setText("");
			lblVersion = new JLabel();
			lblVersion.setText("");
			lblVersion.setSize(new Dimension(147, 16));
			lblVersion.setLocation(new Point(133, 23));
			Fecha = new JLabel();
			Fecha.setBounds(new Rectangle(94, 63, 37, 17));
			Fecha.setText("Fecha:");
			Build = new JLabel();
			Build.setBounds(new Rectangle(98, 42, 32, 17));
			Build.setText("Build:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(84, 23, 48, 16));
			jLabel.setText("Version:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(Build, null);
			jContentPane.add(Fecha, null);
			jContentPane.add(lblVersion, null);
			jContentPane.add(lblBuild, null);
			jContentPane.add(lblFecha, null);
			jContentPane.add(getCmdVerLogCambios(), null);
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cmdVerLogCambios
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCmdVerLogCambios() {
		if (cmdVerLogCambios == null) {
			cmdVerLogCambios = new JButton();
			cmdVerLogCambios.setBounds(new Rectangle(220, 96, 182, 27));
			cmdVerLogCambios.setText("VER LOG DE CAMBIOS");
			cmdVerLogCambios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getChangeLog();
				}
			});
		}
		return cmdVerLogCambios;
	}

	/**
	 * This method initializes txtLogCambios
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getTxtLogCambios() {
		if (txtLogCambios == null) {
			txtLogCambios = new JTextArea();
			txtLogCambios.setEditable(false);
			txtLogCambios.setText("");
		}
		return txtLogCambios;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(6, 134, 635, 214));
			jScrollPane.setViewportView(getTxtLogCambios());
		}
		return jScrollPane;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
