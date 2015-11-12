package com.boutique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.boutique.domain.SalidaCaja;

public class DaoSalidas {
	public static boolean registrarSalida(SalidaCaja salida, int idSucursal,
			int idTerminal, int idUsuario) {

		String sql = "INSERT INTO salidas_dinero_terminal (concepto,monto,idSucursal,idTerminal,idUsuario,enCorte,fecha) VALUES(?,?,?,?,?,0,NOW())";
		Connection con = DaoSource.getConnectionLocal();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, salida.getConcepto());
			pst.setDouble(2, salida.getMonto());
			pst.setInt(3, idSucursal);
			pst.setInt(4, idTerminal);
			pst.setInt(5, idUsuario);
			pst.execute();
			pst.close();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static boolean eliminarSalida(int id) {
		String sql = "delete from salidas_dinero_terminal WHERE id=" + id;
		Connection con = DaoSource.getConnectionLocal();
		try {
			con.createStatement().execute(sql);
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static List<SalidaCaja> findSalidasCajaByidTerminalEnCorte(
			int idTerminal) {
		List<SalidaCaja> salidas = new ArrayList<SalidaCaja>();
		String sql = "SELECT * from salidas_dinero_terminal WHERE idTerminal="
				+ idTerminal + " and enCorte=0";
		Connection con = DaoSource.getConnectionLocal();
		SalidaCaja s = null;
		ResultSet rs = null;
		try {
			rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				s = new SalidaCaja();
				s.setId(rs.getInt("id"));
				s.setMonto(rs.getDouble("monto"));
				s.setConcepto(rs.getString("concepto"));
				salidas.add(s);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return salidas;
	}
}
