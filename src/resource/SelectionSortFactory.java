package resource;

/**
 * Created by local on 05-Feb-17.
 */
public class SelectionSortFactory implements SortedFactory {
    @Override
    public Sorted create() {
        return new SelectionSort();
    }
}
