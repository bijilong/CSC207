/*

The Train class.  Trains have an Engine, followed by one or more Cars,
followed by a Caboose.  There is no limit to the length of a Train.  The
train has a speed, which is related to the size of the engine, the weight
of the whole train, and the amount of power flowing through the tracks.

A train has a delay, which is directly related to the speed -- the faster
the train is moving, the shorter the delay.  Each turn, a Train will move
one track piece in the current direction.

*/


/**
 * The Train class.  Trains have an Engine, followed by one or more Cars,
 * followed by a Caboose.A train has a delay, which is directly related
 * to the speed -- the faster the train is moving, the shorter the
 * delay.  Each turn, a Train will move one track piece in
 * the current direction.
 */
class Train extends Thread {

//  private int totalWeight;    // The sum of the weights of my cars.
  /**
   * The delay of the train.
   */
  private int delay;          // The amount of time between each of my turns.
//  private boolean forward;    // Whether I am moving forward.
  /**
   * The first Car as engine.
   */
  private Car engine;      // The Car pulling the train.
  /**
   * The last Car as engine.
   */
  private Car caboose;    // The Car at the end of the train.
  /**
   * The Track on which I am running.
   */
  private Track theTrack;   // The Track on which I am running.
  /**
   * The number of cars in this train.
   */
  private int numCars = 0;
  /**
   * variable to avoid magic number.
   */
  private final int vartwe = 20;

  /**
   * There can have more than one train run at the same time.
   *
   * @param threadName thread
   */


  Train(final String threadName) {
    super(threadName);
  }


  /**
   * dd Car t to the end of me.
   *
   * @param t Car to be added.
   */
  void addToTrain(final Car t) {
    if (engine != null) {
      caboose.setNextCar(t);
    } else {
      engine = t;
    }

    caboose = t;
    numCars++;
  }


  /**
   * Set the speed of the train.
   *
   * @param d the delay.
   */
  void setSpeed(final int d) {
    delay = d;
  }


  /**
   * Add train to the track at location loc moving in direction dir.
   *
   * @param t the track to add.
   * @param dir the direction to add.
   * @param loc the location to add.
   */
  void addToTrack(final Track t, final Direction dir, final GridLoc loc) {
    theTrack = t;
    theTrack.addTrain(this);
    Direction s = dir;

    Car currCar = engine;
    while (currCar != null) {
      currCar.setDirection(s);
      theTrack.addCar(loc, currCar);

      // Now figure out the dir for the next Car,
      // and the next loc.

      if (s.equals("north")) {
        loc.setRow(loc.getRow() - 1);
      } else if (s.equals("south")) {
        loc.setRow(loc.getRow() + 1);
      } else if (s.equals("east")) {
        loc.setCol(loc.getCol() + 1);
      } else if (s.equals("west")) {
        loc.setCol(loc.getCol() - 1);
      }

      Direction nD = currCar.getCurrentRail().exit(s);
      Rail nextRail = currCar.getCurrentRail().nextRail(nD);

      // Now I know the Rail on which the next currCar will
      // be.  Find out how it got on to it.
      s = nextRail.exit(s.opposite());

      currCar = currCar.getNextCar();
    }
  }


  /**
   * Halve my delay.
   */
  void accelerateALot() {
    delay /= 2;
  }


  /**
   * Double my delay.
   */
  void decelerateALot() {
    delay *= 2;
  }


  /**
   * Speed up by a factor of 20ms.
   */
  void accelerate() {
    delay -= vartwe;
  }


  /**
   * Slow down by a factor of 20ms.
   */
  void decelerate() {
    delay += vartwe;
  }

  /**
   * Run the train.
   */
  public void run() {
    while (true) {
      engine.move();

      // Sleep for 1 second.
      try {
        sleep(delay);
      } catch (InterruptedException e) {
      }
    }
  }

}

