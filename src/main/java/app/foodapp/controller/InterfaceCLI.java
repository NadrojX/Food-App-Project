package app.foodapp.controller;

import java.util.Scanner;

public class InterfaceCLI {

    public void FoodAppCLIMenu(){
        boolean menu = true;
        while(menu){
            System.out.println("-----[Mode Console]-----");
            System.out.println("1. Recherche de recette.\n");
            System.out.println("2. Recette en favoris.\n");
            System.out.println("3.     Quitter.       ");
            System.out.println("------------------------");
            Scanner scanner = new Scanner(System.in);
            switch(scanner.nextInt()){
                default: break;
                case 1:
                    scanner.close();
                    menu = false;
                    receiptsResearch();
                    break;
                case 3:
                    scanner.close();
                    menu = false;
                    System.exit(0);
                    break;
            }
        }
    }

    public void receiptsResearch(){

    }

}
