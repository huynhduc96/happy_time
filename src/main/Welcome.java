package main;

import base.Settings;
import base.jsonObject.DataPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Button;
import java.awt.TextField;
import java.io.*;

/**
 * Created by huynh on 06-May-17.
 */
public class Welcome extends Application {
    Pane playLayer;
    Scene scene;
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();
    private DataPlayer player = new DataPlayer();
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


        //    data = playerData.getDataPlayer();

        // lay data player
        //   dataPlayer = playerData.getPlayer();

        // add scene
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);


        primaryStage.setScene(scene);
        primaryStage.show();
        playerData.getdataPlayer();
        player = playerData.getPlayer();
        String name = player.getJoUser1().getJoUserName();

        Image blank_img = new Image(getClass().getResourceAsStream("../res/welcome/name.png"),
                166, 35, false, false);
        ImageView blank = new ImageView();
        blank.setImage(blank_img);
        blank.relocate(317, 420);
        playLayer.getChildren().add(blank);

        Text text = new Text();
        text.setText(name);
        text.relocate(330,430);
        text.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        text.maxHeight(166);
        playLayer.getChildren().add(text);

        Image new_game_img = new Image(getClass().getResourceAsStream("../res/welcome/new.png"),
                130, 35, false, false);
        ImageView ng = new ImageView();
        ng.setImage(new_game_img);
        ng.relocate(502, 470);
        playLayer.getChildren().add(ng);

        ng.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                javafx.scene.control.TextField textField = new javafx.scene.control.TextField();
                textField.setMinWidth(200);
                textField.setMinHeight(35);
                textField.relocate(300, 419);
                playLayer.getChildren().add(textField);
                textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent ke) {
                        if (ke.getCode().equals(KeyCode.ENTER)) {
                            String inputField = textField.getText();
                            System.out.println(inputField);
                            getNewPlayer();
                            player.getJoUser1().setJoUserName(inputField);
                            saveJson(player);
                            Game game = new Game();
                            try {
                                game.start(Game.classStage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            primaryStage.close();
                        }
                    }
                });


            }
        });


        Image cont_game_img = new Image(getClass().getResourceAsStream("../res/welcome/cont.png"),
                166, 35, false, false);
        ImageView cg = new ImageView();
        cg.setImage(cont_game_img);
        cg.relocate(317, 470);
        playLayer.getChildren().add(cg);
        cg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Game game = new Game();
                try {
                    game.start(Game.classStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                primaryStage.close();
            }
        });

        Image help_game_img = new Image(getClass().getResourceAsStream("../res/welcome/help.png"),
                130, 35, false, false);
        ImageView hg = new ImageView();
        hg.setImage(help_game_img);
        hg.relocate(167, 470);
        playLayer.getChildren().add(hg);
        hg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Help");
            }
        });

    }


    void initUI() {
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/start_screen.jpg")));
        backgroud.setImage(image_back);
        backgroud.setFitWidth(Settings.SCENE_WIDTH);
        backgroud.setFitHeight(Settings.SCENE_HEIGHT);
        playLayer.getChildren().add(backgroud);
    }

    void getNewPlayer() {

        Gson gson = new Gson();


        String jsonString = new String();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/res/data/tmp.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        String inputLine;
        try {
            while ((inputLine = bufferedReader.readLine()) != null) {
                jsonString += inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(jsonString);

        player = gson.fromJson(jsonString, DataPlayer.class);
    }

    public void saveJson(DataPlayer obj) {
        Gson gson = new Gson();

        String json;
        json = gson.toJson(obj);
        System.out.println(json.toString());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                System.getProperty("user.dir") + "/src/res/data/user.json", false))) {
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
