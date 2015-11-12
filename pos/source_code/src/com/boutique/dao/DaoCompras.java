package com.boutique.dao;

import java.sql.*;
import java.util.*;

import com.boutique.domain.*;

/**
 * <p>Title: boutique management</p>
 *
 * <p>Description: Sistema de administracion de boitiques</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: SESTO</p>
 *
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class DaoCompras {
  public DaoCompras() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static List<Object[]> findComprasAbiertas() {
    List<Object[]> lista = new ArrayList<Object[]>();
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Object[] row = null;
    String sql = "SELECT proveedores.nombre,compras.fecha,compras.id,compras.noReferencia,compras.status FROM compras,proveedores WHERE compras.idProveedor=proveedores.id  AND compras.status='ABIERTA' ORDER BY compras.fecha DESC;";
    try {
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        row = new Object[5];
        row[0] = rs.getInt("id");
        row[1] = rs.getTimestamp("fecha");
        row[2] = rs.getString("nombre");
        row[3] = rs.getString("noReferencia");
        row[4] = rs.getString("status");
        lista.add(row);
      }
      ps.close();
//      con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      }
      catch (SQLException e) {}
    }
    return lista;

  }

  public static List<Compra> findByNombre(String nombre) {
    List<Compra> lista = new ArrayList<Compra> ();
    Compra compra;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {

      String sql;
      if (!nombre.equals("")) {
        sql = "SELECT compras.* FROM compras,proveedores WHERE compras.idProveedor=proveedores.id AND proveedores.nombre LIKE ?";
        Connection con = DaoSource.getConnection();

        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + nombre + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
          compra = new Compra();
          compra.setId(rs.getInt("id"));
          compra.setFecha(rs.getTimestamp("fecha"));
          compra.setIdProveedor(rs.getInt("idProveedor"));
          compra.setNoReferencia(rs.getString("noReferencia"));
          compra.setStatus(rs.getString("status"));
          lista.add(compra);
        }
        ps.close();
        //con.close();
      }
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        if (ps != null) {
          ps.close();
        }

      }
      catch (SQLException e) {}
    }
    return lista;

  }

  public static boolean add(Compra c) throws SQLException {
    Connection con = DaoSource.getConnection();
    boolean result = true;
    PreparedStatement ps = null;
    String sql =
        "INSERT INTO compras (fecha,idProveedor,noReferencia,status) VALUES (?,?,?,?)";
    ps = con.prepareStatement(sql);
    ps.setTimestamp(1, c.getFecha());
    ps.setInt(2, c.getIdProveedor());
    ps.setString(3, c.getNoReferencia());
    ps.setString(4, "ABIERTA");
    ps.execute();
    ps.close();
    //con.close();
    return result;
  }
  
  public static boolean add(Factura f) throws SQLException{
	  Connection con = DaoSource.getConnection();
	  boolean result = true;
	  PreparedStatement ps = null;
	  String sql ="INSERT INTO facturas_compras (noFactura,idProveedor,tipoPago,fechaEmision) VALUES (?,?,?,?)";
	  ps = con.prepareStatement(sql);
	  ps.setString(1, f.getNoFactura());
	  ps.setInt(2, f.getIdProveedor());
	  ps.setInt(3, f.getTipoPago());
	  ps.setTimestamp(4, f.getFecha());
	  ps.execute();
	  return result;
	  
  }

  public static boolean update(Compra c) throws SQLException {
    Connection con = DaoSource.getConnection();
    boolean result = true;
    PreparedStatement ps = null;
    String sql =
        "UPDATE compras SET fecha=?,idProveedor = ?, noReferencia = ? WHERE id=?";

    ps = con.prepareStatement(sql);
    ps.setTimestamp(1, c.getFecha());
    ps.setInt(2, c.getIdProveedor());
    ps.setString(3, c.getNoReferencia());
    ps.setInt(4, c.getId());
    ps.execute();
    ps.close();
    //con.close();
    return result;

  }

  public static boolean remove(int id) throws SQLException {
    Connection con = DaoSource.getConnection();
    boolean result = true;
    PreparedStatement ps = null;
    String sql = "DELETE FROM compras WHERE id= ?";
    ps = con.prepareStatement(sql);
    ps.setInt(1, id);
    ps.execute();
    ps.close();
    //con.close();
    return result;

  }

  private void jbInit() throws Exception {
  }

  /**
   * findById
   *
   * @param id int
   * @return Compra
   */
  public static Compra findById(int id) {
    Compra compra = null;
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM compras WHERE id=?";
    try {
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      if (rs.next()) {
        compra = new Compra();
        compra.setFecha(rs.getTimestamp("fecha"));
        compra.setId(rs.getInt("id"));
        compra.setIdProveedor(rs.getInt("idProveedor"));
        compra.setNoReferencia(rs.getString("noReferencia"));
        compra.setStatus(rs.getString("status"));
      }
      ps.close();
      //con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      }
      catch (SQLException e) {}
    }
    return compra;
  }

  /**
   * cerrarCompra
   *
   * @param i int
   */
  public static boolean cerrarCompra(int id) {
    Connection con = DaoSource.getConnection();
    boolean result = true;
    PreparedStatement ps = null;
    String sql = "UPDATE compras SET status='CERRADA' WHERE id= ?";
    try {
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ps.execute();
      ps.close();
      //con.close();
    }
    catch (SQLException ex) {
      try {
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      }
      catch (SQLException ee) {}
    }
    return result;

  }
}
