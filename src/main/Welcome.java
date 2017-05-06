package main;

import base.Settings;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    }

    void initUI() {
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/start_screen.jpg")));
        backgroud.setImage(image_back);
        backgroud.setFitWidth(Settings.SCENE_WIDTH);
        backgroud.setFitHeight(Settings.SCENE_HEIGHT);
        playLayer.getChildren().add(backgroud);
    }
}
