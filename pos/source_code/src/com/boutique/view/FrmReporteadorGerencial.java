package com.boutique.view;

import java.awt.*;
import javax.swing.*;

public class FrmReporteadorGerencial extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout borderLayout1 = new BorderLayout();
	PnlReporteadorGerencial pnlReporteadorGerencial1 = new PnlReporteadorGerencial();

	public FrmReporteadorGerencial() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setTitle("REPORTEADOR GERENCIAL");
		getContentPane().setLayout(borderLayout1);
		this.getContentPane().add(pnlReporteadorGerencial1,
				java.awt.BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new FrmReporteadorGerencial();
	}
}
