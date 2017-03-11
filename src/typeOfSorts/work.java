package typeOfSorts;


import javax.swing.*;
import java.util.Random;

public class work {

    public static void main(String[] args) {

        Random random = new Random();
        int array[] = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(20);
        }

        SortedFactory sortedFactory = createSortMethod(JOptionPane.showInputDialog(null, "bubble/selection/merge/quick"));
        Sorted sorted = sortedFactory.create();

        sorted.sortArray(array);
    }

    static SortedFactory createSortMethod(String spec) {
        if (spec.equalsIgnoreCase("bubble")) {
            return new BubbleSortFactory();
        } else if (spec.equalsIgnoreCase("selection")) {
            return new SelectionSortFactory();
        } else if (spec.equalsIgnoreCase("merge")) {
            return new MergeSortFactory();
        } else if (spec.equalsIgnoreCase("quick")) {
            return new QuickSortFactory();
        } else {
            throw new RuntimeException("NOT FOUND!");
        }
    }

}
