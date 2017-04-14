

/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoGrass {

    @SerializedName("total_number")
    @Expose
    private String totalNumber;
    @SerializedName("step1")
    @Expose
    private String step1;
    @SerializedName("step2")
    @Expose
    private String step2;
    @SerializedName("step3")
    @Expose
    private String step3;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }

}
