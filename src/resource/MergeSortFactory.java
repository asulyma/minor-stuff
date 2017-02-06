package resource;

/**
 * Created by local on 06-Feb-17.
 */
public class MergeSortFactory implements SortedFactory {
    @Override
    public Sorted create() {
        return new MergeSort();
    }
}
