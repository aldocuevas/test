package com.boutique.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.boutique.domain.Preferencias;


public class DaoPreferencias {
	public static Preferencias findPreferencias() {
		Connection con = DaoSource.getConnection();
		Preferencias preferencias = null;
		java.sql.ResultSet rs;
		try {
			rs = con.createStatement().executeQuery(
					"SELECT * FROM preferencias;");
			if (rs.next()) {
				preferencias = new Preferencias();
				preferencias.setDiasDevolucionProductos(rs
						.getInt("devDiasPermitidos"));
				preferencias.setPagarCreditoConTC(rs.getBoolean("pagarCreditoConTC"));
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preferencias;
	}
}
