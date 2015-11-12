package com.boutique.impresiones;

import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.font.*;

import report.*;
import com.boutique.domain.*;
import java.io.*;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeException;
import java.awt.image.BufferedImage;
import net.sourceforge.barbecue.output.OutputException;
import com.boutique.util.PFMyImage;
import net.sourceforge.barbecue.BarcodeImageHandler;

import com.boutique.engine.impl.AppInstance;

public class HojaEtiquetas {
  Map<?, ?> idToTipoProducto;
  double incrementoX;
  double incrementoY;
  double x;
  double y;
  PFDocument d = new PFDocument();
  //Agregamos una pagina
  PFPage page = new PFPage();
  java.text.NumberFormat nmbf = java.text.NumberFormat.getNumberInstance(Locale.
      US);

  PFPageFormat f = new PFPageFormat();

  public void imprimirUnaEtiquetaJanel(java.util.List<Producto> productos,
                                       int posInicial, Map<?, ?> idToTipoProducto) {
//Obtenemos los valores iniciales de X y Y
    try {
      File file = new File("mi.ser");
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream oi = new ObjectInputStream(fis);
      Object obj = oi.readObject();
      MargenesImpresion mi = (MargenesImpresion) obj;
      double xSer = mi.getX();
      double ySer = mi.getY();
      nmbf.setMaximumFractionDigits(2);
      nmbf.setMinimumFractionDigits(2);
      this.idToTipoProducto = idToTipoProducto;

      d.showPrintDialog(true);
      incrementoX = 2.8;
      incrementoY = 1.5;
      f.setTopMargin(new PFInchUnit(0.05));
      f.setLeftMargin(new PFInchUnit(0.3));
      f.setBottomMargin(new PFInchUnit(0.05));
      f.setRightMargin(new PFInchUnit(0.3));
      f.setPageSize(new PFSize(new PFInchUnit(6), new PFInchUnit(8.8)));
      f.setPageOrientation(PFPageFormat.LANDSCAPE);
      y = ySer;

      for (Producto p : productos) {
        for (int cantidad = 0; cantidad < 1; cantidad++) {
          if ( (posInicial % 3) == 1) { //Nuevo renglon
            //Sacamos el cociente y le sumamos a las y
            y = (incrementoY * ( (posInicial - 1) / 3)) + ySer;

            x = xSer;
            //X=inicial;
          }
          else {
            //Incrementamos la x
            y = (incrementoY * ( (posInicial - 1) / 3)) + ySer;
            x = (incrementoX * ( (posInicial - 1) % 3)) + xSer;
          }
          imprimeEtiqueta(x, y, p);
          if (posInicial == 12) {
            posInicial = 1;
            page.setPageFormat(f);
            d.addPage(page);
            page = new PFPage();
          }
          else {
            posInicial++;
          }

        }
      }
      if (posInicial < 12) {
        page.setPageFormat(f);
        d.addPage(page);
      }
      d.print();
    }
    catch (ClassNotFoundException ex) {
      System.err.println(ex.toString());
    }
    catch (FileNotFoundException ex) {
      System.err.println(ex.toString());

    }
    catch (IOException ex) {
      System.err.println(ex.toString());

    }
    /*
        for (int i = 0; i < etiquetas.size(); i++) {

          if ( (posInicial % 4) == 1) { //Nuevo renglon
            //Sacamos el cociente y le sumamos a las y
            y = (incrementoY * ( (posInicial - 1) / 4)) + 0.16;
            if (y == 0.0) {
              y = 0.16;
            }
            x = 0.34;
            //X=inicial;
          }
          else {
            //Incrementamos la x
            y = (incrementoY * ( (posInicial - 1) / 4)) + 0.16;

            x = (incrementoX * ( (posInicial - 1) % 4)) + 0.34;
          }
//Imprimimos la etiqueta
          imprimeEtiqueta(x, y, (Object[]) etiquetas.get(i));
          if (posInicial == 28) {
            posInicial = 0;
            page.setPageFormat(f);
            d.addPage(page);
            page = new PFPage();
          }
          else {
            posInicial++;
          }
        }
        if (posInicial < 28) {
          page.setPageFormat(f);
          d.addPage(page);
        }
        d.print();
     */
  }

  public void imprimirEtiquetasJanel(java.util.List<Producto> productos,
                                     int posInicial, Map<?, ?> idToTipoProducto) {
//Obtenemos los valores iniciales de X y Y
    try {
      File file = new File("mi.ser");
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream oi = new ObjectInputStream(fis);
      Object obj = oi.readObject();
      MargenesImpresion mi = (MargenesImpresion) obj;
      double xSer = mi.getX();
      double ySer = mi.getY();
      nmbf.setMaximumFractionDigits(2);
      nmbf.setMinimumFractionDigits(2);
      this.idToTipoProducto = idToTipoProducto;

      d.showPrintDialog(true);
      incrementoX = 2.8;
      incrementoY = 1.5;
      f.setTopMargin(new PFInchUnit(0.05));
      f.setLeftMargin(new PFInchUnit(0.3));
      f.setBottomMargin(new PFInchUnit(0.05));
      f.setRightMargin(new PFInchUnit(0.3));
      f.setPageSize(new PFSize(new PFInchUnit(6), new PFInchUnit(8.8)));
      f.setPageOrientation(PFPageFormat.LANDSCAPE);
      y = ySer;

      for (Producto p : productos) {
        for (int cantidad = 0; cantidad < p.getCantidad(); cantidad++) {
          if ( (posInicial % 3) == 1) { //Nuevo renglon
            //Sacamos el cociente y le sumamos a las y
            y = (incrementoY * ( (posInicial - 1) / 3)) + ySer;

            x = xSer;
            //X=inicial;
          }
          else {
            //Incrementamos la x
            y = (incrementoY * ( (posInicial - 1) / 3)) + ySer;
            x = (incrementoX * ( (posInicial - 1) % 3)) + xSer;
          }
          imprimeEtiqueta(x, y, p);
          if (posInicial == 12) {
            posInicial = 1;
            page.setPageFormat(f);
            d.addPage(page);
            page = new PFPage();
          }
          else {
            posInicial++;
          }

        }
      }
      if (posInicial < 12) {
        page.setPageFormat(f);
        d.addPage(page);
      }
      d.print();
    }
    catch (ClassNotFoundException ex) {
      System.err.println(ex.toString());
    }
    catch (FileNotFoundException ex) {
      System.err.println(ex.toString());

    }
    catch (IOException ex) {
      System.err.println(ex.toString());

    }
    /*
        for (int i = 0; i < etiquetas.size(); i++) {

          if ( (posInicial % 4) == 1) { //Nuevo renglon
            //Sacamos el cociente y le sumamos a las y
            y = (incrementoY * ( (posInicial - 1) / 4)) + 0.16;
            if (y == 0.0) {
              y = 0.16;
            }
            x = 0.34;
            //X=inicial;
          }
          else {
            //Incrementamos la x
            y = (incrementoY * ( (posInicial - 1) / 4)) + 0.16;

            x = (incrementoX * ( (posInicial - 1) % 4)) + 0.34;
          }
//Imprimimos la etiqueta
          imprimeEtiqueta(x, y, (Object[]) etiquetas.get(i));
          if (posInicial == 28) {
            posInicial = 0;
            page.setPageFormat(f);
            d.addPage(page);
            page = new PFPage();
          }
          else {
            posInicial++;
          }
        }
        if (posInicial < 28) {
          page.setPageFormat(f);
          d.addPage(page);
        }
        d.print();
     */
  }

  public void imprimirEtiquetasTukStick(java.util.List<Producto> productos,
                                        int posInicial, Map<?, ?> idToTipoProducto) {
//Obtenemos los valores iniciales de X y Y
    try {
      File file = new File("mi.ser");
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream oi = new ObjectInputStream(fis);
      Object obj = oi.readObject();
      MargenesImpresion mi = (MargenesImpresion) obj;
      double xSer = mi.getX();
      double ySer = mi.getY();
      nmbf.setMaximumFractionDigits(2);
      nmbf.setMinimumFractionDigits(2);
      this.idToTipoProducto = idToTipoProducto;

      d.showPrintDialog(true);
      incrementoX = 2.8;
      incrementoY = 1.38;
      f.setTopMargin(new PFInchUnit(0.1));
      f.setLeftMargin(new PFInchUnit(0.03));
      f.setBottomMargin(new PFInchUnit(0.1));
      f.setRightMargin(new PFInchUnit(0.03));
      f.setPageSize(new PFSize(new PFInchUnit(5.4), new PFInchUnit(8.3)));
      f.setPageOrientation(PFPageFormat.PORTRAIT);
      y = ySer;

      for (Producto p : productos) {
        for (int cantidad = 0; cantidad < p.getCantidad(); cantidad++) {
          if ( (posInicial % 2) == 1) { //Nuevo renglon
            //Sacamos el cociente y le sumamos a las y
            y = (incrementoY * ( (posInicial - 1) / 2)) + ySer;

            x = xSer;
            //X=inicial;
          }
          else {
            //Incrementamos la x
            y = (incrementoY * ( (posInicial - 1) / 2)) + ySer;
            x = (incrementoX * ( (posInicial - 1) % 2)) + xSer;
          }
          imprimeEtiqueta(x, y, p);
          if (posInicial == 12) {
            posInicial = 1;
            page.setPageFormat(f);
            d.addPage(page);
            page = new PFPage();
          }
          else {
            posInicial++;
          }

        }
      }
      if (posInicial < 12) {
        page.setPageFormat(f);
        d.addPage(page);
      }
      d.print();
    }
    catch (ClassNotFoundException ex) {
      System.err.println(ex.toString());
    }
    catch (FileNotFoundException ex) {
      System.err.println(ex.toString());

    }
    catch (IOException ex) {
      System.err.println(ex.toString());

    }
    /*
        for (int i = 0; i < etiquetas.size(); i++) {

          if ( (posInicial % 4) == 1) { //Nuevo renglon
            //Sacamos el cociente y le sumamos a las y
            y = (incrementoY * ( (posInicial - 1) / 4)) + 0.16;
            if (y == 0.0) {
              y = 0.16;
            }
            x = 0.34;
            //X=inicial;
          }
          else {
            //Incrementamos la x
            y = (incrementoY * ( (posInicial - 1) / 4)) + 0.16;

            x = (incrementoX * ( (posInicial - 1) % 4)) + 0.34;
          }
//Imprimimos la etiqueta
          imprimeEtiqueta(x, y, (Object[]) etiquetas.get(i));
          if (posInicial == 28) {
            posInicial = 0;
            page.setPageFormat(f);
            d.addPage(page);
            page = new PFPage();
          }
          else {
            posInicial++;
          }
        }
        if (posInicial < 28) {
          page.setPageFormat(f);
          d.addPage(page);
        }
        d.print();
     */
  }

  /**
   * imprimeEtiqueta
   *
   * @param x double
   * @param y double
   */
  private void imprimeEtiqueta(double x, double y, Producto p) {
//Ponemos la etiqueta
//Codigo, Tipo de producto, talla, precio, clave

    if (p.getEtiqueta().length() < 4) {

    }

    PFMyImage pfImage = new PFMyImage();

    Barcode barcode = null;
    try {
      barcode = BarcodeFactory.createCode128(p.getEtiqueta());
      barcode.setDrawingText(false);
      barcode.setBarWidth(1);
      barcode.setBarWidth(0);
      // We are created an image from scratch here, but for printing in Java, your
      // print renderer should have a Graphics internally anyway
      BufferedImage image = new BufferedImage(154, 24,
                                              BufferedImage.TYPE_BYTE_GRAY);
      // We need to cast the Graphics from the Image to a Graphics2D - this is OK

      Graphics2D g = (Graphics2D) image.getGraphics();

      barcode.draw(g, 0, 0);
      File f = null;
      try {
        f = new File(p.getEtiqueta().replace("/", "_") + ".png");
        if (f.exists()) {
          f.delete();
        }
        // Let the barcode image handler do the hard work
        BarcodeImageHandler.savePNG(barcode, f);
      }
      catch (Exception e) {
        e.printStackTrace();
        // Error handling here
      }
      pfImage = new PFMyImage(p.getEtiqueta().replace("/", "_") + ".png");
      f.delete();
    }
    catch (BarcodeException ex1) {
      ex1.printStackTrace();
    }
    catch (OutputException ex) {
      /** @todo Handle this exception */
      ex.printStackTrace();
    }

    /*
     AttributedString atStr = new AttributedString("*" + p.getEtiqueta() + "*");
        atStr.addAttribute(TextAttribute.FONT,
                           new Font("C39HrP24DhTt", Font.PLAIN, 25));
        PFParagraph etiquetaBarra = new PFParagraph(atStr);
     */
    AttributedString atStr = new AttributedString(idToTipoProducto.get(String.
        valueOf(p.
                getIdTipoProducto())).toString());
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
    PFParagraph etiquetaNumero = new PFParagraph(atStr);

    atStr = new AttributedString(String.valueOf(p.getEstilo() + " "));
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
    PFParagraph clave = new PFParagraph(atStr);

    String marcas = AppInstance.id2marcas.get(p.getIdMarca());
    String desc = AppInstance.id2descripciones.get(p.getIdDescripcion());
    String color = AppInstance.id2colores.get(p.getIdColor());
    if(marcas!=null){
      marcas = ( (marcas.length() > 11) ? marcas.substring(0, 11) : marcas);
    }else{
      marcas="";
    }
    if(desc!=null){
      desc = ( (desc.length() > 11) ? desc.substring(0, 11) : desc);
    }else{
      desc="";
    }if(color!=null){
      color = ( (color.length() > 8) ? color.substring(0, 8) : color);
    }else{
      color="";
    }
    atStr = new AttributedString(marcas + " " + desc + " " + color);
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 7));
    PFParagraph marca = new PFParagraph(atStr);

    atStr = new AttributedString("Talla: " + p.getTalla());
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 10));
    PFParagraph talla = new PFParagraph(atStr);

    atStr = new AttributedString("Precio: $" +
                                 nmbf.format(p.getPrecioPublico()));

    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 10));
    PFParagraph precio = new PFParagraph(atStr);

    atStr = new AttributedString(p.getEtiqueta());

    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
    PFParagraph etiqueta = new PFParagraph(atStr);

//Ponemos las coordenadads primero el codigo de barras

    pfImage.setWidth(new PFInchUnit(2));

    pfImage.setHeight(new PFInchUnit(.17));
    pfImage.setPosition(new PFPoint(new PFInchUnit(x - .20), new PFInchUnit(y)));
    this.page.add(pfImage);

    /*
      etiquetaBarra.setWidth(new PFInchUnit(3));
     etiquetaBarra.setPosition(new PFPoint(new PFInchUnit(x), new PFInchUnit(y)));
      this.page.add(etiquetaBarra);*/


    y += .21;
    etiqueta.setWidth(new PFInchUnit(3));
    etiqueta.setPosition(new PFPoint(new PFInchUnit(x), new PFInchUnit(y)));
    this.page.add(etiqueta);
    clave.setWidth(new PFInchUnit(3));
    clave.setPosition(new PFPoint(new PFInchUnit(x + .8),
                                  new PFInchUnit(y)));
    page.add(clave);
    y += .12;

    etiquetaNumero.setWidth(new PFInchUnit(3));
    etiquetaNumero.setPosition(new PFPoint(new PFInchUnit(x), new PFInchUnit(y)));
    page.add(etiquetaNumero);
    y += .25;

    marca.setWidth(new PFInchUnit(3));
    marca.setPosition(new PFPoint(new PFInchUnit(x), new PFInchUnit(y)));
    page.add(marca);

    y += .25;
    talla.setWidth(new PFInchUnit(2));
    talla.setPosition(new PFPoint(new PFInchUnit(x), new PFInchUnit(y)));
    page.add(talla);
    precio.setWidth(new PFInchUnit(2));
    precio.setPosition(new PFPoint(new PFInchUnit(x + 1), new PFInchUnit(y)));
    page.add(precio);

  }

  public void impresionHojaCompleta() {
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 4; j++) {

      }

    }

  }

}
