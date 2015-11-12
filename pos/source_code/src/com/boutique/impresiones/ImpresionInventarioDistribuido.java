package com.boutique.impresiones;

import com.boutique.domain.RotacionInventario;
import report.PFPageFormat;
import java.util.List;
import report.PFInchUnit;
import report.PFPage;
import report.PFDocument;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import com.boutique.engine.impl.AppInstance;
import com.boutique.dao.DaoSource;
import java.awt.Font;
import report.PFParagraph;
import report.PFPoint;
import com.boutique.dao.DaoInventarios;

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
public class ImpresionInventarioDistribuido {
  static PFPageFormat format = new PFPageFormat();
  static PFInchUnit margen = new PFInchUnit(0.7);
  static double y;
  RotacionInventario ri;
  List<?> productos;
  static {
    format.setTopMargin(margen);
    format.setLeftMargin(margen);
    format.setBottomMargin(margen);
    format.setRightMargin(margen);
  }

  public ImpresionInventarioDistribuido() {
  }

  public static void imprimirEncabezado(PFPage page, int idBoutique,
                                        String pagina) {
    y = .8;
    AttributedString atStr = new AttributedString(com.boutique.engine.impl.
                                                  AppInstance.nombreNegocio);
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.BOLD, 9));
    PFParagraph encabezado = new PFParagraph(atStr);
    encabezado.setPosition(new PFPoint(new PFInchUnit(2.50),
                                       new PFInchUnit(y)));
    encabezado.setWidth(new PFInchUnit(7));
    page.add(encabezado);
    atStr = new AttributedString("Pagina: " + pagina);
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.BOLD, 9));
    encabezado = new PFParagraph(atStr);
    encabezado.setPosition(new PFPoint(new PFInchUnit(6.50),
                                       new PFInchUnit(y)));
    encabezado.setWidth(new PFInchUnit(7));
    page.add(encabezado);

    y += .13;
    //ROTACION DE INVANTARIOS
    String enca = "ENTREGA DE MERCANCIA DE ALMACEN A SUCURSAL";
    atStr = new AttributedString(enca);
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    PFParagraph nombrePropietario = new PFParagraph(atStr);
    nombrePropietario.setPosition(new PFPoint(new PFInchUnit(1.5),
                                              new PFInchUnit(y)));
    nombrePropietario.setWidth(new PFInchUnit(7.5));
    nombrePropietario.setHeight(new PFInchUnit(1));
    page.add(nombrePropietario);
    y += .16;
    //AHORA IMPRIMIMOS LA FECHA EL FOLIO DELA NOTA
    atStr = new AttributedString("FECHA:" +
                                 AppInstance.formatoLargo.format(DaoSource.
        getFechaActual()));
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    PFParagraph folio = new PFParagraph(atStr);
    folio.setPosition(new PFPoint(new PFInchUnit(2.01),
                                  new PFInchUnit(y)));
    folio.setWidth(new PFInchUnit(3.8));
    page.add(folio);
    y += .16;
    //BOUTIQUE INICIO Y FINAL. JIJI
    atStr = new AttributedString("SUCURSAL DE ENTREGA: " +
                                 AppInstance.idToNombreBoutique.get(idBoutique));
    atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN, 9));
    PFParagraph vendedor = new PFParagraph(atStr);
    vendedor.setPosition(new PFPoint(new PFInchUnit(1.7),
                                     new PFInchUnit(y)));
    vendedor.setWidth(new PFInchUnit(6.8));
    page.add(vendedor);
    y += 0.26;
    /***********************AGREGAMOS LOS DATOS DE LOS PRODUCTOS******************/

    atStr = new AttributedString(
        "Cantidad      Estilo                   Etiqueta                    Producto                                      Precio");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    PFParagraph encabezado2 = new PFParagraph(atStr);
    encabezado2.setPosition(new PFPoint(new PFInchUnit(1.01),
                                        new PFInchUnit(y)));
    encabezado2.setWidth(new PFInchUnit(8.5));
    page.add(encabezado2);
    y += .05;
    atStr = new AttributedString(
        "-------------------------------------------------------------------------------------------------------------------------");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    PFParagraph encabezado23 = new PFParagraph(atStr);
    encabezado23.setPosition(new PFPoint(new PFInchUnit(1.01),
                                         new PFInchUnit(y)));
    encabezado23.setWidth(new PFInchUnit(8));
    page.add(encabezado23);
    y += .13;

  }

  public static void imprimirInventarioDistribuidoPorBoutique(int idBoutique,
      PFDocument dd) {

    List<?> productos = DaoInventarios.
        findProductosNoDistribuidosViewBySucursal(idBoutique);
    PFDocument d = dd;
//Agregamos una pagina
    PFPage page = new PFPage();

//Ponemos el id
    page.setPageFormat(format);

    String pagina = "1 de " + (int) (Math.ceil(productos.size() / 60.0));
    imprimirEncabezado(page, idBoutique, pagina);
    int numRenglones = 0;
    int totalProductos = 0;
    int j = 1;
    for (int i = 0; i < productos.size(); i++) {
      //HASTA LOS 60
      if (numRenglones == 60) {
        d.addPage(page);
        page = new PFPage();
        page.setPageFormat(format);
        pagina = ++j + " de " + (int) (Math.ceil(productos.size() / 60.0));
        imprimirEncabezado(page, idBoutique, pagina);
        numRenglones = 0;
      }

      Object[] row = (Object[]) productos.get(i);
      if (Integer.parseInt(row[2].toString()) > 0) {
        numRenglones++;
        AttributedString atStr = new AttributedString(row[2].toString());
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
        PFParagraph cantidad = new PFParagraph(atStr);
        cantidad.setPosition(new PFPoint(new PFInchUnit(1.01), new PFInchUnit(y)));
        cantidad.setWidth(new PFInchUnit(3));
        page.add(cantidad);
        atStr = new AttributedString(row[7].toString()+" ");
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
        PFParagraph estilo = new PFParagraph(atStr);
        estilo.setPosition(new PFPoint(new PFInchUnit(1.7),
                                       new PFInchUnit(y)));
        estilo.setWidth(new PFInchUnit(3));
        page.add(estilo);

        atStr = new AttributedString(row[3].toString());
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
        PFParagraph descripcion = new PFParagraph(atStr);
        descripcion.setPosition(new PFPoint(new PFInchUnit(2.7),
                                            new PFInchUnit(y)));
        descripcion.setWidth(new PFInchUnit(3));
        page.add(descripcion);
        String strDesc = row[4].toString();
        if (strDesc.length() > 13) {
          strDesc = strDesc.substring(0, 12);
        }
        atStr = new AttributedString(strDesc);
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
        descripcion = new PFParagraph(atStr);
        descripcion.setPosition(new PFPoint(new PFInchUnit(3.7),
                                            new PFInchUnit(y)));
        descripcion.setWidth(new PFInchUnit(3));
        page.add(descripcion);

        atStr = new AttributedString(
            row[6].toString());

        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
        PFParagraph precioInicial = new PFParagraph(atStr);
        precioInicial.setPosition(new PFPoint(new PFInchUnit(6.3),
                                              new PFInchUnit(y)));
        precioInicial.setWidth(new PFInchUnit(3));
        page.add(precioInicial);
        y += 0.13;
        totalProductos += Integer.parseInt(row[2].toString());
      }
    }

    y += .08;

//Imprimimos el total sin descontar
    AttributedString atStr = new AttributedString("TOTAL DE PRODUCTOS:    " +
                                                  totalProductos);
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    PFParagraph totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(3));
    page.add(totalesSinDescontar);
    y += 0.26;
    atStr = new AttributedString(
        "ENTREGA (NOMBRE Y FIRMA): ______________________________________________");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(9));
    page.add(totalesSinDescontar);
    y += 0.30;
    atStr = new AttributedString(
        "TRASLADA (NOMBRE Y FIRMA):_______________________________________________");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(9));
    page.add(totalesSinDescontar);
    y += 0.30;
    atStr = new AttributedString(
        "RECIBE (NOMBRE Y FIRMA):_________________________________________________");
    atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
    totalesSinDescontar = new PFParagraph(atStr);
    totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
                                                new PFInchUnit(y)));
    totalesSinDescontar.setWidth(new PFInchUnit(9));
    page.add(totalesSinDescontar);
    d.addPage(page);
//El total con letra queda pendiente..
    d.print();

  }
}
