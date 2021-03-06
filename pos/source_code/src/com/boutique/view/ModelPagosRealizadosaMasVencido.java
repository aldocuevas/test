package com.boutique.view;

import java.beans.*;
import java.util.*;

import javax.swing.table.*;
 


public class ModelPagosRealizadosaMasVencido
    extends AbstractTableModel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private boolean DEBUG = false;

  private String[] columnNames = {
      "No.","Venta", "Monto", "Tipo", "Status Venta"};

  // public Object[][] data = new Object[0][0];
  public List<?> data = new ArrayList<Object>();




  public final Object[] longValues = {
      "   ", "      ", "      ","     "};
 
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
    //Aqui hacemos las modificaciones pertinentes a los costos de los articulos...
    /*    "Cantidad", "Descripcion", "Talla", "P.C.U.", "Boutique", "Clave 45",
      "Etiqueta",
      "+50%", "+75%", "+90%",
      "P.P.U", "Total costo", "Total venta"};
     */
    ( (Object[]) data.get(row))[col] = value;
    // data[row][col] = value;
    fireTableCellUpdated(row, col);

    //Dependiendo la columna es la operacion
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
        System.out.print("  " + ( (Object[]) data.get(i))[j]);
      }
      System.out.println();
    }
    System.out.println("--------------------------");
  }

  /* Funcion que nos permite poner los datos en la tabla..*/
  public void setPagos(Vector<?> v) {
    this.data=v;
    this.fireTableDataChanged();
  }


  /**
   * addRow      "Cantidad", "Descripción", "Clave", "Precio unitario", "Precio"};

   */


  public void propertyChange(PropertyChangeEvent e) {
  }

}
