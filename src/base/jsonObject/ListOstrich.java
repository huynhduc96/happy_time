package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 06-May-17.
 */
public class ListOstrich {

    @SerializedName("life")
    @Expose
    private String life;
    @SerializedName("sickness")
    @Expose
    private String sickness;
    @SerializedName("step")
    @Expose
    private String step;

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

}