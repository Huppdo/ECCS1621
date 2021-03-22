import javax.swing.*;

public class Player {

    private boolean turnScoreLost;
    private boolean gameScoreLost;
    private int gameScore;
    private int turnScore;

    private String name;

    private static final java.util.Random rng = new java.util.Random();

    //Array to store images
    private static ImageIcon[] dieImage = new ImageIcon[DieGameForm.NUMBER_OF_SIDES + 1];

    /**
     * Constructor for the player class. Initializes all player variables to false / minimum value, loads dice values into a
     * static variable to be shared across all Player objects.
     */
    public Player() {
        //Sets all object variables to false
        turnScoreLost = false;
        gameScoreLost = false;
        gameScore = 0;
        turnScore = 0;
        name = "";

        //Load the dice images into the program
        for (int i = 1; i <= DieGameForm.NUMBER_OF_SIDES; i++) {
            //Construct the file name and path of each gif
            String fileName = "/images/die" + i + ".gif";

            //Add the die to the array
            dieImage[i] = new ImageIcon(this.getClass().getResource(fileName));
        }
    }

    /**
     * Generates two random numbers between 1 and 6 to simulate the dice rolling. The dice on the screen
     * are updated to appropriately reflect these numbers
     * @param die1 the label representing the first die
     * @param die2 the label representing the second die
     * @return an integer representation of the two dice sums
     */
    public int rollDice(JLabel die1, JLabel die2 ) {

        //Gets random values 1-6 for the two dice
        int die1Value = rng.nextInt(DieGameForm.NUMBER_OF_SIDES)+1;
        int die2Value = rng.nextInt(DieGameForm.NUMBER_OF_SIDES)+1;

        //Checks to see if turn or game score was lost
        if (die1Value == 1 && die2Value == 1) {
            turnScoreLost = true;
            gameScoreLost = true;

            gameScore = 0;
            turnScore = 0;
        } else if (die1Value == 1 || die2Value == 1) {
            turnScoreLost = true;

            turnScore = 0;
        } else {
            System.out.println(turnScore);
            turnScore = turnScore + die1Value + die2Value;
        }

        //Update the images
        die1.setIcon(dieImage[die1Value]);
        die2.setIcon(dieImage[die2Value]);

        System.out.println(turnScore);
        return die1Value + die2Value;
    }

    /**
     * Checks to see if the previous roll has 1 or more 1s
     * @return boolean representation of if a one was rolled
     */
    public boolean isTurnScoreLost() {
        return turnScoreLost;
    }

    /**
     * Checks to see if the previous roll has 2 ones
     * @return boolean representation of if two ones was rolled
     */
    public boolean isGameScoreLost() {
        return gameScoreLost;
    }

    /**
     * Checks to see if the user has accumulated enough points to win the game
     * @return boolean representation of if the player score is greater than 100
     */
    public boolean hasWon() {
        return gameScore >= 100;
    }

    /**
     * Adds the turn score of the player to their current game score
     * @return returns true if the players game score was successfully updated
     */
    public boolean addTurnScoreToGameScore() {

        //Resets turn variables
        turnScoreLost = false;
        gameScoreLost = false;

        //If the user had a score of 0, game score was not updated
        if (turnScore < 1) {
            return false;
        }

        //Add turn and game score
        gameScore += turnScore;

        //Reset turn score
        turnScore = 0;

        //Return that the score was updated
        return true;
    }

    /**
     * Retrieves the turn score for the selected player object
     * @return the turn score of the player object
     */
    public int getTurnScore() {
        return turnScore;
    }

    /**
     * Retrieves the game score for the selected player object
     * @return the game score of the player object
     */
    public int getGameScore() {
        return gameScore;
    }

    /**
     * Resets the player object to default state. Can be used
     * if the player loses their turn or a game reset
     */
    public void reset() {
        //Sets all object variables to false
        turnScoreLost = false;
        gameScoreLost = false;
        gameScore = 0;
        turnScore = 0;
    }


    /**
     * Retrieves the name of the player to be used elsewhere
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the players name to the provided value
     * @param name the string to set the players name to
     */
    public void setName(String name) {
        this.name = name;
    }
}
