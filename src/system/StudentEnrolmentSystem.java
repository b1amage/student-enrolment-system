package system;

import exception.WrongCsvFormatException;
import model.Course;
import model.Enrolment;
import model.Student;
import service.CsvService;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

/**
 * Class: StudentEnrolmentSystem
 * Role: Represent the system
 * Functionalities: the enrolment system to process request from the user
 */
public class StudentEnrolmentSystem implements StudentEnrolmentManager {
    private final List<Student> studentList;
    private final List<Course> courseList;
    private final List<Enrolment> enrolmentList;
    private final CsvService csvService;

    private static final String DEFAULT_FILE_PATH = "src/data/default.csv"; // default data given by requirement

    /**
     * Constructor
     */
    public StudentEnrolmentSystem() {
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        enrolmentList = new ArrayList<>();
        csvService = new CsvService(this);
    }

    /**
     * Functionality: populate data for the system
     * @throws FileNotFoundException: wrong file path
     * @throws ParseException: cannot parse string with wrong format
     */
    public void populateData(String fileName) throws FileNotFoundException, ParseException, WrongCsvFormatException {
        try {
            populateStudents(fileName);
            populateCourses(fileName);
            populateEnrolments(fileName);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("File " + fileName + " cannot be found!");
            System.exit(0); // Stop the program
        } catch (WrongCsvFormatException wrongCsvFormatException) {
            System.err.println("CSV was written in wrong format or a field is null!");
            System.exit(0); // Stop the program
        }
    }

    /**
     * Functionality: populate student data for the system
     * @throws FileNotFoundException: wrong file path
     * @throws ParseException: cannot parse string with wrong format
     */
    private void populateStudents(String fileName) throws FileNotFoundException, ParseException, WrongCsvFormatException {
        studentList.clear(); // clear all data before populate
        if (fileName.isEmpty()) {
            studentList.addAll(csvService.getStudentsFromCsv(DEFAULT_FILE_PATH));
        } else {
            String filePath = "src/data/" + fileName;
            studentList.addAll(csvService.getStudentsFromCsv(filePath));
        }
    }

    /**
     * Functionality: populate course data for the system
     * @throws FileNotFoundException: wrong file path
     */
    private void populateCourses(String fileName) throws FileNotFoundException, WrongCsvFormatException {
        courseList.clear(); // clear all data before populate
        if (fileName.isEmpty()) {
            courseList.addAll(csvService.getCoursesFromCsv(DEFAULT_FILE_PATH));
        } else {
            String filePath = "src/data/" + fileName;
            courseList.addAll(csvService.getCoursesFromCsv(filePath));
        }
    }

    /**
     * Functionality: populate enrolment data for the system
     * @throws FileNotFoundException: wrong file path
     */
    private void populateEnrolments(String fileName) throws FileNotFoundException, WrongCsvFormatException {
        enrolmentList.clear(); // clear all data before populate
        if (fileName.isEmpty()) {
            enrolmentList.addAll(csvService.getEnrolmentsFromCsv(DEFAULT_FILE_PATH));
        } else {
            String filePath = "src/data/" + fileName;
            enrolmentList.addAll(csvService.getEnrolmentsFromCsv(filePath));
        }
    }

    @Override
    public List<Enrolment> getAllEnrolments() {
        return enrolmentList;
    }

    @Override
    public Enrolment getOneEnrolment(String sId, String cId, String semester) {
        boolean sIdExits;
        boolean cIdExits;
        boolean semesterExits;

        for (Enrolment e : enrolmentList) {
            sIdExits = e.getStudent().getId().equals(sId);
            cIdExits = e.getCourse().getId().equals(cId);
            semesterExits = e.getSemester().equals(semester);

            if (sIdExits && cIdExits && semesterExits) return e;
        }

        return null;
    }

    @Override
    public boolean addEnrolment(String sId, String cId, String semester) {
        // Enrolment existed
        if (getOneEnrolment(sId, cId, semester) != null) {
            System.err.println("Enrolment existed!");
            return false;
        }

        // Student not exist
        if (getStudentById(sId) == null) {
            System.err.println("Student does not exist");
            return false;
        }

        // Course not exist
        if (getCourseById(cId) == null) {
            System.err.println("Course does not exist");
            return false;
        }

        enrolmentList.add(new Enrolment(getStudentById(sId), getCourseById(cId), semester));
        return true;
    }

    @Override
    public boolean removeEnrolment(String sId, String cId, String semester) {
        // Student not exist
        if (getStudentById(sId) == null) {
            System.err.println("Student does not exist");
            return false;
        }

        // Course not exist
        if (getCourseById(cId) == null) {
            System.err.println("Course does not exist");
            return false;
        }

        // Enrolment not exist
        Enrolment enrolmentToRemove = getOneEnrolment(sId, cId, semester);
        if (enrolmentToRemove == null) {
            System.err.println("Enrolment does not exist");
            return false;
        }

        // All verifications passed
        enrolmentList.remove(enrolmentToRemove);
        return true;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }

    @Override
    public Course getCourseById(String cId) {
        for (Course c : courseList)
            if (c.getId().equals(cId)) return c;

        return null;
    }

    @Override
    public boolean addCourse(Course courseToAdd) {
        if (getCourseById(courseToAdd.getId()) != null) return false;

        courseList.add(courseToAdd);
        return true;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentList;
    }

    @Override
    public Student getStudentById(String sId) {
        for (Student s : studentList)
            if (s.getId().equals(sId)) return s;

        return null;
    }

    @Override
    public boolean addStudent(Student studentToAdd) {
        if (getStudentById(studentToAdd.getId()) != null) return false;

        studentList.add(studentToAdd);
        return true;
    }

    /**
     * Functionality: Get file name from the user. If user do not want to use their file, use default file
     * @return: a String of fileName
     */
    public String getFileName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You want to use your own file? (y: yes, any other keys: no): ");

        String fileName = "";
        if (sc.nextLine().equalsIgnoreCase("y")) {
            do {
                System.out.println("Enter file name (with the .csv): ");
                fileName = sc.nextLine().trim();

                if (!csvService.isFileExist(fileName)) {
                    System.err.println("File cannot be found. Please try again (with the .csv): ");
                }
            } while (!csvService.isFileExist(fileName));

            System.out.println("Populating " + fileName + "...");

        } else {
            System.out.println("Populating default file of the system...");
        }

        return fileName;
    }
}
