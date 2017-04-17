package base;

import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by huynh on 17-Apr-17.
 */
public class Cow extends Animal {
    public Cow(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr, double health, double sick) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick);
    }

    @Override
    public void addToLayer() {
        Animation animation = new SpriteAnimation(imageView
                , Duration.millis(1000.0),
                17, 4,
                0, 0,
                150,150
        );

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        this.layer.getChildren().add(this.imageView);
    }

    @Override
    public void checkRemovability() {

    }
}
