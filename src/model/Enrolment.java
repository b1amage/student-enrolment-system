package model;

/**
 * Class name: Course
 * Role: Model class
 * Functionalities: represent student enrolment
 * */
public class Enrolment {
    private Student student;
    private Course course;
    private String semester;

    /**
     * Constructor
     * @param student: a student object
     * @param course: a course object
     * @param semester: a string of semester. Ex: 2021A
     */
    public Enrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    /**
     * Getter for student
     * @return a student object
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Getter for course
     * @return a course object
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Getter for semester
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "Enrolment{" +
                "student=" + student +
                ", course=" + course +
                ", semester='" + semester + '\'' +
                '}';
    }
}
