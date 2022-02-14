package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URLConnection;

public class JsonGestion {

    public static void jsonTitleIngredientsRead(String section, URLConnection urlConnection){
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

    public static void jsonAddFav(String titleRecipes){
        File file = new File("fav.json");
        try {
            if(!file.exists()){
                FileWriter fileWriter = new FileWriter(file);
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", titleRecipes);
                jsonArray.add(jsonObject);
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("Recette ajoutée au favoris.");
            } else {
                JSONParser jsonParser = new JSONParser();
                Reader reader = new FileReader(file);
                JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", titleRecipes);
                if(jsonArray.contains(jsonObject)){
                    System.out.println("Déjà dans vos favoris.");
                    return;
                }
                jsonArray.add(jsonObject);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("Recette ajoutée au favoris.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String jsonGetId(String section, int idPosition, URLConnection urlConnection){
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            if(idPosition >= jsonArray.size()){
                return null;
            }
            JSONObject jsonObject = (JSONObject) jsonArray.get(idPosition);
            return jsonObject.get(section).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
