package resource;

/**
 * Created by local on 05-Feb-17.
 */
public class SelectionSort implements Sorted {

    @Override
    public void sortArray(int[] arr) {

        int minIndex, tmp;

        System.out.println("Sorted with selection");
        for(int i = 0; i<arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n=================================================");


        for (int i = 0; i < (arr.length-1); i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;

                if (minIndex != i) {
                    tmp = arr[i];
                    arr[i] = arr[minIndex];
                    arr[minIndex] = tmp;
                }
            }
        }


        for(int i = 0; i<arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }
}
