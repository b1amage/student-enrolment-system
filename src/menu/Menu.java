package menu;

import utility.display.Table;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected static final Scanner sc = new Scanner(System.in); // for scanning data

    /**
     * Functionality: show menu as a table by using utility class
     */
    public void showMenu() {
        Table.displayMenuTable(this);
    }

    /**
     * Functionality: get the option from user and validate until it is valid
     * @return a string option of user
     */
    public String getOption() {
        String option; // declare outside the loop to avoid re-declare
        int optionInt = 0;
        do {
            System.out.println("Enter option: ");
            option = sc.nextLine().trim();

            // Validate data (int type)
            try {
                optionInt = Integer.parseInt(option);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer!");
                continue;
            }

            // Validate data (option is in range)
            if (optionInt < 1 || optionInt > getCommands().size()) {
                System.out.println("Please select a number in range of 1 and " + getCommands().size() + "!");
            }

        } while (optionInt < 1 || optionInt > getCommands().size());

        return option;
    }

    /**
     * Functionality: get the input from user by scanner
     * @param message: Message to print out for user
     * @return user input under string format
     */
    protected String input(String message) {
        System.out.println(message);
        return sc.nextLine().trim();
    }

    /**
     * Functionality: run a loop that execute all users' request
     * @throws IOException: cannot open file
     */
    public abstract void run() throws IOException;

    /**
     * Getter for commands
     * @return a list string of commands
     */
    public abstract List<String> getCommands();
}
