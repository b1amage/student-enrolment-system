package model;

import utility.date.DateConverter;

import java.util.Date;

/**
 * Class name: Student
 * Role: Model class
 * Functionalities: represent student
 * */
public class Student {
    private String id;
    private String name;
    private Date birthday;

    /**
     * Constructor
     * @param id: a string of student id. Ex: s3877698
     * @param name a string of student name. Ex: Kyle Nguyen
     * @param birthday a Date object
     */
    public Student(String id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    /**
     * Getter for id
     * @return student id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for name
     * @return student name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for birthday
     * @return birthday date object
     */
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + DateConverter.convertDateToString(birthday) +
                '}';
    }
}
