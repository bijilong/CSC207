/**
 * The (x,y) location on the Track.
 */
public class GridLoc {

  /**
   * the row.
   */
  private int row;
  /**
   * the col.
   */
  private int col;

  /**
   * The GridLoc to report the coordinate.
   *
   * @param r row.
   * @param c col.
   */
  public GridLoc(final int r, final int c) {
    row = r;
    col = c;
  }

  /**
   * Get the row.
   *
   * @return row
   */
  public int getRow() {
    return row;
  }

  /**
   * Set the row.
   *
   * @param r the row to be set.
   */
  public void setRow(final int r) {
    this.row = r;
  }

  /**
   * Get the col.
   *
   * @return col
   */
  public int getCol() {
    return col;
  }

  /**
   * Set the col.
   *
   * @param c the col to be set.
   */
  public void setCol(final int c) {
    this.col = c;
  }

  /**
   * Covert to a readable name.
   *
   * @return the coordinate.
   */
  public String toString() {
    return (row + " " + col);
  }

}

