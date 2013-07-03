
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Tests the LinkedList implementation.
 *
 * @author Zach Tomaszewski and Jonathan Waltz
 */
public class JonA06 {

    /*
     * NOTE: This classes use a JUnit-like format. Specifically, the
     * assert methods are as they are in JUnit.  The one drawback to
     * JUnit is you need to install it to use it.  (And it is not installed
     * on Tamarin.)  So I just duplicated the most important method here.
     *
     * You may learn more about JUnit in lab.
     */
    private static int tests = 0;
    private static int passed = 0;

    /**
     * Prints results of unit tests to the screen.
     */
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        assertEquals("New list is empty", 0, list.size());

        //testing iterator
        ListIterator<Integer> iter = list.listIterator();
        System.out.println("Getting iter on empty list.");
        assertEquals("iter.hasNext()", false, iter.hasNext());
        assertEquals("iter.nextIndex()", 0, iter.nextIndex());
        try {
            iter.next();
            assertTrue("iter.next() throws NSEE", false);
        } catch (Exception e) {
            assertTrue("iter.next() throws NSEE",
                    e instanceof NoSuchElementException);
        }

        //...you should also test that your previous-related methods
        // work correctly on an empty list


        System.out.println("Using iter to add: 1, 2, 3");
        iter.add(1);
        iter.add(2);
        iter.add(3);
        assertEquals("iter.hasNext()", false, iter.hasNext());
        assertEquals("iter.nextIndex()", 3, iter.nextIndex());
        assertEquals("list.size()", 3, list.size());

        System.out.println("Getting new iterator at index(1)");
        iter = list.listIterator(1);
        assertEquals("iter.hasNext()", true, iter.hasNext());
        assertEquals("iter.nextIndex()", 1, iter.nextIndex());
        assertEquals("iter.next()", 2, iter.next());
        assertEquals("iter.next()", 3, iter.next());
        assertEquals("iter.hasNext()", false, iter.hasNext());

        //...you can add your own similar tests here to test moving
        // backwards through the list

        //you should also test set and remove


        // Testing list's Collection constructor
        System.out.println();
        System.out.println("Testing LinkedList now");
        Collection<Integer> primeValues = Arrays.asList(5, 19, 23, 29);
        List<Integer> primes = new LinkedList<Integer>(primeValues);
        assertEquals("LinkedList(Collection) works", primeValues, primes);

        // From here on, we're using methods we did not have to write.
        // Though I call only a few of them below, I wanted to demonstrate how
        // our LinkedList now provides ALL of the List and Collection methods
        // just by writing the few methods we wrote.  As long our implementation
        // is correct, all of these other methods will be too.  That's pretty cool!
        //
        System.out.println("Our new list: " + primes); //a useful toString()!
        assertEquals("New primesList has 4 elements", 4, primes.size());
        assertEquals("list contains 19", true, primes.contains(19));
        //assertEquals("index of 23", 2, primes.indexOf(23)); //requires previousIndex()
        System.out.print("Adding 7, 11, 13 at index 1: ");
        primes.addAll(1, Arrays.asList(7, 11, 13));
        System.out.println(primes);
        assertTrue("list now contains 7 and 19",
                primes.containsAll(Arrays.asList(7, 19)));
        System.out.print("List as an array: ");
        System.out.println(Arrays.toString(primes.toArray(new Integer[0])));
        
        List<Integer> myList = new LinkedList<Integer>();
        myList.add(100);
        myList.add(101);
        myList.add(5);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(1);
        ListIterator<Integer> myIter = myList.listIterator();
        assertEquals("Next returns 100", 100, myIter.next());
        assertEquals("Next returns 101", 101, myIter.next());
        assertEquals("Previous returns 101", 101, myIter.previous());
        myIter.set(102);
        assertEquals("Next returns 102", 102, myIter.next());
        myIter.remove();
        assertEquals("Previous index is 0", 0, myIter.previousIndex());
        assertEquals("Next index is 1", 1, myIter.nextIndex());
        assertEquals("Previous returns 100", 100, myIter.previous());
        myIter.remove();
        assertEquals("Previous index is -1", -1, myIter.previousIndex());
        assertEquals("Next index is 0", 0, myIter.nextIndex());
        assertEquals("Next returns 5", 5, myIter.next());
        myIter.remove();
        assertEquals("Next returns 2", 2, myIter.next());
        assertEquals("Next returns 3", 3, myIter.next());
        myIter.remove();
        assertEquals("Next returns 4", 4, myIter.next());
        assertEquals("Next returns 1", 1, myIter.next());
        myIter.remove();
        assertEquals("Previous index is 1", 1, myIter.previousIndex());
        assertEquals("Next index is 2", 2, myIter.nextIndex());
        assertEquals("Previous returns 4", 4, myIter.previous());
        myIter.remove();
        assertEquals("Previous returns 2", 2, myIter.previous());
        myIter.remove();
        assertEquals("Previous index is -1", -1, myIter.previousIndex());       
        assertEquals("Next index is 0", 0, myIter.nextIndex());
        myIter.add(6);
        for (Integer integer : myList) {
            System.out.println(integer);
        }
        assertEquals("List size updated to 1", 1, myList.size());

        System.out.println();
        System.out.println("Passed " + passed + " of " + tests + " tests.");
    }

    /**
     * Prints the given message that describes this test and a PASS or FAIL
     * header depending on whether the expected value actually equals the actual
     * value. Also updates the total number of tests and the number passed.
     */
    public static void assertEquals(String mesg, Object expected, Object actual) {
        tests++;
        if (expected.equals(actual)) {
            System.out.print("[PASS] ");
            passed++;
        } else {
            System.out.print("[FAIL] ");
        }
        System.out.println(mesg + " [" + expected + "]: " + actual);
    }

    public static void assertTrue(String mesg, boolean actual) {
        assertEquals(mesg, true, actual);
    }
}