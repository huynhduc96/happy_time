package base.jsonObject;

/**
 * Created by huynh on 09-Apr-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPig {

    @SerializedName("life")
    @Expose
    private int life;
    @SerializedName("sickness")
    @Expose
    private int sickness;
    @SerializedName("step")
    @Expose
    private int step;

    public ListPig(int life, int sickness, int step) {
        this.life = life;
        this.sickness = sickness;
        this.step = step;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSickness() {
        return sickness;
    }

    public void setSickness(int sickness) {
        this.sickness = sickness;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}