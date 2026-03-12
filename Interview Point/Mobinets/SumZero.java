// find two number from array whose sum is zero

public class SumZero {
    public static void main(String [] args){
        int arr[]={1,-1,2,3,4,5,6,7,8,9};
        for(int i=0; i<arr.length-1; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i]+arr[j]==0){
                    System.out.println(arr[i]+", "+arr[j]);
                }
            }
        }
    }
}