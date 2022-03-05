package app.foodapp.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class controller_IG {

    private Stage stage;
    private Scene scene;
    private Parent root;
    String userSearch;

/*

    public void addAndRemoveFromFavorites(ActionEvent event) {
        if (checkFav == 1) {
            App.arrayOfFavs.removeToFavs(recette_info);
            add_button.setStyle("-fx-background-color: #E8E8E8; ");
            checkFav = 0;
        }

        else {
            App.arrayOfFavs.addToFavs(recette_info);
            add_button.setStyle("-fx-background-color: #FC4F4F; ");
            checkFav = 1;
        }
    }
*/
    @FXML
    private Button quit;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button button_acceuil;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button fav_button;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private TextField search_barre;

    @FXML
    private Button search_button;

    @FXML
    private Label name_recipe;

    //fav

    @FXML
    private Button button_fav1;

    @FXML
    private Button button_fav2;

    @FXML
    private Button button_fav3;

    @FXML
    private Button button_fav4;

    @FXML
    private Button button_fav5;

    @FXML
    private ListView<Object> liste;

    @FXML
    private ImageView logo;

    @FXML
    void switchToScene1() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/recipe.fxml"));
        Stage window = (Stage) liste.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void switchToScene2() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favoris.fxml"));
        Stage window = (Stage) fav_button.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    public void switchToScene3() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        Stage window = (Stage) logo.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    public void userSearch() throws IOException {
        userSearch = search_barre.getText();
        search();
    }

    @FXML
    public void search() throws IOException {
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + userSearch + "&number=5&apiKey=a838ed2668eb4c62be56c24234c05a5c");
        URLConnection spoonacular = url.openConnection();
        JsonGestion.jsonTitleIngredientsRead("title", spoonacular);
        addToListView(5);
    }

    public void addToListView(int numberOfObjects) {

        if(JsonGestion.recipe.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setContentText("Ingredients entre incorrect. Penser a mettre \nle nom anglais des ingredients.");
            alert.setTitle("Erreur  | Information");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
            return;
        }

        JSONObject jsonObject1 = JsonGestion.recipe.get(0);
        JSONObject jsonObject2 = JsonGestion.recipe.get(1);
        JSONObject jsonObject3 = JsonGestion.recipe.get(2);
        JSONObject jsonObject4 = JsonGestion.recipe.get(3);
        JSONObject jsonObject5 = JsonGestion.recipe.get(4);

        switch (numberOfObjects) {
            default:
                break;
            case 1:
                liste.getItems().add(jsonObject1.get("title"));
                break;
            case 2:
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                break;
            case 3:
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                liste.getItems().add(jsonObject3.get("title"));
                break;
            case 4:
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                liste.getItems().add(jsonObject3.get("title"));
                liste.getItems().add(jsonObject4.get("title"));
                break;
            case 5:
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                liste.getItems().add(jsonObject3.get("title"));
                liste.getItems().add(jsonObject4.get("title"));
                liste.getItems().add(jsonObject5.get("title"));
                break;
        }
    }

    @FXML
    public void itemChosenInList(MouseEvent arg0) throws Exception {

        JSONObject jsonObject1 = JsonGestion.recipe.get(0);
        JSONObject jsonObject2 = JsonGestion.recipe.get(1);
        JSONObject jsonObject3 = JsonGestion.recipe.get(2);
        JSONObject jsonObject4 = JsonGestion.recipe.get(3);
        JSONObject jsonObject5 = JsonGestion.recipe.get(4);

        int index = liste.getSelectionModel().getSelectedIndex();
        Long id;
        JSONObject itemJsonObject;
        int indice = JsonGestion.recipe.size() + 1;

        switch (index) {
            default:
                break;
            case 0:
                // empêche la sélection d'objets vides entrainant un passage à la page suivante
                if (JsonGestion.recipe.size() == 0 || JsonGestion.recipe.size() == indice) {
                    break;
                }
                id = (Long) jsonObject1.get("id");
                itemJsonObject = jsonObject1;
                break;
            case 1:
                id = (Long) jsonObject2.get("id");
                itemJsonObject = jsonObject2;
                break;
            case 2:
                id = (Long) jsonObject3.get("id");
                itemJsonObject = jsonObject3;
                break;
            case 3:
                id = (Long) jsonObject4.get("id");
                itemJsonObject = jsonObject4;
                break;
            case 4:
                id = (Long) jsonObject5.get("id");
                itemJsonObject = jsonObject5;
                break;
        }

        // empêche la sélection d'objets vides entrainant une erreur
        if (!(JsonGestion.recipe.size() == 0) && !(JsonGestion.recipe.size() == indice)) {
            switchToScene1();

        }
    }

}
