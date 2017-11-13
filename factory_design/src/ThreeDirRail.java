/**
 * ThreeDirRail class. The rule is to distinguish the Switch Rail.
 * Reduce duplicate code. Use String RaiType to check out which Switch Rail is.
 */
public class ThreeDirRail {

  /**
   * A swRail can refer to any switch rail type.
   */
  private SwitchRail swRail;
  /**
   * Direction north.
   */
  private Direction north = new Direction("north");
  /**
   * Direction east.
   */
  private Direction east = new Direction("east");
  /**
   * Direction west.
   */
  private Direction west = new Direction("west");
  /**
   * Direction south.
   */
  private Direction south = new Direction("south");
  /**
   * variable to avoid magic number.
   */
  private final int var = 180;
  /**
   * variable to avoid magic number.
   */
  private final int var1 = 270;
  /**
   * variable to avoid magic number.
   */
  private final int var2 = 90;
  /**
   * variable to avoid magic number.
   */
  private final double var3 = 0.5;

  /**
   * To distinguish the Switch Rail.
   *
   * @param railType String refer to which Switch Rail type.
   * @param loc the location.
   * @return The Switch Rail type.
   */
  public SwitchRail getSwRail(final String railType, final GridLoc loc) {

    if (railType == null) {
      return null;
    } else if (railType.equals("EWNRail")) {
      swRail = new SwitchRail(east, west, north);
      swRail.setStartAngle(var);
      swRail.setVariable(0.0, var3, 1.0, var3, var3, -var3);
    } else if (railType.equals("EWSRail")) {
      swRail = new SwitchRail(east, west, south);
      swRail.setStartAngle(var2);
      swRail.setVariable(0.0, var3, 1.0, var3, var3, var3);
    } else if (railType.equals("NSERail")) {
      swRail = new SwitchRail(north, south, east);
      swRail.setStartAngle(var);
      swRail.setVariable(var3, 0.0, var3, 1.0, var3, -var3);
    } else if (railType.equals("NSWRail")) {
      swRail = new SwitchRail(north, south, west);
      swRail.setStartAngle(var1);
      swRail.setVariable(var3, 0.0, var3, 1.0, -var3, -var3);
    } else if (railType.equals("SNERail")) {
      swRail = new SwitchRail(south, north, east);
      swRail.setStartAngle(var2);
      swRail.setVariable(var3, 0.0, var3, 1.0, var3, var3);
    } else if (railType.equals("SNWRail")) {
      swRail = new SwitchRail(south, north, west);
      swRail.setStartAngle(0);
      swRail.setVariable(var3, 0.0, var3, 1.0, -var3, var3);
    } else if (railType.equals("WENRail")) {
      swRail = new SwitchRail(west, east, north);
      swRail.setStartAngle(var1);
      swRail.setVariable(0.0, var3, 1.0, var3, -var3, -var3);
    } else if (railType.equals("WESRail")) {
      swRail = new SwitchRail(west, east, south);
      swRail.setStartAngle(0);
      swRail.setVariable(0.0, var3, 1.0, var3, -var3, var3);
    }
    swRail.setSwType(railType);
    swRail.setLoc(loc);
    return swRail;
  }

}
