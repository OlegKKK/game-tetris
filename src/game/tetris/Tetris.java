package game.tetris;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Tetris extends JPanel implements Runnable {


    static Tetris tetris = new Tetris();
    static JFrame window = new JFrame("Tetris");
    static Thread thread = new Thread(tetris);

    static Board board = new Board();
    static Next next = new Next();

    boolean start = false;
    short scr = 30;

    static int line = 0, points = 0, level = 1;
    static JLabel lPoints, lLine, lLevel, lPoints2, lLine2, lLevel2;
    static Color kPoints = new Color(255,128,255);
    static Color kLine = new Color(255,255,0);
    static Color kLevel = new Color(0,255,255);

    static Font ft1, ft2;

    Tetris()
    {
        super();
        setBackground(new Color(90,90,180));
        setLayout(null);
        start = true;
        ft1 = new Font("System", Font.BOLD, 20);
        ft2 = new Font("System", Font.BOLD, 10);
    }



    public static void main(String[] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(tetris);
        window.setSize(388,555);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        lPoints = new JLabel("0", JLabel.RIGHT);
        lPoints.setForeground(kPoints);
        lPoints.setFont(ft1);
        lPoints.setBounds(270, 300, 100, 20);tetris.add(lPoints);
        lPoints2 = new JLabel("POINTS", JLabel.RIGHT);
        lPoints2.setBounds(270, 290, 100, 10);
        lPoints2.setForeground(kPoints);
        lPoints2.setFont(ft2);tetris.add(lPoints2);

        lLine = new JLabel("0", JLabel.RIGHT);
        lLine.setForeground(kLine);
        lLine.setFont(ft1);
        lLine.setBounds(270, 230, 100, 20);tetris.add(lLine);
        lLine2 = new JLabel("LINE", JLabel.RIGHT);
        lLine2.setBounds(270, 220, 100, 10);
        lLine2.setForeground(kLine);
        lLine2.setFont(ft2);tetris.add(lLine2);

        lLevel = new JLabel("1", JLabel.RIGHT);
        lLevel.setForeground(kLevel);
        lLevel.setFont(ft1);
        lLevel.setBounds(270, 160, 100, 20);tetris.add(lLevel);
        lLevel2 = new JLabel("LEVEL", JLabel.RIGHT);
        lLevel2.setBounds(270, 150, 100, 10);
        lLevel2.setForeground(kLevel);
        lLevel2.setFont(ft2);tetris.add(lLevel2);

        board.setLocation(10, 10);tetris.add(board);
        next.setLocation(270, 10);tetris.add(next);
        window.setVisible(true);
        thread.start();
    }



    @SuppressWarnings("static-access")
    @Override
    public void run() {
        long wait, startTime, loopTime;
        while (start)
        {
            startTime = System.nanoTime();
            board.run();
            next.run();
            loopTime = System.nanoTime() - startTime;
            wait = scr - loopTime / 1000000;
            if (wait<=0) wait = 5;
            try {
                thread.sleep(wait);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }

}
