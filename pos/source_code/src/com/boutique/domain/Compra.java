package com.boutique.domain;

import java.sql.*;

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
public class Compra {
  private int id;
  private Timestamp fecha;
  private int idProveedor;
  private String noReferencia;
  private String status;
  private Factura factura = new Factura();
  public Compra() {
	  
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFecha(Timestamp fecha) {
    this.fecha = fecha;
  }

  public void setIdProveedor(int idProveedor) {
    this.idProveedor = idProveedor;
  }

  public void setNoReferencia(String noReferencia) {
    this.noReferencia = noReferencia;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public Timestamp getFecha() {
    return fecha;
  }

  public int getIdProveedor() {
    return idProveedor;
  }

  public String getNoReferencia() {
    return noReferencia;
  }

  public String getStatus() {
		return status;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Factura getFactura() {
		return factura;
	}
}
