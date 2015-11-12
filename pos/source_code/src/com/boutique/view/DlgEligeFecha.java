package com.boutique.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boutique.enums.TipoReporteFacturacion;
import com.toedter.calendar.JDateChooser;

public class DlgEligeFecha extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="14,220"
	private JButton cmdBuscar = null;
	private JLabel lblFecha = null;
	private JDateChooser fechaInicial=null;
	private JComboBox cmbTipoReporte = null;
	
	public void setCmbTipoReporte(JComboBox cmbTipoReporte) {
		this.cmbTipoReporte = cmbTipoReporte;
	}
	public JComboBox getCmbTipoReporte2(){
		return this.cmbTipoReporte;
	}

	public JDateChooser getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(JDateChooser fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @param owner
	 */
	public DlgEligeFecha(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(223, 151);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblFecha = new JLabel();
			lblFecha.setBounds(new Rectangle(22, 16, 136, 16));
			lblFecha.setText("Indique la fecha:");
			
			fechaInicial = new JDateChooser();
            fechaInicial.setBounds(new Rectangle(26, 37, 138, 24));
            
 			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(173, 135));
			jContentPane.add(getCmdBuscar(), null);
			jContentPane.add(lblFecha, null);
			jContentPane.add(fechaInicial,null);
			jContentPane.add(getCmbTipoReporte(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cmdBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCmdBuscar() {
		if (cmdBuscar == null) {
			cmdBuscar = new JButton();
			cmdBuscar.setBounds(new Rectangle(30, 105, 108, 26));
			cmdBuscar.setText("ACEPTAR");
			cmdBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
 					setVisible(false);
				}
			});
		}
		return cmdBuscar;
	}

	/**
	 * This method initializes cmbTipoReporte	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbTipoReporte() {
		if (cmbTipoReporte == null) {
			cmbTipoReporte = new JComboBox();
			cmbTipoReporte.setBounds(new Rectangle(11, 73, 163, 25));
			for (TipoReporteFacturacion element: TipoReporteFacturacion.values()){
				cmbTipoReporte.addItem(element.getFriendlyText());
			}
		}
		return cmbTipoReporte;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
