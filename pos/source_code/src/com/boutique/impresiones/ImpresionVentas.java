package com.boutique.impresiones;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import report.PFDocument;
import report.PFInchUnit;
import report.PFPage;
import report.PFPageFormat;
import report.PFParagraph;
import report.PFPoint;
import report.PFSize;

import com.boutique.dao.DaoSource;
import com.boutique.dao.DaoTipoProductos;
import com.boutique.domain.Boutique;
import com.boutique.domain.Cliente;
import com.boutique.domain.Leyenda;
import com.boutique.domain.Pago;
import com.boutique.domain.PagoCheque;
import com.boutique.domain.PagoTarjetaCredito;
import com.boutique.domain.PagoTransferenciaBancaria;
import com.boutique.domain.PagoVale;
import com.boutique.domain.PersonaMoral;
import com.boutique.domain.ProductoVendido;
import com.boutique.domain.Vale;
import com.boutique.domain.Venta;
import com.boutique.domain.VentaApartado;
import com.boutique.domain.VentaCredito;
import com.boutique.engine.impl.AppInstance;
import com.boutique.impresiones.ventas.Ticket;

public class ImpresionVentas {
	static java.text.NumberFormat numero = java.text.NumberFormat
			.getNumberInstance(Locale.ENGLISH);
	static java.text.SimpleDateFormat formato = new SimpleDateFormat(
			"dd-MMM-yyyy HH:mm");
	static PFPageFormat format = new PFPageFormat();
	static PFInchUnit margen = new PFInchUnit(0.01);
	static Map<?, ?> idToTipoProducto = DaoTipoProductos.getMapLocal();
	static double total = 0.0;
	static double totalSinDescontar = 0.0;
	static {
		numero.setMinimumFractionDigits(2);
		numero.setMaximumFractionDigits(2);
		format.setPageSize(new PFSize(new PFInchUnit(3.2), new PFInchUnit(13)));
		format.setTopMargin(margen);
		format.setLeftMargin(margen);
		format.setBottomMargin(margen);
		format.setRightMargin(margen);
	}

	public static void imprimirDatosCliente(PFPage page,
			com.boutique.domain.Cliente cliente) {
		// Ponemos el recuadro con los datos del cliente
		// Ponemos el nombre del cliente que es publico al general
		AttributedString atStr = new AttributedString(cliente.getNombre() + " "
				+ cliente.getApellidoPaterno() + " "
				+ cliente.getApellidoMaterno());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.85),
				new PFInchUnit(1.5)));
		nombreCliente.setWidth(new PFInchUnit(5));
		page.add(nombreCliente);

		// Ponemos para domicilio y telefono
		atStr = new AttributedString(cliente.getCalle() + " "
				+ cliente.getNumero() + " Col. " + cliente.getColonia());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph domicilio = new PFParagraph(atStr);
		domicilio.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(
				1.7)));
		domicilio.setWidth(new PFInchUnit(4));
		page.add(domicilio);

		atStr = new AttributedString(cliente.getTelefono() + " ");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph telefono = new PFParagraph(atStr);
		telefono.setPosition(new PFPoint(new PFInchUnit(0.9), new PFInchUnit(
				1.9)));
		telefono.setWidth(new PFInchUnit(3));
		page.add(telefono);

	}

	public static void imprimirVentaCredito(
			com.boutique.domain.VentaCredito venta, List<Object[]> listaPagos,
			com.boutique.domain.Cliente cliente) {
		double y;
		totalSinDescontar = 0;
		total = 0;
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		y = 0.1;
		AttributedString atStr;
		y = Ticket.imprimirEncabezado(page, "CREDITO", venta, y,
				venta.getFecha());
		y += 0.26;
		/*********************** AGREGAMOS LOS DATOS DEL CLIENTE ******************/
		atStr = new AttributedString("DATOS DEL CLIENTE: "
				+ cliente.getNombre() + " " + cliente.getApellidoPaterno()
				+ " " + cliente.getApellidoMaterno() + " COD CLIENTE:"
				+ cliente.getId() + "DOM: " + cliente.getCalle() + " "
				+ cliente.getNumero() + " Col. " + cliente.getColonia()
				+ "Tel." + cliente.getTelefono()); // Nombre

		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
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
				"Ct. Codigo               Ini   Desc  P.Fin");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);
		y += 0.1;
		atStr = new AttributedString("Descripcion");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);

		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);

		// imprimimos los datos del cliente

		// imprimirDatosCliente(page, cliente);
		// los productos
		y += .13;
		y = imprimirProductos(page, venta.getProductosVendidos(), y);
		y += .08;

		// Imprimimos el total sin descontar
		atStr = new AttributedString("SUBTOTAL    "
				+ numero.format(totalSinDescontar));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph totalesSinDescontar = new PFParagraph(atStr);
		totalesSinDescontar.setPosition(new PFPoint(new PFInchUnit(1.1),
				new PFInchUnit(y)));
		totalesSinDescontar.setWidth(new PFInchUnit(3));
		page.add(totalesSinDescontar);
		atStr = new AttributedString("_________");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph raya = new PFParagraph(atStr);
		raya.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(
				y += .02)));
		raya.setWidth(new PFInchUnit(1.3));
		page.add(raya);
		y += .08;
		// los anticipos
		y = imprimirPagos(page, venta.getPagos(), y);

		atStr = new AttributedString("_________");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		raya = new PFParagraph(atStr);
		raya.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(
				y += .02)));
		raya.setWidth(new PFInchUnit(1.3));
		page.add(raya);
		y += .16;
		// el total con el descuento

		atStr = new AttributedString("TOTAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.3), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(numero.format(total));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(2), new PFInchUnit(y)));
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
		atStr = new AttributedString(
				com.boutique.util.Conversor.convertir(total));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);
		// las fechas de pago
		y += .32;
		y = imprimirFechasPago(page, listaPagos, y);
		y += .18;
		// 0 Contado,1 Credito,2 Apartado

		if (AppInstance.promocionDesc != null) {
			String fechaFinal = AppInstance.formatoCorto
					.format(com.boutique.engine.impl.AppInstance.promocionDesc
							.getFechaFinal());
			String saldoConDesc = AppInstance.number
					.format((totalSinDescontar - (totalSinDescontar
							* AppInstance.promocionDesc
									.getPorcentajeDescuento() / 100)));
			atStr = new AttributedString("Si usted liquida su nota antes del "
					+ fechaFinal + ", su saldo a liquidar será de $"
					+ saldoConDesc);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.BOLD, 8));
			PFParagraph leyenda = new PFParagraph(atStr);
			leyenda.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			leyenda.setWidth(new PFInchUnit(2.5));
			leyenda.setHeight(new PFInchUnit(1));
			page.add(leyenda);
			y += .18;

		}

		Leyenda l = AppInstance.idToTipoNotaLeyenda.get(1);

		atStr = new AttributedString(l.getLeyenda());
		y += .18;

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
		y += 0.39;

		atStr = new AttributedString("PAGARE");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1), new PFInchUnit(y)));
		leyenda.setWidth(new PFInchUnit(2.5));
		page.add(leyenda);

		y += 0.26;

		String str = "___________________________________; a ___ de ____ de ____.";
		atStr = new AttributedString(str);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		page.add(pagare);
		y += .39;
		Boutique b = AppInstance.boutique();
		str = "Por el presente PAGARE, me obligo a pagar incondicionalmente a la orden de "
				+ b.getNombrePropietario()
				+ " , en la Ciudad de Colima, Colima, o en cualquier otro que se me requiera, el dia ___ de _______________ de _____ la cantidad de $_______ PESOS _____M.N. El presente causa interes mensual del _____% sobre el importe de este pagare y en caso de incumplimiento, pagaré además un interes moratorio del ____% mensual.";
		atStr = new AttributedString(str);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		pagare.setHeight(new PFInchUnit(2));
		page.add(pagare);
		y += 2.3;

		str = "__________________________________";
		atStr = new AttributedString(str);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		page.add(pagare);
		y += .13;

		str = "Acepto";
		atStr = new AttributedString(str);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(1), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		page.add(pagare);
		y += .13;

		d.addPage(page);
		// El total con letra queda pendiente..
		d.print();

	}

	/**
	 * imprimirFechasPago
	 * 
	 * @param page
	 *            PFPage
	 * @param listaPagos
	 *            List
	 */
	private static double imprimirFechasPago(PFPage page,
			List<Object[]> listaPagos, double ys) {
		double y = ys;
		AttributedString atStr = null;
		atStr = new AttributedString("FECHAS DE PAGO");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph t = new PFParagraph(atStr);
		t.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		t.setWidth(new PFInchUnit(2.5));
		page.add(t);
		y += .13;
		atStr = new AttributedString("No.           Fecha                Monto");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph tt = new PFParagraph(atStr);
		tt.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		tt.setWidth(new PFInchUnit(2.5));
		page.add(tt);
		y += .13;
		for (Object[] row : listaPagos) {
			atStr = new AttributedString(row[0].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph no = new PFParagraph(atStr);
			no.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
			no.setWidth(new PFInchUnit(.3));
			page.add(no);

			atStr = new AttributedString(row[1].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph fecha = new PFParagraph(atStr);
			fecha.setPosition(new PFPoint(new PFInchUnit(0.5),
					new PFInchUnit(y)));
			fecha.setWidth(new PFInchUnit(1.5));
			page.add(fecha);

			atStr = new AttributedString(row[2].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph monto = new PFParagraph(atStr);
			monto.setPosition(new PFPoint(new PFInchUnit(1.5),
					new PFInchUnit(y)));
			monto.setWidth(new PFInchUnit(2.5));
			page.add(monto);
			y += 0.13;

		}
		return y;
	}

	public static void imprimirVentaApartado(
			com.boutique.domain.VentaApartado venta) {
		total = 0;
		// for(int j=0;j<3;j++){
		PFDocument d = new PFDocument();
		// Agregamos una pagina

		PFPage page = new PFPage();
		page.setPageFormat(format);
		// Ponemos el id de la venta
		AttributedString atStr = new AttributedString(String.valueOf(venta
				.getId()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.BOLD, 15));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(3.5), new PFInchUnit(0.5)));
		folio.setWidth(new PFInchUnit(1.5));
		page.add(folio);

		// Ponemos el nombre del cliente que es publico al general
		atStr = new AttributedString(venta.getCliente());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.85),
				new PFInchUnit(1.5)));
		nombreCliente.setWidth(new PFInchUnit(3.2));
		page.add(nombreCliente);

		// Ponemos la fecha

		atStr = new AttributedString(formato.format(venta.getFecha()));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph fechaHora = new PFParagraph(atStr);
		fechaHora.setPosition(new PFPoint(new PFInchUnit(4.3), new PFInchUnit(
				1.5)));
		fechaHora.setWidth(new PFInchUnit(3));
		page.add(fechaHora);

		// Ponemos para domicilio y telefono
		atStr = new AttributedString(venta.getDomicilio());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph domicilio = new PFParagraph(atStr);
		domicilio.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(
				1.7)));
		domicilio.setWidth(new PFInchUnit(3));
		page.add(domicilio);

		atStr = new AttributedString(venta.getTelefono());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph telefono = new PFParagraph(atStr);
		telefono.setPosition(new PFPoint(new PFInchUnit(0.9), new PFInchUnit(
				1.9)));
		telefono.setWidth(new PFInchUnit(3));
		page.add(telefono);

		atStr = new AttributedString("APARTADO");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.BOLD, 18));
		PFParagraph apartado = new PFParagraph(atStr);
		apartado.setPosition(new PFPoint(new PFInchUnit(4.0), new PFInchUnit(
				1.7)));
		apartado.setWidth(new PFInchUnit(3));
		page.add(apartado);

		// Ponemos los productos vendidos
		// y= imprimirProductos(page, venta.getProductosVendidos(),y);
		// Ponemos los pagos
		// imprimirPagos(page, venta.getPagos());
		// Imprimimos el total
		// Ponemos el total con numero
		atStr = new AttributedString(numero.format(total));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph totales = new PFParagraph(atStr);
		totales.setPosition(new PFPoint(new PFInchUnit(4.7),
				new PFInchUnit(7.9)));
		totales.setWidth(new PFInchUnit(1));
		page.add(totales);

		// Ponemos la leyenda de las devoluciones se requiere la nota.

		atStr = new AttributedString("Usted cuenta con 30 dias a partir de");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
		PFParagraph leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1.0),
				new PFInchUnit(7.5)));
		leyenda.setWidth(new PFInchUnit(3.6));
		page.add(leyenda);
		atStr = new AttributedString(
				"su fecha de compra para recoger su apartado.");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
		leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(
				7.65)));
		leyenda.setWidth(new PFInchUnit(3.8));
		page.add(leyenda);
		atStr = new AttributedString(
				"De no hacerlo su apartado ya no sera respetado.");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
		leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1.0),
				new PFInchUnit(7.8)));
		leyenda.setWidth(new PFInchUnit(3.6));
		page.add(leyenda);

		d.addPage(page);
		// El total con letra queda pendiente..
		d.print();
		// }
	}

	public static void imprimirVentaContado(Venta venta) {
		total = 0;
		totalSinDescontar = 0;
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();
		page.setPageFormat(format);
		// Ponemos el id de la venta
		AttributedString atStr = new AttributedString(String.valueOf(venta
				.getId()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.BOLD, 15));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(3.6), new PFInchUnit(0.5)));
		folio.setWidth(new PFInchUnit(3));
		page.add(folio);

		// Ponemos el nombre del cliente que es publico al general
		atStr = new AttributedString("PUBLICO EN GENERAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.85),
				new PFInchUnit(1.5)));
		nombreCliente.setWidth(new PFInchUnit(3.2));
		page.add(nombreCliente);

		atStr = new AttributedString("CONTADO");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.BOLD, 18));
		PFParagraph apartado = new PFParagraph(atStr);
		apartado.setPosition(new PFPoint(new PFInchUnit(4.0), new PFInchUnit(
				1.7)));
		apartado.setWidth(new PFInchUnit(3));
		page.add(apartado);
		// Ponemos la fecha

		atStr = new AttributedString(formato.format(venta.getFecha()));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph fechaHora = new PFParagraph(atStr);
		fechaHora.setPosition(new PFPoint(new PFInchUnit(4.3), new PFInchUnit(
				1.5)));
		fechaHora.setWidth(new PFInchUnit(3));
		page.add(fechaHora);

		// Ponemos asteriscos.... para domicilio y ciudad
		// Ponemos los productos vendidos
		// imprimirProductos(page, venta.getProductosVendidos());
		// Ponemos los pagos
		// imprimirPagos(page, venta.getPagos());

		// Imprimimos el lotal
		// Ponemos el total con numero
		atStr = new AttributedString(numero.format(totalSinDescontar));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph totales = new PFParagraph(atStr);
		totales.setPosition(new PFPoint(new PFInchUnit(4.7),
				new PFInchUnit(7.9)));
		totales.setWidth(new PFInchUnit(1));
		page.add(totales);

		// Ponemos la leyenda

		// Ponemos la leyenda de las devoluciones se requiere la nota.

		atStr = new AttributedString(
				"Usted cuenta con 8 días a partir de la fecha");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
		PFParagraph leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1.0),
				new PFInchUnit(7.5)));
		leyenda.setWidth(new PFInchUnit(4));
		page.add(leyenda);
		atStr = new AttributedString(
				"de compra para realizar cualquier cambio presentando");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
		leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(
				7.65)));
		leyenda.setWidth(new PFInchUnit(3.8));
		page.add(leyenda);
		atStr = new AttributedString(
				"esta nota y la prenda debidamente etiquetada");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 8));
		leyenda = new PFParagraph(atStr);
		leyenda.setPosition(new PFPoint(new PFInchUnit(1.0), new PFInchUnit(
				7.80)));
		leyenda.setWidth(new PFInchUnit(3.6));
		page.add(leyenda);

		d.addPage(page);
		// El total con letra queda pendiente..
		d.print();

	}

	private static double imprimirProductos(PFPage page,
			java.util.List<ProductoVendido> productos, double ys) {
		double y = ys;

		// Ponemos los productos
		AttributedString atCantidad = new AttributedString("1");
		atCantidad.addAttribute(TextAttribute.FONT, new Font("modern",
				Font.PLAIN, 8));

		AttributedString atStr = null;
		for (ProductoVendido p : productos) {
			PFParagraph cantidad = new PFParagraph(atCantidad);
			cantidad.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			cantidad.setWidth(new PFInchUnit(3));
			page.add(cantidad);
			atStr = new AttributedString(p.getEtiqueta());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(0.2),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(3));
			page.add(descripcion);
			if (p.getDescuento() > 0) {
				atStr = new AttributedString("-" + p.getDescuento() + "%");
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 8));
				PFParagraph descuento = new PFParagraph(atStr);
				descuento.setPosition(new PFPoint(new PFInchUnit(1.7),
						new PFInchUnit(y)));
				descuento.setWidth(new PFInchUnit(3));
				page.add(descuento);
			}
			atStr = new AttributedString(numero.format(p.getPrecioInicial()));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph precioInicial = new PFParagraph(atStr);
			precioInicial.setPosition(new PFPoint(new PFInchUnit(1.2),
					new PFInchUnit(y)));
			precioInicial.setWidth(new PFInchUnit(3));
			page.add(precioInicial);

			atStr = new AttributedString(numero.format(p.getPrecioFinal()));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph precioFinal = new PFParagraph(atStr);
			precioFinal.setPosition(new PFPoint(new PFInchUnit(2),
					new PFInchUnit(y)));
			precioFinal.setWidth(new PFInchUnit(3));
			page.add(precioFinal);
			total += p.getPrecioFinal();
			totalSinDescontar += p.getPrecioFinal();
			y += 0.13;
			atStr = new AttributedString(
					((p.getDescripcion().length() > 33) ? p.getDescripcion()
							.substring(0, 33) : p.getDescripcion()));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(3));
			page.add(descripcion);
			y += 0.13;
		}
		return y;
	}

	private static double imprimirPagos(PFPage page,
			java.util.List<Pago> pagos, double ys) {
		double y = ys;
		y += .15;
		AttributedString atStr = null;
		atStr = new AttributedString("Pagos/Anticipo");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph t = new PFParagraph(atStr);
		t.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		t.setWidth(new PFInchUnit(2.5));
		page.add(t);
		y += .15;
		for (Pago pago : pagos) {
			if (pago instanceof PagoCheque) {
				PagoCheque pch = (PagoCheque) pago;
				atStr = new AttributedString("Ch. " + pch.getBanco() + ","
						+ pch.getNoCheque());
			} else if (pago instanceof PagoVale) {
				PagoVale pv = (PagoVale) pago;
				atStr = new AttributedString("Vale no. " + pv.getIdVale());
			} else if (pago instanceof PagoTarjetaCredito) {
				PagoTarjetaCredito pt = (PagoTarjetaCredito) pago;
				atStr = new AttributedString("T.C/D " + pt.getBanco() + ", "
						+ ((pt.getMeses() > 0) ? pt.getMeses() : ""));

			} else if (pago instanceof PagoTransferenciaBancaria) {
				PagoTransferenciaBancaria pb = (PagoTransferenciaBancaria) pago;
				atStr = new AttributedString("T/D Banco:"
						+ pb.getNoReferencia());
			} else if (pago instanceof Pago) {
				atStr = new AttributedString("Efectivo");

			}
			// Ponemos la referencia del pago
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph pagoRef = new PFParagraph(atStr);
			pagoRef.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pagoRef.setWidth(new PFInchUnit(2.5));
			page.add(pagoRef);

			atStr = new AttributedString("-" + numero.format(pago.getMonto()));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));

			PFParagraph pagoMonto = new PFParagraph(atStr);
			pagoMonto.setPosition(new PFPoint(new PFInchUnit(1.9),
					new PFInchUnit(y)));
			pagoMonto.setWidth(new PFInchUnit(1.3));
			page.add(pagoMonto);
			total -= pago.getMonto();
			y += .12;
		}

		return y - .11;
	}

	public static void imprimirAbonos(Cliente cliente, List<?> pagosRealizados,
			double nuevoSaldoVencido, double nuevoSaldoTotal) {

	}

	/**
	 * imprimirValeDeCompra
	 * 
	 * @param vale
	 *            Vale
	 */

	static double imprimirEncabezado(PFPage page, Vale vale, double ys) {
		double y = ys;
		AttributedString atStr = new AttributedString(
				com.boutique.engine.impl.AppInstance.nombreNegocio);
		atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.BOLD, 9));
		PFParagraph encabezado = new PFParagraph(atStr);
		encabezado.setPosition(new PFPoint(new PFInchUnit(0.80),
				new PFInchUnit(y)));
		encabezado.setWidth(new PFInchUnit(2));
		page.add(encabezado);
		y += .20;
		// PONEMOS EN UN SOLO OBJETO TODOS LOS DATOS DEL ENCABEZADO
		PersonaMoral pMoral = AppInstance.getPersonaMoral();

		atStr = new AttributedString(pMoral.getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		PFParagraph nombrePropietario = new PFParagraph(atStr);
		nombrePropietario.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombrePropietario.setWidth(new PFInchUnit(2.5));
		nombrePropietario.setHeight(new PFInchUnit(1));
		page.add(nombrePropietario);
		y += .13;

		String enca = pMoral.getDomicilio().getCalle() + " # "
				+ pMoral.getDomicilio().getNumeroExterior() + " Col. "
				+ pMoral.getDomicilio().getColonia() + " C.P. "
				+ pMoral.getDomicilio().getCodigoPostal() + ", "
				+ pMoral.getDomicilio().getCiudad() + " RFC " + pMoral.getRfc();
		atStr = new AttributedString(enca);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		nombrePropietario = new PFParagraph(atStr);
		nombrePropietario.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombrePropietario.setWidth(new PFInchUnit(2.5));
		nombrePropietario.setHeight(new PFInchUnit(1));
		page.add(nombrePropietario);
		y += .40;

		String sucursal = "SUC. " + AppInstance.boutique().getDescripcion();
		atStr = new AttributedString(sucursal);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		PFParagraph pSucursal = new PFParagraph(atStr);
		pSucursal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(
				y)));
		pSucursal.setWidth(new PFInchUnit(2.5));
		page.add(pSucursal);
		y += .13;

		// PONEMOS EN UN SOLO OBJETO TODOS LOS DATOS DEL ENCABEZADO
		Boutique b = AppInstance.boutique();
		enca = b.getNombrePropietario() + " " + b.getCalle() + " No."
				+ b.getNumero() + " Col. " + b.getColonia() + " C.P."
				+ b.getCp() + " RFC:" + b.getRFC() + " EXPEDIDO EN: "
				+ b.getLugarExpedicion() + " Tel. " + b.getTelefono();
		atStr = new AttributedString(enca);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		nombrePropietario = new PFParagraph(atStr);
		nombrePropietario.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombrePropietario.setWidth(new PFInchUnit(2.5));
		nombrePropietario.setHeight(new PFInchUnit(1));
		page.add(nombrePropietario);
		y += .60;

		atStr = new AttributedString(pMoral.getLeyenda());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		PFParagraph pfMoral = new PFParagraph(atStr);
		pfMoral.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pfMoral.setWidth(new PFInchUnit(2.5));
		page.add(pfMoral);

		y += .30;

		// AHORA IMPRIMIMOS LA FECHA EL FOLIO DELA NOTA
		atStr = new AttributedString("FECHA:"
				+ AppInstance.formatoLargo.format(DaoSource.getFechaActual()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		folio.setWidth(new PFInchUnit(2.8));
		page.add(folio);

		y += .13;
		// EL NOMBRE DEL VENDEDOR Y TIPO DE MOVIMIENTO:
		atStr = new AttributedString("VENDEDOR:"
				+ AppInstance.usuario().getUsuario() + " FOLIO: "
				+ vale.getId());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(2.8));
		page.add(vendedor);
		y += .13;
		// EL NUMERO DE TERMINAL
		atStr = new AttributedString("TML:"
				+ AppInstance.terminal().getNoTerminal());

		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph terminal = new PFParagraph(atStr);
		terminal.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		terminal.setWidth(new PFInchUnit(2.8));
		page.add(terminal);

		y += .26;
		// EL NOMBRE DEL VENDEDOR Y TIPO DE MOVIMIENTO:
		atStr = new AttributedString("VALE DE COMPRA");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.BOLD, 12));
		vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.5), new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(2.5));
		page.add(vendedor);

		y += .26;
		// EL NOMBRE DEL VENDEDOR Y TIPO DE MOVIMIENTO:
		atStr = new AttributedString("MONTO:  $"
				+ AppInstance.number.format(vale.getMonto()));
		atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN,
				12));
		vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.4), new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(2.5));
		page.add(vendedor);

		return y;
	}

	public static void imprimirValeDeCompra(Vale vale) {
		// TENEMOS EL VALE.... GENERAMOS LA IMPRESION
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		double y = 0.1;
		y = imprimirEncabezado(page, vale, y);
		AttributedString atStr;
		y += .26;

		String str = "AUTORIZO";
		atStr = new AttributedString(str);
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(1), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		page.add(pagare);
		y += .39;

		str = "__________________________________";
		atStr = new AttributedString(str);
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		page.add(pagare);
		y += .15;

		atStr = new AttributedString(AppInstance.usuario().getNombre());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pagare = new PFParagraph(atStr);
		pagare.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pagare.setWidth(new PFInchUnit(2.5));
		page.add(pagare);
		y += .13;

		d.addPage(page);
		// El total con letra queda pendiente..
		d.print();

	}

	/**
	 * imprimirAbonosaNota
	 * 
	 * @param listaPagos
	 *            List
	 * @param venta
	 *            Venta
	 * @param cliente
	 *            Cliente
	 * @param montoAcumulado
	 *            double
	 */
	public static void imprimirAbonosaNotaApartado(List<?> listaPagos,
			VentaApartado venta, double montoAcumulado, double saldoTotal) {
		// IMPRIMIMOS EL ENCABEZADO
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		double y = 0.1;
		AttributedString atStr;
		y = Ticket.imprimirEncabezado(page, "APARTADO", venta, y,
				new java.util.Date());
		y += 0.26;
		atStr = new AttributedString("CLIENTE: " + venta.getCliente()
				+ " DOM:  " + venta.getDomicilio() + " Tel. "
				+ venta.getTelefono()); // Nombre

		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombreCliente.setWidth(new PFInchUnit(2.5));
		nombreCliente.setHeight(new PFInchUnit(1));
		page.add(nombreCliente);
		y += .26;
		y += .26;

		atStr = new AttributedString("ABONO A NOTA FOLIO. " + venta.getId());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				12));
		PFParagraph tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .15;

		atStr = new AttributedString(
				"No. pago  Tipo                         Monto");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);
		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;
		// IMPRIMIMOS LA LISTA DE PAGOS
		Iterator<?> i = listaPagos.iterator();
		String strTipo;

		while (i.hasNext()) {
			Object[] prod = (Object[]) i.next();
			// IMPRIMIMOS CADA UNO DE LOS ELEMENTOS DE LOS PAGOS
			atStr = new AttributedString(prod[0].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			strTipo = (prod[3].toString().length() > 15) ? prod[3].toString()
					.substring(0, 15) : prod[3].toString();
			atStr = new AttributedString(strTipo);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.6), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(2));
			page.add(pago);

			atStr = new AttributedString(AppInstance.number.format(Double
					.parseDouble(prod[2].toString())));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			y += .13;
		}
		// IMPRIMIMOS EL TOTAL ABONADO
		atStr = new AttributedString("TOTAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.1), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(montoAcumulado));
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
		atStr = new AttributedString(
				com.boutique.util.Conversor.convertir(montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);

		y += .26;
		atStr = new AttributedString("SALDO ANTERIOR");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pTotal.setWidth(new PFInchUnit(2.0));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoTotal));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(saldoTotal));
		y += .13;
		atStr = new AttributedString("NUEVO SALDO");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoTotal - montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(saldoTotal));
		y += .13;

		if (venta.getStatus() == 1) {
			atStr = new AttributedString("VENTA TERMINADA");
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 12));
			pTotalLetra = new PFParagraph(atStr);
			pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pTotalLetra.setWidth(new PFInchUnit(2.5));
			pTotalLetra.setHeight(new PFInchUnit(0.25));
			page.add(pTotalLetra);

		}
		d.addPage(page);
		d.print();
		// DESPUES DEL ENCABEZADO IMPRIMIMOS LOS DATOS DEL CLIENTE
	}

	public static void imprimirTicketNotaPagoAnticipado(List<?> listaPagos,
			VentaCredito venta, Cliente cliente, double montoConDescuento,
			double saldoConDescuento) {
		// IMPRIMIMOS EL ENCABEZADO
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		double y = 0.1;
		AttributedString atStr;
		y = Ticket.imprimirEncabezado(page, "CRED/DESC", venta, y,
				new java.util.Date());
		y += 0.26;
		atStr = new AttributedString("CLIENTE: " + cliente.getNombre() + " "
				+ cliente.getApellidoPaterno() + " "
				+ cliente.getApellidoMaterno() + ". COD CLIENTE:"
				+ cliente.getId() + ". DOM: " + cliente.getCalle() + " "
				+ cliente.getNumero() + " Col. " + cliente.getColonia()
				+ "Tel." + cliente.getTelefono()); // Nombre

		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombreCliente.setWidth(new PFInchUnit(2.5));
		nombreCliente.setHeight(new PFInchUnit(1));
		page.add(nombreCliente);
		y += .26;
		y += .26;

		atStr = new AttributedString("ABONO A NOTA FOLIO. " + venta.getId());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				12));
		PFParagraph tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .15;

		atStr = new AttributedString(
				"No. pago  Tipo                         Monto");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);
		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;
		// IMPRIMIMOS LA LISTA DE PAGOS
		Iterator<?> i = listaPagos.iterator();
		String strTipo;

		while (i.hasNext()) {
			Object[] prod = (Object[]) i.next();
			// IMPRIMIMOS CADA UNO DE LOS ELEMENTOS DE LOS PAGOS
			atStr = new AttributedString(prod[0].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			strTipo = (prod[3].toString().length() > 15) ? prod[3].toString()
					.substring(0, 15) : prod[3].toString();
			atStr = new AttributedString(strTipo);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.6), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(2));
			page.add(pago);

			atStr = new AttributedString(AppInstance.number.format(Double
					.parseDouble(prod[2].toString().replaceAll(",", ""))));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			y += .13;
		}
		// IMPRIMIMOS EL TOTAL ABONADO
		atStr = new AttributedString("TOTAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.1), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(
				AppInstance.number.format(montoConDescuento));
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
		atStr = new AttributedString(
				com.boutique.util.Conversor.convertir(montoConDescuento));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);
		y += .26;

		atStr = new AttributedString("SALDO ORIGINAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pTotal.setWidth(new PFInchUnit(2.0));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(venta.saldoTotal));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.85), new PFInchUnit(y)));
		page.add(pTotal);

		y += .13;

		atStr = new AttributedString("SALDO CON DESCUENTO");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pTotal.setWidth(new PFInchUnit(2.0));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoConDescuento));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.85), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(
				AppInstance.number.format(montoConDescuento));
		y += .13;

		atStr = new AttributedString("VENTA TERMINADA");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				12));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);

		d.addPage(page);
		d.print();
		// DESPUES DEL ENCABEZADO IMPRIMIMOS LOS DATOS DEL CLIENTE
	}

	/**
	 * imprimirAbonosaNota
	 * 
	 * @param listaPagos
	 *            List
	 * @param venta
	 *            Venta
	 * @param cliente
	 *            Cliente
	 * @param montoAcumulado
	 *            double
	 */
	public static void imprimirAbonosaNota(List<?> listaPagos, Venta venta,
			Cliente cliente, double montoAcumulado, double saldoTotal) {
		// IMPRIMIMOS EL ENCABEZADO
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		double y = 0.1;
		AttributedString atStr;
		y = Ticket.imprimirEncabezado(page, "CREDITO", venta, y,
				new java.util.Date());
		y += 0.26;
		atStr = new AttributedString("CLIENTE: " + cliente.getNombre() + " "
				+ cliente.getApellidoPaterno() + " "
				+ cliente.getApellidoMaterno() + ". COD CLIENTE:"
				+ cliente.getId() + ". DOM: " + cliente.getCalle() + " "
				+ cliente.getNumero() + " Col. " + cliente.getColonia()
				+ "Tel." + cliente.getTelefono()); // Nombre

		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombreCliente.setWidth(new PFInchUnit(2.5));
		nombreCliente.setHeight(new PFInchUnit(1));
		page.add(nombreCliente);
		y += .26;
		y += .26;

		atStr = new AttributedString("ABONO A NOTA FOLIO. " + venta.getId());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				12));
		PFParagraph tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .15;

		atStr = new AttributedString(
				"No. pago  Tipo                         Monto");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);
		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;
		// IMPRIMIMOS LA LISTA DE PAGOS
		Iterator<?> i = listaPagos.iterator();
		String strTipo;

		while (i.hasNext()) {
			Object[] prod = (Object[]) i.next();
			// IMPRIMIMOS CADA UNO DE LOS ELEMENTOS DE LOS PAGOS
			atStr = new AttributedString(prod[0].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			strTipo = (prod[3].toString().length() > 15) ? prod[3].toString()
					.substring(0, 15) : prod[3].toString();
			atStr = new AttributedString(strTipo);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.6), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(2));
			page.add(pago);

			atStr = new AttributedString(AppInstance.number.format(Double
					.parseDouble(prod[2].toString().replaceAll(",", ""))));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			y += .13;
		}
		// IMPRIMIMOS EL TOTAL ABONADO
		atStr = new AttributedString("TOTAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.1), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(montoAcumulado));
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
		atStr = new AttributedString(
				com.boutique.util.Conversor.convertir(montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);
		y += .26;
		atStr = new AttributedString("SALDO ANTERIOR");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pTotal.setWidth(new PFInchUnit(2.0));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoTotal));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(saldoTotal));
		y += .13;
		atStr = new AttributedString("NUEVO SALDO");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoTotal - montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(saldoTotal));
		y += .13;

		y += .13;
		if (venta.getStatus() == 1) {
			atStr = new AttributedString("VENTA TERMINADA");
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 12));
			pTotalLetra = new PFParagraph(atStr);
			pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pTotalLetra.setWidth(new PFInchUnit(2.5));
			pTotalLetra.setHeight(new PFInchUnit(0.25));
			page.add(pTotalLetra);

		}
		d.addPage(page);
		d.print();
		// DESPUES DEL ENCABEZADO IMPRIMIMOS LOS DATOS DEL CLIENTE
	}

	/**
	 * imprimirAbonosAMasVencido
	 * 
	 * @param listaPagos
	 *            List
	 * @param venta
	 *            Venta
	 * @param cliente
	 *            Cliente
	 * @param montoAcumulado
	 *            double
	 * @param saldoTotal
	 *            double
	 * @param saldoVencido
	 *            double
	 */
	public static void imprimirAbonosAMasVencido(List<?> listaPagos,
			Venta venta, Cliente cliente, double montoAcumulado,
			double saldoTotal, double saldoVencido) {
		// IMPRIMIMOS EL ENCABEZADO
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		double y = 0.1;
		AttributedString atStr;
		y = Ticket.imprimirEncabezado(page, "CREDITO", null, y,
				new java.util.Date());
		y += 0.26;
		atStr = new AttributedString("CLIENTE: " + cliente.getNombre() + " "
				+ cliente.getApellidoPaterno() + " "
				+ cliente.getApellidoMaterno() + "COD CLIENTE:"
				+ cliente.getId() + "DOM: " + cliente.getCalle() + " "
				+ cliente.getNumero() + " Col. " + cliente.getColonia()
				+ "Tel." + cliente.getTelefono()); // Nombre

		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombreCliente.setWidth(new PFInchUnit(2.5));
		nombreCliente.setHeight(new PFInchUnit(1));
		page.add(nombreCliente);
		y += .26;
		y += .26;

		atStr = new AttributedString("ABONO A CUENTA");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				12));
		PFParagraph tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .15;

		atStr = new AttributedString(
				"Venta  Tipo                 Monto   Status");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);
		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;
		// IMPRIMIMOS LA LISTA DE PAGOS
		Iterator<?> i = listaPagos.iterator();
		String strTipo;

		while (i.hasNext()) {
			Object[] prod = (Object[]) i.next();
			// IMPRIMIMOS CADA UNO DE LOS ELEMENTOS DE LOS PAGOS
			atStr = new AttributedString(prod[1].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			strTipo = (prod[3].toString().length() > 15) ? prod[3].toString()
					.substring(0, 15) : prod[3].toString();
			atStr = new AttributedString(strTipo);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.5), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(2));
			page.add(pago);

			atStr = new AttributedString(AppInstance.number.format(Double
					.parseDouble(prod[2].toString().replaceAll(",", ""))));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);
			atStr = new AttributedString(prod[4].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(2), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(1));
			page.add(pago);

			y += .13;
		}
		// IMPRIMIMOS EL TOTAL ABONADO
		atStr = new AttributedString("TOTAL");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		PFParagraph pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.8), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
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
		atStr = new AttributedString(
				com.boutique.util.Conversor.convertir(montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 8));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2.5));
		pTotalLetra.setHeight(new PFInchUnit(0.25));
		page.add(pTotalLetra);
		y += .26;
		atStr = new AttributedString("SALDO ANTERIOR");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pTotal.setWidth(new PFInchUnit(2.0));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoTotal));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(saldoTotal));
		y += .13;
		atStr = new AttributedString("NUEVO SALDO");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString("$"
				+ AppInstance.number.format(saldoTotal - montoAcumulado));
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		pTotal = new PFParagraph(atStr);
		pTotal.setPosition(new PFPoint(new PFInchUnit(1.5), new PFInchUnit(y)));
		page.add(pTotal);
		atStr = new AttributedString(AppInstance.number.format(saldoTotal));
		y += .13;

		d.addPage(page);
		d.print();

	}

	public static void imprimirCorteDeCaja(List<?> corte, double retiro,
			List<?> salidas) {
		// IMPRIMIMOS EL ENCABEZADO
		PFDocument d = new PFDocument();
		// Agregamos una pagina
		PFPage page = new PFPage();

		// Ponemos el id
		page.setPageFormat(format);

		double y = 0.1;
		AttributedString atStr = new AttributedString("CORTE DE CAJA");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.BOLD, 10));
		PFParagraph nombreCliente = new PFParagraph(atStr);
		nombreCliente.setPosition(new PFPoint(new PFInchUnit(0.7),
				new PFInchUnit(y)));
		nombreCliente.setWidth(new PFInchUnit(2.5));
		nombreCliente.setHeight(new PFInchUnit(1));
		page.add(nombreCliente);
		y += .26;
		atStr = new AttributedString("Fecha: "
				+ AppInstance.formatoLargo.format(new java.util.Date()));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .13;
		atStr = new AttributedString("SUCURSAL: "
				+ AppInstance.boutique().getNombre());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .13;
		atStr = new AttributedString("TERMINAL: "
				+ AppInstance.terminal().getNoTerminal());
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.BOLD, 9));
		tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);
		y += .13;
		atStr = new AttributedString("Vendedor: "
				+ AppInstance.usuario().getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		tituloNota = new PFParagraph(atStr);
		tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		tituloNota.setWidth(new PFInchUnit(2.5));
		page.add(tituloNota);

		y += .26;

		// de aqui para abajo imprimimos el corte
		atStr = new AttributedString("Concepto                           Monto");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.5));
		page.add(encabezado2);
		y += .05;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;

		// IMPRIMIMOS LA LISTA DE CONCEPTOS
		Iterator<?> i = corte.iterator();

		while (i.hasNext()) {
			Object[] prod = (Object[]) i.next();
			// IMPRIMIMOS CADA UNO DE LOS ELEMENTOS DE LOS PAGOS
			atStr = new AttributedString(prod[0].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			PFParagraph pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(2));
			page.add(pago);
			atStr = new AttributedString(prod[1].toString());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 8));
			pago = new PFParagraph(atStr);
			pago.setPosition(new PFPoint(new PFInchUnit(1.8), new PFInchUnit(y)));
			pago.setWidth(new PFInchUnit(2));
			page.add(pago);

			y += .13;
		}
		y += .13;

		// IMPRIMIMOS LAS SALIDAS
		// IMPRIMIMOS LA LISTA DE CONCEPTOS
		if (salidas != null && salidas.size() > 0) {
			atStr = new AttributedString("SALIDAS DE CAJA");
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.BOLD, 9));
			tituloNota = new PFParagraph(atStr);
			tituloNota.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			tituloNota.setWidth(new PFInchUnit(2.5));
			page.add(tituloNota);
			y += .13;

			atStr = new AttributedString(
					"Concepto                           Monto");
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 10));
			encabezado2 = new PFParagraph(atStr);
			encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			encabezado2.setWidth(new PFInchUnit(2.5));
			page.add(encabezado2);
			y += .05;
			atStr = new AttributedString(
					"----------------------------------------------------");
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 10));
			encabezado23 = new PFParagraph(atStr);
			encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			encabezado23.setWidth(new PFInchUnit(2.8));
			page.add(encabezado23);
			y += .13;

			i = salidas.iterator();

			while (i.hasNext()) {
				Object[] prod = (Object[]) i.next();
				// IMPRIMIMOS CADA UNA DE LAS SALIDAS
				atStr = new AttributedString(prod[0].toString());
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 8));
				PFParagraph pago = new PFParagraph(atStr);
				pago.setPosition(new PFPoint(new PFInchUnit(0.01),
						new PFInchUnit(y)));
				pago.setWidth(new PFInchUnit(2));
				page.add(pago);
				atStr = new AttributedString(prod[1].toString());
				atStr.addAttribute(TextAttribute.FONT, new Font("modern",
						Font.PLAIN, 8));
				pago = new PFParagraph(atStr);
				pago.setPosition(new PFPoint(new PFInchUnit(1.8),
						new PFInchUnit(y)));
				pago.setWidth(new PFInchUnit(2));
				page.add(pago);

				y += .13;
			}
			y += .13;
		}

		y += .50;
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .13;

		atStr = new AttributedString("                       FIRMA");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		PFParagraph pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2));
		page.add(pTotalLetra);

		y += .40;

		atStr = new AttributedString(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .26;
		atStr = new AttributedString("                  DEPOSITO ");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2));
		page.add(pTotalLetra);
		y += .26;
		// PONEMOS EN UN SOLO OBJETO TODOS LOS DATOS DEL ENCABEZADO
		PersonaMoral pMoral = AppInstance.getPersonaMoral();

		atStr = new AttributedString(pMoral.getNombre());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		PFParagraph nombrePropietario = new PFParagraph(atStr);
		nombrePropietario.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		nombrePropietario.setWidth(new PFInchUnit(2.5));
		nombrePropietario.setHeight(new PFInchUnit(1));
		page.add(nombrePropietario);
		y += .20;

		atStr = new AttributedString("CUENTA: ");
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2));
		page.add(pTotalLetra);
		y += .20;
		atStr = new AttributedString("MONTO: $"
				+ AppInstance.number.format(retiro));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.PLAIN, 9));
		pTotalLetra = new PFParagraph(atStr);
		pTotalLetra.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		pTotalLetra.setWidth(new PFInchUnit(2));
		page.add(pTotalLetra);
		y += .13;
		d.addPage(page);
		d.print();

	}

}
