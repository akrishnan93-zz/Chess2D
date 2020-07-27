import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    String color;

    public King(String color) {
        this.color = color;
    }

    @Override
    public boolean tracePaths(int sourceRow, int sourceCol, int destRow, int destCol) {
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
            System.out.print("WK ");
        } else {
            System.out.print("BK ");
        }
        return "";
    }

    public String getName() {
        switch (color) {
            case "White":
                return "WK";
            case "Black":
                return "BK";
        }
        return "";
    }

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
