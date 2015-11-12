package com.boutique.engine.impl;

import java.sql.*;
import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;

public class HistorialClienteEngine {
	public java.util.List<Venta> ventasEncontradas = new ArrayList<Venta>();
	public Cliente cliente = null;
	public VentaCredito venta = null;
	public List<Object[]> intervaloPagos = new ArrayList<Object[]>();
	public List<Object[]> pagosView = new ArrayList<Object[]>();
	public List<Object[]> productosView = new ArrayList<Object[]>();
	List<Pago> pagosDesglosados = new ArrayList<Pago>(); // LISTA QUE NOS
															// PERMITIRA
															// DESGLOSAR CADA
															// PAGO QUE SE
															// REALIZA A CADA
															// NOTA
	public List<Pago> pagos = new ArrayList<Pago>();
	public Set<VentaCredito> ventasaFinalizar = new HashSet<VentaCredito>();

	public List<Venta> getHistorialCliente() {
		if (cliente != null) {
			// DEBEMOS BUSCAR TODAS LAS NOTAS DEL CLIENTE TANTO CONTADO, CREDITO
			// Y APARTADO ORDENADA DE LA MAS NUEVA A LA MAS ANTIGUA QUE NO?
			// BUSCAMOS TODAS LAS NOTAS DE TIPO CREDITO QUE EL STATUS ES
			// ABIERTA, ORDENADAS DE LA MAS ANTIGUA A LA MAS NUEVA
			ventasEncontradas = DaoVentas.findHistorialCliente(cliente.getId());
			return ventasEncontradas;
		} else {
			return null;
		}
	}

	public boolean abonaraVenta(int idVenta, double monto) {
		return false;
	}

	public Venta seleccionarVenta(int index) {
		Venta v = ventasEncontradas.get(index);

		// Ahora ponemos los productos vendidos
		v.setProductosVendidos(DaoVentas.getProductosVendidos(v.getId()));
		// AHORA LOS PAGOS DEPENDIENDO DEL TIPO DE VENTA PARA CALCULAR O NO EL
		// TOTAL
		if (v instanceof VentaCredito) {
			v.setPagos(DaoVentas.getPagos(v.getId()));
		} else {
			v.setPagos(DaoVentas.getPagos(v));
		}

		productosView.clear();
		pagosView.clear();

		for (ProductoVendido p : v.getProductosVendidos()) {
			Object[] row = new Object[6];
			row[0] = "1";
			row[1] = p.getDescripcion();
			row[2] = p.getEtiqueta();
			row[3] = AppInstance.number.format(p.getPrecioInicial());
			row[4] = p.getDescuento() + "%";
			row[5] = AppInstance.number.format(p.getPrecioFinal());
			/*
			 * switch (p.getEntregado()) { case 0: row[6] = "No"; break; case 1:
			 * row[6] = "Si";
			 * 
			 * // montoUsado += pv.getPrecioFinal(); break; }
			 */

			productosView.add(row);
		}

		for (Pago p : v.getPagos()) {
			Object[] row = new Object[4];
			row[0] = p.getId();
			row[1] = AppInstance.formatoCorto.format(p.getFecha());
			row[2] = AppInstance.number.format(p.getMonto());
			if (p instanceof PagoTarjetaCredito) {
				PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
				row[3] = "T Credito/debito "
						+ pt.getBanco()
						+ ((pt.getMeses() > 0) ? pt.getMeses()
								+ " meses sin intereses" : "");
			} else if (p instanceof PagoVale) {
				PagoVale pv = (PagoVale) p;
				row[3] = "Vale no. " + pv.getIdVale();

			} else if (p instanceof PagoCheque) {
				PagoCheque pch = (PagoCheque) p;
				row[3] = "Cheque no. " + pch.getNoCheque() + " de "
						+ pch.getBanco();

			} else if (p instanceof PagoTransferenciaBancaria) {
				PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
				row[3] = "Depósito/transf. bancaria, ref: "
						+ pb.getNoReferencia();
			} else if (p instanceof PagoDevolucion) {
				PagoDevolucion pv = (PagoDevolucion) p;
				row[3] = "Descuento por entrega de vale no. "
						+ pv.getVale().getId();
				pv = null;
			}

			else if (p instanceof Pago) {
				row[3] = "Efectivo";
			}
			pagosView.add(row);

		}
		if (v instanceof VentaCredito) {
			calcularFechasDePago((VentaCredito) v);
		}
		return v;
	}

	public List<Pago> registrarPagos(List<Pago> pagos) {

		try {
			pagos = DaoVentas.registrarPagos(venta.getId(), pagos, null);
			return pagos;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public void calcularFechasDePago(VentaCredito venta) {

		Object[] row = new Object[3];
		// double pagoIndividual = Math.ceil(this.total / numeroPagos);
		double pagoIndividual = (venta.abonado) / venta.getNoPagos();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(venta.getFecha());
		this.intervaloPagos.clear();
		for (int i = 0; i < venta.getNoPagos(); i++) {
			row = new Object[3];
			cal.add(Calendar.DAY_OF_MONTH, venta.getNoDias());
			row[0] = String.valueOf(i + 1);
			row[1] = AppInstance.formatoCorto.format(cal.getTime());
			row[2] = AppInstance.number.format(pagoIndividual);
			this.intervaloPagos.add(row);
		}
	}

	/**
	 * finalizarVenta
	 */
	public void finalizarVenta() {
		DaoVentas.finalizarVenta(venta);
		venta.setStatus(1);
	}

	/**
	 * imprimirPagos
	 * 
	 * @param pagosaVentas
	 *            List
	 * @param d
	 *            double
	 */
	public void imprimirPagosAbonosaNota(List<Pago> pagosaVentas,
			double montoAcumulado, double saldoAnterior) {
		com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.PrintTask(
				5, pagosaVentas, venta, cliente, montoAcumulado, saldoAnterior); // Venta
																					// de
																					// contado
		Thread t = new Thread(obj);
		t.start();

	}

	/**
	 * imprimirPagosMasVencidos
	 * 
	 * @param saldoTotal
	 *            double
	 * @param saldoVencido
	 *            double
	 * @param montoAcumulado
	 *            double
	 * @param pagosView
	 *            List
	 */
	public void imprimirPagosMasVencidos(double saldoTotal,
			double saldoVencido, double montoAcumulado, List<?> pagosView) {
		com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.PrintTask(
				6, pagosView, venta, cliente, montoAcumulado, saldoTotal,
				saldoVencido); // Venta de contado
		Thread t = new Thread(obj);
		t.start();

	}

}
