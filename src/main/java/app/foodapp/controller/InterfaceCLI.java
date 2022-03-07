package app.foodapp.controller;

import app.foodapp.model.JsonGestion;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class InterfaceCLI {

    public void FoodAppCLIMenu() throws IOException {
        System.out.println("-----[Fun 'Eat | Mode Console]-----");
        System.out.println("\t1. Recherche de recette.\n");
        System.out.println("\t2. Recette en favoris.\n");
        System.out.println("\t3.     Quitter.       ");
        System.out.println("-----------------------------------");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()) {
            default -> {
            }
            case 1 -> receiptsResearch();
            case 2 -> favoriteReceipts();
            case 3 -> System.exit(0);
        }
    }

    public void receiptsResearch() throws IOException {
        Scanner scannerIngredients = new Scanner(System.in);
        System.out.println("Quel est ou qu'elles sont les ingrédients que vous possédez ? (Exemple du format d'écriture : riz, carotte en anglais)");
        String ingredients = scannerIngredients.nextLine();

        Scanner scannerNumber = new Scanner(System.in);
        System.out.println("Combien de recettes voulez-vous afficher ?");

        int number = scannerNumber.nextInt();

        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients + "&number=" + number + "&apiKey=239ae3175a894dc78711d17509918bfe");
        URLConnection spoonacular = url.openConnection();

        JsonGestion.jsonTitleIngredientsRead("title", spoonacular);
        System.out.println("\nDe quelle recette ci-dessus, souhaitez-vous connaître la recette ?");
        Scanner scannerNumberArray = new Scanner(System.in);

        int numberArray = scannerNumberArray.nextInt();

        URLConnection spoonacular2 = url.openConnection();
        System.out.println("\nIngrédients utilisés (possédé/mis dans la recherche):");
        JsonGestion.jsonReadIngredients("usedIngredients", numberArray-1, "original", spoonacular2);

        URLConnection spoonacular3 = url.openConnection();
        System.out.println("\nIngrédients manquants :");
        JsonGestion.jsonReadIngredients("missedIngredients", numberArray-1, "original", spoonacular3);

        spoonacular3 = url.openConnection();
        URL urlStep = new URL("https://api.spoonacular.com/recipes/" + JsonGestion.jsonGetId("id", numberArray-1, spoonacular3) + "/information?&apiKey=239ae3175a894dc78711d17509918bfe");
        URLConnection spoonacularStep = urlStep.openConnection();
        System.out.println("\nÉtapes de la recette :");
        JsonGestion.jsonStepsRead("analyzedInstructions", spoonacularStep);

        System.out.println("\nVoulez-vous ajouter cette recette au favoris ?  (Y or N ou OUI ou NON)");
        Scanner scannerFav = new Scanner(System.in);
        URLConnection spoonacular4 = url.openConnection();
        URLConnection spoonacular5 = url.openConnection();
        switch (scannerFav.next()) {
            default -> {
            }
            case "y", "Y", "oui", "OUI" -> JsonGestion.jsonAddFav(JsonGestion.jsonGetId("id", numberArray-1, spoonacular4), JsonGestion.jsonGetId("title", numberArray-1, spoonacular5));
            case "n", "N", "non", "NON" -> System.out.println("Recette pas ajoutée au favoris.");
        }

        Scanner scannerEnd = new Scanner(System.in);
        System.out.println("\nQue voulez-vous faire maintenant ?");
        System.out.println("1. Retourner au menu.");
        System.out.println("2. Quitter.");
        switch (scannerEnd.nextInt()) {
            default -> {
            }
            case 1 -> FoodAppCLIMenu();
            case 2 -> System.exit(0);
        }
    }

    public void favoriteReceipts() throws IOException {
        File file = new File("src/main/resources/fav.json");
        if(!file.exists()){
            System.out.println("Vous n'avez pas de favoris.\n");
            FoodAppCLIMenu();
        } else {
            System.out.println("--------[Favoris]--------");
            JsonGestion.jsonFavTitleRead("title", file);
            System.out.println("-------------------------");
            System.out.println("Quelle recette dans vos favoris voulez-vous choisir ?");

            Scanner scanner = new Scanner(System.in);
            int positionChoice = scanner.nextInt() - 1;
            URL url = new URL("https://api.spoonacular.com/recipes/" + JsonGestion.jsonReturnId(file, positionChoice) + "/information?&apiKey=239ae3175a894dc78711d17509918bfe");
            URLConnection spoonacular = url.openConnection();
            System.out.println("Titre de la recette : \n" + JsonGestion.jsonObjectRead("title", spoonacular) + "\n");

            spoonacular = url.openConnection();
            System.out.println("Ingrédients :");
            JsonGestion.jsonObjectAndArrayRead("extendedIngredients","original", spoonacular);

            spoonacular = url.openConnection();
            System.out.println("\nÉtapes de la recette :");
            JsonGestion.jsonStepsRead("analyzedInstructions", spoonacular);

            System.out.println("\nVoulez-vous supprimer cette recette des favoris ?");
            Scanner choice = new Scanner(System.in);
            switch (choice.next()) {
                default -> {
                }
                case "y", "Y", "oui", "OUI" -> {
                    JsonGestion.jsonDelFav(positionChoice);
                    System.out.println("Recette supprimée des favoris.");
                }
                case "n", "N", "non", "NON" -> System.out.println("Recette conservée dans les favoris.");
            }

            Scanner scannerEnd = new Scanner(System.in);
            System.out.println("\nQue voulez-vous faire maintenant ?");
            System.out.println("1. Retourner au menu.");
            System.out.println("2. Supprimer toutes la liste des favoris.");
            System.out.println("3. Quitter.");
            switch (scannerEnd.nextInt()) {
                default -> {
                }
                case 1 -> FoodAppCLIMenu();
                case 2 -> file.delete();
                case 3 -> System.exit(0);
            }
        }
    }

}
