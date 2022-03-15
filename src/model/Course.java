package model;

public class Course {
    private String id;
    private String name;
    private int numberOfCredit;

    public Course(String id, String name, int numberOfCredit) {
        this.id = id;
        this.name = name;
        this.numberOfCredit = numberOfCredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberOfCredit=" + numberOfCredit +
                '}';
    }
}
