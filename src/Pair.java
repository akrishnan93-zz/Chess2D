public class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        final Pair other = (Pair) obj;
        if (this.x == other.x && this.y == other.y) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "R: " + x + " C: " + y;
    }
}