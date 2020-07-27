import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    String color;

    public Bishop(String color) {
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

    @Override
    public String toString() {
        if (color.equals("White")) {
            System.out.print("WB ");
        } else {
            System.out.print("BB ");
        }
        return "";
    }

    public String getName() {
        switch (color) {
            case "White":
                return "WB";
            case "Black":
                return "BB";
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
        if (other.getName().charAt(1) == 'B') {
            return true;
        }

        return false;
    }
}
