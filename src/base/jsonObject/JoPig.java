

/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoPig {

    @SerializedName("total_number")
    @Expose
    private String totalNumber;
    @SerializedName("list_pig")
    @Expose
    private List<ListPig> listPig = null;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListPig> getListPig() {
        return listPig;
    }

    public void setListPig(List<ListPig> listPig) {
        this.listPig = listPig;
    }

}
