package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 06-May-17.
 */
public class ListOstrich {

    @SerializedName("life")
    @Expose
    private Integer life;
    @SerializedName("sickness")
    @Expose
    private Integer sickness;
    @SerializedName("step")
    @Expose
    private Integer step;

    public ListOstrich(Integer life, Integer sickness, Integer step) {
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