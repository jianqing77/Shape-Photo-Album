package model.album;

import static utils.ModelCommonUtils.invalidColorValue;
import static utils.ModelCommonUtils.invalidScaleAttribute;
import static utils.ModelCommonUtils.invalidString;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.shape.Color;
import model.shape.IShape;
import model.shape.Oval;
import model.shape.Point2D;
import model.shape.Rectangle;

/**
 * This is the AlbumModel that represent an Album which could store different shapes, take and store
 * snapshots.
 */
public class AlbumModel implements IAlbum {

  private LinkedHashMap<String, IShape> shapes;
  private LinkedHashMap<String, Snapshot> snapshots;

  /**
   * Constructor of the AlbumModel class. It initiates two linkedHashMap to store the shapes and
   * snapshots.
   */
  public AlbumModel() {
    shapes = new LinkedHashMap<>();
    snapshots = new LinkedHashMap<>();
  }

  @Override
  public LinkedHashMap<String, IShape> getShapes() {
    return shapes;
  }

  @Override
  public LinkedHashMap<String, Snapshot> getSnapshots() {
    return snapshots;
  }

  @Override
  public List<IShape> getShapeList() {
    return new ArrayList<>(shapes.values());
  }

  @Override
  public List<Snapshot> getSnapshotList() {
    return new ArrayList<>(snapshots.values());
  }

  @Override
  public List<String> getSnapshotsIDList() {
    return new ArrayList<>(snapshots.keySet());
  }

  @Override
  public void addShape(IShape newShape) throws IllegalArgumentException {
    if (newShape == null) {
      throw new IllegalArgumentException("The new shape cannot be null");
    }
    if (shapes.containsKey(newShape.getName())) {
      throw new IllegalArgumentException("The new shape name already exists in the album. "
          + "The name has to be unique. ");
    }
    shapes.put(newShape.getName(), newShape);
  }

  @Override
  public void createAndAddShape(String name, String type, Point2D coordinate, double scaleAttrA,
      double scaleAttrB, Color color) {
    // check input parameters
    if(invalidString(name) || invalidString(type) || color == null ||  coordinate == null
        || invalidScaleAttribute(scaleAttrA) || invalidScaleAttribute(scaleAttrB)) {
      throw new IllegalArgumentException("Invalid parameter to create a shape");
    }
    // add shape to album based on the type
    if (type.equalsIgnoreCase("rectangle")) {
      addShape(new Rectangle(name, color, coordinate, scaleAttrA, scaleAttrB));
    }
    if (type.equalsIgnoreCase("oval")) {
      addShape(new Oval(name, color, coordinate, scaleAttrA, scaleAttrB));
    }
  }

  /**
   * Helper function to check if the shape is currently on the album.
   *
   * @param name (String) the name of the shape to be found
   * @return (boolean) true if the shape is not found on the album, false otherwise.
   */
  private boolean shapeNotOnAlbum(String name) {
    return !shapes.containsKey(name);

  }

  @Override
  public void removeShape(String name) throws ShapeNotFoundException {
    if (shapeNotOnAlbum(name)) {
      throw new ShapeNotFoundException("Remove failed. Cannot find " + name + " in the album");
    }
    shapes.remove(name);
  }

  @Override
  public void changeColor(String name, double tarR, double tarG, double tarB)
      throws Exception {
    // if target name is not on the album
    if (shapeNotOnAlbum(name)) {
      throw new ShapeNotFoundException("Resize failed. Cannot find " + name + " in the album");
    } else if (invalidColorValue(tarR) || invalidColorValue(tarG)
        || invalidColorValue(tarB)) {
      throw new IllegalArgumentException("Change color failed. The color value is invalid");
    }
    shapes.get(name).changeColor(tarR, tarG, tarB);
  }

  @Override
  public void moveShape(String name, double tarX, double tarY) throws ShapeNotFoundException {
    if (shapeNotOnAlbum(name)) {
      throw new ShapeNotFoundException("Move failed. Cannot find " + name + " in the album");
    }
    shapes.get(name).move(tarX, tarY);
  }

  @Override
  public void resizeShape(String name, double newAttrA, double newAttrB)
      throws Exception {
    if (shapeNotOnAlbum(name)) {
      throw new ShapeNotFoundException("Resize failed. Cannot find " + name + " in the album");
    } else if (invalidScaleAttribute(newAttrA) || invalidScaleAttribute(newAttrB)) {
      throw new IllegalArgumentException("Resize failed. The resize value are invalid");
    }
    shapes.get(name).resize(newAttrA, newAttrB);
  }

  @Override
  public void takeSnapshot(String description) {
    // initiate a new snapshot ID
    String newSnapshotId = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSsss")
        .format(new Timestamp(System.currentTimeMillis()));
    // Deep copy the current shapes in the shape hashmap --> change not reflect on each other
    LinkedHashMap<String, IShape> copiedShapes = new LinkedHashMap<>();
    for (Map.Entry<String, IShape> entry: shapes.entrySet()) {
      copiedShapes.put(entry.getKey(), entry.getValue().copyShape());
    }
    // initiate a snapshot with current shapes
    Snapshot newSnapshot = new Snapshot(newSnapshotId, description, copiedShapes);
    // add the new snapshot to the snapshot hashmap
    snapshots.put(newSnapshotId, newSnapshot);
  }

  @Override
  public Snapshot getSnapshot(String id) {
    if (!snapshots.containsKey(id)) {
      throw new IllegalArgumentException("Invalid snapshot id.");
    }
    return snapshots.get(id);
  }

  @Override
  public String printSnapshotIds() {
    return "List of snapshots taken before reset: " + this.getSnapshotsIDList().toString();
  }

  @Override
  public void printSnapshotDetails() {
    System.out.println("Printing Snapshots");
    // Iterating HashMap through for loop
    for (Map.Entry<String, Snapshot> set : this.getSnapshots().entrySet()) {
      // Printing all elements of a Map
      System.out.println(set.getValue().toString());
    }
  }

  @Override
  public void resetAlbum() {
    shapes = new LinkedHashMap<>();
    snapshots = new LinkedHashMap<>();
  }
}
