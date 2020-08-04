import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Pawn.
 */
public class Pawn extends Piece {

    /**
     * The Color.
     */
    String color;

    /**
     * Instantiates a new Pawn.
     *
     * @param color the color
     */
    public Pawn(String color) {
        this.color = color;
    }

    /**
     * Finds all the possible positions that the pawn can go to from the source tile
     *
     * @param sourceTile the source tile that the pawn is starting from
     * @return a list of all the pairs denoting where the pawn can go to
     */
    @Override
    public List<Pair> inSight(Tile sourceTile) {
        List<Pair> possible = new ArrayList<>();
        ChessBoard board = new ChessBoard();

        int sourceRow = sourceTile.getRow();
        int sourceCol = sourceTile.getCol();

        switch (color) {
            case "Black":
                if (sourceRow == 1) {
                    if (!board.isPiece(sourceRow + 2, sourceCol)) {
                        possible.add(new Pair(sourceRow + 2, sourceCol));
                    }
                }
                if (!board.isPiece(sourceRow + 1, sourceCol)) {
                    possible.add(new Pair(sourceRow + 1, sourceCol));
                }
                if (board.isPiece(sourceRow + 1, sourceCol + 1) &&
                        board.getColor(sourceRow + 1, sourceCol + 1) != null &&
                        board.getColor(sourceRow + 1, sourceCol + 1).equals("White")) {
                    possible.add(new Pair(sourceRow + 1, sourceCol + 1));
                }
                if (board.isPiece(sourceRow + 1, sourceCol - 1) &&
                        board.getColor(sourceRow + 1, sourceCol - 1) != null &&
                        board.getColor(sourceRow + 1, sourceCol - 1).equals("White")) {
                    possible.add(new Pair(sourceRow + 1, sourceCol - 1));
                }
                break;
            case "White":
                if (sourceRow == 6) {
                    if (!board.isPiece(sourceRow - 2, sourceCol)) {
                        possible.add(new Pair(sourceRow - 2, sourceCol));
                    }
                }
                if (!board.isPiece(sourceRow - 1, sourceCol)) {
                    possible.add(new Pair(sourceRow - 1, sourceCol));
                }
                if (board.isPiece(sourceRow - 1, sourceCol - 1) &&
                        board.getColor(sourceRow - 1, sourceCol - 1) != null &&
                        board.getColor(sourceRow - 1, sourceCol - 1).equals("Black")) {
                    possible.add(new Pair(sourceRow - 1, sourceCol - 1));
                }
                if (board.isPiece(sourceRow - 1, sourceCol + 1) &&
                        board.getColor(sourceRow - 1, sourceCol + 1) != null &&
                        board.getColor(sourceRow - 1, sourceCol + 1).equals("Black")) {
                    possible.add(new Pair(sourceRow - 1, sourceCol + 1));
                }
                break;
        }

        return possible;
    }


    /**
     * Checks whether the destination tile is as part of the possible positions that the pawn can go to.
     *
     * @param sourceRow the source row
     * @param sourceCol the source col
     * @param destRow   the dest row
     * @param destCol   the dest col
     * @return a boolean representing whether the piece can go from the position tile to the destination tile
     */
    @Override
    public boolean tracePaths(int sourceRow, int sourceCol, int destRow, int destCol) {
        //Make a list of all the possible spots using the source row and column:
        List<Pair> possible = inSight(new Tile(null, sourceRow, sourceCol));

        Pair dest = new Pair(destRow, destCol);
        if (possible.contains(dest)) {
            return true;
        }
        return false;
    }

    /**
     * A getter to return the color of the pawn piece
     *
     * @return a string representing the color of the pawn
     */
    public String getColor() {
        return color;
    }

    /**
     * An overridden version of the toString method for pawn pieces.
     *
     * @return a string is returned when one prints a pawn
     */
    public String toString() {
        if (color.equals("White")) {
            System.out.print("WP ");
        } else {
            System.out.print("BP ");
        }
        return "";
    }

    /**
     * A getter method for the name of the name
     *
     * @return a string that represents the name of the pawn
     */
    public String getName() {
        switch (color) {
            case "White":
                return "WP";
            case "Black":
                return "BP";
        }
        return "";
    }

    /**
     * An overriden version of the equals method to see if two pawns are the same piece.
     *
     * @param o the other pawn that we are comparing with.
     * @return a boolean denoting whether the two pawns are the same piece
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

        if (other.getName().charAt(1) == 'P') {
            return true;
        }

        return false;
    }
}
