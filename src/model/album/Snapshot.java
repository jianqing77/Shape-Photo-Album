package model.album;

import static utils.ModelCommonUtils.invalidString;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import model.shape.IShape;

/**
 * This is the Snapshot class which defined the various elements including id, timeStamp,
 * description and current shapes to compose a Snapshot.
 */
public class Snapshot {

  private String id;
  private String timeStamp;
  private String description;
  private LinkedHashMap<String, IShape>  currentShapes;
  private String currentShapesInfo;

  /**
   * The constructor of the Snapshot class.
   *
   * @param id (String) the unique id of the snapshot
   * @param description (String) the snapshot description
   * @param currentShapes (LinkedHashMap) the current shapes to be included in the snapshot
   */
  public Snapshot(String id, String description, LinkedHashMap<String, IShape> currentShapes) throws
      IllegalArgumentException {
    if (invalidString(id) || currentShapes == null) {
      throw new IllegalArgumentException("ID, description and current shapes cannot be empty. ");
    }
    this.id = id;
    this.description = description;
    this.currentShapes = currentShapes;
    this.timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        .format(new Timestamp(System.currentTimeMillis()));
    this.currentShapesInfo = this.currentShapes.values().stream()
        .map(IShape::toString)
        .collect(Collectors.joining("\n")); // split the shapes by a new line
  }

  /**
   * Get current shape id.
   *
   * @return (String) the id of current shape
   */
  public String getId() {
    return id;
  }

  /**
   * Get current shape's time stamp.
   *
   * @return (String) the time stamp of current shape
   */
  public String getTimeStamp() {
    return timeStamp;
  }

  /**
   * Get current shape description.
   *
   * @return (String) the description of the current shape
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get current shapes in the snapshot.
   * @return (LinkedHashMap) the linked hashmap containing all the shapes
   */
  public LinkedHashMap<String, IShape> getCurrentShapes() {
    return currentShapes;
  }

  @Override
  public String toString() {
    return "Snapshot ID: " + this.id + "\n"
        + "Timestamp: " + this.timeStamp + "\n"
        + "Description: " + this.description + "\n"
        + "Shape Information: \n" + this.currentShapesInfo + "\n";
  }
}
