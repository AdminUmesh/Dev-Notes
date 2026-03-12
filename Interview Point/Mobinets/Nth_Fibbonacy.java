//Find Nth fibbonaci Number

import java.util.Scanner;
public class Nth_Fibbonacy {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Which method You Want to excute (Enter 1 or 2)");
        int method=sc.nextInt();
        int n=10;
        sc.close();
        if(method==1){
            System.out.println(Method1(n));
        }else{
            System.out.println(Method2(n));
        }
    }

    //Method1
    public static int Method1(int n){
        int first=1;
        int second=2;
        int third=0;
        for(int i=3; i<=n; i++){
            if(i==1){
                third=first;
            }else if(i==2){
                third=second;
            }else{
                third=first+second;
                first=second;
                second=third;
            }
        }
        return third;
    }

    //Method2
    public static Long Method2(int n){
        Long l=1L;
        if(n<=1){
            return l;
        }else{
            return Method2(n-1) + Method2(n-2);
        }
    }
}
