package menu;

import system.StudentEnrolmentManager;
import system.StudentEnrolmentSystem;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList() {{
                add("Manage Student");
                add("Manage Course");
                add("Manage Enrolment");
                add("Quit");
            }};

    private StudentMenu studentMenu;
    private CourseMenu courseMenu;
    private EnrolmentMenu enrolmentMenu;
    private StudentEnrolmentManager manager;

    public MainMenu(StudentEnrolmentManager manager) {
        manager = new StudentEnrolmentSystem();
        studentMenu = new StudentMenu(manager);
        courseMenu = new CourseMenu(manager);
        enrolmentMenu = new EnrolmentMenu(manager);
    }

    @Override
    public void run() {

    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
