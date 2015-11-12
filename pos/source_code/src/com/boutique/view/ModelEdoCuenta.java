package com.boutique.view;

import javax.swing.table.*;

public class ModelEdoCuenta extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;

	private String[] columnNames = { "No.", "Sucursal", "Monto", "Abonado",
			"S. Actual", "Vencido", "Vencimiento" };
	@SuppressWarnings("rawtypes")
	public java.util.Vector data = new java.util.Vector();
	public int idProveedor;

	public ModelEdoCuenta() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public final Object[] longValues = {
			"Trabajo del conyugue                                      ",
			"      " };

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
		if (col < 1) {
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
				// System.out.print("  " + data[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	/* Funcion que nos permite poner los datos en la tabla.. */
	public void setData(com.boutique.domain.Cliente c) {

		/*
		 * cliente = new Clientes(this.bdCfg); Proveedor proveedor = new
		 * Proveedor(this.bdCfg); proveedor.getProveedor(idProveedor);
		 * this.arrClientes = cliente.getCliente(); data = new
		 * Object[this.arrClientes.length][2]; for (int i = 0; i <
		 * this.arrCategorias.length; i++) { data[i][0] =
		 * this.arrCategorias[i][1]; if
		 * (proveedor.isCategoria(Integer.parseInt(this.arrCategorias[i][0])))
		 * //Si esta la categoria data[i][1] = new Boolean(true); else
		 * data[i][1] = new Boolean(false);
		 * 
		 * }
		 */
		this.fireTableDataChanged();
	}

	@SuppressWarnings("rawtypes")
	public void clean() {
		this.data = new java.util.Vector();
		this.fireTableDataChanged();

	}

	private void jbInit() throws Exception {
	}
}
