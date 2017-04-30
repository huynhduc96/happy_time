package base.House;

import base.House.PanelType.ProductType;
import base.Settings;
import base.jsonObject.DataPlayer;
import com.google.gson.JsonObject;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by huynh on 21-Apr-17.
 */
public class Store implements House {
    // --------------------------------------------------------------------------------
    /* Class nay phai viet lai toan bo day nhe */
    private ImageView backgroud_store = new ImageView();
    DataPlayer dataPlayer;
    ImageView imageView;
    Image imageStore;
    Pane layer;
    boolean showStore = true;
    double x;
    double y;
    int type;

    double w;
    double h;
    boolean canMove = false;
    Image imageStore_1;
    Image imageStore_2;

    Image imgSyringe_1;
    Image imgSyringe_2;

    ImageView imageView_1 = new ImageView();
    ImageView imageView_2 = new ImageView();

    ImageView imgViewSyringe_1 = new ImageView();
    ImageView imgViewSyringe_2 = new ImageView();


    ClassLoader classLoader = this.getClass().getClassLoader();

    private Group root_store;
    private Pane pane_store;
    private Stage stage_store;
    private Scene scene_store;
    private JsonObject data;
    public Store(Pane layer, double x, double y, double r) {
        this.layer = layer;
        initView();
        this.dataPlayer = dataPlayer;
        this.x = x;
        this.y = y;

        this.imageView = new ImageView();
        this.imageView.setImage(imageStore);
        this.imageView.relocate(x, y);

        this.imageView.setRotate(r);
        this.w = imageStore.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = imageStore.getHeight(); // imageView.getBoundsInParent().getHeight();

        this.layer.getChildren().add(this.imageView);
        this.layer.getChildren().add(imageView_1);
        this.layer.getChildren().add(imageView_2);
        this.layer.getChildren().add(imgViewSyringe_1);
        this.layer.getChildren().add(imgViewSyringe_2);
    }

    void initView() {
        imageStore = new Image(String.valueOf(classLoader.getResource("res/03.dds.png")));
        imageStore_1 = new Image(String.valueOf(classLoader.getResource("res/products/flour/normal.dds.png")));
        imageStore_2 = new Image(String.valueOf(classLoader.getResource("res/products/sourCream/normal.dds.png")));

        imageView_1.setImage(imageStore_1);
        imageView_1.relocate(380, 100);
        imageView_2.setImage(imageStore_2);
        imageView_2.relocate(340, 100);

        imgSyringe_1 = new Image(String.valueOf(classLoader.getResource("res/products/medicine/syringe_1.png")));
        imgSyringe_2 = new Image(String.valueOf(classLoader.getResource("res/products/medicine/syringe_2.png")));

        imgViewSyringe_1.setImage(imgSyringe_1);
        imgViewSyringe_1.setFitHeight(50);
        imgViewSyringe_1.setFitWidth(50);
        imgViewSyringe_1.relocate(380, 100);
        imgViewSyringe_1.setVisible(false);
        imgViewSyringe_2.setImage(imgSyringe_2);
        imgViewSyringe_2.setFitHeight(50);
        imgViewSyringe_2.setFitWidth(50);
        imgViewSyringe_2.relocate(340, 100);
        imgViewSyringe_2.setVisible(false);

    }

    void setShowViewStore(boolean isVisible) {
        if (!isVisible) {
            this.imageView.setVisible(false);
            this.imageView_2.setVisible(false);
            this.imageView_1.setVisible(false);
        } else {
            this.imageView.setVisible(true);
            this.imageView_2.setVisible(true);
            this.imageView_1.setVisible(true);
        }
    }

    void setShowItem(boolean isVisible) {
        if(isVisible){
            this.imgViewSyringe_1.setVisible(false);
            this.imgViewSyringe_2.setVisible(false);
        }else {
            this.imgViewSyringe_1.setVisible(true);
            this.imgViewSyringe_2.setVisible(true);

        }

    }


    @Override
    public void setOnclick(JsonObject data) {
        this.data = data;
        this.imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // bắt sự kiện click
                String file_name = "src/res/sounds2/house_click.mp3";
                Media sound = new Media(new File(file_name).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                root_store = new Group();
                pane_store = new Pane();
                stage_store = new Stage();
                stage_store.setTitle("Cửa hàng");



                Image store_font = new Image(String.valueOf(
                        classLoader.getResource("res/shop/frontof.png")));
                backgroud_store.setImage(store_font);
                pane_store.getChildren().add(0,backgroud_store);
//                ProductType p = new ProductType(pane_store,1,"animal");

                root_store.getChildren().add(pane_store);

                stage_store.setScene(new Scene(root_store, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT));
                stage_store.show();
                ProductType t =new ProductType(pane_store, 1, data);

            }
        });
    }

    @Override
    public void updateDataPlayer(Player player) {
        player.saveJson();
    }
}


