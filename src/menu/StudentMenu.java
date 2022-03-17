package menu;

import model.Student;
import service.StudentService;
import system.StudentEnrolmentManager;
import utility.display.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    }
}
