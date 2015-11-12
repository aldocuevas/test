package com.boutique.engine.impl;

import java.util.*;

import com.boutique.dao.*;
import com.boutique.domain.*;

public class CatalogoClientesEngine {
  private Cliente clienteSeleccionado;
  private List<Colonia> colonias;
  private Colonia coloniaSeleccionada;
  private List<ClasificacionCliente> clasificaciones;
  public CatalogoClientesEngine() {
    colonias = DaoColonias.findAll();
    clasificaciones = DaoClientesSucursal.findClasificaciones();
  }

  public List<Colonia> colonias() {
    return colonias;
  }

  public Colonia coloniaSeleccionada() {
    return coloniaSeleccionada;
  }

  public void seleccionarColonia(int id) {
    coloniaSeleccionada = colonias.get(id);
  }

  /**
   * getClienteSeleccionado
   *
   * @param i int
   * @return Cliente
   */


  /**
   * setClienteSeleccionado
   *
   * @param i int
   */
  public Cliente setClienteSeleccionado(int id) {
    clienteSeleccionado = null;
    clienteSeleccionado = DaoClientesCentral.findById(id);
    return clienteSeleccionado;
  }

  public List<ClasificacionCliente> getClasificaciones() {
    return clasificaciones;
  }

}
