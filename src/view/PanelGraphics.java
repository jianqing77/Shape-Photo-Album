package view;

import controller.Features;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import model.shape.IShape;

/**
 * This is the Graphics Panel extended JPanel designed solely to paint the snapshot graphics.
 *
 * @author Jianqing Ma
 */
public class PanelGraphics extends JPanel {

  private Features features;

  /**
   * Constructor of Panel for Graphics.
   *
   * @param features (Features)
   */
  public PanelGraphics(Features features) {
    this.features = features;
    this.setBackground(Color.BLUE);
    this.setPreferredSize(new Dimension(getWidth(), getHeight()));
  }

  /**
   * Helper method to update the graphics panel.
   *
   * @param features (Features) the new feature to be updated
   */
  public void updateGraphicsPanel(Features features) {
    this.features = features;
    // repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // get a list of shapes in the current snapshot
    List<IShape> curShapes = this.features.getCurSnapshot().getCurrentShapes().values().stream().toList();
    for (IShape s: curShapes) {
      // System.out.println("Painting shape: " + s.getName());
      // set shape color
      Color shapeColor = new Color((int)s.getColor().getRed(), (int)s.getColor().getGreen(),
          (int)s.getColor().getBlue());
      g.setColor(shapeColor);
      // if rectangle
      if (s.getType().equals("rectangle")) {
        // set shape size and coordinate
        g.fillRect((int)s.getCoordinate().getX(), (int)s.getCoordinate().getY(),
            (int)s.getScaleAttrA(), (int)s.getScaleAttrB());
      }
      // if oval
      if (s.getType().equals("oval")) {
        g.fillOval((int)s.getCoordinate().getX(), (int)s.getCoordinate().getY(),
            (int)s.getScaleAttrA(), (int)s.getScaleAttrB());
      }
    }
  }
}
