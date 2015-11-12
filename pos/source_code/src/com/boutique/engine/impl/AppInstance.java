package com.boutique.engine.impl;

import com.boutique.domain.*;
import com.boutique.dao.DaoBoutique;
import java.util.*;
import java.text.NumberFormat;
import com.boutique.dao.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class AppInstance {
	
 public static DefaultComboBoxModel modelBancos;

	public static ImageIcon nodisponible = new ImageIcon(
			com.boutique.view.FrmAppBoutique.class
					.getResource("img/nodisponible.jpg"));
	public static NumberFormat real = NumberFormat
			.getNumberInstance(Locale.ENGLISH);
	public static java.awt.Cursor waitCursor = new java.awt.Cursor(
			java.awt.Cursor.WAIT_CURSOR);
	public static java.awt.Cursor overCursor = new java.awt.Cursor(
			java.awt.Cursor.HAND_CURSOR);
	public static java.awt.Cursor defCursor = new java.awt.Cursor(
			java.awt.Cursor.DEFAULT_CURSOR);
	private static Boutique boutique;
	private static Terminal terminal;
	
	private static PersonaMoral personaMoral;

	public static String nombreNegocio = "PILY BOUTIQUE";
	private static Usuario usuario;
	private static boolean networking;
	private static List<String> bancos;
	private static Map<String, String> promocionesBancarias;
	public static java.text.NumberFormat number;
	public static Map<String, String> idToTipoProducto;
	public static Preferencias preferencias;
	public static Map<?, ?> idToNombreBoutique;
	public static Map<?, ?> nombreBoutiqueToId;
	public static Map<Integer, Leyenda> idToTipoNotaLeyenda;
	public static Map<?, ?> idToNombreUsuario;
	public static Map<String, Integer> marcas2id;
	public static Map<String, Integer> descripciones2id;
	public static Map<String, Integer> colores2id;
	public static Map<Integer, String> id2marcas;
	public static Map<Integer, String> id2descripciones;
	public static Map<Integer, String> id2colores;
	public static InterfaceGrafica interfaceGrafica;
 	
	public static java.text.SimpleDateFormat formatoCorto = new java.text.SimpleDateFormat(
			"dd/MMM/yyyy");
	public static java.text.SimpleDateFormat formatoLargo = new java.text.SimpleDateFormat(
			"dd/MMM/yyyy HH:mm:ss");
	public static DescPagoAnticipado promocionDesc = null;

	@SuppressWarnings("rawtypes")
	public static void iniciarApp() {
		// Get Global preferences
		preferencias = DaoPreferencias.findPreferencias();
		if (DaoSource.getConnection() == null) {
			JOptionPane.showMessageDialog(null,
					"No hay conexion a la base de datos!", nombreNegocio,
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		DaoBoutique.getMatrizInicial();
		idToTipoProducto = DaoTipoProductos.getMapLocal();
		// number =
		// NumberFormat.getCurrencyInstance(java.util.Locale.getDefault());
		number = NumberFormat.getNumberInstance(java.util.Locale.ENGLISH);
		number.setMaximumFractionDigits(2);
		number.setMinimumFractionDigits(2);
		real.setGroupingUsed(false);
		real.setMaximumFractionDigits(2);
		real.setMinimumFractionDigits(2);
		// boutique = DaoBoutique.getBoutiqueLocal();
		bancos = new ArrayList<String>();
		bancos.add("BANAMEX");
		bancos.add("BBVA BANCOMER");
		bancos.add("SANTANDER");
		bancos.add("HSBC");
		bancos.add("BANORTE");
		bancos.add("AMERICAN EXPRESS");
		bancos.add("SCOTIABANK INVERLAT");
		bancos.add("AZTECA");
		bancos.add("INBURSA");
		bancos.add("OTRO");

		promocionesBancarias = new TreeMap<String, String>();
		promocionesBancarias.put("0 Ninguna", "0");
		promocionesBancarias.put("3 meses sin intereses", "3");
		promocionesBancarias.put("6 meses sin intereses", "6");
		Map[] mapas = DaoBoutique.getMapLocal();
		idToNombreBoutique = mapas[0];
		nombreBoutiqueToId = mapas[1];
		idToNombreUsuario = DaoBoutique.getNombresUsuarios();
		promocionDesc = DaoVentas.findPromocionDescuentoPagoAnticipado();
		marcas2id = new TreeMap<String, Integer>();
		id2marcas = new TreeMap<Integer, String>();
		descripciones2id = new TreeMap<String, Integer>();
		id2descripciones = new TreeMap<Integer, String>();
		colores2id = new TreeMap<String, Integer>();
		id2colores = new TreeMap<Integer, String>();
		List<MarcaDescripcion> mm = DaoInventarios.findMarcas();
		for (MarcaDescripcion marca : mm) {
			marcas2id.put(marca.getMarcaDescripcion(), marca.getId());
			id2marcas.put(marca.getId(), marca.getMarcaDescripcion());
		}
		mm = DaoInventarios.findDescripciones();
		for (MarcaDescripcion marca : mm) {
			descripciones2id.put(marca.getMarcaDescripcion(), marca.getId());
			id2descripciones.put(marca.getId(), marca.getMarcaDescripcion());
		}
		mm = DaoInventarios.findColores();
		for (MarcaDescripcion marca : mm) {
			colores2id.put(marca.getMarcaDescripcion(), marca.getId());
			id2colores.put(marca.getId(), marca.getMarcaDescripcion());
		}
		modelBancos = new DefaultComboBoxModel(AppInstance
				.getBancos().toArray());
 	}

	public static Boutique boutique() {
		return boutique;
	}

	public static Terminal terminal() {
		return terminal;
	}

	public static List<String> getBancos() {
		return bancos;
	}

	public static Map<String, String> getPromocionesBancarias() {
		return promocionesBancarias;
	}

	public static Usuario usuario() {
		return usuario;
	}

	public AppInstance() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static Usuario iniciarSesion(String username, String pass) {
		usuario = DaoBoutique.findUsuarioByUsuarioAndContrasena(username, pass);
		if (usuario != null) {
			if (DaoSource.idBoutique == 0) {
				DaoSource.idBoutique = 1;
			}
			boutique = DaoBoutique.findById(DaoSource.idBoutique);
			personaMoral = DaoPersonalMoral.findPersonaMoral();
			idToTipoNotaLeyenda = DaoLeyendas.findNotasLeyendas(boutique
					.getId());
			if (AppInstance.interfaceGrafica.equals(InterfaceGrafica.ADMIN)) {
				terminal = DaoBoutique.findLegacyTerminal();

			} else {
				terminal = DaoBoutique.findTerminalById(DaoSource.idTerminal);
			}

		}
		return usuario;
	}

	public static void setNetworking(boolean value) {
		networking = value;
	}

	public static boolean getNetworking() {
		return networking;
	}

	private void jbInit() throws Exception {

	}

	public static void setPersonaMoral(PersonaMoral personaMoral) {
		AppInstance.personaMoral = personaMoral;
	}

	public static PersonaMoral getPersonaMoral() {
		return personaMoral;
	}

}
