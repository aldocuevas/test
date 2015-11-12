package com.boutique.util;
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
public class TestRespaldo {
  public TestRespaldo() {
  }

  public static void main(String[] args) {
  try {
    DaoSource.respaldarBD(
        "C:\\Users\\aldo\\Documents\\proyectos\\pilyboutique\\prueba1.sql");
  }
  catch (Exception ex) {
  }
  }
}
