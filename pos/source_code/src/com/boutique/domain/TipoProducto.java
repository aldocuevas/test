package com.boutique.domain;

import java.util.List;

import com.boutique.domain.inventarios.Unidad;

public class TipoProducto {
	int id;
	private String nombre;
	String tipoTalla;
	private List<Unidad> unidades;

	public TipoProducto() {
	}

	public int getId() {
		return id;
	}

	public String getNombre() {

		return nombre;
	}

	public String getTipoTalla() {
		return tipoTalla;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {

		this.nombre = nombre;
	}

	public void setTipoTalla(String tipoTalla) {
		this.tipoTalla = tipoTalla;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}
}
