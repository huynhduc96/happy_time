package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huynh on 06-May-17.
 */
public class JoOstrich {

    @SerializedName("total_number")
    @Expose
    private String totalNumber;
    @SerializedName("list_ostrich")
    @Expose
    private List<ListOstrich> listOstrich = null;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListOstrich> getListOstrich() {
        return listOstrich;
    }

    public void setListOstrich(List<ListOstrich> listOstrich) {
        this.listOstrich = listOstrich;
    }

}