public class Game {
    int row;//Number of rows in grid
    int column;//Number of columns in grid
    PlayerToken[][] grid;//Grid for connect four game
    Player player;//Token representing the current player
    int winningRowLength;//Row number needed to win game

    public Game(int rows,int columns,Player firstPlayer,int winningRowLength){
        //Assigning parameters to their instance variables
        this.row =rows;
        this.column =columns;
        this.winningRowLength =winningRowLength;
        grid = new PlayerToken[rows][columns];
        //Initialising the first player
        this.player = firstPlayer;
        //Initialising game grid with white tokens
        PlayerToken emptyCell =PlayerToken.getEmptyToken();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] =emptyCell;
            }
        }

    }
    //Switching players
    public void nextPlayer(){
        if (player.getToken().getColour().equals("RED")) {
            player = new Player("YELLOW");
        }
        else{
            player =new Player("RED");
        }
    }
    //get current player
    public Player getCurrentPlayer(){
        return player;
    }
    //Adding a token to the grid
    public boolean addNewToken (int cols){
        //To check if the index is not out of bounds
        if((cols < 0)|| (cols >= column)){
            System.out.println("Invalid column index");
            return false;
        }
        //To find the lowest available space in the column
        for (int i = row -1; i >= 0; i--) {
            if (grid[i][cols].getColour().equals("WHITE")) { // Check if the cell is empty
                grid[i][cols] = player.getToken(); // Place the token in the cell
                // Checking if the grid is full,with no winner
                if (isGridFull()) {
                    displayGridState();
                    System.out.println("Game over! No winner.");
                    System.exit(0); // End the game if the grid is full and there's no winner
                }
                return true;
            }
        }
        //No space in column
        System.out.println("Column is full.");
        return false;
    }

    private boolean isGridFull() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j].getColour().equals("WHITE")) {
                    return false; // If any cell is white, the grid is not full
                }
            }
        }
        return true; // All cells are filled with non-white tokens
    }

    public void displayGridState(){
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }
}
