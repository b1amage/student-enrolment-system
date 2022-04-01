package service;

import model.Enrolment;
import model.Student;
import system.StudentEnrolmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: StudentService
 * Role: Service class
 * Functionalities: Connect between user commands and the system
 * */
public class StudentService {
    private final StudentEnrolmentManager manager;

    /**
     * Constructor
     * @param manager: system with populated data
     */
    public StudentService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    /**
     * Functionality: get the list of students from the system
     * @return a list of student
     */
    public List<Student> getStudents() {
        return manager.getAllStudent();
    }

    /**
     * Functionality: get all student that enrol in a course in a semester
     * @param cId: course id
     * @param semester: semester
     * @return a list of student
     */
    public List<Student> getAllStudentInACourse(String cId, String semester) {
        if (manager.getCourseById(cId) == null) return null; // Student not exist

        List<Student> studentsByCourse = new ArrayList<>();

        for (Enrolment e : manager.getAllEnrolments())
            if (e.getCourse().getId().equals(cId) && e.getSemester().equals(semester))
                studentsByCourse.add(e.getStudent());

        return studentsByCourse; // isEmpty if semester not found
    }

    public Student getStudentById(String sId) {
        return manager.getStudentById(sId);
    }
}
