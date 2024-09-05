import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Frame size
        int boardWidth = 600;
        int boardHeight = 600;

        // Frame
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add snake game to frame
        SnakeGame snakeGame = new SnakeGame(boardHeight, boardWidth);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}