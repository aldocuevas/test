package com.boutique.impresiones.ventas;

import java.text.*;
import java.util.List;

import java.awt.Font;
import java.awt.font.*;

import com.boutique.domain.*;
import com.boutique.engine.impl.*;

import report.*;

public class PrintVentaContado extends Ticket{
	PFDocument d = new PFDocument();
	// Agregamos una pagina
	PFPage page = new PFPage();
	PFPageFormat format = new PFPageFormat();
	java.text.NumberFormat nmbf = AppInstance.number;

	double x;
	double y;

	public PrintVentaContado(Venta venta, List<?> articulos, double subTotal,
			int descuento, double total, String totalLetra, Cliente cliente) {
		// for (int j = 0; j < 2; j++) {
		page = new PFPage();
		PFInchUnit margen = new PFInchUnit(0.01);
		format.setPageSize(new PFSize(new PFInchUnit(3.0), new PFInchUnit(8.5)));
		format.setTopMargin(new PFInchUnit(0.01));
		format.setLeftMargin(margen);
		format.setBottomMargin(new PFInchUnit(0.1));
		format.setRightMargin(new PFInchUnit(0.01));
		page.setPageFormat(format);
		// IMPRIMIMOS EL ENCABEZADO
		// nombre de la boutique
		y = 0.1;
		AttributedString atStr;
		y = imprimirEncabezado(page, "CONTADO", venta, y, venta.getFecha());
		if (cliente != null) {
			y += 0.26;
			atStr = new AttributedString("CLIENTE: " + cliente.getNombre()
					+ " " + cliente.getApellidoPaterno() + " "
					+ cliente.getApellidoMaterno() + ". COD CLIENTE:"
					+ cliente.getId() + ". DOM: " + cliente.getCalle() + " "
					+ cliente.getNumero() + " Col. " + cliente.getColonia()
					+ "Tel." + cliente.getTelefono()); // Nombre

			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph nombreCliente = new PFParagraph(atStr);
			nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			nombreCliente.setWidth(new PFInchUnit(2.5));
			nombreCliente.setHeight(new PFInchUnit(1));
			page.add(nombreCliente);
			y += .26;
			y += .26;
		}

		y += .13;
		atStr = new AttributedString(
				"Ct. Codigo               Ini   Desc  P.Fin");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.8));
		y += .05;
		page.add(encabezado2);

		atStr = new AttributedString("Descripcion");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		encabezado2 = new PFParagraph(atStr);
		y += .1;
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.8));

		page.add(encabezado2);

		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		y += .05;
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;
		for (int i = 0; i < articulos.size(); i++) { // Por cada articulo
														// generamos un string
			Object[] row = (Object[]) articulos.get(i);
			atStr = new AttributedString("1");
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph cantidad = new PFParagraph(atStr);
			cantidad.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			cantidad.setWidth(new PFInchUnit(0.3));
			page.add(cantidad);
			String strCodigo = row[2].toString();
			atStr = new AttributedString(strCodigo);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(0.2),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(2));
			page.add(descripcion);
			atStr = new AttributedString(row[3].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph pInicial = new PFParagraph(atStr);
			pInicial.setPosition(new PFPoint(new PFInchUnit(1.2),
					new PFInchUnit(y)));
			pInicial.setWidth(new PFInchUnit(.6));
			page.add(pInicial);
			atStr = new AttributedString(row[4].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph desc = new PFParagraph(atStr);
			desc.setPosition(new PFPoint(new PFInchUnit(1.7), new PFInchUnit(y)));
			desc.setWidth(new PFInchUnit(1));
			page.add(desc);
			atStr = new AttributedString(row[5].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph pFinal = new PFParagraph(atStr);
			pFinal.setPosition(new PFPoint(new PFInchUnit(2), new PFInchUnit(y)));
			pFinal.setWidth(new PFInchUnit(1));
			page.add(pFinal);
			y += 0.13;
			String strDesc = row[1].toString();
			if (strDesc.length() > 30) {
				strDesc = strDesc.substring(0, 29);
			}
			atStr = new AttributedString(strDesc);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(3));
			page.add(descripcion);

			y += 0.13;

		}
		y = imprimirPagos(page, venta.getPagos(), 1, y + .10);
		y += .13;
	 
		 
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
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		page.add(pTotalLetra);
		y += .13;
		atStr = new AttributedString(totalLetra);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);
		y += .39;
		// atStr = new AttributedString(
		// "Usted cuenta con 15 días a partir de la fecha de compra para realizar cualquier cambio presentando esta nota y la prenda debidamente etiquetada");
		Leyenda l = AppInstance.idToTipoNotaLeyenda.get(0); // 0 Contado,1
															// Credito,2
															// Apartado
		atStr = new AttributedString(l.getLeyenda());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));

		PFParagraph leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		leyenda.setWidth(new PFInchUnit(2.5));
		leyenda.setHeight(new PFInchUnit(1));
		page.add(leyenda);
		y += .95;
		atStr = new AttributedString("¡GRACIAS POR SU COMPRA!");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(0.35), new PFInchUnit(y)));
		leyenda.setWidth(new PFInchUnit(2.5));
		page.add(leyenda);
		d.addPage(page);
		// }
		d.print();

	}

	


}
