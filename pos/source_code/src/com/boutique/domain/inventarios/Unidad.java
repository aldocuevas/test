package com.boutique.domain.inventarios;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.boutique.domain.TipoProducto;

public class Unidad {
	private int id;
	private String descripcion;
	private List<TipoProducto> tipoProductos;
	private Map<String, Integer> cantidadesPorTalla = new TreeMap<String, Integer>();

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setTipoProductos(List<TipoProducto> tipoProductos) {
		this.tipoProductos = tipoProductos;
	}

	public List<TipoProducto> getTipoProductos() {
		return tipoProductos;
	}

	public void setCantidadesPorTalla(Map<String, Integer> cantidadesPorTalla) {
		this.cantidadesPorTalla = cantidadesPorTalla;
	}

	public Map<String, Integer> getCantidadesPorTalla() {
		return cantidadesPorTalla;
	}

}
