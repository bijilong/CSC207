// Author: Paul Gries
// Written: 27 June 1997
// Last revised: 29 June 1997

// Simulate trains running around a track.


import java.awt.Color;
import java.awt.Frame;

// class TrainSimulation
// ----------------------------------------------------------------------
// The class TrainSimulation contains all the methods and instance variables
// neccessary to keep track of and run the train simulation.

/**
 * The class TrainSimulation contains all the methods and instance variables
 * neccessary to keep track of and run the train simulation.
 */
public class TrainSimulation extends Frame {

  /**
   * variable to avoid magic number.
   */
  private final int varf = 4;
  /**
   * The Tracks on which the Trains run.
   */
  private Track[] tracks = new Track[varf];
  /**
   * variable to avoid magic number.
   */
  private final int vare = 8;

  /**
   * The Trains running on the Tracks.
   */
  private Train[] trains = new Train[vare];


  /**
   * The ThreadGroups; all Trains running on the same Track are in the same.
   */

  private ThreadGroup[] tG = new ThreadGroup[vare];

  /**
   * get ThreadGroup.
   *
   * @return ThreadGroup
   */
  public ThreadGroup[] gettG() {
    return tG;
  }

  /**
   * Set the ThreadGroup.
   *
   * @param t the ThreadGroup to be set.
   */
  public void settG(final ThreadGroup[] t) {
    this.tG = t;
  }

  // main
  // ------------------------------------------------------------------
  // This is where it all starts.

  /**
   * main
   * ------------------------------------------------------------------
   * This is where it all starts.
   *
   * @param args the args.
   */
  public static void main(final String[] args) {

    TrainSimulation t = new TrainSimulation();
    final int varff = 540;
    final int varfz = 400;
    final int varst = 620;
    final int varsf = 350;
    final int varfi = 5;
    // Track 2.
    t.tracks[0] = new Track();
    t.tracks[0].resize(varff, varfz);
    t.tracks[0].move(0, 0);
    t.tracks[0].setBackground(Color.white);
    t.tracks[0].show();

    t.trains[0] = new Train("Train 0");
    t.trains[0].addToTrain(new Car(Color.green));
    t.trains[0].addToTrain(new Car(Color.red));

    t.trains[1] = new Train("Train 1");
    t.trains[1].addToTrain(new Car(Color.green));
    t.trains[1].addToTrain(new Car(Color.red));

//        T.trains[2] = new Train();
//        T.trains[2].addToTrain(new Engine());
//        T.trains[2].addToTrain(new Caboose());

    t.trains[0].addToTrack(t.tracks[0], new Direction("east"),
        new GridLoc(2, 2));
    t.trains[0].setSpeed(varst);
    t.trains[1].addToTrack(t.tracks[0], new Direction("south"),
        new GridLoc(1, varfi));
    t.trains[1].setSpeed(varsf);
//        T.trains[2].addToTrack(T.tracks[0], new Direction("east"),
// new GridLoc(2, 2));
//        T.trains[2].setSpeed(754);

//        T.trains[0].start();
    //       T.trains[1].start();
//        T.trains[2].start();

/* Uncomment this to get more tracks!!!

        // Track 2.
        T.tracks[1] = new Track();
        T.tracks[1].resize (400, 400);
        T.tracks[1].move(410, 0);
        T.tracks[1].setBackground(Color.white);
        T.tracks[1].show();
        T.trains[3] = new Train();
        T.trains[3].addToTrain(new Engine());
        T.trains[4] = new Train();
        T.trains[4].addToTrain(new Engine());

        T.trains[3].addToTrack(T.tracks[1], new Direction("east"),
        new GridLoc(0, 0));
        T.trains[3].setSpeed(500);
        T.trains[4].addToTrack(T.tracks[1], new Direction("north"),
        new GridLoc(1, 2));
        T.trains[4].setSpeed(400);

        T.trains[3].start();
        T.trains[4].start();

        // Track 3.
        T.tracks[2] = new Track();
        T.tracks[2].resize (400, 400);
        T.tracks[2].move(400, 400);
        T.tracks[2].setBackground(Color.white);
        T.tracks[2].show();
        T.trains[5] = new Train();
        T.trains[5].addToTrain(new Engine());
        T.trains[6] = new Train();
        T.trains[6].addToTrain(new Engine());
        T.trains[5].addToTrack(T.tracks[2], new Direction("south"),
         new GridLoc(2, 4));
        T.trains[5].setSpeed(500);
        T.trains[6].addToTrack(T.tracks[2], new Direction("north"),
        new GridLoc(2, 0));
        T.trains[6].setSpeed(400);

        T.trains[5].start();
        T.trains[6].start();
*/
  }
}

