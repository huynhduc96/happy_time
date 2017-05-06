package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Chinh on 5/6/2017.
 */
public class JoCat {

    @SerializedName("total_number")
    @Expose
    private Integer totalNumber;
    @SerializedName("list_cat")
    @Expose
    private List<ListCat> listCat = null;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListCat> getListCat() {
        return listCat;
    }

    public void setListCat(List<ListCat> listCat) {
        this.listCat = listCat;
    }

}