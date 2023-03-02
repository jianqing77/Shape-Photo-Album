package model.shape;

/**
 * This is IShape Interface that defines all the methods that any concrete shape class should
 * support and serves as the "contract" of reuse code for certain common services.
 * @author Jianqing Ma
 */

public interface IShape {

  /* *******************************************************************************************
  * Getter Methods: get the shape's different attributes
   ******************************************************************************************* */
  /**
   * Get the name of the shape.
   *
   * @return (String) the shape name
   */
  String getName();

  /**
   * Get the type of the shape.
   *
   * @return (String) the shape type
   */
  String getType();

  /**
   * Get the color of the shape.
   *
   * @return (Color) the shape color
   */
  Color getColor();

  /**
   * Get the coordinate of the shape.
   *
   * @return (Point2D) the shape coordinate
   */
  Point2D getCoordinate();

  /**
   * Get the scale A of the shape.
   *
   * @return (double) the shape scale attribute value A
   */
  double getScaleAttrA();

  /**
   * Get the scale B of the shape.
   *
   * @return (double) the shape scale attribute value B
   */
  double getScaleAttrB();

  /* *******************************************************************************************
   * Methods to Transform Shapes: change the shape's different attributes(color, position, scale)
   ******************************************************************************************* */
  /**
   * Change the shape color.
   * @param newRed the red attribute
   * @param newGreen the green attribute
   * @param newBlue the blue attribute
   */
  void changeColor(double newRed, double newGreen, double newBlue) throws IllegalArgumentException;

  /**
   * Change the location of the shape.
   *
   * @param newX the target location x coordinate
   * @param newY the target location y coordinate
   */
  void move(double newX, double newY);

  /**
   * Resize the scale of the shape object.
   *
   * @param newScaleAttrA (double) the new scale value A to be changed
   * @param newScaleAttrB (double) the new scale value B to be change
   * @throws IllegalArgumentException when any of the new scale value is negative
   */
  void resize(double newScaleAttrA, double newScaleAttrB) throws IllegalArgumentException;

  /**
   * Copy the shape and return a IShape object.
   *
   * @return (IShape) the copied shape
   */
  IShape copyShape();


}
