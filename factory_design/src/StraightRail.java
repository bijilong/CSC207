import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The StraightRail class.  A StraightRail object has two ends,
 * which must be opposite each other.
 */
public class StraightRail extends TwoEndRail {

  /**
   * The multipliers for the endpoints of the StraightRail.
   */
  private double x1, y1, x2, y2;

  /**
   * The straight rail has location ,two ends and color blue.
   *
   * @param e1 the direction end1.
   * @param e2 the direction end2.
   * @param loc the location.
   */
  StraightRail(final Direction e1, final Direction e2, final GridLoc loc) {
    super(e1, e2, loc);
    setColor(Color.blue);
  }

  /**
   * he straight rail has two ends and color blue.
   *
   * @param e1 the direction end1.
   * @param e2 the direction end2.
   */
  StraightRail(final Direction e1, final Direction e2) {
    super(e1, e2);
    setColor(Color.blue);
  }

  /**
   * Set the variable for x1,y1,x2,y2.
   *
   * @param x the modifier.
   * @param y the modifier.
   * @param xm the modifier.
   * @param ym the modifier.
   */
  public void setVariable(final double x, final double y,
      final double xm, final double ym) {
    this.x1 = x;
    this.y1 = y;
    this.x2 = xm;
    this.y2 = ym;
  }

  /**
   * Redraw myself.
   *
   * @param g graph
   */
  public void draw(final Graphics g) {
    super.draw(g);
    g.setColor(getColor());
    Rectangle b = bounds();
    g.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
        (int) (x2 * b.width), (int) (y2 * b.height));
  }

  /**
   * Covert to a readable name.
   *
   * @return String StraightRail..
   */
  public String toString() {
    return "StraightRail";
  }

}

