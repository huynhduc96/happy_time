package base.productStyle;

import base.jsonObject.DataPlayer;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Created by Nguyen Chinh on 5/6/2017.
 */
public class Product {
    private double x, y;
    private String type;
    private Pane layer;
    private Image image;
    private ImageView imageView;

    private static final String prefix =  "res/product/";

    public Product(Pane layer, String type, double x, double y){
        this.layer = layer;
        this.type = type;
        this.x = x;
        this.y = y;

        initView();

//        setOnClick(data);

//        long start = System.currentTimeMillis();
//        while(true){
//            long end = System.currentTimeMillis();
//            if(end - start >= 1000)
//            {
//                autoRemove();
//                break;
//            }
//        }
    }

    private void initView(){
        image = new Image(prefix + this.type + ".png",
                35, 35, false, false);
        imageView = new ImageView(image);
        imageView.relocate(x, y);
        layer.getChildren().add(4, imageView);

    }

    public void setOnClick(DataPlayer data, List prods){
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                harvest(type, data, prods);
            }
        });
    }
    public void setOnClick(DataPlayer data){
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
//                layer.getChildren().remove(imageView);
                harvest(type, data);
            }
        });
    }

    public void harvest(String name, DataPlayer data, List prods){
        if(data.getJoUser1().getJoSpace() == 0){
            ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Kho của bạn không đủ chỗ hihi");
            dialog.getDialogPane().getButtonTypes().add(loginButtonType);
            dialog.getDialogPane().lookupButton(loginButtonType);
            dialog.show();
            return ;
        }
        autoRemove(prods);
        upToWarehouse(data, name);
    }

    public void harvest(String name, DataPlayer data){
        if(data.getJoUser1().getJoSpace() == 0){
            ButtonType loginButtonType = new ButtonType("Hiểu", ButtonBar.ButtonData.OK_DONE);
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Kho của bạn không đủ chỗ hihi");
            dialog.getDialogPane().getButtonTypes().add(loginButtonType);
            dialog.getDialogPane().lookupButton(loginButtonType);
            dialog.show();
            return ;
        }
        System.out.println(layer.getChildren().size());
        layer.getChildren().remove(imageView);
        System.out.println(layer.getChildren().size());
//        imageView.setVisible(false);

        upToWarehouse(data, name);
    }
    private void upToWarehouse(DataPlayer data, String name){
        if (name.equals("egg")) {
            data.getJoUser1().getJoWarehouse()
                    .setEgg(data.getJoUser1().getJoWarehouse().getEgg() + 1);
        }
        if (name.equals("meat")) {
            data.getJoUser1().getJoWarehouse()
                    .setMeat(data.getJoUser1().getJoWarehouse().getMeat() + 1);
        }
        if (name.equals("milk")) {
            data.getJoUser1().getJoWarehouse()
                    .setMilk(data.getJoUser1().getJoWarehouse().getMilk() + 1);
        }
        if (name.equals("feather")) {
            data.getJoUser1().getJoWarehouse()
                    .setFeather(data.getJoUser1().getJoWarehouse().getFeather() + 1);
        }
        if (name.equals("grass_product")) {
            data.getJoUser1().getJoWarehouse()
                    .setGrass_product(data.getJoUser1()
                            .getJoWarehouse().getGrass_product() + 1);
        }
        data.getJoUser1().setJoSpace(
                data.getJoUser1().getJoSpace() - 1);

    }
    public void autoRemove(List prods){
        layer.getChildren().remove(imageView);
        prods.remove(this);
    }

}
