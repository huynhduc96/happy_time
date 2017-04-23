

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
    private String totalNumber;
    @SerializedName("list_chicken")
    @Expose
    private List<ListChicken> listChicken = null;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListChicken> getListChicken() {
        return listChicken;
    }

    public void setListChicken(List<ListChicken> listChicken) {
        this.listChicken = listChicken;
    }

}