package com.boutique.domain;

import java.util.Map;
import java.util.TreeMap;

public class TipoTallas {
	static Map<String, String[]> tipoTallas = new TreeMap<String, String[]>();

	static {
		tipoTallas.put("XS,CH,M,G,XL,1XL,2XL,3XL", new String[] { "XS", "CH", "M", "G", "XL", "1XL", "2XL", "3XL" });
		tipoTallas.put("0,1,3,5,7,9,11,13,15,17,19,21,23",
				new String[] { "0", "1", "3", "5", "7", "9", "11", "13", "15", "17", "19", "21", "23" });
		tipoTallas.put("26,28,30,32,34,36,38,40,42",
				new String[] { "26", "28", "30", "32", "34", "36", "38", "40", "42" });
		tipoTallas.put("26,28,29,30,31,32,33,34,36,38,40,42",
				new String[] { "26", "28", "29", "30", "31", "32", "33", "34", "36", "38", "40", "42" });
		tipoTallas.put("22,22/,23,23/,24,24/,25,25/,26,26/,27",
				new String[] { "22", "22/", "23", "23/", "24", "24/", "25", "25/", "26", "26/", "27" });
		tipoTallas.put("22,22/,23,23/,24,24/,25,25/,26,26/,27,27/,28,28/,29,29/,30,30/,31,31/",
				new String[] { "22", "22/", "23", "23/", "24", "24/", "25", "25/", "26", "26/", "27", "27/", "28",
						"28/", "29", "29/", "30", "31", "31/" });

		tipoTallas.put("UNITALLA", new String[] { "UNITALLA" });
		tipoTallas.put("32-A,32-B,34-A,34-B,34-C,34-D,36-B,36-C,36-D,38-B,38-C,38-D", new String[] { "32-A", "32-B",
				"34-A", "34-B", "34-C", "34-D", "36-B", "36-C", "36-D", "38-B", "38-C", "38-D" });
	}

	public static Map<String, String[]> getTipoTallas() {
		return tipoTallas;
	}
}
