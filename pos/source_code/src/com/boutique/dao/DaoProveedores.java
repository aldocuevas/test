package com.boutique.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.boutique.domain.Proveedor;

public class DaoProveedores {

  public DaoProveedores() {
  }

  public static List<Proveedor> findAll(){
    List<Proveedor> l = new ArrayList<Proveedor> ();
    Connection con = null;
    PreparedStatement ps = null;
    Proveedor p = null;
    ResultSet rs = null;
    String sql = null;
    con = DaoSource.getConnection();
    try {
        sql = "SELECT * FROM proveedores ORDER BY nombre";
        ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        p = new Proveedor();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setDomicilio(rs.getString("domicilio"));
        p.setCiudad(rs.getString("ciudad"));
        p.setEstado(rs.getString("estado"));
        p.setColonia(rs.getString("colonia"));
        p.setCp(rs.getString("cp"));
        p.setRfc(rs.getString("rfc"));
        p.setTelefono(rs.getString("telefono"));
        p.setCredito(rs.getInt("credito"));
        l.add(p);
      }
      rs.close();
      ps.close();
//      con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return l;

  }

  /**
   * add
   *
   * @param a Object
   * @return boolean
   */
  public static boolean add(Proveedor proveedor) {
    PreparedStatement ps = null;
    Connection conn;
    conn = DaoSource.getConnection();
    String sql = "INSERT INTO `proveedores` ( `nombre` , `domicilio` , `colonia` , `ciudad` , `estado` , `cp` , `telefono` , `rfc` , `credito` ) VALUES (?,?,?,?,?,?,?,?,?)";
    try {
      ps = conn.prepareStatement(sql);
      ps.setString(1, proveedor.getNombre());
      ps.setString(2, proveedor.getDomicilio());
      ps.setString(3, proveedor.getColonia());
      ps.setString(4, proveedor.getCiudad());
      ps.setString(5, proveedor.getEstado());
      ps.setString(6, proveedor.getCp());
      ps.setString(7, proveedor.getTelefono());
      ps.setString(8, proveedor.getRfc());
      ps.setInt(9, proveedor.getCredito());
      ps.execute();
      ps.close();
      //conn.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return false;
    }
    return true;
  }

  /**
   * getLike
   *
   * @param pattern String
   * @return ArrayList
   */
  public static List<Proveedor> findBy(String pattern) {
    List<Proveedor> l = new ArrayList<Proveedor> ();
    Connection con = null;
    PreparedStatement ps = null;
    Proveedor p = null;
    ResultSet rs = null;
    String sql = null;
    con = DaoSource.getConnection();
    try {
      if (pattern.equals("")) {
        sql = "SELECT * FROM proveedores ORDER BY nombre";
        ps = con.prepareStatement(sql);
      }
      else {
        sql = "SELECT * FROM proveedores WHERE nombre LIKE ? ORDER BY nombre";
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + pattern + "%");
      }

      rs = ps.executeQuery();
      while (rs.next()) {
        p = new Proveedor();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setDomicilio(rs.getString("domicilio"));
        p.setCiudad(rs.getString("ciudad"));
        p.setEstado(rs.getString("estado"));
        p.setColonia(rs.getString("colonia"));
        p.setCp(rs.getString("cp"));
        p.setRfc(rs.getString("rfc"));
        p.setTelefono(rs.getString("telefono"));
        p.setCredito(rs.getInt("credito"));
        l.add(p);
      }
      rs.close();
      ps.close();
    //  con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return l;

  }

  /**
   * remove
   *
   * @param a Object
   * @return boolean
   */
  public static boolean remove(int id) {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = "DELETE FROM proveedores WHERE id=?";
    con = DaoSource.getConnection();
    try {
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ps.execute();
      ps.close();
     // con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return false;
    }

    return true;
  }

  /**
   * update
   *
   * @param a Object
   * @return boolean
   */
  public static boolean update(Proveedor p) {
    String sql = "UPDATE `proveedores` SET `nombre` = ?,`domicilio` = ? ,`colonia` = ?,`ciudad` = ? ,`estado` = ?,`cp` = ?,`telefono` = ?,`rfc` = ?,`credito` = ? WHERE `id` = ?;";
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, p.getNombre());
      ps.setString(2, p.getDomicilio());
      ps.setString(3, p.getColonia());
      ps.setString(4, p.getCiudad());
      ps.setString(5, p.getEstado());
      ps.setString(6, p.getCp());
      ps.setString(7, p.getTelefono());
      ps.setString(8, p.getRfc());
      ps.setInt(9, p.getCredito());
      ps.setInt(10, p.getId());
      ps.execute();
      ps.close();
     // con.close();
    }
    catch (SQLException ex) {
System.out.println(ex.toString());
      return false;
    }

    return true;
  }

  public static Proveedor findById(int id) {
    Proveedor p = null;
    String sql = "SELECT * FROM proveedores WHERE id=?;";
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        p = new Proveedor();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setDomicilio(rs.getString("domicilio"));
        p.setCiudad(rs.getString("ciudad"));
        p.setEstado(rs.getString("estado"));
        p.setColonia(rs.getString("colonia"));
        p.setCp(rs.getString("cp"));
        p.setRfc(rs.getString("rfc"));
        p.setTelefono(rs.getString("telefono"));
        p.setCredito(rs.getInt("credito"));
      }
      rs.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return p;
  }
}
