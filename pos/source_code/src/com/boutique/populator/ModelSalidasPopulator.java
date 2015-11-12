package com.boutique.populator;

import java.util.List;

import com.boutique.domain.SalidaCaja;
import com.boutique.engine.impl.AppInstance;
import com.boutique.view.tablemodels.ModelSalidas;

public class ModelSalidasPopulator {

	public static void addSalidaRow(ModelSalidas modelSalidas,
			String conceptoSalida, double monto) {
		Object[] row = new Object[2];
		row[0] = conceptoSalida;
		row[1] = AppInstance.number.format(monto);
		modelSalidas.data.add(row);
	}

	public static void populateModelSalidas(List<SalidaCaja> salidasCaja,
			ModelSalidas modelSalidas) {
		modelSalidas.data.clear();

		if (salidasCaja != null && salidasCaja.size() > 0) {
			for (SalidaCaja salida : salidasCaja) {
				ModelSalidasPopulator.addSalidaRow(modelSalidas,
						salida.getConcepto(), salida.getMonto());
			}
			modelSalidas.fireTableDataChanged();
		}
	}
}
