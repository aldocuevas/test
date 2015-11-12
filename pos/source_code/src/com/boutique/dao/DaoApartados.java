package com.boutique.dao;
import java.util.*;
import com.boutique.domain.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
public class DaoApartados {

  public static List<VentaApartado> findByNombre(String nombre, int idBoutique){
    List<VentaApartado> lista = new ArrayList<VentaApartado>();
    Connection con = DaoSource.getConnection();
     PreparedStatement ps = null;
     ResultSet rs = null;
    try {
      ps = con.prepareStatement("SELECT ventas.*,venta_apartado_datos.* FROM ventas INNER JOIN venta_apartado_datos ON ventas.id=venta_apartado_datos.idVenta  WHERE ventas.tipo=2 AND ventas.status=0 AND venta_apartado_datos.cliente like ? AND ventas.idBoutique=?");
      ps.setString(1, "%" + nombre + "%");
      ps.setInt(2,idBoutique);
      rs = ps.executeQuery();
      while (rs.next()) {
        //Obtenemos el nombre, domicilio y cliente
        VentaApartado v = new VentaApartado();
        v.setCliente( rs.getString("venta_apartado_datos.cliente"));
        v.setDomicilio(rs.getString("venta_apartado_datos.domicilio"));
        v.setEnCorte(rs.getInt("ventas.enCorte"));
        v.setFecha(rs.getTimestamp("ventas.fecha"));
        v.setId(rs.getInt("ventas.id"));
        v.setIdVendedor(rs.getInt("ventas.idVendedor"));
        v.setStatus(rs.getInt("ventas.status"));
        v.setTelefono(rs.getString("venta_apartado_datos.telefono"));
        v.setIdCliente(rs.getInt("ventas.idCliente"));
        lista.add(v);

      }
      rs.close();
      ps.close();
//      con.close();
      return lista;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try{
        if (con != null) {
          con.close();
        }
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();
        }
      }catch(SQLException ex1){
      }
      return null;
    }
  }
}
