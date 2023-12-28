package player_and_computer;

import java.util.Scanner;

public class Game {
    static int boardSize;
    static char[][] playerBoard;
    static final char HUMAN_SYMBOL = 'X';
    static final char COMPUTER_SYMBOL = 'O';
    static char currentSymbol = HUMAN_SYMBOL;
    static int moveCount = 0;


    public static void main(String[] args) {
        boardSize = establishBoardSize();
        playerBoard = new char[boardSize][boardSize];
        boolean win = false;
        do {
            printBoard();
            boolean correctMove = performMove();
            if (correctMove) {
                moveCount++;
                win = checkIfWon();
                if (win) {
                    System.out.println("Brawo. " + currentSymbol + " wygrał.");
                }
                currentSymbol = currentSymbol == HUMAN_SYMBOL ? COMPUTER_SYMBOL : HUMAN_SYMBOL;
            } else {
                System.out.println("Niepoprawny ruch. Spróbuj jeszcze raz.");
            }
        } while (moveCount < boardSize * boardSize && !win);
        printBoard();
        System.out.println("Koniec gry :)");
    }

    static int establishBoardSize() {
        System.out.println("Podaj rozmiar planszy:");
        Scanner board = new Scanner(System.in);
        return board.nextInt();
    }

    static void printBoard() {
        System.out.println();
        System.out.print("\t");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        for (int row = 0; row < boardSize; row++) {
            System.out.print(row + ":  ");
            for (int column = 0; column < boardSize; column++) {
                char symbol = playerBoard[row][column];
                symbol = symbol != 0 ? symbol : ' ';
                System.out.print(symbol + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean performMove() {
        int[] playerMove;
        if (currentSymbol == HUMAN_SYMBOL) {
            playerMove = HumanAlgorithm.getMove();
        } else {
            playerMove = ComputerAlgorithm.getMove();
        }
        int row = playerMove[0];
        int column = playerMove[1];
        if (checkIfCorrectMove(row, column) && checkIfEmptySquare(row, column)) {
            playerBoard[row][column] = currentSymbol;
            return true;
        }
        return false;
    }

    static boolean checkIfCorrectMove(int row, int column) {
        return row >= 0 && row <= boardSize && column >= 0 && column <= boardSize;
    }

    static boolean checkIfEmptySquare(int row, int column) {
        return playerBoard[row][column] == 0;
    }

    static boolean checkIfWon() {
        return checkRows() ||
                checkColumns() ||
                checkDiagonal1() ||
                checkDiagonal2();
    }

    static boolean checkRows() {
        for (int row = 0; row < playerBoard.length; row++) {
            boolean win = true;
            int sameMarkCount = 0;
            for (int column = 0; column < playerBoard.length; column++) {
                if (playerBoard.length <= 5) {
                    if (playerBoard[row][column] != currentSymbol) {
                        win = false;
                        break;
                    }
                } else {
                    if (playerBoard[row][column] != currentSymbol) {
                        sameMarkCount = 0;
                        win = false;
                    } else {
                        sameMarkCount++;
                        if (sameMarkCount == 5) {
                            win = true;
                            break;
                        }
                    }
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    static boolean checkColumns() {
        for (int column = 0; column < playerBoard.length; column++) {
            boolean win = true;
            int sameMarkCount = 0;
            for (int row = 0; row < playerBoard.length; row++) {
                if (playerBoard.length <= 5) {
                    if (playerBoard[row][column] != currentSymbol) {
                        win = false;
                        break;
                    }
                } else {
                    if (playerBoard[row][column] != currentSymbol) {
                        sameMarkCount = 0;
                        win = false;
                    } else {
                        sameMarkCount++;
                        if (sameMarkCount == 5) {
                            win = true;
                            break;
                        }
                    }
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonal1() {
        boolean win = true;
        int sameMarkCount = 0;
        for (int i = 0; i < playerBoard.length; i++) {
            if (playerBoard.length <= 5) {
                if (playerBoard[i][i] != currentSymbol) {
                    win = false;
                    break;
                }
            } else {
                if (playerBoard[i][i] != currentSymbol) {
                    sameMarkCount = 0;
                    win = false;
                } else {
                    sameMarkCount++;
                    if (sameMarkCount == 5) {
                        win = true;
                        break;
                    }
                }
            }
        }
        return win;
    }

    static boolean checkDiagonal2() {
        boolean win = true;
        int sameMarkCount = 0;
        for (int i = 0; i < playerBoard.length; i++) {
            if (playerBoard.length <= 5) {
                if (playerBoard[i][playerBoard.length - 1 - i] != currentSymbol) {
                    win = false;
                    break;
                }
            } else {
                if (playerBoard[i][playerBoard.length - 1 - i] != currentSymbol) {
                    sameMarkCount = 0;
                    win = false;
                } else {
                    sameMarkCount++;
                    if (sameMarkCount == 5) {
                        win = true;
                        break;
                    }
                }
            }
        }
        return win;
    }
}
