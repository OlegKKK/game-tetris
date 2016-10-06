package game.tetris;

import java.awt.*;

public class Board extends ACanvas{

    final static short SIZE = 25;
    final static short WIDTH = SIZE * 10;
    final static short HEIGHT = SIZE * 20;
    final Color [] COLORS = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.WHITE};

    Board() {
        super(WIDTH, HEIGHT);

    }

    @Override
    public void drawImage() {
        graphics.drawString("Tetris", 10, 10);
    }

}
