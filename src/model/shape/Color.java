package model.shape;

import static utils.ModelCommonUtils.invalidColorValue;

import java.util.Objects;

/**
 * This is the Color class which uses Red, Green and Blue value to define a color.
 * @author Jianqing Ma
 */
public class Color {

  private double red;
  private double green;
  private double blue;

  /**
   * Constructor of the Color class.
   *
   * @param red (double) the red element
   * @param green (double) the green element
   * @param blue (double) the blue element
   * @throws IllegalArgumentException when the value is larger than 255 or smaller than 0
   */
  public Color(double red, double green, double blue) throws IllegalArgumentException {
    if (invalidColorValue(red) || invalidColorValue(green) || invalidColorValue(blue)) {
      throw new IllegalArgumentException("Color attributes values should be between 0 - 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Get the red element of the color.
   *
   * @return (double) the color's red element
   */
  public double getRed() {
    return red;
  }

  /**
   * Change the red element value.
   *
   * @param red (double) the value to be changed
   * @throws IllegalArgumentException when the target value is not in valid color range
   */
  public void setRed(double red) throws IllegalArgumentException {
    if (invalidColorValue(red)) {
      throw new IllegalArgumentException("Invalid red color value.");
    }
    this.red = red;
  }

  /**
   * Get the green element of the color.
   *
   * @return (double) the color's green element
   */
  public double getGreen() {
    return green;
  }

  /**
   * Change the green element value.
   *
   * @param green (double) the green value to be changed
   * @throws IllegalArgumentException when the target value is not in valid color range
   */
  public void setGreen(double green) throws IllegalArgumentException {
    if (invalidColorValue(green)) {
      throw new IllegalArgumentException("Invalid green color value.");
    }
    this.green = green;
  }

  /**
   * Get the blue element of the color.
   *
   * @return (double) the color's blue element
   */
  public double getBlue() {
    return blue;
  }

  /**
   * Change the blue element value.
   *
   * @param blue (double) the blue value to be changed
   * @throws IllegalArgumentException when the target value is not in valid color range
   */
  public void setBlue(double blue) throws IllegalArgumentException {
    if (invalidColorValue(blue)) {
      throw new IllegalArgumentException("Invalid blue color value.");
    }
    this.blue = blue;
  }

  @Override
  public String toString() {
    return "(" +
        "" + red + ", "
        + green + ", "
        + blue + ')';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Color color = (Color) o;
    return Double.compare(color.getRed(), getRed()) == 0
        && Double.compare(color.getGreen(), getGreen()) == 0
        && Double.compare(color.getBlue(), getBlue()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRed(), getGreen(), getBlue());
  }
}
