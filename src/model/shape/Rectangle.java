package model.shape;

/**
 * This is the Rectangle concrete class which extends the AbstractShape class.
 * @author Jianqing Ma
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor of the Rectangle shape concrete class.
   *
   * @param name (String) the Rectangle shape's name
   * @param color (Color) the Rectangle shape's color
   * @param coordinate (Point2D) the Rectangle shape's coordinate
   * @param width (double) the Rectangle shape's width
   * @param height (double) the Rectangle shape's height
   * @throws IllegalArgumentException when any of the parameters does not match the requirement
   */
  public Rectangle(String name, Color color, Point2D coordinate, double width, double height)
      throws IllegalArgumentException {
    super(name, color, coordinate, width, height);
  }

  @Override
  public IShape copyShape() {
    return new Rectangle(this.getName(), this.getColor(), this.getCoordinate(),
        this.getScaleAttrA(), this.getScaleAttrB());
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + this.getType() + "\n"
        + "Min corner: " + this.getCoordinate()
        + ", Width: " + this.getScaleAttrA() + ", Height: " +this.getScaleAttrB()
        + ", Color: " + this.getColor() + "\n";
  }
}
