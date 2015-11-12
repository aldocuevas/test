package com.boutique.domain;

import com.boutique.engine.impl.AppInstance;

public class Producto {
  private int id;
  private int idTipoProducto;
  private int modelo;
  private String talla;
  private int idBoutique;
  private int cantidad;
  private double precioCostoInicial;
  private double impuestoPrecioCosto;
  private double gasto;
  private double impuestoGasto;
  private double precioCosto;
  private double precioPublicoInicial;
  private double impuestoPrecioPublicoInicial;
  private double precioPublicoInicialTotal;
  private double precioPublicoFinal;
  private double impuestoPrecioPublicoFinal;
  private double precioPublico;
  private int clave;
  private int idCompra;
  private String etiqueta;
  private String estilo;
  private int idMarca;
  private int idDescripcion;
  private int idColor;
  
  public double getPrecioCostoInicial() {
	return precioCostoInicial;
}

public void setPrecioCostoInicial(double precioCostoInicial) {
	this.precioCostoInicial = precioCostoInicial;
}

public double getImpuestoPrecioCosto() {
	return impuestoPrecioCosto;
}

public void setImpuestoPrecioCosto(double impuestoPrecioCosto) {
	this.impuestoPrecioCosto = impuestoPrecioCosto;
}

 

public double getImpuestoGasto() {
	return impuestoGasto;
}

public void setImpuestoGasto(double impuestoGasto) {
	this.impuestoGasto = impuestoGasto;
}

public double getPrecioPublicoInicial() {
	return precioPublicoInicial;
}

public void setPrecioPublicoInicial(double precioPublicoInicial) {
	this.precioPublicoInicial = precioPublicoInicial;
}

public double getImpuestoPrecioPublicoInicial() {
	return impuestoPrecioPublicoInicial;
}

public void setImpuestoPrecioPublicoInicial(double impuestoPrecioPublicoInicial) {
	this.impuestoPrecioPublicoInicial = impuestoPrecioPublicoInicial;
}

public double getPrecioPublicoInicialTotal() {
	return precioPublicoInicialTotal;
}

public void setPrecioPublicoInicialTotal(double precioPublicoInicialTotal) {
	this.precioPublicoInicialTotal = precioPublicoInicialTotal;
}

public double getPrecioPublicoFinal() {
	return precioPublicoFinal;
}

public void setPrecioPublicoFinal(double precioPublicoFinal) {
	this.precioPublicoFinal = precioPublicoFinal;
}

public double getImpuestoPrecioPublicoFinal() {
	return impuestoPrecioPublicoFinal;
}

public void setImpuestoPrecioPublicoFinal(double impuestoPrecioPublicoFinal) {
	this.impuestoPrecioPublicoFinal = impuestoPrecioPublicoFinal;
}


  public Producto() {
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setIdTipoProducto(int idTipoProducto) {
    this.idTipoProducto = idTipoProducto;
  }

  public void setModelo(int modelo) {
    this.modelo = modelo;
  }

  public void setTalla(String talla) {
    this.talla = talla;
  }

  public void setIdBoutique(int idBoutique) {
    this.idBoutique = idBoutique;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public void setPrecioCosto(double precioCosto) {
    this.precioCosto = precioCosto;
  }

  public void setPrecioPublico(double precioPublico) {
    this.precioPublico = precioPublico;
  }

  public void setClave(int clave) {
    this.clave = clave;
  }

  public void setIdCompra(int idCompra) {
    this.idCompra = idCompra;
  }

  public void setEtiqueta(String etiqueta) {
    this.etiqueta = etiqueta;
  }

  public void setEstilo(String estilo) {
    this.estilo = estilo;
  }

  public void setIdDescripcion(int idDescripcion) {
    this.idDescripcion = idDescripcion;
  }

  public void setIdMarca(int idMarca) {
    this.idMarca = idMarca;
  }

  public void setIdColor(int idColor) {
    this.idColor = idColor;
  }

  public int getId() {
    return id;
  }

  public int getIdTipoProducto() {
    return idTipoProducto;
  }

  public int getModelo() {
    return modelo;
  }

  public String getTalla() {
    return talla;
  }

  public int getIdBoutique() {
    return idBoutique;
  }

  public int getCantidad() {
    return cantidad;
  }

  public double getPrecioCosto() {
    return precioCosto;
  }

  public double getPrecioPublico() {
    return precioPublico;
  }

  public int getClave() {
    return clave;
  }

  public int getIdCompra() {
    return idCompra;
  }

  public String getEtiqueta() {
    return etiqueta;
  }

  public String getEstilo() {
    return estilo;
  }

  public int getIdDescripcion() {
    return idDescripcion;
  }

  public int getIdMarca() {
    return idMarca;
  }

  public int getIdColor() {
    return idColor;
  }

  public static String detalleProducto(Producto p) {
   String detalle = "";
   if (p.getEstilo() != null && !p.getEstilo().equals("")) {
     detalle += p.getEstilo() + " ";
   }
   if (p.getIdMarca() > 0) {
     detalle += AppInstance.id2marcas.get(p.getIdMarca()) + " ";
   }
   if (p.getIdDescripcion() > 0) {
     detalle += AppInstance.id2descripciones.get(p.getIdDescripcion()) + " ";
   }
   if (p.getIdColor() > 0) {
     detalle += AppInstance.id2colores.get(p.getIdColor()) + " ";
   }
   return detalle;
 }

public void setGasto(double gasto) {
	this.gasto = gasto;
}

public double getGasto() {
	return gasto;
}

}
