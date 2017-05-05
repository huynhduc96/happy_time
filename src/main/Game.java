package main;

import base.*;
import base.animalstype.Animal;
import base.animalstype.Chicken;
import base.animalstype.Cow;
import base.animalstype.Pig;
import base.grassstyle.Grass;
import base.grassstyle.Can;
import base.House.Store;
import base.jsonObject.DataPlayer;
import base.jsonObject.ListChicken;
import base.jsonObject.ListCow;
import base.jsonObject.ListPig;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.time.Duration;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by huynh on 07-Apr-17.
 */
public class Game extends Application {
    Pane playLayer;
    Scene scene;
    List<Chicken> listChicken = new ArrayList<>();
    JLabel lbMoney;
    List<Pig> listPig = new ArrayList<>();
    List<Cow> listCow = new ArrayList<>();
    List<Can> listCan = new ArrayList<>();
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();

    ImageView money = new ImageView();

    private ImageView backgroud = new ImageView();
    private DataPlayer dataPlayer = null;

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        // make root scene
        Group root = new Group();


        // add Pane play for player
        playLayer = new Pane();
        //add background
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/back.png")));
        backgroud.setImage(image_back);
        playLayer.getChildren().add(backgroud);

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
        playMusic();
        addChicken();
        addCow();
        addPig();

        Store store = new Store(playLayer,300,10,0);
        Image images = new Image(String.valueOf(classLoader.getResource(
                "res/money_box.png")));

        money.setImage(images);
        money.setFitHeight(70);
        money.setFitWidth(100);
        money.relocate(670, 10);
        money.setRotate(0);
        playLayer.getChildren().add(money);
        Text t = new Text(10, 50, "This is a test");
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        t.setRotate(0);
        t.relocate(650, 40);
        t.setX(50);
        t.setY(50);
        playLayer.getChildren().add(t);

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // movement
                store.setOnclick(dataPlayer);
                store.updateDataPlayer(playerData);

           
                t.setText(dataPlayer.getJoUser1().getJoMonney()+"");
                listChicken.forEach(sprite -> sprite.move());
                listCow.forEach(sprite -> sprite.move());
                listPig.forEach(sprite -> sprite.move());

                // update sprites in scene

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

        // create a sprite

//        for(JsonElement chicken_tmp: data.getAsJsonObject(
//                "jo_chicken").getAsJsonArray("list_chicken")) {
//            int tmp_step = chicken_tmp.getAsJsonObject().get("step").getAsInt();
//            double tmp_health = chicken_tmp.getAsJsonObject().get("life").getAsDouble() / 100;
//            double tmp_sickness = chicken_tmp.getAsJsonObject().get("sickness").getAsDouble() / 100;
//            Chicken chicken = new Chicken(playLayer,
//                    Settings.CHIKEN, 200, 200, 0, 0, 0,
//                    0, tmp_health, tmp_sickness, tmp_step);
//            // manage sprite
//            listChicken.add(chicken);
//        }

        List<ListChicken> listChickens = dataPlayer.getJoUser1().getJoChicken().getListChicken();
        for (int i = 0; i < listChickens.size(); i++) {
            int step = Integer.parseInt(listChickens.get(i).getStep());
            double health = Double.parseDouble(listChickens.get(i).getLife());
            double sick = Double.parseDouble(listChickens.get(i).getSickness());
            Chicken chicken = new Chicken(playLayer,Settings.CHIKEN,
                    200,200,0,0,0,0,health,sick,step);
            listChicken.add(chicken);
        }

    }

    void addPig() {

        // create a sprite
//        for(JsonElement chicken_tmp: data.getAsJsonObject(
//                "jo_pig").getAsJsonArray("list_pig")) {
//            int tmp_step = chicken_tmp.getAsJsonObject().get("step").getAsInt();
//            double tmp_health = chicken_tmp.getAsJsonObject().get("life").getAsDouble() / 100;
//            double tmp_sickness = chicken_tmp.getAsJsonObject().get("sickness").getAsDouble() / 100;
//
//            Pig pig = new Pig(playLayer, Settings.PIG, 200, 300, 0, 0,
//                    Settings.ANIMAL_SPEED, 0, tmp_health, tmp_sickness,
//                    tmp_step);
//
//            // manage sprite
//            listPig.add(pig);
//        }


        List<ListPig> listPigs = dataPlayer.getJoUser1().getJoPig().getListPig();
        for (int i = 0; i < listPigs.size(); i++) {
            int step = Integer.parseInt(listPigs.get(i).getStep());
            double health = Double.parseDouble(listPigs.get(i).getLife());
            double sick = Double.parseDouble(listPigs.get(i).getSickness());
            Pig pig = new Pig(playLayer,Settings.PIG,
                    200,200,0,0,0,0,health,sick,step);
            listPig.add(pig);
        }

    }

    void addCow() {

        // create a sprite

//        for(JsonElement chicken_tmp: data.getAsJsonObject(
//                "jo_cow").getAsJsonArray("list_cow")) {
//            int tmp_step = chicken_tmp.getAsJsonObject().get("step").getAsInt();
//            double tmp_health = chicken_tmp.getAsJsonObject().get("life").getAsDouble() / 100;
//            double tmp_sickness = chicken_tmp.getAsJsonObject().get("sickness").getAsDouble() / 100;
//
//            Cow cow = new Cow(playLayer, Settings.COW, 300, 200, 0, 0,
//                    Settings.ANIMAL_SPEED, 0, tmp_health, tmp_sickness,
//                    tmp_step);
//
//            // manage sprite
//            listCow.add(cow);
//        }

        List<ListCow> listCows = dataPlayer.getJoUser1().getJoCow().getListCow();
        for (int i = 0; i < listCows.size(); i++) {
            int step = Integer.parseInt(listCows.get(i).getStep());
            double health = Double.parseDouble(listCows.get(i).getLife());
            double sick = Double.parseDouble(listCows.get(i).getSickness());
            Cow cow = new Cow(playLayer,Settings.COW,
                    200,200,0,0,0,0,health,sick,step);
            listCow.add(cow);
        }

    }

    void addCan() {

        // create a sprite
        int x = ThreadLocalRandom.current().nextInt(150, 600 + 1);
        int y = ThreadLocalRandom.current().nextInt(150, 450 + 1);
        Can can = new Can(playLayer, Settings.CAN, x, y, 0, 0, Settings.ANIMAL_SPEED, 0, 1, 1);

        // manage sprite
        listCan.add(can);
    }




}
