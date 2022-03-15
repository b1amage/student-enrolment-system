package model;

public class Enrolment {
    private Student student;
    private Course course;
    private String semester;

    public Enrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
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
