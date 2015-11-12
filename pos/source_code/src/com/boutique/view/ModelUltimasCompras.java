package com.boutique.view;

import java.beans.*;
import java.util.*;
import javax.swing.table.*;

public class ModelUltimasCompras extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	private String[] columnNames = { "Id", "Proveedor", "Fecha", "Referencia",
			"Status" };

	private List<?> data = new ArrayList<Object>();
	Object[] renglon;

	public void setData(List<?> l) {
		this.data = l;
		this.fireTableDataChanged();

	}

	public ModelUltimasCompras() {

	}

	public final Object[] longValues = { "", "   ", "      ", "      " };
	@SuppressWarnings("unused")
	private transient Vector<?> propertyChangeListeners;

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
	public Class<? extends Object> getColumnClass(int c) {
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
		if (col > 5 && col != 11 && col != 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
		}
		// Aqui hacemos las modificaciones pertinentes a los costos de los
		// articulos...
		/*
		 * "Cantidad", "Descripcion", "Talla", "P.C.U.", "Boutique", "Clave 45",
		 * "Etiqueta", "+50%", "+75%", "+90%", "P.P.U", "Total costo",
		 * "Total venta"};
		 */
		((Object[]) data.get(row))[col] = value;
		// data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	/**
	 * addRow
	 */

	public void propertyChange(PropertyChangeEvent e) {
	}

	/**
	 * duplicateRow
	 * 
	 * @param i
	 *            int
	 */

}
