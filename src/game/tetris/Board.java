package game.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

@SuppressWarnings("serial")
public class Board extends ACanvas implements MouseListener, KeyListener{

    final static short WID = Figures.SIZE * 10;
    final static short HEI = Figures.SIZE * 20;

    byte[][] tab = new byte[12][22];
    Random rand = new Random();

    Blocks blocks = new Blocks();
    byte blocksX, blocksY;

    boolean kLeft, kRight, kUp, kDown;

    short speed, speedMax;
    boolean speedKey;

    Sound sBlocks, sTurn, sLine;

    boolean gamePlay, pause;
    Color sColors;

    Board() {
        super(WID, HEI);addMouseListener(this);addKeyListener(this);
        sBlocks = new Sound("block.wav");
        sTurn = new Sound("turn.wav");
        sLine = new Sound("line.wav");
        for (byte x=0; x<12; x++) {tab[x][0]=1;tab[x][21]=1;}
        for (byte y=0; y<22; y++) {tab[0][y]=1;tab[11][y]=1;}
        blocksX = 4;
        blocksY = 0;
        speedMax = (short) (21 - Tetris.level);
        gamePlay = false;
        pause = false;
        graphics.setFont(new Font("System", Font.BOLD, 18));
        sColors = Color.WHITE;
    }

    @Override
    public void drawImage() {
        if (gamePlay)
        {
            key();
            cmpBoard();
            printBoard();
            printBoard(blocksX, blocksY);
            if (!pause)
            {
                if (speed<speedMax) speed++;else
                {
                    speed=0;
                    if (isBlocksBoard(blocksX, (byte)(blocksY +1))) blocksY++; else
                    {
                        blocksEnd();
                        newBlocks();
                    }
                }
            }
            else
            {
                graphics.setColor(Color.BLACK);
                graphics.drawString("PAUZA", 91, 496);
                graphics.setColor(sColors);
                graphics.drawString("PAUZA", 90, 495);
            }
        }
        else
        {
            graphics.setColor(Figures.COLORS[0]);
            graphics.fillRect(0, 0, WID, HEI);
            graphics.setColor(Color.BLACK);
            graphics.drawString("TETRIS", 91, 51);
            graphics.drawString("TETRIS", 11, 71);
            graphics.drawString("KLIK to START", 56, 496);
            graphics.setColor(sColors);
            graphics.drawString("TETRIS", 90, 50);
            graphics.drawString("TETRIS", 10, 70);
            graphics.drawString("KLIK to START", 57, 495);

        }
    }

    public void newBlocks()
    {
        sBlocks.play();
        blocksX = 4;
        blocksY = 0;
        speedMax = (short) (20 - Tetris.level);
        if (speedMax<0) speedMax=0;
        blocks.randomBlocks(Tetris.next.blocks);
        Tetris.next.randomBlocks();
        Tetris.points +=blocks.akBlocks;
        Tetris.lPoints.setText(String.valueOf(Tetris.points));
    }

    public void blocksEnd()
    {
        for (byte xx=0; xx<4; xx++)
            for (byte yy=0; yy<4; yy++)
                if (blocks.tab[xx][yy]) tab[xx+ blocksX][yy+ blocksY]=(byte)(blocks.akBlocks+1);
    }

    private void printBoard()
    {
        for (byte x=1; x<11; x++)
            for (byte y=1; y<21; y++)
            {
                graphics.setColor(Figures.COLORS[tab[x][y]]);
                graphics.fillRect((x*Figures.SIZE)-Figures.SIZE, (y*Figures.SIZE)-Figures.SIZE, Figures.SIZE, Figures.SIZE);
                graphics.setColor(Color.BLACK);
                if (tab[x][y] > 0)
                    graphics.drawRect((x * Figures.SIZE) - Figures.SIZE, (y * Figures.SIZE) - Figures.SIZE, Figures.SIZE - 1, Figures.SIZE-1);
            }
    }
    private void printCube(byte x, byte y, byte k)
    {
        graphics.setColor(Figures.COLORS[k]);
        graphics.fillRect((x*Figures.SIZE)-Figures.SIZE, (y*Figures.SIZE)-Figures.SIZE, Figures.SIZE, Figures.SIZE);
        graphics.setColor(Color.BLACK);
        graphics.drawRect((x*Figures.SIZE)-Figures.SIZE, (y*Figures.SIZE)-Figures.SIZE, Figures.SIZE-1, Figures.SIZE-1);
    }

    private boolean isLine(byte y)
    {
        for (byte x=1; x<11; x++) {if (tab[x][y]==0) return false;}
        return true;
    }
    private boolean isFull()
    {
        for (byte x=1; x<11; x++) {if (tab[x][1]!=0) return true;}
        return false;
    }

    private void setLine(byte y)
    {
        sLine.play();
        for (byte x=1; x<11; x++) tab[x][y]=8;
        Tetris.line++;Tetris.lLine.setText(String.valueOf(Tetris.line));
        Tetris.points +=(Tetris.level * 10);
        Tetris.lPoints.setText(String.valueOf(Tetris.points));
        if (Tetris.line ==(Tetris.level *Tetris.level))
        {
            Tetris.level++;
            Tetris.lLevel.setText(String.valueOf(Tetris.level));
            if (speedMax>0) speedMax--;
        }
    }
    private void dropBlocks(byte y)
    {
        for (byte ty=y; ty>1; ty--)
            for (byte x=1; x<11; x++) tab[x][ty]=tab[x][ty-1];
        for (byte x=1; x<11; x++) tab[x][1]=0;
    }

    private void cmpBoard()
    {
        for (byte y=1; y<21; y++)
        {
            if (tab[1][y]==8) dropBlocks(y);
            if (isLine(y)) setLine(y);
        }
        if (isFull())
        {
            gamePlay = false;
            Tetris.level =1;
            Tetris.line =0;
            Tetris.points =0;
            blocksX = 4;
            blocksY = 0;
            speedMax = (short) (21 - Tetris.level);
            for (byte x=1; x<11; x++) for (byte y=1; y<21; y++) tab[x][y]=0;
        }
    }

    public void printBoard(byte x, byte y)
    {
        for (byte tx=0; tx<4; tx++)
            for (byte ty=0; ty<4; ty++)
                if (blocks.tab[tx][ty]) printCube((byte)(tx+x),(byte) (ty+y), (byte) (blocks.akBlocks+1));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gamePlay)
        {
            gamePlay=true;
            Tetris.lPoints.setText(String.valueOf(Tetris.points));
            Tetris.lLine.setText(String.valueOf(Tetris.line));
            Tetris.lLevel.setText(String.valueOf(Tetris.level));
        }else
            pause=!pause;
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @SuppressWarnings("static-access")
    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k==e.VK_UP) kUp = true;
        if (k==e.VK_DOWN) kDown = true;
        if (k==e.VK_LEFT) kLeft = true;
        if (k==e.VK_RIGHT) kRight = true;
    }

    @SuppressWarnings("static-access")
    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k==e.VK_UP) kUp = false;
        if (k==e.VK_DOWN) kDown = false;
        if (k==e.VK_LEFT) kLeft = false;
        if (k==e.VK_RIGHT) kRight = false;
    }

    private boolean isBlocksBoard(byte x, byte y)
    {
        for (byte xx=0; xx<4; xx++)
            for (byte yy=0; yy<4; yy++)
                if (blocks.tab[xx][yy] && tab[xx+x][yy+y]>0) return false;
        return true;
    }

    private boolean moveLeft()
    {
        if (!isBlocksBoard((byte)(blocksX -1), blocksY)) return false;
        return true;
    }
    private boolean moveRight()
    {
        if (!isBlocksBoard((byte)(blocksX +1), blocksY)) return false;
        return true;
    }

    private void key()
    {
        speedKey=!speedKey;
        if (kUp && speedKey) {blocks.rotation();
            sTurn.play();if (!isBlocksBoard(blocksX, blocksY)) blocks.backRotation();}
        if (kLeft && moveLeft()) blocksX--;
        if (kRight && moveRight()) blocksX++;
        if (kDown && speedMax>0) {speedMax=0;Tetris.points +=5;Tetris.lPoints.setText(String.valueOf(Tetris.points));}
    }

}
