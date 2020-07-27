import java.util.List;

public abstract class Piece {

    String color;

    public abstract boolean tracePaths(int sourceRow, int sourceCol, int destRow, int destCol);

    public String getColor() {
        return color;
    }

    public abstract String getName();

    public abstract List<Pair> inSight(Tile sourceTile);

}
