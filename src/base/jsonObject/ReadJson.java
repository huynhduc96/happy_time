package base.jsonObject;

import com.google.gson.Gson;
import java.io.*;


/**
 * Created by huynh on 09-Apr-17.
 */
public class ReadJson {
    JsonObject object;

    public void getJson() {
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

           JsonObject jsonObject= gson.fromJson(jsonString,JsonObject.class);
               object = jsonObject;

    }


    public JsonObject getObject() {
        return object;
    }

}
