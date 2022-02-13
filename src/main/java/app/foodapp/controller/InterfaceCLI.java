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

        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients + "&number=" + number + "&apiKey=a838ed2668eb4c62be56c24234c05a5c");
        URLConnection spoonacular = url.openConnection();

        JsonGestion.jsonTitleIngredientsRead("title", spoonacular);
        System.out.println("\nDe quelle recette ci-dessus, souhaitez-vous connaître la recette ?");
        Scanner scannerNumberArray = new Scanner(System.in);

        int numberArray = scannerNumberArray.nextInt();

        URLConnection spoonacular2 = url.openConnection();
        System.out.println("Ingrédients utilisés :");
        JsonGestion.jsonReadIngredients("usedIngredients", numberArray-1, "original", spoonacular2);

        URLConnection spoonacular3 = url.openConnection();
        System.out.println("Ingrédients manquants :");
        JsonGestion.jsonReadIngredients("missedIngredients", numberArray-1, "original", spoonacular3);

        System.out.println("\nVoulez-vous ajouter cette recette au favoris ?  (Y or N ou OUI ou NON)");
        Scanner scannerFav = new Scanner(System.in);
        switch (scannerFav.next()) {
            default -> {
            }
            case "y", "Y", "oui", "OUI" -> System.out.println("Recette ajoutée au favoris.");
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

    public void favoriteReceipts(){

    }

}
