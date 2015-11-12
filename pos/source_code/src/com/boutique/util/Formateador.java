/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boutique.util;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.boutique.dao.DaoBoutique;

/**
 *
 * @author aldo
 */
public class Formateador {

	public static Map<?, ?> idToNombreBoutique;

	static {
		idToNombreBoutique = DaoBoutique.getMapLocal()[0];
	}

	public static String idBoutique(Integer idBoutique) {
		return idToNombreBoutique.get(idBoutique).toString();
	}

	public static String tipoDePago(int tipo) {
		String res = null;
		switch (tipo) {
		case 1:
			res = "Efectivo";
			break;
		case 2:
			res = "T.C.";
			break;
		case 3:
			res = "Vale";
			break;
		case 4:
			res = "Cheque";
			break;
		case 5:
			res = "Cancelacion";
			break;
		case 6:
			res = "Deposito";
		}
		return res;
	}

	public static String tipoDeVenta(int tipo) {
		String res = null;
		switch (tipo) {
		case 0:
			res = "CONTADO";
			break;
		case 1:
			res = "CREDITO";
			break;
		case 2:
			res = "APARTADO";
			break;
		}
		return res;
	}

	public static String mes(int tipo) {
		String res = null;
		switch (tipo) {
		case 1:
			res = "Enero";
			break;
		case 2:
			res = "Febrero";
			break;
		case 3:
			res = "Marzo";
			break;
		case 4:
			res = "Abril";
			break;
		case 5:
			res = "Mayo";
			break;
		case 6:
			res = "Junio";
			break;
		case 7:
			res = "Julio";
			break;
		case 8:
			res = "Agosto";
			break;
		case 9:
			res = "Septiembre";
			break;
		case 10:
			res = "Octubre";
			break;
		case 11:
			res = "Noviembre";
			break;
		case 12:
			res = "Diciembre";
			break;
		}
		return res;
	}

	public static boolean validateEmail(String email) {
		// Input the string for validation
		// String email = "xyz@.com";
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(email);

		// check whether match is found
		boolean matchFound = m.matches();

		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		if (matchFound && lastToken.length() >= 2 && email.length() - 1 != lastToken.length()) {

			// validate the country code
			return true;
		} else {
			return false;
		}
	}

}
