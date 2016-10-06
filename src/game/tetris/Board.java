package game.tetris;

public class Board extends ACanvas{

    final static short SIZE = 25;
    final static short WIDTH = SIZE * 10;
    final static short HEIGHT = SIZE * 20;

    Board() {
        super(WIDTH, HEIGHT);

    }

    @Override
    public void drawImage() {
        graphics.drawString("Tetris", 10, 10);
    }

}
