/* Print given pattern in java in input =4
        1  
    2 * 3 * 4  
  5 * * 6 * * 7
8 * * * 9 * * * 10 */

import java.util.Scanner;

public class Que2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Number Of Row");
        int input = sc.nextInt();
        sc.close();
        printPattern(input);
    }

    public static void printPattern(int n) {
        int num = 1;
        int TotalSpace = n;
        int count = 1;

        for (int Row = 1; Row <= n; Row++) {
            // Print Space
            for (int SpaceInRow = 1; SpaceInRow <= TotalSpace; SpaceInRow++) {
                System.out.print(" ");
            }

            // Print numbers and asterisks
            for (int TotalNumberInRow = 1; TotalNumberInRow <= 3; TotalNumberInRow++) {
                if (Row == 1 && TotalNumberInRow == 1 && count == 1) {
                    System.out.print(num);
                    num++;
                    count++;
                    TotalSpace--;
                    break;
                } else {
                    System.out.print(num + "");
                    num++;
                    // print arstristic
                    if (TotalNumberInRow < 3) {
                        for (int str = 1; str <Row; str++) {
                            System.out.print("*");
                        }
                    }
                }
            }

            System.out.println();
            TotalSpace--;
        }
    }
}
