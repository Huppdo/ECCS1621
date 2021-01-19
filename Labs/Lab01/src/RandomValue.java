public class RandomValue {

    //Creates a new random number generator
    private java.util.Random rndGenerator = new java.util.Random();

    //Holds the random value that's generated
    private int randomValue;

    //"Symbolic Constant", represents the maximum number (exclusive)
    public final static int NUMBER_OF_VALUES = 100;

    //Public class constructor
    //Allows for the class to be created
    public RandomValue() {
        //Generates the random value between 0 and the max number (excluding)
        randomValue = rndGenerator.nextInt(NUMBER_OF_VALUES);
    }

    //Public function, generates a new random value
    public void setRandomValue() {
        randomValue = rndGenerator.nextInt(NUMBER_OF_VALUES);
    }

    //Overload of previous setRandomValue function, allows for random value to be set
    public void setRandomValue( int n ) {
        //Checks to see if value is between predetermined min or max
        if ( n < 0 || n >= NUMBER_OF_VALUES )
            return;  // take no action - value out of range

        //Sets random value to provided value
        randomValue = n;
    }

    //Returns the value, which is private
    public int getRandomValue() {
        return randomValue;
    }

    //Compares provided user value to predetermined random val
    public boolean isCorrectValue( int value ) {
        if ( value == randomValue )
            return true;
        return false;
    }

    // compareTo: returns < 0 if random value is less than passed value,
    //                    > 0 if random value is greater than passed value
    //                      0 if random value equals passed value
    public int compareTo( int value ) {
        return randomValue - value;
    }

}
