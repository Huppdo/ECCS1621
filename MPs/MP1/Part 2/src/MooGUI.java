import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Moo GUI
 *
 * <P>Displays the main GUI used to debug the RandomMooValue class
 *
 * @author Dominic Hupp
 * @version 1.0.0
 */
public class MooGUI {
    private JPanel testPanel;
    private JTextField guessTextField;
    private JLabel errorLabel;
    private JLabel displayLabel;
    private JButton inputButton;
    private static RandomMooValue mooContainer;

    private byte roundCounter;

    /**
     * The main constructor that runs when the jframe and panel are created
     */
    public MooGUI() {

        //Initialize our randomMooHolder
        mooContainer = new RandomMooValue();

        //Start round counter
        roundCounter = -1;

        //Disable text field upon startup
        guessTextField.setEnabled(false);

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //See if a new game needs to be started
                if (roundCounter == -1 || roundCounter > 9) {
                    //reset counter
                    roundCounter = 0;

                    //get new secret value
                    mooContainer.setSecretValue();

                    //change button text
                    inputButton.setText("Submit");

                    //Enable text field
                    guessTextField.setEnabled(true);

                    //Set display msg
                    displayLabel.setText("Waiting for User Value!");
                } else {
                    processGuess();
                }
            }
        });

        guessTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
    }

    /**
     * Processes the guess when called. allows for either button or text field
     * action listener to trigger a guess
     */
    private void processGuess() {
        try { //Ensure value can be parsed

            //Parse value
            int guessInt = Integer.parseInt(guessTextField.getText());

            //Ensure value is in range
            if (guessInt < 0 || guessInt > RandomMooValue.MAX_NUMBER) {
                errorLabel.setVisible(true);
                errorLabel.setText("Please enter number in correct range");
                return;
            }

            errorLabel.setVisible(false);

            //Check to see if the guess was correct or this was the last round
            if (mooContainer.isCorrectGuess(guessInt) || roundCounter >= 9) {

                if (roundCounter < 9) {
                    //Display a success label and popup
                    displayLabel.setText(String.format("MOO! MOO! MOO! MOO! LaurieMoo!!!", guessInt, roundCounter + 1));
                    JOptionPane.showMessageDialog(null, String.format("MOO! MOO! MOO! MOO! LaurieMoo!!!", guessInt, roundCounter + 1));
                } else {
                    //Display a failed label and popup
                    displayLabel.setText(String.format("Boo hoo -- no LaurieMOO. %d", mooContainer.getSecretValue()));
                    JOptionPane.showMessageDialog(null, String.format("Boo hoo -- no LaurieMOO. %d", mooContainer.getSecretValue()));
                }

                //Reset the round counter
                roundCounter = -1;

                //Disable the text field
                guessTextField.setEnabled(false);

                //Change the button text
                inputButton.setText("Start Game!");

                return;
            }

            //String to hold the moos
            String moos = "";

            //Iterate and add moos
            for (int i = 0; i < mooContainer.getBigMooCount(guessInt); i++) {
                moos += "MOO! ";
            }
            for (int i = 0; i < mooContainer.getLittleMooCount(guessInt); i++) {
                moos += "moo. ";
            }

            //If no correct numbers
            if (moos.isEmpty()) {
                moos = "All you hear are cowbells... ";
            }

            //Display moos
            displayLabel.setText(String.format("Round %d - %s", roundCounter+1, moos));

            //Increment round by 1
            roundCounter += 1;

            //Clear user text
            guessTextField.setText("");
        } catch (Exception ex) {
            //Display parse error
            errorLabel.setVisible(true);
            errorLabel.setText("Invalid input.");
        }
    }

    /**
     * The entry point for MP1 to create the GUI
     * @param args Command Line Arguments passed at runtime [none]
     */
    public static void main(String[] args) {

        //Declare the form frame variable
        JFrame myFrame = new JFrame("LaurieMOO - MP1 Part 2");

        //Sets the form panel to the frame
        myFrame.setContentPane(new MooGUI().testPanel);

        //Determine exit behavior of the form
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Insert content into the frame
        myFrame.pack();

        //Display frame to the user
        myFrame.setVisible(true);
    }
}
