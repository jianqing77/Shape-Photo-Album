package model.album;

/**
 * This is the self-designed ShapeNotFoundException that will raise when the shape to be found
 * is not on the album.
 * @author Jianqing Ma
 */
public class ShapeNotFoundException extends Exception {

  /**
   * Constructor of the ShapeNotFoundException.
   *
   * @param msg (String) the message to be passed to the client.
   */
  public ShapeNotFoundException(String msg) {
    super(msg);
  }

}
