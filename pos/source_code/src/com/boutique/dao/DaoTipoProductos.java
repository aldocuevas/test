package com.boutique.dao;

import java.sql.*;
import java.util.*;

import com.boutique.domain.*;
import com.boutique.domain.inventarios.Unidad;

public class DaoTipoProductos {

	/**
	 * add
	 * 
	 * @param a
	 *            Object
	 * @return boolean
	 */
	public static boolean add(TipoProducto p) {
		String sql = "INSERT INTO `tipoproductos` (`nombre` , `tipoTalla` )VALUES (?,?);";
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getTipoTalla());
			ps.execute();
			ps.close();
			// con.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return false;
		}

	}

	/**
	 * getList
	 * 
	 * @return ArrayList
	 */
	public static List<TipoProducto> findAll() {
		List<TipoProducto> l = new ArrayList<TipoProducto>();
		String sql = "SELECT * from tipoproductos ORDER BY nombre;";
		Connection con = DaoSource.getConnection();
		TipoProducto p = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new TipoProducto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setTipoTalla(rs.getString("tipoTalla"));
				findUnidadesByTipoProducto(p);
 				l.add(p);
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return l;

	}

	public static void findUnidadesByTipoProducto(TipoProducto tipoProducto) {
		List<Unidad> unidades = new ArrayList<Unidad>();
		String sql = "SELECT * from inventarios_unidades WHERE idTipoProducto="
				+ tipoProducto.getId();
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs;
		try {
			rs = con.createStatement().executeQuery(sql);

			while (rs.next()) {
				Unidad unidad = new Unidad();
				unidad.setId(rs.getInt("id"));
				unidad.setDescripcion(rs.getString("nombreUnidad"));

				findCantidadesPorTallas(unidad);
				unidades.add(unidad);
			}
			rs.close();
			tipoProducto.setUnidades(unidades);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param unidad
	 * @return
	 */
	public static void findCantidadesPorTallas(Unidad unidad) {
		Map<String, Integer> result = new TreeMap<String, Integer>();
		String sql = "SELECT * from inventarios_tallas_unidad WHERE idInventarioUnidad ="
				+ unidad.getId() + " ORDER BY sort_priority";

		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs;
		try {
			rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				result.put(rs.getString("talla"), rs.getInt("cantidad"));
			}
			unidad.setCantidadesPorTalla(result);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<TipoProducto> findAllLocal() {
		List<TipoProducto> l = new ArrayList<TipoProducto>();
		String sql = "SELECT * from tipoproductos ORDER BY nombre;";
		Connection con = DaoSource.getConnectionLocal();
		TipoProducto p = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new TipoProducto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setTipoTalla(rs.getString("tipoTalla"));
				l.add(p);
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}

		return l;
	}

	/**
	 * remove
	 * 
	 * @param a
	 *            Object
	 * @return boolean
	 */
	public static boolean remove(TipoProducto p) {
		String sql = "DELETE FROM tipoproductos WHERE id=?";
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.execute();
			ps.close();
			// con.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return false;
		}

	}

	/**
	 * update
	 * 
	 * @param a
	 *            Object
	 * @return boolean
	 */
	public static boolean update(TipoProducto p) {

		String sql = "UPDATE `tipoproductos` SET `nombre` = ?,`tipoTalla` = ? WHERE `id` = ?;";
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getTipoTalla());
			ps.setInt(3, p.getId());
			ps.execute();
			ps.close();
			// con.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return false;
		}

	}

	public static String getNombreFromId(String nombreArticulo) { /*
																 * String id =
																 * new String();
																 * this.sql =
																 * new String(
																 * "SELECT id FROM articulos WHERE nombreArticulo='"
																 * +
																 * nombreArticulo
																 * + "';");
																 * 
																 * try { rs =
																 * conn
																 * .createStatement
																 * (
																 * ).executeQuery
																 * (this.sql);
																 * if
																 * (rs.next()) {
																 * id =
																 * rs.getString
																 * ("id"); }
																 * rs.close(); }
																 * catch
																 * (SQLException
																 * ex) {
																 * System.out
																 * .println
																 * (ex.toString
																 * ()); } return
																 * id;
																 */
		return null;
	}

	/*
	 * public String getIdFromNombre(String nombreArticulo) { String id = new
	 * String(); this.sql = new
	 * String("SELECT id FROM articulos WHERE nombreArticulo='" + nombreArticulo
	 * + "';");
	 * 
	 * try { rs = conn.createStatement().executeQuery(this.sql); if (rs.next())
	 * { id = rs.getString("id"); } rs.close(); } catch (SQLException ex) {
	 * System.out.println(ex.toString()); } return id; }
	 * 
	 * /** getNombreFromId
	 * 
	 * @param id String
	 * 
	 * @return Object
	 */
	public static String findTipoTallaFromId(int id) {
		String tipoTalla = null;
		String sql = "SELECT tipoTalla FROM tipoproductos WHERE id=?;";
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				tipoTalla = rs.getString("tipoTalla");
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return tipoTalla;
	}

	public static double findPrecio(String etiqueta) {
		double precio = 0.0;
		String sql = "SELECT precioPublico FROM inventarioLocal WHERE etiqueta=?";
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, etiqueta);
			rs = ps.executeQuery();
			if (rs.next()) {
				precio = rs.getDouble("precioPublico");
			}
			rs.close();
			ps.close();
			// return precio;
		} catch (SQLException ex) {
			System.err.println(ex.toString());
		}
		return precio;
	}

	/**
	 * getMap
	 * 
	 * @return Map
	 */
	public static Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT id,nombre FROM tipoproductos ORDER BY nombre;";
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("id"), rs.getString("nombre"));
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return map;

	}

	public static Map<String, String> getMapLocal() {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT id,nombre FROM tipoproductos ORDER BY nombre;";
		Connection con = DaoSource.getConnectionLocal();
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("id"), rs.getString("nombre"));
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return map;

	}

	/**
	 * findById
	 * 
	 * @param i
	 *            int
	 * @return TipoProducto
	 */
	public static TipoProducto findById(int id) {
		TipoProducto tp = null;
		String sql = "SELECT * FROM tipoproductos WHERE id=?";
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				tp = new TipoProducto();
				tp.setId(rs.getInt("id"));
				tp.setNombre(rs.getString("nombre"));
				tp.setTipoTalla(rs.getString("tipoTalla"));
			}
			rs.close();
			ps.close();
			// return precio;
		} catch (SQLException ex) {
			System.err.println(ex.toString());
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
			} catch (SQLException e) {
			}
		}
		return tp;

	}
}
