

/**
 * Created by huynh on 09-Apr-17.
 */
package base.jsonObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPlayer {

    @SerializedName("jo_user1")
    @Expose
    private JoUser1 joUser1;

    public JoUser1 getJoUser1() {
        return joUser1;
    }

    public void setJoUser1(JoUser1 joUser1) {
        this.joUser1 = joUser1;
    }

}
