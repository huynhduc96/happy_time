package main;

import base.*;
import base.animalstype.Animal;
import base.animalstype.Chicken;
import base.animalstype.Cow;
import base.animalstype.Pig;
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
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();
    PlayerData playerData = new PlayerData();

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
        scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene( scene);
        primaryStage.show();
//        addChicken();
//        addCow();
        addPig();
        Store store = new Store(playLayer,300,10,0);

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // movement
                store.setOnClick();

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
}
