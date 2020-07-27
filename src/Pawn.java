import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    String color;

    public Pawn(String color) {
        this.color = color;
    }

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
            System.out.print("WP ");
        } else {
            System.out.print("BP ");
        }
        return "";
    }

    public String getName() {
        switch (color) {
            case "White":
                return "WP";
            case "Black":
                return "BP";
        }
        return "";
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

        if (other.getName().charAt(1) == 'P') {
            return true;
        }

        return false;
    }
}
