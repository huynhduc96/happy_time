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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private MediaPlayer mediaPlayer;
    ImageView[] imgHelp = new ImageView[4];
    int page = 0;
    int count_cont;
    //  static Stage classStageW = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        //      classStageW = primaryStage ;
        // make root scene
        Group root = new Group();
        count_cont = 0;
        Game game = new Game();
        // add Pane play for player
        playLayer = new Pane();
        //add background
        initUI();
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);


        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(627);
        primaryStage.setMaxWidth(805);
        primaryStage.setResizable(false);
        primaryStage.show();
        getSoundOpening();
        playerData.getdataPlayer();
        player = playerData.getPlayer();
        String name = player.getJoUser1().getJoUserName();

        Image blank_img = new Image(getClass().getResourceAsStream("../res/welcome/name.png"),
                166, 35, false, false);
        ImageView blank = new ImageView();
        blank.setImage(blank_img);
        blank.relocate(317, 420);
        playLayer.getChildren().add(blank);

        Image info_img = new Image(getClass().getResourceAsStream("../res/welcome/info.png"),
                166, 35, false, false);
        ImageView info = new ImageView();
        info.setImage(info_img);
        info.relocate(317, 470 + 50);
        playLayer.getChildren().add(info);

        Text text = new Text();
        text.setText(name);
        text.relocate(330, 430);
        text.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        text.maxHeight(166);
        text.setFill(Color.ORANGE);
        playLayer.getChildren().add(text);

        Image new_game_img = new Image(getClass().getResourceAsStream("../res/welcome/new.png"),
                130, 35, false, false);
        ImageView ng = new ImageView();
        ng.setImage(new_game_img);
        ng.relocate(502, 470);
        playLayer.getChildren().add(ng);
        addHelpImage();


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
                            mediaPlayer.stop();
                            primaryStage.close();
                        }
                    }
                });


            }
        });

        info.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("lc");
                event.consume();
                Group group_info;
                Pane pane_info;
                Stage stage_info;
                group_info = new Group();
                pane_info = new Pane();
                stage_info = new Stage();
                stage_info.setTitle("Info");
                group_info.getChildren().add(pane_info);

                Image info_bg = new Image(getClass().getResourceAsStream("../res/info.jpg"),
                        800, 600, false, false);
                ImageView bg = new ImageView();
                bg.setImage(info_bg);
                bg.relocate(0, 0);
                group_info.getChildren().add(bg);


                stage_info.setScene(new Scene(group_info, 800, 600));
                stage_info.show();
                stage_info.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        stage_info.close();
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

                try {
                    game.start(Game.classStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mediaPlayer.stop();
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
                event.consume();
                Group group_help;
                Pane pane_help;
                Stage stage_help;
                group_help = new Group();
                pane_help = new Pane();
                stage_help = new Stage();
                stage_help.setTitle("Help");
                group_help.getChildren().add(pane_help);
                javafx.scene.control.Button button_truoc = new javafx.scene.control.Button("Trước");
                button_truoc.setMinWidth(100);
                button_truoc.relocate(170, 490);
                pane_help.getChildren().add(button_truoc);
                Image image = new Image(getClass().getResourceAsStream("../res/help/1.jpg"),
                        640, 480, false, false);
                ImageView bg = new ImageView();
                bg.setImage(image);
                bg.relocate(0, 0);
                pane_help.getChildren().add(bg);

                javafx.scene.control.Button button_sau = new javafx.scene.control.Button("Sau");
                button_sau.setMinWidth(100);
                button_sau.relocate(370, 490);
                pane_help.getChildren().add(button_sau);

                button_truoc.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (page > 0) {
                            pane_help.getChildren().add(imgHelp[page - 1]);
                            pane_help.getChildren().remove(imgHelp[page]);
                            page--;
                        } else {
                            pane_help.getChildren().add(imgHelp[3]);
                            pane_help.getChildren().remove(imgHelp[page]);
                            page = 3;
                        }
                    }
                });

                button_sau.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (page < 3) {
                            pane_help.getChildren().add(imgHelp[page + 1]);
                            pane_help.getChildren().remove(imgHelp[page]);
                            page++;
                        } else {
                            pane_help.getChildren().add(imgHelp[0]);
                            pane_help.getChildren().remove(imgHelp[page]);
                            page = 0;
                        }
                    }
                });


                stage_help.setScene(new Scene(group_help, 640, 550));
                stage_help.show();
                stage_help.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        stage_help.close();
                    }
                });
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

    void getSoundOpening() {
        // so epic
        String file_name = "src/res/sounds2/music_mainmenu.mp3";
        Media sound = new Media(new File(file_name).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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

    void addHelpImage() {
        for (int i = 0; i < 4; i++) {
            String temp = "res/help/" + (i + 1) + ".jpg";
            Image image = new Image(String.valueOf(classLoader.getResource(
                    temp)));
            imgHelp[i] = new ImageView();
            imgHelp[i].setImage(image);
            imgHelp[i].setFitHeight(480);
            imgHelp[i].setFitWidth(640);
            imgHelp[i].relocate(0, 0);
        }
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
