import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    String color;

    public Rook(String color) {
        this.color = color;
    }

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

    public String getColor() {
        return color;
    }

    public String toString() {
        if (color.equals("White")) {
            System.out.print("WR ");
        } else {
            System.out.print("BR ");
        }
        return "";
    }

    public String getName() {
        switch (color) {
            case "White":
                return "WR";
            case "Black":
                return "BR";
        }
        return "";
    }

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
