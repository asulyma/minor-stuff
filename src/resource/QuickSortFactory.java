package resource;

/**
 * Created by local on 09-Feb-17.
 */
public class QuickSortFactory implements SortedFactory{
    @Override
    public Sorted create() {
        return new QuickSort();
    }
}
