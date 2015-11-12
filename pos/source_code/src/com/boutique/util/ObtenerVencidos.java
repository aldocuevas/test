package com.boutique.util;

import com.boutique.dao.DaoClientesSucursal;
import com.boutique.domain.VentaCredito;
import com.boutique.engine.impl.AppInstance;
import com.boutique.engine.impl.SistemaCreditoEngine;

import java.io.*;
import java.util.List;

import com.boutique.dao.DaoSource;
import com.boutique.domain.ClasificacionCliente;
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
public class ObtenerVencidos {
  public ObtenerVencidos() {
  }

  public static void main(String[] args) {
    try {
      DaoSource.iniciar();
      AppInstance.iniciarApp();
    }
    catch (Exception ex) {
    }
    int i = 1;
    FileOutputStream fos = null;
 java.text.SimpleDateFormat formatoCort = new java.text.
      SimpleDateFormat(
          "ddMMMyyyy");
    try {
      fos = new FileOutputStream(new File("CreditoAl"+ formatoCort.format(new java.util.Date()) + ".csv"));
    }
    catch (FileNotFoundException ex1) {
    }

    SistemaCreditoEngine engine = new SistemaCreditoEngine();
    List<?> listaClientes;
   // listaClientes = DaoClientesSucursal.
    //    findAllByRutaAndVentasActivasAnd6MesesAtras(); //TENEMOS LOS CLIENTES CON NOTAS ACTIVAS
       listaClientes = DaoClientesSucursal.findAllByRutaAndVentasActivas();
    Object[] row;
    //AHORA POR CADA CLIENTE BUSCAMOS SU SALDO TOTAL Y SU SALDO VENCIDO JOJO
    for (Object obj : listaClientes) {
      row = (Object[]) obj;
      //POR CADA CLIENTE LO BUSCAMOS

      engine.cliente = DaoClientesSucursal.findById(Integer.parseInt(row[0].
          toString()));

      java.util.List<VentaCredito> ventasEncontradas = engine.
          getResumenEstadoCuenta();

      double saldoVencido = 0;
      double saldoTotal = 0;
      int j=0;
      java.sql.Timestamp fecha = new java.sql.Timestamp(new java.util.Date().getTime());
      Object[] row1 = null;
      /*try {
        Thread.sleep(1000);
       }
       catch (InterruptedException ex) {
       }*/
      if (ventasEncontradas != null) {
        //Si hay ventas.. ponemos los datos en el datamodel
        for (VentaCredito vc : engine.ventasEncontradas) {
          if (j==0){
            fecha= vc.getFechaVencimiento();
          }
          j++;
          row1 = new Object[7];
          row1[0] = vc.getId();
          row1[1] = AppInstance.idToNombreBoutique.get(vc.getIdBoutique());
          row1[2] = AppInstance.number.format(vc.subTotal);
          row1[3] = AppInstance.number.format(vc.saldoAbonado);
          row1[4] = AppInstance.number.format(vc.saldoActual);
          row1[5] = AppInstance.number.format(vc.saldoVencido);
          row1[6] = AppInstance.formatoCorto.format(vc.getFechaVencimiento());

          saldoVencido += vc.saldoVencido;
          saldoTotal += vc.saldoActual;
          if(vc.getFechaVencimiento().compareTo(fecha)<0){ //LA FECHA DE VENCIMIENTO ES MENOR A HOY, SE PONE LA FECHA ANTERIOR
            fecha=vc.getFechaVencimiento();
          }
          //  System.out.println(row1[0] + "," + row1[1] + "," + row1[2] + "," + row1[3] + "," + row1[4] + "," + row1[5] + "," + row1[6] );
        }

      }
ClasificacionCliente cc = engine.cliente.getClasificacionCliente();
      System.out.println(i++ +" " + engine.cliente.getApellidoPaterno() + " " +
                    engine.cliente.getApellidoMaterno() + " " +
                    engine.cliente.getNombre() + "," +
                    engine.cliente.getCalle() + " " +
                    engine.cliente.getNumero() +  ((engine.cliente.getNumeroInterior().equals(""))?"":" int. " + engine.cliente.getNumeroInterior())+ ", " +
                    engine.cliente.getColonia() + "," + row[4].toString() + ", Tel " +

                    engine.cliente.getTelefono() + " , Cel " +
                    engine.cliente.getCelular() +
                    ", " + saldoTotal + "," +
                    saldoVencido + "," + AppInstance.formatoCorto.format(fecha));
 //   + "," + engine.cliente.getClasificacion());
      try {
        fos.write( (i +" " + engine.cliente.getApellidoPaterno() + " " +
                    engine.cliente.getApellidoMaterno() + " " +
                    engine.cliente.getNombre() + "," +
                    engine.cliente.getCalle() + " " +
                    engine.cliente.getNumero() +  ((engine.cliente.getNumeroInterior().equals(""))?"":" int. " + engine.cliente.getNumeroInterior())+", " +
                    engine.cliente.getColonia() + "," + row[4].toString() + ", Tel " +

                    engine.cliente.getTelefono() + " , Cel " +
                    engine.cliente.getCelular() +
                    ", " + saldoTotal + "," +
                    saldoVencido + "," + AppInstance.formatoCorto.format(fecha)  + "," + cc.getClasificacion() + "\r"
                  ).getBytes());
      }
      catch (IOException ex2) {
      }

      /*row[0] = rs.getString("id");
            row[1] =  rs.getString("apellidoPaterno") +
       " " + rs.getString("apellidoMaterno")  + " " + rs.getString("nombre");
            row[2] = rs.getString("calle") + " " + rs.getString("numero") +
                ( (rs.getString("numeroInterior").equals("")) ? "" :
                 " Int. " + rs.getString("numeroInterior"));
            row[3] = rs.getString("colonia") + ", " + rs.getString("ciudad");
            row[4] = rs.getString("ruta") + " ";
            row[5] = rs.getString("telefono");
       */
    }
try {
  fos.close();
}
catch (IOException ex3) {
}

  }
}
