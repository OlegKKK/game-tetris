package game.tetris;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Tetris extends JPanel implements Runnable{

    static Tetris tetris = new Tetris();
    static JFrame window = new JFrame();
    static Thread thread = new Thread(tetris);
    static Board board = new Board();

    boolean start = false;
    short scr = 50;

    Tetris() {
        super();
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        start = true;
    }

    public static void main(String [] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(tetris);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        board.setLocation(10, 10);
        tetris.add(board);
        window.setVisible(true);
        thread.start();
    }

    @Override
    public void run() {
        long wait, startTime, loopTime;
        while (start){
            startTime = System.nanoTime();
            board.run();
            loopTime = startTime - System.nanoTime();
            wait = scr - loopTime / 1000000;
            if (wait<=0) wait = 3;
            try {thread.sleep(scr);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(scr+" > "+wait);
        }

    }
}
