
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;


/**
 * <p>Tic Tac Toe JonA07, for use as a web-accessible applet!</p>
 * <p>Available online at: http://www2.hawaii.edu/~jwaltz/</p>
 * <p>List of extra credit features:</p>
 * <ul>
 *     <li>Nicer-looking buttons/widgets (maybe using images or icons)</li>
 *     <li>A JFrame window starts off at an decent size and centered on the screen.</li>
 *     <li>Keep a running score or a count of games won/lost</li>
 *     <li>Hosted at a freakin' beautiful website</li>
 *     <li>TicTacToe: Optimal AI strategy. +15</li>
 * </ul>
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class JonA07 extends JApplet implements ActionListener{
    
    //Tic Tac Toe JonA07 components
    private JButton[] buttons;
    private TicTacToe newGame = new TicTacToe();
    private JButton reset;
    private JPanel outerPanel;
    private int winCount = 0;
    private int lossCount = 0;
    private int tieCount = 0;
    private JLabel winLabel;
    private JLabel lossLabel;
    private JLabel tieLabel;

    /**
     * Constructor that initializes and puts together the various JonA07 components. The end result
     * is Tic-Tac-Toe, in a ready-to-play JonA07.
     */
    public JonA07() {
        //PANEL FOR BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        
        //9 BUTTONS
        buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = new JButton("");
            btn.setPreferredSize(new Dimension(150,150));
            btn.setIcon(new ImageIcon(this.getClass().getResource(i + ".jpg")));
            btn.setDisabledIcon(new ImageIcon(this.getClass().getResource(i + ".jpg")));
            buttons[i] = btn;
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        //RESET BUTTON
        this.reset = new JButton("Reset");
        this.reset.addActionListener(this);
        this.reset.setEnabled(false);

        //WIN LOSS TIE LABELS
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        this.winLabel = new JLabel("Wins: " + winCount);
        this.lossLabel = new JLabel("Losses: " + lossCount);
        this.tieLabel = new JLabel("Ties: " + tieCount);
        bottomPanel.add(winLabel);
        bottomPanel.add(lossLabel);
        bottomPanel.add(tieLabel);
        bottomPanel.add(reset);

        //OUTER PANEL FOR GRID, RESET, AND LABELS
        this.outerPanel = new JPanel();
        this.outerPanel.setLayout(new BorderLayout());
        this.outerPanel.add(buttonPanel, BorderLayout.CENTER);
        this.outerPanel.add(bottomPanel, BorderLayout.PAGE_END);
    }

    /**
     * Runs when applet loads.
     */
    public void init() {
        JonA07 myGUI = new JonA07();
        this.setContentPane(myGUI.outerPanel);
    }

    /**
     * Puts the Tic-Tac-Toe JonA07 into a JFrame.
     * @param args Command-line arguments. Note: This JonA07 does not use command-line arguments.
     */
    public static void main(String[] args) {
        initGUI();
    }

    /**
     * All of the Swing boilerplate for JFrame goes here.
     */
    private static void initGUI() {
        JonA07 myGUI = new JonA07();
        JFrame window = new JFrame("TIC TAC TOE");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(myGUI.outerPanel);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.reset) {
            resetGame();
        }
        else if (ae.getSource() instanceof JButton) {
            playerMove(ae);
            if (newGame.checkForWin(3)) {
                gameOver();
                this.winCount++;
                this.winLabel.setText("Wins: " + this.winCount);
            } else if (newGame.isFilled()) {
                this.reset.setEnabled(true);
                this.tieCount++;
                this.tieLabel.setText("Ties: " + this.tieCount);
            } else {
                compTurn();
                if (newGame.checkForWin(-3)) {
                    gameOver();
                    this.lossCount++;
                    this.lossLabel.setText("Losses: " + lossCount);
                } else if (newGame.isFilled()) {
                    this.reset.setEnabled(true);
                    this.tieCount++;
                    this.tieLabel.setText("Ties: " + this.tieCount);
                }
            }
        }
    }

    /**
     * Performs a move for the computer and updates the JonA07.
     */
    private void compTurn() {
        int[] compMoveXY = this.newGame.compMove();
        int compButton = compMoveXY[0] * 3 + compMoveXY[1];
        updateButton(this.buttons[compButton], compButton, 'O');
    }

    /**
     * Performs an update of the JonA07 after the player has made a move.
     * @param ae ActionEvent from ActionListener.
     */
    private void playerMove(ActionEvent ae) {
        JButton clicked = (JButton) ae.getSource();
        //get number of button just clicked
        int buttonNum = 0;
        for (int i = 0; i < 9; i++) {
            if (clicked == buttons[i]) {
                buttonNum = i;
                //update internal representation
                newGame.setGrid(i / 3, i % 3, 1);
            }
        }
        //update icon, disable button
        updateButton(clicked, buttonNum, 'X');
    }

    /**
     * Updates the button with a new picture containing an X or an O.
     * @param clicked The button clicked.
     * @param buttonNum The number of the clicked button.
     * @param mark The mark that goes onto the button, an X or an O.
     */
    private void updateButton(JButton clicked, int buttonNum, char mark) {
        clicked.setIcon(new ImageIcon(this.getClass().getResource(buttonNum + "_" + mark + ".jpg")));
        clicked.setDisabledIcon(new ImageIcon(this.getClass().getResource(buttonNum + "_" + mark + ".jpg")));
        clicked.setEnabled(false);
    }

    /**
     * Called when the game is over due to a winning condition. Disables all buttons and
     * enables the use of the reset button.
     */
    private void gameOver() {
        for (JButton btn : buttons) {
            btn.setEnabled(false);
            this.reset.setEnabled(true);
        }
    }

    /**
     * Returns the game JonA07 to it's initial state.
     */
    private void resetGame() {
        this.newGame.resetGrid();
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setIcon(new ImageIcon(this.getClass().getResource(i + ".jpg")));
            buttons[i].setDisabledIcon(new ImageIcon(this.getClass().getResource(i + ".jpg")));
        }
        this.reset.setEnabled(false);
    }
}
