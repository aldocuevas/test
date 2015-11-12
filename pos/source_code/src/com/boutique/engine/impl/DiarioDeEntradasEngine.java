package com.boutique.engine.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.boutique.dao.DaoBoutique;
import com.boutique.dao.DaoResumenes;
import com.boutique.dao.DaoSource;
import com.boutique.dao.DaoVentas;
import com.boutique.domain.Boutique;
import com.boutique.domain.CorteDeCaja;
import com.boutique.domain.Pago;
import com.boutique.domain.PagoCheque;
import com.boutique.domain.PagoDevolucion;
import com.boutique.domain.PagoTarjetaCredito;
import com.boutique.domain.PagoTransferenciaBancaria;
import com.boutique.domain.PagoVale;
import com.boutique.domain.ProductoVendido;
import com.boutique.domain.Venta;
import com.boutique.domain.VentaCredito;

public class DiarioDeEntradasEngine {
	public java.util.List<Boutique> boutiques = new java.util.ArrayList<Boutique>();
	public List<Object[]> pagosView = new ArrayList<Object[]>();
	public List<Object[]> productosView = new ArrayList<Object[]>();
	public List<Object[]> intervaloPagos = new ArrayList<Object[]>();

	public DiarioDeEntradasEngine() {
		try {
			jbInit();
			boutiques = DaoBoutique.findAllActivas();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public double totalVentasDeContado = 0;
	public List<Object[]> boutiqueLocal = new ArrayList<Object[]>();
	public String nombreBoutiqueLocal;
	public double totalBoutiqueLocal;
	public double totalBoutique1;
	public double totalBoutique2;

	public String nombreBoutique1;
	public String nombreBoutique2;
	public List<Object[]> boutique1 = new ArrayList<Object[]>();
	public List<Object[]> boutique2 = new ArrayList<Object[]>();
	public CorteDeCaja corteDeCaja;
	List<Object[]> corte;
	List<?> listaContado;

	public List<?> getVentasDeContado() {
		listaContado = DaoResumenes.getDiarioVentasContado(AppInstance
				.boutique().getId(), AppInstance.terminal().getId());
		totalVentasDeContado = DaoResumenes.totalDiarioVentasDeContado;
		return listaContado;
	}

	/*
	 * METODO UTILIZADO PARA VISUALIZAR LA INFORMACION COMPLETA DE LA VENTA
	 * SELECCIONADA
	 */
	public Venta getVentaContado(int idx) {
		Venta v = DaoVentas.findById(idx);
		// Ahora ponemos los productos vendidos
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

	public List<?> getVentasDeContado(int idBoutique, java.util.Date fecha) {
		listaContado = DaoResumenes.getDiarioVentasContado(idBoutique, fecha);
		totalVentasDeContado = DaoResumenes.totalDiarioVentasDeContado;
		return listaContado;
	}

	public double granTotal() {
		return totalBoutiqueLocal + totalBoutique1 + totalBoutique2;
	}

	public List<Object[]> getCorteDeCaja(int idSucursal, int idTerminal) {
		corteDeCaja = DaoResumenes.getCorte(idSucursal, idTerminal);
		corte = new ArrayList<Object[]>();
		Object[] row = new Object[2];
		row[0] = "FONDO FIJO";
		row[1] = AppInstance.number.format(corteDeCaja.getFondoFijoEnCaja());
		corte.add(row);
		row = new Object[2];
		row[0] = "Efectivo";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalEfectivo());
		corte.add(row);
		row = new Object[2];
		row[0] = "Tarjeta";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalTarjeta());
		corte.add(row);
		row = new Object[2];
		row[0] = "Cheque";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalCheque());
		corte.add(row);
		row = new Object[2];
		row[0] = "Vale";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalVales());
		corte.add(row);
		row = new Object[2];
		row[0] = "Dep. o Transf";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalTransferencia());
		corte.add(row);
		row = new Object[2];
		row[0] = "Salidas(-)";
		row[1] = AppInstance.number.format(corteDeCaja.getSalidas());
		corte.add(row);
		row = new Object[2];
		row[0] = "TOTAL:";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalEnCaja());
		corte.add(row);
		row = new Object[2];
		row[0] = "RETIRO:";
		row[1] = AppInstance.number.format(corteDeCaja.getTotalEfectivo()
				- corteDeCaja.getSalidas());
		corte.add(row);

		row = new Object[2];
		row[0] = "Vales emitidos";
		row[1] = String.valueOf(corteDeCaja.getValesEmitidos());
		corte.add(row);
		row = new Object[2];
		row[0] = "Monto de los vales emitidos";
		row[1] = AppInstance.number.format(corteDeCaja.getMontoValesEmitidos());
		corte.add(row);
		row = new Object[2];
		row[0] = "Cuentas por cobrar:";
		row[1] = AppInstance.number.format(corteDeCaja.getCreditos());
		corte.add(row);
		return corte;
	}

	public List<?> getDiarioACreditoCompleto(java.util.Date fecha) {
		totalBoutique1 = 0;
		totalBoutique2 = 0;
		totalBoutiqueLocal = 0;
		boutiqueLocal.clear();
		boutique1.clear();
		boutique2.clear();
		Object[] row = null;
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		// DEBEMOS SACAR LOS IDS DE LAS VENTAS DE CREDITO PERTENECIENTES A ESTA
		// BOUTIQUE EN LAS CUALES SE REALIZARON PAGOS EL DIA DE HOY
		String sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>=DATE(NOW()) AND pagos.fecha<=NOW() and (ventas.tipo=1 OR ventas.tipo=2)  and ventas.idBoutique="
				+ AppInstance.boutique().getId()
				+ " and pagos.tipoPago<>5 and pagos.idSucursal="
				+ AppInstance.boutique().getId()
				+ " GROUP BY ventas.id ORDER BY pagos.fecha;";
		Statement st = null;
		try {
			this.nombreBoutiqueLocal = AppInstance.boutique().getNombre();
			CallableStatement cst = null;
			ResultSet rsNotas = null;

			st = con.createStatement();
			rs = st.executeQuery(sql);
			int idNota;
			int tipo;

			while (rs.next()) {
				idNota = rs.getInt("id");
				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCredito(?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartado(?,?)}");
				}

				cst.setInt(2, AppInstance.boutique().getId());
				// POR CADA NOTA
				cst.setInt(1, idNota);
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[7];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[2] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[3] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[3] = rsNotas.getString("_NOMBRE");
							}
							row[4] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[5] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[6] = rsNotas.getString("_MONTO");
							this.totalBoutiqueLocal += rsNotas
									.getDouble("_MONTO");
							boutiqueLocal.add(row);
						}
					}
					rsNotas.close();
				} while (cst.getMoreResults());

				// sacamos todos los pagos de la nota

			}
			rs.close();
			// AHORA SACAMOS LO CORRESPONDIENTE A LA BOUTIQUE NO. 1
			List<Boutique> lb = DaoBoutique.findBoutiquesRemotas(AppInstance
					.boutique().getId());

			Boutique boutique = lb.get(0);
			nombreBoutique1 = boutique.getNombre();
			sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>=DATE(NOW()) AND pagos.fecha<=NOW() and ventas.tipo=1 and ventas.idBoutique="
					+ boutique.getId()
					+ " and pagos.tipoPago<>5 and pagos.idSucursal="
					+ AppInstance.boutique().getId()
					+ " GROUP BY ventas.id  ORDER BY pagos.fecha;";

			rsNotas = null;

			rs = st.executeQuery(sql);
			System.out.println(rs.toString());

			while (rs.next()) {
				idNota = rs.getInt("id");
				// POR CADA NOTA
				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCredito(?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartado(?,?)}");
				}

				cst.setInt(1, idNota);
				cst.setInt(2, AppInstance.boutique().getId());
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[7];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[2] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[3] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[3] = rsNotas.getString("_NOMBRE");
							}
							row[4] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[5] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[6] = rsNotas.getString("_MONTO");
							this.totalBoutique1 += rsNotas.getDouble("_MONTO");
							boutique1.add(row);
						}
					}
				} while (cst.getMoreResults());
			}
			rs.close();
			boutique = lb.get(1);
			nombreBoutique2 = boutique.getNombre();
			// AHORA SACAMOS LO CORRESPONDIENTE A LA BOUTIQUE NO. 2
			sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>=DATE(NOW()) AND pagos.fecha<=NOW() and ventas.tipo=1 and ventas.idBoutique="
					+ boutique.getId()
					+ " and pagos.idSucursal="
					+ AppInstance.boutique().getId()
					+ " and pagos.tipoPago<>5  GROUP BY ventas.id ORDER BY pagos.fecha;";

			rsNotas = null;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				idNota = rs.getInt("id");
				// POR CADA NOTA

				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCredito(?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartado(?,?)}");
				}

				cst.setInt(1, idNota);
				cst.setInt(2, AppInstance.boutique().getId());
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[7];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[2] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[3] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[3] = rsNotas.getString("_NOMBRE");
							}
							row[4] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[5] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[6] = rsNotas.getString("_MONTO");
							this.totalBoutique2 += rsNotas.getDouble("_MONTO");
							boutique2.add(row);
						}
					}
				} while (cst.getMoreResults());
			}
			rs.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return null;
	}

	public List<?> getDiarioACreditoCompleto(int idBoutique,
			java.util.Date fecha) {
		totalBoutique1 = 0;
		totalBoutique2 = 0;
		totalBoutiqueLocal = 0;
		boutiqueLocal.clear();
		boutique1.clear();
		boutique2.clear();

		Object[] row = null;
		java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		java.sql.Timestamp fIni = new java.sql.Timestamp(fecha.getTime());
		java.sql.Timestamp fFin = new java.sql.Timestamp(cal.getTimeInMillis());
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		// DEBEMOS SACAR LOS IDS DE LAS VENTAS DE CREDITO PERTENECIENTES A ESTA
		// BOUTIQUE EN LAS CUALES SE REALIZARON PAGOS EL DIA DE HOY
		String sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>='"
				+ ff.format(fecha)
				+ "T00:00:00' AND pagos.fecha<='"
				+ ff.format(fecha)
				+ "T23:59:59' and (ventas.tipo=1 OR ventas.tipo=2) and ventas.idBoutique="
				+ idBoutique
				+ " and pagos.tipoPago<>5 and pagos.idSucursal="
				+ idBoutique + " GROUP BY ventas.id ORDER BY pagos.fecha;";
		Statement st = null;
		try {

			this.nombreBoutiqueLocal = AppInstance.idToNombreBoutique.get(
					idBoutique).toString();
			CallableStatement cst = null;
			ResultSet rsNotas = null;

			st = con.createStatement();
			rs = st.executeQuery(sql);
			int idNota;
			int tipo;

			while (rs.next()) {
				idNota = rs.getInt("id");
				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCreditoFecha(?,?,?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartadoFecha(?,?,?,?)}");
				}
				cst.setTimestamp(3, fIni);
				cst.setTimestamp(4, fFin);

				cst.setInt(2, idBoutique);
				// POR CADA NOTA
				cst.setInt(1, idNota);
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[8];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = ((tipo == 1) ? "CREDITO" : "APARTADO");
							row[2] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[3] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[4] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[4] = rsNotas.getString("_NOMBRE");
							}
							row[5] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[6] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[7] = rsNotas.getString("_MONTO");
							this.totalBoutiqueLocal += rsNotas
									.getDouble("_MONTO");
							boutiqueLocal.add(row);
						}
					}

				} while (cst.getMoreResults());

				// sacamos todos los pagos de la nota

			}
			rs.close();
			// AHORA SACAMOS LO CORRESPONDIENTE A LA BOUTIQUE NO. 1
			List<Boutique> lb = DaoBoutique.findBoutiquesRemotas(idBoutique);

			Boutique boutique = lb.get(0);
			nombreBoutique1 = boutique.getNombre();
			sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>='"
					+ ff.format(fecha)
					+ "T00:00:00' AND pagos.fecha<='"
					+ ff.format(fecha)
					+ "T23:59:59' and ventas.tipo=1 and ventas.idBoutique="
					+ boutique.getId()
					+ " and pagos.tipoPago<>5 and pagos.idSucursal="
					+ idBoutique + " GROUP BY ventas.id  ORDER BY pagos.fecha;";

			rsNotas = null;

			rs = st.executeQuery(sql);
			System.out.println(rs.toString());

			while (rs.next()) {
				idNota = rs.getInt("id");
				// POR CADA NOTA
				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCreditoFecha(?,?,?,?)}");

				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartadoFecha(?,?,?,?)}");
				}

				cst.setTimestamp(3, fIni);
				cst.setTimestamp(4, fFin);
				cst.setInt(1, idNota);
				cst.setInt(2, idBoutique);
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[8];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = ((tipo == 1) ? "CREDITO" : "APARTADO");
							row[2] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[3] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[4] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[4] = rsNotas.getString("_NOMBRE");
							}
							row[5] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[6] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[7] = rsNotas.getString("_MONTO");
							this.totalBoutique1 += rsNotas.getDouble("_MONTO");
							boutique1.add(row);
						}
					}
				} while (cst.getMoreResults());
			}
			rs.close();
			boutique = lb.get(1);
			nombreBoutique2 = boutique.getNombre();
			// AHORA SACAMOS LO CORRESPONDIENTE A LA BOUTIQUE NO. 2
			sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>='"
					+ ff.format(fecha)
					+ "T00:00:00' AND pagos.fecha<='"
					+ ff.format(fecha)
					+ "T23:59:59' and ventas.tipo=1 and ventas.idBoutique="
					+ boutique.getId()
					+ " and pagos.idSucursal="
					+ idBoutique
					+ " and pagos.tipoPago<>5  GROUP BY ventas.id ORDER BY pagos.fecha;";

			rsNotas = null;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				idNota = rs.getInt("id");
				// POR CADA NOTA

				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCreditoFecha(?,?,?,?)}");

				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartadoFecha(?,?,?,?)}");
				}
				cst.setTimestamp(3, fIni);
				cst.setTimestamp(4, fFin);

				cst.setInt(1, idNota);
				cst.setInt(2, idBoutique);
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[7];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[2] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[3] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[3] = rsNotas.getString("_NOMBRE");
							}
							row[4] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[5] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[6] = rsNotas.getString("_MONTO");
							this.totalBoutique2 += rsNotas.getDouble("_MONTO");
							boutique2.add(row);
						}
					}
				} while (cst.getMoreResults());
			}
			rs.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return null;
	}

	public List<?> getDiarioACreditoCompleto() {
		totalBoutique1 = 0;
		totalBoutique2 = 0;
		totalBoutiqueLocal = 0;
		boutiqueLocal.clear();
		boutique1.clear();
		boutique2.clear();
		Object[] row = null;
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		// DEBEMOS SACAR LOS IDS DE LAS VENTAS DE CREDITO PERTENECIENTES A ESTA
		// BOUTIQUE EN LAS CUALES SE REALIZARON PAGOS EL DIA DE HOY
		String sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>=DATE(NOW()) AND pagos.fecha<=NOW() and (ventas.tipo=1 OR ventas.tipo=2) and ventas.idBoutique="
				+ AppInstance.boutique().getId()
				+ " and ventas.idTerminal="
				+ AppInstance.terminal().getId()
				+ " and pagos.tipoPago<>5 and pagos.idSucursal="
				+ AppInstance.boutique().getId()
				+ " GROUP BY ventas.id ORDER BY pagos.fecha;";
		Statement st = null;
		try {
			this.nombreBoutiqueLocal = AppInstance.boutique().getNombre();
			CallableStatement cst = null;
			ResultSet rsNotas = null;

			st = con.createStatement();
			rs = st.executeQuery(sql);
			int idNota;
			int tipo;

			while (rs.next()) {
				idNota = rs.getInt("id");
				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCredito(?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartado(?,?)}");
				}

				cst.setInt(2, AppInstance.boutique().getId());
				// POR CADA NOTA
				cst.setInt(1, idNota);
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[8];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = ((tipo == 1) ? "CREDITO" : "APARTADO");
							row[2] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[3] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[4] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[4] = rsNotas.getString("_NOMBRE");
							}
							row[5] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[6] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[7] = rsNotas.getString("_MONTO");
							this.totalBoutiqueLocal += rsNotas
									.getDouble("_MONTO");
							boutiqueLocal.add(row);
						}
					}

				} while (cst.getMoreResults());

				// sacamos todos los pagos de la nota

			}
			rs.close();
			// AHORA SACAMOS LO CORRESPONDIENTE A LA BOUTIQUE NO. 1
			List<Boutique> lb = DaoBoutique.findBoutiquesRemotas(AppInstance
					.boutique().getId());

			Boutique boutique = lb.get(0);
			nombreBoutique1 = boutique.getNombre();
			sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>=DATE(NOW()) AND pagos.fecha<=NOW() and ventas.tipo=1 and ventas.idBoutique="
					+ boutique.getId()
					+ " and pagos.tipoPago<>5 and pagos.idSucursal="
					+ AppInstance.boutique().getId()
					+ " GROUP BY ventas.id  ORDER BY pagos.fecha;";

			rsNotas = null;

			rs = st.executeQuery(sql);
			System.out.println(rs.toString());

			while (rs.next()) {
				idNota = rs.getInt("id");
				// POR CADA NOTA
				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCredito(?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartado(?,?)}");
				}

				cst.setInt(1, idNota);
				cst.setInt(2, AppInstance.boutique().getId());
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[8];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = "CREDITO";

							row[2] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));
							row[3] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[4] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[4] = rsNotas.getString("_NOMBRE");
							}
							row[5] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[6] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[7] = rsNotas.getString("_MONTO");
							this.totalBoutique1 += rsNotas.getDouble("_MONTO");
							boutique1.add(row);
						}
					}
				} while (cst.getMoreResults());
			}
			rs.close();
			boutique = lb.get(1);
			nombreBoutique2 = boutique.getNombre();
			// AHORA SACAMOS LO CORRESPONDIENTE A LA BOUTIQUE NO. 2
			sql = "select distinct(ventas.id),ventas.tipo,pagos.fecha from ventas INNER JOIN pagos ON pagos.idVenta=ventas.id where pagos.fecha>=DATE(NOW()) AND pagos.fecha<=NOW() and ventas.tipo=1 and ventas.idBoutique="
					+ boutique.getId()
					+ " and pagos.idSucursal="
					+ AppInstance.boutique().getId()
					+ " and pagos.tipoPago<>5  GROUP BY ventas.id ORDER BY pagos.fecha;";

			rsNotas = null;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				idNota = rs.getInt("id");
				// POR CADA NOTA

				tipo = rs.getInt("tipo");
				if (tipo == 1) {
					cst = con
							.prepareCall("{call obtenerPagosVentaCredito(?,?)}");
				} else {
					cst = con
							.prepareCall("{call obtenerPagosVentaApartado(?,?)}");
				}

				cst.setInt(1, idNota);
				cst.setInt(2, AppInstance.boutique().getId());
				cst.executeQuery();
				do {
					rsNotas = cst.getResultSet();
					if (rsNotas != null) {
						while (rsNotas.next()) {
							// SACO LOS DATOS DE CADA PAGO
							row = new Object[8];
							row[0] = rsNotas.getString("ID_VENTA");
							// row[1] =
							// AppInstance.formatoLargo.format(rs.getTimestamp("fecha"));
							row[1] = "CREDITO";
							row[2] = AppInstance.formatoLargo.format(rsNotas
									.getTimestamp("_FECHAPAGO"));

							row[3] = rsNotas.getString("_IDPAGO");
							if (tipo == 1) {
								row[4] = rsNotas.getString("_NOMBRE") + " "
										+ rsNotas.getString("_APELLIDOPATERNO")
										+ " "
										+ rsNotas.getString("_APELLIDOMATERNO");
							} else {
								row[4] = rsNotas.getString("_NOMBRE");
							}
							row[5] = rsNotas.getString("_MEDIOS_DE_PAGO");
							row[6] = rsNotas
									.getString("_DESCRIPCION_TIPO_PAGO");
							row[7] = rsNotas.getString("_MONTO");
							this.totalBoutique2 += rsNotas.getDouble("_MONTO");
							boutique2.add(row);
						}
					}
				} while (cst.getMoreResults());
			}
			rs.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return null;
	}

	public List<?> getDiarioaCreditoByIdBoutique(int idBoutique) {
		return null;
	}

	/**
	 * registrarCorte
	 * 
	 * @param idBoutique
	 *            int
	 */
	public boolean registrarCorte(int idBoutique, int idTerminal, int idUsuario) {
		// TENEMOS EL CORTE, HAY QUE REGISTRAR LAS SALIDAS Y LAS OBSERVACIONES
		// mandamos llama el procedimiento almacenado en la bd
		return DaoResumenes.registrarCorte(corteDeCaja, idBoutique, idTerminal,
				idUsuario);

	}

	private void jbInit() throws Exception {
	}

	/**
	 * imprimirCorteDeCaja
	 */
	public void imprimirCorteDeCaja(double salidas, List<Object[]> listaSalidas) {
		com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.PrintTask(
				8, corte, corteDeCaja.getTotalEfectivo()
						- corteDeCaja.getSalidas(), listaSalidas);
		Thread t = new Thread(obj);
		t.start();

	}
}
