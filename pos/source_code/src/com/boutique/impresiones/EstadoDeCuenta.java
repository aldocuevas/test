package com.boutique.impresiones;

import com.boutique.domain.Cliente;
import report.*;
import java.text.*;

import com.boutique.engine.impl.AppInstance;

import java.awt.*;
import java.awt.font.*;

public class EstadoDeCuenta {
  report.PFRectangle rectangle = new PFRectangle();

  private static String _estadoDeCuenta = "ESTADO DE CUENTA";
  private static String _fechaExpedicion = "FECHA DE EXPEDICION:";
  private static String _cliente = "CLIENTE:";
  private static String _domicilio = "DOMICILIO:";
  private static String _colonia = "COLONIA:";
  private static String _ciudad = "CIUDAD:";
  private static String _telefono = "TELEFONO:";
  private static String _encabezado = "NOTAS   SUCURSAL     FECHA    ANTICIPO    ABONOS     FECHA     SALDO      VENCIMIENTO";
  private static String _saldoVencido = "SALDO VENCIDO:";
  private static String _saldoTotal = "SALDO TOTAL:";
  private static String _nuevaDisposicion = "NUEVA DISPOSICION: Para realizar una NUEVA compra de crédito es necesario liquidar todos sus abonos VENCIDOS de sus notas anteriores. ";
  private static String _estimadoCliente = "Estimado cliente: Para nuestro establecimiento es indispensable que mantenga su cuenta al corriente para seguir proporcionandole el servicio que usted se merece.";
  private static String _leAgradecemos = "Le agradecemos cualquier sugerencia ya que para nosotros es muy importante su opinión.";
  private static String _gracias = "Gracias por su preferencia.";
  private static String _domicilio1 =
      "Av. Lázaro Cárdenas 181 Col. Placetas Tel. 3147250";
  private static String _domicilio2 =
      "Av. Felipe Sevilla del Río 352-A Col. Lomas de Circunvalación Tel. 3136911";
  private static String _domicilio3 =
      "Plaza Zentralia Local 241 P.A. Bugambilias Tel. 3120220";
  private Cliente cliente = null;
  private static Font font = new Font("Arial", Font.PLAIN, 10);
  private static Font fontBold = new Font("Arial", Font.BOLD, 10);
  private static Font fontEdoCuenta = new Font("Arial", Font.BOLD, 14);
  private static report.PFImage logo = new PFImage();
  public EstadoDeCuenta(Cliente c) {
    cliente = c;
  }

  public EstadoDeCuenta() {

  }

  public void imprimirEdoCuenta() {

    report.PFFrame pfFrame = new PFFrame();
    pfFrame.setHeight(new PFInchUnit(0.25));
    pfFrame.setWidth(new PFInchUnit(6.6));
    pfFrame.setPosition(new PFPoint(new PFInchUnit(0.9), new PFInchUnit(3.1)));

    //  if (cliente != null) {
    PFDocument d = new PFDocument();
    //Agregamos una pagina
    PFPage page = new PFPage();
    PFPageFormat format = new PFPageFormat();

    java.text.SimpleDateFormat formato = AppInstance.formatoCorto;
   
    PFInchUnit margen = new PFInchUnit(0.3);
    format.setTopMargin(margen);
    format.setLeftMargin(margen);
    format.setBottomMargin(margen);
    format.setRightMargin(new PFInchUnit(0.3));
    page.setPageFormat(format);

    logo.setURL("file:c://proyectos//pilyboutique//logo.jpg");
    logo.setHeight(new PFInchUnit(1.5));
    logo.setWidth(new PFInchUnit(2.5));
    logo.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(0.4)));

    page.add(logo);

//agregamos el rectangulo del encabezado;


    page.add(pfFrame);

    //Empezamos la impresion de los textos
    //Titulo Estaod de Cuenta
    AttributedString atStr = new AttributedString(_estadoDeCuenta);
    atStr.addAttribute(TextAttribute.FONT, fontEdoCuenta);
    PFParagraph _pfEstadoDeCuenta = new PFParagraph(atStr);
    _pfEstadoDeCuenta.setPosition(new PFPoint(new PFInchUnit(5),
                                              new PFInchUnit(1)));
    _pfEstadoDeCuenta.setWidth(new PFInchUnit(3));
    page.add(_pfEstadoDeCuenta);
    //Fecha de expedicion
    atStr = new AttributedString(_fechaExpedicion + " " +
                                 formato.format(new java.util.Date()));
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfFechaExpedicion = new PFParagraph(atStr);
    _pfFechaExpedicion.setPosition(new PFPoint(new PFInchUnit(5),
                                               new PFInchUnit(1.4)));
    _pfFechaExpedicion.setWidth(new PFInchUnit(3));
    page.add(_pfFechaExpedicion);

    //Cliente, domicilio, colonia
    atStr = new AttributedString(_cliente + " " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfCliente = new PFParagraph(atStr);
    _pfCliente.setPosition(new PFPoint(new PFInchUnit(1),
                                       new PFInchUnit(2.2)));
    _pfCliente.setWidth(new PFInchUnit(6));
    page.add(_pfCliente);

    atStr = new AttributedString(_domicilio + " " + cliente.getCalle() + " " + cliente.getNumero() + (cliente.getNumeroInterior().equals("")?"":" Int. " + cliente.getNumeroInterior()));
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfDomicilio = new PFParagraph(atStr);
    _pfDomicilio.setPosition(new PFPoint(new PFInchUnit(1),
                                         new PFInchUnit(2.34)));
    _pfDomicilio.setWidth(new PFInchUnit(3));
    page.add(_pfDomicilio);

    atStr = new AttributedString(_colonia + " " + cliente.getColonia());
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfColonia = new PFParagraph(atStr);
    _pfColonia.setPosition(new PFPoint(new PFInchUnit(1),
                                       new PFInchUnit(2.48)));
    _pfColonia.setWidth(new PFInchUnit(3));
    page.add(_pfColonia);

    atStr = new AttributedString(_ciudad);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfCiudad = new PFParagraph(atStr);
    _pfCiudad.setPosition(new PFPoint(new PFInchUnit(5),
                                      new PFInchUnit(2.34)));
    _pfEstadoDeCuenta.setWidth(new PFInchUnit(3));
    page.add(_pfCiudad);

    atStr = new AttributedString(_telefono + " " + cliente.getTelefono());
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfTelefono = new PFParagraph(atStr);
    _pfTelefono.setPosition(new PFPoint(new PFInchUnit(5),
                                        new PFInchUnit(2.48)));
    _pfTelefono.setWidth(new PFInchUnit(3));
    page.add(_pfTelefono);

    atStr = new AttributedString(_encabezado);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfEncabezado = new PFParagraph(atStr);
    _pfEncabezado.setPosition(new PFPoint(new PFInchUnit(1),
                                          new PFInchUnit(3.15)));
    _pfEncabezado.setWidth(new PFInchUnit(7));
    page.add(_pfEncabezado);

    atStr = new AttributedString(_saldoVencido);
    atStr.addAttribute(TextAttribute.FONT, fontBold);
    PFParagraph _pfSaldoVencido = new PFParagraph(atStr);
    _pfSaldoVencido.setPosition(new PFPoint(new PFInchUnit(1),
                                            new PFInchUnit(7)));
    _pfSaldoVencido.setWidth(new PFInchUnit(3));
    page.add(_pfSaldoVencido);

    atStr = new AttributedString(_saldoTotal);
    atStr.addAttribute(TextAttribute.FONT, fontBold);
    PFParagraph _pfSaldoTotal = new PFParagraph(atStr);
    _pfSaldoTotal.setPosition(new PFPoint(new PFInchUnit(1),
                                          new PFInchUnit(7.25)));
    _pfSaldoTotal.setWidth(new PFInchUnit(3));
    page.add(_pfSaldoTotal);

    atStr = new AttributedString(_nuevaDisposicion);
    atStr.addAttribute(TextAttribute.FONT, fontBold);
    PFParagraph _pfNuevaDisposicion = new PFParagraph(atStr);
    _pfNuevaDisposicion.setPosition(new PFPoint(new PFInchUnit(1),
                                                new PFInchUnit(7.7)));
    _pfNuevaDisposicion.setWidth(new PFInchUnit(7));
    page.add(_pfNuevaDisposicion);

    atStr = new AttributedString(_estimadoCliente);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfEstimadoCliente = new PFParagraph(atStr);
    _pfEstimadoCliente.setPosition(new PFPoint(new PFInchUnit(1),
                                               new PFInchUnit(8.1)));
    _pfEstimadoCliente.setWidth(new PFInchUnit(6.5));
    page.add(_pfEstimadoCliente);

    atStr = new AttributedString(_leAgradecemos);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfLeAgradecemos = new PFParagraph(atStr);
    _pfLeAgradecemos.setPosition(new PFPoint(new PFInchUnit(1),
                                             new PFInchUnit(8.4)));
    _pfLeAgradecemos.setWidth(new PFInchUnit(7));
    page.add(_pfLeAgradecemos);

    atStr = new AttributedString(_gracias);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfGracias = new PFParagraph(atStr);
    _pfGracias.setPosition(new PFPoint(new PFInchUnit(3.5),
                                       new PFInchUnit(8.9)));
    _pfGracias.setWidth(new PFInchUnit(7));
    page.add(_pfGracias);

    atStr = new AttributedString(_domicilio1);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfDomicilio1 = new PFParagraph(atStr);
    _pfDomicilio1.setPosition(new PFPoint(new PFInchUnit(2.6),
                                          new PFInchUnit(9.5)));
    _pfDomicilio1.setWidth(new PFInchUnit(7));
    page.add(_pfDomicilio1);

    atStr = new AttributedString(_domicilio2);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfDomicilio2 = new PFParagraph(atStr);
    _pfDomicilio2.setPosition(new PFPoint(new PFInchUnit(2),
                                          new PFInchUnit(9.64)));
    _pfDomicilio2.setWidth(new PFInchUnit(7));
    page.add(_pfDomicilio2);

    atStr = new AttributedString(_domicilio3);
    atStr.addAttribute(TextAttribute.FONT, font);
    PFParagraph _pfDomicilio3 = new PFParagraph(atStr);
    _pfDomicilio3.setPosition(new PFPoint(new PFInchUnit(2.5),
                                          new PFInchUnit(9.78)));
    _pfDomicilio3.setWidth(new PFInchUnit(7));
    page.add(_pfDomicilio3);

    d.addPage(page);

    d.print();

  }
}
