package com.boutique.dao;

import java.sql.*;
import java.util.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;

public class DaoInventarios {
	public DaoInventarios() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Producto findByEtiquetaAndIdBoutique(String etiqueta,
			int idBoutique, int idProducto, int cantidadUsados) {
		Connection con = DaoSource.getConnectionLocal();
		PreparedStatement ps = null;
		Producto p = null;
		ResultSet rs = null;
		String sql;
		try {
			if (idProducto == -1) {
				sql = "SELECT inventarios.*,inventarios_md.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario  WHERE etiqueta = ? AND cantidad>0 and idBoutique=? AND  productoDistribuido='1' ORDER BY inventarios.id LIMIT 0,1";
				ps = con.prepareStatement(sql);
				ps.setString(1, etiqueta);
				ps.setInt(2, idBoutique);
				rs = ps.executeQuery();
				if (rs.next()) {
					p = new Producto();
					p.setCantidad(rs.getInt("cantidad"));
					p.setClave(rs.getInt("clave"));
					p.setEtiqueta(rs.getString("etiqueta"));
					p.setId(rs.getInt("id"));
					p.setIdBoutique(rs.getInt("idBoutique"));
					p.setIdCompra(rs.getInt("idCompra"));
					p.setIdTipoProducto(rs.getInt("idTipoProducto"));
					p.setModelo(rs.getInt("modelo"));
					p.setPrecioCosto(rs.getDouble("precioCosto"));
					p.setImpuestoPrecioPublicoFinal(rs.getDouble("impuestoPrecioPublicoFinal"));
					p.setPrecioPublico(rs.getDouble("precioPublico"));
					p.setTalla(rs.getString("talla"));
					p.setEstilo(rs.getString("estilo"));
					p.setIdMarca(rs.getInt("idMarca"));
					p.setIdDescripcion(rs.getInt("idDescripcion"));
					p.setIdColor(rs.getInt("idColor"));
				}
				rs.close();
				ps.close();

			} else { // YA EXISTE EL ARTICULO REVISAMOS SI TODAVIA DEL MISMO
						// REGISTRO PODEMOS SACAR MAS ARTICULOS
				sql = "SELECT inventarios.*,inventarios_md.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario WHERE etiqueta = ? AND cantidad>? and idBoutique=? AND inventarios.id=? AND productoDistribuido='1' ORDER BY inventarios.id LIMIT 0,1";
				ps = con.prepareStatement(sql);
				ps.setString(1, etiqueta);
				ps.setInt(2, cantidadUsados);
				ps.setInt(3, idBoutique);
				ps.setInt(4, idProducto);
				rs = ps.executeQuery();
				if (rs.next()) {
					p = new Producto();
					p.setCantidad(rs.getInt("cantidad"));
					p.setClave(rs.getInt("clave"));
					p.setEtiqueta(rs.getString("etiqueta"));
					p.setId(rs.getInt("id"));
					p.setIdBoutique(rs.getInt("idBoutique"));
					p.setIdCompra(rs.getInt("idCompra"));
					p.setIdTipoProducto(rs.getInt("idTipoProducto"));
					p.setModelo(rs.getInt("modelo"));
					p.setPrecioCosto(rs.getDouble("precioCosto"));
					p.setImpuestoPrecioPublicoFinal(rs.getDouble("impuestoPrecioPublicoFinal"));
					p.setPrecioPublico(rs.getDouble("precioPublico"));
					p.setTalla(rs.getString("talla"));
					p.setEstilo(rs.getString("estilo"));
					p.setIdMarca(rs.getInt("idMarca"));
					p.setIdDescripcion(rs.getInt("idDescripcion"));
					p.setIdColor(rs.getInt("idColor"));

					rs.close();
					ps.close();
				} else {
					sql = "SELECT inventarios.*,inventarios_md.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario  WHERE etiqueta = ? AND cantidad>0 and idBoutique=? AND inventarios.id>? AND productoDistribuido='1' ORDER BY inventarios.id  LIMIT 0,1";
					rs.close();
					ps.close();
					ps = con.prepareStatement(sql);
					ps.setString(1, etiqueta);
					ps.setInt(2, idBoutique);
					ps.setInt(3, idProducto);
					rs = ps.executeQuery();
					if (rs.next()) {
						p = new Producto();
						p.setCantidad(rs.getInt("cantidad"));
						p.setClave(rs.getInt("clave"));
						p.setEtiqueta(rs.getString("etiqueta"));
						p.setId(rs.getInt("inventarios.id"));
						p.setIdBoutique(rs.getInt("idBoutique"));
						p.setIdCompra(rs.getInt("idCompra"));
						p.setIdTipoProducto(rs.getInt("idTipoProducto"));
						p.setModelo(rs.getInt("modelo"));
						p.setPrecioCosto(rs.getDouble("precioCosto"));
						p.setImpuestoPrecioPublicoFinal(rs.getDouble("impuestoPrecioPublicoFinal"));
						p.setPrecioPublico(rs.getDouble("precioPublico"));
						p.setTalla(rs.getString("talla"));
						p.setEstilo(rs.getString("estilo"));
						p.setIdMarca(rs.getInt("idMarca"));
						p.setIdDescripcion(rs.getInt("idDescripcion"));
						p.setIdColor(rs.getInt("idColor"));

					}
					ps.close();
					rs.close();
				}

			}

			// con.close();
		} catch (SQLException ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				// con.close();
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {

			}

		}
		return p;

	}

	/**
	 * Funcion que permite agregar productos al inventario local en el momento
	 * en que estos no se encuentran en el inventario local y necesitan
	 * venderse...
	 * 
	 * @param idTipoProducto
	 *            int
	 * @param monto
	 *            double
	 * @return String
	 */
	public static String agregarInventarioLocal(int idTipoProducto,
			double monto, int idBoutique) {
		String etiqueta = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int modelo;
		con = DaoSource.getConnectionLocal();
		try {
			st = con.createStatement();
			con.setAutoCommit(false);
			rs = st.executeQuery("SELECT modelo FROM inventarios ORDER BY modelo DESC LIMIT 0,1");
			if (rs.next()) {
				modelo = rs.getInt("modelo");
				modelo++;
				etiqueta = "0F" + modelo;
				String sql = "INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido) VALUES ("
						+ idTipoProducto
						+ ","
						+ modelo
						+ ",'U',"
						+ idBoutique
						+ ",1,0," + monto + ",0,1,'" + etiqueta + "','1');";
				con.createStatement().execute(sql);

			}
			con.commit();
			con.setAutoCommit(true);
			rs.close();
			// con.close();
		} catch (SQLException ex) {
			System.out.println(ex.toString());
			etiqueta = null;
		}
		// Sacamos el ultimo modelo
		// Agregamos 1 al ultimo modelo y 0F
		// Generamos el producto y lo agregamos con sus datos respectivos
		// Regresamos la etiqueta final
		return etiqueta;
	}

	/**
	 * findEstilos
	 */
	public static final List<MarcaDescripcion> findMarcas() {
		List<MarcaDescripcion> marcas = new ArrayList<MarcaDescripcion>();
		String sql = "SELECT * FROM inventarios_marcas ORDER BY marca";
		Connection con = DaoSource.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MarcaDescripcion md = new MarcaDescripcion();
				md.setId(rs.getInt("id"));
				md.setMarcaDescripcion(rs.getString("marca"));
				marcas.add(md);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return marcas;
	}

	/**
	 * findEstilos
	 */
	public static final List<MarcaDescripcion> findDescripciones() {
		List<MarcaDescripcion> marcas = new ArrayList<MarcaDescripcion>();
		String sql = "SELECT * FROM inventarios_descripcion ORDER BY descripcion";
		Connection con = DaoSource.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MarcaDescripcion md = new MarcaDescripcion();
				md.setId(rs.getInt("id"));
				md.setMarcaDescripcion(rs.getString("descripcion"));
				marcas.add(md);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
		}
		return marcas;
	}

	public static MarcaDescripcion addMarca(String marca) {
		MarcaDescripcion md = null;
		String sql = "INSERT INTO inventarios_marcas (marca) VALUES ('" + marca
				+ "')";
		Connection con = DaoSource.getConnection();
		try {
			con.createStatement().execute(sql);
			ResultSet rs2 = con.createStatement().executeQuery(
					"SELECT MAX(id) as id FROM inventarios_marcas");
			// GENERAMOS EL OBJETO MARCADESCRIPCION Y LO INSERTAMOS A LA LISTA.
			if (rs2.next()) {
				md = new MarcaDescripcion();
				md.setId(rs2.getInt("id"));
				md.setMarcaDescripcion(marca);
			}
			rs2.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return md;
	}

	public static MarcaDescripcion addDescripcion(String marca) {
		MarcaDescripcion md = null;
		String sql = "INSERT INTO inventarios_descripcion (descripcion) VALUES ('"
				+ marca + "')";
		Connection con = DaoSource.getConnection();
		try {
			con.createStatement().execute(sql);
			ResultSet rs2 = con.createStatement().executeQuery(
					"SELECT MAX(id) as id FROM inventarios_descripcion");
			// GENERAMOS EL OBJETO MARCADESCRIPCION Y LO INSERTAMOS A LA LISTA.
			if (rs2.next()) {
				md = new MarcaDescripcion();
				md.setId(rs2.getInt("id"));
				md.setMarcaDescripcion(marca);
			}
			rs2.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return md;
	}

	public static MarcaDescripcion addColor(String marca) {
		MarcaDescripcion md = null;
		String sql = "INSERT INTO inventarios_colores (color) VALUES ('"
				+ marca + "')";
		Connection con = DaoSource.getConnection();
		try {
			con.createStatement().execute(sql);
			ResultSet rs2 = con.createStatement().executeQuery(
					"SELECT MAX(id) as id FROM inventarios_colores");
			// GENERAMOS EL OBJETO MARCADESCRIPCION Y LO INSERTAMOS A LA LISTA.
			if (rs2.next()) {
				md = new MarcaDescripcion();
				md.setId(rs2.getInt("id"));
				md.setMarcaDescripcion(marca);
			}
			rs2.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return md;
	}

	public static final List<MarcaDescripcion> findColores() {
		List<MarcaDescripcion> marcas = new ArrayList<MarcaDescripcion>();
		String sql = "SELECT * FROM inventarios_colores ORDER BY color";
		Connection con = DaoSource.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MarcaDescripcion md = new MarcaDescripcion();
				md.setId(rs.getInt("id"));
				md.setMarcaDescripcion(rs.getString("color"));
				marcas.add(md);
			}
			rs.close();
			st.close();
		} catch (SQLException ex) {
		}
		return marcas;
	}

	public static boolean cambiarCantidad(String etiqueta, int cantidadInicial,
			int cantidadFinal, int idBoutique, int idUsuario, int idTerminal) {
		Connection con = DaoSource.getConnectionLocal();
		// PASO UNO MODIFICAR TODOS LOS REGISTROS DE ESA ETIQUETA CON IDBOUTIQUE
		// EN CERO
		try {
			con.setAutoCommit(false);
			con.createStatement().execute(
					"UPDATE inventarios SET cantidad=0 WHERE etiqueta='"
							+ etiqueta + "' AND idBoutique=" + idBoutique);
			ResultSet rst = con.createStatement().executeQuery(
					"SELECT MAX(id) as id FROM inventarios WHERE etiqueta='"
							+ etiqueta + "' AND idBoutique=" + idBoutique);
			rst.next();
			int idInventario = rst.getInt("id");
			rst.close();
			con.createStatement().execute(
					"UPDATE inventarios SET cantidad=" + cantidadFinal
							+ " WHERE  id=" + idInventario);
			PreparedStatement pst = con
					.prepareStatement("insert into `inventarios_registro_modificacion_cantidad`(`idUsuario`,`idInventario`,`idBoutique`,`cantidadInicial`,`cantidadNueva`,`fecha`,`idTerminal`) values ( ?,?,?,?,?,NOW(),?)");
			pst.setInt(1, idUsuario);
			pst.setInt(2, idInventario);
			pst.setInt(3, idBoutique);
			pst.setInt(4, cantidadInicial);
			pst.setInt(5, cantidadFinal);
			pst.setInt(6, idTerminal);
			pst.execute();
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException ex1) {
			}
			return false;
		}
		// HACER UN UPDATE DE UNO DE LOS REGISTROS CON LA NUEVA CANTIDAD DE
		// PRODUCTOS
		// GUARDAR EL REGISTRO DE MODIFICACION DE PRODUCTOS CON CANTIDAD
		// ANTERIOR Y CANTIDAD NUEVA.
		// GENERAR REPORTE DE PAGOS Y CANTIDADES MODIFICADAS

	}

	public static List<Object[]> findByEtiquetaAll(String etiqueta,
			double nuevoPrecio) {
		List<Object[]> lista = new ArrayList<Object[]>();
		// String sql =
		// "SELECT etiqueta,idTipoProducto,cantidad,idBoutique,precioPublico FROM inventarios where etiqueta=? and productoDistribuido='1'";
		String sql = "SELECT sum(cantidad) as cantidad,inventarios.id, etiqueta,idTipoProducto,idBoutique,precioPublico,estilo,inventarios_md.idMarca,inventarios_md.idDescripcion, inventarios_md.idColor FROM inventarios LEFT JOIN inventarios_md ON  inventarios.modelo=inventarios_md.idModeloInventario where etiqueta=? and productoDistribuido='1' group by idBoutique;";
		Connection con = DaoSource.getConnectionLocal();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, etiqueta);
			ResultSet rs = null;
			rs = pst.executeQuery();
			Object[] row = null;
			while (rs.next()) {
				row = new Object[7];
				row[0] = rs.getString("etiqueta");
				row[1] = AppInstance.idToTipoProducto.get(rs
						.getString("idTipoProducto"));
				row[2] = DaoInventarios.detalleProducto(rs.getString("estilo"),
						rs.getInt("idMarca"), rs.getInt("idDescripcion"),
						rs.getInt("idColor"));
				row[3] = rs.getInt("cantidad");
				row[4] = AppInstance.idToNombreBoutique.get(rs
						.getInt("idBoutique"));
				row[5] = AppInstance.number.format(rs
						.getDouble("precioPublico"));
				row[6] = AppInstance.number.format(nuevoPrecio);
				lista.add(row);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

	@SuppressWarnings("rawtypes")
	public static List[] findByEtiquetaAll(String etiqueta) {
		List[] listas = new List[2];
		List<Object[]> lista = new ArrayList<Object[]>();
		List<Object[]> lista2 = new ArrayList<Object[]>();
		// String sql =
		// "SELECT etiqueta,idTipoProducto,cantidad,idBoutique,precioPublico FROM inventarios where etiqueta=? and productoDistribuido='1'";
		String sql = "SELECT sum(cantidad) as cantidad,inventarios.id, etiqueta,idTipoProducto,idBoutique,precioPublico,estilo,inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario where etiqueta=? and productoDistribuido='1' group by idBoutique;";
		Connection con = DaoSource.getConnectionLocal();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, etiqueta);
			ResultSet rs = null;
			rs = pst.executeQuery();
			Object[] row = null;
			Object[] row2 = null;
			while (rs.next()) {
				row = new Object[6];
				row2 = new Object[7];
				row[0] = rs.getString("etiqueta");
				row[1] = AppInstance.idToTipoProducto.get(rs
						.getString("idTipoProducto"));
				row[2] = DaoInventarios.detalleProducto(rs.getString("estilo"),
						rs.getInt("idMarca"), rs.getInt("idDescripcion"),
						rs.getInt("idColor"));
				row[3] = rs.getInt("cantidad");
				row[4] = AppInstance.idToNombreBoutique.get(rs
						.getInt("idBoutique"));
				row[5] = AppInstance.number.format(rs
						.getDouble("precioPublico"));
				System.arraycopy(row, 0, row2, 0, row.length);
				row2[6] = rs.getInt("idBoutique");

				lista.add(row);
				lista2.add(row2);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		listas[0] = lista;
		listas[1] = lista2;
		return listas;
	}

	public static Producto findByEtiqueta(String etiqueta) {
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		Producto p = null;
		ResultSet rs = null;
		String sql = "SELECT inventarios.*,inventarios_md.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario WHERE etiqueta = ? ORDER BY inventarios.id DESC LIMIT 0,1";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, etiqueta);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Producto();
				p.setCantidad(rs.getInt("cantidad"));
				p.setClave(rs.getInt("clave"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("inventarios.id"));
				p.setIdBoutique(rs.getInt("idBoutique"));
				p.setIdCompra(rs.getInt("idCompra"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioCosto(rs.getDouble("precioCosto"));
				p.setPrecioPublico(rs.getDouble("precioPublico"));
				p.setTalla(rs.getString("talla"));
				p.setEstilo(rs.getString("estilo"));
				p.setIdMarca(rs.getInt("inventarios_md.idMarca"));
				p.setIdDescripcion(rs.getInt("inventarios_md.idDescripcion"));
				p.setIdColor(rs.getInt("inventarios_md.idColor"));
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				// con.close();
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {

			}

		}
		return p;
	}

	public static Producto findById(int id) {
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		Producto p = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM inventarios WHERE id = ? ORDER BY id DESC LIMIT 0,1";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Producto();
				p.setCantidad(rs.getInt("cantidad"));
				p.setClave(rs.getInt("clave"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("id"));
				p.setIdBoutique(rs.getInt("idBoutique"));
				p.setIdCompra(rs.getInt("idCompra"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioCosto(rs.getDouble("precioCosto"));
				p.setPrecioPublico(rs.getDouble("precioPublico"));
				p.setTalla(rs.getString("talla"));
				p.setEstilo(rs.getString("estilo"));
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				// con.close();
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {

			}

		}
		return p;
	}

	public static boolean registrarProductoExistente(List<Producto> lista) {
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		String sql = "INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		try {
			ps = con.prepareStatement(sql);
			con.setAutoCommit(false);
			String etiqueta;
			for (Producto p : lista) {
				if (p.getTalla().equals("UNITALLA")) {
					etiqueta = String.valueOf(p.getModelo()) + "U";
				} else {
					etiqueta = String.valueOf(p.getModelo()) + p.getTalla();
				}
				if (etiqueta.length() < 4) {
					switch (etiqueta.length()) {
					case 1:
						etiqueta = "000" + etiqueta;
						break;
					case 2:
						etiqueta = "00" + etiqueta;
						break;

					case 3:
						etiqueta = "0" + etiqueta;
						break;

					}
				}
				p.setEtiqueta(etiqueta);
				ps.setInt(1, p.getIdTipoProducto());
				ps.setLong(2, p.getModelo());
				ps.setString(3, p.getTalla());
				ps.setInt(4, p.getIdBoutique());
				ps.setInt(5, p.getCantidad());
				ps.setDouble(6, p.getPrecioCosto());
				ps.setDouble(7, p.getPrecioPublico());
				ps.setInt(8, p.getClave());
				ps.setInt(9, p.getIdCompra());
				ps.setString(10, etiqueta);
				ps.setString(11, "0");
				ps.execute();

			}
			con.commit();
			con.setAutoCommit(true);
			ps.close();
			// con.close();
			return true;
		} catch (SQLException ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				// con.close();
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e1x) {

			}

			return false;
		}

	}

	public static boolean registrarProductos(List<Producto> lista) {
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		PreparedStatement addProducto = null;
		PreparedStatement rsIdProducto = null;
		ResultSet rsId = null;
		PreparedStatement addProductoMD = null;
		ResultSet rs = null;
		String sql = "SELECT modelo FROM inventarios ORDER BY modelo DESC LIMIT 0,1";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			rsIdProducto = con
					.prepareStatement("SELECT id FROM inventarios ORDER BY id DESC LIMIT 1");
			rs = ps.executeQuery();
			String etiqueta;
			sql = "INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCostoInicial, impuestoPrecioCosto, gasto, impuestoGasto,precioCosto, precioPublicoInicial,impuestoPrecioPublicoInicial, precioPublicoInicialTotal, precioPublicoFinal, impuestoPrecioPublicoFinal, precioPublico,clave,idCompra,etiqueta,productoDistribuido,estilo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			addProducto = con.prepareStatement(sql);
			sql = "INSERT INTO inventarios_md (idModeloInventario,idMarca,idDescripcion,idColor) VALUES (?,?,?,?);";
			addProductoMD = con.prepareStatement(sql);
			int modelo;
			if (rs.next()) {
				// sumamos uno al modelo
				modelo = rs.getInt("modelo");
				modelo++;

			} else {
				// No tenemos ningun modelo empezamos en el 1
				modelo = 1;
			}
			// Tenemos el nuevo modelo, generamos las etiquetas para los
			// productos
			Producto pp = null;
			for (Producto p : lista) {
				pp = p;
				if (p.getTalla().equals("UNITALLA")) {
					etiqueta = modelo + "U";
				} else {
					etiqueta = modelo + p.getTalla();
				}
				if (etiqueta.length() < 4) {
					switch (etiqueta.length()) {
					case 1:
						etiqueta = "000" + etiqueta;
						break;
					case 2:
						etiqueta = "00" + etiqueta;
						break;

					case 3:
						etiqueta = "0" + etiqueta;
						break;

					}
				}
				p.setModelo(modelo);
				p.setEtiqueta(etiqueta);
				addProducto.setInt(1, p.getIdTipoProducto());
				addProducto.setLong(2, modelo);
				addProducto.setString(3, p.getTalla());
				addProducto.setInt(4, p.getIdBoutique());
				addProducto.setInt(5, p.getCantidad());
				addProducto.setDouble(6, p.getPrecioCostoInicial());
				addProducto.setDouble(7, p.getImpuestoPrecioCosto());
				addProducto.setDouble(8, p.getGasto());
				addProducto.setDouble(9, p.getImpuestoGasto());
				addProducto.setDouble(10, p.getPrecioCosto());
				addProducto.setDouble(11, p.getPrecioPublicoInicial());
				addProducto.setDouble(12, p.getImpuestoPrecioPublicoInicial());
				addProducto.setDouble(13, p.getPrecioPublicoInicialTotal());
				addProducto.setDouble(14, p.getPrecioPublicoFinal());
				addProducto.setDouble(15, p.getImpuestoPrecioPublicoFinal());
				addProducto.setDouble(16, p.getPrecioPublico());
				addProducto.setInt(17, p.getClave());
				addProducto.setInt(18, p.getIdCompra());
				addProducto.setString(19, etiqueta);
				addProducto.setString(20, "0");
				addProducto.setString(21, p.getEstilo());
				addProducto.execute();

				rsId = rsIdProducto.executeQuery();
				if (rsId.next()) {
					p.setId(rsId.getInt("id"));
				}
				rsId.close();
				rsId = null;
				// INSERTAMOS EL REGISTRO EN inventarios_md;
			}
			addProductoMD.setInt(1, modelo);
			if (pp.getIdMarca() != 0) {
				addProductoMD.setInt(2, pp.getIdMarca());
			} else {
				addProductoMD.setNull(2, java.sql.Types.NULL);
			}
			if (pp.getIdDescripcion() != 0) {
				addProductoMD.setInt(3, pp.getIdDescripcion());
			} else {
				addProductoMD.setNull(3, java.sql.Types.NULL);
			}

			if (pp.getIdColor() != 0) {
				addProductoMD.setInt(4, pp.getIdColor());
			} else {
				addProductoMD.setNull(4, java.sql.Types.NULL);
			}

			if (pp.getIdMarca() != 0 || pp.getIdDescripcion() != 0
					|| pp.getIdColor() != 0) {
				addProductoMD.execute();
			}

			con.commit();
			con.setAutoCommit(true);
			rs.close();
			ps.close();
			addProducto.close();
			addProductoMD.close();
			// con.close();
			return true;
		} catch (Exception ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				// con.close();
				if (rs != null) {
					rs.close();
				}
				if (addProducto != null) {
					addProducto.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {

			}
			return false;
		}
	}

	/**
	 * findProductosByIdCompra
	 * 
	 * @param id
	 *            int
	 * @return List
	 */
	public static List<Producto> findProductosByIdCompra(int id) {
		Connection con = DaoSource.getConnection();
		Producto p;
		List<Producto> lista = new ArrayList<Producto>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT inventarios.*,inventarios_md.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario WHERE idCompra=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new Producto();
				p.setCantidad(rs.getInt("cantidad"));
				p.setId(rs.getInt("inventarios.id"));
				p.setClave(rs.getInt("clave"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setIdBoutique(rs.getInt("idBoutique"));
				p.setIdCompra(rs.getInt("idCompra"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioCosto(rs.getDouble("precioCosto"));
				p.setPrecioPublico(rs.getDouble("precioPublico"));
				p.setTalla(rs.getString("talla"));
				p.setEstilo(rs.getString("estilo"));
				p.setIdMarca(rs.getInt("inventarios_md.idMarca"));
				p.setIdDescripcion(rs.getInt("inventarios_md.idDescripcion"));
				p.setIdColor(rs.getInt("inventarios_md.idColor"));
				lista.add(p);
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.err.println(ex.toString());
		}
		return lista;

	}

	/**
	 * findByEtiquetaWithCompraCerrada
	 * 
	 * @param etiqueta
	 *            String
	 * @return Producto
	 */
	public static Producto findByEtiquetaWithCompraCerrada(String etiqueta) {
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		Producto p = null;
		ResultSet rs = null;
		String sql = "SELECT inventarios_md.*,inventarios.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario  INNER JOIN compras ON compras.id=inventarios.idCompra WHERE compras.status='CERRADA' AND etiqueta = ? AND cantidad>0 AND idBoutique=? ORDER BY inventarios.id DESC LIMIT 0,1";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, etiqueta);
			ps.setInt(2, DaoBoutique.getMatriz());
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Producto();
				p.setCantidad(rs.getInt("cantidad"));
				p.setClave(rs.getInt("clave"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("inventarios.id"));
				p.setIdBoutique(rs.getInt("idBoutique"));
				p.setIdCompra(rs.getInt("idCompra"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioCostoInicial(rs.getDouble("precioCostoInicial"));
				p.setImpuestoPrecioCosto(rs.getDouble("impuestoPrecioCosto"));
				p.setGasto(rs.getDouble("gasto"));
				p.setImpuestoGasto(rs.getDouble("impuestoGasto"));
				p.setPrecioCosto(rs.getDouble("precioCosto"));
				p.setPrecioPublicoInicial(rs.getDouble("precioPublicoInicial"));
				p.setImpuestoPrecioPublicoInicial(rs.getDouble("impuestoPrecioPublicoInicial"));
				p.setPrecioPublicoInicialTotal(rs.getDouble("precioPublicoInicialTotal"));
				p.setPrecioPublicoFinal(rs.getDouble("precioPublicoFinal"));
				p.setImpuestoPrecioPublicoFinal(rs.getDouble("impuestoPrecioPublicoFinal"));
				p.setPrecioPublico(rs.getDouble("precioPublico"));
				p.setTalla(rs.getString("talla"));
				p.setEstilo(rs.getString("estilo"));
				p.setIdMarca(rs.getInt("inventarios_md.idMarca"));
				p.setIdDescripcion(rs.getInt("inventarios_md.idDescripcion"));
				p.setIdColor(rs.getInt("inventarios_md.idColor"));
				p.setEstilo(rs.getString("inventarios.estilo"));
				
				
				/*addProducto.setInt(1, p.getIdTipoProducto());
				addProducto.setLong(2, modelo);
				addProducto.setString(3, p.getTalla());
				addProducto.setInt(4, p.getIdBoutique());
				addProducto.setInt(5, p.getCantidad());
				addProducto.setDouble(6, p.getPrecioCostoInicial());
				addProducto.setDouble(7, p.getImpuestoPrecioCosto());
				addProducto.setDouble(8, p.getGasto());
				addProducto.setDouble(9, p.getImpuestoGasto());
				addProducto.setDouble(10, p.getPrecioCosto());
				addProducto.setDouble(11, p.getPrecioPublicoInicial());
				addProducto.setDouble(12, p.getImpuestoPrecioPublicoInicial());
				addProducto.setDouble(13, p.getPrecioPublicoInicialTotal());
				addProducto.setDouble(14, p.getPrecioPublicoFinal());
				addProducto.setDouble(15, p.getImpuestoPrecioPublicoFinal());
				addProducto.setDouble(16, p.getPrecioPublico());
				addProducto.setInt(17, p.getClave());
				addProducto.setInt(18, p.getIdCompra());
				addProducto.setString(19, etiqueta);
				addProducto.setString(20, "0");
				addProducto.setString(21, p.getEstilo());*/
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				// con.close();
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {

			}

		}
		return p;

	}

	/**
	 * dividirProductos
	 * 
	 * @param productosaDividir
	 *            List
	 * @return boolean
	 */
	public static boolean dividirProductos(List<Producto> productosaDividir) {
		return false;
	}

	private void jbInit() throws Exception {
	}

	/**
	 * dividirProducto
	 * 
	 * @param p
	 *            Producto
	 */
	public static boolean dividirProducto(Producto p) {
		boolean result;
		// String sql =
		// "SELECT id FROM inventarios WHERE idCompra=? AND etiqueta =? AND productoDistribuido='0' AND idBoutique=? AND cantidad>0;";
		Connection con = DaoSource.getConnection();
		PreparedStatement psUpdate = null;
		PreparedStatement insert = null;
		try {
			con.setAutoCommit(false);
			String sql = null;
			// psId = con.prepareStatement(sql);
			// psId.setInt(1, p.getIdCompra());
			// psId.setString(2, p.getEtiqueta());
			// psId.setInt(3, DaoBoutique.getMatriz());
			// rs = psId.executeQuery();
			// if (rs.next()) {
			// Si encontramos el producto que queriamos, le descontamos uno en
			// su cantidad
			sql = "UPDATE inventarios SET cantidad=cantidad-1 WHERE id=?";
			psUpdate = con.prepareStatement(sql);
			psUpdate.setInt(1, p.getId());
			// psUpdate.setString(2, p.getEtiqueta());
			// psUpdate.setInt(3, DaoBoutique.getMatriz());
			psUpdate.execute();
			// Se actualizo la cantidad que se deja en la matriz, ahora
			// agregamos el registro para la boutique a la que pertenecerá
			//sql = "INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido,estilo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
			sql = "INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCostoInicial, impuestoPrecioCosto, gasto, impuestoGasto,precioCosto, precioPublicoInicial,impuestoPrecioPublicoInicial, precioPublicoInicialTotal, precioPublicoFinal, impuestoPrecioPublicoFinal, precioPublico,clave,idCompra,etiqueta,productoDistribuido,estilo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			insert = con.prepareStatement(sql);
			insert.setInt(1, p.getIdTipoProducto());
			insert.setLong(2, p.getModelo());
			insert.setString(3, p.getTalla());
			insert.setInt(4, p.getIdBoutique());
			insert.setInt(5, p.getCantidad());
			insert.setDouble(6, p.getPrecioCostoInicial());
			insert.setDouble(7, p.getImpuestoPrecioCosto());
			insert.setDouble(8, p.getGasto());
			insert.setDouble(9, p.getImpuestoGasto());
			insert.setDouble(10, p.getPrecioCosto());
			insert.setDouble(11, p.getPrecioPublicoInicial());
			insert.setDouble(12, p.getImpuestoPrecioPublicoInicial());
			insert.setDouble(13, p.getPrecioPublicoInicialTotal());
			insert.setDouble(14, p.getPrecioPublicoFinal());
			insert.setDouble(15, p.getImpuestoPrecioPublicoFinal());
			insert.setDouble(16, p.getPrecioPublico());
			insert.setInt(17, p.getClave());
			insert.setInt(18, p.getIdCompra());
			insert.setString(19, p.getEtiqueta());
			insert.setString(20, "0");
			
			
 		 
		 
 			if (p.getEstilo() == null) {
				insert.setNull(21, java.sql.Types.NULL);
			} else {
				insert.setString(21, p.getEstilo());
			}
			insert.execute();
			// Descontado el producto y registrado nuevamente a la nueva
			// boutique, tenemos listo el inventario
			con.commit();
			con.setAutoCommit(true);
			insert.close();
			psUpdate.close();
			result = true;
			// }
			// else { //No hay etiqueta, no lo agregamos y retornamos false
			// con.close();
			// result = false;
			// }
			// rs.close();
			// psId.close();
			// con.close();

		} catch (SQLException ex) {
			try {
				System.out.println(ex.toString());
				con.rollback();
				// con.close();
			} catch (Exception ee) {
			}
			result = false;
		}
		return result;
	}

	public static List<Object[]> findProductosNoDistribuidosViewBySucursal(
			int idSucursal) {
		java.text.NumberFormat nmbF = java.text.NumberFormat
				.getNumberInstance(Locale.ENGLISH);
		nmbF.setMaximumFractionDigits(2);
		nmbF.setMinimumFractionDigits(2);

		Connection con = DaoSource.getConnection();
		List<Object[]> lista = new ArrayList<Object[]>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		// "id", "Etiqueta","Tipo de producto", "Talla","P. al público"}
		String sql = "SELECT boutiques.nombre,inventarios.estilo, inventarios.id,inventarios.etiqueta,tipoproductos.nombre,inventarios.talla,inventarios.precioPublico,sum(inventarios.cantidad) as cantidad FROM inventarios INNER JOIN tipoproductos ON inventarios.idTipoProducto=tipoproductos.id INNER JOIN compras ON compras.id=inventarios.idCompra INNER JOIN boutiques ON boutiques.id=inventarios.idBoutique WHERE  compras.status='CERRADA' AND inventarios.productoDistribuido='0' AND idBoutique=? GROUP BY inventarios.etiqueta";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idSucursal);
			rs = ps.executeQuery();
			Object[] row;
			while (rs.next()) {
				row = new Object[8];
				row[0] = rs.getInt("id");
				row[1] = rs.getString("boutiques.nombre");
				row[2] = rs.getInt("cantidad");
				row[3] = rs.getString("etiqueta");
				row[4] = rs.getString("tipoproductos.nombre");
				row[5] = rs.getString("talla");
				row[6] = nmbF.format(rs.getDouble("precioPublico"));
				row[7] = rs.getString("estilo") + " ";
				lista.add(row);
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.err.println(ex.toString());
		}
		return lista;

	}

	public static List<Object[]> findProductosNoDistribuidosView() {
		java.text.NumberFormat nmbF = java.text.NumberFormat
				.getNumberInstance(Locale.ENGLISH);
		nmbF.setMaximumFractionDigits(2);
		nmbF.setMinimumFractionDigits(2);

		Connection con = DaoSource.getConnection();
		List<Object[]> lista = new ArrayList<Object[]>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		// "id", "Etiqueta","Tipo de producto", "Talla","P. al público"}
		String sql = "SELECT boutiques.nombre,inventarios.estilo,inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor, inventarios.id,inventarios.etiqueta,tipoproductos.nombre,inventarios.talla,inventarios.precioPublico,sum(inventarios.cantidad) as cantidad FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario INNER JOIN tipoproductos ON inventarios.idTipoProducto=tipoproductos.id INNER JOIN compras ON compras.id=inventarios.idCompra INNER JOIN boutiques ON boutiques.id=inventarios.idBoutique WHERE  compras.status='CERRADA' AND inventarios.productoDistribuido='0' AND inventarios.cantidad>0 GROUP BY inventarios.etiqueta,inventarios.idBoutique ORDER BY boutiques.nombre";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] row;
			while (rs.next()) {
				row = new Object[8];
				row[0] = rs.getInt("id");
				row[1] = rs.getString("boutiques.nombre");
				row[2] = rs.getInt("cantidad");
				row[3] = rs.getString("etiqueta");
				row[4] = rs.getString("tipoproductos.nombre");
				row[5] = detalleProducto(rs.getString("inventarios.estilo"),
						rs.getInt("inventarios_md.idMarca"),
						rs.getInt("inventarios_md.idDescripcion"),
						rs.getInt("inventarios_md.idColor"));
				row[6] = rs.getString("talla");
				row[7] = nmbF.format(rs.getDouble("precioPublico"));
				lista.add(row);
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.err.println(ex.toString());
		}
		return lista;

	}

	public static String detalleProducto(String estilo, int idMarca,
			int idDescripcion, int idColor) {
		String detalle = "";
		if (estilo != null && !estilo.equals("")) {
			detalle += estilo + "-";
		}
		if (idMarca > 0) {
			detalle += AppInstance.id2marcas.get(idMarca) + "-";
		}
		if (idDescripcion > 0) {
			detalle += AppInstance.id2descripciones.get(idDescripcion) + "-";
		}
		if (idColor > 0) {
			detalle += AppInstance.id2colores.get(idColor) + " ";
		}
		if (detalle.length() > 0) {
			return detalle.substring(0, detalle.length() - 1);
		} else {
			return detalle;
		}
	}

	/**
	 * findProductosByIdBoutique
	 * 
	 * @param index
	 *            int
	 * @return List
	 */
	public static List<Object[]> findProductosByIdBoutique(int index) {
		java.text.NumberFormat nmbF = java.text.NumberFormat
				.getNumberInstance(Locale.ENGLISH);
		nmbF.setMaximumFractionDigits(2);
		nmbF.setMinimumFractionDigits(2);

		Connection con = DaoSource.getConnection();
		List<Object[]> lista = new ArrayList<Object[]>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		// "id", "Etiqueta","Tipo de producto", "Talla","P. al público"}
		String sql = "SELECT inventarios.id,inventarios.etiqueta,tipoproductos.nombre,inventarios.talla,inventarios.precioPublico FROM inventarios INNER JOIN tipoproductos ON inventarios.idTipoProducto=tipoproductos.id WHERE inventarios.idBoutique=? AND inventarios.productoDistribuido='0'";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			rs = ps.executeQuery();
			Object[] row;
			while (rs.next()) {
				row = new Object[5];
				row[0] = rs.getInt("id");
				row[1] = rs.getString("etiqueta");
				row[2] = rs.getString("nombre");
				row[3] = rs.getString("talla");
				row[4] = nmbF.format(rs.getDouble("precioPublico"));
				lista.add(row);
			}
			rs.close();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			System.err.println(ex.toString());
		}
		return lista;

	}

	/**
	 * actualizarProductoExistente
	 * 
	 * @param productoaEditar
	 *            Producto
	 * @return boolean
	 */
	public static boolean actualizarProductoExistente(Producto p) {
		String sql = "UPDATE inventarios SET cantidad=?, precioCosto=?, precioPublico=? WHERE id=?";
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getCantidad());
			ps.setDouble(2, p.getPrecioCosto());
			ps.setDouble(3, p.getPrecioPublico());
			ps.setInt(4, p.getId());
			ps.execute();
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			try {
				System.err.println(ex.toString());
				if (con != null) {
					// con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {
			}
			return false;
		}
		return true;
	}

	/**
	 * borrarProducto
	 * 
	 * @param p
	 *            Producto
	 * @return boolean
	 */
	public static boolean borrarProducto(Producto p) {
		String sql = "DELETE FROM inventarios WHERE  id=?";
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		PreparedStatement psModeloInventario = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.execute();
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT COUNT(*) as counter FROM inventarios WHERE modelo="
							+ p.getModelo());
			if (rs.next()) { // ca veut dire qu il n y a plus de produits avec
								// le meme modele. On erase le record sur la
								// tableau inventarios_md;
				if (rs.getInt("counter") == 0) {
					psModeloInventario = con
							.prepareStatement("DELETE FROM inventarios_md WHERE idModeloInventario="
									+ p.getModelo());
					psModeloInventario.execute();
					psModeloInventario.close();
					psModeloInventario = null;
				}
			}
			rs.close();
			ps.close();
			rs = null;
			ps = null;
			con.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				System.err.println(ex.toString());
				con.rollback();
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e1x) {
				e1x.printStackTrace();
			}
			return false;
		}
		return true;

	}

	/**
	 * regresarProducto
	 * 
	 * @param p
	 *            Producto
	 * @return boolean
	 */
	public static boolean regresarProducto(Producto p) {
		Connection con = DaoSource.getConnection();

		PreparedStatement ps = null;
		PreparedStatement psDescontar = null;
		PreparedStatement psAumentar = null;
		ResultSet rs = null;
		// Buscamos el registro en inventarios para esta boutique, con compra
		// cerrada y que no ha sido distribuido por Etiqueta;
		String sql = "SELECT inventarios.* FROM inventarios INNER JOIN compras ON compras.id=inventarios.idCompra WHERE compras.status='CERRADA' AND etiqueta = ? AND idBoutique=? AND productoDistribuido='0' ORDER BY id DESC LIMIT 0,1";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getEtiqueta());
			ps.setInt(2, p.getIdBoutique());
			rs = ps.executeQuery();
			// Encontramos algo
			if (rs.next()) {
				// tenemos el id, descontamos 1
				sql = "UPDATE inventarios INNER JOIN compras ON compras.id=inventarios.idCompra SET cantidad=cantidad-1 WHERE inventarios.etiqueta=? and compras.status='CERRADA' AND inventarios.idCompra=? AND inventarios.productoDistribuido='0' AND inventarios.idBoutique=? AND inventarios.cantidad>0 AND inventarios.id=?";
				psDescontar = con.prepareStatement(sql);
				psDescontar.setString(1, p.getEtiqueta());
				psDescontar.setInt(2, p.getIdCompra());
				psDescontar.setInt(3, p.getIdBoutique());
				psDescontar.setInt(4, p.getId());
				psDescontar.execute();
				sql = "UPDATE inventarios INNER JOIN compras ON compras.id=inventarios.idCompra SET cantidad=cantidad+1 WHERE inventarios.etiqueta=? and compras.status='CERRADA' AND inventarios.idCompra=? AND inventarios.productoDistribuido='0' AND inventarios.idBoutique=?";
				psAumentar = con.prepareStatement(sql);
				psAumentar.setString(1, p.getEtiqueta());
				psAumentar.setInt(2, p.getIdCompra());
				psAumentar.setInt(3, DaoBoutique.getMatriz());
				psAumentar.execute();
				con.commit();
				// con.close();
				con.setAutoCommit(true);
				return true;
				// Si fue descontado correctamente, lo sumamos en el inventario
				// inical donde la etiqueta, el numero de compra y la compra
				// cerrada que no ha sido distribuido

			} else {
				return false;
			}
		} catch (SQLException ex) {
			try {
				con.rollback();
				// con.close();
				System.err.println(ex.toString());
				if (psDescontar != null) {
					psDescontar.close();
				}
				if (psAumentar != null) {
					psAumentar.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex1) {
			}
			return false;
		}
	}

	public static boolean eliminarCantidadesCero() {
		Connection con = DaoSource.getConnection();
		Statement st = null;
		try {
			String sql = "DELETE FROM inventarios where productoDistribuido='0' AND cantidad=0 and inventarios.idBoutique<>"
					+ DaoBoutique.getMatriz();
			st = con.createStatement();
			st.execute(sql);
			st.close();
			// con.close();
		} catch (SQLException ex) {
			return false;
		}

		return true;

	}

	/**
	 * findProductosNoDistribuidosByIdBoutique
	 * 
	 * Tenemos la boutique seleccionada, ponemos aquellos productos que no se
	 * han distribuido de compras cerradas que pertenecen a esta boutique
	 * 
	 * @param idBoutique
	 *            int
	 * @return List
	 */
	public static List<Producto> findProductosNoDistribuidosByIdBoutique(
			int idBoutique) {
		Connection con = DaoSource.getConnection();
		List<Producto> lista = new ArrayList<Producto>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto p;
		// "id", "Etiqueta","Tipo de producto", "Talla","P. al público"}
		String sql = "SELECT inventarios.*,inventarios_md.* FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario INNER JOIN compras ON compras.id=inventarios.idCompra  WHERE inventarios.idBoutique=? AND inventarios.productoDistribuido='0' AND compras.status='CERRADA' AND inventarios.cantidad>0";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idBoutique);
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new Producto();
				p.setCantidad(rs.getInt("cantidad"));
				p.setClave(rs.getInt("clave"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("inventarios.id"));
				p.setIdBoutique(rs.getInt("idBoutique"));
				p.setIdCompra(rs.getInt("idCompra"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioCosto(rs.getDouble("precioCosto"));
				p.setPrecioPublico(rs.getDouble("precioPublico"));
				p.setTalla(rs.getString("talla"));
				p.setEstilo(rs.getString("estilo"));
				p.setIdMarca(rs.getInt("inventarios_md.idMarca"));
				p.setIdDescripcion(rs.getInt("inventarios_md.idDescripcion"));
				p.setIdColor(rs.getInt("inventarios_md.idColor"));
				lista.add(p);
			}
			rs.close();
			ps.close();
			// con.close();
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
			} catch (SQLException ex1) {
			}
			return null;
		}
		return lista;
	}

	public static List<Producto> findProductosNoDistribuidos() {
		Connection con = DaoSource.getConnection();
		List<Producto> lista = new ArrayList<Producto>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto p;
		// "id", "Etiqueta","Tipo de producto", "Talla","P. al público"}
		String sql = "SELECT inventarios.* FROM inventarios INNER JOIN compras ON compras.id=inventarios.idCompra WHERE inventarios.productoDistribuido='0' AND compras.status='CERRADA'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new Producto();
				p.setCantidad(rs.getInt("cantidad"));
				p.setClave(rs.getInt("clave"));
				p.setEtiqueta(rs.getString("etiqueta"));
				p.setId(rs.getInt("id"));
				p.setIdBoutique(rs.getInt("idBoutique"));
				p.setIdCompra(rs.getInt("idCompra"));
				p.setIdTipoProducto(rs.getInt("idTipoProducto"));
				p.setModelo(rs.getInt("modelo"));
				p.setPrecioCosto(rs.getDouble("precioCosto"));
				p.setPrecioPublico(rs.getDouble("precioPublico"));
				p.setTalla(rs.getString("talla"));
				lista.add(p);
			}
			rs.close();
			ps.close();
			// con.close();
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
			} catch (SQLException ex1) {
			}
			return null;
		}
		return lista;
	}

	/**
	 * distribuirProductos
	 * 
	 * @param productos
	 *            List
	 */
	public static void distribuirProductos(List<Producto> productos)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Actualizamos los productos y los ponemos como distribuidos
			con = DaoSource.getConnection();
			con.setAutoCommit(false);
			String sql = "UPDATE inventarios SET productoDistribuido='1' WHERE id=?";
			ps = con.prepareStatement(sql);
			for (Producto p : productos) {
				ps.setInt(1, p.getId());
				ps.execute();
			}
			con.commit();
			con.setAutoCommit(true);
			ps.close();
			// con.close();
		} catch (SQLException ex) {
			con.rollback();
			// con.close();
			throw ex;
		}

	}

	/**
	 * findBoutiquesConProductosNoDistribuidos
	 * 
	 * @return int[]
	 */
	public static List<Integer> findBoutiquesConProductosNoDistribuidos() {
		Connection con = DaoSource.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> boutiques = new ArrayList<Integer>();
		// "id", "Etiqueta","Tipo de producto", "Talla","P. al público"}
		String sql = "SELECT DISTINCT boutiques.id FROM inventarios INNER JOIN compras ON compras.id=inventarios.idCompra INNER JOIN boutiques ON inventarios.idBoutique=boutiques.id WHERE inventarios.productoDistribuido='0' AND compras.status='CERRADA'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer i = rs.getInt("id");
				boutiques.add(i);
			}
			rs.close();
			ps.close();
			// con.close();
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
			} catch (SQLException ex1) {
			}
			return null;
		}
		return boutiques;
	}

	/**
	 * actualizarPrecioProductos
	 * 
	 * @param productosaCambiar
	 *            List
	 * @return boolean
	 */
	public static boolean actualizarPrecioProductos(
			List<Object[]> productosaCambiar) {
		String sql = "UPDATE inventarios SET precioPublico=? WHERE etiqueta=? AND productoDistribuido='1'";
		Connection con = DaoSource.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			con.setAutoCommit(false);
			for (Object[] row : productosaCambiar) {

				pst.setDouble(1, AppInstance.number.parse(row[6].toString())
						.doubleValue());
				pst.setString(2, row[0].toString());
				// POR CADA ARTICULO EJECUTAMOS LA ACTUALIZACION
				pst.executeUpdate();
			}
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (Exception ex) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException ex1) {
			}

			return false;
		}
		// LISTA PARA ACTUALIZAR LOS PRECIOS DE LOS PRODUCTOS

	}

}
