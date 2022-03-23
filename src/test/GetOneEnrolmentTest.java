package test;

import exception.WrongCsvFormatException;
import model.Enrolment;
import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;

public class GetOneEnrolmentTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void getExistEnrolment() {
        Enrolment e = system.getOneEnrolment("S102732", "COSC3321", "2021A");
        assertNotNull(e);
    }

    @org.junit.jupiter.api.Test
    void getNonExistEnrolment() {
        Enrolment e = system.getOneEnrolment("S1234", "COSC1234", "2021A");
        assertNull(e);
    }
}
