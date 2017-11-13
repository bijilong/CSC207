import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The SwitchRail class.  A SwitchRail object has three ends, If a Car
 * moves on from the first end, the switch determines which of the other
 * two ends it leaves from.  If it moves on from one of the other two ends,
 * it automatically leaves by the first end.
 */
public class SwitchRail extends Rail {

  /**
   * The multipliers for the endpoints of the StraightRail.
   */
  private double x1, y1, x2, y2, x3, y3;

  /**
   * The startAngle.
   */
  private int startAngle;


  /**
   * The type of switchRail.
   */
  private String swType;


  /**
   * (end1,end2) and (end1,end3) are the two pairs. end1 and end2 are
   * the straight directions (i.e., they are opposite each other),
   * and end1 and end3 form the corner.
   */
  Direction end1, end2, end3;
  /**
   * The Rail in the direction end1.
   */
  Rail neighbour1;
  /**
   * The Rail in the direction end2.
   */
  Rail neighbour2;
  /**
   * The Rail in the direction end3.
   */
  Rail neighbour3;

  /**
   * Whether I am straight.
   */
  boolean goingStraight;

  /**
   * A switch rail has three direction, one location and color magenta.
   *
   * @param e1 the direction end1.
   * @param e2 the direction end2.
   * @param e3 the direction end3.
   * @param loc the location.
   */
  public SwitchRail(final Direction e1, final Direction e2, final Direction e3,
      final GridLoc loc) {
    super(loc);
    setColor(Color.magenta);
    end1 = e1;
    end2 = e2;
    end3 = e3;
  }

  /**
   * A switch rail has one location and color magenta.
   *
   * @param e1 the direction end1.
   * @param e2 the direction end2.
   * @param e3 the direction end3.
   */
  public SwitchRail(final Direction e1, final Direction e2,
      final Direction e3) {
    super();
    setColor(Color.magenta);
    end1 = e1;
    end2 = e2;
    end3 = e3;
  }

  /**
   * Get the startAngle.
   *
   * @return the startAngle.
   */
  public int getStartAngle() {
    return startAngle;
  }

  /**
   * Set the start Angle.
   *
   * @param s the angle to be set.
   */
  public void setStartAngle(final int s) {
    this.startAngle = s;
  }

  /**
   * Get the type of switch rail.
   *
   * @return the type of switch rail.
   */
  public String getSwType() {
    return swType;
  }

  /**
   * Set the type of switch rail.
   *
   * @param t the type of switch rail to be set.
   */
  public void setSwType(final String t) {
    this.swType = t;
  }

  /**
   * Check whether can exit.
   *
   * @param d the direction entered.
   * @return whether can exit.
   */
  public boolean exitOK(final Direction d) {
    if (neighbour1 instanceof SwitchRail || neighbour2 instanceof SwitchRail
        || neighbour3 instanceof
        SwitchRail) {
//        return (d.equals(end1) || d.equals(end2) || d.equals(end3));
      if (neighbour1 instanceof SwitchRail) {
        if ((d.equals(((SwitchRail) neighbour1).end1) || d.equals(((SwitchRail) neighbour1).end2)
            || d.equals(((SwitchRail) neighbour1).end3))) {
          return true;
        }
      } else if (neighbour2 instanceof SwitchRail) {
        if ((d.equals(((SwitchRail) neighbour2).end1) || d.equals(((SwitchRail) neighbour2).end2)
            || d.equals(((SwitchRail) neighbour2).end3))) {
          return true;
        }
      } else if (((d.equals(((SwitchRail) neighbour3).end1) || d.equals(((SwitchRail) neighbour3).end2)
          || d.equals(((SwitchRail) neighbour3).end3)))) {
        return true;
      }
    }
    return d.equals(end1) || d.equals(end2) || d.equals(end3);
  }

  /**
   * The modifier for first, second and third.
   *
   * @param xf the modifier.
   * @param yf the modifier.
   * @param xs the modifier.
   * @param ys the modifier.
   * @param xt the modifier.
   * @param yt the modifier.
   */
  public void setVariable(final double xf, final double yf, final double xs,
      final double ys, final double xt, final double yt) {
    this.x1 = xf;
    this.x2 = xs;
    this.x3 = xt;
    this.y1 = yf;
    this.y2 = ys;
    this.y3 = yt;

  }

  /**
   * Return true if d is valid for this Rail, return false and
   * rint an error otherwise.
   *
   * @param d direction entered.
   * @return whether vaild direction.
   */
  private boolean validDir(final Direction d) {
    if (!exitOK(d)) {
      System.err.print("exit(): Not a valid dir for this piece: ");
      System.err.println(end1.getDirection() + " "
          + end2.getDirection() + " " + d.getDirection());
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * Register that r is adjacent to me from direction d.
   *
   * @param r The rail to register.
   * @param d The direction entered.
   */
  public void register(final Rail r, final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = r;
      } else if (d.equals(end2)) {
        neighbour2 = r;
      } else {
        neighbour3 = r;
      }
    }
  }

  /**
   * Unregister that r is adjacent to me from direction d.
   *
   * @param d the direction enter
   */
  public void unRegister(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = null;
      } else if (!d.equals(end2)) {
        neighbour2 = null;
      } else {
        neighbour3 = null;
      }
    }
  }

  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   * f d is not end1's Direction, then it will have to
   * exit toward end1.
   *
   * @param d the direction entered.
   * @return the exit direction.
   */
  public Direction exit(final Direction d) {
    if (validDir(d)) {
      if (goingStraight) {
        return d.opposite();
      } else {
        if (d.equals(end1)) {
          return end3;
        } else {
          return end2;
        }
      }
    }
    return null;
  }

  /**
   * d is the direction that entered , and must be one of end1, end2 and end3.
   * return the rail exit.
   *
   * @param d the direction entered.
   * @return next Rail.
   */
  public Rail nextRail(final Direction d) {
      if (d.equals(end1) && !goingStraight) {
        return neighbour3;
      }
      if (d.equals(end1) && goingStraight) {
        return neighbour2;
      } else {
        return neighbour1;
      }
    }

//    return null;
//  }


  /**
   * Handle a mouse click.  This will toggle the direction of the switch.
   *
   * @param evt an event.
   * @return true when we click the mouse.
   */
  public boolean handleEvent(final Event evt) {
    Object target = evt.target;

    if (evt.id == Event.MOUSE_DOWN && !occupied()) {
      goingStraight = !goingStraight;
      repaint();
      return true;
    }

    // If we get this far, I couldn't handle the event
    return false;
  }


  /**
   * Redraw.
   *
   * @param g graph
   */
  public void draw(final Graphics g) {

    super.draw(g);
    final int var = 90;

    Rectangle b = bounds();
    int wid = b.width;
    int hei = b.height;

    // Draw current direction of the switch darker.
    if (goingStraight) {
      g.setColor(Color.lightGray);
      g.drawArc((int) (x3 * wid), (int) (y3 * hei),
          wid, hei, startAngle, var);
      g.setColor(getColor());
      g.drawLine((int) (x1 * wid), (int) (y1 * hei),
          (int) (x2 * wid), (int) (y2 * hei));
    } else {
      g.setColor(Color.lightGray);
      g.drawLine((int) (x1 * wid), (int) (y1 * hei),
          (int) (x2 * wid), (int) (y2 * hei));
      g.setColor(getColor());
      g.drawArc((int) (x3 * wid), (int) (y3 * hei),
          wid, hei, startAngle, var);
    }
  }

  /**
   * Covert to a readable name.
   *
   * @return Type of SwitchRail.
   */
  public String toString() {
    return swType;
  }

}

