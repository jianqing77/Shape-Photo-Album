package model.shape;

import java.util.Objects;

/**
 * This is the Point2D class which defines the coordinate of the shape in the 2D space.
 * @author Jianqing Ma
 */
public class Point2D {

  private double x;
  private double y;

  /**
   * Constructor of the Point2D class.
   *
   * @param x (double) the x coordinate
   * @param y (double) the y coordinate
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the x coordinate.
   *
   * @return (double) the x coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Get the y coordinate.
   *
   * @return (double) the y coordinate
   */
  public double getY() {
    return y;
  }

  /**
   * Set the x coordinate.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Set the y coordinate.
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ')';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point2D point2D = (Point2D) o;
    return Double.compare(point2D.getX(), getX()) == 0
        && Double.compare(point2D.getY(), getY()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}
