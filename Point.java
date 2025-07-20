/*  Name: Ngaatendwe Manyika
*  PennKey: nmanyika
*
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class that represents a coordinate of an object, depicting
*  the x and y position. This class allows you to get and set 
*  the x and y position.
*/

/**
 * A point class
 */
public class Point {

    // coordinate system (x, y)
    private double xPos, yPos;

    public Point(double xPos, double yPos) {
        
        this.xPos = xPos;
        this.yPos = yPos; 

    }

    // getters and setters 

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public void setX(double x) {
        xPos = x;
    }

    public void setY(double y) {
        yPos = y;
    }
}