package view;

import controller.Features;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the TopBar panel designed for painting th top bar.
 *
 * @author Jianqing Ma
 */
public class PanelTopBar extends JPanel {

  private JLabel topBarText;

  /**
   * Constructor of Panel Top Bar.
   *
   * @param features (Features)
   */
  public PanelTopBar(Features features) {
    // initiate top bar as JLabel
    this.topBarText = new JLabel();
    // set top bar text
    this.topBarText.setText("<html><u><b>Snapshot ID: </u>" + features.getCurSnapshot().getId()
        + "<br>" + "<u><b>Description: </u>" + features.getCurSnapshot().getDescription());
    // set top bar color & size
    this.setBackground(Color.ORANGE);
    this.setPreferredSize(new Dimension(getWidth(), 40));
    add(topBarText);
  }

  /**
   * Helper method to update the top bar panel.
   *
   * @param features (Features) features to be updated
   */
  public void updateTopBarPanel(Features features) {
    this.topBarText.setText("<html><u><b>Snapshot ID: </u>" + features.getCurSnapshot().getId()
        + "<br>" + "<u><b>Description: </u>" + features.getCurSnapshot().getDescription());
    // repaint();
  }
}
