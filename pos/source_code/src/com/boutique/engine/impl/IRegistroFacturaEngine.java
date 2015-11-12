package com.boutique.engine.impl;

import com.boutique.domain.Cliente;

public interface IRegistroFacturaEngine {
	/**
	 * Verifica si hay un cliente asignado a la venta.
	 * 
	 * @return true si hay un cliente asignado
	 */
	public boolean clienteAsignadoAVenta();

	/**
	 * Verifica si el correo electronico del cliente esta registrado.
	 * 
	 * @return true si el cliente tiene correo registrado, falso si no.
	 */
	public boolean clienteTieneCorreoRegistrado();
	
	/**
	 * Verifica si el RFC del cliente esta registrado.
	 * @return
	 */
	public boolean clienteTieneRFCRegistrado();

	/**
	 * Marca la venta que sera facturada manualmente.
	 */
	public void marcarVentaParaFacturaIndividual();
	
	 
}
