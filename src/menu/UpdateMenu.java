package menu;

import model.Course;
import service.CourseService;
import service.EnrolmentService;
import system.StudentEnrolmentManager;
import utility.display.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: StudentMenu
 * Role: Client-interaction class
 * Functionalities: Get request from the user and call service functions to perform task
 * */
public class UpdateMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList<>() {{
                add("Enrol a course");
                add("Drop a course");
                add("Back");
            }};

    private final EnrolmentService enrolmentService;
    private final CourseService courseService;

    /**
     * Constructor
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public UpdateMenu(StudentEnrolmentManager manager) {
        enrolmentService = new EnrolmentService(manager);
        courseService = new CourseService(manager);
    }

    @Override
    public void run() {
        // Get input(s)
        String sId = input("Enter student id: ");
        String semester = input("Enter semester: ");

        while (true) {
            displayCourseList(sId, semester);
            showMenu();
            String option = getOption();

            switch (option) {
                case "1":
                    handleEnrolCourse(sId, semester);
                    break;
                case "2":
                    handleDropCourse(sId, semester);
                    break;
                case "3":
                    return;
            }
        }
    }

    /**
     * Functionality: List all course of a student in a semester by calling service
     * @param sId: student id
     * @param semester: semester
     */
    public void displayCourseList(String sId, String semester) {
        // Call service
        List<Course> coursesOfStudentBySemester = courseService.getCourseOfAStudentBySemester(sId, semester);

        // Check and display
        if (coursesOfStudentBySemester == null) {
            System.out.printf("Student with id %s does not exist!\n", sId);
        } else if (coursesOfStudentBySemester.isEmpty()) {
            System.out.printf("Student with id %s does not have any course in semester %s!\n", sId, semester);
        } else {
            System.out.println("Updating information of student " + sId + " in semester " + semester);
            Table.displayCourseTable(coursesOfStudentBySemester);
        }
    }

    /**
     * Functionality: Call service to enrol a new course for a student in a semester
     * @param sId: student id
     * @param semester: semester
     */
    public void handleEnrolCourse(String sId, String semester) {
        // Get input(s)
        String cId = input("Enter course id: ");
        boolean isSuccess = enrolmentService.enrolCourse(sId, cId, semester);
        System.out.println(isSuccess ? "Enrol course successfully!" : "Enrol course fail!");
    }

    /**
     * Functionality: Call service to drop a course for a student in a semester
     * @param sId: student id
     * @param semester: semester
     */
    public void handleDropCourse(String sId, String semester) {
        // Get input(s)
        String cId = input("Enter course id: ");
        boolean isSuccess = enrolmentService.dropCourse(sId, cId, semester);
        System.out.println(isSuccess ? "Drop course successfully!" : "Drop course fail!");
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
