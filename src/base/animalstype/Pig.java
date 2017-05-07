package base.animalstype;

import base.Settings;
import base.SpriteAnimation;
import base.jsonObject.DataPlayer;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.xml.crypto.Data;

/**
 * Created by huynh on 17-Apr-17.
 */
public class Pig extends Animal {

    public Pig(Pane layer, int type, double x, double y, double r, double dx,

               double dy, double dr, double health, double sick, int step, DataPlayer data) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick, step, data);
        this.timeDie = 4500;
        this.hasDied = 0;
        this.diedByStep = 0;
        this.diedByHeight = 0;
    }

    @Override
    public void addToLayer() {

            animation = new SpriteAnimation(imageView
                    , Duration.millis(1000.0),
                    12, 4,
                    0, 0,
                    112, 112

            );
            animation.setCycleCount(Animation.INDEFINITE);
            animation.play();
            // animation.stop();
            this.layer.getChildren().add(1, this.imageView);
            //animation.pause();

    }

    @Override
    public void checkRemovability() {
        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }
}
