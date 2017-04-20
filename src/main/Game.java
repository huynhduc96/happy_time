package main;

import base.*;
import base.jsonObject.JsonObject;
import base.jsonObject.ReadJson;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by huynh on 07-Apr-17.
 */
public class Game extends Application {
    Pane playLayer;
    Scene scene;
    List<Chicken> listChicken = new ArrayList<>();
    List<Pig> listPig = new ArrayList<>();
    List<Cow> listCow = new ArrayList<>();
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();

    private ImageView backgroud = new ImageView();

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

// -------------------------------------------------------------
//        // demo get Json

        ReadJson readJson = new ReadJson();
        readJson.getJson();

        JsonObject object = readJson.getObject();
        System.out.println("user : " + object.getJoUser1().getJoUserName());


// -------------------------------------------------------------
        // add scene
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                addChicken();
            addCow();
//            addPig();
                // movement

                listChicken.forEach(sprite -> sprite.move());
//                listChicken.forEach(sprite ->
//                        sprite.getSound("hungry")
//
//                );
                listCow.forEach(sprite -> sprite.move());
                listCow.forEach(sprite -> sprite.getSound("hungry"));
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
        Random rnd = new Random();
        if (rnd.nextInt(100) != 0) {
            return;
        }


        // create a sprite
        Chicken chicken = new Chicken(playLayer, Settings.CHIKEN, 100, 100, 0, 0, Settings.ANIMAL_SPEED, 0, 1, 1);

        // manage sprite
        listChicken.add(chicken);
    }

    void addPig() {
        Random rnd = new Random();
        if (rnd.nextInt(100) != 0) {
            return;
        }
        // image

        // random speed


        // create a sprite
        Pig pig = new Pig(playLayer, Settings.PIG, 200, 100, 0, 0, Settings.ANIMAL_SPEED, 0, 1, 1);

        // manage sprite
        listPig.add(pig);
    }

    void addCow() {
        Random rnd = new Random();
        if (rnd.nextInt(100) != 0) {
            return;
        }


        // create a sprite
        Cow cow = new Cow(playLayer, Settings.COW, 300, 100, 0, 0, Settings.ANIMAL_SPEED, 0, 1, 1);

        // manage sprite
        listCow.add(cow);
    }
}
