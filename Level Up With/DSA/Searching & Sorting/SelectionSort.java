public class SelectionSort {
    public static void main(String[] args) {
        int arr[] = { 7, 6, 8, 4, 5, 2, 3, 1, 9, };
        // Selection Sort
        for (int i = 0; i < arr.length - 1; i++) {
            int SmallIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[SmallIndex] > arr[j]) {
                    SmallIndex = j;
                }
            }
            //Swap
            int temp = arr[SmallIndex];
            arr[SmallIndex] = arr[i];
            arr[i] = temp;
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
