import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The Car class.  A Car object is a car in a train.  It has
 * weight and color, and can draw() and move().
 */

class Car {

  /**
   * The color of the Car.
   */
  private Color color;
  /**
   * The Car that immediately follows me.
   */
  private Car nextCar;

  /**
   * The Rail that I am currently occupying.
   */
  private Rail currentRail;
  /**
   * variable to avoid magic number.
   */
  private final int var4 = 4;
  /**
   * variable to avoid magic number.
   */
  private final int var3 = 3;
  /**
   * variable to avoid magic number.
   */
  private final int var7 = 7;
  /**
   * variable to avoid magic number.
   */
  private final int var8 = 8;
  /**
   * The direction in which I entered the current Rail.
   */
  private Direction dir;

  /**
   * A car has color c.
   *
   * @param c the color of this Car.
   */
  Car(final Color c) {
    this.color = c;
  }

  /**
   * Get the car follows me.
   *
   * @return The Car that immediately follows me.
   */
  Car getNextCar() {
    return nextCar;
  }

  /**
   * Set the car follows me.
   *
   * @param nextCa The Car i want to be the next Car.
   */
  void setNextCar(final Car nextCa) {
    this.nextCar = nextCa;
  }


  /**
   * Get the current Rail.
   *
   * @return the current Rail.
   */
  Rail getCurrentRail() {
    return currentRail;
  }

  /**
   * Set the current Rail.
   *
   * @param currentRai the rail i want to be the current Rail.
   */
  public void setCurrentRail(final Rail currentRai) {
    this.currentRail = currentRai;
  }

  /**
   * Set me moving in direction d.
   *
   * @param d the direction to set.
   */
  public void setDirection(final Direction d) {
    dir = d;
  }

  /**
   * Place this Car on Rail r.
   *
   * @param r the rail to set.
   */

  void setRail(final Rail r) {
    currentRail = r;
  }

  /**
   * Move forward one TrackPiece; this is the current TrackPiece.
   */

  void move() {

    Direction nD = currentRail.exit(dir);
    Direction nextDir = nD.opposite();
    Rail nextRail = currentRail.nextRail(dir);
    dir = nextDir;
    if (!nextRail.isHaveATrain()) {
      if (!(nextRail instanceof SwitchRail)) {
        if (nextRail.enter(this)) {
          currentRail.leave();
          currentRail = nextRail;

          // We have to call this here rather than within currentRail.enter()
          // because otherwise the wrong Rail is used...
          currentRail.repaint();

          if (nextCar != null) {
            nextCar.move();
          }
        }
      } else {
        if (((SwitchRail) nextRail).goingStraight) {
          if (((SwitchRail) nextRail).neighbour1.equals(currentRail)
              || ((SwitchRail) nextRail).neighbour2.equals(currentRail)) {
            if (nextRail.enter(this)) {
              currentRail.leave();
              currentRail = nextRail;

              // We have to call this here rather than within
              // currentRail.enter() because otherwise the wrong
              // Rail is used...
              currentRail.repaint();

              if (nextCar != null) {
                nextCar.move();
              }
            }
          }
        } else {
          if (((SwitchRail) nextRail).neighbour3.equals(currentRail)
              || ((SwitchRail) nextRail).neighbour1.equals(currentRail)) {
            if (nextRail.enter(this)) {
              currentRail.leave();
              currentRail = nextRail;
              currentRail.repaint();

              if (nextCar != null) {
                nextCar.move();
              }
            }
          }
        }
      }
    }

  }


  /**
   * Return true if the current Rail is a SwitchRail and is StraightRail.
   *
   * @return whether current Rail is SwitchRail and  is StraightRail.
   */

  private boolean switchStraight() {

    if (currentRail instanceof SwitchRail) {
      if (((SwitchRail) currentRail).goingStraight) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return true if the current Rail is a SwitchRail and is CornerRail.
   *
   * @return whether current Rail is SwitchRail and  is CornerRail.
   */

  private boolean switchCorner() {

    if (!(currentRail instanceof SwitchRail)) {
      return false;
    }
    return !switchStraight();

  }

  /**
   * Draw this Car.
   *
   * @param g graph.
   */
  void draw(final Graphics g) {

//    GridLoc myLoc = currentRail.;
    Rectangle b = currentRail.bounds();

    // the polygon to draw on the screen.
    //    Polygon p;

    double width = b.width;
    double height = b.height;
    final double variableFour = 4.0;
    final int variableFive = 5;

    int sqrtOfHypotenuse = (int) Math.sqrt((width * width / variableFour)
        + (height * height / variableFour));

    int[] xPoints = new int[variableFive];
    int[] yPoints = new int[variableFive];
//    int nPoints = 5;

    if (currentRail instanceof StraightRail
        || currentRail instanceof CrossRail
        || switchStraight()) {
      if (dir.equals("north") || dir.equals("south")) {
        makeStraightPolygon(xPoints, yPoints);
      } else {
        makeStraightPolygon(yPoints, xPoints);
      }
    } else if (currentRail instanceof CornerRail || switchCorner()) {
      if (currentRail.toString().equals("NERail")
//          || currentRail instanceof NSERail
          || currentRail.toString().equals("NSERail")
          || currentRail.toString().equals("EWNRail")) {
//          || currentRail instanceof EWNRail) {
        makeCornerPolygon(xPoints, -sqrtOfHypotenuse,
            (int) width, (int) (width / 2));
        makeCornerPolygon(yPoints, sqrtOfHypotenuse,
            (int) (height / 2), 0);
      } else if (currentRail.toString().equals("NWRail")
          || currentRail.toString().equals("NSWRail")
//          || currentRail instanceof NSWRail
          || currentRail.toString().equals("WENRail")) {
//          || currentRail instanceof WENRail) {
        makeCornerPolygon(xPoints,  sqrtOfHypotenuse, (int) (width / 2), 0);
        makeCornerPolygon(yPoints, sqrtOfHypotenuse, 0, (int) (height / 2));
      } else if (currentRail.toString().equals("SERail")
          || currentRail.toString().equals("SNERail")
//          || currentRail instanceof SNERail
          || currentRail.toString().equals("EWSRail")) {
//          || currentRail instanceof EWSRail) {
        makeCornerPolygon(xPoints, -sqrtOfHypotenuse,
        (int) (width / 2), (int) width);
        makeCornerPolygon(yPoints, -sqrtOfHypotenuse,
            (int) height, (int) (height / 2));
      } else if (currentRail.toString().equals("SWRail")
          || currentRail.toString().equals("SNWRail")
//          || currentRail instanceof SNWRail
          || currentRail.toString().equals("WESRail")) {
//          || currentRail instanceof WESRail) {
        makeCornerPolygon(xPoints,  sqrtOfHypotenuse,
        0, (int) (width / 2));
        makeCornerPolygon(yPoints, -sqrtOfHypotenuse,
            (int) (height / 2), (int) height);
      }
    }

    g.setColor(color);
    g.drawPolygon(xPoints, yPoints, variableFive);

  }


  /**
   * Calculate the CornerPolygon.
   * @param xp the xpoint.
   * @param xSideOffset the Xside.
   * @param x0Mod x0Mod
   * @param x1Mod x1Mod.
   */
  private void makeCornerPolygon(final int[] xp, final int xSideOffset,
      final int x0Mod, final int x1Mod) {

    xp[0] = x0Mod;
    xp[1] = x1Mod;
    xp[2] = x1Mod + xSideOffset / 2;
    xp[var3] = x0Mod + xSideOffset / 2;
    xp[var4] = x0Mod;

  }

  /**
   * Calculate the coordinate when going straight.
   * @param aPoints the x points.
   * @param bPoints the y points.
   */
  private void makeStraightPolygon(final int[] aPoints, final int[] bPoints) {
    Rectangle b = currentRail.bounds();
    int width = b.width;
    int height = b.height;
    int valueWid = width / var4;
    int valueHei = height / var8;

    aPoints[0] = valueWid;
    aPoints[1] =  var3 * valueWid;
//    aPoints[2] = 3 * valueWid;
    aPoints[2] = var3 * valueWid;
    aPoints[var3] = valueWid;
    aPoints[var4] = valueWid;

    bPoints[0] =   valueHei;
    bPoints[1] = valueHei;
    bPoints[2] = var7 * valueHei;
    bPoints[var3] = var7 * valueHei;
    bPoints[var4] = valueHei;
  }

  /**
   * Covert to a readable name.
   *
   * @return String Car.
   */
  @Override
  public String toString() {
    return "Car";
  }


}

