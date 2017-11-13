/**
 * TwoDirStr class to distinguish the two direction straight rail.
 */
class TwoDirStr {

  /**
   * A stRail can refer to any two direction straight rail type.
   */
  private StraightRail stRail;
  /**
   * variable to avoid magic number.
   */
  private final double varf = 0.5;
  /**
   * variable to avoid magic number.
   */
  private final double varo = 1.0;

  /**
   * To distinguish the Straight Rail.
   *
   * @param railType String refer to which Straight Rail type.
   * @param loc the location.
   * @return The Straight Rail type.
   */
  StraightRail getStRail(final String railType, final GridLoc loc) {
    if (railType == null) {
      return null;
    } else if (railType.equals("EWRail")) {
      stRail = new StraightRail(new Direction("east"), new Direction("west"));
      stRail.setVariable(0.0, varf, varo, varf);
    } else if (railType.equals("NSRail")) {
      stRail = new StraightRail(new Direction("north"), new Direction("south"));
      stRail.setVariable(varf, 0.0, varf, 1.0);
    }
    stRail.setLoc(loc);
    return stRail;
  }

}
