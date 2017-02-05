package resource;

/**
 * Created by local on 05-Feb-17.
 */
public class BubbleSort implements Sorted {
    @Override
    public void sortArray(int[] arr) {
        boolean swapped = true;
        int j = 0;

        System.out.println("Sorted with bubble");
        for(int i = 0; i<arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n=================================================");


        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.length - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    swapped = true;
                }
            }
        }

        for(int i = 0; i<arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }

    }
}
