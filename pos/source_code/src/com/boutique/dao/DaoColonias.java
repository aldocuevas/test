package com.boutique.dao;

import com.boutique.domain.*;
import java.util.*;
import java.sql.*;

public class DaoColonias {
	public DaoColonias() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * add
	 * 
	 * @return boolean
	 */
	public static boolean add(Colonia c) {
		String sql = "INSERT INTO `colonias` (`nombre`,`cp`,`ciudad`,`estado`,municipio,ruta) VALUES (?,?,?,?,?,?)";

		Connection con = DaoSource.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, c.getNombre());
			ps.setString(2, c.getCp());
			ps.setString(3, c.getCiudad());
			ps.setString(4, c.getEstado());
			ps.setString(5, c.getMunicipio());
			ps.setInt(6, c.getRuta());
			ps.execute();
			ps.close();
			// con.close();

		} catch (SQLException ex) {
			System.out.println("DaoClientes.add(Cliente c): " + ex.toString());
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
			return false;
		}
		return true;

	}

	public static boolean remove(int id) {
		String sql = "DELETE FROM `colonias` WHERE id=?";

		Connection con = DaoSource.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1, id);
			ps.execute();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println("DaoColonias.remove(int id): " + ex.toString());
			ex.printStackTrace();
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
			return false;
		}
		return true;

	}

	public static boolean update(Colonia c) {
		String sql = "UPDATE `colonias` SET nombre=?, cp=?, ciudad=?, estado=?, municipio=?, ruta=? WHERE id=?";

		Connection con = DaoSource.getConnection();
		java.sql.PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getCp());
			ps.setString(3, c.getCiudad());
			ps.setString(4, c.getEstado());
			ps.setString(5, c.getMunicipio());
			ps.setInt(6, c.getRuta());
			ps.setInt(7, c.getId());
			ps.execute();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println("DaoColonias.update(Colonia c): "
					+ ex.toString());
			ex.printStackTrace();
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			}
			return false;
		}
		return true;

	}

	public static Colonia findById(int id) {
		Connection con = DaoSource.getConnection();
		String sql = "SELECT * FROM colonias WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Colonia c = null;
		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Colonia();
				c.setId(rs.getInt("id"));
				c.setCiudad(rs.getString("ciudad"));
				c.setCp(rs.getString("cp"));
				c.setEstado(rs.getString("estado"));
				c.setNombre(rs.getString("nombre"));
				c.setMunicipio(rs.getString("municipio"));
				c.setRuta(rs.getInt("ruta"));
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}

		}
		return c;

	}

	public static int findRutaByNombre(String nombre) {
		Connection con = DaoSource.getConnection();
		String sql = "SELECT ruta FROM colonias WHERE nombre=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int ruta = 0;
		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();

			if (rs.next()) {
				ruta = rs.getInt("ruta");
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}

		}
		return ruta;

	}

	public static List<Colonia> findAll() {
		List<Colonia> lista = new ArrayList<Colonia>();
		Connection con = DaoSource.getConnection();
		String sql = "SELECT * FROM colonias ORDER BY nombre";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Colonia c = new Colonia();
				c.setId(rs.getInt("id"));
				c.setCiudad(rs.getString("ciudad"));
				c.setCp(rs.getString("cp"));
				c.setEstado(rs.getString("estado"));
				c.setNombre(rs.getString("nombre"));
				c.setMunicipio(rs.getString("municipio"));
				c.setRuta(rs.getInt("ruta"));
				lista.add(c);
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}

		}
		return lista;

	}

	public static Colonia findByNombreUnico(String nombre) {

		Colonia c = null;
		Connection con = DaoSource.getConnection();
		String sql = "SELECT * FROM colonias WHERE nombre = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if (rs.next()) {
				c = new Colonia();
				c.setId(rs.getInt("id"));
				c.setCiudad(rs.getString("ciudad"));
				c.setCp(rs.getString("cp"));
				c.setEstado(rs.getString("estado"));
				c.setNombre(rs.getString("nombre"));
				c.setMunicipio(rs.getString("municipio"));
				c.setRuta(rs.getInt("ruta"));
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}

		}
		return c;
	}

	public static List<Colonia> findByNombre(String nombre) {
		List<Colonia> list = new ArrayList<Colonia>();
		Connection con = DaoSource.getConnection();
		String sql = "SELECT * FROM colonias WHERE nombre LIKE ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + nombre + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Colonia c = new Colonia();
				c.setId(rs.getInt("id"));
				c.setCiudad(rs.getString("ciudad"));
				c.setCp(rs.getString("cp"));
				c.setEstado(rs.getString("estado"));
				c.setNombre(rs.getString("nombre"));
				c.setMunicipio(rs.getString("municipio"));
				c.setRuta(rs.getInt("ruta"));
				list.add(c);
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}

		}
		return list;
	}

	private void jbInit() throws Exception {
	}

}
