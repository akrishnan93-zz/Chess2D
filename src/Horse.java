import java.util.ArrayList;
import java.util.List;

/**
 * Anand Krishnan
 * Class for Horse Piece
 */
public class Horse extends Piece {

    /**
     * The color of the piece
     */
    String color;

    /**
     * Instantiates a new Horse.
     *
     * @param color the color of the new horse object
     */
    public Horse(String color) {
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
        //Make a list of all the possible spots using the source row and column:
        List<Pair> possible = inSight(new Tile(null, sourceRow, sourceCol));

        Pair dest = new Pair(destRow, destCol);
        if (possible.contains(dest)) {
            return true;
        }

        return false;
    }

    /**
     * Getter method for the color attribute of a horse object
     *
     * @return a String representing the color of the horse object
     */
    public String getColor() {
        return color;
    }

    /**
     * The overridden version of the object's toString method
     *
     * @return the string that represents the toString for a horse object
     */
    @Override
    public String toString() {
        if (color.equals("White")) {
            System.out.print("WH ");
        } else {
            System.out.print("BH ");
        }
        return "";
    }

    /**
     * A getter method for the nam of this horse object
     *
     * @return a string representing the name of this horse object. Used on the 2D chessboard
     */
    public String getName() {
        switch (color) {
            case "White":
                return "WH";
            case "Black":
                return "BH";
        }
        return "";
    }

    /**
     * Finds all the possible positions that the horse can jump to from the source tile
     *
     * @param sourceTile a tile object acting as the source tile to find all the possible position that
     *                   the horse can jump to.
     * @return List<Pair> a list of pairs that represents the positions that this horse can jump to.
     */
    @Override
    public List<Pair> inSight(Tile sourceTile) {
        List<Pair> possible = new ArrayList<>();
        ChessBoard board = new ChessBoard();

        int sourceRow = sourceTile.getRow();
        int sourceCol = sourceTile.getCol();

        //Up-shared
        if (!board.isPiece(sourceRow - 2, sourceCol - 1)) {
            possible.add(new Pair(sourceRow - 2, sourceCol - 1));
        }
        if (!board.isPiece(sourceRow - 2, sourceCol + 1)) {
            possible.add(new Pair(sourceRow - 2, sourceCol + 1));
        }

        //right-shared
        if (!board.isPiece(sourceRow + 1, sourceCol + 2)) {
            possible.add(new Pair(sourceRow + 1, sourceCol + 2));
        }
        if (!board.isPiece(sourceRow - 1, sourceCol + 2)) {
            possible.add(new Pair(sourceRow - 1, sourceCol + 2));
        }

        //left-shared
        if (!board.isPiece(sourceRow + 1, sourceCol - 2)) {
            possible.add(new Pair(sourceRow + 1, sourceCol - 2));
        }
        if (!board.isPiece(sourceRow - 1, sourceCol - 2)) {
            possible.add(new Pair(sourceRow - 1, sourceCol - 2));
        }

        //down shared
        if (!board.isPiece(sourceRow + 2, sourceCol - 1)) {
            possible.add(new Pair(sourceRow + 2, sourceCol - 1));
        }
        if (!board.isPiece(sourceRow + 2, sourceCol + 1)) {
            possible.add(new Pair(sourceRow + 2, sourceCol + 1));
        }

        //Up
        if (board.isPiece(sourceRow - 2, sourceCol - 1) &&
                !board.getColor(sourceRow - 2, sourceCol - 1).equals(this.color)) {
            possible.add(new Pair(sourceRow - 2, sourceCol - 1));
        }
        if (board.isPiece(sourceRow - 2, sourceCol + 1) &&
                !board.getColor(sourceRow - 2, sourceCol + 1).equals(this.color)) {
            possible.add(new Pair(sourceRow - 2, sourceCol + 1));
        }

        //Right
        if (board.isPiece(sourceRow + 1, sourceCol + 2) &&
                !board.getColor(sourceRow + 1, sourceCol + 2).equals(this.color)) {
            possible.add(new Pair(sourceRow + 1, sourceCol + 2));
        }
        if (board.isPiece(sourceRow - 1, sourceCol + 2) &&
                !board.getColor(sourceRow - 1, sourceCol + 2).equals(this.color)) {
            possible.add(new Pair(sourceRow - 1, sourceCol + 2));
        }

        //Left
        if (board.isPiece(sourceRow + 1, sourceCol - 2) &&
                !board.getColor(sourceRow + 1, sourceCol - 2).equals(this.color)) {
            possible.add(new Pair(sourceRow + 1, sourceCol - 2));
        }
        if (board.isPiece(sourceRow - 1, sourceCol - 2) &&
                !board.getColor(sourceRow - 1, sourceCol - 2).equals(this.color)) {
            possible.add(new Pair(sourceRow - 1, sourceCol - 2));
        }

        //Down
        if (board.isPiece(sourceRow + 2, sourceCol - 1) &&
                !board.getColor(sourceRow + 2, sourceCol - 1).equals(this.color)) {
            possible.add(new Pair(sourceRow + 2, sourceCol - 1));
        }
        if (board.isPiece(sourceRow + 2, sourceCol + 1) &&
                !board.getColor(sourceRow + 2, sourceCol + 1).equals(this.color)) {
            possible.add(new Pair(sourceRow + 2, sourceCol + 1));
        }

        return possible;
    }

    /**
     * The overridden version of the object's equals method
     *
     * @param o the other horse to be compared to
     * @return a boolean representing whether the two horses are equal
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
        if (other.getName().charAt(1) == 'H') {
            return true;
        }

        return false;
    }
}
