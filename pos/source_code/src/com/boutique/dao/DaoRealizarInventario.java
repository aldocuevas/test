package com.boutique.dao;

import java.sql.*;
import java.util.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;

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
public class DaoRealizarInventario {
  public DaoRealizarInventario() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static double findSumaMontoProductosEnBoutique(int idBoutique) {
    Connection con = DaoSource.getConnection();
    double total = 0;
    PreparedStatement pstProductos = null;
    String sql = "SELECT SUM(total) as total FROM inventario_temporal_faltante";
    ResultSet rs = null;
    try {
      pstProductos = con.prepareStatement(sql);
      rs = pstProductos.executeQuery();

      if (rs.next()) {
        total = rs.getDouble("total");
      }
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return total;
  }

  /**
   *  Metodo que regresa la lista de productos que se encuentran fisicamente en la boutique indicada
   *
   *
   * @param idBoutique int
   * @return List
   */
  public static int findSumaProductosEnBoutique(int idBoutique) {
    Connection con = DaoSource.getConnection();
    int cantidad = 0;
    PreparedStatement pstProductos = null;
    String sql = "SELECT SUM(inventario_temporal_faltante.cantidad) as cantidad FROM inventario_temporal_faltante";
    ResultSet rs = null;
    try {
      pstProductos = con.prepareStatement(sql);
      rs = pstProductos.executeQuery();

      if (rs.next()) {
        cantidad = rs.getInt("cantidad");
      }
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return cantidad;
  }

  public static List<Object[]> findProductosEncontrados() {
    List<Object[]> productos = new ArrayList<Object[]>();
    Connection con = DaoSource.getConnection();
    String sql = "SELECT * FROM inventario_temporal_encontrado;";
    ResultSet rs = null;
    try {
      rs = con.createStatement().executeQuery(sql);
      Object[] row;
      double total;
      while (rs.next()) {
        row = new Object[8];
        row[0] = rs.getInt("id");
        row[1] = "1";
        row[2] = rs.getString("nombre");
        row[3] = rs.getString("detalle");
        row[4] = rs.getString("etiqueta");
        row[5] = rs.getDate("fecha");
        row[6] = AppInstance.number.format(rs.getDouble("precioPublico"));
        total = rs.getDouble("precioPublico");
        row[7] = AppInstance.number.format(total);
        productos.add(row);
      }
      rs.close();
    }
    catch (NumberFormatException ex) {
      System.out.println(ex.toString());
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return productos;

  }

  public static List<Object[]> findProductosNoEncontrados() {
    List<Object[]> productos = new ArrayList<Object[]>();
    Connection con = DaoSource.getConnection();
    String sql = "SELECT * FROM inventario_temporal_noencontrado;";
    ResultSet rs = null;
    try {
      rs = con.createStatement().executeQuery(sql);
      Object[] row;
      double total;
      while (rs.next()) {
        row = new Object[8];
        row[0] = rs.getInt("id");
        row[1] = "1";
        row[2] = rs.getString("nombre");
        row[3] = rs.getString("detalle");
        row[4] = rs.getString("etiqueta");
        row[5] = rs.getDate("fecha");
        row[6] = AppInstance.number.format(rs.getDouble("precioPublico"));
        total = rs.getDouble("precioPublico");
        row[7] = AppInstance.number.format(total);
        productos.add(row);
      }
      rs.close();
    }
    catch (NumberFormatException ex) {
      System.out.println(ex.toString());
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return productos;

  }

  public static List<Object[]> findProductosEnStock() {
    List<Object[]> productos = new ArrayList<Object[]>();
    Connection con = DaoSource.getConnection();
    String sql = "SELECT * FROM inventario_temporal_faltante;";
    ResultSet rs = null;
    try {
      con.createStatement().execute(
          "DELETE FROM inventario_temporal_faltante WHERE cantidad=0");
      rs = con.createStatement().executeQuery(sql);
      Object[] row;
      double total;
      while (rs.next()) {
        row = new Object[8];
        row[0] = rs.getInt("id");
        row[1] = rs.getInt("cantidad");
        row[2] = rs.getString("nombre");
        row[3] = rs.getString("detalle");
        row[4] = rs.getString("etiqueta");
        row[5] = rs.getDate("fecha");
        row[6] = AppInstance.number.format(rs.getDouble("precioPublico"));
        total = Double.parseDouble(row[1].toString()) *
            rs.getDouble("precioPublico");
        row[7] = AppInstance.number.format(total);
        productos.add(row);
      }
      rs.close();
    }
    catch (NumberFormatException ex) {
      System.out.println(ex.toString());
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    return productos;

  }

  /**
   *  Metodo que regresa la lista de productos que se encuentran fisicamente en la boutique indicada
   *
   *
   * @param idBoutique int
   * @return List
   */
  public static List<Object[]> findProductosEnBoutique(int idBoutique) {
    List<Object[]> productos = new ArrayList<Object[]>();
    Connection con = DaoSource.getConnection();
    PreparedStatement pstProductos = null;
    PreparedStatement pstTemporal = null;
    String sql = "SELECT inventarios.id,inventarios.cantidad,inventarios.etiqueta,inventarios.precioPublico,tipoproductos.nombre,compras.fecha,inventarios.estilo,inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor  FROM inventarios LEFT JOIN inventarios_md ON  inventarios_md.idModeloInventario=inventarios.modelo  INNER JOIN tipoproductos ON  inventarios.idTipoProducto=tipoproductos.id INNER JOIN compras ON  inventarios.idCompra=compras.id WHERE cantidad>0 AND idBoutique=" +
        idBoutique + " AND productoDistribuido='1' ORDER BY compras.fecha";
    ResultSet rs = null;
    try {
      pstProductos = con.prepareStatement(sql);
      //CREAMOS LA TABLA TEMPORAL DE PRODUCTOS EN INVENTARIO
      sql = "CREATE TEMPORARY TABLE inventario_temporal_faltante (id int, cantidad int, nombre varchar(30), detalle varchar(122),etiqueta varchar(12), fecha DATETIME, precioPublico double(8,2),total double(8,2));";
      con.createStatement().execute(sql);

      //CREAMOS LA TABLA TEMPORAL DE PRODUCTOS ENCONTRADOS FISICAMENTE
      sql = "CREATE TEMPORARY TABLE inventario_temporal_encontrado (id int, nombre varchar(30),detalle varchar(122), etiqueta varchar(12), fecha DATETIME, precioPublico double(8,2));";
      con.createStatement().execute(sql);
      //CREAMOS LA TABLA TEMPORAL DE PRODUCTOS ENCONTRADOS FISICAMENTE Y NO ENCONTRADOS EN EL SISTEMA.
      sql = "CREATE TEMPORARY TABLE inventario_temporal_noencontrado (id int, nombre varchar(30), detalle varchar(122), etiqueta varchar(12), fecha DATETIME, precioPublico double(8,2));";
      con.createStatement().execute(sql);
      sql = "CREATE TEMPORARY TABLE inventario_temporal_inicial SELECT * FROM inventario_temporal_faltante";
      con.createStatement().execute(sql);

      sql = "INSERT INTO inventario_temporal_faltante VALUES (?,?,?,?,?,?,?,?)";
      pstTemporal = con.prepareStatement(sql);
      rs = pstProductos.executeQuery();
      Object[] row;
      double total;

      while (rs.next()) {
        String detalle = DaoInventarios.detalleProducto(rs.getString(
            "estilo"), rs.getInt("idMarca"), rs.getInt("idDescripcion"),
            rs.getInt("idColor"));
        row = new Object[8];
        row[0] = rs.getInt("id");
        row[1] = rs.getInt("cantidad");
        row[2] = rs.getString("nombre");
        row[3] = detalle;
        row[4] = rs.getString("etiqueta");
        row[5] = rs.getDate("fecha");
        row[6] = AppInstance.number.format(rs.getDouble("precioPublico"));
        total = Double.parseDouble(row[1].toString()) *
            rs.getDouble("precioPublico");
        row[7] = AppInstance.number.format(total);
        productos.add(row);
        pstTemporal.setInt(1, rs.getInt("id"));
        pstTemporal.setInt(2, rs.getInt("cantidad"));
        pstTemporal.setString(3, rs.getString("nombre"));
        pstTemporal.setString(4, detalle);
        pstTemporal.setString(5, rs.getString("etiqueta"));
        pstTemporal.setDate(6, rs.getDate("fecha"));
        pstTemporal.setDouble(7, rs.getDouble("precioPublico"));
        pstTemporal.setDouble(8, total);
        pstTemporal.execute();
      }
    }
    catch (NumberFormatException ex) {
      System.out.println(ex.toString());
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
    }
    //AHORA CREAMOS LA TABLA TEMPORAL CON LOS DATOS DEL INVENTARIO, DE LA CUAL IREMOS BUSCANDO LOS PRODUCTOSS
    //Y DE AHI DESCONTANDO
    return productos;
  }

  public static boolean addProductoEncontrado(ProductoInventario p) {
    String sql =
        "INSERT INTO inventario_temporal_encontrado VALUES (?,?,?,?,?,?);";
    Connection con = DaoSource.getConnection();
    try {
      PreparedStatement pst = con.prepareStatement(sql);
      pst.setInt(1, p.getId());
      pst.setString(2, p.getDescripcion());
      pst.setString(3, p.getDetalle());
      pst.setString(4, p.getEtiqueta());
      pst.setTimestamp(5, new java.sql.Timestamp(p.getFecha().getTime()));
      pst.setDouble(6, p.getPrecioUnitario());
      int i = pst.executeUpdate();
      pst.close();
      if (i > 0) {
        return true;
      }
      return false;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public static ProductoInventario findByEtiquetaOnTemporal(String etiqueta) {
    String sql = "SELECT * FROM inventario_temporal_faltante WHERE etiqueta='" +
        etiqueta + "' AND cantidad>0;";
    ProductoInventario producto = null;
    Connection con = DaoSource.getConnection();
    ResultSet rsProducto = null;
    Statement st = null;
    try {
      st = con.createStatement();
      rsProducto = st.executeQuery(sql);
      if (rsProducto.next()) {
        producto = new ProductoInventario();
        producto.setCantidad(rsProducto.getInt("cantidad"));
        producto.setEtiqueta(etiqueta);
        producto.setDescripcion(rsProducto.getString("nombre"));
        producto.setFecha(rsProducto.getDate("fecha"));
        producto.setPrecioUnitario(rsProducto.getDouble("precioPublico"));
        producto.setId(rsProducto.getInt("id"));
        producto.setDetalle(rsProducto.getString("detalle"));
      }
      rsProducto.close();
      st.close();
      return producto;
    }
    catch (SQLException ex) {
      return null;
    }

  }

  /**
   * descontarProductoEncontrado
   *
   * @param p ProductoInventario
   */
  public static int descontarProductoEncontrado(ProductoInventario p) {
    Connection con = DaoSource.getConnection();
    String sql =
        "UPDATE inventario_temporal_faltante SET  cantidad=cantidad-1 WHERE  id=" +
        p.getId();
    try {
      Statement st = con.createStatement();
      int afectados = st.executeUpdate(sql);
      st.close();
      return afectados;
    }
    catch (SQLException ex) {
      return 0;
    }

  }

  /**
   * findProductoNoEncontrado
   *
   * @param etiqueta String
   * @param idBoutique int
   * @return ProductoInventario
   */
  public static ProductoInventario findProductoNoEncontrado(String etiqueta,
      int idBoutique) { //SI NO ES ENCONTRADO, BUSCAMOS LOS DATOS EN LA TABLA DE INVENTARIOS, DE SER POSIBLE DONDE LA BOUTIQUE CORRESPONDE
    //SI NO SE ENCONTRO EN LA BOUTIQUE CORRESPONDIENTE, LO BUSCAMOS EN OTRA TABLA


    ProductoInventario p = null;
    String sql = "SELECT inventarios.id,inventarios.cantidad,inventarios.etiqueta,inventarios.precioPublico,tipoproductos.nombre,compras.fecha,inventarios.estilo,inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor FROM inventarios LEFT JOIN inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario INNER JOIN tipoproductos ON  inventarios.idTipoProducto=tipoproductos.id INNER JOIN compras ON  inventarios.idCompra=compras.id WHERE etiqueta='" +
        etiqueta + "' AND idBoutique=" + idBoutique +
        " AND productoDistribuido='1'";
    Connection con = DaoSource.getConnection();
    Statement st = null;
    ResultSet rs = null;
    try {
      st = con.createStatement();
      rs = st.executeQuery(sql);
      if (rs.next()) {
        p = new ProductoInventario();
        p.setId(rs.getInt("id"));
        p.setCantidad(rs.getInt("cantidad"));
        p.setDescripcion(rs.getString("nombre"));
        p.setDetalle(DaoInventarios.detalleProducto(rs.getString("estilo"),
            rs.getInt("idMarca"), rs.getInt("idDescripcion"),
            rs.getInt("idColor")));
        p.setEtiqueta(etiqueta.toUpperCase());
        p.setFecha(rs.getDate("fecha"));
        p.setPrecioUnitario(rs.getDouble("precioPublico"));
        p.setTotal(rs.getDouble("precioPublico"));
      }
      else {
        sql = "SELECT inventarios.estilo,inventarios.id,inventarios.cantidad,inventarios.etiqueta,inventarios.precioPublico,tipoproductos.nombre,compras.fecha,inventarios_md.idMarca,inventarios_md.idDescripcion,inventarios_md.idColor  FROM inventarios INNER JOIN  inventarios_md ON inventarios.modelo=inventarios_md.idModeloInventario  INNER JOIN tipoproductos ON  inventarios.idTipoProducto=tipoproductos.id INNER JOIN compras ON  inventarios.idCompra=compras.id WHERE etiqueta='" +
            etiqueta + "'";
        rs.close();
        st.close();
        st = con.createStatement();
        rs = st.executeQuery(sql);
        if (rs.next()) { //ARMAMOS EL PRODUCTO PARA AGREGAR A TABLA DE INVENTARIO SI ES QUE ASI LO DECIDE EL AUDITOR
          p = new ProductoInventario();
          p.setId(rs.getInt("id"));
          p.setCantidad(rs.getInt("cantidad"));
          p.setDescripcion(rs.getString("nombre"));
          p.setDetalle(DaoInventarios.detalleProducto(rs.getString(
              "estilo"), rs.getInt("idMarca"), rs.getInt("idDescripcion"),
              rs.getInt("idColor")));
          p.setEtiqueta(etiqueta.toUpperCase());
          p.setFecha(rs.getDate("fecha"));
          p.setPrecioUnitario(rs.getDouble("precioPublico"));
          p.setTotal(rs.getDouble("precioPublico"));
        }

      }
      rs.close();
      st.close();

      return p;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * addProductoNoEncontrado
   *
   * @param p ProductoInventario
   */
  public static boolean addProductoNoEncontrado(ProductoInventario p) { //UNA VEZ ENCONTRADO, SE INSERTA EN LA TABLA TEMPORAL DE NO ENCONTRADO
    String sql =
        "INSERT INTO inventario_temporal_noencontrado VALUES (?,?,?,?,?,?);";
    Connection con = DaoSource.getConnection();
    try {
      PreparedStatement pst = con.prepareStatement(sql);
      pst.setInt(1, p.getId());
      pst.setString(2, p.getDescripcion());
      pst.setString(3, p.getDetalle());
      pst.setString(4, p.getEtiqueta());
      pst.setTimestamp(5, new java.sql.Timestamp(p.getFecha().getTime()));
      pst.setDouble(6, p.getPrecioUnitario());
      int i = pst.executeUpdate();
      pst.close();
      if (i > 0) {
        return true;
      }
      return false;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }

  }

  private void jbInit() throws Exception {
  }

  /**
   * registrarInventario
   *
   * @param inventario Inventario
   */
  public static int registrarInventario(Inventario inventario) {
    //AGREGAR EL NUEVO INVENTARIO A LA TABLA DEFINITIVA.
    //AGREGAR LA LISTA DE PRODUCTOS INICIAL Y LA LISTA DE PRODUCTOS FALTANTES Y LA DE NO ENCONTRADOS A LA BASE DE DATOS.
    Connection con = DaoSource.getConnection();
    int idRevision = -1;
    String sql = "INSERT INTO revision_inventarios (fecha,idBoutique,idAuditor,idEmpleado,totalProductosInicial,totalProductosFaltantes,totalProductosNoEncontrados,montoInicial,montoFaltante,montoNoEncontrado) VALUES (NOW(),?,?,?,?,?,?,?,?,?);";
    PreparedStatement pst = null;
    try {
      con.setAutoCommit(false);
      pst = con.prepareStatement(sql);
      pst.setInt(1, inventario.getBoutique().getId());
      pst.setInt(2, inventario.getAuditor().getId());
      pst.setInt(3, inventario.getEmpeladoPresente().getId());
      pst.setInt(4, inventario.cantidadProductosInicial);
      pst.setInt(5, inventario.cantidadProductosFaltantes);
      pst.setInt(6, inventario.cantidadProductosNoEncontrados);
      pst.setDouble(7, inventario.montoInicial);
      pst.setDouble(8, inventario.montoFaltante);
      pst.setDouble(9, inventario.montoNoEncontrado);
      pst.execute();
      pst.close();
      pst = null;
      ResultSet rs = con.createStatement().executeQuery(
          "SELECT MAX(id) as id FROM revision_inventarios");

      if (rs.next()) {
        idRevision = rs.getInt("id");
      }
      if (idRevision == 0) {
        con.rollback();
        con.setAutoCommit(true);
        return idRevision;
      }
      //AGREGAR LA LISTA DE PRODUCTOS INICIAL Y LA LISTA DE PRODUCTOS FALTANTES Y LA DE NO ENCONTRADOS A LA BASE DE DATOS.
      //cantidad int, nombre varchar(30), etiqueta varchar(12), fecha DATETIME, precioPublico double(8,2)
      sql = "SELECT id,cantidad FROM inventario_temporal_inicial";
      ResultSet rsInicial = con.createStatement().executeQuery(sql);
      sql = "INSERT INTO inventarios_productos_inicial (idInventario,idRevisionInventario,cantidad) VALUES (?,?,?);";
      pst = con.prepareStatement(sql);
      pst.setInt(2, idRevision);
      while (rsInicial.next()) {
        pst.setInt(1, rsInicial.getInt("id"));
        pst.setInt(3, rsInicial.getInt("cantidad"));
        pst.execute();
      }
      pst.close();
      rsInicial.close();
      sql = "SELECT id FROM inventario_temporal_noencontrado";
      rsInicial = con.createStatement().executeQuery(sql);
      sql = "INSERT INTO inventarios_productos_noencontrados(idInventario,idRevisionInventario,cantidad) VALUES (?,?,1);";
      pst = con.prepareStatement(sql);
      pst.setInt(2, idRevision);
      while (rsInicial.next()) {
        pst.setInt(1, rsInicial.getInt("id"));
        pst.execute();
      }
      pst.close();
      rsInicial.close();

      sql = "SELECT id,cantidad FROM inventario_temporal_faltante";
      rsInicial = con.createStatement().executeQuery(sql);
      sql = "INSERT INTO inventarios_productos_faltantes (idInventario,idRevisionInventario,cantidad) VALUES (?,?,?);";
      pst = con.prepareStatement(sql);
      pst.setInt(2, idRevision);
      while (rsInicial.next()) {
        pst.setInt(1, rsInicial.getInt("id"));
        pst.setInt(3, rsInicial.getInt("cantidad"));
        pst.execute();
      }

      con.createStatement().execute(
          "DROP TEMPORARY TABLE inventario_temporal_faltante;");
      con.createStatement().execute(
          "DROP TEMPORARY TABLE inventario_temporal_encontrado;");
      con.createStatement().execute(
          "DROP TEMPORARY TABLE inventario_temporal_noencontrado;");
      con.createStatement().execute(
          "DROP TEMPORARY TABLE inventario_temporal_inicial");
      pst.close();
      rsInicial.close();
      con.commit();
      con.setAutoCommit(true);
    }
    catch (SQLException ex) {
      try {
        con.rollback();
      }
      catch (SQLException ex1) {
      }
      System.out.println(ex.toString());

    }
    return idRevision;

  }

  /**
   * registrarMermas
   *
   * @param list List
   */
  public static boolean registrarMermas(List<?> list, int idRevisionInventario,
                                        int idBoutique) {
    String sql = "INSERT INTO inventarios_mermas (idInventario,cantidad,idRevisionInventario) VALUES (?,?,?);";
    Connection con = DaoSource.getConnection();
    Object[] row = null;
    PreparedStatement pst = null;
    PreparedStatement pstDescontar = null;
    try {
      pst = con.prepareStatement(sql);
      sql = "UPDATE inventarios SET cantidad=cantidad-? WHERE id=?";
      pstDescontar = con.prepareStatement(sql);
      con.setAutoCommit(false);
      for (Object r : list) {
        row = (Object[]) r;
        int id = Integer.valueOf(row[0].toString());
        int cantidad = Integer.valueOf(row[1].toString());
        pst.setInt(1, id);
        pst.setInt(2, cantidad);
        pst.setInt(3, idRevisionInventario);
        pst.execute();
        pstDescontar.setInt(1, cantidad);
        pstDescontar.setInt(2, id);
        pstDescontar.execute();
        //POR CADA UNO LO BUSCAMOS EN EL INVENTARIO REAL Y DESCONTAMOS EL PRODUCTO
      }

      con.commit();
      con.setAutoCommit(true);
      return true;
    }
    catch (NumberFormatException ex) {
      System.out.println(ex.toString());
      try {
        con.rollback();
        con.setAutoCommit(true);
      }
      catch (SQLException ee) {
        ee.printStackTrace();
      }
      return false;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        con.rollback();
        con.setAutoCommit(true);
      }
      catch (SQLException ee) {
        ee.printStackTrace();
      }
      return false;
    }
  }

  public static boolean registrarProductosNoEncontrados(List<?> lista,
      int idBoutique) {
    Object[] row = null;
    String sql = "SELECT * FROM inventarios WHERE id=?";
    PreparedStatement pst = null;
    PreparedStatement pstUpdate = null;
    PreparedStatement pstCopy = null;
    ResultSet rs = null;
    Connection con = DaoSource.getConnection();
    try {
      pst = con.prepareStatement(sql);

      pstUpdate = con.prepareStatement(
          "UPDATE inventarios  SET cantidad=cantidad+? WHERE id=?");
      pstCopy = con.prepareStatement("INSERT INTO inventarios (idTipoProducto,modelo,talla,idBoutique,cantidad,precioCosto,precioPublico,clave,idCompra,etiqueta,productoDistribuido) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
      int idInventario = 0;
      int idB;
      con.setAutoCommit(false);
      for (Object obj : lista) {
        row = (Object[]) obj;
//    id,cantidad,descropcion,etiqueta,fecha de adquisicion?,precioalpublico, total.
        idInventario = ( (Integer) row[0]).intValue();
        pst.setInt(1, idInventario);
        rs = pst.executeQuery();
        if (rs.next()) {
          //REVISAMOS SI EL ID DE LA BOUTIQUE ES LA MISMA
          idB = rs.getInt("idBoutique");
          if (idB == idBoutique) {
            pstUpdate.setInt(1, Integer.parseInt(row[1].toString()));
            pstUpdate.setInt(2, idInventario);
            pstUpdate.execute();
          }
          else { //boutique distinta... copiamos los datos del registro e insertamos uno nuevo con la boutique.
            Producto p = DaoInventarios.findById(idInventario);
            p.setIdBoutique(idBoutique);
            pstCopy.setInt(1, p.getIdTipoProducto());
            pstCopy.setLong(2, p.getModelo());
            pstCopy.setString(3, p.getTalla());
            pstCopy.setInt(4, p.getIdBoutique());
            pstCopy.setInt(5, Integer.parseInt(row[1].toString()));
            pstCopy.setDouble(6, p.getPrecioCosto());
            pstCopy.setDouble(7, p.getPrecioPublico());
            pstCopy.setInt(8, p.getClave());
            pstCopy.setInt(9, p.getIdCompra());
            pstCopy.setString(10, p.getEtiqueta());
            pstCopy.setString(11, "1");
            System.out.println(pstCopy.toString());
            pstCopy.execute();

          }
        } //fin del if rs.next
      } // fin del for
      con.commit();
      con.setAutoCommit(true);
      return true;
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        con.rollback();
      }
      catch (SQLException ee) {
      }
      return false;
    }

  }
}
