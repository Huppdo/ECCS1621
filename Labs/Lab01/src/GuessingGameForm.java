import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGameForm {

    //Sets up variables for the form elements
    private JTextField inputTextField;
    private JButton newGameButton;
    private JLabel inputLabel;
    private JLabel resultsLabel;
    private JPanel panel1;

    //Number of times the user has guessed
    private int numberOfGuesses = 0;
    private final static int maxNumberOfGuesses = 10;

    //New random number generator based off custom class
    private RandomValue randomValue;

    public GuessingGameForm() {

        randomValue = new RandomValue();
        newGameButton.setEnabled(false);

        inputTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Increment number of guesses by 1
                numberOfGuesses += 1;

                //Converts the input field to an int and stores the value
                int guess = Integer.parseInt( inputTextField.getText() );

                //If the user value equals the random value
                if ( randomValue.isCorrectValue(guess) ) {

                    //Creates a string to store plurality
                    String guessStr = " guess";  // note space before word

                    //If the user has more than 1 guess, change to plural
                    if ( numberOfGuesses > 1 )
                        guessStr = " guesses";

                    //Sets label to correct in X guesses
                    resultsLabel.setText("Correct in " + numberOfGuesses + guessStr );

                    //Disables text inputs
                    inputTextField.setEnabled(false);

                    //Enables new game input
                    newGameButton.setEnabled(true);

                    //Exit listener
                    return;
                }

                //Stores the difference between user guess and random #
                int difference = randomValue.compareTo(guess);

                //If guess is too low, display too low
                if ( difference > 0 ) {
                    resultsLabel.setText(guess + " is too low");
                }
                else { //Else, display too high
                    resultsLabel.setText(guess + " is too high");
                }

                //Checks to see if user has hit max number of guesses
                if (numberOfGuesses < maxNumberOfGuesses) {
                    //Clear input text field
                    inputTextField.setText("");
                } else { //Alerts user that they hit the max
                    resultsLabel.setText("Max guesses hit.");
                    inputTextField.setText("Correct answer: " + randomValue.getRandomValue());
                    //Disables text inputs
                    inputTextField.setEnabled(false);

                    //Enables new game input
                    newGameButton.setEnabled(true);
                }

            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Resets number of guesses to 0
                numberOfGuesses = 0;

                //Gets new random value
                randomValue.setRandomValue();

                //Clears input text field
                inputTextField.setText( "" );

                //Displays message to user so they know to start over
                resultsLabel.setText("Can you guess my new value?");

                //Enable the text field for guessing
                inputTextField.setEnabled(true);

                //Disable the new game button
                newGameButton.setEnabled(false);
            }
        });
    }

    public static void main(String[] args) {

        //Declare the form frame variable
        JFrame myFrame = new JFrame("The Guessing Game!");

        //Sets the form panel to the frame
        myFrame.setContentPane(new GuessingGameForm().panel1);

        //Determine exit behavior of the form
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Insert content into the frame
        myFrame.pack();

        //Display frame to the user
        myFrame.setVisible(true);
    }
}
