package base.pets;

import base.SpriteAnimation;
import base.jsonObject.DataPlayer;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by huynh on 07-May-17.
 */
public class Dog extends Pet {
    public Dog(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr, int step, DataPlayer data) {
        super(layer, type, x, y, r, dx, dy, dr, step, data);
    }

    @Override
    public void addToLayer() {
        animation = new SpriteAnimation(imageView
                , Duration.millis(1000.0),
                16, 3,
                0, 0,
                75, 75
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        this.layer.getChildren().add(this.imageView);
    }

    @Override
    public void checkRemovability() {

    }
}