

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
    private Integer totalNumber;
    @SerializedName("list_pig")
    @Expose
    private List<ListPig> listPig = null;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListPig> getListPig() {
        return listPig;
    }

    public void setListPig(List<ListPig> listPig) {
        this.listPig = listPig;
    }

}