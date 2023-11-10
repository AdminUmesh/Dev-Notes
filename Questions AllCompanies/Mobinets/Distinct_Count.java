// Count Total Distinct Number in given array
// int arr[]={1,2,3,3,4,5,6,2,4,8};

import java.util.HashSet;

public class Distinct_Count{
    public static void main(String [] args){
        int arr[]={1,2,3,3,4,5,6,2,4,8};
        HashSet <Integer> hs=new HashSet<>();
        for(int i:arr){
            hs.add(i);
        }
        System.out.println(hs.size());
    }
}