package com.boutique.util;

import javax.swing.*;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.*;

public class Example {

  public void usingBarbecueAsSwingComponent() {
    JPanel panel = new JPanel();

    Barcode barcode3 = null;

    try {
      // barcode = BarcodeFactory.createCode128B("14783CH");
      // barcode = BarcodeFactory.create2of7("14783CH");
      // barcode = BarcodeFactory.create3of9("14783CH", true);
      //  barcode = BarcodeFactory.create3of9("14783CH", false);
  /*    barcode = BarcodeFactory.createCode128("14783CH");
      barcode1 = BarcodeFactory.createCode128A("14783CH");
      barcode2 = BarcodeFactory.createCode128B("14783CH");
      barcode3 = BarcodeFactory.createCode128C("14783CH");
      barcode3 = BarcodeFactory.createCode39("14783CH", false);
      barcode3 = BarcodeFactory.createEAN128("14783CH");
      barcode3 = BarcodeFactory.createGlobalTradeItemNumber("14783CH");
      barcode3 = BarcodeFactory.createUSPS("14783CH");
  */    barcode3 = BarcodeFactory.createInt2of5("14783CH");

      //  barcode.setBarWidth(1);
    }
    catch (BarcodeException e) {
      // Error handling
    }

    /* Because Barcode extends Component, you can use it just like any other
     * Swing component. In this case, we can add it straight into a panel
     * and it will be drawn and layed out according to the layout of the panel.
     */
    /*  fl.addLayoutComponent("",barcode);
          fl.addLayoutComponent("",barcode1);
              fl.addLayoutComponent("",barcode2);
                  fl.addLayoutComponent("",barcode3);*/
//    barcode3.setFont(new Font("Arial", Font.PLAIN, 10));
//    panel.add(barcode3);
///  panel.add(barcode2);
//    panel.add(barcode3);


    try {
      for (int i = 0; i < 10; i++) {
        File f = new File(i + "size.jpg");
        switch (i) {

          case 0:
            barcode3 = BarcodeFactory.createCode128("23291478325/");
            break;
          case 1:
            barcode3 = BarcodeFactory.createCode128A("91478325/");
            break;
          case 2:
            barcode3 = BarcodeFactory.createCode128B("91478325/");
            break;
          case 3:
            barcode3 = BarcodeFactory.createCode128C("91478325/");
            break;
          case 4:
            barcode3 = BarcodeFactory.createCode39("91478325/", false);
            break;
          case 5:
            barcode3 = BarcodeFactory.createEAN128("91478325/");
            break;
          case 6:
            barcode3 = BarcodeFactory.createGlobalTradeItemNumber("91478325/");
            break;
          case 7:
            barcode3 = BarcodeFactory.createUSPS("91478325/");
            break;
          case 8:
            barcode3 = BarcodeFactory.createInt2of5("91478325/");
            break;
        }
        barcode3.setDrawingText(false);
//        barcode3.setBounds(0,0,100,100);
//barcode3.setBarHeight(20);
        barcode3.setBarWidth(1);

        // Let the barcode image handler do the hard work
        BarcodeImageHandler.saveJPEG(barcode3, f);
      }
    }
    catch (Exception e) {
      // Error handling here
      e.printStackTrace();
    }

    JFrame frm = new JFrame();
    frm.add(panel);
    frm.setVisible(true);

  }

  public void drawingBarcodeDirectToGraphics() throws BarcodeException,
      OutputException {
    // Always get a Barcode from the BarcodeFactory
    Barcode barcode = BarcodeFactory.createCode128B("My Barcode");

    // We are created an image from scratch here, but for printing in Java, your
    // print renderer should have a Graphics internally anyway
    BufferedImage image = new BufferedImage(500, 500,
                                            BufferedImage.TYPE_BYTE_GRAY);
    // We need to cast the Graphics from the Image to a Graphics2D - this is OK
    Graphics2D g = (Graphics2D) image.getGraphics();

    // Barcode supports a direct draw method to Graphics2D that lets you position the
    // barcode on the canvas
    barcode.draw(g, 10, 56);
  }

  public void outputtingBarcodeAsPNG() throws BarcodeException {
    // get a Barcode from the BarcodeFactory
    Barcode barcode = BarcodeFactory.createCode128B("My Barcode");

    try {
      File f = new File("mybarcode.png");

      // Let the barcode image handler do the hard work
      BarcodeImageHandler.savePNG(barcode, f);
    }
    catch (Exception e) {
      // Error handling here
    }
  }

  public static void main(String args[]) {
    new Example().usingBarbecueAsSwingComponent();
  }

}
