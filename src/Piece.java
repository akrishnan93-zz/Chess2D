import java.util.List;

/**
 * The type Piece.
 */
public abstract class Piece {

    /**
     * The Color.
     */
    String color;

    /**
     * Trace paths boolean.
     *
     * @param sourceRow the source row
     * @param sourceCol the source col
     * @param destRow   the dest row
     * @param destCol   the dest col
     * @return the boolean
     */
    public abstract boolean tracePaths(int sourceRow, int sourceCol, int destRow, int destCol);

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * In sight list.
     *
     * @param sourceTile the source tile
     * @return the list
     */
    public abstract List<Pair> inSight(Tile sourceTile);

}
