import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

/**
 * The Track class.  A Track object is made up of Rails, and
 * has zero or more trains in it.
 */
class Track extends Frame {

  /**
   * The Panel on which the Track appears.
   */
  private TrackPanel trackPanel;

  // ------------------------------------------------------------------
  // The following items are the Rails and Trains on the Track.

  // private Rail first;    // The first Rail in the graph.

  /**
   * variable to avoid magic number.
   */
  private final int var = 10;
  /**
   * variable to avoid magic number.
   */
  private final int var1 = 20;
  /**
   * variable to avoid magic number.
   */
  private final int var3 = 3;
  /**
   * variable to avoid magic number.
   */
  private final int var4 = 4;
  /**
   * variable to avoid magic number.
   */
  private final int var5 = 5;
  /**
   * variable to avoid magic number.
   */
  private final int var6 = 6;
  /**
   * variable to avoid magic number.
   */
  private final int var7 = 7;
  /**
   * variable to avoid magic number.
   */
  private final int var8 = 8;
  /**
   * The maximum number of trains I can hold.
   */
  private Train[] trainList = new Train[var];
  /**
   * the  current number of trains.
   */
  private int numTrains = 0;

  /**
   * The grid of rails.
   */
  private Rail[][] rails;

  // Whether my Trains are running.  If true, no more Rails can be placed.
//  private boolean running = false;

  // ------------------------------------------------------------------
  // The following buttons define the interface for running
  // and saving me.
//  private Button runStopButton, quitButton;

  // The following buttons are used to control the track.
//  private Button accelButton, decelButton, accelLotsButton, decelLotsButton;

  // Add the buttons for creating Rails.

  /**
   * To build the Track.
   */
  private void buildTrack() {
    trackPanel = new TrackPanel();
    add("Center", trackPanel);

//    runStopButton = new Button("Run");
//    quitButton = new Button("Quit");
//    accelButton = new Button("Accelerate");
//    decelButton = new Button("Decelerate");
//    accelLotsButton = new Button("Accelerate A Lot");
//    decelLotsButton = new Button("Decelerate A Lot");

    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(0, 1));
    p2.add(new Button("Run"));
    p2.add(new Button("Accelerate A Lot"));
    p2.add(new Button("Decelerate A Lot"));
    p2.add(new Button("Accelerate"));
    p2.add(new Button("Decelerate"));
    p2.add(new Button("Quit"));
    add("East", p2);

    pack();
  }

  // Read Rail-placing commands from the user.

  /**
   * To handle the event.
   *
   * @param evt event
   * @return true if have event.
   */
  public boolean handleEvent(final Event evt) {
    Object target = evt.target;

    if (evt.id == Event.ACTION_EVENT) {
      if (target instanceof Button) {
        if ("Run".equals(evt.arg)) {
//          running = true;
          for (int i = 0; i < numTrains; i++) {
            trainList[i].start();
          }
          ((Button) target).setLabel("Suspend");
        } else if ("Suspend".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].suspend();
          }
//          running = false;
          ((Button) target).setLabel("Resume");
        } else if ("Resume".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].resume();
          }
//          running = false;
          ((Button) target).setLabel("Suspend");
        } else if ("Accelerate".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].accelerate();
          }
        } else if ("Decelerate".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].decelerate();
          }
        } else if ("Accelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].accelerateALot();
          }
        } else if ("Decelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].decelerateALot();
          }
        } else if ("Quit".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].stop();
          }
//          running = false;
          System.exit(0);
        }
        return true;
      }
    }

    // If we get this far, I couldn't handle the event
    return false;
  }

  // If test and r1 != null and r1.exitOK(r1), connect or unrester
  // r1 and r2 depending on whether r2's exits match r1's.

  /**
   * Unregister if not exit OK.
   *
   * @param test test.
   * @param r1 one rail.
   * @param r2 the other rail.
   * @param d the direction .
   */
  private void registerOrUnRegister(final boolean test,
      final Rail r1, final Rail r2,
      final Direction d) {
    if (test && r1 != null && r1.exitOK(d)) {
      if (r2.exitOK(d.opposite())) {
        connectRails(r1, r2, d);
      } else {
        r1.unRegister(d);
      }
    }

  }


  /**
   * Connect the Rail at (row,col) to its neighbours.
   *
   * @param row row.
   * @param col col.
   */
  protected void connectRail(final int row, final int col) {
    Rail r = rails[row][col];

    Direction north = new Direction("north");
    Direction south = new Direction("south");
    Direction east = new Direction("east");
    Direction west = new Direction("west");

    if (r != null) {
      if (row > 0) {
        Rail rN = rails[row - 1][col];
        registerOrUnRegister(row > 0, rN, r, south);
      } else {
        Rail rN = null;
        registerOrUnRegister(row > 0, rN, r, south);
      }
      if (row < rails.length - 1) {
        Rail rS = rails[row + 1][col];
        registerOrUnRegister(row < rails.length - 1, rS, r, north);
      } else {
        Rail rS = null;
        registerOrUnRegister(row < rails.length - 1, rS, r, north);
      }
      if (col < rails[0].length - 1) {
        Rail rE = rails[row][col + 1];
        registerOrUnRegister(col < rails[0].length - 1, rE, r, west);
      } else {
        Rail rE = null;
        registerOrUnRegister(col < rails[0].length - 1, rE, r, west);
      }
      if (col > 0) {
        Rail rW = rails[row][col - 1];
        registerOrUnRegister(col > 0, rW, r, east);
      } else {
        Rail rW = null;
        registerOrUnRegister(col > 0, rW, r, east);
      }
    }
  }


  /**
   * Connect rails r1 and r2; r2 is in direction d from r1.
   *
   * @param r1 one rail.
   * @param r2 the other rail to connect.
   * @param d direction
   */
  private void connectRails(final Rail r1, final Rail r2, final Direction d) {
    r1.register(r2, d);
    r2.register(r1, d.opposite());
  }

  // Add e to the rail at location loc.

  /**
   * Add another Car e to the rail at location loc.
   *
   * @param loc location.
   * @param e Another car.
   */
  void addCar(final GridLoc loc, final Car e) {
    rails[loc.getRow()][loc.getCol()].enter(e);
    e.setRail(rails[loc.getRow()][loc.getCol()]);
  }

  // paint
  // ------------------------------------------------------------------
  // Paint the display.

  /**
   * paint the display.
   *
   * @param g graph.
   */
  public void paint(final Graphics g) {
    update(g);
  }

  // update
  // ------------------------------------------------------------------
  // Update the display; tell all my Tracks to update themselves.

  /**
   * Update the dispaly.
   *
   * @param g graph.
   */
  public void update(final Graphics g) {

    trackPanel.repaint();
  }

  // Add T to myself.

  /**
   * Add T to myself.
   *
   * @param t train.
   */
  void addTrain(final Train t) {
    trainList[numTrains] = t;
    numTrains++;
  }

  /**
   * create my own rail.
   */
  private void createRail() {
    TwoDirStr twoDirStr = new TwoDirStr();

    TwoDirCor twoDirC = new TwoDirCor();

    rails[var6][var7] = twoDirStr.getStRail("EWRail", new GridLoc(var6, var7));
    connectRails(rails[var6][var6], rails[var6][var7], new Direction("east"));

    rails[var6][var8] = twoDirC.getCoRail("NWRail", new GridLoc(var6, var8));
    connectRails(rails[var6][var8], rails[var6][var7], new Direction("west"));

    rails[var5][var8] = twoDirStr.getStRail("NSRail", new GridLoc(var5, var8));
    connectRails(rails[var6][var8], rails[var5][var8], new Direction("north"));

    rails[var4][var8] = twoDirStr.getStRail("NSRail", new GridLoc(var4, var8));
    connectRails(rails[var5][var8], rails[var4][var8], new Direction("north"));

    rails[var3][var8] = twoDirStr.getStRail("NSRail", new GridLoc(var3, var8));
    connectRails(rails[var4][var8], rails[var3][var8], new Direction("north"));

    rails[2][var8] = twoDirStr.getStRail("NSRail", new GridLoc(2, var8));
    connectRails(rails[var3][var8], rails[2][var8], new Direction("north"));

    rails[1][var8] = twoDirStr.getStRail("NSRail", new GridLoc(1, var8));
    connectRails(rails[2][var8], rails[1][var8], new Direction("north"));

    rails[0][var8] = twoDirC.getCoRail("SWRail", new GridLoc(1, var8));
    connectRails(rails[0][var8], rails[1][var8], new Direction("south"));

    rails[0][var7] = twoDirStr.getStRail("EWRail", new GridLoc(0, var7));
    connectRails(rails[0][var7], rails[0][var8], new Direction("east"));

    rails[0][var6] = twoDirStr.getStRail("EWRail", new GridLoc(0, var6));
    connectRails(rails[0][var6], rails[0][var7], new Direction("east"));

    rails[0][var5] = twoDirStr.getStRail("EWRail", new GridLoc(0, var5));
    connectRails(rails[0][var5], rails[0][var6], new Direction("east"));

    rails[0][var4] = twoDirStr.getStRail("EWRail", new GridLoc(0, var4));
    connectRails(rails[0][var4], rails[0][var5], new Direction("east"));

    rails[0][var3] = twoDirStr.getStRail("EWRail", new GridLoc(0, var3));
    connectRails(rails[0][var3], rails[0][var4], new Direction("east"));
    connectRails(rails[0][var3], rails[0][2], new Direction("west"));
  }

  /**
   * Set up a new Track.
   */
  Track() {

    ThreeDirRail thrDirR = new ThreeDirRail();
    TwoDirCor twoDirC = new TwoDirCor();
    TwoDirStr twoDirStr = new TwoDirStr();
    rails = new Rail[var1][var1];
    buildTrack();

    for (int row = 0; row < rails.length; row++) {
      for (int col = 0; col < rails[0].length; col++) {
        rails[row][col] = new EmptyRail();
      }
    }

    rails[0][0] = twoDirC.getCoRail("SERail", new GridLoc(0, 0));

    rails[0][1] = twoDirStr.getStRail("EWRail", new GridLoc(0, 1));
    connectRails(rails[0][0], rails[0][1], new Direction("east"));

    rails[0][2] = thrDirR.getSwRail("WESRail", new GridLoc(0, 2));
    connectRails(rails[0][1], rails[0][2], new Direction("east"));
    connectRails(rails[0][2], rails[0][var3], new Direction("east"));

    rails[1][2] = twoDirStr.getStRail("NSRail", new GridLoc(1, 2));
    connectRails(rails[0][2], rails[1][2], new Direction("south"));

    rails[2][2] = new CrossRail(new GridLoc(2, 2));
    connectRails(rails[1][2], rails[2][2], new Direction("south"));

    rails[2][var3] = twoDirStr.getStRail("EWRail", new GridLoc(2, var3));
    connectRails(rails[2][2], rails[2][var3], new Direction("east"));

    rails[2][var4] = twoDirStr.getStRail("EWRail", new GridLoc(2, var4));
    connectRails(rails[2][var3], rails[2][var4], new Direction("east"));

    rails[2][var5] = new CrossRail(new GridLoc(2, var5));
    connectRails(rails[2][var4], rails[2][var5], new Direction("east"));

    rails[2][var6] = twoDirC.getCoRail("SWRail", new GridLoc(2, var6));
    connectRails(rails[2][var5], rails[2][var6], new Direction("east"));

    rails[var3][var6] = twoDirC.getCoRail("NWRail", new GridLoc(var3, var6));
    connectRails(rails[2][var6], rails[var3][var6], new Direction("south"));

    rails[var3][var5] = twoDirC.getCoRail("NERail", new GridLoc(var3, var5));
    connectRails(rails[var3][var6], rails[var3][var5], new Direction("west"));
    connectRails(rails[2][var5], rails[var3][var5], new Direction("south"));

    rails[1][var5] = twoDirC.getCoRail("SERail", new GridLoc(1, var5));
    connectRails(rails[2][var5], rails[1][var5], new Direction("north"));

    rails[1][var6] = twoDirStr.getStRail("EWRail", new GridLoc(1, var6));
    connectRails(rails[1][var5], rails[1][var6], new Direction("east"));

    rails[1][var7] = twoDirC.getCoRail("SWRail", new GridLoc(1, var7));
    connectRails(rails[1][var6], rails[1][var7], new Direction("east"));

    rails[2][var7] = twoDirStr.getStRail("NSRail", new GridLoc(2, var7));
    connectRails(rails[1][var7], rails[2][var7], new Direction("south"));

    rails[var3][var7] = twoDirStr.getStRail("NSRail", new GridLoc(var3, var7));
    connectRails(rails[2][var7], rails[var3][var7], new Direction("south"));

    rails[var4][var7] = twoDirC.getCoRail("NWRail", new GridLoc(var4, var7));
    connectRails(rails[var3][var7], rails[var4][var7], new Direction("south"));

    rails[var4][var6] = thrDirR.getSwRail("EWSRail", new GridLoc(var4, var6));
    connectRails(rails[var4][var7], rails[var4][var6], new Direction("west"));

    rails[var4][var5] = thrDirR.getSwRail("WESRail", new GridLoc(var4, var5));
    connectRails(rails[var4][var6], rails[var4][var5], new Direction("west"));

    rails[var4][var4] = twoDirC.getCoRail("NERail", new GridLoc(var4, var4));
    connectRails(rails[var4][var5], rails[var4][var4], new Direction("west"));

    rails[var3][var4] = twoDirC.getCoRail("SWRail", new GridLoc(var3, var4));
    connectRails(rails[var4][var4], rails[var3][var4], new Direction("north"));

    rails[var3][var3] = thrDirR.getSwRail("EWSRail", new GridLoc(var3, var3));
    connectRails(rails[var3][var4], rails[var3][var3], new Direction("west"));

    rails[var4][var3] = thrDirR.getSwRail("SNWRail", new GridLoc(var4, var3));
    connectRails(rails[var4][var3], rails[var3][var3], new Direction("north"));

    rails[var5][var3] = twoDirC.getCoRail("NERail", new GridLoc(var5, var3));
    connectRails(rails[var5][var3], rails[var4][var3], new Direction("north"));

    rails[var5][var4] = twoDirStr.getStRail("EWRail", new GridLoc(var5, var4));
    connectRails(rails[var5][var4], rails[var5][var3], new Direction("west"));

    rails[var5][var5] = twoDirC.getCoRail("NWRail", new GridLoc(var5, var5));
    connectRails(rails[var5][var5], rails[var5][var4], new Direction("west"));
    connectRails(rails[var5][var5], rails[var4][var5], new Direction("north"));

    rails[var5][var6] = twoDirStr.getStRail("NSRail", new GridLoc(1, 0));
    connectRails(rails[var5][var6], rails[var4][var6], new Direction("north"));
    connectRails(rails[var6][var6], rails[var5][var6], new Direction("north"));

    rails[var6][var6] = twoDirC.getCoRail("NERail", new GridLoc(var6, var6));
    connectRails(rails[var6][var6], rails[var5][var6], new Direction("north"));

    createRail();

    rails[var3][2] = new CrossRail(new GridLoc(var3, 2));
    connectRails(rails[var3][var3], rails[var3][2], new Direction("west"));
    connectRails(rails[2][2], rails[var3][2], new Direction("south"));

    rails[var4][2] = thrDirR.getSwRail("WENRail", new GridLoc(var4, 2));
    connectRails(rails[var3][2], rails[var4][2], new Direction("south"));
    connectRails(rails[var4][var3], rails[var4][2], new Direction("west"));

    rails[var4][1] = twoDirC.getCoRail("NERail", new GridLoc(var4, 1));
    connectRails(rails[var4][2], rails[var4][1], new Direction("west"));

    rails[var3][1] = twoDirC.getCoRail("SERail", new GridLoc(var3, 1));
    connectRails(rails[var3][2], rails[var3][1], new Direction("west"));
    connectRails(rails[var3][1], rails[var4][1], new Direction("south"));

    rails[2][1] = twoDirStr.getStRail("EWRail", new GridLoc(2, 1));
    connectRails(rails[2][2], rails[2][1], new Direction("west"));

    rails[2][0] = twoDirC.getCoRail("NERail", new GridLoc(2, 0));
    connectRails(rails[2][1], rails[2][0], new Direction("west"));

    rails[1][0] = twoDirStr.getStRail("NSRail", new GridLoc(1, 0));
    connectRails(rails[2][0], rails[1][0], new Direction("north"));
    connectRails(rails[1][0], rails[0][0], new Direction("north"));

    trackPanel.addToPanel(rails);
  }

}
