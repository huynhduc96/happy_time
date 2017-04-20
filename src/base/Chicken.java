package base;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.media.Media;

import javax.sound.sampled.*;

/**
 * Created by huynh on 09-Apr-17.
 */
public class Chicken extends Animal {

    public Chicken(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr, double health, double sick) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick);
//        keu();
    }

    @Override
    public void addToLayer() {
        Animation animation = new SpriteAnimation(imageView
                , Duration.millis(1000.0),
                12, 4,
                0, 0,
                64, 64
        );

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        this.layer.getChildren().add(this.imageView);
    }

    @Override
    public void checkRemovability() {
        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }

    public void keu() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
//        System.out.println("Working Directory = " +
//                System.getProperty("user.dir"));
        String musicFile = "src/res/sounds2/chicken_voice.mp3";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(500);
        mediaPlayer.play();

    }


}
