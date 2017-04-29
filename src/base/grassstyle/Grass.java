package base.grassstyle;

import base.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Created by hoang on 4/29/2017.
 */
public abstract class Grass {
    ImageView imageView;
    final int time = 200;

    // check time for Grass state
    Timer _t;
    int _count=time;

    Pane layer;
    double x;
    double y;
    double r;
    int type;
    int direction;
    //Can =1

    double dx;
    double dy;
    double dr;

    double health;
    double sick;

    boolean removable = false;
    boolean isScale = false;

    double w;
    double h;

    boolean canMove = true;
    ArrayList<String> nameImage = new ArrayList<>();
    ArrayList<Image> arrImage = new ArrayList<>();
    ClassLoader classLoader = this.getClass().getClassLoader();

    public Grass(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr,
                  double health, double sick) {
        this.layer = layer;
        this.type = type;
        String typeGrass = null;
        if (type == Settings.CAN) {
            typeGrass = "Can";
        }
        getNameImage(typeGrass);
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.health = health;
        this.sick = sick;

        int n = 0;
        this.direction = n;
        this.imageView = new ImageView(arrImage.get(n));
        this.imageView.setVisible(false);
        this.imageView.relocate(x, y);

        this.imageView.setRotate(r);
        this.w = arrImage.get(n).getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = arrImage.get(n).getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();
    }
    private String getName(){
        String typeGrass = null;
        if (type == Settings.CAN) {
            typeGrass = "grass";
        }
        return typeGrass;
    }

    void getNameImage(String s) {

        String tmg0 = "res/grass.dds.png";

        nameImage.add(tmg0);

        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg0))));

    }

    public abstract void addToLayer();

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public boolean isAlive() {
        return Double.compare(health, 0) > 0;
    }
    public ImageView getView() {
        return imageView;
    }

    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }

    public void kill() {
        setHealth(0);
    }

    /**
     * Set flag that the sprite can be removed from the UI.
     */
    public void remove() {
        setRemovable(true);
    }

    /**
     * Set flag that the sprite can't move anymore.
     */
    public void stopMovement() {
        this.canMove = false;
    }


    public abstract void checkRemovability();


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDr() {
        return dr;
    }

    public void setDr(double dr) {
        this.dr = dr;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSick() {
        return sick;
    }

    public void setSick(double sick) {
        this.sick = sick;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean changeHungryStateOfGrasss() {
        _t = new Timer();
        _t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (_count > 0) {
                    _count--;
                } else _t.cancel();
            }
        }, 1000, 1000);
        if (_count == 0)
            return true;
        return false;
    }

    public void providedFoodWhenHungry() {
        if (changeHungryStateOfGrasss()) {
            _count += time;
        }
    }

}
