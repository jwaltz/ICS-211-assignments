import java.util.ArrayList;
import java.util.List;

/**
 * User: JonWorks
 * Date: 4/10/13
 * Time: 6:30 PM
 */
public class JonA09 {

    private static int tests = 0;
    private static int passed = 0;

    public static void main(String[] args) {

        /*
        Creates a new BST object with integers 1 through 7 inclusive for testing.
         */
        int[] myArray = new int[]
                {6, 2, 1, 5, 4, 3, 7};
        List<Integer> myList = new ArrayList<Integer>();
        for (int i : myArray) {
            myList.add(i);
        }

        BinarySearchTree<String> strTree = new BinarySearchTree<String>();
        BinarySearchTree.BinarySearchTreeIterator iterate = strTree.iterator();
        iterate.hasNext();
//        iterate.remove();


        BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>(myList);
        System.out.println(myTree.toString());
        System.out.println(myTree.toFullString());

        BinarySearchTree.BinarySearchTreeIterator iter = myTree.iterator();
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 1", 1, iter.next());
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 2", 2, iter.next());
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 3", 3, iter.next());
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 4", 4, iter.next());
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 5", 5, iter.next());
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 6", 6, iter.next());
        assertTrue("Has a next", iter.hasNext());
        assertEquals("Next Element is 7", 7, iter.next());
        assertTrue("Does not have a next", !iter.hasNext());

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
