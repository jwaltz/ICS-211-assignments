
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Counts the letters in a given plaintext file.
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class JonA02 {

    /**
     * Prints individual and total letter count of given file.
     *
     * @param args the filename (i.e. myfile.txt)
     */
    public static void main(final String[] args) {
        // Quits if no file is provided
        if (args.length == 0) {
            System.err.println("This program counts the letters in a given file.");
            System.err.println("You must specify the filename as a command line argument.");
            System.exit(1);
        }

        String fileName = args[0];
        try {
            System.out.println("Count of each letter found in " + args[0] + "\n");
            int totalLetterCount = printLetterCount(countLetters(importFile(fileName)));
            System.out.println("\nTotal letters found: " + totalLetterCount);
        } catch (IOException e) {
            System.err.println("Could not open file: " + e.getMessage());
        }


    }

    /**
     * Makes a file object from the string.
     *
     * @param filename
     * @return file object
     */
    private static File importFile(final String filename) {
        File newFile = new File(filename);
        return newFile;
    }

    /**
     * Creates a list composed of the lines of the file.
     *
     * @param myFile
     * @return list of lines
     * @throws FileNotFoundException
     */
    private static List<String> readLinesFromFile(final File myFile) throws FileNotFoundException {
        Scanner scan = new Scanner(myFile);
        List<String> lines = new ArrayList<String>();
        while (scan.hasNext()) {
            lines.add(scan.nextLine().toLowerCase().replace(" ", ""));
        }
        return lines;
    }

    /**
     * Counts how many times each letter is used.
     *
     * @param file
     * @return array of individual letter counts
     * @throws FileNotFoundException
     */
    private static int[] countLetters(final File file) throws FileNotFoundException {
        int[] lettersArray = new int[26];
        for (String line : readLinesFromFile(file)) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= 'a' && line.charAt(i) <= 'z') {
                    lettersArray[line.charAt(i) - 'a'] += 1;
                }
            }
        }
        return lettersArray;
    }

    /**
     * Prints how many times each letter is used and the total letter count.
     *
     * @param lettersArray
     * @return total letter count
     */
    private static int printLetterCount(final int[] lettersArray) {
        int letterCount = 0;
        for (int j = 0; j < lettersArray.length; j++) {
            if (lettersArray[j] > 0) {
                System.out.println((char) (j + 'a') + ": " + lettersArray[j]);
                letterCount += lettersArray[j];
            }
        }
        return letterCount;
    }
}