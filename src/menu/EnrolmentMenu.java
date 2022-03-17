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

    public EnrolmentMenu(StudentEnrolmentManager manager) {
        this.enrolmentService = new EnrolmentService(manager);
    }

    @Override
    public void run() {

    }

    @Override
    public List<String> getCommands() {
        return commands;
    }

    public void viewAllEnrolments() {
        Table.displayEnrolmentTable(enrolmentService.getEnrolments());
    }

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
