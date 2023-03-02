package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the Graphical View class which extend the JFrame and Implements the iView Interface
 */
public class GraphicalView extends JFrame implements IView {

  private PanelTopBar topBarPanel;
  private PanelGraphics graphicsPanel;
  private final JButton btnPrev;
  private final JButton btnSelect;
  private final JButton btnNext;
  private final JButton btnQuit;
  private Features features;

  /**
   * Constructor of GraphicalView class.
   *
   * @param title (String)
   * @param windowWidth (int)
   * @param height (int)
   */
  public GraphicalView(String title, int windowWidth, int height) {
    super(title); // display the title

    // ---------------- Frame Attributes ---------------
    // set graphical view overall attributes
    this.setBounds(0, 0, windowWidth, height); // set bound
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the operation correctly
    this.setLayout(new BorderLayout()); // set layout

    // ------------- Control Panel: Buttons -------------
    // create and add the control panel to the frame bottom
    JPanel btmControlPanel = createControlPanel();
    // create buttons
    this.btnPrev = new JButton("<< Previous <<");
    this.btnSelect = new JButton("^^ Select ^^");
    this.btnNext = new JButton(">> Next >>");
    this.btnQuit = new JButton("xx Quit xx");
    // add elements to frame
    this.add(btmControlPanel, BorderLayout.SOUTH);
    btmControlPanel.add(btnPrev);
    btmControlPanel.add(btnSelect);
    btmControlPanel.add(btnNext);
    btmControlPanel.add(btnQuit);
  }

  /**
   * Helper method to set up and return a JPanel object.
   *
   * @return (JPanel) the modified JPanel
   */
  private JPanel createControlPanel() {
    JPanel controlPanel = new JPanel();
    controlPanel.setPreferredSize(new Dimension(getWidth(), 40));
    controlPanel.setBackground(Color.ORANGE);
    return controlPanel;
  }

  /**
   * Helper method to show error in dialog format.
   *
   * @param msg (String) error message
   */
  private void showError(String msg) {
    JOptionPane.showMessageDialog(this, "Operation failed.\n"
        + "Reason: " + msg);
  }

  /**
   * Set features of the current view.
   *
   * @param newFeatures (Feature)
   */
  public void setFeatures(Features newFeatures) {
    this.features = newFeatures;

    // add top bar panel to view
    this.topBarPanel = new PanelTopBar(features);
    // add graphics panel to view
    this.graphicsPanel = new PanelGraphics(features);

    this.add(graphicsPanel, BorderLayout.CENTER);
    this.add(topBarPanel, BorderLayout.NORTH);

    // ----- Set Up Bottom Control Bar: Button Functions -----
    btnPrev.addActionListener(event -> {
      try {
        features.gotoPreviousSnapshot();
        updateView(features);
      } catch (IndexOutOfBoundsException e) {
        showError("Reached the album beginning... No previous snapshot.");
      }
    });

    btnNext.addActionListener(event -> {
      try {
        features.gotoNextSnapshot();
        updateView(features);
      } catch (IndexOutOfBoundsException e) {
        showError("Reached the album end... No more snapshot next.");
      }
    });

    btnSelect.addActionListener(event -> {
      int curOptionIdx = features.getCurSnapshotIdx();
      String selectedSnapshotId = (String) JOptionPane.showInputDialog(this,
          "Choose a snapshot to display", "Select Snapshot",
          JOptionPane.QUESTION_MESSAGE, null, features.getOptions(),
          features.getOptions()[curOptionIdx]);
      features.selectSnapshot(selectedSnapshotId);
      updateView(features);
    });

    btnQuit.addActionListener(event -> {
      System.exit(1);
    });
  }


  @Override
  public void updateView(Features newFeatures) {
    // update features -- update
    this.features = newFeatures;
    this.graphicsPanel.updateGraphicsPanel(features);
    // repaint();
    this.topBarPanel.updateTopBarPanel(features);
    repaint();
  }

  @Override
  public void display() {
    try {
      this.setVisible(true);
    } catch (Exception e) {
      showError(e.getMessage());
    }
  }

}
