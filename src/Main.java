import menu.*;
import system.StudentEnrolmentSystem;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        StudentEnrolmentSystem system = new StudentEnrolmentSystem();
        system.populateData();

        MainMenu mainMenu = new MainMenu(system);
        mainMenu.run();

    }
}
