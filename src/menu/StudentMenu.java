package menu;

import model.Student;
import service.StudentService;
import system.StudentEnrolmentManager;
import utility.csv.CsvWriter;
import utility.display.Table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: StudentMenu
 * Role: Client-interaction class
 * Functionalities: Get request from the user and call service functions to perform task
 * */
public class StudentMenu extends Menu {
    private static final ArrayList<String> commands =
            new ArrayList<>() {{
                add("View all students");
                add("View all students in a course");
                add("Back");
            }};

    private final StudentService studentService;

    /**
     * Constructor
     * @param manager: StudentEnrolmentManager system with populated data
     */
    public StudentMenu(StudentEnrolmentManager manager) {
        this.studentService = new StudentService(manager);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            showMenu();
            String option = getOption();

            switch (option) {
                case "1":
                    handleViewAllStudent();
                    break;
                case "2":
                    handleViewAllStudentInACourseInASemester();
                    break;
                case "3":
                    return;
            }
        }
    }

    @Override
    public List<String> getCommands() {
        return commands;
    }

    /**
     * Functionality: request the student service to get all student
     */
    public void handleViewAllStudent() {
        Table.displayStudentTable(studentService.getStudents());
    }

    /**
     * Functionality: request the student service to get student in a course in a semester
     * @throws IOException: file cannot be opened
     */
    public void handleViewAllStudentInACourseInASemester() throws IOException {
        // Get inputs
        String cId = input("Enter course ID: ");
        String semester = input("Enter semester: ");

        // Query
        List<Student> studentsByCourse = studentService.getAllStudentInACourse(cId, semester);

        // Display
        if (studentsByCourse == null) {
            System.out.printf("Course with id %s is not exist!\n", cId);
        } else if (studentsByCourse.isEmpty()) {
            System.out.printf("Course with id %s may not be available at semester %s!\n", cId, semester);
        } else {
            Table.displayStudentTable(studentsByCourse);

            // Ask if write to CSV file
            boolean saveToCsv = input("Save those data to a csv file? (y/n)").equalsIgnoreCase("y");
            if (saveToCsv) {
                String filePath = "src/reports/student/students_" + cId + "_" + semester + ".csv";
                CsvWriter.writeStudentToFile(filePath, studentsByCourse);
                System.out.println("Written data to "+ filePath + "!");
            }
        }
    }
}
