package com.boutique.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;
import com.boutique.helper.IvaHelper;

public class DaoVentas {
	private static SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");

	public DaoVentas() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static double findAnticipo(int idVenta) {
		double monto = 0.0;
		PreparedStatement ps = null;
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		String sql = "SELECT SUM(monto) as suma FROM pagos WHERE idVenta=? AND esAnticipo=1;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idVenta);

			rs = ps.executeQuery();
			if (rs.next()) {
				monto = rs.getDouble("suma");
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex1) {
			}
		}
		return monto;

	}

	public static DescPagoAnticipado findPromocionDescuentoPagoAnticipado() {
		DescPagoAnticipado desc = null;
		String sql = "select * from `promocion_credito_pag_anticipado` WHERE activa=1 AND fechaInicial>=? AND fechaFinal<=?";
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DaoSource.getConnectionLocal();
			ResultSet rs = null;
			pst = con.prepareStatement(sql);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date());
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			cal.set(Calendar.DAY_OF_MONTH, 1);
			java.util.Date fechaInicial = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			java.util.Date fechaFinal = cal.getTime();
			pst.setDate(1, new java.sql.Date(fechaInicial.getTime()));
			pst.setDate(2, new java.sql.Date(fechaFinal.getTime()));
			rs = pst.executeQuery();
			if (rs.next()) {
				desc = new DescPagoAnticipado();
				desc.setActivado(true);
				desc.setPorcentajeDescuento(rs.getDouble("porcentaje"));
				desc.setFechaInicial(rs.getDate("fechaInicial"));
				desc.setFechaFinal(rs.getDate("fechaFinal"));
				desc.setTipoVentaCredito(rs.getInt("tipoVentaCredito"));
			}
			rs.close();
		} catch (Exception ex) {
		}

		return desc;
	}

	public static double findSubtotal(int idVenta) {
		double monto = 0.0;
		PreparedStatement ps = null;
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		String sql = "SELECT SUM(precioFinal) as suma FROM productosvendidos WHERE idVenta=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idVenta);
			rs = ps.executeQuery();
			if (rs.next()) {
				monto = rs.getDouble("suma");
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex1) {
			}
		}
		return monto;

	}

	public static double findAbonado(int idVenta) {
		double monto = 0.0;
		PreparedStatement ps = null;
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		String sql = "SELECT SUM(monto) as suma FROM pagos WHERE idVenta=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idVenta);
			rs = ps.executeQuery();
			if (rs.next()) {
				monto = rs.getDouble("suma");
			}
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex1) {
			}
		}
		return monto;

	}

	private static boolean entregarArticulos(int idVenta, Connection con)
			throws SQLException {
		String sql = "UPDATE productosvendidos SET entregado=1 where idVenta="
				+ idVenta;
		if (con == null) {
			con = DaoSource.getConnectionLocal();
		}
		con.createStatement().execute(sql);
		return true;
	}

	public static Set<Integer> finalizarVentas(Set<VentaCredito> ventas)
			throws SQLException {
		Connection con = DaoSource.getConnectionLocal();
		Set<Integer> ventasFinalizadas = new HashSet<Integer>();
		String sql = "UPDATE ventas set status=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		for (VentaCredito venta : ventas) {
			st.setInt(1, 1);
			st.setInt(2, venta.getId());
			st.executeUpdate();
			ventasFinalizadas.add(venta.getId());
		}
		con.commit();
		con.setAutoCommit(true);
		return ventasFinalizadas;

	}

	public static boolean finalizarVenta(Venta venta) {

		Connection con = DaoSource.getConnectionLocal();
		try {
			con.setAutoCommit(false);
			String sql = "UPDATE ventas set status=? , fechaFinalizacion=NOW() where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 1);
			st.setInt(2, venta.getId());
			st.executeUpdate();
			entregarArticulos(venta.getId(), con);
			con.commit();
			con.setAutoCommit(true);
			return true;

		} catch (SQLException ex) {
			System.out.println(ex);
			try {
				con.rollback();
			} catch (SQLException ex2) {
			}
			return false;
		}
	}

	public static boolean cancelarVenta(Venta venta) {

		Connection con = DaoSource.getConnectionLocal();
		try {
			con.setAutoCommit(false);
			String sql = "UPDATE ventas set status=?, fechaFinalizacion=NOW() where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 2);
			st.setInt(2, venta.getId());
			st.executeUpdate();
			entregarArticulos(venta.getId(), con);
			con.commit();
			con.setAutoCommit(true);
			return true;

		} catch (SQLException ex) {
			System.out.println(ex);
			try {
				con.rollback();
			} catch (SQLException ex2) {
			}
			return false;
		}
	}

	public static boolean registrarVenta(Venta venta,
			java.sql.Timestamp fechaNota) {
		int idVenta;
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs;
		try {
			con.setAutoCommit(false);
			// insertamos la venta
			String sql = "INSERT INTO ventas (fecha,idCliente,tipo,noPagos,noDias,status,fechaFinalizacion,enCorte,idVendedor,idBoutique) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insertVenta = con.prepareStatement(sql);
			insertVenta.setInt(10, venta.getIdBoutique());

			if (venta instanceof VentaCredito) {
				// insertamos la venta
				VentaCredito vc = (VentaCredito) venta;
				insertVenta.setTimestamp(1, fechaNota);
				insertVenta.setInt(2, vc.getIdCliente());
				insertVenta.setInt(3, 1);
				insertVenta.setInt(4, vc.getNoPagos());
				insertVenta.setInt(5, vc.getNoDias());
				insertVenta.setInt(6, 0);
				insertVenta.setTimestamp(7, vc.getFechaFinalizacion());
				insertVenta.setInt(8, 1);
				insertVenta.setInt(9, venta.getIdVendedor());

				insertVenta.execute();
				// insertamos los productos vendidos
				// descontamos los productos de los inventarios
				// insertamos los pagos
			} else if (venta instanceof VentaApartado) {
				VentaApartado va = (VentaApartado) venta;
				// insertamos la venta
				// insertVenta.setTimestamp(1, venta.getFecha());
				insertVenta.setInt(2, 0);
				insertVenta.setInt(3, 2);
				insertVenta.setInt(4, 0);
				insertVenta.setInt(5, 0);
				insertVenta.setInt(6, 0);
				insertVenta.setTimestamp(7, va.getFechaFinalizacion());
				insertVenta.setInt(8, 0);
				insertVenta.setInt(9, venta.getIdVendedor());
				insertVenta.execute();
				// insertamos
			} else if (venta instanceof Venta) {
				// insertVenta.setTimestamp(1, venta.getFecha());
				insertVenta.setInt(2, 0);
				insertVenta.setInt(3, 0);
				insertVenta.setInt(4, 0);
				insertVenta.setInt(5, 0);
				insertVenta.setInt(6, 1);
				insertVenta.setTimestamp(7, venta.getFecha());
				insertVenta.setInt(8, 0);
				insertVenta.setInt(9, venta.getIdVendedor());
				insertVenta.execute();
			}

			sql = "SELECT MAX(id) as idVenta FROM ventas ORDER BY id DESC";
			rs = con.createStatement().executeQuery(sql);
			rs.next();
			idVenta = rs.getInt("idVenta");
			rs.close();
			venta.setId(idVenta);
			if (venta instanceof VentaApartado) {
				VentaApartado va = (VentaApartado) venta;
				// insertamos los datos del cliente de la venta
				Statement st = con.createStatement();
				st.execute("INSERT INTO venta_apartado_datos  (idVenta,cliente,domicilio,telefono) VALUES ("
						+ idVenta
						+ ",'"
						+ va.getCliente()
						+ "','"
						+ va.getDomicilio() + "','" + va.getTelefono() + "')");

			}

			// insertamos los productos vendidos
			if (registrarProductosVendidos(idVenta,
					venta.getProductosVendidos(), con)) {
				// insertamos los pagos
				registrarPagos(idVenta, venta.getPagos(), con, fechaNota);
				insertVenta.close();
				con.commit();
				con.setAutoCommit(true);
				// con.close();
				return true;
			} else {
				con.rollback();
				// con.close();

			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
			try {
				con.rollback();
				// con.close();
			} catch (SQLException ex1) {
			}
		}
		return false;

	}

	public static boolean registrarVenta(Venta venta) {
		int idVenta;
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs;
		try {
			con.setAutoCommit(false);
			// insertamos la venta
			String sql = "INSERT INTO ventas (fecha,idCliente,tipo,noPagos,noDias,status,fechaFinalizacion,enCorte,idVendedor,idBoutique,idTerminal,requiereFacturaIndividual) VALUES (NOW(),?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insertVenta = con.prepareStatement(sql);
			// Indicate Id Boutique and Id Terminal
			insertVenta.setInt(9, venta.getIdBoutique());
			insertVenta.setInt(10, venta.getIdTerminal());
			insertVenta.setBoolean(11, venta.isRequiereFacturaIndividual());
			if (venta instanceof VentaCredito) {
				// insertamos la venta
				VentaCredito vc = (VentaCredito) venta;
				// insertVenta.setTimestamp(1, venta.getFecha());
				insertVenta.setInt(1, vc.getIdCliente());
				insertVenta.setInt(2, 1);
				insertVenta.setInt(3, vc.getNoPagos());
				insertVenta.setInt(4, vc.getNoDias());
				insertVenta.setInt(5, 0);
				insertVenta.setTimestamp(6, vc.getFechaFinalizacion());
				insertVenta.setInt(7, 0);
				insertVenta.setInt(8, venta.getIdVendedor());

				insertVenta.execute();
				// insertamos los productos vendidos
				// descontamos los productos de los inventarios
				// insertamos los pagos
			} else if (venta instanceof VentaApartado) {
				VentaApartado va = (VentaApartado) venta;
				// insertamos la venta
				// insertVenta.setTimestamp(1, venta.getFecha());

				insertVenta.setInt(1, va.getIdCliente());
				insertVenta.setInt(2, 2);
				insertVenta.setInt(3, 0);
				insertVenta.setInt(4, 0);
				insertVenta.setInt(5, 0);
				insertVenta.setTimestamp(6, va.getFechaFinalizacion());
				insertVenta.setInt(7, 0);
				insertVenta.setInt(8, venta.getIdVendedor());
				insertVenta.execute();
				// insertamos
			} else if (venta instanceof Venta) {
				// insertVenta.setTimestamp(1, venta.getFecha());

				insertVenta.setInt(1, venta.getIdCliente());
				insertVenta.setInt(2, 0);
				insertVenta.setInt(3, 0);
				insertVenta.setInt(4, 0);
				insertVenta.setInt(5, 1);
				insertVenta.setTimestamp(6, venta.getFecha());
				insertVenta.setInt(7, 0);
				insertVenta.setInt(8, venta.getIdVendedor());
				insertVenta.execute();
			}

			sql = "SELECT MAX(id) as idVenta FROM ventas ORDER BY id DESC";
			rs = con.createStatement().executeQuery(sql);
			rs.next();
			idVenta = rs.getInt("idVenta");
			rs.close();
			venta.setId(idVenta);
			if (venta instanceof VentaApartado) {
				VentaApartado va = (VentaApartado) venta;
				// insertamos los datos del cliente de la venta
				Statement st = con.createStatement();
				st.execute("INSERT INTO venta_apartado_datos  (idVenta,cliente,domicilio,telefono) VALUES ("
						+ idVenta
						+ ",'"
						+ va.getCliente()
						+ "','"
						+ va.getDomicilio() + "','" + va.getTelefono() + "')");

			}

			// insertamos los productos vendidos
			if (registrarProductosVendidos(idVenta,
					venta.getProductosVendidos(), con)) {
				// insertamos los pagos
				registrarPagos(idVenta, venta.getPagos(), con);
				insertVenta.close();
				con.commit();
				con.setAutoCommit(true);
				// con.close();
				return true;
			} else {
				con.rollback();
				// con.close();

			}

		} catch (SQLException ex) {
			System.out.println(ex.toString());
			try {
				con.rollback();
				// con.close();
			} catch (SQLException ex1) {
			}
		}
		return false;
	}

	/**
	 * registrarProductosVendidos
	 * 
	 * 
	 * @param idVenta
	 *            int
	 * @param list
	 *            List
	 * @param con
	 *            Connection
	 */
	private static boolean registrarProductosVendidos(int idVenta,
			List<ProductoVendido> list, Connection con) throws SQLException {
		PreparedStatement insertProducto;
		PreparedStatement descontarProducto;
		insertProducto = con
				.prepareStatement("INSERT INTO productosvendidos (idVenta,etiqueta,modelo,precioInicial,descuento,impuestoPrecioFinal,precioFinal,entregado,idInventario) VALUES (?,?,?,?,?,?,?,?,?);");
		descontarProducto = con
				.prepareStatement("UPDATE inventarios SET cantidad=cantidad-1 WHERE cantidad>0 AND id=?");

		for (ProductoVendido p : list) {
			// descontamos los productos de los inventarios
			descontarProducto.setInt(1, p.getId());

			if (descontarProducto.executeUpdate() > 0) {
				insertProducto.setInt(1, idVenta);
				insertProducto.setString(2, p.getEtiqueta());
				insertProducto.setInt(3, p.getModelo());
				insertProducto.setDouble(4, p.getPrecioInicial());
				insertProducto.setInt(5, p.getDescuento());
				insertProducto.setDouble(6, p.getImpuestoPrecioFinal());
				insertProducto.setDouble(7, p.getPrecioFinal());
				insertProducto.setInt(8, p.getEntregado());
				insertProducto.setInt(9, p.getIdInventario());
				insertProducto.execute();
			} else { // No hay producto disponible..
				return false;
			}
		}
		insertProducto.close();
		descontarProducto.close();
		return true;
	}

	public static java.util.List<Pago> registrarPagos(int idVenta,
			List<Pago> pagos, Connection con, java.sql.Timestamp fechaPagos)
			throws SQLException {
		boolean cerrar = false;
		if (con == null) {
			con = DaoSource.getConnectionLocal();
			cerrar = true;
		}
		PreparedStatement insertPago = null;
		PreparedStatement insertPagoDetalle = null;
		ResultSet rs = null;
		String sql;

		insertPago = con
				.prepareStatement("INSERT INTO pagos (fecha,idVenta,impuestoMonto,monto,tipoPago,enCorte,idVendedor,idSucursal,esAnticipo,idTerminal) VALUES (?,?,?,?,?,?,?,?,?,?);");
		for (Pago p : pagos) {
			// Insertamos en pagos
			insertPago.setTimestamp(1, fechaPagos);
			insertPago.setInt(2, idVenta);
			insertPago.setDouble(3, p.getImpuestoMonto());
			insertPago.setDouble(4, p.getMonto());
			insertPago.setInt(5, p.getTipo());
			insertPago.setInt(6, p.getEnCorte());
			insertPago.setInt(7, p.getIdVendedor());
			insertPago.setInt(8, p.getIdSucursal());
			insertPago.setInt(9, p.getEsAnticipo());
			insertPago.setInt(10, p.getIdTerminal());
			insertPago.execute();
			sql = "SELECT id,fecha FROM pagos ORDER BY id DESC LIMIT 0,1";
			rs = con.createStatement().executeQuery(sql);
			rs.next();
			int idPago = rs.getInt("id");
			p.setFecha(rs.getTimestamp("fecha"));
			p.setId(idPago);
			rs.close();
			if (p instanceof PagoCheque) {
				PagoCheque pch = (PagoCheque) p;

				// Insertamos en pagos y en pagos_cheque
				sql = "INSERT INTO pagos_cheque (idPago,banco,noCheque) VALUES (?,?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setString(2, pch.getBanco());
				insertPagoDetalle.setString(3, pch.getNoCheque());
				insertPagoDetalle.execute();
			} else if (p instanceof PagoVale) {
				PagoVale pv = (PagoVale) p;
				// Insertamos en pagos_vale
				sql = "INSERT INTO pagos_vale (idPago,idVale) VALUES (?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setInt(2, pv.getIdVale());
				insertPagoDetalle.execute();
				// PONEMOS EL VALE COMO UTILIZADO
				sql = "UPDATE vales SET utilizado=1 WHERE id=" + pv.getIdVale();
				con.createStatement().execute(sql);
			} else if (p instanceof PagoTarjetaCredito) {
				PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
				// Insertamos en pagos_tarjeta
				sql = "INSERT INTO pagos_tarjeta (banco,meses,idPago) VALUES (?,?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setString(1, pt.getBanco());
				insertPagoDetalle.setInt(2, pt.getMeses());
				insertPagoDetalle.setInt(3, idPago);
				insertPagoDetalle.execute();
			} else if (p instanceof PagoTransferenciaBancaria) {
				PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
				sql = "INSERT INTO pagos_transf_banco (idPago,referencia) VALUES (?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setString(2, pb.getNoReferencia());
				insertPagoDetalle.execute();

			}
			if (insertPagoDetalle != null) {
				insertPagoDetalle.close();
			}

			// Terminamos la transaccion

		}

		if (cerrar) {
			con.close();
		}
		return pagos;

	}

	public static java.util.List<Pago> registrarPagos(int idVenta,
			List<Pago> pagos, Connection con) throws SQLException {
		boolean cerrar = false;
		if (con == null) {
			con = DaoSource.getConnectionLocal();
			cerrar = true;
		}
		PreparedStatement insertPago = null;
		PreparedStatement insertPagoDetalle = null;
		ResultSet rs = null;
		String sql;

		insertPago = con
				.prepareStatement("INSERT INTO pagos (fecha,idVenta,impuestoMonto,monto,tipoPago,enCorte,idVendedor,idSucursal,esAnticipo,idTerminal) VALUES (NOW(),?,?,?,?,?,?,?,?,?);");
		for (Pago p : pagos) {
			// Insertamos en pagos
			// insertPago.setTimestamp(1, p.getFecha());
			insertPago.setInt(1, idVenta);
			insertPago.setDouble(2, p.getImpuestoMonto());
			insertPago.setDouble(3, p.getMonto());
			insertPago.setInt(4, p.getTipo());
			insertPago.setInt(5, p.getEnCorte());
			insertPago.setInt(6, p.getIdVendedor());
			insertPago.setInt(7, p.getIdSucursal());
			insertPago.setInt(8, p.getEsAnticipo());
			insertPago.setInt(9, p.getIdTerminal());
			insertPago.execute();
			sql = "SELECT id,fecha FROM pagos ORDER BY id DESC LIMIT 0,1";
			rs = con.createStatement().executeQuery(sql);
			rs.next();
			int idPago = rs.getInt("id");
			p.setFecha(rs.getTimestamp("fecha"));
			p.setId(idPago);
			rs.close();
			if (p instanceof PagoCheque) {
				PagoCheque pch = (PagoCheque) p;

				// Insertamos en pagos y en pagos_cheque
				sql = "INSERT INTO pagos_cheque (idPago,banco,noCheque) VALUES (?,?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setString(2, pch.getBanco());
				insertPagoDetalle.setString(3, pch.getNoCheque());
				insertPagoDetalle.execute();
			} else if (p instanceof PagoVale) {
				PagoVale pv = (PagoVale) p;
				// Insertamos en pagos_vale
				sql = "INSERT INTO pagos_vale (idPago,idVale) VALUES (?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setInt(2, pv.getIdVale());
				insertPagoDetalle.execute();
				// PONEMOS EL VALE COMO UTILIZADO
				sql = "UPDATE vales SET utilizado=1 WHERE id=" + pv.getIdVale();
				con.createStatement().execute(sql);
			} else if (p instanceof PagoTarjetaCredito) {
				PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
				// Insertamos en pagos_tarjeta
				sql = "INSERT INTO pagos_tarjeta (banco,meses,idPago) VALUES (?,?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setString(1, pt.getBanco());
				insertPagoDetalle.setInt(2, pt.getMeses());
				insertPagoDetalle.setInt(3, idPago);
				insertPagoDetalle.execute();
			} else if (p instanceof PagoTransferenciaBancaria) {
				PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
				sql = "INSERT INTO pagos_transf_banco (idPago,referencia) VALUES (?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setString(2, pb.getNoReferencia());
				insertPagoDetalle.execute();

			}
			if (insertPagoDetalle != null) {
				insertPagoDetalle.close();
			}

			// Terminamos la transaccion

		}

		if (cerrar) {
			con.close();
		}
		return pagos;
	}

	public static java.util.List<Pago> registrarPagos(List<Pago> pagos)
			throws SQLException {
		Connection con = DaoSource.getConnectionLocal();
		con.setAutoCommit(false);

		PreparedStatement insertPago = null;
		PreparedStatement insertPagoDetalle = null;
		ResultSet rs = null;
		String sql;

		insertPago = con
				.prepareStatement("INSERT INTO pagos (fecha,idVenta,impuestoMonto,monto,tipoPago,enCorte,idVendedor,idSucursal,esAnticipo,idTerminal) VALUES (NOW(),?,?,?,?,?,?,?,?,?);");
		for (Pago p : pagos) {
			// Insertamos en pagos
			// insertPago.setTimestamp(1, p.getFecha());
			insertPago.setInt(1, p.getIdVenta());
			insertPago.setDouble(2, p.getImpuestoMonto());
			insertPago.setDouble(3, p.getMonto());
			insertPago.setInt(4, p.getTipo());
			insertPago.setInt(5, p.getEnCorte());
			insertPago.setInt(6, p.getIdVendedor());
			insertPago.setInt(7, p.getIdSucursal());
			insertPago.setInt(8, p.getEsAnticipo());
			insertPago.setInt(9, p.getIdTerminal());
			insertPago.execute();
			sql = "SELECT id,fecha FROM pagos ORDER BY id DESC LIMIT 0,1";
			rs = con.createStatement().executeQuery(sql);
			rs.next();
			int idPago = rs.getInt("id");
			p.setFecha(rs.getTimestamp("fecha"));
			p.setId(idPago);
			rs.close();
			if (p instanceof PagoCheque) {
				PagoCheque pch = (PagoCheque) p;

				// Insertamos en pagos y en pagos_cheque
				sql = "INSERT INTO pagos_cheque (idPago,banco,noCheque) VALUES (?,?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setString(2, pch.getBanco());
				insertPagoDetalle.setString(3, pch.getNoCheque());
				insertPagoDetalle.execute();
			} else if (p instanceof PagoVale) {
				PagoVale pv = (PagoVale) p;
				// Insertamos en pagos_vale
				sql = "INSERT INTO pagos_vale (idPago,idVale) VALUES (?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setInt(2, pv.getIdVale());
				insertPagoDetalle.execute();
				// PONEMOS EL VALE COMO UTILIZADO
				sql = "UPDATE vales SET utilizado=1 WHERE id=" + pv.getIdVale();
				con.createStatement().execute(sql);

			} else if (p instanceof PagoTarjetaCredito) {
				PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
				// Insertamos en pagos_tarjeta
				sql = "INSERT INTO pagos_tarjeta (banco,meses,idPago) VALUES (?,?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setString(1, pt.getBanco());
				insertPagoDetalle.setInt(2, pt.getMeses());
				insertPagoDetalle.setInt(3, idPago);
				insertPagoDetalle.execute();
			} else if (p instanceof PagoTransferenciaBancaria) {
				PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
				sql = "INSERT INTO pagos_transf_banco (idPago,referencia) VALUES (?,?);";
				insertPagoDetalle = con.prepareStatement(sql);
				insertPagoDetalle.setInt(1, idPago);
				insertPagoDetalle.setString(2, pb.getNoReferencia());
				insertPagoDetalle.execute();

			}
			if (insertPagoDetalle != null) {
				insertPagoDetalle.close();
			}
			// Terminamos la transaccion
		}
		return pagos;
	}

	public static Venta findByIdNoFinalizada(int id) {
		Venta venta = null;
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		Statement st = null;
		String sql = "SELECT ventas.* FROM ventas WHERE id=" + id; // +
																	// " AND status <2 ";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// Checamos el tipo de venta que es
				int tipo = rs.getInt("tipo");
				int idBoutique = rs.getInt("idBoutique");

				switch (tipo) {
				case 0:

					// Venta contado
					venta = new Venta();
					venta.setId(rs.getInt("id"));
					venta.setEnCorte(rs.getInt("enCorte"));
					venta.setFecha(rs.getTimestamp("fecha"));
					venta.setIdVendedor(rs.getInt("idVendedor"));
					venta.setStatus(rs.getInt("status"));
					venta.setIdCliente(rs.getInt("idCliente"));

					// Ponemos los pagos
					// Ponemos los productos vendidos

					break;
				case 1:

					// Venta a credito
					VentaCredito vc = new VentaCredito();
					vc.setId(rs.getInt("id"));
					vc.setEnCorte(rs.getInt("enCorte"));
					vc.setFecha(rs.getTimestamp("fecha"));
					vc.setIdVendedor(rs.getInt("idVendedor"));
					vc.setStatus(rs.getInt("status"));
					vc.setFechaFinalizacion(rs
							.getTimestamp("fechaFinalizacion"));
					vc.setIdCliente(rs.getInt("idCliente"));
					vc.setNoDias(rs.getInt("noDias"));
					vc.setNoPagos(rs.getInt("noPagos"));

					// Ponemos los datos
					venta = vc;
					break;
				case 2:

					// Venta de apartado
					VentaApartado va = new VentaApartado();
					va.setId(rs.getInt("id"));
					va.setEnCorte(rs.getInt("enCorte"));
					va.setFecha(rs.getTimestamp("fecha"));
					va.setIdVendedor(rs.getInt("idVendedor"));
					va.setStatus(rs.getInt("status"));
					va.setFechaFinalizacion(rs
							.getTimestamp("fechaFinalizacion"));
					ResultSet rsa = st
							.executeQuery("SELECT * FROM venta_apartado_datos WHERE idVenta="
									+ id);
					if (rsa.next()) {
						va.setCliente(rsa.getString("cliente"));
						va.setDomicilio(rsa.getString("domicilio"));
						va.setTelefono(rsa.getString("telefono"));
					}
					rsa.close();
					venta = va;
					break;
				}
				// Ponemos los pagos
				venta.setIdBoutique(idBoutique);
				venta.setPagos(getPagos(venta.getId()));
				// Ahora ponemos los productos vendidos
				venta.setProductosVendidos(getProductosVendidos(venta.getId()));
				venta.setProductosDevueltos(getProductosDevueltos(venta.getId()));
			}
			rs.close();
			st.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return venta;
	}

	public static List<Pago> getPagos(Venta v) {
		v.abonado = 0;
		List<Pago> pagos = new ArrayList<Pago>();
		try {
			Connection con = DaoSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT * FROM pagos WHERE idVenta=" + v.getId());
			while (rs.next()) {
				Pago pago = null;
				// Checamos el tipo de pago
				int tipoPago = rs.getInt("tipoPago");
				switch (tipoPago) {
				case 1:

					// Efectivo
					pago = new Pago();
					pago.setEnCorte(rs.getInt("enCorte"));
					pago.setFecha(rs.getTimestamp("fecha"));
					pago.setId(rs.getInt("id"));
					pago.setIdSucursal(rs.getInt("idSucursal"));
					pago.setIdVendedor(rs.getInt("idVendedor"));
					pago.setIdVenta(rs.getInt("idVenta"));
					pago.setMonto(rs.getDouble("monto"));
					pago.setImpuestoMonto(rs.getDouble("impuestoMonto"));

					pago.setTipo(tipoPago);

					break;
				case 2:

					// Credito
					PagoTarjetaCredito pt = new PagoTarjetaCredito();

					// Obtenemos los datos
					ResultSet rsPagoTarjeta = st
							.executeQuery("SELECT * FROM pagos_tarjeta WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoTarjeta.next()) {
						pt.setEnCorte(rs.getInt("enCorte"));
						pt.setFecha(rs.getTimestamp("fecha"));
						pt.setId(rs.getInt("id"));
						pt.setIdSucursal(rs.getInt("idSucursal"));
						pt.setIdVendedor(rs.getInt("idVendedor"));
						pt.setIdVenta(rs.getInt("idVenta"));
						pt.setMonto(rs.getDouble("monto"));
						pt.setImpuestoMonto(rs.getDouble("impuestoMonto"));
						pt.setTipo(tipoPago);
						pt.setBanco(rsPagoTarjeta.getString("banco"));
						pt.setMeses(rsPagoTarjeta.getInt("meses"));

					}
					pago = pt;
					break;
				case 3:

					// Vale
					PagoVale pv = new PagoVale();
					ResultSet rsPagoVale = st
							.executeQuery("SELECT * FROM pagos_vale WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoVale.next()) {
						pv.setIdVale(rsPagoVale.getInt("idVale"));
						pv.setEnCorte(rs.getInt("enCorte"));
						pv.setFecha(rs.getTimestamp("fecha"));
						pv.setId(rs.getInt("id"));
						pv.setIdSucursal(rs.getInt("idSucursal"));
						pv.setIdVendedor(rs.getInt("idVendedor"));
						pv.setIdVenta(rs.getInt("idVenta"));
						pv.setMonto(rs.getDouble("monto"));
						pv.setImpuestoMonto(rs.getDouble("impuestoMonto"));
						pv.setTipo(tipoPago);
					}
					pago = pv;
					break;
				case 4:

					// Cheque
					PagoCheque pch = new PagoCheque();
					ResultSet rsPagoCheque = st
							.executeQuery("SELECT * FROM pagos_cheque WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoCheque.next()) {
						pch.setEnCorte(rs.getInt("enCorte"));
						pch.setFecha(rs.getTimestamp("fecha"));
						pch.setId(rs.getInt("id"));
						pch.setIdSucursal(rs.getInt("idSucursal"));
						pch.setIdVendedor(rs.getInt("idVendedor"));
						pch.setIdVenta(rs.getInt("idVenta"));
						pch.setMonto(rs.getDouble("monto"));
						pch.setImpuestoMonto(rs.getDouble("impuestoMonto"));
						pch.setTipo(tipoPago);
						pch.setBanco(rsPagoCheque.getString("banco"));
						pch.setNoCheque(rsPagoCheque.getString("noCheque"));
					}
					pago = pch;
					break;
				case 5: // Descuento pago por entrega de vale

					// Efectivo
					PagoDevolucion pdv = new PagoDevolucion();
					pdv.setEnCorte(rs.getInt("enCorte"));
					pdv.setFecha(rs.getTimestamp("fecha"));
					pdv.setId(rs.getInt("id"));
					pdv.setIdSucursal(rs.getInt("idSucursal"));
					pdv.setIdVendedor(rs.getInt("idVendedor"));
					pdv.setIdVenta(rs.getInt("idVenta"));
					pdv.setMonto(rs.getDouble("monto"));
					pdv.setImpuestoMonto(rs.getDouble("impuestoMonto"));
					pdv.setTipo(tipoPago);
					pdv.setVale(DaoVales.findValeByPago(pdv.getId()));
					pago = pdv;
					break;
				case 6: // Pago por deposito o transferencia bancaria.
					PagoTransferenciaBancaria pb = new PagoTransferenciaBancaria();
					ResultSet rsPagoTransf = st
							.executeQuery("SELECT * FROM pagos_transf_banco WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoTransf.next()) {
						pb.setEnCorte(rs.getInt("enCorte"));
						pb.setFecha(rs.getTimestamp("fecha"));
						pb.setId(rs.getInt("id"));
						pb.setIdSucursal(rs.getInt("idSucursal"));
						pb.setIdVendedor(rs.getInt("idVendedor"));
						pb.setIdVenta(rs.getInt("idVenta"));
						pb.setMonto(rs.getDouble("monto"));
						pb.setImpuestoMonto(rs.getDouble("impuestoMonto"));

						pb.setTipo(tipoPago);
						pb.setNoReferencia(rsPagoTransf.getString("referencia"));

					}
					pago = pb;
				}
				v.setAbonado(v.getAbonado() + pago.getMonto());
				pagos.add(pago);
			}
			rs.close();
			v.setPagos(pagos);

			return pagos;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}

	}

	public static List<Pago> getPagos(int idVenta) {
		List<Pago> pagos = new ArrayList<Pago>();
		try {
			Connection con = DaoSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT * FROM pagos WHERE idVenta=" + idVenta);
			while (rs.next()) {
				Pago pago = null;
				// Checamos el tipo de pago
				int tipoPago = rs.getInt("tipoPago");
				switch (tipoPago) {
				case 1:

					// Efectivo
					pago = new Pago();
					pago.setEnCorte(rs.getInt("enCorte"));
					pago.setFecha(rs.getTimestamp("fecha"));
					pago.setId(rs.getInt("id"));
					pago.setIdSucursal(rs.getInt("idSucursal"));
					pago.setIdVendedor(rs.getInt("idVendedor"));
					pago.setIdVenta(rs.getInt("idVenta"));
					pago.setMonto(rs.getDouble("monto"));
					pago.setImpuestoMonto(rs.getDouble("impuestoMonto"));
					pago.setTipo(tipoPago);

					break;
				case 2:

					// Credito
					PagoTarjetaCredito pt = new PagoTarjetaCredito();

					// Obtenemos los datos
					ResultSet rsPagoTarjeta = st
							.executeQuery("SELECT * FROM pagos_tarjeta WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoTarjeta.next()) {
						pt.setEnCorte(rs.getInt("enCorte"));
						pt.setFecha(rs.getTimestamp("fecha"));
						pt.setId(rs.getInt("id"));
						pt.setIdSucursal(rs.getInt("idSucursal"));
						pt.setIdVendedor(rs.getInt("idVendedor"));
						pt.setIdVenta(rs.getInt("idVenta"));
						pt.setImpuestoMonto(rs.getDouble("impuestoMonto"));
						pt.setMonto(rs.getDouble("monto"));
						pt.setTipo(tipoPago);
						pt.setBanco(rsPagoTarjeta.getString("banco"));
						pt.setMeses(rsPagoTarjeta.getInt("meses"));

					}
					pago = pt;
					break;
				case 3:

					// Vale
					PagoVale pv = new PagoVale();
					ResultSet rsPagoVale = st
							.executeQuery("SELECT * FROM pagos_vale WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoVale.next()) {
						pv.setIdVale(rsPagoVale.getInt("idVale"));
						pv.setEnCorte(rs.getInt("enCorte"));
						pv.setFecha(rs.getTimestamp("fecha"));
						pv.setId(rs.getInt("id"));
						pv.setIdSucursal(rs.getInt("idSucursal"));
						pv.setIdVendedor(rs.getInt("idVendedor"));
						pv.setIdVenta(rs.getInt("idVenta"));
						pv.setMonto(rs.getDouble("monto"));
						pv.setImpuestoMonto(rs.getDouble("impuestoMonto"));

						pv.setTipo(tipoPago);
					}
					pago = pv;
					break;
				case 4:

					// Cheque
					PagoCheque pch = new PagoCheque();
					ResultSet rsPagoCheque = st
							.executeQuery("SELECT * FROM pagos_cheque WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoCheque.next()) {
						pch.setEnCorte(rs.getInt("enCorte"));
						pch.setFecha(rs.getTimestamp("fecha"));
						pch.setId(rs.getInt("id"));
						pch.setIdSucursal(rs.getInt("idSucursal"));
						pch.setIdVendedor(rs.getInt("idVendedor"));
						pch.setIdVenta(rs.getInt("idVenta"));
						pch.setMonto(rs.getDouble("monto"));
						pch.setImpuestoMonto(rs.getDouble("impuestoMonto"));

						pch.setTipo(tipoPago);
						pch.setBanco(rsPagoCheque.getString("banco"));
						pch.setNoCheque(rsPagoCheque.getString("noCheque"));
					}
					pago = pch;
					break;
				case 5: // Descuento pago por entrega de vale

					// Efectivo
					// pago = new Pago();
					PagoDevolucion pdv = new PagoDevolucion();
					pdv.setEnCorte(rs.getInt("enCorte"));
					pdv.setFecha(rs.getTimestamp("fecha"));
					pdv.setId(rs.getInt("id"));
					pdv.setIdSucursal(rs.getInt("idSucursal"));
					pdv.setIdVendedor(rs.getInt("idVendedor"));
					pdv.setIdVenta(rs.getInt("idVenta"));
					pdv.setMonto(rs.getDouble("monto"));
					pdv.setImpuestoMonto(rs.getDouble("impuestoMonto"));

					pdv.setTipo(tipoPago);
					pdv.setVale(DaoVales.findValeByPago(pdv.getId()));
					pago = pdv;
					break;
				case 6: // Pago por deposito o transferencia bancaria.
					PagoTransferenciaBancaria pb = new PagoTransferenciaBancaria();
					ResultSet rsPagoTransf = st
							.executeQuery("SELECT * FROM pagos_transf_banco WHERE idPago="
									+ rs.getInt("id"));
					if (rsPagoTransf.next()) {
						pb.setEnCorte(rs.getInt("enCorte"));
						pb.setFecha(rs.getTimestamp("fecha"));
						pb.setId(rs.getInt("id"));
						pb.setIdSucursal(rs.getInt("idSucursal"));
						pb.setIdVendedor(rs.getInt("idVendedor"));
						pb.setIdVenta(rs.getInt("idVenta"));
						pb.setMonto(rs.getDouble("monto"));
						pb.setImpuestoMonto(rs.getDouble("impuestoMonto"));

						pb.setTipo(tipoPago);
						pb.setNoReferencia(rsPagoTransf.getString("referencia"));

					}
					pago = pb;
				}

				pagos.add(pago);
			}
			rs.close();

			return pagos;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static Vale generarVale(double monto, int idVenta) {
		// Agregamos un vale en la tabla
		Vale vale = null;
		Connection con = DaoSource.getConnectionLocal();
		try {
			Statement st = con.createStatement();

			// GENERAMOS PRIMERAMENTE EL PAGO NEGATIVO
			Pago pago = new Pago();
			pago.setEnCorte(0); // TODO CAMBIAR ESTA LOGICA, LOS PAGOS NEGATIVOS
								// NO DEBERIAN DE APARECER EN NINGUN CORTE NI
								// SER UTILIZADOS PARA CALCULAR
			// EL MONTO DE LOS VALES EMITIDOS POR SUCURSAL.
			pago.setIdSucursal(AppInstance.boutique().getId());
			pago.setIdVendedor(AppInstance.usuario().getId());
			pago.setIdVenta(idVenta);
			pago.setIdTerminal(AppInstance.terminal().getId());
			pago.setTipo(5); // Registro del descuento por el vale que se esta
								// entregando
			pago.setMonto(monto * -1);
			pago.setImpuestoMonto(IvaHelper.getIvaDesglosadFromMonto(pago
					.getMonto()));
			List<Pago> pagos = new ArrayList<Pago>();
			pagos.add(pago);
			pagos = registrarPagos(idVenta, pagos, con);
			pago = (Pago) pagos.get(0);

			String sql = "INSERT INTO vales (monto,numero,utilizado,idPago,idTerminalCreacion) VALUES ("
					+ monto
					+ ",0,0,"
					+ pago.getId()
					+ ","
					+ AppInstance.terminal().getId() + ");";
			st.execute(sql);
			sql = "SELECT * FROM vales ORDER BY id DESC LIMIT 0,1";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				// Generamos el pago
				vale = new Vale();
				vale.setId(rs.getInt("id"));
				vale.setMonto(rs.getDouble("monto"));
				vale.setNumero(rs.getDouble("numero"));
				vale.setUtilizado(rs.getInt("utilizado"));
				vale.setIdPago(pago.getId());
				vale.setIdTerminalCreacion(pago.getIdTerminal());

			}
			rs.close();
			st.close();
			// Generamos el pago

			// con.close();
			return vale;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static List<Venta> findVentasFacturaGeneralByFecha(Date fecha) {

		String sql = "SELECT * FROM ventas WHERE fecha>='"
				+ ff.format(fecha)
				+ "T00:00:00' AND fecha<='"
				+ ff.format(fecha)
				+ "T23:59:59' AND ((tipo='0' AND requiereFacturaIndividual='0'));";

		return getVentasParaFacturacion(fecha, sql);
	}

	private static List<Venta> getVentasParaFacturacion(Date fecha, String sql) {
		List<Venta> ventas = new ArrayList<Venta>();
		Connection con = DaoSource.getConnectionLocal();

		Statement st;
		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Venta venta = null;
				venta = mapRsToVenta(venta, rs);
				ventas.add(venta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ventas;
	}

	public static Vale generarValeManual(double monto, String motivo,
			int idUsuario, int idBoutique) {
		// Agregamos un vale en la tabla
		Vale vale = null;
		Connection con = DaoSource.getConnectionLocal();
		try {
			con.setAutoCommit(false);
			Statement st = con.createStatement();

			String sql = "INSERT INTO vales (monto,numero,utilizado,idPago) VALUES ("
					+ monto + ",0,0,NULL);";
			st.execute(sql);
			sql = "SELECT * FROM vales ORDER BY id DESC LIMIT 0,1";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				// Generamos el pago
				vale = new Vale();
				vale.setId(rs.getInt("id"));
				vale.setMonto(rs.getDouble("monto"));
				vale.setNumero(rs.getDouble("numero"));
				vale.setUtilizado(rs.getInt("utilizado"));
				// vale.setIdPago(pago.getId());
			}
			rs.close();
			st.close();
			// INSERTAMOS EL MOTIVO POR EL QUE SE GENERA EL VALE
			con.createStatement()
					.execute(
							"INSERT INTO vales_generacion_manual (idVale,idUsuario,idBoutique,motivo,fecha) VALUES ("
									+ vale.getId()
									+ ","
									+ idUsuario
									+ ","
									+ idBoutique + ",'" + motivo + "',NOW())");
			con.commit();
			con.setAutoCommit(true);
			// Generamos el pago

			// con.close();
			return vale;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			try {
				con.rollback();
			} catch (SQLException ex1) {
			}

			return null;
		}
	}

	public static List<ProductoDevuelto> getProductosDevueltos(int idVenta) {
		List<ProductoDevuelto> productos = new ArrayList<ProductoDevuelto>();
		ResultSet rs = null;
		Statement st = null;
		Connection con = DaoSource.getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT DISTINCT productosdevueltos.*, inventarios.idTipoProducto FROM productosdevueltos INNER JOIN inventarios ON inventarios.etiqueta=productosdevueltos.etiqueta WHERE productosdevueltos.idVenta="
					+ idVenta);
			while (rs.next()) {
				ProductoDevuelto p = new ProductoDevuelto();
				p.setDescuento(rs.getInt("descuento"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setFechaDevolucion(rs.getTimestamp("fechaDevolucion"));
				p.setId(rs.getInt("id"));
				p.setIdInventario(rs.getInt("idInventario"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setIdVendedor(rs.getInt("idVendedor"));
				p.setIdVenta(rs.getInt("idVenta"));
				p.setModelo(rs.getInt("modelo"));
				p.setImpuestoPrecioFinal(rs.getDouble("impuestoPrecioFinal"));
				p.setPrecioFinal(rs.getDouble("precioFinal"));
				p.setPrecioInicial(rs.getDouble("precioInicial"));
				productos.add(p);
			}
			rs.close();
			st.close();
			return productos;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static List<ProductoVendido> getProductosVendidos(int idVenta) {
		// Obtenemos los productos vendidos de la nota
		List<ProductoVendido> productos = new ArrayList<ProductoVendido>();
		ResultSet rs = null;
		Statement st = null;
		Connection con = DaoSource.getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT DISTINCT productosvendidos.*,inventarios.idTipoProducto,inventarios.estilo, inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor FROM productosvendidos LEFT JOIN inventarios_md ON productosvendidos.modelo=inventarios_md.idModeloInventario INNER JOIN inventarios ON inventarios.etiqueta=productosvendidos.etiqueta WHERE productosvendidos.idVenta="
					+ idVenta + " GROUP BY productosvendidos.id;");
			while (rs.next()) {
				ProductoVendido p = new ProductoVendido();
				p.setDescuento(rs.getInt("descuento"));
				p.setEntregado(rs.getInt("entregado"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("id"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setIdVenta(rs.getInt("idVenta"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioFinal(rs.getDouble("precioFinal"));
				p.setImpuestoPrecioFinal(rs.getDouble("impuestoPrecioFinal"));
				p.setPrecioInicial(rs.getDouble("precioInicial"));
				p.setIdInventario(rs.getInt("idInventario"));
				String tipoPr = AppInstance.idToTipoProducto.get(
						String.valueOf(p.getIdTipoProducto())).toString();
				tipoPr = ((tipoPr.length() > 13) ? tipoPr.substring(0, 12)
						: tipoPr);
				String detalle = tipoPr
						+ " "
						+ DaoInventarios.detalleProducto(
								rs.getString("estilo"), rs.getInt("idMarca"),
								rs.getInt("idDescripcion"),
								rs.getInt("idColor"));
				p.setDescripcion(detalle);
				productos.add(p);
			}
			rs.close();
			return productos;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static Venta getProductosVendidos(Venta venta) {

		// Obtenemos los productos vendidos de la nota
		List<ProductoVendido> productos = new ArrayList<ProductoVendido>();
		ResultSet rs = null;
		Statement st = null;
		Connection con = DaoSource.getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT DISTINCT productosvendidos.*,inventarios.idTipoProducto,inventarios.estilo, inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor FROM productosvendidos LEFT JOIN inventarios_md ON productosvendidos.modelo=inventarios_md.idModeloInventario INNER JOIN inventarios ON inventarios.etiqueta=productosvendidos.etiqueta WHERE productosvendidos.idVenta="
					+ venta.getId() + " GROUP BY productosvendidos.id");
			while (rs.next()) {
				ProductoVendido p = new ProductoVendido();
				p.setDescuento(rs.getInt("descuento"));
				p.setEntregado(rs.getInt("entregado"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("id"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setIdVenta(rs.getInt("idVenta"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioFinal(rs.getDouble("precioFinal"));
				p.setImpuestoPrecioFinal(rs.getDouble("impuestoPrecioFinal"));
				p.setPrecioInicial(rs.getDouble("precioInicial"));
				p.setIdInventario(rs.getInt("idInventario"));
				String tipoPr = AppInstance.idToTipoProducto.get(
						String.valueOf(p.getIdTipoProducto())).toString();
				tipoPr = ((tipoPr.length() > 13) ? tipoPr.substring(0, 12)
						: tipoPr);
				String detalle = tipoPr
						+ " "
						+ DaoInventarios.detalleProducto(
								rs.getString("estilo"), rs.getInt("idMarca"),
								rs.getInt("idDescripcion"),
								rs.getInt("idColor"));
				p.setDescripcion(detalle);

				productos.add(p);
				venta.setTotal(venta.getTotal() + p.getPrecioFinal());
			}
			rs.close();
			venta.setProductosVendidos(productos);
			return venta;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * devolverProductos
	 * 
	 * @param idVenta
	 *            int
	 * @param productosaDevolver
	 *            List
	 */
	public static boolean devolverProductos(int idVenta,
			List<ProductoVendido> productosaDevolver, boolean cancelarVenta,
			boolean mismaSucursal) {
		Connection con = DaoSource.getConnectionLocal();
		// Devolvemos los productos vendidos al inventario
		try {
			con.setAutoCommit(false);
			PreparedStatement psDevolver = con
					.prepareStatement("UPDATE inventarios SET cantidad=cantidad+1 WHERE etiqueta=? AND idBoutique=? AND id=?");
			PreparedStatement psEliminar = con
					.prepareStatement("DELETE FROM productosvendidos WHERE id=?");
			PreparedStatement psDevueltos = con
					.prepareStatement("INSERT INTO productosdevueltos (idVenta,etiqueta,modelo,precioInicial,descuento,impuestoPrecioFinal,precioFinal,idInventario,fechaDevolucion,idVendedor,idBoutique,idTerminal) values (?,?,?,?,?,?,?,?,NOW(),?,?,?)");
			PreparedStatement psBuscar = con
					.prepareStatement("SELECT * FROM inventarios where etiqueta=? and idBoutique="
							+ AppInstance.boutique().getId());
			PreparedStatement psInsertar = con
					.prepareStatement("INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
			ResultSet rsBuscar = null;
			for (ProductoVendido p : productosaDevolver) {
				if (mismaSucursal) {
					psDevolver.setString(1, p.getEtiqueta());
					psDevolver.setInt(2, AppInstance.boutique().getId());
					psDevolver.setInt(3, p.getIdInventario());
					psDevolver.execute();
				} else {
					psBuscar.setString(1, p.getEtiqueta());
					// BUSCAMOS SI HAY REGISTRO CON LA MISMA ETIQUETA
					rsBuscar = psBuscar.executeQuery();
					if (rsBuscar.next()) { // SI LO HAY HACEMOS UN UPDATE
											// CANTIDAD +1 DE LA BOUTIQUE ACTUAL
						psDevolver.setString(1, p.getEtiqueta());
						psDevolver.setInt(2, AppInstance.boutique().getId());
						psDevolver.setInt(3, rsBuscar.getInt("id"));
						psDevolver.execute();
					} else { // SI NO, INGRESAMOS INSERT INTO PRODUCTO
						Producto pp = DaoInventarios.findByEtiqueta(p
								.getEtiqueta());
						// Producto pp =
						// DaoInventarioInicial.findById(p.getIdInventario());
						if (pp != null) {
							psInsertar.setInt(1, pp.getIdTipoProducto());
							psInsertar.setLong(2, pp.getModelo());
							psInsertar.setString(3, pp.getTalla());
							psInsertar
									.setInt(4, AppInstance.boutique().getId());
							psInsertar.setInt(5, 1);
							psInsertar.setDouble(6, pp.getPrecioCosto());
							psInsertar.setDouble(7, pp.getPrecioPublico());
							psInsertar.setInt(8, pp.getClave());
							psInsertar.setInt(9, pp.getIdCompra());
							psInsertar.setString(10, pp.getEtiqueta());
							psInsertar.setString(11, "1");
							psInsertar.execute();
						}
					}

				}
				psEliminar.setInt(1, p.getId());
				// DEBEMOS INSERTAR LOS PRODUCTOS DEVUELTOS A PRODUCTOSDEVUELTOS
				// PARAT ENER UN REGISTRO DE QUE NOTA REGRESO LOS
				// PRODUCTOS
				psDevueltos.setInt(1, idVenta);
				psDevueltos.setString(2, p.getEtiqueta());
				psDevueltos.setInt(3, p.getModelo());
				psDevueltos.setDouble(4, p.getPrecioInicial());
				psDevueltos.setInt(5, p.getDescuento());
				psDevueltos.setDouble(6, p.getImpuestoPrecioFinal());
				psDevueltos.setDouble(7, p.getPrecioFinal());
				psDevueltos.setInt(8, p.getIdInventario());
				psDevueltos.setInt(9, AppInstance.usuario().getId());
				psDevueltos.setInt(10, AppInstance.boutique().getId());
				psDevueltos.setInt(11, AppInstance.terminal().getId());
				// SE DEBE DE REVISAR SI EL PRODUCTO QUE SE DEVUELVE ERA DE ESTA
				// BOUTIQUE, SI NO PARA REVISAR SI HAY ALGUN REGISTRO EN
				// ESTA BOUTIQUE DEL MISMO PRODUCTO Y AUMENTAR EL CONTADOR
				psDevueltos.execute();

				psEliminar.execute();
			}
			if (cancelarVenta) {

				Statement st = con.createStatement();
				st.execute("UPDATE ventas SET status=2 WHERE id=" + idVenta);
			}
			con.commit();
			con.setAutoCommit(true);
			psDevolver.close();
			psEliminar.close();
			// con.close();
			return true;
			// Eliminamos los productos vendidos de la tabla productosvendidos;
		} catch (SQLException ex) {
			try {
				System.out.println(ex);
				con.rollback();
			} catch (SQLException ee) {
			}
			return false;
		}

	}

	public static Venta findById(int idVenta) {
		Venta venta = null;
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		Statement st = null;
		String sql = "SELECT * FROM ventas WHERE id=" + idVenta;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// Checamos el tipo de venta que es
				venta = mapRsToVenta(venta, rs);
			}
			rs.close();
			st.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}
		return venta;
	}

	private static Venta mapRsToVenta(Venta venta, ResultSet rs)
			throws SQLException {
		int tipo = rs.getInt("tipo");
		switch (tipo) {
		case 0: // CONTADO
			Venta v = new Venta();
			v.setId(rs.getInt("id"));
			v.setEnCorte(rs.getInt("enCorte"));
			v.setFecha(rs.getTimestamp("fecha"));
			v.setIdVendedor(rs.getInt("idVendedor"));
			v.setIdBoutique(rs.getInt("idBoutique"));
			v.setStatus(rs.getInt("status"));
			v.setIdCliente(rs.getInt("idCliente"));
			v.setRequiereFacturaIndividual(rs.getBoolean("requiereFacturaIndividual"));
			// Ponemos los pagos
			v.setPagos(getPagos(v));
			// Ahora ponemos los productos vendidos
			v = getProductosVendidos(v);
			// Ponemos los datos
			venta = v;
			break;
		case 1: // CREDITO
			VentaCredito vc = new VentaCredito();
			vc.setId(rs.getInt("id"));
			vc.setEnCorte(rs.getInt("enCorte"));
			vc.setFecha(rs.getTimestamp("fecha"));
			vc.setIdVendedor(rs.getInt("idVendedor"));
			vc.setIdBoutique(rs.getInt("idBoutique"));
			vc.setStatus(rs.getInt("status"));
			vc.setFechaFinalizacion(rs.getTimestamp("fechaFinalizacion"));
			vc.setIdCliente(rs.getInt("idCliente"));
			vc.setNoDias(rs.getInt("noDias"));
			vc.setNoPagos(rs.getInt("noPagos"));
			vc.setRequiereFacturaIndividual(rs.getBoolean("requiereFacturaIndividual"));

			// Ponemos los pagos
			vc.setPagos(getPagos(vc.getId()));

			// Ahora ponemos los productos vendidos
			vc.setProductosVendidos(getProductosVendidos(vc.getId()));

			// Ponemos los datos
			// Calculamos los totales de cada cuenta
			vc = DaoVentas.calcularTotales(vc);
			if (vc.saldoActual <= 0.10 && vc.getStatus() == 0) { // PARCHE
																	// PARA
																	// BORRAR
																	// LAS
																	// NOTAS
																	// QUE
																	// YA
																	// TIENEN
																	// SALDO
																	// EN
																	// CERO
				DaoVentas.finalizarVenta(vc);
				vc.setStatus(1);
			}
			venta = (vc);
			break;

		case 2: // APARTADO
			VentaApartado va = new VentaApartado();
			va.setId(rs.getInt("id"));
			va.setEnCorte(rs.getInt("enCorte"));
			va.setFecha(rs.getTimestamp("fecha"));
			va.setIdVendedor(rs.getInt("idVendedor"));
			va.setIdBoutique(rs.getInt("idBoutique"));
			va.setStatus(rs.getInt("status"));
			va.setIdCliente(rs.getInt("idCliente"));
			va.setRequiereFacturaIndividual(rs.getBoolean("requiereFacturaIndividual"));

			// Ponemos los pagos
			va.setPagos(getPagos(va));

			// Ahora ponemos los productos vendidos
			va = (VentaApartado) getProductosVendidos(va);
			va.setMontoPendiente(va.getTotal() - va.getAbonado());
			venta = (va);
			break;
		}
		// Venta a credito
		return venta;
	}

	/**
	 * findHistorialCliente
	 * 
	 * @param idCliente
	 *            int
	 * @return Venta
	 */
	public static List<Venta> findHistorialCliente(int idCliente) {
		List<Venta> ventas = new ArrayList<Venta>();
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		Statement st = null;
		String sql = "SELECT * FROM ventas WHERE idCliente=" + idCliente
				+ " ORDER BY id DESC";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// Checamos el tipo de venta que es
				int tipo = rs.getInt("tipo");
				switch (tipo) {
				case 0: // CONTADO
					Venta v = new Venta();
					v.setId(rs.getInt("id"));
					v.setEnCorte(rs.getInt("enCorte"));
					v.setFecha(rs.getTimestamp("fecha"));
					v.setIdVendedor(rs.getInt("idVendedor"));
					v.setIdBoutique(rs.getInt("idBoutique"));
					v.setStatus(rs.getInt("status"));
					v.setIdCliente(rs.getInt("idCliente"));

					// Ponemos los pagos
					v.setPagos(getPagos(v));

					// Ahora ponemos los productos vendidos
					v = getProductosVendidos(v);

					// Ponemos los datos
					ventas.add(v);

					break;
				case 1: // CREDITO
					VentaCredito vc = new VentaCredito();
					vc.setId(rs.getInt("id"));
					vc.setEnCorte(rs.getInt("enCorte"));
					vc.setFecha(rs.getTimestamp("fecha"));
					vc.setIdVendedor(rs.getInt("idVendedor"));
					vc.setIdBoutique(rs.getInt("idBoutique"));
					vc.setStatus(rs.getInt("status"));
					vc.setFechaFinalizacion(rs
							.getTimestamp("fechaFinalizacion"));
					vc.setIdCliente(rs.getInt("idCliente"));
					vc.setNoDias(rs.getInt("noDias"));
					vc.setNoPagos(rs.getInt("noPagos"));

					// Ponemos los pagos
					vc.setPagos(getPagos(vc.getId()));

					// Ahora ponemos los productos vendidos
					vc.setProductosVendidos(getProductosVendidos(vc.getId()));

					// Ponemos los datos
					// Calculamos los totales de cada cuenta
					vc = DaoVentas.calcularTotales(vc);
					if (vc.saldoActual <= 0.10 && vc.getStatus() == 0) { // PARCHE
																			// PARA
																			// BORRAR
																			// LAS
																			// NOTAS
																			// QUE
																			// YA
																			// TIENEN
																			// SALDO
																			// EN
																			// CERO
						DaoVentas.finalizarVenta(vc);
						vc.setStatus(1);
					}
					ventas.add(vc);

					break;

				case 2: // APARTADO
					VentaApartado va = new VentaApartado();
					va.setId(rs.getInt("id"));
					va.setEnCorte(rs.getInt("enCorte"));
					va.setFecha(rs.getTimestamp("fecha"));
					va.setIdVendedor(rs.getInt("idVendedor"));
					va.setIdBoutique(rs.getInt("idBoutique"));
					va.setStatus(rs.getInt("status"));
					va.setIdCliente(rs.getInt("idCliente"));

					// Ponemos los pagos
					va.setPagos(getPagos(va));

					// Ahora ponemos los productos vendidos
					va = (VentaApartado) getProductosVendidos(va);
					va.setMontoPendiente(va.getTotal() - va.getAbonado());
					ventas.add(va);
					break;
				}
				// Venta a credito

			}
			rs.close();
			st.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}

		return ventas;

	}

	/**
	 * findByIdCliente
	 * 
	 * @param idCliente
	 *            int
	 * @return Venta
	 */
	public static List<VentaCredito> findByIdCliente(int idCliente) {
		List<VentaCredito> ventas = new ArrayList<VentaCredito>();
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		Statement st = null;
		String sql = "SELECT * FROM ventas WHERE idCliente=" + idCliente
				+ " AND status=0 AND tipo=1";

		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// Checamos el tipo de venta que es
				// Venta a credito
				VentaCredito vc = new VentaCredito();
				vc.setId(rs.getInt("id"));
				vc.setEnCorte(rs.getInt("enCorte"));
				vc.setFecha(rs.getTimestamp("fecha"));
				vc.setIdVendedor(rs.getInt("idVendedor"));
				vc.setIdBoutique(rs.getInt("idBoutique"));
				vc.setStatus(rs.getInt("status"));
				vc.setFechaFinalizacion(rs.getTimestamp("fechaFinalizacion"));
				vc.setIdCliente(rs.getInt("idCliente"));
				vc.setNoDias(rs.getInt("noDias"));
				vc.setNoPagos(rs.getInt("noPagos"));

				// Ponemos los pagos
				vc.setPagos(getPagos(vc.getId()));
				// Ahora ponemos los productos vendidos
				vc.setProductosVendidos(getProductosVendidos(vc.getId()));
				// Ponemos los datos
				// Calculamos los totales de cada cuenta
				vc = DaoVentas.calcularTotales(vc);
				if (vc.saldoActual <= 0.10) { // PARCHE PARA BORRAR LAS NOTAS
												// QUE YA TIENEN SALDO EN CERO
					DaoVentas.finalizarVenta(vc);
				} else {
					ventas.add(vc);
				}
			}
			rs.close();
			st.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}

		return ventas;

	}

	private void jbInit() throws Exception {
	}

	/**
	 * entregarProducto
	 * 
	 * @param pv
	 *            ProductoVendido
	 */
	public static boolean entregarProducto(ProductoVendido pv) {
		Connection con = DaoSource.getConnectionLocal();
		try {
			String sql = "UPDATE productosvendidos SET entregado=1 WHERE id="
					+ pv.getId();
			con.createStatement().execute(sql);
			// con.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ee) {
			}
			return false;
		}

	}

	/**
	 * calcularTotales
	 * 
	 * @param lista
	 *            List
	 */
	public static VentaCredito calcularTotales(VentaCredito vc) {
		vc.subTotal = Double.parseDouble(AppInstance.number.format(
				DaoVentas.findSubtotal(vc.getId())).replaceAll(",", ""));
		vc.setAnticipo(Double.parseDouble(AppInstance.number.format(
				DaoVentas.findAnticipo(vc.getId())).replaceAll(",", "")));
		vc.saldoTotal = Double.parseDouble(AppInstance.number.format(
				vc.subTotal - vc.getAnticipo()).replaceAll(",", ""));
		vc.saldoAbonado = Double.parseDouble(AppInstance.number.format(
				DaoVentas.findAbonado(vc.getId())).replaceAll(",", ""));
		VentaCredito.getSaldoVencido(vc); // CALCULAMOS EL SALDO VENCIDO
											// TENIENDO PREVIAMENTE LOS ABONOS
											// YA REALIZADOS
		vc.saldoActual = Double.parseDouble(AppInstance.number.format(
				vc.subTotal - vc.saldoAbonado).replaceAll(",", ""));
		return vc;
	}

	public static int cancelarAbono(int idAbono, String razon) {
		int movimiento = 0;
		Connection con = DaoSource.getConnectionLocal();
		ResultSet st = null;
		PreparedStatement insertPagoCancelado = null;
		try {
			con.setAutoCommit(false);
			insertPagoCancelado = con
					.prepareStatement("INSERT INTO pagos_cancelados (idPago,fecha,idVenta,monto,tipoPago,enCorte,idVendedor,idSucursal,esAnticipo,fechaCancelacion,idSucursalCancelacion,idVendedorCancelacion,razonCancelacion) VALUES (?,?,?,?,?,?,?,?,?,NOW(),?,?,?);");
			st = con.createStatement().executeQuery(
					"SELECT * FROM pagos where id=" + idAbono);
			if (st.next()) { // se encontro el pago..lo insertamos en pagos
								// cancelados
				insertPagoCancelado.setInt(1, st.getInt("id"));
				insertPagoCancelado.setTimestamp(2, st.getTimestamp("fecha"));
				insertPagoCancelado.setInt(3, st.getInt("idVenta"));
				insertPagoCancelado.setDouble(4, st.getDouble("monto"));
				insertPagoCancelado.setInt(5, st.getInt("tipoPago"));
				insertPagoCancelado.setInt(6, st.getInt("enCorte"));
				insertPagoCancelado.setInt(7, st.getInt("idVendedor"));
				insertPagoCancelado.setInt(8, st.getInt("idSucursal"));

				insertPagoCancelado.setInt(9, st.getInt("esAnticipo"));

				insertPagoCancelado.setInt(10, AppInstance.boutique().getId());
				insertPagoCancelado.setInt(11, AppInstance.usuario().getId());
				insertPagoCancelado.setString(12, razon);
				// INSERTAMOS EL PAGO CANCELADO
				insertPagoCancelado.execute();
				con.createStatement().execute(
						"UPDATE ventas set status=0 where id="
								+ st.getInt("idVenta"));
				ResultSet rs = con
						.createStatement()
						.executeQuery(
								"SELECT id FROM pagos_cancelados ORDER BY id DESC LIMIT 1");
				rs.next();
				movimiento = rs.getInt("id");
				con.createStatement().executeUpdate(
						"DELETE FROM pagos where id=" + idAbono);

				rs.close();
				rs = null;
				insertPagoCancelado = null;
				con.commit();
				con.setAutoCommit(true);

				// TENGO EL MOVIMIENTO.. AHORA BORRO EL ABONO
			}

			return movimiento;
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			return -1;
		}
	}

	/**
	 * finalizarNotaConDescuento
	 * 
	 * @param promocion
	 *            DescPagoAnticipado
	 * @param venta
	 *            VentaCredito
	 */
	public static boolean finalizarNotaConDescuento(
			DescPagoAnticipado promocion, VentaCredito venta, List<Pago> pagos) { // MANDAMOS
																					// TAMBIEN
																					// LOS
																					// PAGOS
		boolean r = false;
		Connection con = DaoSource.getConnection();
		try {
			con.setAutoCommit(false);

			// BUSCAMOS TODOS LOS PRODUCTOS Y LOS MODIFICAMOS EL PORCENTAJE DE
			// DESCUENTO AL CALCULADO
			String sql = "UPDATE productosvendidos SET descuento="
					+ promocion.getPorcentajeDescuento()
					+ ",precioFinal=(precioFinal-("
					+ promocion.getPorcentajeDescuento() / 100
					+ "*precioInicial)) WHERE idVenta=" + venta.getId();
			con.createStatement().execute(sql);

			// CALCULAMOS LOS TOTALES NUEVAMENTE DE LA VENTA.
			venta = (VentaCredito) DaoVentas
					.findByIdNoFinalizada(venta.getId());

			PreparedStatement insertPago = null;
			PreparedStatement insertPagoDetalle = null;
			ResultSet rs = null;

			insertPago = con
					.prepareStatement("INSERT INTO pagos (fecha,idVenta,impuestoMonto,monto,tipoPago,enCorte,idVendedor,idSucursal,esAnticipo,idTerminal) VALUES (NOW(),?,?,?,?,?,?,?,?,?);");
			for (Pago p : pagos) {
				// Insertamos en pagos
				// insertPago.setTimestamp(1, p.getFecha());
				insertPago.setInt(1, venta.getId());
				insertPago.setDouble(2, p.getImpuestoMonto());
				insertPago.setDouble(3, p.getMonto());
				insertPago.setInt(4, p.getTipo());
				insertPago.setInt(5, p.getEnCorte());
				insertPago.setInt(6, p.getIdVendedor());
				insertPago.setInt(7, p.getIdSucursal());
				insertPago.setInt(8, p.getEsAnticipo());
				insertPago.setInt(9, p.getIdTerminal());
				insertPago.execute();
				sql = "SELECT id,fecha FROM pagos ORDER BY id DESC LIMIT 0,1";
				rs = con.createStatement().executeQuery(sql);
				rs.next();
				int idPago = rs.getInt("id");
				p.setFecha(rs.getTimestamp("fecha"));
				p.setId(idPago);
				rs.close();
				if (p instanceof PagoCheque) {
					PagoCheque pch = (PagoCheque) p;

					// Insertamos en pagos y en pagos_cheque
					sql = "INSERT INTO pagos_cheque (idPago,banco,noCheque) VALUES (?,?,?);";
					insertPagoDetalle = con.prepareStatement(sql);
					insertPagoDetalle.setInt(1, idPago);
					insertPagoDetalle.setString(2, pch.getBanco());
					insertPagoDetalle.setString(3, pch.getNoCheque());
					insertPagoDetalle.execute();
				} else if (p instanceof PagoVale) {
					PagoVale pv = (PagoVale) p;
					// Insertamos en pagos_vale
					sql = "INSERT INTO pagos_vale (idPago,idVale) VALUES (?,?);";
					insertPagoDetalle = con.prepareStatement(sql);
					insertPagoDetalle.setInt(1, idPago);
					insertPagoDetalle.setInt(2, pv.getIdVale());
					insertPagoDetalle.execute();
					// PONEMOS EL VALE COMO UTILIZADO
					sql = "UPDATE vales SET utilizado=1 WHERE id="
							+ pv.getIdVale();
					con.createStatement().execute(sql);
				} else if (p instanceof PagoTarjetaCredito) {
					PagoTarjetaCredito pt = (PagoTarjetaCredito) p;
					// Insertamos en pagos_tarjeta
					sql = "INSERT INTO pagos_tarjeta (banco,meses,idPago) VALUES (?,?,?);";
					insertPagoDetalle = con.prepareStatement(sql);
					insertPagoDetalle.setString(1, pt.getBanco());
					insertPagoDetalle.setInt(2, pt.getMeses());
					insertPagoDetalle.setInt(3, idPago);
					insertPagoDetalle.execute();
				} else if (p instanceof PagoTransferenciaBancaria) {
					PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) p;
					sql = "INSERT INTO pagos_transf_banco (idPago,referencia) VALUES (?,?);";
					insertPagoDetalle = con.prepareStatement(sql);
					insertPagoDetalle.setInt(1, idPago);
					insertPagoDetalle.setString(2, pb.getNoReferencia());
					insertPagoDetalle.execute();

				}
				if (insertPagoDetalle != null) {
					insertPagoDetalle.close();
				}
			}

			sql = "UPDATE ventas set status=? , fechaFinalizacion=NOW() where id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 1);
			st.setInt(2, venta.getId());
			st.executeUpdate();
			entregarArticulos(venta.getId(), con);

			con.commit();
			con.setAutoCommit(true);
			r = true;
		} catch (SQLException ex) {
			r = false;
			System.out.println(ex.toString());
			try {
				con.rollback();
				con.setAutoCommit(true);

			} catch (SQLException ex1) {
			}
		}

		return r;
		// TENEMOS LOS PAGOS, LOS REGISTRAMOS

		// TERMINAMOS LA VENTA
		// IMPRIMIMOS EL TICKET

	}

	/**
	 * Metodo utilizado para aplicar un descuento a una venta de credito desde
	 * la interfaz de administracion
	 * 
	 * @param idVenta
	 * @param idProducto
	 * @param idUser
	 * @param descuento
	 * @param descripcion
	 * @return
	 */
	public static boolean aplicarDescuentoCredito(int idProducto, int idUser,
			int descuento, String descripcion) {
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		boolean result = false;
		try {
			// (_IDVENTA INT,_IDPRODUCTO INT,_IDUSER INT, _DESCUENTO
			// INT,_DESCRIPCION VARCHAR(255)
			String sql = "SELECT aplicarDescuentoCredito(" + idProducto + ","
					+ idUser + "," + descuento + ",'" + descripcion
					+ "') as result;";
			rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				result = rs.getBoolean("result");
			}

			rs.close();
			rs = null;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		return result;
	}

	public static List<Venta> findVentasFacturaPorClienteContado(Date fecha) {
		String sql = "SELECT * FROM ventas WHERE fecha>='"
			+ ff.format(fecha)
			+ "T00:00:00' AND fecha<='"
			+ ff.format(fecha)
			+ "T23:59:59' AND (tipo='0' AND requiereFacturaIndividual='1');";

	        return getVentasParaFacturacion(fecha, sql);
	}
	public static List<Venta> findVentasFacturaPorClienteByFechaCredito(Date fecha) {
		String sql = "SELECT * FROM ventas WHERE fecha>='"
			+ ff.format(fecha)
			+ "T00:00:00' AND fecha<='"
			+ ff.format(fecha)
			+ "T23:59:59' AND tipo='1';";

	        return getVentasParaFacturacion(fecha, sql);
	}
	public static List<Venta> findVentasFacturaPorClienteByFechaApartado(Date fecha) {
		String sql = "SELECT * FROM ventas WHERE fecha>='"
			+ ff.format(fecha)
			+ "T00:00:00' AND fecha<='"
			+ ff.format(fecha)
			+ "T23:59:59' AND tipo='2';";

	        return getVentasParaFacturacion(fecha, sql);
	}
}
