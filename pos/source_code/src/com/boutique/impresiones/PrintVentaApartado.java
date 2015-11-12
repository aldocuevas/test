package com.boutique.impresiones;

import report.*;
import java.text.*;
import java.util.List;

import java.awt.Font;
import java.awt.font.*;

import com.boutique.engine.impl.AppInstance;
 import com.boutique.impresiones.ventas.Ticket;
import com.boutique.domain.*;

public class PrintVentaApartado extends Ticket{

  PFDocument d = new PFDocument();
  //Agregamos una pagina
  PFPage page = new PFPage();
  PFPageFormat format = new PFPageFormat();
  java.text.NumberFormat nmbf = java.text.NumberFormat.getNumberInstance();

  double x;
  double y;
  public PrintVentaApartado(Venta venta, List<?> articulos,
                            String nombre,
                            String dom, String tel, double subTotal,
                            double anticipo,
                            double total, String totalLetra) {
    nmbf.setMaximumFractionDigits(2);
    nmbf.setMinimumFractionDigits(2);
    for (int j = 0; j < 1; j++) {
      page = new PFPage();
      PFInchUnit margen = new PFInchUnit(0.01);
      format.setPageSize(new PFSize(new PFInchUnit(3.0), new PFInchUnit(8.5)));
      format.setTopMargin(margen);
      format.setLeftMargin(margen);
      format.setBottomMargin(margen);
      format.setRightMargin(margen);

      page.setPageFormat(format);
      AttributedString atStr;
      y = 0.1;
     
      //IMPRIMIMOS EL ENCABEZADO
      y = imprimirEncabezado(page, "APARTADO", venta, y,
                                               venta.getFecha());
 
      y += 0.18;
      /***********************AGREGAMOS LOS DATOS DEL CLIENTE******************/
      atStr = new AttributedString("CLIENTE: " + nombre + " " + dom + " Tel." +
                                   tel); //Nombre
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
      PFParagraph nombreCliente = new PFParagraph(atStr);
      nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.01),
                                            new PFInchUnit(y)));
      nombreCliente.setWidth(new PFInchUnit(2.5));
      nombreCliente.setHeight(new PFInchUnit(1));
      page.add(nombreCliente);
      y += .26;
      y += .26;
      y += .26;
      atStr = new AttributedString(
          "Ct. Producto                          Precio");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 10));
      PFParagraph encabezado2 = new PFParagraph(atStr);
      encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
                                          new PFInchUnit(y)));
      encabezado2.setWidth(new PFInchUnit(2.5));
      page.add(encabezado2);
      y += 0.1;
      atStr = new AttributedString(
          "Descripcion");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 10));
      encabezado2 = new PFParagraph(atStr);
      encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
                                          new PFInchUnit(y)));
      encabezado2.setWidth(new PFInchUnit(2.5));
      page.add(encabezado2);

      y += .05;
      atStr = new AttributedString(
          "----------------------------------------------------");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 10));
      PFParagraph encabezado23 = new PFParagraph(atStr);
      encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
                                           new PFInchUnit(y)));
      encabezado23.setWidth(new PFInchUnit(2.8));
      page.add(encabezado23);

      /* for (int i=0;i<articulos.size();i++){*/
      /*          ArticuloVendido articuloVendido = new ArticuloVendido();
       java.util.Vector articulosVendidos = articuloVendido.findByIdVenta(venta.
                    getId());
                DirectorioArticulos dirArticulos = new DirectorioArticulos();
       DirectorioInventarioLocal dirLocal = new DirectorioInventarioLocal();*/
      y += .13;
      for (int i = 0; i < articulos.size(); i++) { //Por cada articulo generamos un string
        Object[] row = (Object[]) articulos.get(i);
        atStr = new AttributedString("1");
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
        PFParagraph cantidad = new PFParagraph(atStr);
        cantidad.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
        cantidad.setWidth(new PFInchUnit(.5));
        page.add(cantidad);
        atStr = new AttributedString(row[2].toString());
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
        PFParagraph descripcion = new PFParagraph(atStr);
        descripcion.setPosition(new PFPoint(new PFInchUnit(0.2),
                                            new PFInchUnit(y)));
        descripcion.setWidth(new PFInchUnit(6));
        page.add(descripcion);
        atStr = new AttributedString(row[3].toString());
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
        PFParagraph pInicial = new PFParagraph(atStr);
        pInicial.setPosition(new PFPoint(new PFInchUnit(2), new PFInchUnit(y)));
        pInicial.setWidth(new PFInchUnit(1));
        page.add(pInicial);
        y += 0.13;
        atStr = new AttributedString( ( (row[1].toString().length() > 33) ?
                                       row[1].toString().substring(0, 33) :
                                       row[1].toString()));
        atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
        descripcion = new PFParagraph(atStr);
        descripcion.setPosition(new PFPoint(new PFInchUnit(0.01),
                                            new PFInchUnit(y)));
        descripcion.setWidth(new PFInchUnit(3));
        page.add(descripcion);
        y += 0.13;
      }

      /**********************/
      y = imprimirPagos(page, venta.getPagos(), 2, y);

      y += 0.13;

      atStr = new AttributedString("SUBTOTAL");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      PFParagraph pSubTotal = new PFParagraph(atStr);
      pSubTotal.setPosition(new PFPoint(new PFInchUnit(1.1), new PFInchUnit(y)));
      page.add(pSubTotal);
      atStr = new AttributedString(nmbf.format(subTotal));
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      pSubTotal = new PFParagraph(atStr);
      pSubTotal.setPosition(new PFPoint(new PFInchUnit(1.8),
                                        new PFInchUnit(y)));
      page.add(pSubTotal);
      y += 0.13;
      atStr = new AttributedString("ANTICIPO");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      PFParagraph pDescuento = new PFParagraph(atStr);
      pDescuento.setPosition(new PFPoint(new PFInchUnit(1.1), new PFInchUnit(y)));
      page.add(pDescuento);
      atStr = new AttributedString(nmbf.format( -1 * anticipo));
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      pDescuento = new PFParagraph(atStr);
      pDescuento.setPosition(new PFPoint(new PFInchUnit(1.8),
                                         new PFInchUnit(y)));
      page.add(pDescuento);

      
      y += 0.13;
      atStr = new AttributedString("TOTAL");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      PFParagraph pTotal = new PFParagraph(atStr);
      pTotal.setPosition(new PFPoint(new PFInchUnit(1.1), new PFInchUnit(y)));
      page.add(pTotal);
      atStr = new AttributedString(nmbf.format(total));
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      pTotal = new PFParagraph(atStr);
      pTotal.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(y)));
      page.add(pTotal);
      y += .13;
      atStr = new AttributedString("Total en letra:");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 9));
      PFParagraph pTotalLetra = new PFParagraph(atStr);
      pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
                                          new PFInchUnit(y)));
      page.add(pTotalLetra);
      y += .13;
      atStr = new AttributedString(totalLetra);
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN, 8));
      pTotalLetra = new PFParagraph(atStr);
      pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
                                          new PFInchUnit(y)));
      pTotalLetra.setWidth(new PFInchUnit(2.5));
      pTotalLetra.setHeight(new PFInchUnit(0.25));
      page.add(pTotalLetra);
      y += .36;
      //0 Contado,1 Credito,2 Apartado
      Leyenda l = AppInstance.idToTipoNotaLeyenda.get(2);
      atStr = new AttributedString(l.getLeyenda());
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
      PFParagraph leyenda = new PFParagraph(atStr);
      leyenda.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
      leyenda.setWidth(new PFInchUnit(2.5));
      page.add(leyenda);
      y += .95;

      atStr = new AttributedString("¡GRACIAS POR SU COMPRA!");
      atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
      leyenda = new PFParagraph(atStr);
      leyenda.setPosition(new PFPoint(new PFInchUnit(0.35), new PFInchUnit(y)));
      leyenda.setWidth(new PFInchUnit(2.5));
      page.add(leyenda);
      d.addPage(page);
    }

    d.print();
  }

}
