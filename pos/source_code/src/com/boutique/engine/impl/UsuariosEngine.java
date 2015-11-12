package com.boutique.engine.impl;

import java.util.*;
import com.boutique.domain.*;
import com.boutique.dao.*;

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
public class UsuariosEngine {
public  List<Usuario> usuariosEncontrados = null;
  public Usuario u = null;
  public UsuariosEngine() {
  }

  public void buscarUsuarios(String pattern) {
    usuariosEncontrados = DaoUsuarios.findUsuariosByNombre(pattern);
  }

  public void seleccionarUsuario(int index) {
    u = usuariosEncontrados.get(index);
  }

  public boolean agregarUsuario() {
    return DaoUsuarios.agregarUsuario(u);
  }

  public boolean modificarUsuario() {
    return DaoUsuarios.modificarUsuario(u);
  }

  public boolean eliminarUsuario() {
    return DaoUsuarios.eliminarUsuario(u.getId());
  }
}
