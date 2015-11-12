package com.boutique.view;

import java.awt.*;
import javax.swing.*;

import com.borland.jbcl.layout.VerticalFlowLayout;
import javax.swing.border.TitledBorder;

public class FrmAdministrador extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
	JToggleButton jToggleButton1 = new JToggleButton();
	JToggleButton jToggleButton2 = new JToggleButton();
	JToggleButton jToggleButton3 = new JToggleButton();
	JToggleButton jToggleButton4 = new JToggleButton();
	JToggleButton jToggleButton5 = new JToggleButton();
	TitledBorder titledBorder1 = new TitledBorder("");
	JPanel jPanel2 = new JPanel();

	public FrmAdministrador() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(borderLayout1);
		jPanel1.setBackground(Color.gray);
		jPanel1.setFont(new java.awt.Font("Arial", Font.PLAIN, 11));
		jPanel1.setMinimumSize(new Dimension(160, 575));
		jPanel1.setPreferredSize(new Dimension(125, 575));
		verticalFlowLayout1.setHgap(0);
		verticalFlowLayout1.setVgap(5);
		jToggleButton1.setBackground(Color.white);
		jToggleButton1.setBorder(null);
		jToggleButton1.setToolTipText("");
		jToggleButton1.setHorizontalTextPosition(SwingConstants.CENTER);
		jToggleButton1.setIcon(new ImageIcon(FrmAdministrador.class
				.getResource("img/reportes.png")));
		jToggleButton1.setText("Reportes");
		jToggleButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
		jToggleButton2.setBackground(Color.white);
		jToggleButton2.setBorder(null);
		jToggleButton2.setHorizontalTextPosition(SwingConstants.CENTER);
		jToggleButton2.setIcon(new ImageIcon(FrmAdministrador.class
				.getResource("img/inventarios.png")));
		jToggleButton2.setText("Inventarios");
		jToggleButton2.setVerticalTextPosition(SwingConstants.BOTTOM);
		jToggleButton3.setBackground(Color.white);
		jToggleButton3.setBorder(null);
		jToggleButton3.setHorizontalTextPosition(SwingConstants.CENTER);
		jToggleButton3.setIcon(new ImageIcon(FrmAdministrador.class
				.getResource("img/clientes.png")));
		jToggleButton3.setText("Clientes");
		jToggleButton3.setVerticalAlignment(SwingConstants.BOTTOM);
		jToggleButton3.setVerticalTextPosition(SwingConstants.BOTTOM);
		jToggleButton4.setBackground(Color.white);
		jToggleButton4.setBorder(null);
		jToggleButton4.setHorizontalTextPosition(SwingConstants.CENTER);
		jToggleButton4.setIcon(new ImageIcon(FrmAdministrador.class
				.getResource("img/pos.png")));
		jToggleButton4.setText("Punto de Venta");
		jToggleButton4.setVerticalTextPosition(SwingConstants.BOTTOM);
		jToggleButton5.setBackground(Color.white);
		jToggleButton5.setBorder(null);
		jToggleButton5.setHorizontalTextPosition(SwingConstants.CENTER);
		jToggleButton5.setIcon(new ImageIcon(FrmAdministrador.class
				.getResource("img/utils.png")));
		jToggleButton5.setText("Administrador");
		jToggleButton5.setVerticalTextPosition(SwingConstants.BOTTOM);
		jPanel2.setBackground(Color.white);
		jPanel2.setMinimumSize(new Dimension(10, 50));
		jPanel2.setPreferredSize(new Dimension(10, 70));
		this.getContentPane().setBackground(Color.white);
		this.getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);
		jPanel1.add(jToggleButton4);
		jPanel1.add(jToggleButton3);
		jPanel1.add(jToggleButton2);
		jPanel1.add(jToggleButton1);
		jPanel1.add(jToggleButton5);
		this.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
		jPanel1.setLayout(verticalFlowLayout1);
	}

	public static void main(String args[]) {
		FrmAdministrador frm = new FrmAdministrador();
		JFrame.setDefaultLookAndFeelDecorated(true);
		frm.setVisible(true);
	}
}
