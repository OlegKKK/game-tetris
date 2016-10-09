package game.tetris;

public class Blocks {

    public boolean [][] tab = new boolean[4][4];
    private boolean [][] tabE = new boolean[4][4];
    byte akBlocks;

    Blocks() {
        setBlocks((byte) 0);
    }

    public void setBlocks(byte k) {
        akBlocks = k;
        for (byte x=0; x<4; x++)
        for (byte y=0; y<4; y++)
        tab[y][x] = Figures.FIGURES[akBlocks][x][y];
    }

    public void rotation() {
        for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tabE[x][y] = tab[x][y];
        for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tab[y][3-x] = tabE[x][y];
    }

    public void backRotation() { for (byte x=0; x<4; x++) for (byte y=0; y<4; y++) tab[x][y] = tabE[x][y]; }

    public void turn() {

    }
}