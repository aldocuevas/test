package com.boutique.util;

import java.sql.*;
import com.boutique.dao.DaoSource;

public class ModificarClasificacionClientes {
  public ModificarClasificacionClientes() {
  }

  public static void main(String[] args) {
    //SELECT id, tieneCredito,clasificacion FROM clientes;
    Connection con = DaoSource.getConnection();
    ResultSet rs = null;
    PreparedStatement pst = null;
    Statement st = null;
    int idClasificacion = 0;
    try {
      idClasificacion = 0;

      st = con.createStatement();
      rs = st.executeQuery(
          "SELECT id, tieneCredito,clasificacion FROM clientes;");
      pst = con.prepareStatement(
          "UPDATE clientes SET clasificacion_id=?,tieneCredito=? WHERE id=?");
      while (rs.next()) {
        String clasificacion = rs.getString("clasificacion");
        String tieneCredito = rs.getString("tieneCredito");
        int idCliente = rs.getInt("id");
        if (tieneCredito.equals("AUTORIZADO") ||
            tieneCredito.equals("NO AUTORIZADO") ||
            tieneCredito.equals("CANCELADO")) {
          //SIGNIFICA QUE ES CREDITO

          if (clasificacion.equals("A")) {
            idClasificacion = 1;
          }
          else if (clasificacion.equals("B")) {
            idClasificacion = 2;
          }
          else if (clasificacion.equals("C")) {
            idClasificacion = 3;
          }
          else if (clasificacion.equals("D")) {
            idClasificacion = 4;
          }
          else if (clasificacion.equals("E")) {
            idClasificacion = 5;
          }

        }
        else if (tieneCredito.equals("CONTADO")) {
          //CLIENTE DE CONTADO
          tieneCredito="NO AUTORIZADO";
          if (clasificacion.equals("A")) {
            idClasificacion = 6;
          }
          else if (clasificacion.equals("B")) {
            idClasificacion = 7;
          }
          else if (clasificacion.equals("C")) {
            idClasificacion = 8;
          }
          else if (clasificacion.equals("D")) {
            idClasificacion = 9;
          }
          else if (clasificacion.equals("E")) {
            idClasificacion = 10;
          }
        }
        pst.setInt(1, idClasificacion);
        pst.setString(2, tieneCredito);
        pst.setInt(3, idCliente);
        pst.execute();
      }
    }
    catch (Exception ex) {
    }
  }
}
