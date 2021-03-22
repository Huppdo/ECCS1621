import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Moo Test GUI
 *
 * <P>Displays the main GUI used to debug the RandomMooValue class
 *
 * @author Dominic Hupp
 * @version 1.0.0
 */
public class MooTestGUI {
    private JButton randomButton;
    private JPanel testPanel;
    private JTextField secretValueTextField;
    private JTextField guessTextField;
    private JLabel numberBigMoo;
    private JLabel numberSmallMoo;
    private JLabel isCorrect;
    private JLabel header;
    private static RandomMooValue mooContainer;

    /**
     * The main constructor that runs when the jframe and panel are created
     */
    public MooTestGUI() {

        //Initialize our randomMooHolder
        mooContainer = new RandomMooValue();

        //Displays secret value
        secretValueTextField.setText(mooContainer.getSecretValueAsString());

        //Listens for submit on secret text field
        secretValueTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Ensure that the value provided can be converted to int
                try {
                    //If the value was set successfully
                     if (mooContainer.setSecretValue(Integer.parseInt(secretValueTextField.getText()))) {

                         //Update text box per instructions
                         mooContainer.convertIntToGameString(mooContainer.getSecretValue());

                         //Clear error in headers
                         header.setText("LaurieMOO Test Program");
                     } else { //If the value was not submitted
                         //Display error
                         header.setText("Value not in correct range (0 - 9999)");
                     }

                } catch (Exception ex) {
                    //Display parsing error
                    header.setText("Invalid input - " + ex.getMessage());
                }
            }
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Generate a new secret value
                mooContainer.setSecretValue();

                //Display new secret value
                secretValueTextField.setText(mooContainer.convertIntToGameString(mooContainer.getSecretValue()));
            }
        });
        guessTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { //Ensure value can be parsed

                    //Parse value
                    int guessInt = Integer.parseInt(guessTextField.getText());

                    //Ensure value is in range
                    if (guessInt < 0 || guessInt > RandomMooValue.MAX_NUMBER) {
                        header.setText("Please enter number in correct range");
                        return;
                    }

                    //Set auxiliary information
                    numberBigMoo.setText("" + mooContainer.getBigMooCount(guessInt));
                    numberSmallMoo.setText("" + mooContainer.getLittleMooCount(guessInt));
                    isCorrect.setText(mooContainer.isCorrectGuess(guessInt) ? "yes" : "no");

                    //Reset error header
                    header.setText("LaurieMOO Test Program");
                } catch (Exception ex) {
                    //Display parse error
                    header.setText("Invalid input - " + ex.getMessage());
                }
            }
        });
    }

    /**
     * The entry point for MP1 to create the GUI
     * @param args Command Line Arguements passed at runtime [none]
     */
    public static void main(String[] args) {

        //Declare the form frame variable
        JFrame myFrame = new JFrame("LaurieMOO - MP1 Part 1");

        //Sets the form panel to the frame
        myFrame.setContentPane(new MooTestGUI().testPanel);

        //Determine exit behavior of the form
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Insert content into the frame
        myFrame.pack();

        //Display frame to the user
        myFrame.setVisible(true);
    }
}
