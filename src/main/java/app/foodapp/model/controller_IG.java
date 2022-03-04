package app.foodapp.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class controller_IG {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public void userSearch(){
        String userSearch = search_barre.getText();
    }



    @FXML
    public void itemChosenInList(MouseEvent arg0) throws Exception {
        int index = liste.getSelectionModel().getSelectedIndex();
    }

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
    private ListView<?> liste;

    @FXML
    private ImageView logo;
/*
    @FXML
    void switchToScene1(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Page_recette.fxml"));
        Stage window = (Stage) next_button.getScene().getWindow();
        window.setScene(new Scene(root));
    }
*/
    @FXML
    public void switchToScene2() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favoris.fxml"));
        Stage window = (Stage) fav_button.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    public void switchToScene3() throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/app/foodapp/view/foodapp.fxml"));
        Stage window = (Stage) logo.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
