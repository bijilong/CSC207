import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The CrossRail class.  A CrossRail object has four ends.
 */
class CrossRail extends Rail {

  /**
   * My line coordinates for drawing myself.
   */
  private double x1, y1, x2, y2, x3, y3, x4, y4;

  // (end1,end2) and (end3,end4) are the two pairs.
  // The are, in order, always 'north', 'south', 'east', and 'west'.
//  private Direction end1, end2, end3, end4;

  /**
   * The Rail in the direction end1.
   */
  private Rail neighbour1;
  /**
   * The Rail in the direction end2.
   */
  private Rail neighbour2;
  /**
   * The Rail in the direction end3.
   */
  private Rail neighbour3;
  /**
   * The Rail in the direction end4.
   */
  private Rail neighbour4;
  /**
   * set end1 Direction north.
   */
  private Direction end1 = new Direction("north");
  /**
   * set end2 Direction south.
   */
  private Direction end2 = new Direction("south");
  /**
   * set end3 Direction east.
   */
  private Direction end3 = new Direction("east");
  /**
   * set end4 Direction west.
   */
  private Direction end4 = new Direction("west");

  /**
   * Check whether can exit.
   *
   * @param d the direction
   * @return true if can exit, else false.
   */
  public boolean exitOK(final Direction d) {
    return d.equals(end1) || d.equals(end2) || d.equals(end3) || d.equals(end4);
  }

  /**
   * Check whether a valid direction.
   *
   * @param d the direction
   * @return true if d is valid, else false and print an error.
   */
  private boolean validDir(final Direction d) {
    if (!exitOK(d)) {
      System.err.print("exit(): Not a valid dir for this piece: ");
      System.err.println(end1.getDirection() + " " + end2.getDirection() + " "
          + d.getDirection());
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * CrossRail has color darkGary.
   */
  CrossRail() {
    setColor(Color.darkGray);
//    end1 = new Direction("north");
//    end2 = new Direction("south");
//    end3 = new Direction("east");
//    end4 = new Direction("west");
  }

  /**
   * CrossRail has location.
   *
   * @param loc the location of this CrossRail
   */
  CrossRail(final GridLoc loc) {
    super(loc);

    setColor(Color.darkGray);
//    end1 = new Direction("north");
//    end2 = new Direction("south");
//    end3 = new Direction("east");
//    end4 = new Direction("west");

    setLoc(loc);

  }

  /**
   * Set the location of this CrossRail.
   *
   * @param loc the location wants to set.
   */
  public void setLoc(final GridLoc loc) {
    final double variable = 0.5;
    super.setLoc(loc);
    x1 = 0.0;
    y1 = variable;
    x2 = 1.0;
    y2 = variable;

    x3 = variable;
    y3 = 0.0;
    x4 = variable;
    y4 = 1.0;
  }

  /**
   * Register rail as neighbour.
   *
   * @param r Rail to register as neighbour.
   * @param d the direction entered.
   */
  public void register(final Rail r, final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = r;
      } else if (d.equals(end2)) {
        neighbour2 = r;
      } else if (d.equals(end3)) {
        neighbour3 = r;
      } else if (d.equals(end4)) {
        neighbour4 = r;
      }
    }
  }

  /**
   * Unregister the neighbour has the same direction as d.
   *
   * @param d the direction entered.
   */
  public void unRegister(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        neighbour1 = null;
      } else if (d.equals(end2)) {
        neighbour2 = null;
      } else if (d.equals(end3)) {
        neighbour3 = null;
      } else if (d.equals(end4)) {
        neighbour4 = null;
      }
    }
  }

  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param d the direction entered.
   * @return the direction exit.
   */
  public Direction exit(final Direction d) {
    if (validDir(d)) {

//      if (d.equals(end1)) {
//        return end2;
//      } else if (d.equals(end2)) {
//        return end1;
//      } else if (d.equals(end3)) {
//        return end4;
//      } else if (d.equals(end4)) {
//        return end3;
//      }
      return d.opposite();
    }

    return null;
  }


  /**
   * d is the direction entered and must be one of end1 and end2.
   *
   * @param d the direction entered.
   * @return return the next Rail.
   */
  public Rail nextRail(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return neighbour2;
      } else if (d.equals(end2)) {
        return neighbour1;
      } else if (d.equals(end3)) {
        return neighbour4;
      } else if (d.equals(end4)) {
        return neighbour3;
      }
    }

    return null;
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
    int hei = b.height;
    int wid = b.width;

    g.drawLine((int) (x1 * wid), (int) (y1 * hei),
        (int) (x2 * wid), (int) (y2 * hei));
    g.drawLine((int) (x3 * wid), (int) (y3 * hei),
        (int) (x4 * wid), (int) (y4 * hei));
  }

  /**
   * Covert to a readable name.
   *
   * @return Type of CrossRail.
   */
  public String toString() {
    return "CrossRail";
  }

}

