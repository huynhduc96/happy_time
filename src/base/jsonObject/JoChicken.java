

/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoChicken {

    @SerializedName("total_number")
    @Expose
    private Integer totalNumber;
    @SerializedName("list_chicken")
    @Expose
    private List<ListChicken> listChicken = null;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListChicken> getListChicken() {
        return listChicken;
    }

    public void setListChicken(List<ListChicken> listChicken) {
        this.listChicken = listChicken;
    }

}