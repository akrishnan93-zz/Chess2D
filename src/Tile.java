/**
 * Anand Krishnan
 * Class for tile object
 */
public class Tile {

    /**
     * The piece that is at this certain tile. Null if there is none.
     */
    Piece piece;
    /**
     * The row that points to this tile.
     */
    int row;
    /**
     * The column that points to this tile.
     */
    int col;

    /**
     * Instantiates a new Tile.
     *
     * @param piece the piece to be added to this tile
     * @param row   the row that points to this tile
     * @param col   the col that points to this tile
     */
    public Tile(Piece piece, int row, int col) {
        this.piece = piece;
        this.row = row;
        this.col = col;
    }

    /**
     * Getter for the piece that exists on this tile (if applicable)
     *
     * @return the piece that is on this tile
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets piece to a certain tile
     *
     * @param newPiece the new piece that is to be added to this tile
     */
    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    }

    /**
     * Remove piece on a certain tile
     */
    public void removePiece() {
        this.piece = null;
    }

    /**
     * Getter for the row corresponding to this tile.
     *
     * @return the row pointing to this tile.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for the col corresponding to this tile.
     *
     * @return the col pointing to this tile
     */
    public int getCol() {
        return col;
    }

    /**
     * Overidde version for tile objects
     *
     * @return the string that represents the toString phrase for tile objects
     */
    @Override
    public String toString() {
        System.out.println("ROW: " + row + " - COL: " + col);
        return "";
    }


}
