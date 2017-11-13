import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The CornerRail class.  A CornerRail object has two ends,
 * which must be not be opposite each other.
 */
public class CornerRail extends TwoEndRail {

  // The multipliers for the width and height.
  /**
   * coordinate.
   */
  private double x1, y1;
  /**
   * Create Variable for 90 avoid magic number.
   */
  private final int variablen = 90;
  /**
   * the 90 degree startAngle.
   */
  private int startAngle = variablen;


  /**
   * Type of CornerRail.
   */
  private String coType;

  /**
   * CornerRail has two end and one location.
   *
   * @param e1 Direction of end1.
   * @param e2 Direction of end2.
   * @param loc the location.
   */
  CornerRail(final Direction e1, final Direction e2, final GridLoc loc) {
    super(e1, e2, loc);
  }

  /**
   * CornerRail has two end.
   *
   * @param e1 Direction of end1.
   * @param e2 Direction of end2.
   */
  CornerRail(final Direction e1, final Direction e2) {
    super(e1, e2);
  }

  /**
   * Get the type of CornerRail.
   *
   * @return the type of this CornerRail
   */
  public String getCoType() {
    return coType;
  }

  /**
   * Set the type of CornerRail.
   *
   * @param type CornerRail wants to set.
   */
  public void setCoType(final String type) {
    this.coType = type;
  }

  /**
   * Set x,y coordinate.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  public void setVariable(final double x, final double y) {
    this.x1 = x;
    this.y1 = y;
  }

  /**
   * Get the StartAngle.
   *
   * @return the StartAngle.
   */
  public int getStartAngle() {
    return startAngle;
  }

  /**
   * Set the StartAngle.
   *
   * @param angle the startAngle to be set.
   */
  public void setStartAngle(final int angle) {
    this.startAngle = angle;
  }

  /**
   * Redraw myself.
   *
   * @param g graph
   */
  public void draw(final Graphics g) {
    final int variable = 90;
    super.draw(g);
    g.setColor(getColor());
    Rectangle b = bounds();
    g.drawArc((int) (x1 * b.width), (int) (y1 * b.height),
        b.width, b.height, getStartAngle(), variable);
  }

  /**
   * Covert to a readable name.
   *
   * @return Type of CornerRail.
   */
  @Override
  public String toString() {
    return coType;
  }

}

