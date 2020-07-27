import java.sql.SQLOutput;
import java.util.*;

public class ChessGame {

    //Default constructor
    public ChessGame() {

    }

    static ChessBoard board = new ChessBoard();
    static Player player1 = new Player(1);
    static Player player2 = new Player(2);

    static boolean gameOver = false;
    static String winner; //color of the player that won

    static Scanner scan = new Scanner(System.in);

    static Map<Player, String> playerColor = new HashMap<>();

    /**
     * Selects the players' colors at random giving each player a 50/50 chance to use the white pieces
     */
    public static void selectPlayerColor() {
        int temp = (int) (Math.random() * 99) + 1; //Random number between 1 and 100.
        if (temp % 2 == 0) { // 50:50 chance of it being odd or even
            playerColor.put(player1, "White");
            playerColor.put(player2, "Black");
        } else {
            playerColor.put(player1, "Black");
            playerColor.put(player2, "White");
        }

        player1.setColor(playerColor.get(player1));
        player2.setColor(playerColor.get(player2));

        System.out.println("Player 1 is " + playerColor.get(player1));
        System.out.println("Player 2 is " + playerColor.get(player2));
    }

    /**
     * Proceedings to start the game
     */
    public static void startGame() {
        player1.setName();

        System.out.println("Single player (enter 1) or multiplayer (enter 2)?");
        String input = scan.nextLine();

        if (input.equals("1")) {

            selectPlayerColor();
            System.out.println();
            board.setUp();

            //Singleplayer
            System.out.println("Which difficulty easy (enter 1), medium (enter 2), or hard (enter 3)?");
            input = scan.nextLine();

            if (input.equals("1")) {
                switch (playerColor.get(player1)) {
                    case "White":
                        while (true) {
                            winner();
                            turn(player1);
                            board.printChessBoard();
                            winner();
                            turnRandom(player2);
                            board.printChessBoard();
                        }
                    case "Black":
                        while (true) {
                            winner();
                            turnRandom(player2);
                            board.printChessBoard();
                            winner();
                            turn(player1);
                            board.printChessBoard();
                        }
                }
            } else if (input.equals("2")) {
                //proceedings for medium
            } else {
                //proceedings from hardest diff
            }
        } else {
            //multiplayer
            player2.setName();

            selectPlayerColor();
            System.out.println();
            board.setUp();
            board.printChessBoard();

            switch (playerColor.get(player1)) {
                case "White":
                    while (true) {
                        winner();
                        turn(player1);
                        board.printChessBoard();
                        winner();
                        turn(player2);
                        board.printChessBoard();
                    }
                case "Black":
                    while (true) {
                        winner();
                        turn(player2);
                        board.printChessBoard();
                        winner();
                        turn(player1);
                        board.printChessBoard();
                    }
            }
        }

    }


    /**
     * A method to announce the winner of the chess game once it is complete
     *
     * @return the player that won the match
     */
    public static void winner() {
        List<Piece> removedPiecesBlack = board.getRemovedPiecesBlack();
        List<Piece> removedPiecesWhite = board.getRemovedPiecesWhite();

        King temp = new King("White");
        for (Piece pieces : removedPiecesBlack) {
            if (pieces.equals(temp)) {
                gameOver = true;
            }
        }

        for (Piece pieces : removedPiecesWhite) {
            if (pieces.equals(temp)) {
                gameOver = true;
            }
        }

        if (gameOver) {
            System.out.println(winner + " has won the game!");
            board.printChessBoard();
            System.exit(0);
        }
    }

    public static void turn(Player player) {
        System.out.println();
        System.out.println("It is now " + player.name + "'s turn to make a move. ");

        if (ChessBoard.isCheck == true) {
            System.out.println("Check! You must only move your king!");
        }

        System.out.println("Please enter the column followed by the row of the piece that you want to move: (EX: A2)");
        String source = scan.next();

        while (!board.isValidStarting(playerColor.get(player), source.charAt(0),
                Integer.parseInt(String.valueOf(source.charAt(1))))) {
            System.out.println("Illegal move. Try again.");
            System.out.println("Please enter the column followed by the row of the piece that you want to move: (EX: A2)");
            source = scan.next();
        }

        System.out.println("Please enter the column followed by the row of the destination tile that you want to move to: (EX: B2)");
        String dest = scan.next();

        while (!board.isValidDestination(playerColor.get(player), dest.charAt(0),
                Integer.parseInt(String.valueOf(dest.charAt(1))))) {
            System.out.println("Illegal move. Try again.");
            System.out.println("Please enter the column followed by the row of the destination tile that you want to move to: (EX: B2)");
            dest = scan.next();
        }

        //The move makes sense board-wise. Now we need to see if the specific piece in question can do that
        Tile sourceTile = board.convert(source.charAt(0), Integer.parseInt(String.valueOf(source.charAt(1))));
        Tile destTile = board.convert(dest.charAt(0), Integer.parseInt(String.valueOf(dest.charAt(1))));

        while (!sourceTile.getPiece().tracePaths(sourceTile.getRow(), sourceTile.getCol(), destTile.getRow(), destTile.getCol())) {
            System.out.println("The " + sourceTile.getPiece().getName() + " cannot move to that tile. Illegal move.");

            System.out.println("Please enter the column followed by the row of the piece that you want to move: (EX: A2)");
            source = scan.next();

            while (!board.isValidStarting(playerColor.get(player), source.charAt(0),
                    Integer.parseInt(String.valueOf(source.charAt(1))))) {
                System.out.println("Illegal move. Try again.");
                System.out.println("Please enter the column followed by the row of the piece that you want to move: (EX: A2)");
                source = scan.next();
            }

            System.out.println("Please enter the column followed by the row of the destination tile that you want to move to: (EX: B2)");
            dest = scan.next();

            while (!board.isValidDestination(playerColor.get(player), dest.charAt(0),
                    Integer.parseInt(String.valueOf(dest.charAt(1))))) {
                System.out.println("Illegal move. Try again.");
                System.out.println("Please enter the column followed by the row of the destination tile that you want to move to: (EX: B2)");
                dest = scan.next();
            }

            //The move makes sense board-wise. Now we need to see if the specific piece in question can do that
            sourceTile = board.convert(source.charAt(0), Integer.parseInt(String.valueOf(source.charAt(1))));
            destTile = board.convert(dest.charAt(0), Integer.parseInt(String.valueOf(dest.charAt(1))));
        }

        board.movePiece(sourceTile.getPiece(), sourceTile, destTile);
        winner();
    }

    public static void turnRandom(Player player) {
        System.out.println();
        System.out.println("It is now the computer's turn to make a move. ");

        char randCol = (char) ((Math.random() * 7) + 62);
        int randRow = (int) ((Math.random() * 7) + 1);
        String source = "" + randCol + randRow;

        while (!board.isValidStarting(playerColor.get(player), source.charAt(0),
                Integer.parseInt(String.valueOf(source.charAt(1))))) {
            randCol = (char) ((Math.random() * 7) + 62);
            randRow = (int) ((Math.random() * 7) + 1);
            source = "" + randCol + randRow;
        }

        randCol = (char) ((Math.random() * 7) + 62);
        randRow = (int) ((Math.random() * 7) + 1);
        String dest = "" + randCol + randRow;

        while (!board.isValidDestination(playerColor.get(player), dest.charAt(0),
                Integer.parseInt(String.valueOf(dest.charAt(1))))) {
            randCol = (char) ((Math.random() * 7) + 62);
            randRow = (int) ((Math.random() * 7) + 1);
            dest = "" + randCol + randRow;
        }

        //The move makes sense board-wise. Now we need to see if the specific piece in question can do that
        Tile sourceTile = board.convert(source.charAt(0), Integer.parseInt(String.valueOf(source.charAt(1))));
        Tile destTile = board.convert(dest.charAt(0), Integer.parseInt(String.valueOf(dest.charAt(1))));

        while (!sourceTile.getPiece().tracePaths(sourceTile.getRow(), sourceTile.getCol(), destTile.getRow(), destTile.getCol())) {
            randCol = (char) ((Math.random() * 7) + 62);
            randRow = (int) ((Math.random() * 7) + 1);
            source = "" + randCol + randRow;

            while (!board.isValidStarting(playerColor.get(player), source.charAt(0),
                    Integer.parseInt(String.valueOf(source.charAt(1))))) {
                randCol = (char) ((Math.random() * 7) + 62);
                randRow = (int) ((Math.random() * 7) + 1);
                source = "" + randCol + randRow;
            }

            randCol = (char) ((Math.random() * 7) + 62);
            randRow = (int) ((Math.random() * 7) + 1);
            dest = "" + randCol + randRow;

            while (!board.isValidDestination(playerColor.get(player), dest.charAt(0),
                    Integer.parseInt(String.valueOf(dest.charAt(1))))) {
                randCol = (char) ((Math.random() * 7) + 62);
                randRow = (int) ((Math.random() * 7) + 1);
                dest = "" + randCol + randRow;
            }

            //The move makes sense board-wise. Now we need to see if the specific piece in question can do that
            sourceTile = board.convert(source.charAt(0), Integer.parseInt(String.valueOf(source.charAt(1))));
            destTile = board.convert(dest.charAt(0), Integer.parseInt(String.valueOf(dest.charAt(1))));
        }

        board.movePiece(sourceTile.getPiece(), sourceTile, destTile);

    }

    public static void main(String[] args) {
        startGame();
    }

    public static void testGame() {
        selectPlayerColor();
        board.setUp();
        board.printChessBoard();

        List<String> moveset1 = new ArrayList<String>();
        moveset1.add("A2");
        moveset1.add("A3");

        moveset1.add("A3");
        moveset1.add("A4");


        moveset1.add("A4");
        moveset1.add("A5");

        moveset1.add("A5");
        moveset1.add("A6");

        moveset1.add("A6");
        moveset1.add("B7");

        List<String> moveset2 = new ArrayList<String>();
        moveset2.add("F7");
        moveset2.add("F5");

        moveset2.add("F5");
        moveset2.add("F4");

        moveset2.add("F4");
        moveset2.add("F3");

        moveset2.add("F3");
        moveset2.add("E2");

        moveset2.add("E2");
        moveset2.add("D1");


        int moveCounter = 0;
        switch (playerColor.get(player1)) {
            case "White":
                while (moveCounter <= moveset1.size()) {
                    winner();
                    turnTesting(player1, moveset1, moveCounter);
                    board.printChessBoard();
                    winner();
                    turnTesting(player2, moveset2, moveCounter);
                    board.printChessBoard();
                    moveCounter += 2;
                }
            case "Black":
                while (moveCounter <= moveset1.size()) {
                    winner();
                    turnTesting(player2, moveset1, moveCounter);
                    board.printChessBoard();
                    winner();
                    turnTesting(player1, moveset2, moveCounter);
                    board.printChessBoard();
                    moveCounter += 2;
                }
        }
    }

    public static void turnTesting(Player player, List<String> moveset, int move) {
        System.out.println();
        System.out.println("It is now " + player.name + "'s turn to make a move. ");

        String source = moveset.get(move);

        while (!board.isValidStarting(playerColor.get(player), source.charAt(0),
                Integer.parseInt(String.valueOf(source.charAt(1))))) {
            System.out.println("Illegal move. Try again.");
        }

        String dest = moveset.get(move + 1);

        while (!board.isValidDestination(playerColor.get(player), dest.charAt(0),
                Integer.parseInt(String.valueOf(dest.charAt(1))))) {
            System.out.println("Illegal move. Try again.");
        }

        //The move makes sense board-wise. Now we need to see if the specific piece in question can do that
        Tile sourceTile = board.convert(source.charAt(0), Integer.parseInt(String.valueOf(source.charAt(1))));
        Tile destTile = board.convert(dest.charAt(0), Integer.parseInt(String.valueOf(dest.charAt(1))));

        while (!sourceTile.getPiece().tracePaths(sourceTile.getRow(), sourceTile.getCol(), destTile.getRow(), destTile.getCol())) {
            System.out.println("The " + sourceTile.getPiece().getName() + " cannot move to that tile. Illegal move.");

            while (!board.isValidStarting(playerColor.get(player), source.charAt(0),
                    Integer.parseInt(String.valueOf(source.charAt(1))))) {
                System.out.println("Illegal move. Try again.");
            }

            while (!board.isValidDestination(playerColor.get(player), dest.charAt(0),
                    Integer.parseInt(String.valueOf(dest.charAt(1))))) {
                System.out.println("Illegal move. Try again.");
            }

            //The move makes sense board-wise. Now we need to see if the specific piece in question can do that
            sourceTile = board.convert(source.charAt(0), Integer.parseInt(String.valueOf(source.charAt(1))));
            destTile = board.convert(dest.charAt(0), Integer.parseInt(String.valueOf(dest.charAt(1))));
        }

        board.movePiece(sourceTile.getPiece(), sourceTile, destTile);
        winner();
    }

}
