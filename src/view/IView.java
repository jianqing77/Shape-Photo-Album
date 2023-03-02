package view;

import controller.Features;

/**
 * This is the IView interface that serves as the contract for all the views.
 */
public interface IView {


  /**
   * Set features of the current view.
   *
   * @param features (Features)
   */
  void setFeatures(Features features);

  /**
   * Update  the current view.
   *
   * @param features (Features)
   */
  void updateView(Features features);

  /**
   * Make view visible.
   */
  void display();


}
