package com.boutique.dao;

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
import com.boutique.domain.*;
import java.util.*;
import java.sql.*;
public class DaoLeyendas {
  public DaoLeyendas() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }
  public static Map<Integer,Leyenda> findNotasLeyendas(int idBoutique){
    Map<Integer,Leyenda> leyendas = new HashMap<Integer,Leyenda>();
    Leyenda l=null;

    String sql= "SELECT * from leyendas where idBoutique=" + idBoutique;
    Connection con = DaoSource.getConnection();
    ResultSet rs=null;
    try {
      rs = con.createStatement().executeQuery(sql);
      while (rs.next()) {
        l = new Leyenda();
        l.setId(rs.getInt("id"));
        l.setTipo(rs.getInt("tipoVenta"));
        l.setLeyenda(rs.getString("leyenda"));
        leyendas.put(l.getTipo(), l);
      }
      rs.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return leyendas;
  }

  private void jbInit() throws Exception {
  }
}
