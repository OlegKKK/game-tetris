package game.tetris;

import java.awt.*;
import java.util.Random;

@SuppressWarnings("serial")
public class Next extends ACanvas{

    byte blocks;
    private Random rand = new Random();

    Next() {
        super((byte) 100, (byte)100);
        randomBlocks();
    }

    @Override
    public void drawImage() { printBlocks();}

    public void randomBlocks() { blocks = (byte) (rand.nextInt(6)+1);}

    private void printCube(byte x, byte y, byte k) {
        graphics.setColor(Figures.COLORS[k]);
        graphics.fillRect(x*Figures.SIZE, y*Figures.SIZE, Figures.SIZE, Figures.SIZE);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x*Figures.SIZE, y*Figures.SIZE, Figures.SIZE-1, Figures.SIZE-1);
    }

    private void printBlocks() {
        graphics.setColor(Figures.COLORS[0]);
        graphics.fillRect(0, 0, 4*Figures.SIZE, 4*Figures.SIZE);
        for (byte tx=0; tx<4; tx++);
        for (byte ty=0; ty<4; ty++);
        if (Figures.FIGURES[blocks][ty][tx]) printCube(tx, ty, (byte) (blocks+1));
    }
}