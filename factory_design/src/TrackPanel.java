import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;

/**
 * Panel for Track.
 */
class TrackPanel extends Panel {
  // ------------------------------------------------------------------
  // The following items are used for double buffering.

  /**
   * The buffer in which to draw the image; used for double buffering.
   */
  private Image backBuffer;

  /**
   * The graphics context to use when double buffering.
   */
  private Graphics backG;

//  private Rail[][] rails;    // The grid of rails.
  /**
   * variable to avoid magic number.
   */
  private final int varten = 10;

  /**
   * Add rail to panel.
   *
   * @param r rail
   */
  void addToPanel(final Rail[][] r) {
//    rails = r;

    setLayout(new GridLayout(r.length, r[0].length, 0, 0));

    for (Rail[] item : r) {
      for (Rail rail : item) {
        add("", rail);
      }
    }
  }

  // paint
  // ------------------------------------------------------------------
  // Paint the display.

  /**
   * Paint the display.
   *
   * @param g graph
   */
  public void paint(final Graphics g) {
    update(g);
  }

  /**
   * Inset size.
   *
   * @return An inset.
   */
  public Insets insets() {
    return new Insets(varten, varten, varten, varten);
  }

  // update
  // ------------------------------------------------------------------
  // Update the display; tell all my Tracks to update themselves.

  /**
   * Update the display.
   *
   * @param g graph
   */
  public void update(final Graphics g) {

    // Get my width and height.
    int w = bounds().width;
    int h = bounds().height;

    // If we don't yet have an Image, create one.
    if (backBuffer == null
        || backBuffer.getWidth(null) != w
        || backBuffer.getHeight(null) != h) {
      backBuffer = createImage(w, h);
      if (backBuffer != null) {

        // If we have a backG, it belonged to an old Image.
        // Get rid of it.
        if (backG != null) {
          backG.dispose();
        }
        backG = backBuffer.getGraphics();
      }
    }

    if (backBuffer != null) {

      // Fill in the Graphics context backG.
      g.setColor(Color.white);
      g.fillRect(0, 0, w, h);

      // Now copy the new image to g.
      // g.drawImage(backBuffer, 0, 0, null);
    }

  }
}

