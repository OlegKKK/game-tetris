package game.tetris;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    public boolean isPlay = false;
    private AudioClip clip;

    Sound(String nameFile) {
        clip = Applet.newAudioClip(Sound.class.getResource(nameFile));
    }

    public void play() {
        isPlay = true;
        new Thread() {public void run(){clip.play();}}.start();
    }
    public void stop() {
        isPlay = false;
        clip.stop();
    }
    public void loop() {
        isPlay = true;
        clip.loop();
    }
}