/*  Name: Ngaatendwe Manyika
*  PennKey: nmanyika
*
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class that represents a bonus fruit which is eaten in
*  the Snake game. Can update its own position based
*  on a random coordinate generator funciton.
*/

public class Bonus {
    
    // the cell width, height and half length values
    private double cellWidth, cellHeight, halfCell;

    // position of the fruit
    private double headRow, headCol;

    // variable to randomize appearence of fruit on board
    private double randomizer;

    /** 
    * creates a Bonus object by initializing variables 
    * and reseting the bonus using a random coordinate 
    * generator.
    */
    public Bonus(double cellWidth, double cellHeight) {

        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        halfCell = 0.5 * cellWidth;
        randomizer = Math.random();
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
        randomizer = Math.random();
        
    }

    // draw the bonus fruit in a muted hot pink
    public void draw() {

        PennDraw.setPenColor(150, 20, 75);
        PennDraw.filledCircle(headRow, headCol, halfCell);

    }

    /**
    * given a Food input, make sure that no fruit is 
    * generated on the same coordinates as the bonus 
    * fruit. If that does occur, regenerate the bonus
    * fruit until it is not generating on regular fruit. 
    */
    public boolean fruitCollision(Food f) {

        if (f.getFruitX() == headRow && f.getFruitY() == headCol) {
            reset();
        }
        return true;
    }

    // getter and setter funcitons 

    public double getFruitX() {
        return headRow;
    }

    public double getFruitY() {
        return headCol;
    }

    public boolean getRandom() {
        return randomizer > 0.5;
    }

    public void setX() {
        headRow = -1;
    }

    public void setY() {
        headCol = -1;
    }

}