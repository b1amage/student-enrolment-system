package menu;

import utility.display.Table;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    private List<String> commands;
    protected static final Scanner sc = new Scanner(System.in);

    public void showMenu() {
        Table.displayMenuTable(this);
    }

    public String getOption() {
        String option;
        do {
            System.out.println("Enter option: ");
            option = sc.nextLine().trim();

            if (Integer.parseInt(option) < 1 || Integer.parseInt(option) > getCommands().size()) {
                System.out.println("Invalid option, try again: ");
            }

        } while (Integer.parseInt(option) < 1 || Integer.parseInt(option) > getCommands().size());

        return option;
    }
    public abstract void run() throws IOException;
    public abstract List<String> getCommands();
}
