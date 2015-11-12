package com.boutique.view;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.collections.MapUtils;

import com.boutique.domain.inventarios.Unidad;

public class ModelProductosPorTalla extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModelProductosPorTalla() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private final boolean DEBUG = false;

	private final String[] columnNames = { "Talla", "Cantidad" };

	// public Object[][] data = new Object[0][0];
	public List<Object[]> data = new ArrayList<Object[]>();

	public final Object[] longValues = { "   ", "      " };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public void setTipoTallas(String tipoTallas, Unidad unidad) {
		System.out.println(tipoTallas);
		data.clear();
		if (unidad == null) {
			if (tipoTallas.equals("XS,CH,M,G,XL,1XL,2XL,3XL")) {
				data.add(new Object[] { "XS", 2 });
				data.add(new Object[] { "CH", 2 });
				data.add(new Object[] { "M", 2 });
				data.add(new Object[] { "G", 2 });
				data.add(new Object[] { "XL", 2 });
				data.add(new Object[] { "1XL", 2 });
				data.add(new Object[] { "2XL", 2 });
				data.add(new Object[] { "3XL", 2 });
			} else if (tipoTallas.equals("26,28,30,32,34,36,38,40,42")) {
				data.add(new Object[] { "26", 2 });
				data.add(new Object[] { "28", 2 });
				data.add(new Object[] { "30", 2 });
				data.add(new Object[] { "32", 2 });
				data.add(new Object[] { "34", 2 });
				data.add(new Object[] { "36", 2 });
				data.add(new Object[] { "38", 2 });
				data.add(new Object[] { "40", 2 });
				data.add(new Object[] { "42", 2 });

			} else if (tipoTallas.equals("26,28,29,30,31,32,33,34,36,38,40,42")) {
				data.add(new Object[] { "26", 2 });
				data.add(new Object[] { "28", 2 });
				data.add(new Object[] { "29", 2 });
				data.add(new Object[] { "30", 2 });
				data.add(new Object[] { "31", 2 });
				data.add(new Object[] { "32", 2 });
				data.add(new Object[] { "33", 2 });
				data.add(new Object[] { "34", 2 });
				data.add(new Object[] { "36", 2 });
				data.add(new Object[] { "38", 2 });
				data.add(new Object[] { "40", 2 });
				data.add(new Object[] { "42", 2 });

			}

			else if (tipoTallas.equals("32-A,32-B,34-A,34-B,34-C,34-D,36-B,36-C,36-D,38-B,38-C,38-D")) {
				data.add(new Object[] { "32-A", 2 });
				data.add(new Object[] { "32-B", 2 });
				data.add(new Object[] { "34-A", 2 });
				data.add(new Object[] { "34-B", 2 });
				data.add(new Object[] { "34-C", 2 });
				data.add(new Object[] { "34-D", 2 });
				data.add(new Object[] { "36-B", 2 });
				data.add(new Object[] { "36-C", 2 });
				data.add(new Object[] { "36-D", 2 });
				data.add(new Object[] { "38-B", 2 });
				data.add(new Object[] { "38-C", 2 });
				data.add(new Object[] { "38-D", 2 });

			}

			else if (tipoTallas.equals("22,22/,23,23/,24,24/,25,25/,26,26/,27")) {
				data.add(new Object[] { "22", 2 });
				data.add(new Object[] { "22/", 2 });
				data.add(new Object[] { "23", 2 });
				data.add(new Object[] { "23/", 2 });
				data.add(new Object[] { "24", 2 });
				data.add(new Object[] { "24/", 2 });
				data.add(new Object[] { "25", 2 });
				data.add(new Object[] { "25/", 2 });
				data.add(new Object[] { "26", 2 });
				data.add(new Object[] { "26/", 2 });
				data.add(new Object[] { "27", 2 });

			} else if (tipoTallas.equals("22,22/,23,23/,24,24/,25,25/,26,26/,27,27/,28,28/,29,29/,30,30/,31,31/")) {
				data.add(new Object[] { "22", 2 });
				data.add(new Object[] { "22/", 2 });
				data.add(new Object[] { "23", 2 });
				data.add(new Object[] { "23/", 2 });
				data.add(new Object[] { "24", 2 });
				data.add(new Object[] { "24/", 2 });
				data.add(new Object[] { "25", 2 });
				data.add(new Object[] { "25/", 2 });
				data.add(new Object[] { "26", 2 });
				data.add(new Object[] { "26/", 2 });
				data.add(new Object[] { "27", 2 });
				data.add(new Object[] { "27/", 2 });
				data.add(new Object[] { "28", 2 });
				data.add(new Object[] { "28/", 2 });
				data.add(new Object[] { "29", 2 });
				data.add(new Object[] { "29/", 2 });
				data.add(new Object[] { "30", 2 });
				data.add(new Object[] { "30/", 2 });
				data.add(new Object[] { "31", 2 });
				data.add(new Object[] { "31/", 2 });
			} else if (tipoTallas.equals("0,1,3,5,7,9,11,13,15,17,19,21,23")) {
				data.add(new Object[] { "0", 2 });
				data.add(new Object[] { "1", 2 });
				data.add(new Object[] { "3", 2 });
				data.add(new Object[] { "5", 2 });
				data.add(new Object[] { "7", 2 });
				data.add(new Object[] { "9", 2 });
				data.add(new Object[] { "11", 2 });
				data.add(new Object[] { "13", 2 });
				data.add(new Object[] { "15", 2 });
				data.add(new Object[] { "17", 2 });
				data.add(new Object[] { "19", 2 });
				data.add(new Object[] { "21", 2 });
				data.add(new Object[] { "23", 2 });

			} else if (tipoTallas.equals("UNITALLA")) {
				data.add(new Object[] { "UNITALLA", 2 });
			}
		} else {
			// Set sizes coming from Unidades
			if (MapUtils.isNotEmpty(unidad.getCantidadesPorTalla())) {
				Set<String> tallas = unidad.getCantidadesPorTalla().keySet();
				for (String talla : tallas) {
					data.add(new Object[] { talla, unidad.getCantidadesPorTalla().get(talla) });

				}
			}
		}
		this.fireTableDataChanged();

	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */
	@Override
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
	@Override
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		if (col == 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
					+ value.getClass() + ")");
		}

		data.get(row)[col] = value;
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
				System.out.print("  " + data.get(i)[j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	/* Funcion que nos permite poner los datos en la tabla.. */
	public void setData(List<Object[]> v) {
		this.data = v;
		this.fireTableDataChanged();
	}

	public void clean() {
		/*
		 * "Cantidad", "Descripcion", "Talla", "P.C.U.", "Clave 45", "Etiqueta",
		 * "+50%", "+75%", "+90%", "P.P.U", "Total"};
		 */
		this.data.clear();
		this.fireTableDataChanged();
	}

	/**
	 * addRow "Cantidad", "Descripción", "Clave", "Precio unitario", "Precio"};
	 */

	public void propertyChange(PropertyChangeEvent e) {
	}

	private void jbInit() throws Exception {
	}

	/**
	 * siHayProductos
	 * 
	 * @return boolean
	 */
	public boolean siHayProductos() {
		// Revisamos si en todos los renglones en la segunda columna al menos
		// hay un producto para dar de alta
		int contador = 0;
		for (Object obj : data) {
			Object[] row = (Object[]) obj;
			try {
				contador += (Integer) row[1];
			} catch (Exception ex) {
				return false;
			}
		}
		if (contador > 0) {
			return true;
		} else {
			return false;
		}
	}

}
