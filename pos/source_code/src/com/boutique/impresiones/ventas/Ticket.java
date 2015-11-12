package com.boutique.impresiones.ventas;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import report.PFInchUnit;
import report.PFPage;
import report.PFParagraph;
import report.PFPoint;

import com.boutique.domain.Boutique;
import com.boutique.domain.Pago;
import com.boutique.domain.PagoCheque;
import com.boutique.domain.PagoTarjetaCredito;
import com.boutique.domain.PagoTransferenciaBancaria;
import com.boutique.domain.PagoVale;
import com.boutique.domain.PersonaMoral;
import com.boutique.domain.Venta;
import com.boutique.engine.impl.AppInstance;

public class Ticket {
	public static double imprimirEncabezado(PFPage page, String tipoMov,
			Venta venta, double ys, java.util.Date fecha) {
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
		Boutique b = AppInstance.boutique();
		String sucDom = b.getCalle() + " # " + b.getNumero() + " Col. "
				+ b.getColonia() + " C.P." + b.getCp() + ","
				+ b.getLugarExpedicion();
		atStr = new AttributedString(sucDom);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 7));
		PFParagraph pSucDom = new PFParagraph(atStr);
		pSucDom.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		pSucDom.setWidth(new PFInchUnit(2.5));
		page.add(pSucDom);

		y += .30;

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
				+ AppInstance.formatoLargo.format(fecha)
				+ ((venta != null) ? " FOLIO:" + venta.getId() : ""));
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		folio.setWidth(new PFInchUnit(2.9));
		page.add(folio);
		y += .13;
		// EL NOMBRE DEL VENDEDOR Y TIPO DE MOVIMIENTO:
		atStr = new AttributedString("VENDEDOR:"
				+ AppInstance.usuario().getUsuario() + "      TIPO: " + tipoMov);
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph vendedor = new PFParagraph(atStr);
		vendedor.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		vendedor.setWidth(new PFInchUnit(2.8));
		page.add(vendedor);

		// Numero de terminal
		y += .13;
		atStr = new AttributedString("TML:"
				+ AppInstance.terminal().getNoTerminal());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph terminal = new PFParagraph(atStr);
		terminal.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		terminal.setWidth(new PFInchUnit(2.8));
		page.add(terminal);
		return y;
	}

	public static double imprimirPagos(PFPage page, java.util.List<Pago> pagos,
			int tipoVenta, double ys) {
		double y = ys;

		AttributedString atStr = null;
		if (tipoVenta == 1) {
			atStr = new AttributedString("Pagos");
		} else {
			atStr = new AttributedString("Anticipo");
		}
		atStr.addAttribute(TextAttribute.FONT,
				new Font("modern", Font.BOLD, 10));
		PFParagraph t = new PFParagraph(atStr);
		t.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		t.setWidth(new PFInchUnit(4.5));
		page.add(t);
		y = y + 0.2;
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
			pagoRef.setWidth(new PFInchUnit(4.5));
			page.add(pagoRef);
			atStr = new AttributedString("-"
					+ AppInstance.number.format(pago.getMonto()));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph pagoMonto = new PFParagraph(atStr);
			pagoMonto.setPosition(new PFPoint(new PFInchUnit(1.8),
					new PFInchUnit(y)));
			pagoMonto.setWidth(new PFInchUnit(1.3));
			page.add(pagoMonto);
			y += 0.13;
		}
		return y;
	}
}
