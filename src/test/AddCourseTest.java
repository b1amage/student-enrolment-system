package test;

import exception.WrongCsvFormatException;

import model.Course;

import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AddCourseTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void addExistedCourse() {
        boolean isSuccess = system.addCourse(new Course("COSC3321",
                "Artificial Intelligence", 3));

        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void addNonExistedCourse() {
        boolean isSuccess = system.addCourse(new Course("COSC2083",
                "Web Programming", 8));

        assertTrue(isSuccess);
    }
}
