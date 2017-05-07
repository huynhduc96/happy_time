package base.jsonObject;

/**
 * Created by huynh on 09-Apr-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPig {

    @SerializedName("life")
    @Expose
    private double life;
    @SerializedName("sickness")
    @Expose
    private double sickness;
    @SerializedName("step")
    @Expose
    private int step;

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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public ListPig(double life, double sickness, int step) {
        this.life = life;
        this.sickness = sickness;
        this.step = step;
    }
}