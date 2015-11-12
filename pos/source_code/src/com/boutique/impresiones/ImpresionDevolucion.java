package com.boutique.impresiones;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.List;

import report.PFDocument;
import report.PFInchUnit;
import report.PFPage;
import report.PFPageFormat;
import report.PFParagraph;
import report.PFPoint;
import report.PFSize;

import com.boutique.dao.DaoClientesCentral;
import com.boutique.domain.Boutique;
import com.boutique.domain.Cliente;
import com.boutique.domain.PersonaMoral;
import com.boutique.domain.ProductoVendido;
import com.boutique.domain.Venta;
import com.boutique.domain.VentaApartado;
import com.boutique.domain.VentaCredito;
import com.boutique.engine.impl.AppInstance;

public class ImpresionDevolucion {
	public ImpresionDevolucion() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	PFDocument d = new PFDocument();
	// Agregamos una pagina
	PFPage page = new PFPage();
	PFPageFormat format = new PFPageFormat();
	java.text.NumberFormat nmbf = java.text.NumberFormat.getNumberInstance();

	double x;
	double y;

	static double imprimirEncabezado(PFPage page, Venta venta, double ys,
			java.util.Date fecha) {
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
		enca = b.getNombrePropietario() + ". " + b.getCalle() + " No."
				+ b.getNumero() + " Col. " + b.getColonia() + " C.P."
				+ b.getCp() + " RFC: " + b.getRFC() + " EXPEDIDO EN: "
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
				+ AppInstance.formatoLargo.format(fecha) + " VENTA:"
				+ venta.getId());
		atStr.addAttribute(TextAttribute.FONT,
				new Font("Modern", Font.PLAIN, 9));
		PFParagraph folio = new PFParagraph(atStr);
		folio.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		folio.setWidth(new PFInchUnit(2.9));
		page.add(folio);
		y += .13;
		// EL NOMBRE DEL VENDEDOR Y TIPO DE MOVIMIENTO:
		String tipoMov = "";
		String nombreCliente = "";
		if (venta instanceof VentaCredito) {
			tipoMov = "DEV/CRED";
			VentaCredito vc = (VentaCredito) venta;
			Cliente cr = DaoClientesCentral.findByNombreById(vc.getIdCliente());
			nombreCliente = cr.getApellidoPaterno() + " "
					+ cr.getApellidoMaterno() + " " + cr.getNombre();
			cr = null;
		} else if (venta instanceof VentaApartado) {
			tipoMov = "DEV/AP";
			VentaApartado va = (VentaApartado) venta;
			nombreCliente = va.getCliente();
		} else if (venta instanceof Venta) {
			tipoMov = "DEV/CON";
		}
		atStr = new AttributedString("VENDEDOR:"
				+ AppInstance.usuario().getUsuario() + "      TIPO: " + tipoMov);
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

		if (!nombreCliente.equals("")) {
			y += .15;
			atStr = new AttributedString("CLIENTE:" + nombreCliente);
			atStr.addAttribute(TextAttribute.FONT, new Font("Modern",
					Font.PLAIN, 9));
			PFParagraph cliente = new PFParagraph(atStr);
			cliente.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			cliente.setWidth(new PFInchUnit(2.8));
			page.add(cliente);
		}

		return y;
	}

	public void imprimirDevolucion(Venta venta, double saldoAnterior,
			double nuevoSaldo, List<ProductoVendido> productosDevueltos,
			boolean finalizada) throws Exception {
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
		y = 0.01;
		AttributedString atStr;
		y = imprimirEncabezado(page, venta, y, new java.util.Date());

		// EL SALDO ANTERIOR
		y += 0.26;
		atStr = new AttributedString("SALDO ANTERIOR $"
				+ AppInstance.number.format(saldoAnterior));

		atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN,
				10));
		PFParagraph pSaldoAnt = new PFParagraph(atStr);
		pSaldoAnt.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(
				y)));
		pSaldoAnt.setWidth(new PFInchUnit(2.8));
		page.add(pSaldoAnt);

		// AHORA IMPRIMIMOS LOS PRODUCTOS DEVUELTOS
		y += .26;

		atStr = new AttributedString("PRODUCTOS DEVUELTOS");

		atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN,
				10));
		pSaldoAnt = new PFParagraph(atStr);
		pSaldoAnt.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(
				y)));
		pSaldoAnt.setWidth(new PFInchUnit(2.8));
		page.add(pSaldoAnt);

		// AHORA IMPRIMIMOS LOS PRODUCTOS DEVUELTOS
		y += .15;

		atStr = new AttributedString(
				"Producto                 Codigo      Precio");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado2 = new PFParagraph(atStr);
		encabezado2.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado2.setWidth(new PFInchUnit(2.8));
		y += .05;
		page.add(encabezado2);
		atStr = new AttributedString(
				"----------------------------------------------------");
		atStr.addAttribute(TextAttribute.FONT, new Font("modern", Font.PLAIN,
				10));
		PFParagraph encabezado23 = new PFParagraph(atStr);
		encabezado23.setPosition(new PFPoint(new PFInchUnit(0.01),
				new PFInchUnit(y)));
		encabezado23.setWidth(new PFInchUnit(2.8));
		page.add(encabezado23);
		y += .11;
		// imprimimos los productos
		for (ProductoVendido pv : productosDevueltos) {
			String strDesc = AppInstance.idToTipoProducto.get(
					String.valueOf(pv.getIdTipoProducto())).toString();
			if (strDesc.length() > 13) {
				strDesc = strDesc.substring(0, 12);
			}

			atStr = new AttributedString(strDesc);
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph cantidad = new PFParagraph(atStr);
			cantidad.setPosition(new PFPoint(new PFInchUnit(0.01),
					new PFInchUnit(y)));
			cantidad.setWidth(new PFInchUnit(2));
			page.add(cantidad);

			atStr = new AttributedString(pv.getEtiqueta());
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph descripcion = new PFParagraph(atStr);
			descripcion.setPosition(new PFPoint(new PFInchUnit(1.3),
					new PFInchUnit(y)));
			descripcion.setWidth(new PFInchUnit(2));
			page.add(descripcion);

			atStr = new AttributedString(AppInstance.number.format(pv
					.getPrecioFinal()));
			atStr.addAttribute(TextAttribute.FONT, new Font("modern",
					Font.PLAIN, 9));
			PFParagraph pInicial = new PFParagraph(atStr);
			pInicial.setPosition(new PFPoint(new PFInchUnit(2), new PFInchUnit(
					y)));
			pInicial.setWidth(new PFInchUnit(.6));
			page.add(pInicial);
			y += 0.13;

		}

		// AHORA EL NUEVO SALOD SI ES CERO LO PONEMOS COMO VENTA FINALIZADA.
		y += 0.26;
		atStr = new AttributedString("NUEVO SALDO $"
				+ AppInstance.number.format(nuevoSaldo));

		atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN,
				10));

		pSaldoAnt = new PFParagraph(atStr);
		pSaldoAnt.setPosition(new PFPoint(new PFInchUnit(0.01), new PFInchUnit(
				y)));
		pSaldoAnt.setWidth(new PFInchUnit(2.8));
		page.add(pSaldoAnt);
		/*
		 * if (finalizada) { y += 0.13; atStr = new
		 * AttributedString("VENTA FINALIZADA");
		 * 
		 * atStr.addAttribute(TextAttribute.FONT, new Font("Modern", Font.PLAIN,
		 * 10));
		 * 
		 * pSaldoAnt = new PFParagraph(atStr); pSaldoAnt.setPosition(new
		 * PFPoint(new PFInchUnit(0.01), new PFInchUnit(y)));
		 * pSaldoAnt.setWidth(new PFInchUnit(2.8)); page.add(pSaldoAnt); }
		 */
		d.addPage(page);
		d.print();
	}

	private void jbInit() throws Exception {
	}

}
