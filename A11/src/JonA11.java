import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>Prompts the user for a word and finds all the anagrams of that word repeatedly
 * until nothing is entered. Must specify a file to act as a dictionary or database
 * of words.</p><br>
 *
 * <p>Usage: java JonA11 dictionary.txt</p>
 *
 * @author Jonathan Waltz
 */
public class JonA11 {
    /**
     * <p>Finds anagrams of user input within the specified command-line argument file.
     * Continues to prompt the user for words until nothing is entered.</p>
     *
     * @param args dictionary file
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Anagram Finder!");
            System.out.println("You did not enter a filename as a command line argument");
            System.out.println("Example: java JonA10 dictionary.txt");
            System.exit(1);
        }
        File dict = new File(args[0]);
        if (!dict.isFile()) {
            System.out.println("Anagram Finder!");
            System.out.println("Filename specified must be a file");
            System.out.println("Example: java JonA10 dictionary.txt");
            System.exit(1);
        }
        Map<String, Set<String>> anagrams = findAnagrams(dict);
        prompt(anagrams);
    }

    /**
     * <p>Prompts the user for words until nothing is entered. Looks up anagrams
     * of words and prints them out. Attempts to accomplish this as expediently
     * as possible through use of HashMap.</p>
     *
     * @param anagrams populated map of all anagrams
     */
    private static void prompt(Map<String, Set<String>> anagrams) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a word (or nothing to quit):");
        do {
            String word = input.nextLine().toLowerCase();
            if (word.equals("")) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.println("Anagrams of '" + word + "':");
            Set<String> words = anagrams.get(sortString(word));
            //print anagrams or none found message
            System.out.println(words == null ? "No anagrams found for '" + word + "'" : words);
        } while (true);
    }

    /**
     * <p>Populates a HashMap using the given file.</p><br>
     *
     * <p>Key: A sorted version of a given string.
     * i.e. The sorted version of Tommy is mmoty, sorted version of edcba is abcde</p>
     *
     * <p>Value: A list of anagrams containing the key's letters</p>
     * <p>i.e. Key 'aemt' is associated with values [mate, meat, meta, tame, team]</p>
     *
     * @param dict command-line argument used as dictionary or database of words
     * @return
     */
    private static Map<String, Set<String>> findAnagrams(File dict) {
        Map<String, Set<String>> anagrams = new HashMap<String, Set<String>>();
        try {
            Scanner read = new Scanner(dict);
            while (read.hasNext()) {
                String word = read.next().toLowerCase();
                String key = sortString(word);
                if (anagrams.containsKey(key)) {
                    anagrams.get(key).add(word);
                } else {
                    Set<String> set = new TreeSet<String>();    //TreeSet enforces order and uniqueness
                    set.add(word);
                    anagrams.put(key, set);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return anagrams;
    }

    /**
     * <p>Sorts the given string to it's natural ordering.</p>
     * <br>Examples:
     * <li>
     *     <ul>Tommy -> mmoty</ul>
     *     <ul>Jonathan -> aahjnnot</ul>
     * </li>
     *
     * @param str word to be sorted
     * @return sorted version of the word
     */
    private static String sortString(String str) {
        char[] sorted = str.toCharArray();
        Arrays.sort(sorted);
        return new String(sorted);
    }
}
