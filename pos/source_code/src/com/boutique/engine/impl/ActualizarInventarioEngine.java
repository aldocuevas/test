package com.boutique.engine.impl;

import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import java.sql.SQLException;
import com.boutique.impresiones.*;
import report.PFDocument;
public class ActualizarInventarioEngine {
  List<Boutique> boutiques;
  Boutique boutiqueSeleccionada;
  List<?> productosView;
  public ActualizarInventarioEngine() {
    boutiques = DaoBoutique.findAll();
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public List<Boutique> getBoutiques() {
    return boutiques;

  }

  public List<?> getProductosView() {
    return productosView;
  }

  public void setBoutiqueSeleccionada(int index) {
    boutiqueSeleccionada = boutiques.get(index);
    //   Obtenemos el inventario a dividir. de esta boutique
        productosView = DaoInventarios.findProductosByIdBoutique(
        boutiqueSeleccionada.getId());

  }

  public void verProductosADistribuir() {
    DaoInventarios.eliminarCantidadesCero();
    productosView = DaoInventarios.findProductosNoDistribuidosView();

  }

  public boolean distribuirInventario() {
//Generamos la instancia del archivo
    /*  String ls = System.getProperty("line.separator");
      File file = new File(nombreArchivo);
      //Obtenemos los productos no distribuidos con compra cerrada para meterlos en el archivo
      //Generamos el archivo
      //Guardamos el archivo
      //Mostramos la vista de los productos
      //Actualizamos los productos de no distribuidos a distribuidos
      //regresamos true*/
   List<Producto> productos = DaoInventarios.findProductosNoDistribuidos();
    if (productos.size() > 0) {

      try { 
        List<Integer> boutiques= DaoInventarios.findBoutiquesConProductosNoDistribuidos();
        for (Integer i: boutiques){
          PFDocument d = new PFDocument();
          d.showPrintDialog(true);
         ImpresionInventarioDistribuido.imprimirInventarioDistribuidoPorBoutique(i,d);
        }

         DaoInventarios.eliminarCantidadesCero();

         DaoInventarios.distribuirProductos(productos);

         return true;
       }
       catch (SQLException ex) {
         return false;
       }

    }
    else {
      return false;
    }
  }

  private void jbInit() throws Exception {
  }

}
