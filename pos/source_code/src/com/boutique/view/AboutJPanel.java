package com.boutique.view;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JLabel;

import com.boutique.engine.impl.AppInstance;

import java.awt.Font;
import java.awt.Color;

public class AboutJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton cmdVerAboutTxt = null;
	private JLabel lblAcercaDe = null;

	/**
	 * This is the default constructor
	 */
	public AboutJPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridy = 0;
		lblAcercaDe = new JLabel();
		lblAcercaDe.setText("Acerca de..");
		lblAcercaDe.setForeground(Color.blue);
		lblAcercaDe.setFont(new Font("Arial", Font.BOLD, 12));
		lblAcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseExited(java.awt.event.MouseEvent e) {
				setCursor(AppInstance.defCursor);
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				setCursor(AppInstance.overCursor);
			}

			public void mouseClicked(java.awt.event.MouseEvent e) {
				DlgAcercaDe dlg = new DlgAcercaDe(null);
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.setSize(103, 34);
		this.setLayout(new GridBagLayout());
		this.add(lblAcercaDe, gridBagConstraints1);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
