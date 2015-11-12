package com.boutique.enums;

/**
 * Indica el tipo de reporte de facturacion generado.
 * 
 * @author aldo
 * 
 */
public enum TipoReporteFacturacion {
	PUBLICO_GENERAL("Publico en general"), INDIVIDUAL_CONTADO(
			"Ventas de contado"), INDIVIDUAL_CREDITO("Ventas de credito"), INDVIDUAL_APARTADO(
			"Apartados");
	private String friendlyText;

	private TipoReporteFacturacion(String friendlyText) {
		this.setFriendlyText(friendlyText);
	}

	public void setFriendlyText(String friendlyText) {
		this.friendlyText = friendlyText;
	}

	public String getFriendlyText() {
		return friendlyText;
	}
	
}
