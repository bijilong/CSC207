/**
 * The EmptyRail class.  This is a place on the Track which
 * does not have an actual piece of track.
 */
class EmptyRail extends Rail {

  // Return true if d is a valid direction for me.

  /**
   * Return true if d is a valid direction for me.
   *
   * @param d the direction entered.
   * @return true if can exit, else false.
   */
  public boolean exitOK(final Direction d) {
    return false;
  }


  /**
   * Register that Rail r is in Direction d.
   *
   * @param r Rail to register as neighbour.
   * @param d the direction entered.
   */
  public void register(final Rail r, final Direction d) {
  }

  // Register that there is no Rail in Direction d.

  /**
   * Register that there is no Rail in Direction d.
   *
   * @param d direction entered.
   */
  public void unRegister(final Direction d) {
  }

  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param d direction entered.
   * @return true if can exit, else false.
   */
  public Direction exit(final Direction d) {
    return null;
  }

  /**
   * Given that d is the Direction from which a Car entered,
   * report which Rail is next.
   *
   * @param d direction entered.
   * @return the next Rail.
   */
  public Rail nextRail(final Direction d) {
    return null;
  }

}

