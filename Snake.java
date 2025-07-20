/*  Name: Ngaatendwe Manyika
*  PennKey: nmanyika
*
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class that represents the slithering Snake in the
*  Snake game. It can update its own position based
*  on velocity and time, and can compute whether
*  it overlaps a given fruit, boundary or itself.
*
*/

import java.util.ArrayList;

public class Snake {

    // the cell width, height and half length values
    private double cellWidth, cellHeight, halfCell;

    // the position and velocity, of thr Snake
    private double xVel, yVel, headRow, headCol;

    // the snake body is an array list of points
    private ArrayList<Point> body;

    /** 
    * this key preserves the previous cardinal direction 
    * of the snake's head
    */
    private char dir;

    /**
    * Initialize the Snake's member variables
    * with the same names as the inputs to those values.
    * Initializes the Snakes's velocity components to 0.
    */
    public Snake(double headRow, double headCol, 
        double cellWidth, double cellHeight) {

        this.headRow = headRow;
        this.headCol = headCol;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        halfCell = 0.5 * cellWidth;
        body = new ArrayList<Point>();
        body.add(new Point(headRow, headCol));
        xVel = 0;
        yVel = 0;

    }

    /**
    * Set xPos and yPos to 6.0,
    * set xVel and yVel to 0.0.
    */
    public void reset() {

        headRow = 6.0;
        headCol = 6.0;
        xVel = 0.0;
        yVel = 0.0;

    }

    /**
    * Given the change in time, compute the Snakes's
    * new position and new velocity. Keep track of the 
    * cardinal directions via W, A, S, D user inputs.
    */
    public void update(double timeStep) {

        char key = dir;

        if (PennDraw.hasNextKeyTyped()) {
            key = PennDraw.nextKeyTyped();
        }

        if (key == 'a' && xVel != 1) {
            xVel = -1;
            yVel = 0;
        }
        if (key == 'd' && xVel != -1) {
            xVel = 1;
            yVel = 0;
        }
        if (key == 's' && yVel != 1) {
            xVel = 0;
            yVel = -1;
        }
        if (key == 'w' && yVel != -1) {
            xVel = 0;
            yVel = 1;
        }

        // updates the head's position
        headRow = headRow + xVel * timeStep;
        headCol = headCol + yVel * timeStep;
        body.get(0).setX(headRow);
        body.get(0).setY(headCol);

        // store this frame's head positions
        double xPrev = headRow;
        double yPrev = headCol;

        // swap the coordinates between the Snake segments
        for (int i = body.size() - 1; i > -1; i--) {
            double xCurr = body.get(i).getX();
            double yCurr = body.get(i).getY();
            body.get(i).setX(xPrev);
            body.get(i).setY(yPrev);
            xPrev = xCurr;
            yPrev = yCurr;
        }

        dir = key;

    }

    // draw's all segments of the Snake
    public void draw() {

        PennDraw.setPenColor(0, 100, 0);

        // draws the body of the snake in dark green
        for (int i = 0; i < body.size(); i++) {
            double x = body.get(i).getX();
            double y = body.get(i).getY();
            PennDraw.setPenColor(0, 100, 0);

            // distinguish the head as light green
            if (i == body.size() - 1) {
                PennDraw.setPenColor(0, 180, 0);
            }
            PennDraw.filledCircle(x, y, halfCell);
        }

    }

    /**
    * Given fruit, test for a collision against it
    * and reset the fruit if there is a collision. The 
    * Snake should grow by 1 in size regardless of the type 
    * of food it interacts with. Score should increment by 1.
    */
    public boolean foodCollision(Food f, Bonus b, double x, double y) {
        if (f.getFruitX() == headRow && f.getFruitY() == headCol) {
            f.reset();
            b.reset();
            body.add(new Point(x, y));
            return true;
        }

        return false;

    }

    /**
    * Given a bonus fruit, test for a collision against it
    * and reset the bonus fruit if there is a collision. The 
    * Snake should grow by 1 in size and increment score by 3.
    */
    public boolean bonusCollision(Bonus b, double x, double y) {
        if (b.getFruitX() == headRow && b.getFruitY() == headCol) {
            if (b.getRandom()) {
                b.reset();
            } else {
                b.setX();
                b.setY();
            }
            body.add(new Point(x, y));
            return true;
        }

        return false;

    }

    /**
    * if the snake collides with any segment of itself
    * then the function should return true at that instance 
    * to allow the game to be declared as over.
    */
    public boolean selfCollision() {
        for (int i = 0; i < body.size() - 1; i++) {
            double x = body.get(i).getX();
            double y = body.get(i).getY();
            if (x == headRow && y == headCol) {
                return true;
            }
        }
        return false;
    }
        

    /**
    * given a Food input, make sure that no fruit is 
    * generated on the same coordinates as any segment 
    * of the snake. If that does occur, regenerate the 
    * fruit until it is not generating on the Snake. 
    */
    public void testIntersection(Food f) {
        for (int i = 0; i < body.size(); i++) {
            double x = body.get(i).getX();
            double y = body.get(i).getY();

            if (f.getFruitX() == x && f.getFruitY() == y) {
                f.reset();
            }

        }
    }

    /**
    * given a Bonus food input, make sure that no bonus  
    * fruit is generated on the same coordinates as any 
    * segment of the snake. If that does occur, regenerate the 
    * bonus fruit until it is not generating on the Snake. 
    */
    public void bonusIntersection(Bonus b) {
        for (int i = 0; i < body.size(); i++) {
            double x = body.get(i).getX();
            double y = body.get(i).getY();

            if (b.getFruitX() == x && b.getFruitY() == y) {
                b.reset();
            }

        }
    }

    /**
    * ensure the Snake stays within the boundaries 
    * of the board. If the snake surpasses the boundaries
    * this function returns true, so that the gaem ends. 
    * this function allows the snake to glide along 
    * the board's edges
    */
    public boolean boundaries() {
        boolean headR = headRow < 2 || headRow > 18;
        boolean headC = headCol < 2 || headCol > 18;
        return headR || headC;
    }

    // getter functions 

    public double getLastX() {
        return body.get(body.size() - 1).getX();
    }

    public double getLastY() {
        return body.get(body.size() - 1).getY();
    }
    
}
