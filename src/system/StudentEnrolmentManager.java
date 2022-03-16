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
    List<Enrolment> getAllEnrolments();

    /**
     * Functionality: find an enrolment by student id, course id, and semester
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return an object of Enrolment if found, null if not found
     */
    Enrolment getOneEnrolment(String sId, String cId, String semester);

    /**
     * Functionality: add an enrolment to the list
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return true if add successfully (not already existed), false if add unsuccessfully (already existed)
     */
    boolean addEnrolment(String sId, String cId, String semester);

    /**
     * Functionality: remove an enrolment from the list
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return true if remove successfully, false if remove unsuccessfully (not found)
     */
    boolean removeEnrolment(String sId, String cId, String semester);

    //============================ Methods for Courses Management ============================//

    /**
     * Functionality: get current courses in the system
     * @return a list of course
     */
    List<Course> getAllCourses();

    /**
     * Functionality: find a course by id
     * @param cId: course id
     * @return an object of Course if found, null if not found
     */
    Course getCourseById(String cId);

    /**
     * Functionality: add a course to the list
     * @param courseToAdd: course object to be added
     * @return true if add successfully (not already existed), false if add unsuccessfully (already existed)
     */
    boolean addCourse(Course courseToAdd);

    //============================ Methods for Students Management ============================//

    /**
     * Functionality: get all current students in the system
     * @return a list of student
     */
    List<Student> getAllStudent();

    /**
     * Functionality: find a student by id
     * @param sId: student id
     * @return an object of Student if found, null if not found
     */
    Student getStudentById(String sId);

    /**
     * Functionality: add a student to the list
     * @param studentToAdd: student object to be added
     * @return true if add successfully (not already existed), false if add unsuccessfully (already existed)
     */
    boolean addStudent(Student studentToAdd);

}
