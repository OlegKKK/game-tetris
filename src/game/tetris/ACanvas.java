package game.tetris;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public abstract class ACanvas extends Canvas{

    BufferedImage image;
    Graphics2D graphics;

    ACanvas(short width, short height) {
        super();
        setSize(width, height);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();
    }

    public abstract void drawImage();

    private void onScreen() {

        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    public void run(){

        drawImage();
        onScreen();
    }

}
