package controller;

import static utils.ViewCommonUtils.showErrorDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.album.IAlbum;
import model.album.ShapeNotFoundException;
import model.shape.Color;
import model.shape.Point2D;
import view.GraphicalView;
import view.IView;
import view.WebView;

/**
 * This is the controller class which Implements the IController interface.
 *
 * @author Jianqing Ma
 */
public class Controller implements IController {

  private String fileName;
  private IAlbum model;
  private IView view;

  /**
   * Constructor of the Controller.
   *
   * @param fileName (String) the filename to be read
   * @param model (IAlbum) the model
   * @param view (IView) the view to show the result
   */
  public Controller(String fileName, IAlbum model, IView view) {
    this.fileName = fileName;
    this.model = model;
    this.view = view;
  }

  @Override
  public void go() throws Exception {
    // ----------- MODEL: tell model what to do -----------
    // ----> Read file data using scanner
    // create a File instance
    File inputFileToBeOpened = new File(this.fileName);
    // create a Scanner for the file
    Scanner input = null;

    try {
      input = new Scanner(inputFileToBeOpened);
      // System.out.println(" ------ Successfully initiate the input scanner");
    } catch (FileNotFoundException e) {
      showErrorDialog("Error. Cannot locate file to be opened");
    }

    // read argument and act correspondingly
    while (input.hasNext()) {
      switch (input.next().toLowerCase()) {
        // comments: move to next line
        case "#":
          input.nextLine();
          break;
        // shape: new shape is created and added to albumModel
        case "shape":
          String shapeName = input.next();
          String shapeType = input.next();
          Point2D shapeCoordinate = new Point2D(input.nextDouble(), input.nextDouble());
          double shapeScaleAttrA = input.nextDouble();
          double shapeScaleAttrB = input.nextDouble();
          Color shapeColor = new Color(input.nextDouble(), input.nextDouble(), input.nextDouble());

          this.model.createAndAddShape(shapeName, shapeType, shapeCoordinate, shapeScaleAttrA,
              shapeScaleAttrB,shapeColor);
          // System.out.println("Created shape: " + shapeName);
          break;
        // move -- move the current shape
        case "move":
          String tarMoveName = input.next();
          try {
            this.model.moveShape(tarMoveName, input.nextDouble(), input.nextDouble());
          } catch (ShapeNotFoundException e) {
            // e.printStackTrace();
            showErrorDialog("Failed to move shape " + tarMoveName);
          }
          // System.out.println("Moved shape: " + tarMoveName);
          break;
        // resize -- resize the shape
        case "resize":
          String tarResizeName = input.next();
          try {
            this.model.resizeShape(tarResizeName, input.nextDouble(), input.nextDouble());
          } catch (Exception e) {
            // e.printStackTrace();
            showErrorDialog("Failed to resize shape " + tarResizeName);
          }
          // System.out.println("Resized shape: " + tarResizeName);
          break;

        // color -- change the shape color
        case "color":
          String tarRecolorName = input.next();
          try {
            this.model.changeColor(tarRecolorName, input.nextDouble(), input.nextDouble(),
                input.nextDouble());
          } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Failed to change the color of shape " + tarRecolorName);
          }
          // System.out.println("Changed " + tarRecolorName + "'s color");
          break;

        // snapshot -- take snapshot of current album
        case "snapshot":
          String description = input.nextLine();
          this.model.takeSnapshot(description);
          // System.out.println("Successfully took a snapshot. Description: " + description);
          break;
      }
    }
    // ----------- View: tell view what to display -----------
    if (this.view instanceof GraphicalView){
      // graphical view: set features
      Features graphicsFeatures = new Features(this.model);
      this.view.setFeatures(graphicsFeatures);
      this.view.display();
    } else if (this.view instanceof WebView) {
      Features webFeatures = new Features(this.model);
      this.view.setFeatures(webFeatures);
      this.view.display();
    }

  }

}
