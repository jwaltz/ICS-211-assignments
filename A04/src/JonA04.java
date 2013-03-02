
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Uses recursive backtracking to find the path out of a maze. New concepts:
 * recursion, backtracking, OOP, enums.
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class JonA04 {

    /**
     * Internal representation of the map contained in text file.
     */
    private char[][] map;
    /**
     * Dimensions of the map and location of the starting coordinate.
     */
    private int mapWidth, mapHeight, startRow, startCol;
    /**
     * Lines from the text file, for use with resetting char map on subsequent
     * calls to getPathToExit().
     */
    private List<String> mapLines;
    
    /**
     * Constructor reads in a plaintext map, checks for it's validity, and
     * initializes internal representation of the map.<p> Map must have only one
     * 'S' start position.<p> Map can have zero, one, or more exits.<p> Path
     * cannot go outside the dimensions of the map.<p> Empty characters, ' ',
     * are the only traversable area. Other characters should be treated as
     * obstacles.
     *
     * @param filename name of the plaintext map file.
     * @throws IOException
     *
     */
    public JonA04(final String filename) throws IOException {
        mapLines = readFileIn(filename);

        if (isValidMap(mapLines)) {
            map = populateCharMap(mapLines);
        } else {
            throw new IOException("Map is invalid.");
        }

    }

    /**
     * Validity of a plaintext map file is based on if all of the lines are of
     * the same length and there is only one 'S' start position. If either of
     * these conditions are not met, the map is invalid.
     *
     * @param lines Lines from the plaintext map file.
     * @return
     */
    public boolean isValidMap(final List<String> lines) {
        boolean valid = true;
        int sCount = 0;
        final int LINE_WIDTH = lines.get(0).length();
        
        //check width of lines against the first
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).length() != LINE_WIDTH) {
                valid = false;
            }
            //check each character for start position
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'S') {
                    sCount++;
                    startRow = i;
                    startCol = j;
                }
            }
        }
        if (sCount != 1) {
            valid = false;
        }
        return valid;
    }

    /**
     * Reads in a plaintext map file, storing it as a list of strings.
     *
     * @param filename the plaintext map file.
     * @return List of Strings from the plaintext map file.
     * @throws IOException
     */
    private List<String> readFileIn(final String filename) throws FileNotFoundException {
        final Scanner scan = new Scanner(new File(filename));
        List<String> lines = new ArrayList<String>();

        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }

        return lines;
    }

    /**
     * Initializes the internal representation of the map.
     *
     * @param lines Lines of the plaintext map file.
     * @return A 2-dimensional char array of the map.
     */
    private char[][] populateCharMap(final List<String> lines) {
        final int ROWS = lines.size();
        final int COLUMNS = lines.get(0).length();
        char[][] mapRepresentation = new char[ROWS][COLUMNS];
        
        this.mapWidth = COLUMNS;
        this.mapHeight = ROWS;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                //normalize all obstacles to specified character
                if (isObstacle(lines.get(i).charAt(j))) {
                    mapRepresentation[i][j] = '\u25FE';
                } else {
                    mapRepresentation[i][j] = lines.get(i).charAt(j);
                }
            }
        }
        return mapRepresentation;
    }

    /**
     * Recursively finds the way out of a maze using backtracking.
     *
     * @param row Row of coordinate.
     * @param col Column of coordinate.
     * @return A list of directions out of the maze, if a path out exists.
     */
    public ArrayList<Direction> getPathToExit(final int row, final int col) {
        ArrayList<Direction> pathToExit = new ArrayList<Direction>();

        if (isOutOfBounds(row, col)) {
            return new ArrayList<Direction>();
        } else if (isObstacle(row, col)) {
            return new ArrayList<Direction>();
        } else if (isTheExit(row, col)) {
            ArrayList<Direction> foundExit = new ArrayList<Direction>();
            foundExit.add(Direction.HERE);
            return foundExit;
        } else {
            //mark potential path with open square char
            mark(row, col, '\u25FD');
                
            Direction[] dir = Direction.values();
            for (int i = 0; i < dir.length - 1; i++) {
                pathToExit = getPathToExit(row + dir[i].getRowModifier(),
                        col + dir[i].getColModifier());

                if (!pathToExit.isEmpty()) {
                    pathToExit.add(dir[i]);
                    return pathToExit;
                }
            }
        }
        //mark deadends with obstacle char
        mark(row, col, '\u25FE');

        return new ArrayList<Direction>();
    }

    /**
     * Checks if coordinate is out of the bounds of the map.
     *
     * @param row Row of coordinate.
     * @param col Column of coordinate.
     * @return
     */
    public boolean isOutOfBounds(final int row, final int col) {
        if (row < 0 || col < 0) {
            return true;
        } else if (row >= this.mapHeight || col >= this.mapWidth) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the coordinate is an obstacle.
     *
     * @param row Row of coordinate.
     * @param col Column of coordinate.
     * @return
     */
    public boolean isObstacle(final int row, final int col) {
        return (map[row][col] != ' ' &&
                map[row][col] != 'E' &&
                map[row][col] != 'S');
    }
    
    /**
     * Checks if the given char is considered a map obstacle. Used for
     * initializing internal map representation.
     * 
     * @param mark the character to check
     * @return
     */
    public boolean isObstacle(final char mark) {
        return (mark != ' ' && mark != 'S' && mark != 'E');
    }

    /**
     * Checks if the coordinate is the exit.
     *
     * @param row Row of coordinate.
     * @param col Column of coordinate.
     * @return
     */
    public boolean isTheExit(final int row, final int col) {
        return (map[row][col] == 'E');
    }

    /**
     * Marks the coordinate as part of a potential path to the exit.
     *
     * @param row Row of coordinate.
     * @param col Column of coordinate.
     * @param marker Character used to mark the potential path.
     * @throws IllegalArgumentException 
     */
    public void mark(final int row, final int col, final char marker) 
            throws IllegalArgumentException {
        if (marker == 'E' || marker == 'S' || marker == ' ') {
            throw new IllegalArgumentException("Invalid marker character.");
        }
        map[row][col] = marker;
    }

    /**
     * Returns a list of directions that, processed in order, specify a path
     * from the 'S' starting point to an exit. Upon reaching the exit square,
     * the path must end in a HERE direction. Any valid path that reaches an
     * exit is acceptable; it does not need to be the shortest path possible. If
     * there is no exit or if the exit is unreachable from the starting
     * location, the returned list should be empty.
     *
     * @return
     */
    public ArrayList<Direction> getPathToExit() {
        map = populateCharMap(mapLines);
        ArrayList<Direction> path = getPathToExit(this.startRow, this.startCol);
        map[startRow][startCol] = 'S';
        Collections.reverse(path);
        return path;
    }

    /**
     * Returns a multi-line string representation of the current map.
     *
     * @return
     */
    @Override
    public String toString() {
        String mapString = "";
        for (int i = 0; i < map.length; i++) {
            for (char c : map[i]) {
                mapString += c;
            }
            mapString += "\n";
        }
        return mapString;
    }
}