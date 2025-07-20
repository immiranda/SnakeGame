/*  Name: Ngaatendwe Manyika
*  PennKey: nmanyika
*
*  Execution: java SnakeGame
*
*  Represents the Snake game. This initializes a board on which 
*  the game will be played through a series of user inputs
*  via key input.
*
*/

public class SnakeGame {
    public static void main(String[] args) {

        // set the game board to be a 20 x 20 board
        int width = 20;
        int height = 20;
        final double TIME_STEP = 1.0;
        int highScore = 0;
        int nmuPixels = 600;

        PennDraw.setCanvasSize(nmuPixels, nmuPixels);
        // Set the scale in terms of width and height;
        // hopefully gives "rounder" numbers for sizes & speeds than
        // using the default scale of 0-1.
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);
        PennDraw.enableAnimation(3);

        // create a new board and wait for game to be played
        Board board = new Board(width, height, highScore);
        boolean play = false;

        while (true) {

            while (play) {

                // play the game if it is not over
                while (!board.gameOver()) {
                    PennDraw.clear(200, 200, 200);
                    board.update(TIME_STEP);

                    // keep track of high score
                    if (board.getScore() > highScore) {
                        highScore = board.getScore();
                        board.setHighScore(highScore); 
                    }
                    board.draw();
                    PennDraw.advance();
                } 

                // Draw the game over screen 
                PennDraw.clear(200, 200, 200);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(7, 12, "current score: " + board.getScore());
                PennDraw.text(7, 10, "high score: " + highScore);
                PennDraw.text(7, 8, "GAMEOVER... click 'r' to restart");
                
                // if 'r' is pressed, the game restarts
                if (PennDraw.hasNextKeyTyped()) {
                    if (PennDraw.nextKeyTyped() == 'r') {
                        board = new Board(width, height, highScore);
                    }
                }

                PennDraw.advance();

            }

                // bonus: draws the title page of the game
                PennDraw.clear(200, 200, 200);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(8, 12, "S N A K O");
                PennDraw.text(10, 10, "to start the game press 'p'");

                // change game state from dormant to play mode
                if (PennDraw.hasNextKeyTyped()) {
                    if (PennDraw.nextKeyTyped() == 'p') {
                        play = true;
                    }
                }
                PennDraw.advance();

            
        }
    }
}