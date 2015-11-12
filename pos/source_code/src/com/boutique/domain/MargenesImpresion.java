package com.boutique.domain;

public class MargenesImpresion implements java.io.Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private double top;
  private double left;
  private double bottom;
  private double right;
  private double x;
  private double y;
  public MargenesImpresion() {
  }

  public void setTop(double top) {
    this.top = top;
  }

  public void setLeft(double left) {
    this.left = left;
  }

  public void setBottom(double bottom) {
    this.bottom = bottom;
  }

  public void setRight(double right) {
    this.right = right;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getTop() {
    return top;
  }

  public double getLeft() {
    return left;
  }

  public double getBottom() {
    return bottom;
  }

  public double getRight() {
    return right;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}
