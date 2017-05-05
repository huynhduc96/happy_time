package base.House;

import base.House.PanelType.ProductType;
import base.Settings;
import base.jsonObject.DataPlayer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.Player;

import java.io.File;

/**
 * Created by Thanh Chinh on 21-Apr-17.
 */
public class WareHouse implements House {
    // --------------------------------------------------------------------------------
    /* Class nay phai viet lai toan bo day nhe */
    private ImageView backgroud_store = new ImageView();
    DataPlayer dataPlayer;
    ImageView imageView;
    Image imageStore;
    Pane layer;
    double x;
    double y;
    int type;

    double w;
    double h;
    ClassLoader classLoader = this.getClass().getClassLoader();

    private Group root_store;
    private Pane pane_store;
    private Stage stage_store;
    private Scene scene_store;
    private DataPlayer data;
    public WareHouse(Pane layer, double x, double y, double r) {
        this.layer = layer;
        initView();
        this.dataPlayer = dataPlayer;
        this.x = x;
        this.y = y;

        this.imageView = new ImageView();
        this.imageView.setImage(imageStore);
        this.imageView.relocate(x, y);
        this.imageView.setFitWidth(170);
        this.imageView.setFitHeight(170);

        this.imageView.setRotate(r);
        this.w = imageStore.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = imageStore.getHeight(); // imageView.getBoundsInParent().getHeight();

        this.layer.getChildren().add(this.imageView);

    }

    void initView() {
        imageStore = new Image(String.valueOf(classLoader.getResource("res/testnhakho.png")));


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
                root_store = new Group();
                pane_store = new Pane();
                stage_store = new Stage();
                stage_store.setTitle("Nhà Kho");



                Image store_font = new Image(String.valueOf(
                        classLoader.getResource("res/deport.png")));
                backgroud_store.setImage(store_font);
                pane_store.getChildren().add(0,backgroud_store);

                root_store.getChildren().add(pane_store);

                stage_store.setScene(new Scene(root_store, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT));
                stage_store.show();


            }
        });
    }

}


