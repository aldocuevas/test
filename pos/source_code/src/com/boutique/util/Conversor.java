package com.boutique.util;

/*
 * Conversor.java
 *
 * Created on April 14, 2008, 1:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author aldo
 */
public class Conversor {
	
	
 
  static String numu;
  static String numd;
  static String numce;
  static String numm;
  static String numde;
  static String num_letracm;
  static String num_letramm;
  static String num_letradmm;
  static String num_letracms;
  static String num_letrammd;
  static String numf;
  /** Creates a new instance of Conversor */
  public Conversor() {
  }

  public static void main(String ar[]) {
    System.out.println("Cantidad con letra: " + convertir(225268.11));
  }

  public static String unidad(double numuero) {
    double nn = numuero;
    nn -= (int) numuero;

    switch ( (int) numuero) {
      case 9: {
        numu = "NUEVE";
        break;
      }
      case 8: {
        numu = "OCHO";
        break;
      }
      case 7: {
        numu = "SIETE";
        break;
      }
      case 6: {
        numu = "SEIS";
        break;
      }
      case 5: {
        numu = "CINCO";
        break;
      }
      case 4: {
        numu = "CUATRO";
        break;
      }
      case 3: {
        numu = "TRES";
        break;
      }
      case 2: {
        numu = "DOS";
        break;
      }
      case 1: {
        numu = "UN";
        break;
      }
      case 0: {
        numu = "";
        break;
      }
    }
    return numu;
  }

  public static String decena(double numdero) {

    if (numdero >= 90 && numdero <= 99) {
      numd = "NOVENTA ";
      if (numdero > 90) {
        numd = numd + "Y " + (unidad(numdero - 90));
      }
    }
    else if (numdero >= 80 && numdero <= 89) {
      numd = "OCHENTA ";
      if (numdero > 80) {
        numd = numd + "Y " + (unidad(numdero - 80));
      }
    }
    else if (numdero >= 70 && numdero <= 79) {
      numd = "SETENTA ";
      if (numdero > 70) {
        numd = numd + "Y " + (unidad(numdero - 70));
      }
    }
    else if (numdero >= 60 && numdero <= 69) {
      numd = "SESENTA ";
      if (numdero > 60) {
        numd = numd + "Y " + (unidad(numdero - 60));
      }
    }
    else if (numdero >= 50 && numdero <= 59) {
      numd = "CINCUENTA ";
      if (numdero > 50) {
        numd = numd + "Y " + (unidad(numdero - 50));
      }
    }
    else if (numdero >= 40 && numdero <= 49) {
      numd = "CUARENTA ";
      if (numdero > 40) {
        numd = numd + "Y " + (unidad(numdero - 40));
      }
    }
    else if (numdero >= 30 && numdero <= 39) {
      numd = "TREINTA ";
      if (numdero > 30) {
        numd = numd + "Y " + (unidad(numdero - 30));
      }
    }
    else if (numdero >= 20 && numdero <= 29) {
      if (numdero == 20) {
        numd = "VEINTE ";
      }
      else {
        numd = "VEINTI" + (unidad(numdero - 20));
      }
    }
    else if (numdero >= 10 && numdero <= 19) {
      switch ( (int) numdero) {
        case 10: {
          numd = "DIEZ ";
          break;
        }
        case 11: {
          numd = "ONCE";
          break;
        }
        case 12: {
          numd = "DOCE ";
          break;
        }
        case 13: {
          numd = "TRECE ";
          break;
        }
        case 14: {
          numd = "CATORCE ";
          break;
        }
        case 15: {
          numd = "QUINCE ";
          break;
        }
        case 16: {
          numd = "DIECISEIS ";
          break;
        }
        case 17: {
          numd = "DIECISIETE ";
          break;
        }
        case 18: {
          numd = "DIECIOCHO ";
          break;
        }
        case 19: {
          numd = "DIECINUEVE ";
          break;
        }
      }
    }
    else {
      numd = unidad(numdero);
    }
    return numd;
  }

  public static String centena(double numc) {
    if (numc >= 100) {
      if (numc >= 900 && numc <= 999) {
        numce = "NOVECIENTOS ";
        if (numc > 900) {
          numce = numce + (decena(numc - 900));
        }
      }
      else if (numc >= 800 && numc <= 899) {
        numce = "OCHOCIENTOS ";
        if (numc > 800) {
          numce = numce + (decena(numc - 800));
        }
      }
      else if (numc >= 700 && numc <= 799) {
        numce = "SETECIENTOS ";
        if (numc > 700) {
          numce = numce + (decena(numc - 700));
        }
      }
      else if (numc >= 600 && numc <= 699) {
        numce = "SEISCIENTOS ";
        if (numc > 600) {
          numce = numce + (decena(numc - 600));
        }
      }
      else if (numc >= 500 && numc <= 599) {
        numce = "QUINIENTOS ";
        if (numc > 500) {
          numce = numce + (decena(numc - 500));
        }
      }
      else if (numc >= 400 && numc <= 499) {
        numce = "CUATROCIENTOS ";
        if (numc > 400) {
          numce = numce + (decena(numc - 400));
        }
      }
      else if (numc >= 300 && numc <= 399) {
        numce = "TRESCIENTOS ";
        if (numc > 300) {
          numce = numce + (decena(numc - 300));
        }
      }
      else if (numc >= 200 && numc <= 299) {
        numce = "DOSCIENTOS ";
        if (numc > 200) {
          numce = numce + (decena(numc - 200));
        }
      }
      else if (numc >= 100 && numc <= 199) {
        if (numc == 100) {
          numce = "CIEN ";
        }
        else {
          numce = "CIENTO " + (decena(numc - 100));
        }
      }
    }
    else {
      numce = decena(numc);
    }

    return numce;
  }

  public static String miles(double nummero) {
    if (nummero >= 1000 && nummero < 2000) {
      numm = "MIL " + (centena(nummero % 1000));
    }
    if (nummero >= 2000 && nummero < 10000) {
      numm = unidad(Math.floor(nummero / 1000)) + " MIL " +
          (centena(nummero % 1000));
    }
    if (nummero < 1000) {
      numm = centena(nummero);
    }

    return numm;
  }

  public static String decmiles(double numdmero) {
    if (numdmero == 10000) {
      numde = "DIEZ MIL";
    }
    if (numdmero > 10000 && numdmero < 20000) {
      numde = decena(Math.floor(numdmero / 1000)) + "MIL " +
          (centena(numdmero % 1000));
    }
    if (numdmero >= 20000 && numdmero < 100000) {
      numde = decena(Math.floor(numdmero / 1000)) + " MIL " +
          (miles(numdmero % 1000));
    }
    if (numdmero < 10000) {
      numde = miles(numdmero);
    }

    return numde;
  }

  public static String cienmiles(double numcmero) {
    if (numcmero == 100000) {
      num_letracm = "CIEN MIL";
    }
    if (numcmero >= 100000 && numcmero < 1000000) {
      num_letracm = centena(Math.floor(numcmero / 1000)) + " MIL " +
          (centena(numcmero % 1000));
    }
    if (numcmero < 100000) {
      num_letracm = decmiles(numcmero);
    }
    return num_letracm;
  }

  public static String millon(double nummiero) {
    if (nummiero >= 1000000 && nummiero < 2000000) {
      num_letramm = "UN MILLON " + (cienmiles(nummiero % 1000000));
    }
    if (nummiero >= 2000000 && nummiero < 10000000) {
      num_letramm = unidad(Math.floor(nummiero / 1000000)) + " MILLONES " +
          (cienmiles(nummiero % 1000000));
    }
    if (nummiero < 1000000) {
      num_letramm = cienmiles(nummiero);
    }

    return num_letramm;
  }

  public static String decmillon(double numerodm) {
    if (numerodm == 10000000) {
      num_letradmm = "DIEZ MILLONES";
    }
    if (numerodm > 10000000 && numerodm < 20000000) {
      num_letradmm = decena(Math.floor(numerodm / 1000000)) + "MILLONES " +
          (cienmiles(numerodm % 1000000));
    }
    if (numerodm >= 20000000 && numerodm < 100000000) {
      num_letradmm = decena(Math.floor(numerodm / 1000000)) + " MILLONES " +
          (millon(numerodm % 1000000));
    }
    if (numerodm < 10000000) {
      num_letradmm = millon(numerodm);
    }

    return num_letradmm;
  }

  public static String cienmillon(double numcmeros) {
    if (numcmeros == 100000000) {
      num_letracms = "CIEN MILLONES";
    }
    if (numcmeros >= 100000000 && numcmeros < 1000000000) {
      num_letracms = centena(Math.floor(numcmeros / 1000000)) + " MILLONES " +
          (millon(numcmeros % 1000000));
    }
    if (numcmeros < 100000000) {
      num_letracms = decmillon(numcmeros);
    }
    return num_letracms;
  }

  public static String milmillon(double nummierod) {
    if (nummierod >= 1000000000 && nummierod < 2000000000) {
      num_letrammd = "MIL " + (cienmillon(nummierod % 1000000000));
    }
    if (nummierod >= 2000000000.00 && nummierod < 10000000000.00) {
      num_letrammd = unidad(Math.floor(nummierod / 1000000000)) + " MIL " +
          (cienmillon(nummierod % 1000000000));
    }
    if (nummierod < 1000000000) {
      num_letrammd = cienmillon(nummierod);
    }

    return num_letrammd;
  }

  public static String convertir(double numero) {
    int centavos = (int) Math.round( (numero - (int) numero) * 100);
    numf = milmillon(numero);
    numf += " PESOS " + ( (centavos < 10) ? "0" + centavos : centavos) +
        "/100 M.N.";
    return numf;
  }

  public static String tiempoTranscurrido(java.sql.Timestamp fecha) {
        String msg = "";
        long segundos = (new java.util.Date().getTime() - fecha.getTime()) / 1000;
        long horas = 0;
        long dias = 0;
        long minutos = 0;

        if (segundos < 0) {
            msg = "0 seg";
            return msg;
        }
        if (segundos >= 86400) {
            dias = segundos / 86400;
        } else {
            dias = 0;
        }
        if (segundos - (dias * 86400) >= 3600) {
            horas = (segundos - (dias * 86400)) / 3600;
        } else {
            horas = 0;
        }
        if ((segundos - (horas * 3600) - (dias * 86400)) >= 60) {
            minutos = (segundos - (horas * 3600) - (dias * 86400)) / 60;
        } else {
            minutos = 0;

        }
        segundos = segundos - (minutos * 60) - (horas * 3600) - (dias * 86400);
        if (segundos > 0) {
            msg = segundos + " seg";
        }
        if (minutos > 0) {
            if (msg.length() > 0) {
                msg = minutos + " min y " + msg;
            } else {
                msg = minutos + " min.";
            }
        }
        if (horas > 0) {
            if (msg.length() > 0) {
                msg = horas + " hr, " + msg;
            } else {
                msg = horas + " hr.";
            }
        }
        if (dias > 0) {
            String strDia = null;
            if (dias > 1) {
                strDia = "dias";
            } else {
                strDia = "dia";
            }
            if (msg.length() > 0) {

                msg = dias + " " + strDia + ", " + msg;
            } else {
                msg = dias + " " + strDia;
            }
        }

        return msg;
    }

}
