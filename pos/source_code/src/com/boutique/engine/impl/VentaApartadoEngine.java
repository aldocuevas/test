package com.boutique.engine.impl;

import java.util.*;

import com.boutique.helper.IvaHelper;
import com.boutique.impresiones.*;
import com.boutique.dao.*;
import com.boutique.domain.*;

public class VentaApartadoEngine {
  private VentaApartado venta;
  List<ProductoVendido> productos;
  List<Object[]> productosView;
  List<Pago> pagos;
  List<?> pagosView;
  java.sql.Timestamp fecha = new java.sql.Timestamp(new java.util.Date().
      getTime());
  double total = 0.0;
  public Cliente cliente = null;
  //NumberFormat number = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);

  public VentaApartadoEngine() {

  }

  public List<Object[]> productosView() {
    return productosView;
  }

  public void iniciarVenta(int idVendedor) {
    setVenta(new VentaApartado());
    getVenta().setEnCorte(0);
    getVenta().setIdVendedor(idVendedor);
    getVenta().setFecha(fecha);

    getVenta().setFechaFinalizacion(null);
    getVenta().setStatus(0); //Pagado
    getVenta().setIdBoutique(AppInstance.boutique().getId());
    getVenta().setIdTerminal(AppInstance.terminal().getId());
    productos = new ArrayList<ProductoVendido> ();
    pagos = new ArrayList<Pago> ();
    productosView = new ArrayList<Object[]>();
    pagosView = new ArrayList<Object>();

  }

  public boolean agregarProducto(String etiqueta) {
    //Obtenemos del inventario inicial
    int idProducto = -1;
    int cantidadUsados = 0;
    for (ProductoVendido pv : productos) {
      if (pv.getEtiqueta().equals(etiqueta)) {
        if (pv.getIdInventario() != idProducto) {
          cantidadUsados = 0;
        }
        idProducto = pv.getIdInventario();
        cantidadUsados++;
      }
    }

    //Obtenemos del inventario inicial
    Producto producto = DaoInventarios.findByEtiquetaAndIdBoutique(etiqueta,
        AppInstance.boutique().getId(), idProducto, cantidadUsados);
    if (producto != null) {
      //Generamos el producto vendido
      ProductoVendido pv = new ProductoVendido();
      pv.setId(producto.getId());
      pv.setIdTipoProducto(producto.getIdTipoProducto());
      pv.setDescuento(0);
      pv.setEntregado(0);
      pv.setEtiqueta(etiqueta);
      pv.setModelo(producto.getModelo());
      pv.setPrecioInicial(producto.getPrecioPublico());
      pv.setImpuestoPrecioFinal(IvaHelper.getIvaDesglosadFromMonto(producto.getPrecioPublico()));
      pv.setPrecioFinal(producto.getPrecioPublico());
      pv.setIdInventario(producto.getId());
      String tipoPr = AppInstance.idToTipoProducto.get(String.valueOf(pv.
          getIdTipoProducto())).toString();
      tipoPr = ( (tipoPr.length() > 13) ? tipoPr.substring(0, 12) : tipoPr);
      String detalle = tipoPr + " " + Producto.detalleProducto(producto);

      productos.add(pv);
      total += pv.getPrecioFinal();
      //Agregamos el producto a productosView
      Object[] row = new Object[6];
      row[0] = "1";
      row[1] = detalle;
      row[2] = producto.getEtiqueta();
      row[3] = AppInstance.number.format(pv.getPrecioInicial());
      row[4] = "0%";
      row[5] = AppInstance.number.format(pv.getPrecioFinal());
      productosView.add(row);
      return true;
    }
    else {
      return false;
    }

    //Generamos el producto vendido
    //Buscamos el producto en inventario


  }

  public void removerProducto(int index) {
    ProductoVendido pv = (ProductoVendido) productos.get(index);
    this.total -= pv.getPrecioFinal();
    productos.remove(index);
    productosView.remove(index);
  }

  public void agregarPagos(List<Pago> pagos) {
    for (Pago pago : pagos) {
      pago.setEnCorte(0);
      pago.setFecha(fecha);
      pago.setIdVendedor(AppInstance.usuario().getId());
      pago.setIdSucursal(AppInstance.boutique().getId());

    }
    this.pagos = pagos;
  }

  public boolean registrarVenta() {
    getVenta().setProductosVendidos(productos);
    getVenta().setPagos(pagos);
    return DaoVentas.registrarVenta(getVenta());
  }

  /**
   * getTotal
   *
   * @return boolean
   */
  public double getTotal() {
    return total;
  }

  /**
   * aplicarDescuentoaProductoSeleccionado
   *
   * @param index int
   */
  public void aplicarDescuentoaProductoSeleccionado(int index, int descuento) {
    ProductoVendido pv = productos.get(index);
    this.total -= pv.getPrecioFinal();
    pv.setDescuento(descuento);
    double precioFinal = pv.getPrecioInicial() -
        (pv.getPrecioInicial() * (descuento * .01));
    pv.setPrecioFinal(precioFinal);
    pv.setImpuestoPrecioFinal(IvaHelper.getIvaDesglosadFromMonto(precioFinal));
    //Modificamos la vista
    Object[] row = (Object[]) productosView.get(index);
    row[4] = descuento + "%";
    row[5] = AppInstance.number.format(precioFinal);

    this.total += pv.getPrecioFinal();
  }

  /**
   * aplicarDescuentoaNota
   *
   * @param i int
   */
  public void aplicarDescuentoaNota(int descuento) {
    for (int i = 0; i < productos.size(); i++) {
      this.aplicarDescuentoaProductoSeleccionado(i, descuento);
    }
  }

  /**
   * imprimirNota
   */
  public void imprimirNota() {
    /*
         com.boutique.impresiones.PrintTask obj = new com.boutique.impresiones.
        PrintTask(3, this.venta); //Venta de contado
         Thread t = new Thread(obj);
         t.start();*/
    double d = 0;
    for (Pago p : this.pagos) {
      d += p.getMonto();
    }
    @SuppressWarnings("unused")
	PrintVentaApartado va = new PrintVentaApartado(this.getVenta(),
        this.productosView, getVenta().getCliente(), getVenta().getDomicilio(),
        getVenta().getTelefono(), this.total, d, this.total - d,
        com.boutique.util.Conversor.convertir(this.total));

  }

  public void setDatosCliente(String nombreCliente, String domicilio,
                              String telefono) {
    getVenta().setCliente(nombreCliente);
    getVenta().setDomicilio(domicilio);
    getVenta().setTelefono(telefono);
		if (cliente != null) {
      getVenta().setIdCliente(cliente.getId());
      //   venta.setIdCliente(cliente.getId());
    }
  }

public void setVenta(VentaApartado venta) {
	this.venta = venta;
}

public VentaApartado getVenta() {
	return venta;
}

}
