package app.foodapp.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class favController implements Initializable {

    @FXML
    private Button quit;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private ImageView logo;

    @FXML
    private Button fav_button;

    @FXML
    private ListView list_fav;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list_fav.setStyle("-fx-control-inner-background:  #B4846C");
        File file = new File("src/main/resources/fav.json");
        if(!file.exists() || JsonGestion.fav.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setContentText("Vous n'avez pas de recette dans vos favoris.");
            alert.setTitle("Erreur  | Information");
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.showAndWait();
            return;
        }
        JsonGestion.jsonFavTitleRead("title", file);
        for(int i = 0; i < JsonGestion.fav.size();i++){
            String recipe = (String) JsonGestion.fav.get(i).get("title");
            if(!list_fav.getItems().contains(recipe)) {
                list_fav.getItems().add(recipe);
            }
        }
    }
}
