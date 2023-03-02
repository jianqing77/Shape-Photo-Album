package model.shape;

import static utils.ModelCommonUtils.invalidColorValue;
import static utils.ModelCommonUtils.invalidScaleAttribute;
import static utils.ModelCommonUtils.invalidString;

import java.util.Objects;

/**
 * This is the AbstractShape class that implements the IShape interface which defines the concrete
 * implementation of the methods defined in the interface.
 * @author Jianqing Ma
 */
public class AbstractShape implements IShape {

  private String name;
  private String type;
  private Color color;
  private Point2D coordinate;
  private double scaleAttrA;
  private double scaleAttrB;

  /**
   * Constructor of the AbstractShape class.
   *
   * @param name (String) the shape's name
   * @param color (Color) the shape's color
   * @param coordinate (Point2D) the shape's coordinate
   * @param scaleAttrA (double) the scale attribute A
   * @param scaleAttrB (double) the scale attribute B
   * @throws IllegalArgumentException when any of the parameters is in invalid format of in invalid
   *        value range
   */
  public AbstractShape(String name, Color color, Point2D coordinate, double scaleAttrA,
      double scaleAttrB) throws IllegalArgumentException {
    if (invalidString(name)) {
      throw new IllegalArgumentException("Invalid name. Name cannot be empty.");
    }
    if (invalidScaleAttribute(scaleAttrA) || invalidScaleAttribute(scaleAttrB)) {
      throw new IllegalArgumentException("Invalid scale value.");
    }
    this.name = name;
    this.color = color;
    this.coordinate = coordinate;
    this.scaleAttrA = scaleAttrA;
    this.scaleAttrB = scaleAttrB;
    this.type = this.getClass().getSimpleName().toLowerCase();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Point2D getCoordinate() {
    return coordinate;
  }

  @Override
  public double getScaleAttrA() {
    return scaleAttrA;
  }

  @Override
  public double getScaleAttrB() {
    return scaleAttrB;
  }

  @Override
  public void changeColor(double red, double green, double blue) throws IllegalArgumentException {
    if (invalidColorValue(red) || invalidColorValue(green) || invalidColorValue(blue)) {
      throw new IllegalArgumentException("Invalid color value. All should be between 1 - 255");
    }
    this.color.setRed(red);
    this.color.setGreen(green);
    this.color.setBlue(blue);
  }

  @Override
  public void move(double x, double y) {
    this.coordinate.setX(x);
    this.coordinate.setY(y);
  }

  @Override
  public void resize(double newScaleAttrA, double newScaleAttrB) throws IllegalArgumentException {
    if (invalidScaleAttribute(newScaleAttrA) || invalidScaleAttribute(newScaleAttrB)) {
      throw new IllegalArgumentException("Invalid new attributes value");
    }
    this.scaleAttrA = newScaleAttrA;
    this.scaleAttrB = newScaleAttrB;
  }

  @Override
  public IShape copyShape() {
    return new AbstractShape(this.getName(), this.getColor(), this.getCoordinate(),
        this.getScaleAttrA(), this.getScaleAttrB());
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractShape that = (AbstractShape) o;
    return Objects.equals(getName(), that.getName()) && Objects.equals(getType(),
        that.getType()) && Objects.equals(getColor(), that.getColor())
        && Objects.equals(getCoordinate(), that.getCoordinate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getType(), getColor(), getCoordinate());
  }
}
