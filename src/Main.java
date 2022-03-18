import menu.*;
import system.StudentEnrolmentSystem;

import java.io.IOException;
import java.text.ParseException;

/**
 * Class: Main
 * Role: Interact with user
 * Functionalities: Initialize necessary instances and run as client code
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        // Initialize new instance of StudentEnrolmentSystem
        StudentEnrolmentSystem system = new StudentEnrolmentSystem();

        // Populate data from the default file
        system.populateData();

        // Create main menu from the system
        MainMenu mainMenu = new MainMenu(system);

        // Run the menu
        mainMenu.run();
    }
}
