import static utils.ViewCommonUtils.showErrorDialog;

import controller.Controller;
import controller.IController;
import model.album.AlbumModel;
import view.GraphicalView;
import view.IView;
import view.WebView;

public class Main {

  static String inputFileName;
  static String outputFileName;
  static String viewType;
  static int windowWidth = 1000;
  static int windowHeight = 1000;

  public static void main(String[] args) throws Exception {
    // use helper method to initialize all the fields.
    readUserInput(args);
  }

  private static void readUserInput(String[] args) throws Exception {
    // loop through the args
    for (int i = 0; i < args.length; i++) {

      // Keyword: "-in" pair command
      if (args[i].equalsIgnoreCase("-in")) {
        try {
          inputFileName = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          // catch exception when command is not in pair
          showErrorDialog("Error. Please specify file name");
        }
      }

      // Keyword: "-v" or "-view" command
      if (args[i].equalsIgnoreCase("-v")
          || args[i].equalsIgnoreCase("-view")) {
        try {
          viewType = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          // catch exception when command is not in pair
          showErrorDialog("Error. Please specify view type");
        }
      }

      // Optional Keyword: "-out" pair command
      if (args[i].equalsIgnoreCase("-out")) {
        try {
          outputFileName = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          // catch exception when command is not in pair
          showErrorDialog("Error. Please specify output file name");
        }
      }

      // Optional Keyword: view window bound attribute
      try {
        windowWidth = Integer.parseInt(args[i]);
        windowHeight = Integer.parseInt(args[i + 1]);
      } catch (Exception e) {
        // if xMax or yMax have any exception, remain the default value
        windowWidth = 1000;
        windowHeight = 1000;
      }
    }

    // run controller based on view type.
    switch (viewType.toLowerCase()) {
      case "graphical":
        IView graphicalView = new GraphicalView("Graphical View", windowWidth, windowHeight);
        IController graphicalController = new Controller(inputFileName,
            new AlbumModel(), graphicalView);
        graphicalController.go();
        break;
      case "web":
        IView webView = new WebView(outputFileName, windowWidth, windowHeight);
        IController webController = new Controller(inputFileName, new AlbumModel(), webView);
        webController.go();
        break;
      default:
        showErrorDialog("Error. Invalid view type");
    }
  }

}
