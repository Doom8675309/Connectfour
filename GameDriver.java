/******************************************************************************
* Written by: Tyler Moroni                                                    *
* Code for group project                                                      *
******************************************************************************/
import java.io.*;
import java.util.*;

public class GameDriver {
    public static void main(String[] args) {
        Scanner scanner;
        int choice;
        scanner= new Scanner(System.in);

        System.out.println("Welcome to the Game Center!");
        
        boolean playAgain;
        do {
        // Asks what game would like to be played
            System.out.println("Select a game to play:");
            System.out.println("1. Connect Four");
            System.out.println("2. Another Game (To Be Implemented)");

            choice = scanner.nextInt();
       // Choices for games go here
            switch (choice) {
                case 1:
                    playConnectFour();
                    break;
                case 2:
                    System.out.println("Sorry, the second game is not" +
                        " implemented yet.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a" +
                     " valid game.");
            }
       // When game is done ask if you want to change to another game
            System.out.print("Do you want to play another game? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        } while (playAgain);

        System.out.println("Thanks for playing at the Game Center!");
    }
       // Code for game 1 
    public static void playConnectFour() {
        Scanner scanner;
        String player1Name, player2Name;
        scanner = new Scanner(System.in);

        System.out.println("Welcome to Connect Four!");
       // Asks for player name in order to correctly show whos turn it is
        player1Name = getPlayerName(scanner, "Player 1");
        player2Name = getPlayerName(scanner, "Player 2");
       // Running code to see if the player would like to play again
        boolean playAgain;
        do {
            ConnectFourGame.playConnectFour(player1Name, player2Name);
            System.out.print("Do you want to play Connect Four again?" +
            "(yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        } while (playAgain);
    }

    public static String getPlayerName(Scanner scanner, String playerLabel) {
        System.out.print(playerLabel + ", enter your name: ");
        return scanner.next();
    }
}
