import java.io.FileNotFoundException;
import java.util.List;
import java.util.Iterator;

public class JonA08 {

    public static void main(String[] args) throws FileNotFoundException {
        //lets do some sorting
    }

    /**
     * An optimized version of the classic Insertion Sort algorithm.
     * <p>This algorithm iterates one element at a time, building up a sorted section of the given list.
     * It will continue this process until the entire list is sorted.</p>
     * <ul>
     *     <li>Worst Case: O(n^2)</li>
     *     <li>Average Case: O(n^2)</li>
     *     <li>Best Case: O(n)</li>
     *     <li>Optimized to use binary search for inserting</li>
     * </ul>
     * @param list List of items for sorting.
     * @param <E> Type of the list's data.
     */
    public static <E extends Comparable<E>> void insertionSort(List<E> list) {
        int insertAt;
        for (int i = 1; i < list.size(); i++) {
            E key = list.get(i);
            insertAt = binarySearch(list, 0, i - 1, key);
            for (int j = i; j > insertAt; j--) {
                swap(list, j, j - 1);
            }
            list.set(insertAt, key);
        }

    }

    /**
     * Swap method to easily exchange the position of two elements in a list.
     * @param list The list containing the items to be swapped.
     * @param firstIndex The position of the first item.
     * @param secondIndex The position of the second item.
     * @param <E> Type of the list's data.
     */
    private static <E extends Comparable<E>> void swap(List<E> list, int firstIndex, int secondIndex) {
        E temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }

    /**
     * Runs quickSort on a list of comparable elements.
     * @param list The list to be sorted.
     * @param <E> Type of the list's data.
     */
    public static <E extends Comparable<E>> void quickSort(List<E> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * Recursively sorts a list of elements by partitioning the list into two sublists according to a pivot element.
     * <ul>
     *     <li>Best Case: O(n log n)</li>
     *     <li>Average Case: O(n log n)</li>
     *     <li>Worst Case: O(n^2)</li>
     * </ul>
     * @param list The list to be sorted.
     * @param start Low end of the range.
     * @param end High end of the range.
     * @param <E> Type of the list's data.
     */
    private static <E extends Comparable<E>> void quickSort(List<E> list, int start, int end) {
        if (start < end) {
            int pivot = partition(list, start, end);
            quickSort(list, start, pivot);
            quickSort(list, pivot + 1, end);
        }
    }

    /**
     * Determines a pivot point and partitions the list into two groups: elements less than the pivot and elements
     * greater than the pivot.
     * @param list List of elements.
     * @param start Low end of the range.
     * @param end High end of the range.
     * @param <E> Type of the list's data.
     * @return Position of the pivot.
     */
    private static <E extends Comparable<E>> int partition(List<E> list, int start, int end) {
        int pivot = (int) (Math.random() * (end - start + 1)) + start;
        swap(list, pivot, end);
        pivot = end;

        int lower = start - 1;
        for (int upper = start; upper < end; upper++) {
            if (list.get(upper).compareTo(list.get(pivot)) < 0) {
                lower++;
                swap(list, lower, upper);
            }
        }

        lower++;
        swap(list, lower, pivot);
        pivot = lower;

        return pivot;
    }

    /**
     * Shellsort is a multiple-pass comparison sort that takes advantage of insertion sort's efficiency
     * when inserting an element near to the element's starting position. It does this by sorting every nth
     * element, where n is decreasing until n is 1 and the final pass over the data is a standard insertion sort.
     * <ul>
     *     <li>Worst Case: depends on gap sequence</li>
     *     <li>Average Case: depends on gap sequence</li>
     *     <li>Best Case: depends on gap sequence</li>
     * </ul>
     * @param list
     * @param <E>
     */
    public static <E extends Comparable<E>> void otherSort(List<E> list) {
        /*
         * These gap values are chosen based on Marcin Ciura's paper
         * "Best Increments for the Average Case of Shellsort"
         */
        int[] GAPS = {701, 301, 132, 57, 23, 10, 4, 1};

        int i, j;

        for (int gap : GAPS) {
            for (i = gap; i < list.size(); i++) {
                E temp = list.get(i);
                for (j = i; j >= gap && list.get(j - gap).compareTo(temp) > 0; j -= gap) {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, temp);
            }
        }

    }

    /**
     * Binary Search algorithm for specific use in the insertionSort method. This version is iterative and varies
     * from the typical binary search algorithm in that instead of returning -1 on not finding the element it returns
     * the low parameter, which represents where the element would go in the natural order of the list.
     * @param list The list to be searched.
     * @param low The index representing the lower end of the range being searched.
     * @param high The index representing the upper end of the range being searched.
     * @param key The item being searched for.
     * @param <E> Type of the list's data.
     * @return The position of the element if found, or the position where it would be otherwise.
     */
    private static <E extends Comparable<? super E>> int binarySearch(List<E> list, int low, int high, E key) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            if (key.compareTo(list.get(mid)) > 0) {
                low = mid + 1;
            }
            else if (key.compareTo(list.get(mid)) < 0) {
                high = mid - 1;
            }
            else {
                return mid;
            }
        }

        return low;
    }

    /**
     * Checks if a list is sorted. Useful when testing sorts.
      * @param iterable
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> boolean isSorted(Iterable<T> iterable) {
        Iterator<T> iter = iterable.iterator();
        if (!iter.hasNext()) {
            return true;
        }
        T t = iter.next();
        while (iter.hasNext()) {
            T t2 = iter.next();
            if (t.compareTo(t2) > 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }

}