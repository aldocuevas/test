package com.boutique.view;

import java.util.*;

import javax.swing.table.*;

public class ModelRegistroEncargo extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	private String[] columnNames = { "id", "Etiqueta", "Tipo de producto",
			"P. al público", "Fecha", "Registro" };
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
	}

}
