public class Tile {

    Piece piece;
    int row;
    int col;

    public Tile(Piece piece, int row, int col) {
        this.piece = piece;
        this.row = row;
        this.col = col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        System.out.println("ROW: " + row + " - COL: " + col);
        return "";
    }


}
