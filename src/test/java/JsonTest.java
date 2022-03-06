
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
        title.add("beetroot apple smoothie");
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=apple&number=2&apiKey=a838ed2668eb4c62be56c24234c05a5c%22");
        URLConnection urlConnection = url.openConnection();
        JsonGestion.jsonTitleIngredientsRead("title", urlConnection);
        String test = JsonGestion.recipe.get(0).get("title").toString();
        assertEquals(title.get(0),test);
    }
}
