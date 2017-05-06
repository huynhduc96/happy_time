package base.jsonObject;

/**
 * Created by huynh on 09-Apr-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoUser1 {

    @SerializedName("jo_user_name")
    @Expose
    private String joUserName;
    @SerializedName("jo_warehouse")
    @Expose
    private JoWarehouse joWarehouse;
    @SerializedName("jo_grass")
    @Expose
    private JoGrass joGrass;
    @SerializedName("jo_chicken")
    @Expose
    private JoChicken joChicken;
    @SerializedName("jo_cow")
    @Expose
    private JoCow joCow;
    @SerializedName("jo_pig")
    @Expose
    private JoPig joPig;
    @SerializedName("jo_ostrich")
    @Expose
    private JoOstrich joOstrich;
    @SerializedName("jo_money")
    @Expose
    private int joMonney;

    @SerializedName("jo_space")
    @Expose
    private int jo_space;

    public int getJo_space() {
        return jo_space;
    }

    public void setJo_space(int jo_space) {
        this.jo_space = jo_space;
    }

    public int getJoMonney() {
        return joMonney;
    }

    public void setJoMonney(int joMonney) {
        this.joMonney = joMonney;
    }

    public String getJoUserName() {
        return joUserName;
    }

    public void setJoUserName(String joUserName) {
        this.joUserName = joUserName;
    }

    public JoWarehouse getJoWarehouse() {
        return joWarehouse;
    }

    public void setJoWarehouse(JoWarehouse joWarehouse) {
        this.joWarehouse = joWarehouse;
    }

    public JoGrass getJoGrass() {
        return joGrass;
    }

    public void setJoGrass(JoGrass joGrass) {
        this.joGrass = joGrass;
    }

    public JoChicken getJoChicken() {
        return joChicken;
    }

    public void setJoChicken(JoChicken joChicken) {
        this.joChicken = joChicken;
    }

    public JoCow getJoCow() {
        return joCow;
    }

    public void setJoCow(JoCow joCow) {
        this.joCow = joCow;
    }

    public JoPig getJoPig() {
        return joPig;
    }

    public void setJoPig(JoPig joPig) {
        this.joPig = joPig;
    }

    public JoOstrich getJoOstrich() { return joOstrich; }

    public void setJoOstrich(JoOstrich joOstrich) { this.joOstrich = joOstrich; }
}
