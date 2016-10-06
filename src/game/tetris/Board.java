package game.tetris;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends ACanvas implements MouseListener{

    final static short SIZE = 25;
    final static short WIDTH = SIZE * 10;
    final static short HEIGHT = SIZE * 20;
    final Color [] COLORS = {Color.GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.WHITE};

    byte [][] tab = new byte[10][20];
    Random color = new Random();

    Board() {
        super(WIDTH, HEIGHT);addMouseListener(this);
        tab [1][1]=1;

    }

    @Override
    public void drawImage() {
        cmpBoard();
        printBoard();
    }

    private void printBoard() {
        for (byte x=0; x<10; x++)
            for (byte y=0; y<20; y++) {
                graphics.setColor(COLORS[tab[x] [y]]);
                graphics.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
                graphics.setColor(Color.BLACK);
                if (tab[x][y]>0) graphics.drawRect(x*SIZE, y*SIZE, SIZE-1, SIZE-1);


            }
    }

    private boolean isLine(byte y) {
    for (byte x=0; x<10; x++) {if (tab[x][y]==0) return false;}
    return true;
}
    private void setLine(byte y) {
        for (byte x=0; x<10; x++) tab[x][y]=8;
    }
    private void downBoard(byte y){


    }

    private void cmpBoard() {
        for (byte y=0; y<20; y++){

            if (isLine(y)) setLine(y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tab[e.getX()/SIZE] [e.getY()/SIZE] = 1;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
