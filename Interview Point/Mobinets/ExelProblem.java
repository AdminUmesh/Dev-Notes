// if a/A=1 and z/Z=26 so on then AA=11 amd AB=12 then find the value of a string

import java.util.Scanner;
public class ExelProblem {
    public static void main(String[] args) {
        int number=0;
        Scanner sc=new Scanner (System.in);
        System.out.println("Enter the exel collums");
        String str=sc.nextLine();
        sc.close();

        for(int i=0; i<str.length(); i++){
            number=number*10+(Character.toUpperCase(str.charAt(i))-64);
        }
        System.out.println(number);
    }
}