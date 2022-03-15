package system;

import model.Course;
import model.Enrolment;
import model.Student;

import java.util.List;

/**
 * Interface for the Enrolment System
 */
public interface StudentEnrolmentManager {

    //============================ Methods for Enrolments Management ============================//

    /**
     * Functionality: get current enrolments in the system
     * @return a list of enrolment
     */
    public List<Enrolment> getAllEnrolments();

    /**
     * Functionality: find an enrolment by student id, course id, and semester
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return an object of Enrolment if found, null if not found
     */
    public Enrolment getOneEnrolment(String sId, String cId, String semester);

    /**
     * Functionality: add an enrolment to the list
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return true if add successfully (not already existed), false if add unsuccessfully (already existed)
     */
    public boolean addEnrolment(String sId, String cId, String semester);

    /**
     * Functionality: remove an enrolment from the list
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return true if remove successfully, false if remove unsuccessfully (not found)
     */
    public boolean removeEnrolment(String sId, String cId, String semester);

    //============================ Methods for Courses Management ============================//

    /**
     * Functionality: get current courses in the system
     * @return a list of course
     */
    public List<Course> getAllCourses();

    /**
     * Functionality: find a course by id
     * @param cId: course id
     * @return an object of Course if found, null if not found
     */
    public Course getCourseById(String cId);

    /**
     * Functionality: add a course to the list
     * @param courseToAdd: course object to be added
     * @return true if add successfully (not already existed), false if add unsuccessfully (already existed)
     */
    public boolean addCourse(Course courseToAdd);

    //============================ Methods for Students Management ============================//

    /**
     * Functionality: get all current students in the system
     * @return a list of student
     */
    public List<Student> getAllStudent();

    /**
     * Functionality: find a student by id
     * @param sId: student id
     * @return an object of Student if found, null if not found
     */
    public Student getStudentById(String sId);

    /**
     * Functionality: add a student to the list
     * @param studentToAdd: student object to be added
     * @return true if add successfully (not already existed), false if add unsuccessfully (already existed)
     */
    public boolean addStudent(Student studentToAdd);

}
