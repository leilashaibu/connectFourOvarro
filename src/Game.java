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
    public void displayGridState(){
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }
}
