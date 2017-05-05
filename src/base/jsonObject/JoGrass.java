

/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JoGrass  {

    @SerializedName("total_number")
    @Expose
    private String totalNumber;
    @SerializedName("list_grass")
    @Expose
    private List<ListGras> listGrass = null;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListGras> getListGrass() {
        return listGrass;
    }

    public void setListGrass(List<ListGras> listGrass) {
        this.listGrass = listGrass;
    }

}
