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

    @FXML
    private Button add_fav;

    @FXML
    private Button fav_button;

    @FXML
    private ImageView image_recette;

    @FXML
    private ListView<String > liste_ingredient;

    @FXML
    private ImageView logo;

    @FXML
    private Label name_recipe;

    @FXML
    private Button quit;

    @FXML
    private ListView<?> step_recipe;

    @FXML
    private Label nbr_pers;

    @FXML
    private Label time_prepa;

    @FXML
    private Rectangle rectangle;

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

            liste_ingredient.getItems().add(ingredients_elements + ".");
        }
        return str;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name_recipe.setText(title);
        image_recette.setImage(new Image(image));
        time_prepa.setText("" + temps_prepa + " min");
        nbr_pers.setText("for " + nbr_person + " person(s)");
        gettingAllIngredients(ingredient_elements);

        liste_ingredient.setCellFactory(param -> new ListCell<String>() {
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
    }



}
