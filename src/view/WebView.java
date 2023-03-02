package view;

import controller.Features;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import model.shape.IShape;

/**
 * This is the WebView class implemented from IView interface. It defines the method to generate
 * SVG based HTML file.
 */
public class WebView implements IView {

  private String outputFileName;
  private int windowWidth;
  private int windowHeight;
  private StringBuilder htmlBuilder;

  private Features features;


  /**
   * Constructor of WebView.
   *
   * @param outputFileName (String) the name of the output file
   * @param windowWidth (int)
   * @param windowHeight (int)
   */
  public WebView(String outputFileName, int windowWidth, int windowHeight) {
    this.outputFileName = outputFileName;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    this.htmlBuilder = new StringBuilder();

  }

  /**
   * Helper method to set up the html overall style.
   */
  private void htmlSetUp() {
    System.out.println("---- HTML is set up ----- ");
    this.htmlBuilder.append("""
        <!DOCTYPE html>
        <html>
        <head>
            <style>
                h1 {
                    border-style: solid;
                    border-width: 10px;
                    border-color: orange;
                    background: white;
                }
                div {
                    border-style: solid;
                    border-width: 7px;
                    border-color: black;
                    background: white;
                }
            </style>
        </head>
        <body>
        <h1>Shape Album: Web View with SVG</h1>
        """);
  }


  @Override
  public void setFeatures(Features features) {
    System.out.println("Started to Set features_____ ");
    this.features = features;
    htmlSetUp();
  }

  @Override
  public void updateView(Features features) {
    htmlBuilder.append("<div>\n");
    String snapshotId = "<h3>Snapshot ID: " + features.getCurSnapshot().getId() + "</h3>\n";
    String snapshotTimeStamp = "<h3>Time Stamp: " + features.getCurSnapshot().getTimeStamp()
        + "</h3>\n";
    String snapshotDescription = "<h3>Description: " + features.getCurSnapshot().getDescription()
        + "</h3>\n";
    String svgWindowSize = "<svg width=" + this.windowWidth + " height=" + this.windowHeight
        + ">\n";
    htmlBuilder.append(snapshotId);
    htmlBuilder.append(snapshotTimeStamp);
    htmlBuilder.append(snapshotDescription);
    htmlBuilder.append(svgWindowSize);

    // get all shapes in the snapshot & show the shapes in html format
    List<IShape> curShapeList = this.features.getCurSnapshot().getCurrentShapes().values()
        .stream().toList();
    for (IShape s: curShapeList) {
      if (s.getType().equals("rectangle")) {
        String rectangleStr = "<rect id=" + s.getName()
            + " x=" + s.getCoordinate().getX()
            + " y=" + s.getCoordinate().getY()
            + " width=" + s.getScaleAttrA()
            + " height=" + s.getScaleAttrB()
            + " fill=rgb(" + s.getColor().getRed()
            + "," + s.getColor().getGreen()
            + "," + s.getColor().getBlue() + ")>"
            + "</rect>\n";
        htmlBuilder.append(rectangleStr);
      }
      if (s.getType().equals("oval")) {
        String ovalStr = "<ellipse id=" + s.getName()
            + " cx=" + s.getCoordinate().getX()
            + " cy=" + s.getCoordinate().getY()
            + " rx=" + s.getScaleAttrA()
            + " ry=" + s.getScaleAttrB()
            + " fill=rgb(" + s.getColor().getRed()
            + "," + s.getColor().getGreen()
            + "," + s.getColor().getBlue() + ")>"
            + "</ellipse>\n";
        htmlBuilder.append(ovalStr);
      }
    }
    htmlBuilder.append("</svg>\n");
    htmlBuilder.append("</div>\n");
    htmlBuilder.append("<br>\n\n");
    System.out.println("Current index: " + features.getModel().getSnapshotList()
        .indexOf(features.getCurSnapshot()));
    // update index
    this.features.setIndex(features.getModel().getSnapshotList()
        .indexOf(features.getCurSnapshot()) + 1);

    if (this.features.getIndex() < this.features.getModel().getSnapshotList().size() ){
      this.features.setCurSnapshot(features.getModel().getSnapshotList().get(features.getIndex()));
    }

  }

  @Override
  public void display() {
    // when there is still snapshot left
    while (features.getIndex() < features.getModel().getSnapshotList().size()) {
      updateView(features);
    }
    createFile();
  }

  /**
   * Helper method to create and write a file with the output file name.
   */
  private void createFile() {
    htmlBuilder.append("""
        \n</body>
        \n</html>""");
    try {
      Writer writer = Files.newBufferedWriter(Path.of(outputFileName), StandardCharsets.UTF_8);
      writer.write(htmlBuilder.toString());
      writer.close();  // Close the "test" file
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
