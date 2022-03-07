package app.foodapp.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class recipeController implements Initializable {

    int checkFav = 0;

    @FXML
    private Button add_fav;

    @FXML
    private Button fav_button;

    @FXML
    private ImageView image_recette;

    @FXML
    private ListView<String > l_ingredients;

    @FXML
    private ImageView logo;

    @FXML
    private Label name_recipe;

    @FXML
    private Button quit;

    @FXML
    private ListView<String> step_recipe;

    @FXML
    private Label nbr_pers;

    @FXML
    private Label time_prepa;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Rectangle rectangle1;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void switchToScene2() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favoris.fxml"));
        Stage window = (Stage) fav_button.getScene().getWindow();
        window.setScene(new Scene(root));

        File file = new File("src/main/resources/fav.json");
        JsonGestion.jsonFavTitleRead("title", file);

    }

    @FXML
    public void switchToScene3() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        Stage window = (Stage) logo.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    String idObject = String.valueOf(controller_IG.getId());
    JSONObject recipe_info = JsonGestion.Recherche_via_id(idObject);
    JSONArray ingredient_elements = (JSONArray) recipe_info.get("extendedIngredients");
    JSONArray steps_elements = (JSONArray) recipe_info.get("analyzedInstructions");

    String title = (String) recipe_info.get("title");
    String image = (String) recipe_info.get("image");
    Long temps_prepa = (Long) recipe_info.get("readyInMinutes");
    Long nbr_person = (Long) recipe_info.get("servings");

    String gettingAllIngredients(JSONArray ingredients2) {
        Object obj = "";
        String str = "";
        for (int i = 0; i < ingredients2.size(); i++) {
            obj = ingredients2.get(i);
            JSONObject jObject = (JSONObject) obj;
            String ingredients_elements = (String) jObject.get("original");

            l_ingredients.getItems().add(ingredients_elements + ".");
        }
        return str;
    }

    String gettingAllSteps12(JSONArray steps) {
        Object obj = "";
        String str = "";
        for (int i = 0; i < steps.size(); i++) {
            obj = steps.get(i);
            JSONObject jObject = (JSONObject) obj;
            String steps_elements = (String) jObject.get("steps");

            step_recipe.getItems().add(steps_elements + ".");
        }
        return str;
    }

    String gettingAllSteps(JSONArray instructions) {
        Object obj = "";
        String str2 = "";
        Object obj2 = "";

        for (int i = 0; i < instructions.size(); i++) {
            obj = instructions.get(i);
            JSONObject jObject = (JSONObject) obj;

            JSONArray jArray = (JSONArray) jObject.get("steps");
            for (int j = 0; j < jArray.size(); j++) {
                obj2 = jArray.get(j);
                JSONObject jObject2 = (JSONObject) obj2;
                step_recipe.getItems()
                        .add("Step " + jObject2.get("number") + " : " + (String) jObject2.get("step"));
            }
        }
        return str2;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name_recipe.setText(title);
        image_recette.setImage(new Image(image));
        time_prepa.setText("Preparation time: " + temps_prepa + " min");
        nbr_pers.setText("Recipe for " + nbr_person + " person(s)");
        gettingAllIngredients(ingredient_elements);
        gettingAllSteps(steps_elements);
        l_ingredients.setStyle("-fx-control-inner-background: #7D5A50");
        step_recipe.setStyle("-fx-control-inner-background: #FCDEC0");

        l_ingredients.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    // other stuff to do...

                } else {

                    // set the width's
                    setMinWidth(rectangle.getWidth());
                    setMaxWidth(rectangle.getWidth());
                    setPrefWidth(rectangle.getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item);

                }
            }
        });

        step_recipe.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    // other stuff to do...

                } else {

                    // set the width's
                    setMinWidth(rectangle1.getWidth());
                    setMaxWidth(rectangle1.getWidth());
                    setPrefWidth(rectangle1.getWidth());

                    // allow wrapping
                    setWrapText(true);

                    setText(item);

                }
            }
        });
    }

    public void checkFavorites() {
        if (App.arrayOfFavs.contains(itemJsonObject)) {
            checkFav = 1;
            add_fav.setStyle("-fx-background-color: red; ");
        } else {
            checkFav = 0;
            add_fav.setStyle("-fx-background-color: #c4b6ae; ");
        }
    }

    public void addAndRemoveFromFavorites(ActionEvent event) {
        if (checkFav == 1) {
            App.arrayOfFavs.removeToFavs(itemJsonObject);
            add_fav.setStyle("-fx-background-color: #c4b6ae; ");
            checkFav = 0;
        } else {
            App.arrayOfFavs.addToFavs(itemJsonObject);
            add_fav.setStyle("-fx-background-color: red; ");
            checkFav = 1;
        }
    }


}
