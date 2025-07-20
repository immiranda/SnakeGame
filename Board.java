/*  Name: Ngaatendwe Manyika
*  PennKey: nmanyika
*
*  Execution: N/A, this class is meant to be used by other classes
*
*  A class representing the arena in which the Snake game 
*  takes place. This keeps track of the game's snake and fruit
*  objects, and receives the player's input to control the snake.
*
*/

public class Board {

    // number of rows and columns
    private double numRows, numCols;

    // canvas width and height
    private double width, height;

    // the canvas cell width and height 
    private double cellWidth, cellHeight;

    // the score trackers 
    private int score, highScore;

    // the one and only snake in the game
    private Snake snake;

    // the fruits on the board 
    private Food fruit;
    private Bonus bonus;

    /** creates a board of size width x height, keeping track 
    * of the highscore in the current game session.
    */
    public Board(double width, double height, int highScore) {

        this.width = width;
        this.height = height;
        this.highScore = highScore;

        // inital values
        score = 0;
        cellWidth = 1.0;
        cellHeight = 1.0;

        // making new game pieces
        snake = new Snake(6, 6, 1, 1);
        fruit = new Food(1, 1);
        bonus = new Bonus(1, 1);

    }

    /**
    * update each of the entities within the board.
    * and invoke the appropriate functions for the snake, 
    * including collisions and interactions of various
    * board objects.
    */
    public void update(double timeStep) {

        double xLast = snake.getLastX();
        double yLast = snake.getLastY();
        if (snake.foodCollision(fruit, bonus, xLast, yLast)) {
            score++;
        }
        if (snake.bonusCollision(bonus, xLast, yLast)) {
            score = score + 3;
        }
        snake.testIntersection(fruit);
        snake.bonusIntersection(bonus);
        bonus.fruitCollision(fruit);
        snake.update(timeStep);
    }

    /**
    * draws the board background which includes the 
    * score and high score. Then draw the other objects 
    * in the game such as the snake and the fruit.
    */
    public void draw() {

        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(10, 10, 8.5);
        
        PennDraw.text(3, 19, "score: " + score);
        PennDraw.text(8, 19, "high score: " + highScore);

        fruit.draw();
        if (bonus.getRandom()) {
            bonus.draw();
        } 
        snake.draw();

    }

    /**
    * the game is over when the snake collides with the board
    * boundaries or with itself, else, the game continues 
    * to play until said condition is met.
    */
    public boolean gameOver() {
        return snake.boundaries() || snake.selfCollision();
    }

    // getter and setter functions
    
    public int getScore() {
        return score;
    }

    public void setHighScore(int high) {
        highScore = high;
    }

}
