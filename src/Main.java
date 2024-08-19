import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            //Collect user input for number of rows,columns and winning row length
            int row = validInteger(scanner, "Please enter the number of rows you want (Number must be at least 1):", 1, Integer.MAX_VALUE);
            int column = validInteger(scanner, "Please enter the number of columns you want (must be at least 1):", 1, Integer.MAX_VALUE);
            int winningRowLength = validInteger(scanner,
                    "Please enter the winning row length (must be at least 1 and can not be greater than the grid):",
                    1, Math.min(row, column));
            //Get the colour of the token for the first player
            String firstPlayerColour = validColour(scanner);
            //Trim and convert colour to upper case
            String caseInsensitiveColour = firstPlayerColour.toUpperCase().trim();
            //Making sure colour entered is valid
            Player firstPlayer = new Player(caseInsensitiveColour);

            //Create a new game object
            Game game = new Game(row, column, firstPlayer, winningRowLength);
            //Display the initial game grid
            System.out.println("Initial Grid");
            game.displayGridState();
            //Loop to run the game
            boolean playingGame = true;
            while (playingGame) {
                //Get Current Player and where they wish to drop token
                Player currentPlayer = game.getCurrentPlayer();
                System.out.println("Player with " + currentPlayer.getToken().getColourToken() + " tokens, enter the column  (0 to " + (column - 1) + ")you wish to drop your token:");
                int token = scanner.nextInt();
                scanner.nextLine();
                //Add token for player
                if (game.addNewToken(token)) {
                    game.displayGridState();
                    game.nextPlayer();
                } else {
                    System.out.println("Failed to add token. Please try again.");
                }
            }
        }
        catch (NoSuchElementException e){
            System.out.println("No more input available. Exiting game.");
        }
    }
    //Check if the user entered a valid number
    private static int validInteger(Scanner scanner,String input,int min,int max){
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println(input);
            try {
                value = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character from the buffer
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }

        return value;
    }
    //Check if the user entered a valid colour
    private static String validColour(Scanner scanner) {
        String colour;
        while (true) {
            System.out.println("Player 1 choose either RED or YELLOW for token colour:");
            colour = scanner.nextLine().trim().toUpperCase();
            if (colour.equals("RED") || colour.equals("YELLOW")) {
                return colour;
            }
            System.out.println("Invalid colour. Player can only choose between 'RED' or 'YELLOW'.");
        }
    }
}