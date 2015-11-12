package com.boutique.engine.impl;

import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;

public class DividirInventarioEngine {
  List<Boutique> boutiques;
  List<Producto> productosaDividir = new ArrayList<Producto> ();
  public List<Object[]> productosView = new ArrayList<Object[]>();
  Boutique boutiqueSeleccionada;
  Map<?, ?> idToTipoProducto;
  public List<Boutique> getBoutiques() {
    return boutiques;
  }

  public DividirInventarioEngine() {
    boutiques = DaoBoutique.findSucursales();
    idToTipoProducto = DaoTipoProductos.getMap();
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void setBoutiqueSeleccionada(int id) {
    boutiqueSeleccionada = boutiques.get(id);
  }

  /**
   * agregarProductoaDividir
   *
   * @param string String
   */
  public void agregarProductoaDividir(String etiqueta) {
    java.text.NumberFormat nmbF = java.text.NumberFormat.getNumberInstance(
        Locale.ENGLISH);
    nmbF.setMaximumFractionDigits(2);
    nmbF.setMinimumFractionDigits(2);

    Producto p;
    p = DaoInventarios.findByEtiquetaWithCompraCerrada(etiqueta);
    if (p != null) {
      //Tenemos un producto disponible...
      //Hacemos la division del inventario
      //si la division fue exitosa, ponemos el prudcto en nuestra lista..
      p.setIdBoutique(this.boutiqueSeleccionada.getId());
      p.setCantidad(1);
      if (DaoInventarios.dividirProducto(p)) {
        productosaDividir.add(p);
        Object[] row = new Object[8];
        row[0] = p.getId();
        row[1] = boutiqueSeleccionada.getNombre();
        row[2] = p.getCantidad();
        row[3] = p.getEtiqueta();
        row[4] = idToTipoProducto.get(String.valueOf(p.getIdTipoProducto()));
        row[5] = Producto.detalleProducto(p);
        row[6] = p.getTalla();
        row[7] = nmbF.format(p.getPrecioPublico());
        this.productosView.add(row);
      }
    }
  }

  private void jbInit() throws Exception {
  }



  public boolean regresarProducto(int i) {
    Producto p = this.productosaDividir.get(i);
    //Descontaremos en -1 la cantidad e
    if (DaoInventarios.regresarProducto(p)) {
      productosaDividir.remove(i);
      productosView.remove(i);
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * setProductosADividir
   *
   * @param idBoutique int
   */
  public void setProductosADividir() {
    //Tenemos la boutique seleccionada, ponemos aquellos productos que no se han distribuido de compras cerradas que pertenecen
    //a esta boutique
    java.text.NumberFormat nmbF = java.text.NumberFormat.getNumberInstance(
        Locale.ENGLISH);
    nmbF.setMaximumFractionDigits(2);
    nmbF.setMinimumFractionDigits(2);

    List<Producto> lst = DaoInventarios.
        findProductosNoDistribuidosByIdBoutique(boutiqueSeleccionada.getId());
    if (lst != null) {
      this.productosaDividir.addAll(lst);
      for (Producto p : lst) {
        Object[] row = new Object[8];
        row[0] = p.getId();
        row[1] = boutiqueSeleccionada.getNombre();
        row[2] = p.getCantidad();
        row[3] = p.getEtiqueta();
        row[4] = idToTipoProducto.get(String.valueOf(p.getIdTipoProducto()));
        row[5] = Producto.detalleProducto(p);
        row[6] = p.getTalla();
        row[7] = nmbF.format(p.getPrecioPublico());
        this.productosView.add(row);
      }
    }
  }
}
