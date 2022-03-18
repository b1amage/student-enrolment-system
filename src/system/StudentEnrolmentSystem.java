package system;

import model.Course;
import model.Enrolment;
import model.Student;

import service.CsvService;

import java.io.FileNotFoundException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

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

    private static final String FILE_PATH = "src/data/default.csv"; // default data given by requirement

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
    public void populateData() throws FileNotFoundException, ParseException {
        populateStudents();
        populateCourses();
        populateEnrolments();
    }

    /**
     * Functionality: populate student data for the system
     * @throws FileNotFoundException: wrong file path
     * @throws ParseException: cannot parse string with wrong format
     */
    private void populateStudents() throws FileNotFoundException, ParseException {
        studentList.clear(); // clear all data before populate
        studentList.addAll(csvService.getStudentsFromCsv(FILE_PATH));
    }

    /**
     * Functionality: populate course data for the system
     * @throws FileNotFoundException: wrong file path
     */
    private void populateCourses() throws FileNotFoundException {
        courseList.clear(); // clear all data before populate
        courseList.addAll(csvService.getCoursesFromCsv(FILE_PATH));
    }

    /**
     * Functionality: populate enrolment data for the system
     * @throws FileNotFoundException: wrong file path
     */
    private void populateEnrolments() throws FileNotFoundException {
        enrolmentList.clear();
        enrolmentList.addAll(csvService.getEnrolmentsFromCsv(FILE_PATH));
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
        if (getOneEnrolment(sId, cId, semester) != null) {
            return false;
        }

        enrolmentList.add(new Enrolment(getStudentById(sId), getCourseById(cId), semester));
        return true;
    }

    @Override
    public boolean removeEnrolment(String sId, String cId, String semester) {
        Enrolment enrolmentToRemove = getOneEnrolment(sId, cId, semester);
        if (enrolmentToRemove == null) {
            return false;
        }

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
            if (c.getId().equals(cId)) {
                return c;
            }
        }

        return null;
    }

    @Override
    public boolean addCourse(Course courseToAdd) {
        if (getCourseById(courseToAdd.getId()) != null) {
            return false;
        }

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
            if (s.getId().equals(sId)) {
                return s;
            }
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
}
