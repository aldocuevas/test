package com.boutique.view;

import java.beans.*;
import java.util.*;

import javax.swing.table.*;

public class ModelPagosCreditoEstado extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	private String[] columnNames = { "No. de pago", "Fecha de pago", "Cantidad" };

	@SuppressWarnings("rawtypes")
	public List data = new java.util.ArrayList();
	double total;
	Object[] renglon;

	public ModelPagosCreditoEstado() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public final Object[] longValues = { "   ", "      ", "      " };

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
		try {
			return getValueAt(0, c).getClass();
		} catch (Exception ex) {
			return null;
		}
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
		// data[row][col] = value;
		fireTableCellUpdated(row, col);

		// Dependiendo la columna es la operacion
		if (DEBUG) {
			System.out.println("New value of data:");
			printDebugData();
		}
	}

	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();

		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + ((Object[]) data.get(i))[j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	public void clean() {
		this.data.clear();
		this.fireTableDataChanged();
	}

	private void jbInit() throws Exception {
		this.clean();
	}

	@SuppressWarnings("unchecked")
	public void addPagos(java.util.Date fechaCompra, int diasPorPeriodo,
			int numeroPagos, double cantidadPorPagar) {
		String[] row = new String[3];
		java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat(
				"dd/MMM/yyyy");
		double pagoIndividual = Math.ceil(cantidadPorPagar / numeroPagos);

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(fechaCompra);
		this.data.clear();
		for (int i = 0; i < numeroPagos; i++) {
			row = new String[3];
			cal.add(Calendar.DAY_OF_MONTH, diasPorPeriodo);
			row[0] = String.valueOf(i + 1);
			row[1] = formato.format(cal.getTime());
			row[2] = String.valueOf(pagoIndividual);
			this.data.add(row);
		}

		this.fireTableDataChanged();
	}

	public void addTotal(Double precio) {
		this.total += precio.doubleValue();
	}

	public double getTotal() {
		return total;
	}

	public void propertyChange(PropertyChangeEvent e) {
	}

}
