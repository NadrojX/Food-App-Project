package app.foodapp;

import app.foodapp.controller.InterfaceCLI;

import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException{
        InterfaceCLI interfaceCLI = new InterfaceCLI();
        interfaceCLI.FoodAppCLIMenu();
    }

}
