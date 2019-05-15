package Beispiel02.Functionalities.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PseudoGUI {

    public void startGUI() throws Exception {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        boolean stop = false;

        while (!stop) {
            //throws the choices at the console
            printChoices();

            //wait for input
            String choice = reader.readLine();

            switch (choice.toUpperCase()) {
                case "ADD":
                    //call add method
                    break;
                case "DELETE":
                    //call delete method
                    break;
                case "SHOWALL":
                    //call showall method
                    break;
                case "SHOWONE":
                    //call showone method
                    break;
                case "STOP":
                    stop = true;
                    break;
                default: //user wants to fuck me over
                    System.out.println("No choice was choosen. Please retry.");
                    break;
            }

        }

        reader.close();
    }

    //print the variation of choices onto the console
    private void printChoices() {
        System.out.println("Good day. What would you like to do?");
        System.out.println("Type 'Add' a Person to the database.");
        System.out.println("Type 'Delete' to start the deletion process.");
        System.out.println("Type 'ShowAll' to show all Persons in the database.");
        System.out.println("Type 'ShowOne' to show one specific Person.");
        System.out.println("Type 'STOP' to exit the GUI.");
    }

}
