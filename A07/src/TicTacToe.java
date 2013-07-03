import java.util.Random;

/**
 * <p>Tic Tac Toe Logic for use with a JonA07</p>
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class TicTacToe {
    
    /**
     * Representation of the 3x3 Tic-Tac-Toe grid.
     */
    private int[][] grid = new int[3][3];

    /**
     * Default constructor.
     */
    public TicTacToe() {
    }

    /**
     * Checks if the game is over due to tic-tac-toe.
     * @return True if a tic-tac-toe is found.
     */
    protected boolean checkForWin(int check) {
        return checkRows(check) || checkColumns(check) || checkDiagonals(check);
    }

    private boolean checkRows(int check) {
        int sum;
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += this.grid[i][j];
            }
            if (sum == check) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(int check) {
        int sum;
        for (int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += this.grid[j][i];
            }
            if (sum == check) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(int check) {
        return checkLeftToRight(check) || checkRightToLeft(check);
    }

    private boolean checkLeftToRight(int check) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += this.grid[i][i];
        }
        return (sum == check);
    }

    private boolean checkRightToLeft(int check) {
        int row = 0;
        int col = 2;
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += this.grid[row++][col--];
        }
        return (sum == check);
    }

    /**
     * Iterates through the grid, checking for any tiles left to choose.
     * @return True if the grid is completely filled.
     */
    protected boolean isFilled() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Chooses a tile randomly for the computer's move.
     * @return the Row and Column of the computer's move in a size-2 array.
     */
    protected int[] compMove() {
        if (winOrLoseNextMove()) {
            int[] nextMove = winOrStopLoss();
            setGrid(nextMove, -1);

            return nextMove;
        }
        else {
            int row;
            int col;
            Random rand = new Random(System.nanoTime());
            do {
                row = rand.nextInt(3);
                col = rand.nextInt(3);
            } while (this.grid[row][col] != 0);
            setGrid(row, col, -1);

            return new int[] {row, col};
        }
    }

    private boolean winOrLoseNextMove() {
        return checkForWin(-2) || checkForWin(2);
    }

    private int[] winOrStopLoss() {
        int[] nextMove = new int[2];
        if (checkRows(2)) {
            //looks for the 2 sum in rows
            for (int row = 0; row < 3; row++) {
                int sum = 0;
                for (int col = 0; col < 3; col++) {
                    sum += this.grid[row][col];
                }
                if (sum == 2) {
                    //looks for the 0 in that row
                    for (int i = 0; i < 3; i++) {
                        if (this.grid[row][i] == 0) {
                            nextMove = new int[] {row, i};
                        }
                    }
                }
            }
        }
        else if (checkColumns(2)) {
            //looks for the 2 sum in columns
            for (int col = 0; col < 3; col++) {
                int sum = 0;
                for (int row = 0; row < 3; row++) {
                    sum += this.grid[row][col];
                }
                if (sum == 2) {
                    //looks for the 0 in that column
                    for (int i = 0; i < 3; i++) {
                        if (this.grid[i][col] == 0) {
                            nextMove = new int[] {i, col};
                        }
                    }
                }
            }
        }
        else if (checkLeftToRight(2)) {
            for (int i = 0; i < 3; i++) {
                if (this.grid[i][i] == 0) {
                    nextMove = new int[] {i, i};
                }
            }
        }
        else if (checkRightToLeft(2)) {
            int row = 0;
            int col = 2;
            for (int i = 0; i < 3; i++) {
                if (this.grid[row][col] == 0) {
                    nextMove = new int[] {row, col};
                }
                row++;
                col--;
            }
        }
        if (checkRows(-2)) {
            //looks for the 2 sum in rows
            for (int row = 0; row < 3; row++) {
                int sum = 0;
                for (int col = 0; col < 3; col++) {
                    sum += this.grid[row][col];
                }
                if (sum == -2) {
                    //looks for the 0 in that row
                    for (int i = 0; i < 3; i++) {
                        if (this.grid[row][i] == 0) {
                            nextMove = new int[] {row, i};
                        }
                    }
                }
            }
        }
        else if (checkColumns(-2)) {
            //looks for the 2 sum in columns
            for (int col = 0; col < 3; col++) {
                int sum = 0;
                for (int row = 0; row < 3; row++) {
                    sum += this.grid[row][col];
                }
                if (sum == -2) {
                    //looks for the 0 in that column
                    for (int i = 0; i < 3; i++) {
                        if (this.grid[i][col] == 0) {
                            nextMove = new int[] {i, col};
                        }
                    }
                }
            }
        }
        else if (checkLeftToRight(-2)) {
            for (int i = 0; i < 3; i++) {
                if (this.grid[i][i] == 0) {
                    nextMove = new int[] {i, i};
                }
            }
        }
        else if (checkRightToLeft(-2)) {
            int row = 0;
            int col = 2;
            for (int i = 0; i < 3; i++) {
                if (this.grid[row][col] == 0) {
                    nextMove = new int[] {row, col};
                }
                row++;
                col--;
            }
        }
        return nextMove;
    }

    /**
     * Resets the grid back to initial state.
     */
    protected void resetGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {                
                setGrid(i, j, 0);
            }
        }
    }
    
    protected void setGrid(int row, int col, int mark) {
        this.grid[row][col] = mark;
    }

    protected void setGrid(int[] move, int mark) {
        this.grid[move[0]][move[1]] = mark;
    }
}
