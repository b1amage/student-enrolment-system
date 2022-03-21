package system;

import exception.WrongCsvFormatException;
import model.Course;
import model.Enrolment;
import model.Student;

import service.CsvService;

import java.io.FileNotFoundException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
//        String fileName = getFileName();
        populateStudents(fileName);
        populateCourses(fileName);
        populateEnrolments(fileName);
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
        enrolmentList.clear();
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

            if (sIdExits && cIdExits && semesterExits) {
                return e;
            }
        }

        return null;
    }

    @Override
    public boolean addEnrolment(String sId, String cId, String semester) {
        if (getOneEnrolment(sId, cId, semester) != null) return false; // Enrolment existed
        if (getStudentById(sId) == null) return false; // Student not exist
        if (getCourseById(cId) == null) return false; // Course not exist

        enrolmentList.add(new Enrolment(getStudentById(sId), getCourseById(cId), semester));
        return true;
    }

    @Override
    public boolean removeEnrolment(String sId, String cId, String semester) {
        if (getStudentById(sId) == null) return false; // Student not exist
        if (getCourseById(cId) == null) return false; // Course not exist

        Enrolment enrolmentToRemove = getOneEnrolment(sId, cId, semester);
        if (enrolmentToRemove == null) return false; // Enrolment not exist

        enrolmentList.remove(enrolmentToRemove);
        return true;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }

    @Override
    public Course getCourseById(String cId) {
        for (Course c : courseList) {
            if (c.getId().equals(cId)) return c;
        }

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
        for (Student s : studentList) {
            if (s.getId().equals(sId)) return s;
        }

        return null;
    }

    @Override
    public boolean addStudent(Student studentToAdd) {
        if (getStudentById(studentToAdd.getId()) != null) {
            return false;
        }

        studentList.add(studentToAdd);
        return true;
    }

    /**
     * Functionality: Get file name from the user. If user do not want to use their file, use default file
     * @return: a String of fileName
     */
    public String getFileName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("You want to use your own file? (y/n): ");

        String fileName = "";
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Enter file name (with the .csv): ");
            fileName = sc.nextLine().trim();
        } else {
            System.out.println("Using default file of the system...");
        }

        return fileName;
    }
}
