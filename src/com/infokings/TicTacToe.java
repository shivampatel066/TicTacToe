package com.infokings;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToe {
    private char board[][] = new char[3][3];
    private int currentRow = 0;
    private int currentColumn = 0;
    private final char PLAYER_X = 'X';
    private final char PLAYER_O = 'O';

    TicTacToe() {
        char k = 'a';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = k;
                k++;
            }
        }
    }

    private void drawGameUI() {
        System.out.printf("┌─────┬─────┬─────┐\n");
        System.out.printf("│  %s  │  %s  │  %s  │\n", board[0][0], board[0][1], board[0][2]);
        System.out.printf("├─────┼─────┼─────┤\n");
        System.out.printf("│  %s  │  %s  │  %s  │\n", board[1][0], board[1][1], board[1][2]);
        System.out.printf("├─────┼─────┼─────┤\n");
        System.out.printf("│  %s  │  %s  │  %s  │\n", board[2][0], board[2][1], board[2][2]);
        System.out.printf("└─────┴─────┴─────┘\n");
    }

    private void setSymbol(char symbol) {
        board[currentRow][currentColumn] = symbol;
    }

    private boolean isCellEmpty() {
        return (board[currentRow][currentColumn] != PLAYER_X && board[currentRow][currentColumn] != PLAYER_O);
    }

    private boolean checkWinCondition(char currentPlayer) {
        int vertical = 0, horizontal = 0, diagonalLeftToRight = 0, diagonalRightToLeft = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (column == currentColumn) {
                    if (board[row][column] == currentPlayer) {
                        vertical++;
                    }
                }

                if (row == currentRow) {
                    if (board[row][column] == currentPlayer) {
                        horizontal++;
                    }
                }

                if (row == column) {
                    if (board[row][column] == currentPlayer) {
                        diagonalRightToLeft++;
                    }
                }

                if ((row + column) == 2) {
                    if (board[row][column] == currentPlayer) {
                        diagonalLeftToRight++;
                    }
                }
            }
        }
        return (vertical == 3 || horizontal == 3 || diagonalLeftToRight == 3 || diagonalRightToLeft == 3);
    }


    public void play() throws IOException {
        int totalRounds = 0;

        // draw the initial board
        drawGameUI();

        boolean turn = true;

        while (true) {
            char currentPlayer = turn ? PLAYER_O : PLAYER_X;
            System.out.printf("Turn %c:\n", currentPlayer);
            Scanner get = new Scanner(System.in);
            char inputChar = get.next().charAt(0);

            if (inputChar < 'a' || inputChar > 'i') {
                System.out.println("Wrong Input Try Again:");
                continue;
            }
            
            // 0  1  2  3  4  5  6  7  8
            // a, b, c, d, e, f, g, h, i
            // 00 01 02 10 11 12 20 21 22

            currentRow = (inputChar - 'a') / 3;
            currentColumn = (inputChar - 'a') % 3;

            if(! isCellEmpty()) {
                System.out.println("Cell is not empty. Please try again:");
                continue;
            }

            setSymbol(currentPlayer);
            totalRounds++;

            drawGameUI();

            if (checkWinCondition(currentPlayer)) {
                System.out.printf("Player %c is the winner !! Congratulations\n", currentPlayer);
                System.exit(0);
            }

            if (totalRounds == 9) {
                System.out.println("Its a DRAW\n");
                System.exit(0);
            }
            turn = !turn;
        }
    }
}
