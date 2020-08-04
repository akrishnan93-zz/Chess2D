import java.util.Scanner;

/**
 * The type Player.
 */
public class Player extends ChessGame {
    /**
     * The Name.
     */
    String name;
    /**
     * The Number.
     */
    int number;
    /**
     * The Color.
     */
    String color;

    /**
     * Instantiates a new Player.
     *
     * @param number the number
     */
    public Player(int number) {
        this.number = number;
    }


    /**
     * The Scan.
     */
    Scanner scan = new Scanner(System.in);

    /**
     * Sets name.
     */
    public void setName() {
        System.out.println("Please enter the name for player " + number + ":");
        name = scan.next();
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

}
