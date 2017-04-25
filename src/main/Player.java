package main;

import base.jsonObject.DataPlayer;
import com.google.gson.Gson;

import java.io.*;

/**
 * Created by huynh on 24-Apr-17.
 */
public class Player {
   private DataPlayer dataPlayer = new DataPlayer();

    public DataPlayer getDataPlayer() {
        return dataPlayer;
    }

    public void setDataPlayer(DataPlayer dataPlayer) {
        this.dataPlayer = dataPlayer;
    }

    void getdataPlayer(){

        Gson gson = new Gson();


        String jsonString = new String();

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

        DataPlayer jsonObject= gson.fromJson(jsonString,DataPlayer.class);
        dataPlayer = jsonObject;
    }

    public void saveJson(DataPlayer obj) {
        Gson gson = new Gson();

        String json ;
        json= gson.toJson(obj);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/src/res/data/user.json", false))) {
            bw.write(json);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}
