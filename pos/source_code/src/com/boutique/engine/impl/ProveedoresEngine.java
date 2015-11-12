package com.boutique.engine.impl;
import java.util.*;
import com.boutique.dao.*;
import com.boutique.domain.Proveedor;
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
public class ProveedoresEngine {
  List<Proveedor> proveedores;
  public ProveedoresEngine() {

  }
  public void BuscarProveedores(String nombre){
    proveedores = DaoProveedores.findBy(nombre);
  }
  public List<Proveedor> proveedores(){
    return proveedores;
  }


}
