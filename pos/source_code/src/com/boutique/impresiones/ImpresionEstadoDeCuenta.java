package com.boutique.impresiones;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.*;

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
public class ImpresionEstadoDeCuenta {
  public ImpresionEstadoDeCuenta() {

  }

  public static void main(String[] arg) {

    Document doc = new Document(PageSize.HALFLETTER.rotate());
    try {
      PdfWriter.getInstance(doc, new FileOutputStream("prueba.pdf"));
      doc.open();
      Paragraph p = new Paragraph();
      p.setFont(new Font(Font.HELVETICA));
      doc.add(new Paragraph("Hello Word"));
      doc.close();
    }
    catch (FileNotFoundException ex) {
    }
    catch (DocumentException ex) {
    }
  }

}
