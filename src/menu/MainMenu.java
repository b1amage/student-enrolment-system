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
        studentMenu = new StudentMenu(manager);
        courseMenu = new CourseMenu(manager);
        enrolmentMenu = new EnrolmentMenu(manager);
    }

    @Override
    public void run() {
        while (true) {
            showMenu();
            String option = getOption();

            switch (option) {
                case "1":
                    System.out.println("*********** Managing Student ***********");
                    studentMenu.run();
                    break;
                case "2":
                    System.out.println("*********** Managing Course ***********");
                    courseMenu.run();
                    break;
                case "3":
                    System.out.println("*********** Managing Enrolment ***********");
                    enrolmentMenu.run();
                    break;
                default:
                    System.out.println("Quit");
                    return;
            }
        }
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
