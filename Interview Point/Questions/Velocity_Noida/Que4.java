// find the 0 in given integer 2d matrix and and  replace the entire row and column with 0 

import java.util.Arrays;

public class Que4 {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 0, 5},
            {6, 7, 8}
        };

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[] rowFlags = new boolean[rows];
        boolean[] colFlags = new boolean[cols];

        // Mark the rows and columns containing 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowFlags[i] = true;
                    colFlags[j] = true;
                }
            }
        }

        // Replace rows with 0
        for (int i = 0; i < rows; i++) {
            if (rowFlags[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }

        // Replace columns with 0
        for (int j = 0; j < cols; j++) {
            if (colFlags[j]) {
                for (int i = 0; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Print the updated matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
