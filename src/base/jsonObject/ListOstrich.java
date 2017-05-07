package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 06-May-17.
 */
public class ListOstrich {

    @SerializedName("life")
    @Expose
    private Double life;
    @SerializedName("sickness")
    @Expose
    private Double sickness;
    @SerializedName("step")
    @Expose
    private Integer step;

    public ListOstrich(Double life, Double sickness, Integer step) {
        this.life = life;
        this.sickness = sickness;
        this.step = step;
    }

    public Double getLife() {
        return life;
    }

    public void setLife(Double life) {
        this.life = life;
    }

    public Double getSickness() {
        return sickness;
    }

    public void setSickness(Double sickness) {
        this.sickness = sickness;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

}