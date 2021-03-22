/**
 * Calculates the date of Easter Sunday using Gauss' algorithm
 *
 * @author Dominic Hupp
 * @modified 2/1/21
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class EasterSunday {

    //Form elements
    private JTextField yearField;
    private JPanel panel1;
    private JLabel resultLabel;

    public EasterSunday() {
        resultLabel.setVisible(false);

        yearField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //Get text value
                String userValue = yearField.getText();

                //Variable to store int value of user year
                int userYear;

                //Checks to see if user value is a string
                try {
                    //convert user string to int
                    userYear = Integer.parseInt(userValue);
                } catch (NumberFormatException exception) {

                    //If not an int, show error
                    JOptionPane.showMessageDialog(null, "Please enter a valid year.");

                    //Prevent other code from running
                    return;
                } catch (Exception except) {
                    //Other errors besides number format exception
                    JOptionPane.showMessageDialog(null, "Error: " + except.toString());

                    //Prevent other code from running
                    return;
                }

                //Divide y by 19 and call the remainder a. Ignore the quotient.
                int a = userYear % 19;

                //Divide y by 100 to get a quotient b and a remainder c.
                int b = userYear / 100;
                int c = userYear % 100;

                //Integer of quotient of b divided by 4
                int d = b / 4;

                //Remainder of b divided by 4
                int e = b % 4;

                //Divide 8 * b + 13 by 25 to get a quotient g. Ignore the remainder.
                int g = (8 * b + 13) / 25;

                //Divide 19 * a + b - d - g + 15 by 30 to get a remainder h. Ignore the quotient.
                int h = (19* a + b - d - g + 15) % 30;

                //Divide c by 4 to get a quotient j and a remainder k.
                int j = c / 4;
                int k = c % 4;

                //Divide a + 11 * h by 319 to get a quotient m. Ignore the remainder.
                int m = (a + 11 * h) / 319;

                //Divide 2 * e + 2 * j - k - h + m + 32 by 7 to get a remainder r. Ignore the quotient.
                int r = (2 * e + 2 * j - k - h + m + 32) % 7;

                //Divide h - m + r + 90 by 25 to get a quotient n. Ignore the remainder.
                int n = (h - m + r + 90) / 25;

                //Divide h - m + r + n + 19 by 32 to get a remainder p. Ignore the quotient.
                int p = (h - m + r + n + 19) % 32;

                //Easter falls on day p of month n.
                //Display this to user
                resultLabel.setText(String.format("%s %d",
                        java.time.Month.of(n).getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH),
                        p));
                resultLabel.setVisible(true);

            }
        });
    }

    /**
     * Entry point for code
     * @param args command line arguements
     */
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("Easter Sunday Calculator - Lab 03 Part 1");

        //Sets the form panel to the frame
        myFrame.setContentPane(new EasterSunday().panel1);

        //Determine exit behavior of the form
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Insert content into the frame
        myFrame.pack();

        //Display frame to the user
        myFrame.setVisible(true);
    }
}
