////////////////////////////////////////////////////////////////////
// Author: Tyler Moroni (Moved to seperate .java by George Gherardi)
// Class: Programming II
// Assignment: Implementation Lab
////////////////////////////////////////////////////////////////////

public class Player {
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