import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 600;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardWidth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(boardWidth, boardWidth);
        frame.add(snakeGame);
        frame.pack();
    }
}