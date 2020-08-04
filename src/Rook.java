import java.util.ArrayList;
import java.util.List;

/**
 * The type Rook.
 */
public class Rook extends Piece {

    /**
     * The Color of the rook
     */
    String color;

    /**
     * Instantiates a new Rook.
     *
     * @param color the color
     */
    public Rook(String color) {
        this.color = color;
    }

    /**
     * A method to find whether the destination tile exists in the possible method that we get from inSight() function
     *
     * @param sourceRow the source row
     * @param sourceCol the source col
     * @param destRow   the dest row
     * @param destCol   the dest col
     * @return a boolean of of whether the destination tile exists in the possible list
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
     * A getter method for the color of the rook
     *
     * @return a string representing the color of the rook
     */
    public String getColor() {
        return color;
    }

    /**
     * The overridden version of the toString method for rook objects
     *
     * @return a string representing what is returned when we print out a rook object
     */
    public String toString() {
        if (color.equals("White")) {
            System.out.print("WR ");
        } else {
            System.out.print("BR ");
        }
        return "";
    }

    /**
     * Getter method for the name of a rook
     *
     * @return a string representing the name of a rook
     */
    public String getName() {
        switch (color) {
            case "White":
                return "WR";
            case "Black":
                return "BR";
        }
        return "";
    }

    /**
     * @param sourceTile the source tile
     * @return a list of all the possible tiles that we can go to with a rook from the source tile
     */
    @Override
    public List<Pair> inSight(Tile sourceTile) {

        List<Pair> possible = new ArrayList<>();
        ChessBoard board = new ChessBoard();

        int sourceRow = sourceTile.getRow();
        int sourceCol = sourceTile.getCol();

        //Check going left
        int tempCol = sourceCol - 1;
        while (tempCol >= 0) {
            if (!board.isPiece(sourceRow, tempCol)) {
                possible.add(new Pair(sourceRow, tempCol));
                tempCol--;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(sourceRow, tempCol).getPiece().getColor().equals(color)) {
                possible.add(new Pair(sourceRow, tempCol));
                tempCol--;
            }
            break;
        }

        //check going right
        tempCol = sourceCol + 1;
        while (tempCol <= 7) { //keep looking until we either hit another piece or we hit a border
            if (!board.isPiece(sourceRow, tempCol)) {
                possible.add(new Pair(sourceRow, tempCol));
                tempCol++;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(sourceRow, tempCol).getPiece().getColor().equals(this.color)) {
                possible.add(new Pair(sourceRow, tempCol));
                tempCol++;
            }
            break;
        }

        //check going up
        int tempRow = sourceRow - 1;
        while (tempRow >= 0) { //keep looking until we either hit another piece or we hit a border
            if (!board.isPiece(tempRow, sourceCol)) {
                possible.add(new Pair(tempRow, sourceCol));
                tempRow--;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(tempRow, sourceCol).getPiece().getColor().equals(this.color)) {
                possible.add(new Pair(tempRow, sourceCol));
                tempRow--;
            }
            break;
        }

        //check going down
        tempRow = sourceRow + 1;
        while (tempRow <= 7) { //keep looking until we either hit another piece or we hit a border
            if (!board.isPiece(tempRow, sourceCol)) {
                possible.add(new Pair(tempRow, sourceCol));
                tempRow++;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(tempRow, sourceCol).getPiece().getColor().equals(this.color)) {
                possible.add(new Pair(tempRow, sourceCol));
                tempRow++;
            }
            break;
        }

        return possible;
    }

    /**
     * Overriden version of the equals method to see if two rook objects are equal
     *
     * @param o the other rook that we are comparing with
     * @return a boolean representing whether the two rooks are equal
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
        if (other.getName().charAt(1) == 'R') {
            return true;
        }

        return false;
    }
}
