package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonGestion {

    private static int i = 0;

    public static void jsonRead() throws FileNotFoundException {
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new FileReader("C:\\Users\\galat\\Desktop\\fichiers json\\findByIngredients.json")) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            while (jsonArray.iterator().hasNext()){
                if(i >= jsonArray.size()){
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                System.out.println(jsonObject.get("title"));
                i++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        jsonRead();
    }

    public static void jsonWrite(){

    }

}
