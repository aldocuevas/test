package com.boutique.dao;

import com.boutique.domain.Vale;
import java.sql.*;

public class DaoVales {

  public static Vale modificaryCrearNuevoVale(int idVale, double nuevoMonto, int idTerminal) {
    Vale nuevoVale = null;
    double montoNuevoVale = 0;
    int idPago = 0;
    String sql = "SELECT monto,idPago FROM vales WHERE id=" + idVale;
    Connection con = DaoSource.getConnection();

    try {
      con.setAutoCommit(false);
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      if (rs.next()) {
        montoNuevoVale = rs.getDouble("monto") - nuevoMonto;
        idPago = rs.getInt("idPago");
        nuevoVale = new Vale();
        nuevoVale.setIdPago(idPago);
        nuevoVale.setMonto(montoNuevoVale);
        nuevoVale.setUtilizado(0);

      }
      rs.close();
      sql = "UPDATE vales set monto=" + nuevoMonto + " WHERE id=" + idVale;
      if (st.executeUpdate(sql) > 0) {
        //se acualizo bien el vale.. generamos el nuevo vale
        sql = "INSERT INTO vales (monto,numero,utilizado,idPago,idTerminalCreacion) VALUES (" +
            montoNuevoVale + ",0,0," + idPago + "," + idTerminal + ");";
        st.execute(sql);
        sql = "SELECT MAX(id) as idF FROM vales";
        rs = st.executeQuery(sql);
        if (rs.next()) {
          nuevoVale.setId(rs.getInt("idF"));
        }
        rs.close();
      }
      st.close();
      con.commit();
      con.setAutoCommit(true);
      return nuevoVale;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        con.rollback();
      }
      catch (SQLException ee) {}
      return null;
    }

  }

  public static Vale findValeByPago(int idPago) {
      Vale vale = null;
      String sql = "SELECT * FROM vales WHERE idPago=" + idPago;
      Connection con = DaoSource.getConnection();
      try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
          vale = new Vale();
          vale.setId(rs.getInt("id"));
          vale.setIdPago(rs.getInt("idPago"));
          vale.setMonto(rs.getDouble("monto"));
          vale.setNumero(rs.getInt("numero"));
          vale.setUtilizado(rs.getInt("utilizado"));
        }
        rs.close();
        st.close();
        return vale;
      }
      catch (SQLException ex) {
        System.out.println(ex.toString());
        return null;
      }
  }



  public static Vale findVale(int idVale) {
    Vale vale = null;
    String sql = "SELECT * FROM vales WHERE id=" + idVale;
    Connection con = DaoSource.getConnection();
    try {
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      if (rs.next()) {
        vale = new Vale();
        vale.setId(rs.getInt("id"));
        vale.setIdPago(rs.getInt("idPago"));
        vale.setMonto(rs.getDouble("monto"));
        vale.setNumero(rs.getInt("numero"));
        vale.setUtilizado(rs.getInt("utilizado"));
      }
      rs.close();
      st.close();
      return vale;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return null;
    }
  }
}
