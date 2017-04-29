package base.grassstyle;

import base.Settings;
import base.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by hoang on 4/29/2017.
 */
public class Can extends Grass {
    public Can(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr, double health, double sick) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick);
    }
    @Override
    public void addToLayer() {
        Animation animation = new SpriteAnimation(imageView
                , Duration.millis(5000.0),
                16, 4,
                0, 0,
                48, 48
        );

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
    
