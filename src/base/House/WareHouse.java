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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    double x;
    double y;
    static double item_x = 25, item_y = 110;


    double w;
    double h;
    ClassLoader classLoader = this.getClass().getClassLoader();
    private List<ImageView> item_image_view= new ArrayList<>();
    private List<Image> item_image= new ArrayList<Image>();
    private static String item_prefix = "res/warehouse/item/";
    private Group root_warehouse;
    private Pane pane_warehouse;
    private Stage stage_warehouse;
    private Scene scene_warehouse;
    private DataPlayer data;
    private HashMap<String, Integer> item_price = new HashMap<>();
    private HashMap<String, Integer> item_space = new HashMap<>();
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

        this.layer.getChildren().add(this.imageView);

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
                pane_warehouse.getChildren().add(0,backgroud_warehouse);
                root_warehouse.getChildren().add(pane_warehouse);

                stage_warehouse.setScene(new Scene(root_warehouse,
                        Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT));

                stage_warehouse.show();
                initViewItem();
            }
        });
    }

    void initViewItem(){
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
        addImageView("food_normal",
                data.getJoUser1().getJoWarehouse().getFoodNormal(),
                "use");
        addImageView("food_special",
                data.getJoUser1().getJoWarehouse().getFoodSpecial(),
                "use");
        addImageView("medicine_normal",
                data.getJoUser1().getJoWarehouse().getMedicineNormal(),
                "use");
        addImageView("medicine_special",
                data.getJoUser1().getJoWarehouse().getMedicineSpecial(),
                "use");
        addImageView("egg",
                data.getJoUser1().getJoWarehouse().getEgg(),
                null);
        addImageView("meat",
                data.getJoUser1().getJoWarehouse().getMeat(),
                null);
        addImageView("milk",
                data.getJoUser1().getJoWarehouse().getMilk(),
                null);

    }

    void addImageView(String name, int count, String option){
        item_image.add(new Image(item_prefix + name + ".png", 20, 20,
                false, false));
        double tmp_x = item_x + 260 * (item_image_view.size() / 13);
        double tmp_y = item_y + 32 *  (item_image_view.size() % 13);
        ImageView tmp = new ImageView(item_image.get(item_image.size() - 1));
        tmp.relocate(tmp_x, tmp_y);
        pane_warehouse.getChildren().add(tmp);
        item_image_view.add(tmp);
    }
    private void loadDataStore() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("res/warehouse/item_info.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Gson gson = new Gson();
        JsonArray data = gson.fromJson(bufferedReader, JsonObject.class).getAsJsonArray("item");
        for (JsonElement i: data) {
            String tmp_name = i.getAsJsonObject().get("name").getAsString();
            int tmp_price = i.getAsJsonObject().get("price").getAsInt();
            int tmp_space = i.getAsJsonObject().get("space").getAsInt();
            item_price.put(tmp_name, tmp_price);
            item_space.put(tmp_name, tmp_space);
        }
    }
    void excuteObject(String name){
        if(data.getJoUser1().getSpaceOut() < item_space.get(name)){
            ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Nông trại của bạn không đủ chỗ hihi");
            dialog.getDialogPane().getButtonTypes().add(loginButtonType);
            dialog.getDialogPane().lookupButton(loginButtonType);
            dialog.show();
            return;
        }
        if (name.equals("chicken")) {
            data.getJoUser1().getJoWarehouse()
                    .setChicken(data.getJoUser1().getJoWarehouse().getChicken() - 1);
            data.getJoUser1().getJoChicken().setTotalNumber(data.getJoUser1()
                    .getJoChicken().getTotalNumber()+1);

        }
        if (name.equals("cow")) {
            data.getJoUser1().getJoWarehouse()
                    .setCow(data.getJoUser1().getJoWarehouse().getCow() - 1);
            data.getJoUser1().getJoCow().setTotalNumber(data.getJoUser1()
                    .getJoCow().getTotalNumber()+1);
        }
        if (name.equals("pig")) {
            data.getJoUser1().getJoWarehouse()
                    .setPig(data.getJoUser1().getJoWarehouse().getPig() - 1);
            data.getJoUser1().getJoPig().setTotalNumber(data.getJoUser1()
                    .getJoPig().getTotalNumber()+1);
        }
        if (name.equals("ostric")) {
            data.getJoUser1().getJoWarehouse()
                    .setOstric(data.getJoUser1().getJoWarehouse().getOstric() - 1);
            data.getJoUser1().getJoOstrich().setTotalNumber(data.getJoUser1()
                    .getJoOstrich().getTotalNumber()+1);
        }
        if (name.equals("grass")) {
            data.getJoUser1().getJoWarehouse()
                    .setGrass(data.getJoUser1().getJoWarehouse().getGrass() - 1);
            data.getJoUser1().getJoGrass().setTotalNumber(data.getJoUser1()
                    .getJoGrass().getTotalNumber()+1);
        }
        if (name.equals("dog")) {
            data.getJoUser1().getJoWarehouse()
                    .setDog(data.getJoUser1().getJoWarehouse().getDog() - 1);
            data.getJoUser1().getJoDog().setTotalNumber(data.getJoUser1()
                    .getJoDog().getTotalNumber()+1);
        }
        if (name.equals("cat")) {
            data.getJoUser1().getJoWarehouse()
                    .setCat(data.getJoUser1().getJoWarehouse().getCat() - 1);
            data.getJoUser1().getJoCat().setTotalNumber(data.getJoUser1()
                    .getJoCat().getTotalNumber()+1);
        }
        data.getJoUser1().setJoSpace(
                data.getJoUser1().getJoSpace() + item_space.get(name)
        );
        data.getJoUser1().setSpaceOut(
                data.getJoUser1().getSpaceOut() - item_space.get(name)
        );
    }

    void saleItem(String name){
        if (name.equals("chicken")) {
            data.getJoUser1().getJoWarehouse()
                    .setChicken(data.getJoUser1().getJoWarehouse().getChicken() - 1);

        }
        if (name.equals("cow")) {
            data.getJoUser1().getJoWarehouse()
                    .setCow(data.getJoUser1().getJoWarehouse().getCow() - 1);

        }
        if (name.equals("pig")) {
            data.getJoUser1().getJoWarehouse()
                    .setPig(data.getJoUser1().getJoWarehouse().getPig() - 1);

        }
        if (name.equals("ostric")) {
            data.getJoUser1().getJoWarehouse()
                    .setOstric(data.getJoUser1().getJoWarehouse().getOstric() - 1);

        }
        if (name.equals("grass")) {
            data.getJoUser1().getJoWarehouse()
                    .setGrass(data.getJoUser1().getJoWarehouse().getGrass() - 1);

        }
        if (name.equals("dog")) {
            data.getJoUser1().getJoWarehouse()
                    .setDog(data.getJoUser1().getJoWarehouse().getDog() - 1);
        }
        if (name.equals("cat")) {
            data.getJoUser1().getJoWarehouse()
                    .setCat(data.getJoUser1().getJoWarehouse().getCat() - 1);
        }
        if (name.equals("food_normal")) {
            data.getJoUser1().getJoWarehouse()
                    .setFoodNormal(data.getJoUser1().getJoWarehouse().getFoodNormal() - 1);
        }
        if (name.equals("food_special")) {
            data.getJoUser1().getJoWarehouse()
                    .setFoodSpecial(data.getJoUser1().getJoWarehouse().getFoodSpecial() - 1);
        }
        if (name.equals("medicine_normal")) {
            data.getJoUser1().getJoWarehouse()
                    .setMedicineNormal(data.getJoUser1().getJoWarehouse().getMedicineNormal() - 1);
        }
        if (name.equals("medicine_special")) {
            data.getJoUser1().getJoWarehouse()
                    .setMedicineSpecial(data.getJoUser1().getJoWarehouse().getMedicineSpecial() - 1);
        }
        if (name.equals("egg")) {
            data.getJoUser1().getJoWarehouse()
                    .setEgg(data.getJoUser1().getJoWarehouse().getEgg() - 1);
        }
        if (name.equals("meat")) {
            data.getJoUser1().getJoWarehouse()
                    .setMeat(data.getJoUser1().getJoWarehouse().getMeat() - 1);
        }
        if (name.equals("milk")) {
            data.getJoUser1().getJoWarehouse()
                    .setMilk(data.getJoUser1().getJoWarehouse().getMilk() - 1);
        }
    }
//

}


