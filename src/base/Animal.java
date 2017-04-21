package base;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by huynh on 06-Apr-17.
 */
public abstract class Animal {

    ImageView imageView;

    Pane layer;
    double x;
    double y;
    double r;
    int type;
    int direction;
    //CHICKEN =1
    //COW =2
    //PIG =3
    double dx;
    double dy;
    double dr;

    double health;
    double sick;

    boolean removable = false;
    boolean isScale = false;

    double w;
    double h;

    int death = 0;
    int eat = 0;

    boolean canMove = true;
    ArrayList<String> nameImage = new ArrayList<>();
    ArrayList<Image> arrImage = new ArrayList<>();
    ClassLoader classLoader = this.getClass().getClassLoader();

    public Animal(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr,
                  double health, double sick) {
        this.layer = layer;
        this.type = type;
        String typeAnimal = null;
        if (type == Settings.CHIKEN) {
            typeAnimal = "chicken";
        } else if (type == Settings.COW) {
            typeAnimal = "cow";
        } else if (type == Settings.PIG) {
            typeAnimal = "pig";
        }
        getNameImage(typeAnimal);
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.health = health;
        this.sick = sick;

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
    }
    private String getName(){
        String typeAnimal = null;
        if (type == Settings.CHIKEN) {
            typeAnimal = "chicken";
        } else if (type == Settings.COW) {
            typeAnimal = "cow";
        } else if (type == Settings.PIG) {
            typeAnimal = "pig";
        }
        return typeAnimal;
    }
    public void getSound(String model){
        if(!(model.equals("hungry") || model.equals("die")
                || model.equals("flyout") || model.equals("voice"))) return;
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
//        ANIMAL_EAT = 8;
//        ANIMAL_DEATH = 9;


        String tmg0 = "res/pets/" + s + "/up.dds.png";
        String tmg1 = "res/pets/" + s + "/down.dds.png";
        String tmg2 = "res/pets/" + s + "/left.dds.png";
        String tmg3 = "res/pets/" + s + "/left.dds.png";
        String tmg4 = "res/pets/" + s + "/up_left.dds.png";
        String tmg5 = "res/pets/" + s + "/down_left.dds.png";
        String tmg6 = "res/pets/" + s + "/up_left.dds.png";
        String tmg7 = "res/pets/" + s + "/down_left.dds.png";
        String tmg8 = "res/pets/" + s + "/eat.dds.png";
        String tmg9 = "res/pets/" + s + "/death.dds.png";
        nameImage.add(tmg0);
        nameImage.add(tmg1);
        nameImage.add(tmg2);
        nameImage.add(tmg3);
        nameImage.add(tmg4);
        nameImage.add(tmg5);
        nameImage.add(tmg6);
        nameImage.add(tmg7);
        nameImage.add(tmg8);
        nameImage.add(tmg9);

        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg0))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg1))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg2))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg3))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg4))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg5))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg6))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg7))));
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg8))));
    }

    public abstract void addToLayer();

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    //-----------------------------------------------------------------------------------------------------
    // viet them code ham move
    public void move() {

        if (!canMove)
            return;

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

    //----------------------------------------------------------------------------
    // viet them ham xu ly vung khong gian di chuyen cua animal
    public void changeDirection() {
        // ham se thay  doi lai gia tri cua bien direction
        // khi đến vị trí cuối map thì di chuyển ra phía khác
//        if ( (Double.compare( getY(), 450) > 0) || (Double.compare( getY(), 120) < 0) ||
//                (Double.compare( getX(), 600) > 0) || (Double.compare( getX(), 100) < 0) )  {
//            setRemovable(true);
//            Random a = new Random();
//            direction = a.nextInt(7);
//        }
        if (Double.compare(getX(), 600) > 0) {
            while (direction != 2 && direction != 4 && direction != 5) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }

        if (Double.compare(getX(), 100) < 0) {
            while (direction != 3 && direction != 6 && direction != 7) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }

        if (Double.compare(getY(), 400) > 0) {
            while (direction != 0 && direction != 4 && direction != 6) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }

        if (Double.compare(getY(), 120) < 0) {
            while (direction != 1 && direction != 5 && direction != 7) {
                Random a = new Random();
                direction = a.nextInt(7);
            }
        }
    }


    public boolean isAlive() {
        return Double.compare(health, 0) > 0;
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

        if (eat == 1) {
            imageView.setImage(arrImage.get(Settings.ANIMAL_EAT));
            eat = 0;
        }

        if (death == 1) {
            imageView.setImage(arrImage.get(Settings.ANIMAL_DEATH));
            death = 0;
        }

        imageView.relocate(x, y);

        imageView.setRotate(r);
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
}

