import java.util.ArrayList;
import java.util.List;

public class Horse extends Piece {

    String color;

    public Horse(String color) {
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
            System.out.print("WH ");
        } else {
            System.out.print("BH ");
        }
        return "";
    }

    public String getName() {
        switch (color) {
            case "White":
                return "WH";
            case "Black":
                return "BH";
        }
        return "";
    }

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
