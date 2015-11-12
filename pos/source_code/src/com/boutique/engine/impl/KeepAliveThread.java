package com.boutique.engine.impl;

import com.boutique.dao.DaoSource;

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
public class KeepAliveThread
    extends Thread {
  public KeepAliveThread() {

  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(90000);
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      DaoSource.getFechaActual();
//      System.out.println("HORA ACTUAL:" + DaoSource.getFechaActual());
    }
  }
}
