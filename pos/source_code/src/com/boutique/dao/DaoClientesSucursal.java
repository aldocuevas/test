package com.boutique.dao;

import java.sql.*;
import java.util.*;
import com.boutique.domain.*;

public class DaoClientesSucursal {
  public DaoClientesSucursal() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  String sql;
  ResultSet rs;
  Connection conn;
  Cliente cliente = new Cliente();

  public DaoClientesSucursal(Connection conn) {
    this.conn = conn;
  }



public static ClasificacionCliente findClasificacionContado(){
  String sql = "SELECT * FROM clientes_clasificacion WHERE tipo='CONTADO' ORDER BY id DESC LIMIT 1";
  Connection con = DaoSource.getConnection();
  ClasificacionCliente cc = null;
  try {
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    System.out.println(ps.toString());
   if(rs.next()) {
      cc = new ClasificacionCliente();
      cc.setId(rs.getInt("id"));
      cc.setClasificacion(rs.getString("clasificacion"));
      cc.setTipo(rs.getString("tipo"));

    }
    rs.close();
    return cc;
  }
  catch (SQLException ex) {
    System.out.print(ex.toString());
    return null;
  }


}
public static boolean addClasificacion(ClasificacionCliente cc){
String sql="INSERT INTO clientes_clasificacion (clasificacion,tipo) VALUES ('" + cc.getClasificacion() + "','" + cc.getTipo() + "');";
  Connection con = DaoSource.getConnection();
  Statement st = null;
  try {
    st = con.createStatement();
    st.execute(sql);
    st.close();
    return true;
  }
  catch (SQLException ex) {
    System.out.println(ex.toString());
    return false;
  }
}

public static boolean updateClasificacion(ClasificacionCliente cc){
String sql="UPDATE clientes_clasificacion SET clasificacion='" + cc.getClasificacion() + "',tipo='" + cc.getTipo() + "' WHERE id=" + cc.getId() + ";";
  Connection con = DaoSource.getConnection();
  Statement st = null;
  try {
    st = con.createStatement();
   st.execute(sql);
    st.close();
    return true;
  }
  catch (SQLException ex) {

    System.out.println(ex.toString());
        return false;
  }

}
public static boolean deleteClasificacion(int id){
  String sql="DELETE FROM clientes_clasificacion WHERE id=" + id + ";";
  Connection con = DaoSource.getConnection();
  Statement st = null;
  try {
    st = con.createStatement();
    st.execute(sql);
    st.close();
    return true;
  }
  catch (SQLException ex) {
    System.out.println(ex.toString());
    return false;
  }

}


public static List<ClasificacionCliente> findClasificaciones(String pattern){
  List<ClasificacionCliente> l = new ArrayList<ClasificacionCliente>();
  String sql = "SELECT * FROM clientes_clasificacion WHERE clasificacion like '%" + pattern + "%'";
  Connection con = DaoSource.getConnection();
  try {
    PreparedStatement ps = con.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    System.out.println(ps.toString());
    while (rs.next()) {
      ClasificacionCliente cc = new ClasificacionCliente();
      cc.setId(rs.getInt("id"));
      cc.setClasificacion(rs.getString("clasificacion"));
      cc.setTipo(rs.getString("tipo"));
      l.add(cc);
    }
    rs.close();
    return l;
  }
  catch (SQLException ex) {
    System.out.print(ex.toString());
    return null;
  }

}


  public static List<ClasificacionCliente> findClasificaciones(){
    List<ClasificacionCliente> l = new ArrayList<ClasificacionCliente>();
    String sql = "SELECT * FROM clientes_clasificacion";
    Connection con = DaoSource.getConnection();
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      System.out.println(ps.toString());
      while (rs.next()) {
        ClasificacionCliente cc = new ClasificacionCliente();
        cc.setId(rs.getInt("id"));
        cc.setClasificacion(rs.getString("clasificacion"));
        cc.setTipo(rs.getString("tipo"));
        l.add(cc);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      System.out.print(ex.toString());
      return null;
    }

  }

  private static Cliente mapRsToCliente(ResultSet rs) {
    Cliente cliente = null;
    try {
      if (rs.next()) {
        cliente = new Cliente();
        cliente.setId(rs.getInt("clientes.id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setCalle(rs.getString("calle"));
        cliente.setNumero(rs.getString("numero"));
        cliente.setNumeroInterior(rs.getString("numeroInterior"));
        cliente.setCalleAledanaA(rs.getString("calleAledanaA"));
        cliente.setCalleAledanaB(rs.getString("calleAledanaB"));
        cliente.setColonia(rs.getString("colonia"));
        // cliente.setCiudad(rs.getString("ciudad"));
        cliente.setTelefono(rs.getString("telefono"));
        try {
          cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        }
        catch (SQLException ex1) {
        }
        cliente.setTallaCalzado(rs.getString("tallaCalzado"));
        cliente.setTallaConjunto(rs.getString("tallaConjunto"));
        cliente.setTieneCredito(rs.getString("tieneCredito"));
        cliente.setLimiteCredito(rs.getDouble("limiteCredito"));
        cliente.setEstadoCivil(rs.getString("estadoCivil"));
        cliente.setCasaPropia(rs.getString("casaPropia"));
        cliente.setTrabaja(rs.getString("trabaja"));
        cliente.setDomicilioTrabajo(rs.getString("domicilioTrabajo"));
        cliente.setNombreConyugue(rs.getString("nombreConyugue"));
        cliente.setOcupacionConyugue(rs.getString("ocupacionConyugue"));
        cliente.setDomicilioTrabajoConyugue(rs.getString(
            "domicilioTrabajoConyugue"));
        cliente.setPersonal1(rs.getString("personal1"));
        cliente.setPersonal2(rs.getString("personal2"));
        cliente.setComercial1(rs.getString("comercial1"));
        cliente.setComercial2(rs.getString("comercial2"));
        cliente.setComercial3(rs.getString("comercial3"));
        //    cliente.setCodigoPostal(rs.getString("codigoPostal"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
        cliente.setTelefonoTrabajo(rs.getString("telefonoTrabajo"));
        cliente.setCelular(rs.getString("celular"));
        ClasificacionCliente cc = new ClasificacionCliente();
        cc.setId(rs.getInt("clientes_clasificacion.id"));
        cc.setTipo(rs.getString("clientes_clasificacion.tipo"));
        cc.setClasificacion(rs.getString("clientes_clasificacion.clasificacion"));
        cliente.setClasificacionCliente(cc);
        cliente.setRfc(rs.getString("rfc"));
        cliente.setEmail(rs.getString("email"));
        cliente.setNoEmail(rs.getString("noEmail"));
        cliente.setFotografia(rs.getBinaryStream("fotografia"));
        cliente.setLastUpdate(rs.getTimestamp("last_update"));
      }
      return cliente;

    }
    catch (SQLException ex) {
      System.out.println("DaoClientes.mapRsToCliente(Cliente cliente):" +
                         ex.toString());
      return null;
    }

  }

  public static List<Object[]> findAllByRutaAndVentasActivas() {
    List<Object[]> l = new ArrayList<Object[]>();
    String sql = "SELECT distinct clientes.id,clientes.nombre,clientes.apellidoPaterno,clientes.apellidoMaterno,clientes.calle,clientes.numero,clientes.numeroInterior,clientes.colonia,colonias.ciudad,colonias.ruta, clientes.telefono,clientes.tieneCredito,clientes_clasificacion.clasificacion FROM clientes INNER JOIN colonias ON colonias.nombre=clientes.colonia inner join ventas on ventas.idCliente=clientes.id  INNER JOIN clientes_clasificacion ON clientes_clasificacion.id=clientes.clasificacion where ventas.status=0 and ventas.tipo=1 ORDER BY clientes.clasificacion,colonias.ruta,colonias.nombre,clientes.apellidoPaterno ";
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      Object[] row;
      System.out.println(ps.toString());
      while (rs.next()) {
        row = new Object[8];
        row[0] = rs.getString("id");
        row[1] = rs.getString("apellidoPaterno") +
            " " + rs.getString("apellidoMaterno") + " " + rs.getString("nombre");
        row[2] = rs.getString("calle") + " " + rs.getString("numero") +
            ( (rs.getString("numeroInterior").equals("")) ? "" :
             " Int. " + rs.getString("numeroInterior"));
        row[3] = rs.getString("colonia") + ", " + rs.getString("ciudad");
        row[4] = rs.getString("ruta") + " ";
        row[5] = rs.getString("telefono");
        row[6] = rs.getString("clientes_clasificacion.clasificacion");
        row[7] = rs.getString("clientes.tieneCredito");
        l.add(row);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      System.out.print(ex.toString());
      return null;
    }

  }

  public static List<Object[]> findAllByRutaAndVentasActivasAnd6MesesAtras() {
    List<Object[]> l = new ArrayList<Object[]>();
    // String sql = "SELECT distinct clientes.id,clientes.nombre,clientes.apellidoPaterno,clientes.apellidoMaterno,clientes.calle,clientes.numero,clientes.numeroInterior,clientes.colonia,colonias.ciudad,colonias.ruta, clientes.telefono FROM clientes INNER JOIN colonias ON colonias.nombre=clientes.colonia inner join ventas on ventas.idCliente=clientes.id where ventas.status=0  ORDER BY colonias.ruta,colonias.nombre,clientes.apellidoPaterno ";
    String sql = "SELECT distinct clientes.id,clientes.nombre,clientes.apellidoPaterno,clientes.apellidoMaterno,clientes.calle,clientes.numero,clientes.numeroInterior,clientes.colonia,colonias.ciudad,colonias.ruta, clientes.telefono, clientes.celular FROM clientes INNER JOIN colonias ON colonias.nombre=clientes.colonia inner join ventas on ventas.idCliente=clientes.id where ventas.status=0 OR ventas.fecha>='2008-06-15T00:00:00' ORDER BY colonias.ruta,colonias.nombre,clientes.apellidoPaterno;";
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      Object[] row;
      System.out.println(ps.toString());
      while (rs.next()) {
        row = new Object[6];
        row[0] = rs.getString("id");
        row[1] = rs.getString("apellidoPaterno") +
            " " + rs.getString("apellidoMaterno") + " " + rs.getString("nombre");
        row[2] = rs.getString("calle") + " " + rs.getString("numero") +
            ( (rs.getString("numeroInterior").equals("")) ? "" :
             " Int. " + rs.getString("numeroInterior"));
        row[3] = rs.getString("colonia") + ", " + rs.getString("ciudad");
        row[4] = rs.getString("ruta") + " ";
        row[5] = "TEL " + rs.getString("telefono") + ", CEL " +
            rs.getString("celular");

        l.add(row);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      System.out.print(ex.toString());
      return null;
    }

  }

  public static List<Object[]> findByNombreCompleto(String nombre) {
    List<Object[]> l = new ArrayList<Object[]>();
    String sql = "SELECT clientes.id,clientes.nombre,clientes.apellidoPaterno,clientes.apellidoMaterno,clientes.calle,clientes.numero,clientes.numeroInterior,clientes.colonia,colonias.ciudad,clientes.telefono,clientes.tieneCredito,clientes_clasificacion.clasificacion FROM clientes INNER JOIN clientes_clasificacion ON clientes_clasificacion.id=clientes.clasificacion INNER JOIN colonias ON colonias.nombre=clientes.colonia WHERE (clientes.nombreCompleto like ? )  ORDER BY apellidoPaterno";
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, "%" + nombre + "%");
      ResultSet rs = ps.executeQuery();
      Object[] row;
      System.out.println(ps.toString());
      while (rs.next()) {
        row = new Object[8];
        row[0] = rs.getString("id");
        row[1] = rs.getString("apellidoPaterno") +
            " " + rs.getString("apellidoMaterno") + " " + rs.getString("nombre");
        row[2] = rs.getString("calle") + " " + rs.getString("numero") +
            ( (rs.getString("numeroInterior").equals("")) ? "" :
             " Int. " + rs.getString("numeroInterior"));
        row[3] = rs.getString("colonia");
        row[4] = rs.getString("ciudad");
        row[5] = rs.getString("telefono");
        row[6] = rs.getString("clientes_clasificacion.clasificacion");
        row[7] = rs.getString("tieneCredito");

        l.add(row);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      System.out.print(ex.toString());
      return null;
    }
  }

  public static List<Object[]> findByNombreMaternoPaternoCreditoAutorizado(String nombre) {
    List<Object[]> l = new ArrayList<Object[]>();
    String sql = "SELECT clientes.id,clientes.nombre,clientes.apellidoPaterno,clientes.apellidoMaterno,clientes.calle,clientes.numero,clientes.numeroInterior,clientes.colonia,colonias.ciudad,clientes.telefono FROM clientes INNER JOIN colonias ON colonias.nombre=clientes.colonia WHERE (clientes.nombre like ?  OR clientes.apellidoPaterno like ? OR clientes.apellidoMaterno like ?) AND clientes.tieneCredito='AUTORIZADO' ORDER BY apellidoPaterno";
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, "%" + nombre + "%");
      ps.setString(2, "%" + nombre + "%");
      ps.setString(3, "%" + nombre + "%");
      ResultSet rs = ps.executeQuery();
      Object[] row;
      System.out.println(ps.toString());
      while (rs.next()) {
        row = new Object[6];
        row[0] = rs.getString("id");
        row[1] = rs.getString("apellidoPaterno") +
            " " + rs.getString("apellidoMaterno") + " " + rs.getString("nombre");
        row[2] = rs.getString("calle") + " " + rs.getString("numero") +
            ( (rs.getString("numeroInterior").equals("")) ? "" :
             " Int. " + rs.getString("numeroInterior"));
        row[3] = rs.getString("colonia");
        row[4] = rs.getString("ciudad");
        row[5] = rs.getString("telefono");

        l.add(row);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      System.out.print(ex.toString());
      return null;
    }
  }

  public static List<Object[]> findByNombreMaternoPaterno(String nombre) {
    List<Object[]> l = new ArrayList<Object[]>();
    String sql = "SELECT clientes.id,clientes.nombre,clientes.apellidoPaterno,clientes.apellidoMaterno,clientes.calle,clientes.numero,clientes.numeroInterior,clientes.colonia,colonias.ciudad,clientes.telefono FROM clientes INNER JOIN colonias ON colonias.nombre=clientes.colonia WHERE clientes.nombre like ?  OR clientes.apellidoPaterno like ? OR clientes.apellidoMaterno like ? ORDER BY nombre";
    Connection con = DaoSource.getConnectionLocal();
    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, "%" + nombre + "%");
      ps.setString(2, "%" + nombre + "%");
      ps.setString(3, "%" + nombre + "%");
      ResultSet rs = ps.executeQuery();
      Object[] row;
      System.out.println(ps.toString());
      while (rs.next()) {
        row = new Object[6];
        row[0] = rs.getString("id");
        row[1] = rs.getString("nombre") + " " + rs.getString("apellidoPaterno") +
            " " + rs.getString("apellidoMaterno");
        row[2] = rs.getString("calle") + " " + rs.getString("numero") +
            ( (rs.getString("numeroInterior").equals("")) ? "" :
             " Int. " + rs.getString("numeroInterior"));
        row[3] = rs.getString("colonia");
        row[4] = rs.getString("ciudad");
        row[5] = rs.getString("telefono");

        l.add(row);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      System.out.print(ex.toString());
      return null;
    }
  }

  public static Cliente findById(int id) {

    String sql = "SELECT clientes.*,clientes_clasificacion.* FROM clientes INNER JOIN  clientes_clasificacion ON clientes.clasificacion= clientes_clasificacion.id WHERE clientes.id=?";
    Connection con = DaoSource.getConnection();
    Cliente cliente = null;
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      cliente = mapRsToCliente(rs);
      rs.close();
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
    return cliente;

  }

  /**
   * findLast
   *
   * @return Cliente
   */
  public Cliente findLast() {
    this.sql = "SELECT * FROM clientes ORDER BY id DESC  LIMIT 1";
    try {
      rs = conn.createStatement().executeQuery(this.sql);
      /*   if (rs.next()) {
           cliente = new Cliente();
           cliente.setId(rs.getString("id"));
           cliente.setNombre(rs.getString("nombre"));
           cliente.setDomicilio(rs.getString("domicilio"));
           cliente.setColonia(rs.getString("colonia"));
           cliente.setTelefono(rs.getString("telefono"));
           cliente.setCiudad(rs.getString("ciudad"));
           cliente.setEstado(rs.getString("estado"));
           cliente.setCasaPropia(rs.getString("casaPropia"));
           cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
           cliente.setTallaCalzado(rs.getString("tallaCalzado"));
           cliente.setTallaConjunto(rs.getString("tallaConjunto"));
           cliente.setOcupacion(rs.getString("ocupacion"));
           cliente.setDomicilioTrabajo(rs.getString("domicilioTrabajo"));
           cliente.setTelefonoTrabajo(rs.getString("telefonoTrabajo"));
           cliente.setEstadoCivil(rs.getString("estadoCivil"));
           cliente.setNombreConyugue(rs.getString("nombreConyugue"));
           cliente.setOcupacionConyugue(rs.getString("ocupacionConyugue"));
       cliente.setDomicilioTrabajoConyugue(rs.getString("domicilioTrabajoConyugue"));
           cliente.setTelefonoTrabajoConyugue(rs.getString(
               "telefonoTrabajoConyugue"));
           cliente.setComercial1(rs.getString("comercial1"));
           cliente.setComercial2(rs.getString("comercial2"));
           cliente.setComercial3(rs.getString("comercial3"));
           cliente.setPersonal1(rs.getString("personal1"));
           cliente.setTelefonoPersonal1(rs.getString("telefonoPersonal1"));
           cliente.setPersonal2(rs.getString("personal2"));
           cliente.setTelefonoPersonal2(rs.getString("telefonoPersonal2"));
           cliente.setCredito(rs.getString("credito"));
           cliente.setCurp(rs.getString("curp"));
         }*/
      rs.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return null;
    }
    return cliente;

  }

  private void jbInit() throws Exception {
  }

  /*--------------FUNCION GET ERROR------------------*/
  /* public String getMsgError() {
     return this.ERR_MESSAGE;
   }
   */

}
