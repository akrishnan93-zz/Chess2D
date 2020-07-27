import java.util.Scanner;

public class Player extends ChessGame {
    String name;
    int number;
    String color;

    public Player(int number) {
        this.number = number;
    }


    Scanner scan = new Scanner(System.in);

    public void setName() {
        System.out.println("Please enter the name for player " + number + ":");
        name = scan.next();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
