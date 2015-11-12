package com.boutique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.boutique.domain.PersonaMoral;
import com.boutique.reportes.facturacion.dto.DomicilioDTO;

public class DaoPersonalMoral {

	public static PersonaMoral findPersonaMoral() {
		PersonaMoral p = new PersonaMoral();
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ref_persona_moral";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				p.setNombre(rs.getString("nombre"));
				p.setRfc(rs.getString("rfc"));
				DomicilioDTO dom = new DomicilioDTO();
				dom.setCalle(rs.getString("calle"));
				dom.setNumeroExterior(rs.getString("numero"));
				dom.setColonia(rs.getString("colonia"));
				dom.setCodigoPostal(rs.getString("cp"));
				dom.setCiudad(rs.getString("lugar"));
				p.setLeyenda(rs.getString("leyenda"));
				p.setDomicilio(dom);

			}
			rs.close();
			ps.close();
			// con.close();
			return p;
		} catch (SQLException ex) {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			return null;
		}

	}
}
