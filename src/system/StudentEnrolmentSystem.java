package system;

import model.Course;
import model.Enrolment;
import model.Student;
import service.CsvService;

import java.util.ArrayList;
import java.util.List;

public class StudentEnrolmentSystem implements StudentEnrolmentManager{
    private List<Student> studentList;
    private List<Course> courseList;
    private List<Enrolment> enrolmentList;
    private CsvService csvService;

    public StudentEnrolmentSystem() {
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        enrolmentList = new ArrayList<>();
        csvService = new CsvService(this);
    }

    public void populateData() {
        populateStudents();
        populateCourses();
        populateEnrolments();
    }

    private void populateStudents() {

    }

    private void populateCourses() {

    }

    private void populateEnrolments() {

    }

    @Override
    public List<Enrolment> getAllEnrolments() {
        return null;
    }

    @Override
    public Enrolment getOneEnrolment(String sId, String cId, String semester) {
        return null;
    }

    @Override
    public boolean addEnrolment(String sId, String cId, String semester) {
        return false;
    }

    @Override
    public boolean removeEnrolment(String sId, String cId, String semester) {
        return false;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public Course getCourseById(String cId) {
        return null;
    }

    @Override
    public boolean addCourse(Course courseToAdd) {
        return false;
    }

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public Student getStudentById(String sId) {
        return null;
    }

    @Override
    public boolean addStudent(Student studentToAdd) {
        return false;
    }
}
