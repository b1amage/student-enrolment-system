package system;

import model.Course;
import model.Enrolment;
import model.Student;

import java.util.List;

public interface StudentEnrolmentManager {

    /** Methods for Enrolments Management **/
    public List<Enrolment> getAllEnrolments();
    public Enrolment getOneEnrolment(String sId, String cId, String semester);
    public boolean addEnrolment(String sId, String cId, String semester);
    public boolean removeEnrolment(String sId, String cId, String semester);

    /** Methods for Courses Management **/
    public List<Course> getAllCourses();
    public Course getCourseById(String cId);
    public boolean addCourse(Course courseToAdd);

    /** Methods for Courses Management **/
    public List<Student> getAllStudent();
    public Student getStudentById(String sId);
    public boolean addStudent(Student studentToAdd);

}
