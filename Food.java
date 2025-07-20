/*  Name: Ngaatendwe Manyika
*  PennKey: nmanyika
*
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class that represents a regular fruit which is eaten in
*  the Snake game. Can update its own position based
*  on a random coordinate generator funciton.
*/

public class Food {
    
    // the cell width, height and half length values
    private double cellWidth, cellHeight, halfCell;

    // position of the fruit
    private double headRow, headCol;

    /** 
    * creates a Food object by initializing variables 
    * and reseting the fruit using a random coordinate 
    * generator.
    */
    public Food(double cellWidth, double cellHeight) {

        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        halfCell = 0.5 * cellWidth;
        reset();

    }

    /** 
    * modified Math.random() function which randomly 
    * generates a coordinate for the fruit, and uses
    * recursion generate a valid number eg. a number
    * that places the fruit on the board.
    */
    private double mathRandom() {

        int range = (int) (20 * Math.random());

        // check that fruit is within the boundaries
        if (range < 18 && range > 2) {
            return (double) range;
        }
        return mathRandom();

    }

    /**
    * Set headRow and headCol to random valid values,
    * on the board.
    */
    public void reset() {

        headRow = mathRandom();
        headCol = mathRandom();

    }

    // draw the  regular fruit in bright red
    public void draw() {

        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledCircle(headRow, headCol, halfCell);

    }

    // getter functions 

    public double getFruitX() {
        return headRow;
    }

    public double getFruitY() {
        return headCol;
    }

}