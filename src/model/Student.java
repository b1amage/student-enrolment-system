package model;

import utility.date.DateConverter;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private Date birthday;

    public Student(String id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
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
