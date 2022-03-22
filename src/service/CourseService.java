package service;

import model.Course;
import model.Enrolment;
import system.StudentEnrolmentManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class name: CourseService
 * Role: Service class
 * Functionalities: Connect between user commands and the system
 * */
public class CourseService {
    private final StudentEnrolmentManager manager;

    /**
     * Constructor
     * @param manager: system with populated data
     */
    public CourseService(StudentEnrolmentManager manager) {
        this.manager = manager;
    }

    /**
     * Functionality: get the list of courses
     * @return a list of Course
     */
    public List<Course> getCourses() {
        return manager.getAllCourses();
    }

    /**
     * Functionality: get course with semester as criteria
     * @param semester: semester provide by user
     * @return a list of course
     */
    public List<Course> getCourseBySemester(String semester) {
        Set<Course> courses = new HashSet<>(); // Avoid duplicate

        for (Enrolment e : manager.getAllEnrolments()) {
            if (e.getSemester().equals(semester)) courses.add(e.getCourse());
        }

        List<Course> courseBySemester = new ArrayList<>(courses); // cast back to array list

        return courseBySemester.isEmpty() ? null : courseBySemester; // if there is no course, return null
    }

    /**
     * Functionality: get course with semester and student id as criteria
     * @param sId: student id
     * @param semester: semester
     * @return a list of course
     */
    public List<Course> getCourseOfAStudentBySemester(String sId, String semester) {
        // Check if student exists
        if (manager.getStudentById(sId) == null) return null;

        // Create list for storing result
        List<Course> coursesOfStudentBySemester = new ArrayList<>();

        // Loop through enrolment list
        for (Enrolment e : manager.getAllEnrolments()) {
            if (e.getStudent().getId().equals(sId) && e.getSemester().equals(semester))
                coursesOfStudentBySemester.add(e.getCourse());
        }

        return coursesOfStudentBySemester; // empty if there is no course at that semester
    }

}
