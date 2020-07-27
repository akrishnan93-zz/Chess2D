import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
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

    String color;

    public Queen(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        if (color.equals("White")) {
            System.out.print("WQ ");
        } else {
            System.out.print("BQ ");
        }
        return "";
    }

    public String getName() {
        switch (color) {
            case "White":
                return "WQ";
            case "Black":
                return "BQ";
        }
        return "";
    }

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