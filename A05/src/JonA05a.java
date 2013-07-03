
import java.util.Scanner;

/**
* Asks the user to enter strings and saves them only if they are "smaller" 
* then the previous string. Ends when the user enters nothing.
* 
* @author Jonathan Waltz
* @version 1.0
*/
public class JonA05a {
   /**
    * Collects strings from user, outputs the strings and their lengths.
    * 
    * @param args 
    */
   public static void main(String[] args) {
       PyramidStack<String> stringStack = new PyramidStack<String>();
       PyramidStack<Integer> intStack = new PyramidStack<Integer>();
       
       getUserInput(stringStack, intStack);
       System.out.println(stringStack);
       System.out.println(stringStack.getLengths());
   }
   /**
    * Collects strings from the user.
    * 
    * @param stringStack
    * @throws IllegalArgumentException 
    */
   public static void getUserInput(PyramidStack<String> stringStack, PyramidStack<Integer> lengths) 
           throws IllegalArgumentException {
       Scanner input = new Scanner(System.in);
       String userString;
       
       System.out.println("This program will save some of the "
               + "strings you enter.");
       System.out.println("Can you predict which ones will be saved?  "
               + "(Enter nothing to quit.)");

       do {
           System.out.println("Enter a string:");
           userString = input.nextLine();
           if (userString.equals("")) {
               break;
           }
           if (stringStack.countBefore(userString) == 0 &&
                   lengths.countBefore(userString.length()) == 0) {
               System.out.println("String saved.");
               stringStack.push(userString);
               lengths.push(userString.length());
           } else {
               System.out.println("String NOT saved: Already saved " + 
                       Math.max(lengths.countBefore(userString.length()), 
                                stringStack.countBefore(userString))
                       + " strings that should come before this one.");
           }
       } while (!userString.equals(""));

   }
}
