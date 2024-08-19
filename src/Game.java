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
                //Checking if there is a win
                if (determineWinner(i,cols)) {
                    displayGridState();
                    System.out.println("Player " + player.getToken().getColour() + " wins!!");
                    // Game ends if there's a win
                    System.exit(0);
                }
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
    public boolean determineWinner(int rows,int columns){
        if (rows < 0 || rows >= this.row || columns < 0 || columns >= this.column) {
            return false;
        }
        //Check if the grid is empty
        PlayerToken cell = grid[rows][columns];
        if (cell.getColour().equals("WHITE")) {
            return false; // No win if the grid is empty
        }
        //Checking all possible directions for a win
        return checkDirection(rows,columns,1,0)|| //horizontal
                checkDirection(rows,columns,0,1) || //vertical
                checkDirection(rows,columns,1,1)|| //diagonal /
                checkDirection(rows,columns,1,-1); //diagonal \
    }
    public boolean checkDirection (int rows,int columns,int rowIncrease,int colIncrease){
        int count = 0;
        PlayerToken token = grid[rows][columns];

        // Check in the positive direction
        for (int r = rows, c = columns; isInBounds(r, c) && grid[r][c].getColour().equals(token.getColour()); r += rowIncrease, c += colIncrease) {
            count++;
        }

        // Check in the negative direction
        for (int r = rows - rowIncrease, c = columns - colIncrease; isInBounds(r, c) && grid[r][c].getColour().equals(token.getColour()); r -= rowIncrease, c -= colIncrease) {
            count++;
        }

        // Check if the count meets the winning length
        return count >= winningRowLength; //
    }
    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.row && col >= 0 && col < this.column;
    }

}
