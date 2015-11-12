package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import com.boutique.dao.DaoSource;
import com.boutique.engine.impl.AppInstance;

public class DlgRespaldoBD
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JButton cmdExaminar = new JButton();
  JButton cmdRespaldar = new JButton();
  JFileChooser jf = null;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea txtMsg = new JTextArea();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton cmdSalir = new JButton();
  JPanel jPanel3 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JLabel jLabel2 = new JLabel();
  public DlgRespaldoBD(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      jbInit();
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgRespaldoBD() {
    this(new Frame(), "DlgRespaldoBD", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    jLabel1.setText("Selecciona la carpeta de destino:");
    cmdExaminar.setText("Examinar...");
    cmdExaminar.addActionListener(new DlgRespaldoBD_cmdExaminar_actionAdapter(this));
    cmdRespaldar.setEnabled(false);
    cmdRespaldar.setText("Respaldar");
    cmdRespaldar.addActionListener(new DlgRespaldoBD_cmdRespaldar_actionAdapter(this));
    txtMsg.setText("");
    txtMsg.setWrapStyleWord(true);
    jPanel2.setLayout(borderLayout2);
    cmdSalir.setText("SALIR");
    cmdSalir.addActionListener(new DlgRespaldoBD_cmdSalir_actionAdapter(this));
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.
                                              HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.setTitle("Respaldo de datos");
    jPanel3.setLayout(borderLayout3);
    jLabel2.setText("Consola:");
    getContentPane().add(panel1);
    jPanel1.add(jLabel1);
    jPanel1.add(cmdExaminar);
    jPanel1.add(cmdRespaldar);
    jScrollPane1.getViewport().add(txtMsg);
    this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
    jPanel2.add(cmdSalir, java.awt.BorderLayout.EAST);
    jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jPanel3.add(jLabel2, java.awt.BorderLayout.NORTH);
    panel1.add(jPanel1, java.awt.BorderLayout.NORTH);
    panel1.add(jPanel3, java.awt.BorderLayout.CENTER);
  }

  public void cmdExaminar_actionPerformed(ActionEvent e) {
    jf = new JFileChooser();
    jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    jf.showDialog(this, "Seleccionar directorio");
    if (jf.getSelectedFile() != null) {
      //TENEMOS LA CARPETA, EL ARCHIVO SE CREARIA CON EL NOMBRE DE LA  BD Y EL TIMESTAMP.SQL

      this.txtMsg.setText(this.txtMsg.getText() + "Carpeta seleccionada:\n\r" +
                          jf.getSelectedFile().getAbsolutePath() + "\n\r");
this.cmdRespaldar.setEnabled(true);
    }
  }

  public void cmdRespaldar_actionPerformed(ActionEvent e) {
    this.setCursor(AppInstance.waitCursor);
    if (jf != null) {
      this.cmdSalir.setEnabled(false);
      //GENERAMOS EL ARCHIVO
      String separador = System.getProperty("file.separator");
      Calendar cal = Calendar.getInstance();
      cal.setTime(DaoSource.getFechaActual());
      String archivo = jf.getSelectedFile().getAbsolutePath() + separador +
          DaoSource.ip + "_" + DaoSource.database + "_" +
          cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) +
          cal.get(Calendar.DAY_OF_MONTH) + cal.get(Calendar.HOUR) +
          cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND) +
          ".sql";

      txtMsg.setText(txtMsg.getText() + "Generando el archivo:\n\r" + archivo
                     + "\n\r");
      //EJECUTAMOS EL RESPALDO..
      try {
        if (DaoSource.respaldarBD(archivo)) {
          DaoSource.registrarRespaldo();
          txtMsg.setText(txtMsg.getText() +
                         "Respaldo generado satisfactoriamente en:\n\r" + archivo);
                this.setCursor(AppInstance.defCursor);
          JOptionPane.showMessageDialog(this,
                                        "Respaldo realizado satisfactoriamente",
                                        AppInstance.nombreNegocio,
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {
          txtMsg.setText(txtMsg.getText() +
                         "Error al generar el respaldo..");
                this.setCursor(AppInstance.defCursor);
          JOptionPane.showMessageDialog(this,
                                        "Error al generar el respaldo",
                                        AppInstance.nombreNegocio,
                                        JOptionPane.INFORMATION_MESSAGE);
        }
      }
      catch (Exception ex) {

        txtMsg.setText(txtMsg.getText() +
                       " Error al generar el respaldo:\n\r" + ex.getMessage());
              this.setCursor(AppInstance.defCursor);
        JOptionPane.showMessageDialog(this,
                                      "Error al generar el respaldo, vea el error en la consola",
                                      AppInstance.nombreNegocio,
                                      JOptionPane.ERROR_MESSAGE);

      }
      this.cmdSalir.setEnabled(true);
      this.setCursor(AppInstance.defCursor);
    }
  }

  public void cmdSalir_actionPerformed(ActionEvent e) {
this.setVisible(false);
  }
}

class DlgRespaldoBD_cmdSalir_actionAdapter
    implements ActionListener {
  private DlgRespaldoBD adaptee;
  DlgRespaldoBD_cmdSalir_actionAdapter(DlgRespaldoBD adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdSalir_actionPerformed(e);
  }
}

class DlgRespaldoBD_cmdRespaldar_actionAdapter
    implements ActionListener {
  private DlgRespaldoBD adaptee;
  DlgRespaldoBD_cmdRespaldar_actionAdapter(DlgRespaldoBD adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdRespaldar_actionPerformed(e);
  }
}

class DlgRespaldoBD_cmdExaminar_actionAdapter
    implements ActionListener {
  private DlgRespaldoBD adaptee;
  DlgRespaldoBD_cmdExaminar_actionAdapter(DlgRespaldoBD adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {

    adaptee.cmdExaminar_actionPerformed(e);
  }
}
