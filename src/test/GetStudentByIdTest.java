package test;
import exception.WrongCsvFormatException;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import system.StudentEnrolmentSystem;
import utility.date.DateConverter;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class GetStudentByIdTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void getNonExistStudent() {
        Student s = system.getStudentById("S12345");
        assertNull(s);
    }

    @org.junit.jupiter.api.Test
    void getExistStudent() throws ParseException {
        Student s = system.getStudentById("S103192");
        assertNotNull(s);
        assertEquals("S103192", s.getId());
        assertEquals("Ngan Thu Vo", s.getName());
        assertEquals(DateConverter.convertStringToDate("3/9/1998"), s.getBirthday());
    }
}
