package app.foodapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class JsonGestion {

    public static ArrayList<JSONObject> recipe = new ArrayList<>();
    public static ArrayList<JSONObject> fav = new ArrayList<>();


    public static void jsonTitleIngredientsRead(String section, URLConnection urlConnection) {
        int i = 0;
        recipe.clear();
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            while (jsonArray.iterator().hasNext()) {
                if (i >= jsonArray.size()) {
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                recipe.add(jsonObject);
                System.out.println(i + 1 + ". " + jsonObject.get(section));
                i++;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void jsonFavTitleRead(String section, File file) {
        int i = 0;
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new FileReader(file)) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            while (jsonArray.iterator().hasNext()) {
                if (i >= jsonArray.size()) {
                    return;
                }
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                fav.add(jsonObject);
                System.out.println(i + 1 + ". " + jsonObject.get(section));
                i++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String jsonObjectRead(String section, URLConnection urlConnection) {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            return jsonObject.get(section).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void jsonObjectAndArrayRead(String sectionArray, String section, URLConnection urlConnection) {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            JSONObject jsonObjectFile = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObjectFile.get(sectionArray);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                System.out.println(jsonObject.get(section));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void jsonStepsRead(String sectionArray, URLConnection urlConnection) {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            JSONObject jsonObjectFile = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObjectFile.get(sectionArray);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                JSONArray jsonArray1 = (JSONArray) jsonObject.get("steps");
                for(Object o2 : jsonArray1){
                    JSONObject jsonObject1 = (JSONObject) o2;
                    System.out.println( "step " +jsonObject1.get("number").toString() + " : " + jsonObject1.get("step"));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void jsonReadIngredients(String sectionArray, int idPosition, String section, URLConnection urlConnection) {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            if (idPosition == jsonArray.size()) {
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

    public static void jsonAddFav(String id, String titleRecipes) {
        File file = new File("src/main/resources/fav.json");
        try {
            if (!file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", id);
                jsonObject.put("title", titleRecipes);
                jsonArray.add(jsonObject);
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("Recette ajoutée au favoris.");
            } else {
                JSONParser jsonParser = new JSONParser();
                Reader reader = new FileReader(file);
                JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", id);
                jsonObject.put("title", titleRecipes);
                if (jsonArray.contains(jsonObject)) {
                    System.out.println("Déjà dans vos favoris.");
                    return;
                }
                jsonArray.add(jsonObject);
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("Recette ajoutée au favoris.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jsonDelFav(int position){
        File file = new File("src/main/resources/fav.json");
        JSONParser jsonParser = new JSONParser();
        try(Reader reader = new FileReader(file)){
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            jsonArray.remove(position);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    public static String jsonGetId(String section, int idPosition, URLConnection urlConnection) {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            if (idPosition >= jsonArray.size()) {
                return null;
            }
            JSONObject jsonObject = (JSONObject) jsonArray.get(idPosition);
            return jsonObject.get(section).toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jsonReturnId(File file, int position) {
        JSONParser jsonParser = new JSONParser();
        try (Reader reader = new FileReader(file)) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) jsonArray.get(position);
            return jsonObject.get("id").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject Recherche_via_id(String id) {
        JSONObject jsonObj = null;
        try {
            URL url = new URL("https://api.spoonacular.com/recipes/" + id + "/information?&apiKey=239ae3175a894dc78711d17509918bfe");
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            connexion.connect();
            // Check if connect is made
            int responseCode = connexion.getResponseCode();

            // 200 = OK
            if (responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            else {
                InputStream stream = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String myData = reader.readLine();
                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(myData);
                jsonObj = (JSONObject) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObj;
    }


}