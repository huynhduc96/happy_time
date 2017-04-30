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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    JsonObject data;

    private ImageView backgroud = new ImageView();

    private void playMusic(){
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
        data = playerData.getDataPlayer();


      // add scene
        root.getChildren().add(playLayer);
        scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene( scene);
        primaryStage.show();
        playMusic();
        addChicken();
        addCow();
        addPig();
        addCan();
        Store store = new Store(playLayer,300,10,0);

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // movement
                store.setOnclick(data);
                store.updateDataPlayer(playerData);

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

                removeSprites( listChicken);
                removeSprites( listPig);
                removeSprites( listCow);
            }

        };
        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void removeSprites(  List<? extends Animal> spriteList) {
        Iterator<? extends Animal> iter = spriteList.iterator();
        while( iter.hasNext()) {
            Animal sprite = iter.next();

            if( sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    void addChicken(){

        // create a sprite
        Chicken chicken = new Chicken( playLayer, Settings.CHIKEN, 200, 200, 0, 0, 0,
                0, 1,1);
        // manage sprite
        listChicken.add( chicken);
    }

    void addPig(){

        // create a sprite
        Pig pig = new Pig( playLayer, Settings.PIG, 200, 300, 0, 0, Settings.ANIMAL_SPEED, 0, 1,1);

        // manage sprite
        listPig.add( pig);
    }

    void addCow(){

        // create a sprite
        Cow cow = new Cow( playLayer, Settings.COW, 300, 200, 0, 0, Settings.ANIMAL_SPEED, 0, 1,1);

        // manage sprite
        listCow.add(cow);
    }

    void addCan(){

        // create a sprite
        Can can = new Can( playLayer, Settings.CAN, 400, 300, 0, 0, Settings.ANIMAL_SPEED, 0, 1,1);

        // manage sprite
        listCan.add(can);
    }
}
