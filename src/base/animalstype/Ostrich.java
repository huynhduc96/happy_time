package base.animalstype;

import base.Settings;
import base.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by anhtu on 5/6/2017.
 */
public class Ostrich extends Animal {
    public int timeDie = 6000;
    public Ostrich(Pane layer, int type, double x, double y, double r, double dx, double dy,
                   double dr, double health, double sick, int step) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick, step);
    }

    @Override
    public void addToLayer() {

        if (sick >= timeDie) {
            Animation animation = new SpriteAnimation(imageView
                    , Duration.millis(5000.0),
                    16, 3,
                    0, 0,
                    150, 150
            );
            animation.play();
            // animation.stop();
            this.layer.getChildren().add(1, this.imageView);
            //animation.pause();
        }
        else {
            Animation animation = new SpriteAnimation(imageView
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
    }

    @Override
    public void stopAnimationWhenDied() {

    }

    @Override
    public void checkRemovability() {
        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }
}
