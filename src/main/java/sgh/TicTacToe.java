package sgh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class TicTacToe {
    public static List<List<Integer>> tet (String boardFileName) throws FileNotFoundException {
        File boardFile = new File(boardFileName);
        Scanner scanner = new Scanner(boardFile);

        List<List<Integer>> newBoard = new ArrayList<>();

        while (scanner.hasNextLine()) {
            ArrayList<Integer> li  = new ArrayList<>();
            
            li.add(0);
            li.add(0);
            li.add(0);

            String line = scanner.nextLine();
            int i = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'o') {
                    li.set(i, -1);
                    j++;
                    i++;
                } else if (line.charAt(j) == 'x') {
                    li.set(i, 1);
                    j++;
                    i++;
                } else if (line.charAt(j) == ';') {
                    i++;
                }
            }
            newBoard.add(li);
        }
        return newBoard;
    }
  

    public static int rowSum(List<List<Integer>> matrix, int rowIndex) {
        int sum = 0;
        List<Integer> row = matrix.get(rowIndex);
        for (Integer elem : row) {
            sum += elem;
        }
        return sum;
    }


    public static int columnSum(List<List<Integer>> matrix, int columnIndex) {
        int sum = 0;
        for (List<Integer> row : matrix) {
            sum += row.get(columnIndex);
        }
        return sum;
    }


    public static int FirstDiagonal(List<List<Integer>> matrix) {
        int sum = 0;
        int columnIndex = 0;
        for (List<Integer> row : matrix) {
            sum += row.get(columnIndex);
            columnIndex++;
        }
        return sum;
    }


    public static int SecondDiagonal(List<List<Integer>> matrix) {
        int sum = 0;
        int columnIndex = 2;

        for (List<Integer> row : matrix) {
            sum += row.get(columnIndex);
            columnIndex--;
        }

        return sum;
    }


    public enum Result { NOT_FINISHED, NO_WINNER, X_WON, O_WON }

    public static Result checkBoard(String boardFileName) throws FileNotFoundException {
        List<List<Integer>> finalBoard = tet(boardFileName);

        int sum1Diagonal = FirstDiagonal(finalBoard);
        int sum2Diagonal = SecondDiagonal(finalBoard);

        if (sum1Diagonal == 3 || sum2Diagonal == 3) {
            return Result.X_WON;
        } else if (sum1Diagonal == -3 || sum2Diagonal == -3) {
            return Result.O_WON;
        }

        for (int n = 0; n < 2; n++) {
            if (rowSum(finalBoard, n) == 3 || columnSum(finalBoard, n) == 3) {
                return Result.X_WON;
            } else if (rowSum(finalBoard, n) == -3 || columnSum(finalBoard, n) == -3) {
                return Result.O_WON;
            }
        }

        for (List<Integer> row : finalBoard) {
            for (int elem : row) {
                if (elem == 0) {
                    return Result.NOT_FINISHED;
                }
            }
        }
        return Result.NO_WINNER;
    }

    public static void main(String[] args) {
        Result res = checkBoard("boards/tick0.csv");
        System.out.println(res);
    }
}
