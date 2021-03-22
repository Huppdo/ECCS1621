import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LabTwoForm extends javax.swing.JPanel {

    //Strings to hold user input
    String string1;
    String string2;



    //Variables that reference the GUI form
    private JTextField string1TextField;
    private JTextField string2TextField;
    private JButton compareToButton;
    private JButton endsWithButton;
    private JButton toLowerCaseButton;
    private JButton toUpperCaseButton;
    private JButton trimButton;
    private JPanel stringPanel;
    private JLabel result1Label;
    private JLabel result2Label;
    private JLabel length1Label;
    private JLabel length2Label;

    public LabTwoForm() {

        //Action Listener for submitting the top text field
        string1TextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Sets string1 variable to current string 1 text field contents
                string1 = string1TextField.getText();

                //Sets length 1 results label to length of string
                length1Label.setText("" + string1.length());

            }
        });

        //Action Listener for submitting the lower text field
        string2TextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Sets string2 variable to current string 2 text field contents
                string2 = string2TextField.getText();

                //Sets length 2 results label to length of string
                length2Label.setText("" + string2.length());

            }
        });

        //Listens for click on compareToButton
        //Compares strings lexographically
        compareToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*
                Uses a built in string comparison to get a lexographic comparison
                < 0 means string 1 comes first
                = 0 means the strings are equiv
                > 0 means string 2 comes first
                */
                int comparisionResults = string1.compareTo(string2);

                //Analyze result and display to user
                if (comparisionResults < 0) {
                    result1Label.setText("str1 < str2 - String 1 comes first");
                } else if (comparisionResults == 0) {
                    result1Label.setText("str1 == str2 - They are equivalent");
                } else if (comparisionResults > 0) {
                    result1Label.setText("str1 > str2 - String 2 comes first");
                } else {
                    result1Label.setText("Error in compareToButton execution");
                }
            }
        });

        //Listens for click on endsWithButton
        //Checks to see if string 2 is a suffix of string 1
        endsWithButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Sets the result 2 label to the suffix status
                // condition ? strIfTrue : strIfFalse
                result2Label.setText(string1.endsWith(string2) ? "Yes, string 2 is the suffix" : "No, string 2 is not the suffix");

            }
        });

        //Listens for click on toLowerCaseButton
        //Converts both strings to lowercase
        toLowerCaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Convert string 1 to lowercase and display it
                result1Label.setText(string1.toLowerCase(Locale.ROOT));

                //Convert string 2 to lowercase and display it
                result2Label.setText(string2.toLowerCase(Locale.ROOT));

            }
        });

        //Listens for click on toUpperCaseButton
        //Converts both strings to uppercase
        toUpperCaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Convert string 1 to lowercase and display it
                result1Label.setText(string1.toUpperCase(Locale.ROOT));

                //Convert string 2 to lowercase and display it
                result2Label.setText(string2.toUpperCase(Locale.ROOT));

            }
        });

        //Listens for click on trimButton
        //Removes leading and trailing whitespace
        trimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Remove whitespace from string1 and display
                result1Label.setText(string1.trim());

                //Remove whitespace from string2 and display
                result2Label.setText(string2.trim());

            }
        });
    }

    public static void main(String[] args) {
        //Declare the form frame variable
        JFrame myFrame = new JFrame("String Comparison - Lab 2");

        //Sets the form panel to the frame
        myFrame.setContentPane(new LabTwoForm().stringPanel);

        //Determine exit behavior of the form
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Insert content into the frame
        myFrame.pack();

        //Display frame to the user
        myFrame.setVisible(true);
    }
}
