package com.boutique.engine.impl;

import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;

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
public class RevisionInventarioEngine {
  Date fecha = new java.util.Date();
  //FECHA Y HORA
  public boolean terminado = true;
  //TENEMOS EL AUDITOR

  //TENEMOS EL EMPLEADO

  //TENEMOS LOS PRODUCTOS
  List<Integer> auditores = new ArrayList<Integer> ();
  List<Integer> empleados = new ArrayList<Integer> ();
  List<Boutique> boutiques = new ArrayList<Boutique> ();
  public Inventario inventario = new Inventario();

  //List productosView = new ArrayList<ProductosView>();
  //TENEMOS LA BOUTIQUE
  //POR CADA CODIGO LEIDO. LO BUSCAMOS EN LA LISTA Y REVISAMOS QUE LA CANTIDAD YA ENCONTRADA FISICAMENTE SEA MENOR A LA CANTIDAD
  // EN SUPUESTO STOCK. SI ES ASI AGREGAMOS UNO A LOS ENCONTRADOS
  // SI NO, SEGUIMOS BUSCANDO EL PRODUCTO EN TODA LA LISTA Y SI YA NO LO ENCONTRAMOS PUES LO AGREGAMOS A FALTANTE
  public RevisionInventarioEngine() {
    auditores = DaoUsuarios.findAuditores();
    empleados = DaoUsuarios.findNoAuditores();
    boutiques = DaoBoutique.findAllActivas();
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public List<Integer> getAuditores() {
    return auditores;
  }

  public List<Integer> getNoAuditores() {
    return empleados;
  }

  public List<Boutique> getBoutiques() {
    return boutiques;
  }

  /**
   * iniciarInventario
   *
   * @param idAuditor int
   * @param idEmpleado int
   * @param idBoutique int
   */
  public void iniciarInventario(int idAuditor, int idEmpleado, int idBoutique) {
    inventario.setAuditor(DaoUsuarios.findUsuarioById(auditores.get(idAuditor)));
    inventario.setEmpeladoPresente(DaoUsuarios.findUsuarioById(empleados.get(
        idEmpleado)));
    inventario.setBoutique(boutiques.get(idBoutique));
    inventario.setFecha(DaoSource.getFechaActual());
    this.terminado = false;
  }

  /**
   * findProductosEnInventario
   */
  public void findProductosEnInventario() {
    inventario.setProductosInventario(DaoRealizarInventario.
                                      findProductosEnBoutique(
                                          inventario.getBoutique().getId()));
    inventario.cantidadProductosInicial = DaoRealizarInventario.
        findSumaProductosEnBoutique(inventario.
                                    getBoutique().
                                    getId());
    inventario.cantidadProductosFaltantes = inventario.cantidadProductosInicial;
    inventario.montoFaltante = DaoRealizarInventario.
        findSumaMontoProductosEnBoutique(
            inventario.
            getBoutique().getId());
    inventario.montoInicial = inventario.montoFaltante;
    //BUSCAMOS TODOS LOS PRODUCTOS EN INVENTARIO EN LA BOUTIQUE SELECCIONADA

  }

  /**
   * cotejarProducto
   *
   * @param etiqueta String
   */
  public void cotejarProducto(String etiqueta) {
    //BUSCAMOS LA ETIQUETA EN LA TABLA TEMPORAL DONDE LA CANTIDAD SEA MAYOR A CERO
    ProductoInventario p = DaoRealizarInventario.findByEtiquetaOnTemporal(
        etiqueta);
    if (p != null) {
      //SI SI ES ENCONTRADO, LO INSERTAMOS EN LA TABLA TEMPORAL DE PRODUCTOS ENCONTRADOS
      if (DaoRealizarInventario.addProductoEncontrado(p)) { // sql="INSERT INTO productos_temporal_encontrados VALUES (?,?,?,?,?,?);";
        //LO DESCONTAMOS DE LA TABLA TEMPORAL
        DaoRealizarInventario.descontarProductoEncontrado(p);
        //LO AGREGAMOS A LA VISTA PRODUCTOS ENCONTRADOS? CREO QUE NOO.
        //ACTUALIZAMOS LOS PRODUCTOS FALTANTES Y EL MONTO FALTANTE, DESCONTANDO EN UNO LA CANTIDAD Y EL MONTO POR EL COSTO DEL PRODUCTO
        inventario.cantidadProductosFaltantes = inventario.
            cantidadProductosFaltantes - 1;
        inventario.montoFaltante = inventario.montoFaltante -
            p.getPrecioUnitario();
        //SI SE PUEDE Y NO ESTA MUY LENTO, ACTUALIZAMOS LA LISTA DE PRODUCTOS EN INVENTARIO
        inventario.setProductosInventario(DaoRealizarInventario.
                                          findProductosEnStock());
        inventario.setProductosEncontrados(DaoRealizarInventario.
                                           findProductosEncontrados());

        //  inventario.setPRo
      }

    }
    else {

      p = DaoRealizarInventario.findProductoNoEncontrado(etiqueta,
          inventario.getBoutique().getId());
      if (p != null) {
        if (DaoRealizarInventario.addProductoNoEncontrado(p)) { //INSERTAMOS A LA TABLA TEMPORAL DE PRODUCTO NO ENCONTRADO EN INVENTARIO.
          //ACTUALIZAMOS LA LISTA DE PRODUCTOS NO ENCONTRADOS.
          inventario.setProductosNoEncontrados(DaoRealizarInventario.
                                               findProductosNoEncontrados());
          inventario.montoNoEncontrado += p.getPrecioUnitario();
          inventario.cantidadProductosNoEncontrados++;
        }

      }

    }
  }

  /**
   * finalizarInventario
   *
   * @return boolean
   */
  public boolean finalizarInventario() {

    inventario.id = DaoRealizarInventario.registrarInventario(inventario); //AGREGAR EL NUEVO INVENTARIO A LA TABLA DEFINITIVA.
    if (inventario.id > 0) {
      terminado = true;
      return true;
    }
    else {
      return false;
    }
    //CALCULAR LOS PRODUCTOS FALTANTES Y MOSTRAR LA LISTA




  }

  private void jbInit() throws Exception {
  }

  /**
   * agregarAlInventario
   *
   * @return boolean
   */
  public boolean agregarAlInventario() {
    //DE LA LISTA DE NO ENCONTRADOS SACAMOS LOS DATOS Y AGREGAMOS EN UNO EN EL INVENTARIO
    return DaoRealizarInventario.registrarProductosNoEncontrados(inventario.
        getProductosNoEncontrados(), inventario.getBoutique().getId());

  }

  /**
   * registrarMermas
   *
   * @return boolean
   */
  public boolean registrarMermas() {
    //DEBEMOS DE REGISTRAR LA LISTA DE PRODUCTOS EN LA LISTA DE MERMAS

    return DaoRealizarInventario.registrarMermas(inventario.
                                                 getProductosInventario(),
                                                 inventario.getBoutique().getId(),
                                                 inventario.id);
    //AHORA DAMOS DE BAJA LA LISTA DE PRODUCTOS FALTANTES DEL INVENTARIO DE ESTA BOUTIQUE


  }

}
