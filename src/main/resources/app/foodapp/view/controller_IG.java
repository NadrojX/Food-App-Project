public class controller_IG {

    String userSearch = search_barre.getText();

    public void switchToScene1() throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("Page_recette.fxml"));
            Stage window = (Stage) next_button.getScene().getWindow();
            window.setScene(new Scene(root));
            }
    public void switchToScene2() throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("favoris.fxml"));
            Stage window = (Stage) next_button.getScene().getWindow();
            window.setScene(new Scene(root));
            }

    public void switchToScene3() throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("foodapp.fxml"));
            Stage window = (Stage) next_button.getScene().getWindow();
            window.setScene(new Scene(root));
            }

    @FXML
    public void itemChosenInList(MouseEvent arg0) throws Exception {
        int index = liste.getSelectionModel().getSelectedIndex();
    }


    public void addAndRemoveFromFavorites(ActionEvent event) {
        if (checkFav == 1) {
            App.arrayOfFavs.removeToFavs(recette_info);
            add_button.setStyle("-fx-background-color: #E8E8E8; ");
            checkFav = 0;
        } else {
            App.arrayOfFavs.addToFavs(recette_info);
            add_button.setStyle("-fx-background-color: #FC4F4F; ");
            checkFav = 1;
        }
    }
}