package main;

import base.*;
import base.House.WareHouse;
import base.animalstype.*;
import base.grassstyle.Grass;
import base.grassstyle.Can;
import base.House.Store;
import base.jsonObject.*;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.time.Duration;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by huynh on 07-Apr-17.
 */
public class Game extends Application {
    Pane playLayer;
    Scene scene;
    List<Chicken> listChicken = new ArrayList<>();
    List<Pig> listPig = new ArrayList<>();
    List<Cow> listCow = new ArrayList<>();
    List<Ostrich> listOstrich = new ArrayList<>();
    List<Can> listCan = new ArrayList<>();
    ImageView[] imgHelp = new ImageView[3];
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();

    ImageView money = new ImageView();
    Integer[] location = new Integer[10];

    private ImageView backgroud = new ImageView();
    private ImageView backgroud_l = new ImageView();
    private ImageView backgroud_r = new ImageView();
    private ImageView img_help = new ImageView();
    private ImageView img_select = new ImageView();
    private ImageView img_food_nol = new ImageView();
    private ImageView img_food_sep = new ImageView();
    private ImageView img_medi_nol = new ImageView();
    private ImageView img_medi_sep = new ImageView();
    private DataPlayer dataPlayer = null;

    static Stage classStage = new Stage();
    int page = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        classStage = primaryStage;
        for (int i = 0; i < 10; i++) {
            location[i] = 0;
        }
        // make root scene
        Group root = new Group();


        // add Pane play for player
        playLayer = new Pane();
        //add background
        addBack();

        //get data player from json
        playerData.getdataPlayer();

        //    data = playerData.getDataPlayer();

        // lay data player
        dataPlayer = playerData.getPlayer();

        // add scene
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("close");
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
        playMusic();
        addChicken();
        addCow();
        addPig();
        addCan();

        addOstrich();
        for (int i = 0; i < 3; i++) {
            String temp = "res/help/" + (i + 1) + ".png";
            Image image = new Image(String.valueOf(classLoader.getResource(
                    temp)));
            imgHelp[i] = new ImageView();
            imgHelp[i].setImage(image);
            imgHelp[i].setFitHeight(480);
            imgHelp[i].setFitWidth(640);
            imgHelp[i].relocate(0, 0);
        }
        Store store = new Store(playLayer, 300, 10, 0);
        WareHouse wareHouse = new WareHouse(playLayer, 620, 305, 0);
        Image images = new Image(String.valueOf(classLoader.getResource(
                "res/money_box.png")));
        img_help.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("help");
                event.consume();
                Group group_cf;
                Pane pane_cf;
                Stage stage_cf;
                group_cf = new Group();
                pane_cf = new Pane();
                stage_cf = new Stage();
                stage_cf.setTitle("Help");
                group_cf.getChildren().add(pane_cf);


                javafx.scene.control.Button ok = new Button("Trước");
                ok.setPrefWidth(70);
                ok.relocate(220, 496);
                Button no = new Button("Sau");
                no.setPrefWidth(70);
                no.relocate(700 - 220 - 70, 496);

                pane_cf.getChildren().add(imgHelp[page]);
                pane_cf.getChildren().add(ok);
                pane_cf.getChildren().add(no);

                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (page > 0) {
                            pane_cf.getChildren().add(imgHelp[page - 1]);
                            pane_cf.getChildren().remove(imgHelp[page]);
                            page--;
                        } else {
                            page = 2;
                            pane_cf.getChildren().add(imgHelp[page]);
                            pane_cf.getChildren().remove(imgHelp[0]);
                        }
                    }
                });

                no.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (page < 2) {
                            pane_cf.getChildren().add(imgHelp[page + 1]);
                            pane_cf.getChildren().remove(imgHelp[page]);
                            page++;
                        } else {
                            page = 0;
                            pane_cf.getChildren().add(imgHelp[page]);
                            pane_cf.getChildren().remove(imgHelp[2]);
                        }
                    }
                });

                stage_cf.setScene(new Scene(group_cf, 640, 540));
                stage_cf.show();
                stage_cf.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        stage_cf.close();
                    }
                });
            }
        });

        money.setImage(images);
        money.setFitHeight(60);
        money.setFitWidth(120);
        money.relocate(665, 20);
        money.setRotate(0);
        playLayer.getChildren().add(money);
        Text t = new Text(10, 50, "This is a test");
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        t.setRotate(0);
        t.relocate(650, 40);
        t.setX(50);
        t.setY(50);
        t.setFill(Color.YELLOW);
        playLayer.getChildren().add(t);
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // movement
                store.setOnclick(dataPlayer);
                wareHouse.setOnclick(dataPlayer);
                //       store.updateDataPlayer(playerData);
                updateListCan();
                updateListChicken();
                updateListCow();
                updateListOstrich();
                updateListPig();

                t.setText(dataPlayer.getJoUser1().getJoMoney() + " $");
                listChicken.forEach(sprite -> sprite.move());
                listCow.forEach(sprite -> sprite.move());
                listPig.forEach(sprite -> sprite.move());
                listOstrich.forEach(sprite -> sprite.move());
//                listChicken.forEach(sprite -> sprite.delayTimeForHealth());
                // update sprites in scene4

                listChicken.forEach(sprite -> sprite.updateUI());
                listPig.forEach(sprite -> sprite.updateUI());
                listCow.forEach(sprite -> sprite.updateUI());
                listOstrich.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                listChicken.forEach(sprite -> sprite.checkRemovability());
                listPig.forEach(sprite -> sprite.checkRemovability());
                listCow.forEach(sprite -> sprite.checkRemovability());
                listOstrich.forEach(sprite -> sprite.checkRemovability());


                removeSprites(listChicken);
                removeSprites(listPig);
                removeSprites(listCow);
                removeSprites(listOstrich);
                //checkDieChiken();
            }

        };
        gameLoop.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    void addBack() {
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/back.png")));
        Image image_back_left = new Image(String.valueOf(classLoader.getResource("res/back_left.png")));
        Image image_back_right = new Image(String.valueOf(classLoader.getResource("res/back_right.png")));
        Image image_help = new Image(String.valueOf(classLoader.getResource("res/help.png")));
        Image image_sel = new Image(String.valueOf(classLoader.getResource("res/select.png")));
        Image image_food_nol = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/food_normal.png")));
        Image image_food_sep = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/food_special.png")));
        Image image_med_nol = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/medicine_normal.png")));
        Image image_med_sep = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/medicine_special.png")));
        Text txt_help = new Text("Help");
        txt_help.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        txt_help.relocate(77, 394);
        txt_help.setFill(Color.YELLOW);
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
        img_help.setImage(image_help);
        img_help.relocate(50, 320);
        img_help.setFitHeight(140);
        img_help.setFitWidth(100);
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
        playLayer.getChildren().add(img_help);
        playLayer.getChildren().add(txt_help);
        playLayer.getChildren().add(img_select);
        playLayer.getChildren().add(img_food_nol);
        playLayer.getChildren().add(img_food_sep);
        playLayer.getChildren().add(img_medi_nol);
        playLayer.getChildren().add(img_medi_sep);
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
            int health = listChickens.get(i).getLife();
            int sick = listChickens.get(i).getSickness();
            Chicken chicken = new Chicken(playLayer, Settings.CHIKEN,
                    200, 200, 0, 0, 0, 0, health, sick, step);
            chicken.setOnDrag();
            listChicken.add(chicken);
        }

    }

    void updateListChicken() {

        List<ListChicken> listChickens = dataPlayer.getJoUser1().getJoChicken().getListChicken();
        for (int i = listChickens.size(); i < dataPlayer.getJoUser1().getJoChicken().getTotalNumber(); i++) {
            ListChicken chickenOJ = new ListChicken(100, 0, 1);
            listChickens.add(chickenOJ);
            Chicken chicken = new Chicken(playLayer, Settings.CHIKEN,
                    200, 200, 0, 0, 0, 0, 100, 0, 1);
            chicken.setOnDrag();

            listChicken.add(chicken);
        }
        dataPlayer.getJoUser1().getJoChicken().setTotalNumber(dataPlayer.getJoUser1().getJoChicken().getListChicken().size());
    }

    void updateListPig() {

        List<ListPig> list = dataPlayer.getJoUser1().getJoPig().getListPig();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoPig().getTotalNumber(); i++) {
            ListPig ob = new ListPig(100, 0, 1);
            list.add(ob);
            Pig animal = new Pig(playLayer, Settings.PIG,
                    200, 200, 0, 0, 0, 0, 100, 0, 1);
            animal.setOnDrag();

            listPig.add(animal);
        }
        dataPlayer.getJoUser1().getJoPig().setTotalNumber(dataPlayer.getJoUser1().getJoPig().getListPig().size());
    }

    void updateListCow() {

        List<ListCow> list = dataPlayer.getJoUser1().getJoCow().getListCow();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoCow().getTotalNumber(); i++) {
            ListCow ob = new ListCow(100, 0, 1);
            list.add(ob);
            Cow animal = new Cow(playLayer, Settings.COW,
                    200, 200, 0, 0, 0, 0, 100, 0, 1);
            animal.setOnDrag();

            listCow.add(animal);
        }
        dataPlayer.getJoUser1().getJoCow().setTotalNumber(dataPlayer.getJoUser1().getJoCow().getListCow().size());
    }

    void updateListOstrich() {

        List<ListOstrich> list = dataPlayer.getJoUser1().getJoOstrich().getListOstrich();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoOstrich().getTotalNumber(); i++) {
            ListOstrich ob = new ListOstrich(100, 0, 1);
            list.add(ob);
            Ostrich animal = new Ostrich(playLayer, Settings.OSTRICH,
                    200, 200, 0, 0, 0, 0, 100, 0, 1);
            animal.setOnDrag();

            listOstrich.add(animal);
        }
        dataPlayer.getJoUser1().getJoOstrich().setTotalNumber(dataPlayer.getJoUser1().getJoOstrich().getListOstrich().size());
    }

    void updateListCan(){
        List<ListGras> list = dataPlayer.getJoUser1().getJoGrass().getListGrass();
        for (int i = list.size(); i < dataPlayer.getJoUser1().getJoGrass().getTotalNumber(); i++) {
           buyCan();
        }
        dataPlayer.getJoUser1().getJoGrass().setTotalNumber(dataPlayer.getJoUser1().getJoGrass().getListGrass().size());
    }

    void addPig() {
        List<ListPig> listPigs = dataPlayer.getJoUser1().getJoPig().getListPig();
        for (int i = 0; i < listPigs.size(); i++) {
            int step = listPigs.get(i).getStep();
            double health = listPigs.get(i).getLife();
            double sick = listPigs.get(i).getSickness();
            Pig pig = new Pig(playLayer, Settings.PIG,
                    200, 200, 0, 0, 0, 0, health, sick, step);
            pig.setOnDrag();
            listPig.add(pig);
        }

    }


    void addCow() {
        List<ListCow> listCows = dataPlayer.getJoUser1().getJoCow().getListCow();
        for (int i = 0; i < listCows.size(); i++) {
            int step = listCows.get(i).getStep();
            double health = listCows.get(i).getLife();
            double sick = listCows.get(i).getSickness();
            Cow cow = new Cow(playLayer, Settings.COW,
                    200, 200, 0, 0, 0, 0, health, sick, step);
            cow.setOnDrag();
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
                    200, 200, 0, 0, 0, 0, health, sick, step);
            ostrich.setOnDrag();
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
                    x, y, 0, 0, 0, 0, 1, 1);
            //
            //            // manage sprite
            listCan.add(can);
            //            dataPlayer.getJoUser1().getJoGrass().setTotalNumber(String.valueOf(Integer.parseInt(dataPlayer.getJoUser1().getJoGrass().getTotalNumber())+1));
            //            ListGras listGras = new ListGras(i, "1");
            //            dataPlayer.getJoUser1().getJoGrass().getListGrass().add(listGras);

        }
    }

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

            Can can = new Can(playLayer, Settings.CAN,
                    x, y, 0, 0, 0, 0, 1, 1);
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
            if (listChicken.get(i).timeDie > 0) {
                listChicken.get(i).timeDie--;
            } else {
                dataPlayer.getJoUser1().getJoChicken().getListChicken().remove(i);
                listChicken.get(i).remove();
                dataPlayer.getJoUser1().getJoChicken().setTotalNumber(dataPlayer.getJoUser1().getJoChicken().getTotalNumber() - 1);
                break;

                //   addChicken();
            }
        }
    }

}
