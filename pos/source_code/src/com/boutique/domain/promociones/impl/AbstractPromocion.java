package com.boutique.domain.promociones.impl;

import java.util.Date;
import java.util.List;

import com.boutique.domain.Boutique;
import com.boutique.domain.Venta;
import com.boutique.domain.VentaTypeEnum;

public abstract class AbstractPromocion {
	private List<Boutique> boutiques;
	private List<VentaTypeEnum> ventas;
	private Date startDate;
	private Date endDate;
	private boolean aplicaConOtrasPromociones;
	
	boolean aplicaPromocion(Boutique boutique, Venta venta){
		if (boutiques.contains(boutique) && ventas.contains(venta.getVentaTypeEnum())){
			return true;
		}
		return false;
	}
	

	public void setBoutiques(List<Boutique> boutiques) {
		this.boutiques = boutiques;
	}

	public List<Boutique> getBoutiques() {
		return boutiques;
	}

	public void setVentas(List<VentaTypeEnum> ventas) {
		this.ventas = ventas;
	}

	public List<VentaTypeEnum> getVentas() {
		return ventas;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}


	public void setAplicaConOtrasPromociones(boolean aplicaConOtrasPromociones) {
		this.aplicaConOtrasPromociones = aplicaConOtrasPromociones;
	}


	public boolean isAplicaConOtrasPromociones() {
		return aplicaConOtrasPromociones;
	}

	
}
