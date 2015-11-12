package com.boutique.pofs;

import com.boutique.dao.*;
import com.boutique.domain.*;
import com.boutique.impresiones.*;

public class Tester {
	public static void main(String[] args) {
		Cliente c = DaoClientesCentral.findById(3335);
		EstadoDeCuenta e = new EstadoDeCuenta(c);
		// DiarioDeEntradasEngine engine = new DiarioDeEntradasEngine();
		// engine.getDiarioACreditoCompleto();
		e.imprimirEdoCuenta();

	}
}
