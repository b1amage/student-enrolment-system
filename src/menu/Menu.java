package menu;

import utility.display.Table;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    private List<String> commands;
    protected static final Scanner sc = new Scanner(System.in);

    public void showMenu() {
        Table.displayMenuTable(this);
    }
    public abstract void run();
    public abstract List<String> getCommands();
}
