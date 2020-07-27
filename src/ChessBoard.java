import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    static final Tile[][] gameBoard = new Tile[8][8];
    static List<Piece> removedPiecesBlack = new ArrayList<Piece>();
    static List<Piece> removedPiecesWhite = new ArrayList<Piece>();

    static boolean isCheck;
    static boolean isCheckMate;

    static Piece BK;
    static Piece WK;

    public void movePiece(Piece piece, Tile sourceTile, Tile destTile) {
        //Need to change the gameboard so that it makes sense visually
        //This includes removing the piece at the old tile and add it in the new location
        //need to add the new piece to the new tile

        if (destTile.getPiece() != null) {
            switch (piece.getColor()) {
                case "White":
                    removedPiecesBlack.add(destTile.getPiece());
                    break;
                case "Black":
                    removedPiecesWhite.add(destTile.getPiece());
                    break;
            }
        }
        sourceTile.removePiece();
        destTile.setPiece(piece);

        //we need to check if the moved piece has access to a king
        Tile checkedTile = isCheck(piece, destTile);
        if (checkedTile != null) {
            isCheckMate(checkedTile.getPiece(), checkedTile);
        }
    }

    public Tile isCheck(Piece piece, Tile sourceTile) {
        //find the list of possible destination tiles and see if there is a
        //king of the opposite color in one of those tiles
        List<Pair> possible = piece.inSight(sourceTile);

        King temp = new King("White");
        for (Pair pair : possible) {
            if (pair.getX() < 0 || pair.getY() < 0 || pair.getX() > 7 || pair.getY() > 7) {
                continue;
            }
            if (gameBoard[pair.getX()][pair.getY()].getPiece() != null &&
                    gameBoard[pair.getX()][pair.getY()].getPiece().equals(temp)) {
                isCheck = true;
                return gameBoard[pair.getX()][pair.getY()];
            }
        }
        isCheck = false;
        return null;
    }

    public void isCheckMate(Piece piece, Tile sourceTile) {
        List<Pair> possible = piece.inSight(sourceTile);
        if (possible.isEmpty()) {
            isCheckMate = true;
            System.out.println("CHECKMATE! Game over!");
            System.exit(0);
        }
    }

    //Will check if there is a piece at the given row/col pair
    public boolean isPiece(int row, int col) {
        if (col < 0 || col > 7 || row < 0 || row > 7) {
            return false;
        }
        if (gameBoard[row][col] == null || gameBoard[row][col].getPiece() == null) {
            return false;
        }

        return true;
    }

    //returns the color of the piece that exists in a certain tile
    //if there is no piece there, then return null
    public String getColor(int row, int col) {
        if (gameBoard[row][col] == null || gameBoard[row][col].getPiece() == null) {
            return null;
        }
        return gameBoard[row][col].getPiece().getColor();
    }

    public void setUp() {
        Piece BR1 = new Rook("Black");
        Piece BH1 = new Horse("Black");
        Piece BB1 = new Bishop("Black");
        BK = new King("Black");
        Piece BQ = new Queen("Black");
        Piece BB2 = new Bishop("Black");
        Piece BH2 = new Horse("Black");
        Piece BR2 = new Rook("Black");

        gameBoard[0][0] = new Tile(BR1, 0, 0);
        gameBoard[0][1] = new Tile(BH1, 0, 1);
        gameBoard[0][2] = new Tile(BB1, 0, 2);
        gameBoard[0][3] = new Tile(BK, 0, 3);
        gameBoard[0][4] = new Tile(BQ, 0, 4);
        gameBoard[0][5] = new Tile(BB2, 0, 5);
        gameBoard[0][6] = new Tile(BH2, 0, 6);
        gameBoard[0][7] = new Tile(BR2, 0, 7);

        Piece BP1 = new Pawn("Black");
        Piece BP2 = new Pawn("Black");
        Piece BP3 = new Pawn("Black");
        Piece BP4 = new Pawn("Black");
        Piece BP5 = new Pawn("Black");
        Piece BP6 = new Pawn("Black");
        Piece BP7 = new Pawn("Black");
        Piece BP8 = new Pawn("Black");

        gameBoard[1][0] = new Tile(BP1, 1, 0);
        gameBoard[1][1] = new Tile(BP2, 1, 1);
        gameBoard[1][2] = new Tile(BP3, 1, 2);
        gameBoard[1][3] = new Tile(BP4, 1, 3);
        gameBoard[1][4] = new Tile(BP5, 1, 4);
        gameBoard[1][5] = new Tile(BP6, 1, 5);
        gameBoard[1][6] = new Tile(BP7, 1, 6);
        gameBoard[1][7] = new Tile(BP8, 1, 7);

        int rowCount = 2;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 8; i++) {
                gameBoard[rowCount][i] = new Tile(null, rowCount, i);
            }
            rowCount++;
        }

        Piece WP1 = new Pawn("White");
        Piece WP2 = new Pawn("White");
        Piece WP3 = new Pawn("White");
        Piece WP4 = new Pawn("White");
        Piece WP5 = new Pawn("White");
        Piece WP6 = new Pawn("White");
        Piece WP7 = new Pawn("White");
        Piece WP8 = new Pawn("White");

        gameBoard[6][0] = new Tile(WP1, 6, 0);
        gameBoard[6][1] = new Tile(WP2, 6, 1);
        gameBoard[6][2] = new Tile(WP3, 6, 2);
        gameBoard[6][3] = new Tile(WP4, 6, 3);
        gameBoard[6][4] = new Tile(WP5, 6, 4);
        gameBoard[6][5] = new Tile(WP6, 6, 5);
        gameBoard[6][6] = new Tile(WP7, 6, 6);
        gameBoard[6][7] = new Tile(WP8, 6, 7);

        Piece WR1 = new Rook("White");
        Piece WH1 = new Horse("White");
        Piece WB1 = new Bishop("White");
        WK = new King("White");
        Piece WQ = new Queen("White");
        Piece WB2 = new Bishop("White");
        Piece WH2 = new Horse("White");
        Piece WR2 = new Rook("White");

        gameBoard[7][0] = new Tile(WR1, 7, 0);
        gameBoard[7][1] = new Tile(WH1, 7, 1);
        gameBoard[7][2] = new Tile(WB1, 7, 2);
        gameBoard[7][3] = new Tile(WK, 7, 3);
        gameBoard[7][4] = new Tile(WQ, 7, 4);
        gameBoard[7][5] = new Tile(WB2, 7, 5);
        gameBoard[7][6] = new Tile(WH2, 7, 6);
        gameBoard[7][7] = new Tile(WR2, 7, 7);
    }

    public void printChessBoard() {

        System.out.print("  ");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print((char) ('A' + i) + "  ");
        }
        System.out.println();
        for (int row = 0; row < gameBoard.length; row++) {
            System.out.print((8 - row) + " ");
            for (int col = 0; col < gameBoard[0].length; col++) {
                if (gameBoard[row][col].getPiece() == null) {
                    System.out.print("   ");
                } else {
                    System.out.print(gameBoard[row][col].getPiece().toString());
                }
            }
            System.out.println();
        }
    }

    //Given a tile in the format (A3), we can return the tile in that position
    public Tile convert(char col, int row) {
        Map<Character, Integer> cols = new HashMap<>();
        cols.put('A', 0);
        cols.put('B', 1);
        cols.put('C', 2);
        cols.put('D', 3);
        cols.put('E', 4);
        cols.put('F', 5);
        cols.put('G', 6);
        cols.put('H', 7);

        return gameBoard[8 - row][cols.get(col)];
    }

    public Tile getTile(int row, int col) {
        return gameBoard[row][col];
    }

    public Piece getKing(String color) { //Returns the king of that color
        switch (color) {
            case "White":
                return WK;
            case "Black":
                return BK;
        }
        return null;
    }

    //Checks whether the given coordinate is a valid source for a move to begin with
    public boolean isValidStarting(String color, char col, int row) {

        if (col < 65 || col > 73 || row < 1 || row > 8) {
            return false;
        }

        Map<Character, Integer> cols = new HashMap<>();
        cols.put('A', 0);
        cols.put('B', 1);
        cols.put('C', 2);
        cols.put('D', 3);
        cols.put('E', 4);
        cols.put('F', 5);
        cols.put('G', 6);
        cols.put('H', 7);

        Tile temp = gameBoard[8 - row][cols.get(col)];

        if (temp == null || temp.getPiece() == null || !temp.getPiece().getColor().equalsIgnoreCase(color)) {
            return false;
        }

        //some pieces cannot be moved depending on their situation

        return true;
    }

    //Checks whether the given coordinate is a valid destination
    public boolean isValidDestination(String color, char col, int row) {

        if (col < 65 || col > 73 || row < 1 || row > 8) {
            return false;
        }

        Map<Character, Integer> cols = new HashMap<>();
        cols.put('A', 0);
        cols.put('B', 1);
        cols.put('C', 2);
        cols.put('D', 3);
        cols.put('E', 4);
        cols.put('F', 5);
        cols.put('G', 6);
        cols.put('H', 7);

        Tile temp = gameBoard[8 - row][cols.get(col)];

        if (temp.getPiece() != null && temp.getPiece().getColor().equalsIgnoreCase(color)) {
            return false;
        }

        return true;
    }

    public List<Piece> getRemovedPiecesBlack() {
        return removedPiecesBlack;
    }

    public List<Piece> getRemovedPiecesWhite() {
        return removedPiecesWhite;
    }


}
