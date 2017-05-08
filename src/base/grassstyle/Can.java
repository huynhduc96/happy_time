package base.grassstyle;

import base.Settings;
import base.SpriteAnimation;
import base.jsonObject.DataPlayer;
import base.productStyle.Product;
import javafx.animation.Animation;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by hoang on 4/29/2017.
 */
public class Can extends Grass {
    public int timeDieCan;
    public Can(Pane layer, int type, double x, double y, int step, DataPlayer data,
               int locate) {
        super(layer, type, x, y, step, data, locate);
        timeDieCan = 360;
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
        this.layer.getChildren().add(1, this.imageView);
    }

    @Override
    public void checkRemovability() {
        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }

    public void dieToBorn(){
        layer.getChildren().remove(imageView);
        Product tmp = new Product(layer, "grass_product", x, y);
        tmp.setOnClick(data);
    }
}
    
