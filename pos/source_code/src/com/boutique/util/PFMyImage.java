package com.boutique.util;

/**
   4| * Class: PFImage <p>
   5| *
   6| * Render an image on a <code>PFPage</code> object.
   7| * This class will render an image of type GIF or JPEG.
   8| * The image will be rendered using the specified height
   9| * and width
  10| *
  11| * @author Jean-Pierre Dube <jpdube@videotron.ca>
  12| * @version 1.0
  13| * @since 1.0
  14| * @see PFPrintObject
  15| */

import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.awt.image.BufferedImage;
import report.PFPrintObject;

public class PFMyImage
    extends PFPrintObject {

  //--- Private instances declarations
  private Canvas canvas = new Canvas();
  private MediaTracker mt = new MediaTracker(canvas);
  private URL imageURL = null;
  private Image image;

  /**
    37|    * Constructor: PFImage <p>
    38|    *
    39|    * Default constructor
    40|    *
    41|    */
  public PFMyImage() {

  }

  public PFMyImage(String im) {
    //image = im;

    image = Toolkit.getDefaultToolkit().getImage(im);
    mt.addImage(image, 0);

    try {
      mt.waitForID(0);
    }
    catch (InterruptedException e) {
    }

  }

  /**
    48|    * Method: setURL <p>
    49|    *
    50|    * Set the <code>URL</code> and load the image
    51|    * in an <code>Image</code> object
    52|    *
    53|    * @param parURL a value of type URL
    54|    */
  public void setURL(String parURL) {

    try {

      imageURL = new URL(parURL);
    }
    catch (MalformedURLException me) {
      me.printStackTrace();
    }

    //--- Load the image and wait for it to load
    image = Toolkit.getDefaultToolkit().getImage(imageURL);
    mt.addImage(image, 0);
    try {
      mt.waitForID(0);
    }
    catch (InterruptedException e) {
    }
  }

  /**
   * Method: print <p>
    78|    *
    79|    * Render the image.
    80|    *
    81|    * @param parG a value of type Graphics2D
    82|    */
  public void print(Graphics2D parG) {

    //--- Compute the size and position of this object
    computePositionAndSize();

    //--- Render the image on the sheet
    parG.drawImage(image,
                   (int) getDrawingOrigin().getX().getPoints(),
                   (int) getDrawingOrigin().getY().getPoints(),
                   (int) getDrawingSize().getWidth().getPoints(),
                   (int) getDrawingSize().getHeight().getPoints(),
                   canvas);

    //--- Draw childs
    printChilds(parG);
  }

  /**
   102|    * Method: getBufferedImage <p>
   103|    *
   104|    * Return the loaded image as a <code>BufferedImage</code>.
   105|    * Use this method when you need to process the image before
   106|    * it is rendered.
   107|    *
   108|    * @return a value of type BufferedImage
   109|    */
  public BufferedImage getBufferedImage() {

    return ( (BufferedImage) image);

  }

} // PFImage
