package base.House;

import com.google.gson.JsonObject;
import main.Player;

/**
 * Created by huynh on 25-Apr-17.
 */
public interface House {
    void setOnclick(JsonObject data);
    void updateDataPlayer(Player player);
}
