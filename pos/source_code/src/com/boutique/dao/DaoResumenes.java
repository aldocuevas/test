package com.boutique.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.boutique.domain.CorteDeCaja;
import com.boutique.engine.impl.AppInstance;

public class DaoResumenes {

	public static double totalDiarioVentasDeContado = 0;

	public static List<Object[]> getDevolucionesDelDia(int idBoutique,
			java.util.Date fecha) {

		java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		List<Object[]> diario = new ArrayList<Object[]>();
		Object[] row = null;

		Connection con = DaoSource.getConnectionLocal();
		String sql = "SELECT DISTINCT productosdevueltos.fechaDevolucion,productosdevueltos.idVenta,inventarios.idTipoProducto,productosdevueltos.etiqueta,productosdevueltos.precioFinal,productosdevueltos.idVendedor,productosdevueltos.idBoutique FROM productosdevueltos INNER JOIN inventarios ON inventarios.etiqueta=productosdevueltos.etiqueta WHERE productosdevueltos.idBoutique="
				+ idBoutique
				+ " AND fechaDevolucion>='"
				+ ff.format(fecha)
				+ "T00:00:00' AND fechaDevolucion<='"
				+ ff.format(fecha)
				+ "T:23:59:59';";
		ResultSet rs = null;

		Statement pst = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			while (rs.next()) {
				row = new Object[6];
				row[0] = AppInstance.formatoLargo.format(rs
						.getTimestamp("fechaDevolucion"));
				row[1] = AppInstance.idToTipoProducto.get(rs
						.getString("idTipoProducto"));
				row[2] = rs.getString("etiqueta");
				row[3] = rs.getInt("idVenta");
				row[4] = AppInstance.idToNombreUsuario.get(rs
						.getInt("idVendedor"));
				row[5] = AppInstance.number.format(rs.getDouble("precioFinal"));
				diario.add(row);
				// POR CADA NOTA MANDAMOS LLAMAR NUESTRO PROCEDIMIENTO
				// ALMACENADO
				// ID DE LA VENTA
				// MONTO Y TIPO DE PAGO
				// DEVOLVEMOS ESA LISTA
			}
			rs.close();
			pst.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return diario;

	}

	public static List<Object[]> getDevolucionesDelDia(int idBoutique,
			int idTerminal) {

		List<Object[]> diario = new ArrayList<Object[]>();
		Object[] row = null;

		Connection con = DaoSource.getConnectionLocal();
		String sql = "SELECT DISTINCT productosdevueltos.fechaDevolucion,productosdevueltos.idVenta,inventarios.idTipoProducto,productosdevueltos.etiqueta,productosdevueltos.precioFinal,productosdevueltos.idVendedor,productosdevueltos.idBoutique FROM productosdevueltos INNER JOIN inventarios ON inventarios.etiqueta=productosdevueltos.etiqueta WHERE productosdevueltos.idBoutique="
				+ idBoutique
				+ " AND productosdevueltos.idTerminal="
				+ idTerminal
				+ " AND fechaDevolucion>=DATE(NOW()) AND fechaDevolucion<=NOW();";
		ResultSet rs = null;

		Statement pst = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			while (rs.next()) {
				row = new Object[6];
				row[0] = AppInstance.formatoLargo.format(rs
						.getTimestamp("fechaDevolucion"));
				row[1] = AppInstance.idToTipoProducto.get(rs
						.getString("idTipoProducto"));
				row[2] = rs.getString("etiqueta");
				row[3] = rs.getInt("idVenta");
				row[4] = AppInstance.idToNombreUsuario.get(rs
						.getInt("idVendedor"));
				row[5] = AppInstance.number.format(rs.getDouble("precioFinal"));
				diario.add(row);
				// POR CADA NOTA MANDAMOS LLAMAR NUESTRO PROCEDIMIENTO
				// ALMACENADO
				// ID DE LA VENTA
				// MONTO Y TIPO DE PAGO
				// DEVOLVEMOS ESA LISTA
			}
			rs.close();
			pst.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return diario;

	}

	public static void registrarAutorizacionVentaCreditoConSaldoVencido(
			int idVendedor, int idCliente, double saldoVencido, int idBoutique,
			int idTerminal) {
		String sql = "INSERT INTO autorizacion_credito_vencido (idVendedor,idCliente,saldoVencido,fecha,idBoutique,enCorte,idTerminal) VALUES (?,?,?,NOW(),?,0,?)";
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, idVendedor);
			ps.setInt(2, idCliente);
			ps.setDouble(3, saldoVencido);
			ps.setInt(4, idBoutique);
			ps.setInt(5, idTerminal);
			System.out.println(ps.toString());
			ps.execute();
		} catch (SQLException ex) {
			System.out.println(ex.toString());

		}
	}

	public static CorteDeCaja getCorte(int idSucursal, int idTerminal) {
		CorteDeCaja corte = null;
		Connection con = DaoSource.getConnection();
		try {
			CallableStatement cst = con
					.prepareCall("{call obtenerCorteDeCajaPorTerminal2014("
							+ idSucursal + "," + idTerminal + ")}");
			ResultSet rs = cst.executeQuery();
			if (rs.next()) {
				corte = new CorteDeCaja();
				corte.setFondoFijoEnCaja(rs.getDouble("_FONDO_FIJO"));
				corte.setTotalEfectivo(rs.getDouble("_TOTAL_EFECTIVO"));
				corte.setTotalTarjeta(rs.getDouble("_TOTAL_TARJETA"));
				corte.setTotalCheque(rs.getDouble("_TOTAL_CHEQUE"));
				corte.setTotalVales(rs.getDouble("_TOTAL_VALES"));
				corte.setTotalTransferencia(rs
						.getDouble("_TOTAL_TRANSFERENCIA"));
				corte.setCreditos(rs.getDouble("_CREDITOS"));
				corte.setValesEmitidos(rs.getInt("_VALES_EMITIDOS"));
				corte.setMontoValesEmitidos(rs
						.getDouble("_MONTO_VALES_EMITIDOS"));
				corte.setTotalEnCaja(rs.getDouble("_TOTAL_EN_CAJA"));
				corte.setSalidas(rs.getDouble("_TOTAL_SALIDAS"));
			}
			rs.close();
			cst.close();
			return corte;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * Registra corte de caja en base de datos
	 * 
	 * @param corteDeCaja
	 *            Informacion del corte de caja.
	 * @param idBoutique
	 *            Identificador de sucursal
	 * @param idTerminal
	 *            Identificador de terminal
	 * @param idUsuario
	 *            Identificador de usiario
	 * @return true si el registro se realizao correctamente, falso sino.
	 */
	public static boolean registrarCorte(CorteDeCaja corteDeCaja,
			int idBoutique, int idTerminal, int idUsuario) {
		Connection con = DaoSource.getConnection();

		try {
			con.setAutoCommit(false);
			String sql = "INSERT INTO cortes (fecha,fondoFijo,efectivo,tarjeta,cheque,vale,transferencias,salidas,total,retiro,valesEmitidos,montoValesEmitidos,creditosSinAbonos,idBoutique,idTerminal,idUsuario)"
					+ "VALUES (NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1, corteDeCaja.getFondoFijoEnCaja());
			pst.setDouble(2, corteDeCaja.getTotalEfectivo());
			pst.setDouble(3, corteDeCaja.getTotalTarjeta());
			pst.setDouble(4, corteDeCaja.getTotalCheque());
			pst.setDouble(5, corteDeCaja.getTotalVales());
			pst.setDouble(6, corteDeCaja.getTotalTransferencia());
			pst.setDouble(7, corteDeCaja.getSalidas());
			pst.setDouble(8, corteDeCaja.getTotalEnCaja());
			pst.setDouble(
					9,
					corteDeCaja.getTotalEnCaja()
							- corteDeCaja.getFondoFijoEnCaja());
			pst.setInt(10, corteDeCaja.getValesEmitidos());
			pst.setDouble(11, corteDeCaja.getMontoValesEmitidos());
			pst.setDouble(12, corteDeCaja.getCreditos());
			pst.setInt(13, idBoutique);
			pst.setInt(14, idTerminal);
			pst.setInt(15, idUsuario);
			pst.execute();

			/*
			 * PONEMOS TODOS LOS PAGOS DE ESTA SUCURSAL QUE NO ESTAN EN CORTE,
			 * EN CORTE, PARA ESTO SACAMOS EL ID DEL ULTIMO CORTE REALIZADO
			 */
			sql = "SELECT MAX(id) as idCorte FROM cortes ORDER BY id  DESC;";

			int idCorte = 0;
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				idCorte = rs.getInt("idCorte");
			}
			rs.close();
			/*
			 * PONEMOS TODOS LOS PAGOS Y TODAS LAS VENTAS DE ESTA SUCURSAL EN
			 * CORTE
			 */

			sql = "UPDATE pagos SET pagos.enCorte=" + idCorte
					+ " WHERE pagos.idSucursal=" + idBoutique
					+ " AND pagos.idTerminal=" + idTerminal
					+ " AND pagos.enCorte=0;";

			con.createStatement().execute(sql);

			sql = "UPDATE ventas SET ventas.enCorte=" + idCorte
					+ " WHERE ventas.idBoutique=" + idBoutique
					+ " AND ventas.idTerminal=" + idTerminal
					+ " AND ventas.enCorte=0;";

			con.createStatement().execute(sql);

			sql = "UPDATE salidas_dinero_terminal SET encorte=" + idCorte
					+ " WHERE idSucursal=" + idBoutique + " AND idTerminal="
					+ idTerminal + " AND enCorte=0;";

			con.createStatement().execute(sql);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException ex) {

			System.out.println(ex.toString());
			return false;
		}
		return true;

	}

	public static List<Object[]> getDiarioVentasContado(int idBoutique,
			int idTerminal) {
		totalDiarioVentasDeContado = 0;
		List<Object[]> diario = new ArrayList<Object[]>();
		Object[] row = new Object[4];

		Connection con = DaoSource.getConnectionLocal();
		String sql = "select id,status,fecha from ventas where fecha>=DATE(NOW()) AND fecha<=NOW() and tipo=0 and idBoutique="
				+ idBoutique
				+ " AND idTerminal="
				+ idTerminal
				+ " ORDER BY fecha;";
		ResultSet rs = null;
		ResultSet rsProc = null;
		java.sql.CallableStatement cst = null;
		try {
			cst = con.prepareCall("call obtenerDatosVentaContado(?)");
			rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				row = new Object[5];
				row[0] = rs.getInt("id");
				row[1] = AppInstance.formatoLargo.format(rs
						.getTimestamp("fecha"));
				cst.setInt(1, rs.getInt("id"));
				rsProc = cst.executeQuery();
				if (rsProc.next()) {
					row[2] = rsProc.getString("PRODUCTOS");
					row[3] = rsProc.getString("MEDIOS_DE_PAGO");
					row[4] = AppInstance.number.format(rsProc
							.getDouble("MONTO_PAGADO"));
				}
				totalDiarioVentasDeContado += rsProc.getDouble("MONTO_PAGADO");
				rsProc.close();
				diario.add(row);
				// POR CADA NOTA MANDAMOS LLAMAR NUESTRO PROCEDIMIENTO
				// ALMACENADO
				// ID DE LA VENTA
				// MONTO Y TIPO DE PAGO
				// DEVOLVEMOS ESA LISTA
			}
			rs.close();
			cst.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return diario;
	}

	public static List<Object[]> getDiarioVentasContado(int idBoutique,
			java.util.Date fecha) {
		totalDiarioVentasDeContado = 0;
		List<Object[]> diario = new ArrayList<Object[]>();
		Object[] row = new Object[4];
		java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		Connection con = DaoSource.getConnectionLocal();
		String sql = "select id,status,fecha from ventas where fecha>='"
				+ ff.format(fecha) + "T00:00:00' AND fecha<='"
				+ ff.format(fecha) + "T23:59:59' and tipo=0 and idBoutique="
				+ idBoutique + " ORDER BY fecha;";
		ResultSet rs = null;
		ResultSet rsProc = null;

		java.sql.CallableStatement cst = null;
		try {
			cst = con.prepareCall("call obtenerDatosVentaContado(?)");
			rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				row = new Object[5];
				row[0] = rs.getInt("id");
				row[1] = AppInstance.formatoLargo.format(rs
						.getTimestamp("fecha"));
				cst.setInt(1, rs.getInt("id"));
				rsProc = cst.executeQuery();
				if (rsProc.next()) {
					row[2] = rsProc.getString("PRODUCTOS");
					row[3] = rsProc.getString("MEDIOS_DE_PAGO");
					row[4] = AppInstance.number.format(rsProc
							.getDouble("MONTO_PAGADO"));
				}
				totalDiarioVentasDeContado += rsProc.getDouble("MONTO_PAGADO");
				rsProc.close();
				diario.add(row);
				// POR CADA NOTA MANDAMOS LLAMAR NUESTRO PROCEDIMIENTO
				// ALMACENADO
				// ID DE LA VENTA
				// MONTO Y TIPO DE PAGO
				// DEVOLVEMOS ESA LISTA
			}
			rs.close();
			cst.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return diario;
	}

	public static double getDineroRecibido() {
		// BUSCAMOS EL ULTIMO CORTE DE CAJA DE ESTA BOUTIQUE
		// SI HAY CORTE PONEMOS LA DIFERENCIA ENTRE EL TOTAL EN CAJA - EL DINERO
		// RETIRADO

		// SI NO HAY CORTE DE CAJA PUES PONEMOS CANTIDAD CERO
		// YA TENEMOS EL DINERO RECIBIDO EN CAJA AHORA,

		return 0;
	}
	/*
	 * public static List getDiarioDeEntradas(int idBoutique){ List diario = new
	 * ArrayList(); Object[] row = new Object[4];
	 * 
	 * Connection con = DaoSource.getConnectionLocal(); String sql=
	 * "select id,status,fecha from ventas where fecha>=DATE(NOW()) AND fecha<=NOW() and tipo=0 and idBoutique="
	 * + idBoutique + " ORDER BY fecha;"; ResultSet rs=null; rs
	 * =con.createStatement().executeQuery(sql); while(rs.next()){ //Revisamos
	 * el tipo } //Tenemos las notas.. aqui hacemos el diario de entradas
	 * 
	 * //SACAMOS LAS VENTAS DEL DIA DE HOY DE CONTADO //POR CADA NOTA //SACAMOS
	 * LOS PRODUCTOS Y LOS PAGOS // PONEMOS LE NUMERO DE NOTA //VERIFICAMOS LA
	 * CANTIDAD DE PRODUCTOS VENDIDOS //SI HAY MAS DE UN PRODUCTO PONEMOS
	 * "VARIOS" //SI HAY UN SOLO PRODUCTO PONEMOS EL NOMBRE DEL TIPO DEL
	 * PRODUCTO //PONEMOS LAS FORMAS DE PAGO ANTES DEL TOTAL Y LA SUMA DE LOS
	 * PAGOS EN EL TOTAL //SI HAY MAS DE UN PRODUCTO PONEMOS VARIOS Y EL TOTAL
	 * DE LA SUMA DEL PRECIO FINAL DEL COSTO DE LOS PRODUCTOS //SI HAY UN SOLO
	 * PRODUCTO PONEMOS EL NOMBRE DEL TIPO DE PRODUCTO Y EL COSTO DEL PRECIO
	 * FINAL DEL PRODUCTO // }
	 */
}
