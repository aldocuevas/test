package com.boutique.domain;

import java.sql.*;
import java.util.*;

public class Venta {
	protected int idCliente;
	private int id;
	private int idVendedor;
	private Timestamp fecha;
	private VentaTypeEnum ventaTypeEnum = VentaTypeEnum.CONTADO;
	private List<ProductoVendido> productosVendidos;
	private List<ProductoDevuelto> productosDevueltos;
	private List<Pago> pagos;
	public double abonado;
	private double total;
	private int enCorte;
	private int status;
	private int idBoutique;
	private int idTerminal;
	private boolean requiereFacturaIndividual=false;

	public Venta() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setProductosVendidos(List<ProductoVendido> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}

	public void setProductosDevueltos(List<ProductoDevuelto> productosDevueltos) {
		this.productosDevueltos = productosDevueltos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public void setEnCorte(int enCorte) {
		this.enCorte = enCorte;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setIdBoutique(int idBoutique) {
		this.idBoutique = idBoutique;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setAbonado(double saldoTotal) {
		this.abonado = saldoTotal;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public List<ProductoVendido> getProductosVendidos() {
		return productosVendidos;
	}

	public List<ProductoDevuelto> getProductosDevueltos() {
		return productosDevueltos;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public int getEnCorte() {
		return enCorte;
	}

	public int getStatus() {
		return status;
	}

	public int getIdBoutique() {
		return idBoutique;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getAbonado() {
		return abonado;
	}

	public double getTotal() {
		return total;
	}

	private void jbInit() throws Exception {
	}

	public void setVentaTypeEnum(VentaTypeEnum ventaTypeEnum) {
		this.ventaTypeEnum = ventaTypeEnum;
	}

	public VentaTypeEnum getVentaTypeEnum() {
		return ventaTypeEnum;
	}

	public void setIdTerminal(int idTerminal) {
		this.idTerminal = idTerminal;
	}

	public int getIdTerminal() {
		return idTerminal;
	}

	public void setRequiereFacturaIndividual(boolean requiereFacturaIndividual) {
		this.requiereFacturaIndividual = requiereFacturaIndividual;
	}

	public boolean isRequiereFacturaIndividual() {
		return requiereFacturaIndividual;
	}

}
