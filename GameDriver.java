/******************************************************************************
* Written by: Tyler Moroni                                                    *
* Code for group project                                                      *
******************************************************************************/
import java.io.*;
import java.util.*;

public class GameDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Game Center!");
        
        boolean playAgain;
        do {
            System.out.println("Select a game to play:");
            System.out.println("1. Connect Four");
            System.out.println("2. Another Game (To Be Implemented)");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playConnectFour();
                    break;
                case 2:
                    System.out.println("Sorry, the second game is not implemented yet.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid game.");
            }

            System.out.print("Do you want to play another game? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        } while (playAgain);

        System.out.println("Thanks for playing at the Game Center!");
    }

    public static void playConnectFour() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Connect Four!");

        String player1Name = getPlayerName(scanner, "Player 1");
        String player2Name = getPlayerName(scanner, "Player 2");

        boolean playAgain;
        do {
            ConnectFourGame.playConnectFour(player1Name, player2Name);
            System.out.print("Do you want to play Connect Four again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        } while (playAgain);
    }

    public static String getPlayerName(Scanner scanner, String playerLabel) {
        System.out.print(playerLabel + ", enter your name: ");
        return scanner.next();
    }
}
