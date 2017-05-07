package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nguyen Chinh on 5/6/2017.
 */
public class ListDog {

    @SerializedName("life")
    @Expose
    private double life;

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }
}
