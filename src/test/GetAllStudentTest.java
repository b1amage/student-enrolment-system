package test;
import exception.WrongCsvFormatException;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class GetAllStudentTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void getStudentList() {
        List<Student> students = system.getAllStudent();
        assertEquals(10, students.size()); // There are total 10 students
    }
}
