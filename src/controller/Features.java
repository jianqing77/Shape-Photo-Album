package controller;

import java.util.Objects;
import model.album.IAlbum;
import model.album.Snapshot;

/**
 * This is the Features class which defines the implementation features of the view and connect
 * the view with the model.
 *
 * @author Jianqing Ma
 */
public class Features {
  private IAlbum model;
  private int index;
  private Snapshot curSnapshot;

  /**
   * Constructor of Features.
   *
   * @param model (IAlbum)
   */
  public Features(IAlbum model) {
    Objects.requireNonNull(model);
    this.model = model;
    this.index = 0;
    this.curSnapshot = this.model.getSnapshotList().get(0);
  }

  /**
   * Getter method to get the model.
   *
   * @return (IAlbum)
   */
  public IAlbum getModel() {
    return this.model;
  }

  /**
   * Getter method to get the index.
   *
   * @return (int)
   */
  public int getIndex() {
    return index;
  }

  /**
   * Getter method to get the current Snapshot.
   *
   * @return (Snapshot)
   */
  public Snapshot getCurSnapshot() {
    return this.curSnapshot;
  }

  /**
   * Setter method to set the index.
   */
  public void setIndex(int index) {
    this.index = index;
  }

  /**
   * Get the current snapshot index.
   */
  public int getCurSnapshotIdx() {
    return this.model.getSnapshotList().indexOf(this.curSnapshot);
  }

  /**
   * Set the current snapshot index.
   */
  public void setCurSnapshot(Snapshot snapshot) {
    this.curSnapshot = snapshot;
  }

  /**
   * Go to previous snapshot.
   */
  public void gotoPreviousSnapshot() throws IndexOutOfBoundsException {
    // if current snapshot is the first snapshot
    if (this.index == 0) {
      throw new IndexOutOfBoundsException();
    }
    // if not the first, set current snapshot to the previous snapshot
    this.index -= 1;
    setCurSnapshot(this.model.getSnapshotList().get(this.index));
  }

  /**
   * Go to next snapshot.
   */
  public void gotoNextSnapshot() throws IndexOutOfBoundsException {
    if (this.index == this.model.getSnapshotList().size() - 1) {
      throw new IndexOutOfBoundsException();
    }
    this.index += 1;
    setCurSnapshot(this.model.getSnapshotList().get(this.index));
  }

  /**
   * Get all the options to choose.
   */
  public String[] getOptions() {
    return this.model.getSnapshotsIDList().toArray(new String[0]);
  }

  /**
   * Select snapshot from the options.
   */
  public void selectSnapshot(String selectedSnapshotId) {
    setCurSnapshot(this.model.getSnapshot(selectedSnapshotId));
  }


}
