
import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * Simulates the natural living environment of a burrow of meeps.
 * 
 * @author Jonathan Waltz
 * @date Feb. 28 2013
 * @version 1.0
 ******************************************************************************/
public class MeepBurrow {
    /**
     * The three column burrow where the meeps reside.
     */
    private List<PyramidStack<Meep>> burrow = new ArrayList<PyramidStack<Meep>>();
    /**
     * Constants for readably referring to the meep burrow shafts.
     */
    private final int VESTIBULE = 0,
                      ATRIUM = 1,
                      TABLINUM = 2;
    /**
     * Constructs a new MeepBurrow with three shafts.
     */
    public MeepBurrow() {
        for (int i = 0; i < 3; i++) {
            burrow.add(new PyramidStack<Meep>());
        }
    }
    /**
     * String representation of the burrow's current state.
     * @return 
     */
    @Override
    public String toString() {
        return "V: " + this.getVestibule().toString() + "\n"
                + "A: " + this.getAtrium().toString() + "\n"
                + "T: " + this.getTablinum().toString() + "\n"
                + "--------------------------------------";
    }
    /**
     * Adds a meep to the burrow, sorting it according to natural meep rules.
     * 
     * @param newcomer The meep being added to the burrow.
     * @param print    Determines if the method executes verbosely or silently.
     */
    public void add(Meep newcomer, boolean print) {
        getVestibule().push(newcomer);
        if (print) {
            System.out.println(this);
        }
        //Okay to push directly to TABLINUM if it's empty or the meep is smallest
        if (this.isEmptyShaft(TABLINUM) || isOkayToPush(TABLINUM, newcomer)) {
            if (print) {
                phase1Msg();
                phase2Msg();
            }
            move(VESTIBULE, TABLINUM, print);
        } else {
            if (print) {
                phase1Msg();
            }
            //Towers of Hanoi movement from TABLINUM onto VESTIBULE
            flurry(getTablinum().countBefore(newcomer), TABLINUM, VESTIBULE, ATRIUM, print);
            if (print) {
                phase2Msg();
            }
            //Towers of Hanoi movement back to TABLINUM from VESTIBULE
            flurry(getVestibule().countBefore(newcomer) + 1, VESTIBULE, TABLINUM, ATRIUM, print);
        }
    }
    /**
     * Moves meeps around based on the well-known Towers of Hanoi algorithm.
     * 
     * @param count     Meep number.
     * @param source    Shaft meep is coming from.
     * @param dest      Shaft meep is traveling to.
     * @param spare     Extra shaft.
     * @param print     Determines if the method executes verbosely or silently.
     */
    private void flurry(int count, int source, int dest, int spare, boolean print) {
        //Base case: smallest meep
        if (count == 1) {
            this.move(source, dest, print);
        } else {
            //Move all smaller meeps to spare shaft
            flurry(count - 1, source, spare, dest, print);
            //Move new meep into place
            this.move(source, dest, print);
            //Move all smaller meeps back
            flurry(count - 1, spare, dest, source, print);
        }
    }
    /**
     * Moves a meep from the top of source to the top of destination.
     * 
     * @param source    Shaft meep is coming from.
     * @param dest      Shaft meep is traveling to.
     * @param print     Determines if the method executes verbosely or silently.
     * @throws IllegalArgumentException
     */
    private void move(int source, int dest, boolean print) throws IllegalArgumentException {
        this.burrow.get(dest).push(this.burrow.get(source).pop());
        if (print) {
            fromToMsg(source, dest);
            System.out.println(this);
        }
    }
    /**
     * Checks the size of the given burrow shaft
     * @param dest      Shaft being checked.
     * @return 
     */
    private boolean isEmptyShaft(int dest) {
        return this.burrow.get(dest).size() == 0;
    }
    /**
     * Checks if meep can be safely moved to a specified shaft.
     * 
     * @param shaft     The specified shaft to check.
     * @param meep      The meep to be moved.
     * @return 
     */
    private boolean isOkayToPush(int shaft, Meep meep) {
        return this.burrow.get(shaft).countBefore(meep) == 0;
    }
    /**
     * Retrieves the Vestibule stack.
     * @return 
     */
    private PyramidStack<Meep> getVestibule() {
        return this.burrow.get(this.VESTIBULE);
    }
    /**
     * Retrieves the Atrium stack.
     * @return 
     */
    private PyramidStack<Meep> getAtrium() {
        return this.burrow.get(this.ATRIUM);
    }
    /**
     * Retrieves the Tablinum stack.
     * @return 
     */
    private PyramidStack<Meep> getTablinum() {
        return this.burrow.get(this.TABLINUM);
    }
    /**
     * Prints the first phase message of meep flurrying.
     */
    private void phase1Msg() {
        System.out.println("PHASE 1: Moving meeps smaller than newcomer to V.");
    }
    /**
     * Prints the second phase message of meep flurring.
     */
    private void phase2Msg() {
        System.out.println("PHASE 2: Move entire stack from V to T.");
    }
    /**
     * Prints where a meep is coming from and where it is going to.
     * 
     * @param source    Shaft meep is coming from.
     * @param dest      Shaft meep is traveling to.
     */
    private void fromToMsg(int source, int dest) {
        System.out.println("Moved from: " + getShaftName(source) + " to " + getShaftName(dest));
    }
    /**
     * Converts the integer constants of the burrow shafts to strings.
     * 
     * @param shaft     Shaft constant.
     * @return 
     */
    private String getShaftName(int shaft) {
        String shaftName = "";
        switch (shaft) {
            case VESTIBULE:
                shaftName = "VESTIBULE";
                break;
            case ATRIUM:
                shaftName = "ATRIUM";
                break;
            case TABLINUM:
                shaftName = "TABLINUM";
                break;
        }
        return shaftName;
    }
}
