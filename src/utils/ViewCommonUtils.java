package utils;

import javax.swing.JOptionPane;

public class ViewCommonUtils {

  public static void showErrorDialog(String message) {
    // show error message as a dialog
    JOptionPane.showMessageDialog(null, message, "Invalid Command",
        JOptionPane.ERROR_MESSAGE);
    System.exit(1);
  }



}
