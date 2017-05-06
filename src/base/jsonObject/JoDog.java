package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Chinh on 5/6/2017.
 */
public class JoDog {

    @SerializedName("total_number")
    @Expose
    private Integer totalNumber;
    @SerializedName("list_dog")
    @Expose
    private List<ListDog> listDog = null;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListDog> getListDog() {
        return listDog;
    }

    public void setListDog(List<ListDog> listDog) {
        this.listDog = listDog;
    }

}
