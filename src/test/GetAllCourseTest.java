package test;

import exception.WrongCsvFormatException;
import model.Course;

import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class GetAllCourseTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void getCourseList() {
        List<Course> courses = system.getAllCourses();
        assertEquals(4, courses.size()); // There are total 4 courses
    }
}
