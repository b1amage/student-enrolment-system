package test;

import exception.WrongCsvFormatException;
import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveEnrolmentTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void removeNonExistedEnrolment() {
        boolean isSuccess = system.addEnrolment("S102732", "COSC3321", "2021A");
        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void removeNonExistedStudent() {
        boolean isSuccess = system.addEnrolment("S12345", "COSC3321", "2020C");
        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void removeNonExistedCourse() {
        boolean isSuccess = system.addEnrolment("S102732", "COSC1234", "2020C");
        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void removeExistedEnrolment() {
        boolean isSuccess = system.addEnrolment("S102732", "COSC3321", "2020C");
        assertTrue(isSuccess);
    }
}
