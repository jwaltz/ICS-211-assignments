
/*******************************************************************************
 * Drops some randomly-sized meeps into a burrow and sorts them using
 * meep rules.
 * 
 * @author Jonathan Waltz
 * @date Feb. 28 2013
 * @version 1.0
 ******************************************************************************/
public class JonA05b {

    public static void main(String[] args) {
        System.out.println("Dropping some meeps into a burrow:");

        //randomly order some meeps with none the same size
        Integer[] sizes = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        //convert to list in order to use API's shuffle method
        java.util.List<Integer> meeps = java.util.Arrays.asList(sizes);
        java.util.Collections.shuffle(meeps);

        //drop meeps into the burrow
        MeepBurrow hole = new MeepBurrow();
        for (int size : meeps) {
            Meep meep = new Meep(size);
            System.out.println("===ADDING: " + meep + "===");
            hole.add(meep, false);
        }

        //show the final burrow state
        System.out.println("FINAL burrow state:\n" + hole);
    }
}
