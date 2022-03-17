package service;

import model.Enrolment;
import system.StudentEnrolmentManager;

import java.util.List;

public class EnrolmentService {
    private final StudentEnrolmentManager manager;

    public EnrolmentService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    public List<Enrolment> getEnrolments() {
        return manager.getAllEnrolments();
    }

    public boolean enrolCourse(String sId, String cId, String semester) {
        // Check sid exist
        if (manager.getStudentById(sId) == null) return false;

        // Check cid exit
        if (manager.getCourseById(cId) == null) return false;

        // Enrol
        return manager.addEnrolment(sId, cId, semester);
    }

    public boolean dropCourse(String sId, String cId, String semester) {
        // Check sid exist
        if (manager.getStudentById(sId) == null) return false;

        // Check cid exit
        if (manager.getCourseById(cId) == null) return false;

        // Drop
        return manager.removeEnrolment(sId, cId, semester);
    }
}
