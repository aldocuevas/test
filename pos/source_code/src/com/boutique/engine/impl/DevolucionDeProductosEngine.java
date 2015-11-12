package com.boutique.engine.impl;

import java.util.*;

import com.boutique.impresiones.*;
import com.boutique.dao.*;
import com.boutique.domain.*;

import static com.boutique.engine.impl.AppInstance.*;

public class DevolucionDeProductosEngine {
	public DevolucionDeProductosEngine() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	Vale vale = null;
	Venta venta = null;
	Cliente cliente = null;
	List<Object[]> productosView = new ArrayList<Object[]>();
	List<Object[]> pagosView = new ArrayList<Object[]>();
	List<Venta> ventasEncontradas;
	public List<ProductoVendido> productosADevolver = new ArrayList<ProductoVendido>();
	double montoaDevolver;
	double total;
	double totalInicial;
	double abonado;
	int cantidadInicialProductos = 0;

	public Vale vale() {
		return vale;
	}

	public Venta venta() {
		return venta;
	}

	public void imprimirVale() {
		com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.PrintTask(
				4, vale);
		Thread t = new Thread(obj);
		t.start();
	}

	public void imprimirDevolucion() throws Exception {

		ImpresionDevolucion imp = new ImpresionDevolucion();
		if (productosADevolver.size() == cantidadInicialProductos) { // NO SE
																		// USA
																		// EL
																		// TRUE,FALSE
			imp.imprimirDevolucion(venta, totalInicial, total,
					productosADevolver, true);
		} else {
			imp.imprimirDevolucion(venta, totalInicial, total,
					productosADevolver, false);
		}
	}

	public void devolverProducto(int index) {
		// Agregamos el producto a devolver
		ProductoVendido pv = venta.getProductosVendidos().get(index);
		productosADevolver.add(pv);
		// Quitamos el producto de la lista que se muestra en pantalla
		this.venta.getProductosVendidos().remove(index);
		this.productosView.remove(index);
		// Agregamos al monto a devolver

		montoaDevolver += pv.getPrecioFinal();
		total -= pv.getPrecioFinal();

	}

	public boolean terminar() {
		// Devolvemos los productos
		boolean result;
		boolean mismaSucursal = true;
		if (productosADevolver.size() > 0) {
			if (venta.getIdBoutique() != AppInstance.boutique().getId()) { // REVISAMOS
																			// SI
																			// LA
																			// VENTA
																			// ES
																			// DE
																			// ESTA
																			// MISMA
																			// SUCURSAL
																			// O
																			// DE
																			// OTRA.
				mismaSucursal = false;
			}
			if (productosADevolver.size() == cantidadInicialProductos) {
				// Cancelamos la venta
				result = DaoVentas.devolverProductos(venta.getId(),
						this.productosADevolver, true, mismaSucursal);
			} else {
				// Cancelamos los productos que se piden
				result = DaoVentas.devolverProductos(venta.getId(),
						this.productosADevolver, false, mismaSucursal);
			}
			if (!result) {
				return result;
			}
			// Generamos el vale si es necesario
			if ((venta instanceof VentaApartado || venta instanceof VentaCredito)) {
				// El nuevo total de la nota es el total-montoDevuelto
				// Si ese total es menor a el total abonado
				if (total < abonado) {
					// Se hace un vale por el montoDevuelto-abonado
					vale = DaoVentas
							.generarVale(abonado - total, venta.getId()); // Finalizamos
																			// la
																			// venta
																			// de
																			// igual
																			// forma
					DaoVentas.cancelarVenta(venta);
				} else if (total == abonado) {
					DaoVentas.cancelarVenta(venta);
				}

			} else if (venta instanceof Venta) {
				// Generamos el vale de todo el monto devuelto
				vale = DaoVentas.generarVale(montoaDevolver, venta.getId());
			}
			return true;
		} else {
			return false;
		}
	}

	public List<Venta> buscarNotasPorCliente(int idCliente) {
		// ventasEncontradas = DaoVentas.findByIdCliente(idCliente);
		return ventasEncontradas;
	}

	public boolean buscarPorNoVenta(int idVenta, boolean primeraBusqueda) {
		total = 0;
		abonado = 0;
		montoaDevolver = 0;
		productosView.clear();

		// Buscamos la venta.. si se encuentra la mostramos..
		// PARCHE PARA LA IMPRESION DEL ID DE LA VENTA SI LA VENTA YA FUE
		// FINALIZADA...

		venta = DaoVentas.findByIdNoFinalizada(idVenta);
		if (venta == null) {
			return false;
		} else {
			for (ProductoVendido p : venta.getProductosVendidos()) {
				Object[] row = new Object[7];
				row[0] = "1";
				row[1] = p.getDescripcion();
				row[2] = p.getEtiqueta();
				row[3] = number.format(p.getPrecioInicial());
				row[4] = p.getDescuento() + "%";
				row[5] = number.format(p.getPrecioFinal());
				switch (p.getEntregado()) {
				case 0:
					row[6] = "No";
					break;
				case 1:
					row[6] = "Si";

					// montoUsado += pv.getPrecioFinal();
					break;
				}

				productosView.add(row);
				total += p.getPrecioFinal();
				if (primeraBusqueda) {
					totalInicial += p.getPrecioFinal();
				}
			}
			cantidadInicialProductos = venta.getProductosVendidos().size();

			for (ProductoDevuelto pp : venta.getProductosDevueltos()) {
				Object[] row = new Object[7];
				row[0] = "1";
				row[1] = AppInstance.idToTipoProducto.get(String.valueOf(pp
						.getIdTipoProducto()));
				row[2] = pp.getEtiqueta();
				row[3] = number.format(pp.getPrecioInicial());
				row[4] = pp.getDescuento() + "%";
				row[5] = number.format(pp.getPrecioFinal());
				row[6] = "DEVUELTO: "
						+ AppInstance.formatoLargo.format(pp
								.getFechaDevolucion());
				productosView.add(row);
			}
			pagosView.clear();
			for (Pago p : venta.getPagos()) {
				Object[] row = new Object[4];
				row[0] = p.getId();
				row[1] = AppInstance.formatoLargo.format(p.getFecha());
				row[2] = number.format(p.getMonto());
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
				abonado += p.getMonto();
				pagosView.add(row);
			}
		}
		return true;
	}

	public List<Object[]> pagosView() {
		return this.pagosView;
	}

	public double getAbonado() {
		return abonado;
	}

	public double getTotalaPagar() {
		return total - abonado;
	}

	public List<Object[]> productosView() {
		return productosView;
	}

	public double getTotal() {
		return total;
	}

	public Venta getVenta() {
		return venta;
	}

	/**
	 * setCliente
	 * 
	 * @param cliente
	 *            Cliente
	 */
	public void setCliente(com.boutique.domain.Cliente cliente) {
		this.cliente = cliente;
	}

	private void jbInit() throws Exception {
	}

	/**
	 * Verifica la fecha de la venta con la fecha actual y calcula la antiguedad
	 * de la nota.
	 * 
	 * Si la nota ha sido emitida hace mas de N dias (N = valor definido en base
	 * de datos), la venta no puede ser devuelta.
	 * 
	 * @param venta2
	 * @return
	 */
	public boolean ventaSePuedeDevolver(Venta venta) {
		// Checar si el usuario puede devolver ilimitadamente
		if (AppInstance.usuario().getDevolucionesIlimitadas() == 1) {
			return true;
		}

		Date currentDate = DaoSource.getFechaActual();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		long segundos = (currentDate.getTime() - venta.getFecha().getTime()) / 1000;
		long dias = ((segundos / 60) / 60) / 24;
		if (dias <= getDiasPermitidosParaDevolucion()) {
			return true;
		} else {
			return false;
		}

	}

	public int getDiasPermitidosParaDevolucion() {
		// If null it means that table does not exist in Database hence return
		// 15 days as default value.
		if (AppInstance.preferencias != null) {
			return AppInstance.preferencias.getDiasDevolucionProductos();
		} else {
			return 15;
		}
	}

}
