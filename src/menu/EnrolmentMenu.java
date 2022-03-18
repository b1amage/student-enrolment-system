package menu;

import service.EnrolmentService;
import system.StudentEnrolmentManager;
import utility.display.Table;

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

    private EnrolmentService enrolmentService;

    /**
     * Constructor for enrolment menu
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public EnrolmentMenu(StudentEnrolmentManager manager) {
        this.enrolmentService = new EnrolmentService(manager);
    }

    @Override
    public void run() {
        while (true) {
            showMenu();
            String option = getOption();

            switch (option) {
                case "1":
                    viewAllEnrolments();
                    break;
                case "2":
                    addEnrolment();
                    break;
                case "3":
                    dropEnrolment();
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
    public void viewAllEnrolments() {
        Table.displayEnrolmentTable(enrolmentService.getEnrolments());
    }

    /**
     * Functionality: get users' inputs and request service to add a new enrolment
     */
    public void addEnrolment() {
        // Input sID
        System.out.println("Enter student ID: ");
        String sId = sc.nextLine().trim();

        // Input cID
        System.out.println("Enter course ID: ");
        String cId = sc.nextLine().trim();

        // Input semester
        System.out.println("Enter semester: ");
        String semester = sc.nextLine().trim();

        // Call service enrol
        boolean isSuccess = enrolmentService.enrolCourse(sId, cId, semester);

        // Display state
        System.out.println(isSuccess ? "Enrol successfully!" : "Enrol failed!");
    }

    /**
     * Functionality: get users' inputs and request service to drop new enrolment
     */
    public void dropEnrolment() {
        // Input sID
        System.out.println("Enter student ID: ");
        String sId = sc.nextLine().trim();

        // Input cID
        System.out.println("Enter course ID: ");
        String cId = sc.nextLine().trim();

        // Input semester
        System.out.println("Enter semester: ");
        String semester = sc.nextLine().trim();

        // Call service enrol
        boolean isSuccess = enrolmentService.dropCourse(sId, cId, semester);

        // Display state
        System.out.println(isSuccess ? "Enrol successfully!" : "Enrol failed!");
    }
}
