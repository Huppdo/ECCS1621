import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DieGameForm {

    //Form elements
    private static JFrame myFrame;
    private JButton rollDice;
    private JLabel die1Label;
    private JLabel die2Label;
    private JPanel panel1;
    private JButton endTurn;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel player1Score;
    private JLabel player2Score;
    private JLabel player1ScoreChange;
    private JLabel player2ScoreChange;
    private JLabel messageLabel;

    //Holds which player is currently playing
    private int whichPlayerTurn;

    //Player objects
    private Player player1 = new Player();
    private Player player2 = new Player();

    private final String playingColor = "#ff9900";
    private final String defaultColor = "#000000";

    //Constants
    public static final int NUMBER_OF_SIDES = 6;
    public static final int NUMBER_OF_DICE = 2;

    /**
     * Constructor to be added when the form is created
     */
    public DieGameForm() {

        //Gets the players names from dialog boxes
        while (player1.getName().isBlank()) {
            player1.setName(JOptionPane.showInputDialog("Enter Player 1's name: "));
        }
        while (player2.getName().isBlank()) {
            player2.setName(JOptionPane.showInputDialog("Enter Player 2's name: "));
        }

        //Sets the players name to the labels
        player1Label.setText(player1.getName());
        player2Label.setText(player2.getName());

        //Set the current turn to player 1 and show the appropriate labels
        whichPlayerTurn = 1;
        updateTurnLabels();

        //Establish an action listener for when the roll dice button is pressed
        rollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Decide which player to run code for
                if (whichPlayerTurn == 1) {
                    //Roll the dice
                    player1.rollDice(die1Label, die2Label);

                    //Update the turn score
                    player1ScoreChange.setText("+" + player1.getTurnScore());

                    //Checks to see if the player has lost either the game or turn
                    if (player1.isGameScoreLost()) {
                        messageLabel.setText("Player 1 has lost their game score");
                        rollDice.setEnabled(false);
                    } else if (player1.isTurnScoreLost()) {
                        messageLabel.setText("Player 1 has lost their turn");
                        rollDice.setEnabled(false);
                    }
                } else {
                    //Run player 2s turn

                    //Roll the dice
                    player2.rollDice(die1Label, die2Label);

                    //Update the turn score
                    player2ScoreChange.setText("+" + player2.getTurnScore());

                    //Checks to see if the player has lost either the game or turn
                    if (player2.isGameScoreLost()) {
                        messageLabel.setText("Player 2 has lost their game score");
                        rollDice.setEnabled(false);
                    } else if (player2.isTurnScoreLost()) {
                        messageLabel.setText("Player 2 has lost their turn");
                        rollDice.setEnabled(false);
                    }
                }
            }
        });

        //Establish an action listener for when the roll dice button is pressed
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Update the current players game score
                if (whichPlayerTurn == 1) {
                    player1.addTurnScoreToGameScore();
                } else {
                    player2.addTurnScoreToGameScore();
                }

                //Updates the game scores
                player1Score.setText(String.valueOf(player1.getGameScore()));
                player2Score.setText(String.valueOf(player2.getGameScore()));

                //Flips the current player's turn
                whichPlayerTurn = whichPlayerTurn == 1 ? 2 : 1;

                //Check to see if either player has one
                if (player1.hasWon()) {
                    endGame(player1.getName());
                } else if (player2.hasWon()) {
                    endGame(player2.getName());
                }

                //Updates the labels
                updateTurnLabels();

                //Ensures both buttons can be pressed
                endTurn.setEnabled(true);
                rollDice.setEnabled(true);
            }
        });
    }

    /**
     * Checks to see if the players would like to end the game
     * @param winner the name of the winner
     */
    private void endGame(String winner) {

        //Set the current player turn to 1
        whichPlayerTurn = 1;

        //Asks if the players would like to play again
        //0=yes, 1=no
        int resp = JOptionPane.showConfirmDialog(null, winner + " won the game. \nWould you like to play again?", "Restart Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resp == 0) {
            //Reset the players
            player1.reset();
            player2.reset();

            //Updates the game scores to 0
            player1Score.setText("0");
            player2Score.setText("0");
        } else {
            //Close the window
            myFrame.dispose();
        }

    }

    /**
     * Sets the colors and default text for the current players labels
     */
    private void updateTurnLabels() {
        //Hide the message
        messageLabel.setText("");

        //If the new turn is player 1
        if (whichPlayerTurn == 1) {

            //Set the colors of the labels
            player1Label.setForeground(Color.decode(playingColor));
            player1Score.setForeground(Color.decode(playingColor));
            player1ScoreChange.setForeground(Color.decode(playingColor));

            //Show the score change label and set the default text
            player1ScoreChange.setText("+0");
            player1ScoreChange.setVisible(true);

            //Reset all the labels for player 2
            player2Label.setForeground(Color.decode(defaultColor));
            player2Score.setForeground(Color.decode(defaultColor));
            player2ScoreChange.setForeground(Color.decode(defaultColor));
            player2ScoreChange.setText("");
            player2ScoreChange.setVisible(false);

        } else {
            //This code is to setup player 2

            //Set the colors of the labels
            player2Label.setForeground(Color.decode(playingColor));
            player2Score.setForeground(Color.decode(playingColor));
            player2ScoreChange.setForeground(Color.decode(playingColor));

            //Show the score change label and set the default text
            player2ScoreChange.setText("+0");
            player2ScoreChange.setVisible(true);

            //Reset all the labels for player 1
            player1Label.setForeground(Color.decode(defaultColor));
            player1Score.setForeground(Color.decode(defaultColor));
            player1ScoreChange.setForeground(Color.decode(defaultColor));
            player1ScoreChange.setText("");
            player1ScoreChange.setVisible(false);
        }
    }

    /**
     * Main entry point to the game form
     *
     * @param args command line arguments [none]
     */
    public static void main(String[] args) {
        myFrame = new JFrame("Dice Game!"); //Makes the frame
        myFrame.setContentPane(new DieGameForm().panel1); //attaches the panel to the frame

        //sets up what happens with the frame is closes
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.setPreferredSize(new Dimension(500, 300)); //Set desired dimensions
        myFrame.setResizable(false); //Prevent window resizing

        myFrame.pack(); //put everything in the frame
        myFrame.setVisible(true); //allows us to see the frame

    }
}
