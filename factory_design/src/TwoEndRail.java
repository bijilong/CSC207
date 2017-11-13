import java.awt.Color;

/**
 * he TwoEndRail class.  A TwoEndRail object has two ends,
 * which may or may be not be opposite each other.
 */
abstract class TwoEndRail extends Rail {

  /**
   * The Rail in the direction end1.
   * The Rail in the direction end2.
   */
  private Direction end1, end2;
  /**
   * The Rail in the direction end1.
   */
  private Rail neighbour1;
  /**
   * The Rail in the direction end2.
   */
  private Rail neighbour2;

  /**
   * Check whether can exit.
   *
   * @param d the direction entered.
   * @return whether can exit.
   */
  public boolean exitOK(final Direction d) {
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
    }
    return d.equals(end1) || d.equals(end2);
  }

  /**
   * Return true if d is valid for this Rail, return false and
   * print an error otherwise.
   *
   * @param d direction entered.
   * @return whether vaild direction.
   */
  private boolean validDir(final Direction d) {
    if (!exitOK(d)) {
      System.err.print("exit(): Not a valid dir for this piece: ");
      System.err.println(end1.getDirection() + " " + end2.getDirection()
          + " " + d.getDirection());
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * TwoEndRail class has two direction ,one location and black color.
   *
   * @param e1 the direction end1.
   * @param e2 the direction end2.
   * @param loc the location.
   */
  TwoEndRail(final Direction e1, final Direction e2, final GridLoc loc) {
    super(loc);
    setColor(Color.black);
    end1 = e1;
    end2 = e2;
  }

  /**
   * TwoEndRail class has two direction and black color.
   *
   * @param e1 the direction end1.
   * @param e2 the direction end2.
   */
  TwoEndRail(final Direction e1, final Direction e2) {
    super();
    setColor(Color.black);
    end1 = e1;
    end2 = e2;
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
      } else {
        neighbour2 = r;
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
      } else if (d.equals(end2)) {
        neighbour2 = null;
      }
    }
  }

  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param d the direction entered.
   * @return exit direction.
   */
  public Direction exit(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return end2;
      } else {
        return end1;
      }
    }

    return null;
  }


  /**
   * d is the direction that I entered from, Return the Rail at the other end.
   *
   * @param d the direction entered.
   * @return the next rail with the enter direction d.
   */
  public Rail nextRail(final Direction d) {
    if (validDir(d)) {
      if (d.equals(end1)) {
        return neighbour2;
      } else {
        return neighbour1;
      }
    }

    return null;
  }

  /**
   * Covert to a readable name.
   *
   * @return String TwoEndRail.
   */
  public String toString() {
    return "TwoEndRail";
  }

}

