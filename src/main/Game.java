package main;

import base.*;
import base.House.WareHouse;
import base.animalstype.*;
import base.grassstyle.Can;
import base.House.Store;
import base.jsonObject.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

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
    List<Ostrich> listOstrich = new ArrayList<>();
    List<Can> listCan = new ArrayList<>();
    ImageView[] imgHelp = new ImageView[3];
    //get location to load url image
    ClassLoader classLoader = this.getClass().getClassLoader();
    Player playerData = new Player();


    Integer[] location = new Integer[10];
    private ImageView money = new ImageView();
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
    Image image_med_sep;
    Image image_food_nol;
    Image image_food_sep;
    Image image_med_nol;
    Text txt_money;
    Text txt_food_nol;
    Text txt_food_sep;
    Text txt_medi_nol;
    Text txt_medi_sep;

    static Stage classStage = new Stage();
    int page = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        classStage = primaryStage;

        // make root scene
        Group root = new Group();


        // add Pane play for player
        playLayer = new Pane();


        //get data player from json
        playerData.getdataPlayer();
        for (int i = 0; i < 10; i++) {
            location[i] = 0;
        }

        // lay data player
        dataPlayer = playerData.getPlayer();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                playMusic();
            }
        });

        // add scene
        root.getChildren().add(playLayer);
        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        addBack();
        primaryStage.setScene(scene);
        primaryStage.show();
        setClickStage(primaryStage);

        addFirst();
        Store store = new Store(playLayer, 300, 10, 0);
        WareHouse wareHouse = new WareHouse(playLayer, 620, 305, 0);

        addText();

        setOnclickItem();

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // movement
                store.setOnclick(dataPlayer);
                wareHouse.setOnclick(dataPlayer);

                updateData();
                updateText();

                listChicken.forEach(sprite -> sprite.move());
                listCow.forEach(sprite -> sprite.move());
                listPig.forEach(sprite -> sprite.move());
                listOstrich.forEach(sprite -> sprite.move());
//                listChicken.forEach(sprite -> sprite.delayTimeForHealth());

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
                checkDieChiken();
                //checkCowDie();
                //checkOstrichDie();
                // checkPigDie();
            }

        };
        gameLoop.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
    void setClickStage(Stage primaryStage){
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
    }

    void setOnclickItem() {
        img_food_nol.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(new ImageCursor(image_food_nol));
            }
        });
        img_food_sep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(new ImageCursor(image_food_sep));
            }
        });
        img_medi_nol.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(new ImageCursor(image_med_nol));
            }
        });
        img_medi_sep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(new ImageCursor(image_med_sep));
            }
        });
        backgroud.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
    }

    void addText() {
        txt_money = new Text(10, 50, "");
        txt_money.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        txt_money.relocate(650, 40);
        txt_money.setX(50);
        txt_money.setY(50);
        txt_money.setFill(Color.YELLOW);
        playLayer.getChildren().add(txt_money);
        txt_food_nol = new Text(10, 50, "");
        txt_food_nol.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_food_nol.relocate(3, 66);
        txt_food_nol.setX(50);
        txt_food_nol.setY(50);
        txt_food_nol.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_food_nol);

        txt_food_sep = new Text(10, 50, "");
        txt_food_sep.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_food_sep.relocate(3, 110);
        txt_food_sep.setX(50);
        txt_food_sep.setY(50);
        txt_food_sep.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_food_sep);

        txt_medi_nol = new Text(10, 50, "");
        txt_medi_nol.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_medi_nol.relocate(3, 153);
        txt_medi_nol.setX(50);
        txt_medi_nol.setY(50);
        txt_medi_nol.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_medi_nol);

        txt_medi_sep = new Text(10, 50, "");
        txt_medi_sep.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        txt_medi_sep.relocate(3, 196);
        txt_medi_sep.setX(50);
        txt_medi_sep.setY(50);
        txt_medi_sep.setFill(Color.BLACK);
        playLayer.getChildren().add(txt_medi_sep);
    }

    void updateText() {
        txt_money.setText(dataPlayer.getJoUser1().getJoMoney() + " $");
        txt_food_nol.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getFoodNormal() + "");
        txt_food_sep.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getFoodSpecial() + "");
        txt_medi_nol.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getMedicineNormal() + "");
        txt_medi_sep.setText("x " + dataPlayer.getJoUser1().getJoWarehouse().getMedicineSpecial() + "");
    }

    void updateData() {
        updateListCan();
        updateListChicken();
        updateListCow();
        updateListOstrich();
        updateListPig();
    }

    void addFirst() {
        //   playMusic();
        addChicken();
        addCow();
        addPig();
        addCan();
        addOstrich();
        addHelpImage();
    }

    void addBack() {
        Image image_back = new Image(String.valueOf(classLoader.getResource("res/back.png")));
        Image image_back_left = new Image(String.valueOf(classLoader.getResource("res/back_left.png")));
        Image image_back_right = new Image(String.valueOf(classLoader.getResource("res/back_right.png")));
        Image image_help = new Image(String.valueOf(classLoader.getResource("res/help.png")));
        Image image_sel = new Image(String.valueOf(classLoader.getResource("res/select.png")));
        image_food_nol = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/food_normal.png")));
        image_food_sep = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/food_special.png")));
        image_med_nol = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/medicine_normal.png")));
        Image txt_monney = new Image(String.valueOf(classLoader.getResource("res/money_box.png")));
        image_med_sep = new Image(String.valueOf(classLoader.getResource("res/warehouse/item/medicine_special.png")));
        Text txt_help = new Text("Help");
        txt_help.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        txt_help.relocate(77, 394);
        txt_help.setFill(Color.YELLOW);
        money.setImage(txt_monney);
        money.setFitHeight(60);
        money.setFitWidth(120);
        money.relocate(665, 20);
        money.setRotate(0);
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
        playLayer.getChildren().add(money);
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

    void updateListCan() {
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
            ListGras gras = new ListGras(tmp,1);
            listGrass.add(gras);

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

    void addHelpImage() {
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
    }

    private void checkDieChiken() {
        for (int i = 0; i < listChicken.size(); i++) {
            if (listChicken.get(i).getSick() < listChicken.get(i).timeDie) {
                listChicken.get(i).setSick( listChicken.get(i).getSick()+1);
                System.out.println(listChicken.get(i).getSick());
            } else {
            //    listChicken.get(i).setDeath(1);
                listChicken.get(i).setHealth(0);
                // dataPlayer.getJoUser1().getJoChicken().getListChicken().remove(i);
                // listChicken.get(i).remove();
                // dataPlayer.getJoUser1().getJoChicken().setTotalNumber(dataPlayer.getJoUser1().getJoChicken().getTotalNumber() - 1);

//                listChicken.get(i).canMove = false;
                break;

                //   addChicken();
            }
        }
    }

    private void checkCowDie() {
        for (int i = 0; i < listCow.size(); i++) {
            if (listCow.get(i).getSick() < listCow.get(i).timeDie) {
                listCow.get(i).setSick(listCow.get(i).getSick() + 1);

            }
            else {
                listCow.get(i).setHealth(0);
                listCow.get(i).setDeath(1);
            }
        }
    }

    private void checkPigDie() {
        for (int i = 0; i < listPig.size(); i++) {
            if (listPig.get(i).getSick() < listPig.get(i).timeDie) {
                listPig.get(i).setSick(listPig.get(i).getSick() + 1);

            }
            else {
                listPig.get(i).setHealth(0);
                listPig.get(i).setDeath(1);
            }
        }
    }

    private void checkOstrichDie() {
        for (int i = 0; i < listOstrich.size(); i++) {
            if (listOstrich.get(i).getSick() < listOstrich.get(i).timeDie) {
                listOstrich.get(i).setSick(listOstrich.get(i).getSick() + 1);

            }
            else {
                listOstrich.get(i).setHealth(0);
                listOstrich.get(i).setDeath(1);
            }
        }
    }


}
