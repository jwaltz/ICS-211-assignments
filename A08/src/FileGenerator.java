import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Generates a file with a given number of random integers, one per line.
 * 
 * @author Zach Tomaszewski
 */
public class FileGenerator {

  /**
   * Specify a number as a command line argument.  This program will then
   * output a text file named "###.txt", where ### is the given number.
   * The output file will contain the given number of random integers.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Error: No command line argument given.");
      System.out.println();
      System.out.println("Usage: java FileGenerator ###");
      System.out.println("where ### is a number.");
      return; //quit
    }

    //convert arg
    int lines;
    try {
      lines = Integer.parseInt(args[0]);
    }catch (NumberFormatException e) {
      System.out.println("Error: Command line argument not an integer.");
      return; //quit
    }
    
    //write lines to file
    String output = lines + ".txt";
    try {
      PrintWriter fileout = new PrintWriter(new java.io.File(output));
      java.util.Random rand = new java.util.Random();
      for (int i = 0; i < lines; i++) {
        fileout.println(rand.nextInt());
      }
      fileout.close();
    }catch (FileNotFoundException e) {
      System.out.println("Error: Could not write to output file: " + 
          e.getMessage());
    }
  }
}
