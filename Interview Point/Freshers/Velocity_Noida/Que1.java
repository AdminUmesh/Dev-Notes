// Print given integer in readable time formate (Minut, Hour, Second);
// Input 47 = 47 Second(s)
// Input 70 = 1 minute(s) 10 second(s)
// Input 3602 = 1 hour(s) 0 minut(s) 2 second(s)
import java.util.Scanner;
public class Que1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Number");
        int Num=sc.nextInt();
        sc.close();
        
        Times(Num);
    }

    static void Times(int Num){
        int Hour = Num/(60*60);
        int RestHour=Num%(60*60);
        int Minut = RestHour/60;
        int RestMinut =RestHour%60;
        int Second = RestMinut;

        if(Hour>0){
            System.out.println(Hour + " Hour(s)" + Minut + " Minut(s)" + Second + " Second(s)");
        }else if(Minut>0){
            System.out.println(Minut + " Minut(s)" + Second + " Second(s)");
        }else{
            System.out.println(RestMinut + " Second(s)");
        }
    }
}