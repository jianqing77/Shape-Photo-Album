package utils;

/**
 * This is the commonUtils class which defines common methods that are reused in several packages.
 */
public class ModelCommonUtils {

  /**
   * Helper method to check if the string input is null or blank.
   *
   * @param str (String) the string to be checked
   * @return (boolean) true if the string is empty, false otherwise
   */
  public static boolean invalidString(String str) {
    return (str == null || str.isBlank());
  }

  /**
   * Helper method to check if the input color value is smaller than 0 or larger than 255.
   *
   * @param value (double) the color value to be checked
   * @return (boolean) true if the color value is smaller than 0 or larger than 255, false otherwise
   */
  public static boolean invalidColorValue(double value) {
    return (value < 0 || value > 255);
  }

  /**
   * Helper method to check if the scale value is negative.
   *
   * @param attribute (double) the value to be checked
   * @return (boolean) true if the value is negative, false otherwise.
   */
  public static boolean invalidScaleAttribute(double attribute) {
    return (attribute <= 0);
  }

}
