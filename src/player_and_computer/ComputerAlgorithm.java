package player_and_computer;

import java.util.Random;

public class ComputerAlgorithm {
    static boolean found = false;
    static int[] computedMove = new int[2];

    static int[] getMove() {
        found = false;
        checkCentre();
        if (found) return computedMove;
        checkRow();
        if (found) return computedMove;
        checkColumn();
        if (found) return computedMove;
        drawRandomly();
        if (found) return computedMove;
        return computedMove;
    }

    static void checkCentre() {
        if (Game.boardSize == 3 && Game.playerBoard[1][1] == 0) {
            System.out.println("Ruch komputera: 1, 1");
            computedMove[0] = 1;
            computedMove[1] = 1;
            found = true;
        }
    }

    static void checkRow() {
        for (int row = 0; row < Game.playerBoard.length; row++) {
            for (int column = 0; column < Game.playerBoard.length; column++) {
                if (Game.playerBoard[row][column] == Game.HUMAN_SYMBOL) {
                    break;
                }
                if (column == Game.boardSize - 1 && Game.playerBoard[row][column] == 0) {
                    System.out.println("Ruch komputera: " + row + ", " + column);
                    computedMove[0] = row;
                    computedMove[1] = column;
                    found = true;
                    return;
                }
            }
        }
    }

    static void checkColumn() {
        for (int column = 0; column < Game.playerBoard.length; column++) {
            for (int row = 0; row < Game.playerBoard.length; row++) {
                if (Game.playerBoard[row][column] == Game.HUMAN_SYMBOL) {
                    break;
                }
                if (row == Game.boardSize - 1 && Game.playerBoard[row][column] == 0) {
                    System.out.println("Ruch komputera: " + row + ", " + column);
                    computedMove[0] = row;
                    computedMove[1] = column;
                    found = true;
                    return;
                }
            }
        }
    }

    static void drawRandomly() {
        int row;
        int column;
        Random draw = new Random();
        do {
            row = draw.nextInt(Game.boardSize);
            computedMove[0] = row;
            column = draw.nextInt(Game.boardSize);
            computedMove[1] = column;
        } while (Game.playerBoard[row][column] != 0);
        found = true;
    }
}
