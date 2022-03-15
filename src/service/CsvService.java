package service;

import model.Course;
import model.Enrolment;
import model.Student;
import system.StudentEnrolmentManager;
import utility.date.DateConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CsvService {
    private StudentEnrolmentManager manger;
    private static Scanner fileScanner;

    static {
        try {
            fileScanner = new Scanner(new File("src/default.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CsvService(StudentEnrolmentManager manager) {
        this.manger = manager;
    }

    private Student convertCsvRowToStudent(String line) throws ParseException {
        String[] fields = line.split(",");
        trimStringArray(fields);

        String sId = fields[0];
        String name = fields[1];
        Date birthday = DateConverter.convertStringToDate(fields[2]);

        return new Student(sId, name, birthday);
    }

    private Course convertCsvRowToCourse(String line) {
        String[] fields = line.split(",");
        trimStringArray(fields);

        String cID = fields[3];
        String name = fields[4];
        int numberOfCredit = Integer.parseInt(fields[5]);

        return new Course(cID, name, numberOfCredit);
    }

    private Enrolment convertCsvRowToEnrolment(String line) {
        String[] fields = line.split(",");
        trimStringArray(fields);

        String sId = fields[0];
        String cId = fields[3];
        String semester = fields[6];

        return new Enrolment(manger.getStudentById(sId), manger.getCourseById(cId), semester);
    }

    public List<Student> getStudentsFromCsv(String fileName) {

        return null;
    }

    public List<Course> getCoursesFromCsv(String fileName) {
        return null;
    }

    public List<Course> getEnrolmentsFromCsv(String fileName) {
        return null;
    }

    public static void trimStringArray(String[] fields) {
        for (String field : fields) {
            field = field.trim();
        }
    }
}
