import java.util.ArrayList;

/**
 * This class tests for a few common object design mistakes for A04.
 * Prints tests and results to the screen.  Requires these room files
 * to be in the same directory:
 * <ul>
 * <li>cul-de-sac.txt
 * <li>chasm.txt
 * </ul>
 */
 public class A04Tester {

     public static void main(String[] args) throws java.io.IOException {
        //Dr.Zach's tests
        //test that repeated calls to not append to some instance variable list
        String mapFile = "cul-de-sac.txt";
        JonA04 room = new JonA04(mapFile);
        System.out.println(room);
        //did not call toString() first
        ArrayList<Direction> path = room.getPathToExit();
        System.out.println(room);
        System.out.println("Path for " + mapFile + ": " + path);
        ArrayList<Direction> repeated = room.getPathToExit();
        System.out.println("Repeated call: " + repeated);
        System.out.print("Same path reaturned each time: ");
        System.out.println((path.equals(repeated)) ? "PASS" : "FAIL");

        //test that you are not using static variables
        JonA04 chasm = new JonA04("chasm.txt");  //different instance
        System.out.println("Path for " + mapFile + ": " + room.getPathToExit());
        System.out.println("Path for chasm.txt:" + chasm.getPathToExit());
        System.out.print("Two paths are different: ");
        System.out.println((chasm.getPathToExit().equals(room.getPathToExit())) ?
                       "FAIL" : "PASS");
   }
 }