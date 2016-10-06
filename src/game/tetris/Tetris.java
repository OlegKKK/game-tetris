package game.tetris;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Tetris extends JPanel{

    static Tetris tetris = new Tetris();
    static JFrame window = new JFrame();

    Tetris() {

        super();
        setBackground(Color.DARK_GRAY);
        setLayout(null);

    }

    public static void main(String [] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(tetris);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }
}
