package com.boutique.reportes.facturacion.dto;

import java.util.List;

public class FacturaClienteIndividualDTO extends StringFriendlyDTO {
	private String enviarPorCorreo;
	private DatosClienteDTO cliente;
	private List<FacturaDatosGeneralesDTO> datosFactura;

	public DatosClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(DatosClienteDTO cliente) {
		this.cliente = cliente;
	}

	public void setDatosFactura(List<FacturaDatosGeneralesDTO> datosFactura) {
		this.datosFactura = datosFactura;
	}

	public List<FacturaDatosGeneralesDTO> getDatosFactura() {
		return datosFactura;
	}

	public void setEnviarPorCorreo(String enviarPorCorreo) {
		this.enviarPorCorreo = enviarPorCorreo;
	}

	public String getEnviarPorCorreo() {
		return enviarPorCorreo;
	}

}
