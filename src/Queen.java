import java.util.ArrayList;
import java.util.List;

/**
 * The type Queen.
 */
public class Queen extends Piece {
    /**
     * Decide whether the destination pair is a possible position for the piece in question relative to the source
     * position that it is starting from.
     *
     * @param sourceRow the row of the source tile
     * @param sourceCol the column of the source tile
     * @param destRow   the row of the destination tile
     * @param destCol   the column of the destination tile
     * @return whether the destination is a possible spot that we can go to from the source tile with this piece
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
     * The Color of the queen
     */
    String color;

    /**
     * Instantiates a new Queen.
     *
     * @param color the color of the queen that we are creating
     */
    public Queen(String color) {
        this.color = color;
    }

    /**
     * A getter for the queen's color
     *
     * @return a string representing the color of the queen
     */
    public String getColor() {
        return color;
    }

    /**
     * The overridden version of the toString method for queen chess pieces
     *
     * @return the string that is printed when we print a queen piece
     */
    public String toString() {
        if (color.equals("White")) {
            System.out.print("WQ ");
        } else {
            System.out.print("BQ ");
        }
        return "";
    }

    /**
     * A getter for the name of the queen. Used in the creation of the game board
     *
     * @return a string that represents the name of a queen piece
     */
    public String getName() {
        switch (color) {
            case "White":
                return "WQ";
            case "Black":
                return "BQ";
        }
        return "";
    }

    /**
     * A method that finds all the positions that this queen can go to from the starting position
     *
     * @param sourceTile the tile that we are starting from
     * @return a list of pairs that is the array list of all possible destination tiles for this piece
     * starting from this source tile.
     */
    @Override
    public List<Pair> inSight(Tile sourceTile) {
        List<Pair> possible = new ArrayList<>();
        ChessBoard board = new ChessBoard();

        int sourceRow = sourceTile.getRow();
        int sourceCol = sourceTile.getCol();

        //Up and to the left
        int tempCol = sourceCol - 1;
        int tempRow = sourceRow - 1;
        while (tempCol >= 0 && tempRow >= 0) {
            if (!board.isPiece(tempRow, tempCol)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol--;
                tempRow--;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(tempRow, tempCol).getPiece().getColor().equals(color)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol--;
                tempRow--;
            }
            break;
        }

        //Up and to the right
        tempCol = sourceCol + 1;
        tempRow = sourceRow - 1;
        while (tempCol <= 7 && tempRow >= 0) {
            if (!board.isPiece(tempRow, tempCol)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol++;
                tempRow--;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(tempRow, tempCol).getPiece().getColor().equals(color)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol++;
                tempRow--;
            }
            break;
        }

        //Down and to the left
        tempCol = sourceCol - 1;
        tempRow = sourceRow + 1;
        while (tempCol >= 0 && tempRow <= 7) {
            if (!board.isPiece(tempRow, tempCol)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol--;
                tempRow++;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(tempRow, tempCol).getPiece().getColor().equals(color)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol--;
                tempRow++;
            }
            break;
        }

        //Down and to the right
        tempCol = sourceCol + 1;
        tempRow = sourceRow + 1;
        while (tempCol <= 7 && tempRow <= 7) {
            if (!board.isPiece(tempRow, tempCol)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol++;
                tempRow++;
                continue;
            }
            //do a check if the tile has a piece of the opposite color:
            if (!board.getTile(tempRow, tempCol).getPiece().getColor().equals(color)) {
                possible.add(new Pair(tempRow, tempCol));
                tempCol++;
                tempRow++;
            }
            break;
        }

        //Check going left
        tempCol = sourceCol - 1;
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
        tempRow = sourceRow - 1;
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
     * The overridden version of the equals method to test whether two queens are equal
     *
     * @param o the other queen that we are comparing with
     * @return whether the two queens in question are equal or not
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
        if (other.getName().charAt(1) == 'Q') {
            return true;
        }

        return false;
    }
}