package com.boutique.view;

import java.beans.*;
import java.util.*;

import javax.swing.table.*;
 
public class ModelVentaArticulos
    extends AbstractTableModel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private boolean DEBUG = false;

  private String[] columnNames = { "Cantidad", "Descripción", "Etiqueta", "Precio inicial","Descuento","Precio Final"};

  public List<?> data = new ArrayList<Object>();

  public ModelVentaArticulos() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public final Object[] longValues = {
      "   ", "      ", "      ", "          ", "     ","    "};

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
    if (DEBUG) {
      System.out.println("Setting value at " + row + "," + col
                         + " to " + value
                         + " (an instance of "
                         + value.getClass() + ")");
    }

    ( (Object[]) data.get(row))[col] = value;
    // data[row][col] = value;
    fireTableCellUpdated(row, col);


  }

  private void jbInit() throws Exception {
  }

  public void propertyChange(PropertyChangeEvent e) {
  }

}
