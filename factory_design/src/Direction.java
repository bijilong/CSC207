/**
 * Direction class. one of 'north', 'south', 'east' and 'west'.
 */
class Direction {

  /**
   * one of 'north', 'south', 'east' and 'west'.
   */
  private String direction;

  /**
   * if s is one of 'north', 'south', 'east' and 'west',
   * set direction as s.else print error.
   *
   * @param s String.
   */
  Direction(final String s) {

    if (!s.equals("north") && !s.equals("south")
        && !s.equals("east") && !s.equals("west")) {
      System.err.println(s + " is an invalid direction.  Must be "
          + "'north', 'south', 'east' or 'west'");
      System.exit(0);
    }

    direction = s;

  }

  /**
   * Get the direction.
   *
   * @return Direction.
   */
  public String getDirection() {
    return direction;
  }

  /**
   * Set the direction.
   *
   * @param dir the direction to be set.
   */

  public void setDirection(final String dir) {
    this.direction = dir;
  }

  /**
   * Check whether direction is equal.
   *
   * @param d one direction
   * @return true if this equals d, else false.
   */
  boolean equals(final Direction d) {
    return d.equals(direction);
  }

  /**
   * Check whether direction is equal.
   *
   * @param s one direction written in String.
   * @return true if this equals d, else false.
   */
  boolean equals(final String s) {
    return s.equals(direction);
  }

  /**
   * Covert to a readable name.
   *
   * @return the direction.
   */

  public String toString() {
    return direction;
  }

  /**
   * the opposite direction.
   *
   * @return the opposite direction.
   */
  public Direction opposite() {
    if (direction.equals("north")) {
      return new Direction("south");
    } else if (direction.equals("south")) {
      return new Direction("north");
    } else if (direction.equals("east")) {
      return new Direction("west");
    } else if (direction.equals("west")) {
      return new Direction("east");
    }
    return null;
  }
}

