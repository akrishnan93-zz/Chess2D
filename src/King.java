import java.util.ArrayList;
import java.util.List;

/**
 * Anand Krishnan
 * Class for King pieces
 */
public class King extends Piece {

    /**
     * The Color of the king piece
     */
    String color;

    /**
     * Instantiates a new King.
     *
     * @param color the color of the king piece
     */
    public King(String color) {
        this.color = color;
    }

    /**
     * Decide whether the destination position is one of the possible positions for this piece
     *
     * @param sourceRow the row of the source position
     * @param sourceCol the column of the source position
     * @param destRow   the row of the destination position
     * @param destCol   the column of the destination position
     * @returns boolean reflect whether the destination position given is possible with the source destination
     */
    @Override
    public boolean tracePaths(int sourceRow, int sourceCol, int destRow, int destCol) {
        List<Pair> possible = inSight(new Tile(null, sourceRow, sourceCol));

        Pair dest = new Pair(destRow, destCol);
        if (possible.contains(dest)) {
            return true;
        }
        return false;
    }

    /**
     * Getter for the color of the king piece
     *
     * @return a string representing the color of the king piece
     */
    public String getColor() {
        return color;
    }

    /**
     * The overriden version of the toString method for king pieces.
     *
     * @return a string reflecting the toString composition for it.
     */
    @Override
    public String toString() {
        if (color.equals("White")) {
            System.out.print("WK ");
        } else {
            System.out.print("BK ");
        }
        return "";
    }

    /**
     * A getter method for the name of the king.
     *
     * @return a string representing the name of the king peice in question
     */
    public String getName() {
        switch (color) {
            case "White":
                return "WK";
            case "Black":
                return "BK";
        }
        return "";
    }

    /**
     * Finds all the possible positions that the King can move to relative to the source position
     *
     * @param sourceTile the tile fom which we are moving
     * @return a list of pairs that represents all the positions that we can jump to relative the the source
     */
    @Override
    public List<Pair> inSight(Tile sourceTile) {
        //Make a list of all the possible spots using the source row and column:
        List<Pair> possible = new ArrayList<>();
        ChessBoard board = new ChessBoard();

        int sourceRow = sourceTile.getRow();
        int sourceCol = sourceTile.getCol();

        if (!board.isPiece(sourceRow + 1, sourceCol)) {
            possible.add(new Pair(sourceRow + 1, sourceCol));
        }
        if (!board.isPiece(sourceRow - 1, sourceCol)) {
            possible.add(new Pair(sourceRow - 1, sourceCol));
        }
        if (!board.isPiece(sourceRow, sourceCol + 1)) {
            possible.add(new Pair(sourceRow, sourceCol + 1));
        }
        if (!board.isPiece(sourceRow, sourceCol - 1)) {
            possible.add(new Pair(sourceRow, sourceCol - 1));
        }
        if (!board.isPiece(sourceRow + 1, sourceCol + 1)) {
            possible.add(new Pair(sourceRow + 1, sourceCol + 1));
        }
        if (!board.isPiece(sourceRow - 1, sourceCol - 1)) {
            possible.add(new Pair(sourceRow - 1, sourceCol - 1));
        }
        if (!board.isPiece(sourceRow + 1, sourceCol - 1)) {
            possible.add(new Pair(sourceRow + 1, sourceCol - 1));
        }
        if (!board.isPiece(sourceRow - 1, sourceCol + 1)) {
            possible.add(new Pair(sourceRow - 1, sourceCol + 1));
        }

        if (board.isPiece(sourceRow + 1, sourceCol) &&
                !board.getColor(sourceRow + 1, sourceCol).equals(this.color)) {
            possible.add(new Pair(sourceRow + 1, sourceCol));
        }
        if (board.isPiece(sourceRow - 1, sourceCol) &&
                !board.getColor(sourceRow - 1, sourceCol).equals(this.color)) {
            possible.add(new Pair(sourceRow - 1, sourceCol));
        }
        if (board.isPiece(sourceRow, sourceCol + 1) &&
                !board.getColor(sourceRow, sourceCol + 1).equals(this.color)) {
            possible.add(new Pair(sourceRow, sourceCol + 1));
        }
        if (board.isPiece(sourceRow, sourceCol - 1) &&
                !board.getColor(sourceRow, sourceCol - 1).equals(this.color)) {
            possible.add(new Pair(sourceRow, sourceCol - 1));
        }
        if (board.isPiece(sourceRow + 1, sourceCol + 1) &&
                !board.getColor(sourceRow + 1, sourceCol + 1).equals(this.color)) {
            possible.add(new Pair(sourceRow + 1, sourceCol + 1));
        }
        if (board.isPiece(sourceRow - 1, sourceCol - 1) &&
                !board.getColor(sourceRow - 1, sourceCol - 1).equals(this.color)) {
            possible.add(new Pair(sourceRow - 1, sourceCol - 1));
        }
        if (board.isPiece(sourceRow + 1, sourceCol - 1) &&
                !board.getColor(sourceRow + 1, sourceCol - 1).equals(this.color)) {
            possible.add(new Pair(sourceRow + 1, sourceCol - 1));
        }
        if (board.isPiece(sourceRow - 1, sourceCol + 1) &&
                !board.getColor(sourceRow - 1, sourceCol + 1).equals(this.color)) {
            possible.add(new Pair(sourceRow - 1, sourceCol + 1));
        }

        return possible;
    }

    /**
     * The overriden version of the equals method for seeing of two kings are equal
     *
     * @param o the other king with which we are comparing
     * @return a boolean representing whether the two kings are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() == this.getClass()) {
            return true;
        }
        Piece other = (Piece) o;
        if (other.getName().charAt(1) == 'K') {
            return true;
        }

        return false;
    }
}
