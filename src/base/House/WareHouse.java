package base.House;


import base.Settings;
import base.jsonObject.DataPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Thanh Chinh on 21-Apr-17.
 */
public class WareHouse implements House {
    private ImageView backgroud_warehouse = new ImageView();
    DataPlayer dataPlayer;
    ImageView imageView;
    Image imageWarehouse;
    Pane layer;
    boolean tmp = true;
    double x;
    double y;
    static double item_x = 25, item_y = 110;


    double w;
    double h;
    ClassLoader classLoader = this.getClass().getClassLoader();
    private List<ImageView> item_image_view;
    private List<Image> item_image;
    private List<Button> sale_buton;
    private List<Button> option_buton;
    private List<Text> count_text = new ArrayList<>();
    private static String item_prefix = "res/warehouse/item/";
    private Group root_warehouse;
    private Pane pane_warehouse;
    private Stage stage_warehouse;
    private Scene scene_warehouse;
    private DataPlayer data;
    private HashMap<String, Integer> item_price = new HashMap<>();
    private HashMap<String, Integer> item_space = new HashMap<>();
    private Text cur_money;
    private Text cur_space;
    private  Text cur_jo_space;

    public WareHouse(Pane layer, double x, double y, double r) {
        this.layer = layer;
        initView();
        this.dataPlayer = dataPlayer;
        this.x = x;
        this.y = y;

        this.imageView = new ImageView();
        this.imageView.setImage(imageWarehouse);
        this.imageView.relocate(x, y);
        this.imageView.setFitWidth(170);
        this.imageView.setFitHeight(170);

        this.imageView.setRotate(r);
        this.w = imageWarehouse.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = imageWarehouse.getHeight(); // imageView.getBoundsInParent().getHeight();

        this.layer.getChildren().add( this.imageView);

    }

    void initView() {
        imageWarehouse = new Image(String.valueOf(classLoader.getResource("res/warehouse/testnhakho.png")));
    }


    @Override
    public void setOnclick(DataPlayer data) {
        this.data = data;
        this.imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tmp == true) {
                    item_image_view = new ArrayList<>();
                    item_image = new ArrayList<Image>();
                    sale_buton = new ArrayList<>();
                    option_buton = new ArrayList<>();

//                // bắt sự kiện click
                    String file_name = "src/res/sounds2/house_click.mp3";
                    Media sound = new Media(new File(file_name).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(sound);
                    mediaPlayer.play();
                    root_warehouse = new Group();
                    pane_warehouse = new Pane();
                    stage_warehouse = new Stage();
                    stage_warehouse.setTitle("Nhà Kho");
                    Image store_font = new Image(String.valueOf(
                            classLoader.getResource("res/warehouse/deport.png")));
                    backgroud_warehouse.setImage(store_font);
                    pane_warehouse.getChildren().add(0, backgroud_warehouse);
                    root_warehouse.getChildren().add(pane_warehouse);

                    stage_warehouse.setScene(new Scene(root_warehouse,
                            Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT));
                    stage_warehouse.setMaxHeight(627);
                    stage_warehouse.setMaxWidth(805);
                    stage_warehouse.setResizable(false);
                    stage_warehouse.show();
                    initViewItem();
                    setCloseStage(stage_warehouse);
                }
                tmp = false;
            }
        });
    }

    private void initViewItem() {
        cur_money = new Text("" + data.getJoUser1().getJoMoney() + " $");
        cur_space = new Text("" + data.getJoUser1().getSpaceOut());
        cur_jo_space = new Text("" + data.getJoUser1().getJoSpace());
        cur_space.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        cur_space.setFill(Color.GREENYELLOW);
        cur_space.relocate(620, 490);
        cur_money.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        cur_money.setFill(Color.YELLOW);
        cur_money.relocate(620, 420);
        cur_jo_space.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
        cur_jo_space.setFill(Color.GREEN);
        cur_jo_space.relocate(620, 350);
        pane_warehouse.getChildren().add(cur_money);
        pane_warehouse.getChildren().add(cur_space);
        pane_warehouse.getChildren().add(cur_jo_space);
        addImageView("cow",
                data.getJoUser1().getJoWarehouse().getCow(),
                "getOut");
        addImageView("chicken",
                data.getJoUser1().getJoWarehouse().getChicken(),
                "getOut");
        addImageView("pig",
                data.getJoUser1().getJoWarehouse().getPig(),
                "getOut");
        addImageView("grass",
                data.getJoUser1().getJoWarehouse().getGrass(),
                "getOut");
        addImageView("ostric",
                data.getJoUser1().getJoWarehouse().getOstric(),
                "getOut");
        addImageView("cat",
                data.getJoUser1().getJoWarehouse().getCat(),
                "getOut");
        addImageView("dog",
                data.getJoUser1().getJoWarehouse().getDog(),
                "getOut");
        addImageView("panda",
                data.getJoUser1().getJoWarehouse().getPanda(),
                "getOut");
        addImageView("food_normal",
                data.getJoUser1().getJoWarehouse().getFoodNormal(),
                null);
        addImageView("food_special",
                data.getJoUser1().getJoWarehouse().getFoodSpecial(),
                null);
        addImageView("medicine_normal",
                data.getJoUser1().getJoWarehouse().getMedicineNormal(),
                null);
        addImageView("medicine_special",
                data.getJoUser1().getJoWarehouse().getMedicineSpecial(),
                null);
        addImageView("egg",
                data.getJoUser1().getJoWarehouse().getEgg(),
                null);
        addImageView("meat",
                data.getJoUser1().getJoWarehouse().getMeat(),
                null);
        addImageView("milk",
                data.getJoUser1().getJoWarehouse().getMilk(),
                null);
        addImageView("grass_product",
                data.getJoUser1().getJoWarehouse().getGrass_product(),
                null);
        addImageView("feather",
                data.getJoUser1().getJoWarehouse().getFeather(),
                null);
    }

    void addImageView(String name, int count, String option) {
        final int[] cout_1 = {count};
        loadDataStore();
        item_image.add(new Image(item_prefix + name + ".png", 25, 25,
                false, false));
        double tmp_x = item_x + 260 * (item_image_view.size() / 13);
        double tmp_y = item_y + 31.5 * (item_image_view.size() % 13);
        ImageView tmp = new ImageView(item_image.get(item_image.size() - 1));

        Text tmp_txt = new Text("x" + count);
        tmp_txt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        tmp_txt.setFill(Color.WHEAT);
        tmp_txt.relocate(tmp_x + 31, tmp_y + 6);
        tmp.relocate(tmp_x, tmp_y);

        Button sale_tmp = new Button();
        sale_tmp.setText("sale");
        sale_tmp.relocate(tmp_x + 130, tmp_y);
        if (count <= 0) sale_tmp.setVisible(false);

        Button op_tmp = new Button();
        if (option != null) {

            op_tmp.setText(option);
            op_tmp.relocate(tmp_x + 170, tmp_y);
            if (cout_1[0] == 0) op_tmp.setVisible(false);
            op_tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    int res = excuteObject(name);
                    if (res == 0) {
                        cout_1[0] = 0;
                        op_tmp.setVisible(false);
                        sale_tmp.setVisible(false);
                    }

                    if (res != -1) {
                        cout_1[0] = res;
                        tmp_txt.setText("x" + cout_1[0]);
                        cur_money.setText(data.getJoUser1().getJoMoney() + " $");
                        cur_space.setText("" + data.getJoUser1().getSpaceOut());
                        cur_jo_space.setText("" + data.getJoUser1().getJoSpace());
                    }
                }
            });

            option_buton.add(op_tmp);
            pane_warehouse.getChildren().add(op_tmp);
        }
        sale_tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                cout_1[0] = saleItem(name);
                if (cout_1[0] <= 0) {
                    sale_tmp.setVisible(false);
                    if (option != null) op_tmp.setVisible(false);
                }
                tmp_txt.setText("x" + cout_1[0]);
                cur_money.setText(data.getJoUser1().getJoMoney() + " $");
                cur_space.setText("" + data.getJoUser1().getSpaceOut());
                cur_jo_space.setText("" + data.getJoUser1().getJoSpace());
            }
        });
        pane_warehouse.getChildren().add(sale_tmp);
        pane_warehouse.getChildren().add(tmp);
        pane_warehouse.getChildren().add(tmp_txt);
        item_image_view.add(tmp);
        count_text.add(tmp_txt);
        sale_buton.add(sale_tmp);
    }

    private void loadDataStore() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/res/warehouse/item_info.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Gson gson = new Gson();
        JsonArray data = gson.fromJson(bufferedReader, JsonObject.class).getAsJsonArray("item");
        for (JsonElement i : data) {
            String tmp_name = i.getAsJsonObject().get("name").getAsString();
            int tmp_price = i.getAsJsonObject().get("price").getAsInt();
            int tmp_space = i.getAsJsonObject().get("space").getAsInt();
            item_price.put(tmp_name, tmp_price);
            item_space.put(tmp_name, tmp_space);
        }
    }

    private int excuteObject(String name) {
        int result = 0;
        if (data.getJoUser1().getSpaceOut() < item_space.get(name)) {
            ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Nông trại của bạn không đủ chỗ hihi");
            dialog.getDialogPane().getButtonTypes().add(loginButtonType);
            dialog.getDialogPane().lookupButton(loginButtonType);
            dialog.show();
            return -1;
        }
        if (name.equals("chicken")) {
            data.getJoUser1().getJoWarehouse()
                    .setChicken(data.getJoUser1().getJoWarehouse().getChicken() - 1);
            data.getJoUser1().getJoChicken().setTotalNumber(data.getJoUser1()
                    .getJoChicken().getTotalNumber() + 1);

            result = data.getJoUser1().getJoWarehouse().getChicken();
        }
        if (name.equals("cow")) {
            data.getJoUser1().getJoWarehouse()
                    .setCow(data.getJoUser1().getJoWarehouse().getCow() - 1);
            data.getJoUser1().getJoCow().setTotalNumber(data.getJoUser1()
                    .getJoCow().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getCow();
        }
        if (name.equals("pig")) {
            data.getJoUser1().getJoWarehouse()
                    .setPig(data.getJoUser1().getJoWarehouse().getPig() - 1);
            data.getJoUser1().getJoPig().setTotalNumber(data.getJoUser1()
                    .getJoPig().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getPig();
        }
        if (name.equals("ostric")) {
            data.getJoUser1().getJoWarehouse()
                    .setOstric(data.getJoUser1().getJoWarehouse().getOstric() - 1);
            data.getJoUser1().getJoOstrich().setTotalNumber(data.getJoUser1()
                    .getJoOstrich().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getOstric();
        }
        if (name.equals("grass")) {
            if (data.getJoUser1().getJoGrass().getTotalNumber() >= 10) {
                ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
                Dialog<String> dialog = new Dialog<>();
                dialog.setContentText("Trồng nhiều cần là phạm pháp hihi");
                dialog.getDialogPane().getButtonTypes().add(loginButtonType);
                dialog.getDialogPane().lookupButton(loginButtonType);
                dialog.show();
                return -1;
            }
            data.getJoUser1().getJoWarehouse()
                    .setGrass(data.getJoUser1().getJoWarehouse().getGrass() - 1);
            data.getJoUser1().getJoGrass().setTotalNumber(data.getJoUser1()
                    .getJoGrass().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getGrass();
            return result;
        }
        if (name.equals("dog")) {
            data.getJoUser1().getJoWarehouse()
                    .setDog(data.getJoUser1().getJoWarehouse().getDog() - 1);
            data.getJoUser1().getJoDog().setTotalNumber(data.getJoUser1()
                    .getJoDog().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getDog();
        }
        if (name.equals("panda")) {
            data.getJoUser1().getJoWarehouse()
                    .setPanda(data.getJoUser1().getJoWarehouse().getPanda() - 1);
            data.getJoUser1().getJoPanda().setTotalNumber(data.getJoUser1()
                    .getJoPanda().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getPanda();
        }
        if (name.equals("cat")) {
            data.getJoUser1().getJoWarehouse()
                    .setCat(data.getJoUser1().getJoWarehouse().getCat() - 1);
            data.getJoUser1().getJoCat().setTotalNumber(data.getJoUser1()
                    .getJoCat().getTotalNumber() + 1);
            result = data.getJoUser1().getJoWarehouse().getCat();
        }
        data.getJoUser1().setJoSpace(
                data.getJoUser1().getJoSpace() + item_space.get(name)
        );
        data.getJoUser1().setSpaceOut(
                data.getJoUser1().getSpaceOut() - item_space.get(name)
        );
        return result;
    }

    private int saleItem(String name) {
        int result = -1;
        if (name.equals("chicken")) {
            data.getJoUser1().getJoWarehouse()
                    .setChicken(data.getJoUser1().getJoWarehouse().getChicken() - 1);
            result = data.getJoUser1().getJoWarehouse().getChicken();

        }
        if (name.equals("cow")) {
            data.getJoUser1().getJoWarehouse()
                    .setCow(data.getJoUser1().getJoWarehouse().getCow() - 1);
            result = data.getJoUser1().getJoWarehouse().getCow();

        }
        if (name.equals("pig")) {
            data.getJoUser1().getJoWarehouse()
                    .setPig(data.getJoUser1().getJoWarehouse().getPig() - 1);
            result = data.getJoUser1().getJoWarehouse().getPig();
        }
        if (name.equals("ostric")) {
            data.getJoUser1().getJoWarehouse()
                    .setOstric(data.getJoUser1().getJoWarehouse().getOstric() - 1);
            result = data.getJoUser1().getJoWarehouse().getOstric();
        }
        if (name.equals("grass")) {
            data.getJoUser1().getJoWarehouse()
                    .setGrass(data.getJoUser1().getJoWarehouse().getGrass() - 1);
            result = data.getJoUser1().getJoWarehouse().getGrass();
        }
        if (name.equals("dog")) {
            data.getJoUser1().getJoWarehouse()
                    .setDog(data.getJoUser1().getJoWarehouse().getDog() - 1);
            result = data.getJoUser1().getJoWarehouse().getDog();
        }
        if (name.equals("panda")) {
            data.getJoUser1().getJoWarehouse()
                    .setPanda(data.getJoUser1().getJoWarehouse().getPanda() - 1);
            result = data.getJoUser1().getJoWarehouse().getDog();
        }
        if (name.equals("cat")) {
            data.getJoUser1().getJoWarehouse()
                    .setCat(data.getJoUser1().getJoWarehouse().getCat() - 1);
            result = data.getJoUser1().getJoWarehouse().getCat();
        }
        if (name.equals("food_normal")) {
            data.getJoUser1().getJoWarehouse()
                    .setFoodNormal(data.getJoUser1().getJoWarehouse().getFoodNormal() - 1);
            result = data.getJoUser1().getJoWarehouse().getFoodNormal();
        }
        if (name.equals("food_special")) {
            data.getJoUser1().getJoWarehouse()
                    .setFoodSpecial(data.getJoUser1().getJoWarehouse().getFoodSpecial() - 1);
            result = data.getJoUser1().getJoWarehouse().getFoodSpecial();
        }
        if (name.equals("medicine_normal")) {
            data.getJoUser1().getJoWarehouse()
                    .setMedicineNormal(data.getJoUser1().getJoWarehouse().getMedicineNormal() - 1);
            result = data.getJoUser1().getJoWarehouse().getMedicineNormal();
        }
        if (name.equals("medicine_special")) {
            data.getJoUser1().getJoWarehouse()
                    .setMedicineSpecial(data.getJoUser1().getJoWarehouse().getMedicineSpecial() - 1);
            result = data.getJoUser1().getJoWarehouse().getMedicineSpecial();
        }
        if (name.equals("egg")) {
            data.getJoUser1().getJoWarehouse()
                    .setEgg(data.getJoUser1().getJoWarehouse().getEgg() - 1);
            result = data.getJoUser1().getJoWarehouse().getEgg();
        }
        if (name.equals("meat")) {
            data.getJoUser1().getJoWarehouse()
                    .setMeat(data.getJoUser1().getJoWarehouse().getMeat() - 1);
            result = data.getJoUser1().getJoWarehouse().getMeat();
        }
        if (name.equals("milk")) {
            data.getJoUser1().getJoWarehouse()
                    .setMilk(data.getJoUser1().getJoWarehouse().getMilk() - 1);
            result = data.getJoUser1().getJoWarehouse().getMilk();

        }
        if (name.equals("feather")) {
            data.getJoUser1().getJoWarehouse()
                    .setFeather(data.getJoUser1().getJoWarehouse().getFeather() - 1);
            result = data.getJoUser1().getJoWarehouse().getFeather();
        }
        if (name.equals("grass_product")) {
            data.getJoUser1().getJoWarehouse()
                    .setGrass_product(data.getJoUser1().getJoWarehouse().getGrass_product() - 1);
            result = data.getJoUser1().getJoWarehouse().getGrass_product();
        }
        data.getJoUser1().setJoSpace(
                data.getJoUser1().getJoSpace() + item_space.get(name)
        );
        data.getJoUser1().setJoMoney(
                data.getJoUser1().getJoMoney() + item_price.get(name)
        );

        return result;
    }

    void setCloseStage(Stage stage_store) {
        stage_store.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                tmp = true;
                System.out.println("Dafuq" + tmp);
            }
        });
    }

}


