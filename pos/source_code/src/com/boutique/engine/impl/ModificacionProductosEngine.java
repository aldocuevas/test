package com.boutique.engine.impl;
import java.util.*;
import com.boutique.dao.*;
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
public class ModificacionProductosEngine {
  List<Object[]> productosaCambiar= new ArrayList<Object[]>();
  public ModificacionProductosEngine() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public List<Object[]> cambiarPrecioaProducto(String etiqueta, double precio) {
    productosaCambiar.addAll(DaoInventarios.findByEtiquetaAll(etiqueta,precio));
//    return DaoInventarioInicial.findByEtiquetaAll(etiqueta,precio);
    return productosaCambiar;
  }
  public boolean guardarCambios(){
    //CON LOS PRODUCTOS QUE SE TIENEN EN LA LISTA SE HACE LA ACTUALIZACION
    return DaoInventarios.actualizarPrecioProductos(productosaCambiar);

  }

  public void imprimirEtiquetas(int posicion, int idxInicio,int idxFinal ,int i){

  }

  private void jbInit() throws Exception {
  }

}
