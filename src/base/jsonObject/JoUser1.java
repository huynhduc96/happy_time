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
    @SerializedName("jo_dog")
    @Expose
    private JoDog joDog;
    @SerializedName("jo_cat")
    @Expose
    private JoCat joCat;
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
    private Integer joMoney;
    @SerializedName("jo_space")
    @Expose
    private Integer joSpace;
    @SerializedName("space_out")
    @Expose
    private Integer spaceOut;

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

    public JoDog getJoDog() {
        return joDog;
    }

    public void setJoDog(JoDog joDog) {
        this.joDog = joDog;
    }

    public JoCat getJoCat() {
        return joCat;
    }

    public void setJoCat(JoCat joCat) {
        this.joCat = joCat;
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

    public JoOstrich getJoOstrich() {
        return joOstrich;
    }

    public void setJoOstrich(JoOstrich joOstrich) {
        this.joOstrich = joOstrich;
    }

    public Integer getJoMoney() {
        return joMoney;
    }

    public void setJoMoney(Integer joMoney) {
        this.joMoney = joMoney;
    }

    public Integer getJoSpace() {
        return joSpace;
    }

    public void setJoSpace(Integer joSpace) {
        this.joSpace = joSpace;
    }

    public Integer getSpaceOut() {
        return spaceOut;
    }

    public void setSpaceOut(Integer spaceOut) {
        this.spaceOut = spaceOut;
    }

}