import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The Rail class.  A Rail object is a piece of track.  It knows
 * whether there is a Train on it or not, and Trains can enter()
 * and leave().  Given an entrance, a Rail can report where the exit()
 * is.
 */
abstract class Rail extends Canvas {


  /**
   * The color of the Rail.
   */
  private Color color;

  /**
   * Whether the current Rail has Train.
   */
  private boolean haveATrain;
  /**
   * the car in this Rail.
   */
  private Car currentCar;


  /**
   * the location of this rail.
   */
  private GridLoc location;
  /**
   * Create a variable for 20 avoid magic number.
   */
  private final int va = 20;

  /**
   * whether the state change.
   *
   * @return true if state change,false otherwise.
   */
  public boolean isStateChanged() {
    return stateChanged;
  }

  /**
   * Set state change.
   *
   * @param s Boolean to be set.
   */
  public void setStateChanged(final boolean s) {
    this.stateChanged = s;
  }

  /**
   * True if a car entered or left.
   */
  private boolean stateChanged = true;

  /**
   * Rail jas location and does not have train.
   *
   * @param loc the locaion of the rail.
   */
  Rail(final GridLoc loc) {
    location = loc;
    haveATrain = false;
  }

  /**
   * Rail does not have train and have size 20*20.
   */
  Rail() {
    super();
    haveATrain = false;
    Rectangle b = bounds();
    resize(va, va);
  }

  /**
   * Get the color.
   *
   * @return the color of the Rail
   */
  Color getColor() {
    return color;
  }

  /**
   * Set the color.
   *
   * @param c the color to be set.
   */
  void setColor(final Color c) {
    this.color = c;
  }
  // Return true iff I have a Car.

  /**
   * Check whether has train.
   * @return whether has train.
   */
  public boolean isHaveATrain() {
    return haveATrain;
  }

  /**
   * Set whether has train.
   * @param h the boolean to be set.
   */
  public void setHaveATrain(final boolean h) {
    this.haveATrain = h;
  }

  /**
   * Check whether the rail is occupied.
   *
   * @return whether the rail is occupied.
   */
  boolean occupied() {
    return haveATrain;
  }

  /**
   * Set the loc.
   *
   * @param loc the loc to be set.
   */
  public void setLoc(final GridLoc loc) {
    location = loc;
  }

  /**
   * Get the location of the Rail.
   *
   * @return the loc of the Rail.
   */
  public GridLoc getLoc() {
    return location;
  }


  /**
   * Redraw myself.
   *
   * @param g graph
   */
  public void draw(final Graphics g) {

    Rectangle b = bounds();
    g.setColor(Color.white);
    g.fillRect(0, 0, b.width - 1, b.height - 1);
    g.setColor(Color.lightGray);
    g.drawRect(0, 0, b.width - 1, b.height - 1);

    if (haveATrain) {
      currentCar.draw(g);
    }
  }

  // Register that a Train is on me.  Return true if succesful,
  // false otherwise.

  /**
   * Whether a new Car enter.
   *
   * @param newCar the car enter.
   * @return true if a car enter,else otherwise.
   */
  boolean enter(final Car newCar) {
    haveATrain = true;
    currentCar = newCar;
    return true;
  }

  /**
   * Register that a Train is no longer on me.
   */
  void leave() {
    haveATrain = false;
    repaint();
  }

  /**
   * Paint.
   *
   * @param g graph.
   */
  public void paint(final Graphics g) {
    draw(g);
  }

  // Return true if d is a valid direction for me.

  /**
   * Check whether can exit.
   *
   * @param d the direction entered.
   * @return whether can exit.
   */
  public abstract boolean exitOK(Direction d);

  // Register that Rail r is in Direction d.

  /**
   * Register the rail.
   *
   * @param r The rail to register.
   * @param d The direction entered.
   */
  public abstract void register(Rail r, Direction d);

  // Register that there is no Rail in Direction d.

  /**
   * Unregister the rail.
   *
   * @param d the direction enter
   */
  public abstract void unRegister(Direction d);


  /**
   * Given that d is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param d the direction entered.
   * @return the exit direction.
   */
  public abstract Direction exit(Direction d);


  /**
   * Given that d is the Direction from which a Car entered,
   * report which Rail is next.
   *
   * @param d the direction entered.
   * @return the next rail.
   */
  public abstract Rail nextRail(Direction d);

  /**
   * Covert to a readable name.
   *
   * @return String Rail.
   */
  public String toString() {
    return "Rail";
  }

}

