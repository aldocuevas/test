package com.boutique.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.boutique.domain.ClasificacionCliente;
import com.boutique.domain.Cliente;

public class DaoClientesCentral {
  String sql;
  ResultSet rs;
  Connection conn;
  Cliente cliente = new Cliente();
  private static long sizeFoto = 0;
  public DaoClientesCentral() {

  }

  public DaoClientesCentral(Connection conn) {
    this.conn = conn;
  }

  public static boolean aplicarDescuentoEmpleado(double porcentaje) {
    String sql = "SELECT clientes.id from clientes INNER JOIN clientes_clasificacion ON clientes.clasificacion=clientes_clasificacion.id WHERE clientes_clasificacion.clasificacion='EMPLEADO'";
    Connection con = DaoSource.getConnection();
    ResultSet rs = null;
    ResultSet rs2 = null;
    try {
      rs = con.createStatement().executeQuery(sql);
      while (rs.next()) {
        int id = rs.getInt("clientes.id");
        sql = "SELECT descuento20Empleadas(" + id + ",20) as result;";
        rs2 = con.createStatement().executeQuery(sql);
        rs2.close();
        rs2 = null;
      }
      rs.close();
      return true;
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public static boolean agregarCliente(Cliente c, long size) {
    sizeFoto = size;
    return agregarCliente(c);
  }

  /*--------------METODOS ADD,REMOVE,UPDATE----------*/

  public static boolean agregarCliente(Cliente c) {
    String sql = "INSERT INTO `clientes` (`nombre`,`calle`,`numero`,`calleAledanaA` , `calleAledanaB` , `colonia` ,  `telefono` , `fechaNacimiento` , `tallaCalzado` , `tallaConjunto` , `tieneCredito` , `limiteCredito` , `estadoCivil` , `casaPropia` , `trabaja` , `domicilioTrabajo` , `nombreConyugue` , `ocupacionConyugue` , `domicilioTrabajoConyugue` , `personal1` , `personal2` , `comercial1` , `comercial2` , `comercial3` ,  `apellidoPaterno`,`apellidoMaterno`,`telefonoTrabajo`,numeroInterior,celular,ocupacion,nombreEmpresa,nombreEmpresaConyugue,familiar1,familiar2,clasificacion,email,noEmail,fotografia,rfc) VALUES " +
        "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    //Actualizamos el directorio local
    java.sql.PreparedStatement ps = null;
    Connection con = null;
    try {

      con = DaoSource.getConnection();
      ps = con.prepareStatement(sql);
      ps.setString(1, c.getNombre());
      ps.setString(2, c.getCalle());
      ps.setString(3, c.getNumero());
      ps.setString(4, c.getCalleAledanaA());
      ps.setString(5, c.getCalleAledanaB());
      ps.setString(6, c.getColonia());

      ps.setString(7, c.getTelefono());
      ps.setDate(8, c.getFechaNacimiento());
      ps.setString(9, c.getTallaCalzado());
      ps.setString(10, c.getTallaConjunto());
      ps.setString(11, c.getTieneCredito());
      ps.setDouble(12, c.getLimiteCredito());
      ps.setString(13, c.getEstadoCivil());
      ps.setString(14, c.getCasaPropia());
      ps.setString(15, c.getTrabaja());
      ps.setString(16, c.getDomicilioTrabajo());
      ps.setString(17, c.getNombreConyugue());
      ps.setString(18, c.getOcupacionConyugue());
      ps.setString(19, c.getDomicilioTrabajoConyugue());
      ps.setString(20, c.getPersonal1());
      ps.setString(21, c.getPersonal2());
      ps.setString(22, c.getComercial1());
      ps.setString(23, c.getComercial2());
      ps.setString(24, c.getComercial3());

      ps.setString(25, c.getApellidoPaterno());
      ps.setString(26, c.getApellidoMaterno());
      ps.setString(27, c.getTelefonoTrabajo());
      ps.setString(28, c.getNumeroInterior());
      ps.setString(29, c.getCelular());
      ps.setString(30, c.getOcupacion());
      ps.setString(31, c.getNombreEmpresa());
      ps.setString(32, c.getNombreEmpresaConyugue());
      ps.setString(33, c.getFamiliar1());
      ps.setString(34, c.getFamiliar2());
      ClasificacionCliente cc = c.getClasificacionCliente();
      ps.setInt(35, cc.getId());
      ps.setString(36, c.getEmail());
      ps.setString(37, c.getNoEmail());
      if (sizeFoto > 0) {
        ps.setBinaryStream(38, c.getFotografia(), (int) sizeFoto);
      }
      else {
        ps.setNull(38, java.sql.Types.BLOB);
      }
      ps.setString(39, c.getRfc());
      ps.execute();
      //Ahora obtenemos el id del cliente recien agregado.
      /*
       int id = DaoClientesCentral.getIdUltimoCliente();

       //Por cada boutique actualizamos el directorio de clientes
       sql = "INSERT INTO `clientes` (`nombre`,`calle`,`numero`,`calleAledanaA` , `calleAledanaB` , `colonia` , `telefono` , `fechaNacimiento` , `tallaCalzado` , `tallaConjunto` , `tieneCredito` , `limiteCredito` , `estadoCivil` , `casaPropia` , `trabaja` , `domicilioTrabajo` , `nombreConyugue` , `ocupacionConyugue` , `domicilioTrabajoConyugue` , `personal1` , `personal2` , `comercial1` , `comercial2` , `comercial3` , `codigoPostal`, `apellidoPaterno`,`apellidoMaterno`,`id`,`telefonoTrabajo`) VALUES " +
           "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
       for (Connection cn : pool) {
         cn.setAutoCommit(false);
         ps = cn.prepareStatement(sql);
         ps.setString(1, c.getNombre());
         ps.setString(2, c.getCalle());
         ps.setString(3, c.getNumero());
         ps.setString(4, c.getCalleAledanaA());
         ps.setString(5, c.getCalleAledanaB());
         ps.setString(6, c.getColonia());

         ps.setString(7, c.getTelefono());
         ps.setDate(8, c.getFechaNacimiento());
         ps.setString(9, c.getTallaCalzado());
         ps.setString(10, c.getTallaConjunto());
         ps.setString(11, c.getTieneCredito());
         ps.setDouble(12, c.getLimiteCredito());
         ps.setString(13, c.getEstadoCivil());
         ps.setString(14, c.getCasaPropia());
         ps.setString(15, c.getTrabaja());
         ps.setString(16, c.getDomicilioTrabajo());
         ps.setString(17, c.getNombreConyugue());
         ps.setString(18, c.getOcupacionConyugue());
         ps.setString(19, c.getDomicilioTrabajoConyugue());
         ps.setString(20, c.getPersonal1());
         ps.setString(21, c.getPersonal2());
         ps.setString(22, c.getComercial1());
         ps.setString(23, c.getComercial2());
         ps.setString(24, c.getComercial3());
         ps.setString(25, c.getApellidoPaterno());
         ps.setString(26, c.getApellidoMaterno());
         ps.setInt(27, id);
         ps.setString(28, c.getTelefonoTrabajo());
         ps.execute();
         cn.commit();
       }*/
    }
    catch (SQLException ex1) {
      System.out.println(ex1.toString());

      return false;
    }
    return true;
  }

  /**
   * getIdUltimoCliente
   *
   * @return int
   */
  @SuppressWarnings("unused")
private static int getIdUltimoCliente() {
    int id = 0;
    String sql = "SELECT id FROM clientes ORDER BY id DESC LIMIT 1";
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        id = rs.getInt("id");
      }
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
    return id;

  }

  public static boolean remove(int id) {
    String sql = "DELETE FROM clientes WHERE id=?";
    boolean res = false;
    PreparedStatement ps = null;
    Connection con = null;
    con = DaoSource.getConnection();
    try {
      ps = con.prepareStatement(sql);
      ps.setInt(1, id);
      res = ps.execute();
      ps.close();
      //  con.close();
    }
    catch (SQLException ex) {
      System.out.println("DaoClientes.remove(int id): " + ex.toString());
      try {
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      }
      catch (SQLException e) {}
      return false;

    }
    return res;
  }

  public static boolean actualizarCliente(Cliente cliente, long size) {
    sizeFoto = size;
    return actualizarCliente(cliente);
  }

  public static boolean actualizarCliente(Cliente cliente) {
    String sql = "UPDATE `clientes` SET" +
        " `nombre` = ?,`calle` = ? ,`numero` = ?," +
        "`calleAledanaA` = ?," +
        "`calleAledanaB` = ?," +
        "`colonia` = ?," +
        "`celular` = ?," +
        "`telefono` = ?," +
        "`fechaNacimiento` = ?," +
        "`tallaCalzado` = ?," +
        "`tallaConjunto` = ?," +
        "`tieneCredito` = ?," +
        "`limiteCredito` = ?," +
        "`estadoCivil` = ?," +
        "`casaPropia` = ?," +
        "`trabaja` = ?," +
        "`domicilioTrabajo` = ?," +
        "`nombreConyugue` = ?," +
        "`ocupacionConyugue` = ?," +
        "`domicilioTrabajoConyugue` = ?," +
        "`personal1` = ?," +
        "`personal2` = ?," +
        "`comercial1` = ?," +
        "`comercial2` = ?," +
        "`comercial3` = ?," +
        "`familiar1` = ?," +
        "`apellidoPaterno` = ?," +
        "`apellidoMaterno`=?," +
        "`telefonoTrabajo`=?," +
        "`numeroInterior`=?," +
        "`ocupacion`=?," +
        "`nombreEmpresa`=?," +
        "`nombreEmpresaConyugue`=?," +
        "`familiar2`=?," +
        "`clasificacion`=?," +
        "`email`=?, " +
        "`noEmail`=?, " +
        "`fotografia`=?, " +
        "`rfc`=? " +
        " WHERE `id` = ?";
    Connection con = DaoSource.getConnection();
    java.sql.PreparedStatement ps = null;
//    List<Connection> pool = DaoSource.getPoolSucursales();

    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, cliente.getNombre());
      ps.setString(2, cliente.getCalle());
      ps.setString(3, cliente.getNumero());
      ps.setString(4, cliente.getCalleAledanaA());
      ps.setString(5, cliente.getCalleAledanaB());
      ps.setString(6, cliente.getColonia());
      ps.setString(7, cliente.getCelular());
      ps.setString(8, cliente.getTelefono());
      ps.setDate(9, cliente.getFechaNacimiento());
      ps.setString(10, cliente.getTallaCalzado());
      ps.setString(11, cliente.getTallaConjunto());
      ps.setString(12, cliente.getTieneCredito());
      ps.setDouble(13, cliente.getLimiteCredito());
      ps.setString(14, cliente.getEstadoCivil());
      ps.setString(15, cliente.getCasaPropia());
      ps.setString(16, cliente.getTrabaja());
      ps.setString(17, cliente.getDomicilioTrabajo());
      ps.setString(18, cliente.getNombreConyugue());
      ps.setString(19, cliente.getOcupacionConyugue());
      ps.setString(20, cliente.getDomicilioTrabajoConyugue());
      ps.setString(21, cliente.getPersonal1());
      ps.setString(22, cliente.getPersonal2());
      ps.setString(23, cliente.getComercial1());
      ps.setString(24, cliente.getComercial2());
      ps.setString(25, cliente.getComercial3());
      ps.setString(26, cliente.getFamiliar1());
      ps.setString(27, cliente.getApellidoPaterno());
      ps.setString(28, cliente.getApellidoMaterno());
      ps.setString(29, cliente.getTelefonoTrabajo());
      ps.setString(30, cliente.getNumeroInterior());
      ps.setString(31, cliente.getOcupacion());
      ps.setString(32, cliente.getNombreEmpresa());
      ps.setString(33, cliente.getNombreEmpresaConyugue());
      ps.setString(34, cliente.getFamiliar2());
      ClasificacionCliente cc = cliente.getClasificacionCliente();
      ps.setInt(35, cc.getId());
      ps.setString(36, cliente.getEmail());
      ps.setString(37, cliente.getNoEmail());
      ps.setString(39, cliente.getRfc());
      ps.setInt(40, cliente.getId());

      if (sizeFoto > 0) {
        ps.setBinaryStream(38, cliente.getFotografia(),
                           (int) sizeFoto);
      }
      else {
        ps.setNull(38, java.sql.Types.BLOB);
      }
      sizeFoto = 0;
      ps.execute();
      ps.close();
      //con.close();

    }
    catch (SQLException ex) {
      System.out.println("DaoClientes.actualizarCliente(Cliente cliente): " +
                         ex.toString());
      return false;

    }
    return true;
  }

  public static List<String[]> findIdToNombreyApellidos(String nombreCliente) {

    List<String[]> lista = new ArrayList<String[]>();
    String sql;
    if (nombreCliente.equals("")) {
      sql =
          "SELECT id,apellidoPaterno,apellidoMaterno,nombre FROM clientes ORDER BY nombre";
    }
    else {
      sql = "SELECT id,nombre,apellidoPaterno,apellidoMaterno FROM clientes where nombre like '%" +
          nombreCliente + "%'";
    }

    Connection con = DaoSource.getConnection();
    PreparedStatement ps;
    try {
      ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        String idToName[] = new String[2];
        idToName[0] = rs.getString("id");
        idToName[1] = rs.getString("apellidoPaterno") + " " +
            rs.getString("apellidoMaterno") + " " + rs.getString("nombre");
        lista.add(idToName);
      }
      rs.close();
      ps.close();
      // con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      return null;
    }
    return lista;
  }

  private static Cliente mapRsToCliente(ResultSet rs) {
    Cliente cliente = null;
    try {
      if (rs.next()) {
        cliente = new Cliente();
        ClasificacionCliente cc = new ClasificacionCliente();
        cc.setClasificacion(rs.getString("clientes_clasificacion.clasificacion"));
        cc.setId(rs.getInt("clientes_clasificacion.id"));
        cc.setTipo(rs.getString("clientes_clasificacion.tipo"));
        cliente.setClasificacionCliente(cc);
        //cliente.setClasificacion(rs.getString("clasificacion"));
        cliente.setId(rs.getInt("clientes.id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setEmail(rs.getString("email"));
        cliente.setRfc(rs.getString("rfc"));
        cliente.setCalle(rs.getString("calle"));
        cliente.setNumero(rs.getString("numero"));
        cliente.setNumeroInterior(rs.getString("numeroInterior"));
        cliente.setCalleAledanaA(rs.getString("calleAledanaA"));
        cliente.setCalleAledanaB(rs.getString("calleAledanaB"));
        cliente.setColonia(rs.getString("colonia"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setCelular(rs.getString("celular"));
        cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        cliente.setTallaCalzado(rs.getString("tallaCalzado"));
        cliente.setTallaConjunto(rs.getString("tallaConjunto"));
        cliente.setTieneCredito(rs.getString("tieneCredito"));
        cliente.setLimiteCredito(rs.getDouble("limiteCredito"));
        cliente.setEstadoCivil(rs.getString("estadoCivil"));
        cliente.setCasaPropia(rs.getString("casaPropia"));
        cliente.setTrabaja(rs.getString("trabaja"));
        cliente.setOcupacion(rs.getString("ocupacion"));
        cliente.setDomicilioTrabajo(rs.getString("domicilioTrabajo"));
        cliente.setNombreEmpresa(rs.getString("nombreEmpresa"));
        cliente.setNombreConyugue(rs.getString("nombreConyugue"));
        cliente.setOcupacionConyugue(rs.getString("ocupacionConyugue"));
        cliente.setNombreEmpresaConyugue(rs.getString("nombreEmpresaConyugue"));
        cliente.setDomicilioTrabajoConyugue(rs.getString(
            "domicilioTrabajoConyugue"));
        cliente.setPersonal1(rs.getString("personal1"));
        cliente.setPersonal2(rs.getString("personal2"));
        cliente.setComercial1(rs.getString("comercial1"));
        cliente.setComercial2(rs.getString("comercial2"));
        cliente.setComercial3(rs.getString("comercial3"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
        cliente.setTelefonoTrabajo(rs.getString("telefonoTrabajo"));
        cliente.setFamiliar1(rs.getString("familiar1"));
        cliente.setFamiliar2(rs.getString("familiar2"));
        cliente.setClaveCliente(rs.getString("claveCliente"));
        cliente.setFotografia(rs.getBinaryStream("fotografia"));
        cliente.setNoEmail(rs.getString("noEmail"));
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

  public static Cliente findByRfc(String rfc) {
    String sql = "SELECT * FROM clientes WHERE rfc=?";
    Connection con = DaoSource.getConnection();
    Cliente cliente = null;
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, rfc);
      ResultSet rs = ps.executeQuery();
      cliente = mapRsToCliente(rs);
      rs.close();
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
    return cliente;

  }

  public static List<String[]> findByNombreCompleto(String nombre) {
    List<String[]> lista = new ArrayList<String[]> ();
    String[] row;
    String sql = "SELECT id,nombre,apellidoPaterno,apellidoMaterno FROM clientes WHERE nombreCompleto like ?  ORDER BY nombre";
    Connection con = DaoSource.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement(sql);
      ps.setString(1, "%" + nombre + "%");
      rs = ps.executeQuery();
      while (rs.next()) {
        row = new String[2];
        row[0] = rs.getString("id");
        row[1] = rs.getString("nombre") + " " + rs.getString("apellidoPaterno") +
            " " + rs.getString("apellidoMaterno");
        lista.add(row);
      }
      rs.close();
      ps.close();
      //con.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          ps.close();
        }
      }
      catch (SQLException e) {}

    }
    return lista;
  }

  public static Cliente findByNombreById(int id) {
    Cliente cliente = null;
    String sql =
        "SELECT id,nombre,apellidoPaterno,apellidoMaterno FROM clientes WHERE id=" +
        id;
    Connection con = DaoSource.getConnection();
    Statement ps = null;
    ResultSet rs = null;
    try {
      ps = con.createStatement();
      rs = ps.executeQuery(sql);
      if (rs.next()) {
        cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
        cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
      }
      rs.close();
      ps.close();
    }
    catch (SQLException ex) {
      System.out.println(ex.toString());
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          ps.close();
        }
      }
      catch (SQLException e) {}

    }
    return cliente;
  }

  public static List<Object[]> findByNombreMaternoPaterno(String nombre) {
    List<Object[]> l = new ArrayList<Object[]>();
    String sql = "SELECT id,nombre,domicilio,colonia,ciudad,estado,telefono,curp FROM clientes WHERE nombre like '%" +
        nombre + "%'";
    Connection con = DaoSource.getConnection();
    try {
      ResultSet rs = con.createStatement().executeQuery(sql);
      Object[] row;
      while (rs.next()) {
        row = new Object[8];
        row[0] = rs.getString("id");
        row[1] = rs.getString("nombre");
        row[2] = rs.getString("domicilio");
        row[3] = rs.getString("colonia");
        row[4] = rs.getString("ciudad");
        row[5] = rs.getString("estado");
        row[6] = rs.getString("telefono");
        row[7] = rs.getString("curp");
        l.add(row);
      }
      rs.close();
      return l;
    }
    catch (SQLException ex) {
      return null;
    }
  }

  public static Cliente findById(int id) {

    String sql = "SELECT clientes.*,clientes_clasificacion.* FROM clientes INNER JOIN clientes_clasificacion ON clientes.clasificacion=clientes_clasificacion.id WHERE clientes.id=?";
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

  /**
   * eliminarCliente
   *
   * @param id int
   * @return boolean
   */
  public static boolean eliminarCliente(int id) {
    //Primero eliminamos en los foraneos
    //Despues en el inventario local
    Connection con = DaoSource.getConnection();
    java.sql.PreparedStatement ps = null;
    String sql = "DELETE FROM clientes WHERE id=" + id;

    try {
      ps = con.prepareStatement(sql);
      ps.execute();
      ps.close();
      return true;
    }
    catch (SQLException ex) {
      return false;
    }
  }

  /**
   * findFoto
   *
   * @param i int
   * @return InputStream
   */
  public static InputStream findFoto(int i) {
    Connection con = DaoSource.getConnection();
    java.sql.ResultSet rs = null;
    InputStream is = null;
    try {
      rs = con.createStatement().executeQuery(
          "SELECT fotografia FROM clientes WHERE id=" + i);

      if (rs.next()) {
        is = rs.getBinaryStream("fotografia");
      }
      rs.close();
    }
    catch (SQLException ex) {
      is = null;
    }
    return is;
  }

  /*--------------FUNCION GET ERROR------------------*/
  /* public String getMsgError() {
     return this.ERR_MESSAGE;
   }
   */

}
