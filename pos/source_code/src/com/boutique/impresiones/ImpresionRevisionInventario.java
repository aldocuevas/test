package com.boutique.impresiones;

import java.text.*;
import java.util.List;

import java.awt.Font;
import java.awt.font.*;

import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.engine.impl.*;

import report.*;

/**
 * <p>
 * Title: boutique management
 * </p>
 * 
 * <p>
 * Description: Sistema de administracion de boitiques
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * 
 * <p>
 * Company: SESTO
 * </p>
 * 
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class ImpresionRevisionInventario {
	static PFPageFormat format = new PFPageFormat();
	static PFInchUnit margen = new PFInchUnit(0.7);
	static double y;
	RotacionInventario ri;
	List<?> productos;
	static RevisionInventarioEngine engine;
	static {
		format.setTopMargin(margen);
		format.setLeftMargin(margen);
		format.setBottomMargin(margen);
		format.setRightMargin(margen);
	}

	public ImpresionRevisionInventario(RevisionInventarioEngine engine) {
		ImpresionRevisionInventario.engine = engine;
	}

	public static void imprimirEncabezado(PFPage page, String pagina) {
		y = .8;
		AttributedString atStr = new AttributedString(
				com.boutique.engine.impl.AppInstance.nombreNegocio);
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
		// ROTACION DE INVANTARIOS
		String enca = "REVISION DE INVENTARIO";
		atStr = new AttributedString(enca);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph nombrePropietario = new PFParagraph(atStr);
		nombrePropietario.setPosition(new PFPoint(new PFInchUnit(2.2),
				new PFInchUnit(y)));
		nombrePropietario.setWidth(new PFInchUnit(7.5));
		nombrePropietario.setHeight(new PFInchUnit(1));
		page.add(nombrePropietario);
		y += .16;
		// AHORA IMPRIMIMOS LA FECHA EL FOLIO DELA NOTA
		atStr = new AttributedString("FECHA:"
				+ AppInstance.formatoLargo.format(DaoSource.getFechaActual()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(2.21), new PFInchUnit(y)));
		folio.setWidth(new PFInchUnit(3.8));
		page.add(folio);
		y += .16;
		// BOUTIQUE INICIO Y FINAL. JIJI
		atStr = new AttributedString("SUCURSAL: "
				+ AppInstance.idToNombreBoutique.get(engine.inventario
						.getBoutique().getId()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(2.3), new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(6.8));
		page.add(vendedor);
		y += .23;
		// BOUTIQUE INICIO Y FINAL. JIJI
		atStr = new AttributedString("AUDITOR: "
				+ engine.inventario.getAuditor().getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(6.8));
		page.add(vendedor);

		y += .16;
		// BOUTIQUE INICIO Y FINAL. JIJI
		atStr = new AttributedString("EMPLEADO PRESENTE: "
				+ engine.inventario.getEmpeladoPresente().getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(6.8));
		page.add(vendedor);
		y += .23;

	}

	public static void imprimirEncabezadoTicket(PFPage page) {
		y = .8;
		AttributedString atStr = new AttributedString(
				com.boutique.engine.impl.AppInstance.nombreNegocio);
		atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.BOLD, 9));
		PFParagraph encabezado = new PFParagraph(atStr);
		encabezado.setPosition(new PFPoint(new PFInchUnit(0.50),
				new PFInchUnit(y)));
		encabezado.setWidth(new PFInchUnit(7));
		page.add(encabezado);

		y += .13;
		// ROTACION DE INVANTARIOS
		String enca = "REVISION DE INVENTARIO";
		atStr = new AttributedString(enca);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph nombrePropietario = new PFParagraph(atStr);
		nombrePropietario.setPosition(new PFPoint(new PFInchUnit(0.5),
				new PFInchUnit(y)));
		nombrePropietario.setWidth(new PFInchUnit(3));
		nombrePropietario.setHeight(new PFInchUnit(1));
		page.add(nombrePropietario);
		y += .16;
		// AHORA IMPRIMIMOS LA FECHA EL FOLIO DELA NOTA
		atStr = new AttributedString("FECHA:"
				+ AppInstance.formatoLargo.format(DaoSource.getFechaActual()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(0.21), new PFInchUnit(y)));
		folio.setWidth(new PFInchUnit(3));
		page.add(folio);
		y += .16;
		// BOUTIQUE INICIO Y FINAL. JIJI
		atStr = new AttributedString("SUCURSAL: "
				+ AppInstance.idToNombreBoutique.get(engine.inventario
						.getBoutique().getId()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(3));
		page.add(vendedor);
		y += .23;
		// BOUTIQUE INICIO Y FINAL. JIJI
		atStr = new AttributedString("AUDITOR: "
				+ engine.inventario.getAuditor().getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(3));
		page.add(vendedor);

		y += .16;
		// BOUTIQUE INICIO Y FINAL. JIJI
		atStr = new AttributedString("EMPLEADO PRESENTE: "
				+ engine.inventario.getEmpeladoPresente().getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(3));
		page.add(vendedor);
		y += .23;

	}

	public static void imprimir() {

		int totalFaltantes = engine.inventario.getProductosInventario().size();
		int totalNoEncontrados = engine.inventario.getProductosNoEncontrados()
				.size();
		int totalRegistros = totalFaltantes + totalNoEncontrados;
		List<?> productosFaltantes = engine.inventario.getProductosInventario();
		List<?> productosNoEncontrados = engine.inventario
				.getProductosNoEncontrados();
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();
		d.showPrintDialog(true);

		// Ponemos el id
		page.setPageFormat(format);
		String pagina = "1 de " + (int) (Math.ceil(totalRegistros / 60.0 + 1));
		imprimirEncabezado(page, pagina);
		int numRenglones = 0;

		int j = 1;

		y += .16;
		AttributedString atStr = new AttributedString(
				"CANTIDAD DE PRODUCTOS INICIALES : "
						+ engine.inventario.cantidadProductosInicial);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph cantidad = new PFParagraph(atStr);
		cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		cantidad.setWidth(new PFInchUnit(5));
		page.add(cantidad);
		y += .16;
		atStr = new AttributedString("MONTO INICIAL : $"
				+ AppInstance.number.format(engine.inventario.montoInicial));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		cantidad = new PFParagraph(atStr);
		cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		cantidad.setWidth(new PFInchUnit(5));
		page.add(cantidad);

		y += .16;
		atStr = new AttributedString("CANTIDAD DE PRODUCTOS FALTANTES : "
				+ engine.inventario.cantidadProductosFaltantes);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		cantidad = new PFParagraph(atStr);
		cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		cantidad.setWidth(new PFInchUnit(5));
		page.add(cantidad);
		y += .16;
		atStr = new AttributedString("MONTO FALTANTE : $"
				+ AppInstance.number.format(engine.inventario.montoFaltante));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		cantidad = new PFParagraph(atStr);
		cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		cantidad.setWidth(new PFInchUnit(3));
		page.add(cantidad);

		y += .16;
		atStr = new AttributedString("CANTIDAD DE PRODUCTOS NO ENCONTRADOS : "
				+ engine.inventario.cantidadProductosNoEncontrados);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		cantidad = new PFParagraph(atStr);
		cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		cantidad.setWidth(new PFInchUnit(5));
		page.add(cantidad);
		y += .16;
		atStr = new AttributedString(
				"MONTO DE PRODUCTOS NO ENCONTRADOS : $"
						+ AppInstance.number
								.format(engine.inventario.montoNoEncontrado));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		cantidad = new PFParagraph(atStr);
		cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		cantidad.setWidth(new PFInchUnit(5));
		page.add(cantidad);
		y += .23;
		imprimirEncabezadoFaltantes(page);
		for (int i = 0; i < totalFaltantes; i++) {

			// HASTA LOS 60
			if ((numRenglones == 50 && i == 50)
					|| (numRenglones == 60 && i > 50)) {
				d.addPage(page);
				page = new PFPage();
				page.setPageFormat(format);
				pagina = ++j + " de "
						+ (int) (Math.ceil(totalRegistros / 60.0 + 1));
				numRenglones = 0;
				y = 1.0;
				imprimirEncabezado(page, pagina);
				imprimirEncabezadoFaltantes(page);
			}
			// id,cantidad,descropcion,etiqueta,fecha de
			// adquisicion?,precioalpublico, total.
			Object[] row = (Object[]) productosFaltantes.get(i);
			numRenglones++;
			atStr = new AttributedString(row[1].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			cantidad = new PFParagraph(atStr);
			cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
					new PFInchUnit(y)));
			cantidad.setWidth(new PFInchUnit(3));
			page.add(cantidad);
			String strDesc = row[2].toString();
			if (strDesc.length() > 13) {
				strDesc = strDesc.substring(0, 12);
			}

			atStr = new AttributedString(strDesc);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(1.7),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(3));
			page.add(descripcion);
			atStr = new AttributedString(row[4].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(3.3),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(3));
			page.add(descripcion);

			atStr = new AttributedString(
					AppInstance.formatoCorto.format((java.sql.Date) row[5]));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph precioInicial = new PFParagraph(atStr);
			precioInicial.setPosition(new PFPoint(new PFInchUnit(4.2),
					new PFInchUnit(y)));
			precioInicial.setWidth(new PFInchUnit(3));
			page.add(precioInicial);

			try {
				atStr = new AttributedString(
						AppInstance.number.format(AppInstance.number
								.parse(row[6].toString())));
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 9));
				precioInicial = new PFParagraph(atStr);
				precioInicial.setPosition(new PFPoint(new PFInchUnit(5.5),
						new PFInchUnit(y)));
				precioInicial.setWidth(new PFInchUnit(3));
				page.add(precioInicial);
				atStr = new AttributedString(
						AppInstance.number.format(AppInstance.number
								.parse(row[7].toString())));
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 9));
				precioInicial = new PFParagraph(atStr);
				precioInicial.setPosition(new PFPoint(new PFInchUnit(6.5),
						new PFInchUnit(y)));
				precioInicial.setWidth(new PFInchUnit(3));
				page.add(precioInicial);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			y += 0.13;

		}

		if (totalNoEncontrados > 0) {
			d.addPage(page);
			page = new PFPage();
			page.setPageFormat(format);
			pagina = ++j + " de "
					+ (int) (Math.ceil(totalRegistros / 60.0 + 1));
			numRenglones = 0;
			imprimirEncabezado(page, pagina);
			imprimirEncabezadoNoEncontrados(page);

			for (int i = 0; i < totalNoEncontrados; i++) {
				// HASTA LOS 60
				if (((numRenglones == 50 && i == 50) || (numRenglones == 60 && i > 50))) {
					d.addPage(page);
					page = new PFPage();
					page.setPageFormat(format);
					pagina = ++j + " de "
							+ (int) (Math.ceil(totalRegistros / 60.0 + 1));
					numRenglones = 0;
					y = 1.0;
					imprimirEncabezado(page, pagina);
					imprimirEncabezadoNoEncontrados(page);
				}
				// id,cantidad,descropcion,etiqueta,fecha de
				// adquisicion?,precioalpublico, total.
				Object[] row = (Object[]) productosNoEncontrados.get(i);
				numRenglones++;
				atStr = new AttributedString(row[1].toString());
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 9));
				cantidad = new PFParagraph(atStr);
				cantidad.setPosition(new PFPoint(new PFInchUnit(1.01),
						new PFInchUnit(y)));
				cantidad.setWidth(new PFInchUnit(3));
				page.add(cantidad);
				String strDesc = row[2].toString();
				if (strDesc.length() > 13) {
					strDesc = strDesc.substring(0, 12);
				}

				atStr = new AttributedString(strDesc);
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 9));
				PFParagraph descripcion = new PFParagraph(atStr);
				descripcion.setPosition(new PFPoint(new PFInchUnit(1.7),
						new PFInchUnit(y)));
				descripcion.setWidth(new PFInchUnit(3));
				page.add(descripcion);
				atStr = new AttributedString(row[4].toString());
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 9));
				descripcion = new PFParagraph(atStr);
				descripcion.setPosition(new PFPoint(new PFInchUnit(3.3),
						new PFInchUnit(y)));
				descripcion.setWidth(new PFInchUnit(3));
				page.add(descripcion);

				atStr = new AttributedString(
						AppInstance.formatoCorto.format((java.sql.Date) row[5]));
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 9));
				PFParagraph precioInicial = new PFParagraph(atStr);
				precioInicial.setPosition(new PFPoint(new PFInchUnit(4.2),
						new PFInchUnit(y)));
				precioInicial.setWidth(new PFInchUnit(3));
				page.add(precioInicial);

				try {
					atStr = new AttributedString(
							AppInstance.number.format(AppInstance.number
									.parse(row[6].toString())));
					atStr.addAttribute(TextAttribute.FONT, new Font("modern",
							Font.PLAIN, 9));
					precioInicial = new PFParagraph(atStr);
					precioInicial.setPosition(new PFPoint(new PFInchUnit(5.5),
							new PFInchUnit(y)));
					precioInicial.setWidth(new PFInchUnit(3));
					page.add(precioInicial);
					atStr = new AttributedString(
							AppInstance.number.format(AppInstance.number
									.parse(row[7].toString())));
					atStr.addAttribute(TextAttribute.FONT, new Font("modern",
							Font.PLAIN, 9));
					precioInicial = new PFParagraph(atStr);
					precioInicial.setPosition(new PFPoint(new PFInchUnit(6.5),
							new PFInchUnit(y)));
					precioInicial.setWidth(new PFInchUnit(3));
					page.add(precioInicial);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				y += 0.13;

			}
		}
		y += 0.26;

		atStr = new AttributedString(
				"AUDITOR: ______________________________________________");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph totalesSinDescontar = new PFParagraph(atStr);
		totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		totalesSinDescontar.setWidth(new PFInchUnit(9));
		page.add(totalesSinDescontar);
		y += 0.30;
		atStr = new AttributedString(
				"EMPLEADO: _______________________________________________");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		totalesSinDescontar = new PFParagraph(atStr);
		totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		totalesSinDescontar.setWidth(new PFInchUnit(9));
		page.add(totalesSinDescontar);
		y += 0.30;
		atStr = new AttributedString(
				"EMPLEADO (NOMBRE Y FIRMA):_________________________________________________");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		totalesSinDescontar = new PFParagraph(atStr);
		totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		totalesSinDescontar.setWidth(new PFInchUnit(9));
		page.add(totalesSinDescontar);
		d.addPage(page);
		// El total con letra queda pendiente..
		d.print();
	}

	public static void imprimirEnTicket() {

		/*
		 * int totalFaltantes =
		 * engine.inventario.getProductosInventario().size(); int
		 * totalNoEncontrados =
		 * engine.inventario.getProductosNoEncontrados().size(); int
		 * totalRegistros = totalFaltantes + totalNoEncontrados; List productos
		 * = null;
		 * //DaoInventarioInicial.findProductosNoDistribuidosViewBySucursal
		 * (idBoutique); List productosFaltantes =
		 * engine.inventario.getProductosInventario(); List
		 * productosNoEncontrados =
		 * engine.inventario.getProductosNoEncontrados(); double
		 * montoNoEncontrado = engine.inventario.montoNoEncontrado; PFDocument d
		 * = new PFDocument(); //Agregamos una pagina PFPage page = new
		 * PFPage(); d.showPrintDialog(true);
		 * 
		 * //Ponemos el id page.setPageFormat(format);
		 * imprimirEncabezadoTicket(page); int numRenglones = 0;
		 * 
		 * int j = 1;
		 * 
		 * y += .16; AttributedString atStr = new AttributedString(
		 * "CANTIDAD DE PRODUCTOS INICIALES : " +
		 * engine.inventario.cantidadProductosInicial);
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); PFParagraph cantidad = new PFParagraph(atStr);
		 * cantidad.setPosition(new PFPoint(new PFInchUnit(0.2), new
		 * PFInchUnit(y))); cantidad.setWidth(new PFInchUnit(3));
		 * page.add(cantidad); y += .16; atStr = new
		 * AttributedString("MONTO INICIAL : $" + AppInstance.number.
		 * format(engine.inventario.montoInicial));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad);
		 * 
		 * y += .16; atStr = new
		 * AttributedString("CANTIDAD DE PRODUCTOS FALTANTES : " +
		 * engine.inventario.cantidadProductosFaltantes);
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad); y += .16;
		 * atStr = new AttributedString("MONTO FALTANTE : $" +
		 * AppInstance.number. format(engine.inventario.montoFaltante));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad);
		 * 
		 * y += .16; atStr = new
		 * AttributedString("CANTIDAD DE PRODUCTOS NO ENCONTRADOS : " +
		 * engine.inventario. cantidadProductosNoEncontrados);
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad); y += .16;
		 * atStr = new AttributedString("MONTO DE PRODUCTOS NO ENCONTRADOS : $"
		 * + AppInstance.number.format(engine.inventario. montoNoEncontrado));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(0.2), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad); y += .23;
		 * imprimirEncabezadoFaltantes(page); for (int i = 0; i <
		 * totalFaltantes; i++) {
		 * 
		 * // id,cantidad,descropcion,etiqueta,fecha de
		 * adquisicion?,precioalpublico, total. Object[] row = (Object[])
		 * productosFaltantes.get(i); numRenglones++; atStr = new
		 * AttributedString(row[1].toString());
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(1.01), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad); String
		 * strDesc = row[2].toString(); if (strDesc.length() > 13) { strDesc =
		 * strDesc.substring(0, 12); }
		 * 
		 * atStr = new AttributedString(strDesc);
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); PFParagraph descripcion = new PFParagraph(atStr);
		 * descripcion.setPosition(new PFPoint(new PFInchUnit(1.7), new
		 * PFInchUnit(y))); descripcion.setWidth(new PFInchUnit(3));
		 * page.add(descripcion); atStr = new
		 * AttributedString(row[3].toString());
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); descripcion = new PFParagraph(atStr);
		 * descripcion.setPosition(new PFPoint(new PFInchUnit(3.3), new
		 * PFInchUnit(y))); descripcion.setWidth(new PFInchUnit(3));
		 * page.add(descripcion);
		 * 
		 * atStr = new AttributedString(AppInstance.formatoCorto.format(
		 * (java.sql. Date) row[4])); atStr.addAttribute(TextAttribute.FONT, new
		 * Font("modern", Font.PLAIN, 9)); PFParagraph precioInicial = new
		 * PFParagraph(atStr); precioInicial.setPosition(new PFPoint(new
		 * PFInchUnit(4.2), new PFInchUnit(y))); precioInicial.setWidth(new
		 * PFInchUnit(3)); page.add(precioInicial);
		 * 
		 * try { atStr = new
		 * AttributedString(AppInstance.number.format(AppInstance.
		 * number.parse(row[5].toString())));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); precioInicial = new PFParagraph(atStr);
		 * precioInicial.setPosition(new PFPoint(new PFInchUnit(5.5), new
		 * PFInchUnit(y))); precioInicial.setWidth(new PFInchUnit(3));
		 * page.add(precioInicial); atStr = new
		 * AttributedString(AppInstance.number.format(AppInstance.
		 * number.parse(row[6].toString())));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); precioInicial = new PFParagraph(atStr);
		 * precioInicial.setPosition(new PFPoint(new PFInchUnit(6.5), new
		 * PFInchUnit(y))); precioInicial.setWidth(new PFInchUnit(3));
		 * page.add(precioInicial); } catch (ParseException ex) {
		 * ex.printStackTrace(); } y += 0.13;
		 * 
		 * }
		 * 
		 * 
		 * if (totalNoEncontrados > 0) { d.addPage(page); page = new PFPage();
		 * page.setPageFormat(format); pagina = ++j + " de " + (int)
		 * (Math.ceil(totalRegistros / 60.0 + 1)); numRenglones = 0;
		 * imprimirEncabezado(page, pagina);
		 * imprimirEncabezadoNoEncontrados(page);
		 * 
		 * for (int i = 0; i < totalNoEncontrados; i++) { //HASTA LOS 60 if ( (
		 * (numRenglones == 50 && i == 50) || (numRenglones == 60 && i > 50))) {
		 * d.addPage(page); page = new PFPage(); page.setPageFormat(format);
		 * pagina = ++j + " de " + (int) (Math.ceil(totalRegistros / 60.0 + 1));
		 * numRenglones = 0; y = 1.0; imprimirEncabezado(page, pagina);
		 * imprimirEncabezadoNoEncontrados(page); } //
		 * id,cantidad,descropcion,etiqueta,fecha de
		 * adquisicion?,precioalpublico, total. Object[] row = (Object[])
		 * productosNoEncontrados.get(i); numRenglones++; atStr = new
		 * AttributedString(row[1].toString());
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); cantidad = new PFParagraph(atStr); cantidad.setPosition(new
		 * PFPoint(new PFInchUnit(1.01), new PFInchUnit(y)));
		 * cantidad.setWidth(new PFInchUnit(3)); page.add(cantidad); String
		 * strDesc = row[2].toString(); if (strDesc.length() > 13) { strDesc =
		 * strDesc.substring(0, 12); }
		 * 
		 * atStr = new AttributedString(strDesc);
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); PFParagraph descripcion = new PFParagraph(atStr);
		 * descripcion.setPosition(new PFPoint(new PFInchUnit(1.7), new
		 * PFInchUnit(y))); descripcion.setWidth(new PFInchUnit(3));
		 * page.add(descripcion); atStr = new
		 * AttributedString(row[3].toString());
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); descripcion = new PFParagraph(atStr);
		 * descripcion.setPosition(new PFPoint(new PFInchUnit(3.3), new
		 * PFInchUnit(y))); descripcion.setWidth(new PFInchUnit(3));
		 * page.add(descripcion);
		 * 
		 * atStr = new AttributedString(AppInstance.formatoCorto.format(
		 * (java.sql. Date) row[4])); atStr.addAttribute(TextAttribute.FONT, new
		 * Font("modern", Font.PLAIN, 9)); PFParagraph precioInicial = new
		 * PFParagraph(atStr); precioInicial.setPosition(new PFPoint(new
		 * PFInchUnit(4.2), new PFInchUnit(y))); precioInicial.setWidth(new
		 * PFInchUnit(3)); page.add(precioInicial);
		 * 
		 * try { atStr = new
		 * AttributedString(AppInstance.number.format(AppInstance.
		 * number.parse(row[5].toString())));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); precioInicial = new PFParagraph(atStr);
		 * precioInicial.setPosition(new PFPoint(new PFInchUnit(5.5), new
		 * PFInchUnit(y))); precioInicial.setWidth(new PFInchUnit(3));
		 * page.add(precioInicial); atStr = new
		 * AttributedString(AppInstance.number.format(AppInstance.
		 * number.parse(row[6].toString())));
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); precioInicial = new PFParagraph(atStr);
		 * precioInicial.setPosition(new PFPoint(new PFInchUnit(6.5), new
		 * PFInchUnit(y))); precioInicial.setWidth(new PFInchUnit(3));
		 * page.add(precioInicial); } catch (ParseException ex) {
		 * ex.printStackTrace(); } y += 0.13;
		 * 
		 * } } y += 0.26;
		 * 
		 * atStr = new AttributedString(
		 * "AUDITOR: ______________________________________________");
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); PFParagraph totalesSinDescontar = new PFParagraph(atStr);
		 * totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01), new
		 * PFInchUnit(y))); totalesSinDescontar.setWidth(new PFInchUnit(9));
		 * page.add(totalesSinDescontar); y += 0.30; atStr = new
		 * AttributedString(
		 * "EMPLEADO: _______________________________________________");
		 * atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
		 * 9)); totalesSinDescontar = new PFParagraph(atStr);
		 * totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01), new
		 * PFInchUnit(y))); totalesSinDescontar.setWidth(new PFInchUnit(9));
		 * page.add(totalesSinDescontar); y += 0.30; atStr = new
		 * AttributedString(
		 * "EMPLEADO (NOMBRE Y FIRMA):_________________________________________________"
		 * ); atStr.addAttribute(TextAttribute.FONT, new Font("modern",
		 * Font.PLAIN, 9)); totalesSinDescontar = new PFParagraph(atStr);
		 * totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.01), new
		 * PFInchUnit(y))); totalesSinDescontar.setWidth(new PFInchUnit(9));
		 * page.add(totalesSinDescontar); d.addPage(page); //El total con letra
		 * queda pendiente.. d.print();
		 */

	}

	/**
	 * imprimirEncabezadoNoEncontrados
	 * 
	 * @param page
	 *            PFPage
	 */
	private static void imprimirEncabezadoNoEncontrados(PFPage page) {
		AttributedString atStr = new AttributedString(
				"LISTA DE PRODUCTOS NO ENCONTRADOS");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(2.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(8.5));
		page.add(encabezado2);
		imprimirEncabezadoProductos(page);

	}

	private static void imprimirEncabezadoFaltantes(PFPage page) {
		AttributedString atStr = new AttributedString(
				"LISTA DE PRODUCTOS FALTANTES");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(2.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(8.5));
		page.add(encabezado2);
		imprimirEncabezadoProductos(page);
	}

	/**
	 * imprimirEncabezadoProductos
	 */
	private static void imprimirEncabezadoProductos(PFPage page) {

		y += 0.26;
		/*********************** AGREGAMOS LOS DATOS DE LOS PRODUCTOS ******************/

		AttributedString atStr = new AttributedString(
				"Cantidad      Descripcion                        Etiqueta              Fecha de compra          P.U.                      Total");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(8.5));
		page.add(encabezado2);
		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------------------------------------------------------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(1.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(8));
		page.add(encabezado23);
		y += .13;
	}

}
