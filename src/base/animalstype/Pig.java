package base.animalstype;

import base.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by huynh on 17-Apr-17.
 */
public class Pig extends Animal {

    public Pig(Pane layer, int type, double x, double y, double r, double dx,

               double dy, double dr, double health, double sick, int step) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick, step);
        this.timeDie = 4500;
        this.hasDied = 0;
    }

    @Override
    public void addToLayer() {

            animation = new SpriteAnimation(imageView
                    , Duration.millis(5000.0),
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

    }
}
