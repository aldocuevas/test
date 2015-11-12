package com.boutique.impresiones;

import java.util.ArrayList;
import java.util.List;

import com.boutique.domain.Cliente;
import com.boutique.domain.Vale;
import com.boutique.domain.Venta;
import com.boutique.domain.VentaApartado;
import com.boutique.domain.VentaCredito;

public class PrintTask implements Runnable {
	int action;
	com.boutique.domain.Venta venta;
	@SuppressWarnings("rawtypes")
	List listaPagos = new ArrayList();
	Cliente cliente;
	double montoAcumulado;
	Vale vale;
	private double saldoVencido;
	private double saldoTotal;
	@SuppressWarnings("rawtypes")
	List corte;
	private List<Object[]> salidas;
	double dineroRecogido;
	double dineroEnCaja;

	public PrintTask(int action, com.boutique.domain.Venta venta) {
		this.action = action;
		this.venta = venta;
	}

	@SuppressWarnings("rawtypes")
	public PrintTask(com.boutique.domain.Venta venta, List listaPagos,
			Cliente cliente) {
		this.cliente = cliente;
		this.listaPagos = listaPagos;
		this.action = 2;
		this.venta = venta;
	}

	@SuppressWarnings("rawtypes")
	public PrintTask(int action, List listaPagos, Venta venta,
			double montoAcumulado, double saldoAnterior) {
		this.action = action;
		this.listaPagos = listaPagos;
		this.saldoTotal = saldoAnterior;
		this.venta = venta;
		this.montoAcumulado = montoAcumulado;
	}

	@SuppressWarnings("rawtypes")
	public PrintTask(int action, List listaPagos, Venta venta, Cliente cliente,
			double montoAcumulado, double saldoTotal) {
		this.action = action;
		this.listaPagos = listaPagos;
		this.cliente = cliente;
		this.venta = venta;
		this.saldoTotal = saldoTotal;
		this.montoAcumulado = montoAcumulado;
	}

	public PrintTask(int action, Vale vale) {
		this.vale = vale;
		this.action = action;
	}

	@SuppressWarnings("rawtypes")
	public PrintTask(int action, List pagosView, Venta venta, Cliente cliente,
			double montoAcumulado, double saldoTotal, double saldoVencido) {
		this.action = action;
		this.listaPagos = pagosView;
		this.venta = venta;
		this.cliente = cliente;
		this.montoAcumulado = montoAcumulado;
		this.saldoTotal = saldoTotal;
		this.saldoVencido = saldoVencido;
	}

	public PrintTask(int action, @SuppressWarnings("rawtypes") List corte,
			double dineroRecogido, @SuppressWarnings("rawtypes") List salidas) {
		this.action = action;
		this.corte = corte;
		this.dineroRecogido = dineroRecogido;
		this.salidas = salidas;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		switch (action) {
		case 1:
			com.boutique.impresiones.ImpresionVentas
					.imprimirVentaContado(this.venta);
			break;
		case 2:
			com.boutique.impresiones.ImpresionVentas.imprimirVentaCredito(
					(com.boutique.domain.VentaCredito) this.venta, listaPagos,
					this.cliente);
			break;
		case 3:
			com.boutique.impresiones.ImpresionVentas
					.imprimirVentaApartado((VentaApartado) this.venta);
			break;
		case 4:
			com.boutique.impresiones.ImpresionVentas
					.imprimirValeDeCompra(this.vale);
			break;
		case 5:
			com.boutique.impresiones.ImpresionVentas.imprimirAbonosaNota(
					listaPagos, venta, cliente, montoAcumulado, saldoTotal);
			break;
		case 6:
			com.boutique.impresiones.ImpresionVentas.imprimirAbonosAMasVencido(
					listaPagos, venta, cliente, montoAcumulado, saldoTotal,
					saldoVencido);
			break;
		case 7:
			com.boutique.impresiones.ImpresionVentas
					.imprimirAbonosaNotaApartado(listaPagos,
							(VentaApartado) venta, montoAcumulado, saldoTotal);
			break;
		case 8:
			com.boutique.impresiones.ImpresionVentas.imprimirCorteDeCaja(corte,
					dineroRecogido, this.salidas);
			break;
		case 9:
			com.boutique.impresiones.ImpresionVentas
					.imprimirTicketNotaPagoAnticipado(listaPagos,
							(VentaCredito) venta, cliente, montoAcumulado,
							saldoTotal);

			break;
		}
	}
}
