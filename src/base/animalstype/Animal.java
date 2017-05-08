package base.animalstype;

import base.Settings;
import javafx.animation.Animation;
import base.jsonObject.DataPlayer;
import base.productStyle.Product;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

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


/**
 * Created by huynh on 06-Apr-17.
 */
public abstract class Animal {
    private String info;
    protected  ImageView imageView;
    final int time = 200;

    // check time for animal state
    Timer _t;
    int _count = time;

    protected Pane layer;
    protected double x;
    protected double y;
    protected double r;
    private int type;
    private int direction;
    Text t;
    //CHICKEN =1
    //COW =2
    //PIG =3
    private String typeProduct;
    private double dx;
    private double dy;
    private double dr;
    private long count = 0;
    private double health;
    private double sick;
    private int step;
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
    public int countHealthDie = 0;
    public int timeDie;
    public int stepDie = 0;
    public double hasDied;

    public int getDiedByStep() {
        return diedByStep;
    }

    public void setDiedByStep(int diedByStep) {
        this.diedByStep = diedByStep;
    }

    public int diedByStep;

    public int getDiedByHeight() {
        return diedByHeight;
    }

    public void setDiedByHeight(int diedByHeight) {
        this.diedByHeight = diedByHeight;
    }

    public int diedByHeight;
    public Animation animation;
    public Buff buff;
    ArrayList<String> nameImage = new ArrayList<>();
    ArrayList<Image> arrImage = new ArrayList<>();
    ClassLoader classLoader = this.getClass().getClassLoader();

    public Animal(Pane layer, int type, double x, double y, double r, double dx, double dy, double dr,
                  double health, double sick, int step, DataPlayer data) {
        this.data = data;
        this.layer = layer;
        this.type = type;
        String typeAnimal = null;
        if (type == Settings.CHIKEN) {
            typeAnimal = "chicken";
            typeProduct = "egg";
        } else if (type == Settings.COW) {
            typeAnimal = "cow";
            typeProduct = "milk";
        } else if (type == Settings.PIG) {
            typeAnimal = "pig";
            typeProduct = "meat";
        } else if (type == Settings.OSTRICH) {
            typeAnimal = "ostrich";
            typeProduct = "feather";
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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    private String getName() {
        String typeAnimal = null;
        if (type == Settings.CHIKEN) {
            typeAnimal = "chicken";
        } else if (type == Settings.COW) {
            typeAnimal = "cow";
        } else if (type == Settings.PIG) {
            typeAnimal = "pig";
        } else if (type == Settings.OSTRICH) {
            typeAnimal = "ostrich";
        }
        return typeAnimal;
    }

    public void addBuffListener(Buff buff) {
        this.buff = buff;
    }

    public void getSound(String model) {
        if (!(model.equals("hungry") || model.equals("die")
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
        arrImage.add(new Image(String.valueOf(classLoader.getResource(tmg9))));
    }

    public abstract void addToLayer();

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    void changespeed(double speed) {
        if (direction == Settings.ANIMAL_DOWN) {
            x += 0;
            y += speed;
            r += 0;
        }

        if (direction == Settings.ANIMAL_UP) {
            x += 0;
            y -= speed;
            r += 0;
        }

        if (direction == Settings.ANIMAL_LEFT) {
            x -= speed;
            y += 0;
            r += 0;
        }

        if (direction == Settings.ANIMAL_RIGHT) {
            x += speed;
            y += 0;
            r += 0;
        }

        if (direction == Settings.ANIMAL_UP_LEFT) {
            x -= speed;
            y -= speed;
            r += 0;
        }

        if (direction == Settings.ANIMAL_UP_RIGHT) {
            x += speed;
            y -= speed;
            r += 0;
        }

        if (direction == Settings.ANIMAL_DOWN_LEFT) {
            x -= speed;
            y += speed;
            r += 0;
        }

        if (direction == Settings.ANIMAL_DOWN_RIGHT) {
            x += speed;
            y += speed;
            r += 0;
        }
        changeDirection();
    }

    public void move() {
        count++;
        if (sick < timeDie && health > 0 && step < 3) {
            changespeed(Settings.ANIMAL_SPEED);
        } else {
            changespeed(0);
            death++;
        }


        if (type != 3) {
            if ((count % (type * span)) == 0) {
                Product tmp = new Product(layer, typeProduct, x, y);
                prods.add(tmp);

                tmp.setOnClick(data, prods);
            }
        }
        if (count == 50000) {
            count = 1;
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

        if (eat == 1) {
            imageView.setImage(arrImage.get(Settings.ANIMAL_EAT));
            eat = 0;
        }

        if (death == 1) {
            System.out.println("========================================================");
            imageView.setImage(arrImage.get(Settings.ANIMAL_DEATH));
            animation.setDelay(Duration.millis(9000.0));
        }

        if (death > 20) {
            animation.stop();
        }

        imageView.relocate(x, y);
        t.relocate(x, y - 30);
        imageView.setRotate(r);
    }


    /**
     * Set flag that the sprite can be removed from the UI.
     */
    public void remove() {
        System.out.println(step);
        setRemovable(true);
        if (type == 3 && step == 3) {
            Product tmp = new Product(layer, typeProduct, x, y);
            prods.add(tmp);
            tmp.setOnClick(data, prods);
        }
    }

    /**
     * Set flag that the sprite can't move anymore.
     */

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


    public void setOnDrag() {

        this.imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSound("voice");
                info = "Không đói :" + (int) health + "\n" + "Ốm :" + (int)(sick * 100 / timeDie) +
                        "\nVòng đời:" + step;
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
        this.imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                typeSent = buff.buffOk(1);
                System.out.println("=====> type sent - tu Game " + typeSent);
                if (typeSent > 0) {
                    switch (typeSent) {
                        case 1:
                            if (health > 0) {
                                health = health + 10;
                                if (health > 100) {
                                    health = 100;
                                }
                            }

                            break;
                        case 2:
                            if (health > 0) {
                                health = health + 20;
                                if (health > 100) {
                                    health = 100;
                                }
                            }

                            break;
                        case 3:
                            if (sick < timeDie) {
                                sick = sick - 3000;
                                if (sick < 0) {
                                    sick = 0;
                                }
                            }

                            break;
                        case 4:
                            if (sick < timeDie) {
                                sick = sick - 5000;
                                if (sick < 0) {
                                    sick = 0;
                                }
                            }

                            break;
                    }
                }
            }
        });
    }

}