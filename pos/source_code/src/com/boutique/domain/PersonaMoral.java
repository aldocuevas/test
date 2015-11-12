package com.boutique.domain;

import com.boutique.reportes.facturacion.dto.DomicilioDTO;
import com.boutique.reportes.facturacion.dto.StringFriendlyDTO;

public class PersonaMoral extends StringFriendlyDTO {

	private String nombre;
	private DomicilioDTO domicilio;
	private String rfc;
	private String leyenda;

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	public DomicilioDTO getDomicilio() {
		return domicilio;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getRfc() {
		return rfc;
	}

	/**
	 * @param leyenda the leyenda to set
	 */
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	/**
	 * @return the leyenda
	 */
	public String getLeyenda() {
		return leyenda;
	}
}
