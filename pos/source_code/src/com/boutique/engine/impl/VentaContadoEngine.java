package com.boutique.engine.impl;

import java.text.*;
import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.helper.IvaHelper;

public class VentaContadoEngine {
	private Cliente cliente = null;
	private Venta venta;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	List<ProductoVendido> productos;
	List<Object[]> productosView;
	List<Pago> pagos;
	List<?> pagosView;
	java.sql.Timestamp fecha = new java.sql.Timestamp(
			new java.util.Date().getTime());
	double total = 0.0;
	Map<?, ?> idToTipoProducto;
	NumberFormat number = NumberFormat
			.getNumberInstance(java.util.Locale.ENGLISH);

	public VentaContadoEngine() {
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
	}

	public List<Object[]> productosView() {
		return productosView;
	}

	public void iniciarVenta(int idVendedor) {
		venta = new Venta();
		venta.setEnCorte(0);
		venta.setIdVendedor(idVendedor);
		venta.setFecha(fecha);
		venta.setStatus(1); // Pagado
		venta.setIdBoutique(AppInstance.boutique().getId());
		venta.setIdTerminal(AppInstance.terminal().getId());
		productos = new ArrayList<ProductoVendido>();
		pagos = new ArrayList<Pago>();
		productosView = new ArrayList<Object[]>();
		pagosView = new ArrayList<Object>();
		idToTipoProducto = DaoTipoProductos.getMapLocal();
	}

	public boolean agregarProducto(String etiqueta, int idBoutique) {
		// Obtenemos del inventario inicial
		int cantidadUsados = 0;
		// Obtenemos del inventario inicial
		int idProducto = -1;
		for (ProductoVendido pv : productos) {
			if (pv.getEtiqueta().equals(etiqueta)) {
				if (pv.getIdInventario() != idProducto) {
					cantidadUsados = 0;
				}
				idProducto = pv.getIdInventario();
				cantidadUsados++;
			}
		}

		Producto producto = DaoInventarios.findByEtiquetaAndIdBoutique(
				etiqueta, idBoutique, idProducto, cantidadUsados);
		if (producto != null) {
			// Generamos el producto vendido
			ProductoVendido pv = new ProductoVendido();
			pv.setId(producto.getId());
			pv.setIdTipoProducto(producto.getIdTipoProducto());
			pv.setDescuento(0);
			pv.setEntregado(1);
			pv.setEtiqueta(etiqueta);
			pv.setModelo(producto.getModelo());
			pv.setPrecioInicial(producto.getPrecioPublico());
			pv.setImpuestoPrecioFinal(producto.getImpuestoPrecioPublicoFinal());
			pv.setPrecioFinal(producto.getPrecioPublico());
			pv.setIdInventario(producto.getId());
			productos.add(pv);
			total += pv.getPrecioFinal();
			// Agregamos el producto a productosView
			Object[] row = new Object[6];
			row[0] = "1";
			String tipoPr = idToTipoProducto.get(
					String.valueOf(pv.getIdTipoProducto())).toString();
			row[1] = ((tipoPr.length() > 13) ? tipoPr.substring(0, 12) : tipoPr)
					+ " " + Producto.detalleProducto(producto);
			row[2] = producto.getEtiqueta();
			row[3] = number.format(pv.getPrecioInicial());
			row[4] = "0%";
			row[5] = number.format(pv.getPrecioFinal());
			productosView.add(row);
			return true;
		} else {
			return false;
		}

		// Generamos el producto vendido
		// Buscamos el producto en inventario

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
			pago.setIdTerminal(AppInstance.terminal().getId());
		}
		this.pagos = pagos;
	}

	public boolean registrarVenta() {
		venta.setProductosVendidos(productos);
		venta.setPagos(pagos);
		if (cliente != null) {
			venta.setIdCliente(cliente.getId());
		}
		return DaoVentas.registrarVenta(venta);
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
	 * @param index
	 *            int
	 */
	public void aplicarDescuentoaProductoSeleccionado(int index, int descuento) {
		ProductoVendido pv = productos.get(index);
		this.total -= pv.getPrecioFinal();
		pv.setDescuento(descuento);
		double precioFinal = pv.getPrecioInicial()
				- (pv.getPrecioInicial() * (descuento * .01));
		pv.setPrecioFinal(precioFinal);
		pv.setImpuestoPrecioFinal(IvaHelper.getIvaDesglosadFromMonto(precioFinal));
		// Modificamos la vista
		Object[] row = (Object[]) productosView.get(index);
		row[4] = descuento + "%";
		row[5] = number.format(precioFinal);

		this.total += pv.getPrecioFinal();
	}

	/**
	 * aplicarDescuentoaNota
	 * 
	 * @param i
	 *            int
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
		 * com.boutique.impresiones.PrintTask obj = new
		 * com.boutique.impresiones. PrintTask(1,this.venta); //Venta de contado
		 * Thread t = new Thread(obj); t.start();
		 */
		@SuppressWarnings("unused")
		com.boutique.impresiones.ventas.PrintVentaContado vc = new com.boutique.impresiones.ventas.PrintVentaContado(
				this.venta, this.productosView, this.total, 0, this.total,
				com.boutique.util.Conversor.convertir(this.total), cliente);
	}

}
