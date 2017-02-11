package typeOfSorts;

public class QuickSort implements Sorted {
    @Override
    public void sortArray(int[] arr) {
        System.out.println("Quick sorted: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("\n=================================================");


        qSort(arr,0, arr.length-1);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }


    public void qSort(int[] array, int l, int r) {
        int i = l;
        int j = r;
        int x = array[j];
        while (i <= j) {
            while (array[i] < x) {
                i++;
            }
            while (array[j] > x) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (l<j){
            qSort(array, l, j);
        }
        if(i<r){
            qSort(array, i, r);
        }
    }
}
