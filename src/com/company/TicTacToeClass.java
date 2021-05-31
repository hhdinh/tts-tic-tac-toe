package com.company;

import java.util.Scanner;

public class TicTacToeClass {
    // Instance Variables
    private char[][] board;
    private boolean xTurn;
    private Scanner kb;

    // Constructors
    public TicTacToeClass() {
        // Create the board
        board = new char[3][3];

        // Initialize the board to all spaces
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++)
                board[r][c] = ' ';
        }

        // X will start first
        xTurn = true;

        kb = new Scanner(System.in);
    }

    // Accessor Methods
    public boolean isWinner(int lastR, int lastC) {
        boolean winner = false;

        // Symbol for the last move either X or O
        char symbol = board[lastR][lastC];

        // Check rows
        int numFound = 0;
        for (int c = 0; c < 3; c++) {
            if (board[lastR][c] == symbol) {
                numFound++;
            }
        }

        if (numFound == 3) {
            winner = true;
        }

        // Check columns
        numFound = 0;
        for (int r = 0; r < 3; r++) {
            if (board[r][lastC] == symbol) {
                numFound++;
            }
        }

        if (numFound == 3) {
            winner = true;
        }

        // Check diagonals
        numFound = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == symbol) {
                numFound++;
            }
        }

        if (numFound == 3) {
            winner = true;
        }

        numFound = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] == symbol) {
                numFound++;
            }
        }

        if (numFound == 3) {
            winner = true;
        }

        return winner;
    }

    public boolean isFull() {
        int numSpotsFilled = 0;

        for (int r = 0; r < 3; r++) {

            for (int c = 0; c < 3; c++) {
                if (board[r][c] == 'X' || board[r][c] == 'O')
                    numSpotsFilled++;
            }
        }

        return numSpotsFilled == 9;
    }

    // Display the board
    public void displayBoard() {
        System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("    --+-+--");
        System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("    --+-+--");
        System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
        System.out.println("     0 1 2 ");
    }

    // Modifiers
    public boolean playMove() {
        boolean invalid = true;
        int row = 0;
        int column = 0;
        char p;

        // Check for a valid location
        while (invalid) {
            if (xTurn == true) {
                p = 'X';
            } else {
                p = 'O';
            }

            System.out.println(p + ", choose your location (row, column). Enter two numbers between 0-2 " +
                    "separated by a space to indicate location.");
            row = kb.nextInt();
            column = kb.nextInt();

            // Check the location is within bounds
            if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
                if (board[row][column] != ' ')
                    System.out.println("That location is already taken");
                else
                    invalid = false;
            } else
                System.out.println("Invalid location");
        }

        // Fill board with valid locations
        if (xTurn) {
            board[row][column] = 'X';
        } else {
            board[row][column] = 'O';
        }

        return isWinner(row, column);
    }

    public void play() {
        while (true) {
            displayBoard();

            if (playMove()) {
                displayBoard();

                if (xTurn) {
                    System.out.println("X Wins!");
                } else {
                    System.out.println("O Wins!");
                }
                System.exit(0);
            } else if (isFull()) {
                displayBoard();
                System.out.println("Draw!");
                System.exit(0);
            } else {
                xTurn = !xTurn;
            }
        }
    }
}
