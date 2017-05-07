package base.jsonObject;

/**
 * Created by huynh on 09-Apr-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCow {

    @SerializedName("life")
    @Expose
    private double life;
    @SerializedName("sickness")
    @Expose
    private double sickness;
    @SerializedName("step")
    @Expose
    private Integer step;

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public double getSickness() {
        return sickness;
    }

    public void setSickness(double sickness) {
        this.sickness = sickness;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public ListCow(double life, double sickness, Integer step) {
        this.life = life;
        this.sickness = sickness;
        this.step = step;
    }
}