import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

// ActionListener handels game logic
// KeyListeners gets key inputs
public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    // class for each tiles
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // board values
    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    // Snake head and snake body stored
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    // Food
    Tile food;
    Random random;

    // game values
    Timer gameloop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        // create first tile and tile list
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        // create food
        food = new Tile(10, 10);
        random = new Random();

        // place food
        placeFood();

        // set standard velocity
        velocityX = 0;
        velocityY = 1;

        // set game loop with timer and start game loop
        gameloop = new Timer(100, this);
        gameloop.start();
    }

    // draws paint on screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // draws paint on screen
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        for (int i = 0; snakeBody.size() > i; i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over", boardWidth / 2 - 50, boardHeight / 2 - 50);
        }
    }

    // place food randomly on screen
    public void placeFood() {
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }

    // collision
    // Params == Tiles to check for collision
    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    // moves stuff
    public void move() {
        // check collision snake head with food
        if (collision(snakeHead, food)) {
            // add Tile to List
            // uses foods x and y to create snake body part
            // replaces food
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Snake Body
        // iterates through snake body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) { // first part off snake body
                // set coords off said part to coords of head to move it by one
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1); // all other part
                // set coords off current part to previous part
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // moves snake head by one
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Snake head colliding with itself
        // Iterates through snake body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            // collision snake head with body part i
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        // Snake leaving the screen
        if (snakeHead.x * tileSize < 0) {
            gameOver = true;
        } else if (snakeHead.x * tileSize > boardWidth) {
            gameOver = true;
        } else if (snakeHead.y * tileSize < 0) {
            gameOver = true;
        } else if (snakeHead.y * tileSize > boardHeight) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameloop.stop();
        }
    }

    // Listens to key input and changes direction
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
