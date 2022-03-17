package menu;

import model.Student;
import service.StudentService;
import system.StudentEnrolmentManager;
import utility.display.Table;

import java.util.ArrayList;
import java.util.List;

public class StudentMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList() {{
                add("View all students");
                add("View all students in a course");
                add("Back");
            }};

    private StudentService studentService;

    public StudentMenu(StudentEnrolmentManager manager) {
        this.studentService = new StudentService(manager);
    }

    @Override
    public void run() {

    }

    @Override
    public List<String> getCommands() {
        return commands;
    }

    public void viewAllStudent() {
        Table.displayStudentTable(studentService.getStudents());
    }

    public void viewAllStudentInACourse() {
        // Get inputs
        System.out.println("Enter course ID: ");
        String cId = sc.nextLine().trim();

        System.out.println("Enter semester: ");
        String semester = sc.nextLine().trim();

        // Query
        List<Student> studentsByCourse = studentService.getAllStudentInACourse(cId, semester);

        if (studentsByCourse == null) {
            System.out.printf("Course with id %s is not exist!\n", cId);
        } else if (studentsByCourse.isEmpty()) {
            System.out.printf("Course with id %s may not be available at semester %s!\n", cId, semester);
        } else {
            Table.displayStudentTable(studentsByCourse);
        }
    }
}
