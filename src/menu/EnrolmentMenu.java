package menu;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList() {{
                add("View all enrolments");
                add("Add an enrolment");
                add("Drop an enrolment");
                add("Back");
            }};

    @Override
    public void run() {

    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
