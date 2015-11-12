package com.boutique.reportes.facturacion.dto;

public class DatosClienteDTO extends StringFriendlyDTO{
	private String nombreCompleto;
	private String rfc;
	private DomicilioDTO domicilio;
	private String telefono;
	private String correoElectronico;

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public DomicilioDTO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

}
