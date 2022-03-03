package app.foodapp;

import app.foodapp.controller.InterfaceCLI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException, ParseException {
        InterfaceCLI interfaceCLI = new InterfaceCLI();
        interfaceCLI.FoodAppCLIMenu();
    }

}
