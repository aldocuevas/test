package com.boutique.view.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModelSalidas extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8599755867016486072L;

	private final String[] columnNames = { "Concepto", "Monto" };

	public List<Object[]> data = new ArrayList<Object[]>();

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return (data.get(rowIndex))[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

}
