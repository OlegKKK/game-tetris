package game.tetris;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends ACanvas implements MouseListener, KeyListener{

    final static short SIZE = 25;
    final static short WIDTH = SIZE * 10;
    final static short HEIGHT = SIZE * 20;
    final Color [] COLORS = {Color.GRAY, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.WHITE};

    byte [][] tab = new byte[10][20];
    Random color = new Random();

    Blocks blocks = new Blocks();
    byte blocksX, blocksY;
    boolean kLeft, kRight, kUp, kDown;


    Board() {
        super(WIDTH, HEIGHT);addMouseListener(this); addKeyListener(this);

    }

    @Override
    public void drawImage() {
        key();
        cmpBoard();
        printBoard();
        printBlocks(blocksX, blocksY);

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

    private void printCube(byte x, byte y, byte k) {
        graphics.setColor(COLORS[k]);
        graphics.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x*SIZE, y*SIZE, SIZE-1, SIZE-1);
    }

    private boolean isLine(byte y) {
    for (byte x=0; x<10; x++) {if (tab[x][y]==0) return false;}
    return true;
    }
    private void setLine(byte y) {
        for (byte x=0; x<10; x++) tab[x][y]=8;
    }
    private void downBoard(byte y){

    for (byte ty=y; ty>0; ty--)
        for (byte x=0; x<10; x++) tab[x][ty]=tab[x][ty-1];
    for (byte x=0; x<10; x++) tab[x][0]=0;
    }

    private void cmpBoard() {
        for (byte y=0; y<20; y++){
            if (tab[0][y]==8) downBoard(y);
            if (isLine(y)) setLine(y);
        }
    }

    private void printBlocks(byte x, byte y) {
        for (byte tx=0; tx<4; tx++)
            for (byte ty=0; ty<4; ty++)
                if (blocks.tab[tx][ty]) printCube((byte)(x+tx), (byte)(y+ty), (byte)(blocks.akBlocks+1));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tab[e.getX()/SIZE] [e.getY()/SIZE] = 2;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @SuppressWarnings("static-acces")
    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k==e.VK_UP) kUp = true;
        if (k==e.VK_DOWN) kDown = true;
        if (k==e.VK_LEFT) kLeft = true;
        if (k==e.VK_RIGHT) kRight = true;


    }
    @SuppressWarnings("static-acces")
    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k==e.VK_UP) kUp = false;
        if (k==e.VK_DOWN) kDown = false;
        if (k==e.VK_LEFT) kLeft = false;
        if (k==e.VK_RIGHT) kRight = false;
    }
    private boolean isLineBlocks(byte x) {
        for (byte y=0; y<4; y++) {if (blocks.tab [x][y]) return false;}
        return true;
    }
    private boolean isBlocksBoard(byte x, byte y) {
        for (byte xx=0; xx<4; xx++)
        for (byte yy=0; yy<4; yy++)
            if (blocks.tab[xx][yy] && tab [xx+x] [yy+y]>0) return false;
        return true;
    }

    private boolean moveLeft() {
        if (blocksX==0 && !isLineBlocks((byte) 0)) return false;
        else if (blocksX==-1 && !isLineBlocks((byte) 1)) return false;
        else if (blocksX==-2 && !isLineBlocks((byte) 2)) return false;
        if(!isBlocksBoard((byte) (blocksX-1), blocksY)) return false;
        return true;
    }

    private boolean moveRight() {
        if (blocksX==6 && !isLineBlocks((byte) 3)) return false;
        else if (blocksX==7 && !isLineBlocks((byte) 2)) return false;
        else if (blocksX==8 && !isLineBlocks((byte) 1)) return false;
        if(!isBlocksBoard((byte) (blocksX+1), blocksY)) return false;

        return true;
    }
    private void key(){
        if (kUp) blocks.rotation();
        if (kLeft && moveLeft())  blocksX--;
        if (kRight && moveRight()) blocksX++;
    }
}
