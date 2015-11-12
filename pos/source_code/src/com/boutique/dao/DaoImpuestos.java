package com.boutique.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.boutique.domain.Impuesto;

public class DaoImpuestos {
	public static Impuesto findIva() {
		Connection con = DaoSource.getConnection();
		Impuesto impuesto = null;
		java.sql.ResultSet rs;
		try {
			rs = con.createStatement().executeQuery(
					"SELECT * FROM ref_impuestos WHERE cd='IVA';");
			if (rs.next()) {
				impuesto = new Impuesto();
				impuesto.setPorcentaje(rs.getInt("porcentaje"));
			}
			rs.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return impuesto;
	}
}
