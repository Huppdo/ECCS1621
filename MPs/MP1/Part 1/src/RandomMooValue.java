import java.util.ArrayList;
import java.util.Locale;

/**
 * RandomMooValue
 *
 * <P>Class dedicated to main moo functionality. This class
 * holds information such as the secret guess and methods that
 * can be used to retrieve moos and values
 *
 * @author Dominic Hupp
 * @version 1.0.0
 */
public class RandomMooValue {

    //String to hold secret
    private String secretGuess = "";

    //Maximum value of random number
    public static final int MAX_NUMBER = 9999;

    //Necessary length for number
    public static final int NUMBER_LENGTH = 4;

    //Random number generator
    private static final java.util.Random random = new java.util.Random();

    /**
     * This constructor instantiates a RandomMooValue object and preassigns a string consisting
     * of characters 0-9 with length 4.
     */
    public RandomMooValue() {
        setSecretValue();
    }

    /**
     * The number of digits in the user's guess that exactly (i.e., same position) matches digits in the secret value.
     * @param guess - The number that the user guessed.
     * @return a number 0-4 representing how many digits the user guessed correctly by position.
     */
    public int getBigMooCount(int guess) {
        String userGuess = convertIntToGameString(guess);
        int counter = 0;

        for (int i = 0; i < NUMBER_LENGTH; i++) {

            if (userGuess.charAt(i) == secretGuess.charAt(i)) {
                counter += 1;
            }
        }

        return counter;
    }

    /**
     * The number of digits in the user's guess that match digits in the secret value, but are not at the same position
     * @param guess - The number that the user guessed.
     * @return a number 0-4 representing how many of the guessed digits match, but are in different
     * positions. Note that a guessed number cannot have any one digit generate both a "MOO!"
     * and a "moo." as a result.
     */
    public int getLittleMooCount(int guess) {

        //Convert the user guess to a string with length 4
        String userGuess = convertIntToGameString(guess);

        //Start counter to get found numbers
        int counter = 0;

        //Create integer list of values that have already been found
        //This prevents the same number registering multiple times
        java.util.List<Integer> foundLocation = new ArrayList<>();

        //Loop through each character in the guess
        for (int i = 0; i < NUMBER_LENGTH; i++) {

            //Search for the current character in the secret string
            int location = secretGuess.indexOf(userGuess.charAt(i));

            //If the location was found
            if (-1 < location) {

                //Ensure the values are not in the same location and the location has not been used before
                if (location != i && !foundLocation.contains(location)) {
                    counter += 1;
                }

                //Append the location to the list of locations that have already been considered
                foundLocation.add(location);

            }
        }

        return counter;
    }

    /**
     * Exposes the secret guess string as an integer
     * @return an integer representation of the secret guess
     */
    public int getSecretValue() {
        //Convert the secret game string into an int and return
        return Integer.parseInt(secretGuess);
    }

    /**
     * Exposes the secret guess string as an string
     * @return a string representation of the secret guess
     */
    public String getSecretValueAsString() {
        //Convert the secret game string into an int and return
        return secretGuess;
    }

    /**
     * Determines if the user correctly guessed the secret value .
     * @param guess the user's guess as an integer
     * @return true if the guess is correct, false if not
     */
    public boolean isCorrectGuess(int guess) {

        //converts guess integer into string and then compares to the secret value
        return secretGuess.equals(convertIntToGameString(guess));
    }

    /**
     * Generates a new secret value to be guessed in a game of LaurieMOO
     * @return true
     */
    public boolean setSecretValue() {
        int randomInteger = random.nextInt(MAX_NUMBER);

        secretGuess = convertIntToGameString(randomInteger);

        return true;
    }

    /**
     * Sets the "secret" value to be guessed in a game of LaurieMOO to a known 4-digit quantity
     * @param n the value to set as the secret digit
     * @return true if value is set, false if value is outside of range
     */
    public boolean setSecretValue(int n) {

        //Checks to make sure the user provided value is in the correct range
        if (0 <= n && n <= MAX_NUMBER) {

            //Assign the secret guess variable to a string representation of the user value
            secretGuess = convertIntToGameString(n);

            //Return successful
            return true;
        }

        //Return unsuccessful
        return false;
    }

    /**
     * Converts from an integer to a string of length 4 with leading 0s if needed
     * @param gameInt the integer being set as the random value
     * @return a string representation of the provided integer
     */
    public String convertIntToGameString(int gameInt) {

        //Format the number to be 4 digits long
        return String.format((java.util.Locale) null, "%0" + NUMBER_LENGTH + "d" , gameInt);
    }
}
