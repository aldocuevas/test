package com.boutique.view;

import java.beans.*;
import java.util.*;

import javax.swing.table.*;

import com.boutique.domain.*;

public class ModelBusquedaActualizacion extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	private String[] columnNames = { "id", "No. de nota", "No. de articulos",
			"Sucursal", "Resultados" };
	public Vector<Object[]> data = new Vector<Object[]>();
	Object[] renglon;

	public ModelBusquedaActualizacion() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public final Object[] longValues = { "   ", "      ", "      ", "    ",
			"  " };

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
d	 * contain text ("true"/"false"), rather than a check box.
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
		fireTableCellUpdated(row, col);
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

	/* Funcion que nos permite poner los datos en la tabla.. */
	public void setData(Cliente c) {
	}

	public void clean() {
		/*
		 * "Cantidad", "Descripcion", "Talla", "P.C.U.", "Clave 45", "Etiqueta",
		 * "+50%", "+75%", "+90%", "P.P.U", "Total"};
		 */
		this.data.removeAllElements();
		renglon = new Object[5];
		renglon[0] = new String("");
		renglon[1] = new String("");
		renglon[2] = new String("");
		renglon[3] = new String("");
		renglon[4] = new String("");
		this.data.add(renglon);
		this.fireTableDataChanged();
	}

	private void jbInit() throws Exception {
		this.clean();
	}

	public void propertyChange(PropertyChangeEvent e) {
	}

	/**
	 * duplicateRow
	 * 
	 * @param i
	 *            int
	 */
	public void duplicateRow(int row) {
		// Object[][] tmp = (Object[][])this.data.clone();
		renglon = new Object[13];
		renglon = (Object[]) this.data.get(row);
		renglon = (Object[]) renglon.clone();
		this.data.insertElementAt(renglon, row);
		/*
		 * data = new Object[data.length + 1][12]; for (int i = row + 1; i <
		 * data.length; i++) { for (int j = 0; j < 12; j++) { data[i][j] =
		 * tmp[i-1][j]; } }
		 */

		this.fireTableDataChanged();
	}

	public void deleteRow(int row) {
		this.data.remove(row);
		this.fireTableDataChanged();
	}

	public void setDatos(Vector<Object[]> v) {
		this.data = v;
		this.fireTableDataChanged();
	}

	public boolean guardarCompra() {
		// Recorremos todos los datos
		/*
		 * "Cantidad", "Descripcion", "Talla", "P.C.U.", "Boutique", "Clave 45",
		 * "Etiqueta", "+50%", "+75%", "+90%", "P.P.U", "Total costo",
		 * "Total venta"}; ;
		 */
		int cantidad;
		String descripcion;
		String talla;
		double pcu;
		double ppu;
		String sucursal;
		for (int i = 0; i < this.data.size(); i++) {
			// Sacamos el renglon que evaluaremos..
			renglon = (Object[]) this.data.get(i);
			// Revisamos que los datos del renglon esten correctos..
			cantidad = Integer.parseInt(renglon[0].toString());
			descripcion = renglon[1].toString();
			talla = renglon[2].toString();
			sucursal = renglon[4].toString();
			pcu = Double.parseDouble(renglon[3].toString());
			ppu = Double.parseDouble(renglon[10].toString());
			if (!(cantidad > 0 && !descripcion.equals("") && !talla.equals("")
					&& !sucursal.equals("") && pcu > 0.0 && ppu > 0.0)) {
				javax.swing.JOptionPane.showMessageDialog(null,
						"La informacion en el renglon no. " + (i + 1)
								+ " no es valida");
				return false;
			}
		}
		// Registramos el invantario
		return true;
	}

}
