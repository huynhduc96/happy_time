package base.grassstyle;

import base.Settings;
import base.jsonObject.DataPlayer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by hoang on 4/29/2017.
 */
public abstract class Grass {
    private String info;
    protected ImageView imageView;

    // check time for Grass state

    protected Pane layer;
    protected double x;
    protected double y;
    private double r;

    private Text t;

    int step;

    boolean removable = false;


    double w;
    double h;
    private int locate;
    ArrayList<String> nameImage = new ArrayList<>();
    ArrayList<Image> arrImage = new ArrayList<>();
    ClassLoader classLoader = this.getClass().getClassLoader();
    DataPlayer data;
    public Grass(Pane layer, int type, double x, double y, int step,
                 DataPlayer data, int locate) {
        this.locate = locate;
        this.data = data;
        this.layer = layer;
        getNameImage();
        this.x = x;
        this.y = y;
        this.r = r;

        this.step = step;


        this.imageView = new ImageView(arrImage.get(0));
        this.imageView.setVisible(false);
        this.imageView.relocate(x, y);

        this.imageView.setRotate(r);
        this.w = arrImage.get(0).getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = arrImage.get(0).getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();
        t = new Text("dmm");
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        t.setFill(Color.WHITE);
        layer.getChildren().add(t);
        t.setVisible(false);
    }


    public int getLocate() {
        return locate;
    }

    void getNameImage() {

        String tmg0 = "res/grass.dds.png";

        nameImage.add(tmg0);

        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg0))));

    }

    public abstract void addToLayer();

    public abstract void checkRemovability();

    public ImageView getImageView() {
        return imageView;
    }

    public void updateUI() {
        imageView.relocate(x, y);
        t.relocate(x, y - 30);
        imageView.setRotate(r);
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

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }


}
