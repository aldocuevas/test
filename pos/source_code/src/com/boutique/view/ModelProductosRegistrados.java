package com.boutique.view;

import java.util.*;
import javax.swing.table.*;

public class ModelProductosRegistrados extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	private String[] columnNames = { "id", "Etiqueta", "Tipo de producto",
			"Detalle", "Costo", "P. al público", "Cantidad", "Total" };
	public List<Object[]> data = new ArrayList<Object[]>();
	public int idProveedor;

	public final Object[] longValues = { "   ", "      " };

	public int getColumnCount() {

		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return ((Object[]) data.get(row))[col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		return false;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
		}

		((Object[]) data.get(row))[col] = value;
		fireTableCellUpdated(row, col);
		if (DEBUG) {
			System.out.println("New value of data:");
			printDebugData();
		}
	}

	private void printDebugData() {
		/*
		 * int numRows = getRowCount(); int numCols = getColumnCount();
		 * 
		 * for (int i = 0; i < numRows; i++) { System.out.print("    row " + i +
		 * ":"); for (int j = 0; j < numCols; j++) { System.out.print("  " +
		 * data[i][j]); } System.out.println(); }
		 * System.out.println("--------------------------");
		 */
	}

	public void agregarProductos(List<Object[]> lista) {
		this.data.addAll(lista);
		this.fireTableDataChanged();
	}

	/* Funcion que nos permite poner los datos en la tabla.. */
	public void setProductos(List<Object[]> lista) {
		this.data = lista;
		this.fireTableDataChanged();
		/*
		 * /Obtenemos los datos de la tabla unica
		 * 
		 * data = new Object[24][2]; data[0][0] = new String("Nombre");
		 * data[1][0] = new String("Domicilio"); data[2][0] = new
		 * String("Colonia"); data[3][0] = new String("Telefono"); data[4][0] =
		 * new String("Ciudad"); ; data[5][0] = new String("Estado"); data[6][0]
		 * = new String("Casa propia"); data[7][0] = new
		 * String("Fecha de nacimiento"); data[8][0] = new
		 * String("Talla Calzado"); data[9][0] = new String("Talla conjunto");
		 * data[10][0] = new String("Ocupacion"); data[11][0] = new
		 * String("Domicilio trabajo"); data[12][0] = new
		 * String("Telefono trabajo"); data[13][0] = new String("Estado civil");
		 * data[14][0] = new String("Nombre conyugue"); data[15][0] = new
		 * String("Ocupacion conyugue"); data[16][0] = new
		 * String("Domicilio trabajo conyugue"); data[17][0] = new
		 * String("Referencia comercial 1"); data[18][0] = new
		 * String("Referencia comercial 2"); data[19][0] = new
		 * String("Referencia comercial 3"); data[20][0] = new
		 * String("Referencia personal 1"); data[21][0] = new
		 * String("Tel. ref. personal 1"); data[22][0] = new
		 * String("Referencia personal 2"); data[23][0] = new
		 * String("Tel. ref. personal 2"); data[0][1] = c.getNombre();
		 * data[1][1] = c.getDomicilio(); data[2][1] = c.getColonia();
		 * data[3][1] = c.getTelefono(); data[4][1] = c.getCiudad(); data[5][1]
		 * = c.getEstado(); data[6][1] = c.getCasaPropia(); data[7][1] =
		 * c.getFechaNacimiento(); data[8][1] = c.getTallaCalzado(); data[9][1]
		 * = c.getTallaConjunto(); data[10][1] = c.getOcupacion(); data[11][1] =
		 * c.getDomicilioTrabajo(); data[12][1] = c.getTelefonoTrabajo();
		 * data[13][1] = c.getEstadoCivil(); data[14][1] =
		 * c.getNombreConyugue(); data[15][1] = c.getOcupacionConyugue();
		 * data[16][1] = c.getDomicilioTrabajoConyugue(); data[17][1] =
		 * c.getReferenciaComercial1(); data[18][1] =
		 * c.getReferenciaComercial2(); data[19][1] =
		 * c.getReferenciaComercial3(); data[20][1] =
		 * c.getReferenciaPersonal1(); data[21][1] = c.getTelefonoPersonal1();
		 * data[22][1] = c.getReferenciaPersonal2(); data[23][1] =
		 * c.getTelefonoPersonal2(); this.fireTableDataChanged();
		 */
	}

}
