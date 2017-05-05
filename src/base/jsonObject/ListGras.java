package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by huynh on 05-May-17.
 */
public class ListGras {

    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("step")
    @Expose
    private String step;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public ListGras(Integer position, String step) {
        this.position = position;
        this.step = step;
    }
}


