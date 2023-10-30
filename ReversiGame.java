//////////////////////////////////
// Author: George Gherardi III
// Class: Programming II
// Assignment: Implementation Lab
//////////////////////////////////

import java.io.*;
import java.util.*;

// This code is an absolute mess so I tried to add a bunch of comments to make
// it easier to parce -GG

public class ReversiGame {
    private char[][] board;  // Array for the game board with X and O players
    public char currentPlayer;  // Keeps track of the current player

    // Sets up board with starting pieces
    public ReversiGame() {
        board = new char[8][8];
        currentPlayer = 'X';

        // This is where it actually places blanks & the starting pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }

        board[3][3] = board[4][4] = 'X';
        board[3][4] = board[4][3] = 'O';
    }

    // Prints the current state of the game board
    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Careful, There be Dragons
    public boolean isValidMove(int targetRow, int targetCol) {
        if (board[targetRow][targetCol] != '.') {
            return false; // If the cell is not empty, the move is invalid
        }

        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};  // Offsets for row
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};  // Offsets for column
        
        // to explain this a little these basically define the difference
        // between the piece you just placed and its relationship to the
        // pieces around it. This was the easiest way i could think of to do
        // this from a logic perspective. And was absolutely awful to make 
        // this work correctly but by the grace of the gods its working 
        // right now. -GG

        // This next part uses these offsets to determine surrounding pieces
        // & whether or not the move is valid based on those. Reversi sucks
        
        for (int direction = 0; direction < 8; direction++) {
            int currentRow = targetRow + rowOffsets[direction];
            int currentCol = targetCol + colOffsets[direction];
            while (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && 
            currentCol < 8 && board[currentRow][currentCol] == getOpponent()) {
                currentRow = currentRow + rowOffsets[direction];
                currentCol = currentCol + colOffsets[direction];
                if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && 
                currentCol < 8 && board[currentRow][currentCol]
                == currentPlayer) {
                    return true;
                    // If we find our own piece in this direction,
                    // the move is valid
                }
            }
        }

        return false; // If no valid direction was found, the move is invalid
    }

    // Makes a move at the specified row and column
    public void makeMove(int targetRow, int targetCol) {
        if (!isValidMove(targetRow, targetCol)) {
            System.out.println("Invalid move. Try again.");
            return;
        }

        board[targetRow][targetCol] = currentPlayer;
        
        // same thing I described before, this is the relationship between 
        // the current piece and pieces around it.
        
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        // This goes through the surrounding pieces and flips them accordingly
        // This was also an absolute pain in the butt to get working correctly
        for (int direction = 0; direction < 8; direction++) {
            int currentRow = targetRow + rowOffsets[direction];
            int currentCol = targetCol + colOffsets[direction];
            if (currentRow >= 0 && currentRow < 8 && currentCol >= 0 &&
            currentCol < 8 && board[currentRow][currentCol] ==
            getOpponent()) {
                while (currentRow >= 0 && currentRow < 8 && currentCol
                >= 0 && currentCol < 8) {
                    if (board[currentRow][currentCol] == '.') {
                        break;
                    }
                    if (board[currentRow][currentCol] == currentPlayer) {
                        currentRow = currentRow - rowOffsets[direction];
                        currentCol = currentCol - colOffsets[direction];
                        while (currentRow != targetRow || currentCol != 
                        targetCol) {
                            board[currentRow][currentCol] = currentPlayer;
                            currentRow = currentRow - rowOffsets[direction];
                            currentCol = currentCol - colOffsets[direction];
                        }
                        break;
                    }
                    currentRow = currentRow + rowOffsets[direction];
                    currentCol = currentCol + colOffsets[direction];
                }
            }
        }

        currentPlayer = getOpponent();
    }

    // Returns the opponent's player symbol
    public char getOpponent() {
        char opponent;
        if (currentPlayer == 'X')
            opponent = 'O';
        else
            opponent = 'X';

        return opponent;
    }

    // Checks if the game is over (no valid moves for either player)
    public boolean isGameOver() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == '.') {
                    if (isValidMove(i, j)) {
                        return false; // If there's a valid move, 
                        //the game is not over
                    }
                }
            }
        }
        return true; // If no valid moves were found, the game is over
    }

    // Determines the winner based on the number of pieces on the board
    public char getWinner() {
        int countX = 0, countO = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'X') {
                    countX++;
                } else if (board[i][j] == 'O') {
                    countO++;
                }
            }
        }

        char winner;
        if (countX > countO)
            winner = 'X';
        else if (countX < countO)
            winner = 'O';
        else
            winner = ' ';
        return winner;
    }
}
