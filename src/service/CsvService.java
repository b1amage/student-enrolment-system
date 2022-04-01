package service;

import exception.WrongCsvFormatException;
import model.Course;
import model.Enrolment;
import model.Student;
import system.StudentEnrolmentManager;
import utility.csv.CsvValidator;
import utility.date.DateConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *  Class name: CsvService
 *  Role: Service class
 *  Functionalities: interact with csv file and process them to models
 */
public class CsvService {
    private final StudentEnrolmentManager manager;

    /**
     * Constructor
     * @param manager StudentEnrolmentSystem for accessing data
     */
    public CsvService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    /**
     * Functionality: convert a row (String) to a Student object
     * @param row a row (String) in csv file
     * @return a Student object
     * @throws ParseException cannot parse string with wrong format
     */
    private Student convertCsvRowToStudent(String row) throws ParseException {
        String[] fields = row.split(",");
        trimStringArray(fields);

        String sId = fields[0];
        String name = fields[1];
        Date birthday = DateConverter.convertStringToDate(fields[2]);

        return new Student(sId, name, birthday);
    }

    /**
     * Functionality: convert a row (String) to a Course object
     * @param row a row (String) in csv file
     * @return a Course object
     */
    private Course convertCsvRowToCourse(String row) {
        String[] fields = row.split(",");
        trimStringArray(fields);

        String cID = fields[3];
        String name = fields[4];
        int numberOfCredit = Integer.parseInt(fields[5]);

        return new Course(cID, name, numberOfCredit);
    }

    /**
     * Functionality: convert a row (String) to an Enrolment object
     * @param row a row (String) in csv file
     * @return an Enrolment object
     */
    private Enrolment convertCsvRowToEnrolment(String row) {
        String[] fields = row.split(",");
        trimStringArray(fields);

        String sId = fields[0];
        String cId = fields[3];
        String semester = fields[6];

        return new Enrolment(manager.getStudentById(sId), manager.getCourseById(cId), semester);
    }

    /**
     * Functionality: read csv, convert each row into Student object and add to a list
     * @param filePath: path of the csv file in the project
     * @return a list of Student object
     * @throws FileNotFoundException: wrong file path
     * @throws ParseException: cannot parse string with wrong format
     */
    public List<Student> getStudentsFromCsv(String filePath) throws FileNotFoundException, ParseException, WrongCsvFormatException {
        Scanner fileScanner = new Scanner(new File(filePath));
        String row;
        List<Student> students = new ArrayList<>();
        Student student; // storing temporary student

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();

            // Validate row
            if (!CsvValidator.isAValidCsvRow(row))
                throw new WrongCsvFormatException();

            if (!row.isEmpty()) {
                student = convertCsvRowToStudent(row);
                // Check if exits
                if (!isExist(student, students)) students.add(student);
            }
        }

        return students;
    }

    /**
     * Functionality: read csv, convert each row into Course object and add to a list
     * @param filePath: path of the csv file in the project
     * @return a list of Course object
     * @throws FileNotFoundException: wrong file path
     */
    public List<Course> getCoursesFromCsv(String filePath) throws FileNotFoundException, WrongCsvFormatException {
        Scanner fileScanner = new Scanner(new File(filePath));
        String row;
        List<Course> courses = new ArrayList<>();
        Course course; // storing temporary course

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();

            // Validate row
            if (!CsvValidator.isAValidCsvRow(row))
                throw new WrongCsvFormatException();

            if (!row.isEmpty()) {
                course = convertCsvRowToCourse(row);
                // Check if exits
                if (!isExist(course, courses)) courses.add(course);
            }
        }

        return courses;
    }

    /**
     * Functionality: read csv, convert each row into Enrolment object and add to a list
     * @param filePath: path of the csv file in the project
     * @return a list of Enrolment object
     * @throws FileNotFoundException: wrong file path
     */
    public List<Enrolment> getEnrolmentsFromCsv(String filePath) throws FileNotFoundException, WrongCsvFormatException {
        Scanner fileScanner = new Scanner(new File(filePath));
        String row;
        List<Enrolment> enrolments = new ArrayList<>();
        Enrolment enrolment; // storing temporary enrolment

        while (fileScanner.hasNextLine()) {
            row = fileScanner.nextLine();

            // Validate row
            if (!CsvValidator.isAValidCsvRow(row))
                throw new WrongCsvFormatException();

            if (!row.isEmpty()) {
                enrolment = convertCsvRowToEnrolment(row);
                // Check if exits
                if (!isExist(enrolment, enrolments)) enrolments.add(enrolment);
            }
        }

        return enrolments;
    }

    /**
     * Functionality: trim all redundant space of each String in an array of String
     * @param fields: an array of String
     */
    public static void trimStringArray(String[] fields) {
        for (String field : fields)
            field = field.trim();
    }

    /**
     *  Functionality: check if the student is already existed on a list by comparing id
     * @param student: student to be checked
     * @param students: list to be checked
     * @return true if existed, false if not existed
     */
    private boolean isExist(Student student, List<Student> students) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) return true;
        }


        return false;
    }

    /**
     *  Functionality: check if the course is already existed on a list by comparing id
     * @param course: course to be checked
     * @param courses: list to be checked
     * @return true if existed, false if not existed
     */
    private boolean isExist(Course course, List<Course> courses) {
        for (Course c : courses) {
            if (c.getId().equals(course.getId()))
                return true;
        }

        return false;
    }

    /**
     *  Functionality: check if the course is already existed on a list by comparing student id, course id, and semester
     * @param enrolment: enrolment to be checked
     * @param enrolments: list to be checked
     * @return true if existed, false if not existed
     */
    private boolean isExist(Enrolment enrolment, List<Enrolment> enrolments) {
        // Three criteria (sId, cId, semester)
        boolean sIdExits;
        boolean cIdExits;
        boolean semesterExits;

        for (Enrolment e : enrolments) {
            sIdExits = e.getStudent().getId().equals(enrolment.getStudent().getId());
            cIdExits = e.getCourse().getId().equals(enrolment.getCourse().getId());
            semesterExits = e.getSemester().equals(enrolment.getSemester());

            if (sIdExits  && cIdExits && semesterExits)  // 3 criteria must be true
                return true;
        }

        return false;
    }

    /**
     *  Functionality: check if the file does exist (used to validate user input)
     * @param fileName: the name of the file
     * @return true if it does exist, false when does not exist
     */
    public boolean isFileExist(String fileName) {
        // Create file path
        String filePath = "src/data/" + fileName;

        // Create new file on that path
        File file = new File(filePath);

        // Check if the file does exist, and it is not a directory
        return !file.isDirectory() && file.exists();
    }
}
