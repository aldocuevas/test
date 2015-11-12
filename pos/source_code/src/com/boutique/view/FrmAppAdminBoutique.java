package com.boutique.view;

import java.awt.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
public class FrmAppAdminBoutique
    extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton cmdClientes = new JButton();
  JButton cmdColonias = new JButton();
  JButton cmdSincronizarClientes = new JButton();
  PnlFotografia pnlFotografia1 = new PnlFotografia();

  public FrmAppAdminBoutique() {
    try {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      jbInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Component initialization.
   *
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(borderLayout1);
    setSize(new Dimension(400, 300));
    setTitle("Pily boutique Admin Module");
    cmdClientes.setText("Clientes");
    cmdClientes.addActionListener(new
                                  FrmAppAdminBoutique_cmdClientes_actionAdapter(this));
    cmdClientes.addMouseListener(new
                                 FrmAppAdminBoutique_cmdClientes_mouseAdapter(this));
    cmdColonias.setText("Colonias");
    cmdColonias.addActionListener(new
                                  FrmAppAdminBoutique_cmdColonias_actionAdapter(this));
    cmdSincronizarClientes.setEnabled(true);
    cmdSincronizarClientes.setText("Sincronizar Directorio de clientes");
    cmdSincronizarClientes.addActionListener(new
        FrmAppAdminBoutique_cmdSincronizarClientes_actionAdapter(this));
    jPanel1.add(cmdClientes);
    jPanel1.add(cmdColonias);
    jPanel1.add(cmdSincronizarClientes);
    contentPane.add(jPanel1, java.awt.BorderLayout.NORTH);
    contentPane.add(pnlFotografia1, java.awt.BorderLayout.CENTER);
  }

  public void cmdClientes_mouseClicked(MouseEvent e) {
    FrmDirectorioClientes frm = new FrmDirectorioClientes();
    frm.setExtendedState(Frame.MAXIMIZED_BOTH);
    frm.setVisible(true);
  }

  public void cmdColonias_actionPerformed(ActionEvent e) {
    FrmCatalogoColonias frm = new FrmCatalogoColonias();
    frm.setSize(480,250);
    frm.setLocationRelativeTo(this);

   // frm.setExtendedState(frm.MAXIMIZED_BOTH);
    frm.setVisible(true);

  }

  public void cmdSincronizarClientes_actionPerformed(ActionEvent e) {
      JFileChooser fl = new JFileChooser();
      fl.showOpenDialog(this);
     //  System.out.println(fl.getSelectedFile().toString());
     if (fl.getSelectedFile() != null && !"".equals(fl.getSelectedFile())) {
       pnlFotografia1.setImagen(fl.getSelectedFile().toString());
     }
     else {
       pnlFotografia1.setImagen((InputStream)null);
     }

//mostramos la foto
  }

  public void cmdClientes_actionPerformed(ActionEvent e) {

  }
}

class FrmAppAdminBoutique_cmdClientes_actionAdapter
    implements ActionListener {
  private FrmAppAdminBoutique adaptee;
  FrmAppAdminBoutique_cmdClientes_actionAdapter(FrmAppAdminBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdClientes_actionPerformed(e);
  }
}

class FrmAppAdminBoutique_cmdSincronizarClientes_actionAdapter
    implements ActionListener {
  private FrmAppAdminBoutique adaptee;
  FrmAppAdminBoutique_cmdSincronizarClientes_actionAdapter(FrmAppAdminBoutique
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdSincronizarClientes_actionPerformed(e);
  }
}

class FrmAppAdminBoutique_cmdColonias_actionAdapter
    implements ActionListener {
  private FrmAppAdminBoutique adaptee;
  FrmAppAdminBoutique_cmdColonias_actionAdapter(FrmAppAdminBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdColonias_actionPerformed(e);
  }
}

class FrmAppAdminBoutique_cmdClientes_mouseAdapter
    extends MouseAdapter {
  private FrmAppAdminBoutique adaptee;
  FrmAppAdminBoutique_cmdClientes_mouseAdapter(FrmAppAdminBoutique adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.cmdClientes_mouseClicked(e);
  }
}
