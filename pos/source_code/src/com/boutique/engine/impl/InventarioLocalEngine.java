package com.boutique.engine.impl;

import com.boutique.dao.DaoInventarios;
import com.boutique.domain.TipoProducto;

public class InventarioLocalEngine {
	String etiqueta;

	/**
	 * agregarInventarioLocal
	 * 
	 * @param monto
	 *            double
	 * @param tipoProducto
	 *            TipoProducto
	 */
	public void agregarInventarioLocal(double monto, TipoProducto tipoProducto,
			int idBoutique) {
		// Generamos la etiqueta
		etiqueta = DaoInventarios.agregarInventarioLocal(tipoProducto.getId(),
				monto, idBoutique);
		// Obtenemos el ultimo modelo en el inventairo

	}

	public String etiqueta() {
		return etiqueta;
	}

}
