package com.boutique.impresiones;

import java.util.*;
import report.PFSize;
import report.PFInchUnit;
import report.PFPageFormat;
import com.boutique.domain.*;
import report.PFPage;
import com.boutique.dao.*;
import report.PFDocument;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import com.boutique.engine.impl.AppInstance;

import java.awt.Font;
import report.PFParagraph;
import report.PFPoint;

public class ImpresionEncargos extends Thread{
  static PFPageFormat format = new PFPageFormat();
  static PFInchUnit margen = new PFInchUnit(0.01);
  RotacionInventario ri;
  List<?> productos;
  static {
    format.setPageSize(new PFSize(new PFInchUnit(3.2), new PFInchUnit(13)));
    format.setTopMargin(margen);
    format.setLeftMargin(margen);
    format.setBottomMargin(margen);
    format.setRightMargin(margen);
  }
  public ImpresionEncargos(RotacionInventario ri,List<?> productos){
    this.ri=ri;
    this.productos=productos;
  }
  public void run(){
    imprimirEncargos();
  }

  public  void imprimirEncargos() {
    double y;

    PFDocument d = new PFDocument();
//Agregamos una pagina
    PFPage page = new PFPage();

//Ponemos el id
    page.setPageFormat(format);

    y = 0.1;
    AttributedString atStr = new AttributedString(com.boutique.engine.impl.AppInstance.nombreNegocio);
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.BOLD, 9));
    PFParagraph encabezado = new PFParagraph(atStr);
    encabezado.setPosition(new PFPoint(new PFInchUnit(0.80),
                                       new PFInchUnit(y)));
    encabezado.setWidth(new PFInchUnit(2));
    page.add(encabezado);
    y += .13;
    //ROTACION DE INVANTARIOS
    String enca = "ROTACION DE INVENTARIO";
    atStr = new AttributedString(enca);
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    PFParagraph nombrePropietario = new PFParagraph(atStr);
    nombrePropietario.setPosition(new PFPoint(new PFInchUnit(0.5),
                                              new PFInchUnit(y)));
    nombrePropietario.setWidth(new PFInchUnit(2.5));
    nombrePropietario.setHeight(new PFInchUnit(1));
    page.add(nombrePropietario);
    y += .26;
    //AHORA IMPRIMIMOS LA FECHA EL FOLIO DELA NOTA
    atStr = new AttributedString("FECHA:" +
                                 AppInstance.formatoLargo.format(DaoSource.getFechaActual()) +
                                 " FOLIO: " + ri.getId());
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    PFParagraph folio = new PFParagraph(atStr);
    folio.setPosition(new PFPoint(new PFInchUnit(0.01),
                                  new PFInchUnit(y)));
    folio.setWidth(new PFInchUnit(2.8));
    page.add(folio);
    y += .16;
    //BOUTIQUE INICIO Y FINAL. JIJI
    atStr = new AttributedString("DE:" +
                                 AppInstance.idToNombreBoutique.get(ri.getBoutiqueInicial()));
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    PFParagraph vendedor = new PFParagraph(atStr);
    vendedor.setPosition(new PFPoint(new PFInchUnit(0.01),
                                     new PFInchUnit(y)));
    vendedor.setWidth(new PFInchUnit(2.8));
    page.add(vendedor);
    y += 0.13;
    atStr = new AttributedString("A: " +
                                 AppInstance.idToNombreBoutique.get(ri.getBoutiqueFinal()));
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    vendedor = new PFParagraph(atStr);
    vendedor.setPosition(new PFPoint(new PFInchUnit(0.01),
                                     new PFInchUnit(y)));
    vendedor.setWidth(new PFInchUnit(2.8));
    page.add(vendedor);
    y += 0.26;
    /***********************AGREGAMOS LOS DATOS DE LOS PRODUCTOS******************/

    atStr = new AttributedString(
        "ID         Etiqueta    Producto             Precio");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    PFParagraph encabezado2 = new PFParagraph(atStr);
    encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
                                        new PFInchUnit(y)));
    encabezado2.setWidth(new PFInchUnit(2.5));
    page.add(encabezado2);
    y += .05;
    atStr = new AttributedString(
        "----------------------------------------------------------");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    PFParagraph encabezado23 = new PFParagraph(atStr);
    encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
                                         new PFInchUnit(y)));
    encabezado23.setWidth(new PFInchUnit(2.8));
    page.add(encabezado23);
    y += .13;

    for (int i = 0; i < productos.size(); i++) {
      Object[] row = (Object[]) productos.get(i);
      atStr = new AttributedString(row[0].toString());
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
      PFParagraph cantidad = new PFParagraph(atStr);
      cantidad.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
      cantidad.setWidth(new PFInchUnit(3));
      page.add(cantidad);
      atStr = new AttributedString(row[1].toString());
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
      PFParagraph descripcion = new PFParagraph(atStr);
      descripcion.setPosition(new PFPoint(new PFInchUnit(0.5),
                                          new PFInchUnit(y)));
      descripcion.setWidth(new PFInchUnit(3));
      page.add(descripcion);
      String strDesc = row[2].toString();
      if (strDesc.length() > 13) {
        strDesc = strDesc.substring(0, 12);
      }
      atStr = new AttributedString(strDesc);
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
      descripcion = new PFParagraph(atStr);
      descripcion.setPosition(new PFPoint(new PFInchUnit(1),
                                          new PFInchUnit(y)));
      descripcion.setWidth(new PFInchUnit(3));
      page.add(descripcion);

      atStr = new AttributedString(AppInstance.number.format( Double.parseDouble(row[3].toString())));

      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
      PFParagraph precioInicial = new PFParagraph(atStr);
      precioInicial.setPosition(new PFPoint(new PFInchUnit(2),
                                            new PFInchUnit(y)));
      precioInicial.setWidth(new PFInchUnit(3));
      page.add(precioInicial);
      y += 0.13;
    }

    y += .08;

//Imprimimos el total sin descontar
    atStr = new AttributedString("TOTAL DE PRODUCTOS:    " +
                                 productos.size());
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    PFParagraph totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(0.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(3));
    page.add(totalesSinDescontar);
    y += 0.26;
    atStr = new AttributedString("ENTREGA: " +
                                 AppInstance.usuario().getNombre());
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(0.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(3));
    page.add(totalesSinDescontar);
    y += 0.30;
    atStr = new AttributedString("TRASLADA:________________________");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(0.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(3));
    page.add(totalesSinDescontar);
    y += 0.30;
    atStr = new AttributedString("RECIBE:___________________________");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(0.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(3));
    page.add(totalesSinDescontar);
    d.addPage(page);
//El total con letra queda pendiente..
    d.print();

  }

}
