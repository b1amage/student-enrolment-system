package test;

import exception.WrongCsvFormatException;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AddEnrolmentTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void addNonExistCourse() {
        //        S102732,Mark Duong,8/28/2001,COSC3321,Artificial Intelligence,3,2021A
        boolean isSuccess = system.addEnrolment("S102732", "COSC1234", "2020C");
        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void addNonExistStudent() {
        boolean isSuccess = system.addEnrolment("S12345", "COSC3321", "2020C");
        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void addExistedEnrolment() {
        boolean isSuccess = system.addEnrolment("S102732", "COSC3321", "2021A");
        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void addValidCourse() {
        boolean isSuccess = system.addEnrolment("S102732", "BUS2232", "2020C");
        assertTrue(isSuccess);
    }
}
