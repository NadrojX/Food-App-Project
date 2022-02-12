package app.foodapp.controller;

import app.foodapp.model.JsonGestion;

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
        switch(scanner.nextInt()){
            default: break;
            case 1:
                receiptsResearch();
                break;
            case 2:
                favoriteReceipts();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    public void receiptsResearch() throws IOException {
        Scanner scannerIngredients = new Scanner(System.in);
        System.out.println("Quel est où qu'elles sont les ingrédients que vous possédez ? (Exemple du format d'écriture : riz, carotte en anglais)");
        String ingredients = scannerIngredients.nextLine();
        Scanner scannerNumber = new Scanner(System.in);
        System.out.println("Combien de recettes voulez-vous afficher ?");
        int number = scannerNumber.nextInt();
        URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + ingredients + "&number=" + number + "&apiKey=a838ed2668eb4c62be56c24234c05a5c");
        URLConnection spoonacular = url.openConnection();
        JsonGestion.jsonArrayRead("title", spoonacular);
        System.out.println("De quelle recette ci-dessus, souhaitez-vous connaître la recette ?");
        Scanner scannerNumberArray = new Scanner(System.in);
        int numberArray = scannerNumberArray.nextInt();
        URLConnection spoonacular2 = url.openConnection();
        System.out.println("Ingrédients utilisés :");
        JsonGestion.jsonReadIngredients("usedIngredients", numberArray-1, "original", spoonacular2);
        URLConnection spoonacular3 = url.openConnection();
        System.out.println("Ingrédients manquants :");
        JsonGestion.jsonReadIngredients("missedIngredients", numberArray-1, "original", spoonacular3);
    }

    public void favoriteReceipts(){

    }

}
