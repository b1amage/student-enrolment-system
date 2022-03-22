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

    /**
     * Constructor
     * @param manager: system with populated data
     */
    public EnrolmentService(StudentEnrolmentManager manager) {
        this.manager = manager;
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
        // Check sid exist
        if (manager.getStudentById(sId) == null) return false;

        // Check cid exit
        if (manager.getCourseById(cId) == null) return false;

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
        // Check sid exist
        if (manager.getStudentById(sId) == null) return false;

        // Check cid exit
        if (manager.getCourseById(cId) == null) return false;

        // Drop
        return manager.removeEnrolment(sId, cId, semester);
    }
}
