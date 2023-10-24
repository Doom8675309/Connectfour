/******************************************************************************
* Written by: Tyler Moroni                                                    *
* Code for group project                                                      *
******************************************************************************/
import java.io.*;
import java.util.*;

public class ConnectFourGame {
    public static void playConnectFour(String player1Name,
       String player2Name) {
        int rows = 7;
        int columns = 7;
        Board board = new Board(rows, columns);

        Player player1 = new Player(player1Name, 'X');
        Player player2 = new Player(player2Name, 'O');

        board.printBoard();

        Player currentPlayer = player1;
        boolean gameWon = false;
        while (!board.isFull() && !gameWon) {
            System.out.println(currentPlayer.getName() +
                ", choose a column (0-6): ");
            int column = getPlayerMove();

            if (board.isValidMove(column)) {
                board.dropPiece(column, currentPlayer.getSymbol());
                board.printBoard();
                gameWon = board.checkWin(currentPlayer.getSymbol());
                if (gameWon) {
                    System.out.println(currentPlayer.getName() + " wins!");
                } else {
                    currentPlayer = (currentPlayer == player1) ?
                     player2 : player1;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        if (!gameWon) {
            System.out.println("It's a draw!");
        }
    }

    private static int getPlayerMove() {
        Scanner scanner;
        int move;
        scanner = new Scanner(System.in);
        move = scanner.nextInt();
        return move;
    }

}
class Board {
    private int rows;
    private int columns;
    private char[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];
    }
    // Gathers info from the game in order to make the board

    public void printBoard() {
        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    // Makes the visual portion of the game. 
    }

    public boolean isValidMove(int column) {
        return column >= 0 && column < columns && grid[0][column] == 0;
    }

    public void dropPiece(int column, char symbol) {
        int i;
        for (i = rows - 1; i >= 0; i--) {
            if (grid[i][column] == 0) {
                grid[i][column] = symbol;
                break;
            }
        }
    }

    public boolean checkWin(char symbol) {
        int row, col;
        // Check horizontally
        for (row = 0; row < rows; row++) {
            for (col = 0; col <= columns - 4; col++) {
                if (grid[row][col] == symbol &&
                    grid[row][col + 1] == symbol &&
                    grid[row][col + 2] == symbol &&
                    grid[row][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check vertically
        for (row = 0; row <= rows - 4; row++) {
            for (col = 0; col < columns; col++) {
                if (grid[row][col] == symbol &&
                    grid[row + 1][col] == symbol &&
                    grid[row + 2][col] == symbol &&
                    grid[row + 3][col] == symbol) {
                    return true;
                }
            }
        }

        // Check diagonally (bottom-left to top-right)
        for (row = 3; row < rows; row++) {
            for (col = 0; col <= columns - 4; col++) {
                if (grid[row][col] == symbol &&
                    grid[row - 1][col + 1] == symbol &&
                    grid[row - 2][col + 2] == symbol &&
                    grid[row - 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Check diagonally (top-left to bottom-right)
        for (row = 0; row <= rows - 4; row++) {
            for (col = 0; col <= columns - 4; col++) {
                if (grid[row][col] == symbol &&
                    grid[row + 1][col + 1] == symbol &&
                    grid[row + 2][col + 2] == symbol &&
                    grid[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }
    
        return false;
    }


    public boolean isFull() {
        int i, j;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
    // self explanatory  
            }
        }
        return true;
    }
}

class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}
