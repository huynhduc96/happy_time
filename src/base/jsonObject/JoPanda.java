package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huynh on 07-May-17.
 */
public class JoPanda {
    @SerializedName("total_number")
    @Expose
    private Integer totalNumber;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
