package service;

import model.Enrolment;
import model.Student;
import system.StudentEnrolmentManager;


import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final StudentEnrolmentManager manager;

    public StudentService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    public List<Student> getStudents() {
        return manager.getAllStudent();
    }

    public List<Student> getAllStudentInACourse(String cId, String semester) {
        List<Student> studentsByCourse = new ArrayList<>();

        for (Enrolment e : manager.getAllEnrolments()) {
            if (e.getCourse().getId().equals(cId) && e.getSemester().equals(semester)) {
                studentsByCourse.add(e.getStudent());
            }
        }

        return studentsByCourse.isEmpty() ? null : studentsByCourse;
    }
}
