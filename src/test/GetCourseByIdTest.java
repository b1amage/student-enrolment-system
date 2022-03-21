package test;
import exception.WrongCsvFormatException;
import model.Course;

import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class GetCourseByIdTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void getNonExistCourse() {
        Course c = system.getCourseById("COSC1234");
        assertNull(c);
    }

    @org.junit.jupiter.api.Test
    void getExistCourse() {
        Course c = system.getCourseById("COSC3321");
        assertNotNull(c);
        assertEquals("COSC3321", c.getId());
        assertEquals("Artificial Intelligence", c.getName());
        assertEquals(3, c.getNumberOfCredit());
    }
}
