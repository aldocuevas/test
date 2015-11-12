package com.boutique.engine.impl;

import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.impresiones.ImpresionEncargos;
public class RotacionInventarioEngine {

  RotacionInventario registroRotacion;
  public List<Object[]> productosView = new ArrayList<Object[]>();
  Boutique boutiqueDestino = null;
  List<Boutique> boutiques;

  public RotacionInventarioEngine() {
    boutiques = DaoBoutique.findBoutiquesRemotas(AppInstance.boutique().getId());
  }

  public List<Boutique> getBoutiques() {
    return boutiques;
  }

  public void inciarRotacion() {
    productosView = new ArrayList<Object[]>();
    registroRotacion = DaoRotacionInventarios.inciarRotacion(boutiqueDestino.
        getId());
    //TENEMOS EL REGISTRO DE ROTACION.. VERIFICAMOS SI YA TIENE PRODUCTOS PARA GENERAR LA VISTA
    armarVistaProductos();
  }


  public  boolean cerrarRotacion() {
    if (DaoRotacionInventarios.cerrarRotacion(registroRotacion.getId(),registroRotacion.getBoutiqueFinal())){
      ImpresionEncargos i = new ImpresionEncargos(registroRotacion,productosView);
      i.start();
      return true;
    }else{
      return false;
    }
  }

  /**
   * setBoutiqueSeleccionada
   *
   * @param index int
   */
  public void setBoutiqueSeleccionada(int id) {
    boutiqueDestino = boutiques.get(id);
  }

  /**
   * agregarProductoaRotar
   *
   * @param etiqueta String
   *
   */
  public void agregarProductoaRotar(String etiqueta) {
    DaoRotacionInventarios.agregarProductoaRotar(etiqueta,
                                                 registroRotacion.getId(),
                                                 AppInstance.usuario().getId(),AppInstance.terminal().getId());
    //debo recargar la lista de productos
    registroRotacion.setProductos(DaoRotacionInventarios.findByIdLote(
        registroRotacion.getId()));
    armarVistaProductos();
    //tenemos la lista, la recargamos
  }

  private void armarVistaProductos() {
    this.productosView.clear();
    if (registroRotacion.getProductos() != null) {
      for (ProductoRotacion p : registroRotacion.getProductos()) {
        //POR CADA PRODUCTO SACAMOS SUS DATOS PARA LA VISTA.
//     "id", "Etiqueta", "Tipo de producto", "P. al público","Registro"};
        Object[] row = new Object[6];
        row[0] = p.getId();
        row[1] = p.getEtiqueta();
        row[2] = AppInstance.idToTipoProducto.get(String.valueOf(p.
            getIdTipoProducto()));
        row[3] = AppInstance.number.format(p.getPrecioPublico());
        row[4] = AppInstance.formatoLargo.format(p.getFechaRegistro());
        row[5] = AppInstance.idToNombreUsuario.get(p.getIdVendedor());
        this.productosView.add(row);

      }
    }
  }

  /**
   * regresarProducto
   *
   * @param indice int
   * @return boolean
   */
  public boolean regresarProducto(int indice) {
    ProductoRotacion p = registroRotacion.getProductos().get(indice);

    DaoRotacionInventarios.regresarProducto(p.getEtiqueta(),
                                            registroRotacion.getId(),
                                            AppInstance.boutique().getId());
    //debo recargar la lista de productos
    registroRotacion.setProductos(DaoRotacionInventarios.findByIdLote(
        registroRotacion.getId()));
    armarVistaProductos();
return true;
  }

}
