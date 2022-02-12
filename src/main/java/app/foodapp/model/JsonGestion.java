package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URLConnection;

public class JsonGestion {

    public static void jsonArrayRead(String section, URLConnection urlConnection){
        int i = 0;
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            while (jsonArray.iterator().hasNext()){
                if(i >= jsonArray.size()){
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                System.out.println(i+1 + ". " + jsonObject.get(section));
                i++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Object jsonObjectRead(String section, URLConnection urlConnection){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return jsonObject.get(section);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void jsonObjectAndArrayRead(String sectionArray, int idPosition, String section){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new FileReader("C:\\Users\\galat\\Desktop\\fichiers json\\findByIngredients2.json")){
            JSONObject jsonObjectFile = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObjectFile.get(sectionArray);
            JSONObject jsonObject = (JSONObject) jsonArray.get(idPosition);
            System.out.println(jsonObject.get(section));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void jsonReadIngredients(String sectionArray, int idPosition, String section, URLConnection urlConnection){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            if(idPosition == jsonArray.size()){
                return;
            }
            JSONObject jsonObject = (JSONObject) jsonArray.get(idPosition);
            JSONArray jsonArray1 = (JSONArray) jsonObject.get(sectionArray);
            for (Object o : jsonArray1) {
                JSONObject jsonObject1 = (JSONObject) o;
                System.out.println(" - " + jsonObject1.get(section));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
