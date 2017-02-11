package typeOfSorts;

public class SelectionSort implements Sorted {

    private int minIndex;
    private int tmp;

    @Override
    public void sortArray(int[] arr) {
        System.out.println("Sorted with selection");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
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
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}
