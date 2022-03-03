package app.foodapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class FoodApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        primaryStage.setTitle("Fun'Eat");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/app/foodapp/view/foodAppIco.png"));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}