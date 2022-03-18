package service;

import model.Course;
import model.Enrolment;
import system.StudentEnrolmentManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseService {
    private final StudentEnrolmentManager manager;

    public CourseService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    public List<Course> getCourses() {
        return manager.getAllCourses();
    }

    public List<Course> getCourseBySemester(String semester) {
        Set<Course> courses = new HashSet<>(); // Avoid duplicate

        for (Enrolment e : manager.getAllEnrolments()) {
            if (e.getSemester().equals(semester)) {
                courses.add(e.getCourse());
            }
        }
        List<Course> courseBySemester = new ArrayList<>(courses);

        return courseBySemester.isEmpty() ? null : courseBySemester; // if there is no course, return null
    }

    public List<Course> getCourseOfAStudentBySemester(String sId, String semester) {
        // Check if student exists
        if (manager.getStudentById(sId) == null) {
            return null;
        }

        // Create list for storing result
        List<Course> coursesOfStudentBySemester = new ArrayList<>();

        // Loop through enrolment list
        for (Enrolment e : manager.getAllEnrolments()) {
            if (e.getStudent().getId().equals(sId) && e.getSemester().equals(semester)) {
                coursesOfStudentBySemester.add(e.getCourse());
            }
        }

        return coursesOfStudentBySemester; // empty if there is no course at that semester
    }

}
