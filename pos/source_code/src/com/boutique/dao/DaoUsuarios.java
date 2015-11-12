package com.boutique.dao;

import java.util.*;
import java.sql.*;
import com.boutique.domain.Usuario;

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
public class DaoUsuarios {
  public DaoUsuarios() {
  }

  public static Usuario findUsuarioById(int id) {
    String sql = "SELECT * FROM usuarios WHERE id=" + id;
    Connection con = DaoSource.getConnection();
    Usuario u = null;
    try {
      ResultSet rs = con.createStatement().executeQuery(sql);
      if (rs.next()) {
        u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setCortedecaja(rs.getInt("cortedecaja")); //SI EL USUARIO PUEDE O NO HACER CORTE DE CAJA
        u.setNombre(rs.getString("nombre"));
        u.setPass(rs.getString("pass"));
        u.setUsuario(rs.getString("usuario"));
        u.setReporteador(rs.getInt("reporteador"));
        u.setVendedor(rs.getInt("vendedor"));
        u.setEliminarPagos(rs.getInt("eliminarPagos"));
        u.setAdminUsuarios(rs.getInt("adminUsuarios"));
        u.setAuditor(rs.getInt("auditor"));
        u.setModificarCantidadInventarios(rs.getInt("modificarCantidadInventarios"));
        u.setDevolucionesIlimitadas(rs.getInt("devolucionesIlimitadas"));
        u.setExportar(rs.getInt("exportar"));
      }
      rs.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return u;
  }

  public static List<Integer> findAuditores() {
    List<Integer> auditores = new ArrayList<Integer> ();
    String sql =
        "SELECT id FROM usuarios WHERE auditor=1 and activo=1 ORDER BY nombre";
    Connection con = DaoSource.getConnection();
    try {
      ResultSet rs = con.createStatement().executeQuery(sql);
      while (rs.next()) {
        auditores.add(rs.getInt("id"));
      }
      rs.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return auditores;
  }

  public static List<Integer> findNoAuditores() {
    List<Integer> auditores = new ArrayList<Integer> ();
    String sql =
        "SELECT id FROM usuarios WHERE auditor=0 and activo=1 ORDER BY nombre";
    Connection con = DaoSource.getConnection();
    try {
      ResultSet rs = con.createStatement().executeQuery(sql);
      while (rs.next()) {
        auditores.add(rs.getInt("id"));
      }
      rs.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return auditores;
  }

  public static List<Usuario> findUsuariosByNombre(String nombre) {
    List<Usuario> lista = new ArrayList<Usuario> ();
    String sql = "SELECT * FROM usuarios where nombre like '%" + nombre + "%'";
    Connection con = DaoSource.getConnectionLocal();
    Usuario u = null;
    ResultSet rs = null;
    try {
      rs = con.createStatement().executeQuery(sql);
      while (rs.next()) {
        u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setCortedecaja(rs.getInt("cortedecaja")); //SI EL USUARIO PUEDE O NO HACER CORTE DE CAJA
        u.setNombre(rs.getString("nombre"));
        u.setPass(rs.getString("pass"));
        u.setUsuario(rs.getString("usuario"));
        u.setReporteador(rs.getInt("reporteador"));
        u.setVendedor(rs.getInt("vendedor"));
        u.setEliminarPagos(rs.getInt("eliminarPagos"));
        u.setAdminUsuarios(rs.getInt("adminUsuarios"));
        u.setActivo(rs.getInt("activo"));
        u.setAdmin(rs.getInt("admin"));
        u.setAuditor(rs.getInt("auditor"));
        u.setInventarios(rs.getInt("inventarios"));
        u.setModificarCantidadInventarios(rs.getInt("modificarCantidadInventarios"));
        u.setDevolucionesIlimitadas(rs.getInt("devolucionesIlimitadas"));
        u.setExportar(rs.getInt("exportar"));
     
        lista.add(u);
      }
      rs.close();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
    return lista;
  }

  public static boolean modificarUsuario(Usuario u) {
    String sql = "UPDATE `usuarios` set `usuario`=?,`pass`=?,`nombre`=?,`vendedor`=?,`cortedecaja`=?,`auditor`=?,`activo`=?,`inventarios`=?,`admin`=?,`eliminarPagos`=?,`adminUsuarios`=? , reporteador=?, modificarCantidadInventarios=?, devolucionesIlimitadas=?, exportar=? where `id`= ?";
    
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement pst = con.prepareStatement(sql);
      pst.setString(1, u.getUsuario());
      pst.setString(2, u.getPass());
      pst.setString(3, u.getNombre());
      pst.setInt(4, u.getVendedor());
      pst.setInt(5, u.getCortedecaja());
      pst.setInt(6, u.getAuditor());
      pst.setInt(7, u.getActivo());
      pst.setInt(8, u.getInventarios());
      pst.setInt(9, u.getAdmin());
      pst.setInt(10, u.getEliminarPagos());
      pst.setInt(11, u.getAdminUsuarios());
      pst.setInt(12, u.getReporteador());
      pst.setInt(13, u.getModificarCantidadInventarios());
      pst.setInt(14, u.getDevolucionesIlimitadas());
      pst.setInt(15, u.getExportar());
      pst.setInt(16, u.getId());

      pst.execute();
      pst.close();
      return true;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }

  }

  public static boolean agregarUsuario(Usuario u) {
    String sql = "insert into `usuarios`(`usuario`,`pass`,`nombre`,`vendedor`,`cortedecaja`,`auditor`,`activo`,`inventarios`,`admin`,`eliminarPagos`,`adminUsuarios`,`reporteador`,`modificarCantidadInventarios`,`devolucionesIlimitadas`,`exportar`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement pst = con.prepareStatement(sql);
      pst.setString(1, u.getUsuario());
      pst.setString(2, u.getPass());
      pst.setString(3, u.getNombre());
      pst.setInt(4, u.getVendedor());
      pst.setInt(5, u.getCortedecaja());
      pst.setInt(6, u.getAuditor());
      pst.setInt(7, u.getActivo());
      pst.setInt(8, u.getInventarios());
      pst.setInt(9, u.getAdmin());
      pst.setInt(10, u.getEliminarPagos());
      pst.setInt(11, u.getAdminUsuarios());
      pst.setInt(12, u.getReporteador());
      pst.setInt(13, u.getModificarCantidadInventarios());
      pst.setInt(14, u.getDevolucionesIlimitadas());
      pst.setInt(15, u.getExportar());
      
      pst.execute();
      pst.close();
      return true;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public static boolean eliminarUsuario(int idUsuario) {
    String sql = "DELETE FROM usuarios WHERE id=" + idUsuario;
    Connection con = DaoSource.getConnectionLocal();
    try {
      con.createStatement().execute(sql);
      return true;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }
}
