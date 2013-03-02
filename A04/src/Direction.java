/**
 * One of 9 possible directions an object can move on a 2D board of squares.
 * Includes the 8 cardinal and intermediate compass directions (N, NE, E, etc.)
 * plus HERE, which means the current square.
 * <p>
 * Also provides a number of methods that can be called on a Direction object.
 * Modifiers indicate the change involved with moving the given direction on
 * a 2D board of squares.  Whether for (row, col) or (x, y), the origin (0,0)
 * is assumed to be the top-left of the board or screen.
 *
 * @author Zach Tomaszewski
 * @version 19 Nov 2008
 */
public enum Direction {
  N, NE, E, SE, S, SW, W, NW, HERE;

  /**
   * Returns the X/column change on the screen that is associated with
   * this direction: -1 for W, 0 for N/S, and +1 for E.
   */
  public int getColModifier() {
    int mod;
    switch (this) {
      case NW:
      case W:
      case SW:
        mod = -1;
        break;
      case NE:
      case E:
      case SE:
        mod = +1;
        break;
      default:
        mod = 0;
        break;
      }
    return mod;
  }

  /**
   * Returns the Y/row change on the screen that is associated with
   * this direction: -1 for N, 0 for E/W, and +1 for south.
   */
  public int getRowModifier() {
    int mod;
    switch (this) {
      case N:
      case NE:
      case NW:
        mod = -1;
        break;
      case S:
      case SE:
      case SW:
        mod = +1;
        break;
      default:
        mod = 0;
        break;
      }
    return mod;
  }

  /** As {@link #getColModifier()} */
  public int getXModifier() {
    return this.getColModifier();
  }

  /** As {@link #getRowModifier()} */
  public int getYModifier() {
    return this.getRowModifier();
  }

  /**
   * Returns the direction that is the opposite of this one.
   * For example, <code>Direction.NE.reverse() == Direction.SW</code>.
   * (The opposite of HERE is still HERE though.)
   */
  public Direction reverse() {
    if (this == HERE) {
      return this;
    }else {
      int reversed = (this.ordinal() + 4) % 8;
      Direction[] dirs = Direction.values();
      return dirs[reversed];
    }
  }

}