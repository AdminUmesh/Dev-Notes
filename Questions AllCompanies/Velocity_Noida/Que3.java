// Most frequent matching element in array
// Array1 [1,2,2,3,4,4,2,5,1,1,1,1,1,1]
//Array2 [2,3,4]

public class Que3 {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 3, 4, 4, 2, 5, 1, 1, 1, 1, 1, 1};
        int[] array2 = {2, 3, 4};
        int[] array = new int[100];
        int count = 0;

        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array1.length; j++) {
                if (array2[i] == array1[j]) {
                    count++;
                }
            }
            array[array2[i]] = count;
            count = 0;
        }

        int maxFrequency = 0;
        int mostFrequentElement = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxFrequency) {
                maxFrequency = array[i];
                mostFrequentElement = i;
            }
        }

        System.out.println(mostFrequentElement);
    }
}

