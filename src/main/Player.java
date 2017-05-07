package main;

import base.jsonObject.DataPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

/**
 * Created by huynh on 24-Apr-17.
 */
public class Player {
   private JsonObject dataPlayer = new JsonObject();
    private JsonObject data = new JsonObject();

    private DataPlayer player = new DataPlayer();

    public DataPlayer getPlayer() {
        return player;
    }
    private String jsonString;
    void getdataPlayer(){

        Gson gson = new Gson();


        jsonString = new String();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(System.getProperty("user.dir") + "/src/res/data/user.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        String inputLine;
        try {
            while ((inputLine = bufferedReader.readLine()) != null) {
                jsonString += inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(jsonString);

        data = gson.fromJson(jsonString, JsonObject.class);
        dataPlayer = data.getAsJsonObject("jo_user1");

        player= gson.fromJson(jsonString,DataPlayer.class);
    }

    public void saveJson(DataPlayer obj) {
        Gson gson = new Gson();

        String json ;
        json= gson.toJson(obj);
        System.out.println(json.toString());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                System.getProperty("user.dir") + "/src/res/data/user.json", false))) {
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
