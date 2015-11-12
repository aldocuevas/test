package com.boutique.engine.impl;

import org.apache.commons.lang3.StringUtils;

import com.boutique.domain.Cliente;
import com.boutique.domain.Venta;

 
public class RegistroFacturaIndividualEngine implements IRegistroFacturaEngine {
	private Venta venta;
	private Cliente cliente;

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Venta getVenta() {
		return venta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public boolean clienteAsignadoAVenta() {
		if (cliente != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean clienteTieneCorreoRegistrado() {
		if (cliente != null && StringUtils.isNotEmpty(cliente.getEmail())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean clienteTieneRFCRegistrado() {
		if (cliente != null && StringUtils.isNotEmpty(cliente.getRfc())) {
			return true;
		}
		return false;
	}

	@Override
	public void marcarVentaParaFacturaIndividual() {
		venta.setRequiereFacturaIndividual(true);
 	}
}

