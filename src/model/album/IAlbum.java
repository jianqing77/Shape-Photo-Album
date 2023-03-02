package model.album;

import java.util.LinkedHashMap;
import java.util.List;
import model.shape.Color;
import model.shape.IShape;
import model.shape.Point2D;

/**
 * This is IAlbum Interface that defines all the methods that any concrete album class should
 * support and serves as the "contract" of reuse code for certain common services.
 * @author Jianqing Ma
 */
public interface IAlbum {

  /* *******************************************************************************************
   * Getters: Help to get all the shapes, snapshots and snapshots ids.
   ******************************************************************************************* */
  /**
   * Get all the shapes currently on the album.
   *
   * @return all the shapes currently on the album
   */
  LinkedHashMap<String, IShape> getShapes();

  /**
   * Get all the snapshots currently taken.
   *
   * @return the snapshots
   */
  LinkedHashMap<String, Snapshot> getSnapshots();

  /**
   * Helper method to be used in the test which returns all the shapes on the album as a List.
   *
   * @return (List) a List of IShape objects
   */
  List<IShape> getShapeList();

  /**
   * Helper method to be used in the test which returns all the snapshots on the album as a List.
   *
   * @return (List) a List of Snapshot objects
   */
  List<Snapshot> getSnapshotList();

  /**
   * Get all the snapshot ids. To be used in printing the snapshot id list.
   *
   * @return the snapshots ids
   */
  List<String> getSnapshotsIDList();

  /* ******************************************************************************************
   * Modify Album Contents: Add shape to & remove specific shape from the album
   ***************************************************************************************** */

  /**
   * Create a new IShape object.
   *
   * @param name (String) the shape's unique name
   * @param type (String) the shape's type
   * @param coordinate (Point2D) the coordinate of the shape
   * @param scaleAttrA (double) the shape's size attribute a
   * @param scaleAttrB (double) the shape's size attribute b
   * @param color (Color) the shape's color
   */
  void createAndAddShape(String name, String type, Point2D coordinate,
      double scaleAttrA, double scaleAttrB, Color color);

  /**
   * Add a new IShape object into the album.
   *
   * @param newShape (IShape) the shape to be added
   * @throws IllegalArgumentException when the newShape field is null
   */
  void addShape(IShape newShape) throws IllegalArgumentException;

  /**
   * Remove specific shape from the album.
   *
   * @param name (String) the name of the shape to be removed
   * @throws ShapeNotFoundException when the shape to be removed is not found in the list
   */
  void removeShape(String name) throws ShapeNotFoundException;

  /* *******************************************************************************************
   * Shape Transformation: Transform shapes on the album
   ******************************************************************************************* */
  /**
   * Change color of specific shape on the album.
   *
   * @param name (String) the unique name of the shape
   * @param tarR (double) target red value to be changed to
   * @param tarG (double) target green value to be changed to
   * @param tarB (double) target blue value to be changed to
   * @throws Exception raise ShapeNotFoundException if the input name is not on the album; raise
   *      IllegalArgumentException when any of the other target color value are invalid.
   */
  void changeColor(String name, double tarR, double tarG, double tarB)
      throws Exception;

  /**
   * Change the coordinate of specific shape on the album by identifying the shape by name.
   *
   * @param name (String) the unique name of the shape
   * @param tarX (double) target x coordinate
   * @param tarY (double) target y coordinate
   * @throws ShapeNotFoundException when the shape is not found on the album
   */
  void moveShape(String name, double tarX, double tarY) throws ShapeNotFoundException;

  /**
   * Resize the scale of specific shape on the album by identifying the shape by name.
   *
   * @param name (String) the unique name of the shape
   * @param newAttrA (double)
   * @param newAttrB (double)
   * @throws Exception raise ShapeNotFoundException if the input name is not on the album; raise
   *       IllegalArgumentException when any of the other target color value are invalid.
   */
  void resizeShape(String name, double newAttrA, double newAttrB) throws Exception;

  /* *******************************************************************************************
   * Snapshots related: Take and print Snapshots
  ******************************************************************************************** */
  /**
   * Take a snapshot of all the shapes on the album with its current state, and store the new
   * snapshot into the snapshot LinkedHashMap.
   *
   * @param description (String) a description of the snapshot
   */
  void takeSnapshot(String description);

  /**
   * Get snapshot with id.
   *
   * @param id the snapshot id
   * @return (Snapshot) the snapshot with corresponding id
   */
  Snapshot getSnapshot(String id);
  /**
   * Print all the snapshot ids in a list format.
   */
  String printSnapshotIds();

  /**
   * Print all the snapshots details.
   */
  void printSnapshotDetails();

  /**
   * Reset the album to the initial(empty) state.
   */
  void resetAlbum();

}
