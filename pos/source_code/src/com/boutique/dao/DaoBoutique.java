package com.boutique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boutique.domain.Boutique;
import com.boutique.domain.Terminal;
import com.boutique.domain.Usuario;

public class DaoBoutique {
	public DaoBoutique() {
	}

	static Boutique b = null;
	static Boutique boutiqueLocal = null;

	public static void getMatrizInicial() {

		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boutiques WHERE matriz='1'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				b = new Boutique();
				b.setDescripcion(rs.getString("descripcion"));
				b.setNombre(rs.getString("nombre"));
				b.setIp(rs.getString("ip"));
				b.setId(rs.getInt("id"));
				b.setMatriz(rs.getInt("matriz"));

			}
			rs.close();
			ps.close();
			sql = "SELECT * FROM boutiques WHERE local='1'";
			rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				boutiqueLocal = new Boutique();
				boutiqueLocal.setDescripcion(rs.getString("descripcion"));
				boutiqueLocal.setNombre(rs.getString("nombre"));
				boutiqueLocal.setIp(rs.getString("ip"));
				boutiqueLocal.setId(rs.getInt("id"));
				boutiqueLocal.setMatriz(rs.getInt("matriz"));
				boutiqueLocal.setLocal(rs.getString("local"));
			}
			// con.close();

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
			}

		}

	}

	public static List<Boutique> findAllActivas() {
		List<Boutique> list = new ArrayList<Boutique>();
		Boutique b;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boutiques WHERE activa=1 ORDER BY nombre";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				b = new Boutique();
				b.setDescripcion(rs.getString("descripcion"));
				b.setNombre(rs.getString("nombre"));
				b.setIp(rs.getString("ip"));
				b.setId(rs.getInt("id"));
				list.add(b);
			}
			rs.close();
			ps.close();
			// con.close();
			return list;
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
			}
			return null;
		}

	}

	public static List<Boutique> findAll() {
		List<Boutique> list = new ArrayList<Boutique>();
		Boutique b;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boutiques ORDER BY nombre";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				b = new Boutique();
				b.setDescripcion(rs.getString("descripcion"));
				b.setNombre(rs.getString("nombre"));
				b.setIp(rs.getString("ip"));
				b.setId(rs.getInt("id"));
				list.add(b);
			}
			rs.close();
			ps.close();
			// con.close();
			return list;
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
			}
			return null;
		}

	}

	public static List<Boutique> findBoutiquesRemotas(int idBoutique) {
		List<Boutique> list = new ArrayList<Boutique>();
		Boutique bou;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boutiques where id<>" + idBoutique;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bou = new Boutique();
				bou.setDescripcion(rs.getString("descripcion"));
				bou.setNombre(rs.getString("nombre"));
				bou.setIp(rs.getString("ip"));
				bou.setId(rs.getInt("id"));
				list.add(bou);
			}
			rs.close();
			ps.close();

			return list;
		} catch (SQLException ex) {
			try {

				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex1) {
			}
			return null;
		}

	}

	public static void add(Boutique b) {
		PreparedStatement ps = null;
		Connection con = DaoSource.getConnection();
		String sql = "INSERT INTO boutiques (nombre,descripcion,ip,matriz) VALUES (?,?,?,?);";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, b.getNombre());
			ps.setString(2, b.getDescripcion());
			ps.setString(3, b.getIp());
			ps.setInt(4, b.getMatriz());
			ps.execute();
			ps.close();
			// con.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex1) {
			}
		}

	}

	public static int getMatriz() {
		PreparedStatement ps = null;
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		int matriz = -1;
		String sql = "SELECT id FROM boutiques WHERE matriz='1'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				matriz = rs.getInt("id");
			} else {
				matriz = -1;
			}
			rs.close();
			ps.close();
			// con.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException ex1) {
			}

		}
		return matriz;
	}

	/**
	 * findSucursales
	 * 
	 * @return List
	 */
	public static List<Boutique> findSucursales() {
		List<Boutique> list = new ArrayList<Boutique>();
		Boutique b;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM boutiques WHERE matriz='0' ORDER BY nombre";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				b = new Boutique();
				b.setDescripcion(rs.getString("descripcion"));
				b.setNombre(rs.getString("nombre"));
				b.setIp(rs.getString("ip"));
				b.setId(rs.getInt("id"));
				list.add(b);
			}
			rs.close();
			ps.close();
			// con.close();
			return list;
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
			}
			return null;
		}

	}

	/**
	 * findLocal
	 * 
	 * @return Boutique
	 */
	public static Boutique findById(int idBoutique) {
		Connection con = DaoSource.getConnection();
		ResultSet rs = null;
		String sql = "SELECT * FROM boutiques WHERE id=" + idBoutique;
		try {
			rs = con.createStatement().executeQuery(sql);

			if (rs.next()) {
				boutiqueLocal = new Boutique();
				boutiqueLocal.setDescripcion(rs.getString("descripcion"));
				boutiqueLocal.setNombre(rs.getString("nombre"));
				boutiqueLocal.setIp(rs.getString("ip"));
				boutiqueLocal.setId(rs.getInt("id"));
				boutiqueLocal.setMatriz(rs.getInt("matriz"));
				boutiqueLocal.setLocal(rs.getString("local"));
				boutiqueLocal.setNombrePropietario(rs
						.getString("nombrePropietario"));
				boutiqueLocal.setRFC(rs.getString("RFC"));
				boutiqueLocal.setCalle(rs.getString("calle"));
				boutiqueLocal.setNumero(rs.getString("numero"));
				boutiqueLocal.setCiudad(rs.getString("ciudad"));
				boutiqueLocal.setColonia(rs.getString("colonia"));
				boutiqueLocal.setCp(rs.getString("cp"));
				boutiqueLocal.setRfc(rs.getString("rfc"));
				boutiqueLocal.setCurp(rs.getString("curp"));
				boutiqueLocal.setTelefono(rs.getString("telefono"));
				boutiqueLocal.setLugarExpedicion(rs
						.getString("lugarExpedicion"));
				boutiqueLocal.setLugarExpedicionPagare(rs
						.getString("lugarExpedicionPagare"));
				boutiqueLocal.setAnticipoApartadoLibre(rs
						.getBoolean("apartadoLibre"));
				boutiqueLocal.setMostrarFoto(rs.getBoolean("mostrarFoto"));
				boutiqueLocal.setHFoto(rs.getInt("hFoto"));
				boutiqueLocal.setWFoto(rs.getInt("wFoto"));
				boutiqueLocal.setFondoFijoEnCaja(rs.getDouble("fondoFijoEnCaja"));
			}
			rs.close();

		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return boutiqueLocal;
	}

	/**
	 * getMapLocal
	 * 
	 * @return Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map[] getMapLocal() {
		Map[] mapas = new Map[2];
		Map idToNombre = new HashMap();
		Map nombreToId = new HashMap();
		List<Boutique> boutiques = findAll();
		for (Boutique b : boutiques) {
			idToNombre.put(b.getId(), b.getNombre());
			nombreToId.put(b.getNombre(), b.getId());
		}
		mapas[0] = idToNombre;
		mapas[1] = nombreToId;
		return mapas;
	}

	public static Usuario findUsuarioByUsuarioAndContrasena(String usuario,
			String contrasena) {
		String sql = "SELECT * FROM usuarios WHERE usuario='" + usuario
				+ "' AND pass='" + contrasena + "';";
		Connection con = DaoSource.getConnection();
		Usuario u = null;
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			if (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setCortedecaja(rs.getInt("cortedecaja")); // SI EL USUARIO
															// PUEDE O NO HACER
															// CORTE DE CAJA
				u.setNombre(rs.getString("nombre"));
				u.setReporteador(rs.getInt("reporteador"));
				u.setPass(rs.getString("pass"));
				u.setUsuario(rs.getString("usuario"));
				u.setVendedor(rs.getInt("vendedor"));
				u.setAdmin(rs.getInt("admin"));
				u.setInventarios(rs.getInt("inventarios"));
				u.setEliminarPagos(rs.getInt("eliminarPagos"));
				u.setActivo(rs.getInt("activo"));
				u.setAdminUsuarios(rs.getInt("adminUsuarios"));
				u.setAuditor(rs.getInt("auditor"));
				u.setExportar(rs.getInt("exportar"));
				u.setDevolucionesIlimitadas(rs.getInt("devolucionesIlimitadas"));
				u.setModificarCantidadInventarios(rs.getInt("modificarCantidadInventarios"));
			}
			rs.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
		}
		return u;
	}

	public static Map<Integer, String> getNombresUsuarios() {
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			String sql = "SELECT id,nombre FROM usuarios";
			Connection con = DaoSource.getConnection();
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				map.put(rs.getInt("id"), rs.getString("nombre"));
			}
			rs.close();
			return map;
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			return null;
		}

	}

	public static List<Terminal> findAllTerminalesActivasByIdBoutique(int id) {
		List<Terminal> list = new ArrayList<Terminal>();
		Terminal terminal;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM terminales WHERE activa=1 AND legacy=0 AND idBoutique=" + id + " ORDER BY noTerminal";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				terminal = new Terminal();
				terminal.setId(rs.getInt("id"));
				terminal.setIdBoutique(rs.getInt("idBoutique"));
				terminal.setDescription(rs.getString("descripcion"));
				terminal.setNoTerminal(rs.getString("noTerminal"));
				terminal.setLegacy(rs.getBoolean("legacy"));
				terminal.setActiva(rs.getBoolean("activa"));				 
 				list.add(terminal);
			}
			rs.close();
			ps.close();
			// con.close();
			return list;
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
			}
			return null;
		}
	}

	public static Terminal findTerminalById(int idTerminal) {
		Terminal terminal = null;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM terminales WHERE id=" + idTerminal;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				terminal = new Terminal();
				terminal.setId(rs.getInt("id"));
				terminal.setIdBoutique(rs.getInt("idBoutique"));
				terminal.setDescription(rs.getString("descripcion"));
				terminal.setNoTerminal(rs.getString("noTerminal"));
				terminal.setLegacy(rs.getBoolean("legacy"));
				terminal.setActiva(rs.getBoolean("activa"));				 
 			}
			rs.close();
			ps.close();
			// con.close();
			return terminal;
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
			}
			return null;
		}
	}
	
	public static Terminal findLegacyTerminal() {
		Terminal terminal = null;
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM terminales WHERE legacy=1";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				terminal = new Terminal();
				terminal.setId(rs.getInt("id"));
				terminal.setIdBoutique(rs.getInt("idBoutique"));
				terminal.setDescription(rs.getString("descripcion"));
				terminal.setNoTerminal(rs.getString("noTerminal"));
				terminal.setLegacy(rs.getBoolean("legacy"));
				terminal.setActiva(rs.getBoolean("activa"));				 
 			}
			rs.close();
			ps.close();
			// con.close();
			return terminal;
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
			}
			return null;
		}
	}
}
