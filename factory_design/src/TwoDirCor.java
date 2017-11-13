/**
 * TwoDirCor class to distinguish the two direction corner rail.
 */
class TwoDirCor {

  /**
   * A coRail can refer to any two direction corner rail type.
   */
  private CornerRail coRail;
  /**
   * variable to avoid magic number.
   */
  private final int var = 180;
  /**
   * variable to avoid magic number.
   */
  private final int varts = 270;
  /**
   * variable to avoid magic number.
   */
  private final int varnz = 90;
  /**
   * variable to avoid magic number.
   */
  private final double varf = 0.5;

  /**
   * To distinguish the Corner Rail.
   *
   * @param railType String refer to which Corner Rail type.
   * @param loc the location.
   * @return The Corner Rail type.
   */

  CornerRail getCoRail(final String railType, final GridLoc loc) {
    if (railType == null) {
      return null;
    } else if (railType.equals("NERail")) {
      coRail = new CornerRail(new Direction("north"), new Direction("east"));
      coRail.setStartAngle(var);
      coRail.setVariable(varf, -varf);
    } else if (railType.equals("NWRail")) {
      coRail = new CornerRail(new Direction("north"), new Direction("west"));
      coRail.setStartAngle(varts);
      coRail.setVariable(-varf, -varf);
    } else if (railType.equals("SWRail")) {
      coRail = new CornerRail(new Direction("south"), new Direction("west"));
      coRail.setStartAngle(0);
      coRail.setVariable(-varf, varf);
    } else if (railType.equals("SERail")) {
      coRail = new CornerRail(new Direction("south"), new Direction("east"));
      coRail.setStartAngle(varnz);
      coRail.setVariable(varf, varf);
    }
    coRail.setLoc(loc);
    coRail.setCoType(railType);
    return coRail;
  }

}
