/**
 * Class representation of a triangle. This class is utilized to store and calculate
 * data about a specific triangle through knowledge of the cartesian coordinates of the
 * 3 corners.
 * @version 1.0.0
 * @author Dominic Hupp
 */
public class Triangle {

    //Variable representations of all 3 corners of the triangle
    private double cornerA_x;
    private double cornerA_y;
    private double cornerB_x;
    private double cornerB_y;
    private double cornerC_x;
    private double cornerC_y;

    /**
     * Initializes a triangle with 3 corners located all at location 0,0
     */
    public Triangle() {

        //Set corner a to 0,0
        cornerA_x = 0; cornerA_y = 0;

        //Set corner b to 0,0
        cornerB_x = 0; cornerB_y = 0;

        //Set corner c to 0,0
        cornerC_x = 0; cornerC_y = 0;

    }

    /**
     * Sets both coordinates of a given corner on the triangle
     * @param corner the corner in which the provided points should be applied to (A, B, or C)
     * @param x the x value of the given corner
     * @param y the y value of the given corner
     * @return a boolean representing if the corner coords were successfully updated
     */
    public boolean setCorner(char corner, double x, double y) {

        //Converts character to uppercase
        char upperCaseCorner = Character.toUpperCase(corner);

        if (upperCaseCorner == 'A') {
            cornerA_x = x;
            cornerA_y = y;
        } else if (upperCaseCorner == 'B') {
            cornerB_x = x;
            cornerB_y = y;
        } else if (upperCaseCorner == 'C') {
            cornerC_x = x;
            cornerC_y = y;
        } else {
            //This means the corner was not valid
            return false;
        }

        return true;
    }

    /**
     * Calculates the distance between two corners of the triangle by using a geometric distance formula
     * @param corner1 the character value representing the desired first corner
     * @param corner2 the character value representing the desired second corner
     * @return represents the distance between the two provided corners
     * @throws IllegalArgumentException if either corner provided is not of value A, B, or C
     */
    public double getSideLength(char corner1, char corner2) {

        char upperCaseCorner1 = Character.toUpperCase(corner1);
        char upperCaseCorner2 = Character.toUpperCase(corner2);

        double x1; double y1;
        double x2; double y2;

        if (upperCaseCorner1 == 'A') {

            //Set first coord set to the corner A values
            x1 = cornerA_x;
            y1 = cornerA_y;

        } else if (upperCaseCorner1 == 'B') {

            //Set first coord set to the corner B values
            x1 = cornerB_x;
            y1 = cornerB_y;

        } else if (upperCaseCorner1 == 'C') {

            //Set first coord set to the corner C values
            x1 = cornerC_x;
            y1 = cornerC_y;

        } else {

            //This means the function was called with an invalid corner
            throw new IllegalArgumentException("Corner 1 was not of values A, B, or C");
        }

        if (upperCaseCorner2 == 'A') {

            //Set second coord set to the corner A values
            x2 = cornerA_x;
            y2 = cornerA_y;

        } else if (upperCaseCorner2 == 'B') {

            //Set second coord set to the corner B values
            x2 = cornerB_x;
            y2 = cornerB_y;

        } else if (upperCaseCorner2 == 'C') {

            //Set second coord set to the corner C values
            x2 = cornerC_x;
            y2 = cornerC_y;

        } else {
            //This means the function was called with an invalid corner
            throw new IllegalArgumentException("Corner 2 was not of values A, B, or C");
        }

        //Follows the distance formula
        //Math.sqrt takes the square root
        //Math.pow raises a double to a power, in this case 2
        return Math.sqrt( Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) );
    }

    /**
     * Calculates the angle of the corner based on the SSS triangle principle
     * @param corner the corner of the triangle requested (A, B, C)
     * @return double representing the angle value of the corner
     * @throws IllegalArgumentException
     */
    public double getAngle(char corner) {

        //Convert the character to uppercase
        char upperCaseCorner = Character.toUpperCase(corner);

        //Variable to hold first calculation
        double firstCalculation;

        //Calculate cos(corner) using SSS formula
        if (upperCaseCorner == 'A') {
            firstCalculation = (
                    Math.pow(getSideLength('A','B'),2)
                    + Math.pow(getSideLength('A','C'),2)
                    - Math.pow(getSideLength('C','B'),2)) /
                    (2
                    * getSideLength('A','C')
                    * getSideLength('A','B'));
        } else if (upperCaseCorner == 'B') {
            firstCalculation = (
                    Math.pow(getSideLength('C','B'),2)
                            + Math.pow(getSideLength('A','B'),2)
                            - Math.pow(getSideLength('C','A'),2)) /
                    (2
                            * getSideLength('A','B')
                            * getSideLength('C','B'));
        } else if (upperCaseCorner == 'C') {
            firstCalculation = (
                    Math.pow(getSideLength('C','B'),2)
                            + Math.pow(getSideLength('A','C'),2)
                            - Math.pow(getSideLength('A','B'),2)) /
                    (2
                            * getSideLength('A','C')
                            * getSideLength('C','B'));
        } else {

            //Throw exception if corner is not valid
            throw new IllegalArgumentException("Corner value is not of values A, B, or C");
        }

        //Take the inverse cosine of the value to get angle
        return Math.toDegrees(Math.acos(firstCalculation));
    }


    /**
     * Calculates the area of the triangle by using the side lengths
     * @return a numerical representation of the triangles current area
     */
    public double getArea() {

        //Calculates half of the perimeter length
        double halfPerimeter = getPerimeter() / 2;

        //Returns the area calculated by Heron's Formula
        return Math.sqrt(
                halfPerimeter
                * (halfPerimeter - getSideLength('A', 'B'))
                * (halfPerimeter - getSideLength('B','C'))
                * (halfPerimeter - getSideLength('C', 'A'))
        );
    }

    /**
     * Calculates the perimeter of the triangle
     * @return the sum of all 3 triangle side lengths
     */
    public double getPerimeter() {

        //Sum all of the side lengths and return
        return getSideLength('A', 'B') + getSideLength('B', 'C') + getSideLength('C', 'A');
    }

}
