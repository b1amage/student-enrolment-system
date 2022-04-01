package service;

import model.Enrolment;
import system.StudentEnrolmentManager;

import java.util.List;

/**
 * Class name: EnrolmentService
 * Role: Service class
 * Functionalities: Connect between user commands and the system
 * */
public class EnrolmentService {
    private final StudentEnrolmentManager manager;
    private final CourseService courseService;

    /**
     * Constructor
     * @param manager: system with populated data
     */
    public EnrolmentService(StudentEnrolmentManager manager) {
        this.manager = manager;
        courseService = new CourseService(manager);
    }

    /**
     * Functionality: get the list of enrolments from the system
     * @return a list of enrolment
     */
    public List<Enrolment> getEnrolments() {
        return manager.getAllEnrolments();
    }

    /**
     * Functionality: enrol a student into a course in a semester
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return true if enrol successfully, false if enrol fail
     */
    public boolean enrolCourse(String sId, String cId, String semester) {
        // Course not exist
        if (manager.getCourseById(cId) == null) {
            System.err.println("Course does not exist!");
            return false;
        }

        // Course not available at that semester
        if (!courseService.getCourseBySemester(semester).contains(manager.getCourseById(cId))) {
            System.err.println("Course is not available at " + semester);
            return false;
        }
        // Enrol
        return manager.addEnrolment(sId, cId, semester);
    }

    /**
     * Functionality: drop a course of a student in a semester
     * @param sId: student id
     * @param cId: course id
     * @param semester: semester
     * @return true if drop successfully, false if drop fail
     */
    public boolean dropCourse(String sId, String cId, String semester) {
        // Drop
        return manager.removeEnrolment(sId, cId, semester);
    }
}
