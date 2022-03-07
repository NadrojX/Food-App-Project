package app.foodapp.model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class controller_IG {

    private static Long id;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String userSearch;


    @FXML
    private ImageView image_recette;

    @FXML
    private ListView<?> liste_ingredient;

    @FXML
    private ListView<?> step_recipe;

    @FXML
    private Button add_fav;

    @FXML
    private Button quit;

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
    private ListView<Object> list_fav = new ListView<>();

    @FXML
    private ImageView logo;

    @FXML
    private Label label;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

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

        File file = new File("src/main/resources/fav.json");
        JsonGestion.jsonFavTitleRead("title", file);
        //addToListView2(1);
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

    //239ae3175a894dc78711d17509918bfe
    //a838ed2668eb4c62be56c24234c05a5c
    //6f941600fda4481f8f07381032a293b1

    @FXML
    public void search() throws IOException {
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + userSearch + "&instructionsRequired=true&number=5&apiKey=6f941600fda4481f8f07381032a293b1");
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
            default -> {
            }
            case 1 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                liste.getItems().add(jsonObject1.get("title"));
            }
            case 2 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
            }
            case 3 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                String imageSource3 = (String) jsonObject3.get("image");
                img3.setImage(new Image(imageSource3));
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                liste.getItems().add(jsonObject3.get("title"));
            }
            case 4 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                String imageSource3 = (String) jsonObject3.get("image");
                img3.setImage(new Image(imageSource3));
                String imageSource4 = (String) jsonObject4.get("image");
                img4.setImage(new Image(imageSource4));
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                liste.getItems().add(jsonObject3.get("title"));
                liste.getItems().add(jsonObject4.get("title"));
            }
            case 5 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                String imageSource3 = (String) jsonObject3.get("image");
                img3.setImage(new Image(imageSource3));
                String imageSource4 = (String) jsonObject4.get("image");
                img4.setImage(new Image(imageSource4));
                String imageSource5 = (String) jsonObject5.get("image");
                img5.setImage(new Image(imageSource5));
                liste.getItems().add(jsonObject1.get("title"));
                liste.getItems().add(jsonObject2.get("title"));
                liste.getItems().add(jsonObject3.get("title"));
                liste.getItems().add(jsonObject4.get("title"));
                liste.getItems().add(jsonObject5.get("title"));
            }

        }
        liste.setStyle("-fx-control-inner-background: #FCDEC0");
    }

/*
    public void addToListView2(int numberOfObjects) {

        JSONObject jsonObject1 = JsonGestion.fav.get(0);
        JSONObject jsonObject2 = JsonGestion.fav.get(1);
        JSONObject jsonObject3 = JsonGestion.fav.get(2);
        JSONObject jsonObject4 = JsonGestion.fav.get(3);
        JSONObject jsonObject5 = JsonGestion.fav.get(4);

        switch (numberOfObjects) {
            default -> {
            }
            case 1 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                //list_fav.getItems().add(jsonObject1.get("title"));
            }
            case 2 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                fav.getItems().add(jsonObject1.get("title"));
                fav.getItems().add(jsonObject2.get("title"));
            }
            case 3 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                String imageSource3 = (String) jsonObject3.get("image");
                img3.setImage(new Image(imageSource3));
                fav.getItems().add(jsonObject1.get("title"));
                fav.getItems().add(jsonObject2.get("title"));
                fav.getItems().add(jsonObject3.get("title"));
            }
            case 4 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                String imageSource3 = (String) jsonObject3.get("image");
                img3.setImage(new Image(imageSource3));
                String imageSource4 = (String) jsonObject4.get("image");
                img4.setImage(new Image(imageSource4));
                fav.getItems().add(jsonObject1.get("title"));
                fav.getItems().add(jsonObject2.get("title"));
                fav.getItems().add(jsonObject3.get("title"));
                fav.getItems().add(jsonObject4.get("title"));
            }
            case 5 -> {
                String imageSource1 = (String) jsonObject1.get("image");
                img1.setImage(new Image(imageSource1));
                String imageSource2 = (String) jsonObject2.get("image");
                img2.setImage(new Image(imageSource2));
                String imageSource3 = (String) jsonObject3.get("image");
                img3.setImage(new Image(imageSource3));
                String imageSource4 = (String) jsonObject4.get("image");
                img4.setImage(new Image(imageSource4));
                String imageSource5 = (String) jsonObject5.get("image");
                img5.setImage(new Image(imageSource5));
                fav.getItems().add(jsonObject1.get("title"));
                fav.getItems().add(jsonObject2.get("title"));
                fav.getItems().add(jsonObject3.get("title"));
                fav.getItems().add(jsonObject4.get("title"));
                fav.getItems().add(jsonObject5.get("title"));
            }

        }

    }

*/
    @FXML
    public void itemChosenInList(MouseEvent arg0) throws Exception {

        JSONObject jsonObject1 = JsonGestion.recipe.get(0);
        JSONObject jsonObject2 = JsonGestion.recipe.get(1);
        JSONObject jsonObject3 = JsonGestion.recipe.get(2);
        JSONObject jsonObject4 = JsonGestion.recipe.get(3);
        JSONObject jsonObject5 = JsonGestion.recipe.get(4);

        int index = liste.getSelectionModel().getSelectedIndex();
        JSONObject itemJsonObject;
        int indice = JsonGestion.recipe.size() + 1;

        switch (index) {
            default -> {
            }
            case 0 -> {
                // empêche la sélection d'objets vides entrainant un passage à la page suivante
                if (JsonGestion.recipe.size() == 0) {
                    break;
                }
                id = (Long) jsonObject1.get("id");
                itemJsonObject = jsonObject1;
            }
            case 1 -> {
                id = (Long) jsonObject2.get("id");
                itemJsonObject = jsonObject2;
            }
            case 2 -> {
                id = (Long) jsonObject3.get("id");
                itemJsonObject = jsonObject3;
            }
            case 3 -> {
                id = (Long) jsonObject4.get("id");
                itemJsonObject = jsonObject4;
            }
            case 4 -> {
                id = (Long) jsonObject5.get("id");
                itemJsonObject = jsonObject5;
            }
        }
        // empêche la sélection d'objets vides entrainant une erreur
        if (!(JsonGestion.recipe.size() == 0) && !(JsonGestion.recipe.size() == indice)) {
            switchToScene1();
        }
    }

    public static Long getId() {
        return id;
    }

    @FXML
    public void addToFav(){
        if(JsonGestion.fav.isEmpty()){
            return;
        }
        for(int i = 0; i < JsonGestion.fav.size(); i++){
            String string = JsonGestion.fav.get(i).toString();
            list_fav.getItems().add(string);
        }
    }

    /*
    public void addAndRemoveFromFavorites(ActionEvent event) {

        int checkFav = 0;

        if (checkFav == 1) {
            arrayOfFavs.removeToFavs(recette_info);
            add_fav.setStyle("-fx-background-color: #E8E8E8; ");
            checkFav = 0;
        }

        else {
            App.arrayOfFavs.addToFavs(recette_info);
            add_fav.setStyle("-fx-background-color: #FC4F4F; ");
            checkFav = 1;
        }
    }
*/
/*
    JSONObject jsonObject1 = JsonGestion.recipe.get(0);

    String imageRecette = (String) jsonObject1.get("image");
    String recetteTitle = (String) jsonObject1.get("title");
    JSONArray ingredients = (JSONArray) jsonObject1.get("extendedIngredients");
    JSONArray instructions = (JSONArray) jsonObject1.get("analyzedInstructions");

*/
}
