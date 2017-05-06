package main;

import base.Settings;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Button;

/**
 * Created by huynh on 06-May-17.
 */
public class Welcome extends Application {
    Pane playLayer;
    Scene scene;
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();
    private ImageView backgroud = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // make root scene
        Group root = new Group();


        // add Pane play for player
        playLayer = new Pane();
        //add background
        initUI();

        //get data player from json
        playerData.getdataPlayer();

        //    data = playerData.getDataPlayer();

        // lay data player
        //   dataPlayer = playerData.getPlayer();

        // add scene
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        Image new_game_img = new Image(getClass().getResourceAsStream("../res/welcome/new.png"),
                130,35,false,false);
        ImageView ng = new ImageView();
        ng.setImage(new_game_img);
        ng.relocate(502,470);
        playLayer.getChildren().add(ng);

        Image cont_game_img = new Image(getClass().getResourceAsStream("../res/welcome/cont.png"),
                166,35,false,false);
        ImageView cg = new ImageView();
        cg.setImage(cont_game_img);
        cg.relocate(317,470);
        playLayer.getChildren().add(cg);

        Image help_game_img = new Image(getClass().getResourceAsStream("../res/welcome/help.png"),
                130,35,false,false);
        ImageView hg = new ImageView();
        hg.setImage(help_game_img);
        hg.relocate(167,470);
        playLayer.getChildren().add(hg);

    }

    void initUI() {
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/start_screen.jpg")));
        backgroud.setImage(image_back);
        backgroud.setFitWidth(Settings.SCENE_WIDTH);
        backgroud.setFitHeight(Settings.SCENE_HEIGHT);
        playLayer.getChildren().add(backgroud);
    }
}
