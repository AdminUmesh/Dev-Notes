public class insertionSort {
    public static void main(String[] args) {
        int arr[] = { 7, 6, 8, 4, 5, 2, 3, 1, 9, };
        // Selection Sort
        for (int i = 1; i < arr.length; i++) {
            int Current = arr[i];
            int j=i-1;
            while (j >= 0 && Current< arr[j]) {
                arr[j+1]=arr[j];
                j--;
            }
            //Swap
            arr[j+1] = Current;
        }

        PrintArray(arr);
    }

    // PrintArray
    public static void PrintArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    } 
}
