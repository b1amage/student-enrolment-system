package menu;

import system.StudentEnrolmentManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList<>() {{
                add("Manage Student");
                add("Manage Course");
                add("Manage Enrolment");
                add("Quit");
            }};

    private final StudentMenu studentMenu;
    private final CourseMenu courseMenu;
    private final EnrolmentMenu enrolmentMenu;

    /**
     * Constructor for main menu
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public MainMenu(StudentEnrolmentManager manager) {
        // Initialize each menu
        studentMenu = new StudentMenu(manager);
        courseMenu = new CourseMenu(manager);
        enrolmentMenu = new EnrolmentMenu(manager);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            showMenu();
            String option = getOption();

            switch (option) {
                case "1" -> {
                    System.out.println("*********** Managing Student ***********");
                    studentMenu.run();
                }
                case "2" -> {
                    System.out.println("*********** Managing Course ***********");
                    courseMenu.run();
                }
                case "3" -> {
                    System.out.println("*********** Managing Enrolment ***********");
                    enrolmentMenu.run();
                }
                default -> {
                    System.out.println("*********** Good Bye! ***********");
                    return;
                }
            }
        }
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
