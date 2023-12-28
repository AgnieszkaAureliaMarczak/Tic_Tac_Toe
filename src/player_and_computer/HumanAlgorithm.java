package player_and_computer;

import java.util.Scanner;

public class HumanAlgorithm {
    static int[] getMove() {
        int[] markCoordinates = new int[2];
        System.out.println("Twój ruch. \nPodaj numer wiersza:");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        markCoordinates[0] = row;
        System.out.println("Podaj numer kolumny:");
        int column = scanner.nextInt();
        markCoordinates[1] = column;
        return markCoordinates;
    }
}
