package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class JsonGestion {

    private static int i = 0;

    public static void jsonArrayRead(String section){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new FileReader("C:\\Users\\galat\\Desktop\\fichiers json\\findByIngredients.json")) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            while (jsonArray.iterator().hasNext()){
                if(i >= jsonArray.size()){
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                System.out.println(jsonObject.get(section));
                i++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void jsonObjectRead(String section){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new FileReader("C:\\Users\\galat\\Desktop\\fichiers json\\recipeInformation.json")){
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject.get(section));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void jsonObjectAndArrayRead(String sectionArray, int idPosition, String section){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new FileReader("C:\\Users\\galat\\Desktop\\fichiers json\\recipeInformation.json")){
            JSONObject jsonObjectFile = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObjectFile.get(sectionArray);
            JSONObject jsonObject = (JSONObject) jsonArray.get(idPosition);
            System.out.println(jsonObject.get(section));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    public static void jsonWrite(){

    }

}
