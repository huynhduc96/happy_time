package base;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Created by huynh on 09-Apr-17.
 */
public class Chicken extends Animal {

    public Chicken(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr, double health, double sick) {
        super(layer, type, x, y, r, dx, dy, dr, health, sick);
    }

    @Override
    public void checkRemovability() {
        if( Double.compare( getY(), Settings.SCENE_HEIGHT) > 0) {
            setRemovable(true);
        }
    }


}
