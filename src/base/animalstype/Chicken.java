package base.animalstype;

import base.Settings;
import base.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by huynh on 09-Apr-17.
 */
public class Chicken extends Animal {


    public Chicken(Pane layer, int type, double x, double y, double r, double dx, double dy,
                   double dr, double health, double sick, int step) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick, step);
        this.timeDie = 100;
    }

    @Override
    public void addToLayer() {

         animation = new SpriteAnimation(imageView
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
}
