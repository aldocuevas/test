package com.boutique.dao;

import java.sql.*;
import java.util.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;

public class DaoRotacionInventarios {

  public static RotacionInventario inciarRotacion(int boutiqueFinal) {
    //PRIMERO BUSCAMOS SI HAY ALGUNA ROTACION ABIERTA EN ESTA BOUTIQUE.
    Connection con = DaoSource.getConnection();
    RotacionInventario ri = null;
    Statement st = null;

    ResultSet rs = null;

    String sql =
        "SELECT * FROM lote_rotacion WHERE status='0' and boutiqueInicial=" +
        AppInstance.boutique().getId() + " AND boutiqueFinal=" + boutiqueFinal;
    try {
      st = con.createStatement();

      rs = st.executeQuery(sql);
      //SI HAY ALGUN LOTE ABIERTO, GENERAMOS EL OBJETO DE ROTACION DE INVENTARIO Y ASIGNAMOS LOS DATOS.
      if (rs.next()) {
        ri = new RotacionInventario();
        ri.setId(rs.getInt("id"));
        ri.setFechaInicio(rs.getTimestamp("fechaInicio"));
        ri.setFechaCierre(rs.getTimestamp("fechaCierre"));
        ri.setStatus(rs.getString("status"));
        ri.setBoutiqueInicial(rs.getInt("boutiqueInicial"));
        ri.setBoutiqueFinal(rs.getInt("boutiqueFinal"));
        //BUSCAMOS TODOS LOS ARTICULOS QUE HAY EN LA TABLA DE PRODUCTOS DE ROTACION CON ESE ID DE LOTE
//             "id", "Etiqueta", "Tipo de producto", "P. al público","Registro"};
        ri.setProductos(findByIdLote(ri.getId()));
      }
      else {
        //GENERAMOS UN REGISTRO DE ROTACION DE INVENTARIO;
        ri = DaoRotacionInventarios.iniciarRotacion(boutiqueFinal);
      }
      rs.close();
      return ri;

    }
    catch (SQLException ex) {
      System.err.println(ex.toString());
      return null;
    }
  }

  public static List<ProductoRotacion> findByIdLote(int idLote) {

    Statement st2 = null;
    Connection con = DaoSource.getConnection();
    List<ProductoRotacion> productos = new ArrayList<ProductoRotacion> ();
    ResultSet rs = null;
    ProductoRotacion p = null;
    String sql =
        "SELECT inventarios_rotacion.* from inventarios_rotacion  WHERE idLote=" +
        idLote;
    try {
      st2 = con.createStatement();
      rs = st2.executeQuery(sql);
      while (rs.next()) {
        p = new ProductoRotacion();
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
        p.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        p.setIdVendedor(rs.getInt("idVendedor"));
        p.setIdLote(rs.getInt("idLote"));
        productos.add(p);
      }
      rs.close();
    }
    catch (SQLException ex) {
    }
    return productos;
  }

  public static void regresarProducto(String etiqueta, int idLote,
                                      int boutiqueInicial) {

    //ACTUALIZAMOS CANTIDAD=CANTIDAD+1
    //BORRAMOS DE EL INVENTARIO DE ROTACION EL PRODUCTO
    //ACTUALIZAMOS LA LISTA DE PRODUCTOS EN EL LOTE DE ROTACION.

    //BUSCAMOS EL PRODUCTO EN LA BOUTIQUE LOCAL DISTRIBUIDO Y CON CANTIDAD MAYOR A CERO SOLO UN RENGLON
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    PreparedStatement psUpdate = null;
    PreparedStatement psDelete = null;
    ResultSet rs = null;

    try {
      con.setAutoCommit(false);
      //PRIMERO BUSCAMOS EL PRODUCTO EN EL INVENTARIO DE ROTACION CON EL IDLOTE CORRESPONDIENTE
      String sql = "SELECT id FROM inventarios WHERE productoDistribuido='1' AND idBoutique=? AND etiqueta=? LIMIT 1";
      ps = con.prepareStatement(sql);
      ps.setInt(1, boutiqueInicial);
      ps.setString(2, etiqueta);
      rs = ps.executeQuery();
      if (rs.next()) { //SI SE ENCONTRO EL PRODUCTO EN EL INVENTARIO ORIGINAL, ACTUALIZAMOS LA CANTIDAD MAS UNO
        sql = "UPDATE inventarios SET cantidad=cantidad+1 WHERE id=?";
        psUpdate = con.prepareStatement(sql);
        psUpdate.setInt(1, rs.getInt("id"));
        psUpdate.execute();
        //AHORA BORRAMOS DEL INVENTARIO TEMPORAL EL PRODUCTO CON LA ETIQUETA, LIMITADO A 1
        sql = "DELETE FROM inventarios_rotacion WHERE etiqueta=? LIMIT 1";
        psDelete = con.prepareStatement(sql);
        psDelete.setString(1, etiqueta);
        psDelete.execute();
      } //SI NO SE ENCONTRO PUES NO HACEMOS NADA

      con.commit();
      con.setAutoCommit(true);

      rs.close();
      ps.close();
    }
    catch (SQLException ex) {
      try {
        con.rollback();
      }
      catch (SQLException e) {}
      System.err.println(ex.toString());
    }

  }

  public static RotacionInventario iniciarRotacion(int boutiqueFinal) {
    RotacionInventario ri = null;
    String sql =
        "INSERT INTO lote_rotacion (fechaInicio,boutiqueInicial,boutiqueFinal) VALUES (NOW()," +
        AppInstance.boutique().getId() + "," + boutiqueFinal + ")";
    Connection con = DaoSource.getConnection();
    Statement ps = null;
    Statement st = null;
    ResultSet rs = null;
    try {
      ps = con.createStatement();
      ps.execute(sql);
      sql =
          "SELECT * FROM lote_rotacion WHERE status='0' and boutiqueInicial=" +
          AppInstance.boutique().getId() + " AND boutiqueFinal=" +
          boutiqueFinal;

      st = con.createStatement();

      rs = st.executeQuery(sql);
      //SACAMOS EL LOTE NUEVO
      if (rs.next()) {
        ri = new RotacionInventario();
        ri.setId(rs.getInt("id"));
        ri.setFechaInicio(rs.getTimestamp("fechaInicio"));
        ri.setFechaCierre(rs.getTimestamp("fechaCierre"));
        ri.setStatus(rs.getString("status"));
        ri.setBoutiqueInicial(rs.getInt("boutiqueInicial"));
        ri.setBoutiqueFinal(rs.getInt("boutiqueFinal"));
      }
      rs.close();

    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return null;
    }
    return ri;

  }

  public static boolean confirmarRotacion() {
    return false;
  }

  public static boolean cerrarRotacion(int idRotacion, int boutiqueFinal) {
    //ACTUALIZAMOS EL STATUS DEL LOTE Y HE INDICAMOS LA FECHA DE CIERRE
    try {
      //BUSCAMOS EL PRODUCTO EN LA BOUTIQUE LOCAL DISTRIBUIDO Y CON CANTIDAD MAYOR A CERO SOLO UN RENGLON
      Connection con = DaoSource.getConnection();
      PreparedStatement psUpdate = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      con.setAutoCommit(false);
      //PRIMERO BUSCAMOS EL PRODUCTO EN EL INVENTARIO DE ROTACION CON EL IDLOTE CORRESPONDIENTE
      String sql =
          "UPDATE lote_rotacion SET status='1', fechaCierre=NOW() WHERE id=?";
      psUpdate = con.prepareStatement(sql);
      psUpdate.setInt(1, idRotacion);
      psUpdate.execute();
      //SELECCIONAMOS TODOS LOS ARTICULOS DEL LOTE DE ROTACION Y LOS METEMOS EN LOS INVENTARIOS REALES EN LA BOUTIQUE DESTINO
      sql = "SELECT * FROM inventarios_rotacion WHERE idLote=" + idRotacion;
      rs = con.createStatement().executeQuery(sql);
      sql = "INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
      ps = con.prepareStatement(sql);
      while (rs.next()) {
        //EJECUTAMOS LA INSERCION
        ps.setInt(1, rs.getInt("idTipoProducto"));
        ps.setLong(2, rs.getLong("modelo"));
        ps.setString(3, rs.getString("talla"));
        ps.setInt(4, boutiqueFinal);
        ps.setInt(5, 1);
        ps.setDouble(6, rs.getDouble("precioCosto"));
        ps.setDouble(7, rs.getDouble("precioPublico"));
        ps.setInt(8, rs.getInt("clave"));
        ps.setInt(9, rs.getInt("idCompra"));
        ps.setString(10, rs.getString("etiqueta"));
        ps.setString(11, "1");
        ps.execute();
      }

      con.commit();
      con.setAutoCommit(true);
      rs.close();
      ps.close();
      psUpdate.close();
      return true;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return false;
    }
  }

  /**
   * agregarProductoaRotar
   *
   * @param etiqueta String
   */
  public static void agregarProductoaRotar(String etiqueta, int idLote,
                                           int idVendedor, int idTerminal) {
    //BUSCAMOS EL PRODUCTO EN LA BOUTIQUE LOCAL DISTRIBUIDO Y CON CANTIDAD MAYOR A CERO SOLO UN RENGLON
    Connection con = DaoSource.getConnection();
    Producto p;
    PreparedStatement ps = null;
    PreparedStatement psUpdate = null;
    ResultSet rs = null;
    try {
      con.setAutoCommit(false);
      String sql = "SELECT * FROM inventarios WHERE idBoutique=? AND cantidad>0 AND productoDistribuido='1' AND etiqueta=? LIMIT 1";

      ps = con.prepareStatement(sql);
      ps.setInt(1, AppInstance.boutique().getId());
      ps.setString(2, etiqueta);
      rs = ps.executeQuery();
      if (rs.next()) {
        p = new Producto();
        p.setCantidad(rs.getInt("cantidad"));
        p.setId(rs.getInt("id"));
        p.setClave(rs.getInt("clave"));
        p.setEtiqueta(rs.getString("etiqueta"));
        p.setIdBoutique(rs.getInt("idBoutique"));
        p.setIdCompra(rs.getInt("idCompra"));
        p.setIdTipoProducto(rs.getInt("idTipoProducto"));
        p.setModelo(rs.getInt("modelo"));
        p.setPrecioCosto(rs.getDouble("precioCosto"));
        p.setPrecioPublico(rs.getDouble("precioPublico"));
        p.setTalla(rs.getString("talla"));
        //TENEMOS EL PRODUCTO    //SI SI ESTA GENERAMOS EL REGISTRO EN LA TABLA IDENTICA PER TEMPORAL DE ROTACION EL MISMO DATO DEL INVENTARIO CON CANTIDAD QUE TIENE EL ID DE LOTE APARTE
        sql = "INSERT INTO inventarios_rotacion (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido,idLote,idVendedor,fechaRegistro,idTerminal) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?);";

        ps = con.prepareStatement(sql);
        con.setAutoCommit(false);
        ps.setInt(1, p.getIdTipoProducto());
        ps.setLong(2, p.getModelo());
        ps.setString(3, p.getTalla());
        ps.setInt(4, p.getIdBoutique());
        ps.setInt(5, 1);
        ps.setDouble(6, p.getPrecioCosto());
        ps.setDouble(7, p.getPrecioPublico());
        ps.setInt(8, p.getClave());
        ps.setInt(9, p.getIdCompra());
        ps.setString(10, etiqueta);
        ps.setString(11, "1");
        ps.setInt(12, idLote);
        ps.setInt(13, idVendedor);
        ps.setInt(14, idTerminal);
        ps.execute();
        //DESCONTAMOS EN UNO LA CANTIDAD EN EL INVENTARIO DE ESTA TIENDA.
        sql = "UPDATE inventarios SET cantidad=cantidad-1 WHERE id=?";
        psUpdate = con.prepareStatement(sql);
        psUpdate.setInt(1, p.getId());
        psUpdate.execute();
      }
      con.commit();
      con.setAutoCommit(true);

      rs.close();
      ps.close();

    }
    catch (SQLException ex) {
      try {
        con.rollback();
      }
      catch (SQLException e) {}
      System.err.println(ex.toString());
    }
  }

}
