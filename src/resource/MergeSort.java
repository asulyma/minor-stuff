package resource;

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

    private void SortUnsorted(int[] a, int lo, int hi) {

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        SortUnsorted(a, lo, mid);
        SortUnsorted(a, mid + 1, hi);

        int[] buf = Arrays.copyOf(a, a.length);

        for (int k = lo; k <= hi; k++)
            buf[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                a[k] = buf[j];
                j++;
            } else if (j > hi) {
                a[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                a[k] = buf[j];
                j++;
            } else {
                a[k] = buf[i];
                i++;
            }
        }
    }
}
