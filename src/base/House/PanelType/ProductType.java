package base.House.PanelType;

import base.jsonObject.DataPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.management.Notification;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nguyen Chinh on 4/28/2017.
 */
public class ProductType {
    Pane layer;
    ClassLoader classLoader = this.getClass().getClassLoader();
    private List<Image> type_image = new ArrayList<>();
    private List<ImageView> type_image_view = new ArrayList<>();
    static private String[] type_product = {"animal", "pet", "skin"};
    static private List<List<String>> icon_product = new ArrayList<>();
    static private List<List<Integer>> icon_price = new ArrayList<>();
    static private List<List<Integer>> icon_space = new ArrayList<>();


    private int cur_type_index;
    private List<ImageView> productImgView = new ArrayList<ImageView>();
    private List<Image> productImage = new ArrayList<>();
    static private double type_x = 660, type_y = 80, type_distance = 120;
    static private double item_x = 65, item_y = 80, item_distance_y = 120, item_distance_x = 150;
    static private Image product_enable_image = new Image(
            "res/shop/type_icon/product_enabled.png",
            140, 125, false, false);
    static private ImageView prodduct_enable_image_view = new ImageView(
            product_enable_image);
    static private Image enable_image = new Image("res/shop/type_icon/enabled.png",
            135, 135, false, false);
    static private ImageView enable_image_view = new ImageView(enable_image);
    private int cur_product = -1;
    private DataPlayer player;
    private Text money, space;
    private Button buy;

    private void loadDataStore() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/res/shop/product.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(bufferedReader, JsonObject.class);
        for (int i = 0; i < 3; i++) {
            JsonArray a = data.get(type_product[i]).getAsJsonArray();
            List<String> tmp_name = new ArrayList<>();
            List<Integer> tmp_price = new ArrayList<>();
            List<Integer> tmp_space = new ArrayList<>();
            for (JsonElement j : a) {
                tmp_name.add(j.getAsJsonObject().get("name").getAsString());
                tmp_price.add(j.getAsJsonObject().get("price").getAsInt());
                tmp_space.add(j.getAsJsonObject().get("space").getAsInt());
            }
            icon_product.add(tmp_name);
            icon_price.add(tmp_price);
            icon_space.add(tmp_space);
        }
    }

    public ProductType(Pane layer, int index, DataPlayer player) {
        loadDataStore();
        String imagePath = "res/shop/type_icon/" + type_product[index - 1] + ".png";
        this.layer = layer;
        this.cur_type_index = index;
        this.player = player;
        initView();
        showCurType();
        setOnClickType();

    }

    private void initView() {
        type_image.clear();
//        type_image_view.clear();
        String type_prefix = "res/shop/type_icon/";
        int curIndex = layer.getChildren().size();
        showDataPlayer();
        for (int i = 0; i < 3; i++) {
            type_image.add(new Image(type_prefix + type_product[i] + ".png",
                    75, 75, false, false));
            type_image_view.add(new ImageView(type_image.get(i)));
            type_image_view.get(i).relocate(type_x, type_y + type_distance * i);
            this.layer.getChildren().add(curIndex, type_image_view.get(i));

        }

    }

    void showDataPlayer() {
        int currentMoney = player.getJoUser1().getJoMoney();
        System.out.println(currentMoney);
        int currentSpace = player.getJoUser1().getJoSpace();
        System.out.println(currentSpace);
        money = new Text("" + currentMoney);
        space = new Text(("" + currentSpace));
        money.setFont(new Font(40));
        money.relocate(500, 460);
        money.setFill(Color.YELLOW);
        space.setFont(new Font(40));
        space.relocate(655, 460);
        space.setFill(Color.GREEN);
        this.layer.getChildren().add(money);
        this.layer.getChildren().add(space);
    }


    private void showCurType() {
        int postion = this.layer.getChildren().lastIndexOf(
                this.type_image_view.get(cur_type_index - 1));

        enable_image_view.relocate(type_x - 30,
                type_y + type_distance * (this.cur_type_index - 1) - 20);
        this.layer.getChildren().add(postion, enable_image_view);
        showItemCurType();
        setOnClickBuy();
    }

    private void showItemCurType() {
        String prefix = "res/shop/type_icon/" + type_product[this.cur_type_index - 1] + "/";

        for (int i = 0; i < this.icon_product.get(this.cur_type_index - 1).size(); i++) {
            int longtitude = i % 4;
            int width = i / 4;
            productImage.add(new Image(prefix +
                    icon_product.get(this.cur_type_index - 1).get(i) + ".png",
                    85, 85, false, false));
            productImgView.add(new ImageView(productImage.get(i)));
            productImgView.get(i).relocate(item_x + item_distance_x * longtitude,
                    item_y + item_distance_y * width);
            this.layer.getChildren().add(productImgView.get(i));
        }
        showCurProduct();
    }

    public void setOnClickType() {
        for (Iterator<ImageView> it = type_image_view.iterator(); it.hasNext(); ) {
            ImageView tmp = it.next();
            tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                      @Override
                                      public void handle(MouseEvent event) {
                                          String file_name = "src/res/sounds2/item_add.mp3";
                                          Media sound = new Media(new File(file_name).toURI().toString());
                                          MediaPlayer mediaPlayer = new MediaPlayer(sound);
                                          mediaPlayer.play();
                                          int size = layer.getChildren().size();
                                          cur_type_index = type_image_view.indexOf(tmp) + 1;
                                          layer.getChildren().remove(1, size);
                                          new ProductType(layer, cur_type_index, player);
                                      }
                                  }
            );
            setOnClickProduct();
        }
    }

    private void showCurProduct() {
        if (cur_product == -1 ||
                cur_product >= icon_product.get(this.cur_type_index - 1).size()) return;
        int postion = this.layer.getChildren().lastIndexOf(
                productImgView.get(cur_product));
        int longtitude = cur_product % 4;
        int width = cur_product / 4;
        prodduct_enable_image_view.relocate(item_x - 30 + (item_distance_x) * longtitude,
                item_y - 12 + item_distance_y * width);
        this.layer.getChildren().add(postion, prodduct_enable_image_view);
        showDataPlayer();
        int curMoney = icon_price.get(this.cur_type_index - 1).get(cur_product);
        int curSpace = icon_space.get(this.cur_type_index - 1).get(cur_product);
        money = new Text("" + curMoney);
        space = new Text(("" + curSpace));
        money.setFont(new Font(40));
        money.relocate(70, 460);
        money.setFill(Color.YELLOW);
        space.setFont(new Font(40));
        space.relocate(250, 460);
        space.setFill(Color.GREEN);
        this.layer.getChildren().add(money);
        this.layer.getChildren().add(space);
        Image Coin = new Image("res/shop/coin.png",
                80, 80, false, false);
        buy = new Button();
        buy.setGraphic(new ImageView(Coin));
        buy.relocate(355, 435);


        this.layer.getChildren().add(buy);
        setOnClickBuy();
    }

    private void setOnClickProduct() {
        for (Iterator<ImageView> it = productImgView.iterator(); it.hasNext(); ) {
            ImageView tmp = it.next();
            tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                      @Override
                                      public void handle(MouseEvent event) {
                                          String file_name = "src/res/sounds2/product_add.mp3";
                                          Media sound = new Media(new File(file_name).toURI().toString());
                                          MediaPlayer mediaPlayer = new MediaPlayer(sound);
                                          mediaPlayer.play();
                                          int size = layer.getChildren().size();
                                          cur_product = productImgView.indexOf(tmp);
                                          layer.getChildren().remove(5, size);
                                          showItemCurType();
                                          setOnClickBuy();
                                      }
                                  }
            );
        }
    }

    private void setOnClickBuy() {
        if (cur_product == -1 || cur_product >= icon_product.get(cur_type_index - 1).size())
            return;
        buy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int prodMoney = icon_price.get(cur_type_index - 1).get(cur_product);
                int prodSpace = icon_space.get(cur_type_index - 1).get(cur_product);
                if (prodMoney > player.getJoUser1().getJoMoney()) {
                    ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setContentText("Bạn đéo đủ tiền hihi");
                    dialog.getDialogPane().getButtonTypes().add(loginButtonType);
                    dialog.getDialogPane().lookupButton(loginButtonType);
                    dialog.show();
                    return;
                }
                if (prodSpace > player.getJoUser1().getJoSpace()) {
                    ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
                    Dialog<String> dialog = new Dialog<>();
                    dialog.setContentText("Kho của bạn đếch đủ chỗ hihi");
                    dialog.getDialogPane().getButtonTypes().add(loginButtonType);
                    dialog.getDialogPane().lookupButton(loginButtonType);
                    dialog.show();
                    return;
                }
                String file_name = "src/res/sounds2/action_upgrade.mp3";
                Media sound = new Media(new File(file_name).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                //       player.addProperty("jo_space", player.get("jo_space").getAsInt() - prodSpace);
                player.getJoUser1().setJoSpace(player.getJoUser1().getJoSpace() - prodSpace);
                //        player.addProperty("jo_money", player.get("jo_money").getAsInt() - prodMoney);
                player.getJoUser1().setJoMoney(player.getJoUser1().getJoMoney() - prodMoney);
                String nameItem = icon_product.get(cur_type_index - 1).get(cur_product);
                if (nameItem.equals("chicken")) {
                    player.getJoUser1().getJoWarehouse()
                            .setChicken(player.getJoUser1().getJoWarehouse().getChicken() + 1);
                }
                if (nameItem.equals("pig")) {
                    player.getJoUser1().getJoWarehouse()
                            .setPig(player.getJoUser1().getJoWarehouse().getPig() + 1);
                }
                if (nameItem.equals("cow")) {
                    player.getJoUser1().getJoWarehouse()
                            .setCow(player.getJoUser1().getJoWarehouse().getCow() + 1);
                }
                if (nameItem.equals("grass")) {
                    player.getJoUser1().getJoWarehouse()
                            .setGrass(player.getJoUser1().getJoWarehouse().getGrass() + 1);
                }
                if (nameItem.equals("ostric")) {
                    player.getJoUser1().getJoWarehouse()
                            .setOstric(player.getJoUser1().getJoWarehouse().getOstric() + 1);
                }
                if (nameItem.equals("cat")) {
                    player.getJoUser1().getJoWarehouse()
                            .setCat(player.getJoUser1().getJoWarehouse().getCat() + 1);
                }
                if (nameItem.equals("dog")) {
                    player.getJoUser1().getJoWarehouse()
                            .setDog(player.getJoUser1().getJoWarehouse().getDog() + 1);
                }
                if (nameItem.equals("food_normal")) {
                    player.getJoUser1().getJoWarehouse()
                            .setFoodNormal(player.getJoUser1().getJoWarehouse().getFoodNormal() + 1);
                }
                if (nameItem.equals("food_special")) {
                    player.getJoUser1().getJoWarehouse()
                            .setFoodSpecial(player.getJoUser1().getJoWarehouse().getFoodSpecial() + 1);
                }
                if (nameItem.equals("medicine_normal")) {
                    player.getJoUser1().getJoWarehouse()
                            .setMedicineNormal(player.getJoUser1().getJoWarehouse().getMedicineNormal() + 1);
                }
                if (nameItem.equals("medicine_special")) {
                    player.getJoUser1().getJoWarehouse()
                            .setMedicineSpecial(player.getJoUser1().getJoWarehouse().getMedicineSpecial() + 1);
                }
                if (nameItem.equals("panda")) {
                    player.getJoUser1().getJoWarehouse()
                            .setPanda(player.getJoUser1()
                                    .getJoWarehouse().getPanda() + 1);
                }

                int size = layer.getChildren().size();
                layer.getChildren().remove(5, size);
                showItemCurType();
            }
        });

    }
}
