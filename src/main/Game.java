package main;

import base.*;
import base.House.WareHouse;
import base.animalstype.*;
import base.grassstyle.Can;
import base.House.Store;
import base.jsonObject.*;

import com.sun.deploy.panel.ITreeNode;
import base.pets.Cat;
import base.pets.Dog;
import base.pets.Panda;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javax.management.openmbean.OpenMBeanConstructorInfo;
import java.util.*;


/**
 * Created by huynh on 07-Apr-17.
 */
public class Game extends Application implements Buff {
    Pane playLayer;
    Scene scene;
    List<Chicken> listChicken = new ArrayList<>();
    List<Pig> listPig = new ArrayList<>();
    List<Cow> listCow = new ArrayList<>();
    List<Ostrich> listOstrich = new ArrayList<>();
    List<Can> listCan = new ArrayList<>();
    List<Panda> listPan = new ArrayList<>();
    List<Dog> listDog = new ArrayList<>();
    List<Cat> listCat = new ArrayList<>();
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();


    Integer[] location = new Integer[10];
    private ImageView money = new ImageView();
    private ImageView backgroud = new ImageView();   //typeSent = 0;
    private ImageView backgroud_l = new ImageView();
    private ImageView backgroud_r = new ImageView();
    private ImageView img_select = new ImageView();
    private ImageView img_food_nol = new ImageView();
    private ImageView img_food_sep = new ImageView();
    private ImageView img_medi_nol = new ImageView();
    private ImageView img_medi_sep = new ImageView();
    private ImageView img_BackMenu = new ImageView();
    private DataPlayer dataPlayer = null;
    private Image image_med_sep;
    private Image image_food_nol;
    private Image image_food_sep;
    private Image image_med_nol;
    private Text txt_money;
    private Text txt_food_nol;
    private Text txt_food_sep;
    private Text txt_medi_nol;
    private Text txt_medi_sep;
    private int isokGame;
    private int typeSent = 0;
    private int page = 0;
//    private HashMap<Can, ListGras>

    static Stage classStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        classStage = primaryStage;


        // make root scene
        Group root = new Group();


        // add Pane play for player
        playLayer = new Pane();


        //get data player from json
        playerData.getdataPlayer();
        for (int i = 0; i < 10; i++) {
            location[i] = 0;
        }

        // lay data player
        dataPlayer = playerData.getPlayer();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                playMusic();
            }
        });

        // add scene
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        addBack();
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(627);
        primaryStage.setMaxWidth(805);
        primaryStage.setResizable(false);
        primaryStage.show();
        setClickStage(primaryStage);

        addFirst();
        Store store = new Store(playLayer, 300, 10, 0);
        WareHouse wareHouse = new WareHouse(playLayer, 620, 305, 0);

        addText();

        setOnclickItem();

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // movement
                store.setOnclick(dataPlayer);
                wareHouse.setOnclick(dataPlayer);

                updateData();
                updateText();
                updateBuff();

                listChicken.forEach(sprite -> sprite.move());
                listCow.forEach(sprite -> sprite.move());
                listPig.forEach(sprite -> sprite.move());
                listOstrich.forEach(sprite -> sprite.move());
                listPan.forEach(sprite->sprite.move());
                listDog.forEach(sprite->sprite.move());
                listCat.forEach(sprite->sprite.move());
//                listChicken.forEach(sprite -> sprite.delayTimeForHealth());

                listChicken.forEach(sprite -> sprite.updateUI());
                listPig.forEach(sprite -> sprite.updateUI());
                listCow.forEach(sprite -> sprite.updateUI());
                listOstrich.forEach(sprite -> sprite.updateUI());
                listCan.forEach(sprite -> sprite.updateUI());
                listPan.forEach(sprite -> sprite.updateUI());
                listDog.forEach(sprite -> sprite.updateUI());
                listCat.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                listChicken.forEach(sprite -> sprite.checkRemovability());
                listPig.forEach(sprite -> sprite.checkRemovability());
                listCow.forEach(sprite -> sprite.checkRemovability());
                listOstrich.forEach(sprite -> sprite.checkRemovability());




                removeSprites(listChicken);
                removeSprites(listPig);
                removeSprites(listCow);
                removeSprites(listOstrich);

                checkDieChiken();
                checkCowDie();
                checkOstrichDie();
                checkPigDie();


                checkDiedByHealth();

                checkDiedbyStep();
                removeChickendied();
                removeCowdied();
                removeOstrichdied();
                removePigdied();

                //checkDieChiken();
                checkTiemDieCan();

            }

        };
        gameLoop.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    void setClickStage(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                Group group_cf;
                Pane pane_cf;
                Stage stage_cf;
                group_cf = new Group();
                pane_cf = new Pane();
                stage_cf = new Stage();
                stage_cf.setTitle("");
                group_cf.getChildren().add(pane_cf);

                Text mes = new Text("Do you want to save data and close game?");
                mes.relocate(50, 50);
                mes.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
                pane_cf.getChildren().add(mes);

                javafx.scene.control.Button ok = new Button("Yes");
                ok.setPrefWidth(100);
                ok.relocate(80, 95);
                Button no = new Button("No");
                no.setPrefWidth(100);
                no.relocate(270, 95);

                pane_cf.getChildren().add(ok);
                pane_cf.getChildren().add(no);

                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        playerData.saveJson(dataPlayer);
                        stage_cf.close();
                        primaryStage.close();
                    }
                });

                no.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage_cf.close();
                    }
                });

                stage_cf.setScene(new Scene(group_cf, 450, 150));
                stage_cf.show();
                stage_cf.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        stage_cf.close();
                    }
                });
            }
        });
    }

    void setOnclickItem() {
        img_food_nol.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dataPlayer.getJoUser1().getJoWarehouse().getFoodNormal() > 0) {
                    scene.setCursor(new ImageCursor(image_food_nol));
                    typeSent = 1;
                }
            }
        });
        img_food_sep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dataPlayer.getJoUser1().getJoWarehouse().getFoodSpecial() > 0) {
                    scene.setCursor(new ImageCursor(image_food_sep));
                    typeSent = 2;
                }

            }
        });
        img_medi_nol.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dataPlayer.getJoUser1().getJoWarehouse().getMedicineNormal() > 0) {
                    scene.setCursor(new ImageCursor(image_med_nol));
                    typeSent = 3;
                }

            }
        });
        img_medi_sep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dataPlayer.getJoUser1().getJoWarehouse().getMedicineSpecial() > 0) {
                    scene.setCursor(new ImageCursor(image_med_sep));
                    typeSent = 4;
                }

            }
        });
        backgroud.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
                typeSent = 0;
            }
        });
        img_BackMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new Welcome().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                playerData.saveJson(dataPlayer);
                classStage.close();
            }
        });
    }

    void addText() {
        txt_money = new Text(10, 50, "");
        txt_money.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        txt_money.relocate(650, 40);
        txt_money.setX(50);
        txt_money.setY(50);
        txt_money.setFill(Color.YELLOW);
        playLayer.getChildren().add(txt_money);
        txt_food_nol = new Text(10, 50, "");
        txt_food_nol.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_food_nol.relocate(3, 66);
        txt_food_nol.setX(50);
        txt_food_nol.setY(50);
        txt_food_nol.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_food_nol);

        txt_food_sep = new Text(10, 50, "");
        txt_food_sep.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_food_sep.relocate(3, 110);
        txt_food_sep.setX(50);
        txt_food_sep.setY(50);
        txt_food_sep.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_food_sep);

        txt_medi_nol = new Text(10, 50, "");
        txt_medi_nol.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_medi_nol.relocate(3, 153);
        txt_medi_nol.setX(50);
        txt_medi_nol.setY(50);
        txt_medi_nol.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_medi_nol);

        txt_medi_sep = new Text(10, 50, "");
        txt_medi_sep.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_medi_sep.relocate(3, 196);
        txt_medi_sep.setX(50);
        txt_medi_sep.setY(50);
        txt_medi_sep.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_medi_sep);
    }

    void updateText() {
        txt_money.setText(dataPlayer.getJoUser1().getJoMoney() + " $");
        txt_food_nol.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getFoodNormal() + "");
        txt_food_sep.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getFoodSpecial() + "");
        txt_medi_nol.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getMedicineNormal() + "");
        txt_medi_sep.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getMedicineSpecial() + "");
    }

    void updateData() {
        updateListCan();
        updateListChicken();
        updateListCow();
        updateListOstrich();
        updateListPig();
        updatePanda();
        updateDog();
        updateCat();
    }

    void updateBuff() {
        if (typeSent > 0) {
            if (isokGame > 0) {
                switch (typeSent) {
                    case 1:
                        System.out.println("--- 1");
                        if (dataPlayer.getJoUser1().getJoWarehouse().getFoodNormal() > 0) {
                            dataPlayer.getJoUser1().getJoWarehouse().setFoodNormal(
                                    dataPlayer.getJoUser1().getJoWarehouse().getFoodNormal() - 1
                            );
                        }
                        isokGame = 0;
                        break;
                    case 2:
                        System.out.println("----2");
                        if (dataPlayer.getJoUser1().getJoWarehouse().getFoodSpecial() > 0) {
                            dataPlayer.getJoUser1().getJoWarehouse().setFoodSpecial(
                                    dataPlayer.getJoUser1().getJoWarehouse().getFoodSpecial() - 1
                            );
                        }
                        isokGame = 0;
                        break;
                    case 3:
                        System.out.println("----3");
                        if (dataPlayer.getJoUser1().getJoWarehouse().getMedicineNormal() > 0) {
                            dataPlayer.getJoUser1().getJoWarehouse().setMedicineNormal(
                                    dataPlayer.getJoUser1().getJoWarehouse().getMedicineNormal() - 1
                            );
                        }
                        isokGame = 0;
                        break;
                    case 4:
                        System.out.println("----4");
                        if (dataPlayer.getJoUser1().getJoWarehouse().getMedicineSpecial() > 0) {
                            dataPlayer.getJoUser1().getJoWarehouse().setMedicineSpecial(
                                    dataPlayer.getJoUser1().getJoWarehouse().getMedicineSpecial() - 1
                            );
                        }
                        isokGame = 0;
                        break;
                }
            }

        }
    }

    void addFirst() {
        //   playMusic();
        addChicken();
        addCow();
        addPig();
        addCan();
        addOstrich();
    }

    void addBack() {
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/back.png")));
        Image image_back_left = new Image(String.valueOf(classLoader.getResource("res/back_left.png")));
        Image image_back_right = new Image(String.valueOf(classLoader.getResource("res/back_right.png")));
        Image image_sel = new Image(String.valueOf(classLoader.getResource("res/select.png")));
        Image image_BackMenu = new Image(String.valueOf(classLoader.getResource("res/welcome/back.png")));
        image_food_nol = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/food_normal.png")));
        image_food_sep = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/food_special.png")));
        image_med_nol = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/medicine_normal.png")));
        Image txt_monney = new Image(String.valueOf(classLoader.getResource("res/money_box.png")));
        image_med_sep = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/medicine_special.png")));

        money.setImage(txt_monney);
        money.setFitHeight(60);
        money.setFitWidth(120);
        money.relocate(665, 20);
        money.setRotate(0);
        img_BackMenu.setImage(image_BackMenu);
        img_BackMenu.setFitHeight(150);
        img_BackMenu.setFitWidth(105);
        img_BackMenu.relocate(30, 310);
        img_food_nol.setImage(image_food_nol);
        img_food_nol.setFitWidth(25);
        img_food_nol.setFitHeight(25);
        img_food_nol.relocate(37, 43);
        img_food_sep.setImage(image_food_sep);
        img_food_sep.setFitWidth(25);
        img_food_sep.setFitHeight(25);
        img_food_sep.relocate(37, 83);
        img_medi_nol.setImage(image_med_nol);
        img_medi_nol.setFitWidth(25);
        img_medi_nol.setFitHeight(25);
        img_medi_nol.relocate(37, 130);
        img_medi_sep.setImage(image_med_sep);
        img_medi_sep.setFitWidth(25);
        img_medi_sep.setFitHeight(25);
        img_medi_sep.relocate(37, 170);
        backgroud.setImage(image_back);
        backgroud_l.setImage(image_back_left);
        backgroud_l.relocate(0, 335);
        backgroud_r.setImage(image_back_right);
        backgroud_r.relocate(560, 335);
        img_select.setImage(image_sel);
        img_select.relocate(20, 20);
        img_select.setFitHeight(200);
        img_select.setFitWidth(60);
        playLayer.getChildren().add(backgroud);
        playLayer.getChildren().add(backgroud_l);
        playLayer.getChildren().add(backgroud_r);
        playLayer.getChildren().add(img_select);
        playLayer.getChildren().add(img_food_nol);
        playLayer.getChildren().add(img_food_sep);
        playLayer.getChildren().add(img_medi_nol);
        playLayer.getChildren().add(img_medi_sep);
        playLayer.getChildren().add(money);
        playLayer.getChildren().add(img_BackMenu);
    }

    private void removeSprites(List<? extends Animal> spriteList) {
        Iterator<? extends Animal> iter = spriteList.iterator();
        while (iter.hasNext()) {
            Animal sprite = iter.next();

            if (sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    void addChicken() {
        List<ListChicken> listChickens = dataPlayer.getJoUser1().getJoChicken().getListChicken();
        for (int i = 0; i < listChickens.size(); i++) {
            int step = listChickens.get(i).getStep();
            double health = listChickens.get(i).getLife();
            double sick = listChickens.get(i).getSickness();
            Chicken chicken = new Chicken(playLayer, Settings.CHIKEN,
                    200, 200, 0, 0, 0, 0, health, sick, step, dataPlayer);
            chicken.setOnDrag();
            chicken.addBuffListener(this);
            listChicken.add(chicken);
        }

    }

    void updateListChicken() {

        List<ListChicken> listChickens = dataPlayer.getJoUser1().getJoChicken().getListChicken();
        for (int i = listChickens.size(); i < dataPlayer.getJoUser1().getJoChicken().getTotalNumber(); i++) {
            ListChicken chickenOJ = new ListChicken(100, 0, 1);
            listChickens.add(chickenOJ);
            Chicken chicken = new Chicken(playLayer, Settings.CHIKEN,
                    200, 200, 0, 0, 0, 0, 100, 0, 1, dataPlayer);
            chicken.setOnDrag();
            chicken.addBuffListener(this);
            listChicken.add(chicken);
        }
        dataPlayer.getJoUser1().getJoChicken().setTotalNumber(dataPlayer.getJoUser1().getJoChicken().getListChicken().size());
    }

    void updatePanda() {

        int curent = listPan.size();
        for (int i = curent; i < dataPlayer.getJoUser1().getJoPanda().getTotalNumber(); i++) {

            Panda pets = new Panda(playLayer, Settings.PANDA,
                    200, 200, 0, 0, 0, 0, 100, dataPlayer);
            listPan.add(pets);
        }
    }
    void updateDog() {

        int curent = listDog.size();
        for (int i = curent; i < dataPlayer.getJoUser1().getJoDog().getTotalNumber(); i++) {

            Dog pets = new Dog(playLayer, Settings.DOG,
                    200, 200, 0, 0, 0, 0, 100, dataPlayer);
            listDog.add(pets);
        }
    }
    void updateCat() {

        int curent = listCat.size();
        for (int i = curent; i < dataPlayer.getJoUser1().getJoDog().getTotalNumber(); i++) {

            Cat pets = new Cat(playLayer, Settings.CAT,
                    200, 200, 0, 0, 0, 0, 100, dataPlayer);
            listCat.add(pets);
        }
    }


    void updateListPig() {

        List<ListPig> list = dataPlayer.getJoUser1().getJoPig().getListPig();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoPig().getTotalNumber(); i++) {
            ListPig ob = new ListPig(100, 0, 1);
            list.add(ob);
            Pig animal = new Pig(playLayer, Settings.PIG,
                    200, 200, 0, 0, 0, 0, 100, 0, 1, dataPlayer);
            animal.setOnDrag();
            animal.addBuffListener(this);
            listPig.add(animal);
        }
        dataPlayer.getJoUser1().getJoPig().setTotalNumber(
                dataPlayer.getJoUser1().getJoPig().getListPig().size());
    }

    void updateListCow() {

        List<ListCow> list = dataPlayer.getJoUser1().getJoCow().getListCow();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoCow().getTotalNumber(); i++) {
            ListCow ob = new ListCow(100, 0, 1);
            list.add(ob);
            Cow animal = new Cow(playLayer, Settings.COW,
                    200, 200, 0, 0, 0, 0, 100, 0, 1, dataPlayer);
            animal.setOnDrag();
            animal.addBuffListener(this);
            listCow.add(animal);
        }
        dataPlayer.getJoUser1().getJoCow().setTotalNumber(
                dataPlayer.getJoUser1().getJoCow().getListCow().size());
    }

    void updateListOstrich() {

        List<ListOstrich> list = dataPlayer.getJoUser1().getJoOstrich().getListOstrich();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoOstrich().getTotalNumber(); i++) {
            ListOstrich ob = new ListOstrich(100.0, 0.0, 1);
            list.add(ob);
            Ostrich animal = new Ostrich(playLayer, Settings.OSTRICH,
                    200, 200, 0, 0, 0, 0, 100, 0, 1, dataPlayer);
            animal.setOnDrag();
            animal.addBuffListener(this);

            listOstrich.add(animal);
        }
        dataPlayer.getJoUser1().getJoOstrich().setTotalNumber(dataPlayer.getJoUser1().getJoOstrich().getListOstrich().size());
    }

    void updateListCan() {
        List<ListGras> list = dataPlayer.getJoUser1().getJoGrass().getListGrass();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoGrass().getTotalNumber(); i++) {
            buyCan();
        }
        dataPlayer.getJoUser1().getJoGrass().setTotalNumber(dataPlayer.getJoUser1().getJoGrass().getListGrass().size());
    }

    void addPig() {
        List<ListPig> listPigs = dataPlayer.getJoUser1().getJoPig().getListPig();
        System.out.println(listPigs.size());
        for (int i = 0; i < listPigs.size(); i++) {
            int step = listPigs.get(i).getStep();
            double health = listPigs.get(i).getLife();
            double sick = listPigs.get(i).getSickness();
            Pig pig = new Pig(playLayer, Settings.PIG,
                    200, 200, 0, 0, 0, 0, health, sick, step, dataPlayer);
            pig.setOnDrag();
            pig.addBuffListener(this);
            listPig.add(pig);
            System.out.println(listPigs.size());
        }

    }


    void addCow() {
        List<ListCow> listCows = dataPlayer.getJoUser1().getJoCow().getListCow();
        for (int i = 0; i < listCows.size(); i++) {
            int step = listCows.get(i).getStep();
            double health = listCows.get(i).getLife();
            double sick = listCows.get(i).getSickness();
            Cow cow = new Cow(playLayer, Settings.COW,
                    200, 200, 0, 0, 0, 0, health, sick, step, dataPlayer);
            cow.setOnDrag();
            cow.addBuffListener(this);
            listCow.add(cow);
        }
    }

    void addOstrich() {
        List<ListOstrich> listOstrichs = dataPlayer.getJoUser1().getJoOstrich().getListOstrich();
        for (int i = 0; i < listOstrichs.size(); i++) {
            int step = listOstrichs.get(i).getStep();
            double health = listOstrichs.get(i).getLife();
            double sick = listOstrichs.get(i).getSickness();
            Ostrich ostrich = new Ostrich(playLayer, Settings.OSTRICH,
                    200, 200, 0, 0, 0, 0, health, sick, step, dataPlayer);
            ostrich.setOnDrag();
            ostrich.addBuffListener(this);
            listOstrich.add(ostrich);
        }
    }

    void addCan() {
        List<ListGras> listGrass = dataPlayer.getJoUser1().getJoGrass().getListGrass();
        // create a sprite
        for (int i = 0; i < listGrass.size(); i++) {
            int postion;
            postion = dataPlayer.getJoUser1().getJoGrass().getListGrass().get(i).getPosition();
            location[postion] = 1;

            int y = 530;
            int x = (postion + 3) * 50;

            Can can = new Can(playLayer, Settings.CAN,
                    x, y, 0,  dataPlayer, postion);
            //
            //            // manage sprite
//            can.setOnDrag();
            listCan.add(can);

        }
    }

/*    void setStep() {
        int i;
    }*/

    void buyCan() {
        List<ListGras> listGrass = dataPlayer.getJoUser1().getJoGrass().getListGrass();
        // create a sprite
        for (int i = 0; i < listGrass.size(); i++) {
            int tmp1;
            tmp1 = dataPlayer.getJoUser1().getJoGrass().getListGrass().get(i).getPosition();
            location[tmp1] = 1;
        }

        if (listGrass.size() < 10) {
            int tmp;
            Random rd = new Random();
            do {
                tmp = rd.nextInt(10);
            }
            while (location[tmp] == 1);

            location[tmp] = 1;
            int y = 530;
            int x = (tmp + 3) * 50;
            ListGras gras = new ListGras(tmp, 1);
            listGrass.add(gras);

            Can can = new Can(playLayer, Settings.CAN,
                    x, y, 0, dataPlayer,tmp);
            //
            //            // manage sprite
            listCan.add(can);
            //            dataPlayer.getJoUser1().getJoGrass().setTotalNumber(String.valueOf(Integer.parseInt(dataPlayer.getJoUser1().getJoGrass().getTotalNumber())+1));
            //            ListGras listGras = new ListGras(i, "1");
            //            dataPlayer.getJoUser1().getJoGrass().getListGrass().add(listGras);

        }
    }

    private void playMusic() {
        String file_name = "src/res/sounds2/music_game.mp3";
        Media sound = new Media(new File(file_name).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(javafx.util.Duration.INDEFINITE);
            }
        });
        mediaPlayer.play();
    }


    private void checkDieChiken() {
        for (int i = 0; i < listChicken.size(); i++) {
            if (listChicken.get(i).getSick() < listChicken.get(i).timeDie) {
                listChicken.get(i).setSick(listChicken.get(i).getSick() + 1);
                System.out.println(listChicken.get(i).getSick());
            } else {
                //    listChicken.get(i).setDeath(1);
//                listChicken.get(i).setHealth(0);
//
//                listChicken.get(i).canMove = false;
//                break;

                //   addChicken();
            }
        }
    }

    @Override
    public int buffOk(int isOK) {
        System.out.println("lay ket qua da nhan duoc hay chua tu Animal :  " + isOK);
        isokGame = isOK;
        return typeSent;
    }

    private void checkCowDie() {
        for (int i = 0; i < listCow.size(); i++) {
            if (listCow.get(i).getSick() < listCow.get(i).timeDie) {
                listCow.get(i).setSick(listCow.get(i).getSick() + 1);

            } else {
//                listCow.get(i).setHealth(0);
//                listCow.get(i).hasDied = true;
//                listCow.get(i).setDeath(1);
            }
        }
    }

    private void checkPigDie() {
        for (int i = 0; i < listPig.size(); i++) {
            if (listPig.get(i).getSick() < listPig.get(i).timeDie) {
                listPig.get(i).setSick(listPig.get(i).getSick() + 1);

            } else {

            }
        }
    }

    private void checkOstrichDie() {
        for (int i = 0; i < listOstrich.size(); i++) {
            if (listOstrich.get(i).getSick() < listOstrich.get(i).timeDie) {
                listOstrich.get(i).setSick(listOstrich.get(i).getSick() + 1);

            }
            else {

            }
        }
    }

    private void removeChickendied() {
        for (int i = 0; i < listChicken.size(); i++) {

            if ((listChicken.get(i).getSick() >=
                    listChicken.get(i).timeDie && listChicken.get(i).hasDied >=
                    500 + listChicken.get(i).timeDie)
                    || (listChicken.get(i).getDiedByStep() >= 10000 && (listChicken.get(i).stepDie <
            1000 + listChicken.get(i).getDiedByStep()))) {
                System.out.println("=========" + listChicken.get(i).hasDied);
                listChicken.get(i).remove();
                System.out.println("^^^^^^^^^^^^" + listChicken.get(i).hasDied);
                dataPlayer.getJoUser1().setSpaceOut(
                        dataPlayer.getJoUser1().getSpaceOut()+10);
                dataPlayer.getJoUser1().getJoChicken().getListChicken().remove(i);
                 dataPlayer.getJoUser1().getJoChicken().setTotalNumber(dataPlayer.getJoUser1().getJoChicken().getTotalNumber()-1);
            } else{
                listChicken.get(i).hasDied++;
            }


        }
    }

    private void removeCowdied() {

        for (int i = 0; i < listCow.size(); i++) {
            if ((listCow.get(i).getSick() >= listCow.get(i).timeDie && listCow.get(i).hasDied >= 1000 + listCow.get(i).timeDie) ||
                    (listCow.get(i).getDiedByStep() >= 15000 &&(listCow.get(i).stepDie < 500 +
                    listCow.get(i).getDiedByStep()))) {

                listCow.get(i).remove();
                dataPlayer.getJoUser1().setSpaceOut(
                        dataPlayer.getJoUser1().getSpaceOut() + 15);
                dataPlayer.getJoUser1().getJoCow().getListCow().remove(i);
                dataPlayer.getJoUser1().getJoCow().setTotalNumber(
                        dataPlayer.getJoUser1().getJoCow().getTotalNumber() - 1);
            } else {
                listCow.get(i).hasDied++;
            }
        }
    }

    private void removeOstrichdied() {
        for (int i = 0; i < listOstrich.size(); i++) {
            if ((listOstrich.get(i).getSick() >= listOstrich.get(i).timeDie && listOstrich.get(i).hasDied >= 1000 + listOstrich.get(i).timeDie)
                    || (listOstrich.get(i).getDiedByStep() >= 13000 && listOstrich.get(i).stepDie < 500+
            listOstrich.get(i).getDiedByStep())) {

                listOstrich.get(i).remove();
                dataPlayer.getJoUser1().setSpaceOut(
                        dataPlayer.getJoUser1().getSpaceOut() + 12);
                dataPlayer.getJoUser1().getJoOstrich().getListOstrich().remove(i);
                dataPlayer.getJoUser1().getJoOstrich().setTotalNumber(
                      dataPlayer.getJoUser1().getJoOstrich().getTotalNumber()-1);
            } else{
                listOstrich.get(i).hasDied++;
                listOstrich.get(i).stepDie++;
            }
        }
    }

    private void removePigdied() {
        for (int i = 0; i < listPig.size(); i++) {
            if ((listPig.get(i).getSick() >= listPig.get(i).timeDie &&
                    listPig.get(i).hasDied >= 1000 + listPig.get(i).timeDie) || (listPig.get(i).getDiedByStep() >= 1001
            && listPig.get(i).stepDie < 500 + listPig.get(i).getDiedByHeight())) {

                listPig.get(i).remove();
                dataPlayer.getJoUser1().setSpaceOut(
                        dataPlayer.getJoUser1().getSpaceOut()+13);
                dataPlayer.getJoUser1().getJoPig().getListPig().remove(i);
                dataPlayer.getJoUser1().getJoPig().setTotalNumber(
                     dataPlayer.getJoUser1().getJoPig().getTotalNumber()-1);

            } else{
                listPig.get(i).hasDied++;
                listPig.get(i).stepDie++;
            }
        }
    }



    public void checkTiemDieCan() {
        for (int i = 0; i < listCan.size(); i++) {
            ListGras tmp = dataPlayer.getJoUser1().getJoGrass().getListGrass().get(i);
            if (listCan.get(i).timeDieCan > 0) {
                listCan.get(i).timeDieCan--;
            } else if (listCan.get(i).timeDieCan == 0) {
                tmp.setStep(2);
                listCan.get(i).timeDieCan--;
                dataPlayer.getJoUser1().getJoGrass().getListGrass().remove(tmp);
                dataPlayer.getJoUser1().getJoGrass().setTotalNumber(
                        dataPlayer.getJoUser1().getJoGrass().getTotalNumber() - 1
                );
                listCan.get(i).dieToBorn();
                location[listCan.get(i).getLocate()] = 0;
                listCan.remove(i);
                break;
            }

        }
    }



    public void checkDiedbyStep() {
        for (int i = 0 ;i < listChicken.size(); i++) {
            if  (listChicken.get(i).getDiedByStep() < 10000) {
                listChicken.get(i).setDiedByStep(listChicken.get(i).getDiedByStep() + 1);
                if (listChicken.get(i).getDiedByStep() >= 500) {
                    listChicken.get(i).setStep(2);
                    dataPlayer.getJoUser1().getJoChicken().getListChicken().get(i).setStep(2);
                }
                if (listChicken.get(i).getDiedByStep() >= 900) {
                    listChicken.get(i).setStep(3);
                    dataPlayer.getJoUser1().getJoChicken().getListChicken().get(i).setStep(3);
                }
            }
        }
        for (int i = 0 ;i < listOstrich.size(); i++) {
            if  (listOstrich.get(i).getDiedByStep() < 13000) {
                listOstrich.get(i).setDiedByStep(listOstrich.get(i).getDiedByStep() + 1);
                if (listOstrich.get(i).getDiedByStep() == 6500) {
                    listOstrich.get(i).setStep(2);
                    dataPlayer.getJoUser1().getJoOstrich().getListOstrich().get(i).setStep(2);
                }
                if (listOstrich.get(i).getDiedByStep() == 12500) {
                    listOstrich.get(i).setStep(3);
                    dataPlayer.getJoUser1().getJoOstrich().getListOstrich().get(i).setStep(3);
                }
                }
        }
        for (int i = 0 ;i < listPig.size(); i++) {
            if  (listPig.get(i).getDiedByStep() < 4100 ) {
      //          System.out.println("aaa" + listPig.get(i).getDiedByStep());
                listPig.get(i).setDiedByStep(listPig.get(i).getDiedByStep() + 1);
                if (listPig.get(i).getDiedByStep() >= 2000){
                    dataPlayer.getJoUser1().getJoPig().getListPig().get(i).setStep(2);
                    listPig.get(i).setStep(2);
                }
                if (listPig.get(i).getDiedByStep() >= 4000 ) {
                    dataPlayer.getJoUser1().getJoPig().getListPig().get(i).setStep(3);
                    listPig.get(i).setStep(3);

                }

            }
        }

        for (int i = 0 ;i < listCow.size(); i++) {
            if (listCow.get(i).getDiedByStep() < 1500) {
                listCow.get(i).setDiedByStep(listCow.get(i).getDiedByStep() + 1);


                if (listCow.get(i).getDiedByStep() >= 750) {
                    dataPlayer.getJoUser1().getJoCow().getListCow().get(i).setStep(2);
                    listCow.get(i).setStep(2);
                }
                if (listCow.get(i).getDiedByStep() >= 1450) {
                    listCow.get(i).setStep(2);
                    dataPlayer.getJoUser1().getJoCow().getListCow().get(i).setStep(3);
                }
            }
        }
    }

    public void checkDiedByHealth() {
        for (int i = 0; i < listChicken.size(); i++) {
            if (listChicken.get(i).getHealth() > 0) {
//                listChicken.get(i).setDiedByHeight(listChicken.get(i).getDiedByHeight()+1);
                listChicken.get(i).setHealth(listChicken.get(i).getHealth()-0.1);
                dataPlayer.getJoUser1().getJoChicken().getListChicken().get(i).setLife(
                        dataPlayer.getJoUser1().getJoChicken().getListChicken().get(i).getLife()-0.1);
            }
            else {
                listChicken.get(i).countHealthDie++;
                if (listChicken.get(i).countHealthDie > 750) {
                    System.out.println("**************************" + listChicken.get(i).getHealth());
                    listChicken.get(i).remove();
                    dataPlayer.getJoUser1().setSpaceOut(
                            dataPlayer.getJoUser1().getSpaceOut()+10);
                    dataPlayer.getJoUser1().getJoChicken().getListChicken().remove(i);
                    dataPlayer.getJoUser1().getJoChicken().setTotalNumber(dataPlayer.getJoUser1().getJoChicken().getTotalNumber()-1);
                }

            }
        }

        for (int i = 0; i < listCow.size(); i++) {
            if (listCow.get(i).getHealth() > 0) {
                listCow.get(i).setDiedByHeight(listCow.get(i).getDiedByHeight()+1);
                listCow.get(i).setHealth(listCow.get(i).getHealth()-0.05);
                dataPlayer.getJoUser1().getJoCow().getListCow().get(i).setLife(
                        dataPlayer.getJoUser1().getJoCow().getListCow().get(i).getLife()-0.05);
            }
            else {
                listCow.get(i).countHealthDie++;
                if (listCow.get(i).countHealthDie > 750) {
                    listCow.get(i).remove();
                    dataPlayer.getJoUser1().setSpaceOut(
                            dataPlayer.getJoUser1().getSpaceOut()+15);
                    dataPlayer.getJoUser1().getJoCow().getListCow().remove(i);
                    dataPlayer.getJoUser1().getJoCow().setTotalNumber(dataPlayer.getJoUser1().getJoCow().getTotalNumber()-1);
                }

            }
        }

        for (int i = 0; i < listPig.size(); i++) {
            if (listPig.get(i).getHealth() > 0 && listPig.size() > 0) {
//                listPig.get(i).setDiedByHeight(listPig.get(i).getDiedByHeight()+1);
                listPig.get(i).setHealth(listPig.get(i).getHealth()-0.04);
                dataPlayer.getJoUser1().getJoPig().getListPig().get(i).setLife(
                        dataPlayer.getJoUser1().getJoPig().getListPig().get(i).getLife()-0.04);
            }
            else {
                listPig.get(i).countHealthDie++;
                if (listPig.get(i).countHealthDie > 750) {
                    listPig.get(i).remove();
                    dataPlayer.getJoUser1().setSpaceOut(
                            dataPlayer.getJoUser1().getSpaceOut()+13);
                    dataPlayer.getJoUser1().getJoPig().getListPig().remove(i);
                    dataPlayer.getJoUser1().getJoPig().setTotalNumber(
                            dataPlayer.getJoUser1().getJoPig().getTotalNumber()-1);
                }

            }
        }

        for (int i = 0; i < listOstrich.size(); i++) {
            if (listOstrich.get(i).getHealth() > 0) {
//                listOstrich.get(i).setDiedByHeight(listOstrich.get(i).getDiedByHeight()+1);
                listOstrich.get(i).setHealth(listOstrich.get(i).getHealth()-0.07);
                dataPlayer.getJoUser1().getJoOstrich().getListOstrich().get(i).setLife(
                        dataPlayer.getJoUser1().getJoOstrich().getListOstrich().get(i).getLife()-0.07);
            }
            else {
                listOstrich.get(i).countHealthDie++;
                if (listOstrich.get(i).countHealthDie > 750) {
                    listOstrich.get(i).remove();
                    dataPlayer.getJoUser1().setSpaceOut(
                            dataPlayer.getJoUser1().getSpaceOut()+12);
                    dataPlayer.getJoUser1().getJoOstrich().getListOstrich().remove(i);
                    dataPlayer.getJoUser1().getJoOstrich().setTotalNumber(dataPlayer.getJoUser1().getJoOstrich().getTotalNumber()-1);
                }

            }
        }

    }
}
