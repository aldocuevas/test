package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.boutique.domain.Boutique;
import com.boutique.domain.Terminal;
import com.boutique.dao.DaoBoutique;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.boutique.dao.*;
import com.boutique.engine.impl.AppInstance;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * <p>Title: boutique management</p>
 *
 * <p>Description: Sistema de administracion de boitiques</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: SESTO</p>
 *
 * @author Aldo Antonio Cuevas Alvarez
 * @version 1.0
 */
public class DlgIndicarBoutique
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  DefaultComboBoxModel modelBoutiques = new DefaultComboBoxModel();
  java.util.List<Boutique> boutiques = null;  //  @jve:decl-index=0:
  
  DefaultComboBoxModel modelTerminales = new DefaultComboBoxModel();
  java.util.List<Terminal>terminales = null;


  JPanel panel1 = new JPanel();  //  @jve:decl-index=0:visual-constraint="560,54"
  JLabel jLabel1 = new JLabel();
  JComboBox cmbBoutiques = new JComboBox();
  JComboBox cmbTerminales = new JComboBox();
  JButton cmdAceptar = new JButton();
  public DlgIndicarBoutique(Frame owner, String title, boolean modal) {
    super(owner, title, modal);
    try {
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      pack();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public DlgIndicarBoutique() {
    this(new Frame(), AppInstance.nombreNegocio, false);
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    if (DaoSource.getConnection() == null) {
      int i = JOptionPane.showConfirmDialog(null, "No hay conexion a la base de datos!, ¿Deseas configurar los parametros de conexion?",
                                            AppInstance.nombreNegocio,
                                            JOptionPane.YES_NO_CANCEL_OPTION);
      if (i == JOptionPane.YES_OPTION) {
        this.setSize(new Dimension(180, 79));
        DlgDatosConexion dlg = new DlgDatosConexion();
        dlg.pack();
        dlg.setSize(350, 250);
        dlg.setModal(true);
        dlg.setVisible(true);
      }
      else {
        System.exit(0);
      }
    }

    panel1.setLayout(null);
    panel1.setSize(new Dimension(412, 184));
    jLabel1.setText("SELECCIONE LA BOUTIQUE DESDE LA QUE ACCEDE AL SISTEMA");
    jLabel1.setBounds(new Rectangle(30, 11, 332, 19));
    cmbBoutiques.setModel(modelBoutiques);
    cmbBoutiques.setBounds(new Rectangle(85, 45, 210, 24));
    cmbBoutiques.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(java.awt.event.ActionEvent e) {
    		if(cmbBoutiques.getSelectedIndex()>=0){
    		getTerminales();
    		}
    	}
    });
    
    cmbTerminales.setModel(modelTerminales);
    cmbTerminales.setBounds(new Rectangle(85, 90, 210, 24));
    cmbTerminales.addActionListener(new java.awt.event.ActionListener() {
    	public void actionPerformed(java.awt.event.ActionEvent e) {
    		validateTerminal();
    	}
    });
    
    cmdAceptar.setBounds(new Rectangle(100, 144, 182, 27));
    cmdAceptar.setText("ACEPTAR");
    cmdAceptar.setEnabled(false);

    this.addWindowListener(new DlgIndicarBoutique_this_windowAdapter(this));
    cmdAceptar.addActionListener(new
                                 DlgIndicarBoutique_cmdAceptar_actionAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jLabel1);
    panel1.add(cmbBoutiques);
    panel1.add(cmbTerminales);
    panel1.add(cmdAceptar);
  }

  protected void validateTerminal() {
	if(cmbTerminales.getSelectedIndex()>=0){
		cmdAceptar.setEnabled(true);
	} 
	
}

protected void getTerminales() {
	 
	  try {
		  modelTerminales.removeAllElements();
		  cmdAceptar.setEnabled(false);
	      terminales = DaoBoutique.findAllTerminalesActivasByIdBoutique(boutiques.get(cmbBoutiques.getSelectedIndex()).getId());
	      for (Terminal b : terminales) {
	        modelTerminales.addElement(b.getNoTerminal());
	      }

	    }
	    catch (Exception ex) {
	      JOptionPane.showMessageDialog(null, "No hay conexion a la base de datos!",
	                                    AppInstance.nombreNegocio,
	                                    JOptionPane.ERROR_MESSAGE);
	      this.setVisible(false);

	    }
}

public void this_windowOpened(WindowEvent e) {
    try {
      boutiques = DaoBoutique.findAllActivas();
      for (Boutique b : boutiques) {
        modelBoutiques.addElement(b.getNombre());
      }

    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "No hay conexion a la base de datos!",
                                    AppInstance.nombreNegocio,
                                    JOptionPane.ERROR_MESSAGE);
      this.setVisible(false);

    }
  }

  public void cmdAceptar_actionPerformed(ActionEvent e) {
    if (this.cmbBoutiques.getSelectedIndex() >= 0 && this.cmbTerminales.getSelectedIndex()>=0) {
      Boutique b = boutiques.get(this.cmbBoutiques.getSelectedIndex());
	  Terminal terminal = terminales.get(cmbTerminales.getSelectedIndex());

      //GUARDAMOS EN LAS PROPIEDADES LA BOUTIQUE
      if (DaoSource.guardarDatosBoutique(b.getId(),terminal.getId())) {
        JOptionPane.showMessageDialog(null,
                                      "CONFIGURACION GUARDADA EXITOSAMENTE",
                                      com.boutique.engine.impl.AppInstance.
                                      nombreNegocio,
                                      JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
      }
    }
    //inventario.setBoutique(boutiques.get(idBoutique));
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"

class DlgIndicarBoutique_cmdAceptar_actionAdapter
    implements ActionListener {
  private DlgIndicarBoutique adaptee;
  DlgIndicarBoutique_cmdAceptar_actionAdapter(DlgIndicarBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}

class DlgIndicarBoutique_this_windowAdapter
    extends WindowAdapter {
  private DlgIndicarBoutique adaptee;
  DlgIndicarBoutique_this_windowAdapter(DlgIndicarBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}
