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
public class RegistroInventarioEngine {
  List<?> ultimasCompras = null;
  Compra compraSeleccionada = null;
  List<Producto> productosRegistrados = new ArrayList<Producto> ();
  Map<?, ?> idToTipoProducto;
  Producto productoaEditar;
  public RegistroInventarioEngine() {
  }

  public boolean registrarProductoExistente(Producto pAnterior,
                                            double precioCosto,
                                            double precioPublico,
                                            List<?> listaProductos) {
    List<Producto> productosaRegistrar = new ArrayList<Producto> ();
    //Tenemos el tipo de producto, los precios y la cantidad por talla en la lista. Si hay mas de 0 en cada renglon generamos el producto con su cantidad
    Producto p = null;
    int matriz = DaoBoutique.getMatriz();
    for (Object obj : listaProductos) {
      Object[] row = (Object[]) obj;
      String talla = row[0].toString();
      int cantidad = (Integer) row[1];
      if (cantidad > 0) {
        p = new Producto();
        p.setCantidad(cantidad);
        p.setClave( (int) precioCosto * 45);
        p.setIdCompra(compraSeleccionada.getId());
        p.setIdTipoProducto(pAnterior.getIdTipoProducto());
        p.setPrecioCosto(precioCosto);
        p.setPrecioPublico(precioPublico);
        p.setTalla(talla);
        p.setIdBoutique(matriz);
        p.setModelo(pAnterior.getModelo());
        productosaRegistrar.add(p);

      }
    }
    //Tenemos la lista de productos a registrar. La pasamos a la transaccion, hace falta ponerle modelo y
    if (DaoInventarios.registrarProductoExistente(productosaRegistrar)) {
      //Se registraron los productos, agregamos los productos ahora a la lista de productosRegistrados
      productosRegistrados.addAll(productosaRegistrar);
      return true;
    }
    else {
      //Si no se pudieron agregar pues regresamos un valor de false
      return false;
    }
  }

  public boolean registrarProducto(TipoProducto tp, double precioCostoInicial, double impuestoPrecioCosto, double gasto, double impuestoGasto, double precioCosto,
                                   double precioPublicoInicial, double impuestoPrecioPublicoInicial, double precioPublicoInicialTotal, double precioPublicoFinal, double impuestoPrecioPublicoFinal, double precioPublico,
                                   List<?> listaProductos, String estilo,
                                   int idMarca, int idDescripcion, int idColor) {
    List<Producto> productosaRegistrar = new ArrayList<Producto> ();
    //Tenemos el tipo de producto, los precios y la cantidad por talla en la lista. Si hay mas de 0 en cada renglon generamos el producto con su cantidad
    Producto p = null;
    int matriz = DaoBoutique.getMatriz();
    for (Object obj : listaProductos) {
      Object[] row = (Object[]) obj;
      String talla = row[0].toString();
      int cantidad = (Integer) row[1];
      if (cantidad > 0) {
        p = new Producto();
        p.setEstilo(estilo);
        p.setCantidad(cantidad);
        p.setClave( (int) precioCosto * 45);
        p.setIdCompra(compraSeleccionada.getId());
        p.setIdTipoProducto(tp.getId());
        p.setPrecioCostoInicial(precioCostoInicial);
        p.setImpuestoPrecioCosto(impuestoPrecioCosto);
        p.setGasto(gasto);
        p.setImpuestoGasto(impuestoGasto);
        p.setPrecioCosto(precioCosto);
        p.setPrecioPublicoInicial(precioPublicoInicial);
        p.setImpuestoPrecioPublicoInicial(impuestoPrecioPublicoInicial);
        p.setPrecioPublicoInicialTotal(precioPublicoInicialTotal);
        p.setPrecioPublicoFinal(precioPublicoFinal);
        p.setImpuestoPrecioPublicoFinal(impuestoPrecioPublicoFinal);
        p.setPrecioPublico(precioPublico);
        p.setTalla(talla);
        p.setIdBoutique(matriz);
        p.setIdMarca(idMarca);
        p.setIdDescripcion(idDescripcion);
        p.setIdColor(idColor);
        productosaRegistrar.add(p);

      }
    }
    //Tenemos la lista de productos a registrar. La pasamos a la transaccion, hace falta ponerle modelo y
    if (DaoInventarios.registrarProductos(productosaRegistrar)) {
      //Se registraron los productos, agregamos los productos ahora a la lista de productosRegistrados
      productosRegistrados.addAll(productosaRegistrar);
      return true;
    }
    else {
      //Si no se pudieron agregar pues regresamos un valor de false
      return false;
    }
  }



  public List<Object[]> getProductosRegistradosView() {
    java.text.NumberFormat nmbF = java.text.NumberFormat.getNumberInstance(
        Locale.ENGLISH);
    nmbF.setMaximumFractionDigits(2);
    nmbF.setMinimumFractionDigits(2);
    //Buscamos conforme la compra seleccionada los productos registrados
    //   "id", "Tipo de producto", "Costo","P. al público","Cantidad","Ganancia"}
    List<Object[]> lista = new ArrayList<Object[]>();
    Object[] row = new Object[8];
    for (Producto p : productosRegistrados) {
      row = new Object[8];
      row[0] = p.getId();
      row[1] = p.getEtiqueta();
      row[2] = idToTipoProducto.get(String.valueOf(p.getIdTipoProducto()));
      row[3] = Producto.detalleProducto(p);
      row[4] = nmbF.format(p.getPrecioCosto());
      row[5] = nmbF.format(p.getPrecioPublico());
      row[6] = p.getCantidad();
      row[7] = nmbF.format( (p.getPrecioPublico() - p.getPrecioCosto()) *
                           p.getCantidad());
      lista.add(row);
    }
    return lista;
  }

  public List<?> findUltimas10Compras() {
    ultimasCompras = DaoCompras.findComprasAbiertas();
    return ultimasCompras;
  }

  public void seleccionarCompra(int id) {
    compraSeleccionada = DaoCompras.findById(id);
    //Tambien seleccionamos los productos en inventario que pertenecen a esta compra.
    this.productosRegistrados = DaoInventarios.findProductosByIdCompra(id);
    idToTipoProducto = DaoTipoProductos.getMap();
  }

  public Compra getCompraSeleccionada() {
    return compraSeleccionada;
  }

  public Map<?, ?> getIdToTipoProducto() {
    return idToTipoProducto;
  }

  public List<Producto> getProductosRegistrados() {
    return productosRegistrados;
  }

  public Proveedor getProveedor() {
    if (compraSeleccionada != null) {
      return DaoProveedores.findById(compraSeleccionada.getIdProveedor());
    }
    else {
      return null;
    }
  }

  public boolean cerrarCompraSeleccionada() {
    return DaoCompras.cerrarCompra(compraSeleccionada.getId());
  }

  /**
   * imprimirEtiquetas
   *
   * @param i int
   * @param i1 int
   */
  public boolean imprimirEtiquetas(int posicionEtiqueta, int fromIndex,
                                   int toIndex, int tipo) {
    //Tenemos un intervalo para imprimir etiquetas, mandamos a imprimir
    com.boutique.impresiones.HojaEtiquetas hoja = new com.boutique.impresiones.
        HojaEtiquetas();
    if (tipo == 1) {
      hoja.imprimirEtiquetasJanel(this.productosRegistrados.subList(fromIndex,
          toIndex + 1), posicionEtiqueta, idToTipoProducto);
    }
    else if (tipo == 2) {
      hoja.imprimirEtiquetasTukStick(this.productosRegistrados.subList(
          fromIndex,
          toIndex + 1), posicionEtiqueta, idToTipoProducto);
    }

    return false;
  }

  public boolean imprimirUnaEtiqueta(int posicionEtiqueta, int index,
                                     int index2) {
    //Tenemos un intervalo para imprimir etiquetas, mandamos a imprimir
    com.boutique.impresiones.HojaEtiquetas hoja = new com.boutique.impresiones.
        HojaEtiquetas();

    hoja.imprimirUnaEtiquetaJanel(this.productosRegistrados.subList(index,
        index2 + 1), posicionEtiqueta, idToTipoProducto);

    return false;
  }

  /**
   * seleccionarProductoaEditar
   *
   * @param i int
   * @return Producto
   */
  public void seleccionarProductoaEditar(int i) {
    productoaEditar = this.productosRegistrados.get(i);
  }

  /**
   * setNuevaCantidadEnProductoSeleccionado
   *
   * @param cantidad int
   */
  public boolean setNuevaCantidadEnProductoSeleccionado(int cantidad) {
    int cantidadAnterior = productoaEditar.getCantidad();
    productoaEditar.setCantidad(cantidad);
    if (!DaoInventarios.actualizarProductoExistente(productoaEditar)) {
      productoaEditar.setCantidad(cantidadAnterior);
      return false;
    }
    else {
      return true;
    }
  }

  /**
   * setNuevosPrecios
   *
   * @param precioCosto double
   * @param precioPublico double
   * @return boolean
   */
  public boolean setNuevosPrecios(double precioCosto, double precioPublico) {
    double antiguoPrecioCosto = productoaEditar.getPrecioCosto();
    double antiguoPrecioPublico = productoaEditar.getPrecioPublico();
    productoaEditar.setPrecioCosto(precioCosto);
    productoaEditar.setPrecioPublico(precioPublico);
    if (!DaoInventarios.actualizarProductoExistente(productoaEditar)) {
      productoaEditar.setPrecioCosto(antiguoPrecioCosto);
      productoaEditar.setPrecioPublico(antiguoPrecioPublico);
      return false;
    }
    else {
      return true;
    }

  }

  /**
   * eliminarProducto
   *
   * @param i int
   * @return boolean
   */
  public boolean eliminarProducto(int i) {
    Producto p = productosRegistrados.get(i);
    if (DaoInventarios.borrarProducto(p)) {
      productosRegistrados.remove(i);
      return true;
    }
    else {
      return false;
    }
  }
}
