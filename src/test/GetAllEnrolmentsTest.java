package test;
import exception.WrongCsvFormatException;
import model.Enrolment;
import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllEnrolmentsTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void getEnrolmentList() {
        List<Enrolment> enrolments = system.getAllEnrolments();
        assertEquals(15, enrolments.size());
    }
}
