package typeOfSorts;

import java.util.Arrays;

public class MergeSort implements Sorted {
    @Override
    public void sortArray(int[] arr) {
        System.out.println("Sorted with merge");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("\n=================================================");

        SortUnsorted(arr, 0, arr.length-1);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    private void SortUnsorted(int[] arr, int lo, int hi) {

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        SortUnsorted(arr, lo, mid);
        SortUnsorted(arr, mid + 1, hi);

        int[] buf = Arrays.copyOf(arr, arr.length);

        for (int k = lo; k <= hi; k++)
            buf[k] = arr[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                arr[k] = buf[j];
                j++;
            } else if (j > hi) {
                arr[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                arr[k] = buf[j];
                j++;
            } else {
                arr[k] = buf[i];
                i++;
            }
        }
    }
}
