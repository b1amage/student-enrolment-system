package menu;

import service.EnrolmentService;

import system.StudentEnrolmentManager;

import utility.display.Table;

import java.util.ArrayList;
import java.util.List;

public class EnrolmentMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList<>() {{
                add("View all enrolments");
                add("Add an enrolment");
                add("Update enrolment");
                add("Back");
            }};

    private final EnrolmentService enrolmentService;
    private final UpdateMenu updateMenu;

    /**
     * Constructor for enrolment menu
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public EnrolmentMenu(StudentEnrolmentManager manager) {
        this.enrolmentService = new EnrolmentService(manager);
        updateMenu = new UpdateMenu(manager);
    }

    @Override
    public void run() {
        while (true) {
            showMenu();
            String option = getOption();

            switch (option) {
                case "1":
                    handleViewAllEnrolments();
                    break;
                case "2":
                    handleAddEnrolment();
                    break;
                case "3":
                    updateMenu.run();
                    break;
                case "4":
                    return;
            }
        }
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Functionality: request service to get all enrolments
     */
    public void handleViewAllEnrolments() {
        Table.displayEnrolmentTable(enrolmentService.getEnrolments());
    }

    /**
     * Functionality: get users' inputs and request service to add a new enrolment
     */
    public void handleAddEnrolment() {
        // Get input(s)
        String sId = input("Enter student ID: ");
        String cId = input("Enter course ID: ");
        String semester = input("Enter semester: ");

        // Call service enrol
        boolean isSuccess = enrolmentService.enrolCourse(sId, cId, semester);

        // Display state
        System.out.println(isSuccess ? "Enrol successfully!" : "Enrol failed!");
    }
}
