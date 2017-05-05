package main;

import base.*;
import base.House.WareHouse;
import base.animalstype.Animal;
import base.animalstype.Chicken;
import base.animalstype.Cow;
import base.animalstype.Pig;
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
    List<Can> listCan = new ArrayList<>();
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();

    ImageView money = new ImageView();
    Integer[] location = new Integer[10];

    private ImageView backgroud = new ImageView();
    private ImageView backgroud_l = new ImageView();
    private ImageView backgroud_r = new ImageView();
    private ImageView img_help = new ImageView();
    private DataPlayer dataPlayer = null;


    @Override
    public void start(Stage primaryStage) throws Exception {
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

        Store store = new Store(playLayer, 300, 10, 0);
        WareHouse wareHouse = new WareHouse(playLayer,620,305,0);
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
                ok.setPrefWidth(100);
                ok.relocate(80, 95);
                Button no = new Button("Sau");
                no.setPrefWidth(100);
                no.relocate(270, 95);

                pane_cf.getChildren().add(ok);
                pane_cf.getChildren().add(no);

                ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage_cf.close();

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


                t.setText(dataPlayer.getJoUser1().getJoMonney() + " $");
                listChicken.forEach(sprite -> sprite.move());
                listCow.forEach(sprite -> sprite.move());
                listPig.forEach(sprite -> sprite.move());
//                listChicken.forEach(sprite -> sprite.delayTimeForHealth());
                // update sprites in scene4

                listChicken.forEach(sprite -> sprite.updateUI());
                listPig.forEach(sprite -> sprite.updateUI());
                listCow.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                listChicken.forEach(sprite -> sprite.checkRemovability());
                listPig.forEach(sprite -> sprite.checkRemovability());
                listCow.forEach(sprite -> sprite.checkRemovability());


                removeSprites(listChicken);
                removeSprites(listPig);
                removeSprites(listCow);
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
        Text txt_help = new Text("Help");
        txt_help.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        txt_help.relocate(77,394);
        txt_help.setFill(Color.YELLOW);
        backgroud.setImage(image_back);
        img_help.setImage(image_help);
        img_help.relocate(50,320);
        img_help.setFitHeight(140);
        img_help.setFitWidth(100);
        backgroud_l.setImage(image_back_left);
        backgroud_l.relocate(0, 335);
        backgroud_r.setImage(image_back_right);
        backgroud_r.relocate(560, 335);
        playLayer.getChildren().add(backgroud);
        playLayer.getChildren().add(backgroud_l);
        playLayer.getChildren().add(backgroud_r);
        playLayer.getChildren().add(img_help);
        playLayer.getChildren().add(txt_help);
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
            int step = Integer.parseInt(listChickens.get(i).getStep());
            double health = Double.parseDouble(listChickens.get(i).getLife());
            double sick = Double.parseDouble(listChickens.get(i).getSickness());
            Chicken chicken = new Chicken(playLayer, Settings.CHIKEN,
                    200, 200, 0, 0, 0, 0, health, sick, step);
            chicken.setOnDrag();
            listChicken.add(chicken);
        }

    }

    void addPig() {
        List<ListPig> listPigs = dataPlayer.getJoUser1().getJoPig().getListPig();
        for (int i = 0; i < listPigs.size(); i++) {
            int step = Integer.parseInt(listPigs.get(i).getStep());
            double health = Double.parseDouble(listPigs.get(i).getLife());
            double sick = Double.parseDouble(listPigs.get(i).getSickness());
            Pig pig = new Pig(playLayer, Settings.PIG,
                    200, 200, 0, 0, 0, 0, health, sick, step);
            pig.setOnDrag();
            listPig.add(pig);
        }

    }

    void addCow() {
        List<ListCow> listCows = dataPlayer.getJoUser1().getJoCow().getListCow();
        for (int i = 0; i < listCows.size(); i++) {
            int step = Integer.parseInt(listCows.get(i).getStep());
            double health = Double.parseDouble(listCows.get(i).getLife());
            double sick = Double.parseDouble(listCows.get(i).getSickness());
            Cow cow = new Cow(playLayer, Settings.COW,
                    200, 200, 0, 0, 0, 0, health, sick, step);
            cow.setOnDrag();
            listCow.add(cow);
        }
    }

    void addCan() {
        int j;
        int tmp = Integer.parseInt(dataPlayer.getJoUser1().getJoGrass().getTotalNumber());
        // create a sprite
        for (j = 0; j < tmp; j++) {
            int tmp1;
            tmp1 = dataPlayer.getJoUser1().getJoGrass().getListGrass().get(j).getPosition();
            location[tmp1] = 1;
        }

        if (tmp < 10) {
            int i;
            Random rd = new Random();
            do {
                i = rd.nextInt(10);
            }
            while (location[i] == 1);

            location[i] = 1;
            int y = 530;
            int x = (i + 3) * 50;

//            Can can = new Can(playLayer, Settings.CAN, x, y, 0, 0, Settings.ANIMAL_SPEED, 0, 1, 1);
//
//            // manage sprite
//            listCan.add(can);
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
}
