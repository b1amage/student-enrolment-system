package model;

/**
 * Class name: Course
 * Role: Model class
 * Functionalities: represent Course
 * */
public class Course {
    private String id;
    private String name;
    private int numberOfCredit;

    /**
     * Constructor
     * @param id: a string represent course code. Ex: COSC2440
     * @param name: a string represent course name. Ex: Software Architecture
     * @param numberOfCredit: an integer number. Ex: 12
     */
    public Course(String id, String name, int numberOfCredit) {
        this.id = id;
        this.name = name;
        this.numberOfCredit = numberOfCredit;
    }

    /**
     * Getter for course id
     * @return course id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for course name
     * @return course name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for credit
     * @return number of credit
     */
    public int getNumberOfCredit() {
        return numberOfCredit;
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
