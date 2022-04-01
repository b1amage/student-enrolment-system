package menu;

import model.Course;
import service.CourseService;
import service.EnrolmentService;
import service.StudentService;
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
    private final StudentService studentService;

    /**
     * Constructor
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public UpdateMenu(StudentEnrolmentManager manager) {
        enrolmentService = new EnrolmentService(manager);
        courseService = new CourseService(manager);
        studentService = new StudentService(manager);
    }

    @Override
    public void run() {
        // Get input(s)
        String sId;
        String semester;
        do {
            sId = input("Enter student id: ");
            semester = input("Enter semester: ");

            if (studentService.getStudentById(sId) == null)
                System.err.println("Student not exist, please try again.");

        } while(studentService.getStudentById(sId) == null);

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
            System.err.printf("Student with id %s does not exist!\n", sId);
        } else if (coursesOfStudentBySemester.isEmpty()) {
            System.err.printf("Student with id %s does not have any course in semester %s!\n", sId, semester);
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

        // Display state
        if (isSuccess)
            System.out.println("Enrol successfully!");
        else
            System.err.println("Enrol failed!");
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

        // Display state
        if (isSuccess)
            System.out.println("Drop successfully!");
        else
            System.err.println("Drop failed!");
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }
}
