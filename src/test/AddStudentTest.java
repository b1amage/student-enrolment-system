package test;

import exception.WrongCsvFormatException;
import model.Student;
import system.StudentEnrolmentSystem;
import utility.date.DateConverter;

import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AddStudentTest {
    private StudentEnrolmentSystem system;

    @BeforeEach
    void setup() throws FileNotFoundException, ParseException, WrongCsvFormatException {
        system = new StudentEnrolmentSystem();
        system.populateData(""); // use default file
    }

    @org.junit.jupiter.api.Test
    void addExistedStudent() throws ParseException {
        //       S102732,Mark Duong,8/28/2001,COSC3321,Artificial Intelligence,3,2021A
        boolean isSuccess = system.addStudent(new Student("S102732", "Mark Duong",
                DateConverter.convertStringToDate("8/28/2001")));

        assertFalse(isSuccess);
    }

    @org.junit.jupiter.api.Test
    void addNonExistedStudent() throws ParseException {
        boolean isSuccess = system.addStudent(new Student("S38776", "Quoc Bao",
                DateConverter.convertStringToDate("8/10/2002")));

        assertTrue(isSuccess);
    }
}
