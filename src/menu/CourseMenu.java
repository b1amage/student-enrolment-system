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
            new ArrayList() {{
                add("View all courses");
                add("View all courses in a semester");
                add("View all courses of a student in a semester");
                add("Back");
            }};

    private CourseService courseService;

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
                    viewAllCoursesInASemester();
                    break;
                case "3":
                    viewAllCourseOfAStudentInASemester();
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

    public void viewAllCourses() {
        Table.displayCourseTable(courseService.getCourses());
    }

    public void viewAllCoursesInASemester() throws IOException {
        // Input semester
        System.out.println("Enter semester: ");
        String semester = sc.nextLine().trim();

        // Call service to get filtered list
        List<Course> coursesBySemester = courseService.getCourseBySemester(semester);

        // Check and display
        if (coursesBySemester == null) {
            System.out.printf("There is no course on semester %s!\n", semester);
        } else {
            Table.displayCourseTable(coursesBySemester);

            // Ask if write to csv file
            System.out.println("Save those data to a csv file? (y/n)");
            if (sc.nextLine().trim().toLowerCase().equals("y")) {
                String filePath = "src/reports/course/courses_" + semester + ".csv";
                CsvWriter.writeCourseToFile(filePath, coursesBySemester);
            }
        }

    }

    public void viewAllCourseOfAStudentInASemester() throws IOException {
        // Input student id
        System.out.println("Enter student id: ");
        String sId = sc.nextLine().trim();

        // Input semester
        System.out.println("Enter semester: ");
        String semester = sc.nextLine().trim();

        // Call service to get filter list
        List<Course> coursesOfStudentBySemester = courseService.getCourseOfAStudentBySemester(sId, semester);

        // Check and display
        if (coursesOfStudentBySemester == null) {
            System.out.printf("Student with id %s does not exist!\n", sId);
        } else if (coursesOfStudentBySemester.isEmpty()) {
            System.out.printf("Student with id %s does not have any course in semester %s!\n", sId, semester);
        } else {
            Table.displayCourseTable(coursesOfStudentBySemester);

            // Ask if write to csv file
            System.out.println("Save those data to a csv file? (y/n)");
            if (sc.nextLine().trim().toLowerCase().equals("y")) {
                String filePath = "src/reports/course/courses_" + sId + "_" + semester + ".csv";
                CsvWriter.writeCourseToFile(filePath, coursesOfStudentBySemester);
            }
        }
    }
}
