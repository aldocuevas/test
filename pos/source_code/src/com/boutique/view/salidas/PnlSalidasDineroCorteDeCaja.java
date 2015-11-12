package com.boutique.view.salidas;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.boutique.dao.DaoSalidas;
import com.boutique.domain.SalidaCaja;
import com.boutique.engine.impl.AppInstance;
import com.boutique.populator.ModelSalidasPopulator;
import com.boutique.view.tablemodels.ModelSalidas;

public class PnlSalidasDineroCorteDeCaja extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable tl = null;
	private ModelSalidas modelSalidas = null;

	public ModelSalidas getModelSalidas() {
		return modelSalidas;
	}

	public void setModelSalidas(ModelSalidas modelSalidas) {
		this.modelSalidas = modelSalidas;
	}

	private List<SalidaCaja> salidasCaja = new ArrayList<SalidaCaja>();
	private JLabel lblTitle = null;

	/**
	 * This is the default constructor
	 */
	public PnlSalidasDineroCorteDeCaja() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		lblTitle = new JLabel();
		lblTitle.setText("                SALIDAS DE CAJA REGISTRADAS");
		this.setSize(300, 117);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(lblTitle, null);
		this.add(getJScrollPane(), null);

	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setPreferredSize(new Dimension(453, 100));
			jScrollPane.setViewportView(getTl());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tl
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getTl() {
		if (tl == null) {
			tl = new JTable();
			modelSalidas = new ModelSalidas();
			tl.setName("tblSalidas");
			tl.setModel(modelSalidas);
			salidasCaja = DaoSalidas
					.findSalidasCajaByidTerminalEnCorte(AppInstance.terminal()
							.getId());
			ModelSalidasPopulator.populateModelSalidas(salidasCaja,
					modelSalidas);
		}

		return tl;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
