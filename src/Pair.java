/**
 * The type Pair.
 */
public class Pair {
    /**
     * The X.
     */
    int x;
    /**
     * The Y.
     */
    int y;

    /**
     * Instantiates a new Pair representing a tile on the chess board.
     *
     * @param x the x
     * @param y the y
     */
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * The overridden version of the equals method to see if two pairs are equal to each other
     *
     * @param obj the other object to be comparing this pair with
     * @return a boolean representing whether the two pairs are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        final Pair other = (Pair) obj;
        if (this.x == other.x && this.y == other.y) {
            return true;
        }

        return false;
    }

    /**
     * The overridden version of the toString method that returns the string that signifies this pair.
     *
     * @return a string representing the pair that is calling this method.
     */
    @Override
    public String toString() {
        return "R: " + x + " C: " + y;
    }
}