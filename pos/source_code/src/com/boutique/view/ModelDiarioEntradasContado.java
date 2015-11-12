package com.boutique.view;

import java.beans.*;
import java.util.*;

import javax.swing.table.*;


public class ModelDiarioEntradasContado
    extends AbstractTableModel {


  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String[] columnNames = {
      "Nota", "Concepto", "Total"};

  public  List<?> data = new  ArrayList<Object>();

  public ModelDiarioEntradasContado() {

  }

  public final Object[] longValues = {
      "   ", "      ", "      "};


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
    return ( (Object[]) data.get(row))[col];
  }

  /*
   * JTable uses this method to determine the default renderer/
   * editor for each cell.  If we didn't implement this method,
   * then the last column would contain text ("true"/"false"),
   * rather than a check box.
   */
  public Class<? extends Object> getColumnClass(int c) {
    try {
      return getValueAt(0, c).getClass();
    }
    catch (Exception ex) {
      return null;
    }
  }

  /*
   * Don't need to implement this method unless your table's
   * editable.
   */
  public boolean isCellEditable(int row, int col) {
    //Note that the data/cell address is constant,
    //no matter where the cell appears onscreen.
    return false;
  }

  /*
   * Don't need to implement this method unless your table's
   * data can change.
   */
  public void setValueAt(Object value, int row, int col) {
      ( (Object[]) data.get(row))[col] = value;
    // data[row][col] = value;
    fireTableCellUpdated(row, col);

  }



  public void propertyChange(PropertyChangeEvent e) {
  }

}
