import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Prints an octagon of user-specified size.
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class JonA01 {

    /**
     * Gets an integer from the user and prints an octagon with it.
     *
     * @param args
     */
    public static void main(String[] args) {
        int octagonSize = getInteger("Please enter a size as an integer (1 - 12):");
        printOctagon(octagonSize);
    }

    /**
     * Gets an integer from the user. Re-prompts if input is not an integer.
     *
     * @param userPrompt message the user is prompted with
     * @return integer to make octagon with
     */
    private static int getInteger(final String userPrompt) {
        final int MAXSIZE = 12;
        final int MINSIZE = 1;
        int size = 0;

        Scanner input = new Scanner(System.in);
        System.out.println(userPrompt);
        while (size < MINSIZE || size > MAXSIZE) {
            try {
                size = input.nextInt();
                if (size < MINSIZE || size > MAXSIZE) {
                    System.out.println("The size must be between 1 and 12, inclusive.  Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Sorry, but that is not an integer.  Try again.");
                input.next();
            }
        }
        return size;
    }

    /**
     * Octagon printing broken down into three sections: top, middle, bottom.
     *
     * @param size integer provided by the user
     */
    private static void printOctagon(final int size) {
        printTop(size);
        printMiddle(size);
        printBottom(size);
    }

    /**
     * Prints the middle of the octagon.
     *
     * @param size integer provided by the user
     */
    private static void printMiddle(final int size) {
        final int WIDTH = size * 3;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < WIDTH; col++) {
                System.out.print("#");
            }
            System.out.println("");
        }
    }

    /**
     * Prints the top of the octagon.
     *
     * @param size integer provided by the user
     */
    private static void printTop(int size) {
        for (int row = 0; row < size; row++) {
            // the whitespace
            int whiteSpaceCount = size - row;
            for (int i = 0; i < whiteSpaceCount; i++) {
                System.out.print(" ");
            }
            // the hashmarks
            int hashCount = size + (row * 2);
            for (int i = 0; i < hashCount; i++) {
                System.out.print("#");
            }
            System.out.println("");
        }
    }

    /**
     * Prints the bottom of the octagon.
     *
     * @param size integer provided by the user
     */
    private static void printBottom(final int size) {
        for (int row = 0; row < size; row++) {
            // the whitespace
            int whiteSpaceCount = size - Math.abs(row - (size - 1));
            for (int i = 0; i < whiteSpaceCount; i++) {
                System.out.print(" ");
            }
            // the hashmarks
            int hashCount = size + (Math.abs(row - (size - 1)) * 2);
            for (int i = 0; i < hashCount; i++) {
                System.out.print("#");
            }
            System.out.println("");
        }
    }
}