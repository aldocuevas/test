package com.boutique.helper;

import java.math.BigDecimal;

import org.apache.commons.math.util.MathUtils;

import com.boutique.dao.DaoImpuestos;
import com.boutique.domain.Impuesto;
 
public class IvaHelper {
	public static Impuesto iva = null;

	static {
		iva = DaoImpuestos.findIva();

	}

	public static double getIvaDesglosadFromMonto(double monto) {

		return MathUtils.round((monto - (monto / iva.getPorcentajeDivisivo())),2,BigDecimal.ROUND_HALF_UP);
	}

	public static double getMontoSinIva(double monto) {
		return MathUtils.round(monto / iva.getPorcentajeDivisivo(),2,BigDecimal.ROUND_HALF_UP);
	}

	public static double getIvaFromMontoInicial(double montoSinIva) {
 		
	 	return MathUtils.round(montoSinIva * iva.getPorcentajeMultiplicativo(), 2,BigDecimal.ROUND_HALF_UP);

	}
}
