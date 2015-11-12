package com.boutique.domain;

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
public class Leyenda {
  private int id;
  private String leyenda;
  private int tipo;
  public Leyenda() {
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setLeyenda(String leyenda) {
    this.leyenda = leyenda;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public int getId() {
    return id;
  }

  public String getLeyenda() {
    return leyenda;
  }

  public int getTipo() {
    return tipo;
  }
}
