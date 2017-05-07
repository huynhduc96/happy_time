package base.animalstype;

import base.Settings;
import base.SpriteAnimation;
import base.jsonObject.DataPlayer;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by anhtu on 5/6/2017.
 */
public class Ostrich extends Animal {

    public Ostrich(Pane layer, int type, double x, double y, double r, double dx, double dy,
               double dr, double health, double sick, int step, DataPlayer data) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick, step, data);
        this.timeDie = 4000;
        this.hasDied = 0;
        this.diedByStep = 0;
        this.diedByHeight = 0;
    }

    @Override
    public void addToLayer() {

        animation = new SpriteAnimation(imageView
                    , Duration.millis(1000.0),
                    16, 3,
                    0, 0,
                    150, 150
            );
            animation.setCycleCount(Animation.INDEFINITE);
            animation.play();
            this.layer.getChildren().add(this.imageView);
//            animation.pause();
        }




    @Override
    public void checkRemovability() {
        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }
}
