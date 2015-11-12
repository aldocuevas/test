package com.boutique.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.boutique.domain.MargenesImpresion;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgMargenesImpresion
    extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public DlgMargenesImpresion(Frame owner, String title, boolean modal) {
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

  public DlgMargenesImpresion() {
    this(new Frame(), "Configuracion de margenes de impresion", false);
  }

  private void jbInit() throws Exception {
    this.getContentPane().setLayout(null);
    jLabel5.setText("Valor vertical inical(x):");
    jLabel5.setBounds(new Rectangle(22, 14, 119, 19));
    cmdAceptar.setBounds(new Rectangle(83, 72, 104, 20));
    cmdAceptar.setText("Aceptar");
    this.addWindowListener(new DlgMargenesImpresion_this_windowAdapter(this));
    cmdAceptar.addActionListener(new
                                DlgMargenesImpresion_cmdAceptar_actionAdapter(this));


    this.getContentPane().add(jLabel5);
    txtX.setText("");
    txtX.setBounds(new Rectangle(150, 16, 84, 18));
    txtY.setText("");
    txtY.setBounds(new Rectangle(150, 40, 84, 18));
    this.getContentPane().add(jLabel1);
    this.getContentPane().add(txtX);
    this.getContentPane().add(txtY);
    this.getContentPane().add(cmdAceptar);
    jLabel1.setText("Valor horizontal inicial(y):");
    jLabel1.setBounds(new Rectangle(21, 41, 159, 19));
  }

  JLabel jLabel5 = new JLabel();
  JLabel jLabel1 = new JLabel();
  JTextField txtY = new JTextField();
  JTextField txtX = new JTextField();
  JButton cmdAceptar = new JButton();
  public void this_windowOpened(WindowEvent e) {

    try {
      File file = new File("mi.ser");
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream oi = new ObjectInputStream(fis);
      Object obj = oi.readObject();
      MargenesImpresion mi = (MargenesImpresion) obj;
      txtX.setText(String.valueOf(mi.getX()));
      txtY.setText(String.valueOf(mi.getY()));
    }
    catch (Exception ex) {
      System.err.println(ex.toString());
    }

  }

  public void cmdAceptar_actionPerformed(ActionEvent e) {
    MargenesImpresion mi = new MargenesImpresion();
         mi.setX(Double.parseDouble(txtX.getText()));
         mi.setY(Double.parseDouble(txtY.getText()));
         try {
           FileOutputStream fo = new FileOutputStream("mi.ser");
           ObjectOutputStream oo = new ObjectOutputStream(fo);
           oo.writeObject(mi);
         }
         catch (FileNotFoundException ex) {
           System.err.println(ex.toString());
         }
         catch (IOException ex) {
           System.err.println(ex.toString());
      }
      this.setVisible(false);

  }

}

class DlgMargenesImpresion_cmdAceptar_actionAdapter
    implements ActionListener {
  private DlgMargenesImpresion adaptee;

  DlgMargenesImpresion_cmdAceptar_actionAdapter(DlgMargenesImpresion adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.cmdAceptar_actionPerformed(e);
  }
}

class DlgMargenesImpresion_this_windowAdapter
    extends WindowAdapter {
  private DlgMargenesImpresion adaptee;
  DlgMargenesImpresion_this_windowAdapter(DlgMargenesImpresion adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

