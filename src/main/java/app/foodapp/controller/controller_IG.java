package app.foodapp.controller;

import app.foodapp.model.JsonGestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.ResourceBundle;

public class controller_IG implements Initializable {

    //239ae3175a894dc78711d17509918bfe
    //a838ed2668eb4c62be56c24234c05a5c
    //6f941600fda4481f8f07381032a293b1

    private static Long id;
    String userSearch;

    @FXML
    private Button quit;

    @FXML
    private Button fav_button;

    @FXML
    private TextField search_barre;


    @FXML
    private ListView<Object> liste;

    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void switchToScene1() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/foodapp/view/recipe.fxml")));
        Stage window = (Stage) liste.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void switchToScene2() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/foodapp/view/favoris.fxml")));
        Stage window = (Stage) fav_button.getScene().getWindow();
        window.setScene(new Scene(root));
        File file = new File("src/main/resources/fav.json");
        JsonGestion.jsonFavTitleRead("title", file);
    }

    @FXML
    public void userSearch() throws IOException {
        userSearch = search_barre.getText();
        search();
    }

    @FXML
    public void search() throws IOException {
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + userSearch + "&number=50&instructionsRequired=true&apiKey=239ae3175a894dc78711d17509918bfe");
        URLConnection spoonacular = url.openConnection();
        JsonGestion.jsonTitleIngredientsRead("title", spoonacular);
        addToListView();
    }

    public void addToListView() {
        if(JsonGestion.recipe.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setContentText("Ingredients entre incorrect. Penser a mettre \nle nom anglais des ingredients.");
            alert.setTitle("Erreur  | Information");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
            return;
        }

        for(int i = 0; i < JsonGestion.recipe.size();i++){
            String recipe = (String) JsonGestion.recipe.get(i).get("title");
            if(!liste.getItems().contains(recipe)) {
                liste.getItems().add(recipe);
            }
        }
        liste.setStyle("-fx-control-inner-background: #FCDEC0");
    }

    @FXML
    public void itemChosenInList() throws Exception {

        JSONObject jsonObject1 = JsonGestion.recipe.get(0);
        JSONObject jsonObject2 = JsonGestion.recipe.get(1);
        JSONObject jsonObject3 = JsonGestion.recipe.get(2);
        JSONObject jsonObject4 = JsonGestion.recipe.get(3);
        JSONObject jsonObject5 = JsonGestion.recipe.get(4);

        int index = liste.getSelectionModel().getSelectedIndex();
        int indice = JsonGestion.recipe.size() + 1;

        switch (index) {
            default -> {
            }
            case 0 -> {
                if (JsonGestion.recipe.size() == 0) {
                    break;
                }
                id = (Long) jsonObject1.get("id");
            }
            case 1 -> id = (Long) jsonObject2.get("id");
            case 2 -> id = (Long) jsonObject3.get("id");
            case 3 -> id = (Long) jsonObject4.get("id");
            case 4 -> id = (Long) jsonObject5.get("id");
        }

        if (!(JsonGestion.recipe.size() == 0) && !(JsonGestion.recipe.size() == indice)) {
            switchToScene1();
        }
    }

    public static Long getId() {
        return id;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/main/resources/fav.json");
        if(!file.exists()) return;
        JsonGestion.jsonFavTitleRead("title", file);
    }
}
