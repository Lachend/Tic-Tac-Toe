package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] field = buildField();

 //       getState(field);
        printField(field);

        while (checkGameState(field)) {

            getMove(field);
            printField(field);
        }

    }

    public static void printField(String[][] field) {
        System.out.println(Arrays.deepToString(field)
                .replace("], ", "\n")
                .replace(", ", " ")
                .replace("[", "")
                .replace("]", "")
                .replace("  ", ""));

//        for (String[] col : field) {
//            for (String i: col) {
//                System.out.print(i);
//            }
//            System.out.println();
//        }
    }

    public static String[][] buildField() {

        return new String[][]{{"---------", "", "", "", ""},
                {"|", "_", "_", "_", "|"},
                {"|", "_", "_", "_", "|"},
                {"|", "_", "_", "_", "|"},
                {"---------", "", "", "", ""}};
    }


    public static void getState(String[][] field) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cells: ");
        String userInput = scanner.next();

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                field[i][j] = String.valueOf(userInput.charAt((i - 1) * 3 + j - 1));
            }
        }
    }

    public static boolean checkGameState(String[][] field) {
        int Xs = 0;
        int Os = 0;

        boolean isXWin = false;
        boolean isOWin = false;


        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                // count Os and Xs
                Xs += field[i][j].equals("X") ? 1 : 0;
                Os += field[i][j].equals("O") ? 1 : 0;

                // check X,O win states
                isXWin = checkWin(field, "X");
                isOWin = checkWin(field, "O");


            }
        }

        if (Math.abs(Xs - Os) > 1 || isOWin && isXWin) {
            System.out.println("Impossible");
        } else if (isOWin) {
            System.out.println("O wins");
        } else if (isXWin) {
            System.out.println("X wins");
        } else if (Xs + Os == 9) {
            System.out.println("Draw");
        } else {
            return true;
        }
        return false;


    }

    public static boolean checkWin(String[][] field, String player) {
        int inLine = 0;
        //check rows
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (field[i][j].equals(player)) {
                    inLine++;
                }
                if (inLine == 3) {
                    return true;
                }
            }
            inLine = 0;
        }
        //check columns
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (field[j][i].equals(player)) {
                    inLine++;
                }
                if (inLine == 3) {
                    return true;
                }
            }
            inLine = 0;
        }
        //check diagonals
        for (int i = 1; i < 4; i++) {
            if (field[i][i].equals(player)) {
                inLine++;
            }
            if (inLine == 3) {
                return true;
            }
        }
        inLine = 0;
        for (int i = 1; i < 4; i++) {
            if (field[i][4 - i].equals(player)) {
                inLine++;
            }
            if (inLine == 3) {
                return true;
            }
        }


        return false;
    }

    public static void getMove(String[][] field) {
        int inputRow, inputCol;
        int Xs = 0, Os = 0;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                // count Os and Xs
                Xs += field[i][j].equals("X") ? 1 : 0;
                Os += field[i][j].equals("O") ? 1 : 0;
            }
        }

        String player = Xs > Os ? "O" : "X";


        while (true) {

            System.out.print("Enter the coordinates:");

            try {
                Scanner scanner = new Scanner(System.in);

                inputRow = scanner.nextInt();
                inputCol = scanner.nextInt();

            } catch (InputMismatchException a) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (inputRow < 1 || inputCol < 1 || inputCol > 3 || inputRow > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (field[inputRow][inputCol].equals("X") || field[inputRow][inputCol].equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            field[inputRow][inputCol] = player;
            return;

        }
    }

}
