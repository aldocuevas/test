package com.boutique.view;

import java.beans.*;
import java.util.*;

import javax.swing.table.*;


import com.boutique.domain.*;

public class ModelDatosProductoTemporal
    extends AbstractTableModel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private boolean DEBUG = false;

  private String[] columnNames = {
      "id","Cantidad", "Descripcion","Detalle", "Etiqueta", "Fecha de compra","P.U."};

 public List<Object[]> data = new ArrayList<Object[]>();
  Object[] renglon;

  public ModelDatosProductoTemporal() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public final Object[] longValues = {
      "          ", "     ", " "};

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
  @SuppressWarnings({ "unchecked", "rawtypes" })
public Class getColumnClass(int c) {
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
  public void setData(Cliente c) {
  }

  public void clean() {
    /*    "Cantidad", "Descripcion", "Talla", "P.C.U.", "Clave 45", "Etiqueta",
         "+50%", "+75%", "+90%",
         "P.P.U", "Total"};*/
    this.data.clear();

    this.fireTableDataChanged();
  }

  private void jbInit() throws Exception {
    this.clean();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
public void setData(Vector data) {
    try {
      this.data = data;
      this.fireTableDataChanged();
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }

  public void propertyChange(PropertyChangeEvent e) {
  }

}
