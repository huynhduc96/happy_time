package base.jsonObject;

/**
 * Created by huynh on 09-Apr-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCow {

    @SerializedName("life")
    @Expose
    private Integer life;
    @SerializedName("sickness")
    @Expose
    private Integer sickness;
    @SerializedName("step")
    @Expose
    private Integer step;

    public ListCow(Integer life, Integer sickness, Integer step) {
        this.life = life;
        this.sickness = sickness;
        this.step = step;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getSickness() {
        return sickness;
    }

    public void setSickness(Integer sickness) {
        this.sickness = sickness;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

}