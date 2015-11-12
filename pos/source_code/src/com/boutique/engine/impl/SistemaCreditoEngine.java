package com.boutique.engine.impl;

import java.sql.*;
import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.helper.IvaHelper;

public class SistemaCreditoEngine {
	public java.util.List<VentaCredito> ventasEncontradas = new ArrayList<VentaCredito>();
	public Cliente cliente = null;
	public VentaCredito venta = null;
	public List<Object[]> pagosView = new ArrayList<Object[]>();
	public List<Object[]> intervaloPagos = new ArrayList<Object[]>();
	public List<Object[]> productosView = new ArrayList<Object[]>();
	public List<Integer> idProductos = new ArrayList<Integer>();
	List<Pago> pagosDesglosados = new ArrayList<Pago>(); // LISTA QUE NOS
															// PERMITIRA
															// DESGLOSAR CADA
															// PAGO QUE SE
															// REALIZA A CADA
															// NOTA
	public List<Pago> pagos = new ArrayList<Pago>();
	public Set<VentaCredito> ventasaFinalizar = new HashSet<VentaCredito>();
	public boolean agregarPagosAlCorte = true;

	public List<VentaCredito> getResumenEstadoCuenta() {
		if (cliente != null) {

			// BUSCAMOS TODAS LAS NOTAS DE TIPO CREDITO QUE EL STATUS ES
			// ABIERTA, ORDENADAS DE LA MAS ANTIGUA A LA MAS NUEVA
			ventasEncontradas = DaoVentas.findByIdCliente(cliente.getId());
			return ventasEncontradas;
		} else {
			return null;
		}
	}

	private void iniciarAbonosaNotas(java.util.List<Pago> pagos) {
		ventasaFinalizar.clear();
		pagosDesglosados.clear();
		this.pagos = pagos;

	}

	private void abonosaNotas(VentaCredito vc, double cantidad) {
		double cantidadPendiente = cantidad;
		// TENEMOS LOS PAGOS Y EL MONTO.. RECORREMOS POR LOS PAGOS PARA VER DE
		// CUAL PODEMOS ECHAR MANO.. MIENTRAS QUE LA CANTIDAD
		// SEA MAYOR A CERO DE CADA PAGO
		for (Pago pago : pagos) {
			if (pago.getMonto() > 0 && cantidadPendiente > 0) { // REVISAMOS QUE
																// HAYA DINERO
																// EN ESTE PAGO
																// Y QUE TODAVIA
																// HAYA UNA
																// CANTIDAD POR
																// OBTENER DE
																// ALGUN PAGO
				// REVISAMOS LA CANTIDAD A ABONAR
				double montoDelPagoDesglosado = 0;
				if (cantidadPendiente > pago.getMonto()) { // SI LA CANTIDAD ES
															// MAYOR A LO
															// DISPONIBLE EN EL
															// PAGO
					montoDelPagoDesglosado = pago.getMonto();
					cantidadPendiente -= pago.getMonto();
					pago.setMonto(0); // PONEMOS EL PAGO A CERO
					pago.setImpuestoMonto(0);

				} else if (cantidadPendiente <= pago.getMonto()) { // SI LA
																	// CANTIDAD
																	// ES MENOR
																	// O IGUAL A
																	// LO
																	// DISPONIBLE
																	// EN EL
																	// PAGO
					montoDelPagoDesglosado = cantidadPendiente; // PONEMOS EL
																// PAGO
																// DESGLOSADO
																// CON EL MONTO
																// DEL PAGO
																// ORIGINAL
					pago.setMonto(pago.getMonto() - cantidadPendiente); // RESTAMOS
																		// EL
																		// PAGO
																		// LA
																		// CANTIDAD
																		// PENDIENTE
																		// A
																		// ABONAR
					pago.setImpuestoMonto(IvaHelper
							.getIvaDesglosadFromMonto(pago.getMonto()));

					cantidadPendiente = 0;
				}
				if (pago instanceof PagoTarjetaCredito) {
					PagoTarjetaCredito pagoDesglosado = new PagoTarjetaCredito();
					PagoTarjetaCredito pagoTmp = (PagoTarjetaCredito) pago;
					// AGREGAMOS EL PAGO DESGLOSADO
					pagoDesglosado.setMonto(montoDelPagoDesglosado);
					pagoDesglosado.setImpuestoMonto(IvaHelper
							.getIvaDesglosadFromMonto(montoDelPagoDesglosado));
					pagoDesglosado.setBanco(pagoTmp.getBanco());
					pagoDesglosado.setMeses(pagoTmp.getMeses());
					if (this.agregarPagosAlCorte) {
						pagoDesglosado.setEnCorte(0);
					} else {
						pagoDesglosado.setEnCorte(-1);
					}
					pagoDesglosado.setEsAnticipo(pago.getEsAnticipo());
					pagoDesglosado.setFecha(pago.getFecha());
					pagoDesglosado.setId(pago.getId());
					pagoDesglosado.setIdSucursal(pago.getIdSucursal());
					pagoDesglosado.setIdVendedor(pago.getIdVendedor());
					pagoDesglosado.setIdTerminal(pago.getIdTerminal());
					pagoDesglosado.setIdVenta(vc.getId());
					pagoDesglosado.setTipo(pago.getTipo());

					pagosDesglosados.add(pagoDesglosado);
				} else if (pago instanceof PagoCheque) {
					PagoCheque pagoDesglosado = new PagoCheque();
					PagoCheque pagoTmp = (PagoCheque) pago;
					pagoDesglosado.setMonto(montoDelPagoDesglosado);
					pagoDesglosado.setImpuestoMonto(IvaHelper
							.getIvaDesglosadFromMonto(montoDelPagoDesglosado));

					pagoDesglosado.setBanco(pagoTmp.getBanco());
					pagoDesglosado.setNoCheque(pagoTmp.getNoCheque());
					if (this.agregarPagosAlCorte) {
						pagoDesglosado.setEnCorte(0);
					} else {
						pagoDesglosado.setEnCorte(-1);
					}
					pagoDesglosado.setEsAnticipo(pago.getEsAnticipo());
					pagoDesglosado.setFecha(pago.getFecha());
					pagoDesglosado.setId(pago.getId());
					pagoDesglosado.setIdSucursal(pago.getIdSucursal());
					pagoDesglosado.setIdVendedor(pago.getIdVendedor());
					pagoDesglosado.setIdTerminal(pago.getIdTerminal());
					pagoDesglosado.setIdVenta(vc.getId());
					pagoDesglosado.setTipo(pago.getTipo());

					pagosDesglosados.add(pagoDesglosado);
				} else if (pago instanceof PagoVale) {
					PagoVale pagoDesglosado = new PagoVale();
					PagoVale pagoTmp = (PagoVale) pago;
					pagoDesglosado.setMonto(montoDelPagoDesglosado);
					pagoDesglosado.setImpuestoMonto(IvaHelper
							.getIvaDesglosadFromMonto(montoDelPagoDesglosado));
					pagoDesglosado.setIdVale(pagoTmp.getIdVale());
					pagoDesglosado.setNumero(pagoTmp.getNumero());
					if (this.agregarPagosAlCorte) {
						pagoDesglosado.setEnCorte(0);
					} else {
						pagoDesglosado.setEnCorte(-1);
					}
					pagoDesglosado.setEsAnticipo(pago.getEsAnticipo());
					pagoDesglosado.setFecha(pago.getFecha());
					pagoDesglosado.setId(pago.getId());
					pagoDesglosado.setIdSucursal(pago.getIdSucursal());
					pagoDesglosado.setIdVendedor(pago.getIdVendedor());
					pagoDesglosado.setIdTerminal(pago.getIdTerminal());
					pagoDesglosado.setIdVenta(vc.getId());
					pagoDesglosado.setTipo(pago.getTipo());

					pagosDesglosados.add(pagoDesglosado);
				} else if (pago instanceof PagoTransferenciaBancaria){
					PagoTransferenciaBancaria pagoDesglosado = new PagoTransferenciaBancaria();
					PagoTransferenciaBancaria pagoTmp = (PagoTransferenciaBancaria) pago;
					// AGREGAMOS EL PAGO DESGLOSADO
					pagoDesglosado.setMonto(montoDelPagoDesglosado);
					pagoDesglosado.setImpuestoMonto(IvaHelper
							.getIvaDesglosadFromMonto(montoDelPagoDesglosado));
					pagoDesglosado.setNoReferencia(pagoTmp.getNoReferencia());
					if (this.agregarPagosAlCorte) {
						pagoDesglosado.setEnCorte(0);
					} else {
						pagoDesglosado.setEnCorte(-1);
					}
					pagoDesglosado.setEsAnticipo(pago.getEsAnticipo());
					pagoDesglosado.setFecha(pago.getFecha());
					pagoDesglosado.setId(pago.getId());
					pagoDesglosado.setIdSucursal(pago.getIdSucursal());
					pagoDesglosado.setIdVendedor(pago.getIdVendedor());
					pagoDesglosado.setIdTerminal(pago.getIdTerminal());
					pagoDesglosado.setIdVenta(vc.getId());
					pagoDesglosado.setTipo(pago.getTipo());

					pagosDesglosados.add(pagoDesglosado);
					
				} else if (pago instanceof Pago) {
					Pago pagoDesglosado = new Pago();
					pagoDesglosado.setMonto(montoDelPagoDesglosado);
					pagoDesglosado.setImpuestoMonto(IvaHelper
							.getIvaDesglosadFromMonto(montoDelPagoDesglosado));

					if (this.agregarPagosAlCorte) {
						pagoDesglosado.setEnCorte(0);
					} else {
						pagoDesglosado.setEnCorte(-1);
					}
					pagoDesglosado.setEsAnticipo(pago.getEsAnticipo());
					pagoDesglosado.setFecha(pago.getFecha());
					pagoDesglosado.setId(pago.getId());
					pagoDesglosado.setIdSucursal(pago.getIdSucursal());
					pagoDesglosado.setIdVendedor(pago.getIdVendedor());
					pagoDesglosado.setIdTerminal(pago.getIdTerminal());
					pagoDesglosado.setIdVenta(vc.getId());
					pagoDesglosado.setTipo(pago.getTipo());

					pagosDesglosados.add(pagoDesglosado); // GENERAMOS EL PAGO
															// DESGLOSADO CON LA
															// VENTA
															// CORRESPONDIENTE Y
															// EL MONTO IGUAL AL
															// MONTO DISPONIBLE
															// EN EL PAGO
				}

			}
		}
	}

	public List<Object[]> abonarMasVencido(List<Pago> pagos, double monto) {
		// List<Pago> pagosDesglosados; //LISTA QUE NOS PERMITIRA DESGLOSAR CADA
		// PAGO QUE SE REALIZA A CADA NOTA

		double montoaAplicar = monto;
		// TENEMOS LAS NOTAS
		iniciarAbonosaNotas(pagos);
		for (VentaCredito vc : ventasEncontradas) {
			if (vc.saldoVencido > 0 && montoaAplicar > 0) {
				if (vc.saldoVencido > montoaAplicar) { // SI LO VENCIDO ES MAYOR
														// A LO ABONADO
					abonosaNotas(vc, montoaAplicar); // ABONAMOS TODO A ESA NOTA
														// (AGREGANDO EL PAGO Y
														// SU TIPO)
					montoaAplicar = 0;
				} else if (vc.saldoVencido <= montoaAplicar) { // SI LO VENCIDO
																// ES MENOR O
																// IGUAL AL
																// MONTO A
																// ABONARO
					abonosaNotas(vc, vc.saldoVencido); // AGREGAMOS EL PAGO
					// SI LO VENCIDO ES IGUAL AL SALDO ACTUAL PONEMOS LA NOTA
					// PARA TERMINAR

					if ((vc.saldoActual - vc.saldoVencido) <= 0.05) {
						ventasaFinalizar.add(vc);
					}
					montoaAplicar -= vc.saldoVencido; // DESCONTAMOS LO VENCIDO
				}
			}
		}

		// SI SOBRO DINERO.. VEMOS QUE NOTAS PODEMOS TERMINAR
		double diferenciaaPagar = 0;
		if (montoaAplicar > 0) {
			for (VentaCredito vc : ventasEncontradas) {

				diferenciaaPagar = vc.saldoActual - vc.saldoVencido; // (LA
																		// DIFERENCIA
																		// ENTRE
																		// EL
																		// SALDO
																		// ACTUAL
																		// Y EL
																		// SALDO
																		// VENCIDO)
				if (montoaAplicar > 0 && diferenciaaPagar > 0) { // MIENTRAS
																	// HAYA
																	// DINERO Y
																	// DIFERENCIA
																	// A PAGAR
					if (diferenciaaPagar <= montoaAplicar) { // SI
																// diferenciaaPagar
																// ES MENOR O
																// IGUAL A LO
																// QUE QUEDA,
						abonosaNotas(vc, diferenciaaPagar); // AGREGAMOS EL
															// PAGO, FINALIZAMOS
															// LA VENTA Y
															// RESTAMOS EL SALDO
															// TOTAL€
						if ((vc.saldoActual - diferenciaaPagar) <= 0.05) {
							ventasaFinalizar.add(vc);
						}
						montoaAplicar -= diferenciaaPagar;
					} else if (diferenciaaPagar > montoaAplicar) { // SI
																	// diferenciaaPagar
																	// ES MAYOR
																	// A LO QUE
																	// QUEDA,
						abonosaNotas(vc, montoaAplicar); // AGREGAMOS EL PAGO,
						montoaAplicar = 0; // YA NO HAY MAS DINERO POR QUITAR
					}

				}
			}
		}
		// TENEMOS LOS PAGOS DESGLOSADOS, LOS EJECUTAMOS
		try {
			List<Object[]> pagosAVentas = new ArrayList<Object[]>();
			Set<?> ventasFinalizadas;
			Object[] row = null;
			pagosDesglosados = DaoVentas.registrarPagos(pagosDesglosados);
			ventasFinalizadas = DaoVentas.finalizarVentas(ventasaFinalizar);

			for (Pago p : pagosDesglosados) { // POR CADA PAGO ARMAMOS LA VISTA
												// JUNTO CON EL STATUS DE LA
												// VENTA
				row = new Object[5];
				row[0] = p.getId();
				row[1] = p.getIdVenta();
				row[2] = AppInstance.number.format(p.getMonto());
				if (p instanceof PagoTarjetaCredito) {
					PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
					row[3] = "T Credito/debito "
							+ pt.getBanco()
							+ ((pt.getMeses() > 0) ? pt.getMeses()
									+ " meses sin intereses" : "");
				} else if (p instanceof PagoVale) {
					PagoVale pv = (PagoVale) p;
					row[3] = "Vale no. " + pv.getNumero();

				} else if (p instanceof PagoCheque) {
					PagoCheque pch = (PagoCheque) p;
					row[3] = "Cheque no. " + pch.getNoCheque() + " de "
							+ pch.getBanco();

				} else if (p instanceof PagoTransferenciaBancaria) {
					PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
					row[3] = "Depósito/transf. bancaria, ref: "
							+ pb.getNoReferencia();
				} else if (p instanceof Pago) {
					row[3] = "Efectivo";
				}
				if (ventasFinalizadas.contains(p.getIdVenta())) {
					row[4] = "FINALIZADA";
				} else {
					row[4] = "ABIERTA";
				}
				pagosAVentas.add(row);
			}
			return pagosAVentas;
		}
		//

		catch (SQLException ex) {
			System.out.println(ex.toString());
			try {
				DaoSource.getConnectionLocal().rollback();
			} catch (SQLException ee) {
			}
			return null;

		}
	}

	public boolean abonaraVenta(int idVenta, double monto) {
		return false;
	}

	public void seleccionarVenta(int index) {
		venta = ventasEncontradas.get(index);
		// Buscamos la venta.. si se encuentra la mostramos..
		// Ponemos los pagos
		venta.setPagos(DaoVentas.getPagos(venta.getId()));
		// Ahora ponemos los productos vendidos
		venta.setProductosVendidos(DaoVentas.getProductosVendidos(venta.getId()));
		productosView.clear();
		idProductos.clear();
		pagosView.clear();
		for (ProductoVendido p : venta.getProductosVendidos()) {
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
			idProductos.add(p.getId());
			productosView.add(row);

		}

		for (Pago p : venta.getPagos()) {
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
			} else if (p instanceof Pago) {
				row[3] = "Efectivo";
			}
			pagosView.add(row);
		}
		calcularFechasDePago();
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

	public void calcularFechasDePago() {

		Object[] row = new Object[3];
		// double pagoIndividual = Math.ceil(this.total / numeroPagos);
		double pagoIndividual = (venta.saldoTotal) / venta.getNoPagos();
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

	public void imprimirPagosAbonosaNotaPagoAnticipado(List<Pago> pagosaVentas,
			double montoAcumulado, double saldoAnterior) {
		com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.PrintTask(
				9, pagosaVentas, venta, cliente, montoAcumulado, saldoAnterior); // Venta
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

	/**
	 * finalizarNotaConDescuento
	 * 
	 * @param promocion
	 *            DescPagoAnticipado
	 */
	public boolean finalizarNotaConDescuento(DescPagoAnticipado promocion,
			List<Pago> pagos) {
		return DaoVentas.finalizarNotaConDescuento(promocion, venta, pagos);

	}

}
