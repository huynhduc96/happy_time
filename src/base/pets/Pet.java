package base.pets;

import base.Settings;
import base.jsonObject.DataPlayer;
import base.productStyle.Product;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Buff;

import java.io.File;
import java.util.*;

/**
 * Created by huynh on 07-May-17.
 */
public abstract class Pet {
    private String info;
    ImageView imageView;
    final int time = 200;

    // check time for animal state
    Timer _t;
    int _count = time;

    Pane layer;
    double x;
    double y;
    double r;
    int type;
    int direction;
    Text t;
    double dx;
    double dy;
    double dr;
    private long count = 0;
    long end;

    int step;
    private static final int span = 1000;

    boolean removable = false;
    boolean isScale = false;
    DataPlayer data;
    double w;
    double h;
    private static List<Product> prods = new ArrayList<>();
    int death = 0;
    int eat = 0;
    int typeSent;
    public int timeDie;
    public double hasDied;
    public Animation animation;
    boolean canMove = true;
    public Buff buff;
    ArrayList<String> nameImage = new ArrayList<>();
    ArrayList<Image> arrImage = new ArrayList<>();
    ClassLoader classLoader = this.getClass().getClassLoader();

    public Pet(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr,
             int step, DataPlayer data) {
        this.data = data;
        this.layer = layer;
        this.type = type;
        String typeAnimal = null;
        if (type == Settings.PANDA) {
            typeAnimal = "panda";
        } else if (type == Settings.DOG) {
            typeAnimal = "dog";
        } else if (type == Settings.CAT) {
            typeAnimal = "cat";
        }
        getNameImage(typeAnimal);
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.step = step;
        // random phuong huong khi vao
        Random rand = new Random();
        int n = rand.nextInt(7);
        this.direction = n;
        this.imageView = new ImageView(arrImage.get(n));
        this.imageView.setVisible(false);
        this.imageView.relocate(x, y);

        this.imageView.setRotate(r);
        this.w = arrImage.get(n).getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = arrImage.get(n).getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();
        t = new Text("dmm");
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        t.setFill(Color.WHITESMOKE);
        layer.getChildren().add(t);
        t.setVisible(false);
    }

    private String getName() {
        String typeAnimal = null;
        if (type == Settings.PANDA) {
            typeAnimal = "panda";
        } else if (type == Settings.DOG) {
            typeAnimal = "dog";
        } else if (type == Settings.CAT) {
            typeAnimal = "cat";
        }
        return typeAnimal;
    }

    public void addBuffListener(Buff buff) {
        this.buff = buff;
    }

    public void getSound(String model) {
        String file_name = this.getName() + "_" + model + ".mp3";
        String musicFile = "src/res/sounds2/" + file_name;     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5); // volumn
        mediaPlayer.setCycleCount(1); // times
        mediaPlayer.play();

    }

    void getNameImage(String s) {

//        ANIMAL_UP = 0;
//        ANIMAL_DOWN = 1;
//        ANIMAL_LEFT = 2;
//        ANIMAL_RIGHT = 3;
//        ANIMAL_UP_LEFT = 4;
//        ANIMAL_DOWN_LEFT = 5;
//        ANIMAL_UP_RIGHT = 6;
//        ANIMAL_DOWN_RIGHT = 7;



        String tmg0 = "res/need/" + s + "/up.dds.png";
        String tmg1 = "res/need/" + s + "/down.dds.png";
        String tmg2 = "res/need/" + s + "/left.dds.png";
        String tmg3 = "res/need/" + s + "/left.dds.png";
        String tmg4 = "res/need/" + s + "/up_left.dds.png";
        String tmg5 = "res/need/" + s + "/down_left.dds.png";
        String tmg6 = "res/need/" + s + "/up_left.dds.png";
        String tmg7 = "res/need/" + s + "/down_left.dds.png";

        nameImage.add(tmg0);
        nameImage.add(tmg1);
        nameImage.add(tmg2);
        nameImage.add(tmg3);
        nameImage.add(tmg4);
        nameImage.add(tmg5);
        nameImage.add(tmg6);
        nameImage.add(tmg7);



        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg0))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg1))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg2))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg3))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg4))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg5))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg6))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg7))));


    }

    public abstract void addToLayer();

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public void move() {
        count++;

            if (direction == Settings.ANIMAL_DOWN) {
                x += 0;
                y += Settings.ANIMAL_SPEED;
                r += 0;
            }

            if (direction == Settings.ANIMAL_UP) {
                x += 0;
                y -= Settings.ANIMAL_SPEED;
                r += 0;
            }

            if (direction == Settings.ANIMAL_LEFT) {
                x -= Settings.ANIMAL_SPEED;
                y += 0;
                r += 0;
            }

            if (direction == Settings.ANIMAL_RIGHT) {
                x += Settings.ANIMAL_SPEED;
                y += 0;
                r += 0;
            }

            if (direction == Settings.ANIMAL_UP_LEFT) {
                x -= Settings.ANIMAL_SPEED;
                y -= Settings.ANIMAL_SPEED;
                r += 0;
            }

            if (direction == Settings.ANIMAL_UP_RIGHT) {
                x += Settings.ANIMAL_SPEED;
                y -= Settings.ANIMAL_SPEED;
                r += 0;
            }

            if (direction == Settings.ANIMAL_DOWN_LEFT) {
                x -= Settings.ANIMAL_SPEED;
                y += Settings.ANIMAL_SPEED;
                r += 0;
            }

            if (direction == Settings.ANIMAL_DOWN_RIGHT) {
                x += Settings.ANIMAL_SPEED;
                y += Settings.ANIMAL_SPEED;
                r += 0;
            }
            changeDirection();
    }


    public void changeDirection() {
        if (Double.compare(getX(), 525) > 0) {
            while (direction != 2 && direction != 4 && direction != 5) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }

        if (Double.compare(getX(), 120) < 0) {
            while (direction != 3 && direction != 6 && direction != 7) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }

        if (Double.compare(getY(), 375) > 0) {
            while (direction != 0 && direction != 4 && direction != 6) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }

        if (Double.compare(getY(), 140) < 0) {
            while (direction != 1 && direction != 5 && direction != 7) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }
    }


    public ImageView getView() {
        return imageView;
    }

    public void updateUI() {
//        ANIMAL_UP = 0;
//        ANIMAL_DOWN = 1;
//        ANIMAL_LEFT = 2;
//        ANIMAL_RIGHT = 3;
//        ANIMAL_UP_LEFT = 4;
//        ANIMAL_DOWN_LEFT = 5;
//        ANIMAL_UP_RIGHT = 6;
//        ANIMAL_DOWN_RIGHT = 7;
//        ANIMAL_EAT = 8;
//        ANIMAL_DEATH = 9;

        if (death == 0) {
            switch (direction) {
                case 0: // ANIMAL_UP = 0;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_UP));
                    if (isScale) {
                        isScale = false;
                        imageView.setScaleX(-1);
                    }
                    break;
                case 1: // ANIMAL_DOWN = 1;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_DOWN));
                    if (isScale) {
                        isScale = false;
                        imageView.setScaleX(1);
                    }
                    break;
                case 2: // ANIMAL_LEFT = 2;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_LEFT));
                    if (isScale) {
                        isScale = false;
                        imageView.setScaleX(1);
                    }
                    break;
                case 3: // ANIMAL_RIGHT = 3;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_RIGHT));
                    isScale = true;
                    imageView.setScaleX(-1);
                    break;
                case 4: //ANIMAL_UP_LEFT = 4;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_UP_LEFT));
                    if (isScale) {
                        isScale = false;
                        imageView.setScaleX(1);
                    }
                    break;
                case 5: // ANIMAL_DOWN_LEFT = 5;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_DOWN_LEFT));
                    if (isScale) {
                        isScale = false;
                        imageView.setScaleX(1);
                    }
                    break;
                case 6: // ANIMAL_UP_RIGHT = 6;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_UP_RIGHT));
                    imageView.setScaleX(-1);
                    isScale = true;
                    break;
                case 7: //  ANIMAL_DOWN_RIGHT = 7;
                    imageView.setImage(arrImage.get(Settings.ANIMAL_DOWN_RIGHT));
                    imageView.setScaleX(-1);
                    isScale = true;
                    break;
            }
        }


        imageView.relocate(x, y);
        t.relocate(x, y - 30);
        imageView.setRotate(r);
    }


    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }

    public int getTimeDie() {
        return timeDie;
    }

    public void setTimeDie(int timeDie) {
        this.timeDie = timeDie;
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

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
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

    public boolean changeHungryStateOfAnimals() {
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
        if (changeHungryStateOfAnimals()) {
            _count += time;
        }
    }


    public void setOnDrag() {

        this.imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSound("voice");
                info = "vòng đời:" + step;
                t.setText(info);
                t.setVisible(true);
            }
        });
        this.imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t.setVisible(false);
            }
        });

    }

}
