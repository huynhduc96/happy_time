

/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class JoCow {

    @SerializedName("total_number")
    @Expose
    private Integer totalNumber;
    @SerializedName("list_cow")
    @Expose
    private List<ListCow> listCow = null;

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<ListCow> getListCow() {
        return listCow;
    }

    public void setListCow(List<ListCow> listCow) {
        this.listCow = listCow;
    }

}