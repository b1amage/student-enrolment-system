package menu;

import utility.display.Table;

import java.util.List;

public abstract class Menu {
    private List<String> commands;

    public void showMenu() {
        Table.displayMenuTable(this);
    }
    public abstract void run();
    public abstract List<String> getCommands();
}
