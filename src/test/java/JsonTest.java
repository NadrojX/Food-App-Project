
import app.foodapp.model.JsonGestion;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JsonTest {

    @Test
    public void test() throws IOException {
        ArrayList<String> title = new ArrayList<>();
        title.add("Baked Cinnamon Apple Slices");
        title.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apple&number=2&apiKey=a838ed2668eb4c62be56c24234c05a5c%22");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonTitleIngredientsRead("title", urlConnection);
        String test = JsonGestion.recipe.get(0).get("title").toString();
        assertEquals(title.get(0),test);
        //fini
    }

    @Test
    public void TestjsonObjectRead() throws IOException {
        ArrayList<String> title2 = new ArrayList<>();
        title2.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/633547/information?&apiKey=a838ed2668eb4c62be56c24234c05a5c");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonObjectRead("Ingredients", urlConnection);
        String test = JsonGestion.recipe.get(0).get("Ingredients").toString();
        assertEquals(title2.get(0),test);
        //à modifier
    }

    @Test
    public void TestjsonObjectAndArrayRead() throws IOException {
        ArrayList<String> title3 = new ArrayList<>();
        title3.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/633547/information?&apiKey=a838ed2668eb4c62be56c24234c05a5c");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonObjectAndArrayRead("extendedIngredients","extendedIngredients",urlConnection);
        String test = JsonGestion.recipe.get(0).get("extendedIngredients").toString();
        assertEquals(title3.get(0),title3.get(0),test);
        //à modifier
    }

    @Test
    public void TestjsonStepsRead() throws IOException {
        ArrayList<String> title4 = new ArrayList<>();
        title4.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apple&number=2&apiKey=a838ed2668eb4c62be56c24234c05a5c%22");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonStepsRead("analyzedInstructions", urlConnection);
        String test = JsonGestion.recipe.get(0).get("analysedInstruction").toString();
        assertEquals(title4.get(0),test);
    }

    @Test
    public void TestjsonReadIngredients() throws IOException {
        ArrayList<String> title5 = new ArrayList<>();
        title5.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apple&number=2&apiKey=a838ed2668eb4c62be56c24234c05a5c%22");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonReadIngredients("extendedIngredient",0,"nameClean" ,urlConnection);
        String test = JsonGestion.recipe.get(0).get("nameClean").toString();
        assertEquals(title5.get(0),test);
    }

    @Test
    public void TestjsonGetId() throws IOException {
        ArrayList<String> title6 = new ArrayList<>();
        title6.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apple&number=2&apiKey=a838ed2668eb4c62be56c24234c05a5c%22");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonGetId(, urlConnection);
        String test = JsonGestion.recipe.get(0).get("title").toString();
        assertEquals(title6.get(0),test);
    }
}
