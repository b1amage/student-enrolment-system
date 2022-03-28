package menu;

import model.Course;
import service.CourseService;
import system.StudentEnrolmentManager;
import utility.csv.CsvWriter;
import utility.display.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseMenu extends Menu{
    private static final ArrayList<String> commands =
            new ArrayList<>() {{
                add("View all courses");
                add("View all courses in a semester");
                add("View all courses of a student in a semester");
                add("Back");
            }};

    private final CourseService courseService;

    /**
     * Constructor for course menu
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public CourseMenu(StudentEnrolmentManager manager) {
        this.courseService = new CourseService(manager);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            showMenu();
            String option = getOption();

            switch (option) {
                case "1":
                    viewAllCourses();
                    break;
                case "2":
                    handleViewAllCoursesInASemester();
                    break;
                case "3":
                    handleViewAllCourseOfAStudentInASemester();
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
     * Functionality: request the student service to get all courses
     */
    public void viewAllCourses() {
        Table.displayCourseTable(courseService.getCourses());
    }

    /**
     * Functionality: get users' inputs and request service to view all course in a semester
     * @throws IOException: file cannot be opened
     */
    public void handleViewAllCoursesInASemester() throws IOException {
        // Get input(s)
        String semester = input("Enter semester: ");

        // Call service to get filtered list
        List<Course> coursesBySemester = courseService.getCourseBySemester(semester);

        // Check and display
        if (coursesBySemester == null) {
            System.err.printf("There is no course on semester %s!\n", semester);
        } else {
            Table.displayCourseTable(coursesBySemester);

            // Ask if write to csv file
            boolean saveToCsv = input("Save those data to a csv file? (y: yes, any other keys: no)").equalsIgnoreCase("y");
            if (saveToCsv) {
                String filePath = "src/reports/course/courses_" + semester + ".csv";
                CsvWriter.writeCourseToFile(filePath, coursesBySemester);
                System.out.println("Written data to "+ filePath + "!");
            }
        }

    }

    /**
     * Functionality: get users' inputs and request service to view all courses of a student in a semester
     */
    public void handleViewAllCourseOfAStudentInASemester() throws IOException {
        // Get input(s)
        String sId = input("Enter student id: ");
        String semester = input("Enter semester: ");

        // Call service to get filter list
        List<Course> coursesOfStudentBySemester = courseService.getCourseOfAStudentBySemester(sId, semester);

        // Check and display
        if (coursesOfStudentBySemester == null) {
            System.err.printf("Student with id %s does not exist!\n", sId);
        } else if (coursesOfStudentBySemester.isEmpty()) {
            System.err.printf("Student with id %s does not have any course in semester %s!\n", sId, semester);
        } else {
            Table.displayCourseTable(coursesOfStudentBySemester);

            // Ask if write to csv file
            boolean saveToCsv = input("Save those data to a csv file? (y: yes, any other keys: no):").equalsIgnoreCase("y");
            if (saveToCsv) {
                String filePath = "src/reports/course/courses_" + sId + "_" + semester + ".csv";
                CsvWriter.writeCourseToFile(filePath, coursesOfStudentBySemester);
                System.out.println("Written data to "+ filePath + "!");
            }
        }
    }
}
