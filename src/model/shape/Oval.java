package model.shape;

/**
 * This is the Oval concrete class which extends the AbstractShape class.
 */
public class Oval extends AbstractShape {

  /**
   * Constructor of the Oval shape concrete class.
   *
   * @param name (String) the Oval shape's name
   * @param color (Color) the Oval shape's color
   * @param coordinate (Point2D) the Oval shape's coordinate
   * @param xRadius (double) the Oval shape's x radius
   * @param yRadius (double) the Oval shape's y radius
   * @throws IllegalArgumentException when any of the parameters does not match the requirement
   */
  public Oval(String name, Color color, Point2D coordinate, double xRadius, double yRadius)
      throws IllegalArgumentException {
    super(name, color, coordinate, xRadius, yRadius);
  }

  @Override
  public IShape copyShape() {
    return new Oval(this.getName(), this.getColor(), this.getCoordinate(),
        this.getScaleAttrA(), this.getScaleAttrB());
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + this.getType() + "\n"
        + "Center: " + this.getCoordinate()
        + ", X radius: " + this.getScaleAttrA() + ", Y radius: " + this.getScaleAttrB()
        + ", Color: " + this.getColor() + "\n";
  }
}
