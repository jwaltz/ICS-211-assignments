
/**
 * Assignment 03 Recursive Version: Methods should have no loops and call
 * themselves.
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class JonA03r {
    /**
     * Runs the unit tests for all recursive methods.
     * @param args command line arguments, not necessary for this program.
     */
    public static void main(String[] args) {
        testRow();
        testTriangle();
        testInterpose();
        testSquares();
        testGCD();
    }

    /**
     * Returns a String containing the given number of * characters. <p> If
     * width is 0 or more, the returned String will end with a newline ('\n')
     * character. If width is negative, returns the empty string "".
     *
     * @param width How many *s should be in the generated row
     * @return A String containing width *s and a \n
     */
    public static String row(int width) {
        if (width == 0) {
            return "\n";
        } else if (width < 0) {
            return "";
        } else {
            return "*" + row(width - 1);
        }
    }
    /**
     * Unit tests for row method.
     */
    private static void testRow() {
        System.out.println(row(3).equals("***\n"));
        System.out.println(row(5).equals("*****\n"));
        System.out.println(row(0).equals("\n"));
        System.out.println(row(-2).equals(""));
    }

    /**
     * Returns a String of *s that form a right triangle of the given size. Both
     * the width and height will be equal to the given size. The right angle of
     * the printed triangle will be in the lower-left. <p> If size is 0 or less,
     * returns the empty String "".
     *
     * @param size The width and height of a right triangle of *s
     * @return A String that contains the complete triangle, including necessary
     * line breaks ('\n')
     */
    public static String triangle(int size) {
        if (size <= 0) {
            return "";
        } else if (size == 1) {
            return "*\n";
        } else {
            return triangle(size - 1) + row(size);
        }
    }
    /**
     * Unit tests for triangle method.
     */
    private static void testTriangle() {
        System.out.println(triangle(6).equals("*\n**\n***\n****\n*****\n******\n"));
        System.out.println(triangle(3).equals("*\n**\n***\n"));
        System.out.println(triangle(1).equals("*\n"));
        System.out.println(triangle(0).equals(""));
        System.out.println(triangle(-2).equals(""));
    }

    /**
     * Returns a String containing the given String fragment repeated the given
     * number of times with a decreasing count placed between the fragments.
     * That is, the format of the returned string is frag#frag#frag here # is
     * the sequences of decreasing integers from (times-1) to 1. (See examples
     * for more.) <p> If times is 0 or less, returns an empty string "".
     *
     * @param str The string to repeat
     * @param times How many times to repeat it
     * @return A String containing str x times with a decreasing count between
     * the repeated strs.
     */
    public static String interpose(String str, int times) {
        if (times < 0) {
            return "";
        }
        if (times == 1) {
            return str;
        } else {
            return str + (times - 1) + interpose(str, times - 1);
        }
    }
    /**
     * Unit tests for Interpose method.
     */
    private static void testInterpose() {
        System.out.println(interpose("ho", 3).equals("ho2ho1ho"));
        System.out.println(interpose("yes", 5).equals("yes4yes3yes2yes1yes"));
        System.out.println(interpose("", 5).equals("4321"));
        System.out.println(interpose("x", 1).equals("x"));
        System.out.println(interpose("raise", -1).equals(""));
    }

    /**
     * Sets the cells within a section of the given array equal to i*i, where i
     * is the index of the containing cell. That is, starting at i = startIndex,
     * sets array[i] equal to i*i, and then repeats this for all higher i up to
     * the end of the array. <p> Changes the given array, but also returns a
     * reference to it. If start index is outside the bounds of the array, this
     * method does nothing, returning the array unchanged.
     *
     * @param array The array of ints to change
     * @param startIndex The index within array at which to start writing
     * squares
     * @return The same array given as a parameter
     */
    public static int[] squares(int[] array, int startIndex) {
        if (startIndex >= array.length || startIndex < 0) {
            return array;
        } else {
            array[startIndex] = startIndex * startIndex;
            return squares(array, startIndex + 1);
        }
    }
    /**
     * Unit tests for squares method.
     */
    private static void testSquares() {
        System.out.println(java.util.Arrays.equals(squares(new int[5], 2), new int[]{0, 0, 4, 9, 16}));
        int[] nums = {2, 4, 6, 8, 10};
        System.out.println(java.util.Arrays.equals(squares(nums, 6), nums));
        System.out.println(java.util.Arrays.equals(squares(nums, 3), new int[]{2, 4, 6, 9, 16}));
    }

    /**
     * Uses the Euclidean algorithm to compute the greatest common divisor of
     * the given two integers. In other words, assuming |a| >= |b|:
     * <pre>
     * gcd(a, 0) == a, or
     * gcd(a, b) == gcd(b, a - (b * (a/b)))
     * </pre> <p> When calling this method, the parameter a does not need to be
     * >= b because this method will appropriately reorder the parameters'
     * values if necessary. The returned value will always be >= 0.
     *
     * @param a One of the two values to find the GCD of
     * @param b The other of two values to find the GCD of
     * @return The positive GCD of a and b
     */
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
    /**
     * Unit tests for gcd method.
     */
    private static void testGCD() {
        System.out.println(gcd(100, 0) == 100);
        System.out.println(gcd(100, 20) == 20);
        System.out.println(gcd(48, 32) == 16);
        System.out.println(gcd(32, 48) == 16);
        System.out.println(gcd(0, 32) == 32);
        System.out.println(gcd(9, 17) == 1);
        System.out.println(gcd(18, -6) == 6);
        System.out.println(gcd(-6, -18) == 6);
    }
}